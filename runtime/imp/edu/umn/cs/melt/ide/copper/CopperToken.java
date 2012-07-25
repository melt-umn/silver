package edu.umn.cs.melt.ide.copper;

public class CopperToken implements IToken {

	protected int kind;
	
    protected int term;

    protected int startOffset;

    protected int endOffset;

    protected int tokenIndex;
    
    protected int startLine;
    
    protected int startColumn;
    
    protected int endLine;
    
    protected int endColumn;
	
    public CopperToken(int kind,
    	int term, int tokenIndex, int startOffset, int endOffset,
		int startLine, int endLine, int startColumn, int endColumn) {
    	this.kind = kind;
		this.term = term;
		this.startOffset = startOffset;
		this.endOffset = endOffset;
		this.tokenIndex = tokenIndex;
		this.startLine = startLine;
		this.endLine = endLine;
		this.startColumn = startColumn;
		this.endColumn = endColumn;
	}
    
    public CopperToken(int kind, int term, int tokenIndex, int offset, int line, int column) {
		this(kind, term, tokenIndex, offset, offset, line, line, column, column);
	}
	
	@Override
	public int getEndColumn() {
		return endColumn;
	}

	@Override
	public int getEndLine() {
		// TODO Auto-generated method stub
		return endLine;
	}

	@Override
	public int getEndOffset() {
		return endOffset;
	}

	@Override
	public int getStartColumn() {
		return startColumn;
	}

	@Override
	public int getStartLine() {
		return startLine;
	}

	@Override
	public int getStartOffset() {
		return startOffset;
	}

	@Override
	public int getTerm() {
		return term;
	}

	@Override
	public int getTokenIndex() {
		return tokenIndex;
	}

	@Override
	public int getKind() {
		return kind;
	}

}
