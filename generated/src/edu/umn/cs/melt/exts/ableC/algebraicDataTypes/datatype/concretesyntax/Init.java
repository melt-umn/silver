package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax;

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
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.Init.initAllStatics();
		silver.langutil.pp.Init.initAllStatics();
		silver.langutil.Init.initAllStatics();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.initAllStatics();

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
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.Init.init();
		silver.langutil.pp.Init.init();
		silver.langutil.Init.init();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.init();

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
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.construction.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.Init.postInit();
		silver.langutil.pp.Init.postInit();
		silver.langutil.Init.postInit();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.postInit();


		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NStructOrUnionSpecifier_c.decorators, Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DatatypeFwd_sv_9_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.NDeclaration_c.decorators, Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_34_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NADTDecl_c.decorators, Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c.decorators, Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c.decorators, Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_48_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructor_c.decorators, Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NTypeNameList_c.decorators, Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_60_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NTypeNameList_c.decorators, Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_62_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NTailTypeNameList_c.decorators, Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_67_0.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NTailTypeNameList_c.decorators, Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_69_0.class);
	}

	private static void setupInheritedAttributes(){
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NADTDecl_c.occurs_syn[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_ADTDecl_c] = "silver:langutil:ast";
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructorList_c.occurs_syn[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_ConstructorList_c] = "silver:langutil:ast";
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NConstructor_c.occurs_syn[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_Constructor_c] = "silver:langutil:ast";
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NTypeNameList_c.occurs_syn[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_TypeNameList_c] = "silver:langutil:ast";
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.NTailTypeNameList_c.occurs_syn[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_TailTypeNameList_c] = "silver:langutil:ast";
	}

	private static void initProductionAttributeDefinitions(){
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DatatypeFwd_sv_9_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_34_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_48_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_60_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_62_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_67_0.initProductionAttributeDefinitions();
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Pp_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_69_0.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DatatypeFwd_sv_9_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_34_0 = 0;
	public static int count_inh__ON__ADTDecl_c = 0;
	public static int count_syn__ON__ADTDecl_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_40_0 = 0;
	public static int count_inh__ON__ConstructorList_c = 0;
	public static int count_syn__ON__ConstructorList_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_46_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_48_0 = 0;
	public static int count_inh__ON__Constructor_c = 0;
	public static int count_syn__ON__Constructor_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_54_0 = 0;
	public static int count_inh__ON__TypeNameList_c = 0;
	public static int count_syn__ON__TypeNameList_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_60_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_62_0 = 0;
	public static int count_inh__ON__TailTypeNameList_c = 0;
	public static int count_syn__ON__TailTypeNameList_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_67_0 = 0;
	public static int count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_p_edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_DataType_sv_69_0 = 0;
public static final int silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_ADTDecl_c = edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.count_syn__ON__ADTDecl_c++;
public static final int silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_ConstructorList_c = edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.count_syn__ON__ConstructorList_c++;
public static final int silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_Constructor_c = edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.count_syn__ON__Constructor_c++;
public static final int silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_TypeNameList_c = edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.count_syn__ON__TypeNameList_c++;
public static final int silver_langutil_ast__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_concretesyntax_TailTypeNameList_c = edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.concretesyntax.Init.count_syn__ON__TailTypeNameList_c++;
}
