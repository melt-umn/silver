package silver.modification.ffi;

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
		silver.modification.typedecl.Init.initAllStatics();
		silver.util.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.modification.ffi.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.modification.typedecl.Init.init();
		silver.util.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.modification.ffi.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.modification.typedecl.Init.postInit();
		silver.util.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.modification.ffi.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PffiTypeDcl.class);
		common.Decorator.applyDecorators(silver.definition.type.NType.decorators, PforeignType.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PfunctionDclFFI.class);
		common.Decorator.applyDecorators(silver.modification.ffi.NFFIDefs.decorators, PffidefsOne.class);
		common.Decorator.applyDecorators(silver.modification.ffi.NFFIDefs.decorators, PffidefsMany.class);
		common.Decorator.applyDecorators(silver.modification.ffi.NFFIDef.decorators, Pffidef.class);
	}

	private static void setupInheritedAttributes(){
		silver.modification.ffi.PffiTypeDcl.occurs_local[silver.modification.ffi.Init.fName__ON__silver_modification_ffi_ffiTypeDcl] = "silver:modification:ffi:ffiTypeDcl:local:fName";
		silver.modification.ffi.NFFIDefs.occurs_inh[silver.modification.ffi.Init.silver_definition_env_config__ON__silver_modification_ffi_FFIDefs] = "silver:definition:env:config";
		silver.modification.ffi.NFFIDefs.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.ffi.NFFIDefs.occurs_inh[silver.modification.ffi.Init.silver_definition_core_grammarName__ON__silver_modification_ffi_FFIDefs] = "silver:definition:core:grammarName";
		silver.modification.ffi.NFFIDefs.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.ffi.NFFIDefs.occurs_syn[silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDefs] = "silver:definition:core:errors";
		silver.modification.ffi.NFFIDefs.occurs_inh[silver.modification.ffi.Init.silver_definition_env_env__ON__silver_modification_ffi_FFIDefs] = "silver:definition:env:env";
		silver.modification.ffi.NFFIDefs.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.ffi.NFFIDefs.occurs_syn[silver.modification.ffi.Init.silver_definition_env_pp__ON__silver_modification_ffi_FFIDefs] = "silver:definition:env:pp";
		silver.modification.ffi.NFFIDef.occurs_inh[silver.modification.ffi.Init.silver_definition_env_config__ON__silver_modification_ffi_FFIDef] = "silver:definition:env:config";
		silver.modification.ffi.NFFIDef.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.ffi.NFFIDef.occurs_inh[silver.modification.ffi.Init.silver_definition_core_grammarName__ON__silver_modification_ffi_FFIDef] = "silver:definition:core:grammarName";
		silver.modification.ffi.NFFIDef.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.ffi.NFFIDef.occurs_syn[silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDef] = "silver:definition:core:errors";
		silver.modification.ffi.NFFIDef.occurs_inh[silver.modification.ffi.Init.silver_definition_env_env__ON__silver_modification_ffi_FFIDef] = "silver:definition:env:env";
		silver.modification.ffi.NFFIDef.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.ffi.NFFIDef.occurs_syn[silver.modification.ffi.Init.silver_definition_env_pp__ON__silver_modification_ffi_FFIDef] = "silver:definition:env:pp";
		silver.modification.ffi.PfunctionDclFFI.occurs_local[silver.modification.ffi.Init.fName__ON__silver_modification_ffi_functionDclFFI] = "silver:modification:ffi:functionDclFFI:local:fName";
		//	local attribute namedSig::NamedSignature;
		silver.modification.ffi.PfunctionDclFFI.localInheritedAttributes[silver.modification.ffi.Init.namedSig__ON__silver_modification_ffi_functionDclFFI] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];
		silver.modification.ffi.PfunctionDclFFI.occurs_local[silver.modification.ffi.Init.namedSig__ON__silver_modification_ffi_functionDclFFI] = "silver:modification:ffi:functionDclFFI:local:namedSig";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.ffi.PffiTypeDcl.initProductionAttributeDefinitions();
		silver.modification.ffi.PforeignType.initProductionAttributeDefinitions();
		silver.modification.ffi.PfunctionDclFFI.initProductionAttributeDefinitions();
		silver.modification.ffi.PffidefsOne.initProductionAttributeDefinitions();
		silver.modification.ffi.PffidefsMany.initProductionAttributeDefinitions();
		silver.modification.ffi.Pffidef.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_modification_ffi_ffiTypeDcl = 0;
	public static int count_local__ON__silver_modification_ffi_foreignType = 0;
	public static int count_inh__ON__FFIDefs = 0;
	public static int count_syn__ON__FFIDefs = 0;
	public static int count_inh__ON__FFIDef = 0;
	public static int count_syn__ON__FFIDef = 0;
	public static int count_local__ON__silver_modification_ffi_functionDclFFI = 0;
	public static int count_local__ON__silver_modification_ffi_ffidefsOne = 0;
	public static int count_local__ON__silver_modification_ffi_ffidefsMany = 0;
	public static int count_local__ON__silver_modification_ffi_ffidef = 0;
public static final int fName__ON__silver_modification_ffi_ffiTypeDcl = silver.modification.ffi.Init.count_local__ON__silver_modification_ffi_ffiTypeDcl++;
public static final int silver_definition_env_config__ON__silver_modification_ffi_FFIDefs = silver.modification.ffi.Init.count_inh__ON__FFIDefs++;
public static final int silver_definition_core_grammarName__ON__silver_modification_ffi_FFIDefs = silver.modification.ffi.Init.count_inh__ON__FFIDefs++;
public static final int silver_definition_core_errors__ON__silver_modification_ffi_FFIDefs = silver.modification.ffi.Init.count_syn__ON__FFIDefs++;
public static final int silver_definition_env_env__ON__silver_modification_ffi_FFIDefs = silver.modification.ffi.Init.count_inh__ON__FFIDefs++;
public static final int silver_definition_env_pp__ON__silver_modification_ffi_FFIDefs = silver.modification.ffi.Init.count_syn__ON__FFIDefs++;
public static final int silver_definition_env_config__ON__silver_modification_ffi_FFIDef = silver.modification.ffi.Init.count_inh__ON__FFIDef++;
public static final int silver_definition_core_grammarName__ON__silver_modification_ffi_FFIDef = silver.modification.ffi.Init.count_inh__ON__FFIDef++;
public static final int silver_definition_core_errors__ON__silver_modification_ffi_FFIDef = silver.modification.ffi.Init.count_syn__ON__FFIDef++;
public static final int silver_definition_env_env__ON__silver_modification_ffi_FFIDef = silver.modification.ffi.Init.count_inh__ON__FFIDef++;
public static final int silver_definition_env_pp__ON__silver_modification_ffi_FFIDef = silver.modification.ffi.Init.count_syn__ON__FFIDef++;
public static final int fName__ON__silver_modification_ffi_functionDclFFI = silver.modification.ffi.Init.count_local__ON__silver_modification_ffi_functionDclFFI++;
public static final int namedSig__ON__silver_modification_ffi_functionDclFFI = silver.modification.ffi.Init.count_local__ON__silver_modification_ffi_functionDclFFI++;
}
