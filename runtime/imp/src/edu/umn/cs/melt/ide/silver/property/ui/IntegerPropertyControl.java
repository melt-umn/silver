package edu.umn.cs.melt.ide.silver.property.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import edu.umn.cs.melt.ide.silver.property.Property;

public class IntegerPropertyControl implements PropertyControl {

	private Label info;
	private Text input;
	
	private Composite panel;
	private String name;
	
	public IntegerPropertyControl(Composite panel, String name){
		this.panel = panel;
		this.name = name;
	}

	@Override
	public Control getInfoControl() {
		if(info==null){
			info = new Label(panel, SWT.NONE);
			info.setText(name);
			info.setToolTipText("An integer");
		}
		return info;
	}

	@Override
	public Control getInputControl() {
		if(input==null){
			input = new Text(panel, SWT.BORDER);
		}
		return input;
	}

	@Override
	public Property getProperty() {
		//Always call this after validate()
		String value = input.getText();
		if(value==null||"".equals(value)){
			return null;
		}
		
		return Property.makeIntegerProperty(name, Integer.parseInt(input.getText()));
	}
	
	@Override
	public boolean validate() {
		String value = input.getText();
		if(value==null||"".equals(value)){
			reset();
			return true;
		}
		
		boolean valid = true;
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			valid = false;
		}
		
		if(!valid){
			input.setToolTipText("The value is not an integer.");
			input.setBackground(panel.getDisplay().getSystemColor(SWT.COLOR_RED));
			return false;
		}
		
		reset();
		return true;
	}

	private void reset() {
		input.setToolTipText(null);
		input.setBackground(panel.getDisplay().getSystemColor(SWT.COLOR_WHITE));
	}

	@Override
	public String getKey() {
		return name;
	}

	@Override
	public void setValue(String value) {
		input.setText(value);
	}

}
