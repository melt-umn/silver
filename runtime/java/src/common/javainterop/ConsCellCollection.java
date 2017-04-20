package common.javainterop;

import java.util.AbstractCollection;
import java.util.Iterator;

import common.ConsCell;

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
