package edu.umn.cs.melt.ide.copper;


public class AdaptiveEnhancedParseTreeLeafNode<T> extends EnhancedParseTreeLeafNode {

	protected T langSpecNode;
	
	public AdaptiveEnhancedParseTreeLeafNode(String lexeme, int symbol, int start, int end, T langSpecNode) {
		super(lexeme, symbol, start, end);
		this.langSpecNode = langSpecNode;
	}
	
	public T getLangSpecNode(){
		return langSpecNode;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		sb.append(super.toString());
		sb.append("]");
		
		return sb.toString();
	}
	
}
