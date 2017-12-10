
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PcompatibleQualifiers extends common.FunctionNode {

	public static final int i_q1 = 0;
	public static final int i_q2 = 1;
	public static final int i_allowSubtypes = 2;
	public static final int i_dropOuterQual = 3;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.class,Boolean.class,Boolean.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compatibleQualifiers;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_q1] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.num_inh_attrs];
	childInheritedAttributes[i_q2] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.num_inh_attrs];

	}

	public PcompatibleQualifiers(final Object c_q1, final Object c_q2, final Object c_allowSubtypes, final Object c_dropOuterQual) {
		this.child_q1 = c_q1;
		this.child_q2 = c_q2;
		this.child_allowSubtypes = c_allowSubtypes;
		this.child_dropOuterQual = c_dropOuterQual;

	}

	private Object child_q1;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers getChild_q1() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers) (child_q1 = common.Util.demand(child_q1));
	}

	private Object child_q2;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers getChild_q2() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers) (child_q2 = common.Util.demand(child_q2));
	}

	private Object child_allowSubtypes;
	public final Boolean getChild_allowSubtypes() {
		return (Boolean) (child_allowSubtypes = common.Util.demand(child_allowSubtypes));
	}

	private Object child_dropOuterQual;
	public final Boolean getChild_dropOuterQual() {
		return (Boolean) (child_dropOuterQual = common.Util.demand(child_dropOuterQual));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q1: return getChild_q1();
			case i_q2: return getChild_q2();
			case i_allowSubtypes: return getChild_allowSubtypes();
			case i_dropOuterQual: return getChild_dropOuterQual();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q1: return child_q1;
			case i_q2: return child_q2;
			case i_allowSubtypes: return child_allowSubtypes;
			case i_dropOuterQual: return child_dropOuterQual;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:compatibleQualifiers";
	}

	public static Boolean invoke(final Object c_q1, final Object c_q2, final Object c_allowSubtypes, final Object c_dropOuterQual) {
		try {
		final common.DecoratedNode context = new PcompatibleQualifiers(c_q1, c_q2, c_allowSubtypes, c_dropOuterQual).decorate();
		//qualifiersSubtype(q2Filtered, q1Filtered,) && (allowSubtypes || qualifiersSubtype(q1FilteredHost, q2FilteredHost,))
		return (Boolean)((((Boolean)edu.umn.cs.melt.ableC.abstractsyntax.host.PqualifiersSubtype.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.q2Filtered__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compatibleQualifiers), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.q1Filtered__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compatibleQualifiers))) && (((Boolean)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleQualifiers.i_allowSubtypes)) || ((Boolean)edu.umn.cs.melt.ableC.abstractsyntax.host.PqualifiersSubtype.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.q1FilteredHost__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compatibleQualifiers), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.q2FilteredHost__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_compatibleQualifiers))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:compatibleQualifiers", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcompatibleQualifiers.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}