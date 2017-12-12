package silver.composed.Default;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		silver.extension.list.java.Init.initAllStatics();
		silver.modification.let_fix.java.Init.initAllStatics();
		silver.modification.lambda_fn.java.Init.initAllStatics();
		silver.modification.collection.java.Init.initAllStatics();
		silver.modification.autocopyattr.convenience.Init.initAllStatics();
		silver.modification.autocopyattr.java.Init.initAllStatics();
		silver.modification.ffi.java.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.concrete_syntax.ast.env_parser.Init.initAllStatics();
		silver.definition.env.env_parser.Init.initAllStatics();
		silver.extension.doc.driver.Init.initAllStatics();
		silver.extension.doc.core.Init.initAllStatics();
		silver.extension.templating.syntax.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		silver.definition.flow.syntax.Init.initAllStatics();
		silver.definition.regex.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();
		silver.translation.java.driver.Init.initAllStatics();
		silver.translation.java.type.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.extension.list.env_parser.Init.initAllStatics();
		silver.modification.impide.env_parser.Init.initAllStatics();
		silver.modification.copper.env_parser.Init.initAllStatics();
		silver.modification.typedecl.env_parser.Init.initAllStatics();
		silver.modification.ffi.env_parser.Init.initAllStatics();
		silver.modification.autocopyattr.env_parser.Init.initAllStatics();
		silver.modification.collection.env_parser.Init.initAllStatics();
		silver.definition.flow.env_parser.Init.initAllStatics();
		silver.host.env.Init.initAllStatics();
		silver.modification.impide.Init.initAllStatics();
		silver.modification.copper_mda.Init.initAllStatics();
		silver.modification.defaultattr.Init.initAllStatics();
		silver.modification.copper.Init.initAllStatics();
		silver.modification.typedecl.Init.initAllStatics();
		silver.modification.ffi.Init.initAllStatics();
		silver.modification.autocopyattr.Init.initAllStatics();
		silver.modification.primitivepattern.Init.initAllStatics();
		silver.modification.collection.Init.initAllStatics();
		silver.modification.lambda_fn.Init.initAllStatics();
		silver.modification.let_fix.Init.initAllStatics();
		silver.extension.bidirtransform.Init.initAllStatics();
		silver.extension.monad.Init.initAllStatics();
		silver.extension.functorattrib.Init.initAllStatics();
		silver.extension.doc.Init.initAllStatics();
		silver.extension.treegen.Init.initAllStatics();
		silver.extension.patternmatching.Init.initAllStatics();
		silver.extension.templating.Init.initAllStatics();
		silver.extension.auto_ast.Init.initAllStatics();
		silver.extension.testing.Init.initAllStatics();
		silver.extension.deprecation.Init.initAllStatics();
		silver.extension.easyterminal.Init.initAllStatics();
		silver.extension.list.Init.initAllStatics();
		silver.extension.convenience.Init.initAllStatics();
		silver.host.Init.initAllStatics();
		silver.analysis.warnings.exporting.Init.initAllStatics();
		silver.analysis.warnings.defs.Init.initAllStatics();
		silver.driver.Init.initAllStatics();
		silver.translation.java.Init.initAllStatics();
		silver.composed.Default.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		silver.extension.list.java.Init.init();
		silver.modification.let_fix.java.Init.init();
		silver.modification.lambda_fn.java.Init.init();
		silver.modification.collection.java.Init.init();
		silver.modification.autocopyattr.convenience.Init.init();
		silver.modification.autocopyattr.java.Init.init();
		silver.modification.ffi.java.Init.init();
		silver.definition.flow.env.Init.init();
		core.monad.Init.init();
		silver.definition.env.Init.init();
		silver.definition.concrete_syntax.ast.env_parser.Init.init();
		silver.definition.env.env_parser.Init.init();
		silver.extension.doc.driver.Init.init();
		silver.extension.doc.core.Init.init();
		silver.extension.templating.syntax.Init.init();
		silver.analysis.typechecking.core.Init.init();
		silver.definition.flow.syntax.Init.init();
		silver.definition.regex.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.core.Init.init();
		silver.driver.util.Init.init();
		silver.translation.java.driver.Init.init();
		silver.translation.java.type.Init.init();
		silver.translation.java.core.Init.init();
		core.Init.init();
		silver.extension.list.env_parser.Init.init();
		silver.modification.impide.env_parser.Init.init();
		silver.modification.copper.env_parser.Init.init();
		silver.modification.typedecl.env_parser.Init.init();
		silver.modification.ffi.env_parser.Init.init();
		silver.modification.autocopyattr.env_parser.Init.init();
		silver.modification.collection.env_parser.Init.init();
		silver.definition.flow.env_parser.Init.init();
		silver.host.env.Init.init();
		silver.modification.impide.Init.init();
		silver.modification.copper_mda.Init.init();
		silver.modification.defaultattr.Init.init();
		silver.modification.copper.Init.init();
		silver.modification.typedecl.Init.init();
		silver.modification.ffi.Init.init();
		silver.modification.autocopyattr.Init.init();
		silver.modification.primitivepattern.Init.init();
		silver.modification.collection.Init.init();
		silver.modification.lambda_fn.Init.init();
		silver.modification.let_fix.Init.init();
		silver.extension.bidirtransform.Init.init();
		silver.extension.monad.Init.init();
		silver.extension.functorattrib.Init.init();
		silver.extension.doc.Init.init();
		silver.extension.treegen.Init.init();
		silver.extension.patternmatching.Init.init();
		silver.extension.templating.Init.init();
		silver.extension.auto_ast.Init.init();
		silver.extension.testing.Init.init();
		silver.extension.deprecation.Init.init();
		silver.extension.easyterminal.Init.init();
		silver.extension.list.Init.init();
		silver.extension.convenience.Init.init();
		silver.host.Init.init();
		silver.analysis.warnings.exporting.Init.init();
		silver.analysis.warnings.defs.Init.init();
		silver.driver.Init.init();
		silver.translation.java.Init.init();
		silver.composed.Default.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		silver.extension.list.java.Init.postInit();
		silver.modification.let_fix.java.Init.postInit();
		silver.modification.lambda_fn.java.Init.postInit();
		silver.modification.collection.java.Init.postInit();
		silver.modification.autocopyattr.convenience.Init.postInit();
		silver.modification.autocopyattr.java.Init.postInit();
		silver.modification.ffi.java.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		core.monad.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.concrete_syntax.ast.env_parser.Init.postInit();
		silver.definition.env.env_parser.Init.postInit();
		silver.extension.doc.driver.Init.postInit();
		silver.extension.doc.core.Init.postInit();
		silver.extension.templating.syntax.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		silver.definition.flow.syntax.Init.postInit();
		silver.definition.regex.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.driver.util.Init.postInit();
		silver.translation.java.driver.Init.postInit();
		silver.translation.java.type.Init.postInit();
		silver.translation.java.core.Init.postInit();
		core.Init.postInit();
		silver.extension.list.env_parser.Init.postInit();
		silver.modification.impide.env_parser.Init.postInit();
		silver.modification.copper.env_parser.Init.postInit();
		silver.modification.typedecl.env_parser.Init.postInit();
		silver.modification.ffi.env_parser.Init.postInit();
		silver.modification.autocopyattr.env_parser.Init.postInit();
		silver.modification.collection.env_parser.Init.postInit();
		silver.definition.flow.env_parser.Init.postInit();
		silver.host.env.Init.postInit();
		silver.modification.impide.Init.postInit();
		silver.modification.copper_mda.Init.postInit();
		silver.modification.defaultattr.Init.postInit();
		silver.modification.copper.Init.postInit();
		silver.modification.typedecl.Init.postInit();
		silver.modification.ffi.Init.postInit();
		silver.modification.autocopyattr.Init.postInit();
		silver.modification.primitivepattern.Init.postInit();
		silver.modification.collection.Init.postInit();
		silver.modification.lambda_fn.Init.postInit();
		silver.modification.let_fix.Init.postInit();
		silver.extension.bidirtransform.Init.postInit();
		silver.extension.monad.Init.postInit();
		silver.extension.functorattrib.Init.postInit();
		silver.extension.doc.Init.postInit();
		silver.extension.treegen.Init.postInit();
		silver.extension.patternmatching.Init.postInit();
		silver.extension.templating.Init.postInit();
		silver.extension.auto_ast.Init.postInit();
		silver.extension.testing.Init.postInit();
		silver.extension.deprecation.Init.postInit();
		silver.extension.easyterminal.Init.postInit();
		silver.extension.list.Init.postInit();
		silver.extension.convenience.Init.postInit();
		silver.host.Init.postInit();
		silver.analysis.warnings.exporting.Init.postInit();
		silver.analysis.warnings.defs.Init.postInit();
		silver.driver.Init.postInit();
		silver.translation.java.Init.postInit();
		silver.composed.Default.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION main IOVal<Integer> ::= args::[String] ioin::IO 
	}

	public static int count_local__ON__silver_composed_Default_svParse = 0;
	public static int count_local__ON__silver_composed_Default_sviParse = 0;
	public static int count_local__ON__silver_composed_Default_main = 0;
}
