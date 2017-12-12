
package silver.analysis.warnings.defs;

public final class PcheckAllEqDeps extends common.FunctionNode {

	public static final int i_v = 0;
	public static final int i_l = 1;
	public static final int i_prodName = 2;
	public static final int i_prodNt = 3;
	public static final int i_flowEnv = 4;
	public static final int i_realEnv = 5;
	public static final int i_anonResolve = 6;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,core.NLocation.class,common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_defs_checkAllEqDeps;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PcheckAllEqDeps(final Object c_v, final Object c_l, final Object c_prodName, final Object c_prodNt, final Object c_flowEnv, final Object c_realEnv, final Object c_anonResolve) {
		this.child_v = c_v;
		this.child_l = c_l;
		this.child_prodName = c_prodName;
		this.child_prodNt = c_prodNt;
		this.child_flowEnv = c_flowEnv;
		this.child_realEnv = c_realEnv;
		this.child_anonResolve = c_anonResolve;

	}

	private Object child_v;
	public final common.ConsCell getChild_v() {
		return (common.ConsCell) (child_v = common.Util.demand(child_v));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_prodName;
	public final common.StringCatter getChild_prodName() {
		return (common.StringCatter) (child_prodName = common.Util.demand(child_prodName));
	}

	private Object child_prodNt;
	public final common.StringCatter getChild_prodNt() {
		return (common.StringCatter) (child_prodNt = common.Util.demand(child_prodNt));
	}

	private Object child_flowEnv;
	public final common.DecoratedNode getChild_flowEnv() {
		return (common.DecoratedNode) (child_flowEnv = common.Util.demand(child_flowEnv));
	}

	private Object child_realEnv;
	public final common.DecoratedNode getChild_realEnv() {
		return (common.DecoratedNode) (child_realEnv = common.Util.demand(child_realEnv));
	}

	private Object child_anonResolve;
	public final common.ConsCell getChild_anonResolve() {
		return (common.ConsCell) (child_anonResolve = common.Util.demand(child_anonResolve));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();
			case i_l: return getChild_l();
			case i_prodName: return getChild_prodName();
			case i_prodNt: return getChild_prodNt();
			case i_flowEnv: return getChild_flowEnv();
			case i_realEnv: return getChild_realEnv();
			case i_anonResolve: return getChild_anonResolve();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;
			case i_l: return child_l;
			case i_prodName: return child_prodName;
			case i_prodNt: return child_prodNt;
			case i_flowEnv: return child_flowEnv;
			case i_realEnv: return child_realEnv;
			case i_anonResolve: return child_anonResolve;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		return "silver:analysis:warnings:defs:checkAllEqDeps";
	}

	public static common.ConsCell invoke(final Object c_v, final Object c_l, final Object c_prodName, final Object c_prodNt, final Object c_flowEnv, final Object c_realEnv, final Object c_anonResolve) {
		try {
		final common.DecoratedNode context = new PcheckAllEqDeps(c_v, c_l, c_prodName, c_prodNt, c_flowEnv, c_realEnv, c_anonResolve).decorate();
		//flatMap(checkEqDeps(_, l, prodName, prodNt, flowEnv, realEnv, anonResolve), v)
		return (common.ConsCell)(((common.ConsCell)core.PflatMap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.analysis.warnings.defs.PcheckEqDeps.factory.invokePartial(new int[]{1, 2, 3, 4, 5, 6}, new Object[]{common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.warnings.defs.PcheckAllEqDeps.i_l)), context.childAsIsLazy(silver.analysis.warnings.defs.PcheckAllEqDeps.i_prodName), context.childAsIsLazy(silver.analysis.warnings.defs.PcheckAllEqDeps.i_prodNt), context.childAsIsLazy(silver.analysis.warnings.defs.PcheckAllEqDeps.i_flowEnv), context.childAsIsLazy(silver.analysis.warnings.defs.PcheckAllEqDeps.i_realEnv), context.childAsIsLazy(silver.analysis.warnings.defs.PcheckAllEqDeps.i_anonResolve)}); } }, context.childAsIsLazy(silver.analysis.warnings.defs.PcheckAllEqDeps.i_v))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:defs:checkAllEqDeps", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcheckAllEqDeps.invoke(children[0], children[1], children[2], children[3], children[4], children[5], children[6]);
		}
	};
}