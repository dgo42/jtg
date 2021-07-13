package org.edgo.jtg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Argument.class, name = "Argument"),
    @JsonSubTypes.Type(value = Extends.class, name = "Extends"),
    @JsonSubTypes.Type(value = Import.class, name = "Import"),
    @JsonSubTypes.Type(value = ScriptNode.class, name = "ScriptNode"),
    @JsonSubTypes.Type(value = TemplateNode.class, name = "TemplateNode")}
)
public abstract class TextNode extends Node {

    private String text;

    public TextNode() {
    }
    
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
