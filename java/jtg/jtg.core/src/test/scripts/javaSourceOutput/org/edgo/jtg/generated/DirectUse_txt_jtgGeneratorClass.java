package org.edgo.jtg.generated;

import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Matcher;
import org.edgo.jtg.basics.TemplateClass;
import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.basics.IEnvironment;
import org.edgo.jtg.schema.*;
import java.lang.Thread;
import java.lang.Runnable;
import java.lang.Override;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DirectUse_txt_jtgGeneratorClass extends TemplateClass {
    private DirectUseElement mainElement;

    public DirectUse_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, Map<String, Object> args) throws TemplateException {
        super(derivedType != null ? derivedType : DirectUse_txt_jtgGeneratorClass.class, cmdArgs, env, "UTF-8", args);
        templateFileName = "Input\\DirectUseTemplates\\DirectUse.txt.jtg";
        if (!args.containsKey("mainElement")) { String message = "Property 'mainElement' isn't found in template '" + templateFileName + "' at line 9"; throw new TemplateException(message, new Exception(message), templateFileName, 9); } try { this.mainElement = (DirectUseElement)args.get("mainElement"); } catch (ClassCastException ex) { String message = "Property 'mainElement' has missmathed type in template '" + templateFileName + "' at line 9"; throw new TemplateException(message, ex, templateFileName, 9); }
    }

    public DirectUse_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, Object[] args) throws TemplateException {
        super(derivedType != null ? derivedType : DirectUse_txt_jtgGeneratorClass.class, cmdArgs, env, "UTF-8", args);
        templateFileName = "Input\\DirectUseTemplates\\DirectUse.txt.jtg";
        try { this.mainElement = (DirectUseElement)args[0]; } catch (IndexOutOfBoundsException ex) { String message = "Property '0' doesn't exist in template '" + templateFileName + "' at line 9"; throw new TemplateException(message, ex, templateFileName, 9); } catch (ClassCastException ex) { String message = "Property 'mainElement' has missmathed type in template '" + templateFileName + "' at line 9"; throw new TemplateException(message, ex, templateFileName, 9); }
    }
    public DirectUse_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, String encoding, Map<String, Object> args) throws TemplateException {
        super(derivedType != null ? derivedType : DirectUse_txt_jtgGeneratorClass.class, cmdArgs, env, encoding, args);
        templateFileName = "Input\\DirectUseTemplates\\DirectUse.txt.jtg";
        if (!args.containsKey("mainElement")) { String message = "Property 'mainElement' isn't found in template '" + templateFileName + "' at line 9"; throw new TemplateException(message, new Exception(message), templateFileName, 9); } try { this.mainElement = (DirectUseElement)args.get("mainElement"); } catch (ClassCastException ex) { String message = "Property 'mainElement' has missmathed type in template '" + templateFileName + "' at line 9"; throw new TemplateException(message, ex, templateFileName, 9); }
    }

    public DirectUse_txt_jtgGeneratorClass(Class<?> derivedType, Map<String, String> cmdArgs, IEnvironment env, String encoding, Object[] args) throws TemplateException {
        super(derivedType != null ? derivedType : DirectUse_txt_jtgGeneratorClass.class, cmdArgs, env, encoding, args);
        templateFileName = "Input\\DirectUseTemplates\\DirectUse.txt.jtg";
        try { this.mainElement = (DirectUseElement)args[0]; } catch (IndexOutOfBoundsException ex) { String message = "Property '0' doesn't exist in template '" + templateFileName + "' at line 9"; throw new TemplateException(message, ex, templateFileName, 9); } catch (ClassCastException ex) { String message = "Property 'mainElement' has missmathed type in template '" + templateFileName + "' at line 9"; throw new TemplateException(message, ex, templateFileName, 9); }
    }

    public void Generate(final PrintWriter output) throws TemplateException {
        try {
        
        /*	List<Thread> threads = new ArrayList<Thread>();
            
            final TemplateClass me = this;
            final EmployeeElement emp = mainElement.employeeElements.employeeElements.get(0);
            
            Thread thread = new Thread(new Runnable() {
        		
        		@Override
        		public void run() {
        			double start = System.nanoTime();
        			for(int i = 0; i < 1000; i++) {
        				try {
        		    		me.RunTemplate ("Details\\Message.txt.jtg", "Output\\test.txt", new Object[] { emp });
        	    		} catch(TemplateException e) {
        	    			e.printStackTrace(output);
        	    		}
        			}
        			double end = System.nanoTime();
        			double elapsed = (end - start) / 1000000;
        			output.println("Elapsed time " + new DecimalFormat("#.######").format(elapsed) + " ms");
        		}
        	});
        	thread.start();
        	threads.add(thread);
            for (Thread t : threads) {
            	t.join();
            }
        */
            // 3. Variante
            for (EmployeeElement employee : mainElement.employeeElements.employeeElements) {
        output.print("\r\n" + 
            "	Next employee...\r\n" + 
            ""); 
        
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
		linesMapping.put(19, 9);
		linesMapping.put(20, 9);
		linesMapping.put(10, 2);
		linesMapping.put(11, 3);
		linesMapping.put(12, 4);
		linesMapping.put(13, 5);
		linesMapping.put(14, 6);
		linesMapping.put(15, 7);
		linesMapping.put(16, 8);
		linesMapping.put(17, 8);
		linesMapping.put(18, 8);
		linesMapping.put(19, 8);
		linesMapping.put(46, 24);
		linesMapping.put(47, 25);
		linesMapping.put(48, 26);
		linesMapping.put(49, 27);
		linesMapping.put(50, 28);
		linesMapping.put(51, 29);
		linesMapping.put(52, 30);
		linesMapping.put(53, 31);
		linesMapping.put(54, 32);
		linesMapping.put(55, 33);
		linesMapping.put(56, 34);
		linesMapping.put(57, 35);
		linesMapping.put(58, 36);
		linesMapping.put(59, 37);
		linesMapping.put(60, 38);
		linesMapping.put(61, 39);
		linesMapping.put(62, 40);
		linesMapping.put(63, 41);
		linesMapping.put(64, 42);
		linesMapping.put(65, 43);
		linesMapping.put(66, 44);
		linesMapping.put(67, 45);
		linesMapping.put(68, 46);
		linesMapping.put(69, 47);
		linesMapping.put(70, 48);
		linesMapping.put(71, 49);
		linesMapping.put(72, 50);
		linesMapping.put(73, 51);
		linesMapping.put(74, 52);
		linesMapping.put(75, 53);
		linesMapping.put(76, 54);
		linesMapping.put(77, 55);
		linesMapping.put(78, 55);
		linesMapping.put(79, 55);
		linesMapping.put(0, 55);
		linesMapping.put(1, 55);
		linesMapping.put(2, 55);
		linesMapping.put(3, 55);
		linesMapping.put(4, 55);
		linesMapping.put(5, 55);
		linesMapping.put(6, 55);
		linesMapping.put(7, 55);
		linesMapping.put(8, 55);
		linesMapping.put(9, 55);
		linesMapping.put(10, 55);
		linesMapping.put(11, 55);
		linesMapping.put(12, 55);
		linesMapping.put(13, 55);
		linesMapping.put(14, 55);
		linesMapping.put(15, 55);
		linesMapping.put(16, 55);
		linesMapping.put(17, 55);
		linesMapping.put(18, 55);
		linesMapping.put(19, 55);
		linesMapping.put(20, 55);
		linesMapping.put(21, 55);
		linesMapping.put(22, 55);
		linesMapping.put(23, 55);
		linesMapping.put(24, 55);
		linesMapping.put(25, 55);
		linesMapping.put(26, 55);
		linesMapping.put(27, 55);
		linesMapping.put(28, 55);
		linesMapping.put(29, 55);
		linesMapping.put(30, 55);
		linesMapping.put(31, 55);
		linesMapping.put(32, 55);
		linesMapping.put(33, 55);
		linesMapping.put(34, 55);
		linesMapping.put(35, 55);
		linesMapping.put(36, 55);
		linesMapping.put(37, 55);
		linesMapping.put(38, 55);
		linesMapping.put(39, 55);
		linesMapping.put(40, 55);
		linesMapping.put(41, 55);
		linesMapping.put(42, 55);
		linesMapping.put(43, 55);
		linesMapping.put(44, 55);
		linesMapping.put(45, 55);
		linesMapping.put(46, 55);
		linesMapping.put(47, 55);
		linesMapping.put(48, 55);
		linesMapping.put(49, 55);
		linesMapping.put(50, 55);
		linesMapping.put(51, 55);
		linesMapping.put(52, 55);
		linesMapping.put(53, 55);
		linesMapping.put(54, 55);
		linesMapping.put(55, 55);
		linesMapping.put(56, 55);
		linesMapping.put(57, 55);
		linesMapping.put(58, 55);
		linesMapping.put(80, 57);
		linesMapping.put(81, 58);
		linesMapping.put(82, 59);
		linesMapping.put(83, 60);
		linesMapping.put(84, 60);
		linesMapping.put(113, 10);
		linesMapping.put(114, 11);
		linesMapping.put(115, 12);
		linesMapping.put(116, 13);
		linesMapping.put(117, 14);
		linesMapping.put(118, 15);
		linesMapping.put(119, 16);
		linesMapping.put(120, 17);
		linesMapping.put(121, 18);
		linesMapping.put(122, 19);
		linesMapping.put(123, 20);
		linesMapping.put(124, 20);
    }

}

