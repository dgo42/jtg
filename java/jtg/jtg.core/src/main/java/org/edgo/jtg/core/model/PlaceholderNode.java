package org.edgo.jtg.core.model;

import org.edgo.jtg.basics.TemplateException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = IncludeNode.class, name = "IncludeNode") }
)
public class PlaceholderNode extends TemplateNode {

	public PlaceholderNode() {
		
	}
	
    public PlaceholderNode(String sourceFile, String name, int line) {
        super(sourceFile, name, line);
    }

    @Override
    public void accept(Visitor v) throws TemplateException {
        v.visit(this);
    }

}
