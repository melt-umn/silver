
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::MaybeName ::= 
public final class PnothingName extends edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_nothingName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnothingName() {
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:nothingName erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:nothingName";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:nothingName(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName()); } };
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:nothingName(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName()); } };
		// top.pp = notext(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pnotext()); } };
		// top.maybename = nothing(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybename__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		// top.hasName = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hasName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.valueRedeclarationCheck = doNotDoValueRedeclarationCheck
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueRedeclarationCheck__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return edu.umn.cs.melt.ableC.abstractsyntax.host.PdoNotDoValueRedeclarationCheck.factory; } };
		// top.valueRedeclarationCheckNoCompatible = []
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueRedeclarationCheckNoCompatible__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.valueMergeRedeclExtnQualifiers = \ t::Type  -> errorType(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueMergeRedeclExtnQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() {
  public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_6273_t = args[0];

    return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PerrorType());
  }
}); } };
		// top.valueLocalLookup = []
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.tagLocalLookup = []
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.tagHasForwardDcl = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagHasForwardDcl__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.tagRefId = toString(genInt(,))
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagRefId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke()))); } };

	}

	public static final common.NodeFactory<PnothingName> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnothingName> {

		@Override
		public PnothingName invoke(final Object[] children, final Object[] annotations) {
			return new PnothingName();
		}
	};

}
