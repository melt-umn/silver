package edu.umn.cs.melt.ide.silver.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import silver.composed.idetest.imp.SILVERPlugin;

public class SilverPerspective implements IPerspectiveFactory {

	public static final String PERSPECTIVE_ID = SILVERPlugin.kPluginID + ".perspective";
	
	/**
	 * The Silver's development perspective has the following layout:
	 * <p>
	 * <code>
	 * ____________________________________  	<br>
	 * |Projec|---------------------------|  	<br>
	 * |t-Expl|---------------------------|  	<br> 
	 * |orer--|---------------------------|  	<br>
	 * |------|---------------------------|  	<br>
	 * |------|---------------------------| 	<br>
	 * |------|___________________________| 	<br>
	 * |------|Console|Progess|-----------|  	<br>
	 * |------|---------------------------|  	<br>
	 * |______|___________________________| 	<br>
	 * </code>
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		//Basic settings
		layout.setEditorAreaVisible(true);
		layout.setFixed(false); 
		
		//Get Editor Area
		String editorArea = layout.getEditorArea();
		
		//Project Explorer
		layout.addView(IPageLayout.ID_PROJECT_EXPLORER, IPageLayout.LEFT, 0.20f, editorArea);

		//Bottom Folder
		IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.66f, editorArea);
		
		//Console
		bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		
		//Progress
		bottom.addView(IPageLayout.ID_PROGRESS_VIEW);
		
		//Placeholder
		bottom.addPlaceholder("*");
	}

}
