package org.edgo.jtg.basics;

import java.util.List;

public class TemplateNullPointerException extends TemplateException {

	private static final long serialVersionUID = -5562008041563334545L;

	public TemplateNullPointerException() {
	}

	public TemplateNullPointerException(String message, Throwable cause, String fileName) {
		super(message, cause, fileName);
	}

	public TemplateNullPointerException(String message, Throwable cause, String fileName, int lineNumber) {
		super("NullPointerException in template '" + fileName + "' at line " + lineNumber, cause, fileName, lineNumber);
	}

	public TemplateNullPointerException(String head, List<LogMessage> errors) {
		super(head, errors);
	}

}
