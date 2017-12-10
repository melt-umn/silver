
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::ArrayType ::= size::Integer 
public final class PconstantArrayType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NArrayType {

	public static final int i_size = 0;


	public static final Class<?> childTypes[] = {Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_constantArrayType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NArrayType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NArrayType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PconstantArrayType(final Object c_size) {
		super();
		this.child_size = c_size;

	}

	private Object child_size;
	public final Integer getChild_size() {
		return (Integer) (child_size = common.Util.demand(child_size));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_size: return getChild_size();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_size: return child_size;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:constantArrayType erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:constantArrayType";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:constantArrayType(, size,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PconstantArrayType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ArrayType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NArrayType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconstantArrayType(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PconstantArrayType.i_size))); } };
		// top.pp = text(toString(size),)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PconstantArrayType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ArrayType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PconstantArrayType.i_size)))); } })); } };

	}

	public static final common.NodeFactory<PconstantArrayType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconstantArrayType> {

		@Override
		public PconstantArrayType invoke(final Object[] children, final Object[] annotations) {
			return new PconstantArrayType(children[0]);
		}
	};

}
