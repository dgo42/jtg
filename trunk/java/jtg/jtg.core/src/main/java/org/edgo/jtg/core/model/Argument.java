package org.edgo.jtg.core.model;

import org.edgo.jtg.basics.TemplateException;

public class Argument extends TextNode {

    private final String type;

    private final int num;

    public Argument(String sourceFile, String name, String type, int num, int line) {
        super(sourceFile, name, line);
        this.type = type;
        this.num = num;
    }

    /**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	@Override
    public void accept(Visitor v) throws TemplateException {
        v.visit(this);
    }
}
