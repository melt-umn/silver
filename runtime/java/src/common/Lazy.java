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

    /**
     * Create a Lazy that evaluates in some context and ignores the supplied one.
     * 
     * @param context The context to evaluate in.
     * @return A Lazy that evaluates in the supplied context.
     */
    public default Lazy withContext(final DecoratedNode context) {
        return (ignoredNewContext) -> eval(context);
    }

    public static class Trap implements Lazy {
        String m;

        public Trap(String m) {
            this.m = m;
        }

        public Object eval(DecoratedNode context) {
            return Util.error(m);
        }
    }
}
