package org.edgo.jtg.generated;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import org.edgo.jtg.basics.TemplateClass;
import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.basics.IEnvironment;
import org.edgo.jtg.schema.*;

public class DirectUse_txt_jtgGeneratorClass extends TemplateClass {
    private DirectUseElement mainElement;

    public DirectUse_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, Map<String, Object> args) throws TemplateException {
        super(derivedType != null ? derivedType : DirectUse_txt_jtgGeneratorClass.class, cmdArgs, env, "UTF-8", args);
        templateFileName = "Input\\DirectUseTemplates\\DirectUse.txt.jtg";
        if (!args.containsKey("mainElement")) { String message = "Property 'mainElement' isn't found in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, new Exception(message), templateFileName, 2); } try { this.mainElement = (DirectUseElement)args.get("mainElement"); } catch (ClassCastException ex) { String message = "Property 'mainElement' has missmathed type in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); }
    }

    public DirectUse_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, Object[] args) throws TemplateException {
        super(derivedType != null ? derivedType : DirectUse_txt_jtgGeneratorClass.class, cmdArgs, env, "UTF-8", args);
        templateFileName = "Input\\DirectUseTemplates\\DirectUse.txt.jtg";
        try { this.mainElement = (DirectUseElement)args[0]; } catch (IndexOutOfBoundsException ex) { String message = "Property '0' doesn't exist in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); } catch (ClassCastException ex) { String message = "Property 'mainElement' has missmathed type in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); }
    }
    public DirectUse_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, String encoding, Map<String, Object> args) throws TemplateException {
        super(derivedType != null ? derivedType : DirectUse_txt_jtgGeneratorClass.class, cmdArgs, env, encoding, args);
        templateFileName = "Input\\DirectUseTemplates\\DirectUse.txt.jtg";
        if (!args.containsKey("mainElement")) { String message = "Property 'mainElement' isn't found in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, new Exception(message), templateFileName, 2); } try { this.mainElement = (DirectUseElement)args.get("mainElement"); } catch (ClassCastException ex) { String message = "Property 'mainElement' has missmathed type in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); }
    }

    public DirectUse_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, String encoding, Object[] args) throws TemplateException {
        super(derivedType != null ? derivedType : DirectUse_txt_jtgGeneratorClass.class, cmdArgs, env, encoding, args);
        templateFileName = "Input\\DirectUseTemplates\\DirectUse.txt.jtg";
        try { this.mainElement = (DirectUseElement)args[0]; } catch (IndexOutOfBoundsException ex) { String message = "Property '0' doesn't exist in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); } catch (ClassCastException ex) { String message = "Property 'mainElement' has missmathed type in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); }
    }

    public void Generate(PrintWriter output) throws TemplateException {
        try {
            
        
            // 3. Variante
            for (EmployeeElement employee : mainElement.employeeElements.employeeElements) {
        output.println();
            output.print("	Next employee..."); output.println();
            
                RunTemplate ("Details\\Message.txt.jtg", "Output\\" + employee.lastName + ".txt", new Object[] { employee });
            }
        

        } catch(Throwable e) {
            StackTraceElement[] stackTraces = e.getStackTrace();
            int srcLineNumber = -1;
            for (StackTraceElement stackTrace : stackTraces) {
                if (stackTrace.getClassName().contains("DirectUse_txt_jtgGeneratorClass")) {
                    srcLineNumber = stackTrace.getLineNumber();
                    break;
                }
            }
            if (srcLineNumber >= 0) {
                if (linesMapping.containsKey(srcLineNumber)) {
                    int lineNumber = (Integer) linesMapping.get(srcLineNumber);
                    if (e.getMessage() != null) {
                        Matcher matcher = TEMPLATE_CLASS_PATTERN.matcher(e.getMessage());
                        String message = e.toString();
                        if (ClassNotFoundException.class.isInstance(e) && matcher.find()) {
                            message = "Template \"" + matcher.group(1) + "\" not found!";
                        }
                        throw new TemplateException(message, e, templateFileName, lineNumber);
                    }
                }
            }
            throw new TemplateException(e.getMessage(), e, templateFileName);
        } finally {
            output.flush();
        }
    }

    public int test;
    
	public void print() {
		System.out.println("Hello World!");
	}
	/*
	 * multiline comment
	 */
	// single line java comment
    

    static {
		linesMapping.put(13, 2);
		linesMapping.put(14, 2);
		linesMapping.put(40, 16);
		linesMapping.put(41, 17);
		linesMapping.put(42, 18);
		linesMapping.put(43, 19);
		linesMapping.put(44, 20);
		linesMapping.put(0, 20);
		linesMapping.put(45, 21);
		linesMapping.put(46, 22);
		linesMapping.put(0, 22);
		linesMapping.put(47, 23);
		linesMapping.put(48, 24);
		linesMapping.put(49, 25);
		linesMapping.put(50, 25);
		linesMapping.put(0, 25);
		linesMapping.put(79, 4);
		linesMapping.put(80, 5);
		linesMapping.put(81, 6);
		linesMapping.put(82, 7);
		linesMapping.put(83, 8);
		linesMapping.put(84, 9);
		linesMapping.put(85, 10);
		linesMapping.put(86, 11);
		linesMapping.put(87, 12);
		linesMapping.put(88, 13);
		linesMapping.put(89, 13);
    }

}

