
package edu.umn.cs.melt.ableC.abstractsyntax.env;

// top::Env ::= e::Decorated Env 
public final class PopenScope_i extends edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_openScope_i;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PopenScope_i(final Object c_e) {
		super();
		this.child_e = c_e;

	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:env:openScope_i erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:openScope_i";
	}

	static void initProductionAttributeDefinitions() {
		// top.labels = (tm:empty(compareString,) :: e.labels)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope_i.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labels__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treemap.Pempty.invoke(core.PcompareString.factory)); } }, context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope_i.i_e, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labels__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env))); } };
		// top.tags = (tm:empty(compareString,) :: e.tags)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope_i.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tags__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treemap.Pempty.invoke(core.PcompareString.factory)); } }, context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope_i.i_e, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tags__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env))); } };
		// top.values = (tm:empty(compareString,) :: e.values)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope_i.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_values__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treemap.Pempty.invoke(core.PcompareString.factory)); } }, context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope_i.i_e, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_values__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env))); } };
		// top.refIds = (tm:empty(compareString,) :: e.refIds)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope_i.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIds__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treemap.Pempty.invoke(core.PcompareString.factory)); } }, context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope_i.i_e, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIds__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env))); } };
		// top.misc = (tm:empty(compareString,) :: e.misc)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope_i.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_misc__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treemap.Pempty.invoke(core.PcompareString.factory)); } }, context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope_i.i_e, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_misc__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env))); } };

	}

	public static final common.NodeFactory<PopenScope_i> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PopenScope_i> {

		@Override
		public PopenScope_i invoke(final Object[] children, final Object[] annotations) {
			return new PopenScope_i(children[0]);
		}
	};

}
