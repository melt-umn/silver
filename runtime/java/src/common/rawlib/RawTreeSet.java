package common.rawlib;

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
	
}
