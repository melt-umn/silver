
package silver.definition.core;

// top::AppExprs ::= 
public final class PemptyAppExprs extends silver.definition.core.NAppExprs {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_emptyAppExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAppExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PemptyAppExprs(final Object a_core_location) {
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:emptyAppExprs erroneously claimed to forward");
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
		return "silver:definition:core:emptyAppExprs";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = ""
		silver.definition.core.PemptyAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.isPartial = false
		silver.definition.core.PemptyAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.missingTypereps = []
		silver.definition.core.PemptyAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_missingTypereps__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.rawExprs = []
		silver.definition.core.PemptyAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_rawExprs__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.exprs = []
		silver.definition.core.PemptyAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.appExprIndicies = []
		silver.definition.core.PemptyAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_appExprIndicies__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.appExprSize = 0
		silver.definition.core.PemptyAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_appExprSize__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// top.errors := if null(top.appExprTypereps) then [] else [ err(top.location, "Too few arguments provided to function '" ++ top.appExprApplied ++ "'") ]
		if(silver.definition.core.PemptyAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs] == null)
			silver.definition.core.PemptyAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs);
		((common.CollectionAttribute)silver.definition.core.PemptyAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_appExprTypereps__ON__silver_definition_core_AppExprs))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Too few arguments provided to function '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_appExprApplied__ON__silver_definition_core_AppExprs)), (common.StringCatter)(new common.StringCatter("'")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });

	}

	public static final common.NodeFactory<PemptyAppExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PemptyAppExprs> {

		@Override
		public PemptyAppExprs invoke(final Object[] children, final Object[] annotations) {
			return new PemptyAppExprs(annotations[0]);
		}
	};

}
