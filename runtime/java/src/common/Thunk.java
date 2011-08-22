package common;

/**
 * Wrapper around a closure, to ensure it is not evaluated more than once.
 * 
 * @author tedinski
 * @see Closure
 */
public class Thunk<T> {
	private Object data;
	
	/**
	 * Construct a (real) thunk, from either just a value, or a closure.
	 * 
	 * @param cls {@link Closure} or other value
	 */
	public Thunk(final Object cls) {
		data = cls;
	}
	
	/**
	 * Either evaluates the closure to obtain a value, or returns the already evaluated result.
	 * 
	 * @return The value wrapped by this thunk.
	 */
	public T eval() {
		if(data instanceof Closure) {
			data = ((Closure)data).eval();
		}
		return (T) data;
	}
}
