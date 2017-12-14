
package silver.definition.core;

// top::AppExprs ::= e::AppExpr 
public final class PoneAppExprs extends silver.definition.core.NAppExprs {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NAppExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_oneAppExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAppExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	}

	public PoneAppExprs(final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;

	}

	private Object child_e;
	public final silver.definition.core.NAppExpr getChild_e() {
		return (silver.definition.core.NAppExpr) (child_e = common.Util.demand(child_e));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:oneAppExprs erroneously claimed to forward");
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
		return "silver:definition:core:oneAppExprs";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e.pp
		silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PoneAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AppExpr)); } };
		// top.isPartial = e.isPartial
		silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)context.childDecorated(silver.definition.core.PoneAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AppExpr)); } };
		// top.missingTypereps = e.missingTypereps
		silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_missingTypereps__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PoneAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_missingTypereps__ON__silver_definition_core_AppExpr)); } };
		// top.rawExprs = e.rawExprs
		silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_rawExprs__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PoneAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_rawExprs__ON__silver_definition_core_AppExpr)); } };
		// top.exprs = e.exprs
		silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PoneAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AppExpr)); } };
		// top.appExprIndicies = e.appExprIndicies
		silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_appExprIndicies__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PoneAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_appExprIndicies__ON__silver_definition_core_AppExpr)); } };
		// top.errors := if null(top.appExprTypereps) then [ err(top.location, "Too many arguments provided to function '" ++ top.appExprApplied ++ "'") ] else if length(top.appExprTypereps) > 1 then [ err(top.location, "Too few arguments provided to function '" ++ top.appExprApplied ++ "'") ] else []
		if(silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs] == null)
			silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs);
		((common.CollectionAttribute)silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_appExprTypereps__ON__silver_definition_core_AppExprs))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Too many arguments provided to function '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_appExprApplied__ON__silver_definition_core_AppExprs)), (common.StringCatter)(new common.StringCatter("'")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((((Integer)core.PlistLength.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_appExprTypereps__ON__silver_definition_core_AppExprs))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExprs)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Too few arguments provided to function '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_appExprApplied__ON__silver_definition_core_AppExprs)), (common.StringCatter)(new common.StringCatter("'")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke()))); } });
		// top.errors <- e.errors
		if(silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs] == null)
			silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs);
		((common.CollectionAttribute)silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PoneAppExprs.i_e).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExpr)); } });
		// top.appExprSize = 1
		silver.definition.core.PoneAppExprs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_appExprSize__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
		// e.appExprIndex = 0
		silver.definition.core.PoneAppExprs.childInheritedAttributes[silver.definition.core.PoneAppExprs.i_e][silver.definition.core.Init.silver_definition_core_appExprIndex__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// e.appExprTyperep = if null(top.appExprTypereps) then errorType() else head(top.appExprTypereps)
		silver.definition.core.PoneAppExprs.childInheritedAttributes[silver.definition.core.PoneAppExprs.i_e][silver.definition.core.Init.silver_definition_core_appExprTyperep__ON__silver_definition_core_AppExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_appExprTypereps__ON__silver_definition_core_AppExprs))) ? ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()) : ((silver.definition.type.NType)core.Phead.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_appExprTypereps__ON__silver_definition_core_AppExprs)))); } };

	}

	public static final common.NodeFactory<PoneAppExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneAppExprs> {

		@Override
		public PoneAppExprs invoke(final Object[] children, final Object[] annotations) {
			return new PoneAppExprs(children[0], annotations[0]);
		}
	};

}
