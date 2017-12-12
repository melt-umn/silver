package silver.json;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.json.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.json.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.json.Init.postInit();


		common.Decorator.applyDecorators(silver.json.NJson.decorators, PjsonString.class);
		common.Decorator.applyDecorators(silver.json.NJson.decorators, PjsonFloat.class);
		common.Decorator.applyDecorators(silver.json.NJson.decorators, PjsonArray.class);
		common.Decorator.applyDecorators(silver.json.NJson.decorators, PjsonObject.class);
		common.Decorator.applyDecorators(silver.json.NJson.decorators, PjsonBoolean.class);
		common.Decorator.applyDecorators(silver.json.NJson.decorators, PjsonNull.class);
	}

	private static void setupInheritedAttributes(){
		silver.json.NJson.occurs_syn[silver.json.Init.silver_json_jsonString__ON__silver_json_Json] = "silver:json:jsonString";
		silver.json.PjsonString.occurs_local[silver.json.Init.escapeChar__ON__silver_json_jsonString] = "silver:json:jsonString:local:escapeChar";
		silver.json.PjsonArray.occurs_local[silver.json.Init.strs__ON__silver_json_jsonArray] = "silver:json:jsonArray:local:strs";
		silver.json.PjsonObject.occurs_local[silver.json.Init.strs__ON__silver_json_jsonObject] = "silver:json:jsonObject:local:strs";
	}

	private static void initProductionAttributeDefinitions(){
		silver.json.PjsonString.initProductionAttributeDefinitions();
		//FUNCTION jsonInteger Json ::= int::Integer 
		silver.json.PjsonFloat.initProductionAttributeDefinitions();
		silver.json.PjsonArray.initProductionAttributeDefinitions();
		silver.json.PjsonObject.initProductionAttributeDefinitions();
		silver.json.PjsonBoolean.initProductionAttributeDefinitions();
		silver.json.PjsonNull.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__Json = 0;
	public static int count_syn__ON__Json = 0;
	public static int count_local__ON__silver_json_jsonString = 0;
	public static int count_local__ON__silver_json_jsonInteger = 0;
	public static int count_local__ON__silver_json_jsonFloat = 0;
	public static int count_local__ON__silver_json_jsonArray = 0;
	public static int count_local__ON__silver_json_jsonObject = 0;
	public static int count_local__ON__silver_json_jsonBoolean = 0;
	public static int count_local__ON__silver_json_jsonNull = 0;
public static final int silver_json_jsonString__ON__silver_json_Json = silver.json.Init.count_syn__ON__Json++;
public static final int escapeChar__ON__silver_json_jsonString = silver.json.Init.count_local__ON__silver_json_jsonString++;
public static final int strs__ON__silver_json_jsonArray = silver.json.Init.count_local__ON__silver_json_jsonArray++;
public static final int strs__ON__silver_json_jsonObject = silver.json.Init.count_local__ON__silver_json_jsonObject++;
}
