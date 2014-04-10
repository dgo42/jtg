package org.edgo.jtg.basics;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.edgo.jtg.basics.LogMessage.MessageType;

public class TemplateException extends Exception {

	private static final long		serialVersionUID	= 1507404863141855339L;

	private static final Pattern	LINE_NUMB_REGEX		= Pattern.compile("line ([0-9]+):([0-9]+): ",
																Pattern.CASE_INSENSITIVE);

	private static final Pattern	SAX_LINE_NUMB_REGEX	= Pattern.compile("lineNumber: ([0-9]+)", Pattern.CASE_INSENSITIVE);

	private List<LogMessage>		errors;

	private String					message;

	private String					fileName			= "unknown";

	private int						lineNumber			= -1;

	public TemplateException() {
	}

	public TemplateException(String message, Throwable cause, String fileName) {
		super(message, cause);
		constructMessage(cause);
		this.fileName = fileName;
	}

	public TemplateException(String message, Throwable cause, String fileName, int lineNumber) {
		super(message, cause);
		constructMessage(cause);
		this.fileName = fileName;
		this.lineNumber = lineNumber;
	}

	public TemplateException(String head, List<LogMessage> errors) {
		this.errors = errors;
		StringBuilder sb = new StringBuilder("Errorneus template compilation.\n");

		for (LogMessage message : errors) {
			if ((message.getType() == MessageType.ERROR) || ((message.getType() == MessageType.FATAL_ERROR))) {
				sb.append(head + ": " + message.getFile() + "\n     Line number: " + message.getLine() + " '"
						+ message.getErrorMessage() + "'\n");
			}
		}
		message = sb.toString();
	}

	/**
	 * @return the errors
	 */
	public List<LogMessage> getErrors() {
		return errors;
	}

	/**
	 * @return the message
	 */
	public String getTemplateMessage() {
		if (message == null) {
			return super.getMessage();
		}
		return message;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the lineNumber
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	private void constructMessage(Throwable cause) {
		if (cause != null) {
			Throwable inner = cause;
			while (null != inner.getCause()) {
				inner = inner.getCause();
			}
			if (inner != null && inner.getMessage() != null) {
				Matcher match = LINE_NUMB_REGEX.matcher(inner.toString());
				if (match.find()) {
					lineNumber = Integer.parseInt(match.group(1));
				}
				match = SAX_LINE_NUMB_REGEX.matcher(inner.toString());
				if (match.find()) {
					lineNumber = Integer.parseInt(match.group(1));
				}
			}
		}
	}
}
