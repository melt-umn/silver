package edu.umn.cs.melt.ide.copper;

public class CopperToken implements IToken {

	/**
	 * This is the COLORING id number. not a token number or anything like that.
	 * 
	 * e.g. keywords are 1, comments are 2, default 0. or something like that.
	 */
	protected int kind;
	
    protected int startOffset;

    protected int endOffset;

    public CopperToken(int kind, int startOffset, int endOffset) {
    	this.kind = kind;
		this.startOffset = startOffset;
		this.endOffset = endOffset;
	}
    
	@Override
	public int getEndOffset() {
		return endOffset;
	}

	@Override
	public int getStartOffset() {
		return startOffset;
	}

	@Override
	public int getKind() {
		return kind;
	}
}
