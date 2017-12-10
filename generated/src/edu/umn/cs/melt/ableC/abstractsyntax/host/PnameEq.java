
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PnameEq extends common.FunctionNode {

	public static final int i_n1 = 0;
	public static final int i_n2 = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NName.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NName.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_nameEq;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_n1] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NName.num_inh_attrs];
	childInheritedAttributes[i_n2] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NName.num_inh_attrs];

	}

	public PnameEq(final Object c_n1, final Object c_n2) {
		this.child_n1 = c_n1;
		this.child_n2 = c_n2;

	}

	private Object child_n1;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NName getChild_n1() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName) (child_n1 = common.Util.demand(child_n1));
	}

	private Object child_n2;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NName getChild_n2() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName) (child_n2 = common.Util.demand(child_n2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n1: return getChild_n1();
			case i_n2: return getChild_n2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n1: return child_n1;
			case i_n2: return child_n2;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:nameEq";
	}

	public static Boolean invoke(final Object c_n1, final Object c_n2) {
		try {
		final common.DecoratedNode context = new PnameEq(c_n1, c_n2).decorate();
		//n1.name == n2.name
		return (Boolean)(((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PnameEq.i_n1).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)).equals(((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PnameEq.i_n2).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:nameEq", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnameEq.invoke(children[0], children[1]);
		}
	};
}