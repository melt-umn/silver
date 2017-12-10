
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax;

// p::Pattern ::= p1::Pattern '@' p2::Pattern 
public final class Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0 extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern {

	public static final int i_p1 = 0;
	public static final int i__G_1 = 1;
	public static final int i_p2 = 2;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern.class,edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.TNamedPatternOp_t.class,edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_p1] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern.num_inh_attrs];
	childInheritedAttributes[i_p2] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0(final Object c_p1, final Object c__G_1, final Object c_p2, final Object a_core_location) {
		super(a_core_location);
		this.child_p1 = c_p1;
		this.child__G_1 = c__G_1;
		this.child_p2 = c_p2;

	}

	private Object child_p1;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern getChild_p1() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern) (child_p1 = common.Util.demand(child_p1));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.TNamedPatternOp_t getChild__G_1() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.TNamedPatternOp_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_p2;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern getChild_p2() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern) (child_p2 = common.Util.demand(child_p2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p1: return getChild_p1();
			case i__G_1: return getChild__G_1();
			case i_p2: return getChild_p2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p1: return child_p1;
			case i__G_1: return child__G_1;
			case i_p2: return child_p2;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0";
	}

	static void initProductionAttributeDefinitions() {
		// p.ast = abs:patternBoth(p1.ast, p2.ast,location=p.location)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NPattern)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternBoth(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0.i_p1, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Pattern), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0.i_p2, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Pattern), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0> {

		@Override
		public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_35_0(children[0], children[1], children[2], annotations[0]);
		}
	};

}
