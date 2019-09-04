package edu.umn.cs.melt.ide.imp.builders;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Action performed via menu.
 * 
 * Gets its configuration via the info from plugin.xml, through setInitializationData
 */
public class EnableNature implements IObjectActionDelegate, IExecutableExtension {

	public EnableNature() {}
	
	private IProject project;
	private String natureID;
	
	@Override
	public void run(IAction arg0) {
		if(natureID == null) {
			System.out.println("odd, tried to add null nature from menu.");
			return;
		}
		if(project == null) {
			System.out.println("tried to add to null project?");
			return;
		}
		try {
			Nature.addToProject(project, natureID);
		} catch (CoreException e) {
			// TODO i dunno
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object first = ss.getFirstElement();

			if (first instanceof IProject) {
				project = (IProject) first;
			} else if (first instanceof IJavaProject) {
				project = ((IJavaProject) first).getProject();
			}
		}
	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String property,
			Object data) throws CoreException {
		if(!(data instanceof java.util.Hashtable))
			return;
		
        java.util.Hashtable<String, String> d = (java.util.Hashtable<String, String>)data;
        
        natureID = d.get("nature");
	}


}
