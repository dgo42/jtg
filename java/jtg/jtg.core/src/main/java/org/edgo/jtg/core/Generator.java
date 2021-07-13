package org.edgo.jtg.core;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.edgo.jtg.basics.IEnvironment;
import org.edgo.jtg.basics.TemplateClass;
import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.core.config.JtgConfiguration;

public class Generator {

	private static long PERIOD = 60 * 1000;

	private PrintStream textWriter = System.out;

	private Map<String, String> cmdArgs;

	private GeneratorCommand cmd = GeneratorCommand.COMPLETE;

	private String configPath = "";

	private SourceLineProcessor sourceLineProcessor = new SourceLineProcessor();

	private IEnvironment env;

	public Generator() {
	}

	public Generator(String[] args) throws TemplateException {
		init(args, (String) null);
	}

	public Generator(String[] args, String configFile) throws TemplateException {
		init(args, configFile);
	}

	public Generator(JtgConfiguration configuration, IEnvironment env) {
		init(configuration);
		this.env = env;
	}

	public Generator(String[] args, JtgConfiguration configuration, IEnvironment env) throws TemplateException {
		init(args, configuration);
		this.env = env;
	}

	public boolean getHelpNeeded() {
		return cmdArgs.containsKey("Help");
	}

	public PrintStream getWriter() {
		return textWriter;
	}

	public void setWriter(PrintStream writer) {
		textWriter = writer;
	}

	public GeneratorCommand getCommand() {
		return cmd;
	}

	/**
	 * @return the configPath
	 */
	public String getConfigPath() {
		return configPath;
	}

	/**
	 * @param configPath the configPath to set
	 */
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}

	private static File ROOTS[] = File.listRoots();

	private static Object MUTEX; 
	
	static {
		MUTEX = new Object();
		Runnable r = new Runnable() {
			public void run() {
				while (true) {
					try {
						try {
							synchronized (MUTEX) {
								ROOTS = File.listRoots();
							}
						} catch (Exception e) {
							// nothing to do
						}
						Thread.sleep(PERIOD);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
					// do something here 
				}
			}
		};

		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(r);
	}

	private String makeAbsolute(String path) {
		File roots[] = null;
		synchronized (MUTEX) {
			roots = ROOTS;
		}
		for (File root : roots) {
			if (path.toLowerCase().startsWith(root.toString().toLowerCase()))
				return path;
		}
		if (path.startsWith(File.separator))
			return path;
		String result = new File(configPath, path).getAbsolutePath();

		return result;
	}

	public boolean getUsingCache() {
		return Boolean.parseBoolean(cmdArgs.get("UsingCache"));
	}

	public String getTemplateDir() {
		return makeAbsolute(cmdArgs.get("TemplateDir"));
	}

	public String getSchemaDir() {
		return makeAbsolute(cmdArgs.get("SchemaDir"));
	}

	public String getSourceOutputDir() {
		return makeAbsolute(cmdArgs.get("SourceOutputDir"));
	}

	public String getGeneratorOutputDir() {
		return makeAbsolute(cmdArgs.get("GeneratorOutputDir"));
	}

	public String getJarOutputDir() {
		return makeAbsolute(cmdArgs.get("JarOutputDir"));
	}

	public String getSchemaPackage() {
		return cmdArgs.get("SchemaPackage");
	}

	public String getGenratedPackage() {
		return cmdArgs.get("GeneratedPackage");
	}

	public String getSchema() {
		return cmdArgs.get("Schema");
	}

	public String getProjectFile() {
		return cmdArgs.get("ProjectFile");
	}

	public String getStartTemplate() {
		return cmdArgs.get("StartTemplate");
	}

	public String getTemplateArg() {
		return cmdArgs.get("TemplateArg");
	}

	public String getProjectFullPath() {
		return makeAbsolute(getProjectFile());
	}

	public String generate(ClassLoader parentClassLoader) throws TemplateException {
		JarCompiler jarCompiler;
		ClassLoader classLoader = null;
		URL jarUrl = null;
		List<String> allTemplates = new ArrayList<String>();

		try {
			String templateArg = null;
			String templateFile = getTemplateDir() + File.separator + getStartTemplate();
			jarCompiler = new JarCompiler(sourceLineProcessor, getJarOutputDir(), getSourceOutputDir(), getProjectFile(), getSchemaPackage(),
					getGenratedPackage(), getUsingCache(), parentClassLoader);

			GeneratorCommand command = getCommand();
			switch (command) {
			// only compile schema XSD => java
			case SCHEMA_ONLY:
				generateSchemaClasses(jarCompiler);
				return null;

			// only compile template JTG => java
			case TEMPLATES_ONLY:
				return generateTemplateClasses(jarCompiler, getTemplateDir(), allTemplates);

			// only compile schema and templates {XSD, JTG} => java
			case SOURCES_ONLY:
				generateSchemaClasses(jarCompiler);
				return generateTemplateClasses(jarCompiler, getTemplateDir(), allTemplates);

			// compile schema & templates into java, compile java and put all classes into
			// jar
			case COMPILE_ONLY:
			case JAR_ONLY:
				generateSchemaClasses(jarCompiler);
				templateArg = generateTemplateClasses(jarCompiler, getTemplateDir(), allTemplates);
				jarCompiler.invalidateCache(allTemplates);
				compileJar(jarCompiler, command == GeneratorCommand.JAR_ONLY);
				return templateArg;

			// only generate output stuff using existing jar
			case GENERATE:
				jarUrl = GeneratorUtils.Project2JarUrl(getJarOutputDir(), "main");
				classLoader = URLClassLoader.newInstance(new URL[] { jarUrl }, jarCompiler.getClassLoader(getProjectFile(), getSourceOutputDir()));
				templateArg = getTemplateArg();
				generateMainTemplate(templateFile, templateArg, classLoader);
				return templateArg;

			default:
				generateSchemaClasses(jarCompiler);
				templateArg = generateTemplateClasses(jarCompiler, getTemplateDir(), allTemplates);
				jarCompiler.invalidateCache(allTemplates);
				compileJar(jarCompiler, true);
				jarUrl = GeneratorUtils.Project2JarUrl(getJarOutputDir(), getProjectFile());
				classLoader = URLClassLoader.newInstance(new URL[] { jarUrl }, jarCompiler.getClassLoader(getProjectFile(), getSourceOutputDir()));
				generateMainTemplate(templateFile, templateArg, classLoader);
				return templateArg;
			}
		} catch (TemplateException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new TemplateException(ex.getMessage(), ex, getProjectFile());
		} finally {
			jarCompiler = null;
			classLoader = null;
		}
	}

	public void generate(ClassLoader parentClassLoader, Map<String, Object> parameters) throws TemplateException {
		JarCompiler jarCompiler;
		ClassLoader classLoader = null;
		try {
			String templateFile = getTemplateDir() + File.separator + getStartTemplate();
			jarCompiler = new JarCompiler(sourceLineProcessor, getJarOutputDir(), getSourceOutputDir(), getProjectFile(), getSchemaPackage(),
					getGenratedPackage(), getUsingCache(), parentClassLoader);

			URL jarUrl = GeneratorUtils.Project2JarUrl(getJarOutputDir(), getProjectFile());
			classLoader = URLClassLoader.newInstance(new URL[] { jarUrl }, jarCompiler.getClassLoader(getProjectFile(), getSourceOutputDir()));
			generate(classLoader, templateFile, textWriter, parameters, cmdArgs);
		} catch (TemplateException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new TemplateException(ex.getMessage(), ex, getProjectFile());
		} finally {
			jarCompiler = null;
			classLoader = null;
		}
	}

	public void init(String[] args, String configFile) throws TemplateException {
		try {
			initByConfig(configFile);
			initByArgs(args);
			env = new DefaultEnvironment(configFile, cmdArgs.get("GeneratorOutputDir"));
		} catch (ConfigurationException ex) {
			throw new TemplateException(ex.getMessage(), ex, getProjectFile());
		} catch (Exception ex) {
			throw new TemplateException(ex.getMessage(), ex, getProjectFile());
		}
	}

	public void init(String[] args, JtgConfiguration configuration) throws TemplateException {
		try {
			initByJtgConfig(configuration);
			if (args != null) {
				initByArgs(args);
				env = new DefaultEnvironment("", cmdArgs.get("GeneratorOutputDir"));
			}
		} catch (ConfigurationException ex) {
			throw new TemplateException(ex.getMessage(), ex, getProjectFile());
		} catch (Exception ex) {
			throw new TemplateException(ex.getMessage(), ex, getProjectFile());
		}
	}

	public void init(JtgConfiguration configuration) {
		initByJtgConfig(configuration);
	}

	private void initByArgs(String[] args) throws ConfigurationException {
		if (cmdArgs == null) {
			cmdArgs = new HashMap<String, String>();
		}

		for (int i = 0; i < args.length; i++) {
			String lowArg = args[i].toLowerCase();
			if (lowArg == "-schemadir" || lowArg == "-sd") {
				cmdArgs.put("SchemaDir", args[++i]);
			} else if (lowArg == "-templatedir" || lowArg == "-td") {
				cmdArgs.put("TemplateDir", args[++i]);
			} else if (lowArg == "-generatoroutputdir" || lowArg == "-god") {
				cmdArgs.put("GeneratorOutputDir", args[++i]);
			} else if (lowArg == "-sourceoutputdir" || lowArg == "-sod") {
				cmdArgs.put("SourceOutputDir", args[++i]);
			} else if (lowArg == "-jaroutputdir" || lowArg == "-jod") {
				cmdArgs.put("JarOutputDir", args[++i]);
			} else if (lowArg == "-schemapackage" || lowArg == "-sp") {
				cmdArgs.put("SchemaPackage", args[++i]);
			} else if (lowArg == "-generatedpackage" || lowArg == "-gp") {
				cmdArgs.put("GeneratedPackage", args[++i]);
			} else if (lowArg == "-schema" || lowArg == "-s") {
				cmdArgs.put("Schema", args[++i]);
			} else if (lowArg == "-projectfile" || lowArg == "-p") {
				cmdArgs.put("ProjectFile", args[++i]);
			} else if (lowArg == "-starttemplate" || lowArg == "-t") {
				cmdArgs.put("StartTemplate", args[++i]);
			} else if (lowArg == "-templatearg" || lowArg == "-ta") {
				cmdArgs.put("TemplateArg", args[++i]);
			} else if (lowArg == "-command" || lowArg == "-cmd" || lowArg == "-c") {
				String val = args[++i].toLowerCase();
				if (val == "schema" || val == "s")
					cmd = GeneratorCommand.SCHEMA_ONLY;
				else if (val == "templates" || val == "template" || val == "t")
					cmd = GeneratorCommand.TEMPLATES_ONLY;
				else if (val == "code" || val == "c")
					cmd = GeneratorCommand.SOURCES_ONLY;
				else if (val == "jar" || val == "j")
					cmd = GeneratorCommand.JAR_ONLY;
				else if (val == "generate" || val == "g")
					cmd = GeneratorCommand.GENERATE;
				else if (val == "project" || val == "p")
					cmd = GeneratorCommand.COMPLETE;
				else {
					String msg = MessageFormat.format("Wrong command specified ({0}). Please type -h for help.", args[i]);
					throw new ConfigurationException(msg);
				}
			} else if (lowArg == "-usecache" || lowArg == "-uc") {
				String val = args[++i].toLowerCase();
				if ("true" == val || "1" == val || "yes" == val)
					cmdArgs.put("UsingCache", "true");
				else
					cmdArgs.put("UsingCache", "false");
			} else if (lowArg == "-help" || lowArg == "-h" || lowArg == "-?" || lowArg == "?") {
				cmdArgs.put("Help", "true");
			} else if (lowArg.startsWith("-")) {
				String param = lowArg.substring(1);
				if (i < args.length - 1) {
					String val = args[++i];
					cmdArgs.put(param, val);
				} else {
					cmdArgs.put(param, param);
				}
			} else {
				cmdArgs.put(args[i], args[i]);
			}
		}
	}

	private void initByConfig(String configFile) throws ConfigurationException {
		JtgConfiguration configuration;
		String file = configFile;
		if (configFile == null) {
			String path = JtgSettings.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = "";
			try {
				decodedPath = URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
			file = decodedPath + "jtg.config";
		}
		File config = new File(file);
		if (config.exists()) {
			configuration = JtgSettings.loadConfig(file);
			configPath = config.getParentFile().getAbsolutePath();
			//
			// Werte der Configurations als default Commandline Argumente Uebernehmen.
			//
			initByJtgConfig(configuration);
		}
	}

	private void initByJtgConfig(JtgConfiguration configuration) {
		cmdArgs = new HashMap<String, String>();

		cmdArgs.put("UsingCache", configuration.getUsingCache());
		cmdArgs.put("TemplateDir", configuration.getTemplateDir());
		cmdArgs.put("SchemaDir", configuration.getSchemaDir());
		cmdArgs.put("GeneratorOutputDir", configuration.getGeneratorOutputDir());
		cmdArgs.put("SourceOutputDir", configuration.getSourceOutputDir());
		cmdArgs.put("JarOutputDir", configuration.getJarOutputDir());

		cmdArgs.put("SchemaPackage", configuration.getSchemaPackage());
		cmdArgs.put("GeneratedPackage", configuration.getGeneratedPackage());

		cmdArgs.put("Schema", configuration.getSchema());
		cmdArgs.put("ProjectFile", configuration.getProjectFile());
		cmdArgs.put("StartTemplate", configuration.getStartTemplate());
		cmdArgs.put("TemplateArg", configuration.getTemplateArg());

		cmd = GeneratorCommand.valueOf(configuration.getCommand());
	}

	private SchemaCompiler generateSchemaClasses(JarCompiler jarCompiler) throws TemplateException {
		SchemaCompiler schemaCompiler = new SchemaCompiler(getSchemaPackage());
		schemaCompiler.compile(getSchemaDir(), getSchema(), getSourceOutputDir(), jarCompiler);
		return schemaCompiler;
	}

	private String generateTemplateClasses(JarCompiler jarCompiler, String startDir, List<String> allTemplates) throws TemplateException {
		String mainArg = null;
		String[] templates = (new File(startDir)).list();

		for (String template : templates) {
			File file = new File(startDir, template);
			if (file.isFile() && template.endsWith(".jtg")) {
				allTemplates.add(file.getPath());
				TemplateCompiler templateCompiler = new TemplateCompiler(sourceLineProcessor, getSourceOutputDir(), getTemplateDir(), getConfigPath(),
						getSchemaPackage(), getGenratedPackage(), false);
				String sourceOutName = templateCompiler.compile(file.getPath(), file.lastModified(), template, jarCompiler);
				if (template.endsWith(getStartTemplate())) {
					mainArg = templateCompiler.getFirstArgument();
					if (mainArg == null) {
						Source source = jarCompiler.getSource(sourceOutName);
						if (source != null) {
							mainArg = source.MainArg;
						}
					}
				}
			}
			if (file.isDirectory()) {
				generateTemplateClasses(jarCompiler, file.getPath(), allTemplates);
			}
		}
		return mainArg;
	}

	private void compileJar(JarCompiler jarCompiler, boolean forceJar) throws TemplateException {
		jarCompiler.compile(getTemplateDir(), getProjectFile(), forceJar);
	}

	private void generateMainTemplate(String templateFile, String templateArg, ClassLoader classLoader) throws TemplateException {
		Object data = ProjectLoader.LoadProject(getProjectFullPath(), getSourceOutputDir(), getSchemaPackage(), classLoader);
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		if (null != templateArg && "" != templateArg) {
			parameters.put(templateArg, data);
		}

		generate(classLoader, templateFile, textWriter, parameters, cmdArgs);
	}

	public void generate(ClassLoader classLoader, String templateFile, PrintStream output, Map<String, Object> args, Map<String, String> cmdArgs)
			throws TemplateException {
		String templateName = GeneratorUtils.Template2PackageClassName(getGenratedPackage(), getTemplateDir(), templateFile);
		ClassLoader oldContextClassLoader = Thread.currentThread().getContextClassLoader();
		try {
			Class<?> templateType = classLoader.loadClass(templateName);

			Constructor<?> ctor = templateType.getConstructor(new Class[] { Class.class, Map.class, IEnvironment.class, Map.class });

			TemplateClass template = (TemplateClass) ctor.newInstance(new Object[] { null, cmdArgs, env, args });
			if (null == template) {
				String message = "Can't construct " + templateName + " as TemplateClass (see " + templateFile + ".";
				throw new TemplateException(message, new NullPointerException(message), templateFile);
			}
			Thread.currentThread().setContextClassLoader(classLoader);
			template.Generate(new PrintWriter(output));
		} catch (TemplateException ex) {
			throw ex;
		} catch (Throwable ex) {
			StackTraceElement[] stackTraces = ex.getStackTrace();
			StackTraceElement found = null;
			for (StackTraceElement stackTrace : stackTraces) {
				if (stackTrace.getClassName().contains(templateName)) {
					found = stackTrace;
				}
			}
			if (found == null) {
				throw new TemplateException(ex.getMessage(), ex, templateFile);
			}
			throw new TemplateException(ex.getMessage(), ex, templateFile, found.getLineNumber());
		} finally {
			Thread.currentThread().setContextClassLoader(oldContextClassLoader);
		}
	}

	public static void main(String[] args) {
		try {
			Generator generator = new Generator(args);
			if (generator.getHelpNeeded()) {
				System.out.println("usage"); // TODO make usage
				return;
			}
			generator.generate(Generator.class.getClassLoader());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
