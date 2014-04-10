package org.edgo.jtg.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class JtgUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String	PLUGIN_ID	= "org.edgo.jtg.ui"; //$NON-NLS-1$

	// The shared instance
	private static JtgUIPlugin	plugin;

	/**
	 * The constructor
	 */
	public JtgUIPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		JtgUIPlugin.plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		JtgUIPlugin.plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static JtgUIPlugin getDefault() {
		return plugin;
	}

	public static void log(IStatus status) {
		if (getDefault() != null) {
			getDefault().getLog().log(status);
			return;
		}
		MessageConsole console = findConsole("JTK");
		console.activate();
		console.newMessageStream().println(status.toString());
	}

	private static MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName())) return (MessageConsole) existing[i];
		//no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] {myConsole});
		return myConsole;
	}

	public static String getUniqueIdentifier() {
		return PLUGIN_ID;
	}

	public static void logErrorMessage(String message) {
		log(new Status(Status.ERROR, getUniqueIdentifier(), 150, message, null));
	}

	public static void log(Throwable e) {
		if ((e instanceof CoreException))
			log(new Status(Status.ERROR, getUniqueIdentifier(), 4, e.getMessage(), e.getCause()));
		else
			log(new Status(Status.ERROR, getUniqueIdentifier(), 150, "Internal Error", e));
	}

	public static IWorkbenchWindow getActiveWorkbenchWindow() {
		return getDefault().getWorkbench().getActiveWorkbenchWindow();
	}

	public static IWorkbenchPage getActivePage() {
		IWorkbenchWindow w = getActiveWorkbenchWindow();
		if (w != null) {
			return w.getActivePage();
		}
		return null;
	}

	public static Shell getActiveWorkbenchShell() {
		IWorkbenchWindow window = getActiveWorkbenchWindow();
		if (window != null) {
			return window.getShell();
		}
		return null;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
