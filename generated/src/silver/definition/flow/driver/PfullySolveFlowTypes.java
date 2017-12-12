
package silver.definition.flow.driver;

public final class PfullySolveFlowTypes extends common.FunctionNode {

	public static final int i_graphs = 0;
	public static final int i_ntEnv = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_fullySolveFlowTypes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfullySolveFlowTypes(final Object c_graphs, final Object c_ntEnv) {
		this.child_graphs = c_graphs;
		this.child_ntEnv = c_ntEnv;

	}

	private Object child_graphs;
	public final common.ConsCell getChild_graphs() {
		return (common.ConsCell) (child_graphs = common.Util.demand(child_graphs));
	}

	private Object child_ntEnv;
	public final Object getChild_ntEnv() {
		return (Object) (child_ntEnv = common.Util.demand(child_ntEnv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_graphs: return getChild_graphs();
			case i_ntEnv: return getChild_ntEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_graphs: return child_graphs;
			case i_ntEnv: return child_ntEnv;

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
		return "silver:definition:flow:driver:fullySolveFlowTypes";
	}

	public static core.NPair invoke(final Object c_graphs, final Object c_ntEnv) {
		try {
		final common.DecoratedNode context = new PfullySolveFlowTypes(c_graphs, c_ntEnv).decorate();
		//if ! iter.fst then iter.snd else fullySolveFlowTypes(iter.snd.fst, iter.snd.snd)
		return (core.NPair)(((!((Boolean)context.localDecorated(silver.definition.flow.driver.Init.iter__ON__silver_definition_flow_driver_fullySolveFlowTypes).synthesized(core.Init.core_fst__ON__core_Pair))) ? ((core.NPair)context.localDecorated(silver.definition.flow.driver.Init.iter__ON__silver_definition_flow_driver_fullySolveFlowTypes).synthesized(core.Init.core_snd__ON__core_Pair)) : ((core.NPair)silver.definition.flow.driver.PfullySolveFlowTypes.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)context.localDecorated(silver.definition.flow.driver.Init.iter__ON__silver_definition_flow_driver_fullySolveFlowTypes).synthesized(core.Init.core_snd__ON__core_Pair)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NPair)context.localDecorated(silver.definition.flow.driver.Init.iter__ON__silver_definition_flow_driver_fullySolveFlowTypes).synthesized(core.Init.core_snd__ON__core_Pair)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:fullySolveFlowTypes", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfullySolveFlowTypes.invoke(children[0], children[1]);
		}
	};
}