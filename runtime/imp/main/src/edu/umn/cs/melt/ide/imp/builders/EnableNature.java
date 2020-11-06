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
 * Example configuration:
    <extension point="org.eclipse.ui.popupMenus">
      <objectContribution objectClass="org.eclipse.core.resources.IProject" adaptable="true" nameFilter="*" id="silver.composed.idetest.actions.projectmenu">
        <action
            label="Enable Silver Builder"
            tooltip="Enable the Silver builder for this project"
            id="silver.composed.idetest.actions.nature">
          <class class="edu.umn.cs.melt.ide.imp.builders.EnableNature">
            <parameter name="nature" value="silver.composed.idetest.nature" />
          </class>
        </action>
 *
 * Here we get `nature` passed as a hastable in `setInitializationData`.
 */
public class EnableNature implements IObjectActionDelegate, IExecutableExtension {

	private IProject project;
	private String natureID;
	
	@Override
	public void setInitializationData(IConfigurationElement config, String property,
			Object data) throws CoreException {
		if(!(data instanceof java.util.Hashtable))
			return;
		
		java.util.Hashtable<String, String> d = (java.util.Hashtable<String, String>)data;
		
		natureID = d.get("nature");
	}

	public EnableNature() {}
	
	/**
	 * General invocation process:
	 * 1. Construct
	 * 2. `setInitializationData`
	 * 3. unclear! `selectionChanged` must get called?
	 * 4. `run` gets called.
	 *
	 * Then we do this thing.
	 */
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


}
