package org.edgo.jtg.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.jdt.internal.compiler.IProblemFactory;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;
import org.edgo.jtg.basics.LogMessage;
import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.core.compiler.CompilationUnit;
import org.edgo.jtg.core.compiler.CompilerRequestor;
import org.edgo.jtg.core.compiler.NameEnvironment;
import org.edgo.jtg.core.model.MacroLang;
import org.edgo.jtg.core.model.ParsedUnit;

public class JarCompiler {

	private SourceLineProcessor	sourceLineProcessor;
	private Map<String, Source>	sources;
	private Map<String, String>	jars;
	private ClassLoader			compilerClassLoader	= null;

	private String				sourceOutDir		= null;
	private String				jarOutDir			= null;
	private ClassLoader			parentClassLoader	= null;
	private String				projectName			= null;

	final Logger				log					= new Logger();

	/**
	 * <summary>
	 * Konstruktor, uebergeben wird die .
	 * Mit out_dir wird das Ausgabe Verzeichnisangegeben. Ist kein Verzeichnis (out_dir == null) angegeben
	 * so werden die SourceFiles nicht geschrieben.
	 * @param jarOutDir
	 * @param sourceOutDir
	 * @param projectName
	 */
	public JarCompiler(SourceLineProcessor sourceLineProcessor, String jarOutDir, String sourceOutDir,
			String projectName, ClassLoader parentClassLoader) {
		this.sourceLineProcessor = sourceLineProcessor;
		sources = new HashMap<String, Source>();
		jars = new HashMap<String, String>();
		this.jarOutDir = jarOutDir;
		this.projectName = projectName;
		this.parentClassLoader = parentClassLoader;
		if (sourceOutDir != null && !"".equals(sourceOutDir)) {
			this.sourceOutDir = sourceOutDir;
		}
	}

	public void AddSource(String raw_source, String className, MacroLang language, String filename, String generatedPackage,
			String encoding, ParsedUnit unit) throws TemplateException {
		try {
			if (language == MacroLang.NONE) {
				String message = "Unknown macro language in template '" + filename + "'";
				throw new TemplateException(message, new Exception(message), filename);
			}
			if (sources.containsKey(filename)) return;

			String full_name = GeneratorUtils.SourceOutFullPath(sourceOutDir, generatedPackage, className);

			sources.put(full_name, new Source(encoding, className, unit));

			write_to_file(raw_source, full_name, encoding);
		} catch (Exception ex) {
			throw new TemplateException(ex.getMessage(), ex, filename);
		}
	}

	// TODO
	public void AddJar(String jar, String templatefile) throws TemplateException {
		try {
			if (jars.containsKey(jar)) return;

			jars.put(jar, jar);
		} catch (Exception ex) {
			throw new TemplateException(ex.getMessage(), ex, templatefile);
		}
	}

	public ClassLoader getClassLoader(String templatefile) throws TemplateException {
		if (compilerClassLoader == null) {
			if (jars.size() > 0) {
				URL[] urls = new URL[jars.size()];
				int i = 0;
				for (String jar : jars.values()) {
					try {
						urls[i++] = new File(jar).toURL();
					} catch (MalformedURLException ex) {
						// TODO jar in node
						String message = "Can't find jar + \"" + jar + "\"";
						throw new TemplateException(message, new FileNotFoundException(message), templatefile);
					}
				}
				if (parentClassLoader != null) {
					compilerClassLoader = new URLClassLoader(urls, parentClassLoader);
				} else {
					compilerClassLoader = new URLClassLoader(urls, JarCompiler.class.getClassLoader());
				}
			} else {
				if (parentClassLoader != null) {
					compilerClassLoader = parentClassLoader;
				} else {
					compilerClassLoader = JarCompiler.class.getClassLoader();
				}
			}
		}
		return compilerClassLoader;
	}

	private long jvmLevelFromString(String version) {
		if (version.equals("1.1")) {
			return ClassFileConstants.JDK1_1;
		} else if (version.equals("1.2")) {
			return ClassFileConstants.JDK1_2;
		} else if (version.equals("1.3")) {
			return ClassFileConstants.JDK1_3;
		} else if (version.equals("1.4")) {
			return ClassFileConstants.JDK1_4;
		} else if (version.equals("1.5")) {
			return ClassFileConstants.JDK1_5;
		} else if (version.equals("1.6")) {
			return ClassFileConstants.JDK1_6;
		} else if (version.equals("1.7")) {
			return ClassFileConstants.JDK1_7;
		} else if (version.equals("1.8")) {
			return ClassFileConstants.JDK1_8;
		} else {
			log.warn("Unknown source VM " + version + " ignored.");
			return ClassFileConstants.JDK1_7;
		}
	}

	/*
	 * public void WriteSource(String source, String filename, String encoding) throws Exception { String full_name =
	 * GeneratorUtils.SourceOutFullPath(source_out_dir, filename); write_to_file(source, full_name, encoding); }
	 */
	public boolean Compile(String template_dir, String templatefile) throws TemplateException {
		try {
			File jarOutFile = new File(jarOutDir);
			if (!jarOutFile.exists()) {
				jarOutFile.mkdirs();
			}

			Map<String, CompilationUnit> sourcesCompiledUnits = new HashMap<String, CompilationUnit>();
			collectCompiledUnits(sourcesCompiledUnits, sourceOutDir, "");

			getClassLoader(templatefile);

			final INameEnvironment env = new NameEnvironment(sourcesCompiledUnits, compilerClassLoader, log);

			final IErrorHandlingPolicy policy = DefaultErrorHandlingPolicies.proceedWithAllProblems();

			/*
			 * Compilation options
			 */
			CompilerOptions options = new CompilerOptions();

			options.produceDebugAttributes = ClassFileConstants.ATTR_LINES | ClassFileConstants.ATTR_SOURCE;
			//options.warningThreshold ^= ~CompilerOptions.UsingDeprecatedAPI;

			options.defaultEncoding = "UTF-8";

			// Source JVM
			String vmVersion = System.getProperty("java.runtime.version");
			if (vmVersion != null) {
				String opt = vmVersion.substring(0, 3);
				options.sourceLevel = jvmLevelFromString(opt);
			} else {
				// Default to 1.7
				options.sourceLevel = ClassFileConstants.JDK1_7;
			}

			// Target JVM
			options.targetJDK = ClassFileConstants.JDK1_5;
			options.complianceLevel = ClassFileConstants.JDK1_5;

			final IProblemFactory problemFactory = new DefaultProblemFactory(Locale.getDefault());

			final CompilerRequestor requestor = new CompilerRequestor(sourcesCompiledUnits, sourceOutDir, log);
			CompilationUnit[] compilationUnits = sourcesCompiledUnits.values().toArray(
					new CompilationUnit[sourcesCompiledUnits.size()]);

			Compiler compiler = new Compiler(env, policy, options, requestor, problemFactory);
			compiler.compile(compilationUnits);
			if (!requestor.getProblemList().isEmpty()) {
				List<LogMessage> errors = requestor.getProblemList();
				for (LogMessage error : errors) {
					sourceLineProcessor.findSourceLine(error);
				}
				throw new TemplateException("Template", errors);
			}

			JarOutputStream jarStream = new JarOutputStream(new FileOutputStream(GeneratorUtils.Project2JarName(jarOutDir,
					projectName)));
			JarEntry manifestEntry = new JarEntry("META-INF/");
			jarStream.putNextEntry(manifestEntry);
			jarStream.closeEntry();
			manifestEntry = new JarEntry("META-INF/MANIFEST.MF");
			jarStream.putNextEntry(manifestEntry);
			Manifest manifest = new Manifest();
			Attributes attributes = manifest.getMainAttributes();
			attributes.putValue(Attributes.Name.MANIFEST_VERSION.toString(), "1.0");
			attributes.putValue("Created-By", System.getProperty("java.version") + " (" + System.getProperty("java.vendor")
					+ ") + jtg");
			manifest.write(jarStream);
			packJar(jarStream, sourceOutDir, "");
			jarStream.close();
			return true;
		} catch (TemplateException ex) {
			throw ex;
		} catch (NullPointerException ex) {
			throw new TemplateException(ex.getMessage(), ex, templatefile);
		} catch (Exception ex) {
			throw new TemplateException(ex.getMessage(), ex, templatefile);
		}
	}

	private void packJar(JarOutputStream jarStream, String startDir, String packedPath) throws IOException {
		File startFile = new File(startDir);
		String[] files = startFile.list();
		for (String file : files) {
			String fullPath = new File(startFile, file).getPath();
			File f = new File(fullPath);
			if (f.isDirectory()) {
				packJar(jarStream, fullPath, "".equals(packedPath) ? file : new File(packedPath, file).getPath());
			} else if (f.isFile()) {
				byte[] buffer = new byte[8192];
				JarEntry entry = null;
				try {
					entry = new JarEntry(new File(packedPath, file).getPath().replace("\\", "/"));
					jarStream.putNextEntry(entry);
					int readed = 0;
					InputStream is = null;
					try {
						is = new FileInputStream(f);
						while ((readed = is.read(buffer, 0, buffer.length)) > 0) {
							jarStream.write(buffer, 0, readed);
						}
					} finally {
						if (is != null) {
							try {
								is.close();
							} catch (Exception e) {
								// nothing to do
							}
						}
						jarStream.closeEntry();
					}
				} finally {
					if (jarStream != null) {
						try {
							jarStream.closeEntry();
						} catch (Exception e) {
							// nothing to do
						}
					}
				}
			}
		}
	}

	private void collectCompiledUnits(Map<String, CompilationUnit> sourcesCompiledUnits, String startDir, String packageName) {
		File startFile = new File(startDir);
		String[] files = startFile.list();
		for (String file : files) {
			String fullPath = new File(startDir, file).getPath();
			File f = new File(fullPath);
			if (f.isDirectory()) {
				collectCompiledUnits(sourcesCompiledUnits, fullPath, ("".equals(packageName) ? file : packageName + "."
						+ file));
			} else if (f.isFile() && file.endsWith(".java")) {
				Source src = null;
				if (sources.containsKey(fullPath)) {
					src = sources.get(fullPath);
				}
				String className = (src != null ? src.ClassName : file.substring(0, file.length() - 5));
				CompilationUnit compilationUnit = new CompilationUnit(fullPath, packageName + "." + className,
						(src != null ? src.Encoding : "UTF-8"), (src != null ? src.Unit : null), log);
				sourcesCompiledUnits.put(new File(startDir, className + ".java").getPath(), compilationUnit);
			}
		}
	}

	private void write_to_file(String source, String full_name, String encoding) throws Exception {
		if (sourceOutDir == null) return;

		File pathFile = new File(full_name).getParentFile();
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}

		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(full_name));
		writer.write(source);
		writer.close();
	}
}
