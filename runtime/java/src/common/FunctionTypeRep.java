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
	protected final boolean unifyDirect(final TypeRep other, final boolean flexible) {
		if (flexible && other instanceof VarTypeRep) {
			return other.unifyDirect(this, false);
		} else if (!(other instanceof FunctionTypeRep) ||
				!result.unify(((FunctionTypeRep)other).result, flexible) ||
				params.length != ((FunctionTypeRep)other).params.length ||
				namedParamNames.length != ((FunctionTypeRep)other).namedParamNames.length) {
			return false;
		}
		
		for (int i = 0; i < params.length; i++) {
			if (!params[i].unify(((FunctionTypeRep)other).params[i], flexible)) {
				return false;
			}
		}
		
		Map<String, TypeRep> namedParams = new HashMap<>();
		for (int i = 0; i < namedParamNames.length; i++) {
			namedParams.put(namedParamNames[i], namedParamTypes[i]);
		}
		for (int i = 0; i < namedParamNames.length; i++) {
			String paramName = ((FunctionTypeRep)other).namedParamNames[i];
			if (!namedParams.containsKey(paramName) ||
					!namedParams.get(paramName).unify(((FunctionTypeRep)other).namedParamTypes[i], flexible)) {
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
		if (namedParamNames.length > 0) {
			paramsToString += ";";
		}
		for (int i = 0; i < namedParamNames.length; i++) {
			paramsToString += " " + namedParamNames[i] + "::" + namedParamTypes[i].toString();
		}
		return "(" + result.toString() + " ::= " + paramsToString + ")";
	}
}
