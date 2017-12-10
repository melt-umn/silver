
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::BuiltinType ::= 
public final class PvoidType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_voidType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PvoidType() {
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:voidType erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:voidType";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:voidType(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvoidType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PvoidType()); } };
		// top.pp = text("void",)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvoidType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("void")))); } };
		// top.mangledName = "void"
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvoidType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("void")); } };
		// top.integerPromotionsBuiltin = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvoidType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerPromotionsBuiltin__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.defaultArgumentPromotionsBuiltin = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvoidType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultArgumentPromotionsBuiltin__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.isIntegerType = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvoidType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isIntegerType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.isArithmeticType = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvoidType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isArithmeticType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };

	}

	public static final common.NodeFactory<PvoidType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PvoidType> {

		@Override
		public PvoidType invoke(final Object[] children, final Object[] annotations) {
			return new PvoidType();
		}
	};

}
