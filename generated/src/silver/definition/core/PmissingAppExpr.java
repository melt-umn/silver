
package silver.definition.core;

// top::AppExpr ::= '_' 
public final class PmissingAppExpr extends silver.definition.core.NAppExpr {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TUnderScore_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_missingAppExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PmissingAppExpr(final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.definition.core.TUnderScore_t getChild__G_0() {
		return (silver.definition.core.TUnderScore_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:missingAppExpr erroneously claimed to forward");
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
		return "silver:definition:core:missingAppExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "_"
		silver.definition.core.PmissingAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("_")); } };
		// top.isPartial = true
		silver.definition.core.PmissingAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.missingTypereps = [ top.appExprTyperep ]
		silver.definition.core.PmissingAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_missingTypereps__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_appExprTyperep__ON__silver_definition_core_AppExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.rawExprs = []
		silver.definition.core.PmissingAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_rawExprs__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.exprs = []
		silver.definition.core.PmissingAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.appExprIndicies = []
		silver.definition.core.PmissingAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_appExprIndicies__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.errors := []
		if(silver.definition.core.PmissingAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExpr] == null)
			silver.definition.core.PmissingAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExpr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExpr);
		((common.CollectionAttribute)silver.definition.core.PmissingAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });

	}

	public static final common.NodeFactory<PmissingAppExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmissingAppExpr> {

		@Override
		public PmissingAppExpr invoke(final Object[] children, final Object[] annotations) {
			return new PmissingAppExpr(children[0], annotations[0]);
		}
	};

}
