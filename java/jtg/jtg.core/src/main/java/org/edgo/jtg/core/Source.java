package org.edgo.jtg.core;

import org.edgo.jtg.core.model.ParsedUnit;

public class Source {
    public String Encoding;
    public String ClassName;
    public ParsedUnit Unit;
    public long LastModified;
	public String MainArg;

    public Source() {
    }
    
    public Source(String encoding, String className, ParsedUnit unit, long lastModified, String mainArg) {
        this.Encoding = encoding;
        this.ClassName = className;
        this.Unit = unit;
        this.LastModified = lastModified;
        this.MainArg = mainArg;
    }
}

