package silver.modification.collection.env_parser;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		silver.definition.flow.env_parser.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.env_parser.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.modification.collection.Init.initAllStatics();
		silver.modification.collection.env_parser.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		silver.definition.flow.env_parser.Init.init();
		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.core.Init.init();
		silver.definition.env.env_parser.Init.init();
		silver.definition.env.Init.init();
		silver.modification.collection.Init.init();
		silver.modification.collection.env_parser.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		silver.definition.flow.env_parser.Init.postInit();
		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.env.env_parser.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.modification.collection.Init.postInit();
		silver.modification.collection.env_parser.Init.postInit();


		common.Decorator.applyDecorators(silver.modification.collection.env_parser.NIOperation.decorators, PoperationNameFun.class);
		common.Decorator.applyDecorators(silver.modification.collection.env_parser.NIOperation.decorators, PoperationNameProd.class);
		common.Decorator.applyDecorators(silver.modification.collection.env_parser.NIOperation.decorators, PoperationPlusPlusStr.class);
		common.Decorator.applyDecorators(silver.modification.collection.env_parser.NIOperation.decorators, PoperationPlusPlusLst.class);
		common.Decorator.applyDecorators(silver.modification.collection.env_parser.NIOperation.decorators, PoperationBOr.class);
		common.Decorator.applyDecorators(silver.modification.collection.env_parser.NIOperation.decorators, PoperationBAnd.class);
		common.Decorator.applyDecorators(silver.definition.env.env_parser.NIDclInfo.decorators, PaDclInfoSynthesizedCol.class);
		common.Decorator.applyDecorators(silver.definition.env.env_parser.NIDclInfo.decorators, PaDclInfoInheritedCol.class);
		common.Decorator.applyDecorators(silver.definition.env.env_parser.NIDclInfo.decorators, PaDclInfoLocalCol.class);
	}

	private static void setupInheritedAttributes(){
		silver.modification.collection.env_parser.NIOperation.occurs_syn[silver.modification.collection.env_parser.Init.silver_modification_collection_operation__ON__silver_modification_collection_env_parser_IOperation] = "silver:modification:collection:operation";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.collection.env_parser.PoperationNameFun.initProductionAttributeDefinitions();
		silver.modification.collection.env_parser.PoperationNameProd.initProductionAttributeDefinitions();
		silver.modification.collection.env_parser.PoperationPlusPlusStr.initProductionAttributeDefinitions();
		silver.modification.collection.env_parser.PoperationPlusPlusLst.initProductionAttributeDefinitions();
		silver.modification.collection.env_parser.PoperationBOr.initProductionAttributeDefinitions();
		silver.modification.collection.env_parser.PoperationBAnd.initProductionAttributeDefinitions();
		silver.modification.collection.env_parser.PaDclInfoSynthesizedCol.initProductionAttributeDefinitions();
		silver.modification.collection.env_parser.PaDclInfoInheritedCol.initProductionAttributeDefinitions();
		silver.modification.collection.env_parser.PaDclInfoLocalCol.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__IOperation = 0;
	public static int count_syn__ON__IOperation = 0;
	public static int count_local__ON__silver_modification_collection_env_parser_operationNameFun = 0;
	public static int count_local__ON__silver_modification_collection_env_parser_operationNameProd = 0;
	public static int count_local__ON__silver_modification_collection_env_parser_operationPlusPlusStr = 0;
	public static int count_local__ON__silver_modification_collection_env_parser_operationPlusPlusLst = 0;
	public static int count_local__ON__silver_modification_collection_env_parser_operationBOr = 0;
	public static int count_local__ON__silver_modification_collection_env_parser_operationBAnd = 0;
	public static int count_local__ON__silver_modification_collection_env_parser_aDclInfoSynthesizedCol = 0;
	public static int count_local__ON__silver_modification_collection_env_parser_aDclInfoInheritedCol = 0;
	public static int count_local__ON__silver_modification_collection_env_parser_aDclInfoLocalCol = 0;
public static final int silver_modification_collection_operation__ON__silver_modification_collection_env_parser_IOperation = silver.modification.collection.env_parser.Init.count_syn__ON__IOperation++;
}
