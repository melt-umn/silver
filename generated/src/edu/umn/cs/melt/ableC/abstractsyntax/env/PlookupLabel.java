
package edu.umn.cs.melt.ableC.abstractsyntax.env;

public final class PlookupLabel extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_lookupLabel;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PlookupLabel(final Object c_n, final Object c_e) {
		this.child_n = c_n;
		this.child_e = c_e;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_e: return child_e;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:lookupLabel";
	}

	public static common.ConsCell invoke(final Object c_n, final Object c_e) {
		try {
		final common.DecoratedNode context = new PlookupLabel(c_n, c_e).decorate();
		//readScope_i(n, e.labels,)
		return (common.ConsCell)(((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PreadScope_i.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupLabel.i_n), context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupLabel.i_e, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labels__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:env:lookupLabel", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PlookupLabel.invoke(children[0], children[1]);
		}
	};
}