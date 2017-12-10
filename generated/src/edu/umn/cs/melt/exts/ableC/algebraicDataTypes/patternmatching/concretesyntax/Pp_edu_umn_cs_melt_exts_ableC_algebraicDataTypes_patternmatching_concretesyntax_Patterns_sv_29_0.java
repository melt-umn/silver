
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax;

// p::Pattern ::= id::PatternName_t 
public final class Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0 extends edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern {

	public static final int i_id = 0;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.TPatternName_t.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0(final Object c_id, final Object a_core_location) {
		super(a_core_location);
		this.child_id = c_id;

	}

	private Object child_id;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.TPatternName_t getChild_id() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.TPatternName_t) (child_id = common.Util.demand(child_id));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:concretesyntax:p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0";
	}

	static void initProductionAttributeDefinitions() {
		// p.ast = if id.lexeme == "_" then abs:patternWildcard(,location=p.location) else abs:patternVariable(id.lexeme,location=p.location)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0.synthesizedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.TPatternName_t)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0.i_id)).lexeme).equals((new common.StringCatter("_"))) ? ((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NPattern)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternWildcard(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern)context.undecorate()).getAnno_core_location()); } })) : ((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.NPattern)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PpatternVariable(((common.StringCatter)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.TPatternName_t)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0.i_id)).lexeme), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.concretesyntax.NPattern)context.undecorate()).getAnno_core_location()); } }))); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0> {

		@Override
		public Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_concretesyntax_Patterns_sv_29_0(children[0], annotations[0]);
		}
	};

}
