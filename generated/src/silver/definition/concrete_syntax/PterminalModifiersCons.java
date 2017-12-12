
package silver.definition.concrete_syntax;

// top::TerminalModifiers ::= h::TerminalModifier ',' t::TerminalModifiers 
public final class PterminalModifiersCons extends silver.definition.concrete_syntax.NTerminalModifiers {

	public static final int i_h = 0;
	public static final int i__G_1 = 1;
	public static final int i_t = 2;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.NTerminalModifier.class,silver.definition.core.TComma_t.class,silver.definition.concrete_syntax.NTerminalModifiers.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_terminalModifiersCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifiers.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifiers.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifier.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifiers.num_inh_attrs];

	}

	public PterminalModifiersCons(final Object c_h, final Object c__G_1, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child__G_1 = c__G_1;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.concrete_syntax.NTerminalModifier getChild_h() {
		return (silver.definition.concrete_syntax.NTerminalModifier) (child_h = common.Util.demand(child_h));
	}

	private Object child__G_1;
	public final silver.definition.core.TComma_t getChild__G_1() {
		return (silver.definition.core.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_t;
	public final silver.definition.concrete_syntax.NTerminalModifiers getChild_t() {
		return (silver.definition.concrete_syntax.NTerminalModifiers) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i__G_1: return getChild__G_1();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i__G_1: return child__G_1;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:terminalModifiersCons erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:terminalModifiersCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp ++ ", " ++ t.pp
		silver.definition.concrete_syntax.PterminalModifiersCons.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PterminalModifiersCons.i_h).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifier)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PterminalModifiersCons.i_t).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifiers)))); } };
		// top.terminalModifiers = h.terminalModifiers ++ t.terminalModifiers
		silver.definition.concrete_syntax.PterminalModifiersCons.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PterminalModifiersCons.i_h, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PterminalModifiersCons.i_t, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifiers))); } };
		// top.errors := h.errors ++ t.errors
		if(silver.definition.concrete_syntax.PterminalModifiersCons.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers] == null)
			silver.definition.concrete_syntax.PterminalModifiersCons.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers] = new silver.definition.core.CAerrors(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PterminalModifiersCons.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PterminalModifiersCons.i_h, silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PterminalModifiersCons.i_t, silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers))); } });

	}

	public static final common.NodeFactory<PterminalModifiersCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalModifiersCons> {

		@Override
		public PterminalModifiersCons invoke(final Object[] children, final Object[] annotations) {
			return new PterminalModifiersCons(children[0], children[1], children[2], annotations[0]);
		}
	};

}
