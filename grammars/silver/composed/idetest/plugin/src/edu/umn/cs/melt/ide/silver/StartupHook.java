package edu.umn.cs.melt.ide.silver;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;

import edu.umn.cs.melt.ide.silver.perspective.SilverPerspective;
import edu.umn.cs.melt.ide.silver.util.StandardMenuLocator;

public class StartupHook implements IStartup {

	@Override
	public void earlyStartup() {
		//addPerspectiveListeners();
		SilverAnalysisInvoker2.prepare();
	}

	public void addPerspectiveListeners(){
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				if (workbenchWindow != null) {
					workbenchWindow.addPerspectiveListener(
						new PerspectiveAdapter() {
							@Override
							public void perspectiveActivated(
								IWorkbenchPage page,
								IPerspectiveDescriptor perspectiveDescriptor) {
								//System.out.println("perspectiveActivated");
								if(SilverPerspective.PERSPECTIVE_ID.equals(perspectiveDescriptor.getId())){
									enableMenuItem(false);
								}
							}
							
							@Override
							public void perspectiveDeactivated(
								IWorkbenchPage page,
								IPerspectiveDescriptor perspectiveDescriptor) {
								//System.out.println("perspectiveDeactivated");
								if(SilverPerspective.PERSPECTIVE_ID.equals(perspectiveDescriptor.getId())){
									enableMenuItem(true);
								}
							}
							
							@Override
							public void perspectiveOpened(
								IWorkbenchPage page,
								IPerspectiveDescriptor perspectiveDescriptor) {
								//System.out.println("perspectiveOpened");
								if(SilverPerspective.PERSPECTIVE_ID.equals(perspectiveDescriptor.getId())){
									enableMenuItem(false);
								}
							}

							@Override
							public void perspectiveClosed(
								IWorkbenchPage page,
								IPerspectiveDescriptor perspectiveDescriptor) {
								//System.out.println("perspectiveClosed");
								if(SilverPerspective.PERSPECTIVE_ID.equals(perspectiveDescriptor.getId())){
									enableMenuItem(true);
								}
							}
							
//							@Override
//							public void perspectiveChanged(
//								IWorkbenchPage page,
//								IPerspectiveDescriptor perspectiveDescriptor, 
//								String changeId) {
//								System.out.println("perspectiveChanged");
//								if(SilverPerspective.PERSPECTIVE_ID.equals(perspectiveDescriptor.getId())){
//									enableMenuItem(true);
//								} else {
//									enableMenuItem(false);
//								}
//							}
						}
					);
					
					//Disable the menu item immediately if the default perspective is Silver
					IPerspectiveDescriptor pdescriptor = workbenchWindow.getActivePage().getPerspective();
					if(SilverPerspective.PERSPECTIVE_ID.equals(pdescriptor.getId())){
						enableMenuItem(false);
					}
				}
			}
		});

	}
	
	private MenuItem autobuildMenuItem = null;

	private void enableMenuItem(boolean isDeactivating){
		if(autobuildMenuItem==null){// || autobuildMenuItem.isDisposed()
			autobuildMenuItem = StandardMenuLocator.getMenuItem(
				StandardMenuLocator.Category.PROJECT, 
				StandardMenuLocator.Command.BUILD_AUTOMATICALLY);
		}
		
		if(autobuildMenuItem!=null){// || autobuildMenuItem.isDisposed()
			autobuildMenuItem.setEnabled(isDeactivating);
			
			if(!isDeactivating){
				//save original state
				prevState = autobuildMenuItem.getSelection();
				autobuildMenuItem.setSelection(isDeactivating);
				enableAutoBuild(isDeactivating);
			} else {
				//restore to original state
				autobuildMenuItem.setSelection(prevState);
				enableAutoBuild(prevState);
			}

			//enableAutoBuild(enabled);
			
			/*
			try {
				IAction action = ((ActionContributionItem)autobuildMenuItem.getData()).getAction();
				//action.setChecked(enabled);
				
				if(!enabled){
					prevState = action.isChecked();
					action.setChecked(enabled);
				} else {
					action.setChecked(prevState);
				}
			} catch (ClassCastException e) {
				e.printStackTrace();
				// Report?
				// If data from menu item is not ActionContributionItem, could be problematic
			}
			*/
		}
	}
	
	private boolean prevState = false;
	
	private boolean enableAutoBuild(boolean enable) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceDescription desc = workspace.getDescription();
		boolean isAutoBuilding = desc.isAutoBuilding();
		if (isAutoBuilding != enable) {
			desc.setAutoBuilding(enable);
			try {
				workspace.setDescription(desc);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return isAutoBuilding;
	}
	
/*
	private void enableMenuItem(boolean enabled){
		ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		Command command = service.getCommand("org.eclipse.ui.project.buildAutomatically");
//		if(!enabled){
//			//Check out command if disabled
//			State state = command.getState("org.eclipse.example.command.toggleState");
//			state.setValue(enabled);		
//		}
		command.setEnabled(enabled);
	}
*/
}
