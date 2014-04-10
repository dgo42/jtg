package org.edgo.jtg.core.model;

import java.util.ArrayList;
import java.util.List;

import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.core.GeneratorUtils;

import antlr.RecognitionException;
import antlr.Token;

public class ParsedUnit extends Node {

	private MacroLang					language;
	private final String				templateFile;
	private final String				encoding;
	private final List<Import>			imports			= new ArrayList<Import>();
	private final List<String>			jars			= new ArrayList<String>();
	private final List<Argument>		arguments		= new ArrayList<Argument>();
	private final List<TemplateNode>	templateNodes	= new ArrayList<TemplateNode>();
	private final List<ScriptNode>		scriptNodes		= new ArrayList<ScriptNode>();
	private Extends						extendsClass	= null;

	public ParsedUnit(String templateFile, String encoding) {
		super(templateFile, 1);
		this.templateFile = templateFile;
		this.encoding = encoding;
	}

	public MacroLang getMacroLang() {
		return language;
	}

	public void addScript(Token v) {
		String text = v.getText();
		int lineNum = v.getLine();
		// split the text into lines
		String[] lines = text.split(GeneratorUtils.EOL);
		for (int i = 0; i < lines.length; i++) {
			scriptNodes.add(new ScriptNode(templateFile, lines[i], lineNum + i));
		}
		if (text.endsWith(GeneratorUtils.EOL)) {
			lineNum++;
			scriptNodes.add(new ScriptNode(templateFile, "", lineNum + lines.length - 1));
		}
	}

	public void directiveImport(String ns, int line) {
		imports.add(new Import(templateFile, ns, line));
	}

	public void directiveExtends(String parent, int line) throws RecognitionException {
		if (extendsClass != null) {
			throw new RecognitionException("There is only one extends directive allowed");
		}
		extendsClass = new Extends(templateFile, parent, line);
	}

	public void directiveJar(String assembly) {
		jars.add(assembly);
	}

	public void directiveArguments(String name, String type, int line) {
		arguments.add(new Argument(templateFile, name, type, arguments.size(), line));
	}

	public void addPlaceholder(Token v) {
		String text = v.getText();
		int lineNum = v.getLine();
		// split the text into lines
		String[] lines = text.split(GeneratorUtils.EOL);
		for (int i = 0; i < lines.length; i++) {
			templateNodes.add(new PlaceholderNode(templateFile, lines[i], lineNum + i));
		}
		if (text.endsWith(GeneratorUtils.EOL)) {
			lineNum++;
			templateNodes.add(new PlaceholderNode(templateFile, "", lineNum + lines.length - 1));
		}
	}

	public void addMacrocode(Token v) {
		String text = v.getText();
		int lineNum = v.getLine();
		// split the text into lines
		String[] lines = text.split(GeneratorUtils.EOL);
		for (int i = 0; i < lines.length; i++) {
			templateNodes.add(new MacroNode(templateFile, lines[i], lineNum + i));
		}
		if (text.endsWith(GeneratorUtils.EOL)) {
			lineNum++;
			templateNodes.add(new MacroNode(templateFile, "", lineNum + lines.length - 1));
		}
	}

	public void addTargetcode(Token v) {
		// escape '"'
		String text = v.getText()/*.replace("\"", "\\\"")*/;
		int lineNum = v.getLine();
		// split the text into lines
		String[] lines = text.split(GeneratorUtils.EOL);
		for (int i = 0; i < lines.length; i++) {
			templateNodes.add(new TargetNode(templateFile, lines[i], lineNum + i));
		}
		if (text.endsWith(GeneratorUtils.EOL)) {
			lineNum++;
			templateNodes.add(new TargetNode(templateFile, "", lineNum + lines.length - 1));
		}
	}

	public void setMacroLang(String lang) {
		language = MacroLang.parse(lang);
	}

	@Override
	public void accept(Visitor v) throws TemplateException {
		v.visit(this);
	}

	/**
	 * @return the templateFile
	 */
	public String getTemplateFile() {
		return templateFile;
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @return the imports
	 */
	public List<Import> getImports() {
		return imports;
	}

	/**
	 * @return the extends
	 */
	public Extends getExtends() {
		return extendsClass;
	}

	/**
	 * @return the jars
	 */
	public List<String> getJars() {
		return jars;
	}

	/**
	 * @return the arguments
	 */
	public List<Argument> getArguments() {
		return arguments;
	}

	/**
	 * @return the templateNodes
	 */
	public List<TemplateNode> getTemplateNodes() {
		return templateNodes;
	}

	/**
	 * @return the scriptNodes
	 */
	public List<ScriptNode> getScriptNodes() {
		return scriptNodes;
	}

}
