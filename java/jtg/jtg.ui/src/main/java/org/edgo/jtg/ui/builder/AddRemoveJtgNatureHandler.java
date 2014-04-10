package org.edgo.jtg.ui.builder;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.edgo.jtg.ui.Constants;
import org.edgo.jtg.ui.JtgUIPlugin;
import org.edgo.jtg.ui.config.PreferenceLoader;
import org.edgo.jtg.ui.dialogs.StatusInfo;

public class AddRemoveJtgNatureHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		//
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
				Object element = it.next();
				IProject project = null;
				if (element instanceof IProject) {
					project = (IProject) element;
				} else if (element instanceof IAdaptable) {
					project = (IProject) ((IAdaptable) element).getAdapter(IProject.class);
				}
				if (project != null) {
					try {
						toggleNature(project);
					} catch (CoreException e) {
						JtgUIPlugin.log(e);
						throw new ExecutionException("Failed to toggle nature", e);
					}
				}
			}
		}

		return null;
	}

	/**
	 * Toggles sample nature on a project
	 *
	 * @param project to have sample nature added or removed
	 */
	private void toggleNature(IProject project) throws CoreException {
		IProjectDescription description = project.getDescription();
		String[] natures = description.getNatureIds();

		for (int i = 0; i < natures.length; ++i) {
			if (JtgNature.NATURE_ID.equals(natures[i])) {
				// Remove the nature
				String[] newNatures = new String[natures.length - 1];
				System.arraycopy(natures, 0, newNatures, 0, i);
				System.arraycopy(natures, i + 1, newNatures, i, natures.length - i - 1);
				description.setNatureIds(newNatures);
				project.setDescription(description, null);
				return;
			}
		}

		// Add the nature
		String[] newNatures = new String[natures.length + 1];
		System.arraycopy(natures, 0, newNatures, 0, natures.length);
		newNatures[natures.length] = JtgNature.NATURE_ID;
		description.setNatureIds(newNatures);
		project.setDescription(description, null);

		try {
			IScopeContext projectScope = new ProjectScope(project);
			IEclipsePreferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);

			PreferenceLoader.storeValue(store, Constants.SCHEMA_DIR_PREF, Constants.SCHEMA_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.SCHEMA_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.TEMPLATE_DIR_PREF, Constants.TEMPLATE_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.TEMPLATE_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.SOURCE_OUT_DIR_PREF, Constants.SOURCE_OUT_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.SOURCE_OUT_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.JAR_OUTPUT_DIR_PREF, Constants.JAR_OUTPUT_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.JAR_OUTPUT_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.SCHEMA_FILE_PREF, Constants.SCHEMA_FILE_DEFAULT);
			PreferenceLoader.checkAndCreateFile(project, Constants.SCHEMA_DIR_DEFAULT + "/" + Constants.SCHEMA_FILE_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.PROJECT_FILE_PREF, Constants.PROJECT_FILE_DEFAULT);
			PreferenceLoader
					.checkAndCreateFile(project, Constants.SCHEMA_DIR_DEFAULT + "/" + Constants.PROJECT_FILE_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.START_TEMPLATE_FILE_PREF, Constants.START_TEMPLATE_FILE_DEFAULT);
			PreferenceLoader.checkAndCreateFile(project, Constants.TEMPLATE_DIR_DEFAULT + "/"
					+ Constants.START_TEMPLATE_FILE_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.SCHEMA_PACKAGE_PREF, Constants.SCHEMA_PACKAGE_DEFAULT);
			PreferenceLoader.storeValue(store, Constants.TEMPLATE_PACKAGE_PREF, Constants.TEMPLATE_PACKAGE_DEFAULT);
			PreferenceLoader.storeValue(store, Constants.USING_CACHE_PREF, Constants.USING_CACHE_DEFAULT);
		} catch (Exception e) {
			JtgUIPlugin.log(e);
			throw new CoreException(new StatusInfo(4, "Failed to toggle nature: \r\n" + e.toString()));
		}
	}

}
