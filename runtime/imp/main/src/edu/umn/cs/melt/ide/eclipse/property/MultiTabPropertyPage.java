package edu.umn.cs.melt.ide.eclipse.property;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.dialogs.PropertyPage;

import edu.umn.cs.melt.ide.impl.SVInterface;
import edu.umn.cs.melt.ide.impl.SVRegistry;

/**
 * project's property page
 * <p>
 * This page is organized using tab folder, each tab item under the folder 
 * backed by a class implementing {@link IPropertyPageTab}. As the containter
 * of these tab items, it's injected into IPropertyPageTab using 
 * {@link IPropertyPageTab#setPropertyPage(MultiTabPropertyPage) 
 * setPropertyPage(MultiTabPropertyPage)}. 
 * <p>
 * This class is generated based on what is defined in IDE declaration block.
 */
public class MultiTabPropertyPage extends PropertyPage {

	public MultiTabPropertyPage() {
		SVInterface sv = SVRegistry.get();
		tabs = sv.getPropertyTabs();
		names = new String[tabs.length];
		for(int i = 0; i < tabs.length; i++) {
			names[i] = tabs[i].getName();
		}
		tabNum = tabs.length;
	}
	
    private String[] names;
    private IPropertyPageTab[] tabs;
    private int tabNum;

    private TabFolder folder;

    @Override
    protected Control createContents(Composite parent) {

        //Assembling the page

        //1) The outermost container: a tab folder
        folder = new TabFolder(parent, SWT.NULL); 

        //2) Create tab items
        for(int i=0;i<tabNum;i++){
            TabItem item = new TabItem(folder, SWT.NULL);
            item.setText(names[i]);
            Composite panel = new Composite(folder, SWT.NONE);
            item.setControl(panel);
            tabs[i].setPropertyPage(this);
            tabs[i].fillInTabItem(panel);
        }

        return folder;
    }

    @Override
    public boolean performOk(){
        IPropertyPageTab tab = getSelectedTab();
        return tab!=null?tab.performOk():true;
    }

    @Override
    public void performDefaults(){
        IPropertyPageTab tab = getSelectedTab();
        if(tab!=null){
            tab.performDefaults();
        }
    }

    private IPropertyPageTab getSelectedTab(){
        int index = folder.getSelectionIndex();
        if(index>-1){
            return tabs[index];
        }

        return null;
    }

    IProject getProject(){
        return (IProject) getElement().getAdapter(IProject.class);
    }
}
