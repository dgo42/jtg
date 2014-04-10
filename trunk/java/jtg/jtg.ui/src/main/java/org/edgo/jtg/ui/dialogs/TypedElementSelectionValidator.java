package org.edgo.jtg.ui.dialogs;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

public class TypedElementSelectionValidator implements ISelectionStatusValidator {
	private IStatus			fgErrorStatus	= new StatusInfo(StatusInfo.ERROR, "");
	private IStatus			fgOKStatus		= new StatusInfo();
	private Class<?>[]		fAcceptedTypes;
	private boolean			fAllowMultipleSelection;
	private Collection<?>	fRejectedElements;
	private Pattern			fFileMask;

	public TypedElementSelectionValidator(Class<?>[] acceptedTypes, boolean allowMultipleSelection) {
		this(acceptedTypes, allowMultipleSelection, null, null);
	}

	public TypedElementSelectionValidator(Class<?>[] acceptedTypes, boolean allowMultipleSelection,
			Collection<?> rejectedElements, Pattern fileMask) {
		Assert.isNotNull(acceptedTypes);
		this.fAcceptedTypes = acceptedTypes;
		this.fAllowMultipleSelection = allowMultipleSelection;
		this.fRejectedElements = rejectedElements;
		this.fFileMask = fileMask;
	}

	public IStatus validate(Object[] elements) {
		if (isValid(elements)) {
			return this.fgOKStatus;
		}
		return this.fgErrorStatus;
	}

	private boolean isOfAcceptedType(Object o) {
		for (int i = 0; i < this.fAcceptedTypes.length; i++) {
			if (this.fAcceptedTypes[i].isInstance(o)) {
				return true;
			}
		}
		return false;
	}

	private boolean isRejectedElement(Object elem) {
		return (this.fRejectedElements != null) && (this.fRejectedElements.contains(elem));
	}

	protected boolean isSelectedValid(Object elem) {
		return true;
	}

	private boolean isValid(Object[] selection) {
		if (selection.length == 0) {
			return false;
		}

		if ((!this.fAllowMultipleSelection) && (selection.length != 1)) {
			return false;
		}

		for (int i = 0; i < selection.length; i++) {
			Object o = selection[i];
			if ((!isOfAcceptedType(o)) || (isRejectedElement(o)) || (!isSelectedValid(o))) {
				return false;
			}
			if (IFile.class.isInstance(o)) {
				if (fFileMask != null) {
					IFile file = (IFile) o;
					Matcher matcher = fFileMask.matcher(file.getName());
					if (!matcher.find()) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
