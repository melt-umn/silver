
package edu.umn.cs.melt.ableC.abstractsyntax.env;

// top::ValueItem ::= t::Type handler::(Expr ::= Name Exprs Location) 
public final class PbuiltinFunctionValueItem extends edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem {

	public static final int i_t = 0;
	public static final int i_handler = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_builtinFunctionValueItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PbuiltinFunctionValueItem(final Object c_t, final Object c_handler) {
		super();
		this.child_t = c_t;
		this.child_handler = c_handler;

	}

	private Object child_t;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_t() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_t = common.Util.demand(child_t));
	}

	private Object child_handler;
	public final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> getChild_handler() {
		return (common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>) (child_handler = common.Util.demand(child_handler));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i_handler: return getChild_handler();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i_handler: return child_handler;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:env:builtinFunctionValueItem erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:builtinFunctionValueItem";
	}

	static void initProductionAttributeDefinitions() {
		// top.typerep = t
		edu.umn.cs.melt.ableC.abstractsyntax.env.PbuiltinFunctionValueItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.env.PbuiltinFunctionValueItem.i_t).undecorate(); } };
		// top.sourceLocation = loc("<builtin>", 1, 0, 1, 0, 0, 1,)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PbuiltinFunctionValueItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_sourceLocation__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NLocation)new core.Ploc((new common.StringCatter("<builtin>")), Integer.valueOf((int)1), Integer.valueOf((int)0), Integer.valueOf((int)1), Integer.valueOf((int)0), Integer.valueOf((int)0), Integer.valueOf((int)1))); } };
		// top.directCallHandler = handler
		edu.umn.cs.melt.ableC.abstractsyntax.env.PbuiltinFunctionValueItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_directCallHandler__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.env.PbuiltinFunctionValueItem.i_handler)); } };

	}

	public static final common.NodeFactory<PbuiltinFunctionValueItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbuiltinFunctionValueItem> {

		@Override
		public PbuiltinFunctionValueItem invoke(final Object[] children, final Object[] annotations) {
			return new PbuiltinFunctionValueItem(children[0], children[1]);
		}
	};

}
