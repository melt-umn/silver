
package silver.modification.lambda_fn;

// top::Expr ::= params::ProductionRHS e::Expr 
public final class Plambdap extends silver.definition.core.NExpr {

	public static final int i_params = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NProductionRHS.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_lambda_fn_lambdap;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_params] = new common.Lazy[silver.definition.core.NProductionRHS.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public Plambdap(final Object c_params, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_params = c_params;
		this.child_e = c_e;

	}

	private Object child_params;
	public final silver.definition.core.NProductionRHS getChild_params() {
		return (silver.definition.core.NProductionRHS) (child_params = common.Util.demand(child_params));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_params: return getChild_params();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_params: return child_params;
			case i_e: return child_e;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:modification:lambda_fn:lambdap erroneously claimed to forward");
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
		return "silver:modification:lambda_fn:lambdap";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "\\ " ++ params.pp ++ " -> " ++ e.pp
		silver.modification.lambda_fn.Plambdap.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\\ ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.lambda_fn.Plambdap.i_params).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionRHS)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" -> ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.lambda_fn.Plambdap.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr))))); } };
		// top.errors := params.errors ++ e.errors
		if(silver.modification.lambda_fn.Plambdap.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.modification.lambda_fn.Plambdap.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.modification.lambda_fn.Plambdap.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.lambda_fn.Plambdap.i_params, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHS), context.childDecoratedSynthesizedLazy(silver.modification.lambda_fn.Plambdap.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr))); } });
		// top.typerep = functionType(e.typerep, map((.typerep), params.inputElements), [])
		silver.modification.lambda_fn.Plambdap.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PfunctionType(context.childDecoratedSynthesizedLazy(silver.modification.lambda_fn.Plambdap.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement, context), context.childDecoratedSynthesizedLazy(silver.modification.lambda_fn.Plambdap.i_params, silver.definition.core.Init.silver_definition_env_inputElements__ON__silver_definition_core_ProductionRHS))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// e.downSubst = top.downSubst
		silver.modification.lambda_fn.Plambdap.childInheritedAttributes[silver.modification.lambda_fn.Plambdap.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr)); } };
		// top.upSubst = e.upSubst
		silver.modification.lambda_fn.Plambdap.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.lambda_fn.Plambdap.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// top.flowDeps = e.flowDeps
		silver.modification.lambda_fn.Plambdap.synthesizedAttributes[silver.definition.flow.env.Init.silver_definition_flow_env_flowDeps__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.lambda_fn.Plambdap.i_e).synthesized(silver.definition.flow.env.Init.silver_definition_flow_env_flowDeps__ON__silver_definition_core_Expr)); } };
		// top.flowDefs = e.flowDefs
		silver.modification.lambda_fn.Plambdap.synthesizedAttributes[silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.lambda_fn.Plambdap.i_e).synthesized(silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_Expr)); } };
		// e.env = newScopeEnv(params.lambdaDefs, top.env)
		silver.modification.lambda_fn.Plambdap.childInheritedAttributes[silver.modification.lambda_fn.Plambdap.i_e][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.childDecoratedSynthesizedLazy(silver.modification.lambda_fn.Plambdap.i_params, silver.modification.lambda_fn.Init.silver_modification_lambda_fn_lambdaDefs__ON__silver_definition_core_ProductionRHS), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Expr))); } };

	}

	public static final common.NodeFactory<Plambdap> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Plambdap> {

		@Override
		public Plambdap invoke(final Object[] children, final Object[] annotations) {
			return new Plambdap(children[0], children[1], annotations[0]);
		}
	};

}
