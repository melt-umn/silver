package common;

/**
 * Reify trace for representing a child.  
 * 
 * @author krame505
 */
public class ChildReifyTrace extends ReifyTrace {
	public final String prodName;
	public final String childName;
	public final int numChildren;
	public final int childIndex;
	
	public ChildReifyTrace(final String prodName, final String childName, final int numChildren, final int childIndex, final ReifyTrace parent) {
		super(parent);
		this.prodName = prodName;
		this.childName = childName;
		this.numChildren = numChildren;
		this.childIndex = childIndex;
	}
	
	@Override
	protected final String toString(final String childToString) {
		String result = prodName + "(";
		int i = 0;
		for (; i < childIndex; i++) {
			result += "_, ";
		}
		result += childToString;
		i++;
		for (; i < numChildren; i++) {
			result += ", _";
		}
		result += ")";
		return parent.toString(result);
	}
}
