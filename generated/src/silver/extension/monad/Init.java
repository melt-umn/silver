package silver.extension.monad;

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
		silver.modification.let_fix.Init.initAllStatics();
		silver.modification.lambda_fn.Init.initAllStatics();
		silver.extension.convenience.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.extension.monad.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.modification.let_fix.Init.init();
		silver.modification.lambda_fn.Init.init();
		silver.extension.convenience.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.core.Init.init();
		silver.extension.monad.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.modification.let_fix.Init.postInit();
		silver.modification.lambda_fn.Init.postInit();
		silver.extension.convenience.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.extension.monad.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PbindExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PreturnExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PreturnUnitExpr.class);
		common.Decorator.applyDecorators(silver.extension.monad.NMName.decorators, PmNameIdLower.class);
		common.Decorator.applyDecorators(silver.extension.monad.NMName.decorators, PmNameIdUpper.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, Pdo_c.class);
		common.Decorator.applyDecorators(silver.extension.monad.NDoBodyStmts.decorators, PbindExprDoBodyStmts.class);
		common.Decorator.applyDecorators(silver.extension.monad.NDoBodyStmts.decorators, PletExprDoBodyStmts.class);
		common.Decorator.applyDecorators(silver.extension.monad.NDoBodyStmts.decorators, PconsDoBodyStmt.class);
		common.Decorator.applyDecorators(silver.extension.monad.NDoBodyStmts.decorators, PoneDoBodyStmt.class);
		common.Decorator.applyDecorators(silver.extension.monad.NDoBodyStmt.decorators, PdoBodyBlock.class);
		common.Decorator.applyDecorators(silver.extension.monad.NDoBodyStmt.decorators, PexprDoBody.class);
		common.Decorator.applyDecorators(silver.extension.monad.NDoBodyStmt.decorators, PreturnDoBody.class);
		common.Decorator.applyDecorators(silver.extension.monad.NDoBodyStmt.decorators, PreturnUnitDoBody.class);
		common.Decorator.applyDecorators(silver.extension.monad.NDoBodyStmt.decorators, PcondDoBody.class);
		common.Decorator.applyDecorators(silver.extension.monad.NDoBodyStmt.decorators, PcondDoBodyElse.class);
	}

	private static void setupInheritedAttributes(){
		silver.extension.monad.NMName.occurs_inh[silver.extension.monad.Init.silver_definition_env_config__ON__silver_extension_monad_MName] = "silver:definition:env:config";
		silver.extension.monad.NMName.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.monad.NMName.occurs_inh[silver.extension.monad.Init.silver_definition_core_grammarName__ON__silver_extension_monad_MName] = "silver:definition:core:grammarName";
		silver.extension.monad.NMName.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.extension.monad.NMName.occurs_syn[silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_MName] = "silver:definition:env:pp";
		silver.extension.monad.NMName.occurs_syn[silver.extension.monad.Init.silver_extension_monad_name__ON__silver_extension_monad_MName] = "silver:extension:monad:name";
		silver.extension.monad.Pdo_c.occurs_local[silver.extension.monad.Init.bindLookup__ON__silver_extension_monad_do_c] = "silver:extension:monad:do_c:local:bindLookup";
		silver.extension.monad.Pdo_c.occurs_local[silver.extension.monad.Init.returnLookup__ON__silver_extension_monad_do_c] = "silver:extension:monad:do_c:local:returnLookup";
		silver.extension.monad.Pdo_c.occurs_local[silver.extension.monad.Init.localErrors__ON__silver_extension_monad_do_c] = "silver:extension:monad:do_c:local:localErrors";
		silver.extension.monad.NDoBodyStmts.occurs_syn[silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmts] = "silver:definition:env:pp";
		silver.extension.monad.NDoBodyStmts.occurs_syn[silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmts] = "silver:extension:monad:transform";
		silver.extension.monad.NDoBodyStmts.occurs_inh[silver.extension.monad.Init.silver_extension_monad_bindFn__ON__silver_extension_monad_DoBodyStmts] = "silver:extension:monad:bindFn";
		silver.extension.monad.NDoBodyStmts.decorators.add(silver.extension.monad.DbindFn.singleton);
		silver.extension.monad.NDoBodyStmts.occurs_inh[silver.extension.monad.Init.silver_extension_monad_returnFn__ON__silver_extension_monad_DoBodyStmts] = "silver:extension:monad:returnFn";
		silver.extension.monad.NDoBodyStmts.decorators.add(silver.extension.monad.DreturnFn.singleton);
		silver.extension.monad.NDoBodyStmts.occurs_inh[silver.extension.monad.Init.silver_extension_monad_isFinalVal__ON__silver_extension_monad_DoBodyStmts] = "silver:extension:monad:isFinalVal";
		silver.extension.monad.NDoBodyStmts.decorators.add(silver.extension.monad.DisFinalVal.singleton);
		silver.extension.monad.NDoBodyStmt.occurs_syn[silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmt] = "silver:definition:env:pp";
		silver.extension.monad.NDoBodyStmt.occurs_syn[silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmt] = "silver:extension:monad:transform";
		silver.extension.monad.NDoBodyStmt.occurs_inh[silver.extension.monad.Init.silver_extension_monad_bindFn__ON__silver_extension_monad_DoBodyStmt] = "silver:extension:monad:bindFn";
		silver.extension.monad.NDoBodyStmt.decorators.add(silver.extension.monad.DbindFn.singleton);
		silver.extension.monad.NDoBodyStmt.occurs_inh[silver.extension.monad.Init.silver_extension_monad_returnFn__ON__silver_extension_monad_DoBodyStmt] = "silver:extension:monad:returnFn";
		silver.extension.monad.NDoBodyStmt.decorators.add(silver.extension.monad.DreturnFn.singleton);
		silver.extension.monad.NDoBodyStmt.occurs_inh[silver.extension.monad.Init.silver_extension_monad_isFinalVal__ON__silver_extension_monad_DoBodyStmt] = "silver:extension:monad:isFinalVal";
		silver.extension.monad.NDoBodyStmt.decorators.add(silver.extension.monad.DisFinalVal.singleton);
	}

	private static void initProductionAttributeDefinitions(){
		silver.extension.monad.PbindExpr.initProductionAttributeDefinitions();
		silver.extension.monad.PreturnExpr.initProductionAttributeDefinitions();
		silver.extension.monad.PreturnUnitExpr.initProductionAttributeDefinitions();
		silver.extension.monad.PmNameIdLower.initProductionAttributeDefinitions();
		silver.extension.monad.PmNameIdUpper.initProductionAttributeDefinitions();
		//FUNCTION nameFromMName Name ::= n::MName 
		//FUNCTION mName MName ::= n::String l::Location 
		silver.extension.monad.Pdo_c.initProductionAttributeDefinitions();
		silver.extension.monad.PbindExprDoBodyStmts.initProductionAttributeDefinitions();
		silver.extension.monad.PletExprDoBodyStmts.initProductionAttributeDefinitions();
		silver.extension.monad.PconsDoBodyStmt.initProductionAttributeDefinitions();
		silver.extension.monad.PoneDoBodyStmt.initProductionAttributeDefinitions();
		silver.extension.monad.PdoBodyBlock.initProductionAttributeDefinitions();
		silver.extension.monad.PexprDoBody.initProductionAttributeDefinitions();
		silver.extension.monad.PreturnDoBody.initProductionAttributeDefinitions();
		silver.extension.monad.PreturnUnitDoBody.initProductionAttributeDefinitions();
		silver.extension.monad.PcondDoBody.initProductionAttributeDefinitions();
		silver.extension.monad.PcondDoBodyElse.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_extension_monad_bindExpr = 0;
	public static int count_local__ON__silver_extension_monad_returnExpr = 0;
	public static int count_local__ON__silver_extension_monad_returnUnitExpr = 0;
	public static int count_inh__ON__MName = 0;
	public static int count_syn__ON__MName = 0;
	public static int count_local__ON__silver_extension_monad_mNameIdLower = 0;
	public static int count_local__ON__silver_extension_monad_mNameIdUpper = 0;
	public static int count_local__ON__silver_extension_monad_nameFromMName = 0;
	public static int count_local__ON__silver_extension_monad_mName = 0;
	public static int count_local__ON__silver_extension_monad_do_c = 0;
	public static int count_inh__ON__DoBodyStmts = 0;
	public static int count_syn__ON__DoBodyStmts = 0;
	public static int count_local__ON__silver_extension_monad_bindExprDoBodyStmts = 0;
	public static int count_local__ON__silver_extension_monad_letExprDoBodyStmts = 0;
	public static int count_local__ON__silver_extension_monad_consDoBodyStmt = 0;
	public static int count_local__ON__silver_extension_monad_oneDoBodyStmt = 0;
	public static int count_inh__ON__DoBodyStmt = 0;
	public static int count_syn__ON__DoBodyStmt = 0;
	public static int count_local__ON__silver_extension_monad_doBodyBlock = 0;
	public static int count_local__ON__silver_extension_monad_exprDoBody = 0;
	public static int count_local__ON__silver_extension_monad_returnDoBody = 0;
	public static int count_local__ON__silver_extension_monad_returnUnitDoBody = 0;
	public static int count_local__ON__silver_extension_monad_condDoBody = 0;
	public static int count_local__ON__silver_extension_monad_condDoBodyElse = 0;
public static final int silver_definition_env_config__ON__silver_extension_monad_MName = silver.extension.monad.Init.count_inh__ON__MName++;
public static final int silver_definition_core_grammarName__ON__silver_extension_monad_MName = silver.extension.monad.Init.count_inh__ON__MName++;
public static final int silver_definition_env_pp__ON__silver_extension_monad_MName = silver.extension.monad.Init.count_syn__ON__MName++;
public static final int silver_extension_monad_name__ON__silver_extension_monad_MName = silver.extension.monad.Init.count_syn__ON__MName++;
public static final int bindLookup__ON__silver_extension_monad_do_c = silver.extension.monad.Init.count_local__ON__silver_extension_monad_do_c++;
public static final int returnLookup__ON__silver_extension_monad_do_c = silver.extension.monad.Init.count_local__ON__silver_extension_monad_do_c++;
public static final int localErrors__ON__silver_extension_monad_do_c = silver.extension.monad.Init.count_local__ON__silver_extension_monad_do_c++;
public static final int silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmts = silver.extension.monad.Init.count_syn__ON__DoBodyStmts++;
public static final int silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmts = silver.extension.monad.Init.count_syn__ON__DoBodyStmts++;
public static final int silver_extension_monad_bindFn__ON__silver_extension_monad_DoBodyStmts = silver.extension.monad.Init.count_inh__ON__DoBodyStmts++;
public static final int silver_extension_monad_returnFn__ON__silver_extension_monad_DoBodyStmts = silver.extension.monad.Init.count_inh__ON__DoBodyStmts++;
public static final int silver_extension_monad_isFinalVal__ON__silver_extension_monad_DoBodyStmts = silver.extension.monad.Init.count_inh__ON__DoBodyStmts++;
public static final int silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmt = silver.extension.monad.Init.count_syn__ON__DoBodyStmt++;
public static final int silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmt = silver.extension.monad.Init.count_syn__ON__DoBodyStmt++;
public static final int silver_extension_monad_bindFn__ON__silver_extension_monad_DoBodyStmt = silver.extension.monad.Init.count_inh__ON__DoBodyStmt++;
public static final int silver_extension_monad_returnFn__ON__silver_extension_monad_DoBodyStmt = silver.extension.monad.Init.count_inh__ON__DoBodyStmt++;
public static final int silver_extension_monad_isFinalVal__ON__silver_extension_monad_DoBodyStmt = silver.extension.monad.Init.count_inh__ON__DoBodyStmt++;
}
