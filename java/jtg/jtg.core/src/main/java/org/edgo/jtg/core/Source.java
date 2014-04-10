package org.edgo.jtg.core;

import org.edgo.jtg.core.model.ParsedUnit;

public class Source {
    public final String Encoding;
    public final String ClassName;
    public final ParsedUnit Unit;

    public Source(String encoding, String className, ParsedUnit unit) {
        this.Encoding = encoding;
        this.ClassName = className;
        this.Unit = unit;
    }
}

