package common;

/*
 * I want to document this behavior, as Silver pseudo-code.
 * 
 * nonterminal List<a> with head<a>, tail<a>;
 * 
 * prod cons
 * List<a> ::= h::a t::List<a>
 * { ... }
 * prod nil
 * List<a> ::=
 * { ... }
 * 
 * prod append
 * List<a> ::= l::List<a> r::List<a>
 * {
 *   forwards to case l of 
 *                 nil() -> r
 *               | append(l1,l2) -> append(l1, append(l2, r))
 *               | cons(h,t) -> cons(h, append(t, r))
 *               end;
 * }
 * 
 * This would be the real implementation IF:
 * 1. Silver nodes were as space efficient as Java objects. (important because there's usually a LOT)
 * 2. If pattern matching paid attention to forwards.
 * 
 * Only problem with the above: it doesn't detect already-forwarded bits, and treat
 * them like cons...
 */


/**
 * An "append node" for lists.  We end up actually storing the lists as trees,
 * until demanded, in essence.  Then share the result as much as possible.
 * 
 * @author tedinski
 * @see ConsCell
 */
public class AppendCell extends ConsCell {

	/**
	 * If we've not yet been evaluated to a ConsCell, false. (head :: ConsCell)
	 * 
	 * <p>Once head or tail have been demanded, however, true, as
	 * we've "become" a ConsCell. (head :: Object)
	 */
	private boolean literalConsCell = false;
	
	/**
	 * Construct an append cell, an optimized way of doing list appends.
	 * 
	 * <p>Invariant 1: l is not a Nil
	 * <br>Invariant 2: l is not an AppendCell
	 * <br>Invariant 3: l is not a Closure (enforced by types. yay!) 
	 * 
	 * @param l A real, literal ConsCell
	 * @param r ConsCell (or closure that evaluates to one) to put on the right.
	 */
	private AppendCell(ConsCell l, Object r) {
		super(l, r);
		assert( !(l instanceof ConsCell.NilConsCell) );
		assert( !(l instanceof AppendCell) || ((AppendCell)l).literalConsCell );
	}
	
	/**
	 * Append two lists together. This is the only way to create append cells,
	 * the constructor is private.
	 * 
	 * <p>The append operation is completely allowed to be strict in its LHS.
	 * 
	 * @param l (strict) A list
	 * @param r (lazy) A list
	 * @return A ConsCell representing the appended lists
	 */
	public final static ConsCell append(Object l, Object r) {
		if(l instanceof Thunk)
			l = ((Thunk<?>)l).eval();
		ConsCell left = (ConsCell)l;
		
		// Kill off nil LHS
		if(left.nil()) {
			if(r instanceof Thunk)
				r = ((Thunk<?>)r).eval();
			ConsCell right = (ConsCell)r;
			
			return right;
		}
		// Kill off append LHS
		if(left instanceof AppendCell) {
			final AppendCell leftap = (AppendCell) left;
			final Object rightap = r;
			// This, right here, is the principal reason this optimization can't be
			// accomplished with forwarding: we're checking internal implementation
			// details to SEE if "the forward" has been evaluated yet.
			// If so, then gosh, it's a ConsCell, forget this. If NOT, well, let's be smart!
			if(! leftap.literalConsCell) {
				// We MUST grab the tail of leftap now. If we do not, then it may mutate (becomeLiteralConsCell) before we
				// evaluate the thunk! If that happens, we get duplication.
				final Object leftaptail = leftap.tail;
				return append(leftap.head, new Thunk<Object>(TopNode.singleton) { public final Object doEval() { return append(leftaptail, rightap); }});
			}
		}
		// Okay, we're a real append of a real, literal ConsCell on the LHS.
		return new AppendCell(left, r);
	}
	
	private final void becomeLiteralConsCell() {
		assert(!literalConsCell);
		// By invariants, head is ConsCell
		final ConsCell left = (ConsCell)head;
		head = left.head();
		// append is strict in its LHS, so calling left.tail() is okay.
		// STRICTLY SPEAKING, however, this whole assignment to tail a bug:
		// we should be creating this as a CLOSURE.
		// TODO: investigate what consequences appear here?
		// TODO: depends on fixing the bug that makes all FFI calls strict anyway!
		//tail = append(left.tail(), tail);
		final Object oldtail = tail;
		tail = new Thunk<Object>(TopNode.singleton) { public final Object doEval() { return append(left.tail(), oldtail); } };
		literalConsCell = true;
	}
	
	@Override
	public Object head() {
		if(literalConsCell)
			return super.head();
			
		becomeLiteralConsCell();
		return super.head();
	}

	@Override
	public ConsCell tail() {
		if(literalConsCell)
			return super.tail();
			
		becomeLiteralConsCell();
		return super.tail();
	}

	@Override
	public boolean nil() {
		return false; // By invariants, LHS is a literal ConsCell
	}

	@Override
	public int length() {
		if(literalConsCell)
			return super.length();
		
		return ((ConsCell)super.head()).length() + super.tail().length();
	}
}
