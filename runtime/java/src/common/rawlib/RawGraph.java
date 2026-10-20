package common.rawlib;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import common.ConsCell;
import common.NodeFactory;
import common.Thunk;
import common.javainterop.ConsCellCollection;
import common.javainterop.SilverComparator;

/**
 * The implementation of silver:util:graph.
 *
 * A graph maps each vertex with edges from it to the set of vertices those edges go to.  Each vertex
 * has an index, and each of these sets is a row: a bit set over the indices.  Adding edges and
 * repairing a transitive closure then work on whole rows at once, and the vertices' comparator is
 * only called to find their indices.
 *
 * Graphs are values: every operation returns a new graph, sharing the rows it did not change.  All
 * the graphs derived from one empty graph share its index, which only grows.
 *
 * @see Graph.sv
 */
public final class RawGraph {
	// type Graph<a> = Graph

	/**
	 * Vertices and their indices, in order of first use.  ids is ordered by the vertices'
	 * comparator, so iterating it gives the vertices in that order.
	 */
	static final class Index {
		final Comparator<Object> cmp;
		final TreeMap<Object,Integer> ids;
		final ArrayList<Object> vertices = new ArrayList<Object>();

		Index(Comparator<Object> cmp) {
			this.cmp = cmp;
			this.ids = new TreeMap<Object,Integer>(cmp);
		}

		// The index of v, giving it one if it has none.
		int idOf(Object v) {
			Integer i = ids.get(v);
			if(i == null) {
				i = vertices.size();
				ids.put(v, i);
				vertices.add(v);
			}
			return i;
		}

		// The index of v, or -1 if it has none.
		int find(Object v) {
			final Integer i = ids.get(v);
			return i == null ? -1 : i;
		}
	}

	/**
	 * The set of vertices that the edges from one vertex go to.  A row is not changed once a graph
	 * holds it; its view as a sorted set is built when first asked for.
	 */
	static final class Row {
		final BitSet bits;
		private TreeSet<Object> view;

		Row(BitSet bits) {
			this.bits = bits;
		}

		TreeSet<Object> view(Index index) {
			if(view == null) {
				final TreeSet<Object> s = new TreeSet<Object>(index.cmp);
				for(int i = bits.nextSetBit(0); i >= 0; i = bits.nextSetBit(i + 1)) {
					s.add(index.vertices.get(i));
				}
				view = s;
			}
			return view;
		}
	}

	public static final class Graph {
		final Index index;
		// By index; null where the vertex has no edges from it.  May be shorter than the index.
		final Row[] rows;

		Graph(Index index, Row[] rows) {
			this.index = index;
			this.rows = rows;
		}

		Row rowAt(int i) {
			return i >= 0 && i < rows.length ? rows[i] : null;
		}
	}

	/**
	 * The rows of a graph being built from another: rows are copied the first time they are changed.
	 */
	private static final class Builder {
		final Index index;
		Row[] rows;
		final BitSet owned = new BitSet();

		Builder(Graph g) {
			this.index = g.index;
			this.rows = Arrays.copyOf(g.rows, Math.max(g.rows.length, g.index.vertices.size()));
		}

		Row rowAt(int i) {
			return i < rows.length ? rows[i] : null;
		}

		// The row at i, to be changed: a copy the first time.
		Row own(int i) {
			if(i >= rows.length) {
				rows = Arrays.copyOf(rows, Math.max(i + 1, index.vertices.size()));
			}
			if(!owned.get(i)) {
				final Row r = rows[i];
				rows[i] = new Row(r == null ? new BitSet() : (BitSet)r.bits.clone());
				owned.set(i);
			}
			return rows[i];
		}

		Graph build() {
			return new Graph(index, rows);
		}
	}

	private static TreeSet<Object> emptySet(Graph g) {
		return new TreeSet<Object>(g.index.cmp);
	}

	// empty :: (Graph<a> ::= (Integer ::= a  a))
	public static Graph empty(NodeFactory<Integer> cmp) {
		return new Graph(new Index(new SilverComparator<Object>(cmp)), new Row[0]);
	}

	// add :: (Graph<a> ::= [Pair<a a>]  Graph<a>)
	public static Graph add(ConsCell l, Graph g) {
		if(l.nil())
			return g;
		final Builder b = new Builder(g);
		for(silver.core.NPair elem : new ConsCellCollection<silver.core.NPair>(l)) {
			final int src = g.index.idOf(elem.getAnno_silver_core_fst());
			final int dst = g.index.idOf(elem.getAnno_silver_core_snd());
			final Row r = b.rowAt(src);
			if(r == null || !r.bits.get(dst)) {
				b.own(src).bits.set(dst);
			}
		}
		return b.build();
	}

	// edgesFrom :: (Set<a> ::= a  Graph<a>)
	public static TreeSet<Object> edgesFrom(Object key, Graph g) {
		final Row r = g.rowAt(g.index.find(key));
		return r == null ? emptySet(g) : r.view(g.index);
	}

	// adjacency :: ([Pair<a Set<a>>] ::= Graph<a>), in the vertices' order
	public static ConsCell adjacency(Graph g) {
		final ArrayList<Object> pairs = new ArrayList<Object>();
		for(Entry<Object,Integer> e : g.index.ids.entrySet()) {
			final Row r = g.rowAt(e.getValue());
			if(r != null) {
				final Index index = g.index;
				pairs.add(new silver.core.Ppair(e.getKey(), new Thunk<Object>(() -> r.view(index))));
			}
		}
		return ConsCell.fromList(pairs);
	}

	// contains :: (Boolean ::= Pair<a a>  Graph<a>)
	public static boolean contains(silver.core.NPair p, Graph g) {
		final Row r = g.rowAt(g.index.find(p.getAnno_silver_core_fst()));
		if(r == null)
			return false;
		final int dst = g.index.find(p.getAnno_silver_core_snd());
		return dst >= 0 && r.bits.get(dst);
	}

	// toList :: ([Pair<a a>] ::= Graph<a>)
	public static ConsCell toList(Graph g) {
		ConsCell ret = ConsCell.nil;
		for(Entry<Object,Integer> e : g.index.ids.entrySet()) {
			final Row r = g.rowAt(e.getValue());
			if(r == null)
				continue;
			final Object key = e.getKey();
			for(Object value : r.view(g.index)) {
				ret = new ConsCell(new silver.core.Ppair(key, value), ret);
			}
		}
		return ret;
	}

	// transitiveClosure :: (Graph<a> ::= Graph<a>)
	public static Graph transitiveClosure(Graph g) {
		final Builder b = new Builder(g);
		for(int u = 0; u < b.rows.length; u++) {
			if(b.rows[u] == null)
				continue;
			final BitSet set = b.own(u).bits;
			// The vertices whose rows are not yet merged into set.  A row closed earlier in this loop
			// brings in everything its vertex reaches.
			final BitSet need = (BitSet)set.clone();
			for(int focus = need.nextSetBit(0); focus >= 0; focus = need.nextSetBit(0)) {
				need.clear(focus);
				final Row fr = b.rowAt(focus);
				if(fr == null)
					continue;
				final BitSet diff = (BitSet)fr.bits.clone();
				diff.andNot(set);
				need.or(diff);
				set.or(diff);
			}
		}
		return b.build();
	}

	// repairClosure :: (Graph<a> ::= [Pair<a a>]  Graph<a>)
	public static Graph repairClosure(ConsCell l, Graph g) {
		if(l.nil())
			return g;
		// The new edges, grouped by their source
		final LinkedHashMap<Integer,BitSet> edgesBySrc = new LinkedHashMap<Integer,BitSet>();
		for(silver.core.NPair elem : new ConsCellCollection<silver.core.NPair>(l)) {
			final int src = g.index.idOf(elem.getAnno_silver_core_fst());
			final int dst = g.index.idOf(elem.getAnno_silver_core_snd());
			edgesBySrc.computeIfAbsent(src, k -> new BitSet()).set(dst);
		}

		final Builder b = new Builder(g);
		for(Map.Entry<Integer,BitSet> srcEdges : edgesBySrc.entrySet()) {
			// The graph is a transitive closure, and src gets new edges: repair the closure.
			final int src = srcEdges.getKey();
			final Row srcRow = b.rowAt(src);

			// The new dependencies of src: each dst, and the transitive dependencies of dst
			final BitSet added = new BitSet();
			final BitSet dsts = srcEdges.getValue();
			for(int dst = dsts.nextSetBit(0); dst >= 0; dst = dsts.nextSetBit(dst + 1)) {
				if(srcRow != null && srcRow.bits.get(dst))
					continue;
				added.set(dst);
				final Row dstRow = b.rowAt(dst);
				if(dstRow != null)
					added.or(dstRow.bits);
			}
			if(srcRow != null)
				added.andNot(srcRow.bits);
			if(added.isEmpty())
				continue;

			// This completely repairs the dependencies of src...
			b.own(src).bits.or(added);

			// ...and those that depend on src already have its old dependencies.
			for(int u = 0; u < b.rows.length; u++) {
				final Row r = b.rows[u];
				if(r != null && r.bits.get(src)) {
					b.own(u).bits.or(added);
				}
			}
		}
		return b.build();
	}
}
