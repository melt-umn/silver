
package silver.extension.patternmatching;

// top::Pattern ::= hp::Pattern '::' tp::Pattern 
public final class PconsListPattern extends silver.extension.patternmatching.NPattern {

	public static final int i_hp = 0;
	public static final int i__G_1 = 1;
	public static final int i_tp = 2;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.NPattern.class,silver.definition.core.TColonColon_t.class,silver.extension.patternmatching.NPattern.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_consListPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.patternmatching.NPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_hp] = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];
	childInheritedAttributes[i_tp] = new common.Lazy[silver.extension.patternmatching.NPattern.num_inh_attrs];

	}

	public PconsListPattern(final Object c_hp, final Object c__G_1, final Object c_tp, final Object a_core_location) {
		super(a_core_location);
		this.child_hp = c_hp;
		this.child__G_1 = c__G_1;
		this.child_tp = c_tp;

	}

	private Object child_hp;
	public final silver.extension.patternmatching.NPattern getChild_hp() {
		return (silver.extension.patternmatching.NPattern) (child_hp = common.Util.demand(child_hp));
	}

	private Object child__G_1;
	public final silver.definition.core.TColonColon_t getChild__G_1() {
		return (silver.definition.core.TColonColon_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_tp;
	public final silver.extension.patternmatching.NPattern getChild_tp() {
		return (silver.extension.patternmatching.NPattern) (child_tp = common.Util.demand(child_tp));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_hp: return getChild_hp();
			case i__G_1: return getChild__G_1();
			case i_tp: return getChild_tp();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_hp: return child_hp;
			case i__G_1: return child__G_1;
			case i_tp: return child_tp;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:patternmatching:consListPattern erroneously claimed to forward");
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
		return "silver:extension:patternmatching:consListPattern";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = hp.pp ++ "::" ++ tp.pp
		silver.extension.patternmatching.PconsListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PconsListPattern.i_hp).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_Pattern)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("::")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PconsListPattern.i_tp).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_Pattern)))); } };
		// top.errors := hp.errors ++ tp.errors
		if(silver.extension.patternmatching.PconsListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern] == null)
			silver.extension.patternmatching.PconsListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern] = new silver.definition.core.CAerrors(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern);
		((common.CollectionAttribute)silver.extension.patternmatching.PconsListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PconsListPattern.i_hp, silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern), context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PconsListPattern.i_tp, silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern))); } });
		// top.patternSubPatternList = [ hp, tp ]
		silver.extension.patternmatching.PconsListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSubPatternList__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.extension.patternmatching.PconsListPattern.i_hp), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.extension.patternmatching.PconsListPattern.i_tp), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };
		// top.patternSortKey = "core:cons"
		silver.extension.patternmatching.PconsListPattern.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSortKey__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("core:cons")); } };

	}

	public static final common.NodeFactory<PconsListPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsListPattern> {

		@Override
		public PconsListPattern invoke(final Object[] children, final Object[] annotations) {
			return new PconsListPattern(children[0], children[1], children[2], annotations[0]);
		}
	};

}
