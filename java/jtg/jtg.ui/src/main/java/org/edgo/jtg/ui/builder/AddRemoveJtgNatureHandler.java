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
import org.edgo.jtg.core.ProjectType;
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

			PreferenceLoader.storeValue(store, Constants.PROJECT_TYPE, ProjectType.STANDALONE.name());

			// store default values for leader project
			PreferenceLoader.storeValue(store, Constants.L_SCHEMA_DIR_PREF, Constants.L_SCHEMA_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.L_SCHEMA_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.L_TEMPLATE_DIR_PREF, Constants.L_TEMPLATE_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.L_TEMPLATE_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.L_SOURCE_OUT_DIR_PREF, Constants.ST_SOURCE_OUT_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.ST_SOURCE_OUT_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.L_JAR_OUTPUT_DIR_PREF, Constants.ST_JAR_OUTPUT_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.ST_JAR_OUTPUT_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.L_SCHEMA_FILE_PREF, Constants.L_SCHEMA_FILE_DEFAULT);
			PreferenceLoader.checkAndCreateFile(project, Constants.L_SCHEMA_DIR_DEFAULT + "/" + Constants.L_SCHEMA_FILE_DEFAULT, Constants.DEFAULT_SHEMA);

			PreferenceLoader.storeValue(store, Constants.L_START_TEMPLATE_FILE_PREF, Constants.L_START_TEMPLATE_FILE_DEFAULT);
			PreferenceLoader.checkAndCreateFile(project, Constants.L_TEMPLATE_DIR_DEFAULT + "/" + Constants.L_START_TEMPLATE_FILE_DEFAULT,
					Constants.DEFAULT_MAIN_TEMPLATE);

			PreferenceLoader.storeValue(store, Constants.L_SCHEMA_PACKAGE_PREF, Constants.L_SCHEMA_PACKAGE_DEFAULT);
			PreferenceLoader.storeValue(store, Constants.L_TEMPLATE_PACKAGE_PREF, Constants.L_TEMPLATE_PACKAGE_DEFAULT);

			// store default values for follower project
			PreferenceLoader.storeValue(store, Constants.F_PROJECT_DIR_PREF, Constants.F_SCHEMA_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.F_SCHEMA_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.F_PROJECT_FILE_PREF, Constants.F_PROJECT_FILE_DEFAULT);
			PreferenceLoader.checkAndCreateFile(project, Constants.F_SCHEMA_DIR_DEFAULT + "/" + Constants.F_PROJECT_FILE_DEFAULT, Constants.DEFAULT_PROJECT);

			// store default values for standalone project
			PreferenceLoader.storeValue(store, Constants.ST_SCHEMA_DIR_PREF, Constants.ST_SCHEMA_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.ST_SCHEMA_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.ST_TEMPLATE_DIR_PREF, Constants.ST_TEMPLATE_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.ST_TEMPLATE_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.ST_SOURCE_OUT_DIR_PREF, Constants.ST_SOURCE_OUT_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.ST_SOURCE_OUT_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.ST_JAR_OUTPUT_DIR_PREF, Constants.ST_JAR_OUTPUT_DIR_DEFAULT);
			PreferenceLoader.checkAndCreateFolder(project, Constants.ST_JAR_OUTPUT_DIR_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.ST_SCHEMA_FILE_PREF, Constants.ST_SCHEMA_FILE_DEFAULT);
			PreferenceLoader.checkAndCreateFile(project, Constants.ST_SCHEMA_DIR_DEFAULT + "/" + Constants.ST_SCHEMA_FILE_DEFAULT, Constants.DEFAULT_SHEMA);

			PreferenceLoader.storeValue(store, Constants.ST_PROJECT_FILE_PREF, Constants.ST_PROJECT_FILE_DEFAULT);
			PreferenceLoader.checkAndCreateFile(project, Constants.ST_SCHEMA_DIR_DEFAULT + "/" + Constants.ST_PROJECT_FILE_DEFAULT, Constants.DEFAULT_PROJECT);

			PreferenceLoader.storeValue(store, Constants.ST_START_TEMPLATE_FILE_PREF, Constants.ST_START_TEMPLATE_FILE_DEFAULT);
			PreferenceLoader.checkAndCreateFile(project, Constants.ST_TEMPLATE_DIR_DEFAULT + "/" + Constants.ST_START_TEMPLATE_FILE_DEFAULT,
					Constants.DEFAULT_MAIN_TEMPLATE);

			PreferenceLoader.storeValue(store, Constants.ST_SCHEMA_PACKAGE_PREF, Constants.ST_SCHEMA_PACKAGE_DEFAULT);
			PreferenceLoader.storeValue(store, Constants.ST_TEMPLATE_PACKAGE_PREF, Constants.ST_TEMPLATE_PACKAGE_DEFAULT);
			PreferenceLoader.storeValue(store, Constants.ST_USING_CACHE_PREF, Constants.ST_USING_CACHE_DEFAULT);

			PreferenceLoader.storeValue(store, Constants.ST_GOAL_PREF, Constants.ST_GOAL_DEFAULT.name());

		} catch (Exception e) {
			JtgUIPlugin.log(e);
			throw new CoreException(new StatusInfo(4, "Failed to toggle nature: \r\n" + e.toString()));
		}
	}

}
