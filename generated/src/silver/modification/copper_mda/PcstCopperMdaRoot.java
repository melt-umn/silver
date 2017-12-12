
package silver.modification.copper_mda;

// top::SyntaxRoot ::= parsername::String startnt::String host::Syntax ext::Syntax terminalPrefixes::[Pair<String String>] 
public final class PcstCopperMdaRoot extends silver.definition.concrete_syntax.ast.NSyntaxRoot {

	public static final int i_parsername = 0;
	public static final int i_startnt = 1;
	public static final int i_host = 2;
	public static final int i_ext = 3;
	public static final int i_terminalPrefixes = 4;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,silver.definition.concrete_syntax.ast.NSyntax.class,silver.definition.concrete_syntax.ast.NSyntax.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_mda_cstCopperMdaRoot;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxRoot.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxRoot.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_host] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntax.num_inh_attrs];
	childInheritedAttributes[i_ext] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntax.num_inh_attrs];

	}

	public PcstCopperMdaRoot(final Object c_parsername, final Object c_startnt, final Object c_host, final Object c_ext, final Object c_terminalPrefixes) {
		super();
		this.child_parsername = c_parsername;
		this.child_startnt = c_startnt;
		this.child_host = c_host;
		this.child_ext = c_ext;
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

	private Object child_host;
	public final silver.definition.concrete_syntax.ast.NSyntax getChild_host() {
		return (silver.definition.concrete_syntax.ast.NSyntax) (child_host = common.Util.demand(child_host));
	}

	private Object child_ext;
	public final silver.definition.concrete_syntax.ast.NSyntax getChild_ext() {
		return (silver.definition.concrete_syntax.ast.NSyntax) (child_ext = common.Util.demand(child_ext));
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
			case i_host: return getChild_host();
			case i_ext: return getChild_ext();
			case i_terminalPrefixes: return getChild_terminalPrefixes();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_parsername: return child_parsername;
			case i_startnt: return child_startnt;
			case i_host: return child_host;
			case i_ext: return child_ext;
			case i_terminalPrefixes: return child_terminalPrefixes;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper_mda:cstCopperMdaRoot erroneously claimed to forward");
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
		return "silver:modification:copper_mda:cstCopperMdaRoot";
	}

	static void initProductionAttributeDefinitions() {
		// host.cstEnv = directBuildTree(host.cstDcls ++ ext.cstDcls)
		silver.modification.copper_mda.PcstCopperMdaRoot.childInheritedAttributes[silver.modification.copper_mda.PcstCopperMdaRoot.i_host][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PdirectBuildTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PcstCopperMdaRoot.i_host, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_Syntax), context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PcstCopperMdaRoot.i_ext, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_Syntax))); } })); } };
		// host.containingGrammar = "host"
		silver.modification.copper_mda.PcstCopperMdaRoot.childInheritedAttributes[silver.modification.copper_mda.PcstCopperMdaRoot.i_host][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("host")); } };
		// host.cstNTProds = error("TODO: this should only be used by normalize")
		silver.modification.copper_mda.PcstCopperMdaRoot.childInheritedAttributes[silver.modification.copper_mda.PcstCopperMdaRoot.i_host][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNTProds__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Perror.invoke((new common.StringCatter("TODO: this should only be used by normalize")))); } };
		// host.prefixesForTerminals = directBuildTree(terminalPrefixes)
		silver.modification.copper_mda.PcstCopperMdaRoot.childInheritedAttributes[silver.modification.copper_mda.PcstCopperMdaRoot.i_host][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_prefixesForTerminals__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PdirectBuildTree.invoke(context.childAsIsLazy(silver.modification.copper_mda.PcstCopperMdaRoot.i_terminalPrefixes))); } };
		// ext.cstEnv = host.cstEnv
		silver.modification.copper_mda.PcstCopperMdaRoot.childInheritedAttributes[silver.modification.copper_mda.PcstCopperMdaRoot.i_ext][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.modification.copper_mda.PcstCopperMdaRoot.i_host).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_Syntax)); } };
		// ext.containingGrammar = "ext"
		silver.modification.copper_mda.PcstCopperMdaRoot.childInheritedAttributes[silver.modification.copper_mda.PcstCopperMdaRoot.i_ext][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("ext")); } };
		// ext.cstNTProds = error("TODO: this should only be used by normalize")
		silver.modification.copper_mda.PcstCopperMdaRoot.childInheritedAttributes[silver.modification.copper_mda.PcstCopperMdaRoot.i_ext][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNTProds__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Perror.invoke((new common.StringCatter("TODO: this should only be used by normalize")))); } };
		// ext.prefixesForTerminals = host.prefixesForTerminals
		silver.modification.copper_mda.PcstCopperMdaRoot.childInheritedAttributes[silver.modification.copper_mda.PcstCopperMdaRoot.i_ext][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_prefixesForTerminals__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.modification.copper_mda.PcstCopperMdaRoot.i_host).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_prefixesForTerminals__ON__silver_definition_concrete_syntax_ast_Syntax)); } };
		// startFound = searchEnvTree(startnt, host.cstEnv)
		silver.modification.copper_mda.PcstCopperMdaRoot.localAttributes[silver.modification.copper_mda.Init.startFound__ON__silver_modification_copper_mda_cstCopperMdaRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childAsIsLazy(silver.modification.copper_mda.PcstCopperMdaRoot.i_startnt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.modification.copper_mda.PcstCopperMdaRoot.i_host).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_Syntax)); } })); } };
		// top.cstErrors := host.cstErrors ++ ext.cstErrors
		if(silver.modification.copper_mda.PcstCopperMdaRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] == null)
			silver.modification.copper_mda.PcstCopperMdaRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot);
		((common.CollectionAttribute)silver.modification.copper_mda.PcstCopperMdaRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PcstCopperMdaRoot.i_host, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_Syntax), context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PcstCopperMdaRoot.i_ext, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_Syntax))); } });
		// top.cstErrors <- if ! null(startFound) then [] else [ "Nonterminal " ++ startnt ++ " was referenced but " ++ "this grammar was not included in this parser. (Referenced as parser's starting nonterminal)" ]
		if(silver.modification.copper_mda.PcstCopperMdaRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] == null)
			silver.modification.copper_mda.PcstCopperMdaRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot);
		((common.CollectionAttribute)silver.modification.copper_mda.PcstCopperMdaRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.modification.copper_mda.Init.startFound__ON__silver_modification_copper_mda_cstCopperMdaRoot)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Nonterminal ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.copper_mda.PcstCopperMdaRoot.i_startnt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" was referenced but ")), (common.StringCatter)(new common.StringCatter("this grammar was not included in this parser. (Referenced as parser's starting nonterminal)"))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// univLayout = implode("", map(xmlCopperRef, host.allIgnoreTerminals))
		silver.modification.copper_mda.PcstCopperMdaRoot.localAttributes[silver.modification.copper_mda.Init.univLayout__ON__silver_modification_copper_mda_cstCopperMdaRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.concrete_syntax.ast.PxmlCopperRef.factory, context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PcstCopperMdaRoot.i_host, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allIgnoreTerminals__ON__silver_definition_concrete_syntax_ast_Syntax))); } })); } };
		// host.univLayout = univLayout
		silver.modification.copper_mda.PcstCopperMdaRoot.childInheritedAttributes[silver.modification.copper_mda.PcstCopperMdaRoot.i_host][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_univLayout__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localAsIs(silver.modification.copper_mda.Init.univLayout__ON__silver_modification_copper_mda_cstCopperMdaRoot)); } };
		// ext.univLayout = univLayout
		silver.modification.copper_mda.PcstCopperMdaRoot.childInheritedAttributes[silver.modification.copper_mda.PcstCopperMdaRoot.i_ext][silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_univLayout__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localAsIs(silver.modification.copper_mda.Init.univLayout__ON__silver_modification_copper_mda_cstCopperMdaRoot)); } };
		// top.xmlCopper = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n" ++ "<CopperSpec xmlns=\"http://melt.cs.umn.edu/copper/xmlns\">\n" ++ "  <ExtendedParser id=\"" ++ makeCopperName(parsername) ++ "\">\n" ++ "    <PP>" ++ parsername ++ "</PP>\n" ++ "    <HostGrammar>\n" ++ "      <GrammarRef id=\"" ++ host.containingGrammar ++ "\"/>\n" ++ "    </HostGrammar>\n" ++ "    <ExtensionGrammars>\n" ++ "      <GrammarRef id=\"" ++ ext.containingGrammar ++ "\"/>\n" ++ "    </ExtensionGrammars>\n" ++ "    <StartSymbol>" ++ xmlCopperRef(head(startFound)) ++ "</StartSymbol>\n" ++ "    <StartLayout>" ++ univLayout ++ "</StartLayout>\n" ++ "  </ExtendedParser>\n\n" ++ "  <Grammar id=\"" ++ host.containingGrammar ++ "\">\n\n" ++ "    <PP>" ++ host.containingGrammar ++ "</PP>\n\n" ++ "    <Layout>" ++ univLayout ++ "</Layout>\n\n" ++ "    <Declarations>\n" ++ "      <ParserAttribute id=\"context\">\n" ++ "        <Type><![CDATA[common.DecoratedNode]]></Type>\n" ++ "        <Code><![CDATA[context = common.TopNode.singleton;]]></Code>\n" ++ "      </ParserAttribute>\n" ++ host.xmlCopper ++ "    </Declarations>\n" ++ "  </Grammar>\n\n" ++ "  <ExtensionGrammar id=\"" ++ ext.containingGrammar ++ "\">\n" ++ "    <PP>" ++ host.containingGrammar ++ "</PP>\n\n" ++ "    <Layout>" ++ univLayout ++ "</Layout>\n\n" ++ "    <MarkingTerminals>\n" ++ implode("", map(xmlCopperRef, ext.markingTokens)) ++ "    </MarkingTerminals>\n" ++ "    <BridgeProductions>\n" ++ implode("", map(xmlCopperRef, ext.bridgeProductions)) ++ "    </BridgeProductions>\n" ++ "    <Declarations>\n" ++ ext.xmlCopper ++ "    </Declarations>\n" ++ "  </ExtensionGrammar>\n\n" ++ "</CopperSpec>\n"
		silver.modification.copper_mda.PcstCopperMdaRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("<CopperSpec xmlns=\"http://melt.cs.umn.edu/copper/xmlns\">\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("  <ExtendedParser id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(context.childAsIsLazy(silver.modification.copper_mda.PcstCopperMdaRoot.i_parsername))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\">\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <PP>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.copper_mda.PcstCopperMdaRoot.i_parsername)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</PP>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <HostGrammar>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("      <GrammarRef id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PcstCopperMdaRoot.i_host).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\"/>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    </HostGrammar>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <ExtensionGrammars>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("      <GrammarRef id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PcstCopperMdaRoot.i_ext).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\"/>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    </ExtensionGrammars>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <StartSymbol>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PxmlCopperRef.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.modification.copper_mda.Init.startFound__ON__silver_modification_copper_mda_cstCopperMdaRoot))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</StartSymbol>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <StartLayout>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper_mda.Init.univLayout__ON__silver_modification_copper_mda_cstCopperMdaRoot)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</StartLayout>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("  </ExtendedParser>\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("  <Grammar id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PcstCopperMdaRoot.i_host).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\">\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <PP>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PcstCopperMdaRoot.i_host).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</PP>\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Layout>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper_mda.Init.univLayout__ON__silver_modification_copper_mda_cstCopperMdaRoot)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</Layout>\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Declarations>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("      <ParserAttribute id=\"context\">\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("        <Type><![CDATA[common.DecoratedNode]]></Type>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("        <Code><![CDATA[context = common.TopNode.singleton;]]></Code>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("      </ParserAttribute>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PcstCopperMdaRoot.i_host).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    </Declarations>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("  </Grammar>\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("  <ExtensionGrammar id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PcstCopperMdaRoot.i_ext).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\">\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <PP>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PcstCopperMdaRoot.i_host).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</PP>\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Layout>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper_mda.Init.univLayout__ON__silver_modification_copper_mda_cstCopperMdaRoot)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("</Layout>\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <MarkingTerminals>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.concrete_syntax.ast.PxmlCopperRef.factory, context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PcstCopperMdaRoot.i_ext, silver.modification.copper_mda.Init.silver_modification_copper_mda_markingTokens__ON__silver_definition_concrete_syntax_ast_Syntax))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    </MarkingTerminals>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <BridgeProductions>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.concrete_syntax.ast.PxmlCopperRef.factory, context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PcstCopperMdaRoot.i_ext, silver.modification.copper_mda.Init.silver_modification_copper_mda_bridgeProductions__ON__silver_definition_concrete_syntax_ast_Syntax))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    </BridgeProductions>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    <Declarations>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PcstCopperMdaRoot.i_ext).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_Syntax)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("    </Declarations>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("  </ExtensionGrammar>\n\n")), (common.StringCatter)(new common.StringCatter("</CopperSpec>\n"))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))); } };
		// top.unparse = error("No notion of unparsing for mda root...")
		silver.modification.copper_mda.PcstCopperMdaRoot.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_env_unparse__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("No notion of unparsing for mda root...")))); } };

	}

	public static final common.NodeFactory<PcstCopperMdaRoot> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcstCopperMdaRoot> {

		@Override
		public PcstCopperMdaRoot invoke(final Object[] children, final Object[] annotations) {
			return new PcstCopperMdaRoot(children[0], children[1], children[2], children[3], children[4]);
		}
	};

}
