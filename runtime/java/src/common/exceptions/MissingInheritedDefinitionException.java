package common.exceptions;

/**
 * MissingInheritedDefinitionException are a specialization of MissingDefinitionException for missing inherited equations.
 * 
 * @author krame505
 */
@SuppressWarnings("serial")
public class MissingInheritedDefinitionException extends MissingDefinitionException {

	public MissingInheritedDefinitionException(String s) {
		super(s);
	}
}
