package org.edgo.jtg.ui.builder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.edgo.jtg.basics.EnvironmentException;
import org.edgo.jtg.basics.GeneratorMode;
import org.edgo.jtg.basics.IEnvironment;
import org.edgo.jtg.ui.JtgUIPlugin;
import org.edgo.jtg.ui.config.PreferenceLoader;

public class EclipseEnvironment implements IEnvironment {

	private IProject project;

	private IFile lastFile = null;

	public EclipseEnvironment(IProject project) {
		this.project = project;
	}

	public PrintWriter getOutput(String fileName, String encoding, GeneratorMode mode) throws EnvironmentException {
		try {
			IFile file = project.getFile(fileName);
			if (file.exists()) {
				if (mode == GeneratorMode.ONLY_NEW) {
					return new PrintWriter(new NullOutputStream());
				} else if (mode == GeneratorMode.MAKE_COPY) {
					IContainer path = file.getParent();
					String filenameStr = file.getName();
					String ext = file.getFileExtension();
					int dot = filenameStr.lastIndexOf('.');
					String name = filenameStr;
					if (dot > 0) {
						name = filenameStr.substring(0, dot);
					}

					IPath bakfile = path.getFullPath()
							.append(name + (new SimpleDateFormat(".yyyy_MM_dd_HHmmss.")).format(new Date()) + ext);

					file.move(bakfile, true, null);
				}
			}
			PreferenceLoader.checkAndCreateFile(project, fileName, null);
			file = project.getFile(fileName);
			file.setCharset(encoding, null);
			lastFile = file;
			return new PrintWriter(new OutputStreamWriter(new FileOutputStream(file.getLocation().toOSString()),
					StandardCharsets.UTF_8));
		} catch (CoreException e) {
			throw new EnvironmentException("File not found");
		} catch (FileNotFoundException e) {
			throw new EnvironmentException("File not found");
		}
	}

	public void sync() {
		if (lastFile != null) {
			try {
				lastFile.refreshLocal(IResource.DEPTH_ZERO, null);
			} catch (CoreException e) {
				JtgUIPlugin.log(e);
			}
		}
	}

	public void log(String message) {
		JtgUIPlugin.logErrorMessage(message);
	}

	public void log(String message, Throwable cause) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		cause.printStackTrace(pw);
		JtgUIPlugin.logErrorMessage(message + "\r\n" + sw.toString());
	}

	public class NullOutputStream extends OutputStream {
		public NullOutputStream() {
		}

		public void write(int i) throws IOException {
		}
	}
}
