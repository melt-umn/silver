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
	 * Transforms this closure object into one that calls undecorate afterwards.
	 * 
	 * @param c The closure to operate on. *Possibly not a closure*
	 * @return A new Closure that performs the action.
	 */
	public static final Object transformUndecorate(Object c) {
		if(c instanceof Closure)
			return new UndecorateTransformer((Closure)c);
		return ((DecoratedNode)c).undecorate();
	}
	
	/**
	 * For the case where we must construct a closure from a {@link Lazy}. Should be used as sparingly as
	 * possible! (Leads to a proliferation of small allocations, if used too much.)
	 * 
	 * @author tedinski
	 * @see Closure
	 * @see Lazy
	 */
	public static final class FromLazy extends Closure {
		protected final Lazy fp;
		public FromLazy(DecoratedNode con, Lazy l) {
			super(con);
			fp = l;
		}
		@Override
		public Object eval() {
			return fp.eval(context);
		}
	}

	/**
	 * Construct a closure from a thunk.
	 * 
	 * @author tedinski
	 * @see Closure
	 * @see Thunk
	 */
	public static final class FromThunk extends Closure {
		protected final Thunk<?> th;
		public FromThunk(Thunk<?> t) {
			super(null);
			th = t;
		}
		@Override
		public Object eval() {
			return th.eval();
		}
	}
	
	private static final class UndecorateTransformer extends Closure {
		protected final Closure inner;

		protected UndecorateTransformer(Closure in) {
			super(null);
			inner = in;
		}

		@Override
		public Object eval() {
			return ((DecoratedNode)inner.eval()).undecorate();
		}
	}
}

