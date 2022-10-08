package common.javainterop;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;

import common.ConsCell;
import common.StringCatter;

/**
 * Converts Silver lists into Java iterators.
 * 
 * <p>Also provides {@link #fromIterator(Iterator)} to do the opposite.
 *
 * @param <T>  The type of element in the list we're converting to an iterator.
 * @author tedinski
 */
public class ConsCellCollection<T> extends AbstractCollection<T> {

	private ConsCell start;
	
	public ConsCellCollection(ConsCell start) {
		this.start = start;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ConsCellIterator<T>(start);
	}

	@Override
	public int size() {
		return start.length();
	}

	public static ConsCell fromIterator(Iterator<?> i) {
		if(!i.hasNext())
			return ConsCell.nil;
		return new ConsCell(i.next(), fromIterator(i));
	}
	
	public static ConsCell fromReverseIterator(Iterator<?> i) {
		ConsCell ret = ConsCell.nil;
		while(i.hasNext()) {
			ret = new ConsCell(i.next(), ret);
		}
		return ret;
	}
	
	public static ConsCell fromList(List<?> l) {
		// Start at the end
		ListIterator<?> i = l.listIterator(l.size());
		ConsCell ret = ConsCell.nil;
		while(i.hasPrevious()) {
			ret = new ConsCell(i.previous(), ret);
		}
		return ret;
	}

	public static ConsCell fromStringList(List<String> l) {
		return fromIterator(l.stream().map(StringCatter::new).iterator());
	}

	public static class ConsCellIterator<T> implements Iterator<T> {

		private ConsCell elem;
		
		public ConsCellIterator(ConsCell elemt) {
			this.elem = elemt;
		}
		@Override
		public boolean hasNext() {
			return !elem.nil();
		}

		@Override
		public T next() {
			@SuppressWarnings("unchecked")
			T fst = (T) elem.head();
			elem = elem.tail();
			return fst;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Mutation of ConsCell iterators is not supported!");
		}
		
	}
}
