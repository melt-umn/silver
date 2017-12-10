
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PordinaryFunctionHandler extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_a = 1;
	public static final int i_l = 2;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NName.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ordinaryFunctionHandler;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_f] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NName.num_inh_attrs];
	childInheritedAttributes[i_a] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PordinaryFunctionHandler(final Object c_f, final Object c_a, final Object c_l) {
		this.child_f = c_f;
		this.child_a = c_a;
		this.child_l = c_l;

	}

	private Object child_f;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NName getChild_f() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName) (child_f = common.Util.demand(child_f));
	}

	private Object child_a;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs getChild_a() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs) (child_a = common.Util.demand(child_a));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_a: return getChild_a();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:ordinaryFunctionHandler";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr invoke(final Object c_f, final Object c_a, final Object c_l) {
		try {
		final common.DecoratedNode context = new PordinaryFunctionHandler(c_f, c_a, c_l).decorate();
		//ovrld:callExpr(declRefExpr(f,location=f.location), a,location=l)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdeclRefExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PordinaryFunctionHandler.i_f)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PordinaryFunctionHandler.i_f).undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PordinaryFunctionHandler.i_a)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PordinaryFunctionHandler.i_l)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:ordinaryFunctionHandler", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PordinaryFunctionHandler.invoke(children[0], children[1], children[2]);
		}
	};
}