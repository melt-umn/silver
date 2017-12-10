package edu.umn.cs.melt.ableC.drivers.compile;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.cppTags.Init.initAllStatics();
		core.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		silver.langutil.pp.Init.initAllStatics();
		silver.langutil.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.initAllStatics();
		edu.umn.cs.melt.ableC.drivers.compile.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.cppTags.Init.init();
		core.Init.init();
		core.monad.Init.init();
		silver.langutil.pp.Init.init();
		silver.langutil.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.init();
		edu.umn.cs.melt.ableC.drivers.compile.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.cppTags.Init.postInit();
		core.Init.postInit();
		core.monad.Init.postInit();
		silver.langutil.pp.Init.postInit();
		silver.langutil.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.postInit();
		edu.umn.cs.melt.ableC.drivers.compile.Init.postInit();


	}

	private static void setupInheritedAttributes(){
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.fileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:fileName";
		//	local attribute splitFileName::Pair<String String>;
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localInheritedAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.splitFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.splitFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:splitFileName";
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.baseFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:baseFileName";
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.cppFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:cppFileName";
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.ppFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:ppFileName";
		//	local attribute partitionedArgs::Pair<[String] [String]>;
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localInheritedAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.partitionedArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy[core.NPair.num_inh_attrs];
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.partitionedArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:partitionedArgs";
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.cppArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:cppArgs";
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.xcArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:xcArgs";
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.cppOptions__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:cppOptions";
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.cppCmd__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:cppCmd";
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.fullCppCmd__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:fullCppCmd";
		//	local attribute result::IOMonad<Integer>;
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localInheritedAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.result__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy[core.monad.NIOMonad.num_inh_attrs];
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.occurs_local[edu.umn.cs.melt.ableC.drivers.compile.Init.result__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = "edu:umn:cs:melt:ableC:drivers:compile:driver:local:result";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION driver IOVal<Integer> ::= args::[String] ioIn::IO theParser::(ParseResult<cst:Root> ::= String String) 
		// fileName = head(args,)
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.fileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))); } };
		// splitFileName = splitFileNameAndExtension(fileName,)
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.splitFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)core.PsplitFileNameAndExtension.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Init.fileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver))); } };
		// baseFileName = splitFileName.fst
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.baseFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(edu.umn.cs.melt.ableC.drivers.compile.Init.splitFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver).synthesized(core.Init.core_fst__ON__core_Pair)); } };
		// cppFileName = baseFileName ++ ".i"
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.cppFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(edu.umn.cs.melt.ableC.drivers.compile.Init.baseFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver)), (common.StringCatter)(new common.StringCatter(".i"))); } };
		// ppFileName = baseFileName ++ ".c"
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.ppFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(edu.umn.cs.melt.ableC.drivers.compile.Init.baseFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver)), (common.StringCatter)(new common.StringCatter(".c"))); } };
		// partitionedArgs = partition(partitionArg, tail(args,),)
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.partitionedArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)core.Ppartition.invoke(edu.umn.cs.melt.ableC.drivers.compile.PpartitionArg.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))); } })); } };
		// cppArgs = partitionedArgs.snd
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.cppArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(edu.umn.cs.melt.ableC.drivers.compile.Init.partitionedArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver).synthesized(core.Init.core_snd__ON__core_Pair)); } };
		// xcArgs = partitionedArgs.fst
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.xcArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(edu.umn.cs.melt.ableC.drivers.compile.Init.partitionedArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver).synthesized(core.Init.core_fst__ON__core_Pair)); } };
		// cppOptions = if length(args) >= 2 then implode(" ", cppArgs,) else ""
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.cppOptions__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))) >= Integer.valueOf((int)2)) ? ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(" ")), context.localAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Init.cppArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver))) : (new common.StringCatter(""))); } };
		// cppCmd = "gcc -E -x c -D _POSIX_C_SOURCE -std=gnu1x -I . " ++ cppOptions
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.cppCmd__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("gcc -E -x c -D _POSIX_C_SOURCE -std=gnu1x -I . ")), (common.StringCatter)((common.StringCatter)context.localAsIs(edu.umn.cs.melt.ableC.drivers.compile.Init.cppOptions__ON__edu_umn_cs_melt_ableC_drivers_compile_driver))); } };
		// fullCppCmd = cppCmd ++ " \"" ++ fileName ++ "\" > " ++ cppFileName
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.fullCppCmd__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(edu.umn.cs.melt.ableC.drivers.compile.Init.cppCmd__ON__edu_umn_cs_melt_ableC_drivers_compile_driver)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(edu.umn.cs.melt.ableC.drivers.compile.Init.fileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" > ")), (common.StringCatter)((common.StringCatter)context.localAsIs(edu.umn.cs.melt.ableC.drivers.compile.Init.cppFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver)))))); } };
		// result = do (bindIO, returnIO) {if null(args,) then {printM("Usage: [ableC invocation] [file name] [c preprocessor arguments]\n",); return 5;} else {isF::Boolean <- isFileM(fileName,); if ! isF then {printM("File \"" ++ fileName ++ "\" not found.\n",); return 1;} else {if containsBy(stringEq, "--show-cpp", args,) then printM("CPP command: " ++ fullCppCmd ++ "\n",); mkCppFile::Integer <- systemM(fullCppCmd,); if mkCppFile != 0 then {printM("CPP call failed: " ++ fullCppCmd ++ "\n",); return 3;} else {text::String <- readFileM(cppFileName,); result::ParseResult<cst:Root> = theParser(text, cppFileName,); if ! result.parseSuccess then {printM(result.parseErrors ++ "\n",); return 2;} else {comp::Decorated abs:Compilation = decorate abs:compilation(result.parseTree.ast,) with {env = addEnv(map(xcArgDef, xcArgs,), emptyEnv(,),);}; if containsBy(stringEq, "--show-ast", args,) then {printM(substitute("edu:umn:cs:melt:", "", hackUnparse(comp.abs:srcAst,),) ++ "\n",); return 0;} else if containsBy(stringEq, "--show-host-ast", args,) then {printM(substitute("edu:umn:cs:melt:", "", hackUnparse(comp.abs:hostAst,),) ++ "\n",); return 0;} else if containsBy(stringEq, "--show-lifted-ast", args,) then {printM(substitute("edu:umn:cs:melt:", "", hackUnparse(comp.abs:liftedAst,),) ++ "\n",); return 0;} else if containsBy(stringEq, "--show-pp", args,) then {printM(show(100, comp.abs:srcPP,) ++ "\n",); return 0;} else if containsBy(stringEq, "--show-host-pp", args,) then {printM(show(100, comp.abs:hostPP,) ++ "\n",); return 0;} else if containsBy(stringEq, "--show-lifted-pp", args,) then {printM(show(100, comp.abs:liftedPP,) ++ "\n",); return 0;} else {if ! null(comp.errors,) then printM(messagesToString(comp.errors,) ++ "\n",); if containsBy(stringEq, "--force-trans", args,) || ! containsErrors(comp.errors, false,) then writeFileM(ppFileName, show(80, comp.abs:finalPP,),); if containsErrors(comp.errors, false,) then return 4; else return 0;}}}}}}
		edu.umn.cs.melt.ableC.drivers.compile.Pdriver.localAttributes[edu.umn.cs.melt.ableC.drivers.compile.Init.result__ON__edu_umn_cs_melt_ableC_drivers_compile_driver] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))) ? ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PprintM((new common.StringCatter("Usage: [ableC invocation] [file name] [c preprocessor arguments]\n")))); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_28__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)5)));
  }
}))) : ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PisFileM(context.localAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Init.fileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver))); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_34_isF = args[0];

    return ((!((Boolean)common.Util.demand(__SV_LAMBDA_PARAM_34_isF))) ? ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PprintM(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("File \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(edu.umn.cs.melt.ableC.drivers.compile.Init.fileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver)), (common.StringCatter)(new common.StringCatter("\" not found.\n")))); } })); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_36__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)1)));
  }
}))) : ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)core.PcontainsBy.invoke(core.PstringEq.factory, (new common.StringCatter("--show-cpp")), context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))) ? ((core.monad.NIOMonad)new core.monad.PprintM(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("CPP command: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(edu.umn.cs.melt.ableC.drivers.compile.Init.fullCppCmd__ON__edu_umn_cs_melt_ableC_drivers_compile_driver)), (common.StringCatter)(new common.StringCatter("\n")))); } })) : ((core.monad.NIOMonad)new core.monad.PreturnIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NUnit)new core.Punit()); } }))); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_44__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PsystemM(context.localAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Init.fullCppCmd__ON__edu_umn_cs_melt_ableC_drivers_compile_driver))); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_46_mkCppFile = args[0];

    return (!((Integer)common.Util.demand(__SV_LAMBDA_PARAM_46_mkCppFile)).equals(Integer.valueOf((int)0)) ? ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PprintM(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("CPP call failed: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(edu.umn.cs.melt.ableC.drivers.compile.Init.fullCppCmd__ON__edu_umn_cs_melt_ableC_drivers_compile_driver)), (common.StringCatter)(new common.StringCatter("\n")))); } })); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_48__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)3)));
  }
}))) : ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PreadFileM(context.localAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Init.cppFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver))); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_53_text = args[0];

    return ((core.monad.NIOMonad)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_54_result = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NParseResult)((common.NodeFactory<core.NParseResult>)context.childAsIs(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_theParser)).invoke(new Object[]{((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_53_text)), context.localAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Init.cppFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver)}, null)); } };
return ((!((Boolean)((core.NParseResult)(__SV_LOCAL_54_result.eval())).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_parseSuccess__ON__core_ParseResult))) ? ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PprintM(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((core.NParseResult)(__SV_LOCAL_54_result.eval())).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_parseErrors__ON__core_ParseResult)), (common.StringCatter)(new common.StringCatter("\n"))); } })); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_60__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)2)));
  }
}))) : ((core.monad.NIOMonad)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_71_comp = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NCompilation)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pcompilation(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRoot)((edu.umn.cs.melt.ableC.concretesyntax.NRoot)((core.NParseResult)(__SV_LOCAL_54_result.eval())).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_parseTree__ON__core_ParseResult)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_Root)); } })).decorate(context, common.Util.populateInh(edu.umn.cs.melt.ableC.abstractsyntax.host.NCompilation.num_inh_attrs, new int[]{edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(edu.umn.cs.melt.ableC.drivers.compile.PxcArgDef.factory, context.localAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Init.xcArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PemptyEnv.invoke()); } })); } }})); } };
return (((Boolean)core.PcontainsBy.invoke(core.PstringEq.factory, (new common.StringCatter("--show-ast")), context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))) ? ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PprintM(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)core.Psubstitute.invoke((new common.StringCatter("edu:umn:cs:melt:")), (new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.PhackUnparse.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRoot)((common.DecoratedNode)(__SV_LOCAL_71_comp.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_srcAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } })); } })), (common.StringCatter)(new common.StringCatter("\n"))); } })); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_77__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)0)));
  }
}))) : (((Boolean)core.PcontainsBy.invoke(core.PstringEq.factory, (new common.StringCatter("--show-host-ast")), context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))) ? ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PprintM(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)core.Psubstitute.invoke((new common.StringCatter("edu:umn:cs:melt:")), (new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.PhackUnparse.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRoot)((common.DecoratedNode)(__SV_LOCAL_71_comp.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hostAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } })); } })), (common.StringCatter)(new common.StringCatter("\n"))); } })); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_85__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)0)));
  }
}))) : (((Boolean)core.PcontainsBy.invoke(core.PstringEq.factory, (new common.StringCatter("--show-lifted-ast")), context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))) ? ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PprintM(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)core.Psubstitute.invoke((new common.StringCatter("edu:umn:cs:melt:")), (new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.PhackUnparse.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRoot)((common.DecoratedNode)(__SV_LOCAL_71_comp.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_liftedAst__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } })); } })), (common.StringCatter)(new common.StringCatter("\n"))); } })); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_93__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)0)));
  }
}))) : (((Boolean)core.PcontainsBy.invoke(core.PstringEq.factory, (new common.StringCatter("--show-pp")), context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))) ? ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PprintM(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)silver.langutil.pp.Pshow.invoke(Integer.valueOf((int)100), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)((common.DecoratedNode)(__SV_LOCAL_71_comp.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_srcPP__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } })), (common.StringCatter)(new common.StringCatter("\n"))); } })); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_100__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)0)));
  }
}))) : (((Boolean)core.PcontainsBy.invoke(core.PstringEq.factory, (new common.StringCatter("--show-host-pp")), context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))) ? ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PprintM(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)silver.langutil.pp.Pshow.invoke(Integer.valueOf((int)100), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)((common.DecoratedNode)(__SV_LOCAL_71_comp.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_hostPP__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } })), (common.StringCatter)(new common.StringCatter("\n"))); } })); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_107__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)0)));
  }
}))) : (((Boolean)core.PcontainsBy.invoke(core.PstringEq.factory, (new common.StringCatter("--show-lifted-pp")), context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))) ? ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PprintM(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)silver.langutil.pp.Pshow.invoke(Integer.valueOf((int)100), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)((common.DecoratedNode)(__SV_LOCAL_71_comp.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_liftedPP__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } })), (common.StringCatter)(new common.StringCatter("\n"))); } })); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_114__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)0)));
  }
}))) : ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)(__SV_LOCAL_71_comp.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } }))) ? ((core.monad.NIOMonad)new core.monad.PprintM(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)silver.langutil.PmessagesToString.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)(__SV_LOCAL_71_comp.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } })), (common.StringCatter)(new common.StringCatter("\n"))); } })) : ((core.monad.NIOMonad)new core.monad.PreturnIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NUnit)new core.Punit()); } }))); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_123__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((((Boolean)core.PcontainsBy.invoke(core.PstringEq.factory, (new common.StringCatter("--force-trans")), context.childAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Pdriver.i_args))) || (!((Boolean)silver.langutil.PcontainsErrors.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)(__SV_LOCAL_71_comp.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } }, false)))) ? ((core.monad.NIOMonad)new core.monad.PwriteFileM(context.localAsIsLazy(edu.umn.cs.melt.ableC.drivers.compile.Init.ppFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.langutil.pp.Pshow.invoke(Integer.valueOf((int)80), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)((common.DecoratedNode)(__SV_LOCAL_71_comp.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_finalPP__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } })); } })) : ((core.monad.NIOMonad)new core.monad.PreturnIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NUnit)new core.Punit()); } }))); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_129__ = args[0];

    return (((Boolean)silver.langutil.PcontainsErrors.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)(__SV_LOCAL_71_comp.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Compilation)); } }, false)) ? ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)4))) : ((core.monad.NIOMonad)new core.monad.PreturnIO(Integer.valueOf((int)0))));
  }
})));
  }
}))))))))); } }).eval())); } }).eval());
  }
}))));
  }
})));
  }
}))));
  }
})))); } };
		//FUNCTION partitionArg Boolean ::= arg::String 
		//FUNCTION xcArgDef Def ::= arg::String 
	}

	public static int count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_partitionArg = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_xcArgDef = 0;
public static final int fileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
public static final int splitFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
public static final int baseFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
public static final int cppFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
public static final int ppFileName__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
public static final int partitionedArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
public static final int cppArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
public static final int xcArgs__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
public static final int cppOptions__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
public static final int cppCmd__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
public static final int fullCppCmd__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
public static final int result__ON__edu_umn_cs_melt_ableC_drivers_compile_driver = edu.umn.cs.melt.ableC.drivers.compile.Init.count_local__ON__edu_umn_cs_melt_ableC_drivers_compile_driver++;
}
