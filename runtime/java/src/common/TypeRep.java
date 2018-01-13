package common;

/**
 * Representation of a Silver type, used for run-time type checking in reification.  
 * 
 * @author krame505
 */
public abstract class TypeRep {
	/**
	 * Resolve substituted type variables to get the actual object representing the type.
	 * 
	 * @return The actual type without any wrapping VarTypeRep objects.
	 */
	/*public TypeRep getSubstitution() {
		return this;
	}*/
	
	/**
	 * Verify that this TypeRep is compatible with other, performing substitutions as needed.
	 * 
	 * @param other The TypeRep with which to check.
	 * @return true if the types are valid.
	 */
	public abstract boolean check(final TypeRep other);

	/**
	 * Verify that this TypeRep is equal to other.
	 * 
	 * @param other The TypeRep with which to compare.
	 * @return true if the types are equal.
	 */
//	public abstract boolean equals(final TypeRep other);
	
	@Override
	public abstract String toString();
}
