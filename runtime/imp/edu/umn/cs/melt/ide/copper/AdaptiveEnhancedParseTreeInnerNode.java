package edu.umn.cs.melt.ide.copper;


public class AdaptiveEnhancedParseTreeInnerNode<T> extends EnhancedParseTreeInnerNode {

	protected T langSpecNode;
	
	public AdaptiveEnhancedParseTreeInnerNode(IEnhancedParseTreeNode[] children,
			int production, int symbol, int start, int end, T langSpecNode) {
		super(children, production, symbol, start, end);
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
		
		if(children.length>0){
			sb.append("(");
			int last = children.length - 1;
			for(int i = 0; i<=last; i++){
				sb.append(children[i].toString());
				if(i!=last){
					sb.append(",");
				}
			}
			sb.append(")");
		}
		
		return sb.toString();
	}
	
}
