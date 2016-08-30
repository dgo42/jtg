package org.edgo.jtg.ui.builder;

public class BuildFailedException extends Exception {

	private static final long serialVersionUID = 8974714428814802316L;

	public BuildFailedException() {
	}

	public BuildFailedException(String message) {
		super(message);
	}

	public BuildFailedException(Throwable cause) {
		super(cause);
	}

	public BuildFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BuildFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
