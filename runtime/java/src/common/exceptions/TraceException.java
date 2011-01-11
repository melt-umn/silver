package common.exceptions;

/**
 * Trace exceptions are those that just note an error occurred while doing something else.
 * <p>
 * This is the meat of the "stack trace" you get when silver dies.
 * 
 * @author tedinski
 */
@SuppressWarnings("serial")
public class TraceException extends SilverException {

	public TraceException(String s, Throwable t) {
		super(s, t);
	}
	
}
