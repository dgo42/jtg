package org.edgo.jtg.ui.dialogs;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;
import org.edgo.jtg.ui.Constants;
import org.edgo.jtg.ui.JtgUIPlugin;
import org.edgo.jtg.ui.config.PreferenceLoader;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class ConfigPropertyPage extends PropertyPage {

	private Text					txtSchemaDir;
	private Text					txtTemplateDir;
	private Text					txtSrcOutDir;
	private Text					txtJarOutDir;
	private Text					txtSchemaFile;
	private Text					txtProjectFile;
	private Text					txtStartTemplFile;
	private Text					txtSchemaPackage;
	private Text					txtTemplatePackage;
	private Button					chkUsingCache;

	private IWorkspaceRoot			workspaceRoot;

	/*
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath containerPath = getContainerFullPath();
		if ((containerPath != null) && (containerPath.segmentCount() > 0)) {
		  IProject project = workspaceRoot.getProject(containerPath.segment(0));
		  try {
		    if (!project.hasNature("org.eclipse.jdt.core.javanature")) {
		      setErrorMessage(SnippetMessages.getString("NewSnippetFileWizardPage.error.OnlyInJavaProject"));
		      return false;
		    }
	*/

	@Override
	protected Control createContents(Composite parent) {
		workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		Composite comp = new Composite(parent, 0);

		comp.setLayout(new FormLayout());

		Group grpDirectories = new Group(comp, SWT.NONE);
		FormData fd_grpDirectories = new FormData();
		fd_grpDirectories.top = new FormAttachment(0, 5);
		fd_grpDirectories.left = new FormAttachment(0, 5);
		fd_grpDirectories.bottom = new FormAttachment(0, 138);
		fd_grpDirectories.right = new FormAttachment(1, 1, -5);
		grpDirectories.setLayoutData(fd_grpDirectories);
		grpDirectories.setText("Directories");
		GridLayout gl_grpDirectories = new GridLayout(3, false);
		gl_grpDirectories.marginHeight = 3;
		gl_grpDirectories.marginWidth = 3;
		gl_grpDirectories.verticalSpacing = 3;
		gl_grpDirectories.horizontalSpacing = 3;
		grpDirectories.setLayout(gl_grpDirectories);

		Label lblSchemaDir = new Label(grpDirectories, SWT.NONE);
		lblSchemaDir.setText("Schema directory");

		txtSchemaDir = new Text(grpDirectories, SWT.BORDER);
		txtSchemaDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnSchemaDirBrowse = new Button(grpDirectories, SWT.NONE);
		btnSchemaDirBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select schema directory",
						"Select schema directory", Constants.SCHEMA_FILE_MASK);
				IResource initSelection = getProject().findMember(txtSchemaDir.getText());
				if (initSelection != null) {
					dialog.setInitialSelections(new IResource[] {initSelection});
				}

				if (dialog.open() == ElementTreeSelectionDialog.OK) {
					IResource selected = (IResource) dialog.getFirstResult();
					txtSchemaDir.setText(selected.getProjectRelativePath().toString());
				}
			}
		});
		btnSchemaDirBrowse.setText("Browse...");

		Label lblTemplateDir = new Label(grpDirectories, SWT.NONE);
		lblTemplateDir.setText("Template directory");

		txtTemplateDir = new Text(grpDirectories, SWT.BORDER);
		txtTemplateDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnTemplateDirBrowse = new Button(grpDirectories, SWT.NONE);
		btnTemplateDirBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select template directory",
						"Select template directory", Constants.TEMPLATE_FILE_MASK);
				IResource initSelection = getProject().findMember(txtTemplateDir.getText());
				if (initSelection != null) {
					dialog.setInitialSelections(new IResource[] {initSelection});
				}

				if (dialog.open() == ElementTreeSelectionDialog.OK) {
					IResource selected = (IResource) dialog.getFirstResult();
					txtTemplateDir.setText(selected.getProjectRelativePath().toString());
				}
			}
		});
		btnTemplateDirBrowse.setText("Browse...");

		Label lblSrcOutDir = new Label(grpDirectories, SWT.NONE);
		GridData gd_lblSrcOutDir = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblSrcOutDir.widthHint = 150;
		lblSrcOutDir.setLayoutData(gd_lblSrcOutDir);
		lblSrcOutDir.setText("Source output directory");

		txtSrcOutDir = new Text(grpDirectories, SWT.BORDER);
		txtSrcOutDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnSrcOutDirBrowse = new Button(grpDirectories, SWT.NONE);
		btnSrcOutDirBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select source output directory",
						"Select source output directory", null);
				IResource initSelection = getProject().findMember(txtSrcOutDir.getText());
				if (initSelection != null) {
					dialog.setInitialSelections(new IResource[] {initSelection});
				}

				if (dialog.open() == ElementTreeSelectionDialog.OK) {
					IResource selected = (IResource) dialog.getFirstResult();
					txtSrcOutDir.setText(selected.getProjectRelativePath().toString());
				}
			}
		});
		btnSrcOutDirBrowse.setText("Browse...");

		Label lblJarOutDir = new Label(grpDirectories, SWT.NONE);
		lblJarOutDir.setText("Jar output directory");

		txtJarOutDir = new Text(grpDirectories, SWT.BORDER);
		txtJarOutDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnJarOutDirBrowse = new Button(grpDirectories, SWT.NONE);
		btnJarOutDirBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select JAR output directory",
						"Select JAR output directory", null);
				IResource initSelection = getProject().findMember(txtJarOutDir.getText());
				if (initSelection != null) {
					dialog.setInitialSelections(new IResource[] {initSelection});
				}

				if (dialog.open() == ElementTreeSelectionDialog.OK) {
					IResource selected = (IResource) dialog.getFirstResult();
					txtJarOutDir.setText(selected.getProjectRelativePath().toString());
				}
			}
		});
		btnJarOutDirBrowse.setText("Browse...");

		Group grpFiles = new Group(comp, SWT.NONE);
		grpFiles.setText("Files");
		GridLayout gl_grpFiles = new GridLayout(3, false);
		gl_grpFiles.verticalSpacing = 3;
		gl_grpFiles.marginWidth = 3;
		gl_grpFiles.marginHeight = 3;
		gl_grpFiles.horizontalSpacing = 3;
		grpFiles.setLayout(gl_grpFiles);
		FormData fd_grpFiles = new FormData();
		fd_grpFiles.bottom = new FormAttachment(grpDirectories, 111, SWT.BOTTOM);
		fd_grpFiles.top = new FormAttachment(grpDirectories, 5);
		fd_grpFiles.left = new FormAttachment(0, 5);
		fd_grpFiles.right = new FormAttachment(1, 1, -5);
		grpFiles.setLayoutData(fd_grpFiles);

		Label lblSchemaFile = new Label(grpFiles, SWT.NONE);
		GridData gd_lblSchemaFile = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblSchemaFile.widthHint = 150;
		lblSchemaFile.setLayoutData(gd_lblSchemaFile);
		lblSchemaFile.setText("Schema file");

		txtSchemaFile = new Text(grpFiles, SWT.BORDER);
		txtSchemaFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnSchemaFileBrowse = new Button(grpFiles, SWT.NONE);
		btnSchemaFileBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ElementTreeSelectionDialog dialog = createFileSelectionDialog("Select schema file", "Select schema file",
						Constants.SCHEMA_FILE_MASK);
				IResource input = getProject().findMember(txtSchemaDir.getText());
				if (input != null) {
					dialog.setInput(input);
				}
				IResource initSelection = getProject().findMember(txtSchemaFile.getText());
				if (initSelection != null) {
					dialog.setInitialSelections(new IResource[] {initSelection});
				}

				if (dialog.open() == ElementTreeSelectionDialog.OK) {
					IResource selected = (IResource) dialog.getFirstResult();
					txtSchemaFile.setText(selected.getName());
				}
			}
		});
		btnSchemaFileBrowse.setText("Browse...");

		Label lblProjectFile = new Label(grpFiles, SWT.NONE);
		lblProjectFile.setText("Project file");

		txtProjectFile = new Text(grpFiles, SWT.BORDER);
		txtProjectFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnProjectFileBrowse = new Button(grpFiles, SWT.NONE);
		btnProjectFileBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ElementTreeSelectionDialog dialog = createFileSelectionDialog("Select project file", "Select project file",
						Constants.PROJECT_FILE_MASK);
				dialog.setInput(getProject());
				IResource initSelection = getProject().findMember(txtProjectFile.getText());
				if (initSelection != null) {
					dialog.setInitialSelections(new IResource[] {initSelection});
				}

				if (dialog.open() == ElementTreeSelectionDialog.OK) {
					IResource selected = (IResource) dialog.getFirstResult();
					txtProjectFile.setText(selected.getName());
				}
			}
		});
		btnProjectFileBrowse.setText("Browse...");

		Label lblStartTemplFile = new Label(grpFiles, SWT.NONE);
		lblStartTemplFile.setText("Start template");

		txtStartTemplFile = new Text(grpFiles, SWT.BORDER);
		txtStartTemplFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnStartTemplFileBrowse = new Button(grpFiles, SWT.NONE);
		btnStartTemplFileBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ElementTreeSelectionDialog dialog = createFileSelectionDialog("Select schema file", "Select schema file",
						Constants.SCHEMA_FILE_MASK);
				IResource input = getProject().findMember(txtTemplateDir.getText());
				if (input != null) {
					dialog.setInput(input);
				}
				IResource initSelection = getProject().findMember(txtStartTemplFile.getText());
				if (initSelection != null) {
					dialog.setInitialSelections(new IResource[] {initSelection});
				}

				if (dialog.open() == ElementTreeSelectionDialog.OK) {
					IResource selected = (IResource) dialog.getFirstResult();
					txtStartTemplFile.setText(selected.getName());
				}
			}
		});
		btnStartTemplFileBrowse.setText("Browse...");

		Group grpPackages = new Group(comp, SWT.NONE);
		grpPackages.setText("Packages");
		GridLayout gl_grpPackages = new GridLayout(2, false);
		gl_grpPackages.verticalSpacing = 7;
		gl_grpPackages.horizontalSpacing = 3;
		gl_grpPackages.marginHeight = 3;
		gl_grpPackages.marginWidth = 3;
		grpPackages.setLayout(gl_grpPackages);
		FormData fd_grpPackages = new FormData();
		fd_grpPackages.bottom = new FormAttachment(grpFiles, 79, SWT.BOTTOM);
		fd_grpPackages.top = new FormAttachment(grpFiles, 5);
		fd_grpPackages.left = new FormAttachment(0, 5);
		fd_grpPackages.right = new FormAttachment(1, 1, -5);

		grpPackages.setLayoutData(fd_grpPackages);

		Label lblSchemapackage = new Label(grpPackages, SWT.NONE);
		GridData gd_lblSchemapackage = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblSchemapackage.widthHint = 150;
		lblSchemapackage.setLayoutData(gd_lblSchemapackage);
		lblSchemapackage.setText("SchemaPackage");

		txtSchemaPackage = new Text(grpPackages, SWT.BORDER);
		txtSchemaPackage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTemplatePackage = new Label(grpPackages, SWT.NONE);
		lblTemplatePackage.setText("Template Package");

		txtTemplatePackage = new Text(grpPackages, SWT.BORDER);
		txtTemplatePackage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group grpGeneral = new Group(comp, SWT.NONE);
		grpGeneral.setText("General");
		GridLayout gl_grpGeneral = new GridLayout(2, false);
		gl_grpGeneral.horizontalSpacing = 3;
		gl_grpGeneral.marginHeight = 3;
		gl_grpGeneral.marginWidth = 3;
		gl_grpGeneral.verticalSpacing = 3;
		grpGeneral.setLayout(gl_grpGeneral);
		FormData fd_grpGeneral = new FormData();
		fd_grpGeneral.bottom = new FormAttachment(grpPackages, 46, SWT.BOTTOM);
		fd_grpGeneral.top = new FormAttachment(grpPackages, 5);
		fd_grpGeneral.left = new FormAttachment(0, 5);
		fd_grpGeneral.right = new FormAttachment(1, 1, -5);

		grpGeneral.setLayoutData(fd_grpGeneral);

		Label lblUsingCahce = new Label(grpGeneral, SWT.NONE);
		lblUsingCahce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				chkUsingCache.setSelection(!chkUsingCache.getSelection());
			}
		});
		GridData gd_lblUsingCahce = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblUsingCahce.widthHint = 150;
		lblUsingCahce.setLayoutData(gd_lblUsingCahce);
		lblUsingCahce.setText("Using cahce");

		chkUsingCache = new Button(grpGeneral, SWT.CHECK);
		loadData();
		return comp;
	}

	protected FolderSelectionDialog createFolderSelectionDialog(String title, String message, Pattern fileMask) {
		ILabelProvider lp = new WorkbenchLabelProvider();
		ITreeContentProvider cp = new WorkbenchContentProvider();
		Class<?>[] acceptedSelectedClasses = {IFolder.class};
		Class<?>[] acceptedViewClasses = {IProject.class, IFolder.class};

		ISelectionStatusValidator validator = new TypedElementSelectionValidator(acceptedSelectedClasses, false);
		IProject[] allProjects = workspaceRoot.getProjects();
		ArrayList<IResource> rejectedElements = new ArrayList<IResource>(allProjects.length);
		IProject currProject = getProject();
		for (int i = 0; i < allProjects.length; i++) {
			if (!allProjects[i].equals(currProject)) {
				rejectedElements.add(allProjects[i]);
			}
		}
		ViewerFilter filter = new TypedViewerFilter(acceptedViewClasses, rejectedElements.toArray(), null);

		FolderSelectionDialog dialog = new FolderSelectionDialog(getShell(), lp, cp);
		dialog.setTitle(title);
		dialog.setValidator(validator);
		dialog.setMessage(message);
		dialog.addFilter(filter);
		dialog.setInput(workspaceRoot);
		dialog.setComparator(new ResourceComparator(ResourceComparator.NAME));

		return dialog;
	}

	protected FolderSelectionDialog createFileSelectionDialog(String title, String message, Pattern fileMask) {
		ILabelProvider lp = new WorkbenchLabelProvider();
		ITreeContentProvider cp = new WorkbenchContentProvider();
		Class<?>[] acceptedSelectedClasses = {IFile.class};
		Class<?>[] acceptedViewClasses = {IProject.class, IFolder.class, IFile.class};

		ISelectionStatusValidator validator = new TypedElementSelectionValidator(acceptedSelectedClasses, false, null,
				fileMask);
		IProject[] allProjects = workspaceRoot.getProjects();
		ArrayList<IResource> rejectedElements = new ArrayList<IResource>(allProjects.length);
		IProject currProject = getProject();
		for (int i = 0; i < allProjects.length; i++) {
			if (!allProjects[i].equals(currProject)) {
				rejectedElements.add(allProjects[i]);
			}
		}
		ViewerFilter filter = new TypedViewerFilter(acceptedViewClasses, rejectedElements.toArray(), fileMask);

		FolderSelectionDialog dialog = new FolderSelectionDialog(getShell(), lp, cp);
		dialog.setTitle(title);
		dialog.setValidator(validator);
		dialog.setMessage(message);
		dialog.addFilter(filter);
		dialog.setInput(workspaceRoot);
		dialog.setComparator(new ResourceComparator(ResourceComparator.NAME));

		return dialog;
	}

	private IProject getProject() {
		return (IProject) getElement().getAdapter(IProject.class);
	}

	private void storeData() {
		IProject project = getProject();
		IScopeContext projectScope = new ProjectScope(project);
		IEclipsePreferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);

		PreferenceLoader.storeValue(store, Constants.SCHEMA_DIR_PREF, txtSchemaDir.getText());
		PreferenceLoader.storeValue(store, Constants.TEMPLATE_DIR_PREF, txtTemplateDir.getText());
		PreferenceLoader.storeValue(store, Constants.SOURCE_OUT_DIR_PREF, txtSrcOutDir.getText());
		PreferenceLoader.storeValue(store, Constants.JAR_OUTPUT_DIR_PREF, txtJarOutDir.getText());
		PreferenceLoader.storeValue(store, Constants.SCHEMA_FILE_PREF, txtSchemaFile.getText());
		PreferenceLoader.storeValue(store, Constants.PROJECT_FILE_PREF, txtProjectFile.getText());
		PreferenceLoader.storeValue(store, Constants.START_TEMPLATE_FILE_PREF, txtStartTemplFile.getText());
		PreferenceLoader.storeValue(store, Constants.SCHEMA_PACKAGE_PREF, txtSchemaPackage.getText());
		PreferenceLoader.storeValue(store, Constants.TEMPLATE_PACKAGE_PREF, txtTemplatePackage.getText());
		PreferenceLoader.storeValue(store, Constants.USING_CACHE_PREF, chkUsingCache.getSelection());
	}

	private void loadData() {
		IProject project = getProject();
		IScopeContext projectScope = new ProjectScope(project);
		Preferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);

		try {
			store.sync();
		} catch (BackingStoreException e) {
			JtgUIPlugin.log(e);
		}

		txtSchemaDir.setText(PreferenceLoader.loadValue(store, Constants.SCHEMA_DIR_PREF));
		txtTemplateDir.setText(PreferenceLoader.loadValue(store, Constants.TEMPLATE_DIR_PREF));
		txtSrcOutDir.setText(PreferenceLoader.loadValue(store, Constants.SOURCE_OUT_DIR_PREF));
		txtJarOutDir.setText(PreferenceLoader.loadValue(store, Constants.JAR_OUTPUT_DIR_PREF));
		txtSchemaFile.setText(PreferenceLoader.loadValue(store, Constants.SCHEMA_FILE_PREF));
		txtProjectFile.setText(PreferenceLoader.loadValue(store, Constants.PROJECT_FILE_PREF));
		txtStartTemplFile.setText(PreferenceLoader.loadValue(store, Constants.START_TEMPLATE_FILE_PREF));
		txtSchemaPackage.setText(PreferenceLoader.loadValue(store, Constants.SCHEMA_PACKAGE_PREF));
		txtTemplatePackage.setText(PreferenceLoader.loadValue(store, Constants.TEMPLATE_PACKAGE_PREF));
		chkUsingCache.setSelection(PreferenceLoader.loadValueBool(store, Constants.USING_CACHE_PREF));
	}

	@Override
	public void applyData(Object data) {
		storeData();
		super.applyData(data);
	}

	@Override
	public boolean performOk() {
		storeData();
		return super.performOk();
	}
}
