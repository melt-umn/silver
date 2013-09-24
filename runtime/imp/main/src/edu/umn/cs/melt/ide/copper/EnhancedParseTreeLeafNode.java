package edu.umn.cs.melt.ide.copper;

public class EnhancedParseTreeLeafNode extends EnhancedParseTreeNodeBase implements IEnhancedParseTreeLeafNode {
	
	protected String lexeme;
	
	public EnhancedParseTreeLeafNode(String lexeme, int symbol, int start, int end) {
		super(symbol, start, end);
		this.lexeme = lexeme;
	}

	@Override
	public String getLexeme() {
		return lexeme;
	}

}
