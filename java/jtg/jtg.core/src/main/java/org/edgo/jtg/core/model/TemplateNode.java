package org.edgo.jtg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = MacroNode.class, name = "MacroNode"),
    @JsonSubTypes.Type(value = PlaceholderNode.class, name = "PlaceholderNode"),
    @JsonSubTypes.Type(value = TargetNode.class, name = "TargetNode") }
)
public abstract class TemplateNode extends TextNode {

	public TemplateNode() {

	}

	public TemplateNode(String sourceFile, String text, int sourceLineBegin) {
		super(sourceFile, text, sourceLineBegin);
	}
}
