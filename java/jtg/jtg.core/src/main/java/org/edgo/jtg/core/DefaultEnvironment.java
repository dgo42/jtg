package org.edgo.jtg.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.edgo.jtg.basics.EnvironmentException;
import org.edgo.jtg.basics.GeneratorMode;
import org.edgo.jtg.basics.IEnvironment;

public class DefaultEnvironment implements IEnvironment {

	private String	configPath;
	private String	generatorOutputDir;

	public DefaultEnvironment(String configPath, String generatorOutputDir) {
		this.configPath = configPath;
		this.generatorOutputDir = generatorOutputDir;
	}

	public PrintWriter getOutput(String fileName, String encoding, GeneratorMode mode) throws EnvironmentException {
		File file = new File(configPath, fileName).getAbsoluteFile();
		if (null != generatorOutputDir && "" != generatorOutputDir) {
			file = new File(generatorOutputDir, fileName);
		}
		if (file.exists()) {
			if (mode == GeneratorMode.ONLY_NEW) {
				throw new EnvironmentException("File already exists");
			} else if (mode == GeneratorMode.MAKE_COPY) {
				String path = file.getParentFile().getPath();
				String filenameStr = file.getName();
				int dot = filenameStr.lastIndexOf('.');
				String name = filenameStr;
				String ext = "";
				if (dot > 0) {
					name = filenameStr.substring(0, dot);
					ext = filenameStr.substring(dot);
				}
				file = new File(path + File.separator + name
						+ (new SimpleDateFormat(".yyyy_MM_dd_HHmmss")).format(new Date()) + ext);
			}
		} else {
			file.getParentFile().mkdirs();
		}
		try {
			return new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new EnvironmentException("File not found");
		}
	}

	public void sync() {
		// nothing to do, it's only for eclipse
	}

	public void log(String message) {
		// nothing to do, it's only for eclipse
	}

	public void log(String message, Throwable cause) {
		// nothing to do, it's only for eclipse
	}
}
