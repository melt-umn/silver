package common;

/**
 * Representation of a Silver type, used for run-time type checking in reification.  
 * 
 * @author krame505
 */
public abstract class TypeRep {
	/**
	 * Resolve substituted type variables to get the actual object representing the type.
	 * This should be called before accessing the class of any TypeRep object instead of
	 * doing so directly.  
	 * 
	 * @return The actual type without any wrapping VarTypeRep objects.
	 */
	public TypeRep getSubstitution() {
		return this;
	}
	
	/**
	 * Perform unification in one direction, only performing immediate substitions on this.
	 * Here other is not allowed to contain substituted VarTypeRep types.
	 * 
	 * @param other The TypeRep with which to unify.
	 * @return true if the types are valid.
	 */
	protected abstract boolean unifyPartial(final TypeRep other);
	
	/**
	 * Unify two TypeReps with each other, updating contained variables with the substitutions
	 * performed.
	 * 
	 * @param other The TypeRep with which to unify.
	 * @return true if the types are valid.
	 */
	public static boolean unify(final TypeRep t1, final TypeRep t2) {
		return t1.unifyPartial(t2.getSubstitution()) || t2.unifyPartial(t1.getSubstitution());
	}
	
	/**
	 * @return The base name of the type.
	 */
	public abstract String typeName();
	
	@Override
	public abstract String toString();
}
