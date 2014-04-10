package org.edgo.jtg.basics;

public class EnvironmentException extends Exception {

	private static final long	serialVersionUID	= 5924735424315979121L;

	public EnvironmentException() {
	}

	public EnvironmentException(String message) {
		super(message);
	}

	public EnvironmentException(Throwable cause) {
		super(cause);
	}

	public EnvironmentException(String message, Throwable cause) {
		super(message, cause);
	}

}
