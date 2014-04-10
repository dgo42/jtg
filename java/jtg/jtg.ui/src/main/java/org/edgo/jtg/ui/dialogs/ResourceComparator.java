package org.edgo.jtg.ui.dialogs;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class ResourceComparator extends ViewerComparator {
	public static final int	NAME	= 1;
	public static final int	TYPE	= 2;
	private int				criteria;

	public ResourceComparator(int criteria) {
		this.criteria = criteria;
	}

	protected int classComparison(Object element) {
		if ((element instanceof IResource)) {
			return 2;
		}
		return 0;
	}

	public int compare(Viewer viewer, Object o1, Object o2) {
		if ((!(o1 instanceof IResource)) || (!(o2 instanceof IResource))) {
			return compareClass(o1, o2);
		}
		IResource r1 = (IResource) o1;
		IResource r2 = (IResource) o2;

		if (((r1 instanceof IContainer)) && ((r2 instanceof IContainer))) return compareNames(r1, r2);
		if ((r1 instanceof IContainer)) return -1;
		if ((r2 instanceof IContainer)) return 1;
		if (this.criteria == 1) return compareNames(r1, r2);
		if (this.criteria == 2) {
			return compareTypes(r1, r2);
		}
		return 0;
	}

	protected int compareClass(Object element1, Object element2) {
		return classComparison(element1) - classComparison(element2);
	}

	@SuppressWarnings("unchecked")
	protected int compareNames(IResource resource1, IResource resource2) {
		return getComparator().compare(resource1.getName(), resource2.getName());
	}

	protected int compareTypes(IResource resource1, IResource resource2) {
		String ext1 = getExtensionFor(resource1);
		String ext2 = getExtensionFor(resource2);

		@SuppressWarnings("unchecked")
		int result = getComparator().compare(ext1, ext2);

		if (result != 0) {
			return result;
		}

		return compareNames(resource1, resource2);
	}

	public int getCriteria() {
		return this.criteria;
	}

	private String getExtensionFor(IResource resource) {
		String ext = resource.getFileExtension();
		return ext == null ? "" : ext;
	}
}
