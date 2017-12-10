
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PappendExprs extends common.FunctionNode {

	public static final int i_e1 = 0;
	public static final int i_e2 = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_appendExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_e1] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs.num_inh_attrs];
	childInheritedAttributes[i_e2] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs.num_inh_attrs];

	}

	public PappendExprs(final Object c_e1, final Object c_e2) {
		this.child_e1 = c_e1;
		this.child_e2 = c_e2;

	}

	private Object child_e1;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs getChild_e1() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs) (child_e1 = common.Util.demand(child_e1));
	}

	private Object child_e2;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs getChild_e2() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs) (child_e2 = common.Util.demand(child_e2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e1: return getChild_e1();
			case i_e2: return getChild_e2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e1: return child_e1;
			case i_e2: return child_e2;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:appendExprs";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs invoke(final Object c_e1, final Object c_e2) {
		try {
		final common.DecoratedNode context = new PappendExprs(c_e1, c_e2).decorate();
		//e1.appendedRes
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PappendExprs.i_e1).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_appendedRes__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Exprs)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:appendExprs", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PappendExprs.invoke(children[0], children[1]);
		}
	};
}