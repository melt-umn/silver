package silver.modification.defaultattr;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		silver.translation.java.driver.Init.initAllStatics();
		silver.translation.java.type.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.translation.java.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.modification.defaultattr.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.translation.java.driver.Init.init();
		silver.translation.java.type.Init.init();
		silver.translation.java.core.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.translation.java.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.modification.defaultattr.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.translation.java.driver.Init.postInit();
		silver.translation.java.type.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.translation.java.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.modification.defaultattr.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PaspectDefaultProduction.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PdefaultLhsDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NDefLHS.decorators, PdefaultLhsDefLHS.class);
		common.Decorator.applyDecorators(silver.definition.core.NBlockContext.decorators, PdefaultAspectContext.class);
	}

	private static void setupInheritedAttributes(){
		//	local attribute namedSig::NamedSignature;
		silver.modification.defaultattr.PaspectDefaultProduction.localInheritedAttributes[silver.modification.defaultattr.Init.namedSig__ON__silver_modification_defaultattr_aspectDefaultProduction] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];
		silver.modification.defaultattr.PaspectDefaultProduction.occurs_local[silver.modification.defaultattr.Init.namedSig__ON__silver_modification_defaultattr_aspectDefaultProduction] = "silver:modification:defaultattr:aspectDefaultProduction:local:namedSig";
		silver.modification.defaultattr.PaspectDefaultProduction.occurs_local[silver.modification.defaultattr.Init.fakedDefs__ON__silver_modification_defaultattr_aspectDefaultProduction] = "silver:modification:defaultattr:aspectDefaultProduction:local:fakedDefs";
		silver.modification.defaultattr.PaspectDefaultProduction.occurs_local[silver.modification.defaultattr.Init.sigDefs__ON__silver_modification_defaultattr_aspectDefaultProduction] = "silver:modification:defaultattr:aspectDefaultProduction:local:sigDefs";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.defaultattr.PaspectDefaultProduction.initProductionAttributeDefinitions();
		//FUNCTION defaultLhsDef Def ::= sg::String sl::Location fn::String ty::Type 
		silver.modification.defaultattr.PdefaultLhsDcl.initProductionAttributeDefinitions();
		silver.modification.defaultattr.PdefaultLhsDefLHS.initProductionAttributeDefinitions();
		silver.modification.defaultattr.PdefaultAspectContext.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_modification_defaultattr_aspectDefaultProduction = 0;
	public static int count_local__ON__silver_modification_defaultattr_defaultLhsDef = 0;
	public static int count_local__ON__silver_modification_defaultattr_defaultLhsDcl = 0;
	public static int count_local__ON__silver_modification_defaultattr_defaultLhsDefLHS = 0;
	public static int count_local__ON__silver_modification_defaultattr_defaultAspectContext = 0;
public static final int namedSig__ON__silver_modification_defaultattr_aspectDefaultProduction = silver.modification.defaultattr.Init.count_local__ON__silver_modification_defaultattr_aspectDefaultProduction++;
public static final int fakedDefs__ON__silver_modification_defaultattr_aspectDefaultProduction = silver.modification.defaultattr.Init.count_local__ON__silver_modification_defaultattr_aspectDefaultProduction++;
public static final int sigDefs__ON__silver_modification_defaultattr_aspectDefaultProduction = silver.modification.defaultattr.Init.count_local__ON__silver_modification_defaultattr_aspectDefaultProduction++;
}
