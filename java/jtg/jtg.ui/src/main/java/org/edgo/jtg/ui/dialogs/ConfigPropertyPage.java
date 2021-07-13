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
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;
import org.edgo.jtg.core.GeneratorCommand;
import org.edgo.jtg.core.ProjectType;
import org.edgo.jtg.ui.Constants;
import org.edgo.jtg.ui.JtgUIPlugin;
import org.edgo.jtg.ui.config.PreferenceLoader;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class ConfigPropertyPage extends PropertyPage {

	private IWorkspaceRoot workspaceRoot;

	private Combo cmbProjectType;

	private CTabFolder tabFolder;

	// leader project
	private Text l_txtSchemaDir;
	private Text l_txtTemplateDir;
	private Text l_txtSrcOutDir;
	private Text l_txtJarOutDir;
	private Text l_txtSchemaFile;
	private Text l_txtStartTemplFile;
	private Text l_txtSchemaPackage;
	private Text l_txtTemplatePackage;

	// follower project
	private Text f_leaderProject;
	private Text f_txtProjectDir;
	private Text f_txtProjectFile;

	// standalone project
	private Text st_txtSchemaDir;
	private Text st_txtTemplateDir;
	private Text st_txtSrcOutDir;
	private Text st_txtJarOutDir;
	private Text st_txtSchemaFile;
	private Text st_txtProjectFile;
	private Text st_txtStartTemplFile;
	private Text st_txtSchemaPackage;
	private Text st_txtTemplatePackage;
	private Button st_chkUsingCache;
	private Combo st_cmbGoal;

	@Override
	protected Control createContents(Composite parent) {
		Display display = Display.getCurrent();
		Color widgetBackground = display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);

		workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		Composite mainComp = new Composite(parent, 0);
		mainComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout gl_mainComp = new GridLayout(1, false);
		gl_mainComp.marginHeight = 1;
		gl_mainComp.marginWidth = 1;
		gl_mainComp.verticalSpacing = 1;
		gl_mainComp.horizontalSpacing = 1;
		mainComp.setLayout(gl_mainComp);

		cmbProjectType = new Combo(mainComp, SWT.READ_ONLY);
		cmbProjectType.add("Leader template project", 0);
		cmbProjectType.add("Follower project", 1);
		cmbProjectType.add("Standalone template project", 2);
		cmbProjectType.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cmbProjectType.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tabFolder.setSelection(cmbProjectType.getSelectionIndex());
			}
		});

		tabFolder = new CTabFolder(mainComp, SWT.NONE);
		tabFolder.setTabHeight(0);
		tabFolder.setBorderVisible(false);
		tabFolder.setSelectionBackground(widgetBackground);
		tabFolder.layout();
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setHighlightEnabled(false);

		// leader project
		CTabItem ti1 = new CTabItem(tabFolder, SWT.NONE);
		Composite leaderComp = new Composite(tabFolder, SWT.NONE);
		ti1.setControl(leaderComp);
		leaderComp.setLayout(new FormLayout());
		{
			Group grpDirectories = new Group(leaderComp, SWT.NONE);
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

			l_txtSchemaDir = new Text(grpDirectories, SWT.BORDER);
			l_txtSchemaDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnSchemaDirBrowse = new Button(grpDirectories, SWT.NONE);
			btnSchemaDirBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select schema directory",
							"Select schema directory", Constants.SCHEMA_FILE_MASK);
					IResource initSelection = getProject().findMember(l_txtSchemaDir.getText());
					dialog.setInput(getProject());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						l_txtSchemaDir.setText(selected.getProjectRelativePath().toString());
					}
				}
			});
			btnSchemaDirBrowse.setText("Browse...");

			Label lblTemplateDir = new Label(grpDirectories, SWT.NONE);
			lblTemplateDir.setText("Template directory");

			l_txtTemplateDir = new Text(grpDirectories, SWT.BORDER);
			l_txtTemplateDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnTemplateDirBrowse = new Button(grpDirectories, SWT.NONE);
			btnTemplateDirBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select template directory",
							"Select template directory", Constants.TEMPLATE_FILE_MASK);
					IResource initSelection = getProject().findMember(l_txtTemplateDir.getText());
					dialog.setInput(getProject());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						l_txtTemplateDir.setText(selected.getProjectRelativePath().toString());
					}
				}
			});
			btnTemplateDirBrowse.setText("Browse...");

			Label lblSrcOutDir = new Label(grpDirectories, SWT.NONE);
			GridData gd_lblSrcOutDir = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_lblSrcOutDir.widthHint = 150;
			lblSrcOutDir.setLayoutData(gd_lblSrcOutDir);
			lblSrcOutDir.setText("Source output directory");

			l_txtSrcOutDir = new Text(grpDirectories, SWT.BORDER);
			l_txtSrcOutDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnSrcOutDirBrowse = new Button(grpDirectories, SWT.NONE);
			btnSrcOutDirBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select source output directory",
							"Select source output directory", null);
					dialog.setInput(getProject());
					IResource initSelection = getProject().findMember(l_txtSrcOutDir.getText());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						l_txtSrcOutDir.setText(selected.getProjectRelativePath().toString());
					}
				}
			});
			btnSrcOutDirBrowse.setText("Browse...");

			Label lblJarOutDir = new Label(grpDirectories, SWT.NONE);
			lblJarOutDir.setText("Jar output directory");

			l_txtJarOutDir = new Text(grpDirectories, SWT.BORDER);
			l_txtJarOutDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnJarOutDirBrowse = new Button(grpDirectories, SWT.NONE);
			btnJarOutDirBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select JAR output directory",
							"Select JAR output directory", null);
					dialog.setInput(getProject());
					IResource initSelection = getProject().findMember(l_txtJarOutDir.getText());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						l_txtJarOutDir.setText(selected.getProjectRelativePath().toString());
					}
				}
			});
			btnJarOutDirBrowse.setText("Browse...");

			Group grpFiles = new Group(leaderComp, SWT.NONE);
			grpFiles.setText("Files");
			GridLayout gl_grpFiles = new GridLayout(3, false);
			gl_grpFiles.verticalSpacing = 3;
			gl_grpFiles.marginWidth = 3;
			gl_grpFiles.marginHeight = 3;
			gl_grpFiles.horizontalSpacing = 3;
			grpFiles.setLayout(gl_grpFiles);
			FormData fd_grpFiles = new FormData();
			fd_grpFiles.bottom = new FormAttachment(grpDirectories, 82, SWT.BOTTOM);
			fd_grpFiles.top = new FormAttachment(grpDirectories, 5);
			fd_grpFiles.left = new FormAttachment(0, 5);
			fd_grpFiles.right = new FormAttachment(1, 1, -5);
			grpFiles.setLayoutData(fd_grpFiles);

			Label lblSchemaFile = new Label(grpFiles, SWT.NONE);
			GridData gd_lblSchemaFile = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_lblSchemaFile.widthHint = 150;
			lblSchemaFile.setLayoutData(gd_lblSchemaFile);
			lblSchemaFile.setText("Schema file");

			l_txtSchemaFile = new Text(grpFiles, SWT.BORDER);
			l_txtSchemaFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnSchemaFileBrowse = new Button(grpFiles, SWT.NONE);
			btnSchemaFileBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFileSelectionDialog("Select schema file",
							"Select schema file", Constants.SCHEMA_FILE_MASK);
					IResource input = getProject().findMember(st_txtSchemaDir.getText());
					if (input != null) {
						dialog.setInput(input);
					}
					IResource initSelection = getProject().findMember(l_txtSchemaFile.getText());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						l_txtSchemaFile.setText(selected.getName());
					}
				}
			});
			btnSchemaFileBrowse.setText("Browse...");

			Label lblStartTemplFile = new Label(grpFiles, SWT.NONE);
			lblStartTemplFile.setText("Start template");

			l_txtStartTemplFile = new Text(grpFiles, SWT.BORDER);
			l_txtStartTemplFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnStartTemplFileBrowse = new Button(grpFiles, SWT.NONE);
			btnStartTemplFileBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFileSelectionDialog("Select schema file",
							"Select schema file", Constants.TEMPLATE_FILE_MASK);
					IResource input = getProject().findMember(st_txtTemplateDir.getText());
					if (input != null) {
						dialog.setInput(input);
					}
					IResource initSelection = getProject().findMember(l_txtStartTemplFile.getText());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						l_txtStartTemplFile.setText(selected.getName());
					}
				}
			});
			btnStartTemplFileBrowse.setText("Browse...");

			Group grpPackages = new Group(leaderComp, SWT.NONE);
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

			l_txtSchemaPackage = new Text(grpPackages, SWT.BORDER);
			l_txtSchemaPackage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Label lblTemplatePackage = new Label(grpPackages, SWT.NONE);
			lblTemplatePackage.setText("Template Package");

			l_txtTemplatePackage = new Text(grpPackages, SWT.BORDER);
			l_txtTemplatePackage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		}

		// follower project
		CTabItem ti2 = new CTabItem(tabFolder, SWT.NONE);
		Composite followerComp = new Composite(tabFolder, SWT.NONE);
		ti2.setControl(followerComp);
		followerComp.setLayout(new FormLayout());
		{
			Group grpLeader = new Group(followerComp, SWT.NONE);
			FormData fd_grpLeader = new FormData();
			fd_grpLeader.top = new FormAttachment(0, 5);
			fd_grpLeader.left = new FormAttachment(0, 5);
			fd_grpLeader.bottom = new FormAttachment(0, 52);
			fd_grpLeader.right = new FormAttachment(1, 1, -5);
			grpLeader.setLayoutData(fd_grpLeader);
			grpLeader.setText("Leader");
			GridLayout gl_grpLeader = new GridLayout(3, false);
			gl_grpLeader.marginHeight = 3;
			gl_grpLeader.marginWidth = 3;
			gl_grpLeader.verticalSpacing = 3;
			gl_grpLeader.horizontalSpacing = 3;
			grpLeader.setLayout(gl_grpLeader);

			Label lblLeaderPrj = new Label(grpLeader, SWT.NONE);
			lblLeaderPrj.setText("Leader project");

			f_leaderProject = new Text(grpLeader, SWT.BORDER);
			f_leaderProject.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnLeaderPrjBrowse = new Button(grpLeader, SWT.NONE);
			btnLeaderPrjBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createLeaderPrjSelectionDialog("Select leader project",
							"Select leader project");
					IResource initSelection = workspaceRoot.findMember("/");
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { workspaceRoot });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						f_leaderProject.setText(selected.getProject().getName());
					}
				}
			});
			btnLeaderPrjBrowse.setText("Browse...");

			Group grpDirectories = new Group(followerComp, SWT.NONE);
			FormData fd_grpDirectories = new FormData();
			fd_grpDirectories.bottom = new FormAttachment(grpLeader, 52, SWT.BOTTOM);
			fd_grpDirectories.top = new FormAttachment(grpLeader, 5);
			fd_grpDirectories.left = new FormAttachment(0, 5);
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

			f_txtProjectDir = new Text(grpDirectories, SWT.BORDER);
			f_txtProjectDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnSchemaDirBrowse = new Button(grpDirectories, SWT.NONE);
			btnSchemaDirBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select schema directory",
							"Select schema directory", Constants.SCHEMA_FILE_MASK);
					IResource initSelection = getProject().findMember(f_txtProjectDir.getText());
					dialog.setInput(getProject());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						f_txtProjectDir.setText(selected.getProjectRelativePath().toString());
					}
				}
			});
			btnSchemaDirBrowse.setText("Browse...");

			Group grpFiles = new Group(followerComp, SWT.NONE);
			grpFiles.setText("Files");
			GridLayout gl_grpFiles = new GridLayout(3, false);
			gl_grpFiles.verticalSpacing = 3;
			gl_grpFiles.marginWidth = 3;
			gl_grpFiles.marginHeight = 3;
			gl_grpFiles.horizontalSpacing = 3;
			grpFiles.setLayout(gl_grpFiles);
			FormData fd_grpFiles = new FormData();
			fd_grpFiles.bottom = new FormAttachment(grpDirectories, 52, SWT.BOTTOM);
			fd_grpFiles.top = new FormAttachment(grpDirectories, 5);
			fd_grpFiles.left = new FormAttachment(0, 5);
			fd_grpFiles.right = new FormAttachment(1, 1, -5);
			grpFiles.setLayoutData(fd_grpFiles);

			Label lblProjectFile = new Label(grpFiles, SWT.NONE);
			lblProjectFile.setText("Project file");

			f_txtProjectFile = new Text(grpFiles, SWT.BORDER);
			f_txtProjectFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnProjectFileBrowse = new Button(grpFiles, SWT.NONE);
			btnProjectFileBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFileSelectionDialog("Select project file",
							"Select project file", Constants.PROJECT_FILE_MASK);
					IResource input = getProject().findMember(st_txtSchemaDir.getText());
					if (input != null) {
						dialog.setInput(input);
					}
					IResource initSelection = getProject().findMember(f_txtProjectFile.getText());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						f_txtProjectFile.setText(selected.getName());
					}
				}
			});
			btnProjectFileBrowse.setText("Browse...");

		}

		// standalone project
		CTabItem ti3 = new CTabItem(tabFolder, SWT.NONE);
		Composite standaloneComp = new Composite(tabFolder, SWT.NONE);
		ti3.setControl(standaloneComp);
		standaloneComp.setLayout(new FormLayout());
		{
			Group grpDirectories = new Group(standaloneComp, SWT.NONE);
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

			st_txtSchemaDir = new Text(grpDirectories, SWT.BORDER);
			st_txtSchemaDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnSchemaDirBrowse = new Button(grpDirectories, SWT.NONE);
			btnSchemaDirBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select schema directory",
							"Select schema directory", Constants.SCHEMA_FILE_MASK);
					IResource initSelection = getProject().findMember(st_txtSchemaDir.getText());
					dialog.setInput(getProject());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						st_txtSchemaDir.setText(selected.getProjectRelativePath().toString());
					}
				}
			});
			btnSchemaDirBrowse.setText("Browse...");

			Label lblTemplateDir = new Label(grpDirectories, SWT.NONE);
			lblTemplateDir.setText("Template directory");

			st_txtTemplateDir = new Text(grpDirectories, SWT.BORDER);
			st_txtTemplateDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnTemplateDirBrowse = new Button(grpDirectories, SWT.NONE);
			btnTemplateDirBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select template directory",
							"Select template directory", Constants.TEMPLATE_FILE_MASK);
					IResource initSelection = getProject().findMember(st_txtTemplateDir.getText());
					dialog.setInput(getProject());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						st_txtTemplateDir.setText(selected.getProjectRelativePath().toString());
					}
				}
			});
			btnTemplateDirBrowse.setText("Browse...");

			Label lblSrcOutDir = new Label(grpDirectories, SWT.NONE);
			GridData gd_lblSrcOutDir = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_lblSrcOutDir.widthHint = 150;
			lblSrcOutDir.setLayoutData(gd_lblSrcOutDir);
			lblSrcOutDir.setText("Source output directory");

			st_txtSrcOutDir = new Text(grpDirectories, SWT.BORDER);
			st_txtSrcOutDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnSrcOutDirBrowse = new Button(grpDirectories, SWT.NONE);
			btnSrcOutDirBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select source output directory",
							"Select source output directory", null);
					dialog.setInput(getProject());
					IResource initSelection = getProject().findMember(st_txtSrcOutDir.getText());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						st_txtSrcOutDir.setText(selected.getProjectRelativePath().toString());
					}
				}
			});
			btnSrcOutDirBrowse.setText("Browse...");

			Label lblJarOutDir = new Label(grpDirectories, SWT.NONE);
			lblJarOutDir.setText("Jar output directory");

			st_txtJarOutDir = new Text(grpDirectories, SWT.BORDER);
			st_txtJarOutDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnJarOutDirBrowse = new Button(grpDirectories, SWT.NONE);
			btnJarOutDirBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFolderSelectionDialog("Select JAR output directory",
							"Select JAR output directory", null);
					dialog.setInput(getProject());
					IResource initSelection = getProject().findMember(st_txtJarOutDir.getText());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						st_txtJarOutDir.setText(selected.getProjectRelativePath().toString());
					}
				}
			});
			btnJarOutDirBrowse.setText("Browse...");

			Group grpFiles = new Group(standaloneComp, SWT.NONE);
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

			st_txtSchemaFile = new Text(grpFiles, SWT.BORDER);
			st_txtSchemaFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnSchemaFileBrowse = new Button(grpFiles, SWT.NONE);
			btnSchemaFileBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFileSelectionDialog("Select schema file",
							"Select schema file", Constants.SCHEMA_FILE_MASK);
					IResource input = getProject().findMember(st_txtSchemaDir.getText());
					if (input != null) {
						dialog.setInput(input);
					}
					IResource initSelection = getProject().findMember(st_txtSchemaFile.getText());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						st_txtSchemaFile.setText(selected.getName());
					}
				}
			});
			btnSchemaFileBrowse.setText("Browse...");

			Label lblProjectFile = new Label(grpFiles, SWT.NONE);
			lblProjectFile.setText("Project file");

			st_txtProjectFile = new Text(grpFiles, SWT.BORDER);
			st_txtProjectFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnProjectFileBrowse = new Button(grpFiles, SWT.NONE);
			btnProjectFileBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFileSelectionDialog("Select project file",
							"Select project file", Constants.PROJECT_FILE_MASK);
					IResource input = getProject().findMember(st_txtSchemaDir.getText());
					dialog.setInput(input);
					IResource initSelection = getProject().findMember(st_txtProjectFile.getText());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						st_txtProjectFile.setText(selected.getName());
					}
				}
			});
			btnProjectFileBrowse.setText("Browse...");

			Label lblStartTemplFile = new Label(grpFiles, SWT.NONE);
			lblStartTemplFile.setText("Start template");

			st_txtStartTemplFile = new Text(grpFiles, SWT.BORDER);
			st_txtStartTemplFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Button btnStartTemplFileBrowse = new Button(grpFiles, SWT.NONE);
			btnStartTemplFileBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ElementTreeSelectionDialog dialog = createFileSelectionDialog("Select schema file",
							"Select schema file", Constants.TEMPLATE_FILE_MASK);
					IResource input = getProject().findMember(st_txtTemplateDir.getText());
					if (input != null) {
						dialog.setInput(input);
					}
					IResource initSelection = getProject().findMember(st_txtStartTemplFile.getText());
					if (initSelection != null) {
						dialog.setInitialSelections((Object[]) new IResource[] { initSelection });
					}

					if (dialog.open() == ElementTreeSelectionDialog.OK) {
						IResource selected = (IResource) dialog.getFirstResult();
						st_txtStartTemplFile.setText(selected.getName());
					}
				}
			});
			btnStartTemplFileBrowse.setText("Browse...");

			Group grpPackages = new Group(standaloneComp, SWT.NONE);
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

			st_txtSchemaPackage = new Text(grpPackages, SWT.BORDER);
			st_txtSchemaPackage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Label lblTemplatePackage = new Label(grpPackages, SWT.NONE);
			lblTemplatePackage.setText("Template Package");

			st_txtTemplatePackage = new Text(grpPackages, SWT.BORDER);
			st_txtTemplatePackage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

			Group grpGeneral = new Group(standaloneComp, SWT.NONE);
			grpGeneral.setText("General");
			GridLayout gl_grpGeneral = new GridLayout(2, false);
			gl_grpGeneral.horizontalSpacing = 3;
			gl_grpGeneral.marginHeight = 3;
			gl_grpGeneral.marginWidth = 3;
			gl_grpGeneral.verticalSpacing = 3;
			grpGeneral.setLayout(gl_grpGeneral);
			FormData fd_grpGeneral = new FormData();
			fd_grpGeneral.bottom = new FormAttachment(grpPackages, 72, SWT.BOTTOM);
			fd_grpGeneral.top = new FormAttachment(grpPackages, 5);
			fd_grpGeneral.left = new FormAttachment(0, 5);
			fd_grpGeneral.right = new FormAttachment(1, 1, -5);

			grpGeneral.setLayoutData(fd_grpGeneral);

			Label lblUsingCahce = new Label(grpGeneral, SWT.NONE);
			lblUsingCahce.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					st_chkUsingCache.setSelection(!st_chkUsingCache.getSelection());
				}
			});
			GridData gd_lblUsingCahce = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_lblUsingCahce.widthHint = 150;
			lblUsingCahce.setLayoutData(gd_lblUsingCahce);
			lblUsingCahce.setText("Using cache");

			st_chkUsingCache = new Button(grpGeneral, SWT.CHECK);

			Label lblGoal = new Label(grpGeneral, SWT.NONE);
			lblUsingCahce.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					st_cmbGoal.forceFocus();
				}
			});

			GridData gd_lblGoal = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_lblGoal.widthHint = 150;
			lblGoal.setLayoutData(gd_lblGoal);
			lblGoal.setText("Generate java sources for...");

			st_cmbGoal = new Combo(grpGeneral, SWT.READ_ONLY);
			st_cmbGoal.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 0, 1));
			st_cmbGoal.add("schema only", 0);
			st_cmbGoal.add("templates only", 1);
			st_cmbGoal.add("schema and tempates", 2);
			st_cmbGoal.add("schema and tempates and compile java classes", 3);
			st_cmbGoal.add("schema & tempates & compile java classes & create JAR", 4);
			st_cmbGoal.add("Full process include generate target using project file", 5);
		}
		loadData();
		return mainComp;
	}

	protected FolderSelectionDialog createFolderSelectionDialog(String title, String message, Pattern fileMask) {
		ILabelProvider lp = new WorkbenchLabelProvider();
		ITreeContentProvider cp = new WorkbenchContentProvider();
		Class<?>[] acceptedSelectedClasses = { IFolder.class };
		Class<?>[] acceptedViewClasses = { IProject.class, IFolder.class };

		ISelectionStatusValidator validator = new TypedElementSelectionValidator(acceptedSelectedClasses, false);
		IProject[] allProjects = workspaceRoot.getProjects();
		ArrayList<IResource> rejectedElements = new ArrayList<IResource>(allProjects.length);
		/*
		 * IProject currProject = getProject(); for (int i = 0; i < allProjects.length;
		 * i++) { if (!allProjects[i].equals(currProject)) {
		 * rejectedElements.add(allProjects[i]); } }
		 */
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
		Class<?>[] acceptedSelectedClasses = { IFile.class };
		Class<?>[] acceptedViewClasses = { IProject.class, IFolder.class, IFile.class };

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

	protected FolderSelectionDialog createLeaderPrjSelectionDialog(String title, String message) {
		ILabelProvider lp = new WorkbenchLabelProvider();
		ITreeContentProvider cp = new WorkbenchContentProvider();
		Class<?>[] acceptedSelectedClasses = { IProject.class };
		Class<?>[] acceptedViewClasses = { IProject.class };

		ISelectionStatusValidator validator = new TypedElementSelectionValidator(acceptedSelectedClasses, false);
		IProject[] allProjects = workspaceRoot.getProjects();
		
		ArrayList<IResource> rejectedElements = new ArrayList<IResource>(allProjects.length);

		IProject currProject = getProject();
		for (int i = 0; i < allProjects.length; i++) {
			IProject project = allProjects[i];
			if (project.equals(currProject)) {
				rejectedElements.add(project);
			} else {
				IScopeContext projectScope = new ProjectScope(project);
				IEclipsePreferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);

				ProjectType projectType = ProjectType.parse(PreferenceLoader.loadValue(store, Constants.PROJECT_TYPE));
				if (projectType != ProjectType.LEADER) {
					rejectedElements.add(project);
				}
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

	private IProject getProject() {
		return (IProject) getElement().getAdapter(IProject.class);
	}

	private void storeData() {
		IProject project = getProject();
		IScopeContext projectScope = new ProjectScope(project);
		IEclipsePreferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);

		
		ProjectType projectType = ProjectType.parse(cmbProjectType.getSelectionIndex());
		PreferenceLoader.storeValue(store, Constants.PROJECT_TYPE, projectType.name());

		// store leader settings
		PreferenceLoader.storeValue(store, Constants.L_SCHEMA_DIR_PREF, l_txtSchemaDir.getText());
		PreferenceLoader.storeValue(store, Constants.L_TEMPLATE_DIR_PREF, l_txtTemplateDir.getText());
		PreferenceLoader.storeValue(store, Constants.L_SOURCE_OUT_DIR_PREF, l_txtSrcOutDir.getText());
		PreferenceLoader.storeValue(store, Constants.L_JAR_OUTPUT_DIR_PREF, l_txtJarOutDir.getText());
		PreferenceLoader.storeValue(store, Constants.L_SCHEMA_FILE_PREF, l_txtSchemaFile.getText());
		PreferenceLoader.storeValue(store, Constants.L_START_TEMPLATE_FILE_PREF, l_txtStartTemplFile.getText());
		PreferenceLoader.storeValue(store, Constants.L_SCHEMA_PACKAGE_PREF, l_txtSchemaPackage.getText());
		PreferenceLoader.storeValue(store, Constants.L_TEMPLATE_PACKAGE_PREF, l_txtTemplatePackage.getText());
		
		// store follower settings
		PreferenceLoader.storeValue(store, Constants.F_LEADER_PROJECT, f_leaderProject.getText());
		PreferenceLoader.storeValue(store, Constants.F_PROJECT_DIR_PREF, f_txtProjectDir.getText());
		PreferenceLoader.storeValue(store, Constants.F_PROJECT_FILE_PREF, f_txtProjectFile.getText());

		// store standalone settings
		PreferenceLoader.storeValue(store, Constants.ST_SCHEMA_DIR_PREF, st_txtSchemaDir.getText());
		PreferenceLoader.storeValue(store, Constants.ST_TEMPLATE_DIR_PREF, st_txtTemplateDir.getText());
		PreferenceLoader.storeValue(store, Constants.ST_SOURCE_OUT_DIR_PREF, st_txtSrcOutDir.getText());
		PreferenceLoader.storeValue(store, Constants.ST_JAR_OUTPUT_DIR_PREF, st_txtJarOutDir.getText());
		PreferenceLoader.storeValue(store, Constants.ST_SCHEMA_FILE_PREF, st_txtSchemaFile.getText());
		PreferenceLoader.storeValue(store, Constants.ST_PROJECT_FILE_PREF, st_txtProjectFile.getText());
		PreferenceLoader.storeValue(store, Constants.ST_START_TEMPLATE_FILE_PREF, st_txtStartTemplFile.getText());
		PreferenceLoader.storeValue(store, Constants.ST_SCHEMA_PACKAGE_PREF, st_txtSchemaPackage.getText());
		PreferenceLoader.storeValue(store, Constants.ST_TEMPLATE_PACKAGE_PREF, st_txtTemplatePackage.getText());
		PreferenceLoader.storeValue(store, Constants.ST_USING_CACHE_PREF, st_chkUsingCache.getSelection());
		GeneratorCommand cmd = GeneratorCommand.parse(st_cmbGoal.getSelectionIndex());
		PreferenceLoader.storeValue(store, Constants.ST_GOAL_PREF, cmd != null ? cmd.name() : GeneratorCommand.COMPLETE.name());
	}

	private Preferences getPreferences() {
		IProject project = getProject();
		IScopeContext projectScope = new ProjectScope(project);
		Preferences store = projectScope.getNode(JtgUIPlugin.PLUGIN_ID);
		return store;
	}

	private void loadData() {
		Preferences store = getPreferences();

		try {
			store.sync();
		} catch (BackingStoreException e) {
			JtgUIPlugin.log(e);
		}

		ProjectType projectType = ProjectType.parse(PreferenceLoader.loadValue(store, Constants.PROJECT_TYPE));
		int index = ProjectType.STANDALONE.getIndex();
		if (projectType != null) {
			index = projectType.getIndex();
		}
		cmbProjectType.select(index);
		tabFolder.setSelection(index);

		// store leader settings
		l_txtSchemaDir.setText(PreferenceLoader.loadValue(store, Constants.L_SCHEMA_DIR_PREF));
		l_txtTemplateDir.setText(PreferenceLoader.loadValue(store, Constants.L_TEMPLATE_DIR_PREF));
		l_txtSrcOutDir.setText(PreferenceLoader.loadValue(store, Constants.L_SOURCE_OUT_DIR_PREF));
		l_txtJarOutDir.setText(PreferenceLoader.loadValue(store, Constants.L_JAR_OUTPUT_DIR_PREF));
		l_txtSrcOutDir.setText(PreferenceLoader.loadValue(store, Constants.L_SOURCE_OUT_DIR_PREF));
		l_txtJarOutDir.setText(PreferenceLoader.loadValue(store, Constants.L_JAR_OUTPUT_DIR_PREF));
		l_txtSchemaFile.setText(PreferenceLoader.loadValue(store, Constants.L_SCHEMA_FILE_PREF));
		l_txtStartTemplFile.setText(PreferenceLoader.loadValue(store, Constants.L_START_TEMPLATE_FILE_PREF));
		l_txtSchemaPackage.setText(PreferenceLoader.loadValue(store, Constants.L_SCHEMA_PACKAGE_PREF));
		l_txtTemplatePackage.setText(PreferenceLoader.loadValue(store, Constants.L_TEMPLATE_PACKAGE_PREF));

		// store follower settings
		f_leaderProject.setText(PreferenceLoader.loadValue(store, Constants.F_LEADER_PROJECT));
		f_txtProjectDir.setText(PreferenceLoader.loadValue(store, Constants.F_PROJECT_DIR_PREF));
		f_txtProjectFile.setText(PreferenceLoader.loadValue(store, Constants.F_PROJECT_FILE_PREF));

		// store standalone settings
		st_txtSchemaDir.setText(PreferenceLoader.loadValue(store, Constants.ST_SCHEMA_DIR_PREF));
		st_txtTemplateDir.setText(PreferenceLoader.loadValue(store, Constants.ST_TEMPLATE_DIR_PREF));
		st_txtSrcOutDir.setText(PreferenceLoader.loadValue(store, Constants.ST_SOURCE_OUT_DIR_PREF));
		st_txtJarOutDir.setText(PreferenceLoader.loadValue(store, Constants.ST_JAR_OUTPUT_DIR_PREF));
		st_txtSchemaFile.setText(PreferenceLoader.loadValue(store, Constants.ST_SCHEMA_FILE_PREF));
		st_txtProjectFile.setText(PreferenceLoader.loadValue(store, Constants.ST_PROJECT_FILE_PREF));
		st_txtStartTemplFile.setText(PreferenceLoader.loadValue(store, Constants.ST_START_TEMPLATE_FILE_PREF));
		st_txtSchemaPackage.setText(PreferenceLoader.loadValue(store, Constants.ST_SCHEMA_PACKAGE_PREF));
		st_txtTemplatePackage.setText(PreferenceLoader.loadValue(store, Constants.ST_TEMPLATE_PACKAGE_PREF));
		st_chkUsingCache.setSelection(PreferenceLoader.loadValueBool(store, Constants.ST_USING_CACHE_PREF));
		GeneratorCommand cmd = GeneratorCommand.parse(PreferenceLoader.loadValue(store, Constants.ST_GOAL_PREF));
		if (cmd != null) {
			st_cmbGoal.select(cmd.getIndex());
		} else {
			st_cmbGoal.select(GeneratorCommand.COMPLETE.getIndex());
		}

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
