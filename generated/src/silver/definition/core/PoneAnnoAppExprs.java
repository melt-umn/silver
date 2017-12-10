
package silver.definition.core;

// top::AnnoAppExprs ::= e::AnnoExpr 
public final class PoneAnnoAppExprs extends silver.definition.core.NAnnoAppExprs {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NAnnoExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_oneAnnoAppExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NAnnoExpr.num_inh_attrs];

	}

	public PoneAnnoAppExprs(final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;

	}

	private Object child_e;
	public final silver.definition.core.NAnnoExpr getChild_e() {
		return (silver.definition.core.NAnnoExpr) (child_e = common.Util.demand(child_e));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:oneAnnoAppExprs erroneously claimed to forward");
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
		return "silver:definition:core:oneAnnoAppExprs";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e.pp
		silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PoneAnnoAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AnnoExpr)); } };
		// top.isPartial = e.isPartial
		silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)context.childDecorated(silver.definition.core.PoneAnnoAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AnnoExpr)); } };
		// top.errors := if null(top.missingAnnotations) then [] else [ err(top.location, "Missing named parameters for function '" ++ top.appExprApplied ++ "': " ++ implode(", ", map((.argName), top.missingAnnotations))) ]
		if(silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs] == null)
			silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs);
		((common.CollectionAttribute)silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_missingAnnotations__ON__silver_definition_core_AnnoAppExprs))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Missing named parameters for function '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_appExprApplied__ON__silver_definition_core_AnnoAppExprs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("': ")), (common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.type.Init.silver_definition_type_argName__ON__silver_definition_type_NamedArgType, context), context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_missingAnnotations__ON__silver_definition_core_AnnoAppExprs))); } }))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.errors <- e.errors
		if(silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs] == null)
			silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs);
		((common.CollectionAttribute)silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PoneAnnoAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoExpr)); } });
		// e.remainingFuncAnnotations = top.remainingFuncAnnotations
		silver.definition.core.PoneAnnoAppExprs.childInheritedAttributes[silver.definition.core.PoneAnnoAppExprs.i_e][silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoAppExprs)); } };
		// top.missingAnnotations = e.missingAnnotations
		silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_missingAnnotations__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PoneAnnoAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_missingAnnotations__ON__silver_definition_core_AnnoExpr)); } };
		// top.partialAnnoTypereps = e.partialAnnoTypereps
		silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_partialAnnoTypereps__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PoneAnnoAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_partialAnnoTypereps__ON__silver_definition_core_AnnoExpr)); } };
		// top.annoIndexConverted = e.annoIndexConverted
		silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_annoIndexConverted__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PoneAnnoAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_annoIndexConverted__ON__silver_definition_core_AnnoExpr)); } };
		// top.annoIndexSupplied = e.annoIndexSupplied
		silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_annoIndexSupplied__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PoneAnnoAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_annoIndexSupplied__ON__silver_definition_core_AnnoExpr)); } };
		// top.exprs = e.exprs
		silver.definition.core.PoneAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PoneAnnoAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AnnoExpr)); } };

	}

	public static final common.NodeFactory<PoneAnnoAppExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneAnnoAppExprs> {

		@Override
		public PoneAnnoAppExprs invoke(final Object[] children, final Object[] annotations) {
			return new PoneAnnoAppExprs(children[0], annotations[0]);
		}
	};

}
