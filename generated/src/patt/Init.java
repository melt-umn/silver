package patt;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		patt.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		patt.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		patt.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestNonPrim_sv_18_0.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestNonPrim_sv_19_1.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestNonPrim_sv_20_2.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_List_sv_21_3.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_List_sv_22_4.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_List_sv_33_5.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_List_sv_34_6.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_List_sv_45_7.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_List_sv_46_8.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_List_sv_58_9.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_List_sv_59_10.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_List_sv_60_11.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_List_sv_61_12.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_21_13.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_22_14.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_34_15.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_35_16.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_36_17.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_48_18.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_49_19.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_50_20.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_64_21.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_65_22.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_66_23.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_67_24.class);
		common.Decorator.applyDecorators(patt.NEcho.decorators, Pecho.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_88_25.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_89_26.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_115_27.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_118_28.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Normal_sv_187_29.class);
		common.Decorator.applyDecorators(patt.NOrdinaryNonterminal.decorators, PordinaryProduction.class);
		common.Decorator.applyDecorators(patt.NLCE.decorators, PlcUnit.class);
		common.Decorator.applyDecorators(patt.NLCE.decorators, PlcAbs.class);
		common.Decorator.applyDecorators(patt.NLCE.decorators, PlcApp.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Decoration_sv_41_30.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Decoration_sv_42_31.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Decoration_sv_43_32.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Decoration_sv_44_33.class);
		common.Decorator.applyDecorators(patt.NT.decorators, Pa.class);
		common.Decorator.applyDecorators(patt.NT.decorators, Pb.class);
		common.Decorator.applyDecorators(patt.NT.decorators, Pc.class);
		common.Decorator.applyDecorators(patt.NT.decorators, Pd.class);
		common.Decorator.applyDecorators(silver.testing.NTestSuite.decorators, Ppat_tests.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_52_34.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_53_35.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_54_36.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_55_37.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_57_38.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_58_39.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_59_40.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_60_41.class);
		common.Decorator.applyDecorators(patt.NType.decorators, PunitT.class);
		common.Decorator.applyDecorators(patt.NType.decorators, Parrow.class);
		common.Decorator.applyDecorators(patt.NEq.decorators, Peq.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_107_42.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_108_43.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_109_44.class);
		common.Decorator.applyDecorators(patt.NExpr.decorators, Petrue.class);
		common.Decorator.applyDecorators(patt.NExpr.decorators, Peone.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_125_45.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_126_46.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_12_47.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_13_48.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_26_49.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_27_50.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_28_51.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_29_52.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_30_53.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_31_54.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_32_55.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_46_56.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_47_57.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_48_58.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_49_59.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_52_60.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_65_61.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_66_62.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_67_63.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_68_64.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_69_65.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_82_66.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_83_67.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_84_68.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_85_69.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_86_70.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_99_71.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_100_72.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_101_73.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_102_74.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_103_75.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_104_76.class);
		common.Decorator.applyDecorators(patt.NMyTriple.decorators, Pmytriple.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_120_77.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_121_78.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_134_79.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_135_80.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_136_81.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_137_82.class);
		common.Decorator.applyDecorators(patt.NExistential.decorators, Pexistentialprod.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Existential_sv_18_83.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Existential_sv_19_84.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Existential_sv_20_85.class);
	}

	private static void setupInheritedAttributes(){
		patt.PgeneratedTest_TestNonPrim_sv_18_0.occurs_local[patt.Init.value__ON__patt_generatedTest_TestNonPrim_sv_18_0] = "patt:generatedTest_TestNonPrim_sv_18_0:local:value";
		patt.PgeneratedTest_TestNonPrim_sv_18_0.occurs_local[patt.Init.expected__ON__patt_generatedTest_TestNonPrim_sv_18_0] = "patt:generatedTest_TestNonPrim_sv_18_0:local:expected";
		patt.PgeneratedTest_TestNonPrim_sv_19_1.occurs_local[patt.Init.value__ON__patt_generatedTest_TestNonPrim_sv_19_1] = "patt:generatedTest_TestNonPrim_sv_19_1:local:value";
		patt.PgeneratedTest_TestNonPrim_sv_19_1.occurs_local[patt.Init.expected__ON__patt_generatedTest_TestNonPrim_sv_19_1] = "patt:generatedTest_TestNonPrim_sv_19_1:local:expected";
		patt.PgeneratedTest_TestNonPrim_sv_20_2.occurs_local[patt.Init.value__ON__patt_generatedTest_TestNonPrim_sv_20_2] = "patt:generatedTest_TestNonPrim_sv_20_2:local:value";
		patt.PgeneratedTest_TestNonPrim_sv_20_2.occurs_local[patt.Init.expected__ON__patt_generatedTest_TestNonPrim_sv_20_2] = "patt:generatedTest_TestNonPrim_sv_20_2:local:expected";
		patt.PgeneratedTest_List_sv_21_3.occurs_local[patt.Init.value__ON__patt_generatedTest_List_sv_21_3] = "patt:generatedTest_List_sv_21_3:local:value";
		patt.PgeneratedTest_List_sv_21_3.occurs_local[patt.Init.expected__ON__patt_generatedTest_List_sv_21_3] = "patt:generatedTest_List_sv_21_3:local:expected";
		patt.PgeneratedTest_List_sv_22_4.occurs_local[patt.Init.value__ON__patt_generatedTest_List_sv_22_4] = "patt:generatedTest_List_sv_22_4:local:value";
		patt.PgeneratedTest_List_sv_22_4.occurs_local[patt.Init.expected__ON__patt_generatedTest_List_sv_22_4] = "patt:generatedTest_List_sv_22_4:local:expected";
		patt.PgeneratedTest_List_sv_33_5.occurs_local[patt.Init.value__ON__patt_generatedTest_List_sv_33_5] = "patt:generatedTest_List_sv_33_5:local:value";
		patt.PgeneratedTest_List_sv_33_5.occurs_local[patt.Init.expected__ON__patt_generatedTest_List_sv_33_5] = "patt:generatedTest_List_sv_33_5:local:expected";
		patt.PgeneratedTest_List_sv_34_6.occurs_local[patt.Init.value__ON__patt_generatedTest_List_sv_34_6] = "patt:generatedTest_List_sv_34_6:local:value";
		patt.PgeneratedTest_List_sv_34_6.occurs_local[patt.Init.expected__ON__patt_generatedTest_List_sv_34_6] = "patt:generatedTest_List_sv_34_6:local:expected";
		patt.PgeneratedTest_List_sv_45_7.occurs_local[patt.Init.value__ON__patt_generatedTest_List_sv_45_7] = "patt:generatedTest_List_sv_45_7:local:value";
		patt.PgeneratedTest_List_sv_45_7.occurs_local[patt.Init.expected__ON__patt_generatedTest_List_sv_45_7] = "patt:generatedTest_List_sv_45_7:local:expected";
		patt.PgeneratedTest_List_sv_46_8.occurs_local[patt.Init.value__ON__patt_generatedTest_List_sv_46_8] = "patt:generatedTest_List_sv_46_8:local:value";
		patt.PgeneratedTest_List_sv_46_8.occurs_local[patt.Init.expected__ON__patt_generatedTest_List_sv_46_8] = "patt:generatedTest_List_sv_46_8:local:expected";
		patt.PgeneratedTest_List_sv_58_9.occurs_local[patt.Init.value__ON__patt_generatedTest_List_sv_58_9] = "patt:generatedTest_List_sv_58_9:local:value";
		patt.PgeneratedTest_List_sv_58_9.occurs_local[patt.Init.expected__ON__patt_generatedTest_List_sv_58_9] = "patt:generatedTest_List_sv_58_9:local:expected";
		patt.PgeneratedTest_List_sv_59_10.occurs_local[patt.Init.value__ON__patt_generatedTest_List_sv_59_10] = "patt:generatedTest_List_sv_59_10:local:value";
		patt.PgeneratedTest_List_sv_59_10.occurs_local[patt.Init.expected__ON__patt_generatedTest_List_sv_59_10] = "patt:generatedTest_List_sv_59_10:local:expected";
		patt.PgeneratedTest_List_sv_60_11.occurs_local[patt.Init.value__ON__patt_generatedTest_List_sv_60_11] = "patt:generatedTest_List_sv_60_11:local:value";
		patt.PgeneratedTest_List_sv_60_11.occurs_local[patt.Init.expected__ON__patt_generatedTest_List_sv_60_11] = "patt:generatedTest_List_sv_60_11:local:expected";
		patt.PgeneratedTest_List_sv_61_12.occurs_local[patt.Init.value__ON__patt_generatedTest_List_sv_61_12] = "patt:generatedTest_List_sv_61_12:local:value";
		patt.PgeneratedTest_List_sv_61_12.occurs_local[patt.Init.expected__ON__patt_generatedTest_List_sv_61_12] = "patt:generatedTest_List_sv_61_12:local:expected";
		patt.PgeneratedTest_Normal_sv_21_13.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_21_13] = "patt:generatedTest_Normal_sv_21_13:local:value";
		patt.PgeneratedTest_Normal_sv_21_13.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_21_13] = "patt:generatedTest_Normal_sv_21_13:local:expected";
		patt.PgeneratedTest_Normal_sv_22_14.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_22_14] = "patt:generatedTest_Normal_sv_22_14:local:value";
		patt.PgeneratedTest_Normal_sv_22_14.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_22_14] = "patt:generatedTest_Normal_sv_22_14:local:expected";
		patt.PgeneratedTest_Normal_sv_34_15.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_34_15] = "patt:generatedTest_Normal_sv_34_15:local:value";
		patt.PgeneratedTest_Normal_sv_34_15.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_34_15] = "patt:generatedTest_Normal_sv_34_15:local:expected";
		patt.PgeneratedTest_Normal_sv_35_16.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_35_16] = "patt:generatedTest_Normal_sv_35_16:local:value";
		patt.PgeneratedTest_Normal_sv_35_16.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_35_16] = "patt:generatedTest_Normal_sv_35_16:local:expected";
		patt.PgeneratedTest_Normal_sv_36_17.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_36_17] = "patt:generatedTest_Normal_sv_36_17:local:value";
		patt.PgeneratedTest_Normal_sv_36_17.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_36_17] = "patt:generatedTest_Normal_sv_36_17:local:expected";
		patt.PgeneratedTest_Normal_sv_48_18.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_48_18] = "patt:generatedTest_Normal_sv_48_18:local:value";
		patt.PgeneratedTest_Normal_sv_48_18.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_48_18] = "patt:generatedTest_Normal_sv_48_18:local:expected";
		patt.PgeneratedTest_Normal_sv_49_19.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_49_19] = "patt:generatedTest_Normal_sv_49_19:local:value";
		patt.PgeneratedTest_Normal_sv_49_19.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_49_19] = "patt:generatedTest_Normal_sv_49_19:local:expected";
		patt.PgeneratedTest_Normal_sv_50_20.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_50_20] = "patt:generatedTest_Normal_sv_50_20:local:value";
		patt.PgeneratedTest_Normal_sv_50_20.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_50_20] = "patt:generatedTest_Normal_sv_50_20:local:expected";
		patt.PgeneratedTest_Normal_sv_64_21.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_64_21] = "patt:generatedTest_Normal_sv_64_21:local:value";
		patt.PgeneratedTest_Normal_sv_64_21.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_64_21] = "patt:generatedTest_Normal_sv_64_21:local:expected";
		patt.PgeneratedTest_Normal_sv_65_22.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_65_22] = "patt:generatedTest_Normal_sv_65_22:local:value";
		patt.PgeneratedTest_Normal_sv_65_22.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_65_22] = "patt:generatedTest_Normal_sv_65_22:local:expected";
		patt.PgeneratedTest_Normal_sv_66_23.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_66_23] = "patt:generatedTest_Normal_sv_66_23:local:value";
		patt.PgeneratedTest_Normal_sv_66_23.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_66_23] = "patt:generatedTest_Normal_sv_66_23:local:expected";
		patt.PgeneratedTest_Normal_sv_67_24.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_67_24] = "patt:generatedTest_Normal_sv_67_24:local:value";
		patt.PgeneratedTest_Normal_sv_67_24.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_67_24] = "patt:generatedTest_Normal_sv_67_24:local:expected";
		patt.NEcho.occurs_inh[patt.Init.patt_input__ON__patt_Echo] = "patt:input";
		patt.NEcho.occurs_syn[patt.Init.patt_output__ON__patt_Echo] = "patt:output";
		patt.PgeneratedTest_Normal_sv_88_25.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_88_25] = "patt:generatedTest_Normal_sv_88_25:local:value";
		patt.PgeneratedTest_Normal_sv_88_25.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_88_25] = "patt:generatedTest_Normal_sv_88_25:local:expected";
		patt.PgeneratedTest_Normal_sv_89_26.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_89_26] = "patt:generatedTest_Normal_sv_89_26:local:value";
		patt.PgeneratedTest_Normal_sv_89_26.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_89_26] = "patt:generatedTest_Normal_sv_89_26:local:expected";
		patt.PgeneratedTest_Normal_sv_115_27.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_115_27] = "patt:generatedTest_Normal_sv_115_27:local:value";
		patt.PgeneratedTest_Normal_sv_115_27.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_115_27] = "patt:generatedTest_Normal_sv_115_27:local:expected";
		patt.PgeneratedTest_Normal_sv_118_28.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_118_28] = "patt:generatedTest_Normal_sv_118_28:local:value";
		patt.PgeneratedTest_Normal_sv_118_28.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_118_28] = "patt:generatedTest_Normal_sv_118_28:local:expected";
		patt.PgeneratedTest_Normal_sv_187_29.occurs_local[patt.Init.value__ON__patt_generatedTest_Normal_sv_187_29] = "patt:generatedTest_Normal_sv_187_29:local:value";
		patt.PgeneratedTest_Normal_sv_187_29.occurs_local[patt.Init.expected__ON__patt_generatedTest_Normal_sv_187_29] = "patt:generatedTest_Normal_sv_187_29:local:expected";
		patt.NOrdinaryNonterminal.occurs_syn[patt.Init.patt_ordinaryAttribute__ON__patt_OrdinaryNonterminal] = "patt:ordinaryAttribute";
		patt.NLCE.occurs_syn[patt.Init.patt_value__ON__patt_LCE] = "patt:value";
		patt.PgeneratedTest_Decoration_sv_41_30.occurs_local[patt.Init.value__ON__patt_generatedTest_Decoration_sv_41_30] = "patt:generatedTest_Decoration_sv_41_30:local:value";
		patt.PgeneratedTest_Decoration_sv_41_30.occurs_local[patt.Init.expected__ON__patt_generatedTest_Decoration_sv_41_30] = "patt:generatedTest_Decoration_sv_41_30:local:expected";
		patt.PgeneratedTest_Decoration_sv_42_31.occurs_local[patt.Init.value__ON__patt_generatedTest_Decoration_sv_42_31] = "patt:generatedTest_Decoration_sv_42_31:local:value";
		patt.PgeneratedTest_Decoration_sv_42_31.occurs_local[patt.Init.expected__ON__patt_generatedTest_Decoration_sv_42_31] = "patt:generatedTest_Decoration_sv_42_31:local:expected";
		patt.PgeneratedTest_Decoration_sv_43_32.occurs_local[patt.Init.value__ON__patt_generatedTest_Decoration_sv_43_32] = "patt:generatedTest_Decoration_sv_43_32:local:value";
		patt.PgeneratedTest_Decoration_sv_43_32.occurs_local[patt.Init.expected__ON__patt_generatedTest_Decoration_sv_43_32] = "patt:generatedTest_Decoration_sv_43_32:local:expected";
		patt.PgeneratedTest_Decoration_sv_44_33.occurs_local[patt.Init.value__ON__patt_generatedTest_Decoration_sv_44_33] = "patt:generatedTest_Decoration_sv_44_33:local:value";
		patt.PgeneratedTest_Decoration_sv_44_33.occurs_local[patt.Init.expected__ON__patt_generatedTest_Decoration_sv_44_33] = "patt:generatedTest_Decoration_sv_44_33:local:expected";
		//	local attribute testResults::TestSuite;
		patt.Pmain.localInheritedAttributes[patt.Init.testResults__ON__patt_main] = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];
		patt.Pmain.occurs_local[patt.Init.testResults__ON__patt_main] = "patt:main:local:testResults";
		patt.Ppat_tests.occurs_local[patt.Init.testsToPerform__ON__patt_pat_tests] = "patt:pat_tests:local:testsToPerform";
		patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		patt.PgeneratedTest_Test_sv_52_34.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_52_34] = "patt:generatedTest_Test_sv_52_34:local:value";
		patt.PgeneratedTest_Test_sv_52_34.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_52_34] = "patt:generatedTest_Test_sv_52_34:local:expected";
		patt.PgeneratedTest_Test_sv_53_35.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_53_35] = "patt:generatedTest_Test_sv_53_35:local:value";
		patt.PgeneratedTest_Test_sv_53_35.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_53_35] = "patt:generatedTest_Test_sv_53_35:local:expected";
		patt.PgeneratedTest_Test_sv_54_36.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_54_36] = "patt:generatedTest_Test_sv_54_36:local:value";
		patt.PgeneratedTest_Test_sv_54_36.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_54_36] = "patt:generatedTest_Test_sv_54_36:local:expected";
		patt.PgeneratedTest_Test_sv_55_37.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_55_37] = "patt:generatedTest_Test_sv_55_37:local:value";
		patt.PgeneratedTest_Test_sv_55_37.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_55_37] = "patt:generatedTest_Test_sv_55_37:local:expected";
		patt.PgeneratedTest_Test_sv_57_38.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_57_38] = "patt:generatedTest_Test_sv_57_38:local:value";
		patt.PgeneratedTest_Test_sv_57_38.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_57_38] = "patt:generatedTest_Test_sv_57_38:local:expected";
		patt.PgeneratedTest_Test_sv_58_39.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_58_39] = "patt:generatedTest_Test_sv_58_39:local:value";
		patt.PgeneratedTest_Test_sv_58_39.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_58_39] = "patt:generatedTest_Test_sv_58_39:local:expected";
		patt.PgeneratedTest_Test_sv_59_40.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_59_40] = "patt:generatedTest_Test_sv_59_40:local:value";
		patt.PgeneratedTest_Test_sv_59_40.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_59_40] = "patt:generatedTest_Test_sv_59_40:local:expected";
		patt.PgeneratedTest_Test_sv_60_41.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_60_41] = "patt:generatedTest_Test_sv_60_41:local:value";
		patt.PgeneratedTest_Test_sv_60_41.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_60_41] = "patt:generatedTest_Test_sv_60_41:local:expected";
		patt.PgeneratedTest_Test_sv_107_42.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_107_42] = "patt:generatedTest_Test_sv_107_42:local:value";
		patt.PgeneratedTest_Test_sv_107_42.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_107_42] = "patt:generatedTest_Test_sv_107_42:local:expected";
		patt.PgeneratedTest_Test_sv_108_43.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_108_43] = "patt:generatedTest_Test_sv_108_43:local:value";
		patt.PgeneratedTest_Test_sv_108_43.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_108_43] = "patt:generatedTest_Test_sv_108_43:local:expected";
		patt.PgeneratedTest_Test_sv_109_44.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_109_44] = "patt:generatedTest_Test_sv_109_44:local:value";
		patt.PgeneratedTest_Test_sv_109_44.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_109_44] = "patt:generatedTest_Test_sv_109_44:local:expected";
		patt.PgeneratedTest_Test_sv_125_45.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_125_45] = "patt:generatedTest_Test_sv_125_45:local:value";
		patt.PgeneratedTest_Test_sv_125_45.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_125_45] = "patt:generatedTest_Test_sv_125_45:local:expected";
		patt.PgeneratedTest_Test_sv_126_46.occurs_local[patt.Init.value__ON__patt_generatedTest_Test_sv_126_46] = "patt:generatedTest_Test_sv_126_46:local:value";
		patt.PgeneratedTest_Test_sv_126_46.occurs_local[patt.Init.expected__ON__patt_generatedTest_Test_sv_126_46] = "patt:generatedTest_Test_sv_126_46:local:expected";
		patt.PgeneratedTest_Basics_sv_12_47.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_12_47] = "patt:generatedTest_Basics_sv_12_47:local:value";
		patt.PgeneratedTest_Basics_sv_12_47.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_12_47] = "patt:generatedTest_Basics_sv_12_47:local:expected";
		patt.PgeneratedTest_Basics_sv_13_48.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_13_48] = "patt:generatedTest_Basics_sv_13_48:local:value";
		patt.PgeneratedTest_Basics_sv_13_48.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_13_48] = "patt:generatedTest_Basics_sv_13_48:local:expected";
		patt.PgeneratedTest_Basics_sv_26_49.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_26_49] = "patt:generatedTest_Basics_sv_26_49:local:value";
		patt.PgeneratedTest_Basics_sv_26_49.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_26_49] = "patt:generatedTest_Basics_sv_26_49:local:expected";
		patt.PgeneratedTest_Basics_sv_27_50.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_27_50] = "patt:generatedTest_Basics_sv_27_50:local:value";
		patt.PgeneratedTest_Basics_sv_27_50.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_27_50] = "patt:generatedTest_Basics_sv_27_50:local:expected";
		patt.PgeneratedTest_Basics_sv_28_51.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_28_51] = "patt:generatedTest_Basics_sv_28_51:local:value";
		patt.PgeneratedTest_Basics_sv_28_51.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_28_51] = "patt:generatedTest_Basics_sv_28_51:local:expected";
		patt.PgeneratedTest_Basics_sv_29_52.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_29_52] = "patt:generatedTest_Basics_sv_29_52:local:value";
		patt.PgeneratedTest_Basics_sv_29_52.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_29_52] = "patt:generatedTest_Basics_sv_29_52:local:expected";
		patt.PgeneratedTest_Basics_sv_30_53.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_30_53] = "patt:generatedTest_Basics_sv_30_53:local:value";
		patt.PgeneratedTest_Basics_sv_30_53.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_30_53] = "patt:generatedTest_Basics_sv_30_53:local:expected";
		patt.PgeneratedTest_Basics_sv_31_54.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_31_54] = "patt:generatedTest_Basics_sv_31_54:local:value";
		patt.PgeneratedTest_Basics_sv_31_54.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_31_54] = "patt:generatedTest_Basics_sv_31_54:local:expected";
		patt.PgeneratedTest_Basics_sv_32_55.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_32_55] = "patt:generatedTest_Basics_sv_32_55:local:value";
		patt.PgeneratedTest_Basics_sv_32_55.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_32_55] = "patt:generatedTest_Basics_sv_32_55:local:expected";
		patt.PgeneratedTest_Basics_sv_46_56.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_46_56] = "patt:generatedTest_Basics_sv_46_56:local:value";
		patt.PgeneratedTest_Basics_sv_46_56.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_46_56] = "patt:generatedTest_Basics_sv_46_56:local:expected";
		patt.PgeneratedTest_Basics_sv_47_57.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_47_57] = "patt:generatedTest_Basics_sv_47_57:local:value";
		patt.PgeneratedTest_Basics_sv_47_57.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_47_57] = "patt:generatedTest_Basics_sv_47_57:local:expected";
		patt.PgeneratedTest_Basics_sv_48_58.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_48_58] = "patt:generatedTest_Basics_sv_48_58:local:value";
		patt.PgeneratedTest_Basics_sv_48_58.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_48_58] = "patt:generatedTest_Basics_sv_48_58:local:expected";
		patt.PgeneratedTest_Basics_sv_49_59.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_49_59] = "patt:generatedTest_Basics_sv_49_59:local:value";
		patt.PgeneratedTest_Basics_sv_49_59.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_49_59] = "patt:generatedTest_Basics_sv_49_59:local:expected";
		patt.PgeneratedTest_Basics_sv_52_60.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_52_60] = "patt:generatedTest_Basics_sv_52_60:local:value";
		patt.PgeneratedTest_Basics_sv_52_60.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_52_60] = "patt:generatedTest_Basics_sv_52_60:local:expected";
		patt.PgeneratedTest_Basics_sv_65_61.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_65_61] = "patt:generatedTest_Basics_sv_65_61:local:value";
		patt.PgeneratedTest_Basics_sv_65_61.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_65_61] = "patt:generatedTest_Basics_sv_65_61:local:expected";
		patt.PgeneratedTest_Basics_sv_66_62.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_66_62] = "patt:generatedTest_Basics_sv_66_62:local:value";
		patt.PgeneratedTest_Basics_sv_66_62.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_66_62] = "patt:generatedTest_Basics_sv_66_62:local:expected";
		patt.PgeneratedTest_Basics_sv_67_63.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_67_63] = "patt:generatedTest_Basics_sv_67_63:local:value";
		patt.PgeneratedTest_Basics_sv_67_63.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_67_63] = "patt:generatedTest_Basics_sv_67_63:local:expected";
		patt.PgeneratedTest_Basics_sv_68_64.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_68_64] = "patt:generatedTest_Basics_sv_68_64:local:value";
		patt.PgeneratedTest_Basics_sv_68_64.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_68_64] = "patt:generatedTest_Basics_sv_68_64:local:expected";
		patt.PgeneratedTest_Basics_sv_69_65.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_69_65] = "patt:generatedTest_Basics_sv_69_65:local:value";
		patt.PgeneratedTest_Basics_sv_69_65.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_69_65] = "patt:generatedTest_Basics_sv_69_65:local:expected";
		patt.PgeneratedTest_Basics_sv_82_66.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_82_66] = "patt:generatedTest_Basics_sv_82_66:local:value";
		patt.PgeneratedTest_Basics_sv_82_66.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_82_66] = "patt:generatedTest_Basics_sv_82_66:local:expected";
		patt.PgeneratedTest_Basics_sv_83_67.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_83_67] = "patt:generatedTest_Basics_sv_83_67:local:value";
		patt.PgeneratedTest_Basics_sv_83_67.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_83_67] = "patt:generatedTest_Basics_sv_83_67:local:expected";
		patt.PgeneratedTest_Basics_sv_84_68.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_84_68] = "patt:generatedTest_Basics_sv_84_68:local:value";
		patt.PgeneratedTest_Basics_sv_84_68.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_84_68] = "patt:generatedTest_Basics_sv_84_68:local:expected";
		patt.PgeneratedTest_Basics_sv_85_69.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_85_69] = "patt:generatedTest_Basics_sv_85_69:local:value";
		patt.PgeneratedTest_Basics_sv_85_69.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_85_69] = "patt:generatedTest_Basics_sv_85_69:local:expected";
		patt.PgeneratedTest_Basics_sv_86_70.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_86_70] = "patt:generatedTest_Basics_sv_86_70:local:value";
		patt.PgeneratedTest_Basics_sv_86_70.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_86_70] = "patt:generatedTest_Basics_sv_86_70:local:expected";
		patt.PgeneratedTest_Basics_sv_99_71.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_99_71] = "patt:generatedTest_Basics_sv_99_71:local:value";
		patt.PgeneratedTest_Basics_sv_99_71.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_99_71] = "patt:generatedTest_Basics_sv_99_71:local:expected";
		patt.PgeneratedTest_Basics_sv_100_72.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_100_72] = "patt:generatedTest_Basics_sv_100_72:local:value";
		patt.PgeneratedTest_Basics_sv_100_72.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_100_72] = "patt:generatedTest_Basics_sv_100_72:local:expected";
		patt.PgeneratedTest_Basics_sv_101_73.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_101_73] = "patt:generatedTest_Basics_sv_101_73:local:value";
		patt.PgeneratedTest_Basics_sv_101_73.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_101_73] = "patt:generatedTest_Basics_sv_101_73:local:expected";
		patt.PgeneratedTest_Basics_sv_102_74.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_102_74] = "patt:generatedTest_Basics_sv_102_74:local:value";
		patt.PgeneratedTest_Basics_sv_102_74.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_102_74] = "patt:generatedTest_Basics_sv_102_74:local:expected";
		patt.PgeneratedTest_Basics_sv_103_75.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_103_75] = "patt:generatedTest_Basics_sv_103_75:local:value";
		patt.PgeneratedTest_Basics_sv_103_75.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_103_75] = "patt:generatedTest_Basics_sv_103_75:local:expected";
		patt.PgeneratedTest_Basics_sv_104_76.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_104_76] = "patt:generatedTest_Basics_sv_104_76:local:value";
		patt.PgeneratedTest_Basics_sv_104_76.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_104_76] = "patt:generatedTest_Basics_sv_104_76:local:expected";
		patt.PgeneratedTest_Basics_sv_120_77.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_120_77] = "patt:generatedTest_Basics_sv_120_77:local:value";
		patt.PgeneratedTest_Basics_sv_120_77.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_120_77] = "patt:generatedTest_Basics_sv_120_77:local:expected";
		patt.PgeneratedTest_Basics_sv_121_78.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_121_78] = "patt:generatedTest_Basics_sv_121_78:local:value";
		patt.PgeneratedTest_Basics_sv_121_78.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_121_78] = "patt:generatedTest_Basics_sv_121_78:local:expected";
		patt.PgeneratedTest_Basics_sv_134_79.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_134_79] = "patt:generatedTest_Basics_sv_134_79:local:value";
		patt.PgeneratedTest_Basics_sv_134_79.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_134_79] = "patt:generatedTest_Basics_sv_134_79:local:expected";
		patt.PgeneratedTest_Basics_sv_135_80.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_135_80] = "patt:generatedTest_Basics_sv_135_80:local:value";
		patt.PgeneratedTest_Basics_sv_135_80.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_135_80] = "patt:generatedTest_Basics_sv_135_80:local:expected";
		patt.PgeneratedTest_Basics_sv_136_81.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_136_81] = "patt:generatedTest_Basics_sv_136_81:local:value";
		patt.PgeneratedTest_Basics_sv_136_81.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_136_81] = "patt:generatedTest_Basics_sv_136_81:local:expected";
		patt.PgeneratedTest_Basics_sv_137_82.occurs_local[patt.Init.value__ON__patt_generatedTest_Basics_sv_137_82] = "patt:generatedTest_Basics_sv_137_82:local:value";
		patt.PgeneratedTest_Basics_sv_137_82.occurs_local[patt.Init.expected__ON__patt_generatedTest_Basics_sv_137_82] = "patt:generatedTest_Basics_sv_137_82:local:expected";
		patt.PgeneratedTest_Existential_sv_18_83.occurs_local[patt.Init.value__ON__patt_generatedTest_Existential_sv_18_83] = "patt:generatedTest_Existential_sv_18_83:local:value";
		patt.PgeneratedTest_Existential_sv_18_83.occurs_local[patt.Init.expected__ON__patt_generatedTest_Existential_sv_18_83] = "patt:generatedTest_Existential_sv_18_83:local:expected";
		patt.PgeneratedTest_Existential_sv_19_84.occurs_local[patt.Init.value__ON__patt_generatedTest_Existential_sv_19_84] = "patt:generatedTest_Existential_sv_19_84:local:value";
		patt.PgeneratedTest_Existential_sv_19_84.occurs_local[patt.Init.expected__ON__patt_generatedTest_Existential_sv_19_84] = "patt:generatedTest_Existential_sv_19_84:local:expected";
		patt.PgeneratedTest_Existential_sv_20_85.occurs_local[patt.Init.value__ON__patt_generatedTest_Existential_sv_20_85] = "patt:generatedTest_Existential_sv_20_85:local:value";
		patt.PgeneratedTest_Existential_sv_20_85.occurs_local[patt.Init.expected__ON__patt_generatedTest_Existential_sv_20_85] = "patt:generatedTest_Existential_sv_20_85:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION typeEqualsNonPrim Maybe<Eq<a b>> ::= ta::Type<a> tb::Type<b> 
		patt.PgeneratedTest_TestNonPrim_sv_18_0.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_TestNonPrim_sv_18_0(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_TestNonPrim_sv_18_0()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_TestNonPrim_sv_19_1.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_TestNonPrim_sv_19_1(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_TestNonPrim_sv_19_1()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_TestNonPrim_sv_20_2.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_TestNonPrim_sv_20_2(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_TestNonPrim_sv_20_2()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION mynull Boolean ::= s::[a] 
		patt.PgeneratedTest_List_sv_21_3.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_List_sv_21_3(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_List_sv_21_3()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_List_sv_22_4.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_List_sv_22_4(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_List_sv_22_4()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION myhead a ::= s::[a] 
		patt.PgeneratedTest_List_sv_33_5.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_List_sv_33_5(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_List_sv_33_5()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_List_sv_34_6.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_List_sv_34_6(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_List_sv_34_6()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION mytail [a] ::= s::[a] 
		patt.PgeneratedTest_List_sv_45_7.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_List_sv_45_7(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_List_sv_45_7()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_List_sv_46_8.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_List_sv_46_8(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_List_sv_46_8()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION mysafedoubletail [a] ::= s::[a] 
		patt.PgeneratedTest_List_sv_58_9.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_List_sv_58_9(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_List_sv_58_9()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_List_sv_59_10.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_List_sv_59_10(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_List_sv_59_10()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_List_sv_60_11.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_List_sv_60_11(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_List_sv_60_11()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_List_sv_61_12.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_List_sv_61_12(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_List_sv_61_12()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION stringmatching Boolean ::= s::String 
		//FUNCTION stringmatchingCoqStyleVBarSyntax Boolean ::= s::String 
		patt.PgeneratedTest_Normal_sv_21_13.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_21_13(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_21_13()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Normal_sv_22_14.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_22_14(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_22_14()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION smaybematching Boolean ::= s::Maybe<String> 
		patt.PgeneratedTest_Normal_sv_34_15.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_34_15(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_34_15()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Normal_sv_35_16.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_35_16(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_35_16()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Normal_sv_36_17.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_36_17(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_36_17()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION smaybemaybematching Boolean ::= s::Maybe<Maybe<String>> 
		patt.PgeneratedTest_Normal_sv_48_18.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_48_18(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_48_18()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Normal_sv_49_19.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_49_19(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_49_19()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Normal_sv_50_20.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_50_20(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_50_20()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION pairmatching Boolean ::= p::Pair<Boolean Boolean> 
		patt.PgeneratedTest_Normal_sv_64_21.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_64_21(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_64_21()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Normal_sv_65_22.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_65_22(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_65_22()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Normal_sv_66_23.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_66_23(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_66_23()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Normal_sv_67_24.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_67_24(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_67_24()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.Pecho.initProductionAttributeDefinitions();
		//FUNCTION echotest a ::= s::a 
		patt.PgeneratedTest_Normal_sv_88_25.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_88_25(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_88_25()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Normal_sv_89_26.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_89_26(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_89_26()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION lookattees [Boolean] ::= l::[T] 
		//FUNCTION indivtee Boolean ::= l::T 
		patt.PgeneratedTest_Normal_sv_115_27.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_115_27(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_115_27()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Normal_sv_118_28.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_118_28(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_118_28()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Normal_sv_187_29.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Normal_sv_187_29(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Normal_sv_187_29()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PordinaryProduction.initProductionAttributeDefinitions();
		//FUNCTION ordinaryFunction String ::= undecoratedFirst::OrdinaryNonterminal decoratedSecond::Decorated OrdinaryNonterminal 
		//FUNCTION ordinaryFunction2 String ::= undecoratedFirst::OrdinaryNonterminal attrSecond::String 
		//FUNCTION doesThisGenerateSilverErrors String ::= d::OrdinaryNonterminal 
		//FUNCTION doesThisGenerateJavaCodeWithErrors String ::= d::OrdinaryNonterminal 
		patt.PlcUnit.initProductionAttributeDefinitions();
		patt.PlcAbs.initProductionAttributeDefinitions();
		patt.PlcApp.initProductionAttributeDefinitions();
		//FUNCTION beta LCE ::= e::LCE 
		//FUNCTION betaContrived LCE ::= e::Decorated LCE 
		patt.PgeneratedTest_Decoration_sv_41_30.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Decoration_sv_41_30(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Decoration_sv_41_30()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Decoration_sv_42_31.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Decoration_sv_42_31(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Decoration_sv_42_31()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Decoration_sv_43_32.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Decoration_sv_43_32(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Decoration_sv_43_32()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Decoration_sv_44_33.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Decoration_sv_44_33(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Decoration_sv_44_33()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.Pa.initProductionAttributeDefinitions();
		patt.Pb.initProductionAttributeDefinitions();
		patt.Pc.initProductionAttributeDefinitions();
		patt.Pd.initProductionAttributeDefinitions();
		//FUNCTION tName String ::= t::T 
		//FUNCTION tNameAll String ::= t::T 
		//FUNCTION main IOVal<Integer> ::= [String] mainIO::core:IO 
		// testResults = pat_tests(,)
		patt.Pmain.localAttributes[patt.Init.testResults__ON__patt_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.testing.NTestSuite)new patt.Ppat_tests()); } };
		// testResults.ioIn = mainIO
		patt.Pmain.localInheritedAttributes[patt.Init.testResults__ON__patt_main][silver.testing.Init.silver_testing_ioIn__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(patt.Pmain.i_mainIO)); } };
		patt.Ppat_tests.initProductionAttributeDefinitions();
		patt.PgeneratedTest_Test_sv_52_34.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_52_34(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_52_34()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Test_sv_53_35.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_53_35(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_53_35()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Test_sv_54_36.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_54_36(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_54_36()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Test_sv_55_37.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_55_37(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_55_37()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Test_sv_57_38.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_57_38(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_57_38()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Test_sv_58_39.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_58_39(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_58_39()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Test_sv_59_40.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_59_40(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_59_40()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Test_sv_60_41.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_60_41(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_60_41()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PunitT.initProductionAttributeDefinitions();
		patt.Parrow.initProductionAttributeDefinitions();
		patt.Peq.initProductionAttributeDefinitions();
		//FUNCTION typeEquals Maybe<Eq<a b>> ::= ta::Type<a> tb::Type<b> 
		patt.PgeneratedTest_Test_sv_107_42.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_107_42(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_107_42()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Test_sv_108_43.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_108_43(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_108_43()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Test_sv_109_44.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_109_44(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_109_44()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.Petrue.initProductionAttributeDefinitions();
		patt.Peone.initProductionAttributeDefinitions();
		//FUNCTION retExpr a ::= e::Expr<a> 
		patt.PgeneratedTest_Test_sv_125_45.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_125_45(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_125_45()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Test_sv_126_46.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_126_46(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Test_sv_126_46()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION basic1 Integer ::= s::Maybe<Boolean> 
		patt.PgeneratedTest_Basics_sv_12_47.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_12_47(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_12_47()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_13_48.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_13_48(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_13_48()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION basic2 Boolean ::= s::Pair<Maybe<Boolean> Maybe<Pair<Boolean String>>> 
		patt.PgeneratedTest_Basics_sv_26_49.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_26_49(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_26_49()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_27_50.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_27_50(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_27_50()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_28_51.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_28_51(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_28_51()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_29_52.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_29_52(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_29_52()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_30_53.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_30_53(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_30_53()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_31_54.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_31_54(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_31_54()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_32_55.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_32_55(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_32_55()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION basic3 String ::= s::Maybe<String> t::Maybe<String> u::Maybe<String> 
		patt.PgeneratedTest_Basics_sv_46_56.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_46_56(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_46_56()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_47_57.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_47_57(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_47_57()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_48_58.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_48_58(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_48_58()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_49_59.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_49_59(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_49_59()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_52_60.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_52_60(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_52_60()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION basic4 Integer ::= p::Pair<Integer Maybe<Integer>> 
		patt.PgeneratedTest_Basics_sv_65_61.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_65_61(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_65_61()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_66_62.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_66_62(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_66_62()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_67_63.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_67_63(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_67_63()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_68_64.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_68_64(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_68_64()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_69_65.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_69_65(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_69_65()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION basic5 Integer ::= p::Pair<String Maybe<Integer>> 
		patt.PgeneratedTest_Basics_sv_82_66.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_82_66(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_82_66()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_83_67.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_83_67(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_83_67()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_84_68.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_84_68(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_84_68()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_85_69.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_85_69(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_85_69()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_86_70.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_86_70(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_86_70()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION basic6 Integer ::= p::Pair<String String> 
		patt.PgeneratedTest_Basics_sv_99_71.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_99_71(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_99_71()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_100_72.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_100_72(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_100_72()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_101_73.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_101_73(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_101_73()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_102_74.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_102_74(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_102_74()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_103_75.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_103_75(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_103_75()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_104_76.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_104_76(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_104_76()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.Pmytriple.initProductionAttributeDefinitions();
		//FUNCTION basic7 Integer ::= p::MyTriple<Integer Maybe<Integer> Maybe<Integer>> 
		patt.PgeneratedTest_Basics_sv_120_77.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_120_77(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_120_77()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_121_78.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_121_78(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_121_78()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION basic8 Integer ::= p::Pair<Integer Integer> 
		patt.PgeneratedTest_Basics_sv_134_79.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_134_79(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_134_79()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_135_80.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_135_80(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_135_80()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_136_81.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_136_81(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_136_81()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Basics_sv_137_82.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_137_82(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Basics_sv_137_82()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.Pexistentialprod.initProductionAttributeDefinitions();
		//FUNCTION applyExist String ::= v::Existential 
		patt.PgeneratedTest_Existential_sv_18_83.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Existential_sv_18_83(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Existential_sv_18_83()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Existential_sv_19_84.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Existential_sv_19_84(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Existential_sv_19_84()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		patt.PgeneratedTest_Existential_sv_20_85.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION pat_tests t ::= 
		// testsToPerform <- [ generatedTest_Existential_sv_20_85(,) ]
		((common.CollectionAttribute)patt.Ppat_tests.localAttributes[patt.Init.testsToPerform__ON__patt_pat_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new patt.PgeneratedTest_Existential_sv_20_85()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__patt_typeEqualsNonPrim = 0;
	public static int count_local__ON__patt_generatedTest_TestNonPrim_sv_18_0 = 0;
	public static int count_local__ON__patt_generatedTest_TestNonPrim_sv_19_1 = 0;
	public static int count_local__ON__patt_generatedTest_TestNonPrim_sv_20_2 = 0;
	public static int count_local__ON__patt_mynull = 0;
	public static int count_local__ON__patt_generatedTest_List_sv_21_3 = 0;
	public static int count_local__ON__patt_generatedTest_List_sv_22_4 = 0;
	public static int count_local__ON__patt_myhead = 0;
	public static int count_local__ON__patt_generatedTest_List_sv_33_5 = 0;
	public static int count_local__ON__patt_generatedTest_List_sv_34_6 = 0;
	public static int count_local__ON__patt_mytail = 0;
	public static int count_local__ON__patt_generatedTest_List_sv_45_7 = 0;
	public static int count_local__ON__patt_generatedTest_List_sv_46_8 = 0;
	public static int count_local__ON__patt_mysafedoubletail = 0;
	public static int count_local__ON__patt_generatedTest_List_sv_58_9 = 0;
	public static int count_local__ON__patt_generatedTest_List_sv_59_10 = 0;
	public static int count_local__ON__patt_generatedTest_List_sv_60_11 = 0;
	public static int count_local__ON__patt_generatedTest_List_sv_61_12 = 0;
	public static int count_local__ON__patt_stringmatching = 0;
	public static int count_local__ON__patt_stringmatchingCoqStyleVBarSyntax = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_21_13 = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_22_14 = 0;
	public static int count_local__ON__patt_smaybematching = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_34_15 = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_35_16 = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_36_17 = 0;
	public static int count_local__ON__patt_smaybemaybematching = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_48_18 = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_49_19 = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_50_20 = 0;
	public static int count_local__ON__patt_pairmatching = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_64_21 = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_65_22 = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_66_23 = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_67_24 = 0;
	public static int count_inh__ON__Echo = 0;
	public static int count_syn__ON__Echo = 0;
	public static int count_local__ON__patt_echo = 0;
	public static int count_local__ON__patt_echotest = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_88_25 = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_89_26 = 0;
	public static int count_local__ON__patt_lookattees = 0;
	public static int count_local__ON__patt_indivtee = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_115_27 = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_118_28 = 0;
	public static int count_local__ON__patt_generatedTest_Normal_sv_187_29 = 0;
	public static int count_inh__ON__OrdinaryNonterminal = 0;
	public static int count_syn__ON__OrdinaryNonterminal = 0;
	public static int count_local__ON__patt_ordinaryProduction = 0;
	public static int count_local__ON__patt_ordinaryFunction = 0;
	public static int count_local__ON__patt_ordinaryFunction2 = 0;
	public static int count_local__ON__patt_doesThisGenerateSilverErrors = 0;
	public static int count_local__ON__patt_doesThisGenerateJavaCodeWithErrors = 0;
	public static int count_inh__ON__LCE = 0;
	public static int count_syn__ON__LCE = 0;
	public static int count_local__ON__patt_lcUnit = 0;
	public static int count_local__ON__patt_lcAbs = 0;
	public static int count_local__ON__patt_lcApp = 0;
	public static int count_local__ON__patt_beta = 0;
	public static int count_local__ON__patt_betaContrived = 0;
	public static int count_local__ON__patt_generatedTest_Decoration_sv_41_30 = 0;
	public static int count_local__ON__patt_generatedTest_Decoration_sv_42_31 = 0;
	public static int count_local__ON__patt_generatedTest_Decoration_sv_43_32 = 0;
	public static int count_local__ON__patt_generatedTest_Decoration_sv_44_33 = 0;
	public static int count_inh__ON__T = 0;
	public static int count_syn__ON__T = 0;
	public static int count_local__ON__patt_a = 0;
	public static int count_local__ON__patt_b = 0;
	public static int count_local__ON__patt_c = 0;
	public static int count_local__ON__patt_d = 0;
	public static int count_local__ON__patt_tName = 0;
	public static int count_local__ON__patt_tNameAll = 0;
	public static int count_local__ON__patt_main = 0;
	public static int count_local__ON__patt_pat_tests = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_52_34 = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_53_35 = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_54_36 = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_55_37 = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_57_38 = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_58_39 = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_59_40 = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_60_41 = 0;
	public static int count_inh__ON__Arrow = 0;
	public static int count_syn__ON__Arrow = 0;
	public static int count_inh__ON__Type = 0;
	public static int count_syn__ON__Type = 0;
	public static int count_local__ON__patt_unitT = 0;
	public static int count_local__ON__patt_arrow = 0;
	public static int count_inh__ON__Eq = 0;
	public static int count_syn__ON__Eq = 0;
	public static int count_local__ON__patt_eq = 0;
	public static int count_local__ON__patt_typeEquals = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_107_42 = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_108_43 = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_109_44 = 0;
	public static int count_inh__ON__Expr = 0;
	public static int count_syn__ON__Expr = 0;
	public static int count_local__ON__patt_etrue = 0;
	public static int count_local__ON__patt_eone = 0;
	public static int count_local__ON__patt_retExpr = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_125_45 = 0;
	public static int count_local__ON__patt_generatedTest_Test_sv_126_46 = 0;
	public static int count_local__ON__patt_basic1 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_12_47 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_13_48 = 0;
	public static int count_local__ON__patt_basic2 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_26_49 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_27_50 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_28_51 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_29_52 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_30_53 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_31_54 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_32_55 = 0;
	public static int count_local__ON__patt_basic3 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_46_56 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_47_57 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_48_58 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_49_59 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_52_60 = 0;
	public static int count_local__ON__patt_basic4 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_65_61 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_66_62 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_67_63 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_68_64 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_69_65 = 0;
	public static int count_local__ON__patt_basic5 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_82_66 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_83_67 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_84_68 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_85_69 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_86_70 = 0;
	public static int count_local__ON__patt_basic6 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_99_71 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_100_72 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_101_73 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_102_74 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_103_75 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_104_76 = 0;
	public static int count_inh__ON__MyTriple = 0;
	public static int count_syn__ON__MyTriple = 0;
	public static int count_local__ON__patt_mytriple = 0;
	public static int count_local__ON__patt_basic7 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_120_77 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_121_78 = 0;
	public static int count_local__ON__patt_basic8 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_134_79 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_135_80 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_136_81 = 0;
	public static int count_local__ON__patt_generatedTest_Basics_sv_137_82 = 0;
	public static int count_inh__ON__Existential = 0;
	public static int count_syn__ON__Existential = 0;
	public static int count_local__ON__patt_existentialprod = 0;
	public static int count_local__ON__patt_applyExist = 0;
	public static int count_local__ON__patt_generatedTest_Existential_sv_18_83 = 0;
	public static int count_local__ON__patt_generatedTest_Existential_sv_19_84 = 0;
	public static int count_local__ON__patt_generatedTest_Existential_sv_20_85 = 0;
public static final int value__ON__patt_generatedTest_TestNonPrim_sv_18_0 = patt.Init.count_local__ON__patt_generatedTest_TestNonPrim_sv_18_0++;
public static final int expected__ON__patt_generatedTest_TestNonPrim_sv_18_0 = patt.Init.count_local__ON__patt_generatedTest_TestNonPrim_sv_18_0++;
public static final int value__ON__patt_generatedTest_TestNonPrim_sv_19_1 = patt.Init.count_local__ON__patt_generatedTest_TestNonPrim_sv_19_1++;
public static final int expected__ON__patt_generatedTest_TestNonPrim_sv_19_1 = patt.Init.count_local__ON__patt_generatedTest_TestNonPrim_sv_19_1++;
public static final int value__ON__patt_generatedTest_TestNonPrim_sv_20_2 = patt.Init.count_local__ON__patt_generatedTest_TestNonPrim_sv_20_2++;
public static final int expected__ON__patt_generatedTest_TestNonPrim_sv_20_2 = patt.Init.count_local__ON__patt_generatedTest_TestNonPrim_sv_20_2++;
public static final int value__ON__patt_generatedTest_List_sv_21_3 = patt.Init.count_local__ON__patt_generatedTest_List_sv_21_3++;
public static final int expected__ON__patt_generatedTest_List_sv_21_3 = patt.Init.count_local__ON__patt_generatedTest_List_sv_21_3++;
public static final int value__ON__patt_generatedTest_List_sv_22_4 = patt.Init.count_local__ON__patt_generatedTest_List_sv_22_4++;
public static final int expected__ON__patt_generatedTest_List_sv_22_4 = patt.Init.count_local__ON__patt_generatedTest_List_sv_22_4++;
public static final int value__ON__patt_generatedTest_List_sv_33_5 = patt.Init.count_local__ON__patt_generatedTest_List_sv_33_5++;
public static final int expected__ON__patt_generatedTest_List_sv_33_5 = patt.Init.count_local__ON__patt_generatedTest_List_sv_33_5++;
public static final int value__ON__patt_generatedTest_List_sv_34_6 = patt.Init.count_local__ON__patt_generatedTest_List_sv_34_6++;
public static final int expected__ON__patt_generatedTest_List_sv_34_6 = patt.Init.count_local__ON__patt_generatedTest_List_sv_34_6++;
public static final int value__ON__patt_generatedTest_List_sv_45_7 = patt.Init.count_local__ON__patt_generatedTest_List_sv_45_7++;
public static final int expected__ON__patt_generatedTest_List_sv_45_7 = patt.Init.count_local__ON__patt_generatedTest_List_sv_45_7++;
public static final int value__ON__patt_generatedTest_List_sv_46_8 = patt.Init.count_local__ON__patt_generatedTest_List_sv_46_8++;
public static final int expected__ON__patt_generatedTest_List_sv_46_8 = patt.Init.count_local__ON__patt_generatedTest_List_sv_46_8++;
public static final int value__ON__patt_generatedTest_List_sv_58_9 = patt.Init.count_local__ON__patt_generatedTest_List_sv_58_9++;
public static final int expected__ON__patt_generatedTest_List_sv_58_9 = patt.Init.count_local__ON__patt_generatedTest_List_sv_58_9++;
public static final int value__ON__patt_generatedTest_List_sv_59_10 = patt.Init.count_local__ON__patt_generatedTest_List_sv_59_10++;
public static final int expected__ON__patt_generatedTest_List_sv_59_10 = patt.Init.count_local__ON__patt_generatedTest_List_sv_59_10++;
public static final int value__ON__patt_generatedTest_List_sv_60_11 = patt.Init.count_local__ON__patt_generatedTest_List_sv_60_11++;
public static final int expected__ON__patt_generatedTest_List_sv_60_11 = patt.Init.count_local__ON__patt_generatedTest_List_sv_60_11++;
public static final int value__ON__patt_generatedTest_List_sv_61_12 = patt.Init.count_local__ON__patt_generatedTest_List_sv_61_12++;
public static final int expected__ON__patt_generatedTest_List_sv_61_12 = patt.Init.count_local__ON__patt_generatedTest_List_sv_61_12++;
public static final int value__ON__patt_generatedTest_Normal_sv_21_13 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_21_13++;
public static final int expected__ON__patt_generatedTest_Normal_sv_21_13 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_21_13++;
public static final int value__ON__patt_generatedTest_Normal_sv_22_14 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_22_14++;
public static final int expected__ON__patt_generatedTest_Normal_sv_22_14 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_22_14++;
public static final int value__ON__patt_generatedTest_Normal_sv_34_15 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_34_15++;
public static final int expected__ON__patt_generatedTest_Normal_sv_34_15 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_34_15++;
public static final int value__ON__patt_generatedTest_Normal_sv_35_16 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_35_16++;
public static final int expected__ON__patt_generatedTest_Normal_sv_35_16 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_35_16++;
public static final int value__ON__patt_generatedTest_Normal_sv_36_17 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_36_17++;
public static final int expected__ON__patt_generatedTest_Normal_sv_36_17 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_36_17++;
public static final int value__ON__patt_generatedTest_Normal_sv_48_18 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_48_18++;
public static final int expected__ON__patt_generatedTest_Normal_sv_48_18 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_48_18++;
public static final int value__ON__patt_generatedTest_Normal_sv_49_19 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_49_19++;
public static final int expected__ON__patt_generatedTest_Normal_sv_49_19 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_49_19++;
public static final int value__ON__patt_generatedTest_Normal_sv_50_20 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_50_20++;
public static final int expected__ON__patt_generatedTest_Normal_sv_50_20 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_50_20++;
public static final int value__ON__patt_generatedTest_Normal_sv_64_21 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_64_21++;
public static final int expected__ON__patt_generatedTest_Normal_sv_64_21 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_64_21++;
public static final int value__ON__patt_generatedTest_Normal_sv_65_22 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_65_22++;
public static final int expected__ON__patt_generatedTest_Normal_sv_65_22 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_65_22++;
public static final int value__ON__patt_generatedTest_Normal_sv_66_23 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_66_23++;
public static final int expected__ON__patt_generatedTest_Normal_sv_66_23 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_66_23++;
public static final int value__ON__patt_generatedTest_Normal_sv_67_24 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_67_24++;
public static final int expected__ON__patt_generatedTest_Normal_sv_67_24 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_67_24++;
public static final int patt_input__ON__patt_Echo = patt.Init.count_inh__ON__Echo++;
public static final int patt_output__ON__patt_Echo = patt.Init.count_syn__ON__Echo++;
public static final int value__ON__patt_generatedTest_Normal_sv_88_25 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_88_25++;
public static final int expected__ON__patt_generatedTest_Normal_sv_88_25 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_88_25++;
public static final int value__ON__patt_generatedTest_Normal_sv_89_26 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_89_26++;
public static final int expected__ON__patt_generatedTest_Normal_sv_89_26 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_89_26++;
public static final int value__ON__patt_generatedTest_Normal_sv_115_27 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_115_27++;
public static final int expected__ON__patt_generatedTest_Normal_sv_115_27 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_115_27++;
public static final int value__ON__patt_generatedTest_Normal_sv_118_28 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_118_28++;
public static final int expected__ON__patt_generatedTest_Normal_sv_118_28 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_118_28++;
public static final int value__ON__patt_generatedTest_Normal_sv_187_29 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_187_29++;
public static final int expected__ON__patt_generatedTest_Normal_sv_187_29 = patt.Init.count_local__ON__patt_generatedTest_Normal_sv_187_29++;
public static final int patt_ordinaryAttribute__ON__patt_OrdinaryNonterminal = patt.Init.count_syn__ON__OrdinaryNonterminal++;
public static final int patt_value__ON__patt_LCE = patt.Init.count_syn__ON__LCE++;
public static final int value__ON__patt_generatedTest_Decoration_sv_41_30 = patt.Init.count_local__ON__patt_generatedTest_Decoration_sv_41_30++;
public static final int expected__ON__patt_generatedTest_Decoration_sv_41_30 = patt.Init.count_local__ON__patt_generatedTest_Decoration_sv_41_30++;
public static final int value__ON__patt_generatedTest_Decoration_sv_42_31 = patt.Init.count_local__ON__patt_generatedTest_Decoration_sv_42_31++;
public static final int expected__ON__patt_generatedTest_Decoration_sv_42_31 = patt.Init.count_local__ON__patt_generatedTest_Decoration_sv_42_31++;
public static final int value__ON__patt_generatedTest_Decoration_sv_43_32 = patt.Init.count_local__ON__patt_generatedTest_Decoration_sv_43_32++;
public static final int expected__ON__patt_generatedTest_Decoration_sv_43_32 = patt.Init.count_local__ON__patt_generatedTest_Decoration_sv_43_32++;
public static final int value__ON__patt_generatedTest_Decoration_sv_44_33 = patt.Init.count_local__ON__patt_generatedTest_Decoration_sv_44_33++;
public static final int expected__ON__patt_generatedTest_Decoration_sv_44_33 = patt.Init.count_local__ON__patt_generatedTest_Decoration_sv_44_33++;
public static final int testResults__ON__patt_main = patt.Init.count_local__ON__patt_main++;
public static final int testsToPerform__ON__patt_pat_tests = patt.Init.count_local__ON__patt_pat_tests++;
public static final int value__ON__patt_generatedTest_Test_sv_52_34 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_52_34++;
public static final int expected__ON__patt_generatedTest_Test_sv_52_34 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_52_34++;
public static final int value__ON__patt_generatedTest_Test_sv_53_35 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_53_35++;
public static final int expected__ON__patt_generatedTest_Test_sv_53_35 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_53_35++;
public static final int value__ON__patt_generatedTest_Test_sv_54_36 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_54_36++;
public static final int expected__ON__patt_generatedTest_Test_sv_54_36 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_54_36++;
public static final int value__ON__patt_generatedTest_Test_sv_55_37 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_55_37++;
public static final int expected__ON__patt_generatedTest_Test_sv_55_37 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_55_37++;
public static final int value__ON__patt_generatedTest_Test_sv_57_38 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_57_38++;
public static final int expected__ON__patt_generatedTest_Test_sv_57_38 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_57_38++;
public static final int value__ON__patt_generatedTest_Test_sv_58_39 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_58_39++;
public static final int expected__ON__patt_generatedTest_Test_sv_58_39 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_58_39++;
public static final int value__ON__patt_generatedTest_Test_sv_59_40 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_59_40++;
public static final int expected__ON__patt_generatedTest_Test_sv_59_40 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_59_40++;
public static final int value__ON__patt_generatedTest_Test_sv_60_41 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_60_41++;
public static final int expected__ON__patt_generatedTest_Test_sv_60_41 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_60_41++;
public static final int value__ON__patt_generatedTest_Test_sv_107_42 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_107_42++;
public static final int expected__ON__patt_generatedTest_Test_sv_107_42 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_107_42++;
public static final int value__ON__patt_generatedTest_Test_sv_108_43 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_108_43++;
public static final int expected__ON__patt_generatedTest_Test_sv_108_43 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_108_43++;
public static final int value__ON__patt_generatedTest_Test_sv_109_44 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_109_44++;
public static final int expected__ON__patt_generatedTest_Test_sv_109_44 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_109_44++;
public static final int value__ON__patt_generatedTest_Test_sv_125_45 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_125_45++;
public static final int expected__ON__patt_generatedTest_Test_sv_125_45 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_125_45++;
public static final int value__ON__patt_generatedTest_Test_sv_126_46 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_126_46++;
public static final int expected__ON__patt_generatedTest_Test_sv_126_46 = patt.Init.count_local__ON__patt_generatedTest_Test_sv_126_46++;
public static final int value__ON__patt_generatedTest_Basics_sv_12_47 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_12_47++;
public static final int expected__ON__patt_generatedTest_Basics_sv_12_47 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_12_47++;
public static final int value__ON__patt_generatedTest_Basics_sv_13_48 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_13_48++;
public static final int expected__ON__patt_generatedTest_Basics_sv_13_48 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_13_48++;
public static final int value__ON__patt_generatedTest_Basics_sv_26_49 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_26_49++;
public static final int expected__ON__patt_generatedTest_Basics_sv_26_49 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_26_49++;
public static final int value__ON__patt_generatedTest_Basics_sv_27_50 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_27_50++;
public static final int expected__ON__patt_generatedTest_Basics_sv_27_50 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_27_50++;
public static final int value__ON__patt_generatedTest_Basics_sv_28_51 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_28_51++;
public static final int expected__ON__patt_generatedTest_Basics_sv_28_51 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_28_51++;
public static final int value__ON__patt_generatedTest_Basics_sv_29_52 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_29_52++;
public static final int expected__ON__patt_generatedTest_Basics_sv_29_52 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_29_52++;
public static final int value__ON__patt_generatedTest_Basics_sv_30_53 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_30_53++;
public static final int expected__ON__patt_generatedTest_Basics_sv_30_53 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_30_53++;
public static final int value__ON__patt_generatedTest_Basics_sv_31_54 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_31_54++;
public static final int expected__ON__patt_generatedTest_Basics_sv_31_54 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_31_54++;
public static final int value__ON__patt_generatedTest_Basics_sv_32_55 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_32_55++;
public static final int expected__ON__patt_generatedTest_Basics_sv_32_55 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_32_55++;
public static final int value__ON__patt_generatedTest_Basics_sv_46_56 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_46_56++;
public static final int expected__ON__patt_generatedTest_Basics_sv_46_56 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_46_56++;
public static final int value__ON__patt_generatedTest_Basics_sv_47_57 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_47_57++;
public static final int expected__ON__patt_generatedTest_Basics_sv_47_57 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_47_57++;
public static final int value__ON__patt_generatedTest_Basics_sv_48_58 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_48_58++;
public static final int expected__ON__patt_generatedTest_Basics_sv_48_58 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_48_58++;
public static final int value__ON__patt_generatedTest_Basics_sv_49_59 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_49_59++;
public static final int expected__ON__patt_generatedTest_Basics_sv_49_59 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_49_59++;
public static final int value__ON__patt_generatedTest_Basics_sv_52_60 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_52_60++;
public static final int expected__ON__patt_generatedTest_Basics_sv_52_60 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_52_60++;
public static final int value__ON__patt_generatedTest_Basics_sv_65_61 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_65_61++;
public static final int expected__ON__patt_generatedTest_Basics_sv_65_61 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_65_61++;
public static final int value__ON__patt_generatedTest_Basics_sv_66_62 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_66_62++;
public static final int expected__ON__patt_generatedTest_Basics_sv_66_62 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_66_62++;
public static final int value__ON__patt_generatedTest_Basics_sv_67_63 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_67_63++;
public static final int expected__ON__patt_generatedTest_Basics_sv_67_63 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_67_63++;
public static final int value__ON__patt_generatedTest_Basics_sv_68_64 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_68_64++;
public static final int expected__ON__patt_generatedTest_Basics_sv_68_64 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_68_64++;
public static final int value__ON__patt_generatedTest_Basics_sv_69_65 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_69_65++;
public static final int expected__ON__patt_generatedTest_Basics_sv_69_65 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_69_65++;
public static final int value__ON__patt_generatedTest_Basics_sv_82_66 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_82_66++;
public static final int expected__ON__patt_generatedTest_Basics_sv_82_66 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_82_66++;
public static final int value__ON__patt_generatedTest_Basics_sv_83_67 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_83_67++;
public static final int expected__ON__patt_generatedTest_Basics_sv_83_67 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_83_67++;
public static final int value__ON__patt_generatedTest_Basics_sv_84_68 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_84_68++;
public static final int expected__ON__patt_generatedTest_Basics_sv_84_68 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_84_68++;
public static final int value__ON__patt_generatedTest_Basics_sv_85_69 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_85_69++;
public static final int expected__ON__patt_generatedTest_Basics_sv_85_69 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_85_69++;
public static final int value__ON__patt_generatedTest_Basics_sv_86_70 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_86_70++;
public static final int expected__ON__patt_generatedTest_Basics_sv_86_70 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_86_70++;
public static final int value__ON__patt_generatedTest_Basics_sv_99_71 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_99_71++;
public static final int expected__ON__patt_generatedTest_Basics_sv_99_71 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_99_71++;
public static final int value__ON__patt_generatedTest_Basics_sv_100_72 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_100_72++;
public static final int expected__ON__patt_generatedTest_Basics_sv_100_72 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_100_72++;
public static final int value__ON__patt_generatedTest_Basics_sv_101_73 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_101_73++;
public static final int expected__ON__patt_generatedTest_Basics_sv_101_73 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_101_73++;
public static final int value__ON__patt_generatedTest_Basics_sv_102_74 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_102_74++;
public static final int expected__ON__patt_generatedTest_Basics_sv_102_74 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_102_74++;
public static final int value__ON__patt_generatedTest_Basics_sv_103_75 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_103_75++;
public static final int expected__ON__patt_generatedTest_Basics_sv_103_75 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_103_75++;
public static final int value__ON__patt_generatedTest_Basics_sv_104_76 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_104_76++;
public static final int expected__ON__patt_generatedTest_Basics_sv_104_76 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_104_76++;
public static final int value__ON__patt_generatedTest_Basics_sv_120_77 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_120_77++;
public static final int expected__ON__patt_generatedTest_Basics_sv_120_77 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_120_77++;
public static final int value__ON__patt_generatedTest_Basics_sv_121_78 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_121_78++;
public static final int expected__ON__patt_generatedTest_Basics_sv_121_78 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_121_78++;
public static final int value__ON__patt_generatedTest_Basics_sv_134_79 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_134_79++;
public static final int expected__ON__patt_generatedTest_Basics_sv_134_79 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_134_79++;
public static final int value__ON__patt_generatedTest_Basics_sv_135_80 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_135_80++;
public static final int expected__ON__patt_generatedTest_Basics_sv_135_80 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_135_80++;
public static final int value__ON__patt_generatedTest_Basics_sv_136_81 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_136_81++;
public static final int expected__ON__patt_generatedTest_Basics_sv_136_81 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_136_81++;
public static final int value__ON__patt_generatedTest_Basics_sv_137_82 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_137_82++;
public static final int expected__ON__patt_generatedTest_Basics_sv_137_82 = patt.Init.count_local__ON__patt_generatedTest_Basics_sv_137_82++;
public static final int value__ON__patt_generatedTest_Existential_sv_18_83 = patt.Init.count_local__ON__patt_generatedTest_Existential_sv_18_83++;
public static final int expected__ON__patt_generatedTest_Existential_sv_18_83 = patt.Init.count_local__ON__patt_generatedTest_Existential_sv_18_83++;
public static final int value__ON__patt_generatedTest_Existential_sv_19_84 = patt.Init.count_local__ON__patt_generatedTest_Existential_sv_19_84++;
public static final int expected__ON__patt_generatedTest_Existential_sv_19_84 = patt.Init.count_local__ON__patt_generatedTest_Existential_sv_19_84++;
public static final int value__ON__patt_generatedTest_Existential_sv_20_85 = patt.Init.count_local__ON__patt_generatedTest_Existential_sv_20_85++;
public static final int expected__ON__patt_generatedTest_Existential_sv_20_85 = patt.Init.count_local__ON__patt_generatedTest_Existential_sv_20_85++;
	public static final common.Thunk<Object> signatureTest = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, Integer>() { public final Integer eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv1174___sv_pv_1173_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(0); } };
 return (Integer)((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_1177_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv1174___sv_pv_1173_x.eval())); } };
return ((Integer)(__SV_LOCAL_1177_x.eval())); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Integer)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Normal.sv', 184, 34, 184, 66, 4138, 4170\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)new core.Pjust(Integer.valueOf((int)1))).decorate(context, (common.Lazy[])null)); } };
}
