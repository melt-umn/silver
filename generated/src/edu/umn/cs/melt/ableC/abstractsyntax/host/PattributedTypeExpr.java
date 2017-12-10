
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::BaseTypeExpr ::= attrs::Attributes bt::BaseTypeExpr 
public final class PattributedTypeExpr extends edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr {

	public static final int i_attrs = 0;
	public static final int i_bt = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_attributedTypeExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_attrs] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.num_inh_attrs];
	childInheritedAttributes[i_bt] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.num_inh_attrs];

	}

	public PattributedTypeExpr(final Object c_attrs, final Object c_bt) {
		super();
		this.child_attrs = c_attrs;
		this.child_bt = c_bt;

	}

	private Object child_attrs;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes getChild_attrs() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes) (child_attrs = common.Util.demand(child_attrs));
	}

	private Object child_bt;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr getChild_bt() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr) (child_bt = common.Util.demand(child_bt));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_attrs: return getChild_attrs();
			case i_bt: return getChild_bt();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_attrs: return child_attrs;
			case i_bt: return child_bt;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PinjectGlobalDeclsTypeExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsDecl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.PtypedefDecls(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedTypeExpr.i_attrs)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedTypeExpr.i_bt)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDeclarators)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsDeclarator(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDeclarator)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pdeclarator(common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.liftedName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_attributedTypeExpr)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttribute()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeInitializer)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingInitializer()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDeclarators)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilDeclarator()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecls)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilDecl()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PtypedefTypeExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilQualifier()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.liftedName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_attributedTypeExpr)))); } }));
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:attributedTypeExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = cat(ppAttributes(attrs,), bt.pp,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)edu.umn.cs.melt.ableC.abstractsyntax.host.PppAttributes.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedTypeExpr.i_attrs)))); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedTypeExpr.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr))); } };
		// liftedName = name("_attributedType_" ++ toString(genInt(,)),location=builtinLoc("host",))
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedTypeExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.liftedName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_attributedTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pname(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("_attributedType_")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke())))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)core.PbuiltinLoc.invoke((new common.StringCatter("host")))); } })); } };

	}

	public static final common.NodeFactory<PattributedTypeExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PattributedTypeExpr> {

		@Override
		public PattributedTypeExpr invoke(final Object[] children, final Object[] annotations) {
			return new PattributedTypeExpr(children[0], children[1]);
		}
	};

}
