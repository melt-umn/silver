
package silver.modification.copper;

// top::LexerClassModifiers ::= tm::LexerClassModifier 
public final class PlexerClassModifierSingle extends silver.modification.copper.NLexerClassModifiers {

	public static final int i_tm = 0;


	public static final Class<?> childTypes[] = {silver.modification.copper.NLexerClassModifier.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_lexerClassModifierSingle;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NLexerClassModifiers.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NLexerClassModifiers.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_tm] = new common.Lazy[silver.modification.copper.NLexerClassModifier.num_inh_attrs];

	}

	public PlexerClassModifierSingle(final Object c_tm, final Object a_core_location) {
		super(a_core_location);
		this.child_tm = c_tm;

	}

	private Object child_tm;
	public final silver.modification.copper.NLexerClassModifier getChild_tm() {
		return (silver.modification.copper.NLexerClassModifier) (child_tm = common.Util.demand(child_tm));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tm: return getChild_tm();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tm: return child_tm;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:lexerClassModifierSingle erroneously claimed to forward");
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
		return "silver:modification:copper:lexerClassModifierSingle";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = tm.pp
		silver.modification.copper.PlexerClassModifierSingle.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_LexerClassModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.copper.PlexerClassModifierSingle.i_tm).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_LexerClassModifier)); } };
		// top.lexerClassModifiers = tm.lexerClassModifiers
		silver.modification.copper.PlexerClassModifierSingle.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClassModifiers__ON__silver_modification_copper_LexerClassModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PlexerClassModifierSingle.i_tm).synthesized(silver.modification.copper.Init.silver_modification_copper_lexerClassModifiers__ON__silver_modification_copper_LexerClassModifier)); } };
		// top.errors := tm.errors
		if(silver.modification.copper.PlexerClassModifierSingle.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifiers] == null)
			silver.modification.copper.PlexerClassModifierSingle.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifiers] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifiers);
		((common.CollectionAttribute)silver.modification.copper.PlexerClassModifierSingle.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifiers]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PlexerClassModifierSingle.i_tm).synthesized(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier)); } });

	}

	public static final common.NodeFactory<PlexerClassModifierSingle> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlexerClassModifierSingle> {

		@Override
		public PlexerClassModifierSingle invoke(final Object[] children, final Object[] annotations) {
			return new PlexerClassModifierSingle(children[0], annotations[0]);
		}
	};

}
