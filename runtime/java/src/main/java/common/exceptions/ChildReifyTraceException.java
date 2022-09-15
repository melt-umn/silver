package common.exceptions;

/**
 * Reify trace exception for tracing an error caused by a child.  
 * 
 * @author krame505
 */
@SuppressWarnings("serial")
public class ChildReifyTraceException extends ReifyTraceException {
	public final String prodName;
	public final String childName;
	public final int numChildren;
	public final int childIndex;
	
	public ChildReifyTraceException(final String prodName, final String childName, final int numChildren, final int childIndex, Throwable t) {
		super("While reifying child '" + childName + "' of production '" + prodName + "'", t);
		this.prodName = prodName;
		this.childName = childName;
		this.numChildren = numChildren;
		this.childIndex = childIndex;
	}
	
	@Override
	protected final String getASTRepr() {
		String result = prodName + "(";
		int i = 0;
		for (; i < childIndex; i++) {
			result += "_, ";
		}
		result += getASTRepr(getCause());
		i++;
		for (; i < numChildren; i++) {
			result += ", _";
		}
		result += ")";
		return result;
	}
}
