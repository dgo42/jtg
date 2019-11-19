package org.edgo.jtg.ui.builder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.edgo.jtg.basics.LogMessage;
import org.edgo.jtg.basics.TemplateException;
import org.edgo.jtg.core.Generator;
import org.edgo.jtg.core.GeneratorUtils;
import org.edgo.jtg.core.JarCompiler;
import org.edgo.jtg.core.config.JtgConfiguration;
import org.edgo.jtg.ui.Constants;
import org.edgo.jtg.ui.JtgUIPlugin;
import org.edgo.jtg.ui.config.PreferenceLoader;
import org.osgi.service.prefs.Preferences;

public class JtgBuilder extends IncrementalProjectBuilder {

	boolean	alreadyBuilt	= false;

	boolean	hasErrors		= false;

	class SampleDeltaVisitor implements IResourceDeltaVisitor {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				// handle added resource
				if (!alreadyBuilt) {
					checkResource(resource);
				}
				break;
			case IResourceDelta.REMOVED:
				// handle removed resource
				// TODO remove deleted files?
				break;
			case IResourceDelta.CHANGED:
				// handle changed resource
				if (!alreadyBuilt) {
					checkResource(resource);
				}
				break;
			}
			//return true to continue visiting children.
			return true;
		}
	}

	class SampleResourceVisitor implements IResourceVisitor {
		public boolean visit(IResource resource) throws CoreException {
			checkResource(resource);
			//return true to continue visiting children.
			return true;
		}
	}

	public static final String	BUILDER_ID		= "org.edgo.jtg.ui.jtgBuilder";

	private static final String	JTG_MARKER_TYPE	= "org.edgo.jtg.ui.jtgProblem";

	private void addMarker(IResource file, String message, int lineNumber, int severity) {
		try {
			IMarker marker = file.createMarker(JTG_MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
			if (lineNumber == -1) {
				lineNumber = 1;
			}
			marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
		} catch (CoreException e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.internal.events.InternalBuilder#build(int,
	 *      java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@SuppressWarnings("rawtypes")
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException {
		try {
			if (kind == FULL_BUILD) {
				fullBuild(monitor);
			} else {
				IResourceDelta delta = getDelta(getProject());
				if (delta == null) {
					fullBuild(monitor);
				} else {
					incrementalBuild(delta, monitor);
				}
			}
		} catch(CoreException e) {
			Throwable t = e.getStatus().getException();
			if (!(t instanceof BuildDoneException) && !(t instanceof BuildFailedException)) {
				throw e;
			}
		}
		return null;
	}

	protected void clean(IProgressMonitor monitor) throws CoreException {
		// delete markers set and files created
		getProject().deleteMarkers(JTG_MARKER_TYPE, true, IResource.DEPTH_INFINITE);
	}

	JtgConfiguration getConfiguration(IProject project) {
		IScopeContext projectScope = new ProjectScope(project);
		Preferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);

		JtgConfiguration config = new JtgConfiguration();
		String schemaPath = PreferenceLoader.loadValue(store, Constants.SCHEMA_DIR_PREF);
		config.setSchemaDir(schemaPath);

		String templPath = PreferenceLoader.loadValue(store, Constants.TEMPLATE_DIR_PREF);
		config.setTemplateDir(templPath);

		config.setSourceOutputDir(PreferenceLoader.loadValue(store, Constants.SOURCE_OUT_DIR_PREF));

		config.setJarOutputDir(PreferenceLoader.loadValue(store, Constants.JAR_OUTPUT_DIR_PREF));

		config.setSchema(PreferenceLoader.loadValue(store, Constants.SCHEMA_FILE_PREF));

		config.setProjectFile(schemaPath + "/" + PreferenceLoader.loadValue(store, Constants.PROJECT_FILE_PREF));

		config.setStartTemplate(PreferenceLoader.loadValue(store, Constants.START_TEMPLATE_FILE_PREF));

		config.setSchemaPackage(PreferenceLoader.loadValue(store, Constants.SCHEMA_PACKAGE_PREF));

		config.setGeneratedPackage(PreferenceLoader.loadValue(store, Constants.TEMPLATE_PACKAGE_PREF));

		config.setUsingCache(((Boolean) PreferenceLoader.loadValueBool(store, Constants.USING_CACHE_PREF)).toString());
		
		config.setCommand(PreferenceLoader.loadValue(store, Constants.GOAL_PREF));
		
		return config;
	}

	void checkResource(IResource resource) throws CoreException {
		if (resource instanceof IFile) {
			String name = resource.getName();
			String fullName = resource.getFullPath().toString();
			IProject project = resource.getProject();
			JtgConfiguration config = getConfiguration(project);
			String projectName = config.getProjectFile();
			String projectFile = "/" + projectName + "/" + config.getProjectFile();
			String schemaFile = "/" + projectName + "/" + config.getSchemaDir() + "/" + config.getSchema();
			String jarOutDir = config.getJarOutputDir();

			Matcher matcher = Constants.ALL_FILE_MASK.matcher(name);
			String jarName = GeneratorUtils.Project2JarName(jarOutDir, projectName);
			if (matcher.find() || fullName.equals(projectFile) || fullName.equals(schemaFile)) {
				IResource jarResource = findMember(project, jarName);
				if (jarResource == null || resource.getLocalTimeStamp() > jarResource.getLocalTimeStamp()) {
					regenarateAll(project);
				}
			}
		}
	}

	private MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName())) return (MessageConsole) existing[i];
		//no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] {myConsole});
		return myConsole;
	}

	private String exceptionBuilder(Throwable e) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		e.printStackTrace(ps);
		return baos.toString();
	}

	private String getFileName(String fileName) {
		int pos = fileName.lastIndexOf("/");
		if (pos >= 0) {
			return fileName.substring(pos + 1);
		}
		pos = fileName.lastIndexOf("\\");
		if (pos >= 0) {
			return fileName.substring(pos + 1);
		}
		return fileName;
	}

	private IFile findMember(IContainer container, String fileName) {
		try {
			IResource resource = container.findMember(fileName, true);
			if (resource != null && resource instanceof IFile) {
				return (IFile) resource;
			}
			IResource[] resources = container.members(IContainer.INCLUDE_HIDDEN);
			for (IResource res : resources) {
				if (res instanceof IContainer) {
					IFile file = findMember((IContainer) res, fileName);
					if (file != null) {
						return file;
					}
				}
			}
		} catch (CoreException e) {
			JtgUIPlugin.log(e);
		}
		return null;
	}

	private IFolder findFolder(IContainer container, String folderName) {
		try {
			IResource resource = container.findMember(folderName);
			if (resource != null && resource instanceof IFolder) {
				return (IFolder) resource;
			}
			IResource[] resources = container.members();
			for (IResource res : resources) {
				if (res instanceof IContainer) {
					IFolder file = findFolder((IContainer) res, folderName);
					if (file != null) {
						return file;
					}
				}
			}
		} catch (CoreException e) {
			JtgUIPlugin.log(e);
		}
		return null;
	}

	private ClassLoader getProjectClassLoader(IProject project) throws CoreException {
		IJavaProject javaProject = JavaCore.create(project);
		String[] classPathEntries = JavaRuntime.computeDefaultRuntimeClassPath(javaProject);
		List<URL> urlList = new ArrayList<URL>();
		for (int i = 0; i < classPathEntries.length; i++) {
			String entry = classPathEntries[i];
			IPath path = new Path(entry);
			URL url = null;
			try {
				url = path.toFile().toURI().toURL();
			} catch (MalformedURLException e) {
				// should not happened!!!
			}
			urlList.add(url);
		}
//		ClassLoader parentClassLoader = project.getClass().getClassLoader();
		ClassLoader parentClassLoader = JarCompiler.class.getClassLoader();
		URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
		URLClassLoader classLoader = new URLClassLoader(urls, parentClassLoader);
		return classLoader;
	}

	private void regenarateAll(IProject project) throws CoreException {
		try {
			deleteAllMarkers(project);
		} catch (CoreException e) {
			JtgUIPlugin.log(e);
		}

		JtgConfiguration config = getConfiguration(project);
		Generator generator = new Generator(config, new EclipseEnvironment(project));
		MessageConsole myConsole = findConsole(Constants.CONSOLE_NAME);
		MessageConsoleStream out = myConsole.newMessageStream();
		generator.setWriter(new PrintStream(out));
		String workspacePath = project.getLocation().toOSString();
		generator.setConfigPath(workspacePath);
		String projectLocation = project.getLocation().toOSString();
		String projectLocationSchema = project.getLocation().toPortableString();
		ClassLoader classLoader = null;
		try {
			classLoader = getProjectClassLoader(project);
		} catch (CoreException e) {
			JtgUIPlugin.log(e);
		}

		try {
			hasErrors = false;
			generator.generate(classLoader);
			alreadyBuilt = true;
			Status status = new Status(IStatus.OK, "jtg", "", new BuildDoneException());
			throw new CoreException(status);
		} catch (TemplateException e) {
			List<LogMessage> messages = e.getErrors();
			if (messages != null && messages.size() > 0) {
				for (LogMessage message : messages) {
					int lineNumber = message.getTemplateBeginLineNumber();
					if (lineNumber < 0) {
						lineNumber = message.getJavaLineNumber();
					}
					String fileName = message.getTemplateFileName();
					if (fileName == null) {
						fileName = message.getFile();
					}
					if (fileName != null) {
						if (fileName.startsWith(projectLocation)) {
							fileName = fileName.substring(projectLocation.length() + 1);
						}
						if (fileName.startsWith(projectLocationSchema)) {
							fileName = fileName.substring(projectLocationSchema.length() + 1);
						}
						if (fileName.startsWith("file:/" + projectLocationSchema)) {
							fileName = fileName.substring(("file:/" + projectLocationSchema).length() + 1);
						}
						IFile file = findMember(project, getFileName(fileName));
						if (file != null) {
							addMarker(file, message.getErrorMessage(), lineNumber, IMarker.SEVERITY_ERROR);
							//JtgUIPlugin.log(e);
						}
					} else {
						addMarker(project, exceptionBuilder(e), lineNumber, IMarker.SEVERITY_ERROR);
						//JtgUIPlugin.log(e);
					}
				}
			} else {
				int lineNumber = e.getLineNumber();
				String fileName = e.getFileName();
				if (fileName != null) {
					if (fileName.startsWith(projectLocation)) {
						fileName = fileName.substring(projectLocation.length() + 1);
					}
					String message = e.getTemplateMessage();
					String schema = config.getSchema();
					if (schema.equals(fileName)) {
						fileName = config.getSchemaDir() + "/" + schema;
						message = e.getCause().getMessage();
						if (message == null) {
							message = e.getCause().toString();
						}
					}
					IFile file = findMember(project, getFileName(fileName));
					if (file != null) {
						if (message == null) {
							message = e.toString();
						}
						addMarker(file, message, lineNumber, IMarker.SEVERITY_ERROR);
						//JtgUIPlugin.log(e);
					} else {
						addMarker(project, exceptionBuilder(e), lineNumber, IMarker.SEVERITY_ERROR);
						//JtgUIPlugin.log(e);
					}
				} else {
					addMarker(project, exceptionBuilder(e), lineNumber, IMarker.SEVERITY_ERROR);
					//JtgUIPlugin.log(e);
				}
			}
			hasErrors = true;
		} catch (CoreException e) {
			if (!(e.getStatus().getException() instanceof BuildDoneException)) {
				throw e;
			}
			JtgUIPlugin.log(e);
		} catch (Exception e) {
			hasErrors = true;
			JtgUIPlugin.log(e);
		}
		String srcGenPath = config.getSourceOutputDir();
		IResource res = findFolder(project, srcGenPath);
		if (res != null && res.isAccessible()) {
			try {
				res.refreshLocal(IResource.DEPTH_INFINITE, null);
			} catch (CoreException e) {
				// nothing to do
			}
		}
		if (hasErrors) {
			Status status = new Status(IStatus.ERROR, "jtg", "", new BuildFailedException());
			throw new CoreException(status);
		}
	}

	private void deleteAllMarkers(IContainer project) throws CoreException {
		IResource[] members = project.members();
		deleteMarkers(project);
		for (IResource element : members) {
			if (IFolder.class.isInstance(element)) {
				deleteAllMarkers((IFolder) element);
			} else if (IFile.class.isInstance(element)) {
				deleteMarkers((IFile) element);
			}
		}
	}

	private void deleteMarkers(IResource file) {
		try {
			file.deleteMarkers(JTG_MARKER_TYPE, false, IResource.DEPTH_ZERO);
		} catch (CoreException ce) {
		}
	}

	protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
		alreadyBuilt = false;
		getProject().accept(new SampleResourceVisitor());
	}

	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		alreadyBuilt = false;
		delta.accept(new SampleDeltaVisitor());
	}
}
