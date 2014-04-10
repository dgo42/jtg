package org.edgo.jtg.core;


public final class GeneratorCommand {
    
    public final static GeneratorCommand SCHEMA_ONLY = new GeneratorCommand("JAVA");
    
    public final static GeneratorCommand TEMPLATES_ONLY = new GeneratorCommand("TEMPLATES_ONLY");
    
    public final static GeneratorCommand SOURCES_ONLY = new GeneratorCommand("SOURCES_ONLY");
    
    public final static GeneratorCommand JAR_ONLY = new GeneratorCommand("JAR_ONLY");
    
    public final static GeneratorCommand COMPLETE = new GeneratorCommand("COMPLETE");
    
    public final static GeneratorCommand[] values = new GeneratorCommand[] { SCHEMA_ONLY, TEMPLATES_ONLY, SOURCES_ONLY, JAR_ONLY, COMPLETE };
    
    private String value;
    
    private GeneratorCommand(String value)
    {
        this.value = value;
    }
    
    public static GeneratorCommand parse(String value)
    {
        for (GeneratorCommand ind: values) {
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
        if (!(obj instanceof GeneratorCommand))
        {
            return false;
        }   
        return value.equals(((GeneratorCommand)obj).value);
    }
}
