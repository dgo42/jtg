package org.edgo.jtg.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.core.grammar.JavaTemplateGrammarParser;
import org.edgo.jtg.core.grammar.JavaTemplateLexer;
import org.edgo.jtg.core.model.ParsedUnit;
import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;
import org.mozilla.intl.chardet.nsPSMDetector;


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
		//double start = System.nanoTime();
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
            Charset charset = Charset.forName(encoding);
            //Input
            InputStreamReader encodedReader = new InputStreamReader(new FileInputStream(templFile), charset);
            
            // skip first unicode char
            int ch = encodedReader.read();
            if (ch != 0xFEFF) {
            	// reopen if non unicode
            	encodedReader.close();
                encodedReader = new InputStreamReader(new FileInputStream(templFile), charset);
            }
            // create main lexer - jtgLexer
            CharStream antlrStream = CharStreams.fromReader(encodedReader);
            //ANTLRInputStream antlrStream = new ANTLRInputStream(encodedReader);
            
            JavaTemplateLexer lexer = new JavaTemplateLexer(antlrStream);
            JavaTemplateGrammarParser parser = new JavaTemplateGrammarParser(new CommonTokenStream(lexer));
            ParsedUnit unit = new ParsedUnit(templatefile, encoding);

            parser.setUnit(unit);
/*            
            Token t = null;
            do {
            	t = lexer.nextToken();
            	System.out.println("Token type: " + t.getType() + ", channel: " + t.getChannel() + "   \"" + t.getText() + "\"");
            } while (t.getType() >= 0);
*/
            parser.template();

            return unit;
        } catch (Exception ex) {
            throw new TemplateException(ex.toString(), ex, templatefile);
        /*} finally {
		    double end = System.nanoTime();
		    double elapsed = (end - start) / 1000000;
		    String message = "=====> Elapsed time for generate \"" + templatefile + "\" " + new DecimalFormat("#.###").format(elapsed) + "ms";
        	System.out.println(message);*/
        }
    }
}
