package common;

/**
 * Representation of a (possibly parametric) Silver type, used for run-time type checking in reifiction.  
 * 
 * @author krame505
 */
public class TypeRep {
	/**
	 * The name of the type, minus any type parameters - e.g. "Integer", "Decorated core:Pair", or "[]".
	 */
	public final String baseName;
	
	/**
	 * The type parameters given to the base type.
	 */
	public final TypeRep[] params;
	
	/**
	 * Create a TypeRep
	 * 
	 * @param baseName The base name of the type.
	 * @param params The type parameters given to the base type.
	 */
	public TypeRep(final String baseName, final TypeRep[] params) {
		this.baseName = baseName;
		this.params = params;
	}
	
	/**
	 * Create a TypeRep without type parameters
	 * 
	 * @param baseName The base name of the type.
	 */
	public TypeRep(final String baseName) {
		this(baseName, new TypeRep[0]);
	}
	
	/*
	@Override
	public boolean equals(final TypeRep other) {
		if (!baseName.equals(other.baseName)) {
			return false;
		}
		assert params.length == other.params.length;
		for (int i = 0; i < params.length; i++) {
			if (!params[i].equals(other.params[i])) {
				return false;
			}
		}
		return true;
	}*/

	@Override
	public String toString() {
		if (baseName == "[]") {
			assert params.length == 1;
			return "[" + params[0] + "]";
		} else if (params.length == 0) {
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
