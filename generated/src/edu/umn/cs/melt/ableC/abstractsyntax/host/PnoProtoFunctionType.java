
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::FunctionType ::= 
public final class PnoProtoFunctionType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_noProtoFunctionType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnoProtoFunctionType() {
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:noProtoFunctionType erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:noProtoFunctionType";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:noProtoFunctionType(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType()); } };
		// top.withoutExtensionQualifiers = edu:umn:cs:melt:ableC:abstractsyntax:host:noProtoFunctionType(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutExtensionQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType()); } };
		// top.mergeQualifiers = \ t2::FunctionType  -> noProtoFunctionType(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mergeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType>() {
  public final edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_4664_t2 = args[0];

    return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NFunctionType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType());
  }
}); } };
		// top.lpp = notext(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pnotext()); } };
		// top.rpp = text("()",)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("()")))); } };
		// top.mangledName = "noproto"
		edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("noproto")); } };
		// top.errors := []
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PnoProtoFunctionType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_FunctionType]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });

	}

	public static final common.NodeFactory<PnoProtoFunctionType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnoProtoFunctionType> {

		@Override
		public PnoProtoFunctionType invoke(final Object[] children, final Object[] annotations) {
			return new PnoProtoFunctionType();
		}
	};

}
