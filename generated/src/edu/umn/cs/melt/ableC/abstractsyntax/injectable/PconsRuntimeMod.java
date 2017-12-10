
package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

// top::RuntimeMods ::= h::RuntimeMod t::RuntimeMods 
public final class PconsRuntimeMod extends edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.class,edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_consRuntimeMod;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods.num_inh_attrs];

	}

	public PconsRuntimeMod(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod getChild_h() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods getChild_t() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMods) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:injectable:consRuntimeMod erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:injectable:consRuntimeMod";
	}

	static void initProductionAttributeDefinitions() {
		// h.exprToModify = t.modExpr
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsRuntimeMod.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsRuntimeMod.i_h][edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsRuntimeMod.i_t).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMods)); } };
		// top.modExpr = h.modExpr
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsRuntimeMod.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMods] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PconsRuntimeMod.i_h).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod)); } };

	}

	public static final common.NodeFactory<PconsRuntimeMod> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsRuntimeMod> {

		@Override
		public PconsRuntimeMod invoke(final Object[] children, final Object[] annotations) {
			return new PconsRuntimeMod(children[0], children[1]);
		}
	};

}
