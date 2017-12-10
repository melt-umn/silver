
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PappendDecls extends common.FunctionNode {

	public static final int i_d1 = 0;
	public static final int i_d2 = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_appendDecls;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d1] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls.num_inh_attrs];
	childInheritedAttributes[i_d2] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls.num_inh_attrs];

	}

	public PappendDecls(final Object c_d1, final Object c_d2) {
		this.child_d1 = c_d1;
		this.child_d2 = c_d2;

	}

	private Object child_d1;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls getChild_d1() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls) (child_d1 = common.Util.demand(child_d1));
	}

	private Object child_d2;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls getChild_d2() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls) (child_d2 = common.Util.demand(child_d2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d1: return getChild_d1();
			case i_d2: return getChild_d2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d1: return child_d1;
			case i_d2: return child_d2;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:appendDecls";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls invoke(final Object c_d1, final Object c_d2) {
		try {
		final common.DecoratedNode context = new PappendDecls(c_d1, c_d2).decorate();
		//consDecl(decls(d1,), d2,)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsDecl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pdecls(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PappendDecls.i_d1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PappendDecls.i_d2)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:appendDecls", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PappendDecls.invoke(children[0], children[1]);
		}
	};
}