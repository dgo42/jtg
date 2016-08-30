package org.edgo.jtg.generated;

import java.io.PrintWriter;
import java.lang.NullPointerException;
import org.edgo.jtg.basics.TemplateNullPointerException;
import java.util.Map;
import java.util.regex.Matcher;
import org.edgo.jtg.basics.TemplateClass;
import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.basics.IEnvironment;
import org.edgo.jtg.schema.*;

public class Details_Message_txt_jtgGeneratorClass extends TemplateClass {
    private EmployeeElement employee;

    public Details_Message_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, Map<String, Object> args) throws TemplateException {
        super(derivedType != null ? derivedType : Details_Message_txt_jtgGeneratorClass.class, cmdArgs, env, "UTF-8", args);
        templateFileName = "Input\\DirectUseTemplates\\Details\\Message.txt.jtg";
        if (!args.containsKey("employee")) { String message = "Property 'employee' isn't found in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, new Exception(message), templateFileName, 2); } try { this.employee = (EmployeeElement)args.get("employee"); } catch (ClassCastException ex) { String message = "Property 'employee' has missmathed type in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); }
    }

    public Details_Message_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, Object[] args) throws TemplateException {
        super(derivedType != null ? derivedType : Details_Message_txt_jtgGeneratorClass.class, cmdArgs, env, "UTF-8", args);
        templateFileName = "Input\\DirectUseTemplates\\Details\\Message.txt.jtg";
        try { this.employee = (EmployeeElement)args[0]; } catch (IndexOutOfBoundsException ex) { String message = "Property '0' doesn't exist in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); } catch (ClassCastException ex) { String message = "Property 'employee' has missmathed type in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); }
    }
    public Details_Message_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, String encoding, Map<String, Object> args) throws TemplateException {
        super(derivedType != null ? derivedType : Details_Message_txt_jtgGeneratorClass.class, cmdArgs, env, encoding, args);
        templateFileName = "Input\\DirectUseTemplates\\Details\\Message.txt.jtg";
        if (!args.containsKey("employee")) { String message = "Property 'employee' isn't found in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, new Exception(message), templateFileName, 2); } try { this.employee = (EmployeeElement)args.get("employee"); } catch (ClassCastException ex) { String message = "Property 'employee' has missmathed type in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); }
    }

    public Details_Message_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, String encoding, Object[] args) throws TemplateException {
        super(derivedType != null ? derivedType : Details_Message_txt_jtgGeneratorClass.class, cmdArgs, env, encoding, args);
        templateFileName = "Input\\DirectUseTemplates\\Details\\Message.txt.jtg";
        try { this.employee = (EmployeeElement)args[0]; } catch (IndexOutOfBoundsException ex) { String message = "Property '0' doesn't exist in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); } catch (ClassCastException ex) { String message = "Property 'employee' has missmathed type in template '" + templateFileName + "' at line 2"; throw new TemplateException(message, ex, templateFileName, 2); }
    }

    public void Generate(final PrintWriter output) throws TemplateException {
        try {
        
        	// # sharp
                    try { output.print(employee.firstName); } catch (NullPointerException ex) { throw new TemplateNullPointerException(null, ex, templateFileName, 12); }output.print(" <tag>\r\n" + 
            "-------------------------\r\n" + 
            "Sehr geehrte(r) "); 
            try { output.print(employee.firstName); } catch (NullPointerException ex) { throw new TemplateNullPointerException(null, ex, templateFileName, 14); }output.print(" "); 
            try { output.print(employee.lastName); } catch (NullPointerException ex) { throw new TemplateNullPointerException(null, ex, templateFileName, 14); }output.print(",\r\n" + 
            "\r\n" + 
            "Ihre ID ist "); 
            try { output.print(employee.id); } catch (NullPointerException ex) { throw new TemplateNullPointerException(null, ex, templateFileName, 16); }output.print(".\r\n" + 
            "\r\n" + 
            "Mit freundlichen Gr\u00FC\u00DFen\r\n" + 
            "\u0439\u0446\u0443\u043A\u0435\u043D\u0433\u0448\u0449\u0437\u0445\r\n" + 
            "\u0419\u0426\u0423\u041A\u0415\u041D\u0413\u0428\u0429\u0417\u0425\r\n" + 
            "------------------------\r\n" + 
            ""); 


        } catch(TemplateException e) {
            throw e;
        } catch(Throwable e) {
            StackTraceElement[] stackTraces = e.getStackTrace();
            int srcLineNumber = -1;
            for (StackTraceElement stackTrace : stackTraces) {
                if (stackTrace.getClassName().contains("Details_Message_txt_jtgGeneratorClass")) {
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

    
	public int x = 7;
    

    static {
		linesMapping.put(14, 2);
		linesMapping.put(15, 2);
		linesMapping.put(41, 6);
		linesMapping.put(42, 7);
		linesMapping.put(43, 8);
		linesMapping.put(43, 12);
		linesMapping.put(43, 12);
		linesMapping.put(44, 12);
		linesMapping.put(45, 12);
		linesMapping.put(46, 14);
		linesMapping.put(46, 14);
		linesMapping.put(47, 14);
		linesMapping.put(47, 14);
		linesMapping.put(48, 14);
		linesMapping.put(49, 14);
		linesMapping.put(50, 16);
		linesMapping.put(50, 16);
		linesMapping.put(51, 16);
		linesMapping.put(52, 16);
		linesMapping.put(53, 16);
		linesMapping.put(54, 16);
		linesMapping.put(55, 16);
		linesMapping.put(56, 16);
		linesMapping.put(89, 3);
		linesMapping.put(90, 4);
		linesMapping.put(91, 5);
		linesMapping.put(92, 5);
    }

}

