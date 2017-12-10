
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax;

// t::RefIdItem ::= adt::Decorated ADTDecl s::Decorated StructDecl 
public final class PadtRefIdItem extends edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem {

	public static final int i_adt = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtRefIdItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PadtRefIdItem(final Object c_adt, final Object c_s) {
		super();
		this.child_adt = c_adt;
		this.child_s = c_s;

	}

	private Object child_adt;
	public final common.DecoratedNode getChild_adt() {
		return (common.DecoratedNode) (child_adt = common.Util.demand(child_adt));
	}

	private Object child_s;
	public final common.DecoratedNode getChild_s() {
		return (common.DecoratedNode) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_adt: return getChild_adt();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_adt: return child_adt;
			case i_s: return child_s;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem)new edu.umn.cs.melt.ableC.abstractsyntax.env.PstructRefIdItem(context.childAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtRefIdItem.i_s)));
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:adtRefIdItem";
	}

	static void initProductionAttributeDefinitions() {
		// t.pp = text("ADTDecl: adt.refId=" ++ adt.refId ++ ", s.refId=" ++ s.refId,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtRefIdItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_RefIdItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("ADTDecl: adt.refId=")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtRefIdItem.i_adt)).synthesized(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_refId__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTDecl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", s.refId=")), (common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtRefIdItem.i_s)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_refId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl))))); } })); } };

	}

	public static final common.NodeFactory<PadtRefIdItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PadtRefIdItem> {

		@Override
		public PadtRefIdItem invoke(final Object[] children, final Object[] annotations) {
			return new PadtRefIdItem(children[0], children[1]);
		}
	};

}
