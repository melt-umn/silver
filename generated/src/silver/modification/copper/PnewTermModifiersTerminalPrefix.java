
package silver.modification.copper;

// top::TerminalPrefix ::= r::RegExpr tm::TerminalModifiers 
public final class PnewTermModifiersTerminalPrefix extends silver.modification.copper.NTerminalPrefix {

	public static final int i_r = 0;
	public static final int i_tm = 1;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.NRegExpr.class,silver.definition.concrete_syntax.NTerminalModifiers.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_newTermModifiersTerminalPrefix;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefix.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefix.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_r] = new common.Lazy[silver.definition.concrete_syntax.NRegExpr.num_inh_attrs];
	childInheritedAttributes[i_tm] = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifiers.num_inh_attrs];

	}

	public PnewTermModifiersTerminalPrefix(final Object c_r, final Object c_tm, final Object a_core_location) {
		super(a_core_location);
		this.child_r = c_r;
		this.child_tm = c_tm;

	}

	private Object child_r;
	public final silver.definition.concrete_syntax.NRegExpr getChild_r() {
		return (silver.definition.concrete_syntax.NRegExpr) (child_r = common.Util.demand(child_r));
	}

	private Object child_tm;
	public final silver.definition.concrete_syntax.NTerminalModifiers getChild_tm() {
		return (silver.definition.concrete_syntax.NTerminalModifiers) (child_tm = common.Util.demand(child_tm));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_r: return getChild_r();
			case i_tm: return getChild_tm();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_r: return child_r;
			case i_tm: return child_tm;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.modification.copper.NTerminalPrefix)new silver.modification.copper.PnameTerminalPrefix(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefix)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.modification.copper.Init.terminalName__ON__silver_modification_copper_newTermModifiersTerminalPrefix))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefix)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:copper:newTermModifiersTerminalPrefix";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = r.pp ++ " " ++ tm.pp
		silver.modification.copper.PnewTermModifiersTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PnewTermModifiersTerminalPrefix.i_r).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_RegExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PnewTermModifiersTerminalPrefix.i_tm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifiers)))); } };
		// terminalName = "_Prefix" ++ toString(genInt())
		silver.modification.copper.PnewTermModifiersTerminalPrefix.localAttributes[silver.modification.copper.Init.terminalName__ON__silver_modification_copper_newTermModifiersTerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("_Prefix")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke())))); } };
		// top.liftedAGDcls = terminalDclDefault(terminalKeywordModifierNone(location=top.location), name(terminalName, top.location), r, tm,location=top.location)
		silver.modification.copper.PnewTermModifiersTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_TerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.concrete_syntax.PterminalDclDefault(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.NTerminalKeywordModifier)new silver.definition.concrete_syntax.PterminalKeywordModifierNone(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefix)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.definition.core.Pname.invoke(context.localAsIsLazy(silver.modification.copper.Init.terminalName__ON__silver_modification_copper_newTermModifiersTerminalPrefix), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefix)context.undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PnewTermModifiersTerminalPrefix.i_r)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PnewTermModifiersTerminalPrefix.i_tm)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefix)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PnewTermModifiersTerminalPrefix> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnewTermModifiersTerminalPrefix> {

		@Override
		public PnewTermModifiersTerminalPrefix invoke(final Object[] children, final Object[] annotations) {
			return new PnewTermModifiersTerminalPrefix(children[0], children[1], annotations[0]);
		}
	};

}
