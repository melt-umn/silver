package edu.umn.cs.melt.ide.imp.builders;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
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
import common.IOToken;
import common.Lazy;
import common.OriginContext;
import common.TopNode;

import silver.core.NIOVal;

import edu.umn.cs.melt.ide.silver.property.ProjectProperties;
import edu.umn.cs.melt.ide.util.ReflectedCall;

/**
 * Action performed via menu. Example configuration:
    <extension point="org.eclipse.ui.popupMenus">
      <objectContribution objectClass="org.eclipse.core.resources.IProject" adaptable="true" nameFilter="*" id="silver.compiler.composed.idetest.actions.projectmenu">
        <action
            label="Export as Silver target"
            tooltip="Export the project as Silver distributable"
            id="silver.compiler.composed.idetest.actions.export">
          <class class="edu.umn.cs.melt.ide.imp.builders.Exporter">
            <parameter name="name" value="Silver" />
            <parameter name="markerName" value="silver.compiler.composed.idetest.builder.problem" />
            <parameter name="silver_export" value="silver:compiler:composed:idetest:export" />
          </class>
        </action>
 * 
 * The export function should have Silver type `IOVal<[Message]> ::= IdeProject [IdeProperty] IO`
 */
public class Exporter implements IObjectActionDelegate, IExecutableExtension {

	private IProject project;
	
	private String name; // Language / Project name. e.g. "Silver"
	private ReflectedCall<NIOVal> sv_export;
	private String markerName;
	
	@Override
	public void setInitializationData(IConfigurationElement config, String property,
			Object data) throws CoreException {
		if(!(data instanceof java.util.Hashtable))
			return;
		
		java.util.Hashtable<String, String> d = (java.util.Hashtable<String, String>)data;

		name = d.get("name");
		markerName = d.get("markerName");
		sv_export = new ReflectedCall<NIOVal>(d.get("silver_export"), 3);
	}

	@Override
	public void run(IAction ignored) {
		if(project == null)
			return;
		
		final ProjectProperties properties =
				ProjectProperties.getPropertyPersister(project.getLocation().toString());
		
		Job job = new Job("Exporting " + name + " distributable") {
			
			@Override
			protected IStatus run(final IProgressMonitor monitor) {

				final NIOVal undecorated_export_result =
                                    sv_export.invoke(new Object[]{OriginContext.FFI_CONTEXT, project, properties.serializeToSilverType(), IOToken.singleton});
				final DecoratedNode export_result = undecorated_export_result.decorate();
				// demand evaluation of io actions
				export_result.synthesized(silver.core.Init.silver_core_io__ON__silver_core_IOVal);
				
				final ConsCell errors = (ConsCell)export_result.synthesized(silver.core.Init.silver_core_iovalue__ON__silver_core_IOVal);

				try {
					Builder.renderMessages(errors, project, markerName);
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
