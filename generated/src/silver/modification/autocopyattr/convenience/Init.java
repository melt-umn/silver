package silver.modification.autocopyattr.convenience;

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
		silver.definition.env.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.extension.convenience.Init.initAllStatics();
		silver.modification.autocopyattr.Init.initAllStatics();
		silver.modification.autocopyattr.convenience.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.env.Init.init();
		silver.definition.type.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.core.Init.init();
		silver.extension.convenience.Init.init();
		silver.modification.autocopyattr.Init.init();
		silver.modification.autocopyattr.convenience.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.extension.convenience.Init.postInit();
		silver.modification.autocopyattr.Init.postInit();
		silver.modification.autocopyattr.convenience.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PattributeDclAutoMultiple.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.autocopyattr.convenience.PattributeDclAutoMultiple.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_modification_autocopyattr_convenience_attributeDclAutoMultiple = 0;
}
