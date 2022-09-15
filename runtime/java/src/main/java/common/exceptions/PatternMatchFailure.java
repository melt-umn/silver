package common.exceptions;

/**
 * Thrown when pattern matching fails to find a match.
 * 
 * @author tedinski
 */
@SuppressWarnings("serial")
public class PatternMatchFailure extends SilverError {

	public PatternMatchFailure(String s) {
		super(s);
	}

}
