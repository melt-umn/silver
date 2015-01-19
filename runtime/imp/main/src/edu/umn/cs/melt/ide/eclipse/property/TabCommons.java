package edu.umn.cs.melt.ide.eclipse.property;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;

import edu.umn.cs.melt.ide.impl.SVRegistry;
import edu.umn.cs.melt.ide.silver.property.ProjectProperties;
import edu.umn.cs.melt.ide.silver.property.Property;
import edu.umn.cs.melt.ide.silver.property.ui.IPropertyControlsProvider;
import edu.umn.cs.melt.ide.silver.property.ui.PropertyControl;

public class TabCommons implements IPropertyPageTab {

	private IPropertyControlsProvider provider = SVRegistry.get().getProjectProperties();
	
	private List<PropertyControl> controls;
	
	private PropertyPage page;
	
	@Override
	public void fillInTabItem(Composite panel) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		panel.setLayout(layout);
		
	    ProjectProperties props = getProperties();
	    
		if(controls==null){
			controls = provider.getPropertyControls(panel);
			
			for(PropertyControl control:controls){
				Control info = control.getInfoControl();
				Control input = control.getInputControl();
				
				Property p = props.get(control.getKey());
				if(p!=null){
					control.setValue(p.getSValue());
				}
				
				info.setLayoutData(new GridData());
				input.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			}			
		}
		
	}

	@Override
	public boolean performOk(){
		if(!provider.validateAll()){
			return false;
		}
		
	    ProjectProperties props = getProperties();
	    
		if(controls!=null && props!=null){
			for(PropertyControl control:controls){
				Property p = control.getProperty();
				if(p!=null){
					props.set(p);					
				}
			}			
			
			IProject project = (IProject)page.getElement().getAdapter(IProject.class);
			try {
				project.refreshLocal(IResource.DEPTH_ONE, null);
			} catch (CoreException e) {
				//Ignore
			}
		}
		
		return true;
	}
	
	@Override
	public void performDefaults(){
	    ProjectProperties props = getProperties();
	    
		if(controls!=null && props!=null){
			for(PropertyControl control:controls){
				Property p = control.getProperty();
				if(p!=null){
					control.setValue(p.getDefault());					
				}
			}
		}
	}

	@Override
	public void setPropertyPage(PropertyPage page){
		this.page = page;
	}
	
	private ProjectProperties getProperties(){
		IProject project = (IProject)page.getElement().getAdapter(IProject.class);
	    if (project != null) {
		    return ProjectProperties.getPropertyPersister(project.getLocation().toString());
		}
		
	    return null;
	}

	@Override
	public String getName() {
		return "Common";
	}

}
