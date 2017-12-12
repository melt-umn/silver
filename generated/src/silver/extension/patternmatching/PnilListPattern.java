
package silver.extension.patternmatching;

// top::Pattern ::= '[' ']' 
public final class PnilListPattern extends silver.extension.patternmatching.NPattern {

	public static final int i__G_1 = 0;
	public static final int i__G_0 = 1;


	public static final Class<?> childTypes[] = {silver.extension.list.TLSqr_t.class,silver.extension.list.TRSqr_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_nilListPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.patternmatching.NPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilListPattern(final Object c__G_1, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_1 = c__G_1;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_1;
	public final silver.extension.list.TLSqr_t getChild__G_1() {
		return (silver.extension.list.TLSqr_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child__G_0;
	public final silver.extension.list.TRSqr_t getChild__G_0() {
		return (silver.extension.list.TRSqr_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:patternmatching:nilListPattern erroneously claimed to forward");
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
		return "silver:extension:patternmatching:nilListPattern";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "[]"
		silver.extension.patternmatching.PnilListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("[]")); } };
		// top.errors := []
		if(silver.extension.patternmatching.PnilListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern] == null)
			silver.extension.patternmatching.PnilListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern] = new silver.definition.core.CAerrors(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern);
		((common.CollectionAttribute)silver.extension.patternmatching.PnilListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.patternSubPatternList = []
		silver.extension.patternmatching.PnilListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSubPatternList__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.patternSortKey = "core:nil"
		silver.extension.patternmatching.PnilListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSortKey__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("core:nil")); } };

	}

	public static final common.NodeFactory<PnilListPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilListPattern> {

		@Override
		public PnilListPattern invoke(final Object[] children, final Object[] annotations) {
			return new PnilListPattern(children[0], children[1], annotations[0]);
		}
	};

}
