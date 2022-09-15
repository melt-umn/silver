package common.exceptions;

/**
 * Reify trace exception for tracing an error caused by a list.  
 * 
 * @author krame505
 */
@SuppressWarnings("serial")
public class ConsReifyTraceException extends ReifyTraceException {
	public final boolean isHead;
	
	public ConsReifyTraceException(final boolean isHead, Throwable t) {
		super("While reifying " + (isHead? "head" : "tail") + " of list", t);
		this.isHead = isHead;
	}
	
	@Override
	protected final String getASTRepr() {
		String result = "[";
		ConsReifyTraceException current;
		try {
			for (current = this; !current.isHead; current = (ConsReifyTraceException)current.getCause()) {
				result += "_, ";
			}
		} catch (ClassCastException e) {
			// If we read the end of the ConsReifyTraceException chain without finding an element
			// with isHead set to true, then an internal error must have occured and we shouldn't
			// be reconstructing the AST representation in the first place.
			throw new SilverInternalError("Final cause of ConsReifyTraceException chain must be head, to reconstruct AST representation.");
		}
		result += getASTRepr(current.getCause()) + ", ...]";
		return result;
	}
}
