package edu.umn.cs.melt.ide.util.cst;

import edu.umn.cs.melt.ide.util.cst.CSTVisitor;

public class CSTVisitingService {
	
	/**
	 * Make an object to accept some visitor.
	 * <p>
	 * This is not visitor pattern per se, although quite similar to it. By a
	 * canonical visitor pattern, the target object should have a method called
	 * accept to accommodate some visitor. The reason I did it this way is 
	 * that I chose to not touch Silver runtime code for the IDE service. If 
	 * necessary in future, we might change the definition of Node, DecoratedNode,
	 * and TerminalRecord (they are all from the Silver runtime) to make them
	 * implement some Visitor interface - that is, an accept() method.
	 * 
	 * @param obj	the legal type can only be {@link common.Node Node}, 
	 * {@link common.DecoratedNode DecoratedNode}, and 
	 * {@link common.TerminalRecord TerminalRecord}.
	 * @param visitor
	 * 
	 * @return	true if the visiting is aborted by the visitor, false a complete 
	 * traversal of the tree. Why it can be aborted by visitor is up to visitor's 
	 * semantics. For example, a node finder may abort the traversal if the 
	 * target is found.
	 */
	public static boolean accept(Object obj, CSTVisitor visitor){
		
		if(obj instanceof common.DecoratedNode){
			common.DecoratedNode dn = (common.DecoratedNode) obj;
			if(visitor.visitDecoratedNode(dn)){
				return true;//Abort
			} else {//Continue
				common.Node un = dn.undecorate();
				int i = un.getNumberOfChildren();
				for(int j=0;j<i;j++){
					boolean aborted = false;
					Object child = un.getChild(j);
					if(child instanceof common.TerminalRecord){
						aborted = accept(child, visitor);
					} else {//common.Node
						aborted = accept(dn.childDecorated(j), visitor);
					}
					
					if(aborted){//Abort
						return true;
					}
				}
			}
		} else if(obj instanceof common.Node){
			common.Node node = (common.Node) obj;
			if(visitor.visitNode(node)){
				return true;//Abort
			} else {//Continue
				int i = node.getNumberOfChildren();
				for(int j=0;j<i;j++){
					if(accept(node.getChild(j), visitor)){//Recursion
						return true;//Abort
					}
				}
			}
		} else if (obj instanceof common.TerminalRecord) {
			common.TerminalRecord term = (common.TerminalRecord) obj;
			return visitor.visitTerminal(term);
		} else {
			//Shouldn't be here
			System.err.println(
				"Visiting node of unrecognized class: " + obj.getClass().getName());
		}
		
		return false;
	}
	
}
