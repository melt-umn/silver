package common.exceptions;

/**
 * Reify trace exception for tracing an error caused by a list.  
 * 
 * @author krame505
 */
public class ConsReifyTraceException extends ReifyTraceException {
	public final boolean isHead;
	
	public ConsReifyTraceException(final boolean isHead, Throwable t) {
		super("While reifying " + (isHead? "head" : "tail") + " of list", t);
		this.isHead = isHead;
	}
	
	@Override
	protected final String getASTRepr() {
		if (isHead) {
			if (getCause() instanceof ConsReifyTraceException) {
				return "(" + getASTRepr(getCause()) + ") :: _";
			} else {
				return getASTRepr(getCause()) + " :: _";
			}
		} else {
			return "_ :: " + getASTRepr(getCause());
		}
	}
}
