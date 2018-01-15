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
	 * The names and types of the named parameters to the function
	 */
	public final Map<String, TypeRep> namedParams;
	
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
		
		Map<String, TypeRep> namedParams = new TreeMap<>();
		assert namedParamNames.length == namedParamTypes.length;
		for (int i = 0; i < namedParamNames.length; i++) {
			namedParams.put(namedParamNames[i], namedParamTypes[i]);
		}
		this.namedParams = Collections.unmodifiableMap(namedParams);
	}
	
	@Override
	protected final boolean unifyDirect(final TypeRep other, final boolean flexible) {
		if (flexible && other instanceof VarTypeRep) {
			return other.unifyDirect(this, false);
		} else if (!(other instanceof FunctionTypeRep) || !this.result.unify(((FunctionTypeRep)other).result, flexible)) {
			return false;
		}
		for (int i = 0; i < params.length; i++) {
			if (!params[i].unify(((FunctionTypeRep)other).params[i], flexible)) {
				return false;
			}
		}
		for (String paramName : namedParams.keySet()) {
			if (!((FunctionTypeRep)other).namedParams.containsKey(paramName)) {
				return false;
			}
		}
		for (String paramName : ((FunctionTypeRep)other).namedParams.keySet()) {
			if (!namedParams.containsKey(paramName)) {
				return false;
			}
		}
		for (String paramName : namedParams.keySet()) {
			if (!namedParams.get(paramName).unify(((FunctionTypeRep)other).namedParams.get(paramName), flexible)) {
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
		String namedParamsToString = "";
		for (Map.Entry entry : namedParams.entrySet()) {
			namedParamsToString += " " + entry.getKey() + "::" + entry.getValue().toString();
		}
		return "(" + result.toString() + " ::= " + paramsToString + "; " + namedParamsToString + ")";
	}
}
