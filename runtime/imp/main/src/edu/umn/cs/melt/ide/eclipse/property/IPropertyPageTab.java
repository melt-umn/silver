package edu.umn.cs.melt.ide.eclipse.property;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * This interface defines the set of behaviors required of a tabular item in 
 * the property page.
 * <p>
 * The IDE's property page is organized using a tab folder, which contains
 * a varying number of tab items. What items are contained depends on the 
 * contents of IDE delcaration block.
 * 
 * @see MultiTabPropertyPage the container of these tab items.
 */
public interface IPropertyPageTab {
	
	/**
	 * Get the user-visible name of this tab.
	 */
	String getName();

	/**
	 * Called when OK button is clicked.
	 * @return whether the action was successfully performed.
	 */
	boolean performOk();
	
	/**
	 * Called when Defaults button is clicked.
	 */
	void performDefaults();	
	
	/**
	 * Fill contents into the given tab item.
	 * @param item provided by container.
	 */
	void fillInTabItem(Composite composite);

	/**
	 * Used to inject the containter. The implementing class may ignore this 
     * method (dummy impl.) if it doesn't refer to the container.
	 * @param pp the property page containing this tab
	 */
	void setPropertyPage(PropertyPage pp);
	
}
