package org.edgo.jtg.ui.builder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
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
import org.edgo.jtg.core.GeneratorCommand;
import org.edgo.jtg.core.GeneratorUtils;
import org.edgo.jtg.core.JarCompiler;
import org.edgo.jtg.core.ProjectType;
import org.edgo.jtg.core.config.JtgConfiguration;
import org.edgo.jtg.ui.Constants;
import org.edgo.jtg.ui.JtgUIPlugin;
import org.edgo.jtg.ui.config.PreferenceLoader;
import org.osgi.service.prefs.Preferences;

public class JtgBuilder extends IncrementalProjectBuilder {

	boolean alreadyBuilt = false;

	boolean hasErrors = false;

	class SampleDeltaVisitor implements IResourceDeltaVisitor {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.
		 * resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			//String fullName = resource.getFullPath().toString();
			//String message = "";
			//MessageConsole myConsole = findConsole(Constants.CONSOLE_NAME);
			//MessageConsoleStream out = myConsole.newMessageStream();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				// handle added resource
				//out.println("+++++++++++++++++> Resource \"" + fullName + "\" is added");
				if (!alreadyBuilt) {
					checkResource(resource);
				}
				break;
			case IResourceDelta.REMOVED:
				// handle removed resource
				// TODO remove deleted files?
				//message = "-----------------> Resource \"" + fullName + "\" is removed";
				//IStatus status = new Status(Status.INFO, JtgUIPlugin.getUniqueIdentifier(), 1000, message, null);
				//JtgUIPlugin.log(status);
				//out.println(message);
				break;
			case IResourceDelta.CHANGED:
				// handle changed resource
				//out.println("*****************> Resource \"" + fullName + "\" is changed");
				if (!alreadyBuilt) {
					checkResource(resource);
				}
				break;
			}
			// return true to continue visiting children.
			return true;
		}
	}

	class SampleResourceVisitor implements IResourceVisitor {
		public boolean visit(IResource resource) throws CoreException {
			checkResource(resource);
			// return true to continue visiting children.
			return true;
		}
	}

	public static final String BUILDER_ID = "org.edgo.jtg.ui.jtgBuilder";

	private static final String JTG_MARKER_TYPE = "org.edgo.jtg.ui.jtgProblem";

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
	 * java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException {
		IProject project = getProject();
		try {
			if (kind == FULL_BUILD) {
				fullBuild(monitor);
			} else {
				IResourceDelta delta = getDelta(project);
				if (delta == null) {
					fullBuild(monitor);
				} else {
					incrementalBuild(delta, monitor);
				}
			}
		} catch (CoreException e) {
			Throwable t = e.getStatus().getException();
			if (!(t instanceof BuildDoneException) && !(t instanceof BuildFailedException)) {
				throw e;
			}
		}

		return getJtgProjects();
	}

	private IProject[] getJtgProjects() throws CoreException {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] allProjects = workspaceRoot.getProjects();

		ArrayList<IResource> jtgProjects = new ArrayList<IResource>(allProjects.length);

		for (int i = 0; i < allProjects.length; i++) {
			IProject project = allProjects[i];
			if (project.isOpen()) {
				IProjectNature nature = project.getNature(JtgNature.NATURE_ID);
				if (nature != null) {
					jtgProjects.add(project);
				}
			}
		}
		return jtgProjects.toArray(new IProject[jtgProjects.size()]);
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

	@Override
	protected void clean(IProgressMonitor monitor) throws CoreException {
		// delete markers set and files created
		getProject().deleteMarkers(JTG_MARKER_TYPE, true, IResource.DEPTH_INFINITE);
	}

	JtgConfiguration getConfiguration(IProject project) throws CoreException {
		IScopeContext projectScope = new ProjectScope(project);
		Preferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);
		ProjectType projectType = ProjectType.parse(PreferenceLoader.loadValue(store, Constants.PROJECT_TYPE));

		JtgConfiguration config = new JtgConfiguration();
		if (projectType == ProjectType.LEADER) {
			String schemaPath = PreferenceLoader.loadValue(store, Constants.L_SCHEMA_DIR_PREF);
			config.setSchemaDir(schemaPath);

			String templPath = PreferenceLoader.loadValue(store, Constants.L_TEMPLATE_DIR_PREF);
			config.setTemplateDir(templPath);

			config.setSourceOutputDir(PreferenceLoader.loadValue(store, Constants.L_SOURCE_OUT_DIR_PREF));

			config.setJarOutputDir(PreferenceLoader.loadValue(store, Constants.L_JAR_OUTPUT_DIR_PREF));

			config.setSchema(PreferenceLoader.loadValue(store, Constants.L_SCHEMA_FILE_PREF));

			config.setStartTemplate(PreferenceLoader.loadValue(store, Constants.L_START_TEMPLATE_FILE_PREF));

			config.setSchemaPackage(PreferenceLoader.loadValue(store, Constants.L_SCHEMA_PACKAGE_PREF));

			config.setGeneratedPackage(PreferenceLoader.loadValue(store, Constants.L_TEMPLATE_PACKAGE_PREF));

			config.setTemplateArg(PreferenceLoader.loadValue(store, Constants.L_MAIN_ARG_NAME));

			config.setCommand(GeneratorCommand.JAR_ONLY.name());

			config.setUsingCache("true");
		} else if (projectType == ProjectType.FOLLOWER) {
			// inherit configuration from leader
			IProject leaderProject = getLeaderProject(project);
			if (leaderProject == null) {
				String leaderName = PreferenceLoader.loadValue(store, Constants.F_LEADER_PROJECT);
				IStatus status = new Status(Status.ERROR, JtgUIPlugin.getUniqueIdentifier(), 1000, "Can't open leader project \"" + leaderName + "\"", null);
				JtgUIPlugin.log(status);
				throw new CoreException(status);
			}

			IScopeContext leaderSrojectScope = new ProjectScope(leaderProject);
			Preferences leaderStore = leaderSrojectScope.getNode(JtgUIPlugin.PLUGIN_ID);

			String sourceOutputDir = PreferenceLoader.loadValue(leaderStore, Constants.L_SOURCE_OUT_DIR_PREF);
			IFile sourceOutputDirFile = leaderProject.getFile(sourceOutputDir);
			config.setSourceOutputDir(sourceOutputDirFile.getRawLocation().toOSString());

			String jarOutputDir = PreferenceLoader.loadValue(leaderStore, Constants.L_JAR_OUTPUT_DIR_PREF);
			IFile jarOutputDirFile = leaderProject.getFile(jarOutputDir);
			config.setJarOutputDir(jarOutputDirFile.getRawLocation().toOSString());

			config.setSchema(PreferenceLoader.loadValue(leaderStore, Constants.L_SCHEMA_FILE_PREF));

			config.setStartTemplate(PreferenceLoader.loadValue(leaderStore, Constants.L_START_TEMPLATE_FILE_PREF));

			config.setSchemaPackage(PreferenceLoader.loadValue(leaderStore, Constants.L_SCHEMA_PACKAGE_PREF));

			config.setGeneratedPackage(PreferenceLoader.loadValue(leaderStore, Constants.L_TEMPLATE_PACKAGE_PREF));

			config.setTemplateArg(PreferenceLoader.loadValue(leaderStore, Constants.L_MAIN_ARG_NAME));

			String templPath = PreferenceLoader.loadValue(leaderStore, Constants.L_TEMPLATE_DIR_PREF);
			config.setTemplateDir(templPath);

			String schemaPath = PreferenceLoader.loadValue(store, Constants.F_PROJECT_DIR_PREF);
			config.setSchemaDir(schemaPath);

			config.setProjectFile(schemaPath + "/" + PreferenceLoader.loadValue(store, Constants.F_PROJECT_FILE_PREF));

			config.setCommand(GeneratorCommand.GENERATE.name());

			config.setUsingCache("true");
		} else if (projectType == ProjectType.STANDALONE) {
			String schemaPath = PreferenceLoader.loadValue(store, Constants.ST_SCHEMA_DIR_PREF);
			config.setSchemaDir(schemaPath);

			String templPath = PreferenceLoader.loadValue(store, Constants.ST_TEMPLATE_DIR_PREF);
			config.setTemplateDir(templPath);

			config.setSourceOutputDir(PreferenceLoader.loadValue(store, Constants.ST_SOURCE_OUT_DIR_PREF));

			config.setJarOutputDir(PreferenceLoader.loadValue(store, Constants.ST_JAR_OUTPUT_DIR_PREF));

			config.setSchema(PreferenceLoader.loadValue(store, Constants.ST_SCHEMA_FILE_PREF));

			config.setProjectFile(schemaPath + "/" + PreferenceLoader.loadValue(store, Constants.ST_PROJECT_FILE_PREF));

			config.setStartTemplate(PreferenceLoader.loadValue(store, Constants.ST_START_TEMPLATE_FILE_PREF));

			config.setSchemaPackage(PreferenceLoader.loadValue(store, Constants.ST_SCHEMA_PACKAGE_PREF));

			config.setGeneratedPackage(PreferenceLoader.loadValue(store, Constants.ST_TEMPLATE_PACKAGE_PREF));

			config.setUsingCache(((Boolean) PreferenceLoader.loadBool(store, Constants.ST_USING_CACHE_PREF)).toString());

			config.setCommand(PreferenceLoader.loadValue(store, Constants.ST_GOAL_PREF));
		}
		return config;
	}

	private ProjectType getProjectType(IProject project) {
		IScopeContext projectScope = new ProjectScope(project);
		IEclipsePreferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);

		ProjectType projectType = ProjectType.parse(PreferenceLoader.loadValue(store, Constants.PROJECT_TYPE));
		return projectType;
	}

	private List<IProject> getAllFollowers(IProject currProject) {
		List<IProject> followers = new ArrayList<IProject>();
		ProjectType currProjectType = getProjectType(currProject);
		if (currProjectType != ProjectType.LEADER) {
			return followers;
		}
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] allProjects = workspaceRoot.getProjects();
		String currProjectName = currProject.getName();

		for (int i = 0; i < allProjects.length; i++) {
			IProject project = allProjects[i];
			if (project.isOpen() && !project.equals(currProject)) {
				IScopeContext projectScope = new ProjectScope(project);
				IEclipsePreferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);
				ProjectType projectType = ProjectType.parse(PreferenceLoader.loadValue(store, Constants.PROJECT_TYPE));
				String leaderName = PreferenceLoader.loadValue(store, Constants.F_LEADER_PROJECT);
				if (projectType == ProjectType.FOLLOWER && currProjectName.equals(leaderName)) {
					followers.add(project);
				}
			}
		}
		return followers;
	}

	private IProject getLeaderProject(IProject currProject) {
		ProjectType currProjectType = getProjectType(currProject);
		if (currProjectType != ProjectType.FOLLOWER) {
			return null;
		}

		IScopeContext projectScope = new ProjectScope(currProject);
		IEclipsePreferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);
		String leaderName = PreferenceLoader.loadValue(store, Constants.F_LEADER_PROJECT);

		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] allProjects = workspaceRoot.getProjects();

		for (int i = 0; i < allProjects.length; i++) {
			IProject project = allProjects[i];
			if (project.isOpen() && !project.equals(currProject)) {
				if (leaderName != null && leaderName.equals(project.getName()) && getProjectType(project) == ProjectType.LEADER) {
					return project;
				}
			}
		}
		return null;
	}

	void checkResource(IResource resource) throws CoreException {
		if (resource instanceof IFile) {
			String name = resource.getName();
			String fullName = resource.getFullPath().toString();
			IProject project = resource.getProject();
			File resourceFile = resource.getLocation().toFile();
			ProjectType projectType = getProjectType(project);
			JtgConfiguration config = getConfiguration(project);
			String projectDir = project.getName();
			String projectName = config.getProjectFile();
			// String schemaFile = config.getSchema();
			String projectFile = "/" + projectDir + "/" + config.getProjectFile();
			String schemaFile = "/" + projectDir + "/" + config.getSchemaDir() + "/" + config.getSchema();
			String jarOutDir = config.getJarOutputDir();

			Matcher matcher = Constants.ALL_FILE_MASK.matcher(name);
			if (projectName != null && projectType != ProjectType.FOLLOWER) {
				if (matcher.find() || fullName.equals(projectFile) || fullName.equals(schemaFile)) {
					String jarName = GeneratorUtils.Project2JarName(jarOutDir, projectName);
					IResource jarResource = findMember(project, jarName);
					File jarFile = null;
					if (jarResource != null) {
						jarFile = jarResource.getLocation().toFile();
					}
					if (jarResource == null || resourceFile.lastModified() > jarFile.lastModified()) {
						regenarateAll(project, projectType);
					}
				}
			} else if (projectType == ProjectType.LEADER) {
				if (matcher.find() || fullName.equals(schemaFile)) {
					String jarName = GeneratorUtils.Project2JarName(jarOutDir, "main");
					IResource jarResource = findMember(project, jarName);
					File jarFile = null;
					if (jarResource != null) {
						jarFile = jarResource.getLocation().toFile();
					}
					if (jarResource == null || resourceFile.lastModified() > jarFile.lastModified()) {
						regenarateAll(project, projectType);
					}
				}
			} else if (projectType == ProjectType.FOLLOWER) {
				if (matcher.find() || fullName.equals(projectFile)) {
					String jarName = GeneratorUtils.Project2JarName(jarOutDir, "main");
					IProject leaderProject = getLeaderProject(project);
					IResource jarResource = findMember(leaderProject, jarName);
					File jarFile = null;
					if (jarResource != null) {
						jarFile = jarResource.getLocation().toFile();
					}

					IScopeContext projectScope = new ProjectScope(project);
					Preferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);
					long lastBuild = PreferenceLoader.loadLong(store, Constants.F_LAST_BUILD);
					
					long lastModified = resourceFile.lastModified();
					if (jarFile != null) {
						lastModified = Math.max(lastModified, jarFile.lastModified());
					}
					if (lastBuild < 0 || lastModified > lastBuild) {
						regenarateAll(project, projectType);
						PreferenceLoader.storeValue(store, Constants.F_LAST_BUILD, lastModified);
					}
				}
			}
		}
	}

	private MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		// no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] { myConsole });
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

	private IResource refresh(IContainer container, IPath filePath) {
		try {
			IResource resource = container.findMember(filePath, true);
			if (resource == null) {
				IResource[] resources = container.members(IContainer.INCLUDE_HIDDEN);
				for (IResource res : resources) {
					if (res instanceof IContainer && res.getName().equals(filePath.segment(0))) {
						res.refreshLocal(IResource.DEPTH_ONE, null);
						resource = container.findMember(filePath, true);
						if (resource != null) {
							return resource;
						}
						IPath childPath = filePath.removeFirstSegments(1);
						resource = refresh((IContainer) res, childPath);
						if (resource != null) {
							return resource;
						}
					}
				}
			}
		} catch (CoreException e) {
			JtgUIPlugin.log(e);
		}
		return null;
	}

	private IFile findMember(IContainer container, String fileName) {
		try {
			IResource resource = container.findMember(fileName, true);
			if (resource != null && resource instanceof IFile) {
				resource.refreshLocal(IResource.DEPTH_ZERO, null);
				return (IFile) resource;
			}
			IPath filePath = Path.fromOSString(fileName);
			if (resource == null) {
				resource = refresh(container, filePath);
				if (resource != null && resource instanceof IFile) {
					resource.refreshLocal(IResource.DEPTH_ZERO, null);
					return (IFile) resource;
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
			IPath filePath = Path.fromOSString(folderName);
			if (resource == null) {
				resource = refresh(container, filePath);
				if (resource != null && resource instanceof IFolder) {
					resource.refreshLocal(IResource.DEPTH_ZERO, null);
					return (IFolder) resource;
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
		ClassLoader parentClassLoader = JarCompiler.class.getClassLoader();
		URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
		URLClassLoader classLoader = new URLClassLoader(urls, parentClassLoader);
		return classLoader;
	}

	private void setTemplateArg(IProject project, String templateArg) {
		IScopeContext projectScope = new ProjectScope(project);
		IEclipsePreferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);
		PreferenceLoader.storeValue(store, Constants.L_MAIN_ARG_NAME, templateArg);
	}

	private void regenarateAll(IProject project, ProjectType projectType) throws CoreException {
		try {
			deleteAllMarkers(project);
		} catch (CoreException e) {
			JtgUIPlugin.log(e);
		}

		JtgConfiguration config = getConfiguration(project);
		EclipseEnvironment env = new EclipseEnvironment(project);
		Generator generator = new Generator(config, env);
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
			double start = System.nanoTime();
			String templateArg = "";
			try {
				templateArg = generator.generate(classLoader);
			} finally {
				double end = System.nanoTime();
				double elapsed = (end - start) / 1000000;
				if (elapsed > 1D) {
					String message = "";
					switch(projectType) {
					case STANDALONE:
						message = "==> Generation of the standalone project \"" + project.getName() + "\" took " + new DecimalFormat("#.###").format(elapsed) + "ms";
						break;
					case LEADER:
						message = "==> Generation of the leader project \"" + project.getName() + "\" took " + new DecimalFormat("#.###").format(elapsed) + "ms";
						break;
					case FOLLOWER:
						message = "==> Generation of the follower project \"" + project.getName() + "\" took " + new DecimalFormat("#.###").format(elapsed) + "ms";
						break;
					}
					out.println(message);
				}
			}
			alreadyBuilt = true;
			setTemplateArg(project, templateArg);
			if (projectType == ProjectType.LEADER) {
				List<IProject> followers = getAllFollowers(project);
				for (IProject follower : followers) {
					regenarateAll(follower, getProjectType(follower));
				}
			}
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
						//IFile file = findMember(project, getFileName(fileName));
						IFile file = findMember(project, fileName);
						if (file != null) {
							addMarker(file, message.getErrorMessage(), lineNumber, IMarker.SEVERITY_ERROR);
						}
					} else {
						addMarker(project, exceptionBuilder(e), lineNumber, IMarker.SEVERITY_ERROR);
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
					IFile file = null;
					if (projectType == ProjectType.FOLLOWER) {
						IProject leaderProject = getLeaderProject(project);
						file = findMember(leaderProject, getFileName(fileName));
					} else {
						file = findMember(project, getFileName(fileName));
					}
					if (file != null) {
						if (message == null) {
							message = e.toString();
						}
						if (projectType == ProjectType.FOLLOWER) {
							message += ".\r\n Error occurs in project-follower \"" + project.getName() + "\"";
						}
						addMarker(file, message, lineNumber, IMarker.SEVERITY_ERROR);
					} else {
						addMarker(project, exceptionBuilder(e), lineNumber, IMarker.SEVERITY_ERROR);
					}
				} else {
					addMarker(project, exceptionBuilder(e), lineNumber, IMarker.SEVERITY_ERROR);
				}
			}
			JtgUIPlugin.log(e);
			hasErrors = true;
		} catch (CoreException e) {
			if (!(e.getStatus().getException() instanceof BuildDoneException)) {
				throw e;
			}
			// JtgUIPlugin.log(e);
		} catch (Exception e) {
			hasErrors = true;
			JtgUIPlugin.log(e);
		} finally {
			env.bulkSync();
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

}
