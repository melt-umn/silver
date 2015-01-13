package edu.umn.cs.melt.ide.imp.builders;

import ide.NIdeEnv;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import common.ConsCell;
import common.DecoratedNode;
import common.Lazy;
import common.TopNode;

import core.NIOVal;

import edu.umn.cs.melt.ide.impl.SVInterface;
import edu.umn.cs.melt.ide.impl.SVRegistry;
import edu.umn.cs.melt.ide.silver.property.ProjectProperties;

public class Exporter implements IObjectActionDelegate {

	private IProject project;
	
	@Override
	public void run(IAction ignored) {
		if(project == null)
			return;
		
		final SVInterface sv = SVRegistry.get();
		final ProjectProperties properties =
				ProjectProperties.getPropertyPersister(project.getLocation().toString());
		
		// TODO: likewise, this could be cached.
		final NIdeEnv env = Builder.computeIdeEnv(project);

		Job job = new Job("Exporting @LANG_NAME@ distributable") {
			
			@Override
			protected IStatus run(final IProgressMonitor monitor) {

				final NIOVal undecorated_export_result = sv.export(properties.serializeToSilverType(), env, null);
				final DecoratedNode export_result = undecorated_export_result.decorate(TopNode.singleton, (Lazy[])null);
				// demand evaluation of io actions
				export_result.synthesized(core.Init.core_io__ON__core_IOVal);
				
				final ConsCell errors = (ConsCell)export_result.synthesized(core.Init.core_iovalue__ON__core_IOVal);

				try {
					Builder.renderMessages(errors, project, sv);
				} catch (CoreException e) {
					// TODO who knows
					e.printStackTrace();
				}
				
				return Status.OK_STATUS;
			}
			
		};
		
		job.schedule(); 

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object first = ss.getFirstElement();

			if (first instanceof IProject) {
				project = (IProject) first;
			}
		}

	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
	}

}
