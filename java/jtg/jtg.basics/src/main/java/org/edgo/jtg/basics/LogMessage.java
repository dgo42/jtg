package org.edgo.jtg.basics;

public class LogMessage {

    public static enum MessageType {
    	// @formatter:on
        ERROR,
        FATAL_ERROR,
        WARNING,
        INFO;
    	// @formatter:off
    }
    
    private MessageType type;
    private String javaFileName;
    private int javaLineNum;
    private String templateFileName;
    private int templateBeginLineNum;
    private String errMsg;

    public LogMessage(MessageType type, String javaFileName, int javaLineNum, String errMsg) {
        this.type = type;
        this.javaFileName = javaFileName;
        this.javaLineNum = javaLineNum;
        this.errMsg = errMsg;
        this.templateBeginLineNum = -1;
    }

    public LogMessage(MessageType type, String javaFileName, int javaLineNum, String templateFileName, int templateBeginLineNum, String errMsg) {

        this(type, javaFileName, javaLineNum, errMsg);
        this.templateFileName = templateFileName;
        this.templateBeginLineNum = templateBeginLineNum;
    }

    public String getJavaFileName() {
        return this.javaFileName;
    }

    public int getJavaLineNumber() {
        return this.javaLineNum;
    }

    public String getTemplateFileName() {
        return this.templateFileName;
    }

    public int getTemplateBeginLineNumber() {
        return this.templateBeginLineNum;
    }

    public String getErrorMessage() {
        return this.errMsg.toString();
    }
    
    public MessageType getType() {
        return type;
    }

    public String getFile() {
        return (templateBeginLineNum > 0 ? templateFileName : javaFileName);
    }

    public int getLine() {
        return (templateBeginLineNum > 0 ? templateBeginLineNum : javaLineNum);
    }

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public void setTemplateBeginLineNum(int templateBeginLineNum) {
		this.templateBeginLineNum = templateBeginLineNum;
	}
    
    
}
