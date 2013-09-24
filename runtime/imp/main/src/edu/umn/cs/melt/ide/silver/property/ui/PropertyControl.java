package edu.umn.cs.melt.ide.silver.property.ui;

import org.eclipse.swt.widgets.Control;

import edu.umn.cs.melt.ide.silver.property.Property;

/**
 * The control widget set for a property.
 * <p>
 * Each PropetyControl consists of several {@link org.eclipse.swt.widgets.
 * Control Controls}, provides method for validation, and is backed up by a 
 * {@link edu.umn.cs.melt.ide.silver.property.Property Property}.
 */
public interface PropertyControl {

	/**
	 * Get the control widget displaying information about this field. 
	 * Typically a label or button.
	 * @return
	 */
	Control getInfoControl();
	
	/**
	 * Get the control widget for user to input values. Typically a text.
	 * @return
	 */
	Control getInputControl();
	
	/**
	 * Get the backing property
	 * @return
	 */
	Property getProperty();
	
	/**
	 * Get the key of property
	 * @return
	 */
	String getKey();
	
	/**
	 * Validate the input. This validation is context-agnostic. To perform 
	 * validation based on the input from other controls, you should modify
	 * validateAll() in the generated LANGPropertyPage class. 
	 * @return true if no error.
	 */
	boolean validate();

	/**
	 * Set value to this property.
	 * @param 
	 */
	void setValue(String value);
	
}
