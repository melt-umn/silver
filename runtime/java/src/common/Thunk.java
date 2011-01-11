package common;

/**
 * Thunk is a fake thunk.
 * 
 * <p>It's used for the parameters of function / production calls.
 * 
 * <p>The reason it's fake is that we want to eliminate this object from memory once it's evaluated
 * and there's already a place to store the result of evaluation: the place where the reference
 * to this thunk is stored in Node!
 * 
 * @author tedinski
 * @see RealThunk
 */
public final class Thunk {
	private final DecoratedNode context;
	private final Lazy closure;
	
	public Thunk(final DecoratedNode con, final Lazy clo) {
		context = con;
		closure = clo;
		
		// STATS: Uncomment to enable statistics
		//Statistics.tSpawn(context.self.getClass());
	}
	
	// STATS: Uncomment to enable statistics
//	@Override
//	protected void finalize() throws Throwable {
//		Statistics.tFinal(context.self.getClass());
//	}

	
	public Object eval() {
		//System.err.println("TRACE: THUNK: " + context.name + " evaling closure: " + closure.getClass().getName());
		return closure.eval(context);
	}
}
