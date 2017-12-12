
package silver.modification.copper;

// top::TerminalModifier ::= 'action' acode::ActionCode_c 
public final class PterminalModifierActionCode extends silver.definition.concrete_syntax.NTerminalModifier {

	public static final int i__G_1 = 0;
	public static final int i_acode = 1;


	public static final Class<?> childTypes[] = {silver.modification.copper.TAction_kwd.class,silver.modification.copper.NActionCode_c.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_terminalModifierActionCode;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_acode] = new common.Lazy[silver.modification.copper.NActionCode_c.num_inh_attrs];

	}

	public PterminalModifierActionCode(final Object c__G_1, final Object c_acode, final Object a_core_location) {
		super(a_core_location);
		this.child__G_1 = c__G_1;
		this.child_acode = c_acode;

	}

	private Object child__G_1;
	public final silver.modification.copper.TAction_kwd getChild__G_1() {
		return (silver.modification.copper.TAction_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_acode;
	public final silver.modification.copper.NActionCode_c getChild_acode() {
		return (silver.modification.copper.NActionCode_c) (child_acode = common.Util.demand(child_acode));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i_acode: return getChild_acode();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
			case i_acode: return child_acode;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:terminalModifierActionCode erroneously claimed to forward");
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
		return "silver:modification:copper:terminalModifierActionCode";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "action " ++ acode.pp
		silver.modification.copper.PterminalModifierActionCode.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("action ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PterminalModifierActionCode.i_acode).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ActionCode_c))); } };
		// top.terminalModifiers = [ termAction(acode.actionCode) ]
		silver.modification.copper.PterminalModifierActionCode.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier)new silver.definition.concrete_syntax.ast.PtermAction(context.childDecoratedSynthesizedLazy(silver.modification.copper.PterminalModifierActionCode.i_acode, silver.modification.copper.Init.silver_modification_copper_actionCode__ON__silver_modification_copper_ActionCode_c))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// acode.frame = actionContext()
		silver.modification.copper.PterminalModifierActionCode.childInheritedAttributes[silver.modification.copper.PterminalModifierActionCode.i_acode][silver.modification.copper.Init.silver_definition_core_frame__ON__silver_modification_copper_ActionCode_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NBlockContext)new silver.modification.copper.PactionContext()); } };
		// acode.env = newScopeEnv(addTerminalAttrDefs(acode.defs), top.env)
		silver.modification.copper.PterminalModifierActionCode.childInheritedAttributes[silver.modification.copper.PterminalModifierActionCode.i_acode][silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ActionCode_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.modification.copper.PaddTerminalAttrDefs.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PterminalModifierActionCode.i_acode, silver.modification.copper.Init.silver_definition_env_defs__ON__silver_modification_copper_ActionCode_c))); } }, context.contextInheritedLazy(silver.definition.concrete_syntax.Init.silver_definition_env_env__ON__silver_definition_concrete_syntax_TerminalModifier))); } };
		// top.errors := acode.errors
		if(silver.modification.copper.PterminalModifierActionCode.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier] == null)
			silver.modification.copper.PterminalModifierActionCode.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier] = new silver.definition.core.CAerrors(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier);
		((common.CollectionAttribute)silver.modification.copper.PterminalModifierActionCode.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PterminalModifierActionCode.i_acode).synthesized(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ActionCode_c)); } });

	}

	public static final common.NodeFactory<PterminalModifierActionCode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalModifierActionCode> {

		@Override
		public PterminalModifierActionCode invoke(final Object[] children, final Object[] annotations) {
			return new PterminalModifierActionCode(children[0], children[1], annotations[0]);
		}
	};

}
