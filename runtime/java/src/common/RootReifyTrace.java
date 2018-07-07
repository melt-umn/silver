package common;

/**
 * Reify trace for the outermost position. 
 * 
 * @author krame505
 */
public class RootReifyTrace extends ReifyTrace {
	public RootReifyTrace() {
		super(null);
	}

	@Override
	public String toString(final String childToString) {
		return childToString;
	}
}
