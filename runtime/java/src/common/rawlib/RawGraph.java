package common.rawlib;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Set;
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
			final Object src = elem.getAnno_silver_core_fst();
			final Object dst = elem.getAnno_silver_core_snd();
			
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
		final TreeSet<Object> set = g.get(p.getAnno_silver_core_fst());
		if(set == null)
			return false;
		return set.contains(p.getAnno_silver_core_snd());
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
		final Comparator<? super Object> cmp = g.comparator();

		// Calling our comparator is actually quite expensive, and repairing the closure means looking
		// through every vertex for those that depend on the source of an edge.
		// So group the edges by their source, to do this once for each source rather than each edge.
		final TreeMap<Object,ArrayList<Object>> edgesBySrc = new TreeMap<Object,ArrayList<Object>>(cmp);
		for(silver.core.NPair elem : new ConsCellCollection<silver.core.NPair>(l)) {
			final Object src = elem.getAnno_silver_core_fst();
			ArrayList<Object> dsts = edgesBySrc.get(src);
			if(dsts == null) {
				dsts = new ArrayList<Object>();
				edgesBySrc.put(src, dsts);
			}
			dsts.add(elem.getAnno_silver_core_snd());
		}

		// The sets of the new graph are shared with the old one until they are first changed.
		final TreeMap<Object,TreeSet<Object>> ret = (TreeMap<Object,TreeSet<Object>>)g.clone();
		final Set<TreeSet<Object>> owned = Collections.newSetFromMap(new IdentityHashMap<TreeSet<Object>,Boolean>());

		for(Entry<Object, ArrayList<Object>> srcEdges : edgesBySrc.entrySet()) {
			// So we have a transitively closed graph, currently, and we
			// suddenly want to add the edges from src, and repair the closure.
			final Object src = srcEdges.getKey();

			// Obtain the transitive dependencies of src
			final TreeSet<Object> srcSet = ret.get(src);

			// The new dependencies of src: each dst, and the transitive dependencies of dst
			final TreeSet<Object> added = new TreeSet<Object>(cmp);
			for(Object dst : srcEdges.getValue()) {
				// Short circuit if edge exists already
				if(srcSet != null && srcSet.contains(dst))
					continue;
				added.add(dst);
				final TreeSet<Object> dstSet = ret.get(dst);
				if(dstSet != null)
					added.addAll(dstSet);
			}
			if(srcSet != null)
				added.removeAll(srcSet);
			if(added.isEmpty())
				continue;

			// This completely repairs the dependencies of src to a transitive closure...
			TreeSet<Object> newSrcSet;
			if(srcSet == null) {
				newSrcSet = new TreeSet<Object>(cmp);
				owned.add(newSrcSet);
				ret.put(src, newSrcSet);
			} else if(!owned.contains(srcSet)) {
				newSrcSet = (TreeSet<Object>)srcSet.clone();
				owned.add(newSrcSet);
				ret.put(src, newSrcSet);
			} else {
				newSrcSet = srcSet;
			}
			newSrcSet.addAll(added);

			// ...now for the rest of the vertexes, those that depend on src already have its old dependencies.
			for(Entry<Object, TreeSet<Object>> entry : ret.entrySet()) {
				TreeSet<Object> target = entry.getValue();
				if(target.contains(src)) {
					if(!owned.contains(target)) {
						target = (TreeSet<Object>)target.clone();
						owned.add(target);
						entry.setValue(target);
					}
					target.addAll(added);
				}
			}
		}
		
		return ret;
	}
}
