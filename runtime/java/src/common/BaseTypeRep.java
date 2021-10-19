package common;

/**
 * Representation of a nonterminal or primitive Silver type, used for run-time type checking in reification.  
 * 
 * @author krame505
 */
public class BaseTypeRep extends TypeRep {
	/**
	 * The name of the type - e.g. "Integer" or "silver:core:Pair".
	 */
	public final String name;
	
	/**
	 * Create a BaseTypeRep.
	 * 
	 * @param name The name of the type.
	 */
	public BaseTypeRep(final String name) {
		this.name = name;
	}
	
	@Override
	protected final boolean unifyPartial(final TypeRep other) {
		return other instanceof BaseTypeRep && name.equals(((BaseTypeRep)other).name);
	}
	
	@Override
	public final String typeName() {
		return name;
	}
	
	@Override
	public final String toString() {
		return name;
	}
}
