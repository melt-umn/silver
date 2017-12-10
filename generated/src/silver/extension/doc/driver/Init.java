package silver.extension.doc.driver;

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
		silver.util.cmdargs.Init.initAllStatics();
		silver.util.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.driver.Init.initAllStatics();
		silver.extension.doc.core.Init.initAllStatics();
		silver.extension.doc.driver.Init.initAllStatics();

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
		silver.util.cmdargs.Init.init();
		silver.util.Init.init();
		silver.definition.core.Init.init();
		silver.definition.env.Init.init();
		silver.driver.Init.init();
		silver.extension.doc.core.Init.init();
		silver.extension.doc.driver.Init.init();

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
		silver.util.cmdargs.Init.postInit();
		silver.util.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.driver.Init.postInit();
		silver.extension.doc.core.Init.postInit();
		silver.extension.doc.driver.Init.postInit();


		common.Decorator.applyDecorators(silver.util.cmdargs.NCmdArgs.decorators, PdocFlag.class);
		common.Decorator.applyDecorators(silver.util.cmdargs.NCmdArgs.decorators, PdocOutFlag.class);
		common.Decorator.applyDecorators(silver.driver.util.NDriverAction.decorators, PgenDoc.class);
	}

	private static void setupInheritedAttributes(){
		silver.util.cmdargs.NCmdArgs.occurs_syn[silver.extension.doc.driver.Init.silver_extension_doc_driver_docGeneration__ON__silver_util_cmdargs_CmdArgs] = "silver:extension:doc:driver:docGeneration";
		silver.util.cmdargs.NCmdArgs.occurs_syn[silver.extension.doc.driver.Init.silver_extension_doc_driver_docOutOption__ON__silver_util_cmdargs_CmdArgs] = "silver:extension:doc:driver:docOutOption";
		silver.driver.util.Pcompilation.occurs_local[silver.extension.doc.driver.Init.outputLoc__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:outputLoc";
		silver.extension.doc.driver.PgenDoc.occurs_local[silver.extension.doc.driver.Init.pr__ON__silver_extension_doc_driver_genDoc] = "silver:extension:doc:driver:genDoc:local:pr";
		silver.extension.doc.driver.PwriteAll.occurs_local[silver.extension.doc.driver.Init.now__ON__silver_extension_doc_driver_writeAll] = "silver:extension:doc:driver:writeAll:local:now";
		silver.extension.doc.driver.PwriteAll.occurs_local[silver.extension.doc.driver.Init.recurse__ON__silver_extension_doc_driver_writeAll] = "silver:extension:doc:driver:writeAll:local:recurse";
		silver.extension.doc.driver.PwriteSpec.occurs_local[silver.extension.doc.driver.Init.path__ON__silver_extension_doc_driver_writeSpec] = "silver:extension:doc:driver:writeSpec:local:path";
		//	local attribute mkiotest::IOVal<Boolean>;
		silver.extension.doc.driver.PwriteSpec.localInheritedAttributes[silver.extension.doc.driver.Init.mkiotest__ON__silver_extension_doc_driver_writeSpec] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.extension.doc.driver.PwriteSpec.occurs_local[silver.extension.doc.driver.Init.mkiotest__ON__silver_extension_doc_driver_writeSpec] = "silver:extension:doc:driver:writeSpec:local:mkiotest";
		//	local attribute mkio::IOVal<Boolean>;
		silver.extension.doc.driver.PwriteSpec.localInheritedAttributes[silver.extension.doc.driver.Init.mkio__ON__silver_extension_doc_driver_writeSpec] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.extension.doc.driver.PwriteSpec.occurs_local[silver.extension.doc.driver.Init.mkio__ON__silver_extension_doc_driver_writeSpec] = "silver:extension:doc:driver:writeSpec:local:mkio";
		silver.extension.doc.driver.PwriteSpec.occurs_local[silver.extension.doc.driver.Init.pr__ON__silver_extension_doc_driver_writeSpec] = "silver:extension:doc:driver:writeSpec:local:pr";
		silver.extension.doc.driver.PwriteSpec.occurs_local[silver.extension.doc.driver.Init.rm__ON__silver_extension_doc_driver_writeSpec] = "silver:extension:doc:driver:writeSpec:local:rm";
		silver.extension.doc.driver.PwriteSpec.occurs_local[silver.extension.doc.driver.Init.wr__ON__silver_extension_doc_driver_writeSpec] = "silver:extension:doc:driver:writeSpec:local:wr";
		silver.extension.doc.driver.PdeleteStaleDocs.occurs_local[silver.extension.doc.driver.Init.docPath__ON__silver_extension_doc_driver_deleteStaleDocs] = "silver:extension:doc:driver:deleteStaleDocs:local:docPath";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION endCmdArgs top ::= _ 
		// top.docGeneration = false
		silver.util.cmdargs.PendCmdArgs.synthesizedAttributes[silver.extension.doc.driver.Init.silver_extension_doc_driver_docGeneration__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.docOutOption = []
		silver.util.cmdargs.PendCmdArgs.synthesizedAttributes[silver.extension.doc.driver.Init.silver_extension_doc_driver_docOutOption__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		silver.extension.doc.driver.PdocFlag.initProductionAttributeDefinitions();
		silver.extension.doc.driver.PdocOutFlag.initProductionAttributeDefinitions();
		//ASPECT FUNCTION parseArgs Either<String Decorated CmdArgs> ::= args::[String] 
		// flags <- [ pair("--doc", flag(docFlag)), pair("--doc-out", option(docOutFlag)) ]
		((common.CollectionAttribute)silver.driver.PparseArgs.localAttributes[silver.driver.Init.flags__ON__silver_driver_parseArgs]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("--doc")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NFlag)new silver.util.cmdargs.Pflag(silver.extension.doc.driver.PdocFlag.factory)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("--doc-out")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NFlag)new silver.util.cmdargs.Poption(silver.extension.doc.driver.PdocOutFlag.factory)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } });
		// flagdescs <- [ "\t--doc     : build the documentation", "\t--doc-out : output location for documentation" ]
		((common.CollectionAttribute)silver.driver.PparseArgs.localAttributes[silver.driver.Init.flagdescs__ON__silver_driver_parseArgs]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("\t--doc     : build the documentation")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("\t--doc-out : output location for documentation")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } });
		//ASPECT PRODUCTION compilation top ::= g::Grammars _ buildGrammar::String benv::BuildEnv 
		// outputLoc = if null(top.config.docOutOption) then benv.silverGen else head(top.config.docOutOption) ++ "/"
		silver.driver.util.Pcompilation.localAttributes[silver.extension.doc.driver.Init.outputLoc__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.inherited(silver.driver.util.Init.silver_definition_env_config__ON__silver_driver_util_Compilation)).synthesized(silver.extension.doc.driver.Init.silver_extension_doc_driver_docOutOption__ON__silver_util_cmdargs_CmdArgs)); } })) ? ((common.StringCatter)context.childDecorated(silver.driver.util.Pcompilation.i_benv).synthesized(silver.driver.util.Init.silver_driver_util_silverGen__ON__silver_driver_util_BuildEnv)) : new common.StringCatter((common.StringCatter)((common.StringCatter)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.inherited(silver.driver.util.Init.silver_definition_env_config__ON__silver_driver_util_Compilation)).synthesized(silver.extension.doc.driver.Init.silver_extension_doc_driver_docOutOption__ON__silver_util_cmdargs_CmdArgs)); } })), (common.StringCatter)(new common.StringCatter("/")))); } };
		// top.postOps <- if top.config.docGeneration then [ genDoc(top.config, grammarsToTranslate, outputLoc) ] else []
		if(silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] == null)
			silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] = new silver.driver.util.CApostOps(silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation);
		((common.CollectionAttribute)silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((common.DecoratedNode)context.inherited(silver.driver.util.Init.silver_definition_env_config__ON__silver_driver_util_Compilation)).synthesized(silver.extension.doc.driver.Init.silver_extension_doc_driver_docGeneration__ON__silver_util_cmdargs_CmdArgs)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.driver.util.NDriverAction)new silver.extension.doc.driver.PgenDoc(context.contextInheritedLazy(silver.driver.util.Init.silver_definition_env_config__ON__silver_driver_util_Compilation), context.localAsIsLazy(silver.driver.util.Init.grammarsToTranslate__ON__silver_driver_util_compilation), context.localAsIsLazy(silver.extension.doc.driver.Init.outputLoc__ON__silver_driver_util_compilation))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		silver.extension.doc.driver.PgenDoc.initProductionAttributeDefinitions();
		//FUNCTION writeAll IO ::= i::IO a::Decorated CmdArgs l::[Decorated RootSpec] outputLoc::String 
		// now = writeSpec(i, head(l), outputLoc)
		silver.extension.doc.driver.PwriteAll.localAttributes[silver.extension.doc.driver.Init.now__ON__silver_extension_doc_driver_writeAll] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.extension.doc.driver.PwriteSpec.invoke(context.childAsIsLazy(silver.extension.doc.driver.PwriteAll.i_i), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.doc.driver.PwriteAll.i_l))); } }, context.childAsIsLazy(silver.extension.doc.driver.PwriteAll.i_outputLoc))); } };
		// recurse = writeAll(now, a, tail(l), outputLoc)
		silver.extension.doc.driver.PwriteAll.localAttributes[silver.extension.doc.driver.Init.recurse__ON__silver_extension_doc_driver_writeAll] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.extension.doc.driver.PwriteAll.invoke(context.localAsIsLazy(silver.extension.doc.driver.Init.now__ON__silver_extension_doc_driver_writeAll), context.childAsIsLazy(silver.extension.doc.driver.PwriteAll.i_a), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.doc.driver.PwriteAll.i_l))); } }, context.childAsIsLazy(silver.extension.doc.driver.PwriteAll.i_outputLoc))); } };
		//FUNCTION writeSpec IO ::= i::IO r::Decorated RootSpec outputLoc::String 
		// path = outputLoc ++ "doc/" ++ grammarToPath(r.declaredName)
		silver.extension.doc.driver.PwriteSpec.localAttributes[silver.extension.doc.driver.Init.path__ON__silver_extension_doc_driver_writeSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.doc.driver.PwriteSpec.i_outputLoc)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("doc/")), (common.StringCatter)((common.StringCatter)silver.driver.util.PgrammarToPath.invoke(context.childAsIsSynthesizedLazy(silver.extension.doc.driver.PwriteSpec.i_r, silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec))))); } };
		// mkiotest = isDirectory(path, i)
		silver.extension.doc.driver.PwriteSpec.localAttributes[silver.extension.doc.driver.Init.mkiotest__ON__silver_extension_doc_driver_writeSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PisDirectory.invoke(context.localAsIsLazy(silver.extension.doc.driver.Init.path__ON__silver_extension_doc_driver_writeSpec), context.childAsIsLazy(silver.extension.doc.driver.PwriteSpec.i_i))); } };
		// mkio = if mkiotest.iovalue then mkiotest else mkdir(path, mkiotest.io)
		silver.extension.doc.driver.PwriteSpec.localAttributes[silver.extension.doc.driver.Init.mkio__ON__silver_extension_doc_driver_writeSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.extension.doc.driver.Init.mkiotest__ON__silver_extension_doc_driver_writeSpec).synthesized(core.Init.core_iovalue__ON__core_IOVal)) ? context.localDecorated(silver.extension.doc.driver.Init.mkiotest__ON__silver_extension_doc_driver_writeSpec).undecorate() : ((core.NIOVal)core.Pmkdir.invoke(context.localAsIsLazy(silver.extension.doc.driver.Init.path__ON__silver_extension_doc_driver_writeSpec), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.extension.doc.driver.Init.mkiotest__ON__silver_extension_doc_driver_writeSpec).synthesized(core.Init.core_io__ON__core_IOVal)); } }))); } };
		// pr = if mkio.iovalue then print("\t[" ++ r.declaredName ++ "]\n", mkio.io) else exit(-5, print("\nUnrecoverable Error: Unable to create directory: " ++ path ++ "\n\n", mkio.io))
		silver.extension.doc.driver.PwriteSpec.localAttributes[silver.extension.doc.driver.Init.pr__ON__silver_extension_doc_driver_writeSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.extension.doc.driver.Init.mkio__ON__silver_extension_doc_driver_writeSpec).synthesized(core.Init.core_iovalue__ON__core_IOVal)) ? ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\t[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.extension.doc.driver.PwriteSpec.i_r)).synthesized(silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec)), (common.StringCatter)(new common.StringCatter("]\n")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.extension.doc.driver.Init.mkio__ON__silver_extension_doc_driver_writeSpec).synthesized(core.Init.core_io__ON__core_IOVal)); } })) : ((Object)core.Pexit.invoke(Integer.valueOf((int)-5), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\nUnrecoverable Error: Unable to create directory: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.extension.doc.driver.Init.path__ON__silver_extension_doc_driver_writeSpec)), (common.StringCatter)(new common.StringCatter("\n\n")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.extension.doc.driver.Init.mkio__ON__silver_extension_doc_driver_writeSpec).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } }))); } };
		// rm = deleteStaleDocs(pr, outputLoc, r.declaredName)
		silver.extension.doc.driver.PwriteSpec.localAttributes[silver.extension.doc.driver.Init.rm__ON__silver_extension_doc_driver_writeSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.extension.doc.driver.PdeleteStaleDocs.invoke(context.localAsIsLazy(silver.extension.doc.driver.Init.pr__ON__silver_extension_doc_driver_writeSpec), context.childAsIsLazy(silver.extension.doc.driver.PwriteSpec.i_outputLoc), context.childAsIsSynthesizedLazy(silver.extension.doc.driver.PwriteSpec.i_r, silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec))); } };
		// wr = writeFiles(path, r.genFiles, rm)
		silver.extension.doc.driver.PwriteSpec.localAttributes[silver.extension.doc.driver.Init.wr__ON__silver_extension_doc_driver_writeSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.extension.doc.driver.PwriteFiles.invoke(context.localAsIsLazy(silver.extension.doc.driver.Init.path__ON__silver_extension_doc_driver_writeSpec), context.childAsIsSynthesizedLazy(silver.extension.doc.driver.PwriteSpec.i_r, silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec), context.localAsIsLazy(silver.extension.doc.driver.Init.rm__ON__silver_extension_doc_driver_writeSpec))); } };
		//FUNCTION writeFiles IO ::= path::String s::[Pair<String String>] i::IO 
		//FUNCTION deleteStaleDocs IO ::= iIn::IO outputLoc::String gram::String 
		// docPath = outputLoc ++ "doc/" ++ grammarToPath(gram)
		silver.extension.doc.driver.PdeleteStaleDocs.localAttributes[silver.extension.doc.driver.Init.docPath__ON__silver_extension_doc_driver_deleteStaleDocs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.doc.driver.PdeleteStaleDocs.i_outputLoc)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("doc/")), (common.StringCatter)((common.StringCatter)silver.driver.util.PgrammarToPath.invoke(context.childAsIsLazy(silver.extension.doc.driver.PdeleteStaleDocs.i_gram))))); } };
	}

	public static int count_local__ON__silver_extension_doc_driver_docFlag = 0;
	public static int count_local__ON__silver_extension_doc_driver_docOutFlag = 0;
	public static int count_local__ON__silver_extension_doc_driver_genDoc = 0;
	public static int count_local__ON__silver_extension_doc_driver_writeAll = 0;
	public static int count_local__ON__silver_extension_doc_driver_writeSpec = 0;
	public static int count_local__ON__silver_extension_doc_driver_writeFiles = 0;
	public static int count_local__ON__silver_extension_doc_driver_deleteStaleDocs = 0;
public static final int silver_extension_doc_driver_docGeneration__ON__silver_util_cmdargs_CmdArgs = silver.util.cmdargs.Init.count_syn__ON__CmdArgs++;
public static final int silver_extension_doc_driver_docOutOption__ON__silver_util_cmdargs_CmdArgs = silver.util.cmdargs.Init.count_syn__ON__CmdArgs++;
public static final int outputLoc__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int pr__ON__silver_extension_doc_driver_genDoc = silver.extension.doc.driver.Init.count_local__ON__silver_extension_doc_driver_genDoc++;
public static final int now__ON__silver_extension_doc_driver_writeAll = silver.extension.doc.driver.Init.count_local__ON__silver_extension_doc_driver_writeAll++;
public static final int recurse__ON__silver_extension_doc_driver_writeAll = silver.extension.doc.driver.Init.count_local__ON__silver_extension_doc_driver_writeAll++;
public static final int path__ON__silver_extension_doc_driver_writeSpec = silver.extension.doc.driver.Init.count_local__ON__silver_extension_doc_driver_writeSpec++;
public static final int mkiotest__ON__silver_extension_doc_driver_writeSpec = silver.extension.doc.driver.Init.count_local__ON__silver_extension_doc_driver_writeSpec++;
public static final int mkio__ON__silver_extension_doc_driver_writeSpec = silver.extension.doc.driver.Init.count_local__ON__silver_extension_doc_driver_writeSpec++;
public static final int pr__ON__silver_extension_doc_driver_writeSpec = silver.extension.doc.driver.Init.count_local__ON__silver_extension_doc_driver_writeSpec++;
public static final int rm__ON__silver_extension_doc_driver_writeSpec = silver.extension.doc.driver.Init.count_local__ON__silver_extension_doc_driver_writeSpec++;
public static final int wr__ON__silver_extension_doc_driver_writeSpec = silver.extension.doc.driver.Init.count_local__ON__silver_extension_doc_driver_writeSpec++;
public static final int docPath__ON__silver_extension_doc_driver_deleteStaleDocs = silver.extension.doc.driver.Init.count_local__ON__silver_extension_doc_driver_deleteStaleDocs++;
}
