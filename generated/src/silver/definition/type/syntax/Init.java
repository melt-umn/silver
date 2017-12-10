package silver.definition.type.syntax;

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
		silver.util.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.util.Init.init();
		silver.definition.env.Init.init();
		silver.definition.type.Init.init();
		silver.definition.core.Init.init();
		silver.definition.type.syntax.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.util.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.type.syntax.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PtyperepTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PintegerTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PfloatTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PstringTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PbooleanTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PnominalTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PtypeVariableTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PrefTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PfunTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NSignature.decorators, PsignatureEmptyRhs.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NSignature.decorators, Ppsignature.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NBracketedOptTypeExprs.decorators, PbotlNone.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NBracketedOptTypeExprs.decorators, PbotlSome.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExprs.decorators, PtypeListNone.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExprs.decorators, PtypeListSingle.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExprs.decorators, PtypeListCons.class);
	}

	private static void setupInheritedAttributes(){
		silver.definition.core.NAspectProductionSignature.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectProductionSignature] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.NAspectProductionLHS.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectProductionLHS] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.NAspectRHS.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHS] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.NAspectRHSElem.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHSElem] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.NAspectFunctionSignature.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectFunctionSignature] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.NAspectFunctionLHS.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectFunctionLHS] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.PaspectProductionDcl.occurs_local[silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_aspectProductionDcl] = "silver:definition:core:aspectProductionDcl:local:allLexicalTyVars";
		silver.definition.core.PaspectFunctionDcl.occurs_local[silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_aspectFunctionDcl] = "silver:definition:core:aspectFunctionDcl:local:allLexicalTyVars";
		silver.definition.core.NProductionSignature.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionSignature] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.NProductionLHS.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionLHS] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.NProductionRHS.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionRHS] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.NProductionRHSElem.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionRHSElem] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.PmkProductionDcl.occurs_local[silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_mkProductionDcl] = "silver:definition:core:mkProductionDcl:local:allLexicalTyVars";
		silver.definition.core.NFunctionSignature.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_FunctionSignature] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.NFunctionLHS.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_FunctionLHS] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.core.PfunctionDcl.occurs_local[silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_functionDcl] = "silver:definition:core:functionDcl:local:allLexicalTyVars";
		silver.definition.type.syntax.NTypeExpr.occurs_inh[silver.definition.type.syntax.Init.silver_definition_env_config__ON__silver_definition_type_syntax_TypeExpr] = "silver:definition:env:config";
		silver.definition.type.syntax.NTypeExpr.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.definition.type.syntax.NTypeExpr.occurs_inh[silver.definition.type.syntax.Init.silver_definition_core_grammarName__ON__silver_definition_type_syntax_TypeExpr] = "silver:definition:core:grammarName";
		silver.definition.type.syntax.NTypeExpr.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.type.syntax.NTypeExpr.occurs_syn[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr] = "silver:definition:core:errors";
		silver.definition.type.syntax.NTypeExpr.occurs_inh[silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_TypeExpr] = "silver:definition:env:env";
		silver.definition.type.syntax.NTypeExpr.decorators.add(silver.definition.env.Denv.singleton);
		silver.definition.type.syntax.NTypeExpr.occurs_syn[silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr] = "silver:definition:env:pp";
		silver.definition.type.syntax.NTypeExpr.occurs_syn[silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr] = "silver:definition:env:typerep";
		silver.definition.type.syntax.NTypeExpr.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.type.syntax.NSignature.occurs_inh[silver.definition.type.syntax.Init.silver_definition_env_config__ON__silver_definition_type_syntax_Signature] = "silver:definition:env:config";
		silver.definition.type.syntax.NSignature.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.definition.type.syntax.NSignature.occurs_inh[silver.definition.type.syntax.Init.silver_definition_core_grammarName__ON__silver_definition_type_syntax_Signature] = "silver:definition:core:grammarName";
		silver.definition.type.syntax.NSignature.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.type.syntax.NSignature.occurs_syn[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_Signature] = "silver:definition:core:errors";
		silver.definition.type.syntax.NSignature.occurs_inh[silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_Signature] = "silver:definition:env:env";
		silver.definition.type.syntax.NSignature.decorators.add(silver.definition.env.Denv.singleton);
		silver.definition.type.syntax.NSignature.occurs_syn[silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_Signature] = "silver:definition:env:pp";
		silver.definition.type.syntax.NSignature.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_Signature] = "silver:definition:type:syntax:types";
		silver.definition.type.syntax.NSignature.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_Signature] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.type.syntax.NTypeExprs.occurs_inh[silver.definition.type.syntax.Init.silver_definition_env_config__ON__silver_definition_type_syntax_TypeExprs] = "silver:definition:env:config";
		silver.definition.type.syntax.NTypeExprs.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.definition.type.syntax.NTypeExprs.occurs_inh[silver.definition.type.syntax.Init.silver_definition_core_grammarName__ON__silver_definition_type_syntax_TypeExprs] = "silver:definition:core:grammarName";
		silver.definition.type.syntax.NTypeExprs.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.type.syntax.NTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExprs] = "silver:definition:core:errors";
		silver.definition.type.syntax.NTypeExprs.occurs_inh[silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_TypeExprs] = "silver:definition:env:env";
		silver.definition.type.syntax.NTypeExprs.decorators.add(silver.definition.env.Denv.singleton);
		silver.definition.type.syntax.NTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExprs] = "silver:definition:env:pp";
		silver.definition.type.syntax.NTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_TypeExprs] = "silver:definition:type:syntax:types";
		silver.definition.type.syntax.NTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExprs] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.type.syntax.NTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs] = "silver:definition:type:syntax:errorsTyVars";
		silver.definition.type.syntax.NTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_TypeExprs] = "silver:definition:type:freeVariables";
		silver.definition.type.syntax.NBracketedOptTypeExprs.occurs_inh[silver.definition.type.syntax.Init.silver_definition_env_config__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = "silver:definition:env:config";
		silver.definition.type.syntax.NBracketedOptTypeExprs.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.definition.type.syntax.NBracketedOptTypeExprs.occurs_inh[silver.definition.type.syntax.Init.silver_definition_core_grammarName__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = "silver:definition:core:grammarName";
		silver.definition.type.syntax.NBracketedOptTypeExprs.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.type.syntax.NBracketedOptTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = "silver:definition:core:errors";
		silver.definition.type.syntax.NBracketedOptTypeExprs.occurs_inh[silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = "silver:definition:env:env";
		silver.definition.type.syntax.NBracketedOptTypeExprs.decorators.add(silver.definition.env.Denv.singleton);
		silver.definition.type.syntax.NBracketedOptTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = "silver:definition:env:pp";
		silver.definition.type.syntax.NBracketedOptTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = "silver:definition:type:syntax:types";
		silver.definition.type.syntax.NBracketedOptTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = "silver:definition:type:syntax:lexicalTypeVariables";
		silver.definition.type.syntax.NBracketedOptTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = "silver:definition:type:syntax:errorsTyVars";
		silver.definition.type.syntax.NBracketedOptTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = "silver:definition:type:freeVariables";
		silver.definition.type.syntax.NBracketedOptTypeExprs.occurs_syn[silver.definition.type.syntax.Init.silver_definition_type_syntax_envBindingTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = "silver:definition:type:syntax:envBindingTyVars";
		silver.definition.type.syntax.NBracketedOptTypeExprs.occurs_inh[silver.definition.type.syntax.Init.silver_definition_type_syntax_initialEnv__ON__silver_definition_type_syntax_BracketedOptTypeExprs] = "silver:definition:type:syntax:initialEnv";
		//	local attribute hack::QNameLookup;
		silver.definition.type.syntax.PtypeVariableTypeExpr.localInheritedAttributes[silver.definition.type.syntax.Init.hack__ON__silver_definition_type_syntax_typeVariableTypeExpr] = new common.Lazy[silver.definition.core.NQNameLookup.num_inh_attrs];
		silver.definition.type.syntax.PtypeVariableTypeExpr.occurs_local[silver.definition.type.syntax.Init.hack__ON__silver_definition_type_syntax_typeVariableTypeExpr] = "silver:definition:type:syntax:typeVariableTypeExpr:local:hack";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION addNewLexicalTyVars_ActuallyVariables [Def] ::= gn::String sl::Location l::[String] 
		//ASPECT PRODUCTION aspectProductionDcl top ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
		// allLexicalTyVars = makeSet(ns.lexicalTypeVariables)
		silver.definition.core.PaspectProductionDcl.localAttributes[silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_aspectProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectProductionDcl.i_ns, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectProductionSignature))); } };
		// sigDefs <- addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, allLexicalTyVars)
		((common.CollectionAttribute)silver.definition.core.PaspectProductionDcl.localAttributes[silver.definition.core.Init.sigDefs__ON__silver_definition_core_aspectProductionDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.syntax.PaddNewLexicalTyVars_ActuallyVariables.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_aspectProductionDcl))); } });
		//ASPECT PRODUCTION aspectFunctionDcl top ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
		// allLexicalTyVars = makeSet(ns.lexicalTypeVariables)
		silver.definition.core.PaspectFunctionDcl.localAttributes[silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_aspectFunctionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectFunctionDcl.i_ns, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectFunctionSignature))); } };
		// sigDefs <- addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, allLexicalTyVars)
		((common.CollectionAttribute)silver.definition.core.PaspectFunctionDcl.localAttributes[silver.definition.core.Init.sigDefs__ON__silver_definition_core_aspectFunctionDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.syntax.PaddNewLexicalTyVars_ActuallyVariables.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_aspectFunctionDcl))); } });
		//ASPECT PRODUCTION aspectProductionSignature top ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS 
		// top.lexicalTypeVariables = makeSet(lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables)
		silver.definition.core.PaspectProductionSignature.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectProductionSignature.i_lhs, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectProductionLHS), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectProductionSignature.i_rhs, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHS))); } })); } };
		//ASPECT PRODUCTION aspectProductionLHSTyped top ::= id::Name '::' t::TypeExpr 
		// top.lexicalTypeVariables = t.lexicalTypeVariables
		silver.definition.core.PaspectProductionLHSTyped.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectProductionLHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PaspectProductionLHSTyped.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr)); } };
		//ASPECT PRODUCTION aspectProductionLHSFull top ::= id::Name t::Type 
		// top.lexicalTypeVariables = []
		silver.definition.core.PaspectProductionLHSFull.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectProductionLHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION aspectRHSElemNil top ::= 
		// top.lexicalTypeVariables = []
		silver.definition.core.PaspectRHSElemNil.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION aspectRHSElemCons top ::= h::AspectRHSElem t::AspectRHS 
		// top.lexicalTypeVariables = makeSet(h.lexicalTypeVariables ++ t.lexicalTypeVariables)
		silver.definition.core.PaspectRHSElemCons.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemCons.i_h, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemCons.i_t, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHS))); } })); } };
		//ASPECT PRODUCTION aspectRHSElemTyped top ::= id::Name '::' t::TypeExpr 
		// top.lexicalTypeVariables = t.lexicalTypeVariables
		silver.definition.core.PaspectRHSElemTyped.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PaspectRHSElemTyped.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr)); } };
		//ASPECT PRODUCTION aspectRHSElemFull top ::= id::Name t::Type 
		// top.lexicalTypeVariables = []
		silver.definition.core.PaspectRHSElemFull.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION aspectFunctionSignature top ::= lhs::AspectFunctionLHS '::=' rhs::AspectRHS 
		// top.lexicalTypeVariables = makeSet(lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables)
		silver.definition.core.PaspectFunctionSignature.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectFunctionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectFunctionSignature.i_lhs, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectFunctionLHS), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectFunctionSignature.i_rhs, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHS))); } })); } };
		//ASPECT PRODUCTION functionLHSType top ::= t::TypeExpr 
		// top.lexicalTypeVariables = t.lexicalTypeVariables
		silver.definition.core.PfunctionLHSType.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectFunctionLHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PfunctionLHSType.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr)); } };
		//ASPECT PRODUCTION mkProductionDcl top ::= id::Name ns::ProductionSignature body::ProductionBody isAbstract::Boolean 
		// allLexicalTyVars = makeSet(ns.lexicalTypeVariables)
		silver.definition.core.PmkProductionDcl.localAttributes[silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_mkProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmkProductionDcl.i_ns, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionSignature))); } };
		// sigDefs <- addNewLexicalTyVars(top.grammarName, top.location, allLexicalTyVars)
		((common.CollectionAttribute)silver.definition.core.PmkProductionDcl.localAttributes[silver.definition.core.Init.sigDefs__ON__silver_definition_core_mkProductionDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.syntax.PaddNewLexicalTyVars.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_mkProductionDcl))); } });
		//ASPECT PRODUCTION productionSignature top ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
		// top.lexicalTypeVariables = makeSet(lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables)
		silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionSignature.i_lhs, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionLHS), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionSignature.i_rhs, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionRHS))); } })); } };
		//ASPECT PRODUCTION productionLHS top ::= id::Name '::' t::TypeExpr 
		// top.lexicalTypeVariables = t.lexicalTypeVariables
		silver.definition.core.PproductionLHS.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionLHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PproductionLHS.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr)); } };
		//ASPECT PRODUCTION productionRHSNil top ::= 
		// top.lexicalTypeVariables = []
		silver.definition.core.PproductionRHSNil.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION productionRHSCons top ::= h::ProductionRHSElem t::ProductionRHS 
		// top.lexicalTypeVariables = makeSet(h.lexicalTypeVariables ++ t.lexicalTypeVariables)
		silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_h, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_t, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionRHS))); } })); } };
		//ASPECT PRODUCTION productionRHSElem top ::= id::Name '::' t::TypeExpr 
		// top.lexicalTypeVariables = t.lexicalTypeVariables
		silver.definition.core.PproductionRHSElem.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PproductionRHSElem.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr)); } };
		//ASPECT PRODUCTION functionDcl top ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
		// allLexicalTyVars = makeSet(ns.lexicalTypeVariables)
		silver.definition.core.PfunctionDcl.localAttributes[silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_functionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionDcl.i_ns, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_FunctionSignature))); } };
		// sigDefs <- addNewLexicalTyVars(top.grammarName, top.location, allLexicalTyVars)
		((common.CollectionAttribute)silver.definition.core.PfunctionDcl.localAttributes[silver.definition.core.Init.sigDefs__ON__silver_definition_core_functionDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.syntax.PaddNewLexicalTyVars.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.definition.type.syntax.Init.allLexicalTyVars__ON__silver_definition_core_functionDcl))); } });
		//ASPECT PRODUCTION functionSignature top ::= lhs::FunctionLHS '::=' rhs::ProductionRHS 
		// top.lexicalTypeVariables = makeSet(lhs.lexicalTypeVariables ++ rhs.lexicalTypeVariables)
		silver.definition.core.PfunctionSignature.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_FunctionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionSignature.i_lhs, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_FunctionLHS), context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionSignature.i_rhs, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionRHS))); } })); } };
		//ASPECT PRODUCTION functionLHS top ::= t::TypeExpr 
		// top.lexicalTypeVariables = t.lexicalTypeVariables
		silver.definition.core.PfunctionLHS.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_FunctionLHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PfunctionLHS.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr)); } };
		//FUNCTION addNewLexicalTyVars [Def] ::= gn::String sl::Location l::[String] 
		silver.definition.type.syntax.PtyperepTypeExpr.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PintegerTypeExpr.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PfloatTypeExpr.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PstringTypeExpr.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PbooleanTypeExpr.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PnominalTypeExpr.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PtypeVariableTypeExpr.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PrefTypeExpr.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PfunTypeExpr.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PsignatureEmptyRhs.initProductionAttributeDefinitions();
		silver.definition.type.syntax.Ppsignature.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PbotlNone.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PbotlSome.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PtypeListNone.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PtypeListSingle.initProductionAttributeDefinitions();
		silver.definition.type.syntax.PtypeListCons.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION typeListNone top ::= 
		// top.errorsTyVars := []
		if(silver.definition.type.syntax.PtypeListNone.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs] == null)
			silver.definition.type.syntax.PtypeListNone.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs] = new silver.definition.type.syntax.CAerrorsTyVars(silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs);
		((common.CollectionAttribute)silver.definition.type.syntax.PtypeListNone.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.freeVariables = []
		silver.definition.type.syntax.PtypeListNone.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_TypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION typeListSingle top ::= t::TypeExpr 
		// top.errorsTyVars := case t of typeVariableTypeExpr(_) -> [] | _ -> [ err(t.location, t.pp ++ " is not permitted here, only type variables are") ] end
		if(silver.definition.type.syntax.PtypeListSingle.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs] == null)
			silver.definition.type.syntax.PtypeListSingle.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs] = new silver.definition.type.syntax.CAerrorsTyVars(silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs);
		((common.CollectionAttribute)silver.definition.type.syntax.PtypeListSingle.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5901___fail_5902 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.childDecorated(silver.definition.type.syntax.PtypeListSingle.i_t).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.syntax.PtypeListSingle.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)(new common.StringCatter(" is not permitted here, only type variables are"))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.syntax.PtypeVariableTypeExpr) { final common.Thunk<Object> __SV_LOCAL___pv5907___sv_tmp_pv_5908 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TIdLower_t)scrutinee.childAsIs(0); } };
 return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_5901___fail_5902.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.type.syntax.PtypeListSingle.i_t)); } }).eval()); } });
		// top.freeVariables = t.typerep.freeVariables
		silver.definition.type.syntax.PtypeListSingle.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_TypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.type.NType)context.childDecorated(silver.definition.type.syntax.PtypeListSingle.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type)); } };
		//ASPECT PRODUCTION typeListCons top ::= t::TypeExpr list::TypeExprs 
		// top.errorsTyVars := case t of typeVariableTypeExpr(_) -> [] | _ -> [ err(t.location, t.pp ++ " is not permitted here, only type variables are") ] end ++ list.errorsTyVars
		if(silver.definition.type.syntax.PtypeListCons.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs] == null)
			silver.definition.type.syntax.PtypeListCons.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs] = new silver.definition.type.syntax.CAerrorsTyVars(silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs);
		((common.CollectionAttribute)silver.definition.type.syntax.PtypeListCons.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5911___fail_5910 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.childDecorated(silver.definition.type.syntax.PtypeListCons.i_t).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.syntax.PtypeListCons.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)(new common.StringCatter(" is not permitted here, only type variables are"))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.syntax.PtypeVariableTypeExpr) { final common.Thunk<Object> __SV_LOCAL___pv5912___sv_tmp_pv_5913 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TIdLower_t)scrutinee.childAsIs(0); } };
 return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_5911___fail_5910.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.type.syntax.PtypeListCons.i_t)); } }, context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PtypeListCons.i_list, silver.definition.type.syntax.Init.silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs))); } });
		// top.freeVariables = t.typerep.freeVariables ++ list.freeVariables
		silver.definition.type.syntax.PtypeListCons.synthesizedAttributes[silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_TypeExprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.type.NType)context.childDecorated(silver.definition.type.syntax.PtypeListCons.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type)); } }, context.childDecoratedSynthesizedLazy(silver.definition.type.syntax.PtypeListCons.i_list, silver.definition.type.syntax.Init.silver_definition_type_freeVariables__ON__silver_definition_type_syntax_TypeExprs))); } };
	}

	public static int count_local__ON__silver_definition_type_syntax_addNewLexicalTyVars_ActuallyVariables = 0;
	public static int count_inh__ON__TypeExpr = 0;
	public static int count_syn__ON__TypeExpr = 0;
	public static int count_inh__ON__Signature = 0;
	public static int count_syn__ON__Signature = 0;
	public static int count_inh__ON__TypeExprs = 0;
	public static int count_syn__ON__TypeExprs = 0;
	public static int count_inh__ON__BracketedOptTypeExprs = 0;
	public static int count_syn__ON__BracketedOptTypeExprs = 0;
	public static int count_local__ON__silver_definition_type_syntax_addNewLexicalTyVars = 0;
	public static int count_local__ON__silver_definition_type_syntax_typerepTypeExpr = 0;
	public static int count_local__ON__silver_definition_type_syntax_integerTypeExpr = 0;
	public static int count_local__ON__silver_definition_type_syntax_floatTypeExpr = 0;
	public static int count_local__ON__silver_definition_type_syntax_stringTypeExpr = 0;
	public static int count_local__ON__silver_definition_type_syntax_booleanTypeExpr = 0;
	public static int count_local__ON__silver_definition_type_syntax_nominalTypeExpr = 0;
	public static int count_local__ON__silver_definition_type_syntax_typeVariableTypeExpr = 0;
	public static int count_local__ON__silver_definition_type_syntax_refTypeExpr = 0;
	public static int count_local__ON__silver_definition_type_syntax_funTypeExpr = 0;
	public static int count_local__ON__silver_definition_type_syntax_signatureEmptyRhs = 0;
	public static int count_local__ON__silver_definition_type_syntax_psignature = 0;
	public static int count_local__ON__silver_definition_type_syntax_botlNone = 0;
	public static int count_local__ON__silver_definition_type_syntax_botlSome = 0;
	public static int count_local__ON__silver_definition_type_syntax_typeListNone = 0;
	public static int count_local__ON__silver_definition_type_syntax_typeListSingle = 0;
	public static int count_local__ON__silver_definition_type_syntax_typeListCons = 0;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectProductionSignature = silver.definition.core.Init.count_syn__ON__AspectProductionSignature++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectProductionLHS = silver.definition.core.Init.count_syn__ON__AspectProductionLHS++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHS = silver.definition.core.Init.count_syn__ON__AspectRHS++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectRHSElem = silver.definition.core.Init.count_syn__ON__AspectRHSElem++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectFunctionSignature = silver.definition.core.Init.count_syn__ON__AspectFunctionSignature++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectFunctionLHS = silver.definition.core.Init.count_syn__ON__AspectFunctionLHS++;
public static final int allLexicalTyVars__ON__silver_definition_core_aspectProductionDcl = silver.definition.core.Init.count_local__ON__silver_definition_core_aspectProductionDcl++;
public static final int allLexicalTyVars__ON__silver_definition_core_aspectFunctionDcl = silver.definition.core.Init.count_local__ON__silver_definition_core_aspectFunctionDcl++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionSignature = silver.definition.core.Init.count_syn__ON__ProductionSignature++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionLHS = silver.definition.core.Init.count_syn__ON__ProductionLHS++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionRHS = silver.definition.core.Init.count_syn__ON__ProductionRHS++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_ProductionRHSElem = silver.definition.core.Init.count_syn__ON__ProductionRHSElem++;
public static final int allLexicalTyVars__ON__silver_definition_core_mkProductionDcl = silver.definition.core.Init.count_local__ON__silver_definition_core_mkProductionDcl++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_FunctionSignature = silver.definition.core.Init.count_syn__ON__FunctionSignature++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_FunctionLHS = silver.definition.core.Init.count_syn__ON__FunctionLHS++;
public static final int allLexicalTyVars__ON__silver_definition_core_functionDcl = silver.definition.core.Init.count_local__ON__silver_definition_core_functionDcl++;
public static final int silver_definition_env_config__ON__silver_definition_type_syntax_TypeExpr = silver.definition.type.syntax.Init.count_inh__ON__TypeExpr++;
public static final int silver_definition_core_grammarName__ON__silver_definition_type_syntax_TypeExpr = silver.definition.type.syntax.Init.count_inh__ON__TypeExpr++;
public static final int silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr = silver.definition.type.syntax.Init.count_syn__ON__TypeExpr++;
public static final int silver_definition_env_env__ON__silver_definition_type_syntax_TypeExpr = silver.definition.type.syntax.Init.count_inh__ON__TypeExpr++;
public static final int silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr = silver.definition.type.syntax.Init.count_syn__ON__TypeExpr++;
public static final int silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr = silver.definition.type.syntax.Init.count_syn__ON__TypeExpr++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr = silver.definition.type.syntax.Init.count_syn__ON__TypeExpr++;
public static final int silver_definition_env_config__ON__silver_definition_type_syntax_Signature = silver.definition.type.syntax.Init.count_inh__ON__Signature++;
public static final int silver_definition_core_grammarName__ON__silver_definition_type_syntax_Signature = silver.definition.type.syntax.Init.count_inh__ON__Signature++;
public static final int silver_definition_core_errors__ON__silver_definition_type_syntax_Signature = silver.definition.type.syntax.Init.count_syn__ON__Signature++;
public static final int silver_definition_env_env__ON__silver_definition_type_syntax_Signature = silver.definition.type.syntax.Init.count_inh__ON__Signature++;
public static final int silver_definition_env_pp__ON__silver_definition_type_syntax_Signature = silver.definition.type.syntax.Init.count_syn__ON__Signature++;
public static final int silver_definition_type_syntax_types__ON__silver_definition_type_syntax_Signature = silver.definition.type.syntax.Init.count_syn__ON__Signature++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_Signature = silver.definition.type.syntax.Init.count_syn__ON__Signature++;
public static final int silver_definition_env_config__ON__silver_definition_type_syntax_TypeExprs = silver.definition.type.syntax.Init.count_inh__ON__TypeExprs++;
public static final int silver_definition_core_grammarName__ON__silver_definition_type_syntax_TypeExprs = silver.definition.type.syntax.Init.count_inh__ON__TypeExprs++;
public static final int silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExprs = silver.definition.type.syntax.Init.count_syn__ON__TypeExprs++;
public static final int silver_definition_env_env__ON__silver_definition_type_syntax_TypeExprs = silver.definition.type.syntax.Init.count_inh__ON__TypeExprs++;
public static final int silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExprs = silver.definition.type.syntax.Init.count_syn__ON__TypeExprs++;
public static final int silver_definition_type_syntax_types__ON__silver_definition_type_syntax_TypeExprs = silver.definition.type.syntax.Init.count_syn__ON__TypeExprs++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExprs = silver.definition.type.syntax.Init.count_syn__ON__TypeExprs++;
public static final int silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_TypeExprs = silver.definition.type.syntax.Init.count_syn__ON__TypeExprs++;
public static final int silver_definition_type_freeVariables__ON__silver_definition_type_syntax_TypeExprs = silver.definition.type.syntax.Init.count_syn__ON__TypeExprs++;
public static final int silver_definition_env_config__ON__silver_definition_type_syntax_BracketedOptTypeExprs = silver.definition.type.syntax.Init.count_inh__ON__BracketedOptTypeExprs++;
public static final int silver_definition_core_grammarName__ON__silver_definition_type_syntax_BracketedOptTypeExprs = silver.definition.type.syntax.Init.count_inh__ON__BracketedOptTypeExprs++;
public static final int silver_definition_core_errors__ON__silver_definition_type_syntax_BracketedOptTypeExprs = silver.definition.type.syntax.Init.count_syn__ON__BracketedOptTypeExprs++;
public static final int silver_definition_env_env__ON__silver_definition_type_syntax_BracketedOptTypeExprs = silver.definition.type.syntax.Init.count_inh__ON__BracketedOptTypeExprs++;
public static final int silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs = silver.definition.type.syntax.Init.count_syn__ON__BracketedOptTypeExprs++;
public static final int silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs = silver.definition.type.syntax.Init.count_syn__ON__BracketedOptTypeExprs++;
public static final int silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs = silver.definition.type.syntax.Init.count_syn__ON__BracketedOptTypeExprs++;
public static final int silver_definition_type_syntax_errorsTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs = silver.definition.type.syntax.Init.count_syn__ON__BracketedOptTypeExprs++;
public static final int silver_definition_type_freeVariables__ON__silver_definition_type_syntax_BracketedOptTypeExprs = silver.definition.type.syntax.Init.count_syn__ON__BracketedOptTypeExprs++;
public static final int silver_definition_type_syntax_envBindingTyVars__ON__silver_definition_type_syntax_BracketedOptTypeExprs = silver.definition.type.syntax.Init.count_syn__ON__BracketedOptTypeExprs++;
public static final int silver_definition_type_syntax_initialEnv__ON__silver_definition_type_syntax_BracketedOptTypeExprs = silver.definition.type.syntax.Init.count_inh__ON__BracketedOptTypeExprs++;
public static final int hack__ON__silver_definition_type_syntax_typeVariableTypeExpr = silver.definition.type.syntax.Init.count_local__ON__silver_definition_type_syntax_typeVariableTypeExpr++;
}
