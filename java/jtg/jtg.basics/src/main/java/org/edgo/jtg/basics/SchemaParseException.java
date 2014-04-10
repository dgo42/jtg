package org.edgo.jtg.basics;

public class SchemaParseException extends Exception {

    private static final long serialVersionUID = 6993219145217582210L;

    public SchemaParseException() {
    }

    public SchemaParseException(String message) {
        super(message);
    }

    public SchemaParseException(Throwable cause) {
        super(cause);
    }

    public SchemaParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
