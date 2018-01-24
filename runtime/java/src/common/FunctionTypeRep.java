package common;

import java.util.*;

/**
 * Representation of a (possibly parametric) basic Silver type, used for run-time type checking in reification.  
 * 
 * @author krame505
 */
public class FunctionTypeRep extends TypeRep {
	/**
	 * The result type of the function.
	 */
	public final TypeRep result;
	
	/**
	 * The types of the parameters to the function.
	 */
	public final TypeRep[] params;
	
	/**
	 * The names of the named parameters to the function.
	 */
	public final String[] namedParamNames;
	
	/**
	 * The types of the named parameters to the function.
	 */
	public final TypeRep[] namedParamTypes;
	
	/**
	 * Create a FunctionTypeRep.
	 * 
	 * @param result The result type of the function.
	 * @param params The parameter types of the function.
	 * @param namedParamNames The names of the named parameters to the function.
	 * @param namedParamTypes The types of the named parameters to the function.
	 */
	public FunctionTypeRep(final TypeRep result, final TypeRep[] params, final String[] namedParamNames, final TypeRep[] namedParamTypes) {
		this.result = result;
		this.params = params;
		this.namedParamNames = namedParamNames;
		this.namedParamTypes = namedParamTypes;
		
		assert namedParamNames.length == namedParamTypes.length;
	}
	
	@Override
	protected final boolean unifyPartial(final TypeRep other) {
		if (!(other instanceof FunctionTypeRep) ||
				!TypeRep.unify(result, ((FunctionTypeRep)other).result) ||
				params.length != ((FunctionTypeRep)other).params.length ||
				namedParamNames.length != ((FunctionTypeRep)other).namedParamNames.length) {
			return false;
		}
		
		for (int i = 0; i < params.length; i++) {
			if (!TypeRep.unify(params[i], ((FunctionTypeRep)other).params[i])) {
				return false;
			}
		}
		
		for (int i = 0; i < namedParamNames.length; i++) {
			if (!namedParamNames[i].equals(((FunctionTypeRep)other).namedParamNames[i]) ||
					!TypeRep.unify(namedParamTypes[i], ((FunctionTypeRep)other).namedParamTypes[i])) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public final String toString() {
		String paramsToString = params[0].toString();
		for (int i = 1; i < params.length; i++) {
			paramsToString += " " + params[i].toString();
		}
		for (int i = 0; i < namedParamNames.length; i++) {
			paramsToString += "; " + namedParamNames[i] + "::" + namedParamTypes[i].toString();
		}
		return "(" + result.toString() + " ::= " + paramsToString + ")";
	}
}
