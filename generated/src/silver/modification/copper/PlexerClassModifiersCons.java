
package silver.modification.copper;

// top::LexerClassModifiers ::= h::LexerClassModifier t::LexerClassModifiers 
public final class PlexerClassModifiersCons extends silver.modification.copper.NLexerClassModifiers {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.modification.copper.NLexerClassModifier.class,silver.modification.copper.NLexerClassModifiers.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_lexerClassModifiersCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NLexerClassModifiers.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NLexerClassModifiers.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.modification.copper.NLexerClassModifier.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.modification.copper.NLexerClassModifiers.num_inh_attrs];

	}

	public PlexerClassModifiersCons(final Object c_h, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.modification.copper.NLexerClassModifier getChild_h() {
		return (silver.modification.copper.NLexerClassModifier) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.modification.copper.NLexerClassModifiers getChild_t() {
		return (silver.modification.copper.NLexerClassModifiers) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:lexerClassModifiersCons erroneously claimed to forward");
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
		return "silver:modification:copper:lexerClassModifiersCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp ++ " " ++ t.pp
		silver.modification.copper.PlexerClassModifiersCons.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_LexerClassModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PlexerClassModifiersCons.i_h).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_LexerClassModifier)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PlexerClassModifiersCons.i_t).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_LexerClassModifiers)))); } };
		// top.lexerClassModifiers = h.lexerClassModifiers ++ t.lexerClassModifiers
		silver.modification.copper.PlexerClassModifiersCons.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClassModifiers__ON__silver_modification_copper_LexerClassModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PlexerClassModifiersCons.i_h, silver.modification.copper.Init.silver_modification_copper_lexerClassModifiers__ON__silver_modification_copper_LexerClassModifier), context.childDecoratedSynthesizedLazy(silver.modification.copper.PlexerClassModifiersCons.i_t, silver.modification.copper.Init.silver_modification_copper_lexerClassModifiers__ON__silver_modification_copper_LexerClassModifiers))); } };
		// top.errors := h.errors ++ t.errors
		if(silver.modification.copper.PlexerClassModifiersCons.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifiers] == null)
			silver.modification.copper.PlexerClassModifiersCons.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifiers] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifiers);
		((common.CollectionAttribute)silver.modification.copper.PlexerClassModifiersCons.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifiers]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PlexerClassModifiersCons.i_h, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier), context.childDecoratedSynthesizedLazy(silver.modification.copper.PlexerClassModifiersCons.i_t, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifiers))); } });

	}

	public static final common.NodeFactory<PlexerClassModifiersCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlexerClassModifiersCons> {

		@Override
		public PlexerClassModifiersCons invoke(final Object[] children, final Object[] annotations) {
			return new PlexerClassModifiersCons(children[0], children[1], annotations[0]);
		}
	};

}
