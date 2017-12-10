
package edu.umn.cs.melt.ableC.abstractsyntax.env;

// top::Env ::= d::Defs e::Decorated Env 
public final class PaddEnv_i extends edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv {

	public static final int i_d = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_d] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.num_inh_attrs];

	}

	public PaddEnv_i(final Object c_d, final Object c_e) {
		super();
		this.child_d = c_d;
		this.child_e = c_e;

	}

	private Object child_d;
	public final edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs getChild_d() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs) (child_d = common.Util.demand(child_d));
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:env:addEnv_i erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:addEnv_i";
	}

	static void initProductionAttributeDefinitions() {
		// gd = foldr(consDefs, nilDefs(,), d.globalDefs,)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.gd__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs)core.Pfoldr.invoke(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs)new edu.umn.cs.melt.ableC.abstractsyntax.env.PnilDefs()); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.i_d, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs))); } };
		// top.labels = augmentGlobalScope_i(gd.labelContribs, augmentScope_i(d.labelContribs, e.labels,),)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labels__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.gd__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labelContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentScope_i.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.i_d, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labelContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs), context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.i_e, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labels__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env))); } })); } };
		// top.tags = augmentGlobalScope_i(gd.tagContribs, augmentScope_i(d.tagContribs, e.tags,),)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tags__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.gd__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tagContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentScope_i.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.i_d, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tagContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs), context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.i_e, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tags__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env))); } })); } };
		// top.values = augmentGlobalScope_i(gd.valueContribs, augmentScope_i(d.valueContribs, e.values,),)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_values__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.gd__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_valueContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentScope_i.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.i_d, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_valueContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs), context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.i_e, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_values__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env))); } })); } };
		// top.refIds = augmentGlobalScope_i(gd.refIdContribs, augmentScope_i(d.refIdContribs, e.refIds,),)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIds__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.gd__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIdContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentScope_i.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.i_d, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIdContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs), context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.i_e, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIds__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env))); } })); } };
		// top.misc = augmentGlobalScope_i(gd.miscContribs, augmentScope_i(d.miscContribs, e.misc,),)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_misc__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.gd__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_miscContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentScope_i.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.i_d, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_miscContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs), context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.i_e, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_misc__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env))); } })); } };

	}

	public static final common.NodeFactory<PaddEnv_i> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaddEnv_i> {

		@Override
		public PaddEnv_i invoke(final Object[] children, final Object[] annotations) {
			return new PaddEnv_i(children[0], children[1]);
		}
	};

}
