
package silver.modification.copper;

// top::ParserComponent ::= 'prefix' 'separator' s::String_t ';' 
public final class PprefixSeparatorParserComponent extends silver.modification.copper.NParserComponent {

	public static final int i__G_3 = 0;
	public static final int i__G_2 = 1;
	public static final int i_s = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.modification.copper.TPrefix_t.class,silver.modification.copper.TSeparator_kwd.class,silver.definition.core.TString_t.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_prefixSeparatorParserComponent;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NParserComponent.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NParserComponent.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PprefixSeparatorParserComponent(final Object c__G_3, final Object c__G_2, final Object c_s, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_s = c_s;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_3;
	public final silver.modification.copper.TPrefix_t getChild__G_3() {
		return (silver.modification.copper.TPrefix_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.modification.copper.TSeparator_kwd getChild__G_2() {
		return (silver.modification.copper.TSeparator_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_s;
	public final silver.definition.core.TString_t getChild_s() {
		return (silver.definition.core.TString_t) (child_s = common.Util.demand(child_s));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_s: return getChild_s();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_s: return child_s;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:prefixSeparatorParserComponent erroneously claimed to forward");
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
		return "silver:modification:copper:prefixSeparatorParserComponent";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "prefix separator " ++ s.lexeme ++ ";"
		silver.modification.copper.PprefixSeparatorParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("prefix separator ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.copper.PprefixSeparatorParserComponent.i_s)).lexeme), (common.StringCatter)(new common.StringCatter(";")))); } };
		// top.errors := []
		if(silver.modification.copper.PprefixSeparatorParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent] == null)
			silver.modification.copper.PprefixSeparatorParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent);
		((common.CollectionAttribute)silver.modification.copper.PprefixSeparatorParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.moduleNames = []
		silver.modification.copper.PprefixSeparatorParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.terminalPrefixes = []
		silver.modification.copper.PprefixSeparatorParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.liftedAGDcls = prefixSeparatorAGDcl($1, $2, $3, $4,location=top.location)
		silver.modification.copper.PprefixSeparatorParserComponent.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponent] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.modification.copper.PprefixSeparatorAGDcl(context.childAsIsLazy(silver.modification.copper.PprefixSeparatorParserComponent.i__G_3), context.childAsIsLazy(silver.modification.copper.PprefixSeparatorParserComponent.i__G_2), context.childAsIsLazy(silver.modification.copper.PprefixSeparatorParserComponent.i_s), context.childAsIsLazy(silver.modification.copper.PprefixSeparatorParserComponent.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NParserComponent)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PprefixSeparatorParserComponent> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprefixSeparatorParserComponent> {

		@Override
		public PprefixSeparatorParserComponent invoke(final Object[] children, final Object[] annotations) {
			return new PprefixSeparatorParserComponent(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
