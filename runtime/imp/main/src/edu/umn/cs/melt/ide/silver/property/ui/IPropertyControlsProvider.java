package edu.umn.cs.melt.ide.silver.property.ui;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

/**
 * The generated PropertyPage is backed up by a class implementing
 * this interface.
 * <p>
 * First, PropertyPage can retrieve all the control widgets from this interface
 * by calling {@link #getPropertyControls(Composite)} and thus render them 
 * properly in property page.
 * <p>
 * Second, PropertyPage calls {@link #validateAll()} to perform a comprehensive 
 * input validation in the overridden {@link org.eclipse.ui.dialogs.
 * PropertyPage#performOk() PropertyPage.performOk()} method.
 */
public interface IPropertyControlsProvider {

	/**
	 * Get all the control widgets to render.
	 * @param panel
	 * @return a list containing all the {@link PropertyControl}s.
	 */
	List<PropertyControl> getPropertyControls(Composite panel);
	
	/**
	 * Perform a comprehensive validation over all the input widgets.
	 * @return true if successful.
	 */
	boolean validateAll();
	
}
