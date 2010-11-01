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

	// TODO: make this abstract.
	public Object doReturn() {
		DecoratedNode dn = this.decorate();

		// TODO: Return values are cached.  They don't need to be.
		// We can maybe tell it to forget return values when we demand
		// this?
		return dn.synthesized("__return");
	}
}
