package common;

/**
 * Representation of a nonterminal reference Silver type, used for run-time type checking in reification.  
 * 
 * @author krame505
 */
public class DecoratedTypeRep extends TypeRep {
	/**
	 * The undecorated type.
	 */
	public final TypeRep param;
	
	/**
	 * Create a DecoratedTypeRep
	 * 
	 * @param param The undecorated type.
	 */
	public DecoratedTypeRep(final TypeRep param) {
		this.param = param;
	}
	
	@Override
	protected final boolean unifyPartial(final TypeRep other) {
		return other instanceof DecoratedTypeRep && unify(param, ((DecoratedTypeRep)other).param);
	}
	
	@Override
	public final String typeName() {
		return param.typeName();
	}
	
	@Override
	public final String toString() {
		return "Decorated " + param;
	}
}
