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
	protected boolean unifyDirect(final TypeRep other, final boolean flexible) {
		if (flexible && other instanceof VarTypeRep) {
			return other.unifyDirect(this, false);
		} else {
			return other instanceof ListTypeRep && param.unify(((ListTypeRep)other).param, flexible);
		}
	}
	
	@Override
	public final String toString() {
		return "[" + param.toString() + "]";
	}
}
