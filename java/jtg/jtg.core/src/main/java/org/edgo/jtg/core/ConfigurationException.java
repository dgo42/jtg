package org.edgo.jtg.core;

public class ConfigurationException extends Exception {

    private static final long serialVersionUID = 7118904390849073064L;

    public ConfigurationException() {
    }

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(Throwable cause) {
        super(cause);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

}
