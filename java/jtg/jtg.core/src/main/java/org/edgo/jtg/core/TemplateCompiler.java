package org.edgo.jtg.core;

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

	public void Compile(String templatefile, String template, JarCompiler compiler) throws TemplateException {
		ParsedUnit unit = parse(templatefile);
		//source_out_file = GeneratorUtils.SourceOutFullPath(source_out_dir, templatefile, unit.getMacroLang());
		StringBuilder source_code = compile(unit, templatefile);
		String className = GeneratorUtils.Template2ClassName(templateDir, templatefile);
		compiler.AddSource(source_code.toString(), className, unit.getMacroLang(), template, generatedPackage,
				unit.getEncoding(), unit);

		for (String jar : unit.getJars()) {
			compiler.AddJar(jar + ".jar", templatefile);
		}
	}

	private ParsedUnit parse(String templatefile) throws TemplateException {
		TemplateParser parser = new TemplateParser(templatefile);
		ParsedUnit unit = parser.Parse();
		/*		if (unit.getArguments().size() == 0) {
					String message = "The template \"" + templatefile + "\" must have atleast one property directive";
					throw new TemplateException(message, new Exception(message), templatefile);
				}*/
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
