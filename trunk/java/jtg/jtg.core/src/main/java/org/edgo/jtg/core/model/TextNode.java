package org.edgo.jtg.core.model;

public abstract class TextNode extends Node {

    private final String text;

    public TextNode(String sourceFile, String text, int sourceLineBegin) {
        super(sourceFile, sourceLineBegin);
        this.text = text;
    }

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

}
