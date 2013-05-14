package common.rawlib;

import java.util.ArrayDeque;
import java.util.Map.Entry;
import java.util.Queue;
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
			
			TreeSet<Object> setToModify = ret.get(key);
			
			// If we've mutated it before, we're okay to do it again.
			if(!mutated.contains(key)) {
				// Otherwise, either create the set or clone it so we can mutate.
				if(setToModify == null) {
					setToModify = new TreeSet<Object>(g.comparator());
				} else {
					setToModify = (TreeSet<Object>)ret.get(key).clone();
				}
				ret.put(key, setToModify);
				mutated.add(key);
			}

			setToModify.add(elem.getChild(1));
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
	
	// transitiveClosure :: (Graph<a> ::= Graph<a>)
	public static TreeMap<Object,TreeSet<Object>> transitiveClosure(TreeMap<Object,TreeSet<Object>> g) {

		final TreeMap<Object,TreeSet<Object>> ret = (TreeMap<Object,TreeSet<Object>>)g.clone();

		// For transitive closure we're going to presume that we mutate everything.
		for(Object key : ret.keySet()) {
			final TreeSet<Object> set = (TreeSet<Object>)ret.get(key).clone(); 
			ret.put(key, set);
			
			// This is somewhat inefficient because we sort of recompute
			// the transitive closure of many vertices.
			// But I'm just going for simple correctness for the moment.
			final ArrayDeque<Object> need = new ArrayDeque<Object>(set);
			while(!need.isEmpty()) {
				// Get work item
				final Object focus = need.pop();
				// Find out what this item adds
				TreeSet<Object> diff = ret.get(focus);
				// Many vertexes may have nothing coming out of them!
				if(diff == null)
					continue;
				diff = (TreeSet<Object>)diff.clone();
				diff.removeAll(set);
				// Add it to the work list
				need.addAll(diff);
				// Add it to the set of deps
				set.addAll(diff);
			}
		}
		
		return ret;
	}
}
