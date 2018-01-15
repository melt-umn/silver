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
	 * Unify this TypeRep with other, updating contained variables with the substitutions performed.
	 * 
	 * @param other The TypeRep with which to unify.
	 * @param flexible true if type variables in other should be allowed to unify with other types.
	 * @return true if the types are valid.
	 */
	public final boolean unify(final TypeRep other, final boolean flexible) {
		return unifyDirect(other.getSubstitution(), flexible);
	}
	
	/**
	 * Unify this TypeRep with other, updating contained variables with the substitutions performed.
	 * Here other is not allowed to contain substituted VarTypeRep types.
	 * 
	 * @param other The TypeRep with which to unify.
	 * @param flexible true if type variables in other should be allowed to unify with other types.
	 * @return true if the types are valid.
	 */
	protected abstract boolean unifyDirect(final TypeRep other, final boolean flexible);
	
	@Override
	public abstract String toString();
}
