
package edu.umn.cs.melt.ableC.abstractsyntax.env;

// top::Def ::= d::[Def] 
public final class PglobalDefsDef extends edu.umn.cs.melt.ableC.abstractsyntax.env.NDef {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefsDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PglobalDefsDef(final Object c_d) {
		super();
		this.child_d = c_d;

	}

	private Object child_d;
	public final common.ConsCell getChild_d() {
		return (common.ConsCell) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:env:globalDefsDef erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:globalDefsDef";
	}

	static void initProductionAttributeDefinitions() {
		// top.globalDefs = d ++ foldr(consDefs, nilDefs(,), d,).globalDefs
		edu.umn.cs.melt.ableC.abstractsyntax.env.PglobalDefsDef.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PglobalDefsDef.i_d), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs)core.Pfoldr.invoke(edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs)new edu.umn.cs.melt.ableC.abstractsyntax.env.PnilDefs()); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PglobalDefsDef.i_d))).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs)); } })); } };

	}

	public static final common.NodeFactory<PglobalDefsDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PglobalDefsDef> {

		@Override
		public PglobalDefsDef invoke(final Object[] children, final Object[] annotations) {
			return new PglobalDefsDef(children[0]);
		}
	};

}
