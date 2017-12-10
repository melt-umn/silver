
package edu.umn.cs.melt.ableC.concretesyntax.gcc_exts;

// top::PrimaryExpr_c ::= '__builtin_types_compatible_p' '(' t1::TypeName_c ',' t2::TypeName_c ')' 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0 extends edu.umn.cs.melt.ableC.concretesyntax.NPrimaryExpr_c {

	public static final int i__G_5 = 0;
	public static final int i__G_4 = 1;
	public static final int i_t1 = 2;
	public static final int i__G_2 = 3;
	public static final int i_t2 = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.TGNU_Builtin_TypesCompatible_t.class,edu.umn.cs.melt.ableC.concretesyntax.TLParen_t.class,edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c.class,edu.umn.cs.melt.ableC.concretesyntax.TComma_t.class,edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c.class,edu.umn.cs.melt.ableC.concretesyntax.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NPrimaryExpr_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NPrimaryExpr_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t1] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c.num_inh_attrs];
	childInheritedAttributes[i_t2] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0(final Object c__G_5, final Object c__G_4, final Object c_t1, final Object c__G_2, final Object c_t2, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child__G_4 = c__G_4;
		this.child_t1 = c_t1;
		this.child__G_2 = c__G_2;
		this.child_t2 = c_t2;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.TGNU_Builtin_TypesCompatible_t getChild__G_5() {
		return (edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.TGNU_Builtin_TypesCompatible_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child__G_4;
	public final edu.umn.cs.melt.ableC.concretesyntax.TLParen_t getChild__G_4() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TLParen_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_t1;
	public final edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c getChild_t1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c) (child_t1 = common.Util.demand(child_t1));
	}

	private Object child__G_2;
	public final edu.umn.cs.melt.ableC.concretesyntax.TComma_t getChild__G_2() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_t2;
	public final edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c getChild_t2() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c) (child_t2 = common.Util.demand(child_t2));
	}

	private Object child__G_0;
	public final edu.umn.cs.melt.ableC.concretesyntax.TRParen_t getChild__G_0() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i__G_4: return getChild__G_4();
			case i_t1: return getChild_t1();
			case i__G_2: return getChild__G_2();
			case i_t2: return getChild_t2();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i__G_4: return child__G_4;
			case i_t1: return child_t1;
			case i__G_2: return child__G_2;
			case i_t2: return child_t2;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = ast:typesCompatibleExpr(t1.ast, t2.ast,location=top.location)
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_PrimaryExpr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PtypesCompatibleExpr(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0.i_t1, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_TypeName_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0.i_t2, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_TypeName_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.concretesyntax.NPrimaryExpr_c)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Expr_sv_56_0(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
