package common.rawlib;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import common.ConsCell;
import common.NodeFactory;
import common.javainterop.ConsCellCollection;
import common.javainterop.SilverComparator;

public final class RawGraph {
	// type Graph<a> = Map<a Set<a>>
	
	// empty :: (Graph<a> ::= (Integer ::= a  a))
	public static TreeMap<Object,TreeSet<Object>> empty(NodeFactory<Integer> cmp) {
		return new TreeMap<Object,TreeSet<Object>>(new SilverComparator<Object>(cmp));
	}
	
	// add :: (Graph<a> ::= [Pair<a a>]  Graph<a>)
	public static TreeMap<Object,TreeSet<Object>> add(ConsCell l, TreeMap<Object,TreeSet<Object>> g) {
		if(l.nil())
			return g;
		// Note that this clone only the tree map... it's up to us to clone the sets in the values.
		TreeMap<Object,TreeSet<Object>> ret = (TreeMap<Object,TreeSet<Object>>)g.clone();
		TreeSet<Object> mutated = new TreeSet<Object>(g.comparator());
		for(core.NPair elem : new ConsCellCollection<core.NPair>(l)) {
			assert(elem instanceof core.Ppair); // document as an assert why not
			final Object key = elem.getChild(0);
			final TreeSet<Object> setToModify = 
					// If we've mutated it, then it exists and we can just change it
					mutated.contains(key) ? ret.get(key) :
					// If it existed, unmutated, we shallow copy it to be able to mutate it
					ret.containsKey(key) ? (TreeSet<Object>)ret.get(key).clone() :
					// Otherwise, we create a new empty set
					new TreeSet<Object>(g.comparator());
			// Add this to the set
			setToModify.add(elem.getChild(1));
			// And update our graph
			ret.put(key, setToModify);
		}
		return ret;
	}
	
	// edgesFrom :: (Set<a> ::= a  Graph<a>)
	public static TreeSet<Object> edgesFrom(Object key, TreeMap<Object,TreeSet<Object>> g) {
		final TreeSet<Object> set = g.get(key);
		if(set == null)
			return new TreeSet<Object>(g.comparator());
		return set;
	}
	
	// contains :: (Boolean ::= Pair<a a>  Graph<a>)
	public static boolean contains(core.NPair p, TreeMap<Object,TreeSet<Object>> g) {
		assert(p instanceof core.Ppair); // document as an assert why not
		final TreeSet<Object> set = g.get(p.getChild(0));
		if(set == null)
			return false;
		return set.contains(p.getChild(1));
	}
	
	// toList :: ([Pair<a a>] ::= Graph<a>)
	public static ConsCell toList(TreeMap<Object,TreeSet<Object>> g) {
		ConsCell ret = ConsCell.nil;
		for(Entry<Object, TreeSet<Object>> e : g.entrySet()) {
			final Object key = e.getKey();
			for(Object value : e.getValue()) {
				ret = new ConsCell(new core.Ppair(key, value), ret);
			}
		}
		return ret;
	}
}
