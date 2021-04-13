package org.edgo.jtg.core.compiler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.classfmt.ClassFileReader;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;
import org.edgo.jtg.core.Logger;



public class NameEnvironment implements INameEnvironment {

    private final Map<String, CompilationUnit> sources;
    
    private final ClassLoader classLoader;
    
    private final Logger log;
    
    public NameEnvironment(Map<String, CompilationUnit> sources, ClassLoader classLoader, Logger log) {
        this.sources = sources;
        this.classLoader = classLoader;
        this.log = log;
    }
    
    public void cleanup() {
    }

    public NameEnvironmentAnswer findType(char[][] compoundTypeName) {
        String result = "";
        String sep = "";
        for (int i = 0; i < compoundTypeName.length; i++) {
            result += sep;
            result += new String(compoundTypeName[i]);
            sep = ".";
        }
        return findType(result);
    }

    public NameEnvironmentAnswer findType(char[] typeName, char[][] packageName) {
        String result = "";
        String sep = "";
        for (int i = 0; i < packageName.length; i++) {
            result += sep;
            result += new String(packageName[i]);
            sep = ".";
        }
        result += sep;
        result += new String(typeName);
        return findType(result);
    }

    private NameEnvironmentAnswer findType(String className) {

        InputStream is = null;
        try {
            if (sources.containsKey(className)) {
                return new NameEnvironmentAnswer(sources.get(className), null);
            }
            String resourceName = className.replace('.', '/') + ".class";
            is = classLoader.getResourceAsStream(resourceName);
            if (is != null) {
                byte[] classBytes;
                byte[] buf = new byte[8192];
                ByteArrayOutputStream baos = new ByteArrayOutputStream(buf.length);
                int count;
                while ((count = is.read(buf, 0, buf.length)) > 0) {
                    baos.write(buf, 0, count);
                }
                baos.flush();
                classBytes = baos.toByteArray();
                char[] fileName = className.toCharArray();
                ClassFileReader classFileReader = new ClassFileReader(classBytes, fileName, true);
                return new NameEnvironmentAnswer(classFileReader, null);
            }
        } catch (IOException exc) {
            if (log.isErrorEnabled()) {
            	log.error("Compilation error", exc);
            }
} catch (org.eclipse.jdt.internal.compiler.classfmt.ClassFormatException exc) {
            if (log.isErrorEnabled()) {
            	log.error("Compilation error", exc);
            }
} finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException exc) {
                    // Ignore
                }
            }
        }
        return null;
    }

    public boolean isPackage(char[][] parentPackageName, char[] packageName) {
        String result = "";
        String sep = "";
        if (parentPackageName != null) {
            for (int i = 0; i < parentPackageName.length; i++) {
                result += sep;
                String str = new String(parentPackageName[i]);
                result += str;
                sep = ".";
            }
        }
        String str = new String(packageName);
        if (Character.isUpperCase(str.charAt(0))) {
            if (!isPackage(result)) {
                return false;
            }
        }
        result += sep;
        result += str;
        return isPackage(result);
    }
        
    private boolean isPackage(String result) {
        if (sources.containsKey(result)) {
            return false;
        }
        String resourceName = result.replace('.', '/') + ".class";
        InputStream is = classLoader.getResourceAsStream(resourceName);
        return is == null;
    }

}
