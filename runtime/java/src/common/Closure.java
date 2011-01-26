package common;

/**
 * Closure is used to (lazily) pass arguments to functions/productions.
 * These aren't quite Thunks, since we don't remember the result. 
 * 
 * @author tedinski
 * @see RealThunk
 */
public abstract class Closure {
	protected final DecoratedNode context;
	
	protected Closure(final DecoratedNode con) {
		context = con;
	}
	
	public abstract Object eval();
}
