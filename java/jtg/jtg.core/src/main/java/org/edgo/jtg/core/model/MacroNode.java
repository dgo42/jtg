package org.edgo.jtg.core.model;

import org.edgo.jtg.basics.TemplateException;

public class MacroNode extends TemplateNode {

	public MacroNode() {
		
	}
	
    public MacroNode(String sourceFile, String name, int line) {
        super(sourceFile, name, line);
    }

    @Override
    public void accept(Visitor v) throws TemplateException {
        v.visit(this);
    }

}
