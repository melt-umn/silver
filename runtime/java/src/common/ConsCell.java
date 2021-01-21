package common;

import java.util.List;
import java.util.LinkedList;

import common.exceptions.*;

/**
 * Special Java translation for Silver lists.  The main purpose is to reduce memory consumption
 * for this exceptionally common and prolific data structure.
 * 
 * <p>Similarly to StringCatter, we do special optimizations for appending lists.
 * 
 * @author tedinski
 * @see AppendCell
 */
public class ConsCell implements Typed {
	/**
	 * A shared, singleton nil object.
	 */
	public static final ConsCell nil = new NilConsCell();
	
	/**
	 * Though named head and tail, should probably be considered fst and snd. Or car and cdr.
	 */
	protected Object head, tail;
	
	/**
	 * Creates a cons cell
	 * 
	 * @param h The head element, or a thunk that evaluates to the head element.
	 * @param t The tail element, or a thunk that evaluates to the tail element.
	 */
	public ConsCell(final Object h, final Object t) {
		head = h; tail = t;
	}

	/**
	 * Creates a cons list from a Java List.
	 *
	 * @param l The list.
	 * @return An equivalent cons list.
	 */
	public static <T> ConsCell fromList(List<T> l) {
		ConsCell cons = nil;
		while(l.size() > 0) {
			T val = l.remove(l.size() - 1);
			cons = new ConsCell(val, cons);
		}
		return cons;
	}

	/**
	 * Creates a Java List from a cons list.
	 *
	 * @param l The cons list.
	 * @return An equivalent Java List.
	 */
	public static <T> List<T> toList(ConsCell cons) {
                List<T> lst = new LinkedList();
		while(! cons.nil()) {
                        T val = (T) cons.head();
                        cons = cons.tail();
                        lst.add(val);
		}
		return lst;
	}
	
	/**
	 * Obtain the head of the cons cell, possibly evaluating that thunk to get it.
	 * 
	 * <p> NOTE: This isn't as lazy as it could be, but we prefer the invariant that we never
	 * get thunks out as a result to make things simple to reason about.
	 * 
	 * @return The head element.
	 */
	public Object head() {
		if(head instanceof Thunk) {
			head = ((Thunk<?>)head).eval();
		}
		return head;
	}
	/**
	 * Obtain the tail of the cons cell, possibly evaluating that thunk to get it.
	 * 
	 * @return The tail element.
	 */
	public ConsCell tail() {
		if(tail instanceof Thunk) {
			tail = ((Thunk<?>)tail).eval();
		}
		return (ConsCell)tail;
	}
	/**
	 * Determines if the list is empty.
	 * 
	 * <p>May involve evaluation of thunks for append cells.
	 * 
	 * @return Whether the list is nil.
	 * @see AppendCell#nil()
	 */
	public boolean nil() {
		return false;
	}
	/**
	 * Determine the length of the list.  Will involve evaluating the spine of the entire list.
	 * 
	 * @return The length of the list.
	 */
	public int length() {
		return 1 + tail().length();
	}
	
	@Override
	public AppTypeRep getType() {
		try {
			// The type of a list is the type of its tail, but the type of its tail may be [a].
			// Unify the parameter with the type of the head to constrain this type variable.
			AppTypeRep tailType = tail().getType();
			if (!TypeRep.unify(tailType.arg, Reflection.getType(head()))) {
				throw new SilverInternalError("Unification failed.");
			} else {
				return tailType;
			}
		} catch (SilverException e) {
			throw new TraceException("While constructing type of list", e);
		}
	}
	
	protected static class NilConsCell extends ConsCell {
		public NilConsCell() {
			super(null,null);
		}

		@Override
		public Object head() {
			throw new SilverError("Requested head of nil.");
		}
		@Override
		public boolean nil() {
			return true;
		}
		@Override
		public ConsCell tail() {
			throw new SilverError("Requested tail of nil.");
		}
		@Override
		public int length() {
			return 0;
		}
		@Override
		public AppTypeRep getType() {
			return new AppTypeRep(new BaseTypeRep("[]"), new VarTypeRep());
		}
	}
	
}
