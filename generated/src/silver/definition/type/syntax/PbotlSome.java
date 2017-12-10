
package silver.definition.type.syntax;

// top::BracketedOptTypeExprs ::= '<' tl::TypeExprs '>' 
public final class PbotlSome extends silver.definition.type.syntax.NBracketedOptTypeExprs {

	public static final int i__G_2 = 0;
	public static final int i_tl = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.TLT_t.class,silver.definition.type.syntax.NTypeExprs.class,silver.definition.core.TGT_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_syntax_botlSome;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_tl] = new common.Lazy[silver.definition.type.syntax.NTypeExprs.num_inh_attrs];

	}

	public PbotlSome(final Object c__G_2, final Object c_tl, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_tl = c_tl;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.definition.core.TLT_t getChild__G_2() {
		return (silver.definition.core.TLT_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_tl;
	public final silver.definition.type.syntax.NTypeExprs getChild_tl() {
		return (silver.definition.type.syntax.NTypeExprs) (child_tl = common.Util.demand(child_tl));
	}

	private Object child__G_0;
	public final silver.definition.core.TGT_t getChild__G_0() {
		return (silver.definition.core.TGT_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_tl: return getChild_tl();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_tl: return child_tl;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:syntax:botlSome erroneously claimed to forward");
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
		return "silver:definition:type:syntax:botlSome";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "<" ++ tl.pp ++ ">"
		silver.definition.type.syntax.PbotlSome.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.syntax.PbotlSome.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExprs)), (common.StringCatter)(new common.StringCatter(">")))); } };
		// top.errors := tl.errors
		if(silver.definition.type.syntax.PbotlSome.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_BracketedOptTypeExprs] == null)
			silver.definition.type.syntax.PbotlSome.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new silver.definition.core.CAerrors(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_BracketedOptTypeExprs);
		((common.CollectionAttribute)silver.definition.type.syntax.PbotlSome.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_BracketedOptTypeExprs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.syntax.PbotlSome.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExprs)); } });
		// top.types = tl.types
		silver.definition.type.syntax.PbotlSome.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.syntax.PbotlSome.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_TypeExprs)); } };
		// top.lexicalTypeVariables = tl.lexicalTypeVariables
		silver.definition.type.syntax.PbotlSome.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.syntax.PbotlSome.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExprs)); } };
		// top.freeVariables = tl.freeVariables
		silver.definition.type.syntax.PbotlSome.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.syntax.PbotlSome.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_TypeExprs)); } };
		// top.errorsTyVars := tl.errorsTyVars ++ if containsDuplicates(tl.lexicalTypeVariables) then [ err(top.location, "Type parameter list repeats type variable names") ] else []
		if(silver.definition.type.syntax.PbotlSome.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs] == null)
			silver.definition.type.syntax.PbotlSome.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new silver.definition.type.syntax.CAerrorsTyVars(silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs);
		((common.CollectionAttribute)silver.definition.type.syntax.PbotlSome.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PbotlSome.i_tl, silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)silver.util.PcontainsDuplicates.invoke(context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PbotlSome.i_tl, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExprs))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NBracketedOptTypeExprs)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Type parameter list repeats type variable names")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } })); } });
		// top.envBindingTyVars = newScopeEnv(addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTypeVariables), top.initialEnv)
		silver.definition.type.syntax.PbotlSome.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_envBindingTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.syntax.PaddNewLexicalTyVars.invoke(context.contextInheritedLazy(silver.definition.type.syntax.Init.silver_definition_core_grammarName__ON__silver_definition_type_syntax_BracketedOptTypeExprs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NBracketedOptTypeExprs)context.undecorate()).getAnno_core_location()); } }, context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PbotlSome.i_tl, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExprs))); } }, context.contextInheritedLazy(silver.definition.type.syntax.Init.silver_definition_type_syntax_initialEnv__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } };

	}

	public static final common.NodeFactory<PbotlSome> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbotlSome> {

		@Override
		public PbotlSome invoke(final Object[] children, final Object[] annotations) {
			return new PbotlSome(children[0], children[1], children[2], annotations[0]);
		}
	};

}
