
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax;

// top::Constructor_c ::= n::Identifier_t '(' ad::TypeNameList_c ')' ';' 
public final class Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0 extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructor_c {

	public static final int i_n = 0;
	public static final int i__G_3 = 1;
	public static final int i_ad = 2;
	public static final int i__G_1 = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t.class,edu.umn.cs.melt.ableC.concretesyntax.TLParen_t.class,edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NTypeNameList_c.class,edu.umn.cs.melt.ableC.concretesyntax.TRParen_t.class,edu.umn.cs.melt.ableC.concretesyntax.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructor_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructor_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ad] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NTypeNameList_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0(final Object c_n, final Object c__G_3, final Object c_ad, final Object c__G_1, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_n = c_n;
		this.child__G_3 = c__G_3;
		this.child_ad = c_ad;
		this.child__G_1 = c__G_1;
		this.child__G_0 = c__G_0;

	}

	private Object child_n;
	public final edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t getChild_n() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_3;
	public final edu.umn.cs.melt.ableC.concretesyntax.TLParen_t getChild__G_3() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TLParen_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_ad;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NTypeNameList_c getChild_ad() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NTypeNameList_c) (child_ad = common.Util.demand(child_ad));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TRParen_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TRParen_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child__G_0;
	public final edu.umn.cs.melt.ableC.concretesyntax.TSemi_t getChild__G_0() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i__G_3: return getChild__G_3();
			case i_ad: return getChild_ad();
			case i__G_1: return getChild__G_1();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i__G_3: return child__G_3;
			case i_ad: return child_ad;
			case i__G_1: return child__G_1;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = constructor(n.lexeme, ad.ast,location=top.location)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_Constructor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NConstructor)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Pconstructor(((common.StringCatter)((edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0.i_n)).lexeme), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0.i_ad, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_TypeNameList_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructor_c)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0> {

		@Override
		public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
