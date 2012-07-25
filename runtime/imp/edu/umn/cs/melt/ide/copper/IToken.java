package edu.umn.cs.melt.ide.copper;

public interface IToken {
	
	/**
	 * An integer representing the kind of this token. The interpretation of this property language-specific.
	 * @return
	 */
	public int getKind();
	
    public int getTerm();

    public int getStartOffset();

    public int getEndOffset();

    public int getTokenIndex();
    
    public int getStartLine();
    
    public int getStartColumn();
    
    public int getEndLine();
    
    public int getEndColumn();

    public String toString();
	
}
