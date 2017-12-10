package edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.cppTags.Init.initAllStatics();
		core.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.construction.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.lexerHack.Init.initAllStatics();
		silver.langutil.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.cppTags.Init.init();
		core.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.construction.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.lexerHack.Init.init();
		silver.langutil.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.cppTags.Init.postInit();
		core.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.construction.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.lexerHack.Init.postInit();
		silver.langutil.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.postInit();


	}

	private static void setupInheritedAttributes(){
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PfoldLineNums.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.lineNums__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums] = "edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:foldLineNums:local:lineNums";
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PfoldLineNums.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.lines__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums] = "edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:foldLineNums:local:lines";
		//	local attribute result::ParseResult<cst:TranslationUnit_c>;
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseDecls.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecls] = new common.Lazy[core.NParseResult.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseDecls.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecls] = "edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:parseDecls:local:result";
		//	local attribute result::ParseResult<cst:ExternalDeclaration_c>;
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseDecl.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecl] = new common.Lazy[core.NParseResult.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseDecl.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecl] = "edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:parseDecl:local:result";
		//	local attribute result::ParseResult<cst:BlockItemList_c>;
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseStmt.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseStmt] = new common.Lazy[core.NParseResult.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseStmt.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseStmt] = "edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:parseStmt:local:result";
		//	local attribute result::ParseResult<cst:Expr_c>;
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseExpr.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseExpr] = new common.Lazy[core.NParseResult.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseExpr.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseExpr] = "edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:parseExpr:local:result";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION padRight String ::= len::Integer s::String 
		//FUNCTION foldLineNums String ::= text::String 
		// lineNums = (1 :: map(\ i::Integer  -> i + 1, lineNums,))
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PfoldLineNums.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.lineNums__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke((new common.NodeFactory<Integer>() {
  public final Integer invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13235_i = args[0];

    return Integer.valueOf(((Integer)common.Util.demand(__SV_LAMBDA_PARAM_13235_i)) + Integer.valueOf((int)1));
  }
}), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.lineNums__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums))); } })); } };
		// lines = explode("\n", text,)
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PfoldLineNums.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.lines__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pexplode.invoke((new common.StringCatter("\n")), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PfoldLineNums.i_text))); } };
		//FUNCTION parseDecls Decls ::= text::String 
		// result = declsParser(text, "parseDecls(\"\"\"\n" ++ foldLineNums(text,) ++ "\n\"\"\")",)
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseDecls.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NParseResult)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PdeclsParser.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseDecls.i_text), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("parseDecls(\"\"\"\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PfoldLineNums.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseDecls.i_text))), (common.StringCatter)(new common.StringCatter("\n\"\"\")")))); } })); } };
		//FUNCTION parseDecl Decl ::= text::String 
		// result = declParser(text, "parseDecl(\"\"\"\n" ++ foldLineNums(text,) ++ "\n\"\"\")",)
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseDecl.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NParseResult)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PdeclParser.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseDecl.i_text), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("parseDecl(\"\"\"\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PfoldLineNums.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseDecl.i_text))), (common.StringCatter)(new common.StringCatter("\n\"\"\")")))); } })); } };
		//FUNCTION parseStmt Stmt ::= text::String 
		// result = stmtParser(text, "parseStmt(\"\"\"\n" ++ foldLineNums(text,) ++ "\n\"\"\")",)
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseStmt.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NParseResult)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PstmtParser.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseStmt.i_text), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("parseStmt(\"\"\"\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PfoldLineNums.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseStmt.i_text))), (common.StringCatter)(new common.StringCatter("\n\"\"\")")))); } })); } };
		//FUNCTION parseExpr Expr ::= text::String 
		// result = exprParser(text, "parseExpr(\"\"\"\n" ++ foldLineNums(text,) ++ "\n\"\"\")",)
		edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NParseResult)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PexprParser.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseExpr.i_text), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("parseExpr(\"\"\"\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PfoldLineNums.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseExpr.i_text))), (common.StringCatter)(new common.StringCatter("\n\"\"\")")))); } })); } };
	}

	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_padRight = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_declsParser = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_declParser = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_stmtParser = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_exprParser = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecls = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecl = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseStmt = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseExpr = 0;
public static final int lineNums__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums = edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums++;
public static final int lines__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums = edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums++;
public static final int result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecls = edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecls++;
public static final int result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecl = edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecl++;
public static final int result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseStmt = edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseStmt++;
public static final int result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseExpr = edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseExpr++;
}
