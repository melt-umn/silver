package common;

/**
 * Special Java translation for Silver lists.  The main purpose is to reduce memory consumption
 * for this exceptionally common and prolific data structure.
 * 
 * <p>Similarly to StringCatter, we do special optimizations for appending lists.
 * 
 * @author tedinski
 * @see AppendCell
 */
public class ConsCell {
	// Everyone shares one Nil
	public static final ConsCell nil = new NilConsCell();
	
//	public static int count = 0;
	
	protected Object head, tail;
	
	// Some of the types here are nasty, as we accept
	// either a Thunk or a ConsCell as "tail"
	// So we have to take Object, and just hope for the best. :(
	public ConsCell(Object h, Object t) {
		head = h; tail = t;
		
//		count++;
	}
	
	public Object head() {
		if(head instanceof Thunk) {
			head = ((Thunk)head).eval();
		}
		return head;
	}
	public ConsCell tail() {
		if(tail instanceof Thunk) {
			tail = ((Thunk)tail).eval();
		}
		return (ConsCell)tail;
	}
	public boolean nil() {
		return false;
	}
	public int length() {
		return 1 + tail().length();
	}
	
//	public String debug() {
//		return "CC(" + head + ", " + ((ConsCell)tail).debug() + ")";
//	}
	
	
	private static class NilConsCell extends ConsCell {
		public NilConsCell() {
			super(null,null);
		}

		@Override
		public Object head() {
			throw new RuntimeException("Requested head of nil.");
		}
		@Override
		public boolean nil() {
			return true;
		}
		@Override
		public ConsCell tail() {
			throw new RuntimeException("Requested tail of nil.");
		}
		@Override
		public int length() {
			return 0;
		}
		
//		public String debug() {
//			return "nil";
//		}
	}
	
}
