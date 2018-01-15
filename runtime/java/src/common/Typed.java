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
	 * Note that all type variables used here should be fresh.
	 * 
	 * @return The type of the object
	 */
	public TypeRep getType();
}
