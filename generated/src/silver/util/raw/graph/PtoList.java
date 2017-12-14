
package silver.util.raw.graph;

public final class PtoList extends common.FunctionNode {

	public static final int i_graph = 0;


	public static final Class<?> childTypes[] = { Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_raw_graph_toList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoList(final Object c_graph) {
		this.child_graph = c_graph;

	}

	private Object child_graph;
	public final Object getChild_graph() {
		return (Object) (child_graph = common.Util.demand(child_graph));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_graph: return getChild_graph();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_graph: return child_graph;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:util:raw:graph:toList";
	}

	public static common.ConsCell invoke(final Object c_graph) {
		try {
		return (common.ConsCell)common.rawlib.RawGraph.toList((java.util.TreeMap<Object,java.util.TreeSet<Object>>)((Object)common.Util.demand(c_graph)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:raw:graph:toList", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoList.invoke(children[0]);
		}
	};
}