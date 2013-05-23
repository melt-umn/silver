package edu.umn.cs.melt.ide.silver.property.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import edu.umn.cs.melt.ide.silver.property.Property;

public class TextPropertyControl implements PropertyControl {

	private Label info;
	private Text input;
	
	private Composite panel;
	private String name;
	
	public TextPropertyControl(Composite panel, String name){
		this.panel = panel;
		this.name = name;
	}

	@Override
	public Control getInfoControl() {
		if(info==null){
			info = new Label(panel, SWT.NONE);
			info.setText(name);
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
		return Property.makeStringProperty(name, input.getText());
	}

	@Override
	public boolean validate() {
		return true;
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
