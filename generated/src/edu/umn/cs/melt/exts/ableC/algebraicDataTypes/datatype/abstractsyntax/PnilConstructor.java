
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax;

// top::ConstructorList ::= 
public final class PnilConstructor extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructorList {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_nilConstructor;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructorList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructorList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilConstructor() {
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:nilConstructor erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:nilConstructor";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = notext(,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PnilConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_pp__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pnotext()); } };
		// top.errors := []
		if(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PnilConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] == null)
			edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PnilConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new silver.langutil.CAerrors(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList);
		((common.CollectionAttribute)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PnilConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.enumItems = nilEnumItem(,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PnilConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_enumItems__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NEnumItemList)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilEnumItem()); } };
		// top.structItems = nilStructItem(,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PnilConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_structItems__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilStructItem()); } };
		// top.funDecls = nilDecl(,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PnilConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_funDecls__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilDecl()); } };
		// top.constructors = []
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PnilConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_constructors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PnilConstructor> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilConstructor> {

		@Override
		public PnilConstructor invoke(final Object[] children, final Object[] annotations) {
			return new PnilConstructor();
		}
	};

}
