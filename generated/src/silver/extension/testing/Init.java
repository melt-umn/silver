package silver.extension.testing;

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
		silver.extension.list.Init.initAllStatics();
		silver.modification.collection.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.modification.ffi.Init.initAllStatics();
		silver.extension.testing.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.extension.list.Init.init();
		silver.modification.collection.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		lib.extcore.Init.init();
		silver.modification.ffi.Init.init();
		silver.extension.testing.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.extension.list.Init.postInit();
		silver.modification.collection.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		lib.extcore.Init.postInit();
		silver.modification.ffi.Init.postInit();
		silver.extension.testing.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PmakeTestSuite_p.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PmainTestSuite_p.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PequalityTest2_p.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PwrongDecl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PwrongFlowDecl.class);
	}

	private static void setupInheritedAttributes(){
		//	local attribute sig::ProductionSignature;
		silver.extension.testing.PmakeTestSuite_p.localInheritedAttributes[silver.extension.testing.Init.sig__ON__silver_extension_testing_makeTestSuite_p] = new common.Lazy[silver.definition.core.NProductionSignature.num_inh_attrs];
		silver.extension.testing.PmakeTestSuite_p.occurs_local[silver.extension.testing.Init.sig__ON__silver_extension_testing_makeTestSuite_p] = "silver:extension:testing:makeTestSuite_p:local:sig";
		silver.extension.testing.PmakeTestSuite_p.occurs_local[silver.extension.testing.Init.bod__ON__silver_extension_testing_makeTestSuite_p] = "silver:extension:testing:makeTestSuite_p:local:bod";
		//	local attribute errCheck1::TypeCheck;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.errCheck1__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.errCheck1__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:errCheck1";
		//	local attribute errCheck2::TypeCheck;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.errCheck2__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.errCheck2__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:errCheck2";
		//	local attribute errCheck3::TypeCheck;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.errCheck3__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.errCheck3__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:errCheck3";
		//	local attribute tref::Name;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.tref__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.tref__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:tref";
		//	local attribute testNameref::Name;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.testNameref__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.testNameref__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:testNameref";
		//	local attribute valueref::Name;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.valueref__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.valueref__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:valueref";
		//	local attribute expectedref::Name;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.expectedref__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.expectedref__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:expectedref";
		//	local attribute msgref::Name;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.msgref__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.msgref__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:msgref";
		//	local attribute passref::Name;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.passref__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.passref__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:passref";
		//	local attribute absProdCS::AGDcl;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.absProdCS__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.absProdCS__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:absProdCS";
		//	local attribute aspProdCS::AGDcl;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.aspProdCS__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.aspProdCS__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:aspProdCS";
		//	local attribute equalityTestExpr::Maybe<Expr>;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.equalityTestExpr__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[core.NMaybe.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.equalityTestExpr__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:equalityTestExpr";
		//	local attribute toStringValueExpr::Maybe<Expr>;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.toStringValueExpr__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[core.NMaybe.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.toStringValueExpr__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:toStringValueExpr";
		//	local attribute toStringExpectedExpr::Maybe<Expr>;
		silver.extension.testing.PequalityTest2_p.localInheritedAttributes[silver.extension.testing.Init.toStringExpectedExpr__ON__silver_extension_testing_equalityTest2_p] = new common.Lazy[core.NMaybe.num_inh_attrs];
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.toStringExpectedExpr__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:toStringExpectedExpr";
		silver.extension.testing.PequalityTest2_p.occurs_local[silver.extension.testing.Init.testName__ON__silver_extension_testing_equalityTest2_p] = "silver:extension:testing:equalityTest2_p:local:testName";
	}

	private static void initProductionAttributeDefinitions(){
		silver.extension.testing.PmakeTestSuite_p.initProductionAttributeDefinitions();
		silver.extension.testing.PmainTestSuite_p.initProductionAttributeDefinitions();
		silver.extension.testing.PequalityTest2_p.initProductionAttributeDefinitions();
		//FUNCTION functionNameForBaseTypesCS Maybe<String> ::= valueType::TypeExpr prefixS::String 
		//FUNCTION mkToStringExprCS Maybe<Expr> ::= valueType::TypeExpr exprName::String l::Location 
		//FUNCTION mkEqualityTestExprCS Maybe<Expr> ::= valueType::TypeExpr l::Location 
		silver.extension.testing.PwrongDecl.initProductionAttributeDefinitions();
		silver.extension.testing.PwrongFlowDecl.initProductionAttributeDefinitions();
		//FUNCTION mkNameExpr Expr ::= name::String l::Location 
		//FUNCTION foldStringExprs Expr ::= es::[Expr] 
		//FUNCTION strCnst Expr ::= s::String 
		//FUNCTION attrAcc Expr ::= n::String a::String l::Location 
		//FUNCTION stringifyString String ::= s::String 
	}

	public static int count_local__ON__silver_extension_testing_makeTestSuite_p = 0;
	public static int count_local__ON__silver_extension_testing_mainTestSuite_p = 0;
	public static int count_local__ON__silver_extension_testing_equalityTest2_p = 0;
	public static int count_local__ON__silver_extension_testing_functionNameForBaseTypesCS = 0;
	public static int count_local__ON__silver_extension_testing_mkToStringExprCS = 0;
	public static int count_local__ON__silver_extension_testing_mkEqualityTestExprCS = 0;
	public static int count_local__ON__silver_extension_testing_wrongDecl = 0;
	public static int count_local__ON__silver_extension_testing_wrongFlowDecl = 0;
	public static int count_local__ON__silver_extension_testing_mkNameExpr = 0;
	public static int count_local__ON__silver_extension_testing_foldStringExprs = 0;
	public static int count_local__ON__silver_extension_testing_strCnst = 0;
	public static int count_local__ON__silver_extension_testing_attrAcc = 0;
	public static int count_local__ON__silver_extension_testing_stringifyString = 0;
public static final int sig__ON__silver_extension_testing_makeTestSuite_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_makeTestSuite_p++;
public static final int bod__ON__silver_extension_testing_makeTestSuite_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_makeTestSuite_p++;
public static final int errCheck1__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int errCheck2__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int errCheck3__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int tref__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int testNameref__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int valueref__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int expectedref__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int msgref__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int passref__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int absProdCS__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int aspProdCS__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int equalityTestExpr__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int toStringValueExpr__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int toStringExpectedExpr__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
public static final int testName__ON__silver_extension_testing_equalityTest2_p = silver.extension.testing.Init.count_local__ON__silver_extension_testing_equalityTest2_p++;
}
