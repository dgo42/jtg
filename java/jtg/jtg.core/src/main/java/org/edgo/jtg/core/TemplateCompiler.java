package org.edgo.jtg.core;

import java.io.File;

import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.core.model.ParsedUnit;
import org.edgo.jtg.core.model.Visitor;

public class TemplateCompiler {

	private final SourceLineProcessor	sourceLineProcessor;
	private String						firstArgument			= null;
	private String						configPath;
	private String						sourceOutDir;
	private String						templateDir;

	private String						schemaPackage;
	private String						generatedPackage;
	private boolean						generateOnlyMacrocode	= false;

	public TemplateCompiler(SourceLineProcessor sourceLineProcessor, String templateDir, String configPath) {
		this(sourceLineProcessor, "", templateDir, configPath, Constants.SCHEMA_PACKAGE, Constants.GENERATED_PACKAGE, false);
	}

	public TemplateCompiler(SourceLineProcessor sourceLineProcessor, String sourceOutDir, String templateDir,
			String configPath, String schemaPackage, String generatedPackage, boolean generateOnlyMacrocode) {
		this.sourceLineProcessor = sourceLineProcessor;
		this.sourceOutDir = sourceOutDir;
		this.templateDir = templateDir;
		this.configPath = configPath;
		this.schemaPackage = schemaPackage;
		this.generatedPackage = generatedPackage;
		this.generateOnlyMacrocode = generateOnlyMacrocode;
	}

	public String getFirstArgument() {
		return firstArgument;
	}

	public String compile(String templateFileName, long templateLastModified, String template, JarCompiler compiler) throws TemplateException {
		String className = GeneratorUtils.Template2ClassName(templateDir, templateFileName);
		String sourceOutName = GeneratorUtils.SourceOutFullPath(sourceOutDir, generatedPackage, className);
		File templateFile = new File(templateFileName);
		File sourceOutFile = new File(sourceOutName);
		if (compiler.cacheExists() && sourceOutFile.exists() && sourceOutFile.isFile() && sourceOutFile.lastModified() > templateFile.lastModified()) {
			// Skip template compilation if generated java file is newer as template file
			return sourceOutName;
		}
		
		ParsedUnit unit = parse(templateFileName);

		StringBuilder sourceCode = compile(unit, templateFileName);
		compiler.addSource(sourceCode.toString(), className, unit.getMacroLang(), template, generatedPackage,
				unit.getEncoding(), unit, templateLastModified, firstArgument);

		for (String jar : unit.getJars()) {
			compiler.addJar(jar + ".jar", templateFileName);
		}
		return sourceOutName;
	}

	private ParsedUnit parse(String templatefile) throws TemplateException {
		TemplateParser parser = new TemplateParser(templatefile);
		ParsedUnit unit = parser.Parse();
		if (unit.getArguments().size() > 0) {
			firstArgument = unit.getArguments().get(0).getText();
		} else {
			firstArgument = null;
		}
		return unit;
	}

	private StringBuilder compile(ParsedUnit unit, String templatefile) throws TemplateException {
		StringBuilder result = new StringBuilder();
		Visitor visitor = new JavaSourceVisitor(sourceLineProcessor, result, templatefile, configPath,
				generateOnlyMacrocode, schemaPackage, generatedPackage, sourceOutDir, templateDir);
		unit.accept(visitor);
		return result;
	}
}
