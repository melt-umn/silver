package common;

/**
 * A thunk that ensures an expression is evaluated once, and memoizes the result.
 * 
 * <p>Thunks represent suspended computations that may be demanded later on (or never.)
 * 
 * @author tedinski
 */
public abstract class Thunk<T> {
	
	protected T data;
	protected DecoratedNode context;
	
	/**
	 * Construct a thunk, to be evaluated in context.
	 * 
	 * @param ctx {@link DecoratedNode} to use as context for evaluating thunk
	 */
	public Thunk(final DecoratedNode ctx) {
		assert(ctx != null);
		context = ctx;
	}
	
	/**
	 * Either evaluates the closure to obtain a value, or returns the already evaluated result.
	 * 
	 * @return The value wrapped by this Thunk.
	 */
	public T eval() {
		if(context != null) {
			data = doEval();
			context = null;
		}
		return data;
	}
	
	/**
	 * Does the real evaluation of the Thunk.
	 */
	protected abstract T doEval();
	
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
		else if(/* instance of Thunk */ ((Thunk)t).context == null) // already evaluated!
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
		protected Object doEval() {
			return ((Lazy)data).eval(context);
		}
		
	}
	
	private static class TransformUndecorate extends Thunk<Object> {

		public TransformUndecorate(Thunk<DecoratedNode> t) {
			super(t.context); // We don't need a context, so use a dummy value.
			// Note that the check for context == null in transformUndecorate
			// becomes important here, because otherwise doEval won't be called!
			data = t;
		}

		@Override
		protected Object doEval() {
			return ((Thunk<DecoratedNode>)data).eval().undecorate();
		}
		
	}
	
}
