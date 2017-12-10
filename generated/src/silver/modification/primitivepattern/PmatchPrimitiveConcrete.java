
package silver.modification.primitivepattern;

// top::Expr ::= 'match' e::Expr 'return' t::TypeExpr 'with' pr::PrimPatterns 'else' '->' f::Expr 'end' 
public final class PmatchPrimitiveConcrete extends silver.definition.core.NExpr {

	public static final int i__G_9 = 0;
	public static final int i_e = 1;
	public static final int i__G_7 = 2;
	public static final int i_t = 3;
	public static final int i__G_5 = 4;
	public static final int i_pr = 5;
	public static final int i__G_3 = 6;
	public static final int i__G_2 = 7;
	public static final int i_f = 8;
	public static final int i__G_0 = 9;


	public static final Class<?> childTypes[] = {silver.modification.primitivepattern.TMatch_kwd.class,silver.definition.core.NExpr.class,silver.definition.core.TReturn_kwd.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TWith_kwd.class,silver.modification.primitivepattern.NPrimPatterns.class,silver.definition.core.TElse_kwd.class,silver.extension.patternmatching.TArrow_kwd.class,silver.definition.core.NExpr.class,silver.modification.let_fix.TEnd_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_matchPrimitiveConcrete;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[10][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_pr] = new common.Lazy[silver.modification.primitivepattern.NPrimPatterns.num_inh_attrs];
	childInheritedAttributes[i_f] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PmatchPrimitiveConcrete(final Object c__G_9, final Object c_e, final Object c__G_7, final Object c_t, final Object c__G_5, final Object c_pr, final Object c__G_3, final Object c__G_2, final Object c_f, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_9 = c__G_9;
		this.child_e = c_e;
		this.child__G_7 = c__G_7;
		this.child_t = c_t;
		this.child__G_5 = c__G_5;
		this.child_pr = c_pr;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_f = c_f;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_9;
	public final silver.modification.primitivepattern.TMatch_kwd getChild__G_9() {
		return (silver.modification.primitivepattern.TMatch_kwd) (child__G_9 = common.Util.demand(child__G_9));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_7;
	public final silver.definition.core.TReturn_kwd getChild__G_7() {
		return (silver.definition.core.TReturn_kwd) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_5;
	public final silver.definition.core.TWith_kwd getChild__G_5() {
		return (silver.definition.core.TWith_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_pr;
	public final silver.modification.primitivepattern.NPrimPatterns getChild_pr() {
		return (silver.modification.primitivepattern.NPrimPatterns) (child_pr = common.Util.demand(child_pr));
	}

	private Object child__G_3;
	public final silver.definition.core.TElse_kwd getChild__G_3() {
		return (silver.definition.core.TElse_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.extension.patternmatching.TArrow_kwd getChild__G_2() {
		return (silver.extension.patternmatching.TArrow_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_f;
	public final silver.definition.core.NExpr getChild_f() {
		return (silver.definition.core.NExpr) (child_f = common.Util.demand(child_f));
	}

	private Object child__G_0;
	public final silver.modification.let_fix.TEnd_kwd getChild__G_0() {
		return (silver.modification.let_fix.TEnd_kwd) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_9: return getChild__G_9();
			case i_e: return getChild_e();
			case i__G_7: return getChild__G_7();
			case i_t: return getChild_t();
			case i__G_5: return getChild__G_5();
			case i_pr: return getChild_pr();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_f: return getChild_f();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_9: return child__G_9;
			case i_e: return child_e;
			case i__G_7: return child__G_7;
			case i_t: return child_t;
			case i__G_5: return child__G_5;
			case i_pr: return child_pr;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_f: return child_f;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 10;
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
		return ((silver.definition.core.NExpr)new silver.modification.primitivepattern.PmatchPrimitive(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PmatchPrimitiveConcrete.i_e)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PmatchPrimitiveConcrete.i_t)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PmatchPrimitiveConcrete.i_pr)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.primitivepattern.PmatchPrimitiveConcrete.i_f)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:primitivepattern:matchPrimitiveConcrete";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "match " ++ e.pp ++ " return " ++ t.pp ++ " with " ++ pr.pp ++ " else -> " ++ f.pp ++ "end"
		silver.modification.primitivepattern.PmatchPrimitiveConcrete.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("match ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveConcrete.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" return ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveConcrete.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" with ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveConcrete.i_pr).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPatterns)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" else -> ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveConcrete.i_f).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter("end")))))))))); } };

	}

	public static final common.NodeFactory<PmatchPrimitiveConcrete> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmatchPrimitiveConcrete> {

		@Override
		public PmatchPrimitiveConcrete invoke(final Object[] children, final Object[] annotations) {
			return new PmatchPrimitiveConcrete(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], children[9], annotations[0]);
		}
	};

}
