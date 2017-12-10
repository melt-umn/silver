
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax;

// a::ADTInfo ::= n::String 
public final class PadtInfo extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NADTInfo {

	public static final int i_n = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtInfo;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NADTInfo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NADTInfo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PadtInfo(final Object c_n) {
		super();
		this.child_n = c_n;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:adtInfo erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:adtInfo";
	}

	static void initProductionAttributeDefinitions() {
		// a.name = n
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtInfo.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtInfo.i_n)); } };

	}

	public static final common.NodeFactory<PadtInfo> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PadtInfo> {

		@Override
		public PadtInfo invoke(final Object[] children, final Object[] annotations) {
			return new PadtInfo(children[0]);
		}
	};

}
