
package silver.util.raw.graph;

public final class Pcontains extends common.FunctionNode {

	public static final int i_edge = 0;
	public static final int i_graph = 1;


	public static final Class<?> childTypes[] = { core.NPair.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_raw_graph_contains;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_edge] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public Pcontains(final Object c_edge, final Object c_graph) {
		this.child_edge = c_edge;
		this.child_graph = c_graph;

	}

	private Object child_edge;
	public final core.NPair getChild_edge() {
		return (core.NPair) (child_edge = common.Util.demand(child_edge));
	}

	private Object child_graph;
	public final Object getChild_graph() {
		return (Object) (child_graph = common.Util.demand(child_graph));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_edge: return getChild_edge();
			case i_graph: return getChild_graph();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_edge: return child_edge;
			case i_graph: return child_graph;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
	}

	@Override
	public common.Lazy[] getLocalInheritedAttributes(final int key) {
		return localInheritedAttributes[key];
	}

	@Override
	public common.Lazy[] getChildInheritedAttributes(final int key) {
		return childInheritedAttributes[key];
	}

	@Override
	public common.Lazy getLocal(final int key) {
		return localAttributes[key];
	}

	@Override
	public final int getNumberOfLocalAttrs() {
		return num_local_attrs;
	}

	@Override
	public final String getNameOfLocalAttr(final int index) {
		return occurs_local[index];
	}

	@Override
	public String getName() {
		return "silver:util:raw:graph:contains";
	}

	public static Boolean invoke(final Object c_edge, final Object c_graph) {
		try {
		return (Boolean)common.rawlib.RawGraph.contains(((core.NPair)common.Util.demand(c_edge)), (java.util.TreeMap<Object,java.util.TreeSet<Object>>)((Object)common.Util.demand(c_graph)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:raw:graph:contains", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pcontains.invoke(children[0], children[1]);
		}
	};
}