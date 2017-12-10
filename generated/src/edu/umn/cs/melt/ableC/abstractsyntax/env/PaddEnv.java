
package edu.umn.cs.melt.ableC.abstractsyntax.env;

public final class PaddEnv extends common.FunctionNode {

	public static final int i_d = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PaddEnv(final Object c_d, final Object c_e) {
		this.child_d = c_d;
		this.child_e = c_e;

	}

	private Object child_d;
	public final common.ConsCell getChild_d() {
		return (common.ConsCell) (child_d = common.Util.demand(child_d));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:addEnv";
	}

	public static common.DecoratedNode invoke(final Object c_d, final Object c_e) {
		try {
		final common.DecoratedNode context = new PaddEnv(c_d, c_e).decorate();
		//if null(d,) then e else addEnvDefs(foldr(consDefs, nilDefs(,), d,), e,)
		return (common.DecoratedNode)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv.i_d))) ? ((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv.i_e)) : ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnvDefs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs)core.Pfoldr.invoke(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs)new edu.umn.cs.melt.ableC.abstractsyntax.env.PnilDefs()); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv.i_d))); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv.i_e)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:env:addEnv", t);
		}
	}

	public static final common.NodeFactory<common.DecoratedNode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.DecoratedNode> {
		@Override
		public common.DecoratedNode invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaddEnv.invoke(children[0], children[1]);
		}
	};
}