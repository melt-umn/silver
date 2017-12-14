
package silver.definition.concrete_syntax.ast;

// top::SyntaxRoot ::= parsername::String startnt::String s::Syntax terminalPrefixes::[Pair<String String>] 
public final class PcstRoot extends silver.definition.concrete_syntax.ast.NSyntaxRoot {

	public static final int i_parsername = 0;
	public static final int i_startnt = 1;
	public static final int i_s = 2;
	public static final int i_terminalPrefixes = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,silver.definition.concrete_syntax.ast.NSyntax.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_cstRoot;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxRoot.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxRoot.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntax.num_inh_attrs];

	}

	public PcstRoot(final Object c_parsername, final Object c_startnt, final Object c_s, final Object c_terminalPrefixes) {
		super();
		this.child_parsername = c_parsername;
		this.child_startnt = c_startnt;
		this.child_s = c_s;
		this.child_terminalPrefixes = c_terminalPrefixes;

	}

	private Object child_parsername;
	public final common.StringCatter getChild_parsername() {
		return (common.StringCatter) (child_parsername = common.Util.demand(child_parsername));
	}

	private Object child_startnt;
	public final common.StringCatter getChild_startnt() {
		return (common.StringCatter) (child_startnt = common.Util.demand(child_startnt));
	}

	private Object child_s;
	public final silver.definition.concrete_syntax.ast.NSyntax getChild_s() {
		return (silver.definition.concrete_syntax.ast.NSyntax) (child_s = common.Util.demand(child_s));
	}

	private Object child_terminalPrefixes;
	public final common.ConsCell getChild_terminalPrefixes() {
		return (common.ConsCell) (child_terminalPrefixes = common.Util.demand(child_terminalPrefixes));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_parsername: return getChild_parsername();
			case i_startnt: return getChild_startnt();
			case i_s: return getChild_s();
			case i_terminalPrefixes: return getChild_terminalPrefixes();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_parsername: return child_parsername;
			case i_startnt: return child_startnt;
			case i_s: return child_s;
			case i_terminalPrefixes: return child_terminalPrefixes;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:cstRoot erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:cstRoot";
	}

	static void initProductionAttributeDefinitions() {
		// s.cstEnv = directBuildTree(s.cstDcls)
		silver.definition.concrete_syntax.ast.PcstRoot.childInheritedAttributes[silver.definition.concrete_syntax.ast.PcstRoot.i_s][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PdirectBuildTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PcstRoot.i_s, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// s.cstNTProds = directBuildTree(s.cstProds)
		silver.definition.concrete_syntax.ast.PcstRoot.childInheritedAttributes[silver.definition.concrete_syntax.ast.PcstRoot.i_s][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNTProds__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PdirectBuildTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PcstRoot.i_s, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// s.containingGrammar = "host"
		silver.definition.concrete_syntax.ast.PcstRoot.childInheritedAttributes[silver.definition.concrete_syntax.ast.PcstRoot.i_s][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("host")); } };
		// s.univLayout = error("TODO: make this environment not be decorated?")
		silver.definition.concrete_syntax.ast.PcstRoot.childInheritedAttributes[silver.definition.concrete_syntax.ast.PcstRoot.i_s][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_univLayout__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("TODO: make this environment not be decorated?")))); } };
		// s.prefixesForTerminals = error("TODO: shouldn't by necessary to normalize")
		silver.definition.concrete_syntax.ast.PcstRoot.childInheritedAttributes[silver.definition.concrete_syntax.ast.PcstRoot.i_s][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_prefixesForTerminals__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Perror.invoke((new common.StringCatter("TODO: shouldn't by necessary to normalize")))); } };
		// s2 = foldr(consSyntax, nilSyntax(), sortBy(syntaxDclLte, s.cstNormalize))
		silver.definition.concrete_syntax.ast.PcstRoot.localAttributes[silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsSyntax.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)new silver.definition.concrete_syntax.ast.PnilSyntax()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PsortBy.invoke(silver.definition.concrete_syntax.ast.PsyntaxDclLte.factory, context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PcstRoot.i_s, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_Syntax))); } })); } };
		// s2.cstEnv = directBuildTree(s.cstDcls)
		silver.definition.concrete_syntax.ast.PcstRoot.localInheritedAttributes[silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PdirectBuildTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PcstRoot.i_s, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// s2.containingGrammar = "host"
		silver.definition.concrete_syntax.ast.PcstRoot.localInheritedAttributes[silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("host")); } };
		// s2.cstNTProds = error("TODO: make this environment not be decorated?")
		silver.definition.concrete_syntax.ast.PcstRoot.localInheritedAttributes[silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNTProds__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Perror.invoke((new common.StringCatter("TODO: make this environment not be decorated?")))); } };
		// s2.prefixesForTerminals = directBuildTree(terminalPrefixes)
		silver.definition.concrete_syntax.ast.PcstRoot.localInheritedAttributes[silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_prefixesForTerminals__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PdirectBuildTree.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PcstRoot.i_terminalPrefixes))); } };
		// top.cstErrors := s.cstErrors
		if(silver.definition.concrete_syntax.ast.PcstRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] == null)
			silver.definition.concrete_syntax.ast.PcstRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PcstRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.PcstRoot.i_s).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_Syntax)); } });
		// startFound = searchEnvTree(startnt, s2.cstEnv)
		silver.definition.concrete_syntax.ast.PcstRoot.localAttributes[silver.definition.concrete_syntax.ast.Init.startFound__ON__silver_definition_concrete_syntax_ast_cstRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PcstRoot.i_startnt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_Syntax)); } })); } };
		// top.cstErrors <- if ! null(startFound) then [] else [ "Nonterminal " ++ startnt ++ " was referenced but " ++ "this grammar was not included in this parser. (Referenced as parser's starting nonterminal)" ]
		if(silver.definition.concrete_syntax.ast.PcstRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] == null)
			silver.definition.concrete_syntax.ast.PcstRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PcstRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.startFound__ON__silver_definition_concrete_syntax_ast_cstRoot)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Nonterminal ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PcstRoot.i_startnt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" was referenced but ")), (common.StringCatter)(new common.StringCatter("this grammar was not included in this parser. (Referenced as parser's starting nonterminal)"))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// univLayout = implode("", map(xmlCopperRef, s2.allIgnoreTerminals))
		silver.definition.concrete_syntax.ast.PcstRoot.localAttributes[silver.definition.concrete_syntax.ast.Init.univLayout__ON__silver_definition_concrete_syntax_ast_cstRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.concrete_syntax.ast.PxmlCopperRef.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allIgnoreTerminals__ON__silver_definition_concrete_syntax_ast_Syntax)); } })); } })); } };
		// s2.univLayout = univLayout
		silver.definition.concrete_syntax.ast.PcstRoot.localInheritedAttributes[silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_univLayout__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localAsIs(silver.definition.concrete_syntax.ast.Init.univLayout__ON__silver_definition_concrete_syntax_ast_cstRoot)); } };
		// top.xmlCopper = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<CopperSpec xmlns=\"http://melt.cs.umn.edu/copper/xmlns\">\n  <Parser id=\"" ++ makeCopperName(parsername) ++ "\" isUnitary=\"true\">\n    <PP>" ++ parsername ++ "</PP>\n    <Grammars><GrammarRef id=\"" ++ s2.containingGrammar ++ "\"/></Grammars>\n    <StartSymbol>" ++ xmlCopperRef(head(startFound)) ++ "</StartSymbol>\n" ++ "    <StartLayout>" ++ univLayout ++ "</StartLayout>\n" ++ "    <ClassAuxiliaryCode><Code><![CDATA[\n          protected List<common.Terminal> tokenList = null;\n\n          public void reset() {\n            tokenList = new ArrayList<common.Terminal>();\n          }\n\n          public List<common.Terminal> getTokens() {\n            return tokenList; // The way we reset this iterator when parsing again is to create a new list, so this is defacto immutable\n          }\n        ]]></Code></ClassAuxiliaryCode>\n" ++ "    <ParserInitCode>\n      <Code><![CDATA[\n        reset();\n      ]]></Code>\n    </ParserInitCode>\n    <Preamble>\n<Code><![CDATA[\nimport java.util.ArrayList;\nimport java.util.List;\n]]></Code>\n    </Preamble>\n" ++ "  </Parser>\n\n  <Grammar id=\"" ++ s2.containingGrammar ++ "\">\n\n    <PP>" ++ s2.containingGrammar ++ "</PP>\n\n" ++ "    <Layout>" ++ univLayout ++ "</Layout>\n    <Declarations>\n      <ParserAttribute id=\"context\">\n        <Type><![CDATA[common.DecoratedNode]]></Type>\n        <Code><![CDATA[context = common.TopNode.singleton;]]></Code>\n      </ParserAttribute>\n       " ++ s2.xmlCopper ++ "\n    </Declarations>\n  </Grammar>\n</CopperSpec>"
		silver.definition.concrete_syntax.ast.PcstRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<CopperSpec xmlns=\"http://melt.cs.umn.edu/copper/xmlns\">\n  <Parser id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PcstRoot.i_parsername))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" isUnitary=\"true\">\n    <PP>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PcstRoot.i_parsername)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</PP>\n    <Grammars><GrammarRef id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\"/></Grammars>\n    <StartSymbol>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PxmlCopperRef.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.startFound__ON__silver_definition_concrete_syntax_ast_cstRoot))); } })), (common.StringCatter)(new common.StringCatter("</StartSymbol>\n")))))))))), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <StartLayout>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.concrete_syntax.ast.Init.univLayout__ON__silver_definition_concrete_syntax_ast_cstRoot)), (common.StringCatter)(new common.StringCatter("</StartLayout>\n")))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <ClassAuxiliaryCode><Code><![CDATA[\n          protected List<common.Terminal> tokenList = null;\n\n          public void reset() {\n            tokenList = new ArrayList<common.Terminal>();\n          }\n\n          public List<common.Terminal> getTokens() {\n            return tokenList; // The way we reset this iterator when parsing again is to create a new list, so this is defacto immutable\n          }\n        ]]></Code></ClassAuxiliaryCode>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <ParserInitCode>\n      <Code><![CDATA[\n        reset();\n      ]]></Code>\n    </ParserInitCode>\n    <Preamble>\n<Code><![CDATA[\nimport java.util.ArrayList;\nimport java.util.List;\n]]></Code>\n    </Preamble>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("  </Parser>\n\n  <Grammar id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\">\n\n    <PP>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)(new common.StringCatter("</PP>\n\n")))))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Layout>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.concrete_syntax.ast.Init.univLayout__ON__silver_definition_concrete_syntax_ast_cstRoot)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</Layout>\n    <Declarations>\n      <ParserAttribute id=\"context\">\n        <Type><![CDATA[common.DecoratedNode]]></Type>\n        <Code><![CDATA[context = common.TopNode.singleton;]]></Code>\n      </ParserAttribute>\n       ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)(new common.StringCatter("\n    </Declarations>\n  </Grammar>\n</CopperSpec>"))))))))))); } };
		// top.unparse = implode(",\n ", s.unparses)
		silver.definition.concrete_syntax.ast.PcstRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_env_unparse__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(",\n ")), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PcstRoot.i_s, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_Syntax))); } };

	}

	public static final common.NodeFactory<PcstRoot> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcstRoot> {

		@Override
		public PcstRoot invoke(final Object[] children, final Object[] annotations) {
			return new PcstRoot(children[0], children[1], children[2], children[3]);
		}
	};

}
