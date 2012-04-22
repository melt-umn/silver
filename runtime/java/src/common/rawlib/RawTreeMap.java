package common.rawlib;

import java.util.TreeMap;

import common.ConsCell;
import common.NodeFactory;
import common.javainterop.ConsCellCollection;
import common.javainterop.SilverComparator;

public final class RawTreeMap {
	public static TreeMap<Object,ConsCell> empty(NodeFactory<Integer> cmp) {
		return new TreeMap<Object,ConsCell>(new SilverComparator<Object>(cmp));
	}
	public static TreeMap<Object, ConsCell> addList(ConsCell l, TreeMap<Object, ConsCell> t) {
		if(l.nil())
			return t;
		TreeMap<Object,ConsCell> ret = (TreeMap<Object,ConsCell>)t.clone();
		for(core.NPair elem : new ConsCellCollection<core.NPair>(l)) {
			assert(elem instanceof core.Ppair); // document as an assert why not
			ConsCell existing = lookup(elem.getChild(0), ret);
			ret.put(elem.getChild(0), new ConsCell(elem.getChild(1), existing));
		}
		return ret;
	}
	public static ConsCell toList(TreeMap<Object,ConsCell> t) {
		ConsCell ret = ConsCell.nil;
		for(Object key : t.descendingKeySet()) {
			for(Object value : new ConsCellCollection<Object>(t.get(key))) {
				ret = new ConsCell(new core.Ppair(key, value), ret);
			}
		}
		return ret;
	}
	public static ConsCell lookup(Object k, TreeMap<Object,ConsCell> t) {
		Object r = t.get(k);
		return r == null ? ConsCell.nil : (ConsCell) r;
	}
}
