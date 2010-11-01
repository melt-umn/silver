package common;

/**
 * AnyType is a legacy of pre-polymorphism Silver.  This will be removed ASAP.
 * 
 * @author bodin
 */
public class AnyType {
	private final Object data;

	public AnyType(Object o) {
		this.data = o;
	}

	public Object getData() {
		return this.data;
	}
}
