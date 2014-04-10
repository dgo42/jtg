package org.edgo.jtg.core;

import java.util.HashMap;
import java.util.Map;

import org.edgo.jtg.basics.LogMessage;

public class SourceLineProcessor {

	private Map<String, TemplateLines>	sourceLines	= new HashMap<String, TemplateLines>();

	public void addSourceLines(String javaSource, String templateFile, int lastline, int sourceLine, int javaLine) {
		TemplateLines lines;
		if (!sourceLines.containsKey(javaSource)) {
			lines = new TemplateLines();
			sourceLines.put(javaSource, lines);
		} else {
			lines = sourceLines.get(javaSource);
		}
		lines.lastline = lastline;
		lines.templateFile = templateFile;
		lines.lines.put(javaLine, sourceLine);
	}

	public void findSourceLine(LogMessage error) {
		if (error.getTemplateFileName() == null && error.getTemplateBeginLineNumber() == -1) {
			String javaName = error.getJavaFileName();
			int javaLine = error.getJavaLineNumber();
			if (javaName != null && javaLine >= 0) {
				if (sourceLines.containsKey(javaName)) {
					TemplateLines lines = sourceLines.get(javaName);
					if (lines.lines.containsKey(javaLine)) {
						error.setTemplateFileName(lines.templateFile);
						error.setTemplateBeginLineNum(lines.lines.get(javaLine));
					} else {
						error.setTemplateFileName(lines.templateFile);
						error.setTemplateBeginLineNum(lines.lastline);
					}
				}
			}
		}
	}
}
