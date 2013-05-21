package common.rawlib;

import java.util.Iterator;
import java.util.TreeSet;

import common.*;
import common.javainterop.*;

public final class RawTreeSet {

	public static TreeSet<Object> empty(NodeFactory<Integer> cmp) {
		return new TreeSet<Object>(new SilverComparator<Object>(cmp));
	}
	public static TreeSet<Object> addList(ConsCell l, TreeSet<Object> t) {
		TreeSet<Object> ret = (TreeSet<Object>) t.clone();
		ret.addAll(new ConsCellCollection<Object>(l));
		return ret;
	}
	public static ConsCell toList(TreeSet<Object> t) {
		return ConsCellCollection.fromIterator(t.iterator());
	}
	public static TreeSet<Object> union(TreeSet<Object> l, TreeSet<Object> r) {
		TreeSet<Object> ret = (TreeSet<Object>) l.clone();
		ret.addAll(r);
		return ret;
	}
	public static TreeSet<Object> intersect(TreeSet<Object> l, TreeSet<Object> r) {
		TreeSet<Object> ret = (TreeSet<Object>) l.clone();
		ret.retainAll(r);
		return ret;
	}
	public static TreeSet<Object> difference(TreeSet<Object> l, TreeSet<Object> r) {
		TreeSet<Object> ret = (TreeSet<Object>) l.clone();
		ret.removeAll(r);
		return ret;
	}
	public static boolean contains(Object o, TreeSet<Object> t) {
		return t.contains(o);
	}
	/**
	 * If l is a subset of r.
	 */
	public static boolean subset(TreeSet<Object> l, TreeSet<Object> r) {
		return r.containsAll(l);
	}
	public static boolean containsAll(ConsCell l, TreeSet<Object> r) {
		return r.containsAll(new ConsCellCollection<Object>(l));
	}
	public static boolean isEmpty(TreeSet<Object> s) {
		return s.isEmpty();
	}
	public static int size(TreeSet<Object> s) {
		return s.size();
	}
	public static TreeSet<Object> filter(NodeFactory<Boolean> cmp, TreeSet<Object> s) {
		TreeSet<Object> ret = (TreeSet<Object>)s.clone();
		
		Iterator<Object> x = ret.iterator();
		while(x.hasNext()) {
			if(!cmp.invoke(new Object[]{x.next()}, null)) {
				x.remove();
			}
		}
		
		return ret;
	}
	public static TreeSet<Object> removeAll(ConsCell rm, TreeSet<Object> s) {
		TreeSet<Object> ret = (TreeSet<Object>)s.clone();
		ret.removeAll(new ConsCellCollection<Object>(rm));
		return ret;
	}
}
