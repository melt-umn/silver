package common;

import core.NOriginInfo;


/**
 * Returns a function reference (a NodeFactory) that accesses the attribute
 * provided to the constructor.  Used to implement attribute sections (.pp)
 * 
 * e.g. (.pp) :: (String ::= Decorated Expr)
 * 
 * @param <T> The return type of the attribute access function
 * @author tedinski
 */
public class AttributeSection<T> extends NodeFactory<T> {

	private final int index;
	
	/**
	 * @param i  The index of the synthesized attribute to access.
	 */
	public AttributeSection(final int i) {
		index = i;
	}
	
	@Override
	public T invoke(final common.OriginContext originCtx, final Object[] args, final Object[] notApplicable) {
		DecoratedNode context = (DecoratedNode) Util.demand(args[0]);
		return (T)context.synthesized(index);
	}
	
	@Override
	public final FunctionTypeRep getType() {
		// TODO: I have no idea how to make this work
		throw new common.exceptions.SilverError("Runtime type checking of attribute sections is not supported");
	}

	/**
	 * Identical to AttributeSection, but operates on an undecorated argument instead.
	 * 
	 * @author tedinski
	 */
	public static class Undecorated<T> extends NodeFactory<T> {

		private final int index;
		private final DecoratedNode trueParent;
		
		/**
		 * @param i  The index of the synthesized attribute to access.
		 */
		public Undecorated(final int i) {
			index = i;
			trueParent = TopNode.singleton;
		}
		/**
		 * Strictly speaking, this shouldn't be necessary, however, it's preferrable to
		 * do this (providing the node that the attribute section (.attr) appears within)
		 * than to have the baffling error "inherited attributes not provided by <top>"
		 * show up. This *could* be fixed by well-definedness analysis. Someday.
		 *
		 * @param i  The index of the synthesized attribute to access.
		 * @param parent  The node that this attribute section is being created within.
		 */
		public Undecorated(final int i, final DecoratedNode parent) {
			index = i;
			trueParent = parent;
		}
		
		@Override
		public T invoke(final common.OriginContext originCtx, final Object[] args, final Object[] notApplicable) {
			DecoratedNode context = ((Node) Util.demand(args[0])).decorate(trueParent, (common.Lazy[])null);
			return (T)context.synthesized(index);
		}
		
		@Override
		public final FunctionTypeRep getType() {
			// TODO: I have no idea how to make this work
			throw new common.exceptions.SilverError("Runtime type checking of attribute sections is not supported");
		}
		
	}
}
