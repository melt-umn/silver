package edu.umn.cs.melt.ide.eclipse;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

public class Perspective implements IPerspectiveFactory {

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
		
		//Problem
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		
		//Console
		bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		
		//Progress
		bottom.addView(IPageLayout.ID_PROGRESS_VIEW);
	
		//And a place-holder
		bottom.addPlaceholder("*");
	}

}
