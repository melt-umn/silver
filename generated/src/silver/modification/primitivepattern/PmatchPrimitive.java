
package silver.modification.primitivepattern;

// top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr 
public final class PmatchPrimitive extends silver.definition.core.NExpr {

	public static final int i_e = 0;
	public static final int i_t = 1;
	public static final int i_pr = 2;
	public static final int i_f = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,silver.definition.type.syntax.NTypeExpr.class,silver.modification.primitivepattern.NPrimPatterns.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_matchPrimitive;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_pr] = new common.Lazy[silver.modification.primitivepattern.NPrimPatterns.num_inh_attrs];
	childInheritedAttributes[i_f] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PmatchPrimitive(final Object c_e, final Object c_t, final Object c_pr, final Object c_f, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;
		this.child_t = c_t;
		this.child_pr = c_pr;
		this.child_f = c_f;

	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child_pr;
	public final silver.modification.primitivepattern.NPrimPatterns getChild_pr() {
		return (silver.modification.primitivepattern.NPrimPatterns) (child_pr = common.Util.demand(child_pr));
	}

	private Object child_f;
	public final silver.definition.core.NExpr getChild_f() {
		return (silver.definition.core.NExpr) (child_f = common.Util.demand(child_f));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i_t: return getChild_t();
			case i_pr: return getChild_pr();
			case i_f: return getChild_f();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
			case i_t: return child_t;
			case i_pr: return child_pr;
			case i_f: return child_f;

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
		return ((silver.definition.core.NExpr)new silver.modification.primitivepattern.PmatchPrimitiveReal(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.extension.patternmatching.PensureDecoratedExpr.invoke(context.childDecoratedLazy(silver.modification.primitivepattern.PmatchPrimitive.i_e))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PmatchPrimitive.i_t)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PmatchPrimitive.i_pr)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PmatchPrimitive.i_f)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:primitivepattern:matchPrimitive";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "match " ++ e.pp ++ " return " ++ t.pp ++ " with " ++ pr.pp ++ " else -> " ++ f.pp ++ "end"
		silver.modification.primitivepattern.PmatchPrimitive.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("match ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitive.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" return ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitive.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" with ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitive.i_pr).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPatterns)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" else -> ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitive.i_f).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter("end")))))))))); } };
		// e.downSubst = top.downSubst
		silver.modification.primitivepattern.PmatchPrimitive.childInheritedAttributes[silver.modification.primitivepattern.PmatchPrimitive.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr)); } };
		// forward.downSubst = e.upSubst
		silver.modification.primitivepattern.PmatchPrimitive.forwardInheritedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitive.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// top.errors <- e.errors
		if(silver.modification.primitivepattern.PmatchPrimitive.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.modification.primitivepattern.PmatchPrimitive.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.modification.primitivepattern.PmatchPrimitive.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitive.i_e).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr)); } });

	}

	public static final common.NodeFactory<PmatchPrimitive> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmatchPrimitive> {

		@Override
		public PmatchPrimitive invoke(final Object[] children, final Object[] annotations) {
			return new PmatchPrimitive(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
