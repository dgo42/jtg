package org.edgo.jtg.core.model;

import org.edgo.jtg.basics.TemplateException;

public abstract class Node {

	private final String	fileName;
	private final int		sourceLineBegin;
	private int				targetLineBegin;
	private int				targetLineEnd;

	protected Node(String sourceFile, int sourceLineBegin) {
		this.fileName = sourceFile;
		this.sourceLineBegin = sourceLineBegin;
		this.targetLineBegin = 0;
		this.targetLineEnd = 0;
	}

	public abstract void accept(Visitor v) throws TemplateException;

	/**
	 * @return the targetLineBegin
	 */
	public int getTargetLineBegin() {
		return targetLineBegin;
	}

	/**
	 * @param targetLineBegin the targetLineBegin to set
	 */
	public void setTargetLineBegin(int targetLineBegin) {
		this.targetLineBegin = targetLineBegin;
	}

	/**
	 * @return the targetLineEnd
	 */
	public int getTargetLineEnd() {
		return targetLineEnd;
	}

	/**
	 * @param targetLineEnd the targetLineEnd to set
	 */
	public void setTargetLineEnd(int targetLineEnd) {
		this.targetLineEnd = targetLineEnd;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the sourceLineBegin
	 */
	public int getSourceLineBegin() {
		return sourceLineBegin;
	}

}
