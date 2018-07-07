package common;

/**
 * Reify trace for representing a list.  
 * 
 * @author krame505
 */
public class ConsReifyTrace extends ReifyTrace {
	public final boolean isHead;
	
	public ConsReifyTrace(final boolean isHead, final ReifyTrace parent) {
		super(parent);
		this.isHead = isHead;
	}
	
	@Override
	protected final String toString(final String childToString) {
		String result = "[";
		ReifyTrace current;
		for (current = parent;
				current instanceof ConsReifyTrace && !((ConsReifyTrace)current).isHead;
				current = current.parent) {
			result += "_, ";
		}
		result += childToString;
		if (isHead) {
			result += ", ...]";
		} else {
			result += "]";
		}
		return current.toString(result);
	}
}
