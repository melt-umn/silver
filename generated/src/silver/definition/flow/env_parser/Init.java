package silver.definition.flow.env_parser;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();
		silver.definition.flow.ast.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.env_parser.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.flow.env_parser.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.driver.util.Init.init();
		silver.definition.flow.ast.Init.init();
		silver.definition.flow.env.Init.init();
		silver.definition.core.Init.init();
		silver.definition.env.env_parser.Init.init();
		silver.definition.env.Init.init();
		silver.definition.flow.env_parser.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.driver.util.Init.postInit();
		silver.definition.flow.ast.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.env.env_parser.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.flow.env_parser.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.env.env_parser.NIRootPart.decorators, PaRootFlow.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowVertexes.decorators, PaFlowsNone_vertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowVertexes.decorators, PaFlowsSome_vertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowVertexesInner.decorators, PaFlowsOne_vertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowVertexesInner.decorators, PaFlowsCons_vertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowVertex.decorators, PaFlowSyn_vertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowVertex.decorators, PaFlowInh_vertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowVertex.decorators, PaFlowRhs_vertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowVertex.decorators, PaFlowlocalEqV_vertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowVertex.decorators, PaFlowlocalV_vertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowVertex.decorators, PaFlowanonEqV_vertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowVertex.decorators, PaFlowanonV_vertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlows.decorators, PaFlowsNone.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlows.decorators, PaFlowsSome.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowsInner.decorators, PaFlowsOne.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlowsInner.decorators, PaFlowsCons.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowSyn.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowDef.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowFwd.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowProd.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowRefFlowDef.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaSpecFlowDef.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowImplicitFwdAffects.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowNonHostSyn.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowInh.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowFwdInh.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowLocalEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowLocalInhEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowExtra.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowAnonEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaFlowAnonInhEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIFlow.decorators, PaPatternRuleEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIVertexType.decorators, PalhsVertexType.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIVertexType.decorators, ParhsVertexType.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIVertexType.decorators, PalocalVertexType.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIVertexType.decorators, PaanonVertexType.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIVertexType.decorators, PaforwardVertexType.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIPatternVarProjections.decorators, PaFlowsNone_proj.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIPatternVarProjections.decorators, PaFlowsSome_proj.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIPatternVarProjectionsInner.decorators, PaFlowsOne_proj.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIPatternVarProjectionsInner.decorators, PaFlowsCons_proj.class);
		common.Decorator.applyDecorators(silver.definition.flow.env_parser.NIPatternVarProjection.decorators, PaPatternVarProjection.class);
	}

	private static void setupInheritedAttributes(){
		silver.definition.env.env_parser.NIRoot.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_env_env_parser_IRoot] = "silver:definition:flow:env:flowDefs";
		silver.definition.env.env_parser.NIRootPart.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_env_env_parser_IRootPart] = "silver:definition:flow:env:flowDefs";
		silver.definition.flow.env_parser.NIFlows.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_env_parser_IFlows] = "silver:definition:flow:env:flowDefs";
		silver.definition.flow.env_parser.NIFlowsInner.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_env_parser_IFlowsInner] = "silver:definition:flow:env:flowDefs";
		silver.definition.flow.env_parser.NIFlow.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_env_parser_IFlow] = "silver:definition:flow:env:flowDefs";
		silver.definition.flow.env_parser.NIFlowVertexes.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDeps__ON__silver_definition_flow_env_parser_IFlowVertexes] = "silver:definition:flow:env:flowDeps";
		silver.definition.flow.env_parser.NIFlowVertexesInner.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDeps__ON__silver_definition_flow_env_parser_IFlowVertexesInner] = "silver:definition:flow:env:flowDeps";
		silver.definition.flow.env_parser.NIFlowVertex.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDeps__ON__silver_definition_flow_env_parser_IFlowVertex] = "silver:definition:flow:env:flowDeps";
		silver.definition.flow.env_parser.NIVertexType.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_parser_aVertexType__ON__silver_definition_flow_env_parser_IVertexType] = "silver:definition:flow:env_parser:aVertexType";
		silver.definition.flow.env_parser.NIPatternVarProjections.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_parser_aPatternVarProjection__ON__silver_definition_flow_env_parser_IPatternVarProjections] = "silver:definition:flow:env_parser:aPatternVarProjection";
		silver.definition.flow.env_parser.NIPatternVarProjectionsInner.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_parser_aPatternVarProjection__ON__silver_definition_flow_env_parser_IPatternVarProjectionsInner] = "silver:definition:flow:env_parser:aPatternVarProjection";
		silver.definition.flow.env_parser.NIPatternVarProjection.occurs_syn[silver.definition.flow.env_parser.Init.silver_definition_flow_env_parser_aPatternVarProjection__ON__silver_definition_flow_env_parser_IPatternVarProjection] = "silver:definition:flow:env_parser:aPatternVarProjection";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION interfaceRootSpec top ::= p::IRoot _ 
		// top.flowDefs = p.flowDefs
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_env_env_parser_IRoot)); } };
		//ASPECT PRODUCTION aRoot1 top ::= r::IRootPart 
		// top.flowDefs = r.flowDefs
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_env_env_parser_IRootPart)); } };
		//ASPECT PRODUCTION aRoot2 top ::= r1::IRootPart r2::IRoot 
		// top.flowDefs = r1.flowDefs ++ r2.flowDefs
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r1, silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_env_env_parser_IRootPart), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r2, silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_env_env_parser_IRoot))); } };
		//ASPECT DEFAULT PRODUCTION for IRootPart
		// top.flowDefs = []
		silver.definition.env.env_parser.NIRootPart.defaultSynthesizedAttributes[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_env_env_parser_IRootPart] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		silver.definition.flow.env_parser.PaRootFlow.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsNone_vertex.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsSome_vertex.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsOne_vertex.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsCons_vertex.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowSyn_vertex.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowInh_vertex.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowRhs_vertex.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowlocalEqV_vertex.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowlocalV_vertex.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowanonEqV_vertex.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowanonV_vertex.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsNone.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsSome.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsOne.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsCons.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowSyn.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowDef.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowFwd.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowProd.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowRefFlowDef.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaSpecFlowDef.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowImplicitFwdAffects.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowNonHostSyn.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowInh.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowFwdInh.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowLocalEq.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowLocalInhEq.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowExtra.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowAnonEq.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowAnonInhEq.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaPatternRuleEq.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PalhsVertexType.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.ParhsVertexType.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PalocalVertexType.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaanonVertexType.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaforwardVertexType.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsNone_proj.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsSome_proj.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsOne_proj.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaFlowsCons_proj.initProductionAttributeDefinitions();
		silver.definition.flow.env_parser.PaPatternVarProjection.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_definition_flow_env_parser_aRootFlow = 0;
	public static int count_inh__ON__IFlows = 0;
	public static int count_syn__ON__IFlows = 0;
	public static int count_inh__ON__IFlowsInner = 0;
	public static int count_syn__ON__IFlowsInner = 0;
	public static int count_inh__ON__IFlow = 0;
	public static int count_syn__ON__IFlow = 0;
	public static int count_inh__ON__IFlowVertexes = 0;
	public static int count_syn__ON__IFlowVertexes = 0;
	public static int count_inh__ON__IFlowVertexesInner = 0;
	public static int count_syn__ON__IFlowVertexesInner = 0;
	public static int count_inh__ON__IFlowVertex = 0;
	public static int count_syn__ON__IFlowVertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsNone_vertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsSome_vertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsOne_vertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsCons_vertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowSyn_vertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowInh_vertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowRhs_vertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowlocalEqV_vertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowlocalV_vertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowanonEqV_vertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowanonV_vertex = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsNone = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsSome = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsOne = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsCons = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowSyn = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowDef = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowFwd = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowProd = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowRefFlowDef = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aSpecFlowDef = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowImplicitFwdAffects = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowNonHostSyn = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowInh = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowFwdInh = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowLocalEq = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowLocalInhEq = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowExtra = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowAnonEq = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowAnonInhEq = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aPatternRuleEq = 0;
	public static int count_inh__ON__IVertexType = 0;
	public static int count_syn__ON__IVertexType = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_alhsVertexType = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_arhsVertexType = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_alocalVertexType = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aanonVertexType = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aforwardVertexType = 0;
	public static int count_inh__ON__IPatternVarProjections = 0;
	public static int count_syn__ON__IPatternVarProjections = 0;
	public static int count_inh__ON__IPatternVarProjectionsInner = 0;
	public static int count_syn__ON__IPatternVarProjectionsInner = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsNone_proj = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsSome_proj = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsOne_proj = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aFlowsCons_proj = 0;
	public static int count_inh__ON__IPatternVarProjection = 0;
	public static int count_syn__ON__IPatternVarProjection = 0;
	public static int count_local__ON__silver_definition_flow_env_parser_aPatternVarProjection = 0;
public static final int silver_definition_flow_env_flowDefs__ON__silver_definition_env_env_parser_IRoot = silver.definition.env.env_parser.Init.count_syn__ON__IRoot++;
public static final int silver_definition_flow_env_flowDefs__ON__silver_definition_env_env_parser_IRootPart = silver.definition.env.env_parser.Init.count_syn__ON__IRootPart++;
public static final int silver_definition_flow_env_flowDefs__ON__silver_definition_flow_env_parser_IFlows = silver.definition.flow.env_parser.Init.count_syn__ON__IFlows++;
public static final int silver_definition_flow_env_flowDefs__ON__silver_definition_flow_env_parser_IFlowsInner = silver.definition.flow.env_parser.Init.count_syn__ON__IFlowsInner++;
public static final int silver_definition_flow_env_flowDefs__ON__silver_definition_flow_env_parser_IFlow = silver.definition.flow.env_parser.Init.count_syn__ON__IFlow++;
public static final int silver_definition_flow_env_flowDeps__ON__silver_definition_flow_env_parser_IFlowVertexes = silver.definition.flow.env_parser.Init.count_syn__ON__IFlowVertexes++;
public static final int silver_definition_flow_env_flowDeps__ON__silver_definition_flow_env_parser_IFlowVertexesInner = silver.definition.flow.env_parser.Init.count_syn__ON__IFlowVertexesInner++;
public static final int silver_definition_flow_env_flowDeps__ON__silver_definition_flow_env_parser_IFlowVertex = silver.definition.flow.env_parser.Init.count_syn__ON__IFlowVertex++;
public static final int silver_definition_flow_env_parser_aVertexType__ON__silver_definition_flow_env_parser_IVertexType = silver.definition.flow.env_parser.Init.count_syn__ON__IVertexType++;
public static final int silver_definition_flow_env_parser_aPatternVarProjection__ON__silver_definition_flow_env_parser_IPatternVarProjections = silver.definition.flow.env_parser.Init.count_syn__ON__IPatternVarProjections++;
public static final int silver_definition_flow_env_parser_aPatternVarProjection__ON__silver_definition_flow_env_parser_IPatternVarProjectionsInner = silver.definition.flow.env_parser.Init.count_syn__ON__IPatternVarProjectionsInner++;
public static final int silver_definition_flow_env_parser_aPatternVarProjection__ON__silver_definition_flow_env_parser_IPatternVarProjection = silver.definition.flow.env_parser.Init.count_syn__ON__IPatternVarProjection++;
}
