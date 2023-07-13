package common.exceptions;

/**
 * AttributeCycleException occur when we detect a cycle in attribute evaluation.
 * 
 * @author krame505
 */
@SuppressWarnings("serial")
public class AttributeCycleException extends SilverError {

	public AttributeCycleException(String s) {
		super(s);
	}
}
