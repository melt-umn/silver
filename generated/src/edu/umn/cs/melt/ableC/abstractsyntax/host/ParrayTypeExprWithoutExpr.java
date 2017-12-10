
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::TypeModifierExpr ::= element::TypeModifierExpr indexQualifiers::Qualifiers sizeModifier::ArraySizeModifier 
public final class ParrayTypeExprWithoutExpr extends edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr {

	public static final int i_element = 0;
	public static final int i_indexQualifiers = 1;
	public static final int i_sizeModifier = 2;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NArraySizeModifier.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_arrayTypeExprWithoutExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_element] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr.num_inh_attrs];
	childInheritedAttributes[i_indexQualifiers] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.num_inh_attrs];
	childInheritedAttributes[i_sizeModifier] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NArraySizeModifier.num_inh_attrs];

	}

	public ParrayTypeExprWithoutExpr(final Object c_element, final Object c_indexQualifiers, final Object c_sizeModifier) {
		super();
		this.child_element = c_element;
		this.child_indexQualifiers = c_indexQualifiers;
		this.child_sizeModifier = c_sizeModifier;

	}

	private Object child_element;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr getChild_element() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr) (child_element = common.Util.demand(child_element));
	}

	private Object child_indexQualifiers;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers getChild_indexQualifiers() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers) (child_indexQualifiers = common.Util.demand(child_indexQualifiers));
	}

	private Object child_sizeModifier;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NArraySizeModifier getChild_sizeModifier() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NArraySizeModifier) (child_sizeModifier = common.Util.demand(child_sizeModifier));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_element: return getChild_element();
			case i_indexQualifiers: return getChild_indexQualifiers();
			case i_sizeModifier: return getChild_sizeModifier();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_element: return child_element;
			case i_indexQualifiers: return child_indexQualifiers;
			case i_sizeModifier: return child_sizeModifier;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:arrayTypeExprWithoutExpr erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:arrayTypeExprWithoutExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:arrayTypeExprWithoutExpr(, element.host, indexQualifiers.host, sizeModifier,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_element, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_indexQualifiers, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_sizeModifier)))); } };
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:arrayTypeExprWithoutExpr(, element.lifted, indexQualifiers, sizeModifier,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_element, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_indexQualifiers)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_sizeModifier)))); } };
		// top.lpp = element.lpp
		edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_element).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr)); } };
		// top.rpp = cat(brackets(ppImplode(space(,), indexQualifiers.pps ++ sizeModifier.pps,),), element.rpp,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pbrackets.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppImplode.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_indexQualifiers, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_pps__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_sizeModifier, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_pps__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ArraySizeModifier))); } })); } })); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_element, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr))); } };
		// top.typerep = arrayType(element.typerep, indexQualifiers, sizeModifier, incompleteArrayType(,),)
		edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayType(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_element, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_indexQualifiers)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_sizeModifier)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NArrayType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PincompleteArrayType()); } })); } };
		// top.errors := element.errors ++ indexQualifiers.errors
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_element, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_indexQualifiers, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers))); } });
		// top.globalDecls := element.globalDecls
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new edu.umn.cs.melt.ableC.abstractsyntax.host.CAglobalDecls(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_element).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr)); } });
		// top.freeVariables = element.freeVariables
		edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_element).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr)); } };
		// indexQualifiers.typeToQualify = top.typerep
		edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.ParrayTypeExprWithoutExpr.i_indexQualifiers][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeToQualify__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr)); } };

	}

	public static final common.NodeFactory<ParrayTypeExprWithoutExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<ParrayTypeExprWithoutExpr> {

		@Override
		public ParrayTypeExprWithoutExpr invoke(final Object[] children, final Object[] annotations) {
			return new ParrayTypeExprWithoutExpr(children[0], children[1], children[2]);
		}
	};

}
