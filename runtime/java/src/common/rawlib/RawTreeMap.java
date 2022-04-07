package common.rawlib;

import java.util.TreeMap;
import java.util.List;
import java.util.Arrays;

import common.ConsCell;
import common.NodeFactory;
import common.StringCatter;
import common.javainterop.ConsCellCollection;
import common.javainterop.SilverComparator;

public final class RawTreeMap {
	public static TreeMap<Object,ConsCell> empty(NodeFactory<Integer> cmp) {
		return new TreeMap<Object,ConsCell>(new SilverComparator<Object>(cmp));
	}
	public static TreeMap<String,ConsCell> fromCSVString(StringCatter csvStr, NodeFactory<Integer> cmp) {
		String[] lines = (csvStr.toString()).split(System.lineSeparator());
		TreeMap<String,ConsCell> outputMap = new TreeMap<String,ConsCell>(new SilverComparator<Object>(cmp));
		for (String line : lines) {
			List<String> tokens = Arrays.asList(line.split(","));

			if (tokens.size() > 1) {
				String head = ( // new StringCatter
										(tokens.get(0)) );
				ConsCell rest = ConsCell.fromList(tokens.subList(1, tokens.size()));
				outputMap.put(head, rest);
			}
		}
		return outputMap;
	}
	public static TreeMap<Object, ConsCell> addList(ConsCell l, TreeMap<Object, ConsCell> t) {
		if(l.nil())
			return t;
		@SuppressWarnings("unchecked")
		TreeMap<Object,ConsCell> ret = (TreeMap<Object,ConsCell>)t.clone();
		for(silver.core.NPair elem : new ConsCellCollection<silver.core.NPair>(l)) {
			assert(elem instanceof silver.core.Ppair); // document as an assert why not
			ConsCell existing = lookup(elem.getChild(0), ret);
			ret.put(elem.getChild(0), new ConsCell(elem.getChild(1), existing));
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
