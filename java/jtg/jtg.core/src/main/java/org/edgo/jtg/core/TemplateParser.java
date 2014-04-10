package org.edgo.jtg.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.core.grammar.JavaTemplateLexer;
import org.edgo.jtg.core.grammar.JavaTemplateParser;
import org.edgo.jtg.core.grammar.MacroLexer;
import org.edgo.jtg.core.grammar.TargetLexer;
import org.edgo.jtg.core.model.ParsedUnit;
import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;
import org.mozilla.intl.chardet.nsPSMDetector;

import antlr.TokenStreamSelector;


public class TemplateParser {

    private String templatefile;

    private String encoding;
    
    public TemplateParser(String templatefile) {
        this.templatefile = templatefile;
    }

    public String getTemplateFile() {
        return templatefile;
    }

    public ParsedUnit Parse() throws TemplateException {
        try {
            // open a simple stream to the input
            File templFile = new File(templatefile);
            byte[] buffer = new byte[(int)templFile.length()];
        	InputStream reader = null;
        	try {
        		reader = new FileInputStream(templFile);
        		reader.read(buffer, 0, buffer.length);
        	} finally {
        		if (reader != null) {
        			try {
        				reader.close();
        			} catch(Exception e) {
        				// nothing to do
        			}
        		}
        	}
            nsDetector detector = new nsDetector(nsPSMDetector.MAX_VERIFIERS);
            detector.Init(new nsICharsetDetectionObserver() {
                public void Notify(String charset) {
                    encoding = charset;
                }
            });
            detector.DoIt(buffer, buffer.length, false);
            String[] probEncoding = detector.getProbableCharsets();
            if (probEncoding.length > 0) {
                encoding = probEncoding[0]; 
            }

            //Input
            Reader encodedReader = new InputStreamReader(new FileInputStream(templFile), encoding);
            
            // skip first unicode char
            int ch = encodedReader.read();
            if (ch != 0xFEFF) {
//                encoding = System.getProperty("file.encoding", "UTF-8");
            	// reopen if non unicode
            	encodedReader.close();
                encodedReader = new InputStreamReader(new FileInputStream(templFile), encoding);
            }
            // create main lexer - TargetLexer
            TargetLexer targetLexer = new TargetLexer(encodedReader);
            MacroLexer macroLexer = new MacroLexer(targetLexer.getInputState());
            JavaTemplateLexer jtgLexer = new JavaTemplateLexer(macroLexer.getInputState());

            TokenStreamSelector selector = new TokenStreamSelector();
            // notify lexers about selector
            targetLexer.setSelector(selector);
            jtgLexer.setSelector(selector);
            macroLexer.setSelector(selector);
            // notify selector about various lexers; name them for convenient reference later
            selector.addInputStream(targetLexer, "targetcode");
            selector.addInputStream(jtgLexer, "main");
            selector.addInputStream(macroLexer, "macrocode");
            selector.select("targetcode"); // start with target code lexer

            JavaTemplateParser parser = new JavaTemplateParser(selector);
            ParsedUnit unit = new ParsedUnit(templatefile, encoding);
            parser.setFilename(templatefile);
            parser.template(unit);
            return unit;
        } catch (Exception ex) {
            throw new TemplateException(ex.toString(), ex, templatefile);
        } finally {
        }
    }
}
