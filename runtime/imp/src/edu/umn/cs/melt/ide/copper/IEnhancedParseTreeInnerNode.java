package edu.umn.cs.melt.ide.copper;

public interface IEnhancedParseTreeInnerNode extends IEnhancedParseTreeNode {

	IEnhancedParseTreeNode[] getChildren();
	
	int getProduction();
	
}
