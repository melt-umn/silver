package common;

/**
 * Representation of a Silver function type, used for run-time type checking in reification.  
 * 
 * @author krame505
 */
public class FunctionTypeRep extends TypeRep {
	/**
	 * The number of parameters to the function.
	 */
	public final int params;
	
	/**
	 * The names of the named parameters to the function.
	 */
	public final String[] namedParams;
	
	/**
	 * Create a FunctionTypeRep.
	 *
	 * @param params The number of parameters to the function.
	 * @param namedParams The names of the named parameters to the function.
	 */
	public FunctionTypeRep(final int params, final String[] namedParams) {
		this.params = params;
		this.namedParams = namedParams;
	}
	
	@Override
	protected final boolean unifyPartial(final TypeRep other) {
		if (!(other instanceof FunctionTypeRep) ||
				params != ((FunctionTypeRep)other).params ||
				namedParams.length != ((FunctionTypeRep)other).namedParams.length) {
			return false;
		}
		for (int i = 0; i < namedParams.length; i++) {
			if (!namedParams[i].equals(((FunctionTypeRep)other).namedParams[i])) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public final String toString() {
		String paramsToString = "";
		for (int i = 0; i < params; i++) {
			paramsToString += " _";
		}
		for (int i = 0; i < namedParams.length; i++) {
			paramsToString += "; " + namedParams[i] + "::_";
		}
		return "(_ ::=" + paramsToString + ")";
	}
}
