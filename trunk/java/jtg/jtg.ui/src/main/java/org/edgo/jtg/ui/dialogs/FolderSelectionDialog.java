package org.edgo.jtg.ui.dialogs;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.NewFolderDialog;

public class FolderSelectionDialog extends ElementTreeSelectionDialog implements ISelectionChangedListener {
	private Button		fNewFolderButton;
	private IContainer	fSelectedContainer;

	public FolderSelectionDialog(Shell parent, ILabelProvider labelProvider, ITreeContentProvider contentProvider) {
		super(parent, labelProvider, contentProvider);
		setComparator(new ResourceComparator(ResourceComparator.NAME));
	}

	protected Control createDialogArea(Composite parent) {
		Composite result = (Composite) super.createDialogArea(parent);

		getTreeViewer().addSelectionChangedListener(this);

		Button button = new Button(result, 8);
		button.setText("Create &New Folder");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				FolderSelectionDialog.this.newFolderButtonPressed();
			}
		});
		button.setFont(parent.getFont());
		this.fNewFolderButton = button;

		applyDialogFont(result);

		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(parent, "org.eclipse.jdt.ui.bp_select_default_output_folder_dialog");

		return result;
	}

	private void updateNewFolderButtonState() {
		IStructuredSelection selection = (IStructuredSelection) getTreeViewer().getSelection();
		this.fSelectedContainer = null;
		if (selection.size() == 1) {
			Object first = selection.getFirstElement();
			if ((first instanceof IContainer)) {
				this.fSelectedContainer = ((IContainer) first);
			}
		}
		this.fNewFolderButton.setEnabled(this.fSelectedContainer != null);
	}

	protected void newFolderButtonPressed() {
		NewFolderDialog dialog = new NewFolderDialog(getShell(), this.fSelectedContainer);
		if (dialog.open() == 0) {
			TreeViewer treeViewer = getTreeViewer();
			treeViewer.refresh(this.fSelectedContainer);
			Object createdFolder = dialog.getResult()[0];
			treeViewer.reveal(createdFolder);
			treeViewer.setSelection(new StructuredSelection(createdFolder));
		}
	}

	public void selectionChanged(SelectionChangedEvent event) {
		updateNewFolderButtonState();
	}
}
