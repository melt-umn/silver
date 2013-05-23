package edu.umn.cs.melt.ide.silver.property.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import edu.umn.cs.melt.ide.silver.property.Property;

public class URLPropertyControl implements PropertyControl {

	private Label info;
	private Text input;
	
	private Composite panel;
	private String name;
	
	public URLPropertyControl(Composite panel, String name){
		this.panel = panel;
		this.name = name;
	}

	@Override
	public Control getInfoControl() {
		if(info==null){
			info = new Label(panel, SWT.NONE);
			info.setText(name);
			info.setToolTipText("A URL (http/s, ftp or file)");
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
		return Property.makeURLProperty(name, input.getText());
	}

	@Override
	public boolean validate() {
		String value = input.getText();
		if(value==null||"".equals(value)){
			reset();
			return true;
		}
		
		if(!match(value)){
			input.setToolTipText("The value is not a valid URL");
			input.setBackground(panel.getDisplay().getSystemColor(SWT.COLOR_RED));
			return false;
		}
		
		reset();
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
	
	private static Pattern URL_PATTERN = null;
	
	private boolean match(String value) {
		if(URL_PATTERN==null){
			URL_PATTERN = Pattern.compile(
			"^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
		}
		
		Matcher matcher = URL_PATTERN.matcher(value);
        return matcher.matches();
	}

	private void reset() {
		input.setToolTipText(null);
		input.setBackground(panel.getDisplay().getSystemColor(SWT.COLOR_WHITE));
	}

}
