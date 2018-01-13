package common;

/**
 * Representation of a type variable in Silver, used for run-time type checking in reification.
 * Invariant: One shared VarTypeRep object exists for every instantiation of a type variable.
 * 
 * @author krame505
 */
public class VarTypeRep extends TypeRep {
	/**
	 * A unique identifier for the type.
	 */
	public final int id;
	
	/**
	 * Flag that is true if the variable id was assigned by Silver, or false if it was created as
	 * part of the reification algorithm.
	 */
	public final boolean hasSilverId;
	
	/**
	 * The substituted type, if it has been determined yet.
	 */
	private TypeRep substitution = null;
	
	/**
	 * The next id to be assigned.
	 */
	private static int nextId = 0;
	
	/**
	 * Create a VarTypeRep corresponding to a Silver type variable.
	 * 
	 * @param id The unique identifier for the type variable inferred by Silver.
	 */
	public VarTypeRep(final int id) {
		this.id = id;
		this.hasSilverId = true;
	}
	
	/**
	 * Create a VarTypeRep that does not correspond to a Silver type variable.
	 */
	public VarTypeRep() {
		this.id = nextId++;
		this.hasSilverId = false;
	}
	
	@Override
	public boolean check(final TypeRep other) {
		if (substitution != null) {
			// If this var has a substitution, check against the subsitution
			return substitution.check(other);
		} else if (other instanceof VarTypeRep && ((VarTypeRep)other).substitution != null) {
			// If we are checking against another var with a substitution, follow that var's substitution
			return other.check(this);
		} else {
			if (this != other) {
				// Perform a substitution, unless we are checking a var against itself
				substitution = other;
			}
			return true;
		}
	}
	
	@Override
	public String toString() {
		if (this.substitution != null) {
			return substitution.toString();
		} else if (this.hasSilverId) {
			return "a" + id;
		} else {
			return "b" + id;
		}
	}
}
