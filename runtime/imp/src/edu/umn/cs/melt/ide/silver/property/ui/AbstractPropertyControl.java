package edu.umn.cs.melt.ide.silver.property.ui;

import org.eclipse.swt.widgets.Composite;

abstract public class AbstractPropertyControl implements PropertyControl {

	protected Composite panel;
	protected String name;
	protected String defaultVal;
	protected String display;
	protected boolean isRequired;
	
	protected AbstractPropertyControl(
		Composite panel, String name, String display, String defaultVal, boolean isRequired){
		this.panel = panel;
		this.name = name;
		this.display = display;
		this.defaultVal = defaultVal;
		this.isRequired = isRequired;
	}
	
	protected AbstractPropertyControl(Composite panel, String name){
		this(panel, name, name, "", false);
	}

	/**
	 * Whether this field has been filled. A field that is not required is always
	 * considered filled.
	 * @param value from input component
	 */
	protected boolean isFilled(String value){
		if((value==null||"".equals(value)) && isRequired){
			return false;
		}
		
		return true;
	}
	
	public String getDefault(){
		return defaultVal;
	}
	
}
