
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PwrapWarnExpr extends common.FunctionNode {

	public static final int i_msg = 0;
	public static final int i_e = 1;
	public static final int i_l = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_wrapWarnExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_e] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PwrapWarnExpr(final Object c_msg, final Object c_e, final Object c_l) {
		this.child_msg = c_msg;
		this.child_e = c_e;
		this.child_l = c_l;

	}

	private Object child_msg;
	public final common.ConsCell getChild_msg() {
		return (common.ConsCell) (child_msg = common.Util.demand(child_msg));
	}

	private Object child_e;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr getChild_e() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_msg: return getChild_msg();
			case i_e: return getChild_e();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_msg: return child_msg;
			case i_e: return child_e;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:wrapWarnExpr";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr invoke(final Object c_msg, final Object c_e, final Object c_l) {
		try {
		final common.DecoratedNode context = new PwrapWarnExpr(c_msg, c_e, c_l).decorate();
		//if null(msg,) then e else warnExpr(msg, e,location=l)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PwrapWarnExpr.i_msg))) ? context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PwrapWarnExpr.i_e).undecorate() : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PwarnExpr(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PwrapWarnExpr.i_msg), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PwrapWarnExpr.i_e)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PwrapWarnExpr.i_l))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:wrapWarnExpr", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PwrapWarnExpr.invoke(children[0], children[1], children[2]);
		}
	};
}