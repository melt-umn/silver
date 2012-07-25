package edu.umn.cs.melt.ide.copper;

import edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData;

public interface ParseNodeBuilder<T extends IEnhancedParseTreeNode> {
//<I extends IEnhancedParseTreeInnerNode, L extends IEnhancedParseTreeLeafNode> {

	T buildNode(IEnhancedParseTreeNode[] children, int production, int term);
	
	T buildNode(SingleDFAMatchData scanResult, int start, int end);
	
}
