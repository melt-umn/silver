package silver.extension.list.java;

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
		silver.translation.java.type.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.extension.list.Init.initAllStatics();
		silver.extension.list.java.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.translation.java.type.Init.init();
		silver.definition.core.Init.init();
		silver.definition.env.Init.init();
		silver.definition.type.Init.init();
		silver.extension.list.Init.init();
		silver.extension.list.java.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.translation.java.type.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.extension.list.Init.postInit();
		silver.extension.list.java.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION listType top ::= el::Type 
		// top.transType = "common.ConsCell"
		silver.extension.list.PlistType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("common.ConsCell")); } };
	}

}
