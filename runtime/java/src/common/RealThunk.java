package common;

/**
 * RealThunk behaves like an actual thunk, unlike <code>Thunk</code>.
 * 
 * This is currently only used for global values in Silver.
 * 
 * @author tedinski
 * @see Thunk
 */
public final class RealThunk {
	private DecoratedNode context;
	private Object data;
	
	public RealThunk(DecoratedNode c, Lazy l) {
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
