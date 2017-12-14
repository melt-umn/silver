package silver.modification.typedecl;

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
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.modification.typedecl.Init.initAllStatics();

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
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.core.Init.init();
		silver.modification.typedecl.Init.init();

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
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.modification.typedecl.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PtypeDecl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PtypeDcl.class);
	}

	private static void setupInheritedAttributes(){
		silver.modification.typedecl.PtypeDecl.occurs_local[silver.modification.typedecl.Init.fName__ON__silver_modification_typedecl_typeDecl] = "silver:modification:typedecl:typeDecl:local:fName";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.typedecl.PtypeDecl.initProductionAttributeDefinitions();
		//FUNCTION typeAliasDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type 
		silver.modification.typedecl.PtypeDcl.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_modification_typedecl_typeDecl = 0;
	public static int count_local__ON__silver_modification_typedecl_typeAliasDef = 0;
	public static int count_local__ON__silver_modification_typedecl_typeDcl = 0;
public static final int fName__ON__silver_modification_typedecl_typeDecl = silver.modification.typedecl.Init.count_local__ON__silver_modification_typedecl_typeDecl++;
}
