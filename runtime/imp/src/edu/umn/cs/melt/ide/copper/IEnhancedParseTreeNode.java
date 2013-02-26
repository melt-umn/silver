package edu.umn.cs.melt.ide.copper;

public interface IEnhancedParseTreeNode {

	public final static int UNKNOWN = -1;
	
	int getSymbol();
	
	int getStart();
	
	int getEnd();
	
}
