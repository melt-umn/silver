
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax;

// m::Match ::= '(' scrutinee::Expr_c ')' '(' cs::ExprClauses ')' 
public final class PmatchExpr_c extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NMatch {

	public static final int i__G_5 = 0;
	public static final int i_scrutinee = 1;
	public static final int i__G_3 = 2;
	public static final int i__G_2 = 3;
	public static final int i_cs = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.TLParen_t.class,edu.umn.cs.melt.ableC.concretesyntax.NExpr_c.class,edu.umn.cs.melt.ableC.concretesyntax.TRParen_t.class,edu.umn.cs.melt.ableC.concretesyntax.TLParen_t.class,edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NExprClauses.class,edu.umn.cs.melt.ableC.concretesyntax.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_matchExpr_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NMatch.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NMatch.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_scrutinee] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NExpr_c.num_inh_attrs];
	childInheritedAttributes[i_cs] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NExprClauses.num_inh_attrs];

	}

	public PmatchExpr_c(final Object c__G_5, final Object c_scrutinee, final Object c__G_3, final Object c__G_2, final Object c_cs, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child_scrutinee = c_scrutinee;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_cs = c_cs;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final edu.umn.cs.melt.ableC.concretesyntax.TLParen_t getChild__G_5() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TLParen_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_scrutinee;
	public final edu.umn.cs.melt.ableC.concretesyntax.NExpr_c getChild_scrutinee() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NExpr_c) (child_scrutinee = common.Util.demand(child_scrutinee));
	}

	private Object child__G_3;
	public final edu.umn.cs.melt.ableC.concretesyntax.TRParen_t getChild__G_3() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TRParen_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final edu.umn.cs.melt.ableC.concretesyntax.TLParen_t getChild__G_2() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TLParen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_cs;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NExprClauses getChild_cs() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NExprClauses) (child_cs = common.Util.demand(child_cs));
	}

	private Object child__G_0;
	public final edu.umn.cs.melt.ableC.concretesyntax.TRParen_t getChild__G_0() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i_scrutinee: return getChild_scrutinee();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_cs: return getChild_cs();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i_scrutinee: return child_scrutinee;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_cs: return child_cs;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:concretesyntax:matchExpr_c erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:concretesyntax:matchExpr_c";
	}

	static void initProductionAttributeDefinitions() {
		// m.ast = abs:matchExpr(scrutinee.ast, cs.ast,location=m.location)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.PmatchExpr_c.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Match] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmatchExpr(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.PmatchExpr_c.i_scrutinee, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_Expr_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.PmatchExpr_c.i_cs, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_ExprClauses), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NMatch)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PmatchExpr_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmatchExpr_c> {

		@Override
		public PmatchExpr_c invoke(final Object[] children, final Object[] annotations) {
			return new PmatchExpr_c(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
