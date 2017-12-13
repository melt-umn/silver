
package silver.definition.flow.driver;

public final class PaddAllAutoCopyEqs extends common.FunctionNode {

	public static final int i_prod = 0;
	public static final int i_sigNames = 1;
	public static final int i_inhs = 2;
	public static final int i_flowEnv = 3;
	public static final int i_realEnv = 4;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_addAllAutoCopyEqs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PaddAllAutoCopyEqs(final Object c_prod, final Object c_sigNames, final Object c_inhs, final Object c_flowEnv, final Object c_realEnv) {
		this.child_prod = c_prod;
		this.child_sigNames = c_sigNames;
		this.child_inhs = c_inhs;
		this.child_flowEnv = c_flowEnv;
		this.child_realEnv = c_realEnv;

	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_sigNames;
	public final common.ConsCell getChild_sigNames() {
		return (common.ConsCell) (child_sigNames = common.Util.demand(child_sigNames));
	}

	private Object child_inhs;
	public final common.ConsCell getChild_inhs() {
		return (common.ConsCell) (child_inhs = common.Util.demand(child_inhs));
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
			case i_prod: return getChild_prod();
			case i_sigNames: return getChild_sigNames();
			case i_inhs: return getChild_inhs();
			case i_flowEnv: return getChild_flowEnv();
			case i_realEnv: return getChild_realEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_sigNames: return child_sigNames;
			case i_inhs: return child_inhs;
			case i_flowEnv: return child_flowEnv;
			case i_realEnv: return child_realEnv;

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
		return "silver:definition:flow:driver:addAllAutoCopyEqs";
	}

	public static common.ConsCell invoke(final Object c_prod, final Object c_sigNames, final Object c_inhs, final Object c_flowEnv, final Object c_realEnv) {
		try {
		final common.DecoratedNode context = new PaddAllAutoCopyEqs(c_prod, c_sigNames, c_inhs, c_flowEnv, c_realEnv).decorate();
		//if null(sigNames) then [] else addAutocopyEqs(prod, head(sigNames), inhs, flowEnv, realEnv) ++ addAllAutoCopyEqs(prod, tail(sigNames), inhs, flowEnv, realEnv)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddAllAutoCopyEqs.i_sigNames))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PaddAutocopyEqs.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddAllAutoCopyEqs.i_prod), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddAllAutoCopyEqs.i_sigNames))); } }, context.childAsIsLazy(silver.definition.flow.driver.PaddAllAutoCopyEqs.i_inhs), context.childAsIsLazy(silver.definition.flow.driver.PaddAllAutoCopyEqs.i_flowEnv), context.childAsIsLazy(silver.definition.flow.driver.PaddAllAutoCopyEqs.i_realEnv))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PaddAllAutoCopyEqs.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddAllAutoCopyEqs.i_prod), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.flow.driver.PaddAllAutoCopyEqs.i_sigNames))); } }, context.childAsIsLazy(silver.definition.flow.driver.PaddAllAutoCopyEqs.i_inhs), context.childAsIsLazy(silver.definition.flow.driver.PaddAllAutoCopyEqs.i_flowEnv), context.childAsIsLazy(silver.definition.flow.driver.PaddAllAutoCopyEqs.i_realEnv))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:addAllAutoCopyEqs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaddAllAutoCopyEqs.invoke(children[0], children[1], children[2], children[3], children[4]);
		}
	};
}