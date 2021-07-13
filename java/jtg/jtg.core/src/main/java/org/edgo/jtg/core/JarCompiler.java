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
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
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

import com.fasterxml.jackson.databind.ObjectMapper;

public class JarCompiler {

	private SourceLineProcessor sourceLineProcessor;
	private Map<String, Source> sources;
	private Set<String> jars;
	private ClassLoader compilerClassLoader = null;

	private String sourceOutDir = null;
	private String jarOutDir = null;
	private String templatePackage = null;
	private String schemaPackage = null;
	private ClassLoader parentClassLoader = null;
	private String projectName = null;
	private boolean useCache = false;
	private boolean cacheExists = false;

	final Logger log = new Logger();

	/**
	 * <summary> Konstruktor, uebergeben wird die . Mit out_dir wird das Ausgabe
	 * Verzeichnisangegeben. Ist kein Verzeichnis (out_dir == null) angegeben so
	 * werden die SourceFiles nicht geschrieben.
	 * 
	 * @param jarOutDir
	 * @param sourceOutDir
	 * @param projectName
	 */
	public JarCompiler(SourceLineProcessor sourceLineProcessor, String jarOutDir, String sourceOutDir,
			String projectName, String schemaPackage, String temlatePackage, boolean useCache,
			ClassLoader parentClassLoader) {
		this.sourceLineProcessor = sourceLineProcessor;
		sources = new HashMap<String, Source>();
		jars = new HashSet<String>();
		this.jarOutDir = jarOutDir;
		this.projectName = projectName != null ? projectName : "main.xml";
		this.parentClassLoader = parentClassLoader;
		this.schemaPackage = schemaPackage;
		this.templatePackage = temlatePackage;
		if (sourceOutDir != null && !"".equals(sourceOutDir)) {
			this.sourceOutDir = sourceOutDir;
		}
		this.useCache = useCache;
		if (useCache) {
			tryLoadCache();
		}
	}

	private void tryLoadCache() {
		File cacheFile = new File(jarOutDir, "cache.json");
		if (cacheFile.exists()) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				Cache cache = mapper.readValue(cacheFile, Cache.class);
				sources = cache.getSources();
				jars = cache.getJars();
				cacheExists = true;
			} catch (IOException e) {
				// can't use cache
				e.printStackTrace();
			}
		}
	}

	private void storeCache() {
		File cacheFile = new File(jarOutDir, "cache.json");
		try {
			Cache cache = new Cache(sources, jars);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(cacheFile, cache);
		} catch (IOException e) {
			// can't use cache
			e.printStackTrace();
		}
	}

	public void invalidateCache(List<String> allTemplates) {
		// TODO Auto-generated method stub

	}

	public boolean cacheExists() {
		return cacheExists;
	}

	public void addSource(String raw_source, String className, MacroLang language, String filename,
			String generatedPackage, String encoding, ParsedUnit unit, long templateLastModified, String mainArg)
			throws TemplateException {
		try {
			if (language == MacroLang.NONE) {
				String message = "Unknown macro language in template '" + filename + "'";
				throw new TemplateException(message, new Exception(message), filename);
			}
			if (sources.containsKey(filename))
				return;

			String full_name = GeneratorUtils.SourceOutFullPath(sourceOutDir, generatedPackage, className);

			sources.put(full_name, new Source(encoding, className, unit, templateLastModified, mainArg));

			writeToFile(raw_source, full_name, encoding);
		} catch (Exception ex) {
			throw new TemplateException(ex.getMessage(), ex, filename);
		}
	}

	// TODO
	public void addJar(String jar, String templatefile) throws TemplateException {
		try {
			if (jars.contains(jar))
				return;

			jars.add(jar);
		} catch (Exception ex) {
			throw new TemplateException(ex.getMessage(), ex, templatefile);
		}
	}

	public ClassLoader getClassLoader(String templatefile, String outPath) throws TemplateException {
		if (compilerClassLoader == null) {
			URL[] urls = new URL[jars.size() + 1];
			int i = 0;
			if (jars.size() > 0) {
				for (String jar : jars) {
					try {
						urls[i++] = new File(jar).toURI().toURL();
					} catch (MalformedURLException ex) {
						// TODO jar in node
						String message = "Can't find jar + \"" + jar + "\"";
						throw new TemplateException(message, new FileNotFoundException(message), templatefile);
					}
				}
			}
			try {
				urls[i++] = new File(outPath).toURI().toURL();
			} catch (MalformedURLException ex) {
				// TODO jar in node
				String message = "Can't find output path";
				throw new TemplateException(message, new FileNotFoundException(message), templatefile);
			}
			if (parentClassLoader != null) {
				compilerClassLoader = new URLClassLoader(urls, parentClassLoader);
			} else {
				compilerClassLoader = new URLClassLoader(urls, JarCompiler.class.getClassLoader());
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
		} else if (version.startsWith("9+")) {
			return ClassFileConstants.JDK9;
		} else if (version.startsWith("10")) {
			return ClassFileConstants.JDK10;
		} else if (version.startsWith("11")) {
			return ClassFileConstants.JDK11;
		} else if (version.startsWith("12")) {
			return ClassFileConstants.JDK12;
		} else {
			log.warn("Unknown source VM " + version + " ignored.");
			return ClassFileConstants.JDK9;
		}
	}

	/*
	 * public void WriteSource(String source, String filename, String encoding)
	 * throws Exception { String full_name =
	 * GeneratorUtils.SourceOutFullPath(source_out_dir, filename);
	 * write_to_file(source, full_name, encoding); }
	 */
	public boolean compile(String template_dir, String templatefile, boolean forceJar) throws TemplateException {
		try {
			File jarOutFile = new File(jarOutDir);
			if (!jarOutFile.exists()) {
				jarOutFile.mkdirs();
			}
			if (useCache) {
				storeCache();
			}

			String jarName = GeneratorUtils.Project2JarName(jarOutDir, projectName);
			File jarFile = new File(jarName);
			long jarLastModified = 0;
			if (jarFile.exists()) {
				jarLastModified = jarFile.lastModified();
			}
			Map<String, CompilationUnit> sourcesCompiledUnits = new HashMap<String, CompilationUnit>();
			String startDir = sourceOutDir + "/" + schemaPackage.replace(".", "/");
			boolean needCompile = collectCompiledUnits(sourcesCompiledUnits, startDir, schemaPackage, 0/*jarLastModified*/);
			startDir = sourceOutDir + "/" + templatePackage.replace(".", "/");
			needCompile |= collectCompiledUnits(sourcesCompiledUnits, startDir, templatePackage, jarLastModified);

			if (needCompile) {
				getClassLoader(templatefile, sourceOutDir);

				final INameEnvironment env = new NameEnvironment(sourcesCompiledUnits, compilerClassLoader, log);

				final IErrorHandlingPolicy policy = DefaultErrorHandlingPolicies.proceedWithAllProblems();
				//final IErrorHandlingPolicy policy = DefaultErrorHandlingPolicies.ignoreAllProblems();

				/*
				 * Compilation options
				 */
				CompilerOptions options = new CompilerOptions();

				options.produceDebugAttributes = ClassFileConstants.ATTR_LINES | ClassFileConstants.ATTR_SOURCE;
				// options.warningThreshold ^= ~CompilerOptions.UsingDeprecatedAPI;

				options.defaultEncoding = "UTF-8";

				// Source JVM
				String vmVersion = System.getProperty("java.runtime.version");
				if (vmVersion != null) {
					String opt = vmVersion.substring(0, 3);
					options.sourceLevel = jvmLevelFromString(opt);
				} else {
					// Default to 1.7
					options.sourceLevel = ClassFileConstants.JDK1_8;
				}

				// Target JVM
				options.targetJDK = options.sourceLevel;
				options.complianceLevel = options.sourceLevel;
				options.inlineJsrBytecode = true;
				options.processAnnotations = true;
				//options.reportUnavoidableGenericTypeProblems = false;
				//options.reportUnlikelyCollectionMethodArgumentTypeStrict = false;
				//options.reportUnavoidableGenericTypeProblems = true;
				//options.enableJdtDebugCompileMode = true;
				//options.verbose = true;

				final IProblemFactory problemFactory = new DefaultProblemFactory(Locale.getDefault());

				final CompilerRequestor requestor = new CompilerRequestor(sourcesCompiledUnits, sourceOutDir, log);
				CompilationUnit[] compilationUnits = sourcesCompiledUnits.values()
						.toArray(new CompilationUnit[sourcesCompiledUnits.size()]);

				Compiler compiler = new Compiler(env, policy, options, requestor, problemFactory);
				compiler.compile (compilationUnits);
				if (!requestor.getProblemList().isEmpty()) {
					List<LogMessage> errors = requestor.getProblemList();
					for (LogMessage error : errors) {
						sourceLineProcessor.findSourceLine(error);
					}
					throw new TemplateException("Template", errors);
				}

				if (forceJar) {
					JarOutputStream jarStream = new JarOutputStream(new FileOutputStream(jarFile));
					JarEntry manifestEntry = new JarEntry("META-INF/");
					jarStream.putNextEntry(manifestEntry);
					jarStream.closeEntry();
					manifestEntry = new JarEntry("META-INF/MANIFEST.MF");
					jarStream.putNextEntry(manifestEntry);
					Manifest manifest = new Manifest();
					Attributes attributes = manifest.getMainAttributes();
					attributes.putValue(Attributes.Name.MANIFEST_VERSION.toString(), "1.0");
					attributes.putValue("Created-By",
							System.getProperty("java.version") + " (" + System.getProperty("java.vendor") + ") + jtg");
					manifest.write(jarStream);
					packJar(jarStream, sourceOutDir, "");
					jarStream.close();
				}
			}
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
			} else if (f.isFile() && f.getName().endsWith(".class")) {
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

	private boolean collectCompiledUnits(Map<String, CompilationUnit> sourcesCompiledUnits, String startDir,
			String packageName, long jarLastModified) {
		File startFile = new File(startDir);
		String[] files = startFile.list();
		boolean needCompile = false;
		for (String file : files) {
			String fullPath = new File(startDir, file).getPath();
			File f = new File(fullPath);
			if (f.isDirectory()) {
				needCompile |= collectCompiledUnits(sourcesCompiledUnits, fullPath,
						("".equals(packageName) ? file : packageName + "." + file), jarLastModified);
			} else if (f.isFile() && file.endsWith(".java")) {
				boolean fileIsNew = f.lastModified() > jarLastModified;
				needCompile |= fileIsNew;
				if (fileIsNew) {
					Source src = null;
					if (sources.containsKey(fullPath)) {
						src = sources.get(fullPath);
					}
					String className = (src != null ? src.ClassName : file.substring(0, file.length() - 5));
					String fqcn = className;
					if (!fqcn.contains(".") && !"".equals(packageName)) {
						fqcn = packageName + "." + className;
					}
					CompilationUnit compilationUnit = new CompilationUnit(fullPath, fqcn,
							(src != null ? src.Encoding : "UTF-8"), (src != null ? src.Unit : null), log);
					sourcesCompiledUnits.put(fqcn, compilationUnit);
				}
			}
		}
		return needCompile;
	}

	private void writeToFile(String source, String full_name, String encoding) throws Exception {
		if (sourceOutDir == null)
			return;

		File pathFile = new File(full_name).getParentFile();
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}

		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(full_name));
		writer.write(source);
		writer.close();
	}

	public Source getSource(String sourceOutName) {
		return sources.get(sourceOutName);
	}

}
