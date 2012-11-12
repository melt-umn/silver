package edu.umn.cs.melt.ide.silver.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BuildAction;

import silver.composed.idetest.imp.builders.SILVERBuilder;
import silver.composed.idetest.imp.builders.SILVERNature;

/**
 * Make quick build based on user's previous selection.
 * <p>
 * First try to build the project currently selected in project explorer; if
 * no project is selected, try to derive the project from the file currently
 * open on top of the editor area. In any case, the project must be of Silver
 * nature for the build to proceed.
 * <p>
 * The visibility and usability of Quick Build is subject to several factors.
 * <li>
 * Visibility: <br>
 * &nbsp;   Silver perspective: yes <br>
 * &nbsp;   Other perspective: no <br>
 * &nbsp;   (Controlled by plugin.xml, extension point="org.eclipse.ui.menus")
 * </li>
 * <li>
 * Usability: <br>
 * &nbsp;   Generally, usable (enabled) when Build command is enabled <br>
 * &nbsp;   {@link edu.umn.cs.melt.ide.silver.commands.
 * QuickBuildCommandEnablementTester#addPerspectiveListeners() 
 * QuickBuildCommandEnablementTester.addPerspectiveListeners()} 
 * - to synchronize the general usability with that of Build command, <br>
 * &nbsp;   {@link edu.umn.cs.melt.ide.silver.commands.
 * QuickBuildCommandState QuickBuildCommandState} <br>
 * &nbsp;   and {@link silver.composed.idetest.imp.builders.
 * SILVERBuilder#enableBuildCommands(boolean) 
 * SILVERBuilder.enableBuildCommands(boolean)}
 * - to disable when build is underway )
 * </li>
 * 
 * @author Ming Zhou
 */
public class QuickBuildCommand extends AbstractHandler {
	
	public static final String NAME = "Quick Build";

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		//Project to build (using an array due to other API's requirement)
		final IProject[] projects = new IProject[1];
		
		//Get workbench window
		final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	
		IWorkbenchPage activePage = window.getActivePage();
		
		if(activePage == null){
			//TODO: log here
			return null;
		}
		
		//Get what is currently selected in project explorer
		ISelection iSelection = activePage.getSelection(IPageLayout.ID_PROJECT_EXPLORER);
		if(iSelection instanceof IStructuredSelection){
			IStructuredSelection selection = (IStructuredSelection) iSelection;
			Object firstElement = selection.getFirstElement();
			
			//Build if the selected is a project
			if (firstElement != null) {
				if(firstElement instanceof IProject){
					projects[0] = (IProject) firstElement;
				} else if (firstElement instanceof IFile){
					projects[0] = (IProject) ((IFile)firstElement).getProject();
				} else if (firstElement instanceof IAdaptable) {
					projects[0] = (IProject) ((IAdaptable)firstElement).getAdapter(IProject.class);
				}
			}
		}
		
		//If nothing is selected in explorer, try to derive the project from the
		//file currently opened and on top of the editor.
		IEditorPart activeEditor = activePage.getActiveEditor();
		if("org.eclipse.imp.runtime.impEditor".equals(activeEditor.getSite().getId())){
			try {
				projects[0] = ((IFileEditorInput)activeEditor.getEditorInput()).getFile().getProject();
			} catch (ClassCastException e) {
				//TODO: log here
			}
		}
		
		if(projects[0] != null){
			//Check if the project is of Silver Nature
			boolean isSilverProject = false;
			try {
				isSilverProject = projects[0].hasNature(SILVERNature.k_natureID);
			} catch (CoreException e) {
				// Ignore?
			}
			if(!isSilverProject){
				//Return if it is NOT a Silver project
				return null;
			}
			
			//Create a new job
			WorkspaceJob quickBuildJob = new WorkspaceJob("Quick Build") {

				public boolean belongsTo(Object family) {
					return ResourcesPlugin.FAMILY_MANUAL_BUILD.equals(family);
				}

				public IStatus runInWorkspace(IProgressMonitor monitor)
						throws CoreException {
					QuickBuildAction projectBuild = 
						new QuickBuildAction(
							window,
						    IncrementalProjectBuilder.INCREMENTAL_BUILD,
						    projects);
					projectBuild.runInBackground(
						ResourcesPlugin.getWorkspace().getRuleFactory().buildRule());

					return Status.OK_STATUS;
				}
				
			};

			//Run the job
			quickBuildJob.setRule(ResourcesPlugin.getWorkspace().getRuleFactory().buildRule());
			quickBuildJob.setUser(true);
			quickBuildJob.schedule();					
		}

		
		return null;
	}

	private class QuickBuildAction extends BuildAction {
		private IProject[] projectsToBuild = new IProject[0];
		
		private int buildType;

		public QuickBuildAction(
			IShellProvider shellProvider, int type, IProject[] projects) {
			super(shellProvider, type);
			this.projectsToBuild = projects;
			this.buildType = type;
		}

		@SuppressWarnings("unchecked")
		protected List getSelectedResources() {
			return Arrays.asList(this.projectsToBuild);
		}
		
		@Override
	    protected void invokeOperation(
	    	IResource resource, IProgressMonitor monitor) throws CoreException {
	    	Map<String, String> args = new HashMap<String, String>();
	    	args.put(SILVERBuilder.IS_QUICK_BUILD, "true");
		    ((IProject) resource).build(buildType, SILVERBuilder.BUILDER_ID, args, monitor);
		}
	}

}
