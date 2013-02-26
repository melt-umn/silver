package edu.umn.cs.melt.ide.copper;

public class EnhancedParseTreeNodeBase implements IEnhancedParseTreeNode {
	
	protected int symbol;
	
	protected int start;
	
	protected int end;
	
	public EnhancedParseTreeNodeBase(int symbol, int start, int end) {
		this.symbol = symbol;
		this.start = start;
		this.end = end;
	}

	@Override
	public int getEnd() {
		return end;
	}

	@Override
	public int getStart() {
		return start;
	}

	@Override
	public int getSymbol() {
		return symbol;
	}
	
	public String toString(){
		return symbol + "<" + start + "/" + end + ">";
	}
}
