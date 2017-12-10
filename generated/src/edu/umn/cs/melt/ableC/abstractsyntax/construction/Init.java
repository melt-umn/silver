package edu.umn.cs.melt.ableC.abstractsyntax.construction;

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
		edu.umn.cs.melt.ableC.concretesyntax.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.initAllStatics();
		silver.langutil.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.initAllStatics();

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
		edu.umn.cs.melt.ableC.concretesyntax.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.init();
		silver.langutil.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.init();

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
		edu.umn.cs.melt.ableC.concretesyntax.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postInit();
		silver.langutil.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.postInit();


		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.construction.NTypeSpecifierMutator.decorators, PmodifyTypeSpecifier.class);
	}

	private static void setupInheritedAttributes(){
		//	local attribute bty::BaseTypeExpr;
		edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDeclGeneral.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.bty__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDeclGeneral] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDeclGeneral.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.bty__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDeclGeneral] = "edu:umn:cs:melt:ableC:abstractsyntax:construction:mkDeclGeneral:local:bty";
		//	local attribute bty::BaseTypeExpr;
		edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDecl.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.bty__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDecl] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDecl.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.bty__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDecl] = "edu:umn:cs:melt:ableC:abstractsyntax:construction:mkDecl:local:bty";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION mkIntAssign Stmt ::= x::String a::String l::Location 
		//FUNCTION mkAssign Stmt ::= x::String e::Expr l::Location 
		//FUNCTION foldStructItem StructItemList ::= l::[StructItem] 
		//FUNCTION foldEnumItem EnumItemList ::= l::[EnumItem] 
		//FUNCTION foldDecl Decls ::= l::[Decl] 
		//FUNCTION foldGlobalDecl GlobalDecls ::= l::[Decl] 
		//FUNCTION foldInit InitList ::= l::[Init] 
		//FUNCTION foldExpr Exprs ::= l::[Expr] 
		//FUNCTION foldGenericAssoc GenericAssocs ::= l::[GenericAssoc] 
		//FUNCTION foldStructDeclarator StructDeclarators ::= l::[StructDeclarator] 
		//FUNCTION foldDeclarator Declarators ::= l::[Declarator] 
		//FUNCTION foldStmt Stmt ::= l::[Stmt] 
		//FUNCTION foldParameterDecl Parameters ::= l::[ParameterDecl] 
		//FUNCTION foldAttribute Attributes ::= l::[Attribute] 
		//FUNCTION foldQualifier Qualifiers ::= l::[Qualifier] 
		//FUNCTION figureOutTypeFromSpecifiers BaseTypeExpr ::= l::Location q::Qualifiers pre_ts::[String] real_ts::[BaseTypeExpr] mod::[TypeSpecifierMutator] 
		//FUNCTION interpretTypeSpecifiers Maybe<BaseTypeExpr> ::= q::Qualifiers sorted_type_specifiers::[String] 
		edu.umn.cs.melt.ableC.abstractsyntax.construction.PmodifyTypeSpecifier.initProductionAttributeDefinitions();
		//FUNCTION mkIntDecl Stmt ::= n::String l::Location 
		//FUNCTION mkNamedTypeDecl Stmt ::= n::String l::Location 
		//FUNCTION mkIntDeclInit Stmt ::= n::String val::String l::Location 
		//FUNCTION mkIntDeclExpr Stmt ::= n::String val::Expr l::Location 
		//FUNCTION mkIntDeclGeneral Stmt ::= n::String init::MaybeInitializer l::Location 
		//FUNCTION mkDeclGeneral Stmt ::= n::String typ::Type l::Location 
		// bty = directTypeExpr(typ,)
		edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDeclGeneral.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.bty__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDeclGeneral] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdirectTypeExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDeclGeneral.i_typ)))); } };
		//FUNCTION mkDecl Stmt ::= n::String typ::Type v::Expr l::Location 
		// bty = directTypeExpr(typ,)
		edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDecl.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.bty__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdirectTypeExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkDecl.i_typ)))); } };
		//FUNCTION makeDeclIntInit Decl ::= n::String val::String l::Location 
		//FUNCTION makeDeclIntGeneral Decl ::= n::String init::MaybeInitializer l::Location 
		//FUNCTION fromId Name ::= n::cst:Identifier_t 
		//FUNCTION fromTy Name ::= n::cst:TypeName_t 
		//FUNCTION mkIntConst Expr ::= n::Integer l::Location 
		//FUNCTION mkAdd Expr ::= left::Expr right::Expr l::Location 
		//FUNCTION mkAnd Expr ::= left::Expr right::Expr l::Location 
		//FUNCTION mkAddressOf Expr ::= e::Expr l::Location 
		//FUNCTION mkIntExpr Expr ::= val::String l::Location 
		//FUNCTION mkErrorCheck Expr ::= msg::[Message] e::Expr 
	}

	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkIntAssign = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkAssign = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldStructItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldEnumItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldDecl = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldGlobalDecl = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldInit = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldGenericAssoc = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldStructDeclarator = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldDeclarator = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldStmt = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldParameterDecl = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldAttribute = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldQualifier = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_figureOutTypeFromSpecifiers = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_interpretTypeSpecifiers = 0;
	public static int count_inh__ON__TypeSpecifierMutator = 0;
	public static int count_syn__ON__TypeSpecifierMutator = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_modifyTypeSpecifier = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkIntDecl = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkNamedTypeDecl = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkIntDeclInit = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkIntDeclExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkIntDeclGeneral = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDeclGeneral = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDecl = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_makeDeclIntInit = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_makeDeclIntGeneral = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_fromId = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_fromTy = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkIntConst = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkAdd = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkAnd = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkAddressOf = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkIntExpr = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkErrorCheck = 0;
public static final int bty__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDeclGeneral = edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDeclGeneral++;
public static final int bty__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDecl = edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_mkDecl++;
}
