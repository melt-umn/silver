package common.exceptions;

/**
 * CycleException occurs when we detect a cycle in attribute evaluation.
 * 
 * @author krame505
 */
@SuppressWarnings("serial")
public class CycleException extends SilverError {

	public CycleException(String s) {
		super(s);
	}
}
