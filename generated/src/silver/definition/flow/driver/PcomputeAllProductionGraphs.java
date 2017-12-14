
package silver.definition.flow.driver;

public final class PcomputeAllProductionGraphs extends common.FunctionNode {

	public static final int i_prods = 0;
	public static final int i_prodTree = 1;
	public static final int i_flowEnv = 2;
	public static final int i_realEnv = 3;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_computeAllProductionGraphs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcomputeAllProductionGraphs(final Object c_prods, final Object c_prodTree, final Object c_flowEnv, final Object c_realEnv) {
		this.child_prods = c_prods;
		this.child_prodTree = c_prodTree;
		this.child_flowEnv = c_flowEnv;
		this.child_realEnv = c_realEnv;

	}

	private Object child_prods;
	public final common.ConsCell getChild_prods() {
		return (common.ConsCell) (child_prods = common.Util.demand(child_prods));
	}

	private Object child_prodTree;
	public final Object getChild_prodTree() {
		return (Object) (child_prodTree = common.Util.demand(child_prodTree));
	}

	private Object child_flowEnv;
	public final common.DecoratedNode getChild_flowEnv() {
		return (common.DecoratedNode) (child_flowEnv = common.Util.demand(child_flowEnv));
	}

	private Object child_realEnv;
	public final common.DecoratedNode getChild_realEnv() {
		return (common.DecoratedNode) (child_realEnv = common.Util.demand(child_realEnv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prods: return getChild_prods();
			case i_prodTree: return getChild_prodTree();
			case i_flowEnv: return getChild_flowEnv();
			case i_realEnv: return getChild_realEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prods: return child_prods;
			case i_prodTree: return child_prodTree;
			case i_flowEnv: return child_flowEnv;
			case i_realEnv: return child_realEnv;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:definition:flow:driver:computeAllProductionGraphs";
	}

	public static common.ConsCell invoke(final Object c_prods, final Object c_prodTree, final Object c_flowEnv, final Object c_realEnv) {
		try {
		final common.DecoratedNode context = new PcomputeAllProductionGraphs(c_prods, c_prodTree, c_flowEnv, c_realEnv).decorate();
		//if null(prods) then [] else (constructProductionGraph(head(prods), searchEnvTree(head(prods).fullName, prodTree), flowEnv, realEnv) :: computeAllProductionGraphs(tail(prods), prodTree, flowEnv, realEnv))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.flow.driver.PcomputeAllProductionGraphs.i_prods))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NProductionGraph)silver.definition.flow.driver.PconstructProductionGraph.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PcomputeAllProductionGraphs.i_prods))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PcomputeAllProductionGraphs.i_prods))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo)); } }, context.childAsIsLazy(silver.definition.flow.driver.PcomputeAllProductionGraphs.i_prodTree))); } }, context.childAsIsLazy(silver.definition.flow.driver.PcomputeAllProductionGraphs.i_flowEnv), context.childAsIsLazy(silver.definition.flow.driver.PcomputeAllProductionGraphs.i_realEnv))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PcomputeAllProductionGraphs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.flow.driver.PcomputeAllProductionGraphs.i_prods))); } }, context.childAsIsLazy(silver.definition.flow.driver.PcomputeAllProductionGraphs.i_prodTree), context.childAsIsLazy(silver.definition.flow.driver.PcomputeAllProductionGraphs.i_flowEnv), context.childAsIsLazy(silver.definition.flow.driver.PcomputeAllProductionGraphs.i_realEnv))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:computeAllProductionGraphs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcomputeAllProductionGraphs.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}