package edu.umn.cs.melt.ableC.concretesyntax.c11;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.cppTags.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.langutil.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.cppTags.Init.init();
		core.Init.init();
		silver.langutil.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.cppTags.Init.postInit();
		core.Init.postInit();
		silver.langutil.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Init.postInit();


		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NDeclarationSpecifiers_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_12_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NDeclarationSpecifiers_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_21_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.c11.NAlignmentSpecifier_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_37_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.c11.NAlignmentSpecifier_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_44_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NFunctionSpecifier_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_51_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NPrimaryExpr_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_58_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericSelection_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_63_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssocList_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_77_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssocList_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_80_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssoc_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssoc_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_89_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NTypeSpecifier_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_113_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NTypeQualifier_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_118_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NStorageClassSpecifier_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_133_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.c11.NStaticAssertDeclaration_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_143_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NDeclaration_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_147_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NUnaryExpr_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_155_0.class);
	}

	private static void setupInheritedAttributes(){
		edu.umn.cs.melt.ableC.concretesyntax.c11.NAlignmentSpecifier_c.occurs_syn[edu.umn.cs.melt.ableC.concretesyntax.c11.Init.edu_umn_cs_melt_ableC_concretesyntax_specialSpecifiers__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_AlignmentSpecifier_c] = "edu:umn:cs:melt:ableC:concretesyntax:specialSpecifiers";
		edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericSelection_c.occurs_syn[edu.umn.cs.melt.ableC.concretesyntax.c11.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericSelection_c] = "silver:langutil:ast";
		edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssocList_c.occurs_syn[edu.umn.cs.melt.ableC.concretesyntax.c11.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericAssocList_c] = "silver:langutil:ast";
		edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssocList_c.occurs_syn[edu.umn.cs.melt.ableC.concretesyntax.c11.Init.edu_umn_cs_melt_ableC_concretesyntax_c11_defaultExpr__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericAssocList_c] = "edu:umn:cs:melt:ableC:concretesyntax:c11:defaultExpr";
		edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssoc_c.occurs_syn[edu.umn.cs.melt.ableC.concretesyntax.c11.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericAssoc_c] = "silver:langutil:ast";
		edu.umn.cs.melt.ableC.concretesyntax.c11.NGenericAssoc_c.occurs_syn[edu.umn.cs.melt.ableC.concretesyntax.c11.Init.edu_umn_cs_melt_ableC_concretesyntax_c11_defaultExpr__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericAssoc_c] = "edu:umn:cs:melt:ableC:concretesyntax:c11:defaultExpr";
		edu.umn.cs.melt.ableC.concretesyntax.c11.NStaticAssertDeclaration_c.occurs_syn[edu.umn.cs.melt.ableC.concretesyntax.c11.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_StaticAssertDeclaration_c] = "silver:langutil:ast";
	}

	private static void initProductionAttributeDefinitions(){
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_12_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_21_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_37_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_44_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_51_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_58_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_63_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_77_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_80_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_89_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_113_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_118_0.initProductionAttributeDefinitions();
		//FUNCTION atomicMutator ast:BaseTypeExpr ::= q::ast:Qualifiers bt::ast:BaseTypeExpr 
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_133_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_143_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_147_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.c11.Pp_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_155_0.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_12_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_21_0 = 0;
	public static int count_inh__ON__AlignmentSpecifier_c = 0;
	public static int count_syn__ON__AlignmentSpecifier_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_37_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_44_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_51_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_58_0 = 0;
	public static int count_inh__ON__GenericSelection_c = 0;
	public static int count_syn__ON__GenericSelection_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_63_0 = 0;
	public static int count_inh__ON__GenericAssocList_c = 0;
	public static int count_syn__ON__GenericAssocList_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_77_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_80_0 = 0;
	public static int count_inh__ON__GenericAssoc_c = 0;
	public static int count_syn__ON__GenericAssoc_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_86_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_89_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_113_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_118_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_atomicMutator = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_133_0 = 0;
	public static int count_inh__ON__StaticAssertDeclaration_c = 0;
	public static int count_syn__ON__StaticAssertDeclaration_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_143_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_147_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_p_edu_umn_cs_melt_ableC_concretesyntax_c11_C11_sv_155_0 = 0;
public static final int edu_umn_cs_melt_ableC_concretesyntax_specialSpecifiers__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_AlignmentSpecifier_c = edu.umn.cs.melt.ableC.concretesyntax.c11.Init.count_syn__ON__AlignmentSpecifier_c++;
public static final int silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericSelection_c = edu.umn.cs.melt.ableC.concretesyntax.c11.Init.count_syn__ON__GenericSelection_c++;
public static final int silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericAssocList_c = edu.umn.cs.melt.ableC.concretesyntax.c11.Init.count_syn__ON__GenericAssocList_c++;
public static final int edu_umn_cs_melt_ableC_concretesyntax_c11_defaultExpr__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericAssocList_c = edu.umn.cs.melt.ableC.concretesyntax.c11.Init.count_syn__ON__GenericAssocList_c++;
public static final int silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericAssoc_c = edu.umn.cs.melt.ableC.concretesyntax.c11.Init.count_syn__ON__GenericAssoc_c++;
public static final int edu_umn_cs_melt_ableC_concretesyntax_c11_defaultExpr__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_GenericAssoc_c = edu.umn.cs.melt.ableC.concretesyntax.c11.Init.count_syn__ON__GenericAssoc_c++;
public static final int silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_c11_StaticAssertDeclaration_c = edu.umn.cs.melt.ableC.concretesyntax.c11.Init.count_syn__ON__StaticAssertDeclaration_c++;
}
