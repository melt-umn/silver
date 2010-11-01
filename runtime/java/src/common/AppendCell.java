package common;

/**
 * An "append node" for lists.  We end up actually storing the lists as trees,
 * until demanded, in essence.  Then share the result as much as possible.
 * 
 * @author tedinski
 * @see ConsCell
 */
public class AppendCell extends ConsCell {

	// Laziness once again plays hell with this code.
	// We want to do a "right rotate"
	// i.e. AC(AC(x,y),z) -> AC(x,AC(y,z))
	// but we have to delay doing this until we actually demand the head
	// otherwise we risk evaluating a thunk that's NOT an AC.
	
	// Why do we want this optimization?
	// Look at a tree building up a list via appends.
	// If we don't do this, when we evaluate, we get the lists evaluated
	// at each level of the tree.  Wasted.
	// This way, we get something like StringCatter, where if we're only
	// evaluating at the top, then we only pay the price at the top.
	
	// Basically,  (a ++ (b ++ (c ++ d))) is more efficient than
	//             (((a ++ b) ++ c) ++ d)
	// n vs n^2.
	
	// if evaluated is true, then we've mutated into a glorified ConsCell, and nothing more.
	// i.e.  false -> head :: ConsCell
	//       true  -> head :: Object (whatever this is a list of)
	boolean evaluated = false;
	
	public AppendCell(Object l, Object r) {
		super(l, r);
	}
	
	private void becomeRight() {
		ConsCell right = super.tail();
		head = right.head;
		tail = right.tail;
		if(right instanceof AppendCell) {
			evaluated = ((AppendCell)right).evaluated;
		} else {
			evaluated = true;
		}		
	}

	@Override
	public Object head() {
		// evaluated means we've degenerated into a ConsCell.
		// see tail() below.
		if(evaluated)
			return super.head();
		
		// Examine our left child
		ConsCell left = (ConsCell)super.head();
		// If it's null, defer to the right!
		if(left.nil()) {
			// mutate away the left side.
			becomeRight();
			return head();
		}
		// If it's an AppendCell, do the rotate optimization
		if(left instanceof AppendCell && !((AppendCell)left).evaluated) {
			// We want to do a rotate, as an optimization.
			tail = new AppendCell(left.tail, tail);
			head = left.head;
			// Yes, this is recursion. not meant to be super.head
			return head();
		}
		// otherwise, return the head of our left child.
		return left.head();
	}

	@Override
	public ConsCell tail() {
		// if we've degenerated into a ConsCell.  See below
		if(evaluated)
			return super.tail();
		
		ConsCell left = (ConsCell)super.head();
		if(left.nil()) {
			// mutate away the left side
			becomeRight();
			return tail(); // yes recurse, not super.tail
		}
		// If there's more than one thing on the left, then
		// We need to carry over the rest to a new append
		// on the right
		if( ! left.tail().nil())
			tail = new AppendCell(left.tail(), tail);
		// otherwise, claim out actual head, and return the tail.
		head = left.head();
		evaluated = true;
		return super.tail();
	}

	@Override
	public boolean nil() {
		if(evaluated)
			return false;
		// We're nil iff left.nil() and right.nil()
		return ((ConsCell)super.head()).nil() && super.tail().nil();
	}

	@Override
	public int length() {
		if(evaluated)
			return super.length();
		return ((ConsCell)super.head()).length() + super.tail().length();
	}

//	public String debug() {
//		if(evaluated) return "EC(" + head + ", " + ((ConsCell)tail).debug() + ")";
//		return "AC(" + ((ConsCell)head).debug() + ", " + ((ConsCell)tail).debug() + ")";
//	}
}
