
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PaddQualifiers extends common.FunctionNode {

	public static final int i_qs = 0;
	public static final int i_base = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_addQualifiers;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_base] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PaddQualifiers(final Object c_qs, final Object c_base) {
		this.child_qs = c_qs;
		this.child_base = c_base;

	}

	private Object child_qs;
	public final common.ConsCell getChild_qs() {
		return (common.ConsCell) (child_qs = common.Util.demand(child_qs));
	}

	private Object child_base;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_base() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_base = common.Util.demand(child_base));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qs: return getChild_qs();
			case i_base: return getChild_base();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qs: return child_qs;
			case i_base: return child_base;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:addQualifiers";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object c_qs, final Object c_base) {
		try {
		final common.DecoratedNode context = new PaddQualifiers(c_qs, c_base).decorate();
		//base.withTypeQualifiers
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PaddQualifiers.i_base).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:addQualifiers", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaddQualifiers.invoke(children[0], children[1]);
		}
	};
}