
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PcontainsQualifier extends common.FunctionNode {

	public static final int i_q = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_containsQualifier;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_q] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PcontainsQualifier(final Object c_q, final Object c_t) {
		this.child_q = c_q;
		this.child_t = c_t;

	}

	private Object child_q;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier getChild_q() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier) (child_q = common.Util.demand(child_q));
	}

	private Object child_t;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_t() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;
			case i_t: return child_t;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:containsQualifier";
	}

	public static Boolean invoke(final Object c_q, final Object c_t) {
		try {
		final common.DecoratedNode context = new PcontainsQualifier(c_q, c_t).decorate();
		//containsBy(qualifierCompat, q, t.qualifiers,)
		return (Boolean)(((Boolean)core.PcontainsBy.invoke(edu.umn.cs.melt.ableC.abstractsyntax.host.PqualifierCompat.factory, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcontainsQualifier.i_q)), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PcontainsQualifier.i_t, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:containsQualifier", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcontainsQualifier.invoke(children[0], children[1]);
		}
	};
}