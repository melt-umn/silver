package common.exceptions;

/**
 * MissingDefinitionExceptions occur when we try to evaluate an attribute we don't have an equation for.
 * 
 * @author tedinski
 */
@SuppressWarnings("serial")
public class MissingDefinitionException extends SilverError {

	public MissingDefinitionException(String s) {
		super(s);
	}
}
