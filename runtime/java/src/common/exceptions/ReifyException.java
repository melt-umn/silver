package common.exceptions;

import common.ReifyTrace;

/**
 * Variation on a trace exception that can show a representation of the partially-constructed AST
 * being reified.  
 * 
 * @author krame505
 */
public class ReifyException extends SilverError {
	public final ReifyTrace trace;
	
	public ReifyException(ReifyTrace trace, String s) {
		super("Reification error at " + trace.toString() + ":\n" + s);
		this.trace = trace;
	}
	
	/**
	 * Constructs a String representing the AST constructed so far, indicating the position that
	 * caused the error, with irrelevant portions elided away.
	 * 
	 * @return The AST representation.
	 */
	public String getASTRepr() {
		return trace.toString();
	}
}
