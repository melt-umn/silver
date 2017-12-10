
package silver.modification.lambda_fn;

// top::Expr ::= '\' params::ProductionRHS '->' e::Expr 
public final class Plambda_c extends silver.definition.core.NExpr {

	public static final int i__G_3 = 0;
	public static final int i_params = 1;
	public static final int i__G_1 = 2;
	public static final int i_e = 3;


	public static final Class<?> childTypes[] = {silver.modification.lambda_fn.TLambda_kwd.class,silver.definition.core.NProductionRHS.class,silver.modification.lambda_fn.TArrow_t.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_lambda_fn_lambda_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_params] = new common.Lazy[silver.definition.core.NProductionRHS.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public Plambda_c(final Object c__G_3, final Object c_params, final Object c__G_1, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child_params = c_params;
		this.child__G_1 = c__G_1;
		this.child_e = c_e;

	}

	private Object child__G_3;
	public final silver.modification.lambda_fn.TLambda_kwd getChild__G_3() {
		return (silver.modification.lambda_fn.TLambda_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_params;
	public final silver.definition.core.NProductionRHS getChild_params() {
		return (silver.definition.core.NProductionRHS) (child_params = common.Util.demand(child_params));
	}

	private Object child__G_1;
	public final silver.modification.lambda_fn.TArrow_t getChild__G_1() {
		return (silver.modification.lambda_fn.TArrow_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i_params: return getChild_params();
			case i__G_1: return getChild__G_1();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i_params: return child_params;
			case i__G_1: return child__G_1;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return ((silver.definition.core.NExpr)new silver.modification.lambda_fn.Plambdap(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.lambda_fn.Plambda_c.i_params)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.lambda_fn.Plambda_c.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:lambda_fn:lambda_c";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "\\ " ++ params.pp ++ " -> " ++ e.pp
		silver.modification.lambda_fn.Plambda_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\\ ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.lambda_fn.Plambda_c.i_params).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionRHS)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" -> ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.lambda_fn.Plambda_c.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr))))); } };

	}

	public static final common.NodeFactory<Plambda_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Plambda_c> {

		@Override
		public Plambda_c invoke(final Object[] children, final Object[] annotations) {
			return new Plambda_c(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
