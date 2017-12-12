package silver.definition.flow.ast;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.flow.ast.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.definition.env.Init.init();
		silver.definition.flow.ast.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.flow.ast.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowVertex.decorators, PlhsSynVertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowVertex.decorators, PlhsInhVertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowVertex.decorators, PrhsVertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowVertex.decorators, PlocalEqVertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowVertex.decorators, PlocalVertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowVertex.decorators, PanonEqVertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowVertex.decorators, PanonVertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NExprVertexInfo.decorators, PhasVertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NExprVertexInfo.decorators, PnoVertex.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDefs.decorators, PconsFlow.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDefs.decorators, PnilFlow.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PprodFlowDef.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PextSynFlowDef.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PdefaultSynEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PntRefFlowDef.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PspecificationFlowDef.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PsynEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PinhEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PfwdEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PimplicitFwdAffects.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PfwdInhEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PlocalEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PlocalInhEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PextraEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PanonEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PanonInhEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NFlowDef.decorators, PpatternRuleEq.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NPatternVarProjection.decorators, PpatternVarProjection.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NVertexType.decorators, PlhsVertexType_real.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NVertexType.decorators, PrhsVertexType.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NVertexType.decorators, PlocalVertexType.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NVertexType.decorators, PforwardVertexType_real.class);
		common.Decorator.applyDecorators(silver.definition.flow.ast.NVertexType.decorators, PanonVertexType.class);
	}

	private static void setupInheritedAttributes(){
		silver.definition.flow.ast.NFlowVertex.occurs_syn[silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_FlowVertex] = "silver:definition:env:unparse";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_synTreeContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:synTreeContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_inhTreeContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:inhTreeContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_defTreeContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:defTreeContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdTreeContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:fwdTreeContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdInhTreeContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:fwdInhTreeContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:unparses";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodTreeContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:prodTreeContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:prodGraphContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_flowEdges__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:flowEdges";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_refTreeContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:refTreeContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_localInhTreeContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:localInhTreeContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_suspectFlowEdges__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:suspectFlowEdges";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_extSynTreeContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:extSynTreeContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_nonSuspectContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:nonSuspectContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_localTreeContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:localTreeContribs";
		silver.definition.flow.ast.NFlowDef.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_specContribs__ON__silver_definition_flow_ast_FlowDef] = "silver:definition:flow:ast:specContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_synTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:synTreeContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_inhTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:inhTreeContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_defTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:defTreeContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:fwdTreeContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdInhTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:fwdInhTreeContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:unparses";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:prodTreeContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:prodGraphContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_refTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:refTreeContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_localInhTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:localInhTreeContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_extSynTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:extSynTreeContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_nonSuspectContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:nonSuspectContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_localTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:localTreeContribs";
		silver.definition.flow.ast.NFlowDefs.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_specContribs__ON__silver_definition_flow_ast_FlowDefs] = "silver:definition:flow:ast:specContribs";
		silver.definition.flow.ast.PsynEq.occurs_local[silver.definition.flow.ast.Init.edges__ON__silver_definition_flow_ast_synEq] = "silver:definition:flow:ast:synEq:local:edges";
		silver.definition.flow.ast.PfwdEq.occurs_local[silver.definition.flow.ast.Init.edges__ON__silver_definition_flow_ast_fwdEq] = "silver:definition:flow:ast:fwdEq:local:edges";
		silver.definition.flow.ast.PextraEq.occurs_local[silver.definition.flow.ast.Init.edges__ON__silver_definition_flow_ast_extraEq] = "silver:definition:flow:ast:extraEq:local:edges";
		silver.definition.flow.ast.NPatternVarProjection.occurs_syn[silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_PatternVarProjection] = "silver:definition:env:unparse";
		silver.definition.flow.ast.NVertexType.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_synVertex__ON__silver_definition_flow_ast_VertexType] = "silver:definition:flow:ast:synVertex";
		silver.definition.flow.ast.NVertexType.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_inhVertex__ON__silver_definition_flow_ast_VertexType] = "silver:definition:flow:ast:inhVertex";
		silver.definition.flow.ast.NVertexType.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdVertex__ON__silver_definition_flow_ast_VertexType] = "silver:definition:flow:ast:fwdVertex";
		silver.definition.flow.ast.NVertexType.occurs_syn[silver.definition.flow.ast.Init.silver_definition_flow_ast_eqVertex__ON__silver_definition_flow_ast_VertexType] = "silver:definition:flow:ast:eqVertex";
		silver.definition.flow.ast.NVertexType.occurs_syn[silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_VertexType] = "silver:definition:env:unparse";
	}

	private static void initProductionAttributeDefinitions(){
		silver.definition.flow.ast.PlhsSynVertex.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PlhsInhVertex.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PrhsVertex.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PlocalEqVertex.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PlocalVertex.initProductionAttributeDefinitions();
		//FUNCTION forwardEqVertex FlowVertex ::= 
		//FUNCTION forwardVertex FlowVertex ::= attrName::String 
		silver.definition.flow.ast.PanonEqVertex.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PanonVertex.initProductionAttributeDefinitions();
		//FUNCTION unparseVertices String ::= fvs::[FlowVertex] 
		//FUNCTION equalFlowVertex Boolean ::= a::FlowVertex b::FlowVertex 
		silver.definition.flow.ast.PhasVertex.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PnoVertex.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PconsFlow.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PnilFlow.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for FlowDef
		// top.synTreeContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_synTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.inhTreeContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_inhTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.defTreeContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_defTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.fwdTreeContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.fwdInhTreeContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdInhTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.prodTreeContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.refTreeContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_refTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.localInhTreeContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_localInhTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.localTreeContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_localTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.extSynTreeContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_extSynTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.nonSuspectContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_nonSuspectContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.specContribs = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_specContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.suspectFlowEdges = []
		silver.definition.flow.ast.NFlowDef.defaultSynthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_suspectFlowEdges__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		silver.definition.flow.ast.PprodFlowDef.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PextSynFlowDef.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PdefaultSynEq.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PntRefFlowDef.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PspecificationFlowDef.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PsynEq.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PinhEq.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PfwdEq.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PimplicitFwdAffects.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PfwdInhEq.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PlocalEq.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PlocalInhEq.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PextraEq.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PanonEq.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PanonInhEq.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PpatternRuleEq.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PpatternVarProjection.initProductionAttributeDefinitions();
		//FUNCTION crossnames String ::= a::String b::String 
		//FUNCTION collectAnonOrigin [Pair<String Location>] ::= f::[FlowDef] 
		//FUNCTION collectAnonOriginItem [Pair<String Location>] ::= f::FlowDef rest::[Pair<String Location>] 
		silver.definition.flow.ast.PlhsVertexType_real.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PrhsVertexType.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PlocalVertexType.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PforwardVertexType_real.initProductionAttributeDefinitions();
		silver.definition.flow.ast.PanonVertexType.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__FlowVertex = 0;
	public static int count_syn__ON__FlowVertex = 0;
	public static int count_local__ON__silver_definition_flow_ast_lhsSynVertex = 0;
	public static int count_local__ON__silver_definition_flow_ast_lhsInhVertex = 0;
	public static int count_local__ON__silver_definition_flow_ast_rhsVertex = 0;
	public static int count_local__ON__silver_definition_flow_ast_localEqVertex = 0;
	public static int count_local__ON__silver_definition_flow_ast_localVertex = 0;
	public static int count_local__ON__silver_definition_flow_ast_forwardEqVertex = 0;
	public static int count_local__ON__silver_definition_flow_ast_forwardVertex = 0;
	public static int count_local__ON__silver_definition_flow_ast_anonEqVertex = 0;
	public static int count_local__ON__silver_definition_flow_ast_anonVertex = 0;
	public static int count_local__ON__silver_definition_flow_ast_unparseVertices = 0;
	public static int count_local__ON__silver_definition_flow_ast_equalFlowVertex = 0;
	public static int count_inh__ON__ExprVertexInfo = 0;
	public static int count_syn__ON__ExprVertexInfo = 0;
	public static int count_local__ON__silver_definition_flow_ast_hasVertex = 0;
	public static int count_local__ON__silver_definition_flow_ast_noVertex = 0;
	public static int count_inh__ON__FlowDef = 0;
	public static int count_syn__ON__FlowDef = 0;
	public static int count_inh__ON__FlowDefs = 0;
	public static int count_syn__ON__FlowDefs = 0;
	public static int count_local__ON__silver_definition_flow_ast_consFlow = 0;
	public static int count_local__ON__silver_definition_flow_ast_nilFlow = 0;
	public static int count_local__ON__silver_definition_flow_ast_prodFlowDef = 0;
	public static int count_local__ON__silver_definition_flow_ast_extSynFlowDef = 0;
	public static int count_local__ON__silver_definition_flow_ast_defaultSynEq = 0;
	public static int count_local__ON__silver_definition_flow_ast_ntRefFlowDef = 0;
	public static int count_local__ON__silver_definition_flow_ast_specificationFlowDef = 0;
	public static int count_local__ON__silver_definition_flow_ast_synEq = 0;
	public static int count_local__ON__silver_definition_flow_ast_inhEq = 0;
	public static int count_local__ON__silver_definition_flow_ast_fwdEq = 0;
	public static int count_local__ON__silver_definition_flow_ast_implicitFwdAffects = 0;
	public static int count_local__ON__silver_definition_flow_ast_fwdInhEq = 0;
	public static int count_local__ON__silver_definition_flow_ast_localEq = 0;
	public static int count_local__ON__silver_definition_flow_ast_localInhEq = 0;
	public static int count_local__ON__silver_definition_flow_ast_extraEq = 0;
	public static int count_local__ON__silver_definition_flow_ast_anonEq = 0;
	public static int count_local__ON__silver_definition_flow_ast_anonInhEq = 0;
	public static int count_local__ON__silver_definition_flow_ast_patternRuleEq = 0;
	public static int count_inh__ON__PatternVarProjection = 0;
	public static int count_syn__ON__PatternVarProjection = 0;
	public static int count_local__ON__silver_definition_flow_ast_patternVarProjection = 0;
	public static int count_local__ON__silver_definition_flow_ast_crossnames = 0;
	public static int count_local__ON__silver_definition_flow_ast_collectAnonOrigin = 0;
	public static int count_local__ON__silver_definition_flow_ast_collectAnonOriginItem = 0;
	public static int count_inh__ON__VertexType = 0;
	public static int count_syn__ON__VertexType = 0;
	public static int count_local__ON__silver_definition_flow_ast_lhsVertexType_real = 0;
	public static int count_local__ON__silver_definition_flow_ast_rhsVertexType = 0;
	public static int count_local__ON__silver_definition_flow_ast_localVertexType = 0;
	public static int count_local__ON__silver_definition_flow_ast_forwardVertexType_real = 0;
	public static int count_local__ON__silver_definition_flow_ast_anonVertexType = 0;
public static final int silver_definition_env_unparse__ON__silver_definition_flow_ast_FlowVertex = silver.definition.flow.ast.Init.count_syn__ON__FlowVertex++;
public static final int silver_definition_flow_ast_synTreeContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_inhTreeContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_defTreeContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_fwdTreeContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_fwdInhTreeContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_prodTreeContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_flowEdges__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_refTreeContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_localInhTreeContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_suspectFlowEdges__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_extSynTreeContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_nonSuspectContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_localTreeContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_specContribs__ON__silver_definition_flow_ast_FlowDef = silver.definition.flow.ast.Init.count_syn__ON__FlowDef++;
public static final int silver_definition_flow_ast_synTreeContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_inhTreeContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_defTreeContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_fwdTreeContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_fwdInhTreeContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_prodTreeContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_refTreeContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_localInhTreeContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_extSynTreeContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_nonSuspectContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_localTreeContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int silver_definition_flow_ast_specContribs__ON__silver_definition_flow_ast_FlowDefs = silver.definition.flow.ast.Init.count_syn__ON__FlowDefs++;
public static final int edges__ON__silver_definition_flow_ast_synEq = silver.definition.flow.ast.Init.count_local__ON__silver_definition_flow_ast_synEq++;
public static final int edges__ON__silver_definition_flow_ast_fwdEq = silver.definition.flow.ast.Init.count_local__ON__silver_definition_flow_ast_fwdEq++;
public static final int edges__ON__silver_definition_flow_ast_extraEq = silver.definition.flow.ast.Init.count_local__ON__silver_definition_flow_ast_extraEq++;
public static final int silver_definition_env_unparse__ON__silver_definition_flow_ast_PatternVarProjection = silver.definition.flow.ast.Init.count_syn__ON__PatternVarProjection++;
public static final int silver_definition_flow_ast_synVertex__ON__silver_definition_flow_ast_VertexType = silver.definition.flow.ast.Init.count_syn__ON__VertexType++;
public static final int silver_definition_flow_ast_inhVertex__ON__silver_definition_flow_ast_VertexType = silver.definition.flow.ast.Init.count_syn__ON__VertexType++;
public static final int silver_definition_flow_ast_fwdVertex__ON__silver_definition_flow_ast_VertexType = silver.definition.flow.ast.Init.count_syn__ON__VertexType++;
public static final int silver_definition_flow_ast_eqVertex__ON__silver_definition_flow_ast_VertexType = silver.definition.flow.ast.Init.count_syn__ON__VertexType++;
public static final int silver_definition_env_unparse__ON__silver_definition_flow_ast_VertexType = silver.definition.flow.ast.Init.count_syn__ON__VertexType++;
	public static final common.Thunk<Object> lhsVertexType = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)new silver.definition.flow.ast.PlhsVertexType_real()); } };
	public static final common.Thunk<Object> forwardVertexType = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)new silver.definition.flow.ast.PforwardVertexType_real()); } };
	public static final common.Thunk<Object> forwardEqVertex_singleton = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PlocalEqVertex((new common.StringCatter("forward")))); } };
}
