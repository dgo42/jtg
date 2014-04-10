package org.edgo.jtg.ui.config;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.edgo.jtg.ui.JtgUIPlugin;
import org.osgi.service.prefs.Preferences;

public final class PreferenceLoader {

	private PreferenceLoader() {
	}

	public static String loadValue(Preferences store, String prefName) {
		String value = store.get(prefName, "");
		if (!"".equals(value)) {
			return value;
		}
		return "";
	}

	public static boolean loadValueBool(Preferences store, String prefName) {
		return store.getBoolean(prefName, false);
	}

	public static void storeValue(Preferences store, String prefName, String newValue) {
		try {
			if (!"".equals(newValue)) {
				store.put(prefName, newValue);
			} else {
				store.remove(prefName);
			}
			store.flush();
		} catch (Exception e) {
			JtgUIPlugin.log(e);
		}
	}

	public static void storeValue(Preferences store, String prefName, boolean newValue) {
		try {
			if (newValue) {
				store.putBoolean(prefName, newValue);
			} else {
				store.remove(prefName);
			}
			store.flush();
		} catch (Exception e) {
			JtgUIPlugin.log(e);
		}
	}

	public static String projectRelativePath(IProject project, IResource res) {
		String path = res.getFullPath().toString();
		String projPath = project.getFullPath().toString();
		if (path.startsWith(projPath)) {
			return path.substring(projPath.length() + 1);
		}
		return path;
	}
	
	public static void checkAndCreateContainer(IProject project, IContainer container) {
		String path = projectRelativePath(project, container);
		if (project.findMember(path) == null) {
			IFolder iFolder = project.getFolder(path);
			IContainer parent = iFolder.getParent();
			if (!parent.exists()) {
				checkAndCreateContainer(project, parent);
			}
			try {
				iFolder.create(true, false, null);
			} catch (CoreException e) {
				JtgUIPlugin.log(e);
			}
		}
	}

	public static void checkAndCreateFile(IProject project, String file) {
		if (project.findMember(file) == null) {
			IFile iFile = project.getFile(file);
			IContainer parent = iFile.getParent();
			if (!parent.exists()) {
				checkAndCreateContainer(project, parent);
			}
			try {
				ByteArrayInputStream is = new ByteArrayInputStream(new byte[] {});
				iFile.create(is, false, null);
			} catch (CoreException e) {
				JtgUIPlugin.log(e);
			}
		}
	}

	public static void checkAndCreateFolder(IProject project, String folder) {
		if (project.findMember(folder) == null) {
			IFolder iFolder = project.getFolder(folder);
			IContainer parent = iFolder.getParent();
			if (!parent.exists()) {
				checkAndCreateContainer(project, parent);
			}
			try {
				iFolder.create(true, false, null);
			} catch (CoreException e) {
				JtgUIPlugin.log(e);
			}
		}
	}


}
