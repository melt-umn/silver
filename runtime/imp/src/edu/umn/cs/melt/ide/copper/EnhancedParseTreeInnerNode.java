package edu.umn.cs.melt.ide.copper;

public class EnhancedParseTreeInnerNode extends EnhancedParseTreeNodeBase implements IEnhancedParseTreeInnerNode {

	protected IEnhancedParseTreeNode[] children;
	
	protected int production;
	
	public EnhancedParseTreeInnerNode(IEnhancedParseTreeNode[] children,
			int production, int symbol, int start, int end) {
		super(symbol, start, end);
		this.children = children;
		this.production = production;
	}

	@Override
	public IEnhancedParseTreeNode[] getChildren() {
		return children;
	}

	@Override
	public int getProduction() {
		return production;
	}

}
