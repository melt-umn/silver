package edu.umn.cs.melt.ableC.concretesyntax.construction;

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
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.lexerHack.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.Init.initAllStatics();
		silver.langutil.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.construction.Init.initAllStatics();

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
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.lexerHack.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.Init.init();
		silver.langutil.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.construction.Init.init();

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
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.lexerHack.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.Init.postInit();
		silver.langutil.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.construction.Init.postInit();


		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NDeclaration_c.decorators, PlexerHackTypedefProto.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.construction.NIdentifierList_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_25_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.construction.NIdentifierList_c.decorators, Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0.class);
	}

	private static void setupInheritedAttributes(){
		edu.umn.cs.melt.ableC.concretesyntax.construction.NIdentifierList_c.occurs_syn[edu.umn.cs.melt.ableC.concretesyntax.construction.Init.edu_umn_cs_melt_ableC_concretesyntax_declaredIdents__ON__edu_umn_cs_melt_ableC_concretesyntax_construction_IdentifierList_c] = "edu:umn:cs:melt:ableC:concretesyntax:declaredIdents";
	}

	private static void initProductionAttributeDefinitions(){
		edu.umn.cs.melt.ableC.concretesyntax.construction.PlexerHackTypedefProto.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.construction.Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_25_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.construction.Pp_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_construction_lexerHackTypedefProto = 0;
	public static int count_inh__ON__IdentifierList_c = 0;
	public static int count_syn__ON__IdentifierList_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_construction_p_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_25_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_construction_p_edu_umn_cs_melt_ableC_concretesyntax_construction_ProtoTypedef_sv_27_0 = 0;
public static final int edu_umn_cs_melt_ableC_concretesyntax_declaredIdents__ON__edu_umn_cs_melt_ableC_concretesyntax_construction_IdentifierList_c = edu.umn.cs.melt.ableC.concretesyntax.construction.Init.count_syn__ON__IdentifierList_c++;
}
