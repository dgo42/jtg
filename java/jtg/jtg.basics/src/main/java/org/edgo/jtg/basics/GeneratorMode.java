package org.edgo.jtg.basics;

public final class GeneratorMode {
    
    private GeneratorMode() {
    }

    public final static GeneratorMode OVEWRITE = new GeneratorMode("OVEWRITE");
    
    public final static GeneratorMode ONLY_NEW = new GeneratorMode("ONLY_NEW");
    
    public final static GeneratorMode MAKE_COPY = new GeneratorMode("MAKE_COPY");
    
    public final static GeneratorMode[] values = new GeneratorMode[] { OVEWRITE, ONLY_NEW, MAKE_COPY };
    
    private String value;
    
    private GeneratorMode(String value)
    {
        this.value = value;
    }
    
    public static GeneratorMode parse(String value)
    {
        for (GeneratorMode ind: values) {
            if (ind.value.equals(value))
            {
                return ind;
            }
        }
        return null;
    }
    
    public int hashCode()
    {
        return value.hashCode();
    }
    
    public boolean equals(Object obj)
    {
        if (!(obj instanceof GeneratorMode))
        {
            return false;
        }   
        return value.equals(((GeneratorMode)obj).value);
    }

}
