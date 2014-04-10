package org.edgo.jtg.core.model;

public abstract class TemplateNode extends TextNode {

    public TemplateNode(String sourceFile, String text, int sourceLineBegin) {
        super(sourceFile, text, sourceLineBegin);
    }
}
