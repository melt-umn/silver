
package silver.modification.copper;

// top::LexerClassModifier ::= 'submits' 'to' terms::TermPrecList 
public final class PlexerClassModifierSubmitsTo extends silver.modification.copper.NLexerClassModifier {

	public static final int i__G_2 = 0;
	public static final int i__G_1 = 1;
	public static final int i_terms = 2;


	public static final Class<?> childTypes[] = {silver.modification.copper.TSubmits_t.class,silver.definition.core.TTo_kwd.class,silver.modification.copper.NTermPrecList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_lexerClassModifierSubmitsTo;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NLexerClassModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NLexerClassModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_terms] = new common.Lazy[silver.modification.copper.NTermPrecList.num_inh_attrs];

	}

	public PlexerClassModifierSubmitsTo(final Object c__G_2, final Object c__G_1, final Object c_terms, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child__G_1 = c__G_1;
		this.child_terms = c_terms;

	}

	private Object child__G_2;
	public final silver.modification.copper.TSubmits_t getChild__G_2() {
		return (silver.modification.copper.TSubmits_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child__G_1;
	public final silver.definition.core.TTo_kwd getChild__G_1() {
		return (silver.definition.core.TTo_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_terms;
	public final silver.modification.copper.NTermPrecList getChild_terms() {
		return (silver.modification.copper.NTermPrecList) (child_terms = common.Util.demand(child_terms));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i__G_1: return getChild__G_1();
			case i_terms: return getChild_terms();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i__G_1: return child__G_1;
			case i_terms: return child_terms;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:lexerClassModifierSubmitsTo erroneously claimed to forward");
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
		return "silver:modification:copper:lexerClassModifierSubmitsTo";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "submits to " ++ terms.pp
		silver.modification.copper.PlexerClassModifierSubmitsTo.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_LexerClassModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("submits to ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PlexerClassModifierSubmitsTo.i_terms).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TermPrecList))); } };
		// top.lexerClassModifiers = [ lexerClassSubmits(terms.precTermList) ]
		silver.modification.copper.PlexerClassModifierSubmitsTo.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClassModifiers__ON__silver_modification_copper_LexerClassModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier)new silver.definition.concrete_syntax.ast.PlexerClassSubmits(context.childDecoratedSynthesizedLazy(silver.modification.copper.PlexerClassModifierSubmitsTo.i_terms, silver.modification.copper.Init.silver_modification_copper_precTermList__ON__silver_modification_copper_TermPrecList))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := terms.errors
		if(silver.modification.copper.PlexerClassModifierSubmitsTo.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier] == null)
			silver.modification.copper.PlexerClassModifierSubmitsTo.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier);
		((common.CollectionAttribute)silver.modification.copper.PlexerClassModifierSubmitsTo.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PlexerClassModifierSubmitsTo.i_terms).synthesized(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermPrecList)); } });

	}

	public static final common.NodeFactory<PlexerClassModifierSubmitsTo> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlexerClassModifierSubmitsTo> {

		@Override
		public PlexerClassModifierSubmitsTo invoke(final Object[] children, final Object[] annotations) {
			return new PlexerClassModifierSubmitsTo(children[0], children[1], children[2], annotations[0]);
		}
	};

}
