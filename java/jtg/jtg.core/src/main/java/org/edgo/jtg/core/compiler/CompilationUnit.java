package org.edgo.jtg.core.compiler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;

import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.edgo.jtg.core.Logger;
import org.edgo.jtg.core.model.ParsedUnit;



public class CompilationUnit implements ICompilationUnit {

    private final String className;
    private final String sourceFile;
    private final String encoding;
    private final ParsedUnit unit;
    private final Logger log;

    public CompilationUnit(String sourceFile, String className, String encoding, ParsedUnit unit, Logger log) {
        this.className = className;
        this.sourceFile = sourceFile;
        this.encoding = encoding;
        this.unit = unit;
        this.log = log;
    }

    public char[] getFileName() {
        return sourceFile.toCharArray();
    }

    public char[] getContents() {
        char[] result = null;
        InputStreamReader isReader = null;
        Reader reader = null;
        try {
            isReader = new InputStreamReader(new FileInputStream(sourceFile), encoding);
            reader = new BufferedReader(isReader);
            if (reader != null) {
                char[] chars = new char[8192];
                StringBuffer buf = new StringBuffer();
                int count;
                while ((count = reader.read(chars, 0, chars.length)) > 0) {
                    buf.append(chars, 0, count);
                }
                result = new char[buf.length()];
                buf.getChars(0, result.length, result, 0);
            }
        } catch (IOException e) {
            log.error("Compilation error", e);
        } finally {
        	if (reader != null) {
        		try {
        			reader.close();
        		} catch(Exception e) {
        			// noting to do
        		}
        	}
        	if (isReader != null) {
        		try {
        			isReader.close();
        		} catch(Exception e) {
        			// noting to do
        		}
        	}
        }
        
        return result;
    }

    public char[] getMainTypeName() {
        int dot = className.lastIndexOf('.');
        if (dot > 0) {
            return className.substring(dot + 1).toCharArray();
        }
        return className.toCharArray();
    }

    public char[][] getPackageName() {
        StringTokenizer izer = new StringTokenizer(className, ".");
        char[][] result = new char[izer.countTokens() - 1][];
        for (int i = 0; i < result.length; i++) {
            String tok = izer.nextToken();
            result[i] = tok.toCharArray();
        }
        return result;
    }
    
    public ParsedUnit getUnit() {
        return unit;
    }

	public boolean ignoreOptionalProblems() {
		return true;
	}
}
