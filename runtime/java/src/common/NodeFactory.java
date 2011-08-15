package common;

/**
 * NodeFactories are used when we make higher-order references to functions.
 * To be replaced by MethodHandles, once we can use JDK7.
 * 
 * @author tedinski
 */
public abstract class NodeFactory<T> {
	/**
	 * Invoke a function or production.
	 * @return The return value (or node constructed.)
	 */
	public abstract T invoke(final Object[] args);
}
