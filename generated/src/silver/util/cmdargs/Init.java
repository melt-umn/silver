package silver.util.cmdargs;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.util.cmdargs.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.util.cmdargs.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.util.cmdargs.Init.postInit();


		common.Decorator.applyDecorators(silver.util.cmdargs.NCmdArgs.decorators, PendCmdArgs.class);
		common.Decorator.applyDecorators(silver.util.cmdargs.NCmdArgs.decorators, PerrorCmdArgs.class);
		common.Decorator.applyDecorators(silver.util.cmdargs.NFlag.decorators, Pflag.class);
		common.Decorator.applyDecorators(silver.util.cmdargs.NFlag.decorators, Poption.class);
		common.Decorator.applyDecorators(silver.util.cmdargs.NFlag.decorators, PnOptions.class);
	}

	private static void setupInheritedAttributes(){
		silver.util.cmdargs.NCmdArgs.occurs_syn[silver.util.cmdargs.Init.silver_util_cmdargs_cmdRemaining__ON__silver_util_cmdargs_CmdArgs] = "silver:util:cmdargs:cmdRemaining";
		silver.util.cmdargs.NCmdArgs.occurs_syn[silver.util.cmdargs.Init.silver_util_cmdargs_cmdError__ON__silver_util_cmdargs_CmdArgs] = "silver:util:cmdargs:cmdError";
		//	local attribute l::Maybe<Flag>;
		silver.util.cmdargs.PinterpretCmdArgs.localInheritedAttributes[silver.util.cmdargs.Init.l__ON__silver_util_cmdargs_interpretCmdArgs] = new common.Lazy[core.NMaybe.num_inh_attrs];
		silver.util.cmdargs.PinterpretCmdArgs.occurs_local[silver.util.cmdargs.Init.l__ON__silver_util_cmdargs_interpretCmdArgs] = "silver:util:cmdargs:interpretCmdArgs:local:l";
		//	local attribute here::Flag;
		silver.util.cmdargs.PinterpretCmdArgs.localInheritedAttributes[silver.util.cmdargs.Init.here__ON__silver_util_cmdargs_interpretCmdArgs] = new common.Lazy[silver.util.cmdargs.NFlag.num_inh_attrs];
		silver.util.cmdargs.PinterpretCmdArgs.occurs_local[silver.util.cmdargs.Init.here__ON__silver_util_cmdargs_interpretCmdArgs] = "silver:util:cmdargs:interpretCmdArgs:local:here";
		silver.util.cmdargs.NFlag.occurs_inh[silver.util.cmdargs.Init.silver_util_cmdargs_flagInput__ON__silver_util_cmdargs_Flag] = "silver:util:cmdargs:flagInput";
		silver.util.cmdargs.NFlag.occurs_syn[silver.util.cmdargs.Init.silver_util_cmdargs_flagOutput__ON__silver_util_cmdargs_Flag] = "silver:util:cmdargs:flagOutput";
		silver.util.cmdargs.NFlag.occurs_inh[silver.util.cmdargs.Init.silver_util_cmdargs_flagOriginal__ON__silver_util_cmdargs_Flag] = "silver:util:cmdargs:flagOriginal";
		silver.util.cmdargs.NFlag.occurs_syn[silver.util.cmdargs.Init.silver_util_cmdargs_flagModified__ON__silver_util_cmdargs_Flag] = "silver:util:cmdargs:flagModified";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION interpretCmdArgs CmdArgs ::= flags::[Pair<String Flag>] input::[String] 
		// l = lookupBy(stringEq, head(input), flags)
		silver.util.cmdargs.PinterpretCmdArgs.localAttributes[silver.util.cmdargs.Init.l__ON__silver_util_cmdargs_interpretCmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.PlookupBy.invoke(core.PstringEq.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.util.cmdargs.PinterpretCmdArgs.i_input))); } }, context.childAsIsLazy(silver.util.cmdargs.PinterpretCmdArgs.i_flags))); } };
		// here = l.fromJust
		silver.util.cmdargs.PinterpretCmdArgs.localAttributes[silver.util.cmdargs.Init.here__ON__silver_util_cmdargs_interpretCmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NFlag)context.localDecorated(silver.util.cmdargs.Init.l__ON__silver_util_cmdargs_interpretCmdArgs).synthesized(core.Init.core_fromJust__ON__core_Maybe)); } };
		// here.flagInput = input
		silver.util.cmdargs.PinterpretCmdArgs.localInheritedAttributes[silver.util.cmdargs.Init.here__ON__silver_util_cmdargs_interpretCmdArgs][silver.util.cmdargs.Init.silver_util_cmdargs_flagInput__ON__silver_util_cmdargs_Flag] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.util.cmdargs.PinterpretCmdArgs.i_input)); } };
		// here.flagOriginal = interpretCmdArgs(flags, here.flagOutput)
		silver.util.cmdargs.PinterpretCmdArgs.localInheritedAttributes[silver.util.cmdargs.Init.here__ON__silver_util_cmdargs_interpretCmdArgs][silver.util.cmdargs.Init.silver_util_cmdargs_flagOriginal__ON__silver_util_cmdargs_Flag] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NCmdArgs)silver.util.cmdargs.PinterpretCmdArgs.invoke(context.childAsIsLazy(silver.util.cmdargs.PinterpretCmdArgs.i_flags), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.util.cmdargs.Init.here__ON__silver_util_cmdargs_interpretCmdArgs).synthesized(silver.util.cmdargs.Init.silver_util_cmdargs_flagOutput__ON__silver_util_cmdargs_Flag)); } })); } };
		silver.util.cmdargs.PendCmdArgs.initProductionAttributeDefinitions();
		silver.util.cmdargs.PerrorCmdArgs.initProductionAttributeDefinitions();
		silver.util.cmdargs.Pflag.initProductionAttributeDefinitions();
		silver.util.cmdargs.Poption.initProductionAttributeDefinitions();
		silver.util.cmdargs.PnOptions.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__CmdArgs = 0;
	public static int count_syn__ON__CmdArgs = 0;
	public static int count_local__ON__silver_util_cmdargs_interpretCmdArgs = 0;
	public static int count_local__ON__silver_util_cmdargs_endCmdArgs = 0;
	public static int count_local__ON__silver_util_cmdargs_errorCmdArgs = 0;
	public static int count_inh__ON__Flag = 0;
	public static int count_syn__ON__Flag = 0;
	public static int count_local__ON__silver_util_cmdargs_flag = 0;
	public static int count_local__ON__silver_util_cmdargs_option = 0;
	public static int count_local__ON__silver_util_cmdargs_nOptions = 0;
public static final int silver_util_cmdargs_cmdRemaining__ON__silver_util_cmdargs_CmdArgs = silver.util.cmdargs.Init.count_syn__ON__CmdArgs++;
public static final int silver_util_cmdargs_cmdError__ON__silver_util_cmdargs_CmdArgs = silver.util.cmdargs.Init.count_syn__ON__CmdArgs++;
public static final int l__ON__silver_util_cmdargs_interpretCmdArgs = silver.util.cmdargs.Init.count_local__ON__silver_util_cmdargs_interpretCmdArgs++;
public static final int here__ON__silver_util_cmdargs_interpretCmdArgs = silver.util.cmdargs.Init.count_local__ON__silver_util_cmdargs_interpretCmdArgs++;
public static final int silver_util_cmdargs_flagInput__ON__silver_util_cmdargs_Flag = silver.util.cmdargs.Init.count_inh__ON__Flag++;
public static final int silver_util_cmdargs_flagOutput__ON__silver_util_cmdargs_Flag = silver.util.cmdargs.Init.count_syn__ON__Flag++;
public static final int silver_util_cmdargs_flagOriginal__ON__silver_util_cmdargs_Flag = silver.util.cmdargs.Init.count_inh__ON__Flag++;
public static final int silver_util_cmdargs_flagModified__ON__silver_util_cmdargs_Flag = silver.util.cmdargs.Init.count_syn__ON__Flag++;
}
