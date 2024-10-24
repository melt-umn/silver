package common;

import common.exceptions.CycleException;
import common.exceptions.CycleTraceException;

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

	// Used to check if we are about to demand a thunk that we are currently evaluating
	private int demanded = 0;

	public Thunk(final Evaluable<T> e) {
		assert(e != null);
		o = e;
	}
	
	@SuppressWarnings("unchecked")
	public T eval() {
		if(o instanceof Evaluable) {
			doEval();
		}
		return (T)o;
	}
	@SuppressWarnings("unchecked")
	private void doEval() {
		demanded++;
		if(demanded > 1) {
			handleCycleError();
		}
		try {
			o = ((Evaluable<T>)o).eval();
		} catch(Throwable t) {
			traceCycleError(t);
		}
		assert(o != null);
	}
	private void handleCycleError() {
		throw new CycleException("Cycle detected in execution");
	}
	private void traceCycleError(Throwable t) {
		// Reset the demanded count while unwinding the stack,
		// to avoid superfluous cycle errors if the error is caught and this
		// thunk is later demanded again.
		demanded--;
		// If we caught a cycle, report the start of it here.
		// Otherwise just re-throw the exception.
		if (demanded > 0) {
			demanded = 0;
			throw new CycleTraceException("Cycle begins here", t);
		} else if (t instanceof RuntimeException) {
			throw (RuntimeException)t;
		} else {
			throw new RuntimeException(t);
		}
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
	@SuppressWarnings("unchecked")
	public static Object transformUndecorate(final Object t, final OriginContext ctx) {
		// DecoratedNode
		if(t instanceof DecoratedNode)
			return ((Tracked)((DecoratedNode)t).undecorate()).duplicate(ctx);
		// Thunk
		return transformUndecorateThunk((Thunk<DecoratedNode>)t, ctx);
	}
	private static Object transformUndecorateThunk(final Thunk<DecoratedNode> t, final OriginContext ctx) {
		// Unevaluated Thunk
		if(t.o instanceof Evaluable)
			return new Thunk<Object>(() -> ((Tracked)t.eval().undecorate()).duplicate(ctx));
		// Evaluated Thunk, eagerly undecorate:
		return ((Tracked)t.eval().undecorate()).duplicate(ctx);
	}
}
