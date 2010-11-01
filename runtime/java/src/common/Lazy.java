package common;

/**
 * Lazy is the interface used to create thunks of various kinds.
 * 
 * <p>It's used by Thunk, RealThunk, and the various subclasses of Node in slightly
 * different ways. (The first two should be obvious, the third is simply that the
 * Lazys are stored separately from any association with a particular DecoratedNode.)
 * 
 * @author tedinski, bodin
 */
public interface Lazy {
	public Object eval(DecoratedNode context);
}
