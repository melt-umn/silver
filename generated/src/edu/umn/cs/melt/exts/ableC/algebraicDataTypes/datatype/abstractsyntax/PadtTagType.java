
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax;

// t::Type ::= name::String adtRefId::String structRefId::String 
public final class PadtTagType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NType {

	public static final int i_name = 0;
	public static final int i_adtRefId = 1;
	public static final int i_structRefId = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PadtTagType(final Object c_name, final Object c_adtRefId, final Object c_structRefId) {
		super();
		this.child_name = c_name;
		this.child_adtRefId = c_adtRefId;
		this.child_structRefId = c_structRefId;

	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_adtRefId;
	public final common.StringCatter getChild_adtRefId() {
		return (common.StringCatter) (child_adtRefId = common.Util.demand(child_adtRefId));
	}

	private Object child_structRefId;
	public final common.StringCatter getChild_structRefId() {
		return (common.StringCatter) (child_structRefId = common.Util.demand(child_structRefId));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i_adtRefId: return getChild_adtRefId();
			case i_structRefId: return getChild_structRefId();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i_adtRefId: return child_adtRefId;
			case i_structRefId: return child_structRefId;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PtagType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilQualifier()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTagType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstructSEU()); } }, context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagType.i_name), context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagType.i_structRefId))); } }));
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:adtTagType";
	}

	static void initProductionAttributeDefinitions() {
		// t.lpp = text("ADT adtTagType(" ++ name ++ "," ++ adtRefId ++ "," ++ structRefId ++ ")",)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("ADT adtTagType(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagType.i_name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagType.i_adtRefId)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagType.i_structRefId)), (common.StringCatter)(new common.StringCatter(")")))))))); } })); } };
		// t.rpp = notext(,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pnotext()); } };
		// t.withTypeQualifiers = t
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };

	}

	public static final common.NodeFactory<PadtTagType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PadtTagType> {

		@Override
		public PadtTagType invoke(final Object[] children, final Object[] annotations) {
			return new PadtTagType(children[0], children[1], children[2]);
		}
	};

}
