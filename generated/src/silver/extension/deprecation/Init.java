package silver.extension.deprecation;

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
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.extension.deprecation.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.extension.deprecation.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.extension.deprecation.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NModuleStmt.decorators, PbuildsStmt.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PconcreteDecorateExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PconcreteDontDecorateExpr.class);
		common.Decorator.applyDecorators(silver.extension.deprecation.NNameTick.decorators, PnameIdTick.class);
		common.Decorator.applyDecorators(silver.extension.deprecation.NNameTickTick.decorators, PnameIdTickTick.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdeprecatedDecl.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionBody.decorators, PemptyProductionBodySemi.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PprodTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PfunTypeLegacy.class);
	}

	private static void setupInheritedAttributes(){
		silver.extension.deprecation.NNameTick.occurs_inh[silver.extension.deprecation.Init.silver_definition_env_config__ON__silver_extension_deprecation_NameTick] = "silver:definition:env:config";
		silver.extension.deprecation.NNameTick.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.deprecation.NNameTick.occurs_inh[silver.extension.deprecation.Init.silver_definition_core_grammarName__ON__silver_extension_deprecation_NameTick] = "silver:definition:core:grammarName";
		silver.extension.deprecation.NNameTick.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.extension.deprecation.NNameTick.occurs_syn[silver.extension.deprecation.Init.silver_definition_env_pp__ON__silver_extension_deprecation_NameTick] = "silver:definition:env:pp";
		silver.extension.deprecation.NNameTick.occurs_syn[silver.extension.deprecation.Init.silver_definition_core_name__ON__silver_extension_deprecation_NameTick] = "silver:definition:core:name";
		silver.extension.deprecation.NNameTickTick.occurs_inh[silver.extension.deprecation.Init.silver_definition_env_config__ON__silver_extension_deprecation_NameTickTick] = "silver:definition:env:config";
		silver.extension.deprecation.NNameTickTick.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.deprecation.NNameTickTick.occurs_inh[silver.extension.deprecation.Init.silver_definition_core_grammarName__ON__silver_extension_deprecation_NameTickTick] = "silver:definition:core:grammarName";
		silver.extension.deprecation.NNameTickTick.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.extension.deprecation.NNameTickTick.occurs_syn[silver.extension.deprecation.Init.silver_definition_env_pp__ON__silver_extension_deprecation_NameTickTick] = "silver:definition:env:pp";
		silver.extension.deprecation.NNameTickTick.occurs_syn[silver.extension.deprecation.Init.silver_definition_core_name__ON__silver_extension_deprecation_NameTickTick] = "silver:definition:core:name";
	}

	private static void initProductionAttributeDefinitions(){
		silver.extension.deprecation.PbuildsStmt.initProductionAttributeDefinitions();
		silver.extension.deprecation.PconcreteDecorateExpr.initProductionAttributeDefinitions();
		silver.extension.deprecation.PconcreteDontDecorateExpr.initProductionAttributeDefinitions();
		silver.extension.deprecation.PnameIdTick.initProductionAttributeDefinitions();
		silver.extension.deprecation.PnameIdTickTick.initProductionAttributeDefinitions();
		silver.extension.deprecation.PdeprecatedDecl.initProductionAttributeDefinitions();
		silver.extension.deprecation.PemptyProductionBodySemi.initProductionAttributeDefinitions();
		silver.extension.deprecation.PprodTypeExpr.initProductionAttributeDefinitions();
		silver.extension.deprecation.PfunTypeLegacy.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_extension_deprecation_buildsStmt = 0;
	public static int count_local__ON__silver_extension_deprecation_concreteDecorateExpr = 0;
	public static int count_local__ON__silver_extension_deprecation_concreteDontDecorateExpr = 0;
	public static int count_inh__ON__NameTick = 0;
	public static int count_syn__ON__NameTick = 0;
	public static int count_inh__ON__NameTickTick = 0;
	public static int count_syn__ON__NameTickTick = 0;
	public static int count_local__ON__silver_extension_deprecation_nameIdTick = 0;
	public static int count_local__ON__silver_extension_deprecation_nameIdTickTick = 0;
	public static int count_local__ON__silver_extension_deprecation_deprecatedDecl = 0;
	public static int count_local__ON__silver_extension_deprecation_emptyProductionBodySemi = 0;
	public static int count_local__ON__silver_extension_deprecation_prodTypeExpr = 0;
	public static int count_local__ON__silver_extension_deprecation_funTypeLegacy = 0;
public static final int silver_definition_env_config__ON__silver_extension_deprecation_NameTick = silver.extension.deprecation.Init.count_inh__ON__NameTick++;
public static final int silver_definition_core_grammarName__ON__silver_extension_deprecation_NameTick = silver.extension.deprecation.Init.count_inh__ON__NameTick++;
public static final int silver_definition_env_pp__ON__silver_extension_deprecation_NameTick = silver.extension.deprecation.Init.count_syn__ON__NameTick++;
public static final int silver_definition_core_name__ON__silver_extension_deprecation_NameTick = silver.extension.deprecation.Init.count_syn__ON__NameTick++;
public static final int silver_definition_env_config__ON__silver_extension_deprecation_NameTickTick = silver.extension.deprecation.Init.count_inh__ON__NameTickTick++;
public static final int silver_definition_core_grammarName__ON__silver_extension_deprecation_NameTickTick = silver.extension.deprecation.Init.count_inh__ON__NameTickTick++;
public static final int silver_definition_env_pp__ON__silver_extension_deprecation_NameTickTick = silver.extension.deprecation.Init.count_syn__ON__NameTickTick++;
public static final int silver_definition_core_name__ON__silver_extension_deprecation_NameTickTick = silver.extension.deprecation.Init.count_syn__ON__NameTickTick++;
}
