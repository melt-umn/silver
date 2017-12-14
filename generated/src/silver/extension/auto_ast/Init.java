package silver.extension.auto_ast;

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
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.extension.auto_ast.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.extension.auto_ast.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.extension.auto_ast.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PautoAstDcl.class);
	}

	private static void setupInheritedAttributes(){
		//	local attribute vty::Type;
		silver.extension.auto_ast.PautoAstDcl.localInheritedAttributes[silver.extension.auto_ast.Init.vty__ON__silver_extension_auto_ast_autoAstDcl] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.extension.auto_ast.PautoAstDcl.occurs_local[silver.extension.auto_ast.Init.vty__ON__silver_extension_auto_ast_autoAstDcl] = "silver:extension:auto_ast:autoAstDcl:local:vty";
		silver.extension.auto_ast.PautoAstDcl.occurs_local[silver.extension.auto_ast.Init.hasLoc__ON__silver_extension_auto_ast_autoAstDcl] = "silver:extension:auto_ast:autoAstDcl:local:hasLoc";
		silver.extension.auto_ast.PautoAstDcl.occurs_local[silver.extension.auto_ast.Init.elems__ON__silver_extension_auto_ast_autoAstDcl] = "silver:extension:auto_ast:autoAstDcl:local:elems";
		//	local attribute inferredType::Type;
		silver.extension.auto_ast.PautoAstDcl.localInheritedAttributes[silver.extension.auto_ast.Init.inferredType__ON__silver_extension_auto_ast_autoAstDcl] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.extension.auto_ast.PautoAstDcl.occurs_local[silver.extension.auto_ast.Init.inferredType__ON__silver_extension_auto_ast_autoAstDcl] = "silver:extension:auto_ast:autoAstDcl:local:inferredType";
		//	local attribute errCheck1::TypeCheck;
		silver.extension.auto_ast.PautoAstDcl.localInheritedAttributes[silver.extension.auto_ast.Init.errCheck1__ON__silver_extension_auto_ast_autoAstDcl] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.extension.auto_ast.PautoAstDcl.occurs_local[silver.extension.auto_ast.Init.errCheck1__ON__silver_extension_auto_ast_autoAstDcl] = "silver:extension:auto_ast:autoAstDcl:local:errCheck1";
		//	local attribute lhsQName::QName;
		silver.extension.auto_ast.PautoAstDcl.localInheritedAttributes[silver.extension.auto_ast.Init.lhsQName__ON__silver_extension_auto_ast_autoAstDcl] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
		silver.extension.auto_ast.PautoAstDcl.occurs_local[silver.extension.auto_ast.Init.lhsQName__ON__silver_extension_auto_ast_autoAstDcl] = "silver:extension:auto_ast:autoAstDcl:local:lhsQName";
		//	local attribute astQName::QNameAttrOccur;
		silver.extension.auto_ast.PautoAstDcl.localInheritedAttributes[silver.extension.auto_ast.Init.astQName__ON__silver_extension_auto_ast_autoAstDcl] = new common.Lazy[silver.definition.core.NQNameAttrOccur.num_inh_attrs];
		silver.extension.auto_ast.PautoAstDcl.occurs_local[silver.extension.auto_ast.Init.astQName__ON__silver_extension_auto_ast_autoAstDcl] = "silver:extension:auto_ast:autoAstDcl:local:astQName";
		silver.extension.auto_ast.PastType.occurs_local[silver.extension.auto_ast.Init.occursCheck__ON__silver_extension_auto_ast_astType] = "silver:extension:auto_ast:astType:local:occursCheck";
	}

	private static void initProductionAttributeDefinitions(){
		silver.extension.auto_ast.PautoAstDcl.initProductionAttributeDefinitions();
		//FUNCTION hasAst Boolean ::= ns::NamedSignatureElement env::Decorated Env 
		//FUNCTION astType Type ::= ns::NamedSignatureElement env::Decorated Env 
		// occursCheck = getOccursDcl("silver:langutil:ast", ns.typerep.typeName, env)
		silver.extension.auto_ast.PastType.localAttributes[silver.extension.auto_ast.Init.occursCheck__ON__silver_extension_auto_ast_astType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetOccursDcl.invoke((new common.StringCatter("silver:langutil:ast")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.childDecorated(silver.extension.auto_ast.PastType.i_ns).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.childAsIsLazy(silver.extension.auto_ast.PastType.i_env))); } };
		//FUNCTION accessAst Expr ::= ns::NamedSignatureElement l::Location 
	}

	public static int count_local__ON__silver_extension_auto_ast_autoAstDcl = 0;
	public static int count_local__ON__silver_extension_auto_ast_hasAst = 0;
	public static int count_local__ON__silver_extension_auto_ast_astType = 0;
	public static int count_local__ON__silver_extension_auto_ast_accessAst = 0;
public static final int vty__ON__silver_extension_auto_ast_autoAstDcl = silver.extension.auto_ast.Init.count_local__ON__silver_extension_auto_ast_autoAstDcl++;
public static final int hasLoc__ON__silver_extension_auto_ast_autoAstDcl = silver.extension.auto_ast.Init.count_local__ON__silver_extension_auto_ast_autoAstDcl++;
public static final int elems__ON__silver_extension_auto_ast_autoAstDcl = silver.extension.auto_ast.Init.count_local__ON__silver_extension_auto_ast_autoAstDcl++;
public static final int inferredType__ON__silver_extension_auto_ast_autoAstDcl = silver.extension.auto_ast.Init.count_local__ON__silver_extension_auto_ast_autoAstDcl++;
public static final int errCheck1__ON__silver_extension_auto_ast_autoAstDcl = silver.extension.auto_ast.Init.count_local__ON__silver_extension_auto_ast_autoAstDcl++;
public static final int lhsQName__ON__silver_extension_auto_ast_autoAstDcl = silver.extension.auto_ast.Init.count_local__ON__silver_extension_auto_ast_autoAstDcl++;
public static final int astQName__ON__silver_extension_auto_ast_autoAstDcl = silver.extension.auto_ast.Init.count_local__ON__silver_extension_auto_ast_autoAstDcl++;
public static final int occursCheck__ON__silver_extension_auto_ast_astType = silver.extension.auto_ast.Init.count_local__ON__silver_extension_auto_ast_astType++;
}
