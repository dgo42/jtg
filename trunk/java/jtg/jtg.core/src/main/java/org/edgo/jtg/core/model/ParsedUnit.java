package org.edgo.jtg.core.model;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.core.GeneratorUtils;

public class ParsedUnit extends Node {

	private MacroLang language;
	private final String templateFile;
	private final String encoding;
	private final List<Import> imports = new ArrayList<Import>();
	private final List<String> jars = new ArrayList<String>();
	private final List<Argument> arguments = new ArrayList<Argument>();
	private final List<TemplateNode> templateNodes = new ArrayList<TemplateNode>();
	private final List<ScriptNode> scriptNodes = new ArrayList<ScriptNode>();
	private Extends extendsClass = null;

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
			throw new RecognitionException("There is only one extends directive allowed", null, null, null);
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

	public void addInclude(Token v, String file, String params, String arg) {
		/*
		 * String text = v.getText(); int lineNum = v.getLine(); // split the text into lines String[] lines =
		 * text.split(GeneratorUtils.EOL); for (int i = 0; i < lines.length; i++) { IncludeNode node = new IncludeNode(templateFile,
		 * lines[i], lineNum + i); node.setFile(file); node.setParams(params); node.setArg(arg); templateNodes.add(node); } if
		 * (text.endsWith(GeneratorUtils.EOL)) { lineNum++; IncludeNode node = new IncludeNode(templateFile, "", lineNum + lines.length -
		 * 1); node.setFile(file); node.setParams(params); node.setArg(arg); templateNodes.add(node); }
		 */
		IncludeNode node = new IncludeNode(templateFile, "", v.getLine());
		node.setFile(file);
		node.setParams(params);
		node.setArg(arg);
		templateNodes.add(node);
	}

	public void addMacrocode(Token v1, Token v2) {
		String text = v1.getText();
		if (v2 != null) {
			text += v2.getText();
		}
		int lineNum = v1.getLine();
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
		String text = v.getText();
		if (!"\r\n".equals(text) && !"\n".equals(text)) {
			int lineNum = v.getLine();
			String[] lines = text.split(GeneratorUtils.EOL);
			int lineCount = lines.length;
			if (text.endsWith(GeneratorUtils.EOL)) {
				lineCount++;
			}
			TargetNode node = new TargetNode(templateFile, text, lineNum);
			node.setTargetLineEnd(lineNum + lineCount);
			templateNodes.add(node);
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
