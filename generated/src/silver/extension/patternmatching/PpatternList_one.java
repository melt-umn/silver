
package silver.extension.patternmatching;

// top::PatternList ::= p::Pattern 
public final class PpatternList_one extends silver.extension.patternmatching.NPatternList {

	public static final int i_p = 0;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.NPattern.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_patternList_one;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.patternmatching.NPatternList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.patternmatching.NPatternList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_p] = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];

	}

	public PpatternList_one(final Object c_p, final Object a_core_location) {
		super(a_core_location);
		this.child_p = c_p;

	}

	private Object child_p;
	public final silver.extension.patternmatching.NPattern getChild_p() {
		return (silver.extension.patternmatching.NPattern) (child_p = common.Util.demand(child_p));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:patternmatching:patternList_one erroneously claimed to forward");
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
		return "silver:extension:patternmatching:patternList_one";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = p.pp
		silver.extension.patternmatching.PpatternList_one.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_PatternList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PpatternList_one.i_p).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_Pattern)); } };
		// top.errors := p.errors
		if(silver.extension.patternmatching.PpatternList_one.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList] == null)
			silver.extension.patternmatching.PpatternList_one.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList] = new silver.definition.core.CAerrors(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList);
		((common.CollectionAttribute)silver.extension.patternmatching.PpatternList_one.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.patternmatching.PpatternList_one.i_p).synthesized(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern)); } });
		// top.patternList = [ p ]
		silver.extension.patternmatching.PpatternList_one.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternList__ON__silver_extension_patternmatching_PatternList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.extension.patternmatching.PpatternList_one.i_p), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PpatternList_one> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpatternList_one> {

		@Override
		public PpatternList_one invoke(final Object[] children, final Object[] annotations) {
			return new PpatternList_one(children[0], annotations[0]);
		}
	};

}
