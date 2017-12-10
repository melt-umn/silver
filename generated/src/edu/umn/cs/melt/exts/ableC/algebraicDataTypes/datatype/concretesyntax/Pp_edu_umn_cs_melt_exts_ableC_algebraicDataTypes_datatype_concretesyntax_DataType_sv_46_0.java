
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax;

// top::ConstructorList_c ::= c::Constructor_c cl::ConstructorList_c 
public final class Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0 extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c {

	public static final int i_c = 0;
	public static final int i_cl = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructor_c.class,edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_c] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructor_c.num_inh_attrs];
	childInheritedAttributes[i_cl] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0(final Object c_c, final Object c_cl) {
		super();
		this.child_c = c_c;
		this.child_cl = c_cl;

	}

	private Object child_c;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructor_c getChild_c() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructor_c) (child_c = common.Util.demand(child_c));
	}

	private Object child_cl;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c getChild_cl() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c) (child_cl = common.Util.demand(child_cl));
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = consConstructor(c.ast, cl.ast,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_ConstructorList_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructorList)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PconsConstructor(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0.i_c, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_Constructor_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0.i_cl, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_ConstructorList_c))); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0> {

		@Override
		public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0(children[0], children[1]);
		}
	};

}
