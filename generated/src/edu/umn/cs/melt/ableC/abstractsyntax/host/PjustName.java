
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::MaybeName ::= n::Name 
public final class PjustName extends edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName {

	public static final int i_n = 0;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NName.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_justName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NName.num_inh_attrs];

	}

	public PjustName(final Object c_n) {
		super();
		this.child_n = c_n;

	}

	private Object child_n;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NName getChild_n() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:justName erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:justName";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:justName(, n.host,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName)new edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.i_n, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } };
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:justName(, n.lifted,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName)new edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.i_n, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } };
		// top.pp = n.pp
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.i_n).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } };
		// top.maybename = just(n,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybename__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.i_n)))); } };
		// top.hasName = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hasName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.valueRedeclarationCheck = n.valueRedeclarationCheck
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueRedeclarationCheck__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.NodeFactory<common.ConsCell>)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.i_n).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueRedeclarationCheck__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } };
		// top.valueRedeclarationCheckNoCompatible = n.valueRedeclarationCheckNoCompatible
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueRedeclarationCheckNoCompatible__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.i_n).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueRedeclarationCheckNoCompatible__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } };
		// top.valueMergeRedeclExtnQualifiers = n.valueMergeRedeclExtnQualifiers
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueMergeRedeclExtnQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType>)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.i_n).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueMergeRedeclExtnQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } };
		// top.valueLocalLookup = n.valueLocalLookup
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.i_n).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } };
		// top.tagLocalLookup = n.tagLocalLookup
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.i_n).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } };
		// top.tagHasForwardDcl = n.tagHasForwardDcl
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagHasForwardDcl__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.i_n).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagHasForwardDcl__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } };
		// top.tagRefId = n.tagRefId
		edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagRefId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName.i_n).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagRefId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } };

	}

	public static final common.NodeFactory<PjustName> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PjustName> {

		@Override
		public PjustName invoke(final Object[] children, final Object[] annotations) {
			return new PjustName(children[0]);
		}
	};

}
