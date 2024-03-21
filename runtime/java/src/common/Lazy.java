package common;

import silver.core.NLocation;

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
     * Get the source file location of the equation defining this Lazy.
     * Null if this Lazy does not correspond to an equation.
     * TODO: we should include this in stack traces.
     * 
     * @return The source location of the equation defining this Lazy, or null if it is not available.
     */
    public default NLocation getSourceLocation() {
        return null;
    }

    /**
     * Create a Lazy that evaluates in some context and ignores the supplied one.
     * 
     * @param context The context to evaluate in.
     * @return A Lazy that evaluates in the supplied context.
     */
    public default Lazy withContext(final DecoratedNode context) {
        return new Lazy() {
            @Override
            public Object eval(DecoratedNode ignoredNewContext) { return Lazy.this.eval(context); }

            @Override
            public NLocation getSourceLocation() { return Lazy.this.getSourceLocation(); }
        };
    }

    public static class Trap implements Lazy {
        String m;

        public Trap(String m) {
            this.m = m;
        }

        public Object eval(DecoratedNode context) {
            return Util.error(m);
        }

        public NLocation getSourceLocation() {
            return null;
        }
    }
}
