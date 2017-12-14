
package silver.definition.concrete_syntax;

// top::TerminalModifiers ::= tm::TerminalModifier 
public final class PterminalModifierSingle extends silver.definition.concrete_syntax.NTerminalModifiers {

	public static final int i_tm = 0;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.NTerminalModifier.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_terminalModifierSingle;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifiers.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifiers.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_tm] = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifier.num_inh_attrs];

	}

	public PterminalModifierSingle(final Object c_tm, final Object a_core_location) {
		super(a_core_location);
		this.child_tm = c_tm;

	}

	private Object child_tm;
	public final silver.definition.concrete_syntax.NTerminalModifier getChild_tm() {
		return (silver.definition.concrete_syntax.NTerminalModifier) (child_tm = common.Util.demand(child_tm));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:terminalModifierSingle erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:terminalModifierSingle";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = tm.pp
		silver.definition.concrete_syntax.PterminalModifierSingle.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PterminalModifierSingle.i_tm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifier)); } };
		// top.terminalModifiers = tm.terminalModifiers
		silver.definition.concrete_syntax.PterminalModifierSingle.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.PterminalModifierSingle.i_tm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifier)); } };
		// top.errors := tm.errors
		if(silver.definition.concrete_syntax.PterminalModifierSingle.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers] == null)
			silver.definition.concrete_syntax.PterminalModifierSingle.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers] = new silver.definition.core.CAerrors(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PterminalModifierSingle.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.PterminalModifierSingle.i_tm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier)); } });

	}

	public static final common.NodeFactory<PterminalModifierSingle> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalModifierSingle> {

		@Override
		public PterminalModifierSingle invoke(final Object[] children, final Object[] annotations) {
			return new PterminalModifierSingle(children[0], annotations[0]);
		}
	};

}
