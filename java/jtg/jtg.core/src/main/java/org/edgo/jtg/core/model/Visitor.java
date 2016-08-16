package org.edgo.jtg.core.model;

import org.edgo.jtg.basics.TemplateException;

public interface Visitor {

	void visit(ParsedUnit n) throws TemplateException;

	void visit(Argument n) throws TemplateException;

	void visit(Import n) throws TemplateException;

	void visit(Extends n) throws TemplateException;

	void visit(PlaceholderNode n) throws TemplateException;

	void visit(IncludeNode n) throws TemplateException;

	void visit(MacroNode n) throws TemplateException;

	void visit(TargetNode n) throws TemplateException;

	void visit(ScriptNode n) throws TemplateException;
}
