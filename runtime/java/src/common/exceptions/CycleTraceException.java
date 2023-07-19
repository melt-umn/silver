package common.exceptions;

/**
 * Cycle trace exceptions introduce a note on where the cycle begins in the stack trace when a cycle is detected.
 * 
 * @author krame505
 */@SuppressWarnings("serial")
public class CycleTraceException extends ThunkTraceException {

	public CycleTraceException(String s, Throwable t) {
		super(s, t);
	}

}
