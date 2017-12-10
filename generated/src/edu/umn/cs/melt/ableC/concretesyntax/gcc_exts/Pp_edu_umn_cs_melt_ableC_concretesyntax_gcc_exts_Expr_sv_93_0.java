
package edu.umn.cs.melt.ableC.concretesyntax.gcc_exts;

// top::ArrayDesignator_c ::= '[' e1::ConstantExpr_c '...' e2::ConstantExpr_c ']' 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0 extends edu.umn.cs.melt.ableC.concretesyntax.NArrayDesignator_c {

	public static final int i__G_4 = 0;
	public static final int i_e1 = 1;
	public static final int i__G_2 = 2;
	public static final int i_e2 = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.TLBracket_t.class,edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c.class,edu.umn.cs.melt.ableC.concretesyntax.TELLIPSES.class,edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c.class,edu.umn.cs.melt.ableC.concretesyntax.TRBracket_t.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NArrayDesignator_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NArrayDesignator_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e1] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c.num_inh_attrs];
	childInheritedAttributes[i_e2] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0(final Object c__G_4, final Object c_e1, final Object c__G_2, final Object c_e2, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child_e1 = c_e1;
		this.child__G_2 = c__G_2;
		this.child_e2 = c_e2;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final edu.umn.cs.melt.ableC.concretesyntax.TLBracket_t getChild__G_4() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TLBracket_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_e1;
	public final edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c getChild_e1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c) (child_e1 = common.Util.demand(child_e1));
	}

	private Object child__G_2;
	public final edu.umn.cs.melt.ableC.concretesyntax.TELLIPSES getChild__G_2() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TELLIPSES) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_e2;
	public final edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c getChild_e2() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NConstantExpr_c) (child_e2 = common.Util.demand(child_e2));
	}

	private Object child__G_0;
	public final edu.umn.cs.melt.ableC.concretesyntax.TRBracket_t getChild__G_0() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TRBracket_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i_e1: return getChild_e1();
			case i__G_2: return getChild__G_2();
			case i_e2: return getChild_e2();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i_e1: return child_e1;
			case i__G_2: return child__G_2;
			case i_e2: return child_e2;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = ast:arrayRangeDesignator(top.givenDesignator, e1.ast, e2.ast,)
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_ArrayDesignator_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDesignator)new edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayRangeDesignator(context.contextInheritedLazy(edu.umn.cs.melt.ableC.concretesyntax.Init.edu_umn_cs_melt_ableC_concretesyntax_givenDesignator__ON__edu_umn_cs_melt_ableC_concretesyntax_ArrayDesignator_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0.i_e1, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_ConstantExpr_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0.i_e2, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_ConstantExpr_c))); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_93_0(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
