package common.exceptions;

/**
 * Thunk trace exceptions are those that just note an error occurred while evaluating some thunk.
 * 
 * @author tedinski
 */@SuppressWarnings("serial")
public class ThunkTraceException extends TraceException {

	public ThunkTraceException(String s, Throwable t) {
		super(s, t);
	}

}
