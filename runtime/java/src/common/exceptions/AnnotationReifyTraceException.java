package common.exceptions;

/**
 * Reify trace exception for tracing an error caused by an annotation.  
 * 
 * @author krame505
 */
@SuppressWarnings("serial")
public class AnnotationReifyTraceException extends ReifyTraceException {
	public final String prodName;
	public final String annoName;
	
	public AnnotationReifyTraceException(final String prodName, final String annoName, Throwable t) {
		super("While reifying annotation '" + annoName + "' of production '" + prodName + "'", t);
		this.prodName = prodName;
		this.annoName = annoName;
	}
	
	@Override
	protected final String getASTRepr() {
		return prodName + "(" + annoName + "=" + getASTRepr(getCause()) + ")";
	}
}
