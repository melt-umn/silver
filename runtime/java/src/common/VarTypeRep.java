package common;

/**
 * Representation of a type variable in Silver, used for run-time type checking in reification.
 * Any VarTypeRep object corresponds to a unique instantiation of a type variable, and may be
 * referenced in multiple places.
 * 
 * @author krame505
 */
public class VarTypeRep extends TypeRep {
	/**
	 * A unique identifier for the type.
	 * Invariant: Only one VarTypeRep object may exist for any id.
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
	protected final boolean unifyPartial(final TypeRep other) {
		if (substitution != null) {
			// If this var has been substituted, unify with the substitution
			return substitution.unifyPartial(other);
		} else {
			// Perform a new substitution
			substitution = other;
			return true;
		}
	}

	@Override
	public final String typeName() {
		if (this.substitution != null) {
			return substitution.typeName();
		} else {
			return "a" + id;
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
