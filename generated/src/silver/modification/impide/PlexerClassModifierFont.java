
package silver.modification.impide;

// top::LexerClassModifier ::= 'font' '=' id::QName 
public final class PlexerClassModifierFont extends silver.modification.copper.NLexerClassModifier {

	public static final int i__G_2 = 0;
	public static final int i__G_1 = 1;
	public static final int i_id = 2;


	public static final Class<?> childTypes[] = {silver.modification.impide.TFont_kwd.class,silver.definition.core.TEqual_t.class,silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_lexerClassModifierFont;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NLexerClassModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NLexerClassModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PlexerClassModifierFont(final Object c__G_2, final Object c__G_1, final Object c_id, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child__G_1 = c__G_1;
		this.child_id = c_id;

	}

	private Object child__G_2;
	public final silver.modification.impide.TFont_kwd getChild__G_2() {
		return (silver.modification.impide.TFont_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child__G_1;
	public final silver.definition.core.TEqual_t getChild__G_1() {
		return (silver.definition.core.TEqual_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_id;
	public final silver.definition.core.NQName getChild_id() {
		return (silver.definition.core.NQName) (child_id = common.Util.demand(child_id));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i__G_1: return getChild__G_1();
			case i_id: return getChild_id();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i__G_1: return child__G_1;
			case i_id: return child_id;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:lexerClassModifierFont erroneously claimed to forward");
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
		return "silver:modification:impide:lexerClassModifierFont";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "font = " ++ id.name
		silver.modification.impide.PlexerClassModifierFont.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_LexerClassModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("font = ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.PlexerClassModifierFont.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName))); } };
		// top.lexerClassModifiers = [ lexerClassFont(id.lookupFont.fullName) ]
		silver.modification.impide.PlexerClassModifierFont.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClassModifiers__ON__silver_modification_copper_LexerClassModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier)new silver.modification.impide.cstast.PlexerClassFont(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.modification.impide.PlexerClassModifierFont.i_id).synthesized(silver.modification.impide.Init.silver_modification_impide_lookupFont__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := id.lookupFont.errors
		if(silver.modification.impide.PlexerClassModifierFont.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier] == null)
			silver.modification.impide.PlexerClassModifierFont.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier);
		((common.CollectionAttribute)silver.modification.impide.PlexerClassModifierFont.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.impide.PlexerClassModifierFont.i_id).synthesized(silver.modification.impide.Init.silver_modification_impide_lookupFont__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } });

	}

	public static final common.NodeFactory<PlexerClassModifierFont> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlexerClassModifierFont> {

		@Override
		public PlexerClassModifierFont invoke(final Object[] children, final Object[] annotations) {
			return new PlexerClassModifierFont(children[0], children[1], children[2], annotations[0]);
		}
	};

}
