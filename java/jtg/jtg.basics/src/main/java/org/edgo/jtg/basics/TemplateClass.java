package org.edgo.jtg.basics;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TemplateClass {

	public static final String						SCHEMA_PACKAGE			= "org.edgo.jtg.schema";

	public static final String						GENERATED_PACKAGE		= "org.edgo.jtg.generated";

	protected final Map<String, String>				cmdArgs;
	protected final Class<?>						derivedType;
	protected String								templateFileName;
	protected final String							encoding;
	protected final IEnvironment					env;

	protected final static Map<String, Integer>	linesMapping			= new HashMap<String, Integer>();

	protected final static Pattern					TEMPLATE_CLASS_PATTERN	= Pattern.compile("([^\\.]*_jtg)GeneratorClass");

	public TemplateClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, Object[] args) {
		this(derivedType, cmdArgs, env, "windows-1252", args);
	}

	public TemplateClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, String encoding, Object[] args) {
		this(derivedType, cmdArgs, env, encoding, (Map<String, Object>) null);
	}

	public TemplateClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, Map<String, Object> args) {
		this(derivedType, cmdArgs, env, "windows-1252", args);
	}

	public TemplateClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, String encoding,
			Map<String, Object> args) {
		if (cmdArgs == null) {
			this.cmdArgs = new HashMap<String, String>();
		} else {
			this.cmdArgs = cmdArgs;
		}
		if (!this.cmdArgs.containsKey("GeneratedPackage")) {
			this.cmdArgs.put("GeneratedPackage", GENERATED_PACKAGE);
		}
		if (!this.cmdArgs.containsKey("SchemaPackage")) {
			this.cmdArgs.put("SchemaPackage", SCHEMA_PACKAGE);
		}
		this.derivedType = derivedType;
		this.encoding = encoding;
		this.env = env;
	}

	public String getEncoding() {
		return encoding;
	}

	public abstract void Generate(PrintWriter output) throws TemplateException;

	public void Generate(String filename) throws TemplateException {
		PrintWriter writer = null;
		try {
			writer = env.getOutput(filename, encoding, GeneratorMode.MAKE_COPY);
			Generate(writer);
		} catch (TemplateException e) {
			throw e;
		} catch (EnvironmentException e) {
			throw new TemplateException("Error creating output file", e, filename);
		} finally {
			if (null != writer) {
				writer.close();
			}
			try {
				env.sync();
			} catch (Throwable e) {
				env.log("Can't sync the file", e);
			}
		}
	}

	public void Generate(OutputStream output) throws TemplateException {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new OutputStreamWriter(output, encoding));
			Generate(writer);
		} catch (UnsupportedEncodingException e) {
			throw new TemplateException("Error creating output file", e, templateFileName);
			/*} catch (Throwable e) {*/
		} finally {
			if (null != writer) {
				writer.close();
			}
		}
	}

	private void RunTemplate(String templateFile, PrintWriter output, Map<String, Object> args) throws TemplateException {
		Class<?> templateType;
		Constructor<?> ctor;
		TemplateClass template;
		String templateName = buildClassName(cmdArgs.get("GeneratedPackage"), templateFile);

		try {
			templateType = derivedType.getClassLoader().loadClass(templateName);
			if (null == templateType) {
				String message = "Can't find class \"" + templateName + "\" for template \"" + templateFile + "\"";
				throw new TemplateException(message, new NullPointerException(message), templateFile);
			}

			ctor = templateType.getConstructor(new Class[] {Class.class, Map.class, IEnvironment.class, args.getClass()});

			template = (TemplateClass) ctor.newInstance(new Object[] {null, cmdArgs, env, args});
			if (null == template) {
				String message = "Can't construct " + templateName + " as TemplateClass (see " + templateFile + ".";
				throw new TemplateException(message, new NullPointerException(message), templateFile);
			}
			template.Generate(output);
		} catch (TemplateException e) {
			throw e;
		} catch (Throwable ex) {
			StackTraceElement[] stackTraces = ex.getStackTrace();
			int srcLineNumber = -1;
            String className = null;
			for (StackTraceElement stackTrace : stackTraces) {
				if (stackTrace.getClassName().contains(this.getClass().getName())) {
					srcLineNumber = stackTrace.getLineNumber();
                    className = stackTrace.getClassName();
					break;
				}
			}
			if (srcLineNumber >= 0) {
				String key = className + ":" + srcLineNumber;
				if (linesMapping.containsKey(key)) {
					int lineNumber = (Integer) linesMapping.get(key);
					Matcher matcher = TEMPLATE_CLASS_PATTERN.matcher(ex.getMessage());
					String message = ex.toString();
					if (ClassNotFoundException.class.isInstance(ex) && matcher.find()) {
						message = "Template \"" + matcher.group(1) + "\" not found!";
					}
					throw new TemplateException(message, ex, templateFileName, lineNumber);
				}
			}
			throw new TemplateException(ex.getMessage(), ex, templateFile);
		} finally {
			template = null;
			ctor = null;
			templateType = null;
			try {
				env.sync();
			} catch (Throwable e) {
				env.log("Can't sync the file", e);
			}
		}
	}

	public void RunTemplate(String templateFile, String output, Map<String, Object> args) throws TemplateException {
		RunTemplate(templateFile, output, args, GeneratorMode.MAKE_COPY);
	}

	public void RunTemplate(String templateFile, String output, Map<String, Object> args, GeneratorMode generatorMode)
			throws TemplateException {
		PrintWriter writer = null;
		try {
			writer = env.getOutput(output, encoding, generatorMode);
			RunTemplate(templateFile, writer, args);
		} catch (TemplateException e) {
			throw e;
		} catch (EnvironmentException e) {
			throw new TemplateException("Error creating output file", e, templateFile);
		} finally {
			if (null != writer) {
				writer.close();
			}
			try {
				env.sync();
			} catch (Throwable e) {
				env.log("Can't sync the file", e);
			}
		}
	}

	public void RunTemplate(String templateFile, OutputStream output, Map<String, Object> args) throws TemplateException {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new OutputStreamWriter(output, encoding));
			if (encoding.toLowerCase().startsWith("utf")) {
				writer.write(0xFEFF);
			}
			RunTemplate(templateFile, writer, args);
		} catch (UnsupportedEncodingException e) {
			throw new TemplateException("Error creating output file: unknown encoding", e, templateFile);
		} finally {
			if (null != writer) {
				writer.close();
			}
		}
	}

	public void RunTemplate(String templateFile, PrintWriter output, Object... args) throws TemplateException {
		Class<?> templateType;
		Constructor<?> ctor;
		TemplateClass template;
		String templateName = buildClassName(cmdArgs.get("GeneratedPackage"), templateFile);
		try {

			templateType = derivedType.getClassLoader().loadClass(templateName);
			if (null == templateType) {
				String message = "Can't find class  \"" + templateName + "\" for template \"" + templateFile + "\"";
				throw new TemplateException(message, new NullPointerException(message), templateFile);
			}

			ctor = templateType.getConstructor(new Class[] {Class.class, Map.class, IEnvironment.class, Object[].class});

			template = (TemplateClass) ctor.newInstance(new Object[] {null, cmdArgs, env, args});
			if (null == template) {
				String message = "Can't construct " + templateName + " as TemplateClass (see " + templateFile + ".";
				throw new TemplateException(message, new NullPointerException(message), templateFile);
			}
			template.Generate(output);
		} catch (TemplateException e) {
			throw e;
		} catch (Throwable ex) {
			StackTraceElement[] stackTraces = ex.getStackTrace();
			int srcLineNumber = -1;
            String className = null;
			for (StackTraceElement stackTrace : stackTraces) {
				if (stackTrace.getClassName().contains(this.getClass().getName())) {
					srcLineNumber = stackTrace.getLineNumber();
                    className = stackTrace.getClassName();
					break;
				}
			}
			if (srcLineNumber >= 0) {
				String key = className + ":" + srcLineNumber;
				if (linesMapping.containsKey(key)) {
					int lineNumber = (Integer) linesMapping.get(key);
					Matcher matcher = TEMPLATE_CLASS_PATTERN.matcher(ex.getMessage());
					String message = ex.toString();
					if (ClassNotFoundException.class.isInstance(ex) && matcher.find()) {
						message = "Template \"" + matcher.group(1) + "\" not found!";
					}
					throw new TemplateException(message, ex, templateFileName, lineNumber);
				}
			}
			throw new TemplateException(ex.getMessage(), ex, templateFile);
		} finally {
			template = null;
			ctor = null;
			templateType = null;
		}
	}

	public void RunTemplate(String templateFile, String output, Object... args) throws TemplateException {
		RunTemplate(templateFile, output, GeneratorMode.MAKE_COPY, args);
	}

	public void RunTemplate(String templateFile, String output, GeneratorMode mode, Object... args) throws TemplateException {
		PrintWriter writer = null;
		try {
			writer = env.getOutput(output, encoding, mode);
			RunTemplate(templateFile, writer, args);
		} catch (TemplateException e) {
			throw e;
		} catch (EnvironmentException e) {
			throw new TemplateException("Error creating output file", e, templateFile);
		} finally {
			if (null != writer) {
				writer.close();
			}
			try {
				env.sync();
			} catch (Throwable e) {
				env.log("Can't sync the file", e);
			}
		}
	}

	public void RunTemplate(String templateFile, OutputStream output, Object... args) throws TemplateException {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new OutputStreamWriter(output, encoding));
			if (encoding.toLowerCase().startsWith("utf")) {
				writer.write(0xFEFF);
			}
			RunTemplate(templateFile, writer, args);
		} catch (UnsupportedEncodingException e) {
			throw new TemplateException("Error creating output file: unknown encoding", e, templateFile);
		} finally {
			if (null != writer) {
				writer.close();
			}
		}
	}

	private static String buildClassName(String generatedNamespace, String templateName) {
		StringBuilder sb = new StringBuilder(generatedNamespace);
		sb.append('.').append(templateName.replaceAll("[\\s\\.\\-/\\\\]", "_")).append("GeneratorClass");
		return sb.toString();
	}

}
