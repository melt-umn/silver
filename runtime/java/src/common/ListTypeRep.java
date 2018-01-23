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
	protected boolean unifyPartial(final TypeRep other) {
		return other instanceof ListTypeRep && TypeRep.unify(param, ((ListTypeRep)other).param);
	}
	
	@Override
	public final String toString() {
		return "[" + param.toString() + "]";
	}
}
