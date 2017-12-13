
package silver.definition.core;

// top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs 
public final class PfunctionApplication extends silver.definition.core.NExpr {

	public static final int i_e = 0;
	public static final int i_es = 1;
	public static final int i_anns = 2;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,silver.definition.core.NAppExprs.class,silver.definition.core.NAnnoAppExprs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_functionApplication;
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

	public PfunctionApplication(final Object c_e, final Object c_es, final Object c_anns, final Object a_core_location) {
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((((Boolean)context.childDecorated(silver.definition.core.PfunctionApplication.i_es).synthesized(silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AppExprs)) || ((Boolean)context.childDecorated(silver.definition.core.PfunctionApplication.i_anns).synthesized(silver.definition.core.Init.silver_definition_core_isPartial__ON__silver_definition_core_AnnoAppExprs))) ? ((silver.definition.core.NExpr)new silver.definition.core.PpartialApplication(context.childAsIsLazy(silver.definition.core.PfunctionApplication.i_e), context.childDecoratedLazy(silver.definition.core.PfunctionApplication.i_es), context.localDecoratedLazy(silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NExpr)new silver.definition.core.PfunctionInvocation(context.childAsIsLazy(silver.definition.core.PfunctionApplication.i_e), context.childDecoratedLazy(silver.definition.core.PfunctionApplication.i_es), context.localDecoratedLazy(silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })));
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
		return "silver:definition:core:functionApplication";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e.pp ++ "(" ++ es.pp ++ "," ++ anns.pp ++ ")"
		silver.definition.core.PfunctionApplication.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PfunctionApplication.i_e)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PfunctionApplication.i_es).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AppExprs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PfunctionApplication.i_anns).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AnnoAppExprs)), (common.StringCatter)(new common.StringCatter(")"))))))); } };
		// t = performSubstitution(e.typerep, e.upSubst)
		silver.definition.core.PfunctionApplication.localAttributes[silver.definition.core.Init.t__ON__silver_definition_core_functionApplication] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childAsIsSynthesizedLazy(silver.definition.core.PfunctionApplication.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.childAsIsSynthesizedLazy(silver.definition.core.PfunctionApplication.i_e, silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr))); } };
		// es.appExprTypereps = reverse(t.inputTypes)
		silver.definition.core.PfunctionApplication.childInheritedAttributes[silver.definition.core.PfunctionApplication.i_es][silver.definition.core.Init.silver_definition_core_appExprTypereps__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Preverse.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.t__ON__silver_definition_core_functionApplication).synthesized(silver.definition.type.Init.silver_definition_type_inputTypes__ON__silver_definition_type_Type)); } })); } };
		// es.appExprApplied = e.pp
		silver.definition.core.PfunctionApplication.childInheritedAttributes[silver.definition.core.PfunctionApplication.i_es][silver.definition.core.Init.silver_definition_core_appExprApplied__ON__silver_definition_core_AppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PfunctionApplication.i_e)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)); } };
		// anns.appExprApplied = e.pp
		silver.definition.core.PfunctionApplication.childInheritedAttributes[silver.definition.core.PfunctionApplication.i_anns][silver.definition.core.Init.silver_definition_core_appExprApplied__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PfunctionApplication.i_e)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)); } };
		// anns.remainingFuncAnnotations = t.namedTypes
		silver.definition.core.PfunctionApplication.childInheritedAttributes[silver.definition.core.PfunctionApplication.i_anns][silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.t__ON__silver_definition_core_functionApplication).synthesized(silver.definition.type.Init.silver_definition_type_namedTypes__ON__silver_definition_type_Type)); } };
		// anns.funcAnnotations = anns.remainingFuncAnnotations
		silver.definition.core.PfunctionApplication.childInheritedAttributes[silver.definition.core.PfunctionApplication.i_anns][silver.definition.core.Init.silver_definition_core_funcAnnotations__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PfunctionApplication.i_anns).inherited(silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoAppExprs)); } };
		// annsWithDefaults = fillMissingAnnos(anns, t.namedTypes, top.defaultInheritedAnnos)
		silver.definition.core.PfunctionApplication.localAttributes[silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)silver.definition.core.PfillMissingAnnos.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PfunctionApplication.i_anns)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.t__ON__silver_definition_core_functionApplication).synthesized(silver.definition.type.Init.silver_definition_type_namedTypes__ON__silver_definition_type_Type)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_defaultInheritedAnnos__ON__silver_definition_core_Expr))); } };
		// annsWithDefaults.appExprApplied = e.pp
		silver.definition.core.PfunctionApplication.localInheritedAttributes[silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication][silver.definition.core.Init.silver_definition_core_appExprApplied__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PfunctionApplication.i_e)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)); } };
		// annsWithDefaults.remainingFuncAnnotations = t.namedTypes
		silver.definition.core.PfunctionApplication.localInheritedAttributes[silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication][silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.t__ON__silver_definition_core_functionApplication).synthesized(silver.definition.type.Init.silver_definition_type_namedTypes__ON__silver_definition_type_Type)); } };
		// annsWithDefaults.funcAnnotations = annsWithDefaults.remainingFuncAnnotations
		silver.definition.core.PfunctionApplication.localInheritedAttributes[silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication][silver.definition.core.Init.silver_definition_core_funcAnnotations__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication).inherited(silver.definition.core.Init.silver_definition_core_remainingFuncAnnotations__ON__silver_definition_core_AnnoAppExprs)); } };
		// annsWithDefaults.env = anns.env
		silver.definition.core.PfunctionApplication.localInheritedAttributes[silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.definition.core.PfunctionApplication.i_anns).inherited(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AnnoAppExprs)); } };
		// annsWithDefaults.downSubst = anns.downSubst
		silver.definition.core.PfunctionApplication.localInheritedAttributes[silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.definition.core.PfunctionApplication.i_anns).inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_AnnoAppExprs)); } };
		// annsWithDefaults.finalSubst = anns.finalSubst
		silver.definition.core.PfunctionApplication.localInheritedAttributes[silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.definition.core.PfunctionApplication.i_anns).inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_AnnoAppExprs)); } };
		// annsWithDefaults.frame = anns.frame
		silver.definition.core.PfunctionApplication.localInheritedAttributes[silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication][silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NBlockContext)context.childDecorated(silver.definition.core.PfunctionApplication.i_anns).inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_AnnoAppExprs)); } };
		// annsWithDefaults.defaultInheritedAnnos = top.defaultInheritedAnnos
		silver.definition.core.PfunctionApplication.localInheritedAttributes[silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication][silver.definition.core.Init.silver_definition_core_defaultInheritedAnnos__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.definition.core.Init.silver_definition_core_defaultInheritedAnnos__ON__silver_definition_core_Expr)); } };
		// annsWithDefaults.flowEnv = anns.flowEnv
		silver.definition.core.PfunctionApplication.localInheritedAttributes[silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication][silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.definition.core.PfunctionApplication.i_anns).inherited(silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AnnoAppExprs)); } };
		// annsWithDefaults.config = anns.config
		silver.definition.core.PfunctionApplication.localInheritedAttributes[silver.definition.core.Init.annsWithDefaults__ON__silver_definition_core_functionApplication][silver.definition.core.Init.silver_definition_env_config__ON__silver_definition_core_AnnoAppExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.definition.core.PfunctionApplication.i_anns).inherited(silver.definition.core.Init.silver_definition_env_config__ON__silver_definition_core_AnnoAppExprs)); } };

	}

	public static final common.NodeFactory<PfunctionApplication> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfunctionApplication> {

		@Override
		public PfunctionApplication invoke(final Object[] children, final Object[] annotations) {
			return new PfunctionApplication(children[0], children[1], children[2], annotations[0]);
		}
	};

}
