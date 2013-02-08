package common;

/**
 * Lazy is the interface used to create function pointers, essentially.
 * 
 * <p>Typically, they are only used to supply
 * *static* arrays of certain kinds in nonterminal classes.  e.g.
 * "To evaluate this attribute on this nonterminal, use this function pointer."
 * 
 * @author tedinski, bodin
 */
public interface Lazy {
	// TODO: probably make this Lazy<T>...
	public Object eval(DecoratedNode context);
}
