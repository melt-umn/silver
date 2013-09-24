package edu.umn.cs.melt.ide.silver.property.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import edu.umn.cs.melt.ide.silver.property.Property;

public class TextPropertyControl extends AbstractPropertyControl {

	private Label info;
	private Text input;
	
	public TextPropertyControl(Composite panel, String name){
		super(panel, name);
	}

	public TextPropertyControl(Composite panel, String name, 
		String display, String defaultVal, boolean isRequired){
		super(panel, name, display, defaultVal, isRequired);
	}
	
	@Override
	public Control getInfoControl() {
		if(info==null){
			info = new Label(panel, SWT.NONE);
			info.setText(display);
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
		return Property.makeStringProperty(
			name, input.getText(), defaultVal, display, isRequired);
	}

	@Override
	public boolean validate() {
		String value = input.getText();
		if(!isFilled(value)){
			input.setToolTipText("This field cannot be empty.");
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
