package common;

/**
 * FunctionNode is a Node, but with a doReturn method.
 * 
 * @author tedinski
 * @see Node
 */
public abstract class FunctionNode extends Node {

	protected FunctionNode(Object[] children) {
		super(children);
	}

	// TODO: we should make doReturn an interface, perhaps?
	public Object doReturn() {
		
		if(getSynthesized("__return") == null) {
			throw new RuntimeException("Function " + getName() + " has no return value!");
		}
		
		DecoratedNode dn = this.decorate();
		return dn.synthesized("__return");
	}
}
