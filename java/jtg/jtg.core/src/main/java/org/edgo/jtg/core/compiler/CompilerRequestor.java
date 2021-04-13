package org.edgo.jtg.core.compiler;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.internal.compiler.ClassFile;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.edgo.jtg.basics.LogMessage;
import org.edgo.jtg.basics.LogMessage.MessageType;
import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.core.Logger;
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

public class CompilerRequestor implements ICompilerRequestor {

	private final ArrayList<LogMessage> problemList = new ArrayList<LogMessage>();

	private final Map<String, CompilationUnit> sources;

	private final String outputDir;

	private final Logger log;

	public CompilerRequestor(Map<String, CompilationUnit> sources, String outputDir, Logger log) {
		this.sources = sources;
		this.outputDir = outputDir;
		this.log = log;
	}

	public void acceptResult(CompilationResult result) {
		try {
			if (result.hasProblems()) {
				IProblem[] problems = result.getProblems();
				for (IProblem problem : problems) {
					if (problem.isError()) {
						String name = new String(problem.getOriginatingFileName());
						CompilationUnit unit = sources.get(name);
						ParsedUnit parsedUnit = null;
						if (unit != null) {
							parsedUnit = unit.getUnit();
						}
						problemList.add(createJavacError(name, parsedUnit, new StringBuffer(problem.getMessage()),
								problem.getSourceLineNumber()));
					}
				}
			}
			if (problemList.isEmpty()) {
				ClassFile[] classFiles = result.getClassFiles();
				for (int i = 0; i < classFiles.length; i++) {
					ClassFile classFile = classFiles[i];
					char[][] compoundName = classFile.getCompoundName();
					String className = "";
					String sep = "";
					for (int j = 0; j < compoundName.length; j++) {
						className += sep;
						className += new String(compoundName[j]);
						sep = ".";
					}
					byte[] bytes = classFile.getBytes();
					String outFile = outputDir + "/" + className.replace('.', '/') + ".class";
					FileOutputStream fout = new FileOutputStream(outFile);
					BufferedOutputStream bos = new BufferedOutputStream(fout);
					bos.write(bytes);
					bos.close();
				}
			}
		} catch (IOException exc) {
			if (log.isErrorEnabled()) {
				log.error("Compilation error", exc);
			}
}
	}

	/**
	 * @param fname
	 * @param page
	 * @param errMsgBuf
	 * @param lineNum
	 * @return JavacErrorDetail The error details
	 * @throws JasperException
	 */
	private LogMessage createJavacError(String fname, ParsedUnit unit, StringBuffer errMsgBuf, int lineNum) {
		LogMessage javacError;
		// Attempt to map javac error line number to line in JSP page
		ErrorVisitor errVisitor = new ErrorVisitor(lineNum);
		Node errNode = null;
		if (unit != null) {
			try {
				unit.accept(errVisitor);
			} catch (Exception e) {
				// can't happens nothing to do
			}
			errNode = errVisitor.getNode();
		}
		if (errNode != null) {
			javacError = new LogMessage(MessageType.ERROR, fname, lineNum, errNode.getFileName(),
					errNode.getSourceLineBegin(), errMsgBuf.toString());
		} else {
			/*
			 * javac error line number cannot be mapped to JSP page line number. For
			 * example, this is the case if a scriptlet is missing a closing brace, which
			 * causes havoc with the try-catch-finally block that the code generator places
			 * around all generated code: As a result of this, the javac error line numbers
			 * will be outside the range of begin and end java line numbers that were
			 * generated for the scriptlet, and therefore cannot be mapped to the start line
			 * number of the scriptlet in the JSP page. Include just the javac error info in
			 * the error detail.
			 */
			javacError = new LogMessage(MessageType.ERROR, fname, lineNum, errMsgBuf.toString());
		}
		return javacError;
	}

	/**
	 * @return the problemList
	 */
	public ArrayList<LogMessage> getProblemList() {
		return problemList;
	}

	/*
	 * Visitor responsible for mapping a line number in the generated servlet source
	 * code to the corresponding JSP node.
	 */
	class ErrorVisitor implements Visitor {

		// Java source line number to be mapped
		private int lineNum;

		/*
		 * JSP node whose Java source code range in the generated servlet contains the
		 * Java source line number to be mapped
		 */
		Node found;

		/*
		 * Constructor.
		 * 
		 * @param lineNum Source line number in the generated servlet code
		 */
		public ErrorVisitor(int lineNum) {
			this.lineNum = lineNum;
			this.found = null;
		}

		/*
		 * Gets the JSP node to which the source line number in the generated servlet
		 * code was mapped.
		 * 
		 * @return JSP node to which the source line number in the generated servlet
		 * code was mapped
		 */
		public Node getNode() {
			return found;
		}

		public void visit(ParsedUnit n) throws TemplateException {
			for (Argument arg : n.getArguments()) {
				arg.accept(this);
				if (found != null)
					return;
			}
			for (Import imp : n.getImports()) {
				imp.accept(this);
				if (found != null)
					return;
			}
			for (TemplateNode node : n.getTemplateNodes()) {
				node.accept(this);
				if (found != null)
					return;
			}
			for (ScriptNode node : n.getScriptNodes()) {
				node.accept(this);
				if (found != null)
					return;
			}
		}

		private void visitNode(Node n) {
			if (n.getTargetLineBegin() <= lineNum && n.getTargetLineEnd() >= lineNum) {
				found = n;
			}
		}

		public void visit(Argument n) throws TemplateException {
			visitNode(n);
		}

		public void visit(Import n) throws TemplateException {
			visitNode(n);
		}

		public void visit(IncludeNode n) throws TemplateException {
			visitNode(n);
		}

		public void visit(Extends n) throws TemplateException {
			visitNode(n);
		}

		public void visit(PlaceholderNode n) throws TemplateException {
			visitNode(n);
		}

		public void visit(MacroNode n) throws TemplateException {
			visitNode(n);
		}

		public void visit(TargetNode n) throws TemplateException {
			visitNode(n);
		}

		public void visit(ScriptNode n) throws TemplateException {
			visitNode(n);
		}
	}

}
