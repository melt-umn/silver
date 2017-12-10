
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax;

// ps::PatternList ::= p::ConstPattern ',' rest::PatternList 
public final class Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0 extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPatternList {

	public static final int i_p = 0;
	public static final int i__G_1 = 1;
	public static final int i_rest = 2;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NConstPattern.class,edu.umn.cs.melt.ableC.concretesyntax.TComma_t.class,edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPatternList.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPatternList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPatternList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_p] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NConstPattern.num_inh_attrs];
	childInheritedAttributes[i_rest] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPatternList.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0(final Object c_p, final Object c__G_1, final Object c_rest, final Object a_core_location) {
		super(a_core_location);
		this.child_p = c_p;
		this.child__G_1 = c__G_1;
		this.child_rest = c_rest;

	}

	private Object child_p;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NConstPattern getChild_p() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NConstPattern) (child_p = common.Util.demand(child_p));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TComma_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_rest;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPatternList getChild_rest() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPatternList) (child_rest = common.Util.demand(child_rest));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();
			case i__G_1: return getChild__G_1();
			case i_rest: return getChild_rest();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;
			case i__G_1: return child__G_1;
			case i_rest: return child_rest;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0";
	}

	static void initProductionAttributeDefinitions() {
		// ps.ast = abs:consPattern(p.ast, rest.ast,location=ps.location)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_PatternList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NPatternList)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PconsPattern(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0.i_p, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_ConstPattern), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0.i_rest, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_PatternList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPatternList)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0> {

		@Override
		public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_68_0(children[0], children[1], children[2], annotations[0]);
		}
	};

}
