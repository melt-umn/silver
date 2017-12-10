
package edu.umn.cs.melt.ableC.concretesyntax.gcc_exts;

// top::LabeledStmt_c ::= 'case' l::ConstantExpr_c '...' u::ConstantExpr_c ':' s::Stmt_c 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0 extends edu.umn.cs.melt.ableC.concretesyntax.NLabeledStmt_c {

	public static final int i__G_5 = 0;
	public static final int i_l = 1;
	public static final int i__G_3 = 2;
	public static final int i_u = 3;
	public static final int i__G_1 = 4;
	public static final int i_s = 5;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.TCASE.class,edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c.class,edu.umn.cs.melt.ableC.concretesyntax.TELLIPSES.class,edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c.class,edu.umn.cs.melt.ableC.concretesyntax.TColon_t.class,edu.umn.cs.melt.ableC.concretesyntax.NStmt_c.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NLabeledStmt_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NLabeledStmt_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c.num_inh_attrs];
	childInheritedAttributes[i_u] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c.num_inh_attrs];
	childInheritedAttributes[i_s] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NStmt_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0(final Object c__G_5, final Object c_l, final Object c__G_3, final Object c_u, final Object c__G_1, final Object c_s, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child_l = c_l;
		this.child__G_3 = c__G_3;
		this.child_u = c_u;
		this.child__G_1 = c__G_1;
		this.child_s = c_s;

	}

	private Object child__G_5;
	public final edu.umn.cs.melt.ableC.concretesyntax.TCASE getChild__G_5() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TCASE) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_l;
	public final edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c getChild_l() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c) (child_l = common.Util.demand(child_l));
	}

	private Object child__G_3;
	public final edu.umn.cs.melt.ableC.concretesyntax.TELLIPSES getChild__G_3() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TELLIPSES) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_u;
	public final edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c getChild_u() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c) (child_u = common.Util.demand(child_u));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TColon_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TColon_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_s;
	public final edu.umn.cs.melt.ableC.concretesyntax.NStmt_c getChild_s() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NStmt_c) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i_l: return getChild_l();
			case i__G_3: return getChild__G_3();
			case i_u: return getChild_u();
			case i__G_1: return getChild__G_1();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i_l: return child_l;
			case i__G_3: return child__G_3;
			case i_u: return child_u;
			case i__G_1: return child__G_1;
			case i_s: return child_s;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = ast:caseLabelRangeStmt(l.ast, u.ast, s.ast,)
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_LabeledStmt_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcaseLabelRangeStmt(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0.i_l, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_ConstantExpr_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0.i_u, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_ConstantExpr_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0.i_s, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_Stmt_c))); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Stmts_sv_41_0(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
