package common;

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
	public T invoke(final Object[] args, final Object[] notApplicable) {
		DecoratedNode context = (DecoratedNode) Util.demand(args[0]);
		return (T)context.synthesized(index);
	}

	/**
	 * Identical to AttributeSection, but operates on an undecorated argument instead.
	 * 
	 * @author tedinski
	 */
	public static class Undecorated<T> extends NodeFactory<T> {

		private final int index;
		
		/**
		 * @param i  The index of the synthesized attribute to access.
		 */
		public Undecorated(final int i) {
			index = i;
		}
		
		@Override
		public T invoke(final Object[] args, final Object[] notApplicable) {
			DecoratedNode context = ((Node) Util.demand(args[0])).decorate(TopNode.singleton, (common.Lazy[])null);
			return (T)context.synthesized(index);
		}
		
	}
}
