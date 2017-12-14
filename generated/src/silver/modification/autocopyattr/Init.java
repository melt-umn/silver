package silver.modification.autocopyattr;

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
		silver.definition.type.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.util.Init.initAllStatics();
		silver.modification.autocopyattr.Init.initAllStatics();

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
		silver.definition.type.Init.init();
		silver.definition.core.Init.init();
		silver.definition.env.Init.init();
		silver.util.Init.init();
		silver.modification.autocopyattr.Init.init();

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
		silver.definition.type.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.util.Init.postInit();
		silver.modification.autocopyattr.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PattributeDclAuto.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PautocopyDcl.class);
	}

	private static void setupInheritedAttributes(){
		silver.modification.autocopyattr.PattributeDclAuto.occurs_local[silver.modification.autocopyattr.Init.fName__ON__silver_modification_autocopyattr_attributeDclAuto] = "silver:modification:autocopyattr:attributeDclAuto:local:fName";
		silver.definition.env.NDclInfo.occurs_syn[silver.modification.autocopyattr.Init.silver_modification_autocopyattr_isAutocopy__ON__silver_definition_env_DclInfo] = "silver:modification:autocopyattr:isAutocopy";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.autocopyattr.PattributeDclAuto.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for DclInfo
		// top.isAutocopy = false
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.modification.autocopyattr.Init.silver_modification_autocopyattr_isAutocopy__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		silver.modification.autocopyattr.PautocopyDcl.initProductionAttributeDefinitions();
		//FUNCTION autocopyDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type 
	}

	public static int count_local__ON__silver_modification_autocopyattr_attributeDclAuto = 0;
	public static int count_local__ON__silver_modification_autocopyattr_autocopyDcl = 0;
	public static int count_local__ON__silver_modification_autocopyattr_autocopyDef = 0;
public static final int fName__ON__silver_modification_autocopyattr_attributeDclAuto = silver.modification.autocopyattr.Init.count_local__ON__silver_modification_autocopyattr_attributeDclAuto++;
public static final int silver_modification_autocopyattr_isAutocopy__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
}
