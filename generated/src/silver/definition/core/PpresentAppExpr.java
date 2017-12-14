
package silver.definition.core;

// top::AppExpr ::= e::Expr 
public final class PpresentAppExpr extends silver.definition.core.NAppExpr {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_presentAppExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PpresentAppExpr(final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;

	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:presentAppExpr erroneously claimed to forward");
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
		return "silver:definition:core:presentAppExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e.pp
		silver.definition.core.PpresentAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PpresentAppExpr.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)); } };
		// top.isPartial = false
		silver.definition.core.PpresentAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.missingTypereps = []
		silver.definition.core.PpresentAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_missingTypereps__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.rawExprs = [ e ]
		silver.definition.core.PpresentAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_rawExprs__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PpresentAppExpr.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.exprs = [ e ]
		silver.definition.core.PpresentAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.definition.core.PpresentAppExpr.i_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.appExprIndicies = [ top.appExprIndex ]
		silver.definition.core.PpresentAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_appExprIndicies__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_appExprIndex__ON__silver_definition_core_AppExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := e.errors
		if(silver.definition.core.PpresentAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExpr] == null)
			silver.definition.core.PpresentAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExpr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExpr);
		((common.CollectionAttribute)silver.definition.core.PpresentAppExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PpresentAppExpr.i_e).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr)); } });

	}

	public static final common.NodeFactory<PpresentAppExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpresentAppExpr> {

		@Override
		public PpresentAppExpr invoke(final Object[] children, final Object[] annotations) {
			return new PpresentAppExpr(children[0], annotations[0]);
		}
	};

}
