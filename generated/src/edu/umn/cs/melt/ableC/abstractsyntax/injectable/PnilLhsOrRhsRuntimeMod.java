
package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

// top::LhsOrRhsRuntimeMods ::= 
public final class PnilLhsOrRhsRuntimeMod extends edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_nilLhsOrRhsRuntimeMod;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NLhsOrRhsRuntimeMods.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilLhsOrRhsRuntimeMod() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:injectable:nilLhsOrRhsRuntimeMod erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:injectable:nilLhsOrRhsRuntimeMod";
	}

	static void initProductionAttributeDefinitions() {
		// top.modLhs = top.lhsToModify
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnilLhsOrRhsRuntimeMod.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_lhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods)); } };
		// top.modRhs = top.rhsToModify
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnilLhsOrRhsRuntimeMod.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_rhsToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_LhsOrRhsRuntimeMods)); } };

	}

	public static final common.NodeFactory<PnilLhsOrRhsRuntimeMod> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilLhsOrRhsRuntimeMod> {

		@Override
		public PnilLhsOrRhsRuntimeMod invoke(final Object[] children, final Object[] annotations) {
			return new PnilLhsOrRhsRuntimeMod();
		}
	};

}
