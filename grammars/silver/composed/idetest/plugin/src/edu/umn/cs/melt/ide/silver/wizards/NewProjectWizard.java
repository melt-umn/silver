package edu.umn.cs.melt.ide.silver.wizards;

import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import silver.composed.idetest.imp.builders.SILVERNature;

/**
 * The Silver's New Project Wizard is used to create Silver project. In general
 * it follows these steps:
 * <p>
 * (1) show a page to get project's name <br>
 * (2) create a project folder with the given name in current workspace <br>
 * (3) add Silver nature to the project (note nature is associated with builder) <br>
 * (4) open Silver perspective 
 * 
 * @author Ming Zhou
 */
public class NewProjectWizard extends Wizard implements INewWizard, IExecutableExtension {

	private WizardNewProjectCreationPage page1;
	
	private IConfigurationElement configElement;
	
	public NewProjectWizard() {
		setWindowTitle("Silver");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {

	}
	
	@Override
	public void addPages() {
	    super.addPages();

	    page1 = new WizardNewProjectCreationPage("New Silver Project Wizard");
	    page1.setTitle("Silver Project");
	    page1.setDescription("Create new Silver project");

	    addPage(page1);
	}

	@Override
	public boolean performFinish() {
		//Get user's input
	    String name = page1.getProjectName();
	    URI location = null;
	    if (!page1.useDefaults()) {
	        location = page1.getLocationURI();
	    }
	    
	    //Create project in current workspace
	    createProject(name, location);
	    
	    //Update perspective
	    BasicNewProjectResourceWizard.updatePerspective(configElement);

	    return true;
	}
	
	@Override
	public void setInitializationData(
		IConfigurationElement config, String propertyName, Object data) throws CoreException {
	    configElement = config;
	}

    /**
     * Create a Silver project and add nature to it.
     *
     * @param location
     * @param projectName
     */
    private void createProject(String projectName, URI location) {
        //Get the project handle by given name
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

        if (!project.exists()) {
        	//Set the description
            URI projectLocation = location;
            IProjectDescription desc = 
            	project.getWorkspace().newProjectDescription(project.getName());
            if (location != null && 
            	location.equals(ResourcesPlugin.getWorkspace().getRoot().getLocationURI())) {
                projectLocation = null;
            }
            desc.setLocationURI(projectLocation);
            
            //Create and open the project
            try {
                project.create(desc, null);
                if (!project.isOpen()) {
                    project.open(null);
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
        
        //Add Silver nature, if not added yet
        try {
            if (!project.hasNature(SILVERNature.k_natureID)) {
	            /*
	            IProjectDescription description = project.getDescription();

	            //Replicate nature list and add Silver nature
	            String[] oldNatures = description.getNatureIds();
	            String[] newNatures = new String[oldNatures.length + 1];
	            int i=0;
	            for(;i<oldNatures.length;i++){
	            	newNatures[i] = oldNatures[i];
	            }
	            newNatures[i] = SILVERNature.k_natureID;
	            
	            //Set nature and description again
	            description.setNatureIds(newNatures);
	            project.setDescription(description, null);
	  	        */         
	            
	            new SILVERNature().addToProject(project);
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

}
