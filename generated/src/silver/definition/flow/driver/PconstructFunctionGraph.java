
package silver.definition.flow.driver;

public final class PconstructFunctionGraph extends common.FunctionNode {

	public static final int i_ns = 0;
	public static final int i_flowEnv = 1;
	public static final int i_realEnv = 2;
	public static final int i_prodEnv = 3;
	public static final int i_ntEnv = 4;


	public static final Class<?> childTypes[] = { silver.definition.env.NNamedSignature.class,common.DecoratedNode.class,common.DecoratedNode.class,Object.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_constructFunctionGraph;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];

	}

	public PconstructFunctionGraph(final Object c_ns, final Object c_flowEnv, final Object c_realEnv, final Object c_prodEnv, final Object c_ntEnv) {
		this.child_ns = c_ns;
		this.child_flowEnv = c_flowEnv;
		this.child_realEnv = c_realEnv;
		this.child_prodEnv = c_prodEnv;
		this.child_ntEnv = c_ntEnv;

	}

	private Object child_ns;
	public final silver.definition.env.NNamedSignature getChild_ns() {
		return (silver.definition.env.NNamedSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_flowEnv;
	public final common.DecoratedNode getChild_flowEnv() {
		return (common.DecoratedNode) (child_flowEnv = common.Util.demand(child_flowEnv));
	}

	private Object child_realEnv;
	public final common.DecoratedNode getChild_realEnv() {
		return (common.DecoratedNode) (child_realEnv = common.Util.demand(child_realEnv));
	}

	private Object child_prodEnv;
	public final Object getChild_prodEnv() {
		return (Object) (child_prodEnv = common.Util.demand(child_prodEnv));
	}

	private Object child_ntEnv;
	public final Object getChild_ntEnv() {
		return (Object) (child_ntEnv = common.Util.demand(child_ntEnv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ns: return getChild_ns();
			case i_flowEnv: return getChild_flowEnv();
			case i_realEnv: return getChild_realEnv();
			case i_prodEnv: return getChild_prodEnv();
			case i_ntEnv: return getChild_ntEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ns: return child_ns;
			case i_flowEnv: return child_flowEnv;
			case i_realEnv: return child_realEnv;
			case i_prodEnv: return child_prodEnv;
			case i_ntEnv: return child_ntEnv;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return "silver:definition:flow:driver:constructFunctionGraph";
	}

	public static silver.definition.flow.driver.NProductionGraph invoke(final Object c_ns, final Object c_flowEnv, final Object c_realEnv, final Object c_prodEnv, final Object c_ntEnv) {
		try {
		final common.DecoratedNode context = new PconstructFunctionGraph(c_ns, c_flowEnv, c_realEnv, c_prodEnv, c_ntEnv).decorate();
		//updateGraph(g, prodEnv, ntEnv)
		return (silver.definition.flow.driver.NProductionGraph)(((silver.definition.flow.driver.NProductionGraph)silver.definition.flow.driver.PupdateGraph.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.flow.driver.Init.g__ON__silver_definition_flow_driver_constructFunctionGraph)), context.childAsIsLazy(silver.definition.flow.driver.PconstructFunctionGraph.i_prodEnv), context.childAsIsLazy(silver.definition.flow.driver.PconstructFunctionGraph.i_ntEnv))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:constructFunctionGraph", t);
		}
	}

	public static final common.NodeFactory<silver.definition.flow.driver.NProductionGraph> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.flow.driver.NProductionGraph> {
		@Override
		public silver.definition.flow.driver.NProductionGraph invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PconstructFunctionGraph.invoke(children[0], children[1], children[2], children[3], children[4]);
		}
	};
}