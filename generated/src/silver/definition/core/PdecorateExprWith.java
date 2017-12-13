
package silver.definition.core;

// top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}' 
public final class PdecorateExprWith extends silver.definition.core.NExpr {

	public static final int i__G_5 = 0;
	public static final int i_e = 1;
	public static final int i__G_3 = 2;
	public static final int i__G_2 = 3;
	public static final int i_inh = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.definition.core.TDecorate_kwd.class,silver.definition.core.NExpr.class,silver.definition.core.TWith_kwd.class,silver.definition.core.TLCurly_t.class,silver.definition.core.NExprInhs.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_decorateExprWith;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_inh] = new common.Lazy[silver.definition.core.NExprInhs.num_inh_attrs];

	}

	public PdecorateExprWith(final Object c__G_5, final Object c_e, final Object c__G_3, final Object c__G_2, final Object c_inh, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child_e = c_e;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_inh = c_inh;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final silver.definition.core.TDecorate_kwd getChild__G_5() {
		return (silver.definition.core.TDecorate_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_3;
	public final silver.definition.core.TWith_kwd getChild__G_3() {
		return (silver.definition.core.TWith_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TLCurly_t getChild__G_2() {
		return (silver.definition.core.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_inh;
	public final silver.definition.core.NExprInhs getChild_inh() {
		return (silver.definition.core.NExprInhs) (child_inh = common.Util.demand(child_inh));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i_e: return getChild_e();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_inh: return getChild_inh();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i_e: return child_e;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_inh: return child_inh;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:decorateExprWith erroneously claimed to forward");
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
		return "silver:definition:core:decorateExprWith";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "decorate " ++ e.pp ++ " with {" ++ inh.pp ++ "}"
		silver.definition.core.PdecorateExprWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("decorate ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PdecorateExprWith.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" with {")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PdecorateExprWith.i_inh).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ExprInhs)), (common.StringCatter)(new common.StringCatter("}")))))); } };
		// top.typerep = decoratedType(performSubstitution(e.typerep, e.upSubst))
		silver.definition.core.PdecorateExprWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PdecoratedType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PdecorateExprWith.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.childDecoratedSynthesizedLazy(silver.definition.core.PdecorateExprWith.i_e, silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr))); } })); } };
		// top.errors := e.errors ++ inh.errors
		if(silver.definition.core.PdecorateExprWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PdecorateExprWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PdecorateExprWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PdecorateExprWith.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr), context.childDecoratedSynthesizedLazy(silver.definition.core.PdecorateExprWith.i_inh, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ExprInhs))); } });
		// inh.decoratingnt = performSubstitution(e.typerep, e.upSubst)
		silver.definition.core.PdecorateExprWith.childInheritedAttributes[silver.definition.core.PdecorateExprWith.i_inh][silver.definition.core.Init.silver_definition_core_decoratingnt__ON__silver_definition_core_ExprInhs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PdecorateExprWith.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.childDecoratedSynthesizedLazy(silver.definition.core.PdecorateExprWith.i_e, silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr))); } };

	}

	public static final common.NodeFactory<PdecorateExprWith> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdecorateExprWith> {

		@Override
		public PdecorateExprWith invoke(final Object[] children, final Object[] annotations) {
			return new PdecorateExprWith(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
