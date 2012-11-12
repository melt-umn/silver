package edu.umn.cs.melt.ide.silver.commands;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MenuItem;

import edu.umn.cs.melt.ide.silver.util.StandardMenuLocator;

/**
 * Test if Quick Build should be enabled.
 * <p>
 * Basically, synchronize the command's enablement with that of standard "Build" command 
 * <p>
 * Implementation notice: method <code>test()</code> shall always be stateless.
 *  
 * @author Ming Zhou
 *
 */
public class QuickBuildCommandEnablementTester extends PropertyTester {

	public QuickBuildCommandEnablementTester() {

	}
	
	private MenuItem buildCommand;
	
	private boolean enabled;
	
	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		//if("isEnabled".equals(property)){
			enabled = false;
			
			if(buildCommand == null){
				buildCommand = StandardMenuLocator.getMenuItem(
					StandardMenuLocator.Category.PROJECT, 
					StandardMenuLocator.Command.BUILD_PROJECT);
			}

			if(buildCommand != null){
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						enabled = buildCommand.isEnabled();		
					}
				});	
			}
	    	
			return enabled;
		//}
		
		//return false;
	}

}
