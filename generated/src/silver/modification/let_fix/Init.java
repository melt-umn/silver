package silver.modification.let_fix;

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
		silver.definition.flow.ast.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.modification.let_fix.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.flow.ast.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.modification.let_fix.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.flow.ast.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.modification.let_fix.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PlexicalLocalDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, Pletp_c.class);
		common.Decorator.applyDecorators(silver.modification.let_fix.NLetAssigns.decorators, PassignsListCons.class);
		common.Decorator.applyDecorators(silver.modification.let_fix.NLetAssigns.decorators, PassignListSingle.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, Pletp.class);
		common.Decorator.applyDecorators(silver.modification.let_fix.NAssignExpr.decorators, PappendAssignExpr.class);
		common.Decorator.applyDecorators(silver.modification.let_fix.NAssignExpr.decorators, PassignExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PlexicalLocalReference.class);
	}

	private static void setupInheritedAttributes(){
		silver.modification.let_fix.NAssignExpr.occurs_inh[silver.modification.let_fix.Init.silver_definition_core_defaultInheritedAnnos__ON__silver_modification_let_fix_AssignExpr] = "silver:definition:core:defaultInheritedAnnos";
		silver.modification.let_fix.NAssignExpr.decorators.add(silver.definition.core.DdefaultInheritedAnnos.singleton);
		silver.modification.let_fix.NLetAssigns.occurs_syn[silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_LetAssigns] = "silver:definition:env:pp";
		silver.modification.let_fix.NLetAssigns.occurs_syn[silver.modification.let_fix.Init.silver_modification_let_fix_letAssignExprs__ON__silver_modification_let_fix_LetAssigns] = "silver:modification:let_fix:letAssignExprs";
		silver.modification.let_fix.NAssignExpr.occurs_inh[silver.modification.let_fix.Init.silver_definition_env_config__ON__silver_modification_let_fix_AssignExpr] = "silver:definition:env:config";
		silver.modification.let_fix.NAssignExpr.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.let_fix.NAssignExpr.occurs_inh[silver.modification.let_fix.Init.silver_definition_core_grammarName__ON__silver_modification_let_fix_AssignExpr] = "silver:definition:core:grammarName";
		silver.modification.let_fix.NAssignExpr.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.let_fix.NAssignExpr.occurs_inh[silver.modification.let_fix.Init.silver_definition_env_env__ON__silver_modification_let_fix_AssignExpr] = "silver:definition:env:env";
		silver.modification.let_fix.NAssignExpr.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.let_fix.NAssignExpr.occurs_inh[silver.modification.let_fix.Init.silver_definition_env_compiledGrammars__ON__silver_modification_let_fix_AssignExpr] = "silver:definition:env:compiledGrammars";
		silver.modification.let_fix.NAssignExpr.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.let_fix.NAssignExpr.occurs_syn[silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_AssignExpr] = "silver:definition:env:pp";
		silver.modification.let_fix.NAssignExpr.occurs_syn[silver.modification.let_fix.Init.silver_definition_env_defs__ON__silver_modification_let_fix_AssignExpr] = "silver:definition:env:defs";
		silver.modification.let_fix.NAssignExpr.occurs_syn[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr] = "silver:definition:core:errors";
		silver.modification.let_fix.NAssignExpr.occurs_syn[silver.modification.let_fix.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_let_fix_AssignExpr] = "silver:analysis:typechecking:core:upSubst";
		silver.modification.let_fix.NAssignExpr.occurs_inh[silver.modification.let_fix.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_let_fix_AssignExpr] = "silver:analysis:typechecking:core:downSubst";
		silver.modification.let_fix.NAssignExpr.occurs_inh[silver.modification.let_fix.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_let_fix_AssignExpr] = "silver:analysis:typechecking:core:finalSubst";
		silver.modification.let_fix.NAssignExpr.decorators.add(silver.analysis.typechecking.core.DfinalSubst.singleton);
		silver.modification.let_fix.NAssignExpr.occurs_inh[silver.modification.let_fix.Init.silver_definition_core_frame__ON__silver_modification_let_fix_AssignExpr] = "silver:definition:core:frame";
		silver.modification.let_fix.NAssignExpr.decorators.add(silver.definition.core.Dframe.singleton);
		//	local attribute finalTy::Type;
		silver.modification.let_fix.PassignExpr.localInheritedAttributes[silver.modification.let_fix.Init.finalTy__ON__silver_modification_let_fix_assignExpr] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.modification.let_fix.PassignExpr.occurs_local[silver.modification.let_fix.Init.finalTy__ON__silver_modification_let_fix_assignExpr] = "silver:modification:let_fix:assignExpr:local:finalTy";
		silver.modification.let_fix.PassignExpr.occurs_local[silver.modification.let_fix.Init.fName__ON__silver_modification_let_fix_assignExpr] = "silver:modification:let_fix:assignExpr:local:fName";
		//	local attribute errCheck1::TypeCheck;
		silver.modification.let_fix.PassignExpr.localInheritedAttributes[silver.modification.let_fix.Init.errCheck1__ON__silver_modification_let_fix_assignExpr] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.let_fix.PassignExpr.occurs_local[silver.modification.let_fix.Init.errCheck1__ON__silver_modification_let_fix_assignExpr] = "silver:modification:let_fix:assignExpr:local:errCheck1";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.let_fix.PlexicalLocalDcl.initProductionAttributeDefinitions();
		//FUNCTION lexicalLocalDef Def ::= sg::String sl::Location fn::String ty::Type fi::ExprVertexInfo fd::[FlowVertex] 
		silver.modification.let_fix.Pletp_c.initProductionAttributeDefinitions();
		silver.modification.let_fix.PassignsListCons.initProductionAttributeDefinitions();
		silver.modification.let_fix.PassignListSingle.initProductionAttributeDefinitions();
		silver.modification.let_fix.Pletp.initProductionAttributeDefinitions();
		silver.modification.let_fix.PappendAssignExpr.initProductionAttributeDefinitions();
		silver.modification.let_fix.PassignExpr.initProductionAttributeDefinitions();
		silver.modification.let_fix.PlexicalLocalReference.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_modification_let_fix_lexicalLocalDcl = 0;
	public static int count_local__ON__silver_modification_let_fix_lexicalLocalDef = 0;
	public static int count_local__ON__silver_modification_let_fix_letp_c = 0;
	public static int count_inh__ON__LetAssigns = 0;
	public static int count_syn__ON__LetAssigns = 0;
	public static int count_local__ON__silver_modification_let_fix_assignsListCons = 0;
	public static int count_local__ON__silver_modification_let_fix_assignListSingle = 0;
	public static int count_local__ON__silver_modification_let_fix_letp = 0;
	public static int count_inh__ON__AssignExpr = 0;
	public static int count_syn__ON__AssignExpr = 0;
	public static int count_local__ON__silver_modification_let_fix_appendAssignExpr = 0;
	public static int count_local__ON__silver_modification_let_fix_assignExpr = 0;
	public static int count_local__ON__silver_modification_let_fix_lexicalLocalReference = 0;
public static final int silver_definition_core_defaultInheritedAnnos__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_inh__ON__AssignExpr++;
public static final int silver_definition_env_pp__ON__silver_modification_let_fix_LetAssigns = silver.modification.let_fix.Init.count_syn__ON__LetAssigns++;
public static final int silver_modification_let_fix_letAssignExprs__ON__silver_modification_let_fix_LetAssigns = silver.modification.let_fix.Init.count_syn__ON__LetAssigns++;
public static final int silver_definition_env_config__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_inh__ON__AssignExpr++;
public static final int silver_definition_core_grammarName__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_inh__ON__AssignExpr++;
public static final int silver_definition_env_env__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_inh__ON__AssignExpr++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_inh__ON__AssignExpr++;
public static final int silver_definition_env_pp__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_syn__ON__AssignExpr++;
public static final int silver_definition_env_defs__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_syn__ON__AssignExpr++;
public static final int silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_syn__ON__AssignExpr++;
public static final int silver_analysis_typechecking_core_upSubst__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_syn__ON__AssignExpr++;
public static final int silver_analysis_typechecking_core_downSubst__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_inh__ON__AssignExpr++;
public static final int silver_analysis_typechecking_core_finalSubst__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_inh__ON__AssignExpr++;
public static final int silver_definition_core_frame__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_inh__ON__AssignExpr++;
public static final int finalTy__ON__silver_modification_let_fix_assignExpr = silver.modification.let_fix.Init.count_local__ON__silver_modification_let_fix_assignExpr++;
public static final int fName__ON__silver_modification_let_fix_assignExpr = silver.modification.let_fix.Init.count_local__ON__silver_modification_let_fix_assignExpr++;
public static final int errCheck1__ON__silver_modification_let_fix_assignExpr = silver.modification.let_fix.Init.count_local__ON__silver_modification_let_fix_assignExpr++;
}
