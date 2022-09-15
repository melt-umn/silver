package common.exceptions;

/**
 * SilverInternalErrors are errors that shouldn't have happened, and aren't the user's fault.
 * 
 * @author tedinski
 */
@SuppressWarnings("serial")
public class SilverInternalError extends SilverException {

	public SilverInternalError(String s) {
		super(s);
	}

	public SilverInternalError(String s, Throwable t) {
		super(s, t);
	}

}
