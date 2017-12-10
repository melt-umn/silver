
package silver.definition.core;

// top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs 
public final class PerrorApplication extends silver.definition.core.NExpr {

	public static final int i_e = 0;
	public static final int i_es = 1;
	public static final int i_anns = 2;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,silver.definition.core.NAppExprs.class,silver.definition.core.NAnnoAppExprs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_errorApplication;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_es] = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];
	childInheritedAttributes[i_anns] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	}

	public PerrorApplication(final Object c_e, final Object c_es, final Object c_anns, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;
		this.child_es = c_es;
		this.child_anns = c_anns;

	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}

	private Object child_es;
	public final silver.definition.core.NAppExprs getChild_es() {
		return (silver.definition.core.NAppExprs) (child_es = common.Util.demand(child_es));
	}

	private Object child_anns;
	public final silver.definition.core.NAnnoAppExprs getChild_anns() {
		return (silver.definition.core.NAnnoAppExprs) (child_anns = common.Util.demand(child_anns));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i_es: return getChild_es();
			case i_anns: return getChild_anns();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
			case i_es: return child_es;
			case i_anns: return child_anns;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:errorApplication erroneously claimed to forward");
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
		return "silver:definition:core:errorApplication";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e.pp ++ "(" ++ es.pp ++ "," ++ anns.pp ++ ")"
		silver.definition.core.PerrorApplication.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PerrorApplication.i_e)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PerrorApplication.i_es).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AppExprs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PerrorApplication.i_anns).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AnnoAppExprs)), (common.StringCatter)(new common.StringCatter(")"))))))); } };
		// top.errors := e.errors ++ [ err(top.location, e.pp ++ " has type " ++ prettyType(performSubstitution(e.typerep, e.upSubst)) ++ " and cannot be invoked as a function.") ] ++ es.errors ++ anns.errors
		if(silver.definition.core.PerrorApplication.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PerrorApplication.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PerrorApplication.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsSynthesizedLazy(silver.definition.core.PerrorApplication.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PerrorApplication.i_e)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" has type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.type.PprettyType.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childAsIsSynthesizedLazy(silver.definition.core.PerrorApplication.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.childAsIsSynthesizedLazy(silver.definition.core.PerrorApplication.i_e, silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr))); } })), (common.StringCatter)(new common.StringCatter(" and cannot be invoked as a function."))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PerrorApplication.i_es, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AppExprs), context.childDecoratedSynthesizedLazy(silver.definition.core.PerrorApplication.i_anns, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AnnoAppExprs))); } })); } })); } });
		// top.typerep = errorType()
		silver.definition.core.PerrorApplication.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()); } };
		// es.appExprTypereps = []
		silver.definition.core.PerrorApplication.childInheritedAttributes[silver.definition.core.PerrorApplication.i_es][silver.definition.core.Init.silver_definition_core_appExprTypereps__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// es.appExprApplied = e.pp
		silver.definition.core.PerrorApplication.childInheritedAttributes[silver.definition.core.PerrorApplication.i_es][silver.definition.core.Init.silver_definition_core_appExprApplied__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PerrorApplication.i_e)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)); } };
		// anns.appExprApplied = e.pp
		silver.definition.core.PerrorApplication.childInheritedAttributes[silver.definition.core.PerrorApplication.i_anns][silver.definition.core.Init.silver_definition_core_appExprApplied__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PerrorApplication.i_e)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)); } };
		// anns.remainingFuncAnnotations = []
		silver.definition.core.PerrorApplication.childInheritedAttributes[silver.definition.core.PerrorApplication.i_anns][silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// anns.funcAnnotations = []
		silver.definition.core.PerrorApplication.childInheritedAttributes[silver.definition.core.PerrorApplication.i_anns][silver.definition.core.Init.silver_definition_core_funcAnnotations__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PerrorApplication> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PerrorApplication> {

		@Override
		public PerrorApplication invoke(final Object[] children, final Object[] annotations) {
			return new PerrorApplication(children[0], children[1], children[2], annotations[0]);
		}
	};

}
