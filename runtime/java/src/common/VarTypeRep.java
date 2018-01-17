package common;

/**
 * Representation of a type variable in Silver, used for run-time type checking in reification.
 * Note that this includes both regular type variables as well as skolems, since we want to
 * treat skolems on the LHS of a production signature as flexible during runtime unification, etc.
 * Any VarTypeRep object corresponds to a unique instantiation of a type variable, and may be
 * referenced in multiple places.
 * 
 * @author krame505
 */
public class VarTypeRep extends TypeRep {
	/**
	 * A unique identifier for the type.
	 */
	public final int id;
	
	/**
	 * The substituted type, if it has been determined yet.
	 */
	private TypeRep substitution = null;
	
	/**
	 * The next id to be assigned.
	 */
	private static int nextId = 0;
	
	/**
	 * Create a VarTypeRep.
	 */
	public VarTypeRep() {
		this.id = nextId++;
	}
	
	@Override
	public final TypeRep getSubstitution() {
		if (substitution != null) {
			return substitution.getSubstitution();
		} else {
			return this;
		}
	}
	
	@Override
	protected final boolean unifyDirect(final TypeRep other, final boolean flexible) {
		if (substitution != null) {
			// If this var has been substituted, unify with the substitution
			return substitution.unify(other, flexible);
		} else if (flexible && other instanceof VarTypeRep) {
			// Substitute this into other
			return other.unifyDirect(this, false);
		} else {
			// Substitute other into this
			substitution = other;
			return true;
		}
	}
	
	@Override
	public final String toString() {
		if (this.substitution != null) {
			return substitution.toString();
		} else {
			return "a" + id;
		}
	}
}
