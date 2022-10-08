package common.rawlib;

import java.util.ArrayDeque;
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
	@SuppressWarnings("unchecked")
	public static TreeMap<Object,TreeSet<Object>> add(ConsCell l, TreeMap<Object,TreeSet<Object>> g) {
		if(l.nil())
			return g;
		// Note that this clone only the tree map... it's up to us to clone the sets in the values.
		TreeMap<Object,TreeSet<Object>> ret = (TreeMap<Object,TreeSet<Object>>)g.clone();
		for(silver.core.NPair elem : new ConsCellCollection<silver.core.NPair>(l)) {
			assert(elem instanceof silver.core.Ppair); // document as an assert why not
			final Object src = elem.getChild(0);
			final Object dst = elem.getChild(1);
			
			TreeSet<Object> target = ret.get(src);
			if(target == null) {
				target = new TreeSet<Object>(g.comparator());
				ret.put(src, target);
			} else {
				// Pointer comparison, essentially
				if(target == g.get(src)) {
					// If it's the same object as we started with, clone it.
					target = (TreeSet<Object>)target.clone();
					ret.put(src, target);
				}
			}
			
			target.add(dst);
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
	public static boolean contains(silver.core.NPair p, TreeMap<Object,TreeSet<Object>> g) {
		assert(p instanceof silver.core.Ppair); // document as an assert why not
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
				ret = new ConsCell(new silver.core.Ppair(key, value), ret);
			}
		}
		return ret;
	}
	
	// transitiveClosure :: (Graph<a> ::= Graph<a>)
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
	public static TreeMap<Object,TreeSet<Object>> repairClosure(
			ConsCell l, 
			TreeMap<Object,TreeSet<Object>> g) {
		if(l.nil())
			return g;
		TreeMap<Object,TreeSet<Object>> ret = (TreeMap<Object,TreeSet<Object>>)g.clone();
		// Let's allow every element in the map to be mutated from the start
		for(Entry<Object, TreeSet<Object>> entry : ret.entrySet()) {
			entry.setValue((TreeSet<Object>)entry.getValue().clone());
		}
		// The above is justified because calling our comparator is actually quite expensive
		// Anything we can do to reduce the number of calls is good.
		// Cloning the crap out of graphs way too often is a smaller price to pay than the additional
		// calls to comparison functions. So, uh, to-do someday: make silver fast. Heh.
		
		for(silver.core.NPair elem : new ConsCellCollection<silver.core.NPair>(l)) {
			assert(elem instanceof silver.core.Ppair); // document as an assert why not
			final Object src = elem.getChild(0);
			final Object dst = elem.getChild(1);

			// So we have a transitively closed graph, currently, and we
			// suddenly want to add the edge (src, dst), and repair the closure.
			
			// Obtain the transitive dependencies of src
			TreeSet<Object> srcSet = ret.get(src);
			
			if(srcSet == null) {
				srcSet = new TreeSet<Object>(g.comparator());
				ret.put(src, srcSet);
			} else {
				// Short circuit if edge exists already
				if(srcSet.contains(dst))
					continue;				
			}

			// Transitive dependenceis of dst
			TreeSet<Object> dstSet = ret.get(dst);
			
			// First step: add dst
			srcSet.add(dst);
			if(dstSet != null)
				srcSet.addAll(dstSet);
			
			// This completely repairs srcSet to a transitive closure,
			// now for the rest of the vertexes...
			for(Entry<Object, TreeSet<Object>> entry : ret.entrySet()) {
				TreeSet<Object> target = entry.getValue();
				if(target.contains(src)) {
					target.addAll(srcSet);
				}
			}
		}
		
		return ret;
	}
}
