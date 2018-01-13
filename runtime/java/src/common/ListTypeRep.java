package common;

/**
 * Representation of a list Silver type, used for run-time type checking in reification.  
 * 
 * @author krame505
 */
public class ListTypeRep extends TypeRep {
	/**
	 * The type parameter for the list.
	 */
	public final TypeRep param;
	
	/**
	 * Create a BaseTypeRep
	 * 
	 * @param param The type parameter for the list.
	 */
	public ListTypeRep(final TypeRep param) {
		this.param = param;
	}
	
	@Override
	public boolean check(final TypeRep other) {
		if (other instanceof VarTypeRep) {
			return other.check(this);
		} else {
			return other instanceof ListTypeRep && param.check(((ListTypeRep)other).param);
		}
	}
	
	@Override
	public boolean equals(final TypeRep other) {
		return other instanceof ListTypeRep && param.equals(((ListTypeRep)other).param);
	}
	
	@Override
	public String toString() {
		return "[" + param.toString() + "]";
	}
}
