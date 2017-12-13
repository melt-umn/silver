package silver.analysis.warnings.exporting;

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
		silver.driver.util.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.util.cmdargs.Init.initAllStatics();
		silver.driver.Init.initAllStatics();
		silver.analysis.warnings.exporting.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		silver.driver.util.Init.init();
		core.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.util.cmdargs.Init.init();
		silver.driver.Init.init();
		silver.analysis.warnings.exporting.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		silver.driver.util.Init.postInit();
		core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.util.cmdargs.Init.postInit();
		silver.driver.Init.postInit();
		silver.analysis.warnings.exporting.Init.postInit();


		common.Decorator.applyDecorators(silver.util.cmdargs.NCmdArgs.decorators, PdumpDepGraphFlag.class);
		common.Decorator.applyDecorators(silver.driver.util.NDriverAction.decorators, PdumpDepGraphAction.class);
	}

	private static void setupInheritedAttributes(){
		silver.util.cmdargs.NCmdArgs.occurs_syn[silver.analysis.warnings.exporting.Init.silver_analysis_warnings_exporting_dumpDepGraph__ON__silver_util_cmdargs_CmdArgs] = "silver:analysis:warnings:exporting:dumpDepGraph";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION endCmdArgs top ::= _ 
		// top.dumpDepGraph = false
		silver.util.cmdargs.PendCmdArgs.synthesizedAttributes[silver.analysis.warnings.exporting.Init.silver_analysis_warnings_exporting_dumpDepGraph__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		silver.analysis.warnings.exporting.PdumpDepGraphFlag.initProductionAttributeDefinitions();
		//ASPECT FUNCTION parseArgs Either<String Decorated CmdArgs> ::= args::[String] 
		// flags <- [ pair("--dump-import-graph", flag(dumpDepGraphFlag)) ]
		((common.CollectionAttribute)silver.driver.PparseArgs.localAttributes[silver.driver.Init.flags__ON__silver_driver_parseArgs]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("--dump-import-graph")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NFlag)new silver.util.cmdargs.Pflag(silver.analysis.warnings.exporting.PdumpDepGraphFlag.factory)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//ASPECT PRODUCTION compilation top ::= g::Grammars _ buildGrammar::String benv::BuildEnv 
		// top.postOps <- if top.config.dumpDepGraph then [ dumpDepGraphAction(g.grammarList) ] else []
		if(silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] == null)
			silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] = new silver.driver.util.CApostOps(silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation);
		((common.CollectionAttribute)silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((common.DecoratedNode)context.inherited(silver.driver.util.Init.silver_definition_env_config__ON__silver_driver_util_Compilation)).synthesized(silver.analysis.warnings.exporting.Init.silver_analysis_warnings_exporting_dumpDepGraph__ON__silver_util_cmdargs_CmdArgs)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.driver.util.NDriverAction)new silver.analysis.warnings.exporting.PdumpDepGraphAction(context.childDecoratedSynthesizedLazy(silver.driver.util.Pcompilation.i_g, silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		silver.analysis.warnings.exporting.PdumpDepGraphAction.initProductionAttributeDefinitions();
		//FUNCTION generateDotGraph String ::= specs::[Decorated RootSpec] 
		//FUNCTION makeDotArrow String ::= f::String t::String 
	}

	public static int count_local__ON__silver_analysis_warnings_exporting_dumpDepGraphFlag = 0;
	public static int count_local__ON__silver_analysis_warnings_exporting_dumpDepGraphAction = 0;
	public static int count_local__ON__silver_analysis_warnings_exporting_generateDotGraph = 0;
	public static int count_local__ON__silver_analysis_warnings_exporting_makeDotArrow = 0;
public static final int silver_analysis_warnings_exporting_dumpDepGraph__ON__silver_util_cmdargs_CmdArgs = silver.util.cmdargs.Init.count_syn__ON__CmdArgs++;
}
