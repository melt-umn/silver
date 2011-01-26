package common;

/**
 * RealThunk behaves like an actual thunk, unlike <code>Thunk</code>.
 * 
 * This is currently only used for global values in Silver.
 * 
 * @author tedinski
 * @see Closure
 */
public final class RealThunk {
	// TODO: Maybe we should anon subclass thunk, rather than using thunk with a anon Lazy
	// TODO: genericize it?
	private DecoratedNode context;
	private Object data;
	
	public RealThunk(final DecoratedNode c, final Lazy l) {
		context = c;
		data = l;
	}
	
	public Object eval() {
		if(data instanceof Lazy) {
			data = ((Lazy)data).eval(context);
			context = null; // Forget it.
		}
		return data;
	}

}
