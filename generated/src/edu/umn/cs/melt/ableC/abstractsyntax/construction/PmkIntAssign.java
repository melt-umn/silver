
package edu.umn.cs.melt.ableC.abstractsyntax.construction;

public final class PmkIntAssign extends common.FunctionNode {

	public static final int i_x = 0;
	public static final int i_a = 1;
	public static final int i_l = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkIntAssign;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PmkIntAssign(final Object c_x, final Object c_a, final Object c_l) {
		this.child_x = c_x;
		this.child_a = c_a;
		this.child_l = c_l;

	}

	private Object child_x;
	public final common.StringCatter getChild_x() {
		return (common.StringCatter) (child_x = common.Util.demand(child_x));
	}

	private Object child_a;
	public final common.StringCatter getChild_a() {
		return (common.StringCatter) (child_a = common.Util.demand(child_a));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_x: return getChild_x();
			case i_a: return getChild_a();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_x: return child_x;
			case i_a: return child_a;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:construction:mkIntAssign";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt invoke(final Object c_x, final Object c_a, final Object c_l) {
		try {
		final common.DecoratedNode context = new PmkIntAssign(c_x, c_a, c_l).decorate();
		//exprStmt(eqExpr(declRefExpr(name(x,location=l),location=l), mkIntExpr(a, l,),location=l),)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)new edu.umn.cs.melt.ableC.abstractsyntax.host.PexprStmt(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PeqExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdeclRefExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pname(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntAssign.i_x), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntAssign.i_l)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntAssign.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntExpr.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntAssign.i_a), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntAssign.i_l)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntAssign.i_l)))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:construction:mkIntAssign", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmkIntAssign.invoke(children[0], children[1], children[2]);
		}
	};
}