
package silver.definition.concrete_syntax.ast;

// top::SyntaxDcl ::= n::String regex::Regex modifiers::SyntaxTerminalModifiers 
public final class PsyntaxTerminal extends silver.definition.concrete_syntax.ast.NSyntaxDcl {

	public static final int i_n = 0;
	public static final int i_regex = 1;
	public static final int i_modifiers = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.regex.NRegex.class,silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_syntaxTerminal;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_regex] = new common.Lazy[silver.definition.regex.NRegex.num_inh_attrs];
	childInheritedAttributes[i_modifiers] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.num_inh_attrs];

	}

	public PsyntaxTerminal(final Object c_n, final Object c_regex, final Object c_modifiers) {
		super();
		this.child_n = c_n;
		this.child_regex = c_regex;
		this.child_modifiers = c_modifiers;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_regex;
	public final silver.definition.regex.NRegex getChild_regex() {
		return (silver.definition.regex.NRegex) (child_regex = common.Util.demand(child_regex));
	}

	private Object child_modifiers;
	public final silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers getChild_modifiers() {
		return (silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers) (child_modifiers = common.Util.demand(child_modifiers));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_regex: return getChild_regex();
			case i_modifiers: return getChild_modifiers();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_regex: return child_regex;
			case i_modifiers: return child_modifiers;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:syntaxTerminal erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:syntaxTerminal";
	}

	static void initProductionAttributeDefinitions() {
		// top.sortKey = "CCC" ++ n
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_sortKey__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("CCC")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n))); } };
		// top.cstDcls = [ pair(n, top) ]
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n), context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.cstErrors := modifiers.cstErrors
		if(silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)); } });
		// top.cstErrors <- if length(searchEnvTree(n, top.cstEnv)) == 1 then [] else [ "Name conflict with terminal " ++ n ]
		if(silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxDcl))); } })).equals(Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Name conflict with terminal ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// modifiers.terminalName = n
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.childInheritedAttributes[silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_terminalName__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n)); } };
		// top.cstNormalize = [ top ]
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.undecorate(), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.allIgnoreTerminals = if modifiers.ignored then [ top ] else []
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allIgnoreTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_ignored__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)) ? ((common.ConsCell)core.Pcons.invoke(context, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } };
		// top.allMarkingTerminals = if modifiers.marking then [ top ] else []
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allMarkingTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_marking__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)) ? ((common.ConsCell)core.Pcons.invoke(context, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } };
		// pfx = searchEnvTree(n, top.prefixesForTerminals)
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.localAttributes[silver.definition.concrete_syntax.ast.Init.pfx__ON__silver_definition_concrete_syntax_ast_syntaxTerminal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_prefixesForTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl))); } };
		// top.xmlCopper = "  <Terminal id=\"" ++ makeCopperName(n) ++ "\">\n" ++ "    <PP>" ++ n ++ "</PP>\n" ++ "    <Regex>" ++ regex.xmlCopper ++ "</Regex>\n" ++ (if modifiers.opPrecedence.isJust || modifiers.opAssociation.isJust then "    <Operator>\n" ++ "      <Class>main</Class>\n" ++ "      <Precedence>" ++ toString(fromMaybe(0, modifiers.opPrecedence)) ++ "</Precedence>\n" ++ "      " ++ convertAssocNXML(modifiers.opAssociation) ++ "\n" ++ "    </Operator>\n" else "") ++ "    <Type>" ++ makeTerminalName(n) ++ "</Type>\n" ++ "    <Code><![CDATA[\n" ++ "RESULT = new " ++ makeTerminalName(n) ++ "(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());\n" ++ "  tokenList.add(RESULT);\n" ++ modifiers.acode ++ "]]></Code>\n" ++ "    <InClasses>" ++ modifiers.lexerclassesXML ++ "</InClasses>\n" ++ (if null(pfx) then "" else "    <Prefix><TerminalRef id=\"" ++ head(pfx) ++ "\"/></Prefix>\n") ++ "    <Submits>" ++ modifiers.submitsXML ++ "</Submits>\n" ++ "    <Dominates>" ++ modifiers.dominatesXML ++ "</Dominates>\n" ++ "  </Terminal>\n"
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("  <Terminal id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\">\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <PP>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</PP>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Regex>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_regex).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_Regex)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</Regex>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((((Boolean)((core.NMaybe)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_isJust__ON__core_Maybe)) || ((Boolean)((core.NMaybe)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opAssociation__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_isJust__ON__core_Maybe))) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Operator>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("      <Class>main</Class>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("      <Precedence>")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PfromMaybe.invoke(Integer.valueOf((int)0), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</Precedence>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("      ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PconvertAssocNXML.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opAssociation__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter("    </Operator>\n")))))))))) : (new common.StringCatter(""))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Type>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeTerminalName.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</Type>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Code><![CDATA[\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("RESULT = new ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeTerminalName.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("  tokenList.add(RESULT);\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("]]></Code>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <InClasses>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_lexerclassesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</InClasses>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.pfx__ON__silver_definition_concrete_syntax_ast_syntaxTerminal))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Prefix><TerminalRef id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Phead.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.pfx__ON__silver_definition_concrete_syntax_ast_syntaxTerminal))), (common.StringCatter)(new common.StringCatter("\"/></Prefix>\n"))))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Submits>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</Submits>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Dominates>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</Dominates>\n")), (common.StringCatter)(new common.StringCatter("  </Terminal>\n")))))))))))))))))))))))))))))))); } };
		// top.unparses = [ "term('" ++ n ++ "', /" ++ regex.regString ++ "/, " ++ unparseNonStrings(modifiers.unparses) ++ ")" ]
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("term('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', /")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_regex).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_Regex)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("/, ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseNonStrings.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))), (common.StringCatter)(new common.StringCatter(")")))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PsyntaxTerminal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsyntaxTerminal> {

		@Override
		public PsyntaxTerminal invoke(final Object[] children, final Object[] annotations) {
			return new PsyntaxTerminal(children[0], children[1], children[2]);
		}
	};

}
