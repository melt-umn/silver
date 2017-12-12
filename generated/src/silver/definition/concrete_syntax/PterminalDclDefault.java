
package silver.definition.concrete_syntax;

// top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers 
public final class PterminalDclDefault extends silver.definition.core.NAGDcl {

	public static final int i_t = 0;
	public static final int i_id = 1;
	public static final int i_r = 2;
	public static final int i_tm = 3;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.NTerminalKeywordModifier.class,silver.definition.core.NName.class,silver.definition.concrete_syntax.NRegExpr.class,silver.definition.concrete_syntax.NTerminalModifiers.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_terminalDclDefault;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.concrete_syntax.NTerminalKeywordModifier.num_inh_attrs];
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.definition.concrete_syntax.NRegExpr.num_inh_attrs];
	childInheritedAttributes[i_tm] = new common.Lazy[silver.definition.concrete_syntax.NTerminalModifiers.num_inh_attrs];

	}

	public PterminalDclDefault(final Object c_t, final Object c_id, final Object c_r, final Object c_tm, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;
		this.child_id = c_id;
		this.child_r = c_r;
		this.child_tm = c_tm;

	}

	private Object child_t;
	public final silver.definition.concrete_syntax.NTerminalKeywordModifier getChild_t() {
		return (silver.definition.concrete_syntax.NTerminalKeywordModifier) (child_t = common.Util.demand(child_t));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
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
			case i_t: return getChild_t();
			case i_id: return getChild_id();
			case i_r: return getChild_r();
			case i_tm: return getChild_tm();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i_id: return child_id;
			case i_r: return child_r;
			case i_tm: return child_tm;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:terminalDclDefault erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:terminalDclDefault";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = t.pp ++ "terminal " ++ id.pp ++ " " ++ r.pp ++ " " ++ tm.pp ++ ";"
		silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_t).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalKeywordModifier)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("terminal ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_r).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_RegExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_tm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifiers)), (common.StringCatter)(new common.StringCatter(";"))))))))); } };
		// fName = top.grammarName ++ ":" ++ id.name
		silver.definition.concrete_syntax.PterminalDclDefault.localAttributes[silver.definition.concrete_syntax.Init.fName__ON__silver_definition_concrete_syntax_terminalDclDefault] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// top.defs = [ termDef(top.grammarName, id.location, fName, r.terminalRegExprSpec) ]
		silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PtermDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_id).undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.definition.concrete_syntax.Init.fName__ON__silver_definition_concrete_syntax_terminalDclDefault), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PterminalDclDefault.i_r, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalRegExprSpec__ON__silver_definition_concrete_syntax_RegExpr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors <- if length(getTypeDclAll(fName, top.env)) > 1 then [ err(id.location, "Type '" ++ fName ++ "' is already bound.") ] else []
		if(silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetTypeDclAll.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.Init.fName__ON__silver_definition_concrete_syntax_terminalDclDefault), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Type '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.concrete_syntax.Init.fName__ON__silver_definition_concrete_syntax_terminalDclDefault)), (common.StringCatter)(new common.StringCatter("' is already bound.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors <- if isLower(substring(0, 1, id.name)) then [ err(id.location, "Types must be capitalized. Invalid terminal name " ++ id.name) ] else []
		if(silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.PisLower.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), Integer.valueOf((int)1), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PterminalDclDefault.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name))); } })) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Types must be capitalized. Invalid terminal name ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors <- if indexOf("\\n", r.terminalRegExprSpec.regString) != -1 && indexOf("\\r", r.terminalRegExprSpec.regString) == -1 then [ wrn(r.location, "Regex contains '\\n' but not '\\r'. This is your reminder about '\\r\\n' newlines.") ] else []
		if(silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Integer)core.PindexOf.invoke((new common.StringCatter("\\n")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.regex.NRegex)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_r).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalRegExprSpec__ON__silver_definition_concrete_syntax_RegExpr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_Regex)); } })).equals(Integer.valueOf((int)-1)) && ((Integer)core.PindexOf.invoke((new common.StringCatter("\\r")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.regex.NRegex)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_r).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalRegExprSpec__ON__silver_definition_concrete_syntax_RegExpr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_Regex)); } })).equals(Integer.valueOf((int)-1))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.concrete_syntax.NRegExpr)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_r).undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Regex contains '\\n' but not '\\r'. This is your reminder about '\\r\\n' newlines.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors := tm.errors
		if(silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.PterminalDclDefault.i_tm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers)); } });
		// top.syntaxAst = [ syntaxTerminal(fName, r.terminalRegExprSpec, foldr(consTerminalMod, nilTerminalMod(), t.terminalModifiers ++ tm.terminalModifiers)) ]
		silver.definition.concrete_syntax.PterminalDclDefault.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.definition.concrete_syntax.ast.PsyntaxTerminal(context.localAsIsLazy(silver.definition.concrete_syntax.Init.fName__ON__silver_definition_concrete_syntax_terminalDclDefault), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PterminalDclDefault.i_r, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalRegExprSpec__ON__silver_definition_concrete_syntax_RegExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsTerminalMod.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers)new silver.definition.concrete_syntax.ast.PnilTerminalMod()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PterminalDclDefault.i_t, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalKeywordModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PterminalDclDefault.i_tm, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifiers))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PterminalDclDefault> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalDclDefault> {

		@Override
		public PterminalDclDefault invoke(final Object[] children, final Object[] annotations) {
			return new PterminalDclDefault(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
