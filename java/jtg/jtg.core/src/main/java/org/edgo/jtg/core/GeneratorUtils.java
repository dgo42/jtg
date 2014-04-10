package org.edgo.jtg.core;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

public final class GeneratorUtils {

	public static String	EOL	= System.getProperty("line.separator");

	private GeneratorUtils() {
	}

	/**
	 * Hilfsroutine, um aus einem Dateienamen fuer eine Templatedatei einen Klassennamen zu bilden.
	 * @param generatedPackage Konfigurierte namespace/package fuer generierte class.
	 * @param templateDir Absolutes Verzeichnis der Templatedatei.
	 * @param templatename Dateiname des Templates.
	 * @return Der gebildete vollklassifizierte Classenname.
	 */
	public static String Template2PackageClassName(String generatedPackage, String templateDir, String templatename) {
		return generatedPackage + "." + Template2ClassName(templateDir, templatename);
	}

	/**
	 * Hilfsroutine, um aus einem Dateienamen fur eine Templatedatei einen Klassennamen zu bilden. Dabei wird das 
	 * Wurzelverzeichnis der Templates aus dem Klassennamen extrahiert und alle Sonderzeichen in "_" umgewandelt.
	 * @param templateDir Absolutes Verzeichnis der Templatedatei.
	 * @param filename Dateiname des Templates.
	 * @return Der gebildete Classenname.
	 */
	public static String Template2ClassName(String templateDir, String templateName) {
		if (templateName.startsWith(templateDir)) templateName = templateName.substring(templateDir.length() + 1);

		return templateName.replace(' ', '_').replace('.', '_').replace('-', '_').replace('/', '_').replace('\\', '_')
				+ "GeneratorClass";
	}

	public static String Schema2PackageName(String schemaPackage, String simple) {
		return schemaPackage + "." + simple;
	}

	public static String SchemaFullPath(String dir_name, String file_name) {
		String path;
		if (null == dir_name || "" == dir_name) {
			path = file_name.trim();
		} else {
			path = (dir_name + File.separator + file_name).trim();
		}
		return (new File(path)).getAbsolutePath().toLowerCase(Locale.getDefault());
	}

	public static String SchemaRelativePath(String dir_name, String file_name) {
		String path;
		if (null == dir_name || "" == dir_name) {
			path = file_name;
		} else {
			path = dir_name + File.separator + file_name;
		}
		return path;
	}

	public static String SourceOutFullPath(String dirname, String generatedPackage, String className) {
		StringBuilder sb = new StringBuilder();
		sb.append(dirname);
		sb.append(dirname != "" ? File.separator : "");
		sb.append(generatedPackage.replace('.', '\\'));
		sb.append(generatedPackage != "" ? File.separator : "");
		sb.append(className);
		sb.append(".java");
		return new File(sb.toString()).getPath();
	}

	public static String SourceOutFullPath(String dirname, String generatedPackage, String templateDir, String templateName) {
		StringBuilder sb = new StringBuilder();
		sb.append(dirname);
		sb.append(dirname != "" ? File.separator : "");
		sb.append(generatedPackage.replace('.', '\\'));
		sb.append(generatedPackage != "" ? File.separator : "");
		sb.append(Template2ClassName(templateDir, templateName));
		sb.append(".java");
		return new File(sb.toString()).getPath();
	}

	// TODO do i need this??
	/*
	public static Assembly CachedJar(string assemblyOutDir, string template_dir, string schemaFullPath)
	{
	    string assembly = GeneratorUtils.AssemblyFullPath(assemblyOutDir, template_dir);
	    if (!File.Exists(assembly))
	        return null;
	    DateTime assembly_datetime = File.GetLastWriteTime(assembly);
	    if (File.Exists(schemaFullPath))
	    {
	        DateTime schema_datetime = File.GetLastWriteTime(schemaFullPath);
	        if (assembly_datetime.CompareTo(schema_datetime) < 0)
	        {
	            return null;
	        }

	    }
	    DateTime templates_datetime = TemplatesChangeTime(template_dir);
	    if (assembly_datetime.CompareTo(templates_datetime) < 0)
	    {
	        return null;
	    }
	    return Assembly.LoadFile(assembly);
	}
	*/
	public static Date TemplatesChangeTime(String template_dir) {
		String[] templates = new File(template_dir).list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".cgt");
			}
		});
		Date last_change = new Date(0);
		for (String name : templates) {
			File file = new File(name);
			Date time = new Date(0);
			if (file.isDirectory()) {
				time = TemplatesChangeTime(name);
			} else {
				time = new Date(file.lastModified());
			}
			if (last_change.compareTo(time) < 0) last_change = time;
		}
		return last_change;
	}

	public static String Project2JarName(String jarOutputDir, String projectName) {
		String jarName = new File(projectName).getName();
		if (jarName.lastIndexOf('.') > 0) {
			jarName = jarName.substring(0, jarName.lastIndexOf('.'));
		}
		jarName = jarName + ".jar";
		File jarFile = new File(jarOutputDir, jarName);
		return jarFile.getPath();
	}

	public static URL Project2JarUrl(String jarOutputDir, String projectName) {
		String path = Project2JarName(jarOutputDir, projectName);
		URL url = null;
		try {
			url = new URL("file:" + path.replace("\\", "/"));
		} catch (MalformedURLException ex) {
			// TODO
		}
		return url;
	}
}
