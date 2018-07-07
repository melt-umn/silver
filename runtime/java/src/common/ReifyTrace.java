package common;

/**
 * Representation of a position within the partially-constructed AST being reified, for error-
 * checking purposes.    
 * 
 * @author krame505
 */
public abstract class ReifyTrace {
	/**
	 * The trace to the parent of this position in the AST.
	 */
	protected final ReifyTrace parent;
	
	/**
	 * Construct a reify trace for a position in the AST being reified.
	 * 
	 * @param parent The trace representing the parent of the position being reified, or null if
	 * this is the outermost position.
	 */
	public ReifyTrace(final ReifyTrace parent) {
		this.parent = parent;
	}
	
	/**
	 * Constructs a String representing the AST constructed so far, indicating the position that
	 * caused the error, with irrelevant portions elided away.
	 * 
	 * @return The AST representation.
	 */
	public final String toString() {
		return toString("?");
	}
	
	/**
	 * Constructs a String representing the AST constructed so far, indicating the position that
	 * caused the error, with irrelevant portions elided away.
	 * 
	 * @param childToString the toString representation of the wrapped child to display.
	 * @return The AST representation.
	 */
	protected abstract String toString(final String childToString);
}
