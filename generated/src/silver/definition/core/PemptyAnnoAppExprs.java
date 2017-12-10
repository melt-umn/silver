
package silver.definition.core;

// top::AnnoAppExprs ::= 
public final class PemptyAnnoAppExprs extends silver.definition.core.NAnnoAppExprs {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_emptyAnnoAppExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PemptyAnnoAppExprs(final Object a_core_location) {
		super(a_core_location);

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:emptyAnnoAppExprs erroneously claimed to forward");
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
		return "silver:definition:core:emptyAnnoAppExprs";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = ""
		silver.definition.core.PemptyAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.isPartial = false
		silver.definition.core.PemptyAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.errors := if null(top.missingAnnotations) then [] else [ err(top.location, "Missing named parameters for function '" ++ top.appExprApplied ++ "': " ++ implode(", ", map((.argName), top.missingAnnotations))) ]
		if(silver.definition.core.PemptyAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs] == null)
			silver.definition.core.PemptyAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs);
		((common.CollectionAttribute)silver.definition.core.PemptyAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_missingAnnotations__ON__silver_definition_core_AnnoAppExprs))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAnnoAppExprs)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Missing named parameters for function '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_appExprApplied__ON__silver_definition_core_AnnoAppExprs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("': ")), (common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.type.Init.silver_definition_type_argName__ON__silver_definition_type_NamedArgType, context), context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_missingAnnotations__ON__silver_definition_core_AnnoAppExprs))); } }))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.missingAnnotations = top.remainingFuncAnnotations
		silver.definition.core.PemptyAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_missingAnnotations__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoAppExprs)); } };
		// top.partialAnnoTypereps = []
		silver.definition.core.PemptyAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_partialAnnoTypereps__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.annoIndexConverted = []
		silver.definition.core.PemptyAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_annoIndexConverted__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.annoIndexSupplied = []
		silver.definition.core.PemptyAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_annoIndexSupplied__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.exprs = []
		silver.definition.core.PemptyAnnoAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PemptyAnnoAppExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PemptyAnnoAppExprs> {

		@Override
		public PemptyAnnoAppExprs invoke(final Object[] children, final Object[] annotations) {
			return new PemptyAnnoAppExprs(annotations[0]);
		}
	};

}
