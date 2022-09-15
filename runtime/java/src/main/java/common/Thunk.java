package common;

/**
 * A thunk that ensures an expression is evaluated once, and memoizes the result.
 * 
 * <p>Thunks represent suspended computations that may be demanded later on (or never.)
 * 
 * @author tedinski
 */
public class Thunk<T> {
	
	// Instances should never escape this class
	public static interface Evaluable<T> {
		public T eval();
	}
	
	// Either T or Evaluable<T>
	private Object o;

	public Thunk(final Evaluable<T> e) {
		assert(e != null);
		o = e;
	}
	
	@SuppressWarnings("unchecked")
	public T eval() {
		if(o instanceof Evaluable) {
			o = ((Evaluable<T>)o).eval();
			assert(o != null);
		}
		return (T)o;
	}
	
	public static Thunk<Object> fromLazy(Lazy l, DecoratedNode ctx) {
		return new Thunk<Object>(() -> l.eval(ctx));
	}
	
	/**
	 * Take a Thunk evaluating to a DecoratedNode, and undecorates it.
	 * 
	 * @param t  Either a DecoratedNode or a Thunk<DecoratedNode>
	 * @return  Either a Node or a Thunk<Node>
	 */
	@SuppressWarnings("unchecked")
	public static Object transformUndecorate(final Object t) {
		// DecoratedNode
		if(t instanceof DecoratedNode)
			return ((DecoratedNode)t).undecorate();
		// Thunk
		return transformUndecorateThunk((Thunk<DecoratedNode>)t);
	}
	private static Object transformUndecorateThunk(final Thunk<DecoratedNode> t) {
		// Unevaluated Thunk
		if(t.o instanceof Evaluable)
			return new Thunk<Object>(() -> t.eval().undecorate());
		// Evaluated Thunk, eagerly undecorate:
		return t.eval().undecorate();
	}
}
