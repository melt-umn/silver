package edu.umn.cs.melt.ide.copper.editor;

import org.eclipse.imp.services.base.TreeModelBuilderBase;

import edu.umn.cs.melt.ide.copper.AdaptiveEnhancedParseTreeInnerNode;
import edu.umn.cs.melt.ide.copper.AdaptiveEnhancedParseTreeLeafNode;
import edu.umn.cs.melt.ide.copper.IEnhancedParseTreeNode;

/**
 * <p>
 * A generic outline view builder which can selectively convert AST nodes
 * and put them into a tree, which in turn is visualized in a pane by IMP. 
 * Whether a given AST node should be chosen as part of the tree, and, to
 * what form it should be converted if it's going to the tree, is up to 
 * the implementation of the child class that overrides two methods:
 * {@link #convertInnerNode(AdaptiveEnhancedParseTreeInnerNode)}, 
 * <br>and<br>
 * {@link #convertLeafNode(AdaptiveEnhancedParseTreeLeafNode)}, 
 * </p>
 * @author Ming Zhou
 *
 * @param <TI>
 * 		The class of tree node converted from an AST inner node
 * @param <TL>
 *  	The class of tree node converted from an AST leaf node
 */
abstract public class GenericTreeModelBuilder<TI, TL> extends TreeModelBuilderBase {
	
	@Override
	public void visitTree(Object root) {
		if (root == null) {
			return;
		}

		createTreeModel(root);
	}
	
	/**
	 * Create a tree model to visualize the structure of the program.
	 * 
	 * @param root
	 */
	private void createTreeModel(Object root){
		if(root instanceof AdaptiveEnhancedParseTreeInnerNode<?>){
			AdaptiveEnhancedParseTreeInnerNode<?> innerNode = 
				(AdaptiveEnhancedParseTreeInnerNode<?>) root;
			TI result = convertInnerNode(innerNode);
			if(result != null){
				pushSubItem(result);
				IEnhancedParseTreeNode[] children = innerNode.getChildren();
				for(int i=0;i<children.length;i++){
					createTreeModel(children[i]);
				}
				popSubItem();
			}
		} else if (root instanceof AdaptiveEnhancedParseTreeLeafNode<?>) {
			AdaptiveEnhancedParseTreeLeafNode<?> leafNode = 
				(AdaptiveEnhancedParseTreeLeafNode<?>) root;
			TL result = convertLeafNode(leafNode);
			if(result != null){
				pushSubItem(result);
				popSubItem();
			}
		} 
	}
	
	/**
	 * A language-dependent implementation converting an AST inner node 
	 * (non-terminal) to some other form to put into the outline view.
	 * <p>
	 * If null is returned, nothing will be put into the outline view.
	 * 
	 * @param innerNode
	 * @return the object to be put in outline view
	 */
	abstract protected TI convertInnerNode(AdaptiveEnhancedParseTreeInnerNode<?> innerNode);
	
	/**
	 * A language-dependent implementation converting an AST leaf node 
	 * (terminal) to some other form to put into the outline view.
	 * <p>
	 * If null is returned, nothing will be put into the outline view.
	 * 
	 * @param leafNode
	 * @return the object to be put in outline view
	 */
	abstract protected TL convertLeafNode(AdaptiveEnhancedParseTreeLeafNode<?> leafNode);

}

/*
//Originally suggested implementation using visitor pattern
private class ModelVisitor {

	@Override
	public void unimplementedVisitor(String s) {
	}

	public boolean visit(block n) {
		pushSubItem(n);
		return true;
	}

	public void endVisit(block n) {
		popSubItem();
	}

	public boolean visit(declarationStmt0 n) {
		createSubItem(n);
		return true;
	}

	public boolean visit(declarationStmt1 n) {
		createSubItem(n);
		return true;
	}

	public boolean visit(assignmentStmt n) {
		createSubItem(n);
		return true;
	}
}
*/
