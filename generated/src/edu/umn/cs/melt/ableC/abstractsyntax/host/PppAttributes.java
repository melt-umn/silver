
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PppAttributes extends common.FunctionNode {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ppAttributes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.num_inh_attrs];

	}

	public PppAttributes(final Object c_l) {
		this.child_l = c_l;

	}

	private Object child_l;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes getChild_l() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:ppAttributes";
	}

	public static silver.langutil.pp.NDocument invoke(final Object c_l) {
		try {
		final common.DecoratedNode context = new PppAttributes(c_l).decorate();
		//terminate(space(,), l.pps,)
		return (silver.langutil.pp.NDocument)(((silver.langutil.pp.NDocument)silver.langutil.pp.Pterminate.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PppAttributes.i_l, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_pps__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attributes))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:ppAttributes", t);
		}
	}

	public static final common.NodeFactory<silver.langutil.pp.NDocument> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.langutil.pp.NDocument> {
		@Override
		public silver.langutil.pp.NDocument invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PppAttributes.invoke(children[0]);
		}
	};
}