
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PqualifierCat extends common.FunctionNode {

	public static final int i_q1 = 0;
	public static final int i_q2 = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_qualifierCat;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_q1] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.num_inh_attrs];
	childInheritedAttributes[i_q2] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.num_inh_attrs];

	}

	public PqualifierCat(final Object c_q1, final Object c_q2) {
		this.child_q1 = c_q1;
		this.child_q2 = c_q2;

	}

	private Object child_q1;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers getChild_q1() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers) (child_q1 = common.Util.demand(child_q1));
	}

	private Object child_q2;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers getChild_q2() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers) (child_q2 = common.Util.demand(child_q2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q1: return getChild_q1();
			case i_q2: return getChild_q2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q1: return child_q1;
			case i_q2: return child_q2;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:qualifierCat";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers invoke(final Object c_q1, final Object c_q2) {
		try {
		final common.DecoratedNode context = new PqualifierCat(c_q1, c_q2).decorate();
		//foldQualifier(q1.qualifiers ++ q2.qualifiers,)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)edu.umn.cs.melt.ableC.abstractsyntax.construction.PfoldQualifier.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PqualifierCat.i_q1, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PqualifierCat.i_q2, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:qualifierCat", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PqualifierCat.invoke(children[0], children[1]);
		}
	};
}