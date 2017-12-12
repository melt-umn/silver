
package silver.modification.copper;

// top::ParserComponent ::= m::ModuleName mods::ParserComponentModifiers ';' 
public final class PparserComponent extends silver.modification.copper.NParserComponent {

	public static final int i_m = 0;
	public static final int i_mods = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NModuleName.class,silver.modification.copper.NParserComponentModifiers.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_parserComponent;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NParserComponent.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NParserComponent.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_m] = new common.Lazy[silver.definition.core.NModuleName.num_inh_attrs];
	childInheritedAttributes[i_mods] = new common.Lazy[silver.modification.copper.NParserComponentModifiers.num_inh_attrs];

	}

	public PparserComponent(final Object c_m, final Object c_mods, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_m = c_m;
		this.child_mods = c_mods;
		this.child__G_0 = c__G_0;

	}

	private Object child_m;
	public final silver.definition.core.NModuleName getChild_m() {
		return (silver.definition.core.NModuleName) (child_m = common.Util.demand(child_m));
	}

	private Object child_mods;
	public final silver.modification.copper.NParserComponentModifiers getChild_mods() {
		return (silver.modification.copper.NParserComponentModifiers) (child_mods = common.Util.demand(child_mods));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_m: return getChild_m();
			case i_mods: return getChild_mods();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_m: return child_m;
			case i_mods: return child_mods;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:parserComponent erroneously claimed to forward");
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
		return "silver:modification:copper:parserComponent";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = m.pp
		silver.modification.copper.PparserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.copper.PparserComponent.i_m).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleName)); } };
		// top.moduleNames = m.moduleNames
		silver.modification.copper.PparserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PparserComponent.i_m).synthesized(silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleName)); } };
		// top.errors := m.errors ++ mods.errors
		if(silver.modification.copper.PparserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent] == null)
			silver.modification.copper.PparserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent);
		((common.CollectionAttribute)silver.modification.copper.PparserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserComponent.i_m, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleName), context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserComponent.i_mods, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifiers))); } });
		// top.terminalPrefixes = mods.terminalPrefixes
		silver.modification.copper.PparserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PparserComponent.i_mods).synthesized(silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponentModifiers)); } };
		// top.liftedAGDcls = mods.liftedAGDcls
		silver.modification.copper.PparserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)context.childDecorated(silver.modification.copper.PparserComponent.i_mods).synthesized(silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponentModifiers)); } };
		// mods.componentGrammarName = head(m.moduleNames)
		silver.modification.copper.PparserComponent.childInheritedAttributes[silver.modification.copper.PparserComponent.i_mods][silver.modification.copper.Init.silver_modification_copper_componentGrammarName__ON__silver_modification_copper_ParserComponentModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserComponent.i_m, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleName))); } };

	}

	public static final common.NodeFactory<PparserComponent> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PparserComponent> {

		@Override
		public PparserComponent invoke(final Object[] children, final Object[] annotations) {
			return new PparserComponent(children[0], children[1], children[2], annotations[0]);
		}
	};

}
