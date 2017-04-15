package org.edgo.jtg.core;

import java.io.File;
import java.text.MessageFormat;

import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.core.model.Argument;
import org.edgo.jtg.core.model.Extends;
import org.edgo.jtg.core.model.Import;
import org.edgo.jtg.core.model.IncludeNode;
import org.edgo.jtg.core.model.MacroNode;
import org.edgo.jtg.core.model.Node;
import org.edgo.jtg.core.model.ParsedUnit;
import org.edgo.jtg.core.model.PlaceholderNode;
import org.edgo.jtg.core.model.ScriptNode;
import org.edgo.jtg.core.model.TargetNode;
import org.edgo.jtg.core.model.TemplateNode;
import org.edgo.jtg.core.model.Visitor;

abstract class JavaSourceBaseVisitor implements Visitor {

	protected final static String		METHOD_NOT_IMPLEMENTED	= "The method or operation is not implemented.";

	protected final SourceLineProcessor	sourceLineProcessor;

	protected final StringBuilder		output;

	protected final String				configPath;

	protected final String				templateDir;

	protected final String				templateFile;

	protected final boolean				generateOnlyMacrocode;

	protected final LineCounter			lineNumb;

	private boolean						debugIsOpen				= false;

	private Node						lastNode				= null;

	public JavaSourceBaseVisitor(SourceLineProcessor sourceLineProcessor, StringBuilder output, String templateDir,
			String templateFile, String configPath, boolean generateOnlyMacrocode, LineCounter lineNumb) {
		this.templateDir = templateDir;
		this.sourceLineProcessor = sourceLineProcessor;
		this.output = output;
		this.templateFile = templateFile;
		this.configPath = configPath;
		this.generateOnlyMacrocode = generateOnlyMacrocode;
		this.lineNumb = lineNumb;
	}

	protected void makeDebug(String file, LineCounter line, Node node) {
		makeDebugOutputOff(false);
		makeDebug(file, line.value(), node);
	}

	public void makeDebug(String file, int line, Node node) {
		lastNode = node;
		lastNode.setTargetLineBegin(lineNumb.value());
		lastNode.setTargetLineEnd(lineNumb.value());
		debugIsOpen = true;
	}

	protected void makeDebugOutputOff(boolean force) {
		if (debugIsOpen && lastNode != null) {
			lastNode.setTargetLineEnd(lineNumb.value());
			lastNode = null;
		}
		debugIsOpen = false;
	}

	protected String makeRelativeTemplateFile(String templateFile) {
		String res = templateFile;
		if (res.startsWith(configPath)) {
			res = res.substring(configPath.length() + 1);
		}
		return res.replace("\\", "\\\\");
	}
}

public class JavaSourceVisitor extends JavaSourceBaseVisitor {

	public class JavaSourceDeclareMemberVisitor extends JavaSourceBaseVisitor {
		public JavaSourceDeclareMemberVisitor(SourceLineProcessor sourceLineProcessor, StringBuilder output,
				String templateFile, String templateDir, String configPath, boolean generateOnlyMacrocode,
				LineCounter lineNumb) {
			super(sourceLineProcessor, output, templateDir, templateFile, configPath, generateOnlyMacrocode, lineNumb);
		}

		public void visit(ParsedUnit n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(Argument n) throws TemplateException {
			makeDebug(templateFile, n.getSourceLineBegin(), n);
			output.append("    private ");
			output.append(n.getType());
			output.append(" ");
			output.append(n.getText());
			output.append(";");
			output.append(GeneratorUtils.EOL);
			lineNumb.increment();
		}

		public void visit(Import n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(Extends n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(PlaceholderNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(IncludeNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(MacroNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(TargetNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(ScriptNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}
	}

	public class JavaSourceAssignFromDictionaryVisitor extends JavaSourceBaseVisitor {
		public JavaSourceAssignFromDictionaryVisitor(SourceLineProcessor sourceLineProcessor, StringBuilder output,
				String templateDir, String templateFile, String configPath, boolean generateOnlyMacrocode,
				LineCounter lineNumb) {
			super(sourceLineProcessor, output, templateDir, templateFile, configPath, generateOnlyMacrocode, lineNumb);
		}

		public void visit(ParsedUnit n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(Argument n) throws TemplateException {
			//makeDebug(TemplateFile, n.getSourceLineBegin(), n);
			output.append("        if (!args.containsKey(\"");
			output.append(n.getText());
			output.append("\")) { String message = \"Property '");
			output.append(n.getText());
			output.append("' isn't found in template '\" + templateFileName + \"' at line ");
			output.append(n.getSourceLineBegin());
			output.append("\"; throw new TemplateException(message, new Exception(message), templateFileName, ");
			output.append(n.getSourceLineBegin());
			output.append("); } try { this.");
			output.append(n.getText());
			output.append(" = (");
			output.append(n.getType());
			output.append(")args.get(\"");
			output.append(n.getText());
			output.append("\"); } catch (ClassCastException ex) { String message = \"Property '");
			output.append(n.getText());
			output.append("' has missmathed type in template '\" + templateFileName + \"' at line ");
			output.append(n.getSourceLineBegin());
			output.append("\"; throw new TemplateException(message, ex, templateFileName, ");
			output.append(n.getSourceLineBegin());
			output.append("); }");
			output.append(GeneratorUtils.EOL);
			lineNumb.increment();
		}

		public void visit(Import n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(Extends n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(PlaceholderNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(IncludeNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(MacroNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(TargetNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(ScriptNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}
	}

	public class JavaSourceAssignFromArrayVisitor extends JavaSourceBaseVisitor {
		public JavaSourceAssignFromArrayVisitor(SourceLineProcessor sourceLineProcessor, StringBuilder output,
				String templateDir, String templateFile, String configPath, boolean generateOnlyMacrocode,
				LineCounter lineNumb) {
			super(sourceLineProcessor, output, templateDir, templateFile, configPath, generateOnlyMacrocode, lineNumb);
		}

		public void visit(ParsedUnit n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(Argument n) throws TemplateException {
			//makeDebug(TemplateFile, n.getSourceLineBegin(), n);
			output.append("        try { this.");
			output.append(n.getText());
			output.append(" = (");
			output.append(n.getType());
			output.append(")args[");
			output.append(n.getNum());
			output.append("]; } catch (IndexOutOfBoundsException ex) { String message = \"Property '");
			output.append(n.getNum());
			output.append("' doesn't exist in template '\" + templateFileName + \"' at line ");
			output.append(n.getSourceLineBegin());
			output.append("\"; throw new TemplateException(message, ex, templateFileName, ");
			output.append(n.getSourceLineBegin());
			output.append("); } catch (ClassCastException ex) { String message = \"Property '");
			output.append(n.getText());
			output.append("' has missmathed type in template '\" + templateFileName + \"' at line ");
			output.append(n.getSourceLineBegin());
			output.append("\"; throw new TemplateException(message, ex, templateFileName, ");
			output.append(n.getSourceLineBegin());
			output.append("); }");
			output.append(GeneratorUtils.EOL);
			lineNumb.increment();
		}

		public void visit(Import n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(Extends n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(PlaceholderNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(IncludeNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(MacroNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(TargetNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}

		public void visit(ScriptNode n) throws TemplateException {
			throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
		}
	}

	private String	schemaPackage;

	private String	generatedPackage;

	private String	sourceOutFile;

	private int		prevTokenLine	= 1;

	public JavaSourceVisitor(SourceLineProcessor sourceLineProcessor, StringBuilder output, String templateFile,
			String configPath, boolean generateOnlyMacrocode, String schemaPackage, String generatedPackage,
			String sourceOutDir, String templateDir) {
		super(sourceLineProcessor, output, templateDir, templateFile, configPath, generateOnlyMacrocode, new LineCounter(1));
		this.schemaPackage = schemaPackage;
		this.generatedPackage = generatedPackage;
		sourceOutFile = GeneratorUtils.SourceOutFullPath(sourceOutDir, generatedPackage, templateDir, templateFile);
	}

	public void visit(ParsedUnit n) throws TemplateException {
		String className = GeneratorUtils.Template2ClassName(templateDir, templateFile);
		boolean usingGeneratorMode = false;
		boolean usingRunTemplate = false;
		
		for (ScriptNode node : n.getScriptNodes()) {
			if (node.getText().contains("GeneratorMode.")) {
				usingGeneratorMode = true;
				break;
			}
		}
		for (TemplateNode node : n.getTemplateNodes()) {
			if (MacroNode.class.isInstance(node)) {
				if (node.getText().contains("GeneratorMode.")) {
					usingGeneratorMode = true;
				}
				if (node.getText().contains("RunTemplate")) {
					usingRunTemplate = true;
				}
			}
		}

		boolean hasPlaceholderNodes = false;
		for (TemplateNode node : n.getTemplateNodes()) {
			if (node instanceof PlaceholderNode) {
				hasPlaceholderNodes = true;
				break;
			}
		}

		makeDebug(sourceOutFile, 3, n);
		output.append("package ");
		output.append(generatedPackage);
		output.append(";");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("import java.io.PrintWriter;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("import java.io.StringWriter;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		if (hasPlaceholderNodes) {
			output.append("import java.lang.NullPointerException;");
			output.append(GeneratorUtils.EOL);
			lineNumb.increment();
			output.append("import org.edgo.jtg.basics.TemplateNullPointerException;");
			output.append(GeneratorUtils.EOL);
			lineNumb.increment();
		}
		output.append("import java.util.Map;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("import java.util.regex.Matcher;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();

		if (usingGeneratorMode) {
			output.append("import ");
			output.append(Constants.CODEGEN_BASICS_PACKAGE);
			output.append(".GeneratorMode;");
			output.append(GeneratorUtils.EOL);
			lineNumb.increment();
		}
		output.append("import ");
		output.append(Constants.CODEGEN_BASICS_PACKAGE);
		output.append(".TemplateClass;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("import ");
		output.append(Constants.CODEGEN_BASICS_PACKAGE);
		output.append(".TemplateException;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("import ");
		output.append(Constants.CODEGEN_BASICS_PACKAGE);
		output.append(".IEnvironment;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();

		output.append("import ");
		output.append(schemaPackage);
		output.append(".*;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();

		for (Import imp : n.getImports()) {
			imp.accept(this);
		}

		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("public class ");
		output.append(className);
		output.append(" extends ");
		Extends extendsClass = n.getExtends();
		if (extendsClass != null) {
			n.getExtends().accept(this);
		} else {
			output.append("TemplateClass");
		}
		output.append(" {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		makeDebugOutputOff(false);
		JavaSourceBaseVisitor v = new JavaSourceDeclareMemberVisitor(sourceLineProcessor, output, templateDir, templateFile,
				configPath, generateOnlyMacrocode, lineNumb);
		for (Argument arg : n.getArguments()) {
			arg.accept(v);
		}
		v.makeDebugOutputOff(true);
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		makeDebug(sourceOutFile, lineNumb, n);
		output.append("    public ");
		output.append(className);
		output.append("(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, Map<String, Object> args) throws TemplateException {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("        super(derivedType != null ? derivedType : ");
		output.append(className);
		output.append(".class, cmdArgs, env, \"");
		output.append(n.getEncoding());
		output.append("\", args);");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("        templateFileName = \"");
		output.append(makeRelativeTemplateFile(templateFile));
		output.append("\";");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		makeDebugOutputOff(false);
		v = new JavaSourceAssignFromDictionaryVisitor(sourceLineProcessor, output, templateDir, templateFile, configPath,
				generateOnlyMacrocode, lineNumb);
		for (Argument arg : n.getArguments()) {
			arg.accept(v);
		}
		makeDebug(sourceOutFile, lineNumb, n);
		output.append("    }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();

		output.append("    public ");
		output.append(className);
		output.append("(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, Object[] args) throws TemplateException {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("        super(derivedType != null ? derivedType : ");
		output.append(className);
		output.append(".class, cmdArgs, env, \"");
		output.append(n.getEncoding());
		output.append("\", args);");
		output.append(GeneratorUtils.EOL);
		output.append("        templateFileName = \"");
		output.append(makeRelativeTemplateFile(templateFile));
		output.append("\";");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		lineNumb.increment();
		makeDebugOutputOff(false);
		v = new JavaSourceAssignFromArrayVisitor(sourceLineProcessor, output, templateDir, templateFile, configPath,
				generateOnlyMacrocode, lineNumb);
		for (Argument arg : n.getArguments()) {
			arg.accept(v);
		}
		makeDebug(sourceOutFile, lineNumb, n);
		output.append("    }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();

		output.append("    public ");
		output.append(className);
		output.append("(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, String encoding, Map<String, Object> args) throws TemplateException {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("        super(derivedType != null ? derivedType : ");
		output.append(className);
		output.append(".class, cmdArgs, env, encoding, args);");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("        templateFileName = \"");
		output.append(makeRelativeTemplateFile(templateFile));
		output.append("\";");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		makeDebugOutputOff(false);
		v = new JavaSourceAssignFromDictionaryVisitor(sourceLineProcessor, output, templateDir, templateFile, configPath,
				generateOnlyMacrocode, lineNumb);
		for (Argument arg : n.getArguments()) {
			arg.accept(v);
		}
		makeDebug(sourceOutFile, lineNumb, n);
		output.append("    }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();

		output.append("    public ");
		output.append(className);
		output.append("(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, String encoding, Object[] args) throws TemplateException {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("        super(derivedType != null ? derivedType : ");
		output.append(className);
		output.append(".class, cmdArgs, env, encoding, args);");
		output.append(GeneratorUtils.EOL);
		output.append("        templateFileName = \"");
		output.append(makeRelativeTemplateFile(templateFile));
		output.append("\";");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		lineNumb.increment();
		makeDebugOutputOff(false);
		v = new JavaSourceAssignFromArrayVisitor(sourceLineProcessor, output, templateDir, templateFile, configPath,
				generateOnlyMacrocode, lineNumb);
		for (Argument arg : n.getArguments()) {
			arg.accept(v);
		}
		makeDebug(sourceOutFile, lineNumb, n);
		output.append("    }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();

		output.append(GeneratorUtils.EOL);
		lineNumb.increment();

		output.append("    public void Generate(final PrintWriter output) throws TemplateException {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("        try {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		makeDebugOutputOff(false);
		for (TemplateNode node : n.getTemplateNodes()) {
			node.accept(this);
		}
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		makeDebug(sourceOutFile, lineNumb, n);
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		if (hasPlaceholderNodes || usingRunTemplate) {
			output.append("        } catch(TemplateException e) {");
			output.append(GeneratorUtils.EOL);
			lineNumb.increment();
			output.append("            throw e;");
			output.append(GeneratorUtils.EOL);
			lineNumb.increment();
		}
		output.append("        } catch(Throwable e) {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("            StackTraceElement[] stackTraces = e.getStackTrace();");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("            int srcLineNumber = -1;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("            String className = null;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("            for (StackTraceElement stackTrace : stackTraces) {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                if (stackTrace.getClassName().contains(\"").append(className).append("\")) {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                    srcLineNumber = stackTrace.getLineNumber();");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                    className = stackTrace.getClassName();");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                    break;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("            }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("            if (srcLineNumber >= 0) {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                if (linesMapping.containsKey(srcLineNumber)) {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                    int lineNumber = (Integer) linesMapping.get(srcLineNumber);");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                    if (e.getMessage() != null) {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        Matcher matcher = TEMPLATE_CLASS_PATTERN.matcher(e.getMessage());");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        String message = e.toString();");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        if (ClassNotFoundException.class.isInstance(e) && matcher.find()) {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                            message = \"Template \\\"\" + matcher.group(1) + \"\\\" not found!\";");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        throw new TemplateException(message, e, templateFileName, lineNumber);");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                    } else if (className != null) {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        Matcher matcher = TEMPLATE_CLASS_PATTERN.matcher(className);");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        StringWriter sw = new StringWriter();");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        PrintWriter pw = new PrintWriter(sw);");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        e.printStackTrace(pw);");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        String message = sw.toString();");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        if (matcher.find()) {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                            message = \"Template \\\"\" + matcher.group(1) + \"\\\" runtime error at \" + lineNumber + \":\" + System.lineSeparator() +");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                                    message;");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                        throw new TemplateException(message, e, templateFileName, lineNumber);");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                    }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("                }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("            }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("            throw new TemplateException(e.getMessage(), e, templateFileName);");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("        } finally {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("            output.flush();");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("        }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("    }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		for (ScriptNode node : n.getScriptNodes()) {
			node.accept(this);
		}
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		makeDebug(sourceOutFile, lineNumb, n);
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("    static {");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		File file = new File(templateFile);
		String templFileName = file.getName();
		for (Argument arg : n.getArguments()) {
			int src = arg.getSourceLineBegin();
			for (int i = arg.getTargetLineBegin(); i <= arg.getTargetLineEnd(); i++) {
				sourceLineProcessor.addSourceLines(sourceOutFile, templFileName, 1, src, i);
				output.append("		linesMapping.put(").append(i).append(", ").append(src).append(");");
				output.append(GeneratorUtils.EOL);
				lineNumb.increment();
			}
		}
		for (Import imp : n.getImports()) {
			int src = imp.getSourceLineBegin();
			for (int i = imp.getTargetLineBegin(); i <= imp.getTargetLineEnd(); i++) {
				sourceLineProcessor.addSourceLines(sourceOutFile, templFileName, 1, src, i);
				output.append("		linesMapping.put(").append(i).append(", ").append(src).append(");");
				output.append(GeneratorUtils.EOL);
				lineNumb.increment();
			}
		}
		for (TemplateNode node : n.getTemplateNodes()) {
			int src = node.getSourceLineBegin();
			for (int i = node.getTargetLineBegin(); i <= node.getTargetLineEnd(); i++) {
				sourceLineProcessor.addSourceLines(sourceOutFile, templFileName, 1, src, i);
				output.append("		linesMapping.put(").append(i).append(", ").append(src).append(");");
				output.append(GeneratorUtils.EOL);
				lineNumb.increment();
			}
		}
		for (ScriptNode node : n.getScriptNodes()) {
			int src = node.getSourceLineBegin();
			for (int i = node.getTargetLineBegin(); i <= node.getTargetLineEnd(); i++) {
				sourceLineProcessor.addSourceLines(sourceOutFile, templFileName, 1, src, i);
				output.append("		linesMapping.put(").append(i).append(", ").append(src).append(");");
				output.append(GeneratorUtils.EOL);
				lineNumb.increment(); 
			}
		}
		output.append("    }");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append("}");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		makeDebugOutputOff(false);
	}

	public void visit(Argument n) throws TemplateException {
		throw new TemplateException(METHOD_NOT_IMPLEMENTED, new Exception(METHOD_NOT_IMPLEMENTED), n.getFileName());
	}

	public void visit(Import n) throws TemplateException {
		makeDebug(templateFile, n.getSourceLineBegin(), n);
		output.append("import ");
		output.append(n.getText());
		output.append(";");
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
	}

	public void visit(Extends n) throws TemplateException {
		if (n == null) {
			output.append("TemplateClass");
		} else {
			makeDebug(templateFile, n.getSourceLineBegin(), n);
			output.append(GeneratorUtils.Template2ClassName(templateDir, n.getText()));
		}
	}

	public void visit(PlaceholderNode n) throws TemplateException {
		if (!generateOnlyMacrocode) {
			if (prevTokenLine != n.getSourceLineBegin()) {
				if (n.getSourceLineBegin() - prevTokenLine == 1) {
					output.append(Constants.INDENT);
					output.append("output.println();");
					output.append(GeneratorUtils.EOL);
					lineNumb.increment();
				}
				output.append(Constants.INDENT);
			}
			makeDebug(templateFile, n.getSourceLineBegin(), n);
			output.append("try { ");
			output.append("output.print(");
			output.append(n.getText().trim());
			output.append("); } catch (NullPointerException ex) { throw new TemplateNullPointerException(null, ex, templateFileName, ");
			output.append(n.getSourceLineBegin());
			output.append("); }");
			prevTokenLine = n.getSourceLineBegin();
			makeDebugOutputOff(true);
		}
	}

	public void visit(IncludeNode n) throws TemplateException {
		if (!generateOnlyMacrocode) {
			if (prevTokenLine != n.getSourceLineBegin()) {
				if (n.getSourceLineBegin() - prevTokenLine == 1) {
					output.append(Constants.INDENT);
					output.append("output.println();");
					output.append(GeneratorUtils.EOL);
					lineNumb.increment();
				}
				output.append(Constants.INDENT);
			}
			makeDebug(templateFile, n.getSourceLineBegin(), n);
			output.append("try { ");
			String fileName = n.getFile();
			if (n.getParams() != null) {
				String params = n.getParams();
				String[] paramArray = new String[] { params }; 
				if (params.contains(",")) {
					paramArray = params.split(",");
				}
				for(int i = 0; i < paramArray.length; i++) {
					paramArray[i] = "\" + " + paramArray[i].trim() + " + \"";
				}
				fileName = MessageFormat.format(fileName, (Object[])paramArray); 
			}
			fileName = "\"" + fileName + "\"";
			output.append("RunTemplate (");
			output.append(fileName);
			output.append(", output, new Object[] { ");
			output.append(n.getArg());
			output.append(" }); } catch (NullPointerException ex) { throw new TemplateNullPointerException(null, ex, templateFileName, ");
			output.append(n.getSourceLineBegin());
			output.append("); }");
			prevTokenLine = n.getSourceLineBegin();
			makeDebugOutputOff(true);
		}
	}

	public void visit(MacroNode n) throws TemplateException {
		if (prevTokenLine != n.getSourceLineBegin()) {
			if (n.getSourceLineBegin() - prevTokenLine == 1) {
				output.append(GeneratorUtils.EOL);
				lineNumb.increment();
			}
			makeDebug(templateFile, n.getSourceLineBegin(), n);
			output.append(Constants.INDENT_MACRO);
		}
		output.append(n.getText());
		prevTokenLine = n.getSourceLineBegin();
	}

	public void visit(TargetNode n) throws TemplateException {
		int newLines = 0;
		if (!generateOnlyMacrocode) {
			if (prevTokenLine != n.getSourceLineBegin()) {
				if (n.getSourceLineBegin() - prevTokenLine == 1) {
					output.append(Constants.INDENT);
					output.append("output.println();");
					output.append(GeneratorUtils.EOL);
					lineNumb.increment();
				}
				output.append(Constants.INDENT);
			}
			makeDebug(templateFile, n.getSourceLineBegin(), n);
			if (!"".equals(n.getText())) {
				output.append("output.print(\"");
				for (int chr : n.getText().toCharArray()) {
					if (((char) chr) == '\"') {
						output.append("\\\"");
					} else if (((char) chr) == '\n') {
						output.append("\\n\" + ");
						output.append(GeneratorUtils.EOL);
						lineNumb.increment();
						newLines++;
						//makeDebug(templateFile, n.getSourceLineBegin() + (++newLines), n);
						output.append(Constants.INDENT);
						output.append("\"");
					} else if (((char) chr) == '\r') {
						output.append("\\r");
					} else if (chr > 0x7F) {
						output.append("\\u");
						output.append(String.format("%1$04X", chr & 0xFFFF));
					} else {
						output.append(((char) chr));
					}
				}
				output.append("\"); ");
			}
			makeDebugOutputOff(true);
			//if (n.getText().endsWith(GeneratorUtils.EOL) && newLines > 1) {
				output.append(GeneratorUtils.EOL);
				lineNumb.increment();
				//makeDebug(templateFile, n.getSourceLineBegin() + (++newLines), n);
			//}
			prevTokenLine = n.getSourceLineBegin() + newLines + 1;
		}
	}

	public void visit(ScriptNode n) throws TemplateException {
		output.append(GeneratorUtils.EOL);
		lineNumb.increment();
		makeDebug(templateFile, n.getSourceLineBegin(), n);
		String text = n.getText();
		if (!text.startsWith(" ") && !text.startsWith("\t")) {
			output.append(Constants.INDENT_SCRIPT);
		}
		output.append(text);
		prevTokenLine = n.getSourceLineBegin();
	}
}
