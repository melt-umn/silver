package origins_test_1;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.extension.bidirtransform.Init.initAllStatics();
		origins_test_1.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.extension.bidirtransform.Init.init();
		origins_test_1.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.extension.bidirtransform.Init.postInit();
		origins_test_1.Init.postInit();


		common.Decorator.applyDecorators(origins_test_1.NRoot_c.decorators, Proot_c.class);
		common.Decorator.applyDecorators(origins_test_1.NExpr_c.decorators, Padd_c.class);
		common.Decorator.applyDecorators(origins_test_1.NExpr_c.decorators, Psub_c.class);
		common.Decorator.applyDecorators(origins_test_1.NExpr_c.decorators, PexprTerm_c.class);
		common.Decorator.applyDecorators(origins_test_1.NTerm_c.decorators, Pmul_c.class);
		common.Decorator.applyDecorators(origins_test_1.NTerm_c.decorators, PtermFactor_c.class);
		common.Decorator.applyDecorators(origins_test_1.NFactor_c.decorators, Pnested_c.class);
		common.Decorator.applyDecorators(origins_test_1.NFactor_c.decorators, Pconst_c.class);
		common.Decorator.applyDecorators(origins_test_1.NRoot.decorators, Proot.class);
		common.Decorator.applyDecorators(origins_test_1.NExpr.decorators, Padd.class);
		common.Decorator.applyDecorators(origins_test_1.NExpr.decorators, Psub.class);
		common.Decorator.applyDecorators(origins_test_1.NExpr.decorators, Pmul.class);
		common.Decorator.applyDecorators(origins_test_1.NExpr.decorators, Pconst.class);
		common.Decorator.applyDecorators(origins_test_1.NExpr.decorators, Pdiv.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Root.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Expr.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Factor_c.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Term_c.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Root_c.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Expr_c.class);
	}

	private static void setupInheritedAttributes(){
		origins_test_1.NExpr_c.occurs_syn[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Expr_c] = "origins_test_1:pp";
		origins_test_1.NExpr_c.occurs_syn[origins_test_1.Init.origins_test_1_ast_Expr__ON__origins_test_1_Expr_c] = "origins_test_1:ast_Expr";
		origins_test_1.NTerm_c.occurs_syn[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Term_c] = "origins_test_1:pp";
		origins_test_1.NTerm_c.occurs_syn[origins_test_1.Init.origins_test_1_ast_Expr__ON__origins_test_1_Term_c] = "origins_test_1:ast_Expr";
		origins_test_1.NFactor_c.occurs_syn[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Factor_c] = "origins_test_1:pp";
		origins_test_1.NFactor_c.occurs_syn[origins_test_1.Init.origins_test_1_ast_Expr__ON__origins_test_1_Factor_c] = "origins_test_1:ast_Expr";
		origins_test_1.NRoot_c.occurs_syn[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Root_c] = "origins_test_1:pp";
		origins_test_1.NRoot_c.occurs_syn[origins_test_1.Init.origins_test_1_ast_Root__ON__origins_test_1_Root_c] = "origins_test_1:ast_Root";
		origins_test_1.NRoot.occurs_syn[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Root] = "origins_test_1:pp";
		origins_test_1.NRoot.occurs_syn[origins_test_1.Init.origins_test_1_value__ON__origins_test_1_Root] = "origins_test_1:value";
		origins_test_1.NExpr.occurs_syn[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Expr] = "origins_test_1:pp";
		origins_test_1.NExpr.occurs_syn[origins_test_1.Init.origins_test_1_value__ON__origins_test_1_Expr] = "origins_test_1:value";
		origins_test_1.Pmain.occurs_local[origins_test_1.Init.args__ON__origins_test_1_main] = "origins_test_1:main:local:args";
		//	local attribute result::ParseResult<Root_c>;
		origins_test_1.Pmain.localInheritedAttributes[origins_test_1.Init.result__ON__origins_test_1_main] = new common.Lazy[core.NParseResult.num_inh_attrs];
		origins_test_1.Pmain.occurs_local[origins_test_1.Init.result__ON__origins_test_1_main] = "origins_test_1:main:local:result";
		//	local attribute r_cst::Root_c;
		origins_test_1.Pmain.localInheritedAttributes[origins_test_1.Init.r_cst__ON__origins_test_1_main] = new common.Lazy[origins_test_1.NRoot_c.num_inh_attrs];
		origins_test_1.Pmain.occurs_local[origins_test_1.Init.r_cst__ON__origins_test_1_main] = "origins_test_1:main:local:r_cst";
		//	local attribute r_ast::Root;
		origins_test_1.Pmain.localInheritedAttributes[origins_test_1.Init.r_ast__ON__origins_test_1_main] = new common.Lazy[origins_test_1.NRoot.num_inh_attrs];
		origins_test_1.Pmain.occurs_local[origins_test_1.Init.r_ast__ON__origins_test_1_main] = "origins_test_1:main:local:r_ast";
		origins_test_1.Pmain.occurs_local[origins_test_1.Init.r_ast_origin_pp__ON__origins_test_1_main] = "origins_test_1:main:local:r_ast_origin_pp";
		origins_test_1.Pmain.occurs_local[origins_test_1.Init.print_success__ON__origins_test_1_main] = "origins_test_1:main:local:print_success";
		origins_test_1.Pmain.occurs_local[origins_test_1.Init.print_failure__ON__origins_test_1_main] = "origins_test_1:main:local:print_failure";
		silver.extension.bidirtransform.NOrigin.occurs_syn[origins_test_1.Init.origins_test_1_pp__ON__silver_extension_bidirtransform_Origin] = "origins_test_1:pp";
		origins_test_1.NExpr.occurs_syn[origins_test_1.Init.silver_extension_bidirtransform_wasTransformed__ON__origins_test_1_Expr] = "silver:extension:bidirtransform:wasTransformed";
		origins_test_1.NRoot.occurs_syn[origins_test_1.Init.silver_extension_bidirtransform_wasTransformed__ON__origins_test_1_Root] = "silver:extension:bidirtransform:wasTransformed";
		origins_test_1.NExpr_c.occurs_syn[origins_test_1.Init.silver_extension_bidirtransform_suppliedOrigin__ON__origins_test_1_Expr_c] = "silver:extension:bidirtransform:suppliedOrigin";
		origins_test_1.NRoot_c.occurs_syn[origins_test_1.Init.silver_extension_bidirtransform_suppliedOrigin__ON__origins_test_1_Root_c] = "silver:extension:bidirtransform:suppliedOrigin";
		origins_test_1.NTerm_c.occurs_syn[origins_test_1.Init.silver_extension_bidirtransform_suppliedOrigin__ON__origins_test_1_Term_c] = "silver:extension:bidirtransform:suppliedOrigin";
		origins_test_1.NFactor_c.occurs_syn[origins_test_1.Init.silver_extension_bidirtransform_suppliedOrigin__ON__origins_test_1_Factor_c] = "silver:extension:bidirtransform:suppliedOrigin";
	}

	private static void initProductionAttributeDefinitions(){
		origins_test_1.Proot_c.initProductionAttributeDefinitions();
		origins_test_1.Padd_c.initProductionAttributeDefinitions();
		origins_test_1.Psub_c.initProductionAttributeDefinitions();
		origins_test_1.PexprTerm_c.initProductionAttributeDefinitions();
		origins_test_1.Pmul_c.initProductionAttributeDefinitions();
		origins_test_1.PtermFactor_c.initProductionAttributeDefinitions();
		origins_test_1.Pnested_c.initProductionAttributeDefinitions();
		origins_test_1.Pconst_c.initProductionAttributeDefinitions();
		origins_test_1.Proot.initProductionAttributeDefinitions();
		origins_test_1.Padd.initProductionAttributeDefinitions();
		origins_test_1.Psub.initProductionAttributeDefinitions();
		origins_test_1.Pmul.initProductionAttributeDefinitions();
		origins_test_1.Pconst.initProductionAttributeDefinitions();
		origins_test_1.Pdiv.initProductionAttributeDefinitions();
		//FUNCTION main IOVal<Integer> ::= largs::[String] ioin::IO 
		// args = implode(" ", largs)
		origins_test_1.Pmain.localAttributes[origins_test_1.Init.args__ON__origins_test_1_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(" ")), context.childAsIsLazy(origins_test_1.Pmain.i_largs))); } };
		// result = parse(args, "<<args>>")
		origins_test_1.Pmain.localAttributes[origins_test_1.Init.result__ON__origins_test_1_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NParseResult)origins_test_1.Pparse.invoke(context.localAsIsLazy(origins_test_1.Init.args__ON__origins_test_1_main), (new common.StringCatter("<<args>>")))); } };
		// r_cst = result.parseTree
		origins_test_1.Pmain.localAttributes[origins_test_1.Init.r_cst__ON__origins_test_1_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((origins_test_1.NRoot_c)context.localDecorated(origins_test_1.Init.result__ON__origins_test_1_main).synthesized(core.Init.core_parseTree__ON__core_ParseResult)); } };
		// r_ast = r_cst.ast_Root
		origins_test_1.Pmain.localAttributes[origins_test_1.Init.r_ast__ON__origins_test_1_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((origins_test_1.NRoot)context.localDecorated(origins_test_1.Init.r_cst__ON__origins_test_1_main).synthesized(origins_test_1.Init.origins_test_1_ast_Root__ON__origins_test_1_Root_c)); } };
		// r_ast_origin_pp = r_ast.origin.concreteOrigin.pp
		origins_test_1.Pmain.localAttributes[origins_test_1.Init.r_ast_origin_pp__ON__origins_test_1_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.extension.bidirtransform.NOrigin)((silver.extension.bidirtransform.NOrigin)((origins_test_1.NRoot)context.localDecorated(origins_test_1.Init.r_ast__ON__origins_test_1_main).undecorate()).getAnno_silver_extension_bidirtransform_origin()).decorate(context, (common.Lazy[])null).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin)).decorate(context, (common.Lazy[])null).synthesized(origins_test_1.Init.origins_test_1_pp__ON__silver_extension_bidirtransform_Origin)); } };
		// print_success = print("Command line expression: " ++ args ++ "\n\n" ++ "CST pretty print: " ++ r_cst.pp ++ "\n\n" ++ "AST pretty print: " ++ r_ast.pp ++ "\n\n" ++ "AST Origin pretty print: " ++ r_ast_origin_pp ++ "\n\n", ioin)
		origins_test_1.Pmain.localAttributes[origins_test_1.Init.print_success__ON__origins_test_1_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Command line expression: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(origins_test_1.Init.args__ON__origins_test_1_main)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("CST pretty print: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(origins_test_1.Init.r_cst__ON__origins_test_1_main).synthesized(origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Root_c)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("AST pretty print: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(origins_test_1.Init.r_ast__ON__origins_test_1_main).synthesized(origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Root)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("AST Origin pretty print: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(origins_test_1.Init.r_ast_origin_pp__ON__origins_test_1_main)), (common.StringCatter)(new common.StringCatter("\n\n"))))))))))))); } }, context.childAsIsLazy(origins_test_1.Pmain.i_ioin))); } };
		// print_failure = print("Encountered a parse error:\n" ++ result.parseErrors ++ "\n", ioin)
		origins_test_1.Pmain.localAttributes[origins_test_1.Init.print_failure__ON__origins_test_1_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Encountered a parse error:\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(origins_test_1.Init.result__ON__origins_test_1_main).synthesized(core.Init.core_parseErrors__ON__core_ParseResult)), (common.StringCatter)(new common.StringCatter("\n")))); } }, context.childAsIsLazy(origins_test_1.Pmain.i_ioin))); } };
		//ASPECT PRODUCTION origin_Root o ::= e::Decorated Root 
		// o.concreteOrigin = getConcreteOrigin(e.origin, o)
		origins_test_1.Porigin_Root.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)silver.extension.bidirtransform.PgetConcreteOrigin.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((origins_test_1.NRoot)((common.DecoratedNode)context.childAsIs(origins_test_1.Porigin_Root.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, context.undecorate())); } };
		// o.wasTransformed = wasTransformed(e.origin, e.redex)
		origins_test_1.Porigin_Root.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)silver.extension.bidirtransform.PwasTransformed.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((origins_test_1.NRoot)((common.DecoratedNode)context.childAsIs(origins_test_1.Porigin_Root.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)((origins_test_1.NRoot)((common.DecoratedNode)context.childAsIs(origins_test_1.Porigin_Root.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_redex()); } })); } };
		//ASPECT PRODUCTION origin_Expr o ::= e::Decorated Expr 
		// o.concreteOrigin = getConcreteOrigin(e.origin, o)
		origins_test_1.Porigin_Expr.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)silver.extension.bidirtransform.PgetConcreteOrigin.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((origins_test_1.NExpr)((common.DecoratedNode)context.childAsIs(origins_test_1.Porigin_Expr.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, context.undecorate())); } };
		// o.wasTransformed = wasTransformed(e.origin, e.redex)
		origins_test_1.Porigin_Expr.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)silver.extension.bidirtransform.PwasTransformed.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((origins_test_1.NExpr)((common.DecoratedNode)context.childAsIs(origins_test_1.Porigin_Expr.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)((origins_test_1.NExpr)((common.DecoratedNode)context.childAsIs(origins_test_1.Porigin_Expr.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_redex()); } })); } };
		origins_test_1.Porigin_Root.initProductionAttributeDefinitions();
		origins_test_1.Porigin_Expr.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION origin_Factor_c o ::= e::Decorated Factor_c 
		// o.concreteOrigin = o
		origins_test_1.Porigin_Factor_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// o.wasTransformed = false
		origins_test_1.Porigin_Factor_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		//ASPECT PRODUCTION origin_Term_c o ::= e::Decorated Term_c 
		// o.concreteOrigin = o
		origins_test_1.Porigin_Term_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// o.wasTransformed = false
		origins_test_1.Porigin_Term_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		//ASPECT PRODUCTION origin_Root_c o ::= e::Decorated Root_c 
		// o.concreteOrigin = o
		origins_test_1.Porigin_Root_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// o.wasTransformed = false
		origins_test_1.Porigin_Root_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		//ASPECT PRODUCTION origin_Expr_c o ::= e::Decorated Expr_c 
		// o.concreteOrigin = o
		origins_test_1.Porigin_Expr_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// o.wasTransformed = false
		origins_test_1.Porigin_Expr_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		origins_test_1.Porigin_Factor_c.initProductionAttributeDefinitions();
		origins_test_1.Porigin_Term_c.initProductionAttributeDefinitions();
		origins_test_1.Porigin_Root_c.initProductionAttributeDefinitions();
		origins_test_1.Porigin_Expr_c.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__Expr_c = 0;
	public static int count_syn__ON__Expr_c = 0;
	public static int count_inh__ON__Term_c = 0;
	public static int count_syn__ON__Term_c = 0;
	public static int count_inh__ON__Factor_c = 0;
	public static int count_syn__ON__Factor_c = 0;
	public static int count_inh__ON__Root_c = 0;
	public static int count_syn__ON__Root_c = 0;
	public static int count_local__ON__origins_test_1_root_c = 0;
	public static int count_local__ON__origins_test_1_add_c = 0;
	public static int count_local__ON__origins_test_1_sub_c = 0;
	public static int count_local__ON__origins_test_1_exprTerm_c = 0;
	public static int count_local__ON__origins_test_1_mul_c = 0;
	public static int count_local__ON__origins_test_1_termFactor_c = 0;
	public static int count_local__ON__origins_test_1_nested_c = 0;
	public static int count_local__ON__origins_test_1_const_c = 0;
	public static int count_inh__ON__Root = 0;
	public static int count_syn__ON__Root = 0;
	public static int count_inh__ON__Expr = 0;
	public static int count_syn__ON__Expr = 0;
	public static int count_local__ON__origins_test_1_root = 0;
	public static int count_local__ON__origins_test_1_add = 0;
	public static int count_local__ON__origins_test_1_sub = 0;
	public static int count_local__ON__origins_test_1_mul = 0;
	public static int count_local__ON__origins_test_1_const = 0;
	public static int count_local__ON__origins_test_1_div = 0;
	public static int count_local__ON__origins_test_1_parse = 0;
	public static int count_local__ON__origins_test_1_main = 0;
	public static int count_local__ON__origins_test_1_origin_Root = 0;
	public static int count_local__ON__origins_test_1_origin_Expr = 0;
	public static int count_local__ON__origins_test_1_origin_Factor_c = 0;
	public static int count_local__ON__origins_test_1_origin_Term_c = 0;
	public static int count_local__ON__origins_test_1_origin_Root_c = 0;
	public static int count_local__ON__origins_test_1_origin_Expr_c = 0;
public static final int origins_test_1_pp__ON__origins_test_1_Expr_c = origins_test_1.Init.count_syn__ON__Expr_c++;
public static final int origins_test_1_ast_Expr__ON__origins_test_1_Expr_c = origins_test_1.Init.count_syn__ON__Expr_c++;
public static final int origins_test_1_pp__ON__origins_test_1_Term_c = origins_test_1.Init.count_syn__ON__Term_c++;
public static final int origins_test_1_ast_Expr__ON__origins_test_1_Term_c = origins_test_1.Init.count_syn__ON__Term_c++;
public static final int origins_test_1_pp__ON__origins_test_1_Factor_c = origins_test_1.Init.count_syn__ON__Factor_c++;
public static final int origins_test_1_ast_Expr__ON__origins_test_1_Factor_c = origins_test_1.Init.count_syn__ON__Factor_c++;
public static final int origins_test_1_pp__ON__origins_test_1_Root_c = origins_test_1.Init.count_syn__ON__Root_c++;
public static final int origins_test_1_ast_Root__ON__origins_test_1_Root_c = origins_test_1.Init.count_syn__ON__Root_c++;
public static final int origins_test_1_pp__ON__origins_test_1_Root = origins_test_1.Init.count_syn__ON__Root++;
public static final int origins_test_1_value__ON__origins_test_1_Root = origins_test_1.Init.count_syn__ON__Root++;
public static final int origins_test_1_pp__ON__origins_test_1_Expr = origins_test_1.Init.count_syn__ON__Expr++;
public static final int origins_test_1_value__ON__origins_test_1_Expr = origins_test_1.Init.count_syn__ON__Expr++;
public static final int args__ON__origins_test_1_main = origins_test_1.Init.count_local__ON__origins_test_1_main++;
public static final int result__ON__origins_test_1_main = origins_test_1.Init.count_local__ON__origins_test_1_main++;
public static final int r_cst__ON__origins_test_1_main = origins_test_1.Init.count_local__ON__origins_test_1_main++;
public static final int r_ast__ON__origins_test_1_main = origins_test_1.Init.count_local__ON__origins_test_1_main++;
public static final int r_ast_origin_pp__ON__origins_test_1_main = origins_test_1.Init.count_local__ON__origins_test_1_main++;
public static final int print_success__ON__origins_test_1_main = origins_test_1.Init.count_local__ON__origins_test_1_main++;
public static final int print_failure__ON__origins_test_1_main = origins_test_1.Init.count_local__ON__origins_test_1_main++;
public static final int origins_test_1_pp__ON__silver_extension_bidirtransform_Origin = silver.extension.bidirtransform.Init.count_syn__ON__Origin++;
public static final int silver_extension_bidirtransform_wasTransformed__ON__origins_test_1_Expr = origins_test_1.Init.count_syn__ON__Expr++;
public static final int silver_extension_bidirtransform_wasTransformed__ON__origins_test_1_Root = origins_test_1.Init.count_syn__ON__Root++;
public static final int silver_extension_bidirtransform_suppliedOrigin__ON__origins_test_1_Expr_c = origins_test_1.Init.count_syn__ON__Expr_c++;
public static final int silver_extension_bidirtransform_suppliedOrigin__ON__origins_test_1_Root_c = origins_test_1.Init.count_syn__ON__Root_c++;
public static final int silver_extension_bidirtransform_suppliedOrigin__ON__origins_test_1_Term_c = origins_test_1.Init.count_syn__ON__Term_c++;
public static final int silver_extension_bidirtransform_suppliedOrigin__ON__origins_test_1_Factor_c = origins_test_1.Init.count_syn__ON__Factor_c++;
}
