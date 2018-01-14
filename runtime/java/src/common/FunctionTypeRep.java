package common;

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
	 * Create a FunctionTypeRep.
	 * 
	 * @param result The result type of the function.
	 * @param params The parameter types of the function.
	 */
	public FunctionTypeRep(final TypeRep result, final TypeRep[] params) {
		this.result = result;
		this.params = params;
	}
	
	@Override
	protected boolean unifyDirect(final TypeRep other, final boolean flexible) {
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
		return true;
	}
	
	@Override
	public String toString() {
		String paramsToString = params[0].toString();
		for (int i = 1; i < params.length; i++) {
			paramsToString += " " + params[i].toString();
		}
		return "(" + result.toString() + " ::= " + paramsToString + ")";
	}
}
