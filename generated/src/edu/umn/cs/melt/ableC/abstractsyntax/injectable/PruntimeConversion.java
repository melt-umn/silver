
package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

// top::RuntimeMod ::= conv::(Expr ::= Expr) 
public final class PruntimeConversion extends edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod {

	public static final int i_conv = 0;


	public static final Class<?> childTypes[] = {common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_runtimeConversion;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PruntimeConversion(final Object c_conv) {
		super();
		this.child_conv = c_conv;

	}

	private Object child_conv;
	public final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> getChild_conv() {
		return (common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>) (child_conv = common.Util.demand(child_conv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_conv: return getChild_conv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_conv: return child_conv;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:injectable:runtimeConversion erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:injectable:runtimeConversion";
	}

	static void initProductionAttributeDefinitions() {
		// top.modExpr = conv(top.exprToModify,)
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PruntimeConversion.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PruntimeConversion.i_conv)).invoke(new Object[]{context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod)}, null)); } };

	}

	public static final common.NodeFactory<PruntimeConversion> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PruntimeConversion> {

		@Override
		public PruntimeConversion invoke(final Object[] children, final Object[] annotations) {
			return new PruntimeConversion(children[0]);
		}
	};

}
