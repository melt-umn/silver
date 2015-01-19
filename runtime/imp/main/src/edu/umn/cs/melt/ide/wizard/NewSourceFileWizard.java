package edu.umn.cs.melt.ide.wizard;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import common.ConsCell;

import edu.umn.cs.melt.ide.impl.SVRegistry;

import ide.NIdeProperty;

/**
 * The New Source File Wizard is used to create a source file, 
 * with extension name being given by the plugin, under a given project.
 * <br>
 * The user can also define properties when creating a new source file. The source file 
 * stub generator, defined in IDE declaration block, consults with these properties to 
 * determine how the stub file should be generated.
 */
public class NewSourceFileWizard extends Wizard implements INewWizard, IExecutableExtension {

	private String EXT = "." + SVRegistry.get().fileExtension();
	
	private NewSourceFilePage page1;
	
	public NewSourceFileWizard() {
		setWindowTitle(SVRegistry.get().name());
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {

	}
	
	@Override
	public void addPages() {
	    super.addPages();

	    ISelectionService service = 
	    	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
	    
	    IStructuredSelection selection = (IStructuredSelection) 
	    	service.getSelection("org.eclipse.ui.navigator.ProjectExplorer");

	    // Sometimes users may be (by mistake) using JDT, the perspective of which
	    // uses a package explorer (id=org.eclipse.jdt.ui.PackageExplorer), instead of project explorer.
	    // We won't handle this typical misuse here.

	    String name = SVRegistry.get().name();
	    
	    page1 = new NewSourceFilePage("New " + name + " Source File Wizard", selection);
	    page1.setTitle(name + " Source File");
	    page1.setDescription("Create new " + name + " source file");

	    addPage(page1);
	}

	// Add a new file to specified parent folder
	@Override
	public boolean performFinish() {
		if(!page1.attemptCompletion()){
			return false;
		}

		String name = page1.getFileName() + EXT;
		IResource parent = page1.getParentFolder();
		IFile file = null;
		if(parent instanceof IProject){
			file = ((IProject) parent).getFile(name);
		} else  {
			file = ((IFolder) parent).getFile(name);
		}
		
		if(!file.exists()){
			try {
				// Generate stub
				ConsCell properties = page1.getNIdePerpertyArray();
				String stub = SVRegistry.get().fileStub(properties).toString();
				
				// Create file
				file.create(new ByteArrayInputStream(stub.getBytes()), false, null);
			    
				// Open file
				IWorkbench workbench = PlatformUI.getWorkbench();
				IEditorDescriptor desc = workbench.getEditorRegistry().getDefaultEditor(file.getName());
				IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
				page.openEditor(new FileEditorInput(file), desc.getId());
				
				return true;
			} catch (CoreException e) {
				page1.setErrorMessage(e.getMessage());
				e.printStackTrace();
			}
		} else {
			page1.setErrorMessage("A source file with same name exists already.");
		}

	    return false;
	}
	
	@Override
	public void setInitializationData(
	    IConfigurationElement config, String propertyName, Object data) throws CoreException {
		
	}

}

