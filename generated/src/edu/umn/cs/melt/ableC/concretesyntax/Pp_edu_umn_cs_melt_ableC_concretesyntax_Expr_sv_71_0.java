
package edu.umn.cs.melt.ableC.concretesyntax;

// top::ConditionalExpr_c ::= ce::LogicalOrExpr_c '?' te::Expr_c ':' ee::ConditionalExpr_c 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0 extends edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c {

	public static final int i_ce = 0;
	public static final int i__G_3 = 1;
	public static final int i_te = 2;
	public static final int i__G_1 = 3;
	public static final int i_ee = 4;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.NLogicalOrExpr_c.class,edu.umn.cs.melt.ableC.concretesyntax.TQuestion_t.class,edu.umn.cs.melt.ableC.concretesyntax.NExpr_c.class,edu.umn.cs.melt.ableC.concretesyntax.TColon_t.class,edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_p_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ce] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NLogicalOrExpr_c.num_inh_attrs];
	childInheritedAttributes[i_te] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NExpr_c.num_inh_attrs];
	childInheritedAttributes[i_ee] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0(final Object c_ce, final Object c__G_3, final Object c_te, final Object c__G_1, final Object c_ee, final Object a_core_location) {
		super(a_core_location);
		this.child_ce = c_ce;
		this.child__G_3 = c__G_3;
		this.child_te = c_te;
		this.child__G_1 = c__G_1;
		this.child_ee = c_ee;

	}

	private Object child_ce;
	public final edu.umn.cs.melt.ableC.concretesyntax.NLogicalOrExpr_c getChild_ce() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NLogicalOrExpr_c) (child_ce = common.Util.demand(child_ce));
	}

	private Object child__G_3;
	public final edu.umn.cs.melt.ableC.concretesyntax.TQuestion_t getChild__G_3() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TQuestion_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_te;
	public final edu.umn.cs.melt.ableC.concretesyntax.NExpr_c getChild_te() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NExpr_c) (child_te = common.Util.demand(child_te));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TColon_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TColon_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_ee;
	public final edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c getChild_ee() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c) (child_ee = common.Util.demand(child_ee));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ce: return getChild_ce();
			case i__G_3: return getChild__G_3();
			case i_te: return getChild_te();
			case i__G_1: return getChild__G_1();
			case i_ee: return getChild_ee();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ce: return child_ce;
			case i__G_3: return child__G_3;
			case i_te: return child_te;
			case i__G_1: return child__G_1;
			case i_ee: return child_ee;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:p_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0 erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "edu:umn:cs:melt:ableC:concretesyntax:p_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = ast:conditionalExpr(ce.ast, te.ast, ee.ast,location=top.location)
		edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_ConditionalExpr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconditionalExpr(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0.i_ce, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_LogicalOrExpr_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0.i_te, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_Expr_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0.i_ee, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_ConditionalExpr_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_71_0(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
