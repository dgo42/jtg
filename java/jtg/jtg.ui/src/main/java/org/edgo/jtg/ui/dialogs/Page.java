package org.edgo.jtg.ui.dialogs;

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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class Page extends Composite {
	private Text txtSchemaDir;
	private Text txtTemplateDir;
	private Text txtSrcOutDir;
	private Text txtJarOutDir;
	private Text txtSchemaFile;
	private Text txtProjectFile;
	private Text txtStartTemplFile;
	private Text txtSchemaPackage;
	private Text txtTemplatePackage;
	private Button chkUsingCache;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Page(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Group grpDirectories = new Group(this, SWT.NONE);
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
			}
		});
		btnJarOutDirBrowse.setText("Browse...");
		
		Group grpFiles = new Group(this, SWT.NONE);
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
			}
		});
		btnStartTemplFileBrowse.setText("Browse...");
		
		Group grpPackages = new Group(this, SWT.NONE);
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
		
		Group grpGeneral = new Group(this, SWT.NONE);
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

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
