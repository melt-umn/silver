
package silver.extension.patternmatching;

// top::Pattern ::= str::String_t 
public final class PstrPattern extends silver.extension.patternmatching.NPattern {

	public static final int i_str = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TString_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_strPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.patternmatching.NPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PstrPattern(final Object c_str, final Object a_core_location) {
		super(a_core_location);
		this.child_str = c_str;

	}

	private Object child_str;
	public final silver.definition.core.TString_t getChild_str() {
		return (silver.definition.core.TString_t) (child_str = common.Util.demand(child_str));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_str: return getChild_str();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_str: return child_str;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:patternmatching:strPattern erroneously claimed to forward");
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
		return "silver:extension:patternmatching:strPattern";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = str.lexeme
		silver.extension.patternmatching.PstrPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.extension.patternmatching.PstrPattern.i_str)).lexeme); } };
		// top.errors := []
		if(silver.extension.patternmatching.PstrPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern] == null)
			silver.extension.patternmatching.PstrPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern] = new silver.definition.core.CAerrors(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern);
		((common.CollectionAttribute)silver.extension.patternmatching.PstrPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.patternSubPatternList = []
		silver.extension.patternmatching.PstrPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSubPatternList__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.patternSortKey = str.lexeme
		silver.extension.patternmatching.PstrPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSortKey__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.extension.patternmatching.PstrPattern.i_str)).lexeme); } };

	}

	public static final common.NodeFactory<PstrPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PstrPattern> {

		@Override
		public PstrPattern invoke(final Object[] children, final Object[] annotations) {
			return new PstrPattern(children[0], annotations[0]);
		}
	};

}
