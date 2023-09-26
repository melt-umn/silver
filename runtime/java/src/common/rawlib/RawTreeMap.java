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
		@SuppressWarnings("unchecked")
		TreeMap<Object,ConsCell> ret = (TreeMap<Object,ConsCell>)t.clone();
		for(silver.core.NPair elem : new ConsCellCollection<silver.core.NPair>(l)) {
			ConsCell existing = lookup(elem.getAnno_silver_core_fst(), ret);
			ret.put(elem.getAnno_silver_core_fst(), new ConsCell(elem.getAnno_silver_core_snd(), existing));
		}
		return ret;
	}
	public static ConsCell keys(TreeMap<Object,ConsCell> t) {
		ConsCell ret = ConsCell.nil;
		for(Object key : t.descendingKeySet()) {
			ret = new ConsCell(key, ret);
		}
		return ret;
	}
	public static ConsCell toList(TreeMap<Object,ConsCell> t) {
		ConsCell ret = ConsCell.nil;
		for(Object key : t.descendingKeySet()) {
			for(Object value : new ConsCellCollection<Object>(t.get(key))) {
				ret = new ConsCell(new silver.core.Ppair(key, value), ret);
			}
		}
		return ret;
	}
	public static ConsCell lookup(Object k, TreeMap<Object,ConsCell> t) {
		Object r = t.get(k);
		return r == null ? ConsCell.nil : (ConsCell) r;
	}
	public static TreeMap<Object,ConsCell> update(Object k, ConsCell v, TreeMap<Object, ConsCell> t) {
		@SuppressWarnings("unchecked")
		TreeMap<Object, ConsCell> ret = (TreeMap<Object, ConsCell>) t.clone();
		ret.put(k, v);
		return ret;
	}
}
