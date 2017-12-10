
package edu.umn.cs.melt.ableC.abstractsyntax.env;

// top::Def ::= s::String l::TagItem 
public final class PtagDef extends edu.umn.cs.melt.ableC.abstractsyntax.env.NDef {

	public static final int i_s = 0;
	public static final int i_l = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_tagDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem.num_inh_attrs];

	}

	public PtagDef(final Object c_s, final Object c_l) {
		super();
		this.child_s = c_s;
		this.child_l = c_l;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_l;
	public final edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem getChild_l() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_l: return child_l;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:env:tagDef erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:tagDef";
	}

	static void initProductionAttributeDefinitions() {
		// top.tagContribs = [ pair(s, l,) ]
		edu.umn.cs.melt.ableC.abstractsyntax.env.PtagDef.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tagContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PtagDef.i_s), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PtagDef.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PtagDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtagDef> {

		@Override
		public PtagDef invoke(final Object[] children, final Object[] annotations) {
			return new PtagDef(children[0], children[1]);
		}
	};

}
