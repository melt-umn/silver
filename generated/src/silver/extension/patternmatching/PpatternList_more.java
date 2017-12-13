
package silver.extension.patternmatching;

// top::PatternList ::= p::Pattern ',' ps1::PatternList 
public final class PpatternList_more extends silver.extension.patternmatching.NPatternList {

	public static final int i_p = 0;
	public static final int i__G_1 = 1;
	public static final int i_ps1 = 2;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.NPattern.class,silver.definition.core.TComma_t.class,silver.extension.patternmatching.NPatternList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_patternList_more;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.patternmatching.NPatternList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.patternmatching.NPatternList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_p] = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];
	childInheritedAttributes[i_ps1] = new common.Lazy[silver.extension.patternmatching.NPatternList.num_inh_attrs];

	}

	public PpatternList_more(final Object c_p, final Object c__G_1, final Object c_ps1, final Object a_core_location) {
		super(a_core_location);
		this.child_p = c_p;
		this.child__G_1 = c__G_1;
		this.child_ps1 = c_ps1;

	}

	private Object child_p;
	public final silver.extension.patternmatching.NPattern getChild_p() {
		return (silver.extension.patternmatching.NPattern) (child_p = common.Util.demand(child_p));
	}

	private Object child__G_1;
	public final silver.definition.core.TComma_t getChild__G_1() {
		return (silver.definition.core.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_ps1;
	public final silver.extension.patternmatching.NPatternList getChild_ps1() {
		return (silver.extension.patternmatching.NPatternList) (child_ps1 = common.Util.demand(child_ps1));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();
			case i__G_1: return getChild__G_1();
			case i_ps1: return getChild_ps1();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;
			case i__G_1: return child__G_1;
			case i_ps1: return child_ps1;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:patternmatching:patternList_more erroneously claimed to forward");
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
		return "silver:extension:patternmatching:patternList_more";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = p.pp ++ ", " ++ ps1.pp
		silver.extension.patternmatching.PpatternList_more.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_PatternList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PpatternList_more.i_p).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_Pattern)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PpatternList_more.i_ps1).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_PatternList)))); } };
		// top.errors := p.errors ++ ps1.errors
		if(silver.extension.patternmatching.PpatternList_more.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList] == null)
			silver.extension.patternmatching.PpatternList_more.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList] = new silver.definition.core.CAerrors(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList);
		((common.CollectionAttribute)silver.extension.patternmatching.PpatternList_more.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PpatternList_more.i_p, silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern), context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PpatternList_more.i_ps1, silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList))); } });
		// top.patternList = (p :: ps1.patternList)
		silver.extension.patternmatching.PpatternList_more.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternList__ON__silver_extension_patternmatching_PatternList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.extension.patternmatching.PpatternList_more.i_p), context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PpatternList_more.i_ps1, silver.extension.patternmatching.Init.silver_extension_patternmatching_patternList__ON__silver_extension_patternmatching_PatternList))); } };

	}

	public static final common.NodeFactory<PpatternList_more> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpatternList_more> {

		@Override
		public PpatternList_more invoke(final Object[] children, final Object[] annotations) {
			return new PpatternList_more(children[0], children[1], children[2], annotations[0]);
		}
	};

}
