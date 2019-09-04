package common.exceptions;

/**
 * Variation on a trace exception that can show a representation of the partially-constructed AST
 * being reified.  
 * 
 * @author krame505
 */
@SuppressWarnings("serial")
public abstract class ReifyTraceException extends TraceException {
	public ReifyTraceException(String s, Throwable t) {
		super(s, t);
	}
	
	/**
	 * Constructs a String representing the AST constructed so far, indicating the position that
	 * caused the error, with irrelevant portions elided away.
	 * 
	 * @return The AST representation.
	 */
	protected abstract String getASTRepr();
	
	/**
	 * Constructs a String representing the AST constructed so far, indicating the position that
	 * caused the error, with irrelevant portions elided away.
	 * 
	 * @param t The exception for which to get the AST representation.
	 * @return The AST representation.
	 */
	public static final String getASTRepr(Throwable t) {
		if (t instanceof ReifyTraceException) {
			return ((ReifyTraceException)t).getASTRepr();
		} else {
			return "?";
		}
	}
}
