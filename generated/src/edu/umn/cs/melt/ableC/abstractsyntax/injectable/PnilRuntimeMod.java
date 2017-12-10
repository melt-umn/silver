
package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

// top::RuntimeMods ::= 
public final class PnilRuntimeMod extends edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_nilRuntimeMod;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilRuntimeMod() {
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:injectable:nilRuntimeMod erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:injectable:nilRuntimeMod";
	}

	static void initProductionAttributeDefinitions() {
		// top.modExpr = top.exprToModify
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PnilRuntimeMod.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMods)); } };

	}

	public static final common.NodeFactory<PnilRuntimeMod> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilRuntimeMod> {

		@Override
		public PnilRuntimeMod invoke(final Object[] children, final Object[] annotations) {
			return new PnilRuntimeMod();
		}
	};

}
