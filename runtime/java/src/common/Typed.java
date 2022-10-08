package common;

/**
 * Interface implemented by all objects with a Silver type (recommended for foreign types as well.)
 * Only objects implementing this interface may be part of a reified tree.
 * 
 * @author krame505
 */
public interface Typed {
	/**
	 * Construct the type representation of the object.
	 * Note that this returns the <i>freshened</i> type, containing all new type variables, with
	 * skolems replaced by flexible type vars.
	 * 
	 * @return The type of the object
	 */
	public TypeRep getType();
}
