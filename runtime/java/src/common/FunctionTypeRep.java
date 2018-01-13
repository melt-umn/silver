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
	public boolean check(final TypeRep other) {
		if (other instanceof VarTypeRep) {
			return other.check(this);
		} else if (!(other instanceof FunctionTypeRep) || !this.result.check(((FunctionTypeRep)other).result)) {
			return false;
		}
		for (int i = 0; i < params.length; i++) {
			if (!params[i].check(((BaseTypeRep)other).params[i])) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean equals(final TypeRep other) {
		if (!(other instanceof FunctionTypeRep) || !this.result.equals(((FunctionTypeRep)other).result)) {
			return false;
		}
		for (int i = 0; i < params.length; i++) {
			if (!params[i].equals(((FunctionTypeRep)other).params[i])) {
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
