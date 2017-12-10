
package edu.umn.cs.melt.ableC.concretesyntax.gcc_exts;

// top::ConditionalExpr_c ::= c::LogicalOrExpr_c '?' ':' e::ConditionalExpr_c 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0 extends edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c {

	public static final int i_c = 0;
	public static final int i__G_2 = 1;
	public static final int i__G_1 = 2;
	public static final int i_e = 3;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.NLogicalOrExpr_c.class,edu.umn.cs.melt.ableC.concretesyntax.TQuestion_t.class,edu.umn.cs.melt.ableC.concretesyntax.TColon_t.class,edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_c] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NLogicalOrExpr_c.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0(final Object c_c, final Object c__G_2, final Object c__G_1, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_c = c_c;
		this.child__G_2 = c__G_2;
		this.child__G_1 = c__G_1;
		this.child_e = c_e;

	}

	private Object child_c;
	public final edu.umn.cs.melt.ableC.concretesyntax.NLogicalOrExpr_c getChild_c() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NLogicalOrExpr_c) (child_c = common.Util.demand(child_c));
	}

	private Object child__G_2;
	public final edu.umn.cs.melt.ableC.concretesyntax.TQuestion_t getChild__G_2() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TQuestion_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TColon_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TColon_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e;
	public final edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c getChild_e() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_c: return getChild_c();
			case i__G_2: return getChild__G_2();
			case i__G_1: return getChild__G_1();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_c: return child_c;
			case i__G_2: return child__G_2;
			case i__G_1: return child__G_1;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = ast:binaryConditionalExpr(c.ast, e.ast,location=top.location)
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_ConditionalExpr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PbinaryConditionalExpr(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0.i_c, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_LogicalOrExpr_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0.i_e, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_ConditionalExpr_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.concretesyntax.NConditionalExpr_c)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_20_0(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
