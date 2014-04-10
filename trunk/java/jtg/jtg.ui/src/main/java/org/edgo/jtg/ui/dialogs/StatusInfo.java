package org.edgo.jtg.ui.dialogs;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;

public class StatusInfo implements IStatus {
	public static final IStatus	OK_STATUS	= new StatusInfo();
	private String				fStatusMessage;
	private int					fSeverity;

	public StatusInfo() {
		this(0, null);
	}

	public StatusInfo(int severity, String message) {
		this.fStatusMessage = message;
		this.fSeverity = severity;
	}

	public boolean isOK() {
		return this.fSeverity == 0;
	}

	public boolean isWarning() {
		return this.fSeverity == 2;
	}

	public boolean isInfo() {
		return this.fSeverity == 1;
	}

	public boolean isError() {
		return this.fSeverity == 4;
	}

	public String getMessage() {
		return this.fStatusMessage;
	}

	public void setError(String errorMessage) {
		Assert.isNotNull(errorMessage);
		this.fStatusMessage = errorMessage;
		this.fSeverity = 4;
	}

	public void setWarning(String warningMessage) {
		Assert.isNotNull(warningMessage);
		this.fStatusMessage = warningMessage;
		this.fSeverity = 2;
	}

	public void setInfo(String infoMessage) {
		Assert.isNotNull(infoMessage);
		this.fStatusMessage = infoMessage;
		this.fSeverity = 1;
	}

	public void setOK() {
		this.fStatusMessage = null;
		this.fSeverity = 0;
	}

	public boolean matches(int severityMask) {
		return (this.fSeverity & severityMask) != 0;
	}

	public boolean isMultiStatus() {
		return false;
	}

	public int getSeverity() {
		return this.fSeverity;
	}

	public String getPlugin() {
		return "org.eclipse.jdt.ui";
	}

	public Throwable getException() {
		return null;
	}

	public int getCode() {
		return this.fSeverity;
	}

	public IStatus[] getChildren() {
		return new IStatus[0];
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("StatusInfo ");
		if (this.fSeverity == 0) {
			buf.append("OK");
		} else if (this.fSeverity == 4) {
			buf.append("ERROR");
		} else if (this.fSeverity == 2) {
			buf.append("WARNING");
		} else if (this.fSeverity == 1) {
			buf.append("INFO");
		} else {
			buf.append("severity=");
			buf.append(this.fSeverity);
		}
		buf.append(": ");
		buf.append(this.fStatusMessage);
		return buf.toString();
	}
}
