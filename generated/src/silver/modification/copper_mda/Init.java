package silver.modification.copper_mda;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.modification.copper.Init.initAllStatics();
		silver.definition.concrete_syntax.ast.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();
		silver.util.cmdargs.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.translation.java.driver.Init.initAllStatics();
		silver.driver.Init.initAllStatics();
		silver.modification.copper_mda.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.modification.copper.Init.init();
		silver.definition.concrete_syntax.ast.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.driver.util.Init.init();
		silver.util.cmdargs.Init.init();
		silver.translation.java.core.Init.init();
		silver.translation.java.driver.Init.init();
		silver.driver.Init.init();
		silver.modification.copper_mda.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.modification.copper.Init.postInit();
		silver.definition.concrete_syntax.ast.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.driver.util.Init.postInit();
		silver.util.cmdargs.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.translation.java.driver.Init.postInit();
		silver.driver.Init.postInit();
		silver.modification.copper_mda.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxRoot.decorators, PcstCopperMdaRoot.class);
		common.Decorator.applyDecorators(silver.driver.util.NDriverAction.decorators, PgenerateMdaSpec.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PcopperMdaDcl.class);
		common.Decorator.applyDecorators(silver.modification.copper_mda.NMdaSpec.decorators, PmdaSpec.class);
	}

	private static void setupInheritedAttributes(){
		silver.modification.copper_mda.PcstCopperMdaRoot.occurs_local[silver.modification.copper_mda.Init.startFound__ON__silver_modification_copper_mda_cstCopperMdaRoot] = "silver:modification:copper_mda:cstCopperMdaRoot:local:startFound";
		silver.modification.copper_mda.PcstCopperMdaRoot.occurs_local[silver.modification.copper_mda.Init.univLayout__ON__silver_modification_copper_mda_cstCopperMdaRoot] = "silver:modification:copper_mda:cstCopperMdaRoot:local:univLayout";
		silver.definition.core.NRoot.occurs_syn[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_Root] = "silver:modification:copper_mda:mdaSpecs";
		silver.definition.core.NAGDcls.occurs_syn[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcls] = "silver:modification:copper_mda:mdaSpecs";
		silver.definition.core.NAGDcl.occurs_syn[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcl] = "silver:modification:copper_mda:mdaSpecs";
		silver.driver.util.NRootSpec.occurs_syn[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_driver_util_RootSpec] = "silver:modification:copper_mda:mdaSpecs";
		silver.definition.core.NGrammar.occurs_syn[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_Grammar] = "silver:modification:copper_mda:mdaSpecs";
		silver.driver.util.Pcompilation.occurs_local[silver.modification.copper_mda.Init.targets__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:targets";
		//	local attribute ast::SyntaxRoot;
		silver.modification.copper_mda.PgenerateMdaSpec.localInheritedAttributes[silver.modification.copper_mda.Init.ast__ON__silver_modification_copper_mda_generateMdaSpec] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxRoot.num_inh_attrs];
		silver.modification.copper_mda.PgenerateMdaSpec.occurs_local[silver.modification.copper_mda.Init.ast__ON__silver_modification_copper_mda_generateMdaSpec] = "silver:modification:copper_mda:generateMdaSpec:local:ast";
		silver.modification.copper_mda.PgenerateMdaSpec.occurs_local[silver.modification.copper_mda.Init.parserName__ON__silver_modification_copper_mda_generateMdaSpec] = "silver:modification:copper_mda:generateMdaSpec:local:parserName";
		silver.modification.copper_mda.PgenerateMdaSpec.occurs_local[silver.modification.copper_mda.Init.copperFile__ON__silver_modification_copper_mda_generateMdaSpec] = "silver:modification:copper_mda:generateMdaSpec:local:copperFile";
		silver.modification.copper_mda.PgenerateMdaSpec.occurs_local[silver.modification.copper_mda.Init.err__ON__silver_modification_copper_mda_generateMdaSpec] = "silver:modification:copper_mda:generateMdaSpec:local:err";
		silver.modification.copper_mda.PgenerateMdaSpec.occurs_local[silver.modification.copper_mda.Init.printio__ON__silver_modification_copper_mda_generateMdaSpec] = "silver:modification:copper_mda:generateMdaSpec:local:printio";
		silver.modification.copper_mda.PgenerateMdaSpec.occurs_local[silver.modification.copper_mda.Init.writeio__ON__silver_modification_copper_mda_generateMdaSpec] = "silver:modification:copper_mda:generateMdaSpec:local:writeio";
		silver.modification.copper_mda.PcopperMdaDcl.occurs_local[silver.modification.copper_mda.Init.spec__ON__silver_modification_copper_mda_copperMdaDcl] = "silver:modification:copper_mda:copperMdaDcl:local:spec";
		silver.modification.copper_mda.NMdaSpec.occurs_syn[silver.modification.copper_mda.Init.silver_definition_env_sourceGrammar__ON__silver_modification_copper_mda_MdaSpec] = "silver:definition:env:sourceGrammar";
		silver.modification.copper_mda.NMdaSpec.occurs_syn[silver.modification.copper_mda.Init.silver_definition_env_fullName__ON__silver_modification_copper_mda_MdaSpec] = "silver:definition:env:fullName";
		silver.modification.copper_mda.NMdaSpec.occurs_inh[silver.modification.copper_mda.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_mda_MdaSpec] = "silver:definition:env:compiledGrammars";
		silver.modification.copper_mda.NMdaSpec.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.copper_mda.NMdaSpec.occurs_syn[silver.modification.copper_mda.Init.silver_definition_concrete_syntax_cstAst__ON__silver_modification_copper_mda_MdaSpec] = "silver:definition:concrete_syntax:cstAst";
		//	local attribute hostmed::ModuleExportedDefs;
		silver.modification.copper_mda.PmdaSpec.localInheritedAttributes[silver.modification.copper_mda.Init.hostmed__ON__silver_modification_copper_mda_mdaSpec] = new common.Lazy[silver.definition.core.NModuleExportedDefs.num_inh_attrs];
		silver.modification.copper_mda.PmdaSpec.occurs_local[silver.modification.copper_mda.Init.hostmed__ON__silver_modification_copper_mda_mdaSpec] = "silver:modification:copper_mda:mdaSpec:local:hostmed";
		//	local attribute extmed::ModuleExportedDefs;
		silver.modification.copper_mda.PmdaSpec.localInheritedAttributes[silver.modification.copper_mda.Init.extmed__ON__silver_modification_copper_mda_mdaSpec] = new common.Lazy[silver.definition.core.NModuleExportedDefs.num_inh_attrs];
		silver.modification.copper_mda.PmdaSpec.occurs_local[silver.modification.copper_mda.Init.extmed__ON__silver_modification_copper_mda_mdaSpec] = "silver:modification:copper_mda:mdaSpec:local:extmed";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.modification.copper_mda.Init.silver_modification_copper_mda_markingTokens__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:modification:copper_mda:markingTokens";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.modification.copper_mda.Init.silver_modification_copper_mda_markingTokens__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:modification:copper_mda:markingTokens";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.modification.copper_mda.Init.silver_modification_copper_mda_bridgeProductions__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:modification:copper_mda:bridgeProductions";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.modification.copper_mda.Init.silver_modification_copper_mda_bridgeProductions__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:modification:copper_mda:bridgeProductions";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.copper_mda.PcstCopperMdaRoot.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION root top ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls 
		// top.mdaSpecs = ags.mdaSpecs
		silver.definition.core.Proot.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.Proot.i_ags).synthesized(silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcls)); } };
		//ASPECT PRODUCTION nilAGDcls top ::= 
		// top.mdaSpecs = []
		silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION consAGDcls top ::= h::AGDcl t::AGDcls 
		// top.mdaSpecs = h.mdaSpecs ++ t.mdaSpecs
		silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_h, silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_t, silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcls))); } };
		//ASPECT DEFAULT PRODUCTION for AGDcl
		// top.mdaSpecs = []
		silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION appendAGDcl top ::= ag1::AGDcl ag2::AGDcl 
		// top.mdaSpecs = ag1.mdaSpecs ++ ag2.mdaSpecs
		silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_h, silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_t, silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcl))); } };
		//ASPECT PRODUCTION grammarRootSpec top ::= g::Grammar _ _ _ 
		// top.mdaSpecs = g.mdaSpecs
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_Grammar)); } };
		//ASPECT PRODUCTION interfaceRootSpec top ::= _ _ 
		// top.mdaSpecs = []
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION errorRootSpec top ::= _ _ _ _ 
		// top.mdaSpecs = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION nilGrammar top ::= 
		// top.mdaSpecs = []
		silver.definition.core.PnilGrammar.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION consGrammar top ::= h::Root t::Grammar 
		// top.mdaSpecs = h.mdaSpecs ++ t.mdaSpecs
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_Grammar))); } };
		//ASPECT PRODUCTION compilation top ::= g::Grammars _ buildGrammar::String benv::BuildEnv 
		// top.postOps <- map(generateMdaSpec(g.compiledGrammars, _, benv.silverGen ++ "src/"), flatMap((.mdaSpecs), grammarsToTranslate))
		if(silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] == null)
			silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] = new silver.driver.util.CApostOps(silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation);
		((common.CollectionAttribute)silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.modification.copper_mda.PgenerateMdaSpec.factory.invokePartial(new int[]{0, 2}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.driver.util.Pcompilation.i_g).inherited(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_Grammars)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.driver.util.Pcompilation.i_benv).synthesized(silver.driver.util.Init.silver_driver_util_silverGen__ON__silver_driver_util_BuildEnv)), (common.StringCatter)(new common.StringCatter("src/"))); } }}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(new common.AttributeSection(silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_driver_util_RootSpec), context.localAsIsLazy(silver.driver.util.Init.grammarsToTranslate__ON__silver_driver_util_compilation))); } })); } });
		// targets = foldr(\ a::Decorated RootSpec b::String  -> foldr(\ c::MdaSpec d::String  -> mdaBuildSpecTarget(c) ++ d, "", a.mdaSpecs) ++ b, "", grammarsToTranslate)
		silver.driver.util.Pcompilation.localAttributes[silver.modification.copper_mda.Init.targets__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pfoldr.invoke((new common.NodeFactory<common.StringCatter>() {
  public final common.StringCatter invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_8241_a = args[0];
final Object __SV_LAMBDA_PARAM_8242_b = args[1];

    return new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pfoldr.invoke((new common.NodeFactory<common.StringCatter>() {
  public final common.StringCatter invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_8244_c = args[0];
final Object __SV_LAMBDA_PARAM_8245_d = args[1];

    return new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.copper_mda.PmdaBuildSpecTarget.invoke(((silver.modification.copper_mda.NMdaSpec)common.Util.demand(__SV_LAMBDA_PARAM_8244_c)))), (common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_8245_d)));
  }
}), (new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_8241_a)).synthesized(silver.modification.copper_mda.Init.silver_modification_copper_mda_mdaSpecs__ON__silver_driver_util_RootSpec)); } })), (common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_8242_b)));
  }
}), (new common.StringCatter("")), context.localAsIsLazy(silver.driver.util.Init.grammarsToTranslate__ON__silver_driver_util_compilation))); } };
		// extraTopLevelDecls <- if length(targets) == 0 then [] else [ "  <target name='copper_mda'>\n" ++ targets ++ "  </target>\n" ]
		((common.CollectionAttribute)silver.driver.util.Pcompilation.localAttributes[silver.translation.java.driver.Init.extraTopLevelDecls__ON__silver_driver_util_compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (Integer.valueOf(((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper_mda.Init.targets__ON__silver_driver_util_compilation))).length()).equals(Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("  <target name='copper_mda'>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper_mda.Init.targets__ON__silver_driver_util_compilation)), (common.StringCatter)(new common.StringCatter("  </target>\n")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// extraGrammarsDeps <- if length(targets) == 0 then [] else [ "copper_mda" ]
		((common.CollectionAttribute)silver.driver.util.Pcompilation.localAttributes[silver.translation.java.driver.Init.extraGrammarsDeps__ON__silver_driver_util_compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (Integer.valueOf(((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper_mda.Init.targets__ON__silver_driver_util_compilation))).length()).equals(Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("copper_mda")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		silver.modification.copper_mda.PgenerateMdaSpec.initProductionAttributeDefinitions();
		//FUNCTION mdaBuildSpecTarget String ::= spec::MdaSpec 
		silver.modification.copper_mda.PcopperMdaDcl.initProductionAttributeDefinitions();
		//FUNCTION findSpec [ParserSpec] ::= n::String s::[ParserSpec] 
		silver.modification.copper_mda.PmdaSpec.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION nilSyntax top ::= 
		// top.markingTokens = []
		silver.definition.concrete_syntax.ast.PnilSyntax.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_markingTokens__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.bridgeProductions = []
		silver.definition.concrete_syntax.ast.PnilSyntax.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_bridgeProductions__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION consSyntax top ::= s1::SyntaxDcl s2::Syntax 
		// top.markingTokens = s1.markingTokens ++ s2.markingTokens
		silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_markingTokens__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1, silver.modification.copper_mda.Init.silver_modification_copper_mda_markingTokens__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2, silver.modification.copper_mda.Init.silver_modification_copper_mda_markingTokens__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// top.bridgeProductions = s1.bridgeProductions ++ s2.bridgeProductions
		silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_bridgeProductions__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1, silver.modification.copper_mda.Init.silver_modification_copper_mda_bridgeProductions__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2, silver.modification.copper_mda.Init.silver_modification_copper_mda_bridgeProductions__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		//ASPECT DEFAULT PRODUCTION for SyntaxDcl
		// top.markingTokens = []
		silver.definition.concrete_syntax.ast.NSyntaxDcl.defaultSynthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_markingTokens__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.bridgeProductions = []
		silver.definition.concrete_syntax.ast.NSyntaxDcl.defaultSynthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_bridgeProductions__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION syntaxTerminal top ::= n::String _ modifiers::SyntaxTerminalModifiers 
		// top.markingTokens = if modifiers.marking then [ top ] else []
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_markingTokens__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_marking__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)) ? ((common.ConsCell)core.Pcons.invoke(context, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } };
		//ASPECT PRODUCTION syntaxProduction top ::= ns::NamedSignature modifiers::SyntaxProductionModifiers 
		// top.bridgeProductions = if ! null(lhsRef) && top.containingGrammar != head(lhsRef).containingGrammar then [ top ] else []
		silver.definition.concrete_syntax.ast.PsyntaxProduction.synthesizedAttributes[silver.modification.copper_mda.Init.silver_modification_copper_mda_bridgeProductions__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.lhsRef__ON__silver_definition_concrete_syntax_ast_syntaxProduction)))) && !((common.StringCatter)context.inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_SyntaxDcl)).equals(((common.StringCatter)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.lhsRef__ON__silver_definition_concrete_syntax_ast_syntaxProduction))).inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_SyntaxDcl)))) ? ((common.ConsCell)core.Pcons.invoke(context, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } };
	}

	public static int count_local__ON__silver_modification_copper_mda_cstCopperMdaRoot = 0;
	public static int count_local__ON__silver_modification_copper_mda_generateMdaSpec = 0;
	public static int count_local__ON__silver_modification_copper_mda_mdaBuildSpecTarget = 0;
	public static int count_local__ON__silver_modification_copper_mda_copperMdaDcl = 0;
	public static int count_local__ON__silver_modification_copper_mda_findSpec = 0;
	public static int count_inh__ON__MdaSpec = 0;
	public static int count_syn__ON__MdaSpec = 0;
	public static int count_local__ON__silver_modification_copper_mda_mdaSpec = 0;
public static final int startFound__ON__silver_modification_copper_mda_cstCopperMdaRoot = silver.modification.copper_mda.Init.count_local__ON__silver_modification_copper_mda_cstCopperMdaRoot++;
public static final int univLayout__ON__silver_modification_copper_mda_cstCopperMdaRoot = silver.modification.copper_mda.Init.count_local__ON__silver_modification_copper_mda_cstCopperMdaRoot++;
public static final int silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_Root = silver.definition.core.Init.count_syn__ON__Root++;
public static final int silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcls = silver.definition.core.Init.count_syn__ON__AGDcls++;
public static final int silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_syn__ON__AGDcl++;
public static final int silver_modification_copper_mda_mdaSpecs__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_modification_copper_mda_mdaSpecs__ON__silver_definition_core_Grammar = silver.definition.core.Init.count_syn__ON__Grammar++;
public static final int targets__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int ast__ON__silver_modification_copper_mda_generateMdaSpec = silver.modification.copper_mda.Init.count_local__ON__silver_modification_copper_mda_generateMdaSpec++;
public static final int parserName__ON__silver_modification_copper_mda_generateMdaSpec = silver.modification.copper_mda.Init.count_local__ON__silver_modification_copper_mda_generateMdaSpec++;
public static final int copperFile__ON__silver_modification_copper_mda_generateMdaSpec = silver.modification.copper_mda.Init.count_local__ON__silver_modification_copper_mda_generateMdaSpec++;
public static final int err__ON__silver_modification_copper_mda_generateMdaSpec = silver.modification.copper_mda.Init.count_local__ON__silver_modification_copper_mda_generateMdaSpec++;
public static final int printio__ON__silver_modification_copper_mda_generateMdaSpec = silver.modification.copper_mda.Init.count_local__ON__silver_modification_copper_mda_generateMdaSpec++;
public static final int writeio__ON__silver_modification_copper_mda_generateMdaSpec = silver.modification.copper_mda.Init.count_local__ON__silver_modification_copper_mda_generateMdaSpec++;
public static final int spec__ON__silver_modification_copper_mda_copperMdaDcl = silver.modification.copper_mda.Init.count_local__ON__silver_modification_copper_mda_copperMdaDcl++;
public static final int silver_definition_env_sourceGrammar__ON__silver_modification_copper_mda_MdaSpec = silver.modification.copper_mda.Init.count_syn__ON__MdaSpec++;
public static final int silver_definition_env_fullName__ON__silver_modification_copper_mda_MdaSpec = silver.modification.copper_mda.Init.count_syn__ON__MdaSpec++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_copper_mda_MdaSpec = silver.modification.copper_mda.Init.count_inh__ON__MdaSpec++;
public static final int silver_definition_concrete_syntax_cstAst__ON__silver_modification_copper_mda_MdaSpec = silver.modification.copper_mda.Init.count_syn__ON__MdaSpec++;
public static final int hostmed__ON__silver_modification_copper_mda_mdaSpec = silver.modification.copper_mda.Init.count_local__ON__silver_modification_copper_mda_mdaSpec++;
public static final int extmed__ON__silver_modification_copper_mda_mdaSpec = silver.modification.copper_mda.Init.count_local__ON__silver_modification_copper_mda_mdaSpec++;
public static final int silver_modification_copper_mda_markingTokens__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_modification_copper_mda_markingTokens__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_modification_copper_mda_bridgeProductions__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_modification_copper_mda_bridgeProductions__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
}
