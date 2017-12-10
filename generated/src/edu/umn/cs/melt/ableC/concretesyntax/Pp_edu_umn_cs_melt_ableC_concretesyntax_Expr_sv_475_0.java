
package edu.umn.cs.melt.ableC.concretesyntax;

// top::InitializerList_c ::= il::InitializerList_c ',' d::Designation_c i::Initializer_c 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0 extends edu.umn.cs.melt.ableC.concretesyntax.NInitializerList_c {

	public static final int i_il = 0;
	public static final int i__G_2 = 1;
	public static final int i_d = 2;
	public static final int i_i = 3;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.NInitializerList_c.class,edu.umn.cs.melt.ableC.concretesyntax.TComma_t.class,edu.umn.cs.melt.ableC.concretesyntax.NDesignation_c.class,edu.umn.cs.melt.ableC.concretesyntax.NInitializer_c.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_p_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NInitializerList_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NInitializerList_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_il] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NInitializerList_c.num_inh_attrs];
	childInheritedAttributes[i_d] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NDesignation_c.num_inh_attrs];
	childInheritedAttributes[i_i] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NInitializer_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0(final Object c_il, final Object c__G_2, final Object c_d, final Object c_i, final Object a_core_location) {
		super(a_core_location);
		this.child_il = c_il;
		this.child__G_2 = c__G_2;
		this.child_d = c_d;
		this.child_i = c_i;

	}

	private Object child_il;
	public final edu.umn.cs.melt.ableC.concretesyntax.NInitializerList_c getChild_il() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NInitializerList_c) (child_il = common.Util.demand(child_il));
	}

	private Object child__G_2;
	public final edu.umn.cs.melt.ableC.concretesyntax.TComma_t getChild__G_2() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_d;
	public final edu.umn.cs.melt.ableC.concretesyntax.NDesignation_c getChild_d() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NDesignation_c) (child_d = common.Util.demand(child_d));
	}

	private Object child_i;
	public final edu.umn.cs.melt.ableC.concretesyntax.NInitializer_c getChild_i() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NInitializer_c) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_il: return getChild_il();
			case i__G_2: return getChild__G_2();
			case i_d: return getChild_d();
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_il: return child_il;
			case i__G_2: return child__G_2;
			case i_d: return child_d;
			case i_i: return child_i;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:p_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:p_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = il.ast ++ [ ast:designatedInit(d.ast, i.ast,) ]
		edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_InitializerList_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0.i_il, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_InitializerList_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NInit)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdesignatedInit(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0.i_d, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_Designation_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0.i_i, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_Initializer_c))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_Expr_sv_475_0(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
