
package silver.modification.primitivepattern;

// top::PrimPatterns ::= p::PrimPattern '|' ps::PrimPatterns 
public final class PconsPattern extends silver.modification.primitivepattern.NPrimPatterns {

	public static final int i_p = 0;
	public static final int i__G_1 = 1;
	public static final int i_ps = 2;


	public static final Class<?> childTypes[] = {silver.modification.primitivepattern.NPrimPattern.class,silver.extension.patternmatching.TVbar_kwd.class,silver.modification.primitivepattern.NPrimPatterns.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_consPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPatterns.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPatterns.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_p] = new common.Lazy[silver.modification.primitivepattern.NPrimPattern.num_inh_attrs];
	childInheritedAttributes[i_ps] = new common.Lazy[silver.modification.primitivepattern.NPrimPatterns.num_inh_attrs];

	}

	public PconsPattern(final Object c_p, final Object c__G_1, final Object c_ps, final Object a_core_location) {
		super(a_core_location);
		this.child_p = c_p;
		this.child__G_1 = c__G_1;
		this.child_ps = c_ps;

	}

	private Object child_p;
	public final silver.modification.primitivepattern.NPrimPattern getChild_p() {
		return (silver.modification.primitivepattern.NPrimPattern) (child_p = common.Util.demand(child_p));
	}

	private Object child__G_1;
	public final silver.extension.patternmatching.TVbar_kwd getChild__G_1() {
		return (silver.extension.patternmatching.TVbar_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_ps;
	public final silver.modification.primitivepattern.NPrimPatterns getChild_ps() {
		return (silver.modification.primitivepattern.NPrimPatterns) (child_ps = common.Util.demand(child_ps));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();
			case i__G_1: return getChild__G_1();
			case i_ps: return getChild_ps();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;
			case i__G_1: return child__G_1;
			case i_ps: return child_ps;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:primitivepattern:consPattern erroneously claimed to forward");
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
		return "silver:modification:primitivepattern:consPattern";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = p.pp ++ " | " ++ ps.pp
		silver.modification.primitivepattern.PconsPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPatterns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconsPattern.i_p).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPattern)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" | ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconsPattern.i_ps).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPatterns)))); } };
		// top.errors := p.errors ++ ps.errors
		if(silver.modification.primitivepattern.PconsPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPatterns] == null)
			silver.modification.primitivepattern.PconsPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPatterns] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPatterns);
		((common.CollectionAttribute)silver.modification.primitivepattern.PconsPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPatterns]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PconsPattern.i_p, silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern), context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PconsPattern.i_ps, silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPatterns))); } });
		// top.translation = p.translation ++ "\nelse " ++ ps.translation
		silver.modification.primitivepattern.PconsPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_PrimPatterns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconsPattern.i_p).synthesized(silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_PrimPattern)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nelse ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconsPattern.i_ps).synthesized(silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_PrimPatterns)))); } };
		// p.downSubst = top.downSubst
		silver.modification.primitivepattern.PconsPattern.childInheritedAttributes[silver.modification.primitivepattern.PconsPattern.i_p][silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_primitivepattern_PrimPatterns)); } };
		// ps.downSubst = p.upSubst
		silver.modification.primitivepattern.PconsPattern.childInheritedAttributes[silver.modification.primitivepattern.PconsPattern.i_ps][silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_primitivepattern_PrimPatterns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.primitivepattern.PconsPattern.i_p).synthesized(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// top.upSubst = ps.upSubst
		silver.modification.primitivepattern.PconsPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_primitivepattern_PrimPatterns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.primitivepattern.PconsPattern.i_ps).synthesized(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_primitivepattern_PrimPatterns)); } };

	}

	public static final common.NodeFactory<PconsPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsPattern> {

		@Override
		public PconsPattern invoke(final Object[] children, final Object[] annotations) {
			return new PconsPattern(children[0], children[1], children[2], annotations[0]);
		}
	};

}
