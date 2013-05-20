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
			
			getMutatable(elem.getChild(0), ret, mutated).add(elem.getChild(1));
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
		for(Entry<Object, TreeSet<Object>> entry : ret.entrySet()) {
			
			final TreeSet<Object> set = (TreeSet<Object>)entry.getValue().clone();
			entry.setValue(set);
			
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
	
	// repairClosure :: (Graph<a> ::= [Pair<a a>]  Graph<a>)
	public static TreeMap<Object,TreeSet<Object>> repairClosure(
			ConsCell l, 
			TreeMap<Object,TreeSet<Object>> g) {
		if(l.nil())
			return g;
		// Note that this clone only the tree map... it's up to us to clone the sets in the values.
		TreeMap<Object,TreeSet<Object>> ret = (TreeMap<Object,TreeSet<Object>>)g.clone();
		TreeSet<Object> mutated = new TreeSet<Object>(g.comparator());
		
		for(core.NPair elem : new ConsCellCollection<core.NPair>(l)) {
			assert(elem instanceof core.Ppair); // document as an assert why not
			final Object src = elem.getChild(0);
			final Object dst = elem.getChild(1);

			// So we have a transitively closed graph, currently, and we
			// suddenly want to add the edge (src, dst), and repair the closure.
			
			TreeSet<Object> srcSet = getMutatable(src, ret, mutated);
			TreeSet<Object> dstSet = ret.get(dst);
			
			// Short circuit if edge exists already
			if(srcSet.contains(dst))
				continue;
			
			// First step: add dst
			srcSet.add(dst);
			if(dstSet != null)
				srcSet.addAll(dstSet);
			
			// This completely repairs src's deps to a transitive closure,
			// now for the rest of the vertexes...
			
			// We're choosing to iterate over keys instead of entries, so
			// we can re-use getMutatable safely...
			for(Object key : ret.keySet()) {
				TreeSet<Object> value = ret.get(key);
				// Skip if it doesn't have src
				if(!value.contains(src))
					continue;
				// If it does, go try to add all of src to it
				getMutatable(key, ret, mutated).addAll(srcSet);
			}
		}
		
		return ret;
	}
	
	/**
	 * Get a set that we can mutate, give a key, the graph, 
	 * and a set of keys that are already mutatable
	 * 
	 * @param key  The key (src vertex) we want to mutate
	 * @param ret  The graph
	 * @param mutated  The set of keys that have already been cloned
	 * @return  The set for this key, that we are allowed to mutate
	 */
	private static TreeSet<Object> getMutatable(
			Object key, 
			TreeMap<Object,TreeSet<Object>> ret, 
			TreeSet<Object> mutated) {
		TreeSet<Object> setToModify = ret.get(key);
		
		if(mutated.contains(key)) {
			return setToModify;
		}
		if(setToModify == null) {
			setToModify = new TreeSet<Object>(ret.comparator());
		} else {
			setToModify = (TreeSet<Object>)ret.get(key).clone();
		}
		ret.put(key, setToModify);
		mutated.add(key);
		return setToModify;
	}
}
