package edu.umn.cs.melt.ide.wizard;

import ide.NIdeProperty;
import ide.PmakeIdeProperty;

import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;

import common.ConsCell;

import edu.umn.cs.melt.ide.impl.SVRegistry;
import edu.umn.cs.melt.ide.silver.property.Property;
import edu.umn.cs.melt.ide.silver.property.ui.IPropertyControlsProvider;
import edu.umn.cs.melt.ide.silver.property.ui.PropertyControl;

public class NewSourceFilePage extends WizardPage {

	/**
	 * The project or folder under which this file is to be added.
	 */
	private IResource parentResource;

	/**
	 * The panel displaying all the controls
	 */
	private Composite panel;
	
	/** 
	 * This control contains path relative to workspace root, without the leading '/'.
	 * That is, the first segment is the name of project.
	 */
	private Text parentFolderText;
	
	private Text fileNameText;

	private StructuredViewer tv;
	
	private IPropertyControlsProvider provider;
	
	private IStructuredSelection initialSelection;
	
	/**
	 * Controls used to input user-defined properties
	 */
	private List<PropertyControl> propertyControls;

	public NewSourceFilePage(String pageName,
			IStructuredSelection selection) {
		super(pageName);
		provider = SVRegistry.get().getNewFileProperties();
		initialSelection = selection;
	}

	@Override
	public void createControl(Composite parent) {
		// Prepare some layouts to be used in this page
		GridLayout gdLayoutC1 = new GridLayout();
		gdLayoutC1.numColumns = 1;

		GridLayout gdLayoutC2 = new GridLayout();
		gdLayoutC2.numColumns = 2;

		FillLayout fillLayout = new FillLayout();

		// Assemble the page
		panel = new Composite(parent, SWT.NONE);
		panel.setLayout(gdLayoutC1);
		panel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));

		// row 1: information about the text control below
		Label r1_label = new Label(panel, SWT.NONE);
		r1_label.setText("Enter or select the parent folder:");

		// row 2: the text control indicating which project this file is to be
		// added to
		parentFolderText = new Text(panel, SWT.BORDER);
		parentFolderText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		parentFolderText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});

		// row 3: a sub panel
		Composite sub_panel_projs = new Composite(panel, SWT.NONE);
		sub_panel_projs.setLayout(gdLayoutC1);
		sub_panel_projs.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));

		// A tree viewer for project list
		// Note it's put inside another panel (sub_panel_projs) for filling layout
		Composite panel_3 = new Composite(sub_panel_projs, SWT.NONE);
		panel_3.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));
		panel_3.setLayout(fillLayout);

		tv = new TreeViewer(panel_3, SWT.SINGLE | SWT.BORDER);
		tv.setLabelProvider(new ResourceLabelProvider());
		tv.setContentProvider(new ResourceTreeProvider());
		tv.setInput(ResourcesPlugin.getWorkspace().getRoot().getProjects());
		tv.addFilter(new ViewerFilter() {
			// Show only appropriate projects and folders
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof IProject) {
					IProject project = (IProject) element;
					if(hasValidNature(project)){
						return true;		
					}
				} else if (element instanceof IFolder) {
					return true;
				}

				return false;
			}
		});

		// Add selection change listener: update selected project
		tv.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				IResource resource = (IResource) sel.getFirstElement();
				NewSourceFilePage.this.parentResource = resource;
				updateParentFolderText();
			}
		});

		updateParentFolderText();

		// row 4: a sub panel
		Composite sub_panel_file = new Composite(panel, SWT.NONE);
		sub_panel_file.setLayout(gdLayoutC2);
		sub_panel_file.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));

		// row 4 (left): information about the text control below
		Label r2_label = new Label(sub_panel_file, SWT.NONE);
		r2_label.setText("File name:");

		// row 4 (right): the text control for the name of new file
		fileNameText = new Text(sub_panel_file, SWT.BORDER);
		fileNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		fileNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});

		// row 5+: a sub panel for customized property controls
		Composite sub_panel_properties = new Composite(panel, SWT.NONE);
		sub_panel_properties.setLayout(gdLayoutC2);
		sub_panel_properties.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));
		propertyControls = provider.getPropertyControls(sub_panel_properties);
		if(propertyControls!=null){
			for(PropertyControl control:propertyControls){
				Control info = control.getInfoControl();
				Control input = control.getInputControl();
				info.setLayoutData(new GridData());
				input.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

				if(input instanceof Text){
					Text text = (Text) input;
					text.addModifyListener(new ModifyListener() {
						@Override
						public void modifyText(ModifyEvent e) {
							updatePageComplete();
						}
					});
				}
			}			
		}
		
		setControl(panel);
		setPageComplete(false);
		
        initialize();
	}

	/**
	 * Get destination parent project or folder
	 * @return either an IProject or an IFolder
	 */
	IResource getParentFolder(){
		return parentResource;
	}
	
	/**
	 * Get name of file to create
	 * @return
	 */
	String getFileName(){
		return fileNameText.getText();
	}
	
	/**
	 * Get properties in a [NIdeProperty]
	 */
	ConsCell getNIdePerpertyArray() {
		ConsCell list = ConsCell.nil;
		for(PropertyControl control: propertyControls){
			// this reverses the order, but that's okay I think
			Property prop = control.getProperty();
			NIdeProperty item = new PmakeIdeProperty(
				new common.StringCatter(prop.getName()),
				new common.StringCatter(prop.getType().name()),
				new common.StringCatter(prop.getSValue())
			);
			list = new ConsCell(item, list);
		}
		return list;
	}
	
	private static class ResourceTreeProvider extends ArrayContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof IProject) {
				IProject project = (IProject) parentElement;
				try {
					return project.members();
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}

			if (parentElement instanceof IFolder) {
				IFolder folder = (IFolder) parentElement;
				try {
					return folder.members();
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		public Object getParent(Object element) {
			if (element instanceof IFolder) {
				IFolder folder = (IFolder) element;
				return folder.getParent();
			}
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if (element instanceof IProject || element instanceof IFolder) {
				return true;
			}
			return false;
		}
	}

	private static class ResourceLabelProvider extends LabelProvider {

		private static final ImageDescriptor FLDR = 
			PluginBase.imageDescriptorFromPlugin(SVRegistry.get().pluginId(), "icons/fldr_obj.gif");

		private static Image FLDR_IMG;

		@Override
		public Image getImage(Object resource) {
			if (FLDR_IMG == null) {
				FLDR_IMG = FLDR.createImage();
			}
			return FLDR_IMG;
		}

		@Override
		public String getText(Object resource) {
			IResource res = (IResource) resource;
			return res.getName();
		}
	}

	private void updateParentFolderText() {
		if (parentResource != null) {
			if(parentResource instanceof IProject) {
				parentFolderText.setText(parentResource.getName());
			} else {
				parentFolderText.setText(
					parentResource.getProject().getName() + 
					Path.SEPARATOR + 
					parentResource.getProjectRelativePath());
			}
		}
	}

	private boolean hasTriedFinishing = false;
	
	boolean attemptCompletion() {
		hasTriedFinishing = true;
		return updatePageComplete();
	}

	/**
	 * Update the status of page completion
	 */
	private boolean updatePageComplete() {
		if(!hasTriedFinishing){
			setPageComplete(true);
			return true;
		}
		
		if (parentFolderText == null || fileNameText == null){
			setPageComplete(false);
			return false;
		}
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		
		String parentRelPath = parentFolderText.getText();
		if (parentRelPath == null || "".equals(parentRelPath)) {
			setPageComplete(false);
			setErrorMessage("the parent folder is not set");
			return false;
		}
		
		String parentAbsPath = root.getFullPath().append(parentRelPath).toPortableString();
		IPath parentResPath = Path.fromPortableString(parentAbsPath);
		
		if(isProject(parentRelPath)){
			// It's a project
			IProject[] projects = root.getProjects();
			boolean found = false;
			for(IProject proj : projects){
				if(proj.getName().equals(parentRelPath)){
					found = true;
					this.parentResource = proj;
					break;
				}
			}
			if(!found){
				setPageComplete(false);
				setErrorMessage("the project '" + parentResPath + "' doesn't exist");
				return false;
			} else if(!(hasValidNature((IProject) this.parentResource))){
				setPageComplete(false);
				setErrorMessage("the project '" + parentResPath + "' is not a " + SVRegistry.get().name() + " project");
				return false;
			}
		} else { // It's a folder
			IFolder folder = null;
			try {
				folder = root.getFolder(parentResPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(folder == null){
				setPageComplete(false);
				setErrorMessage("the parent folder '" + parentResPath + "' is invalid");
				return false;
			}
			if(!folder.exists()){
				setPageComplete(false);
				setErrorMessage("the parent folder '" + parentResPath + "' doesn't exist");
				return false;
			}
		}
		
		if (fileNameText.getText() == null || "".equals(fileNameText.getText())) {
			setPageComplete(false);
			setErrorMessage("the file name is not set");
			return false;
		}
		
		// Check user-defined properties
		if(!provider.validateAll()){
			setErrorMessage("Invalid properties");
			setPageComplete(false);
			return false;
		}

		setErrorMessage(null);
		setPageComplete(true);

		return true;
	}

	/**
	 * Determine whether the parent folder is an IDE project.
	 * @param parentRelPath
	 * @return
	 */
	private boolean isProject(String parentRelPath){
		int firstSplitter = parentRelPath.indexOf('/');
		if(firstSplitter==-1){
			return true;
		} else {
			char[] chars = parentRelPath.substring(firstSplitter).toCharArray();
			boolean hasOnlySeparater = true;
			for(char c : chars){
				if(c != Path.SEPARATOR) {
					hasOnlySeparater = false;
					break;
				}
			}
			return hasOnlySeparater;
		}
	}
	
	private boolean hasValidNature(IProject project){
		try {
			if (project.hasNature(SVRegistry.get().getNatureId())) {
				return true;
			}
		} catch (CoreException e) {
			// Internal problem. Ignore.
		}
		
		return false;
	}
	
	private void initialize() {
		if (initialSelection != null) {
			Object element = initialSelection.getFirstElement();

			if (element instanceof IProject) {
				parentResource = (IProject) element;
			} else if (element instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable) element;
				Object adapter = adaptable.getAdapter(IResource.class);
				parentResource = (IResource) adapter;
			}
		}

		if (parentResource != null) {
			updateParentFolderText();
		}
	}

}
