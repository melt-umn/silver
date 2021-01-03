package common;

import core.NOriginInfo;


/**
 * NodeFactories are used when we take references to Silver functions.
 * 
 * e.g. (T ::= ...) is NodeFactory<T>
 * 
 * @param <T>  The return type of the function
 * @author tedinski
 */
public abstract class NodeFactory<T> implements Typed {
	/**
	 * Invoke a function or production.
	 * @return The return value (or node constructed.)
	 */
	public abstract T invoke(final common.OriginContext originCtx, final Object[] args, final Object[] namedArgs);
	
	// Override with a more specific return type
	@Override
	public abstract FunctionTypeRep getType();
	
	// Below are just utilities
	
	/**
	 * Partial application of ordinary arguments.
	 * 
	 * @param indices  Ordered index list of values supplied here
	 * @param args  The values supplied here
	 * @see PartialNodeFactory#PartialNodeFactory(int[], Object[], NodeFactory)
	 */
	public final NodeFactory<T> invokePartial(final int[] indices, final Object[] args) {
		return new PartialNodeFactory<T>(indices, args, this);
	}
	/**
	 * Partial application of "named" arguments.
	 * 
	 * @param iConvertedToOrdered  index list of values moved to regular args
	 * @param iSuppliedHere  index list of values supplied here
	 * @param args  The values supplied here
	 * @see PartialNameNodeFactory#PartialNameNodeFactory(NodeFactory, int[], int[], Object[])
	 */
	public final NodeFactory<T> invokeNamedPartial(
			final int[] iConvertedToOrdered,
			final int[] iSuppliedHere,
			final Object[] args) {
		return new PartialNameNodeFactory<T>(this, iConvertedToOrdered, iSuppliedHere, args);
	}
}
