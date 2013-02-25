package edu.umn.cs.melt.ide.copper;

import java.util.Stack;

import edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData;

public class EnhancedParseTree<T extends IEnhancedParseTreeNode> {
//<INODE extends IEnhancedParseTreeInnerNode, LNODE extends IEnhancedParseTreeLeafNode> {

    private Stack<IEnhancedParseTreeNode> ptnStack;
    
    public EnhancedParseTree(){
    	init();
    }
    
    private void init(){
    	ptnStack = new Stack<IEnhancedParseTreeNode>();
    }
    
    public void clear() {
    	init();
    }
    
    /**
     * @return the root node of the tree.
     */
    public T getRoot(){
    	if(ptnStack.size()==1){
    		return getLowestNode();
    	} else {
    		return null;
    	}
    	
    }
    
    /**
     * Get the lowest node of the tree. 
     * If strict is true, will only work if the tree consists of one node (root).
     * <p>
     * For a tree
     * 		ROOT
     * 		  |
     * 		STMT
     * return ROOT if strict, STMT if not
     * @param strict
     * @return
     */
    @SuppressWarnings("unchecked")
	public T getLowestNode(){
    	if(ptnStack.size()>=1){
    		IEnhancedParseTreeNode top = ptnStack.peek();  		
        	if(top instanceof IEnhancedParseTreeInnerNode){
        		return (T)top;
        	}
    	}
    	
    	return null;
    }
    
    /**
     * Pop <i>prodLength</i> nodes from the stack, and create a node to push back.
     * After the action the size of stack is decreased by (<i>prodLength</i>-1).
     * @param builder
     * @param production
     * @param prodLength
     * @param term
     */
	public void reduce(ParseNodeBuilder<T> builder, int production, int prodLength, int term){
    	IEnhancedParseTreeNode[] children = new IEnhancedParseTreeNode[prodLength];
    	for(int i=prodLength-1;i>=0;i--){
    		children[i] = ptnStack.pop();
    	}
    	
    	IEnhancedParseTreeNode newNode = builder.buildNode(children, production, term);
    	ptnStack.push(newNode);
    }
    
    /**
     * Push a new node into the stack.
     * After the action the size of stack is increased by 1.
     * @param builder
     * @param production
     * @param prodLength
     * @param term
     */
	public void shift(ParseNodeBuilder<T> builder, SingleDFAMatchData scanResult, int start, int end){
		IEnhancedParseTreeNode newNode = builder.buildNode(scanResult, start, end);
    	ptnStack.push(newNode);
    }
	
}
