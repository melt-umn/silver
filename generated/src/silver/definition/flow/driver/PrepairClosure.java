
package silver.definition.flow.driver;

public final class PrepairClosure extends common.FunctionNode {

	public static final int i_newEdges = 0;
	public static final int i_graph = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_repairClosure;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PrepairClosure(final Object c_newEdges, final Object c_graph) {
		this.child_newEdges = c_newEdges;
		this.child_graph = c_graph;

	}

	private Object child_newEdges;
	public final common.ConsCell getChild_newEdges() {
		return (common.ConsCell) (child_newEdges = common.Util.demand(child_newEdges));
	}

	private Object child_graph;
	public final Object getChild_graph() {
		return (Object) (child_graph = common.Util.demand(child_graph));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_newEdges: return getChild_newEdges();
			case i_graph: return getChild_graph();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_newEdges: return child_newEdges;
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
		return "silver:definition:flow:driver:repairClosure";
	}

	public static Object invoke(final Object c_newEdges, final Object c_graph) {
		try {
		final common.DecoratedNode context = new PrepairClosure(c_newEdges, c_graph).decorate();
		//g:repairClosure(newEdges, graph)
		return (Object)(((Object)silver.util.raw.graph.PrepairClosure.invoke(context.childAsIsLazy(silver.definition.flow.driver.PrepairClosure.i_newEdges), context.childAsIsLazy(silver.definition.flow.driver.PrepairClosure.i_graph))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:repairClosure", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrepairClosure.invoke(children[0], children[1]);
		}
	};
}