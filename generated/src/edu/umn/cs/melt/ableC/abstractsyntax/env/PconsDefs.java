
package edu.umn.cs.melt.ableC.abstractsyntax.env;

// top::Defs ::= h::Def t::Defs 
public final class PconsDefs extends edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.class,edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_consDefs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.num_inh_attrs];

	}

	public PconsDefs(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final edu.umn.cs.melt.ableC.abstractsyntax.env.NDef getChild_h() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.env.NDef) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs getChild_t() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:env:consDefs erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:consDefs";
	}

	static void initProductionAttributeDefinitions() {
		// top.labelContribs = h.labelContribs ++ t.labelContribs
		edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labelContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_h, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labelContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_t, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labelContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs))); } };
		// top.tagContribs = h.tagContribs ++ t.tagContribs
		edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tagContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_h, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tagContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_t, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tagContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs))); } };
		// top.valueContribs = h.valueContribs ++ t.valueContribs
		edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_valueContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_h, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_valueContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_t, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_valueContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs))); } };
		// top.refIdContribs = h.refIdContribs ++ t.refIdContribs
		edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIdContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_h, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIdContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_t, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIdContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs))); } };
		// top.miscContribs = h.miscContribs ++ t.miscContribs
		edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_miscContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_h, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_miscContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_t, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_miscContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs))); } };
		// top.globalDefs = h.globalDefs ++ t.globalDefs
		edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_h, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.i_t, edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs))); } };

	}

	public static final common.NodeFactory<PconsDefs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsDefs> {

		@Override
		public PconsDefs invoke(final Object[] children, final Object[] annotations) {
			return new PconsDefs(children[0], children[1]);
		}
	};

}
