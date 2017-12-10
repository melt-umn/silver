
package edu.umn.cs.melt.ableC.abstractsyntax.construction;

public final class PmkIntDeclInit extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_val = 1;
	public static final int i_l = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkIntDeclInit;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PmkIntDeclInit(final Object c_n, final Object c_val, final Object c_l) {
		this.child_n = c_n;
		this.child_val = c_val;
		this.child_l = c_l;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_val;
	public final common.StringCatter getChild_val() {
		return (common.StringCatter) (child_val = common.Util.demand(child_val));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_val: return getChild_val();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_val: return child_val;
			case i_l: return child_l;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:construction:mkIntDeclInit";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt invoke(final Object c_n, final Object c_val, final Object c_l) {
		try {
		final common.DecoratedNode context = new PmkIntDeclInit(c_n, c_val, c_l).decorate();
		//mkIntDeclGeneral(n, justInitializer(exprInitializer(mkIntExpr(val, l,),),), l,)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntDeclGeneral.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntDeclInit.i_n), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeInitializer)new edu.umn.cs.melt.ableC.abstractsyntax.host.PjustInitializer(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NInitializer)new edu.umn.cs.melt.ableC.abstractsyntax.host.PexprInitializer(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntExpr.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntDeclInit.i_val), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntDeclInit.i_l)))); } })); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntDeclInit.i_l)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:construction:mkIntDeclInit", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmkIntDeclInit.invoke(children[0], children[1], children[2]);
		}
	};
}