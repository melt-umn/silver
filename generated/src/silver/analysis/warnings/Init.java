package silver.analysis.warnings;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.driver.Init.initAllStatics();
		silver.util.cmdargs.Init.initAllStatics();
		silver.analysis.warnings.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.driver.util.Init.init();
		core.Init.init();
		silver.driver.Init.init();
		silver.util.cmdargs.Init.init();
		silver.analysis.warnings.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.driver.util.Init.postInit();
		core.Init.postInit();
		silver.driver.Init.postInit();
		silver.util.cmdargs.Init.postInit();
		silver.analysis.warnings.Init.postInit();


		common.Decorator.applyDecorators(silver.util.cmdargs.NCmdArgs.decorators, PwarnAllFlag.class);
	}

	private static void setupInheritedAttributes(){
		silver.util.cmdargs.NCmdArgs.occurs_syn[silver.analysis.warnings.Init.silver_analysis_warnings_warnAll__ON__silver_util_cmdargs_CmdArgs] = "silver:analysis:warnings:warnAll";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION endCmdArgs top ::= l::[String] 
		// top.warnAll = false
		silver.util.cmdargs.PendCmdArgs.synthesizedAttributes[silver.analysis.warnings.Init.silver_analysis_warnings_warnAll__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		silver.analysis.warnings.PwarnAllFlag.initProductionAttributeDefinitions();
		//ASPECT FUNCTION parseArgs Either<String Decorated CmdArgs> ::= args::[String] 
		// flags <- [ pair("--warn-all", flag(warnAllFlag)) ]
		((common.CollectionAttribute)silver.driver.PparseArgs.localAttributes[silver.driver.Init.flags__ON__silver_driver_parseArgs]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("--warn-all")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NFlag)new silver.util.cmdargs.Pflag(silver.analysis.warnings.PwarnAllFlag.factory)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// flagdescs <- [ "\t--warn-all  : enable all warnings" ]
		((common.CollectionAttribute)silver.driver.PparseArgs.localAttributes[silver.driver.Init.flagdescs__ON__silver_driver_parseArgs]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("\t--warn-all  : enable all warnings")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__silver_analysis_warnings_warnAllFlag = 0;
public static final int silver_analysis_warnings_warnAll__ON__silver_util_cmdargs_CmdArgs = silver.util.cmdargs.Init.count_syn__ON__CmdArgs++;
}
