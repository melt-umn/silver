package common;

/**
 * Representation of a function Silver type, used for run-time type checking in reification.  
 * 
 * @author krame505
 */
public class BaseTypeRep extends TypeRep {
	/**
	 * The name of the type, minus any type parameters - e.g. "Integer" or "Decorated core:Pair".
	 */
	public final String baseName;
	
	/**
	 * The type parameters given to the base type.
	 */
	public final TypeRep[] params;
	
	/**
	 * Create a BaseTypeRep
	 * 
	 * @param baseName The base name of the type.
	 * @param params The type parameters given to the base type.
	 */
	public BaseTypeRep(final String baseName, final TypeRep[] params) {
		this.baseName = baseName;
		this.params = params;
	}
	
	/**
	 * Create a BaseTypeRep without type parameters.
	 * 
	 * @param baseName The base name of the type.
	 */
	public BaseTypeRep(final String baseName) {
		this(baseName, new TypeRep[0]);
	}
	
	@Override
	public boolean check(final TypeRep other) {
		if (other instanceof VarTypeRep) {
			return other.check(this);
		} else if (!(other instanceof BaseTypeRep) || !this.baseName.equals(((BaseTypeRep)other).baseName)) {
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
		if (!(other instanceof BaseTypeRep) || !this.baseName.equals(((BaseTypeRep)other).baseName)) {
			return false;
		}
		for (int i = 0; i < params.length; i++) {
			if (!params[i].equals(((BaseTypeRep)other).params[i])) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		if (params.length == 0) {
			return baseName;
		} else {
			String paramsToString = params[0].toString();
			for (int i = 1; i < params.length; i++) {
				paramsToString += " " + params[i].toString();
			}
			return baseName + "<" + paramsToString + ">";
		}
	}
}
