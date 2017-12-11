package paper_dc_3;

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
		paper_dc_3.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.extension.bidirtransform.Init.init();
		paper_dc_3.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.extension.bidirtransform.Init.postInit();
		paper_dc_3.Init.postInit();


		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Root_a.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Expr_a.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Factor_c.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Term_c.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Root_c.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, Porigin_Expr_c.class);
		common.Decorator.applyDecorators(paper_dc_3.NRoot_a.decorators, Proot.class);
		common.Decorator.applyDecorators(paper_dc_3.NExpr_a.decorators, Padd.class);
		common.Decorator.applyDecorators(paper_dc_3.NExpr_a.decorators, Psub.class);
		common.Decorator.applyDecorators(paper_dc_3.NExpr_a.decorators, Pmul.class);
		common.Decorator.applyDecorators(paper_dc_3.NExpr_a.decorators, Pconst.class);
		common.Decorator.applyDecorators(paper_dc_3.NExpr_a.decorators, Pneg.class);
		common.Decorator.applyDecorators(paper_dc_3.NRoot_c.decorators, Proot_c.class);
		common.Decorator.applyDecorators(paper_dc_3.NExpr_c.decorators, Padd_c.class);
		common.Decorator.applyDecorators(paper_dc_3.NExpr_c.decorators, Psub_c.class);
		common.Decorator.applyDecorators(paper_dc_3.NExpr_c.decorators, PexprTerm_c.class);
		common.Decorator.applyDecorators(paper_dc_3.NTerm_c.decorators, Pmul_c.class);
		common.Decorator.applyDecorators(paper_dc_3.NTerm_c.decorators, Pneg_c.class);
		common.Decorator.applyDecorators(paper_dc_3.NTerm_c.decorators, PtermFactor_c.class);
		common.Decorator.applyDecorators(paper_dc_3.NFactor_c.decorators, Pnested_c.class);
		common.Decorator.applyDecorators(paper_dc_3.NFactor_c.decorators, Pconst_c.class);
	}

	private static void setupInheritedAttributes(){
		paper_dc_3.NExpr_a.occurs_syn[paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a] = "silver:extension:bidirtransform:wasTransformed";
		paper_dc_3.NRoot_a.occurs_syn[paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Root_a] = "silver:extension:bidirtransform:wasTransformed";
		paper_dc_3.NExpr_c.occurs_syn[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Expr_c] = "silver:extension:bidirtransform:suppliedOrigin";
		paper_dc_3.NRoot_c.occurs_syn[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Root_c] = "silver:extension:bidirtransform:suppliedOrigin";
		paper_dc_3.NTerm_c.occurs_syn[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Term_c] = "silver:extension:bidirtransform:suppliedOrigin";
		paper_dc_3.NFactor_c.occurs_syn[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Factor_c] = "silver:extension:bidirtransform:suppliedOrigin";
		paper_dc_3.NExpr_a.occurs_syn[paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a] = "paper_dc_3:expd";
		paper_dc_3.NRoot_a.occurs_syn[paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Root_a] = "paper_dc_3:expd";
		paper_dc_3.NExpr_a.occurs_syn[paper_dc_3.Init.paper_dc_3_transformed_expd__ON__paper_dc_3_Expr_a] = "paper_dc_3:transformed_expd";
		paper_dc_3.NRoot_a.occurs_syn[paper_dc_3.Init.paper_dc_3_transformed_expd__ON__paper_dc_3_Root_a] = "paper_dc_3:transformed_expd";
		paper_dc_3.NExpr_a.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Factor_c__ON__paper_dc_3_Expr_a] = "paper_dc_3:restored_Factor_c";
		paper_dc_3.NRoot_a.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Factor_c__ON__paper_dc_3_Root_a] = "paper_dc_3:restored_Factor_c";
		silver.extension.bidirtransform.NOrigin.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Factor_c__ON__silver_extension_bidirtransform_Origin] = "paper_dc_3:restored_Factor_c";
		paper_dc_3.NExpr_a.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Term_c__ON__paper_dc_3_Expr_a] = "paper_dc_3:restored_Term_c";
		paper_dc_3.NRoot_a.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Term_c__ON__paper_dc_3_Root_a] = "paper_dc_3:restored_Term_c";
		silver.extension.bidirtransform.NOrigin.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Term_c__ON__silver_extension_bidirtransform_Origin] = "paper_dc_3:restored_Term_c";
		paper_dc_3.NExpr_a.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Root_c__ON__paper_dc_3_Expr_a] = "paper_dc_3:restored_Root_c";
		paper_dc_3.NRoot_a.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Root_c__ON__paper_dc_3_Root_a] = "paper_dc_3:restored_Root_c";
		silver.extension.bidirtransform.NOrigin.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Root_c__ON__silver_extension_bidirtransform_Origin] = "paper_dc_3:restored_Root_c";
		paper_dc_3.NExpr_a.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Expr_c__ON__paper_dc_3_Expr_a] = "paper_dc_3:restored_Expr_c";
		paper_dc_3.NRoot_a.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Expr_c__ON__paper_dc_3_Root_a] = "paper_dc_3:restored_Expr_c";
		silver.extension.bidirtransform.NOrigin.occurs_syn[paper_dc_3.Init.paper_dc_3_restored_Expr_c__ON__silver_extension_bidirtransform_Origin] = "paper_dc_3:restored_Expr_c";
		paper_dc_3.NExpr_c.occurs_inh[paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_c] = "paper_dc_3:inhRedex_expd";
		paper_dc_3.NExpr_c.decorators.add(paper_dc_3.DinhRedex_expd.singleton);
		paper_dc_3.NRoot_c.occurs_inh[paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Root_c] = "paper_dc_3:inhRedex_expd";
		paper_dc_3.NRoot_c.decorators.add(paper_dc_3.DinhRedex_expd.singleton);
		paper_dc_3.NTerm_c.occurs_inh[paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Term_c] = "paper_dc_3:inhRedex_expd";
		paper_dc_3.NTerm_c.decorators.add(paper_dc_3.DinhRedex_expd.singleton);
		paper_dc_3.NFactor_c.occurs_inh[paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Factor_c] = "paper_dc_3:inhRedex_expd";
		paper_dc_3.NFactor_c.decorators.add(paper_dc_3.DinhRedex_expd.singleton);
		paper_dc_3.NExpr_a.occurs_inh[paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a] = "paper_dc_3:inhRedex_expd";
		paper_dc_3.NExpr_a.decorators.add(paper_dc_3.DinhRedex_expd.singleton);
		paper_dc_3.NRoot_a.occurs_inh[paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Root_a] = "paper_dc_3:inhRedex_expd";
		paper_dc_3.NRoot_a.decorators.add(paper_dc_3.DinhRedex_expd.singleton);
		paper_dc_3.NRoot_a.occurs_syn[paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Root_a] = "paper_dc_3:value";
		paper_dc_3.NExpr_a.occurs_syn[paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a] = "paper_dc_3:value";
		paper_dc_3.NExpr_c.occurs_syn[paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Expr_c] = "paper_dc_3:pp";
		paper_dc_3.NExpr_c.occurs_syn[paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Expr_c] = "paper_dc_3:ast_Expr";
		paper_dc_3.NTerm_c.occurs_syn[paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Term_c] = "paper_dc_3:pp";
		paper_dc_3.NTerm_c.occurs_syn[paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Term_c] = "paper_dc_3:ast_Expr";
		paper_dc_3.NFactor_c.occurs_syn[paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Factor_c] = "paper_dc_3:pp";
		paper_dc_3.NFactor_c.occurs_syn[paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Factor_c] = "paper_dc_3:ast_Expr";
		paper_dc_3.NRoot_c.occurs_syn[paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Root_c] = "paper_dc_3:pp";
		paper_dc_3.NRoot_c.occurs_syn[paper_dc_3.Init.paper_dc_3_ast_Root__ON__paper_dc_3_Root_c] = "paper_dc_3:ast_Root";
		paper_dc_3.NRoot_c.occurs_syn[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Root_c] = "paper_dc_3:treepp";
		paper_dc_3.NExpr_c.occurs_syn[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_c] = "paper_dc_3:treepp";
		paper_dc_3.NTerm_c.occurs_syn[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Term_c] = "paper_dc_3:treepp";
		paper_dc_3.NFactor_c.occurs_syn[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Factor_c] = "paper_dc_3:treepp";
		paper_dc_3.NRoot_a.occurs_syn[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Root_a] = "paper_dc_3:treepp";
		paper_dc_3.NExpr_a.occurs_syn[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a] = "paper_dc_3:treepp";
		paper_dc_3.Pmain.occurs_local[paper_dc_3.Init.args__ON__paper_dc_3_main] = "paper_dc_3:main:local:args";
		//	local attribute result::ParseResult<Root_c>;
		paper_dc_3.Pmain.localInheritedAttributes[paper_dc_3.Init.result__ON__paper_dc_3_main] = new common.Lazy[core.NParseResult.num_inh_attrs];
		paper_dc_3.Pmain.occurs_local[paper_dc_3.Init.result__ON__paper_dc_3_main] = "paper_dc_3:main:local:result";
		//	local attribute r_cst::Root_c;
		paper_dc_3.Pmain.localInheritedAttributes[paper_dc_3.Init.r_cst__ON__paper_dc_3_main] = new common.Lazy[paper_dc_3.NRoot_c.num_inh_attrs];
		paper_dc_3.Pmain.occurs_local[paper_dc_3.Init.r_cst__ON__paper_dc_3_main] = "paper_dc_3:main:local:r_cst";
		//	local attribute r_ast::Root_a;
		paper_dc_3.Pmain.localInheritedAttributes[paper_dc_3.Init.r_ast__ON__paper_dc_3_main] = new common.Lazy[paper_dc_3.NRoot_a.num_inh_attrs];
		paper_dc_3.Pmain.occurs_local[paper_dc_3.Init.r_ast__ON__paper_dc_3_main] = "paper_dc_3:main:local:r_ast";
		paper_dc_3.Pmain.occurs_local[paper_dc_3.Init.print_success__ON__paper_dc_3_main] = "paper_dc_3:main:local:print_success";
		paper_dc_3.Pmain.occurs_local[paper_dc_3.Init.print_failure__ON__paper_dc_3_main] = "paper_dc_3:main:local:print_failure";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION origin_Root_a o ::= e::Decorated Root_a 
		// o.concreteOrigin = getConcreteOrigin(e.origin, o)
		paper_dc_3.Porigin_Root_a.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)silver.extension.bidirtransform.PgetConcreteOrigin.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((paper_dc_3.NRoot_a)((common.DecoratedNode)context.childAsIs(paper_dc_3.Porigin_Root_a.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, context.undecorate())); } };
		// o.wasTransformed = wasTransformed(e.origin, e.redex)
		paper_dc_3.Porigin_Root_a.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)silver.extension.bidirtransform.PwasTransformed.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((paper_dc_3.NRoot_a)((common.DecoratedNode)context.childAsIs(paper_dc_3.Porigin_Root_a.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)((paper_dc_3.NRoot_a)((common.DecoratedNode)context.childAsIs(paper_dc_3.Porigin_Root_a.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_redex()); } })); } };
		//ASPECT PRODUCTION origin_Expr_a o ::= e::Decorated Expr_a 
		// o.concreteOrigin = getConcreteOrigin(e.origin, o)
		paper_dc_3.Porigin_Expr_a.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)silver.extension.bidirtransform.PgetConcreteOrigin.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((paper_dc_3.NExpr_a)((common.DecoratedNode)context.childAsIs(paper_dc_3.Porigin_Expr_a.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, context.undecorate())); } };
		// o.wasTransformed = wasTransformed(e.origin, e.redex)
		paper_dc_3.Porigin_Expr_a.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)silver.extension.bidirtransform.PwasTransformed.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((paper_dc_3.NExpr_a)((common.DecoratedNode)context.childAsIs(paper_dc_3.Porigin_Expr_a.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)((paper_dc_3.NExpr_a)((common.DecoratedNode)context.childAsIs(paper_dc_3.Porigin_Expr_a.i_e)).undecorate()).getAnno_silver_extension_bidirtransform_redex()); } })); } };
		paper_dc_3.Porigin_Root_a.initProductionAttributeDefinitions();
		paper_dc_3.Porigin_Expr_a.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION origin_Factor_c o ::= e::Decorated Factor_c 
		// o.concreteOrigin = o
		paper_dc_3.Porigin_Factor_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// o.wasTransformed = false
		paper_dc_3.Porigin_Factor_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		//ASPECT PRODUCTION origin_Term_c o ::= e::Decorated Term_c 
		// o.concreteOrigin = o
		paper_dc_3.Porigin_Term_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// o.wasTransformed = false
		paper_dc_3.Porigin_Term_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		//ASPECT PRODUCTION origin_Root_c o ::= e::Decorated Root_c 
		// o.concreteOrigin = o
		paper_dc_3.Porigin_Root_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// o.wasTransformed = false
		paper_dc_3.Porigin_Root_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		//ASPECT PRODUCTION origin_Expr_c o ::= e::Decorated Expr_c 
		// o.concreteOrigin = o
		paper_dc_3.Porigin_Expr_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// o.wasTransformed = false
		paper_dc_3.Porigin_Expr_c.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		paper_dc_3.Porigin_Factor_c.initProductionAttributeDefinitions();
		paper_dc_3.Porigin_Term_c.initProductionAttributeDefinitions();
		paper_dc_3.Porigin_Root_c.initProductionAttributeDefinitions();
		paper_dc_3.Porigin_Expr_c.initProductionAttributeDefinitions();
		//FAKE ASPECT PRODUCTION paper_dc_3:const_c ic::paper_dc_3:Factor_c ::= l::paper_dc_3:IntLit_t 
		// ic.suppliedOrigin = bottomOrigin()
		paper_dc_3.Pconst_c.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new silver.extension.bidirtransform.PbottomOrigin()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:nested_c e::paper_dc_3:Factor_c ::= _G_2::paper_dc_3:LParen_t inner::paper_dc_3:Expr_c _G_0::paper_dc_3:RParen_t 
		// e.suppliedOrigin = bottomOrigin()
		paper_dc_3.Pnested_c.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new silver.extension.bidirtransform.PbottomOrigin()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:termFactor_c t::paper_dc_3:Term_c ::= f::paper_dc_3:Factor_c 
		// t.suppliedOrigin = bottomOrigin()
		paper_dc_3.PtermFactor_c.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Term_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new silver.extension.bidirtransform.PbottomOrigin()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:neg_c n::paper_dc_3:Term_c ::= _G_1::paper_dc_3:Dash_t f::paper_dc_3:Factor_c 
		// n.suppliedOrigin = bottomOrigin()
		paper_dc_3.Pneg_c.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Term_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new silver.extension.bidirtransform.PbottomOrigin()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:mul_c prd::paper_dc_3:Term_c ::= t::paper_dc_3:Term_c _G_1::paper_dc_3:Star_t f::paper_dc_3:Factor_c 
		// prd.suppliedOrigin = bottomOrigin()
		paper_dc_3.Pmul_c.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Term_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new silver.extension.bidirtransform.PbottomOrigin()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:exprTerm_c e::paper_dc_3:Expr_c ::= t::paper_dc_3:Term_c 
		// e.suppliedOrigin = bottomOrigin()
		paper_dc_3.PexprTerm_c.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Expr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new silver.extension.bidirtransform.PbottomOrigin()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:sub_c dff::paper_dc_3:Expr_c ::= e::paper_dc_3:Expr_c _G_1::paper_dc_3:Dash_t t::paper_dc_3:Term_c 
		// dff.suppliedOrigin = bottomOrigin()
		paper_dc_3.Psub_c.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Expr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new silver.extension.bidirtransform.PbottomOrigin()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:add_c sum::paper_dc_3:Expr_c ::= e::paper_dc_3:Expr_c _G_1::paper_dc_3:Plus_t t::paper_dc_3:Term_c 
		// sum.suppliedOrigin = bottomOrigin()
		paper_dc_3.Padd_c.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Expr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new silver.extension.bidirtransform.PbottomOrigin()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:root_c r::paper_dc_3:Root_c ::= e::paper_dc_3:Expr_c 
		// r.suppliedOrigin = bottomOrigin()
		paper_dc_3.Proot_c.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Root_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new silver.extension.bidirtransform.PbottomOrigin()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:neg p::paper_dc_3:Expr_a ::= ne::paper_dc_3:Expr_a 
		// ne.inhRedex_expd = if p.transformed_expd then just(origin_Expr_a(p)) else nothing()
		paper_dc_3.Pneg.childInheritedAttributes[paper_dc_3.Pneg.i_ne][paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.synthesized(paper_dc_3.Init.paper_dc_3_transformed_expd__ON__paper_dc_3_Expr_a)) ? ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new paper_dc_3.Porigin_Expr_a(context)); } })) : ((core.NMaybe)new core.Pnothing())); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:const q::paper_dc_3:Expr_a ::= m::Integer 
		//FAKE ASPECT PRODUCTION paper_dc_3:mul prd::paper_dc_3:Expr_a ::= l::paper_dc_3:Expr_a rh::paper_dc_3:Expr_a 
		// l.inhRedex_expd = nothing()
		paper_dc_3.Pmul.childInheritedAttributes[paper_dc_3.Pmul.i_l][paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		// rh.inhRedex_expd = nothing()
		paper_dc_3.Pmul.childInheritedAttributes[paper_dc_3.Pmul.i_rh][paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:sub dff::paper_dc_3:Expr_a ::= l::paper_dc_3:Expr_a rs::paper_dc_3:Expr_a 
		// l.inhRedex_expd = nothing()
		paper_dc_3.Psub.childInheritedAttributes[paper_dc_3.Psub.i_l][paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		// rs.inhRedex_expd = nothing()
		paper_dc_3.Psub.childInheritedAttributes[paper_dc_3.Psub.i_rs][paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:add sum::paper_dc_3:Expr_a ::= l::paper_dc_3:Expr_a rhs::paper_dc_3:Expr_a 
		// l.inhRedex_expd = nothing()
		paper_dc_3.Padd.childInheritedAttributes[paper_dc_3.Padd.i_l][paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		// rhs.inhRedex_expd = nothing()
		paper_dc_3.Padd.childInheritedAttributes[paper_dc_3.Padd.i_rhs][paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:root r::paper_dc_3:Root_a ::= e::paper_dc_3:Expr_a 
		// e.inhRedex_expd = nothing()
		paper_dc_3.Proot.childInheritedAttributes[paper_dc_3.Proot.i_e][paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:neg p::paper_dc_3:Expr_a ::= ne::paper_dc_3:Expr_a 
		// p.transformed_expd = case ne of qx -> true | _ -> false end
		paper_dc_3.Pneg.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_transformed_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_13_qx = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return context.childDecorated(paper_dc_3.Pneg.i_ne); } };
return true; } }).eval()); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:neg p::paper_dc_3:Expr_a ::= ne::paper_dc_3:Expr_a 
		// p.expd = if p.transformed_expd then sub(0, ne,, labels=[], redex=p.inhRedex_expd, origin=origin_Expr_a(p)) else paper_dc_3:neg(ne.expd,origin=origin_Expr_a(p), redex=p.inhRedex_expd, labels=[])
		paper_dc_3.Pneg.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.synthesized(paper_dc_3.Init.paper_dc_3_transformed_expd__ON__paper_dc_3_Expr_a)) ? ((paper_dc_3.NExpr_a)new paper_dc_3.Psub(Integer.valueOf((int)0), common.Thunk.transformUndecorate(context.childDecoratedLazy(paper_dc_3.Pneg.i_ne)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new paper_dc_3.Porigin_Expr_a(context)); } }, context.contextInheritedLazy(paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a))) : ((paper_dc_3.NExpr_a)new paper_dc_3.Pneg(context.childDecoratedSynthesizedLazy(paper_dc_3.Pneg.i_ne, paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new paper_dc_3.Porigin_Expr_a(context)); } }, context.contextInheritedLazy(paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a)))); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:const q::paper_dc_3:Expr_a ::= m::Integer 
		// q.expd = paper_dc_3:const(m,origin=origin_Expr_a(q), redex=q.inhRedex_expd, labels=[])
		paper_dc_3.Pconst.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NExpr_a)new paper_dc_3.Pconst(context.childAsIsLazy(paper_dc_3.Pconst.i_m), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new paper_dc_3.Porigin_Expr_a(context)); } }, context.contextInheritedLazy(paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a))); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:mul prd::paper_dc_3:Expr_a ::= l::paper_dc_3:Expr_a rh::paper_dc_3:Expr_a 
		// prd.expd = paper_dc_3:mul(rh.expd, l.expd,origin=origin_Expr_a(prd), redex=prd.inhRedex_expd, labels=[])
		paper_dc_3.Pmul.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NExpr_a)new paper_dc_3.Pmul(context.childDecoratedSynthesizedLazy(paper_dc_3.Pmul.i_rh, paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a), context.childDecoratedSynthesizedLazy(paper_dc_3.Pmul.i_l, paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new paper_dc_3.Porigin_Expr_a(context)); } }, context.contextInheritedLazy(paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a))); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:sub dff::paper_dc_3:Expr_a ::= l::paper_dc_3:Expr_a rs::paper_dc_3:Expr_a 
		// dff.expd = paper_dc_3:sub(rs.expd, l.expd,origin=origin_Expr_a(dff), redex=dff.inhRedex_expd, labels=[])
		paper_dc_3.Psub.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NExpr_a)new paper_dc_3.Psub(context.childDecoratedSynthesizedLazy(paper_dc_3.Psub.i_rs, paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a), context.childDecoratedSynthesizedLazy(paper_dc_3.Psub.i_l, paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new paper_dc_3.Porigin_Expr_a(context)); } }, context.contextInheritedLazy(paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a))); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:add sum::paper_dc_3:Expr_a ::= l::paper_dc_3:Expr_a rhs::paper_dc_3:Expr_a 
		// sum.expd = paper_dc_3:add(rhs.expd, l.expd,origin=origin_Expr_a(sum), redex=sum.inhRedex_expd, labels=[])
		paper_dc_3.Padd.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NExpr_a)new paper_dc_3.Padd(context.childDecoratedSynthesizedLazy(paper_dc_3.Padd.i_rhs, paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a), context.childDecoratedSynthesizedLazy(paper_dc_3.Padd.i_l, paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new paper_dc_3.Porigin_Expr_a(context)); } }, context.contextInheritedLazy(paper_dc_3.Init.paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a))); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:root r::paper_dc_3:Root_a ::= e::paper_dc_3:Expr_a 
		//FAKE ASPECT PRODUCTION paper_dc_3:neg p::paper_dc_3:Expr_a ::= ne::paper_dc_3:Expr_a 
		// p.wasTransformed = wasTransformed(p.origin, p.redex) || ne.wasTransformed
		paper_dc_3.Pneg.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)silver.extension.bidirtransform.PwasTransformed.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((paper_dc_3.NExpr_a)context.undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)((paper_dc_3.NExpr_a)context.undecorate()).getAnno_silver_extension_bidirtransform_redex()); } })) || ((Boolean)context.childDecorated(paper_dc_3.Pneg.i_ne).synthesized(paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a))); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:const q::paper_dc_3:Expr_a ::= m::Integer 
		// q.wasTransformed = wasTransformed(q.origin, q.redex)
		paper_dc_3.Pconst.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)silver.extension.bidirtransform.PwasTransformed.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((paper_dc_3.NExpr_a)context.undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)((paper_dc_3.NExpr_a)context.undecorate()).getAnno_silver_extension_bidirtransform_redex()); } })); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:mul prd::paper_dc_3:Expr_a ::= l::paper_dc_3:Expr_a rh::paper_dc_3:Expr_a 
		// prd.wasTransformed = wasTransformed(prd.origin, prd.redex) || l.wasTransformed || rh.wasTransformed
		paper_dc_3.Pmul.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)silver.extension.bidirtransform.PwasTransformed.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((paper_dc_3.NExpr_a)context.undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)((paper_dc_3.NExpr_a)context.undecorate()).getAnno_silver_extension_bidirtransform_redex()); } })) || ((Boolean)context.childDecorated(paper_dc_3.Pmul.i_l).synthesized(paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a))) || ((Boolean)context.childDecorated(paper_dc_3.Pmul.i_rh).synthesized(paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a))); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:sub dff::paper_dc_3:Expr_a ::= l::paper_dc_3:Expr_a rs::paper_dc_3:Expr_a 
		// dff.wasTransformed = wasTransformed(dff.origin, dff.redex) || l.wasTransformed || rs.wasTransformed
		paper_dc_3.Psub.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)silver.extension.bidirtransform.PwasTransformed.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((paper_dc_3.NExpr_a)context.undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)((paper_dc_3.NExpr_a)context.undecorate()).getAnno_silver_extension_bidirtransform_redex()); } })) || ((Boolean)context.childDecorated(paper_dc_3.Psub.i_l).synthesized(paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a))) || ((Boolean)context.childDecorated(paper_dc_3.Psub.i_rs).synthesized(paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a))); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:add sum::paper_dc_3:Expr_a ::= l::paper_dc_3:Expr_a rhs::paper_dc_3:Expr_a 
		// sum.wasTransformed = wasTransformed(sum.origin, sum.redex) || l.wasTransformed || rhs.wasTransformed
		paper_dc_3.Padd.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)silver.extension.bidirtransform.PwasTransformed.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((paper_dc_3.NExpr_a)context.undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)((paper_dc_3.NExpr_a)context.undecorate()).getAnno_silver_extension_bidirtransform_redex()); } })) || ((Boolean)context.childDecorated(paper_dc_3.Padd.i_l).synthesized(paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a))) || ((Boolean)context.childDecorated(paper_dc_3.Padd.i_rhs).synthesized(paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a))); } };
		//FAKE ASPECT PRODUCTION paper_dc_3:root r::paper_dc_3:Root_a ::= e::paper_dc_3:Expr_a 
		// r.wasTransformed = wasTransformed(r.origin, r.redex) || e.wasTransformed
		paper_dc_3.Proot.synthesizedAttributes[paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Root_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)silver.extension.bidirtransform.PwasTransformed.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)((paper_dc_3.NRoot_a)context.undecorate()).getAnno_silver_extension_bidirtransform_origin()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)((paper_dc_3.NRoot_a)context.undecorate()).getAnno_silver_extension_bidirtransform_redex()); } })) || ((Boolean)context.childDecorated(paper_dc_3.Proot.i_e).synthesized(paper_dc_3.Init.silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a))); } };
		//FAKE ASPECT PRODUCTION origin_Factor_c o ::= e::Decorated Factor_c 
		// o.restored_Factor_c = nested_c(terminal(paper_dc_3:LParen_t, "(", core:loc("??", -1, -1, -1, -1, -1, -1)), e, terminal(paper_dc_3:RParen_t, ")", core:loc("??", -1, -1, -1, -1, -1, -1)))
		paper_dc_3.Porigin_Factor_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_restored_Factor_c__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NFactor_c)new paper_dc_3.Pnested_c(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new paper_dc_3.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.childAsIsLazy(paper_dc_3.Porigin_Factor_c.i_e), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new paper_dc_3.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } })); } };
		//FAKE ASPECT PRODUCTION origin_Term_c o ::= e::Decorated Term_c 
		// o.restored_Factor_c = nested_c(terminal(paper_dc_3:LParen_t, "(", core:loc("??", -1, -1, -1, -1, -1, -1)), o.restored_Expr_c, terminal(paper_dc_3:RParen_t, ")", core:loc("??", -1, -1, -1, -1, -1, -1)))
		paper_dc_3.Porigin_Term_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_restored_Factor_c__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NFactor_c)new paper_dc_3.Pnested_c(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new paper_dc_3.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.contextSynthesizedLazy(paper_dc_3.Init.paper_dc_3_restored_Expr_c__ON__silver_extension_bidirtransform_Origin), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new paper_dc_3.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } })); } };
		//FAKE ASPECT PRODUCTION origin_Root_c o ::= e::Decorated Root_c 
		// o.restored_Factor_c = nested_c(terminal(paper_dc_3:LParen_t, "(", core:loc("??", -1, -1, -1, -1, -1, -1)), o.restored_Expr_c, terminal(paper_dc_3:RParen_t, ")", core:loc("??", -1, -1, -1, -1, -1, -1)))
		paper_dc_3.Porigin_Root_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_restored_Factor_c__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NFactor_c)new paper_dc_3.Pnested_c(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new paper_dc_3.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.contextSynthesizedLazy(paper_dc_3.Init.paper_dc_3_restored_Expr_c__ON__silver_extension_bidirtransform_Origin), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new paper_dc_3.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } })); } };
		//FAKE ASPECT PRODUCTION origin_Expr_c o ::= e::Decorated Expr_c 
		// o.restored_Factor_c = nested_c(terminal(paper_dc_3:LParen_t, "(", core:loc("??", -1, -1, -1, -1, -1, -1)), o.restored_Expr_c, terminal(paper_dc_3:RParen_t, ")", core:loc("??", -1, -1, -1, -1, -1, -1)))
		paper_dc_3.Porigin_Expr_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_restored_Factor_c__ON__silver_extension_bidirtransform_Origin] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NFactor_c)new paper_dc_3.Pnested_c(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new paper_dc_3.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.contextSynthesizedLazy(paper_dc_3.Init.paper_dc_3_restored_Expr_c__ON__silver_extension_bidirtransform_Origin), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new paper_dc_3.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } })); } };
		paper_dc_3.Proot.initProductionAttributeDefinitions();
		paper_dc_3.Padd.initProductionAttributeDefinitions();
		paper_dc_3.Psub.initProductionAttributeDefinitions();
		paper_dc_3.Pmul.initProductionAttributeDefinitions();
		paper_dc_3.Pconst.initProductionAttributeDefinitions();
		paper_dc_3.Pneg.initProductionAttributeDefinitions();
		paper_dc_3.Proot_c.initProductionAttributeDefinitions();
		paper_dc_3.Padd_c.initProductionAttributeDefinitions();
		paper_dc_3.Psub_c.initProductionAttributeDefinitions();
		paper_dc_3.PexprTerm_c.initProductionAttributeDefinitions();
		paper_dc_3.Pmul_c.initProductionAttributeDefinitions();
		paper_dc_3.Pneg_c.initProductionAttributeDefinitions();
		paper_dc_3.PtermFactor_c.initProductionAttributeDefinitions();
		paper_dc_3.Pnested_c.initProductionAttributeDefinitions();
		paper_dc_3.Pconst_c.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION root_c r ::= e::Expr_c 
		// r.treepp = "(root " ++ e.treepp ++ ")"
		paper_dc_3.Proot_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Root_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(root ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Proot_c.i_e).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_c)), (common.StringCatter)(new common.StringCatter(")")))); } };
		//ASPECT PRODUCTION add_c sum ::= e::Expr_c '+' t::Term_c 
		// sum.treepp = "(+ " ++ e.treepp ++ "," ++ t.treepp ++ ")"
		paper_dc_3.Padd_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(+ ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Padd_c.i_e).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_c)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Padd_c.i_t).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Term_c)), (common.StringCatter)(new common.StringCatter(")")))))); } };
		//ASPECT PRODUCTION sub_c dff ::= e::Expr_c '-' t::Term_c 
		// dff.treepp = "(- " ++ e.treepp ++ "," ++ t.treepp ++ ")"
		paper_dc_3.Psub_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(- ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Psub_c.i_e).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_c)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Psub_c.i_t).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Term_c)), (common.StringCatter)(new common.StringCatter(")")))))); } };
		//ASPECT PRODUCTION exprTerm_c e ::= t::Term_c 
		// e.treepp = "(expr " ++ t.treepp ++ ")"
		paper_dc_3.PexprTerm_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(expr ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.PexprTerm_c.i_t).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Term_c)), (common.StringCatter)(new common.StringCatter(")")))); } };
		//ASPECT PRODUCTION mul_c prd ::= t::Term_c '*' f::Factor_c 
		// prd.treepp = "(* " ++ t.treepp ++ "," ++ f.treepp ++ ")"
		paper_dc_3.Pmul_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Term_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(* ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Pmul_c.i_t).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Term_c)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Pmul_c.i_f).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Factor_c)), (common.StringCatter)(new common.StringCatter(")")))))); } };
		//ASPECT PRODUCTION termFactor_c t ::= f::Factor_c 
		// t.treepp = "(term " ++ f.treepp ++ ")"
		paper_dc_3.PtermFactor_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Term_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(term ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.PtermFactor_c.i_f).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Factor_c)), (common.StringCatter)(new common.StringCatter(")")))); } };
		//ASPECT PRODUCTION nested_c e ::= '(' inner::Expr_c ')' 
		// e.treepp = "(nest " ++ inner.treepp ++ ")"
		paper_dc_3.Pnested_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(nest ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Pnested_c.i_inner).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_c)), (common.StringCatter)(new common.StringCatter(")")))); } };
		//ASPECT PRODUCTION const_c ic ::= i::IntLit_t 
		// ic.treepp = "(int " ++ ic.pp ++ ")"
		paper_dc_3.Pconst_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(int ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.synthesized(paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Factor_c)), (common.StringCatter)(new common.StringCatter(")")))); } };
		//ASPECT PRODUCTION root r ::= e::Expr_a 
		// r.treepp = "{root " ++ e.treepp ++ "}"
		paper_dc_3.Proot.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Root_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("{root ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Proot.i_e).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a)), (common.StringCatter)(new common.StringCatter("}")))); } };
		//ASPECT PRODUCTION add sum ::= l::Expr_a r::Expr_a 
		// sum.treepp = "{+ " ++ l.treepp ++ "," ++ r.treepp ++ "}"
		paper_dc_3.Padd.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("{+ ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Padd.i_l).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Padd.i_rhs).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a)), (common.StringCatter)(new common.StringCatter("}")))))); } };
		//ASPECT PRODUCTION sub dff ::= l::Expr_a r::Expr_a 
		// dff.treepp = "{- " ++ l.treepp ++ "," ++ r.treepp ++ "}"
		paper_dc_3.Psub.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("{- ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Psub.i_l).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Psub.i_rs).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a)), (common.StringCatter)(new common.StringCatter("}")))))); } };
		//ASPECT PRODUCTION mul prd ::= l::Expr_a r::Expr_a 
		// prd.treepp = "{* " ++ l.treepp ++ "," ++ r.treepp ++ "}"
		paper_dc_3.Pmul.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("{* ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Pmul.i_l).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Pmul.i_rh).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a)), (common.StringCatter)(new common.StringCatter("}")))))); } };
		//ASPECT PRODUCTION const e ::= m::Integer 
		// e.treepp = "{int " ++ toString(m) ++ "}"
		paper_dc_3.Pconst.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("{int ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childAsIs(paper_dc_3.Pconst.i_m)))), (common.StringCatter)(new common.StringCatter("}")))); } };
		//ASPECT PRODUCTION neg e ::= ne::Expr_a 
		// e.treepp = "{neg " ++ ne.treepp ++ "}"
		paper_dc_3.Pneg.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("{neg ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Pneg.i_ne).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a)), (common.StringCatter)(new common.StringCatter("}")))); } };
		//FUNCTION main IOVal<Integer> ::= largs::[String] ioin::IO 
		// args = implode(" ", largs)
		paper_dc_3.Pmain.localAttributes[paper_dc_3.Init.args__ON__paper_dc_3_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(" ")), context.childAsIsLazy(paper_dc_3.Pmain.i_largs))); } };
		// result = parse(args, "<<args>>")
		paper_dc_3.Pmain.localAttributes[paper_dc_3.Init.result__ON__paper_dc_3_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NParseResult)paper_dc_3.Pparse.invoke(context.localAsIsLazy(paper_dc_3.Init.args__ON__paper_dc_3_main), (new common.StringCatter("<<args>>")))); } };
		// r_cst = result.parseTree
		paper_dc_3.Pmain.localAttributes[paper_dc_3.Init.r_cst__ON__paper_dc_3_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NRoot_c)context.localDecorated(paper_dc_3.Init.result__ON__paper_dc_3_main).synthesized(core.Init.core_parseTree__ON__core_ParseResult)); } };
		// r_ast = r_cst.ast_Root
		paper_dc_3.Pmain.localAttributes[paper_dc_3.Init.r_ast__ON__paper_dc_3_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NRoot_a)context.localDecorated(paper_dc_3.Init.r_cst__ON__paper_dc_3_main).synthesized(paper_dc_3.Init.paper_dc_3_ast_Root__ON__paper_dc_3_Root_c)); } };
		// print_success = print("Command line expression: " ++ args ++ "\n\n" ++ "CST pretty print: " ++ r_cst.pp ++ "\n\n" ++ "Expanded CST pretty print: " ++ r_ast.expd.restored_Expr_c.pp ++ "\n\n" ++ "AST tree print:" ++ r_ast.treepp ++ "\n\n" ++ "Expanded AST tree print:" ++ r_ast.expd.treepp ++ "\n\n" ++ "CST tree print: " ++ r_cst.treepp ++ "\n\n" ++ "Expanded CST tree print: " ++ r_ast.expd.restored_Expr_c.treepp ++ "\n\n" ++ "Value: " ++ toString(r_ast.value) ++ "\n\n", ioin)
		paper_dc_3.Pmain.localAttributes[paper_dc_3.Init.print_success__ON__paper_dc_3_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Command line expression: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(paper_dc_3.Init.args__ON__paper_dc_3_main)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("CST pretty print: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(paper_dc_3.Init.r_cst__ON__paper_dc_3_main).synthesized(paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Root_c)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("Expanded CST pretty print: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((paper_dc_3.NExpr_c)((paper_dc_3.NExpr_a)context.localDecorated(paper_dc_3.Init.r_ast__ON__paper_dc_3_main).synthesized(paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Root_a)).decorate(context, (common.Lazy[])null).synthesized(paper_dc_3.Init.paper_dc_3_restored_Expr_c__ON__paper_dc_3_Expr_a)).decorate(context, (common.Lazy[])null).synthesized(paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Expr_c)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("AST tree print:")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(paper_dc_3.Init.r_ast__ON__paper_dc_3_main).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Root_a)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("Expanded AST tree print:")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((paper_dc_3.NExpr_a)context.localDecorated(paper_dc_3.Init.r_ast__ON__paper_dc_3_main).synthesized(paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Root_a)).decorate(context, (common.Lazy[])null).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_a)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("CST tree print: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(paper_dc_3.Init.r_cst__ON__paper_dc_3_main).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Root_c)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("Expanded CST tree print: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((paper_dc_3.NExpr_c)((paper_dc_3.NExpr_a)context.localDecorated(paper_dc_3.Init.r_ast__ON__paper_dc_3_main).synthesized(paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Root_a)).decorate(context, (common.Lazy[])null).synthesized(paper_dc_3.Init.paper_dc_3_restored_Expr_c__ON__paper_dc_3_Expr_a)).decorate(context, (common.Lazy[])null).synthesized(paper_dc_3.Init.paper_dc_3_treepp__ON__paper_dc_3_Expr_c)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("Value: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.localDecorated(paper_dc_3.Init.r_ast__ON__paper_dc_3_main).synthesized(paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Root_a)))), (common.StringCatter)(new common.StringCatter("\n\n"))))))))))))))))))))))))); } }, context.childAsIsLazy(paper_dc_3.Pmain.i_ioin))); } };
		// print_failure = print("Encountered a parse error:\n" ++ result.parseErrors ++ "\n", ioin)
		paper_dc_3.Pmain.localAttributes[paper_dc_3.Init.print_failure__ON__paper_dc_3_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Encountered a parse error:\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(paper_dc_3.Init.result__ON__paper_dc_3_main).synthesized(core.Init.core_parseErrors__ON__core_ParseResult)), (common.StringCatter)(new common.StringCatter("\n")))); } }, context.childAsIsLazy(paper_dc_3.Pmain.i_ioin))); } };
	}

	public static int count_local__ON__paper_dc_3_origin_Root_a = 0;
	public static int count_local__ON__paper_dc_3_origin_Expr_a = 0;
	public static int count_local__ON__paper_dc_3_origin_Factor_c = 0;
	public static int count_local__ON__paper_dc_3_origin_Term_c = 0;
	public static int count_local__ON__paper_dc_3_origin_Root_c = 0;
	public static int count_local__ON__paper_dc_3_origin_Expr_c = 0;
	public static int count_inh__ON__Root_a = 0;
	public static int count_syn__ON__Root_a = 0;
	public static int count_inh__ON__Expr_a = 0;
	public static int count_syn__ON__Expr_a = 0;
	public static int count_local__ON__paper_dc_3_root = 0;
	public static int count_local__ON__paper_dc_3_add = 0;
	public static int count_local__ON__paper_dc_3_sub = 0;
	public static int count_local__ON__paper_dc_3_mul = 0;
	public static int count_local__ON__paper_dc_3_const = 0;
	public static int count_local__ON__paper_dc_3_neg = 0;
	public static int count_inh__ON__Expr_c = 0;
	public static int count_syn__ON__Expr_c = 0;
	public static int count_inh__ON__Term_c = 0;
	public static int count_syn__ON__Term_c = 0;
	public static int count_inh__ON__Factor_c = 0;
	public static int count_syn__ON__Factor_c = 0;
	public static int count_inh__ON__Root_c = 0;
	public static int count_syn__ON__Root_c = 0;
	public static int count_local__ON__paper_dc_3_root_c = 0;
	public static int count_local__ON__paper_dc_3_add_c = 0;
	public static int count_local__ON__paper_dc_3_sub_c = 0;
	public static int count_local__ON__paper_dc_3_exprTerm_c = 0;
	public static int count_local__ON__paper_dc_3_mul_c = 0;
	public static int count_local__ON__paper_dc_3_neg_c = 0;
	public static int count_local__ON__paper_dc_3_termFactor_c = 0;
	public static int count_local__ON__paper_dc_3_nested_c = 0;
	public static int count_local__ON__paper_dc_3_const_c = 0;
	public static int count_local__ON__paper_dc_3_parse = 0;
	public static int count_local__ON__paper_dc_3_main = 0;
public static final int silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Expr_a = paper_dc_3.Init.count_syn__ON__Expr_a++;
public static final int silver_extension_bidirtransform_wasTransformed__ON__paper_dc_3_Root_a = paper_dc_3.Init.count_syn__ON__Root_a++;
public static final int silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Expr_c = paper_dc_3.Init.count_syn__ON__Expr_c++;
public static final int silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Root_c = paper_dc_3.Init.count_syn__ON__Root_c++;
public static final int silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Term_c = paper_dc_3.Init.count_syn__ON__Term_c++;
public static final int silver_extension_bidirtransform_suppliedOrigin__ON__paper_dc_3_Factor_c = paper_dc_3.Init.count_syn__ON__Factor_c++;
public static final int paper_dc_3_expd__ON__paper_dc_3_Expr_a = paper_dc_3.Init.count_syn__ON__Expr_a++;
public static final int paper_dc_3_expd__ON__paper_dc_3_Root_a = paper_dc_3.Init.count_syn__ON__Root_a++;
public static final int paper_dc_3_transformed_expd__ON__paper_dc_3_Expr_a = paper_dc_3.Init.count_syn__ON__Expr_a++;
public static final int paper_dc_3_transformed_expd__ON__paper_dc_3_Root_a = paper_dc_3.Init.count_syn__ON__Root_a++;
public static final int paper_dc_3_restored_Factor_c__ON__paper_dc_3_Expr_a = paper_dc_3.Init.count_syn__ON__Expr_a++;
public static final int paper_dc_3_restored_Factor_c__ON__paper_dc_3_Root_a = paper_dc_3.Init.count_syn__ON__Root_a++;
public static final int paper_dc_3_restored_Factor_c__ON__silver_extension_bidirtransform_Origin = silver.extension.bidirtransform.Init.count_syn__ON__Origin++;
public static final int paper_dc_3_restored_Term_c__ON__paper_dc_3_Expr_a = paper_dc_3.Init.count_syn__ON__Expr_a++;
public static final int paper_dc_3_restored_Term_c__ON__paper_dc_3_Root_a = paper_dc_3.Init.count_syn__ON__Root_a++;
public static final int paper_dc_3_restored_Term_c__ON__silver_extension_bidirtransform_Origin = silver.extension.bidirtransform.Init.count_syn__ON__Origin++;
public static final int paper_dc_3_restored_Root_c__ON__paper_dc_3_Expr_a = paper_dc_3.Init.count_syn__ON__Expr_a++;
public static final int paper_dc_3_restored_Root_c__ON__paper_dc_3_Root_a = paper_dc_3.Init.count_syn__ON__Root_a++;
public static final int paper_dc_3_restored_Root_c__ON__silver_extension_bidirtransform_Origin = silver.extension.bidirtransform.Init.count_syn__ON__Origin++;
public static final int paper_dc_3_restored_Expr_c__ON__paper_dc_3_Expr_a = paper_dc_3.Init.count_syn__ON__Expr_a++;
public static final int paper_dc_3_restored_Expr_c__ON__paper_dc_3_Root_a = paper_dc_3.Init.count_syn__ON__Root_a++;
public static final int paper_dc_3_restored_Expr_c__ON__silver_extension_bidirtransform_Origin = silver.extension.bidirtransform.Init.count_syn__ON__Origin++;
public static final int paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_c = paper_dc_3.Init.count_inh__ON__Expr_c++;
public static final int paper_dc_3_inhRedex_expd__ON__paper_dc_3_Root_c = paper_dc_3.Init.count_inh__ON__Root_c++;
public static final int paper_dc_3_inhRedex_expd__ON__paper_dc_3_Term_c = paper_dc_3.Init.count_inh__ON__Term_c++;
public static final int paper_dc_3_inhRedex_expd__ON__paper_dc_3_Factor_c = paper_dc_3.Init.count_inh__ON__Factor_c++;
public static final int paper_dc_3_inhRedex_expd__ON__paper_dc_3_Expr_a = paper_dc_3.Init.count_inh__ON__Expr_a++;
public static final int paper_dc_3_inhRedex_expd__ON__paper_dc_3_Root_a = paper_dc_3.Init.count_inh__ON__Root_a++;
public static final int paper_dc_3_value__ON__paper_dc_3_Root_a = paper_dc_3.Init.count_syn__ON__Root_a++;
public static final int paper_dc_3_value__ON__paper_dc_3_Expr_a = paper_dc_3.Init.count_syn__ON__Expr_a++;
public static final int paper_dc_3_pp__ON__paper_dc_3_Expr_c = paper_dc_3.Init.count_syn__ON__Expr_c++;
public static final int paper_dc_3_ast_Expr__ON__paper_dc_3_Expr_c = paper_dc_3.Init.count_syn__ON__Expr_c++;
public static final int paper_dc_3_pp__ON__paper_dc_3_Term_c = paper_dc_3.Init.count_syn__ON__Term_c++;
public static final int paper_dc_3_ast_Expr__ON__paper_dc_3_Term_c = paper_dc_3.Init.count_syn__ON__Term_c++;
public static final int paper_dc_3_pp__ON__paper_dc_3_Factor_c = paper_dc_3.Init.count_syn__ON__Factor_c++;
public static final int paper_dc_3_ast_Expr__ON__paper_dc_3_Factor_c = paper_dc_3.Init.count_syn__ON__Factor_c++;
public static final int paper_dc_3_pp__ON__paper_dc_3_Root_c = paper_dc_3.Init.count_syn__ON__Root_c++;
public static final int paper_dc_3_ast_Root__ON__paper_dc_3_Root_c = paper_dc_3.Init.count_syn__ON__Root_c++;
public static final int paper_dc_3_treepp__ON__paper_dc_3_Root_c = paper_dc_3.Init.count_syn__ON__Root_c++;
public static final int paper_dc_3_treepp__ON__paper_dc_3_Expr_c = paper_dc_3.Init.count_syn__ON__Expr_c++;
public static final int paper_dc_3_treepp__ON__paper_dc_3_Term_c = paper_dc_3.Init.count_syn__ON__Term_c++;
public static final int paper_dc_3_treepp__ON__paper_dc_3_Factor_c = paper_dc_3.Init.count_syn__ON__Factor_c++;
public static final int paper_dc_3_treepp__ON__paper_dc_3_Root_a = paper_dc_3.Init.count_syn__ON__Root_a++;
public static final int paper_dc_3_treepp__ON__paper_dc_3_Expr_a = paper_dc_3.Init.count_syn__ON__Expr_a++;
public static final int args__ON__paper_dc_3_main = paper_dc_3.Init.count_local__ON__paper_dc_3_main++;
public static final int result__ON__paper_dc_3_main = paper_dc_3.Init.count_local__ON__paper_dc_3_main++;
public static final int r_cst__ON__paper_dc_3_main = paper_dc_3.Init.count_local__ON__paper_dc_3_main++;
public static final int r_ast__ON__paper_dc_3_main = paper_dc_3.Init.count_local__ON__paper_dc_3_main++;
public static final int print_success__ON__paper_dc_3_main = paper_dc_3.Init.count_local__ON__paper_dc_3_main++;
public static final int print_failure__ON__paper_dc_3_main = paper_dc_3.Init.count_local__ON__paper_dc_3_main++;
}
