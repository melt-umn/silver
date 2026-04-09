package common.rawlib;

import java.util.TreeMap;

import common.NodeFactory;
import common.javainterop.SilverComparator;

public final class RawIdCache {
	public static TreeMap<Object,Integer> empty(NodeFactory<Integer> cmp) {
		return new TreeMap<Object,Integer>(new SilverComparator<Object>(cmp));
	}
	public static Integer lookup(Object k, TreeMap<Object,Integer> t) {
		if(!t.containsKey(k)) t.put(k, t.size());
		return t.get(k);
	}
}
