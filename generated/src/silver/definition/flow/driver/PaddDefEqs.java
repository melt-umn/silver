
package silver.definition.flow.driver;

public final class PaddDefEqs extends common.FunctionNode {

	public static final int i_prod = 0;
	public static final int i_nt = 1;
	public static final int i_syns = 2;
	public static final int i_flowEnv = 3;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_addDefEqs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PaddDefEqs(final Object c_prod, final Object c_nt, final Object c_syns, final Object c_flowEnv) {
		this.child_prod = c_prod;
		this.child_nt = c_nt;
		this.child_syns = c_syns;
		this.child_flowEnv = c_flowEnv;

	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_nt;
	public final common.StringCatter getChild_nt() {
		return (common.StringCatter) (child_nt = common.Util.demand(child_nt));
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
			case i_nt: return getChild_nt();
			case i_syns: return getChild_syns();
			case i_flowEnv: return getChild_flowEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_nt: return child_nt;
			case i_syns: return child_syns;
			case i_flowEnv: return child_flowEnv;

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
		return "silver:definition:flow:driver:addDefEqs";
	}

	public static common.ConsCell invoke(final Object c_prod, final Object c_nt, final Object c_syns, final Object c_flowEnv) {
		try {
		final common.DecoratedNode context = new PaddDefEqs(c_prod, c_nt, c_syns, c_flowEnv).decorate();
		//if null(syns) then [] else (if null(lookupSyn(prod, head(syns), flowEnv)) then let x :: [FlowDef] = lookupDef(nt, head(syns), flowEnv) in if null(x) then [] else head(x).flowEdges end else []) ++ addDefEqs(prod, nt, tail(syns), flowEnv)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddDefEqs.i_syns))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PlookupSyn.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddDefEqs.i_prod), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddDefEqs.i_syns))); } }, context.childAsIsLazy(silver.definition.flow.driver.PaddDefEqs.i_flowEnv))); } })) ? ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5974_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PlookupDef.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddDefEqs.i_nt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddDefEqs.i_syns))); } }, context.childAsIsLazy(silver.definition.flow.driver.PaddDefEqs.i_flowEnv))); } };
return (((Boolean)core.Pnull.invoke(__SV_LOCAL_5974_x)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)((silver.definition.flow.ast.NFlowDef)core.Phead.invoke(__SV_LOCAL_5974_x)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.flow.ast.Init.silver_definition_flow_ast_flowEdges__ON__silver_definition_flow_ast_FlowDef))); } }).eval()) : ((common.ConsCell)core.Pnil.invoke())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PaddDefEqs.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddDefEqs.i_prod), context.childAsIsLazy(silver.definition.flow.driver.PaddDefEqs.i_nt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddDefEqs.i_syns))); } }, context.childAsIsLazy(silver.definition.flow.driver.PaddDefEqs.i_flowEnv))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:addDefEqs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaddDefEqs.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}