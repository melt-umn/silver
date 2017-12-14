
package silver.modification.copper;

// top::TerminalModifier ::= 'lexer' 'classes' '{' cl::ClassList '}' 
public final class PterminalModifierClassSpec extends silver.definition.concrete_syntax.NTerminalModifier {

	public static final int i__G_4 = 0;
	public static final int i__G_3 = 1;
	public static final int i__G_2 = 2;
	public static final int i_cl = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.modification.copper.TLexer_kwd.class,silver.modification.copper.TClasses_kwd.class,silver.definition.core.TLCurly_t.class,silver.modification.copper.NClassList.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_terminalModifierClassSpec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_cl] = new common.Lazy[silver.modification.copper.NClassList.num_inh_attrs];

	}

	public PterminalModifierClassSpec(final Object c__G_4, final Object c__G_3, final Object c__G_2, final Object c_cl, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_cl = c_cl;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final silver.modification.copper.TLexer_kwd getChild__G_4() {
		return (silver.modification.copper.TLexer_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child__G_3;
	public final silver.modification.copper.TClasses_kwd getChild__G_3() {
		return (silver.modification.copper.TClasses_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TLCurly_t getChild__G_2() {
		return (silver.definition.core.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_cl;
	public final silver.modification.copper.NClassList getChild_cl() {
		return (silver.modification.copper.NClassList) (child_cl = common.Util.demand(child_cl));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_cl: return getChild_cl();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_cl: return child_cl;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:terminalModifierClassSpec erroneously claimed to forward");
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
		return "silver:modification:copper:terminalModifierClassSpec";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "lexer classes { " ++ cl.pp ++ " } "
		silver.modification.copper.PterminalModifierClassSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("lexer classes { ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PterminalModifierClassSpec.i_cl).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ClassList)), (common.StringCatter)(new common.StringCatter(" } ")))); } };
		// top.terminalModifiers = [ termClasses(cl.lexerClasses) ]
		silver.modification.copper.PterminalModifierClassSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier)new silver.definition.concrete_syntax.ast.PtermClasses(context.childDecoratedSynthesizedLazy(silver.modification.copper.PterminalModifierClassSpec.i_cl, silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_modification_copper_ClassList))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.lexerClasses = cl.lexerClasses
		silver.modification.copper.PterminalModifierClassSpec.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PterminalModifierClassSpec.i_cl).synthesized(silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_modification_copper_ClassList)); } };
		// top.errors := cl.errors
		if(silver.modification.copper.PterminalModifierClassSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier] == null)
			silver.modification.copper.PterminalModifierClassSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier] = new silver.definition.core.CAerrors(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier);
		((common.CollectionAttribute)silver.modification.copper.PterminalModifierClassSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PterminalModifierClassSpec.i_cl).synthesized(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ClassList)); } });

	}

	public static final common.NodeFactory<PterminalModifierClassSpec> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalModifierClassSpec> {

		@Override
		public PterminalModifierClassSpec invoke(final Object[] children, final Object[] annotations) {
			return new PterminalModifierClassSpec(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
