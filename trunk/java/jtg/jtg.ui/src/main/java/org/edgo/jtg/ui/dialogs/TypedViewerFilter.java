package org.edgo.jtg.ui.dialogs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class TypedViewerFilter extends ViewerFilter {
	private Class<?>[]	fAcceptedTypes;
	private Object[]	fRejectedElements;
	private Pattern		fFileMask;

	public TypedViewerFilter(Class<?>[] acceptedTypes) {
		this(acceptedTypes, null, null);
	}

	public TypedViewerFilter(Class<?>[] acceptedTypes, Object[] rejectedElements, Pattern fileMask) {
		Assert.isNotNull(acceptedTypes);
		this.fAcceptedTypes = acceptedTypes;
		this.fRejectedElements = rejectedElements;
		this.fFileMask = fileMask;
	}

	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (this.fRejectedElements != null) {
			for (int i = 0; i < this.fRejectedElements.length; i++) {
				if (element.equals(this.fRejectedElements[i])) {
					return false;
				}
			}
		}
		for (int i = 0; i < this.fAcceptedTypes.length; i++) {
			if (this.fAcceptedTypes[i].isInstance(element)) {
				if (IFile.class.isInstance(element)) {
					if (fFileMask != null) {
						IFile file = (IFile) element;
						Matcher matcher = fFileMask.matcher(file.getName());
						return matcher.find();
					} else {
						return true;
					}
/*				} else if (IFolder.class.isInstance(element)) {
					if (fFileMask != null) {
						try {
							return checkHierarhie((IFolder) element);
						} catch (CoreException e) {
							return false;
						} 
					}
					return true;*/
				} else {
					return true;
				}
			}
		}
		return false;
	}

/*	private boolean checkHierarhie(IFolder folder) throws CoreException {
		boolean result = false;
		IResource[] resources = folder.members();
		for (IResource resource : resources) {
			if (IFolder.class.isInstance(resource)) {
				result |= checkHierarhie((IFolder) resource);
			} else if (IFile.class.isInstance(resource)) {
				Matcher matcher = fFileMask.matcher(resource.getName());
				result |= matcher.find();
			}
		}
		return result;
	}*/
}
