package org.edgo.jtg.basics;

import java.io.PrintWriter;

public interface IEnvironment {
	PrintWriter getOutput(String fileName, String encoding, GeneratorMode mode) throws EnvironmentException;
	void sync();
	void log(String message);
	void log(String message, Throwable cause);
}
