package org.edgo.jtg.core.model;

import org.edgo.jtg.basics.TemplateException;

public class IncludeNode extends PlaceholderNode {

	private String file;
	private String arg;
	private String format;
	
    public IncludeNode(String sourceFile, String name, int line) {
        super(sourceFile, name, line);
    }

    @Override
    public void accept(Visitor v) throws TemplateException {
        v.visit(this);
    }

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
