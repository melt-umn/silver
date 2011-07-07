package common;

/**
 * Closure is used to (lazily) pass arguments to functions/productions.
 * These aren't quite {@link Thunk}s, since we don't remember the result (here). 
 * 
 * @author tedinski
 * @see Thunk
 * @see FromLazy
 */
public abstract class Closure {
	protected final DecoratedNode context;
	
	protected Closure(final DecoratedNode con) {
		context = con;
	}
	
	/**
	 * Overriden by the generated code to implement the evaluation behavior, using the member variable
	 * {@link #context}.  This closure is not parameterized (i.e. it's a "fully-applied" function)
	 * 
	 * @return The result of evaluating this closure
	 */
	public abstract Object eval();
	
	/**
	 * For the case where we must construct a closure from a {@link Lazy}. Should be used as sparingly as
	 * possible! (Leads to a proliferation of small allocations, if used too much.)
	 * 
	 * @author tedinski
	 * @see Closure
	 * @see Thunk
	 */
	public static class FromLazy extends Closure {
		protected final Lazy fp;
		protected FromLazy(DecoratedNode con, Lazy l) {
			super(con);
			fp = l;
		}
		@Override
		public Object eval() {
			return fp.eval(context);
		}
	}
}
