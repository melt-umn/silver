
package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

// top::LhsOrRhsRuntimeMod ::= rm::RuntimeMod 
public final class PrhsRuntimeMod extends edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod {

	public static final int i_rm = 0;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_rhsRuntimeMod;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMod.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_rm] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.num_inh_attrs];

	}

	public PrhsRuntimeMod(final Object c_rm) {
		super();
		this.child_rm = c_rm;

	}

	private Object child_rm;
	public final edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod getChild_rm() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod) (child_rm = common.Util.demand(child_rm));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rm: return getChild_rm();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rm: return child_rm;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:injectable:rhsRuntimeMod erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:injectable:rhsRuntimeMod";
	}

	static void initProductionAttributeDefinitions() {
		// top.modLhs = top.lhsToModify
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrhsRuntimeMod.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_lhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod)); } };
		// top.modRhs = rm.modExpr
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrhsRuntimeMod.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrhsRuntimeMod.i_rm).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod)); } };
		// top.isLhs = false
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrhsRuntimeMod.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_isLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// rm.exprToModify = top.rhsToModify
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrhsRuntimeMod.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.PrhsRuntimeMod.i_rm][edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_rhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMod)); } };

	}

	public static final common.NodeFactory<PrhsRuntimeMod> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrhsRuntimeMod> {

		@Override
		public PrhsRuntimeMod invoke(final Object[] children, final Object[] annotations) {
			return new PrhsRuntimeMod(children[0]);
		}
	};

}
