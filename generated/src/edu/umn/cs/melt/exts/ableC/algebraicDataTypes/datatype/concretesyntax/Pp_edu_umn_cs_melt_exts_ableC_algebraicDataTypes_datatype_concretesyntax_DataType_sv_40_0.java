
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax;

// top::ADTDecl_c ::= n::Identifier_t '{' c::ConstructorList_c '}' 
public final class Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0 extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NADTDecl_c {

	public static final int i_n = 0;
	public static final int i__G_2 = 1;
	public static final int i_c = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t.class,edu.umn.cs.melt.ableC.concretesyntax.TLCurly_t.class,edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c.class,edu.umn.cs.melt.ableC.concretesyntax.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NADTDecl_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NADTDecl_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_c] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0(final Object c_n, final Object c__G_2, final Object c_c, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_n = c_n;
		this.child__G_2 = c__G_2;
		this.child_c = c_c;
		this.child__G_0 = c__G_0;

	}

	private Object child_n;
	public final edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t getChild_n() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_2;
	public final edu.umn.cs.melt.ableC.concretesyntax.TLCurly_t getChild__G_2() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_c;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c getChild_c() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c) (child_c = common.Util.demand(child_c));
	}

	private Object child__G_0;
	public final edu.umn.cs.melt.ableC.concretesyntax.TRCurly_t getChild__G_0() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i__G_2: return getChild__G_2();
			case i_c: return getChild_c();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i__G_2: return child__G_2;
			case i_c: return child_c;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = datatypeDecl(adtDecl(fromId(n,), c.ast,),)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_ADTDecl_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NADTDecl)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtDecl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)edu.umn.cs.melt.ableC.abstractsyntax.construction.PfromId.invoke(context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0.i_n))); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0.i_c, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_ConstructorList_c))); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0> {

		@Override
		public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
