package edu.umn.cs.melt.ide.imp.builders;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.imp.runtime.IPluginLog;

/**
 * Generic nature for SV plugins.
 * 
 * Adds the builder it is told to via the extension declaration of that nature that uses this class.
 * 
 * Does essentially nothing else.
 */
public class Nature implements IProjectNature, IExecutableExtension {

    /**
     * Actually a fully generic function for adding a nature to a project.
     * Seems odd something like this doesn't exist already somewhere.
     * 
     * @param project  The project to affect.
     * @param natureID  The ID of the nature to add.
     */
    public static void addToProject(IProject project, String natureID) throws CoreException {
        IProjectDescription description = project.getDescription();
        String[] natures = description.getNatureIds();
        
        for(int i = 0; i < natures.length; i++) {
        	if(natures[i] == natureID) {
        		System.out.println("nature " + natureID + " already present.");
        		return;
        	}
        }
        
        String[] newNatures = new String[natures.length + 1];

        System.arraycopy(natures, 0, newNatures, 0, natures.length);
        newNatures[natures.length] = natureID;

        description.setNatureIds(newNatures);
        project.setDescription(description, null);
    }

	private IProject project;
	private String builderID;

	@Override
	public void configure() throws CoreException {
		// TODO: should probably log better somehow. this whole function.
		
		if(builderID == null) {
			System.out.println("no builderID configured for SV nature.");
			return; // welp,
		}
		
        IProjectDescription desc = getProject().getDescription();
        ICommand[] cmds = desc.getBuildSpec();

        for(int i = 0; i < cmds.length; i++) {
            if (cmds[i].getBuilderName().equals(builderID)) {
            	System.out.println("odd, the builder is already present.");
                return;
            }
        }

        ICommand buildCmd = desc.newCommand();
        buildCmd.setBuilderName(builderID);

        ICommand[] newCmds = new ICommand[cmds.length+1];

        System.arraycopy(cmds, 0, newCmds, 0, cmds.length);
        newCmds[cmds.length] = buildCmd;

        desc.setBuildSpec(newCmds);
        project.setDescription(desc, null);

	}

	@Override
	public void deconfigure() throws CoreException {
		// builder is automatically removed by the platform. do nothing
		
		// does require the builder to be configured as part of the nature
		// in plugin.xml though. but we do that, so.
	}

	@Override
	public IProject getProject() {
		return project;
	}
	@Override
	public void setProject(IProject arg0) {
		project = arg0;
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String property,
			Object data) throws CoreException {
		if(!(data instanceof java.util.Hashtable))
			return;
		
        java.util.Hashtable<String, String> d = (java.util.Hashtable<String, String>)data;
		
        builderID = d.get("builder");
        // TODO: you know, mayyybe this nature should actually know what natureID it has? no need yet though...
	}
}
