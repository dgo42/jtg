package org.edgo.jtg.ui.builder;

public class BuildDoneException extends Exception {

	private static final long serialVersionUID = 8974714428814802316L;

	public BuildDoneException() {
	}

	public BuildDoneException(String message) {
		super(message);
	}

	public BuildDoneException(Throwable cause) {
		super(cause);
	}

	public BuildDoneException(String message, Throwable cause) {
		super(message, cause);
	}

	public BuildDoneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
