package edu.umn.cs.melt.ide.silver.property.ui;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import edu.umn.cs.melt.ide.silver.property.Property;

public class PathPropertyControl implements PropertyControl {

	private Button info;
	private Text input;
	
	private Composite panel;
	private String name;
	
	public PathPropertyControl(Composite panel, String name){
		this.panel = panel;
		this.name = name;
	}

	@Override
	public Control getInfoControl() {
		if(info==null){
			info = new Button(panel, SWT.NONE);
			info.setText(name);
			info.setToolTipText("A local file system path");
			final Shell shell = panel.getShell();
			info.addSelectionListener(new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent e) {
				    DirectoryDialog dirDialog = new DirectoryDialog(shell);
				    dirDialog.setText("Select " + name);
				    input.setText(dirDialog.open());
				}
				
			});
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
		return Property.makePathProperty(name, input.getText());
	}

	@Override
	public String getKey() {
		return name;
	}
	
	@Override
	public void setValue(String value) {
		input.setText(value);
	}
	
	@Override
	public boolean validate() {
		String value = input.getText();
		if(value==null||"".equals(value)){
			reset();
			return true;
		}
		
		File f = new File(value);
		if(!f.exists()){
			input.setToolTipText("Path doesn't exist");
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

}
