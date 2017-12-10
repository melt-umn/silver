
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::TypeModifierExpr ::= 
public final class PbaseTypeExpr extends edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PbaseTypeExpr() {
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:baseTypeExpr erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:baseTypeExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:baseTypeExpr(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr()); } };
		// top.lpp = notext(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pnotext()); } };
		// top.rpp = notext(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pnotext()); } };
		// top.lifted = if ! null(top.typeModifiersIn,) then mty.lifted else baseTypeExpr(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeModifiersIn__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr)))) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.mty__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr)) : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr())); } };
		// mty = head(top.typeModifiersIn,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.mty__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)core.Phead.invoke(context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeModifiersIn__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr))); } };
		// mty.env = top.env
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.mty__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr)); } };
		// mty.baseType = top.typerep
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.mty__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_baseType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr)); } };
		// mty.typeModifiersIn = tail(top.typeModifiersIn,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.mty__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeModifiersIn__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeModifiersIn__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr))); } };
		// mty.returnType = top.returnType
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.mty__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_returnType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_returnType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr)); } };
		// top.typerep = top.baseType
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_baseType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr)); } };
		// top.errors := []
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.globalDecls := []
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new edu.umn.cs.melt.ableC.abstractsyntax.host.CAglobalDecls(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.freeVariables = []
		edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PbaseTypeExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbaseTypeExpr> {

		@Override
		public PbaseTypeExpr invoke(final Object[] children, final Object[] annotations) {
			return new PbaseTypeExpr();
		}
	};

}
