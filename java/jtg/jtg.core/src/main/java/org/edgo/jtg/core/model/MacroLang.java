package org.edgo.jtg.core.model;

public final class MacroLang {
    
    public final static MacroLang JAVA = new MacroLang("JAVA");
    
    public final static MacroLang NONE = new MacroLang("NONE");
    
    public final static MacroLang[] values = new MacroLang[] { JAVA, NONE };
    
    private String value;
    
    private MacroLang(String value)
    {
        this.value = value;
    }
    
    public static MacroLang parse(String value)
    {
        if ("java".equals(value.toLowerCase()))
        {
            return MacroLang.JAVA;
        }
        else
        {
            return MacroLang.NONE;
        }
    }
    
    public int hashCode()
    {
        return value.hashCode();
    }
    
    public boolean equals(Object obj)
    {
        if (!(obj instanceof MacroLang))
        {
            return false;
        }   
        return value.equals(((MacroLang)obj).value);
    }
}

