
package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

// top::LhsOrRhsRuntimeMods ::= h::LhsOrRhsRuntimeMod t::LhsOrRhsRuntimeMods 
public final class PconsLhsOrRhsRuntimeMod extends edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.class,edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_consLhsOrRhsRuntimeMod;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.num_inh_attrs];

	}

	public PconsLhsOrRhsRuntimeMod(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod getChild_h() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods getChild_t() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:injectable:consLhsOrRhsRuntimeMod erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:injectable:consLhsOrRhsRuntimeMod";
	}

	static void initProductionAttributeDefinitions() {
		// h.lhsToModify = t.modLhs
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.i_h][edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_lhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.i_t).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods)); } };
		// h.rhsToModify = t.modRhs
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.i_h][edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_rhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.i_t).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods)); } };
		// top.modLhs = h.modLhs
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.i_h).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod)); } };
		// top.modRhs = h.modRhs
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsLhsOrRhsRuntimeMod.i_h).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod)); } };

	}

	public static final common.NodeFactory<PconsLhsOrRhsRuntimeMod> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsLhsOrRhsRuntimeMod> {

		@Override
		public PconsLhsOrRhsRuntimeMod invoke(final Object[] children, final Object[] annotations) {
			return new PconsLhsOrRhsRuntimeMod(children[0], children[1]);
		}
	};

}
