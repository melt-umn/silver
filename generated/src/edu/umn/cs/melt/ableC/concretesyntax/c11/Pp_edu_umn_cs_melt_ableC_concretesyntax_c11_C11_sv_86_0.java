
package edu.umn.cs.melt.ableC.concretesyntax.c11;

// top::GenericAssoc_c ::= ty::TypeName_c ':' e::AssignExpr_c 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0 extends edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssoc_c {

	public static final int i_ty = 0;
	public static final int i__G_1 = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c.class,edu.umn.cs.melt.ableC.concretesyntax.TColon_t.class,edu.umn.cs.melt.ableC.concretesyntax.NAssignExpr_c.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssoc_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssoc_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ty] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NAssignExpr_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0(final Object c_ty, final Object c__G_1, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_ty = c_ty;
		this.child__G_1 = c__G_1;
		this.child_e = c_e;

	}

	private Object child_ty;
	public final edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c getChild_ty() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NTypeName_c) (child_ty = common.Util.demand(child_ty));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TColon_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TColon_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e;
	public final edu.umn.cs.melt.ableC.concretesyntax.NAssignExpr_c getChild_e() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NAssignExpr_c) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ty: return getChild_ty();
			case i__G_1: return getChild__G_1();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ty: return child_ty;
			case i__G_1: return child__G_1;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:c11:p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:c11:p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = [ ast:genericAssoc(ty.ast, e.ast,location=top.location) ]
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.c11.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericAssoc_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NGenericAssoc)new edu.umn.cs.melt.ableC.abstractsyntax.host.PgenericAssoc(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0.i_ty, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_TypeName_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0.i_e, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_AssignExpr_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssoc_c)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.defaultExpr = []
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.c11.Init.edu_umn_cs_melt_ableC_concretesyntax_c11_defaultExpr__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericAssoc_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0(children[0], children[1], children[2], annotations[0]);
		}
	};

}
