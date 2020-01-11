package common;

/**
 * Representation of a decorated nonterminal Silver type, used for run-time type checking in reification.  
 * 
 * @author krame505
 */
public class DecoratedTypeRep extends TypeRep {
	/**
	 * The type of the nonterminal that is decorated
	 */
	public final BaseTypeRep baseType;
	
	/**
	 * Create a DecoratedTypeRep
	 * 
	 * @param baseType The undecorated nonterminal type.
	 */
	public DecoratedTypeRep(final BaseTypeRep baseType) {
		this.baseType = baseType;
	}
	
	@Override
	protected final boolean unifyPartial(final TypeRep other) {
		if (!(other instanceof DecoratedTypeRep)) {
			return false;
		}
		return baseType.unifyPartial(((DecoratedTypeRep)other).baseType);
	}
	
	@Override
	public final String toString() {
		return "Decorated " + baseType.toString();
	}
}
