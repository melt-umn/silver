package common;

/**
 * A thunk that ensures an expression is evaluated once, and memoizes the result.
 * 
 * <p>Thunks represent suspended computations that may be demanded later on (or never.)
 * 
 * @author tedinski
 */
public abstract class Thunk<T> {
	
	// Explicitly no visibility modifer here. We want the subclasses below to see this, but nothing outside.
	T data;
	DecoratedNode ctx;
	
	/**
	 * Construct a thunk, to be evaluated in context.
	 * 
	 * @param ctx {@link DecoratedNode} to use as context for evaluating thunk
	 */
	public Thunk(final DecoratedNode ctx) {
		assert(ctx != null);
		this.ctx = ctx;
	}
	
	/**
	 * Either evaluates the closure to obtain a value, or returns the already evaluated result.
	 * 
	 * @return The value wrapped by this Thunk.
	 */
	public T eval() {
		if(ctx != null) {
			data = doEval(ctx);

			assert(data != null);

			ctx = null;
		}
		return data;
	}
	
	/**
	 * Does the real evaluation of the Thunk.
	 */
	protected abstract T doEval(final DecoratedNode context);
	// N.B. We must explicitly pass 'context' into doEval, instead of allowing it to use
	// our member variable 'ctx' as a context, because we mutate 'ctx'.
	// You'd *THINK* Java variable capture rules would mean this doesn't matter, but
	// apparently everything is happily capturing 'this' as a final variable and then
	// we run afoul of 'this.context' suddenly becoming null!
	
	/**
	 * Constructs a Thunk from a Lazy and a DecoratedNode context.
	 */
	public static Thunk<Object> fromLazy(Lazy l, DecoratedNode ctx) {
		return new FromLazy(l,ctx);
	}
	
	/**
	 * Take a Thunk evaluating to a DecoratedNode, and undecorates it.
	 * 
	 * @param t  Either a DecoratedNode or a Thunk<DecoratedNode>
	 * @return  Either a Node or a Thunk<Node>
	 */
	public static Object transformUndecorate(Object t) {
		if(t instanceof DecoratedNode)
			return ((DecoratedNode)t).undecorate();
		else if(/* instance of Thunk */ ((Thunk)t).ctx == null) // already evaluated!
			return ((DecoratedNode)((Thunk)t).data).undecorate();
		// This second check is important, otherwise doEval needs modifying for this class...
		
		return new TransformUndecorate((Thunk<DecoratedNode>)t);
	}
	
	private static class FromLazy extends Thunk<Object> {

		public FromLazy(Lazy l, DecoratedNode ctx) {
			super(ctx);
			data = l;
		}
		
		@Override
		protected Object doEval(final DecoratedNode context) {
			return ((Lazy)data).eval(ctx);
		}
		
	}
	
	private static class TransformUndecorate extends Thunk<Object> {

		public TransformUndecorate(Thunk<DecoratedNode> t) {
			super(t.ctx); // We don't need a context, so use a dummy value.
			// Note that the check for context == null in transformUndecorate
			// becomes important here, because otherwise doEval won't be called!
			data = t;
		}

		@Override
		protected Object doEval(final DecoratedNode context) {
			return ((Thunk<DecoratedNode>)data).eval().undecorate();
		}
		
	}
	
}
