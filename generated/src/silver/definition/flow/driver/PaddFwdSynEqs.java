
package silver.definition.flow.driver;

public final class PaddFwdSynEqs extends common.FunctionNode {

	public static final int i_prod = 0;
	public static final int i_syns = 1;
	public static final int i_flowEnv = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_addFwdSynEqs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PaddFwdSynEqs(final Object c_prod, final Object c_syns, final Object c_flowEnv) {
		this.child_prod = c_prod;
		this.child_syns = c_syns;
		this.child_flowEnv = c_flowEnv;

	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_syns;
	public final common.ConsCell getChild_syns() {
		return (common.ConsCell) (child_syns = common.Util.demand(child_syns));
	}

	private Object child_flowEnv;
	public final common.DecoratedNode getChild_flowEnv() {
		return (common.DecoratedNode) (child_flowEnv = common.Util.demand(child_flowEnv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prod: return getChild_prod();
			case i_syns: return getChild_syns();
			case i_flowEnv: return getChild_flowEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_syns: return child_syns;
			case i_flowEnv: return child_flowEnv;

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
		return "silver:definition:flow:driver:addFwdSynEqs";
	}

	public static common.ConsCell invoke(final Object c_prod, final Object c_syns, final Object c_flowEnv) {
		try {
		final common.DecoratedNode context = new PaddFwdSynEqs(c_prod, c_syns, c_flowEnv).decorate();
		//if null(syns) then [] else (if null(lookupSyn(prod, head(syns), flowEnv)) then [ pair(lhsSynVertex(head(syns)), forwardVertex(head(syns))), pair(lhsSynVertex(head(syns)), forwardEqVertex()) ] else []) ++ addFwdSynEqs(prod, tail(syns), flowEnv)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddFwdSynEqs.i_syns))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PlookupSyn.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddFwdSynEqs.i_prod), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddFwdSynEqs.i_syns))); } }, context.childAsIsLazy(silver.definition.flow.driver.PaddFwdSynEqs.i_flowEnv))); } })) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PlhsSynVertex(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddFwdSynEqs.i_syns))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)silver.definition.flow.ast.PforwardVertex.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddFwdSynEqs.i_syns))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PlhsSynVertex(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddFwdSynEqs.i_syns))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)silver.definition.flow.ast.PforwardEqVertex.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })) : ((common.ConsCell)core.Pnil.invoke())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PaddFwdSynEqs.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddFwdSynEqs.i_prod), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddFwdSynEqs.i_syns))); } }, context.childAsIsLazy(silver.definition.flow.driver.PaddFwdSynEqs.i_flowEnv))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:addFwdSynEqs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaddFwdSynEqs.invoke(children[0], children[1], children[2]);
		}
	};
}