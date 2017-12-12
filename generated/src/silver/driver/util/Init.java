package silver.driver.util;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		silver.definition.flow.env_parser.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.util.raw.graph.Init.initAllStatics();
		silver.util.raw.treemap.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		silver.definition.flow.ast.Init.initAllStatics();
		silver.definition.flow.driver.Init.initAllStatics();
		silver.util.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.env.env_parser.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		silver.definition.flow.env_parser.Init.init();
		core.monad.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.util.raw.graph.Init.init();
		silver.util.raw.treemap.Init.init();
		silver.definition.flow.env.Init.init();
		silver.definition.flow.ast.Init.init();
		silver.definition.flow.driver.Init.init();
		silver.util.Init.init();
		silver.definition.env.Init.init();
		silver.definition.env.env_parser.Init.init();
		silver.definition.core.Init.init();
		silver.driver.util.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		silver.definition.flow.env_parser.Init.postInit();
		core.monad.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.util.raw.graph.Init.postInit();
		silver.util.raw.treemap.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.definition.flow.ast.Init.postInit();
		silver.definition.flow.driver.Init.postInit();
		silver.util.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.env.env_parser.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.driver.util.Init.postInit();


		common.Decorator.applyDecorators(silver.driver.util.NRootSpec.decorators, PgrammarRootSpec.class);
		common.Decorator.applyDecorators(silver.driver.util.NRootSpec.decorators, PinterfaceRootSpec.class);
		common.Decorator.applyDecorators(silver.driver.util.NRootSpec.decorators, PerrorRootSpec.class);
		common.Decorator.applyDecorators(silver.driver.util.NBuildEnv.decorators, PbuildEnv.class);
		common.Decorator.applyDecorators(silver.driver.util.NCompilation.decorators, Pcompilation.class);
		common.Decorator.applyDecorators(silver.driver.util.NGrammars.decorators, PconsGrammars.class);
		common.Decorator.applyDecorators(silver.driver.util.NGrammars.decorators, PnilGrammars.class);
		common.Decorator.applyDecorators(silver.driver.util.NDriverAction.decorators, PwrapUnit.class);
	}

	private static void setupInheritedAttributes(){
		silver.driver.util.NRootSpec.occurs_inh[silver.driver.util.Init.silver_definition_env_config__ON__silver_driver_util_RootSpec] = "silver:definition:env:config";
		silver.driver.util.NRootSpec.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.driver.util.NRootSpec.occurs_inh[silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_RootSpec] = "silver:definition:env:compiledGrammars";
		silver.driver.util.NRootSpec.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.driver.util.NRootSpec.occurs_inh[silver.driver.util.Init.silver_definition_env_productionFlowGraphs__ON__silver_driver_util_RootSpec] = "silver:definition:env:productionFlowGraphs";
		silver.driver.util.NRootSpec.decorators.add(silver.definition.env.DproductionFlowGraphs.singleton);
		silver.driver.util.NRootSpec.occurs_inh[silver.driver.util.Init.silver_definition_env_grammarFlowTypes__ON__silver_driver_util_RootSpec] = "silver:definition:env:grammarFlowTypes";
		silver.driver.util.NRootSpec.decorators.add(silver.definition.env.DgrammarFlowTypes.singleton);
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec] = "silver:definition:env:declaredName";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_definition_env_moduleNames__ON__silver_driver_util_RootSpec] = "silver:definition:env:moduleNames";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_definition_env_exportedGrammars__ON__silver_driver_util_RootSpec] = "silver:definition:env:exportedGrammars";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_definition_env_optionalGrammars__ON__silver_driver_util_RootSpec] = "silver:definition:env:optionalGrammars";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_definition_env_condBuild__ON__silver_driver_util_RootSpec] = "silver:definition:env:condBuild";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_definition_env_allGrammarDependencies__ON__silver_driver_util_RootSpec] = "silver:definition:env:allGrammarDependencies";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_definition_env_defs__ON__silver_driver_util_RootSpec] = "silver:definition:env:defs";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_definition_core_grammarErrors__ON__silver_driver_util_RootSpec] = "silver:definition:core:grammarErrors";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_definition_env_grammarSource__ON__silver_driver_util_RootSpec] = "silver:definition:env:grammarSource";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_definition_env_grammarTime__ON__silver_driver_util_RootSpec] = "silver:definition:env:grammarTime";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_definition_env_interfaceTime__ON__silver_driver_util_RootSpec] = "silver:definition:env:interfaceTime";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_RootSpec] = "silver:driver:util:recheckGrammars";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_driver_util_translateGrammars__ON__silver_driver_util_RootSpec] = "silver:driver:util:translateGrammars";
		silver.driver.util.NRootSpec.occurs_syn[silver.driver.util.Init.silver_driver_util_parsingErrors__ON__silver_driver_util_RootSpec] = "silver:driver:util:parsingErrors";
		silver.driver.util.PgrammarRootSpec.occurs_local[silver.driver.util.Init.actualDependencies__ON__silver_driver_util_grammarRootSpec] = "silver:driver:util:grammarRootSpec:local:actualDependencies";
		silver.driver.util.PgrammarRootSpec.occurs_local[silver.driver.util.Init.depsPlusOptions__ON__silver_driver_util_grammarRootSpec] = "silver:driver:util:grammarRootSpec:local:depsPlusOptions";
		silver.driver.util.PinterfaceRootSpec.occurs_local[silver.driver.util.Init.ood__ON__silver_driver_util_interfaceRootSpec] = "silver:driver:util:interfaceRootSpec:local:ood";
		silver.driver.util.PunparseRootSpec.occurs_local[silver.driver.util.Init.unparses__ON__silver_driver_util_unparseRootSpec] = "silver:driver:util:unparseRootSpec:local:unparses";
		silver.driver.util.PunparseRootSpec.localAttributes[silver.driver.util.Init.unparses__ON__silver_driver_util_unparseRootSpec] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		silver.driver.util.PisOutOfDate.occurs_local[silver.driver.util.Init.n__ON__silver_driver_util_isOutOfDate] = "silver:driver:util:isOutOfDate:local:n";
		silver.driver.util.NBuildEnv.occurs_syn[silver.driver.util.Init.silver_driver_util_silverHome__ON__silver_driver_util_BuildEnv] = "silver:driver:util:silverHome";
		silver.driver.util.NBuildEnv.occurs_syn[silver.driver.util.Init.silver_driver_util_silverGen__ON__silver_driver_util_BuildEnv] = "silver:driver:util:silverGen";
		silver.driver.util.NBuildEnv.occurs_syn[silver.driver.util.Init.silver_driver_util_grammarPath__ON__silver_driver_util_BuildEnv] = "silver:driver:util:grammarPath";
		silver.driver.util.NBuildEnv.occurs_syn[silver.driver.util.Init.silver_driver_util_defaultSilverGen__ON__silver_driver_util_BuildEnv] = "silver:driver:util:defaultSilverGen";
		silver.driver.util.NBuildEnv.occurs_syn[silver.driver.util.Init.silver_driver_util_defaultGrammarPath__ON__silver_driver_util_BuildEnv] = "silver:driver:util:defaultGrammarPath";
		silver.driver.util.PfromArgsAndEnv.occurs_local[silver.driver.util.Init.silverHome__ON__silver_driver_util_fromArgsAndEnv] = "silver:driver:util:fromArgsAndEnv:local:silverHome";
		silver.driver.util.PfromArgsAndEnv.occurs_local[silver.driver.util.Init.silverGen__ON__silver_driver_util_fromArgsAndEnv] = "silver:driver:util:fromArgsAndEnv:local:silverGen";
		silver.driver.util.PfromArgsAndEnv.occurs_local[silver.driver.util.Init.grammarPath__ON__silver_driver_util_fromArgsAndEnv] = "silver:driver:util:fromArgsAndEnv:local:grammarPath";
		//	local attribute benv::BuildEnv;
		silver.driver.util.PfromArgsAndEnv.localInheritedAttributes[silver.driver.util.Init.benv__ON__silver_driver_util_fromArgsAndEnv] = new common.Lazy[silver.driver.util.NBuildEnv.num_inh_attrs];
		silver.driver.util.PfromArgsAndEnv.occurs_local[silver.driver.util.Init.benv__ON__silver_driver_util_fromArgsAndEnv] = "silver:driver:util:fromArgsAndEnv:local:benv";
		silver.driver.util.PexpandExports.occurs_local[silver.driver.util.Init.g__ON__silver_driver_util_expandExports] = "silver:driver:util:expandExports:local:g";
		silver.driver.util.PexpandAllDeps.occurs_local[silver.driver.util.Init.g__ON__silver_driver_util_expandAllDeps] = "silver:driver:util:expandAllDeps:local:g";
		silver.driver.util.PexpandCondBuilds.occurs_local[silver.driver.util.Init.newtriggers__ON__silver_driver_util_expandCondBuilds] = "silver:driver:util:expandCondBuilds:local:newtriggers";
		silver.driver.util.PexpandCondBuilds.occurs_local[silver.driver.util.Init.newset__ON__silver_driver_util_expandCondBuilds] = "silver:driver:util:expandCondBuilds:local:newset";
		silver.driver.util.PexpandCondBuilds.occurs_local[silver.driver.util.Init.triggered__ON__silver_driver_util_expandCondBuilds] = "silver:driver:util:expandCondBuilds:local:triggered";
		silver.driver.util.PexpandOptionalsIter.occurs_local[silver.driver.util.Init.g__ON__silver_driver_util_expandOptionalsIter] = "silver:driver:util:expandOptionalsIter:local:g";
		silver.driver.util.PcomputeOptionalDeps.occurs_local[silver.driver.util.Init.initPlusExported__ON__silver_driver_util_computeOptionalDeps] = "silver:driver:util:computeOptionalDeps:local:initPlusExported";
		silver.driver.util.PcomputeOptionalDeps.occurs_local[silver.driver.util.Init.closeOptions__ON__silver_driver_util_computeOptionalDeps] = "silver:driver:util:computeOptionalDeps:local:closeOptions";
		silver.driver.util.PcompleteDependencyClosure.occurs_local[silver.driver.util.Init.n__ON__silver_driver_util_completeDependencyClosure] = "silver:driver:util:completeDependencyClosure:local:n";
		silver.driver.util.PinductivelyExpand.occurs_local[silver.driver.util.Init.result__ON__silver_driver_util_inductivelyExpand] = "silver:driver:util:inductivelyExpand:local:result";
		silver.driver.util.NCompilation.occurs_inh[silver.driver.util.Init.silver_definition_env_config__ON__silver_driver_util_Compilation] = "silver:definition:env:config";
		silver.driver.util.NCompilation.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.driver.util.NCompilation.occurs_syn[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] = "silver:driver:util:postOps";
		silver.driver.util.NCompilation.occurs_syn[silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Compilation] = "silver:driver:util:grammarList";
		silver.driver.util.NCompilation.occurs_syn[silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_Compilation] = "silver:driver:util:recheckGrammars";
		silver.driver.util.NCompilation.occurs_syn[silver.driver.util.Init.silver_driver_util_allGrammars__ON__silver_driver_util_Compilation] = "silver:driver:util:allGrammars";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.grammarsDependedUpon__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:grammarsDependedUpon";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.grammarsRelevant__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:grammarsRelevant";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.grammarsToTranslate__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:grammarsToTranslate";
		silver.driver.util.NGrammars.occurs_inh[silver.driver.util.Init.silver_definition_env_config__ON__silver_driver_util_Grammars] = "silver:definition:env:config";
		silver.driver.util.NGrammars.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.driver.util.NGrammars.occurs_inh[silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_Grammars] = "silver:definition:env:compiledGrammars";
		silver.driver.util.NGrammars.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.driver.util.NGrammars.occurs_inh[silver.driver.util.Init.silver_definition_env_productionFlowGraphs__ON__silver_driver_util_Grammars] = "silver:definition:env:productionFlowGraphs";
		silver.driver.util.NGrammars.decorators.add(silver.definition.env.DproductionFlowGraphs.singleton);
		silver.driver.util.NGrammars.occurs_inh[silver.driver.util.Init.silver_definition_env_grammarFlowTypes__ON__silver_driver_util_Grammars] = "silver:definition:env:grammarFlowTypes";
		silver.driver.util.NGrammars.decorators.add(silver.definition.env.DgrammarFlowTypes.singleton);
		silver.driver.util.NGrammars.occurs_syn[silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars] = "silver:driver:util:grammarList";
		silver.driver.util.NGrammars.occurs_syn[silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_Grammars] = "silver:driver:util:recheckGrammars";
		silver.driver.util.NGrammars.occurs_syn[silver.driver.util.Init.silver_driver_util_translateGrammars__ON__silver_driver_util_Grammars] = "silver:driver:util:translateGrammars";
		silver.driver.util.NDriverAction.occurs_inh[silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction] = "silver:driver:util:ioIn";
		silver.driver.util.NDriverAction.occurs_syn[silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction] = "core:io";
		silver.driver.util.NDriverAction.occurs_syn[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = "silver:driver:util:code";
		silver.driver.util.NDriverAction.occurs_syn[silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction] = "silver:driver:util:order";
		//	local attribute call::IOVal<Integer>;
		silver.driver.util.PwrapUnit.localInheritedAttributes[silver.driver.util.Init.call__ON__silver_driver_util_wrapUnit] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.driver.util.PwrapUnit.occurs_local[silver.driver.util.Init.call__ON__silver_driver_util_wrapUnit] = "silver:driver:util:wrapUnit:local:call";
		//	local attribute now::DriverAction;
		silver.driver.util.PrunAll.localInheritedAttributes[silver.driver.util.Init.now__ON__silver_driver_util_runAll] = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];
		silver.driver.util.PrunAll.occurs_local[silver.driver.util.Init.now__ON__silver_driver_util_runAll] = "silver:driver:util:runAll:local:now";
		//	local attribute exists::IOVal<Maybe<String>>;
		silver.driver.util.PfindGrammarLocation.localInheritedAttributes[silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarLocation] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.driver.util.PfindGrammarLocation.occurs_local[silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarLocation] = "silver:driver:util:findGrammarLocation:local:exists";
		silver.driver.util.PfindGrammarInLocation.occurs_local[silver.driver.util.Init.idx__ON__silver_driver_util_findGrammarInLocation] = "silver:driver:util:findGrammarInLocation:local:idx";
		silver.driver.util.PfindGrammarInLocation.occurs_local[silver.driver.util.Init.nextGram__ON__silver_driver_util_findGrammarInLocation] = "silver:driver:util:findGrammarInLocation:local:nextGram";
		//	local attribute exists::IOVal<Boolean>;
		silver.driver.util.PfindGrammarInLocation.localInheritedAttributes[silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarInLocation] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.driver.util.PfindGrammarInLocation.occurs_local[silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarInLocation] = "silver:driver:util:findGrammarInLocation:local:exists";
		//	local attribute allFlowDefs::FlowDefs;
		silver.driver.util.Pcompilation.localInheritedAttributes[silver.driver.util.Init.allFlowDefs__ON__silver_driver_util_compilation] = new common.Lazy[silver.definition.flow.ast.NFlowDefs.num_inh_attrs];
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.allFlowDefs__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:allFlowDefs";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.allFlowEnv__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:allFlowEnv";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.prodTree__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:prodTree";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.allRealDefs__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:allRealDefs";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.allRealEnv__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:allRealEnv";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.allProds__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:allProds";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.allNts__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:allNts";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.prodGraph__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:prodGraph";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.initialFT__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:initialFT";
		//	local attribute flowTypes1::Pair<[ProductionGraph] EnvTree<FlowType>>;
		silver.driver.util.Pcompilation.localInheritedAttributes[silver.driver.util.Init.flowTypes1__ON__silver_driver_util_compilation] = new common.Lazy[core.NPair.num_inh_attrs];
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.flowTypes1__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:flowTypes1";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.flowTypes__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:flowTypes";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.finalGraphs__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:finalGraphs";
		silver.driver.util.Pcompilation.occurs_local[silver.driver.util.Init.finalGraphEnv__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:finalGraphEnv";
	}

	private static void initProductionAttributeDefinitions(){
		silver.driver.util.PgrammarRootSpec.initProductionAttributeDefinitions();
		silver.driver.util.PinterfaceRootSpec.initProductionAttributeDefinitions();
		silver.driver.util.PerrorRootSpec.initProductionAttributeDefinitions();
		//FUNCTION parseErrorToMessage Pair<String [Message]> ::= grammarSource::String e::ParseError 
		//FUNCTION unparseRootSpec String ::= r::Decorated RootSpec 
		// unparses := [ "declaredName " ++ quoteString(r.declaredName), "grammarTime " ++ toString(r.grammarTime), "grammarSource \"" ++ escapeString(r.grammarSource) ++ "\"", "moduleNames " ++ unparseStrings(r.moduleNames), "allDeps " ++ unparseStrings(r.allGrammarDependencies), "exportedGrammars " ++ unparseStrings(r.exportedGrammars), "optionalGrammars " ++ unparseStrings(r.optionalGrammars), "condBuild " ++ unparseStrings(concat(r.condBuild)), "defs " ++ unparseDefs(r.defs, []) ]
		((common.CollectionAttribute)silver.driver.util.PunparseRootSpec.localAttributes[silver.driver.util.Init.unparses__ON__silver_driver_util_unparseRootSpec]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("declaredName ")), (common.StringCatter)((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsSynthesizedLazy(silver.driver.util.PunparseRootSpec.i_r, silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("grammarTime ")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((common.DecoratedNode)context.childAsIs(silver.driver.util.PunparseRootSpec.i_r)).synthesized(silver.driver.util.Init.silver_definition_env_grammarTime__ON__silver_driver_util_RootSpec))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("grammarSource \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PescapeString.invoke(context.childAsIsSynthesizedLazy(silver.driver.util.PunparseRootSpec.i_r, silver.driver.util.Init.silver_definition_env_grammarSource__ON__silver_driver_util_RootSpec))), (common.StringCatter)(new common.StringCatter("\"")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("moduleNames ")), (common.StringCatter)((common.StringCatter)silver.definition.env.PunparseStrings.invoke(context.childAsIsSynthesizedLazy(silver.driver.util.PunparseRootSpec.i_r, silver.driver.util.Init.silver_definition_env_moduleNames__ON__silver_driver_util_RootSpec)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("allDeps ")), (common.StringCatter)((common.StringCatter)silver.definition.env.PunparseStrings.invoke(context.childAsIsSynthesizedLazy(silver.driver.util.PunparseRootSpec.i_r, silver.driver.util.Init.silver_definition_env_allGrammarDependencies__ON__silver_driver_util_RootSpec)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("exportedGrammars ")), (common.StringCatter)((common.StringCatter)silver.definition.env.PunparseStrings.invoke(context.childAsIsSynthesizedLazy(silver.driver.util.PunparseRootSpec.i_r, silver.driver.util.Init.silver_definition_env_exportedGrammars__ON__silver_driver_util_RootSpec)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("optionalGrammars ")), (common.StringCatter)((common.StringCatter)silver.definition.env.PunparseStrings.invoke(context.childAsIsSynthesizedLazy(silver.driver.util.PunparseRootSpec.i_r, silver.driver.util.Init.silver_definition_env_optionalGrammars__ON__silver_driver_util_RootSpec)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("condBuild ")), (common.StringCatter)((common.StringCatter)silver.definition.env.PunparseStrings.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pconcat.invoke(context.childAsIsSynthesizedLazy(silver.driver.util.PunparseRootSpec.i_r, silver.driver.util.Init.silver_definition_env_condBuild__ON__silver_driver_util_RootSpec))); } }))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("defs ")), (common.StringCatter)((common.StringCatter)silver.definition.env.PunparseDefs.invoke(context.childAsIsSynthesizedLazy(silver.driver.util.PunparseRootSpec.i_r, silver.driver.util.Init.silver_definition_env_defs__ON__silver_driver_util_RootSpec), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } })); } })); } })); } });
		//FUNCTION mentionedGrammars [String] ::= r::Decorated RootSpec 
		//FUNCTION gatherFlowEnv [FlowDef] ::= deps::[String] e::EnvTree<Decorated RootSpec> 
		//FUNCTION isOutOfDate Boolean ::= mine::Integer l::[String] e::EnvTree<Decorated RootSpec> 
		// n = searchEnvTree(head(l), e)
		silver.driver.util.PisOutOfDate.localAttributes[silver.driver.util.Init.n__ON__silver_driver_util_isOutOfDate] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PisOutOfDate.i_l))); } }, context.childAsIsLazy(silver.driver.util.PisOutOfDate.i_e))); } };
		silver.driver.util.PbuildEnv.initProductionAttributeDefinitions();
		//FUNCTION fromArgsAndEnv BuildEnv ::= SILVER_HOME::String SILVER_GEN::String GRAMMAR_PATH::[String] homeArg::[String] genArg::[String] pathArg::[String] 
		// silverHome = endWithSlash(head(homeArg ++ [ SILVER_HOME ]))
		silver.driver.util.PfromArgsAndEnv.localAttributes[silver.driver.util.Init.silverHome__ON__silver_driver_util_fromArgsAndEnv] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.driver.util.PendWithSlash.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(silver.driver.util.PfromArgsAndEnv.i_homeArg), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.driver.util.PfromArgsAndEnv.i_SILVER_HOME), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
		// silverGen = endWithSlash(head(genArg ++ (if SILVER_GEN == "" then [] else [ SILVER_GEN ]) ++ [ benv.defaultSilverGen ]))
		silver.driver.util.PfromArgsAndEnv.localAttributes[silver.driver.util.Init.silverGen__ON__silver_driver_util_fromArgsAndEnv] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.driver.util.PendWithSlash.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(silver.driver.util.PfromArgsAndEnv.i_genArg), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((common.StringCatter)context.childAsIs(silver.driver.util.PfromArgsAndEnv.i_SILVER_GEN)).equals((new common.StringCatter(""))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.driver.util.PfromArgsAndEnv.i_SILVER_GEN), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.driver.util.Init.benv__ON__silver_driver_util_fromArgsAndEnv).synthesized(silver.driver.util.Init.silver_driver_util_defaultSilverGen__ON__silver_driver_util_BuildEnv)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } };
		// grammarPath = map(endWithSlash, pathArg ++ GRAMMAR_PATH ++ [ benv.defaultGrammarPath ] ++ [ "." ])
		silver.driver.util.PfromArgsAndEnv.localAttributes[silver.driver.util.Init.grammarPath__ON__silver_driver_util_fromArgsAndEnv] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.driver.util.PendWithSlash.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(silver.driver.util.PfromArgsAndEnv.i_pathArg), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(silver.driver.util.PfromArgsAndEnv.i_GRAMMAR_PATH), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.driver.util.Init.benv__ON__silver_driver_util_fromArgsAndEnv).synthesized(silver.driver.util.Init.silver_driver_util_defaultGrammarPath__ON__silver_driver_util_BuildEnv)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter(".")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } };
		// benv = buildEnv(silverHome, silverGen, grammarPath)
		silver.driver.util.PfromArgsAndEnv.localAttributes[silver.driver.util.Init.benv__ON__silver_driver_util_fromArgsAndEnv] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.driver.util.NBuildEnv)new silver.driver.util.PbuildEnv(context.localAsIsLazy(silver.driver.util.Init.silverHome__ON__silver_driver_util_fromArgsAndEnv), context.localAsIsLazy(silver.driver.util.Init.silverGen__ON__silver_driver_util_fromArgsAndEnv), context.localAsIsLazy(silver.driver.util.Init.grammarPath__ON__silver_driver_util_fromArgsAndEnv))); } };
		//FUNCTION endWithSlash String ::= s::String 
		//FUNCTION isExportedBy Boolean ::= target::String sources::[String] e::EnvTree<Decorated RootSpec> 
		//FUNCTION isStrictlyExportedBy Boolean ::= target::String sources::[String] e::EnvTree<Decorated RootSpec> 
		//FUNCTION expandExports [String] ::= need::[String] seen::[String] e::EnvTree<Decorated RootSpec> 
		// g = searchEnvTree(head(need), e)
		silver.driver.util.PexpandExports.localAttributes[silver.driver.util.Init.g__ON__silver_driver_util_expandExports] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PexpandExports.i_need))); } }, context.childAsIsLazy(silver.driver.util.PexpandExports.i_e))); } };
		//FUNCTION expandAllDeps [String] ::= need::[String] seen::[String] e::EnvTree<Decorated RootSpec> 
		// g = searchEnvTree(head(need), e)
		silver.driver.util.PexpandAllDeps.localAttributes[silver.driver.util.Init.g__ON__silver_driver_util_expandAllDeps] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PexpandAllDeps.i_need))); } }, context.childAsIsLazy(silver.driver.util.PexpandAllDeps.i_e))); } };
		//FUNCTION computeDependencies [String] ::= need::[String] e::EnvTree<Decorated RootSpec> 
		//FUNCTION expandCondBuilds [String] ::= need::[String] seen::[String] triggers::[[String]] e::EnvTree<Decorated RootSpec> 
		// newtriggers = flatMap(skipNulls((.condBuild), _), map(searchEnvTree(_, e), need))
		silver.driver.util.PexpandCondBuilds.localAttributes[silver.driver.util.Init.newtriggers__ON__silver_driver_util_expandCondBuilds] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.driver.util.PskipNulls.factory.invokePartial(new int[]{0}, new Object[]{new common.AttributeSection(silver.driver.util.Init.silver_definition_env_condBuild__ON__silver_driver_util_RootSpec)}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PsearchEnvTree.factory.invokePartial(new int[]{1}, new Object[]{context.childAsIsLazy(silver.driver.util.PexpandCondBuilds.i_e)}); } }, context.childAsIsLazy(silver.driver.util.PexpandCondBuilds.i_need))); } })); } };
		// newset = need ++ seen
		silver.driver.util.PexpandCondBuilds.localAttributes[silver.driver.util.Init.newset__ON__silver_driver_util_expandCondBuilds] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(silver.driver.util.PexpandCondBuilds.i_need), context.childAsIsLazy(silver.driver.util.PexpandCondBuilds.i_seen))); } };
		// triggered = noninductiveExpansion(newset, newtriggers)
		silver.driver.util.PexpandCondBuilds.localAttributes[silver.driver.util.Init.triggered__ON__silver_driver_util_expandCondBuilds] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PnoninductiveExpansion.invoke(context.localAsIsLazy(silver.driver.util.Init.newset__ON__silver_driver_util_expandCondBuilds), context.localAsIsLazy(silver.driver.util.Init.newtriggers__ON__silver_driver_util_expandCondBuilds))); } };
		//FUNCTION expandOptionalsIter [String] ::= need::[String] seen::[String] e::EnvTree<Decorated RootSpec> 
		// g = searchEnvTree(head(need), e)
		silver.driver.util.PexpandOptionalsIter.localAttributes[silver.driver.util.Init.g__ON__silver_driver_util_expandOptionalsIter] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_need))); } }, context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_e))); } };
		//FUNCTION computeOptionalDeps [String] ::= init::[String] e::EnvTree<Decorated RootSpec> 
		// initPlusExported = computeDependencies(init, e)
		silver.driver.util.PcomputeOptionalDeps.localAttributes[silver.driver.util.Init.initPlusExported__ON__silver_driver_util_computeOptionalDeps] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PcomputeDependencies.invoke(context.childAsIsLazy(silver.driver.util.PcomputeOptionalDeps.i_init), context.childAsIsLazy(silver.driver.util.PcomputeOptionalDeps.i_e))); } };
		// closeOptions = expandOptionalsIter(initPlusExported, [], e)
		silver.driver.util.PcomputeOptionalDeps.localAttributes[silver.driver.util.Init.closeOptions__ON__silver_driver_util_computeOptionalDeps] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PexpandOptionalsIter.invoke(context.localAsIsLazy(silver.driver.util.Init.initPlusExported__ON__silver_driver_util_computeOptionalDeps), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, context.childAsIsLazy(silver.driver.util.PcomputeOptionalDeps.i_e))); } };
		//FUNCTION completeDependencyClosure [String] ::= init::[String] e::EnvTree<Decorated RootSpec> 
		// n = rem(makeSet(flatMap(skipNulls((.moduleNames), _), map(searchEnvTree(_, e), init))), init)
		silver.driver.util.PcompleteDependencyClosure.localAttributes[silver.driver.util.Init.n__ON__silver_driver_util_completeDependencyClosure] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.Prem.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.driver.util.PskipNulls.factory.invokePartial(new int[]{0}, new Object[]{new common.AttributeSection(silver.driver.util.Init.silver_definition_env_moduleNames__ON__silver_driver_util_RootSpec)}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PsearchEnvTree.factory.invokePartial(new int[]{1}, new Object[]{context.childAsIsLazy(silver.driver.util.PcompleteDependencyClosure.i_e)}); } }, context.childAsIsLazy(silver.driver.util.PcompleteDependencyClosure.i_init))); } })); } })); } }, context.childAsIsLazy(silver.driver.util.PcompleteDependencyClosure.i_init))); } };
		//FUNCTION inductivelyExpand [String] ::= initial::[String] rules::[[String]] 
		// result = noninductiveExpansion(initial, rules)
		silver.driver.util.PinductivelyExpand.localAttributes[silver.driver.util.Init.result__ON__silver_driver_util_inductivelyExpand] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PnoninductiveExpansion.invoke(context.childAsIsLazy(silver.driver.util.PinductivelyExpand.i_initial), context.childAsIsLazy(silver.driver.util.PinductivelyExpand.i_rules))); } };
		//FUNCTION noninductiveExpansion [String] ::= initial::[String] rules::[[String]] 
		//FUNCTION skipNulls [b] ::= f::([b] ::= a) l::[a] 
		silver.driver.util.Pcompilation.initProductionAttributeDefinitions();
		silver.driver.util.PconsGrammars.initProductionAttributeDefinitions();
		silver.driver.util.PnilGrammars.initProductionAttributeDefinitions();
		//FUNCTION grammarPairing Pair<String Decorated RootSpec> ::= r::Decorated RootSpec 
		//FUNCTION keepGrammars [Decorated RootSpec] ::= keep::[String] d::[Decorated RootSpec] 
		silver.driver.util.PwrapUnit.initProductionAttributeDefinitions();
		//FUNCTION runAll IOVal<Integer> ::= l::[DriverAction] i::IO 
		// now = head(l)
		silver.driver.util.PrunAll.localAttributes[silver.driver.util.Init.now__ON__silver_driver_util_runAll] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.driver.util.NDriverAction)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PrunAll.i_l))); } };
		// now.ioIn = i
		silver.driver.util.PrunAll.localInheritedAttributes[silver.driver.util.Init.now__ON__silver_driver_util_runAll][silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(silver.driver.util.PrunAll.i_i)); } };
		//FUNCTION sortUnits [DriverAction] ::= c1::[DriverAction] 
		//FUNCTION unitLTE Boolean ::= l::DriverAction r::DriverAction 
		//FUNCTION grammarToPath String ::= g::String 
		//FUNCTION findGrammarLocation IOVal<Maybe<String>> ::= path::String searchPaths::[String] iIn::IO 
		// exists = findGrammarInLocation(path, head(searchPaths), iIn)
		silver.driver.util.PfindGrammarLocation.localAttributes[silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarLocation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)silver.driver.util.PfindGrammarInLocation.invoke(context.childAsIsLazy(silver.driver.util.PfindGrammarLocation.i_path), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PfindGrammarLocation.i_searchPaths))); } }, context.childAsIsLazy(silver.driver.util.PfindGrammarLocation.i_iIn))); } };
		//FUNCTION findGrammarInLocation IOVal<Maybe<String>> ::= gram::String inPath::String iIn::IO 
		// idx = indexOf("/", gram)
		silver.driver.util.PfindGrammarInLocation.localAttributes[silver.driver.util.Init.idx__ON__silver_driver_util_findGrammarInLocation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PindexOf.invoke((new common.StringCatter("/")), context.childAsIsLazy(silver.driver.util.PfindGrammarInLocation.i_gram))); } };
		// nextGram = substring(0, idx, gram) ++ "." ++ substring(idx + 1, length(gram), gram)
		silver.driver.util.PfindGrammarInLocation.localAttributes[silver.driver.util.Init.nextGram__ON__silver_driver_util_findGrammarInLocation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), context.localAsIsLazy(silver.driver.util.Init.idx__ON__silver_driver_util_findGrammarInLocation), context.childAsIsLazy(silver.driver.util.PfindGrammarInLocation.i_gram))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)((common.StringCatter)core.Psubstring.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.localAsIs(silver.driver.util.Init.idx__ON__silver_driver_util_findGrammarInLocation)) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(silver.driver.util.PfindGrammarInLocation.i_gram))).length()); } }, context.childAsIsLazy(silver.driver.util.PfindGrammarInLocation.i_gram))))); } };
		// exists = isDirectory(inPath ++ gram, iIn)
		silver.driver.util.PfindGrammarInLocation.localAttributes[silver.driver.util.Init.exists__ON__silver_driver_util_findGrammarInLocation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PisDirectory.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.driver.util.PfindGrammarInLocation.i_inPath)), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.driver.util.PfindGrammarInLocation.i_gram))); } }, context.childAsIsLazy(silver.driver.util.PfindGrammarInLocation.i_iIn))); } };
		//ASPECT PRODUCTION compilation top ::= g::Grammars r::Grammars buildGrammar::String benv::BuildEnv 
		// allFlowDefs = foldr(consFlow, nilFlow(), flatMap((.flowDefs), g.grammarList))
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.allFlowDefs__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowDefs)core.Pfoldr.invoke(silver.definition.flow.ast.PconsFlow.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowDefs)new silver.definition.flow.ast.PnilFlow()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(new common.AttributeSection(silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_driver_util_RootSpec), context.childDecoratedSynthesizedLazy(silver.driver.util.Pcompilation.i_g, silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars))); } })); } };
		// allFlowEnv = fromFlowDefs(allFlowDefs)
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.allFlowEnv__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.flow.env.PfromFlowDefs.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.driver.util.Init.allFlowDefs__ON__silver_driver_util_compilation)))); } };
		// prodTree = directBuildTree(allFlowDefs.prodGraphContribs)
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.prodTree__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PdirectBuildTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.driver.util.Init.allFlowDefs__ON__silver_driver_util_compilation).synthesized(silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDefs)); } })); } };
		// allRealDefs = flatMap((.defs), g.grammarList)
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.allRealDefs__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(new common.AttributeSection(silver.driver.util.Init.silver_definition_env_defs__ON__silver_driver_util_RootSpec), context.childDecoratedSynthesizedLazy(silver.driver.util.Pcompilation.i_g, silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars))); } };
		// allRealEnv = toEnv(allRealDefs)
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.allRealEnv__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PtoEnv.invoke(context.localAsIsLazy(silver.driver.util.Init.allRealDefs__ON__silver_driver_util_compilation))); } };
		// allProds = foldr(consDefs, nilDefs(), allRealDefs).prodDclList
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.allProds__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.env.NDefs)core.Pfoldr.invoke(silver.definition.env.PconsDefs.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDefs)new silver.definition.env.PnilDefs()); } }, context.localAsIsLazy(silver.driver.util.Init.allRealDefs__ON__silver_driver_util_compilation))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_prodDclList__ON__silver_definition_env_Defs)); } };
		// allNts = nubBy(stringEq, map(getProdNt, allProds))
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.allNts__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PnubBy.invoke(core.PstringEq.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.driver.util.PgetProdNt.factory, context.localAsIsLazy(silver.driver.util.Init.allProds__ON__silver_driver_util_compilation))); } })); } };
		// prodGraph = computeAllProductionGraphs(allProds, prodTree, allFlowEnv, allRealEnv) ++ map(constructPhantomProductionGraph(_, allFlowEnv, allRealEnv), allNts)
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.prodGraph__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PcomputeAllProductionGraphs.invoke(context.localAsIsLazy(silver.driver.util.Init.allProds__ON__silver_driver_util_compilation), context.localAsIsLazy(silver.driver.util.Init.prodTree__ON__silver_driver_util_compilation), context.localAsIsLazy(silver.driver.util.Init.allFlowEnv__ON__silver_driver_util_compilation), context.localAsIsLazy(silver.driver.util.Init.allRealEnv__ON__silver_driver_util_compilation))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PconstructPhantomProductionGraph.factory.invokePartial(new int[]{1, 2}, new Object[]{context.localAsIsLazy(silver.driver.util.Init.allFlowEnv__ON__silver_driver_util_compilation), context.localAsIsLazy(silver.driver.util.Init.allRealEnv__ON__silver_driver_util_compilation)}); } }, context.localAsIsLazy(silver.driver.util.Init.allNts__ON__silver_driver_util_compilation))); } })); } };
		// initialFT = computeInitialFlowTypes(allFlowDefs)
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.initialFT__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.definition.flow.driver.PcomputeInitialFlowTypes.invoke(context.localDecoratedLazy(silver.driver.util.Init.allFlowDefs__ON__silver_driver_util_compilation))); } };
		// flowTypes1 = fullySolveFlowTypes(prodGraph, initialFT)
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.flowTypes1__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)silver.definition.flow.driver.PfullySolveFlowTypes.invoke(context.localAsIsLazy(silver.driver.util.Init.prodGraph__ON__silver_driver_util_compilation), context.localAsIsLazy(silver.driver.util.Init.initialFT__ON__silver_driver_util_compilation))); } };
		// flowTypes = flowTypes1.snd
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.flowTypes__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.util.Init.flowTypes1__ON__silver_driver_util_compilation).synthesized(core.Init.core_snd__ON__core_Pair)); } };
		// finalGraphs = flowTypes1.fst
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.finalGraphs__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.driver.util.Init.flowTypes1__ON__silver_driver_util_compilation).synthesized(core.Init.core_fst__ON__core_Pair)); } };
		// finalGraphEnv = directBuildTree(map(prodGraphToEnv, finalGraphs))
		silver.driver.util.Pcompilation.localAttributes[silver.driver.util.Init.finalGraphEnv__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PdirectBuildTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.flow.driver.PprodGraphToEnv.factory, context.localAsIsLazy(silver.driver.util.Init.finalGraphs__ON__silver_driver_util_compilation))); } })); } };
		// g.productionFlowGraphs = finalGraphEnv
		silver.driver.util.Pcompilation.childInheritedAttributes[silver.driver.util.Pcompilation.i_g][silver.driver.util.Init.silver_definition_env_productionFlowGraphs__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.localAsIs(silver.driver.util.Init.finalGraphEnv__ON__silver_driver_util_compilation)); } };
		// g.grammarFlowTypes = flowTypes
		silver.driver.util.Pcompilation.childInheritedAttributes[silver.driver.util.Pcompilation.i_g][silver.driver.util.Init.silver_definition_env_grammarFlowTypes__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.localAsIs(silver.driver.util.Init.flowTypes__ON__silver_driver_util_compilation)); } };
		// r.productionFlowGraphs = finalGraphEnv
		silver.driver.util.Pcompilation.childInheritedAttributes[silver.driver.util.Pcompilation.i_r][silver.driver.util.Init.silver_definition_env_productionFlowGraphs__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.localAsIs(silver.driver.util.Init.finalGraphEnv__ON__silver_driver_util_compilation)); } };
		// r.grammarFlowTypes = flowTypes
		silver.driver.util.Pcompilation.childInheritedAttributes[silver.driver.util.Pcompilation.i_r][silver.driver.util.Init.silver_definition_env_grammarFlowTypes__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.localAsIs(silver.driver.util.Init.flowTypes__ON__silver_driver_util_compilation)); } };
		//FUNCTION getProdNt String ::= d::DclInfo 
	}

	public static int count_inh__ON__RootSpec = 0;
	public static int count_syn__ON__RootSpec = 0;
	public static int count_local__ON__silver_driver_util_grammarRootSpec = 0;
	public static int count_local__ON__silver_driver_util_interfaceRootSpec = 0;
	public static int count_local__ON__silver_driver_util_errorRootSpec = 0;
	public static int count_local__ON__silver_driver_util_parseErrorToMessage = 0;
	public static int count_local__ON__silver_driver_util_unparseRootSpec = 0;
	public static int count_local__ON__silver_driver_util_mentionedGrammars = 0;
	public static int count_local__ON__silver_driver_util_gatherFlowEnv = 0;
	public static int count_local__ON__silver_driver_util_isOutOfDate = 0;
	public static int count_inh__ON__BuildEnv = 0;
	public static int count_syn__ON__BuildEnv = 0;
	public static int count_local__ON__silver_driver_util_buildEnv = 0;
	public static int count_local__ON__silver_driver_util_fromArgsAndEnv = 0;
	public static int count_local__ON__silver_driver_util_endWithSlash = 0;
	public static int count_local__ON__silver_driver_util_isExportedBy = 0;
	public static int count_local__ON__silver_driver_util_isStrictlyExportedBy = 0;
	public static int count_local__ON__silver_driver_util_expandExports = 0;
	public static int count_local__ON__silver_driver_util_expandAllDeps = 0;
	public static int count_local__ON__silver_driver_util_computeDependencies = 0;
	public static int count_local__ON__silver_driver_util_expandCondBuilds = 0;
	public static int count_local__ON__silver_driver_util_expandOptionalsIter = 0;
	public static int count_local__ON__silver_driver_util_computeOptionalDeps = 0;
	public static int count_local__ON__silver_driver_util_completeDependencyClosure = 0;
	public static int count_local__ON__silver_driver_util_inductivelyExpand = 0;
	public static int count_local__ON__silver_driver_util_noninductiveExpansion = 0;
	public static int count_local__ON__silver_driver_util_skipNulls = 0;
	public static int count_inh__ON__Compilation = 0;
	public static int count_syn__ON__Compilation = 0;
	public static int count_local__ON__silver_driver_util_compilation = 0;
	public static int count_inh__ON__Grammars = 0;
	public static int count_syn__ON__Grammars = 0;
	public static int count_local__ON__silver_driver_util_consGrammars = 0;
	public static int count_local__ON__silver_driver_util_nilGrammars = 0;
	public static int count_local__ON__silver_driver_util_grammarPairing = 0;
	public static int count_local__ON__silver_driver_util_keepGrammars = 0;
	public static int count_inh__ON__DriverAction = 0;
	public static int count_syn__ON__DriverAction = 0;
	public static int count_local__ON__silver_driver_util_wrapUnit = 0;
	public static int count_local__ON__silver_driver_util_runAll = 0;
	public static int count_local__ON__silver_driver_util_sortUnits = 0;
	public static int count_local__ON__silver_driver_util_unitLTE = 0;
	public static int count_local__ON__silver_driver_util_grammarToPath = 0;
	public static int count_local__ON__silver_driver_util_findGrammarLocation = 0;
	public static int count_local__ON__silver_driver_util_findGrammarInLocation = 0;
	public static int count_local__ON__silver_driver_util_getProdNt = 0;
public static final int silver_definition_env_config__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_inh__ON__RootSpec++;
public static final int silver_definition_env_compiledGrammars__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_inh__ON__RootSpec++;
public static final int silver_definition_env_productionFlowGraphs__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_inh__ON__RootSpec++;
public static final int silver_definition_env_grammarFlowTypes__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_inh__ON__RootSpec++;
public static final int silver_definition_env_declaredName__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_env_moduleNames__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_env_exportedGrammars__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_env_optionalGrammars__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_env_condBuild__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_env_allGrammarDependencies__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_env_defs__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_core_grammarErrors__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_env_grammarSource__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_env_grammarTime__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_env_interfaceTime__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_driver_util_recheckGrammars__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_driver_util_translateGrammars__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_driver_util_parsingErrors__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int actualDependencies__ON__silver_driver_util_grammarRootSpec = silver.driver.util.Init.count_local__ON__silver_driver_util_grammarRootSpec++;
public static final int depsPlusOptions__ON__silver_driver_util_grammarRootSpec = silver.driver.util.Init.count_local__ON__silver_driver_util_grammarRootSpec++;
public static final int ood__ON__silver_driver_util_interfaceRootSpec = silver.driver.util.Init.count_local__ON__silver_driver_util_interfaceRootSpec++;
public static final int unparses__ON__silver_driver_util_unparseRootSpec = silver.driver.util.Init.count_local__ON__silver_driver_util_unparseRootSpec++;
public static final int n__ON__silver_driver_util_isOutOfDate = silver.driver.util.Init.count_local__ON__silver_driver_util_isOutOfDate++;
public static final int silver_driver_util_silverHome__ON__silver_driver_util_BuildEnv = silver.driver.util.Init.count_syn__ON__BuildEnv++;
public static final int silver_driver_util_silverGen__ON__silver_driver_util_BuildEnv = silver.driver.util.Init.count_syn__ON__BuildEnv++;
public static final int silver_driver_util_grammarPath__ON__silver_driver_util_BuildEnv = silver.driver.util.Init.count_syn__ON__BuildEnv++;
public static final int silver_driver_util_defaultSilverGen__ON__silver_driver_util_BuildEnv = silver.driver.util.Init.count_syn__ON__BuildEnv++;
public static final int silver_driver_util_defaultGrammarPath__ON__silver_driver_util_BuildEnv = silver.driver.util.Init.count_syn__ON__BuildEnv++;
public static final int silverHome__ON__silver_driver_util_fromArgsAndEnv = silver.driver.util.Init.count_local__ON__silver_driver_util_fromArgsAndEnv++;
public static final int silverGen__ON__silver_driver_util_fromArgsAndEnv = silver.driver.util.Init.count_local__ON__silver_driver_util_fromArgsAndEnv++;
public static final int grammarPath__ON__silver_driver_util_fromArgsAndEnv = silver.driver.util.Init.count_local__ON__silver_driver_util_fromArgsAndEnv++;
public static final int benv__ON__silver_driver_util_fromArgsAndEnv = silver.driver.util.Init.count_local__ON__silver_driver_util_fromArgsAndEnv++;
public static final int g__ON__silver_driver_util_expandExports = silver.driver.util.Init.count_local__ON__silver_driver_util_expandExports++;
public static final int g__ON__silver_driver_util_expandAllDeps = silver.driver.util.Init.count_local__ON__silver_driver_util_expandAllDeps++;
public static final int newtriggers__ON__silver_driver_util_expandCondBuilds = silver.driver.util.Init.count_local__ON__silver_driver_util_expandCondBuilds++;
public static final int newset__ON__silver_driver_util_expandCondBuilds = silver.driver.util.Init.count_local__ON__silver_driver_util_expandCondBuilds++;
public static final int triggered__ON__silver_driver_util_expandCondBuilds = silver.driver.util.Init.count_local__ON__silver_driver_util_expandCondBuilds++;
public static final int g__ON__silver_driver_util_expandOptionalsIter = silver.driver.util.Init.count_local__ON__silver_driver_util_expandOptionalsIter++;
public static final int initPlusExported__ON__silver_driver_util_computeOptionalDeps = silver.driver.util.Init.count_local__ON__silver_driver_util_computeOptionalDeps++;
public static final int closeOptions__ON__silver_driver_util_computeOptionalDeps = silver.driver.util.Init.count_local__ON__silver_driver_util_computeOptionalDeps++;
public static final int n__ON__silver_driver_util_completeDependencyClosure = silver.driver.util.Init.count_local__ON__silver_driver_util_completeDependencyClosure++;
public static final int result__ON__silver_driver_util_inductivelyExpand = silver.driver.util.Init.count_local__ON__silver_driver_util_inductivelyExpand++;
public static final int silver_definition_env_config__ON__silver_driver_util_Compilation = silver.driver.util.Init.count_inh__ON__Compilation++;
public static final int silver_driver_util_postOps__ON__silver_driver_util_Compilation = silver.driver.util.Init.count_syn__ON__Compilation++;
public static final int silver_driver_util_grammarList__ON__silver_driver_util_Compilation = silver.driver.util.Init.count_syn__ON__Compilation++;
public static final int silver_driver_util_recheckGrammars__ON__silver_driver_util_Compilation = silver.driver.util.Init.count_syn__ON__Compilation++;
public static final int silver_driver_util_allGrammars__ON__silver_driver_util_Compilation = silver.driver.util.Init.count_syn__ON__Compilation++;
public static final int grammarsDependedUpon__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int grammarsRelevant__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int grammarsToTranslate__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int silver_definition_env_config__ON__silver_driver_util_Grammars = silver.driver.util.Init.count_inh__ON__Grammars++;
public static final int silver_definition_env_compiledGrammars__ON__silver_driver_util_Grammars = silver.driver.util.Init.count_inh__ON__Grammars++;
public static final int silver_definition_env_productionFlowGraphs__ON__silver_driver_util_Grammars = silver.driver.util.Init.count_inh__ON__Grammars++;
public static final int silver_definition_env_grammarFlowTypes__ON__silver_driver_util_Grammars = silver.driver.util.Init.count_inh__ON__Grammars++;
public static final int silver_driver_util_grammarList__ON__silver_driver_util_Grammars = silver.driver.util.Init.count_syn__ON__Grammars++;
public static final int silver_driver_util_recheckGrammars__ON__silver_driver_util_Grammars = silver.driver.util.Init.count_syn__ON__Grammars++;
public static final int silver_driver_util_translateGrammars__ON__silver_driver_util_Grammars = silver.driver.util.Init.count_syn__ON__Grammars++;
public static final int silver_driver_util_ioIn__ON__silver_driver_util_DriverAction = silver.driver.util.Init.count_inh__ON__DriverAction++;
public static final int core_io__ON__silver_driver_util_DriverAction = silver.driver.util.Init.count_syn__ON__DriverAction++;
public static final int silver_driver_util_code__ON__silver_driver_util_DriverAction = silver.driver.util.Init.count_syn__ON__DriverAction++;
public static final int silver_driver_util_order__ON__silver_driver_util_DriverAction = silver.driver.util.Init.count_syn__ON__DriverAction++;
public static final int call__ON__silver_driver_util_wrapUnit = silver.driver.util.Init.count_local__ON__silver_driver_util_wrapUnit++;
public static final int now__ON__silver_driver_util_runAll = silver.driver.util.Init.count_local__ON__silver_driver_util_runAll++;
public static final int exists__ON__silver_driver_util_findGrammarLocation = silver.driver.util.Init.count_local__ON__silver_driver_util_findGrammarLocation++;
public static final int idx__ON__silver_driver_util_findGrammarInLocation = silver.driver.util.Init.count_local__ON__silver_driver_util_findGrammarInLocation++;
public static final int nextGram__ON__silver_driver_util_findGrammarInLocation = silver.driver.util.Init.count_local__ON__silver_driver_util_findGrammarInLocation++;
public static final int exists__ON__silver_driver_util_findGrammarInLocation = silver.driver.util.Init.count_local__ON__silver_driver_util_findGrammarInLocation++;
public static final int allFlowDefs__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int allFlowEnv__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int prodTree__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int allRealDefs__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int allRealEnv__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int allProds__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int allNts__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int prodGraph__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int initialFT__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int flowTypes1__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int flowTypes__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int finalGraphs__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int finalGraphEnv__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
}
