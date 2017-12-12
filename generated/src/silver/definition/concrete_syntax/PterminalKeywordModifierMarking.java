
package silver.definition.concrete_syntax;

// top::TerminalKeywordModifier ::= 'marking' 
public final class PterminalKeywordModifierMarking extends silver.definition.concrete_syntax.NTerminalKeywordModifier {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.TMarking_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_terminalKeywordModifierMarking;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.NTerminalKeywordModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.NTerminalKeywordModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PterminalKeywordModifierMarking(final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.definition.concrete_syntax.TMarking_kwd getChild__G_0() {
		return (silver.definition.concrete_syntax.TMarking_kwd) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:terminalKeywordModifierMarking erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:terminalKeywordModifierMarking";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "marking "
		silver.definition.concrete_syntax.PterminalKeywordModifierMarking.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalKeywordModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("marking ")); } };
		// top.terminalModifiers = [ termMarking() ]
		silver.definition.concrete_syntax.PterminalKeywordModifierMarking.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalKeywordModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier)new silver.definition.concrete_syntax.ast.PtermMarking()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PterminalKeywordModifierMarking> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalKeywordModifierMarking> {

		@Override
		public PterminalKeywordModifierMarking invoke(final Object[] children, final Object[] annotations) {
			return new PterminalKeywordModifierMarking(children[0], annotations[0]);
		}
	};

}
