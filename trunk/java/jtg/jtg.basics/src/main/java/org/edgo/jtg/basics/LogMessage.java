package org.edgo.jtg.basics;

public class LogMessage {

    public static final class MessageType {
    
        public final static MessageType ERROR = new MessageType("ERROR");
        
        public final static MessageType FATAL_ERROR = new MessageType("FATAL_ERROR");
        
        public final static MessageType WARNING = new MessageType("WARNING");
        
        public final static MessageType INFO = new MessageType("INFO");
        
        public final static MessageType[] values = new MessageType[] { ERROR, FATAL_ERROR, WARNING, INFO };
        
        private String value;
        
        private MessageType(String value)
        {
            this.value = value;
        }
        
        public static MessageType parse(String value)
        {
            for (MessageType ind: values) {
                if (ind.value.equals(value))
                {
                    return ind;
                }
            }
            return null;
        }
        
        public int hashCode()
        {
            return value.hashCode();
        }
        
        public boolean equals(Object obj)
        {
            if (!(obj instanceof MessageType))
            {
                return false;
            }   
            return value.equals(((MessageType)obj).value);
        }
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
