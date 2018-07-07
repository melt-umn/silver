package common;

/**
 * Reify trace for representing an annotation.  
 * 
 * @author krame505
 */
public class AnnotationReifyTrace extends ReifyTrace {
	public final String prodName;
	public final String annoName;
	
	public AnnotationReifyTrace(final String prodName, final String annoName, final ReifyTrace parent) {
		super(parent);
		this.prodName = prodName;
		this.annoName = annoName;
	}
	
	@Override
	protected final String toString(final String childToString) {
		return parent.toString(prodName + "(" + annoName + "=" + childToString + ")");
	}
}
