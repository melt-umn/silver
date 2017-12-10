
package silver.definition.flow.driver;

public final class PextendFlowGraph extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_g = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_extendFlowGraph;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PextendFlowGraph(final Object c_l, final Object c_g) {
		this.child_l = c_l;
		this.child_g = c_g;

	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_g;
	public final Object getChild_g() {
		return (Object) (child_g = common.Util.demand(child_g));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_g: return getChild_g();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_g: return child_g;

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
		return "silver:definition:flow:driver:extendFlowGraph";
	}

	public static Object invoke(final Object c_l, final Object c_g) {
		try {
		final common.DecoratedNode context = new PextendFlowGraph(c_l, c_g).decorate();
		//g:add(l, g)
		return (Object)(((Object)silver.util.raw.graph.Padd.invoke(context.childAsIsLazy(silver.definition.flow.driver.PextendFlowGraph.i_l), context.childAsIsLazy(silver.definition.flow.driver.PextendFlowGraph.i_g))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:extendFlowGraph", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PextendFlowGraph.invoke(children[0], children[1]);
		}
	};
}