package common.exceptions;

/**
 * SilverErrors are a cause of errors that are the user's fault. (e.g. head of nil)
 * 
 * @author tedinski
 */
@SuppressWarnings("serial")
public class SilverError extends SilverException {

	public SilverError(String s) {
		super(s);
	}
}
