package common.exceptions;

/**
 * The root class of Silver exceptions. These are all unchecked.
 * 
 * <p> The whole hierarchy is documented here, for convenience:
 * 
 * <ul>
 * <li>SilverInternalError - errors that should never ever occur.
 * <li>SilverError - error that can occur due to silver code being wrong
 *   <ul>
 *   <li>MissingDefinitionException - demanded something the user didn't define
 *   <li>PatternMatchFailure - pattern matching failure (NOT YET IMPLEMENTED)
 *   </ul>
 * <li>TraceException - documentation about what triggered the error.
 *   <ul>
 *   <li>ThunkTraceException - ditto, but for thunks (NOT YET IMPLEMENTED)
 *   </ul>
 * </ul>
 * 
 * IO exceptions are not wrapped.
 * 
 * @author tedinski
 */
@SuppressWarnings("serial")
public class SilverException extends RuntimeException {

	public SilverException(String s) {
		super(s);
	}
	public SilverException(String s, Throwable t) {
		super(s,t);
	}
	
	public static Throwable getRootCause(Throwable t) {
		while(t.getCause() != null)
			t = t.getCause();
		return t;
	}
}
