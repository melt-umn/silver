
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax;

// top::ConstructorList ::= c::Constructor cl::ConstructorList 
public final class PconsConstructor extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructorList {

	public static final int i_c = 0;
	public static final int i_cl = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructor.class,edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructorList.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_consConstructor;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructorList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructorList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_c] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructor.num_inh_attrs];
	childInheritedAttributes[i_cl] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructorList.num_inh_attrs];

	}

	public PconsConstructor(final Object c_c, final Object c_cl) {
		super();
		this.child_c = c_c;
		this.child_cl = c_cl;

	}

	private Object child_c;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructor getChild_c() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructor) (child_c = common.Util.demand(child_c));
	}

	private Object child_cl;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructorList getChild_cl() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructorList) (child_cl = common.Util.demand(child_cl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_c: return getChild_c();
			case i_cl: return getChild_cl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_c: return child_c;
			case i_cl: return child_cl;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:consConstructor erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:consConstructor";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = ppConcat([ c.pp, sep, cl.pp ],)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_pp__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_c, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_pp__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_Constructor), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.sep__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_consConstructor)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_cl, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_pp__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
		// top.errors := c.errors ++ cl.errors
		if(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] == null)
			edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new silver.langutil.CAerrors(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList);
		((common.CollectionAttribute)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_c, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_Constructor), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_cl, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList))); } });
		// sep = case cl of consConstructor(_, _) -> line(,) | nilConstructor() -> notext(,) end
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.localAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.sep__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_consConstructor] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, silver.langutil.pp.NDocument>() { public final silver.langutil.pp.NDocument eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor) { final common.Thunk<Object> __SV_LOCAL___pv10116___sv_tmp_pv_10117 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv10118___sv_tmp_pv_10119 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (silver.langutil.pp.NDocument)((silver.langutil.pp.NDocument)new silver.langutil.pp.Pline()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PnilConstructor) {  return (silver.langutil.pp.NDocument)((silver.langutil.pp.NDocument)new silver.langutil.pp.Pnotext()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.langutil.pp.NDocument)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax 'DataType.sv', 285, 4, 288, 7, 9718, 9808\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_cl)); } };
		// top.enumItems = consEnumItem(c.enumItem, cl.enumItems,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_enumItems__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NEnumItemList)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsEnumItem(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_c, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_enumItem__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_Constructor), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_cl, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_enumItems__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList))); } };
		// top.structItems = consStructItem(c.structItem, cl.structItems,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_structItems__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsStructItem(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_c, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_structItem__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_Constructor), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_cl, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_structItems__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList))); } };
		// top.funDecls = consDecl(c.funDecl, cl.funDecls,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_funDecls__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsDecl(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_c, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_funDecl__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_Constructor), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_cl, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_funDecls__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList))); } };
		// c.topTypeName = top.topTypeName
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.childInheritedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_c][edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_topTypeName__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_Constructor] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_topTypeName__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList)); } };
		// cl.topTypeName = top.topTypeName
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.childInheritedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_cl][edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_topTypeName__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_topTypeName__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList)); } };
		// top.constructors = c.constructors ++ cl.constructors
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_constructors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_c, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_constructors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_Constructor), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor.i_cl, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_constructors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ConstructorList))); } };

	}

	public static final common.NodeFactory<PconsConstructor> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsConstructor> {

		@Override
		public PconsConstructor invoke(final Object[] children, final Object[] annotations) {
			return new PconsConstructor(children[0], children[1]);
		}
	};

}
