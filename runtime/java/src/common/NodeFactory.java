package common;

/**
 * NodeFactories are used when we make higher-order references to functions.
 * 
 * @author tedinski
 */
public interface NodeFactory<T extends Node> {
	public T construct(Object[] children);
}
