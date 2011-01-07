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
		
		if(getSynthesized(0) == null) {
			throw new RuntimeException("Function " + getName() + " has no return value!");
		}
		
		return this.decorate().synthesized(0);
	}

	@Override
	public final Lazy getForward() {
		throw new RuntimeException("Functions do not forward!");
	}

	@Override
	public final Lazy getForwardInh(final int index) {
		throw new RuntimeException("Functions do not forward!");
	}

	@Override
	public final int getNumberOfInhAttrs() {
		return 0;
	}

	@Override
	public final int getNumberOfSynAttrs() {
		return 1;
	}
	
}
