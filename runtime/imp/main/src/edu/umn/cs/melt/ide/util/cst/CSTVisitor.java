package edu.umn.cs.melt.ide.util.cst;

public interface CSTVisitor {

	/**
	 * Visit a Silver Node.
	 * 
	 * @param node
	 * @return true if the visitor is to abort the traversal.
	 */
	boolean visitNode(common.Node node);

	/**
	 * Visit a Silver Decorated Node.
	 * 
	 * @param node
	 * @return true if the visitor is to abort the traversal.
	 */
	boolean visitDecoratedNode(common.DecoratedNode dn);
	
	/**
	 * Visit a Silver Terminal.
	 * 
	 * @param node
	 * @return true if the visitor is to abort the traversal.
	 */
	boolean visitTerminal(common.TerminalRecord term);
	
}
