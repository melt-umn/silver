
package edu.umn.cs.melt.ableC.concretesyntax.construction;

// top::IdentifierList_c ::= h::IdentifierList_c ',' t::Identifier_t 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0 extends edu.umn.cs.melt.ableC.concretesyntax.construction.NIdentifierList_c {

	public static final int i_h = 0;
	public static final int i__G_1 = 1;
	public static final int i_t = 2;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.construction.NIdentifierList_c.class,edu.umn.cs.melt.ableC.concretesyntax.TComma_t.class,edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_construction_p_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.construction.NIdentifierList_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.construction.NIdentifierList_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.construction.NIdentifierList_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0(final Object c_h, final Object c__G_1, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child__G_1 = c__G_1;
		this.child_t = c_t;

	}

	private Object child_h;
	public final edu.umn.cs.melt.ableC.concretesyntax.construction.NIdentifierList_c getChild_h() {
		return (edu.umn.cs.melt.ableC.concretesyntax.construction.NIdentifierList_c) (child_h = common.Util.demand(child_h));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TComma_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_t;
	public final edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t getChild_t() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i__G_1: return getChild__G_1();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i__G_1: return child__G_1;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:construction:p_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:construction:p_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.declaredIdents = (fromId(t,) :: h.declaredIdents)
		edu.umn.cs.melt.ableC.concretesyntax.construction.Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.construction.Init.edu_umn_cs_melt_ableC_concretesyntax_declaredIdents__ON__edu_umn_cs_melt_ableC_concretesyntax_construction_IdentifierList_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)edu.umn.cs.melt.ableC.abstractsyntax.construction.PfromId.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.concretesyntax.construction.Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0.i_t))); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.construction.Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0.i_h, edu.umn.cs.melt.ableC.concretesyntax.construction.Init.edu_umn_cs_melt_ableC_concretesyntax_declaredIdents__ON__edu_umn_cs_melt_ableC_concretesyntax_construction_IdentifierList_c))); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0(children[0], children[1], children[2]);
		}
	};

}
