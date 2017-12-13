
package silver.modification.copper;

// top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody 'action' acode::ActionCode_c 
public final class PconcreteProductionDclAction extends silver.definition.core.NAGDcl {

	public static final int i__G_7 = 0;
	public static final int i__G_6 = 1;
	public static final int i_id = 2;
	public static final int i_ns = 3;
	public static final int i_pm = 4;
	public static final int i_body = 5;
	public static final int i__G_1 = 6;
	public static final int i_acode = 7;


	public static final Class<?> childTypes[] = {silver.definition.core.TConcrete_kwd.class,silver.definition.core.TProduction_kwd.class,silver.definition.core.NName.class,silver.definition.core.NProductionSignature.class,silver.definition.concrete_syntax.NProductionModifiers.class,silver.definition.core.NProductionBody.class,silver.modification.copper.TAction_kwd.class,silver.modification.copper.NActionCode_c.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_concreteProductionDclAction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NProductionSignature.num_inh_attrs];
	childInheritedAttributes[i_pm] = new common.Lazy[silver.definition.concrete_syntax.NProductionModifiers.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];
	childInheritedAttributes[i_acode] = new common.Lazy[silver.modification.copper.NActionCode_c.num_inh_attrs];

	}

	public PconcreteProductionDclAction(final Object c__G_7, final Object c__G_6, final Object c_id, final Object c_ns, final Object c_pm, final Object c_body, final Object c__G_1, final Object c_acode, final Object a_core_location) {
		super(a_core_location);
		this.child__G_7 = c__G_7;
		this.child__G_6 = c__G_6;
		this.child_id = c_id;
		this.child_ns = c_ns;
		this.child_pm = c_pm;
		this.child_body = c_body;
		this.child__G_1 = c__G_1;
		this.child_acode = c_acode;

	}

	private Object child__G_7;
	public final silver.definition.core.TConcrete_kwd getChild__G_7() {
		return (silver.definition.core.TConcrete_kwd) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child__G_6;
	public final silver.definition.core.TProduction_kwd getChild__G_6() {
		return (silver.definition.core.TProduction_kwd) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_ns;
	public final silver.definition.core.NProductionSignature getChild_ns() {
		return (silver.definition.core.NProductionSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_pm;
	public final silver.definition.concrete_syntax.NProductionModifiers getChild_pm() {
		return (silver.definition.concrete_syntax.NProductionModifiers) (child_pm = common.Util.demand(child_pm));
	}

	private Object child_body;
	public final silver.definition.core.NProductionBody getChild_body() {
		return (silver.definition.core.NProductionBody) (child_body = common.Util.demand(child_body));
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
			case i__G_7: return getChild__G_7();
			case i__G_6: return getChild__G_6();
			case i_id: return getChild_id();
			case i_ns: return getChild_ns();
			case i_pm: return getChild_pm();
			case i_body: return getChild_body();
			case i__G_1: return getChild__G_1();
			case i_acode: return getChild_acode();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_7: return child__G_7;
			case i__G_6: return child__G_6;
			case i_id: return child_id;
			case i_ns: return child_ns;
			case i_pm: return child_pm;
			case i_body: return child_body;
			case i__G_1: return child__G_1;
			case i_acode: return child_acode;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 8;
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
		return ((silver.definition.core.NAGDcl)new silver.definition.concrete_syntax.PconcreteProductionDcl(context.childAsIsLazy(silver.modification.copper.PconcreteProductionDclAction.i__G_7), context.childAsIsLazy(silver.modification.copper.PconcreteProductionDclAction.i__G_6), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PconcreteProductionDclAction.i_id)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PconcreteProductionDclAction.i_ns)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PconcreteProductionDclAction.i_pm)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PconcreteProductionDclAction.i_body)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:copper:concreteProductionDclAction";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = forward.pp ++ "action " ++ acode.pp
		silver.modification.copper.PconcreteProductionDclAction.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.forward().synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("action ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PconcreteProductionDclAction.i_acode).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ActionCode_c)))); } };
		// fName = top.grammarName ++ ":" ++ id.name
		silver.modification.copper.PconcreteProductionDclAction.localAttributes[silver.modification.copper.Init.fName__ON__silver_modification_copper_concreteProductionDclAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PconcreteProductionDclAction.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// top.syntaxAst = [ syntaxProduction(ns.namedSignature, foldr(consProductionMod, nilProductionMod(), (prodAction(acode.actionCode) :: pm.productionModifiers))) ]
		silver.modification.copper.PconcreteProductionDclAction.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.definition.concrete_syntax.ast.PsyntaxProduction(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconcreteProductionDclAction.i_ns, silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsProductionMod.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers)new silver.definition.concrete_syntax.ast.PnilProductionMod()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxProductionModifier)new silver.definition.concrete_syntax.ast.PprodAction(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconcreteProductionDclAction.i_acode, silver.modification.copper.Init.silver_modification_copper_actionCode__ON__silver_modification_copper_ActionCode_c))); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper.PconcreteProductionDclAction.i_pm, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifiers))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// ns.signatureName = fName
		silver.modification.copper.PconcreteProductionDclAction.childInheritedAttributes[silver.modification.copper.PconcreteProductionDclAction.i_ns][silver.definition.core.Init.silver_definition_core_signatureName__ON__silver_definition_core_ProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localAsIs(silver.modification.copper.Init.fName__ON__silver_modification_copper_concreteProductionDclAction)); } };
		// acode.frame = reduceActionContext(ns.namedSignature)
		silver.modification.copper.PconcreteProductionDclAction.childInheritedAttributes[silver.modification.copper.PconcreteProductionDclAction.i_acode][silver.modification.copper.Init.silver_definition_core_frame__ON__silver_modification_copper_ActionCode_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NBlockContext)new silver.modification.copper.PreduceActionContext(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconcreteProductionDclAction.i_ns, silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature))); } };
		// acode.env = newScopeEnv(addTerminalAttrDefs(acode.defs ++ ns.actionDefs), top.env)
		silver.modification.copper.PconcreteProductionDclAction.childInheritedAttributes[silver.modification.copper.PconcreteProductionDclAction.i_acode][silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ActionCode_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.modification.copper.PaddTerminalAttrDefs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconcreteProductionDclAction.i_acode, silver.modification.copper.Init.silver_definition_env_defs__ON__silver_modification_copper_ActionCode_c), context.childDecoratedSynthesizedLazy(silver.modification.copper.PconcreteProductionDclAction.i_ns, silver.modification.copper.Init.silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionSignature))); } })); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// top.errors <- acode.errors
		if(silver.modification.copper.PconcreteProductionDclAction.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.copper.PconcreteProductionDclAction.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.copper.PconcreteProductionDclAction.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PconcreteProductionDclAction.i_acode).synthesized(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ActionCode_c)); } });

	}

	public static final common.NodeFactory<PconcreteProductionDclAction> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconcreteProductionDclAction> {

		@Override
		public PconcreteProductionDclAction invoke(final Object[] children, final Object[] annotations) {
			return new PconcreteProductionDclAction(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], annotations[0]);
		}
	};

}
