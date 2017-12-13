
package silver.definition.flow.driver;

public final class PsolveFlowTypes extends common.FunctionNode {

	public static final int i_graphs = 0;
	public static final int i_prodEnv = 1;
	public static final int i_ntEnv = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_solveFlowTypes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsolveFlowTypes(final Object c_graphs, final Object c_prodEnv, final Object c_ntEnv) {
		this.child_graphs = c_graphs;
		this.child_prodEnv = c_prodEnv;
		this.child_ntEnv = c_ntEnv;

	}

	private Object child_graphs;
	public final common.ConsCell getChild_graphs() {
		return (common.ConsCell) (child_graphs = common.Util.demand(child_graphs));
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
			case i_graphs: return getChild_graphs();
			case i_prodEnv: return getChild_prodEnv();
			case i_ntEnv: return getChild_ntEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_graphs: return child_graphs;
			case i_prodEnv: return child_prodEnv;
			case i_ntEnv: return child_ntEnv;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:definition:flow:driver:solveFlowTypes";
	}

	public static core.NPair invoke(final Object c_graphs, final Object c_prodEnv, final Object c_ntEnv) {
		try {
		final common.DecoratedNode context = new PsolveFlowTypes(c_graphs, c_prodEnv, c_ntEnv).decorate();
		//if null(graphs) then pair(false, pair([], ntEnv)) else pair(! null(brandNewEdges) || recurse.fst, pair((updatedGraph :: recurse.snd.fst), recurse.snd.snd))
		return (core.NPair)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.flow.driver.PsolveFlowTypes.i_graphs))) ? ((core.NPair)new core.Ppair(false, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, context.childAsIsLazy(silver.definition.flow.driver.PsolveFlowTypes.i_ntEnv))); } })) : ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.flow.driver.Init.brandNewEdges__ON__silver_definition_flow_driver_solveFlowTypes)))) || ((Boolean)context.localDecorated(silver.definition.flow.driver.Init.recurse__ON__silver_definition_flow_driver_solveFlowTypes).synthesized(core.Init.core_fst__ON__core_Pair))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.flow.driver.Init.updatedGraph__ON__silver_definition_flow_driver_solveFlowTypes)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)context.localDecorated(silver.definition.flow.driver.Init.recurse__ON__silver_definition_flow_driver_solveFlowTypes).synthesized(core.Init.core_snd__ON__core_Pair)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NPair)context.localDecorated(silver.definition.flow.driver.Init.recurse__ON__silver_definition_flow_driver_solveFlowTypes).synthesized(core.Init.core_snd__ON__core_Pair)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:solveFlowTypes", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsolveFlowTypes.invoke(children[0], children[1], children[2]);
		}
	};
}