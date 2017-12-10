package silver_features;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		silver_features.anno.Init.initAllStatics();
		silver_features.cond.Init.initAllStatics();
		silver_features.defs.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		silver.util.deque.Init.initAllStatics();
		silver.langutil.pp.Init.initAllStatics();
		silver_features.global_sub.Init.initAllStatics();
		silver_features.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		silver_features.anno.Init.init();
		silver_features.cond.Init.init();
		silver_features.defs.Init.init();
		core.monad.Init.init();
		silver.util.deque.Init.init();
		silver.langutil.pp.Init.init();
		silver_features.global_sub.Init.init();
		silver_features.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		silver_features.anno.Init.postInit();
		silver_features.cond.Init.postInit();
		silver_features.defs.Init.postInit();
		core.monad.Init.postInit();
		silver.util.deque.Init.postInit();
		silver.langutil.pp.Init.postInit();
		silver_features.global_sub.Init.postInit();
		silver_features.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_CommentParsing_sv_4_0.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_CommentParsing_sv_5_1.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_CommentParsing_sv_6_2.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_CommentParsing_sv_7_3.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_CommentParsing_sv_8_4.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_CommentParsing_sv_9_5.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_CommentParsing_sv_10_6.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_CommentParsing_sv_11_7.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_CommentParsing_sv_12_8.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_CommentParsing_sv_13_9.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncManipulation_sv_47_10.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncManipulation_sv_48_11.class);
		common.Decorator.applyDecorators(silver_features.NSection.decorators, Psection.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncManipulation_sv_67_12.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncManipulation_sv_68_13.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncManipulation_sv_72_14.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncManipulation_sv_93_15.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncManipulation_sv_101_16.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncManipulation_sv_107_17.class);
		common.Decorator.applyDecorators(silver_features.NFuncManipNT.decorators, Pfmadd.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncManipulation_sv_124_18.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncManipulation_sv_125_19.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Types_sv_74_20.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Types_sv_75_21.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Types_sv_76_22.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Types_sv_77_23.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Types_sv_78_24.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Types_sv_98_25.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Types_sv_99_26.class);
		common.Decorator.applyDecorators(silver_features.NAutoCopyTestNT.decorators, PconsACTNT.class);
		common.Decorator.applyDecorators(silver_features.NAutoCopyTestNT.decorators, PconsACTNT2.class);
		common.Decorator.applyDecorators(silver_features.NAutoCopyTestNT.decorators, PnilACTNT.class);
		common.Decorator.applyDecorators(silver_features.NAutoCopyTestNT2.decorators, PnilACTNT2.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_AutoCopy_sv_46_27.class);
		common.Decorator.applyDecorators(silver_features.NForwardKeyword.decorators, Pbar.class);
		common.Decorator.applyDecorators(silver_features.NForwardKeyword.decorators, Pbar2.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ForwardKeyword_sv_29_28.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ForwardKeyword_sv_30_29.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Globals_sv_8_30.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Globals_sv_13_31.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Globals_sv_18_32.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Globals_sv_19_33.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Globals_sv_25_34.class);
		common.Decorator.applyDecorators(silver_features.NTglob.decorators, PTfoo.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Globals_sv_39_35.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Globals_sv_43_36.class);
		common.Decorator.applyDecorators(silver_features.NIntNT.decorators, PintTestProd.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncProdTypes_sv_12_37.class);
		common.Decorator.applyDecorators(silver_features.NIntNT.decorators, PintAdd.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FuncProdTypes_sv_33_38.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lambda_sv_9_39.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lambda_sv_12_40.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lambda_sv_15_41.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lambda_sv_18_42.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lambda_sv_22_43.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lambda_sv_25_44.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lambda_sv_26_45.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lambda_sv_31_46.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lambda_sv_34_47.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_EasyTerm_sv_5_48.class);
		common.Decorator.applyDecorators(silver_features.NFunctorTestNT.decorators, PconsFTNT.class);
		common.Decorator.applyDecorators(silver_features.NFunctorTestNT.decorators, PconsFTNT2.class);
		common.Decorator.applyDecorators(silver_features.NFunctorTestNT.decorators, PnilFTNT.class);
		common.Decorator.applyDecorators(silver_features.NFunctorTestNT2.decorators, PnilFTNT2.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Functor_sv_62_49.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Functor_sv_64_50.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_8_51.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_10_52.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_15_53.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_17_54.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_19_55.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_21_56.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_23_57.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_25_58.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_28_59.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_29_60.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_32_61.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_33_62.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_34_63.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_35_64.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_36_65.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_37_66.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_38_67.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_39_68.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_42_69.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_46_70.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_47_71.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_50_72.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Templating_sv_52_73.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Monad_sv_11_74.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Monad_sv_20_75.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Monad_sv_31_76.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Monad_sv_32_77.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Monad_sv_41_78.class);
		common.Decorator.applyDecorators(silver_features.NConcreteProductions.decorators, PoneElem.class);
		common.Decorator.applyDecorators(silver_features.NConcreteProductions.decorators, PconsElem.class);
		common.Decorator.applyDecorators(silver_features.NConcreteProductions.decorators, PmoreElem.class);
		common.Decorator.applyDecorators(silver_features.NConcreteProductions.decorators, Pp_silver_features_Convenience_sv_12_11.class);
		common.Decorator.applyDecorators(silver_features.NColNT.decorators, PcolLeaf.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_17_79.class);
		common.Decorator.applyDecorators(silver_features.NColNT.decorators, PcolNode.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_38_80.class);
		common.Decorator.applyDecorators(silver_features.NColNT.decorators, PcolFwdNode.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_47_81.class);
		common.Decorator.applyDecorators(silver_features.NColNT.decorators, PcolProdLeaf.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_63_82.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_64_83.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_74_84.class);
		common.Decorator.applyDecorators(silver_features.NColNT.decorators, PcolTest1.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_133_85.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_134_86.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_135_87.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_136_88.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_137_89.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_138_90.class);
		common.Decorator.applyDecorators(silver_features.NColNT.decorators, PcolTest2.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_155_91.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_156_92.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_157_93.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_158_94.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_159_95.class);
		common.Decorator.applyDecorators(silver_features.NColNT.decorators, PcolTest3.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_176_96.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_177_97.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_178_98.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_179_99.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_180_100.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Collections_sv_181_101.class);
		common.Decorator.applyDecorators(silver_features.NADecoratedValue.decorators, PaDevProd.class);
		common.Decorator.applyDecorators(silver_features.NADecoratedValue.decorators, PbouncerDecProd.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ThunkTransforms_sv_37_102.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Scoping_sv_4_103.class);
		common.Decorator.applyDecorators(silver_features.NScope.decorators, PrightCodeScoping.class);
		common.Decorator.applyDecorators(silver_features.NScope.decorators, PrightCodeNaming.class);
		common.Decorator.applyDecorators(silver_features.NContext.decorators, Pcontext.class);
		common.Decorator.applyDecorators(silver_features.NDefaultAttrNT.decorators, Pdefaultattr1.class);
		common.Decorator.applyDecorators(silver_features.NDefaultAttrNT.decorators, Pdefaultattr2.class);
		common.Decorator.applyDecorators(silver_features.NDefaultAttrNT.decorators, Pdefaultattrn.class);
		common.Decorator.applyDecorators(silver_features.NDefaultAttrNT.decorators, Pdefaultfwd.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DefaultAttrs_sv_38_104.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DefaultAttrs_sv_39_105.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DefaultAttrs_sv_40_106.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DefaultAttrs_sv_41_107.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DefaultAttrs_sv_42_108.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DefaultAttrs_sv_44_109.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DefaultAttrs_sv_45_110.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DefaultAttrs_sv_46_111.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DefaultAttrs_sv_47_112.class);
		common.Decorator.applyDecorators(silver.testing.NTestSuite.decorators, Psilver_tests.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_15_113.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_16_114.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_17_115.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_18_116.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_21_117.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_23_118.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_24_119.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_26_120.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_27_121.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_28_122.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_31_123.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_32_124.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_33_125.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_37_126.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_38_127.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_41_128.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Main_sv_42_129.class);
	}

	private static void setupInheritedAttributes(){
		silver_features.PgeneratedTest_CommentParsing_sv_4_0.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_CommentParsing_sv_4_0] = "silver_features:generatedTest_CommentParsing_sv_4_0:local:value";
		silver_features.PgeneratedTest_CommentParsing_sv_4_0.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_CommentParsing_sv_4_0] = "silver_features:generatedTest_CommentParsing_sv_4_0:local:expected";
		silver_features.PgeneratedTest_CommentParsing_sv_5_1.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_CommentParsing_sv_5_1] = "silver_features:generatedTest_CommentParsing_sv_5_1:local:value";
		silver_features.PgeneratedTest_CommentParsing_sv_5_1.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_CommentParsing_sv_5_1] = "silver_features:generatedTest_CommentParsing_sv_5_1:local:expected";
		silver_features.PgeneratedTest_CommentParsing_sv_6_2.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_CommentParsing_sv_6_2] = "silver_features:generatedTest_CommentParsing_sv_6_2:local:value";
		silver_features.PgeneratedTest_CommentParsing_sv_6_2.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_CommentParsing_sv_6_2] = "silver_features:generatedTest_CommentParsing_sv_6_2:local:expected";
		silver_features.PgeneratedTest_CommentParsing_sv_7_3.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_CommentParsing_sv_7_3] = "silver_features:generatedTest_CommentParsing_sv_7_3:local:value";
		silver_features.PgeneratedTest_CommentParsing_sv_7_3.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_CommentParsing_sv_7_3] = "silver_features:generatedTest_CommentParsing_sv_7_3:local:expected";
		silver_features.PgeneratedTest_CommentParsing_sv_8_4.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_CommentParsing_sv_8_4] = "silver_features:generatedTest_CommentParsing_sv_8_4:local:value";
		silver_features.PgeneratedTest_CommentParsing_sv_8_4.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_CommentParsing_sv_8_4] = "silver_features:generatedTest_CommentParsing_sv_8_4:local:expected";
		silver_features.PgeneratedTest_CommentParsing_sv_9_5.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_CommentParsing_sv_9_5] = "silver_features:generatedTest_CommentParsing_sv_9_5:local:value";
		silver_features.PgeneratedTest_CommentParsing_sv_9_5.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_CommentParsing_sv_9_5] = "silver_features:generatedTest_CommentParsing_sv_9_5:local:expected";
		silver_features.PgeneratedTest_CommentParsing_sv_10_6.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_CommentParsing_sv_10_6] = "silver_features:generatedTest_CommentParsing_sv_10_6:local:value";
		silver_features.PgeneratedTest_CommentParsing_sv_10_6.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_CommentParsing_sv_10_6] = "silver_features:generatedTest_CommentParsing_sv_10_6:local:expected";
		silver_features.PgeneratedTest_CommentParsing_sv_11_7.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_CommentParsing_sv_11_7] = "silver_features:generatedTest_CommentParsing_sv_11_7:local:value";
		silver_features.PgeneratedTest_CommentParsing_sv_11_7.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_CommentParsing_sv_11_7] = "silver_features:generatedTest_CommentParsing_sv_11_7:local:expected";
		silver_features.PgeneratedTest_CommentParsing_sv_12_8.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_CommentParsing_sv_12_8] = "silver_features:generatedTest_CommentParsing_sv_12_8:local:value";
		silver_features.PgeneratedTest_CommentParsing_sv_12_8.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_CommentParsing_sv_12_8] = "silver_features:generatedTest_CommentParsing_sv_12_8:local:expected";
		silver_features.PgeneratedTest_CommentParsing_sv_13_9.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_CommentParsing_sv_13_9] = "silver_features:generatedTest_CommentParsing_sv_13_9:local:value";
		silver_features.PgeneratedTest_CommentParsing_sv_13_9.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_CommentParsing_sv_13_9] = "silver_features:generatedTest_CommentParsing_sv_13_9:local:expected";
		silver_features.PgeneratedTest_FuncManipulation_sv_47_10.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_47_10] = "silver_features:generatedTest_FuncManipulation_sv_47_10:local:value";
		silver_features.PgeneratedTest_FuncManipulation_sv_47_10.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_47_10] = "silver_features:generatedTest_FuncManipulation_sv_47_10:local:expected";
		silver_features.PgeneratedTest_FuncManipulation_sv_48_11.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_48_11] = "silver_features:generatedTest_FuncManipulation_sv_48_11:local:value";
		silver_features.PgeneratedTest_FuncManipulation_sv_48_11.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_48_11] = "silver_features:generatedTest_FuncManipulation_sv_48_11:local:expected";
		silver_features.NSection.occurs_syn[silver_features.Init.silver_features_sec_valid__ON__silver_features_Section] = "silver_features:sec_valid";
		silver_features.NSection.occurs_syn[silver_features.Init.silver_features_sec_inv__ON__silver_features_Section] = "silver_features:sec_inv";
		silver_features.NSection.occurs_inh[silver_features.Init.silver_features_sec_inv3__ON__silver_features_Section] = "silver_features:sec_inv3";
		silver_features.PgeneratedTest_FuncManipulation_sv_67_12.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_67_12] = "silver_features:generatedTest_FuncManipulation_sv_67_12:local:value";
		silver_features.PgeneratedTest_FuncManipulation_sv_67_12.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_67_12] = "silver_features:generatedTest_FuncManipulation_sv_67_12:local:expected";
		silver_features.PgeneratedTest_FuncManipulation_sv_68_13.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_68_13] = "silver_features:generatedTest_FuncManipulation_sv_68_13:local:value";
		silver_features.PgeneratedTest_FuncManipulation_sv_68_13.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_68_13] = "silver_features:generatedTest_FuncManipulation_sv_68_13:local:expected";
		silver_features.PgeneratedTest_FuncManipulation_sv_72_14.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_72_14] = "silver_features:generatedTest_FuncManipulation_sv_72_14:local:value";
		silver_features.PgeneratedTest_FuncManipulation_sv_72_14.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_72_14] = "silver_features:generatedTest_FuncManipulation_sv_72_14:local:expected";
		silver_features.PgeneratedTest_FuncManipulation_sv_93_15.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_93_15] = "silver_features:generatedTest_FuncManipulation_sv_93_15:local:value";
		silver_features.PgeneratedTest_FuncManipulation_sv_93_15.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_93_15] = "silver_features:generatedTest_FuncManipulation_sv_93_15:local:expected";
		silver_features.PgeneratedTest_FuncManipulation_sv_101_16.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_101_16] = "silver_features:generatedTest_FuncManipulation_sv_101_16:local:value";
		silver_features.PgeneratedTest_FuncManipulation_sv_101_16.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_101_16] = "silver_features:generatedTest_FuncManipulation_sv_101_16:local:expected";
		silver_features.PgeneratedTest_FuncManipulation_sv_107_17.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_107_17] = "silver_features:generatedTest_FuncManipulation_sv_107_17:local:value";
		silver_features.PgeneratedTest_FuncManipulation_sv_107_17.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_107_17] = "silver_features:generatedTest_FuncManipulation_sv_107_17:local:expected";
		silver_features.NFuncManipNT.occurs_syn[silver_features.Init.silver_features_fmeval__ON__silver_features_FuncManipNT] = "silver_features:fmeval";
		silver_features.PgeneratedTest_FuncManipulation_sv_124_18.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_124_18] = "silver_features:generatedTest_FuncManipulation_sv_124_18:local:value";
		silver_features.PgeneratedTest_FuncManipulation_sv_124_18.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_124_18] = "silver_features:generatedTest_FuncManipulation_sv_124_18:local:expected";
		silver_features.PgeneratedTest_FuncManipulation_sv_125_19.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_125_19] = "silver_features:generatedTest_FuncManipulation_sv_125_19:local:value";
		silver_features.PgeneratedTest_FuncManipulation_sv_125_19.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_125_19] = "silver_features:generatedTest_FuncManipulation_sv_125_19:local:expected";
		silver_features.PgeneratedTest_Types_sv_74_20.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Types_sv_74_20] = "silver_features:generatedTest_Types_sv_74_20:local:value";
		silver_features.PgeneratedTest_Types_sv_74_20.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Types_sv_74_20] = "silver_features:generatedTest_Types_sv_74_20:local:expected";
		silver_features.PgeneratedTest_Types_sv_75_21.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Types_sv_75_21] = "silver_features:generatedTest_Types_sv_75_21:local:value";
		silver_features.PgeneratedTest_Types_sv_75_21.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Types_sv_75_21] = "silver_features:generatedTest_Types_sv_75_21:local:expected";
		silver_features.PgeneratedTest_Types_sv_76_22.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Types_sv_76_22] = "silver_features:generatedTest_Types_sv_76_22:local:value";
		silver_features.PgeneratedTest_Types_sv_76_22.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Types_sv_76_22] = "silver_features:generatedTest_Types_sv_76_22:local:expected";
		silver_features.PgeneratedTest_Types_sv_77_23.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Types_sv_77_23] = "silver_features:generatedTest_Types_sv_77_23:local:value";
		silver_features.PgeneratedTest_Types_sv_77_23.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Types_sv_77_23] = "silver_features:generatedTest_Types_sv_77_23:local:expected";
		silver_features.PgeneratedTest_Types_sv_78_24.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Types_sv_78_24] = "silver_features:generatedTest_Types_sv_78_24:local:value";
		silver_features.PgeneratedTest_Types_sv_78_24.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Types_sv_78_24] = "silver_features:generatedTest_Types_sv_78_24:local:expected";
		silver_features.PgeneratedTest_Types_sv_98_25.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Types_sv_98_25] = "silver_features:generatedTest_Types_sv_98_25:local:value";
		silver_features.PgeneratedTest_Types_sv_98_25.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Types_sv_98_25] = "silver_features:generatedTest_Types_sv_98_25:local:expected";
		silver_features.PgeneratedTest_Types_sv_99_26.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Types_sv_99_26] = "silver_features:generatedTest_Types_sv_99_26:local:value";
		silver_features.PgeneratedTest_Types_sv_99_26.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Types_sv_99_26] = "silver_features:generatedTest_Types_sv_99_26:local:expected";
		silver_features.NAutoCopyTestNT.occurs_syn[silver_features.Init.silver_features_autoSyn__ON__silver_features_AutoCopyTestNT] = "silver_features:autoSyn";
		silver_features.NAutoCopyTestNT2.occurs_inh[silver_features.Init.silver_features_autoTest__ON__silver_features_AutoCopyTestNT2] = "silver_features:autoTest";
		silver_features.NAutoCopyTestNT2.decorators.add(silver_features.DautoTest.singleton);
		silver_features.NAutoCopyTestNT2.occurs_syn[silver_features.Init.silver_features_autoSyn__ON__silver_features_AutoCopyTestNT2] = "silver_features:autoSyn";
		silver_features.NAutoCopyTestNT.occurs_inh[silver_features.Init.silver_features_autoTest__ON__silver_features_AutoCopyTestNT] = "silver_features:autoTest";
		silver_features.NAutoCopyTestNT.decorators.add(silver_features.DautoTest.singleton);
		silver_features.PgeneratedTest_AutoCopy_sv_46_27.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_AutoCopy_sv_46_27] = "silver_features:generatedTest_AutoCopy_sv_46_27:local:value";
		silver_features.PgeneratedTest_AutoCopy_sv_46_27.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_AutoCopy_sv_46_27] = "silver_features:generatedTest_AutoCopy_sv_46_27:local:expected";
		silver_features.NForwardKeyword.occurs_syn[silver_features.Init.silver_features_fkSyn1__ON__silver_features_ForwardKeyword] = "silver_features:fkSyn1";
		silver_features.NForwardKeyword.occurs_inh[silver_features.Init.silver_features_fkInh1__ON__silver_features_ForwardKeyword] = "silver_features:fkInh1";
		silver_features.NForwardKeyword.occurs_syn[silver_features.Init.silver_features_fkSyn2__ON__silver_features_ForwardKeyword] = "silver_features:fkSyn2";
		silver_features.NForwardKeyword.occurs_inh[silver_features.Init.silver_features_fkInh2__ON__silver_features_ForwardKeyword] = "silver_features:fkInh2";
		silver_features.PgeneratedTest_ForwardKeyword_sv_29_28.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_ForwardKeyword_sv_29_28] = "silver_features:generatedTest_ForwardKeyword_sv_29_28:local:value";
		silver_features.PgeneratedTest_ForwardKeyword_sv_29_28.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_ForwardKeyword_sv_29_28] = "silver_features:generatedTest_ForwardKeyword_sv_29_28:local:expected";
		silver_features.PgeneratedTest_ForwardKeyword_sv_30_29.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_ForwardKeyword_sv_30_29] = "silver_features:generatedTest_ForwardKeyword_sv_30_29:local:value";
		silver_features.PgeneratedTest_ForwardKeyword_sv_30_29.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_ForwardKeyword_sv_30_29] = "silver_features:generatedTest_ForwardKeyword_sv_30_29:local:expected";
		silver_features.PgeneratedTest_Globals_sv_8_30.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Globals_sv_8_30] = "silver_features:generatedTest_Globals_sv_8_30:local:value";
		silver_features.PgeneratedTest_Globals_sv_8_30.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Globals_sv_8_30] = "silver_features:generatedTest_Globals_sv_8_30:local:expected";
		silver_features.PgeneratedTest_Globals_sv_13_31.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Globals_sv_13_31] = "silver_features:generatedTest_Globals_sv_13_31:local:value";
		silver_features.PgeneratedTest_Globals_sv_13_31.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Globals_sv_13_31] = "silver_features:generatedTest_Globals_sv_13_31:local:expected";
		silver_features.PgeneratedTest_Globals_sv_18_32.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Globals_sv_18_32] = "silver_features:generatedTest_Globals_sv_18_32:local:value";
		silver_features.PgeneratedTest_Globals_sv_18_32.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Globals_sv_18_32] = "silver_features:generatedTest_Globals_sv_18_32:local:expected";
		silver_features.PgeneratedTest_Globals_sv_19_33.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Globals_sv_19_33] = "silver_features:generatedTest_Globals_sv_19_33:local:value";
		silver_features.PgeneratedTest_Globals_sv_19_33.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Globals_sv_19_33] = "silver_features:generatedTest_Globals_sv_19_33:local:expected";
		silver_features.PgeneratedTest_Globals_sv_25_34.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Globals_sv_25_34] = "silver_features:generatedTest_Globals_sv_25_34:local:value";
		silver_features.PgeneratedTest_Globals_sv_25_34.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Globals_sv_25_34] = "silver_features:generatedTest_Globals_sv_25_34:local:expected";
		silver_features.NTglob.occurs_syn[silver_features.Init.silver_features_strGlob__ON__silver_features_Tglob] = "silver_features:strGlob";
		silver_features.PgeneratedTest_Globals_sv_39_35.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Globals_sv_39_35] = "silver_features:generatedTest_Globals_sv_39_35:local:value";
		silver_features.PgeneratedTest_Globals_sv_39_35.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Globals_sv_39_35] = "silver_features:generatedTest_Globals_sv_39_35:local:expected";
		silver_features.PgeneratedTest_Globals_sv_43_36.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Globals_sv_43_36] = "silver_features:generatedTest_Globals_sv_43_36:local:value";
		silver_features.PgeneratedTest_Globals_sv_43_36.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Globals_sv_43_36] = "silver_features:generatedTest_Globals_sv_43_36:local:expected";
		silver_features.NIntNT.occurs_syn[silver_features.Init.silver_features_intValue__ON__silver_features_IntNT] = "silver_features:intValue";
		silver_features.PgeneratedTest_FuncProdTypes_sv_12_37.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncProdTypes_sv_12_37] = "silver_features:generatedTest_FuncProdTypes_sv_12_37:local:value";
		silver_features.PgeneratedTest_FuncProdTypes_sv_12_37.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncProdTypes_sv_12_37] = "silver_features:generatedTest_FuncProdTypes_sv_12_37:local:expected";
		silver_features.PgeneratedTest_FuncProdTypes_sv_33_38.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_FuncProdTypes_sv_33_38] = "silver_features:generatedTest_FuncProdTypes_sv_33_38:local:value";
		silver_features.PgeneratedTest_FuncProdTypes_sv_33_38.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_FuncProdTypes_sv_33_38] = "silver_features:generatedTest_FuncProdTypes_sv_33_38:local:expected";
		silver_features.PgeneratedTest_Lambda_sv_9_39.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Lambda_sv_9_39] = "silver_features:generatedTest_Lambda_sv_9_39:local:value";
		silver_features.PgeneratedTest_Lambda_sv_9_39.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Lambda_sv_9_39] = "silver_features:generatedTest_Lambda_sv_9_39:local:expected";
		silver_features.PgeneratedTest_Lambda_sv_12_40.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Lambda_sv_12_40] = "silver_features:generatedTest_Lambda_sv_12_40:local:value";
		silver_features.PgeneratedTest_Lambda_sv_12_40.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Lambda_sv_12_40] = "silver_features:generatedTest_Lambda_sv_12_40:local:expected";
		silver_features.PgeneratedTest_Lambda_sv_15_41.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Lambda_sv_15_41] = "silver_features:generatedTest_Lambda_sv_15_41:local:value";
		silver_features.PgeneratedTest_Lambda_sv_15_41.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Lambda_sv_15_41] = "silver_features:generatedTest_Lambda_sv_15_41:local:expected";
		silver_features.PgeneratedTest_Lambda_sv_18_42.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Lambda_sv_18_42] = "silver_features:generatedTest_Lambda_sv_18_42:local:value";
		silver_features.PgeneratedTest_Lambda_sv_18_42.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Lambda_sv_18_42] = "silver_features:generatedTest_Lambda_sv_18_42:local:expected";
		silver_features.PgeneratedTest_Lambda_sv_22_43.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Lambda_sv_22_43] = "silver_features:generatedTest_Lambda_sv_22_43:local:value";
		silver_features.PgeneratedTest_Lambda_sv_22_43.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Lambda_sv_22_43] = "silver_features:generatedTest_Lambda_sv_22_43:local:expected";
		silver_features.PgeneratedTest_Lambda_sv_25_44.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Lambda_sv_25_44] = "silver_features:generatedTest_Lambda_sv_25_44:local:value";
		silver_features.PgeneratedTest_Lambda_sv_25_44.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Lambda_sv_25_44] = "silver_features:generatedTest_Lambda_sv_25_44:local:expected";
		silver_features.PgeneratedTest_Lambda_sv_26_45.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Lambda_sv_26_45] = "silver_features:generatedTest_Lambda_sv_26_45:local:value";
		silver_features.PgeneratedTest_Lambda_sv_26_45.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Lambda_sv_26_45] = "silver_features:generatedTest_Lambda_sv_26_45:local:expected";
		silver_features.PgeneratedTest_Lambda_sv_31_46.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Lambda_sv_31_46] = "silver_features:generatedTest_Lambda_sv_31_46:local:value";
		silver_features.PgeneratedTest_Lambda_sv_31_46.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Lambda_sv_31_46] = "silver_features:generatedTest_Lambda_sv_31_46:local:expected";
		silver_features.PgeneratedTest_Lambda_sv_34_47.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Lambda_sv_34_47] = "silver_features:generatedTest_Lambda_sv_34_47:local:value";
		silver_features.PgeneratedTest_Lambda_sv_34_47.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Lambda_sv_34_47] = "silver_features:generatedTest_Lambda_sv_34_47:local:expected";
		silver_features.PgeneratedTest_EasyTerm_sv_5_48.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_EasyTerm_sv_5_48] = "silver_features:generatedTest_EasyTerm_sv_5_48:local:value";
		silver_features.PgeneratedTest_EasyTerm_sv_5_48.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_EasyTerm_sv_5_48] = "silver_features:generatedTest_EasyTerm_sv_5_48:local:expected";
		silver_features.NFunctorTestNT.occurs_syn[silver_features.Init.silver_features_functorSyn__ON__silver_features_FunctorTestNT] = "silver_features:functorSyn";
		silver_features.NFunctorTestNT.occurs_syn[silver_features.Init.silver_features_functorTestAnnoSum__ON__silver_features_FunctorTestNT] = "silver_features:functorTestAnnoSum";
		silver_features.NFunctorTestNT2.occurs_syn[silver_features.Init.silver_features_functorSyn__ON__silver_features_FunctorTestNT2] = "silver_features:functorSyn";
		silver_features.NFunctorTestNT2.occurs_syn[silver_features.Init.silver_features_functorTestAnnoSum__ON__silver_features_FunctorTestNT2] = "silver_features:functorTestAnnoSum";
		silver_features.PgeneratedTest_Functor_sv_62_49.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Functor_sv_62_49] = "silver_features:generatedTest_Functor_sv_62_49:local:value";
		silver_features.PgeneratedTest_Functor_sv_62_49.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Functor_sv_62_49] = "silver_features:generatedTest_Functor_sv_62_49:local:expected";
		silver_features.PgeneratedTest_Functor_sv_64_50.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Functor_sv_64_50] = "silver_features:generatedTest_Functor_sv_64_50:local:value";
		silver_features.PgeneratedTest_Functor_sv_64_50.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Functor_sv_64_50] = "silver_features:generatedTest_Functor_sv_64_50:local:expected";
		silver_features.PgeneratedTest_Templating_sv_8_51.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_8_51] = "silver_features:generatedTest_Templating_sv_8_51:local:value";
		silver_features.PgeneratedTest_Templating_sv_8_51.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_8_51] = "silver_features:generatedTest_Templating_sv_8_51:local:expected";
		silver_features.PgeneratedTest_Templating_sv_10_52.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_10_52] = "silver_features:generatedTest_Templating_sv_10_52:local:value";
		silver_features.PgeneratedTest_Templating_sv_10_52.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_10_52] = "silver_features:generatedTest_Templating_sv_10_52:local:expected";
		silver_features.PgeneratedTest_Templating_sv_15_53.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_15_53] = "silver_features:generatedTest_Templating_sv_15_53:local:value";
		silver_features.PgeneratedTest_Templating_sv_15_53.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_15_53] = "silver_features:generatedTest_Templating_sv_15_53:local:expected";
		silver_features.PgeneratedTest_Templating_sv_17_54.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_17_54] = "silver_features:generatedTest_Templating_sv_17_54:local:value";
		silver_features.PgeneratedTest_Templating_sv_17_54.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_17_54] = "silver_features:generatedTest_Templating_sv_17_54:local:expected";
		silver_features.PgeneratedTest_Templating_sv_19_55.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_19_55] = "silver_features:generatedTest_Templating_sv_19_55:local:value";
		silver_features.PgeneratedTest_Templating_sv_19_55.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_19_55] = "silver_features:generatedTest_Templating_sv_19_55:local:expected";
		silver_features.PgeneratedTest_Templating_sv_21_56.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_21_56] = "silver_features:generatedTest_Templating_sv_21_56:local:value";
		silver_features.PgeneratedTest_Templating_sv_21_56.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_21_56] = "silver_features:generatedTest_Templating_sv_21_56:local:expected";
		silver_features.PgeneratedTest_Templating_sv_23_57.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_23_57] = "silver_features:generatedTest_Templating_sv_23_57:local:value";
		silver_features.PgeneratedTest_Templating_sv_23_57.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_23_57] = "silver_features:generatedTest_Templating_sv_23_57:local:expected";
		silver_features.PgeneratedTest_Templating_sv_25_58.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_25_58] = "silver_features:generatedTest_Templating_sv_25_58:local:value";
		silver_features.PgeneratedTest_Templating_sv_25_58.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_25_58] = "silver_features:generatedTest_Templating_sv_25_58:local:expected";
		silver_features.PgeneratedTest_Templating_sv_28_59.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_28_59] = "silver_features:generatedTest_Templating_sv_28_59:local:value";
		silver_features.PgeneratedTest_Templating_sv_28_59.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_28_59] = "silver_features:generatedTest_Templating_sv_28_59:local:expected";
		silver_features.PgeneratedTest_Templating_sv_29_60.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_29_60] = "silver_features:generatedTest_Templating_sv_29_60:local:value";
		silver_features.PgeneratedTest_Templating_sv_29_60.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_29_60] = "silver_features:generatedTest_Templating_sv_29_60:local:expected";
		silver_features.PgeneratedTest_Templating_sv_32_61.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_32_61] = "silver_features:generatedTest_Templating_sv_32_61:local:value";
		silver_features.PgeneratedTest_Templating_sv_32_61.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_32_61] = "silver_features:generatedTest_Templating_sv_32_61:local:expected";
		silver_features.PgeneratedTest_Templating_sv_33_62.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_33_62] = "silver_features:generatedTest_Templating_sv_33_62:local:value";
		silver_features.PgeneratedTest_Templating_sv_33_62.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_33_62] = "silver_features:generatedTest_Templating_sv_33_62:local:expected";
		silver_features.PgeneratedTest_Templating_sv_34_63.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_34_63] = "silver_features:generatedTest_Templating_sv_34_63:local:value";
		silver_features.PgeneratedTest_Templating_sv_34_63.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_34_63] = "silver_features:generatedTest_Templating_sv_34_63:local:expected";
		silver_features.PgeneratedTest_Templating_sv_35_64.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_35_64] = "silver_features:generatedTest_Templating_sv_35_64:local:value";
		silver_features.PgeneratedTest_Templating_sv_35_64.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_35_64] = "silver_features:generatedTest_Templating_sv_35_64:local:expected";
		silver_features.PgeneratedTest_Templating_sv_36_65.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_36_65] = "silver_features:generatedTest_Templating_sv_36_65:local:value";
		silver_features.PgeneratedTest_Templating_sv_36_65.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_36_65] = "silver_features:generatedTest_Templating_sv_36_65:local:expected";
		silver_features.PgeneratedTest_Templating_sv_37_66.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_37_66] = "silver_features:generatedTest_Templating_sv_37_66:local:value";
		silver_features.PgeneratedTest_Templating_sv_37_66.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_37_66] = "silver_features:generatedTest_Templating_sv_37_66:local:expected";
		silver_features.PgeneratedTest_Templating_sv_38_67.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_38_67] = "silver_features:generatedTest_Templating_sv_38_67:local:value";
		silver_features.PgeneratedTest_Templating_sv_38_67.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_38_67] = "silver_features:generatedTest_Templating_sv_38_67:local:expected";
		silver_features.PgeneratedTest_Templating_sv_39_68.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_39_68] = "silver_features:generatedTest_Templating_sv_39_68:local:value";
		silver_features.PgeneratedTest_Templating_sv_39_68.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_39_68] = "silver_features:generatedTest_Templating_sv_39_68:local:expected";
		silver_features.PgeneratedTest_Templating_sv_42_69.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_42_69] = "silver_features:generatedTest_Templating_sv_42_69:local:value";
		silver_features.PgeneratedTest_Templating_sv_42_69.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_42_69] = "silver_features:generatedTest_Templating_sv_42_69:local:expected";
		silver_features.PgeneratedTest_Templating_sv_46_70.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_46_70] = "silver_features:generatedTest_Templating_sv_46_70:local:value";
		silver_features.PgeneratedTest_Templating_sv_46_70.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_46_70] = "silver_features:generatedTest_Templating_sv_46_70:local:expected";
		silver_features.PgeneratedTest_Templating_sv_47_71.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_47_71] = "silver_features:generatedTest_Templating_sv_47_71:local:value";
		silver_features.PgeneratedTest_Templating_sv_47_71.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_47_71] = "silver_features:generatedTest_Templating_sv_47_71:local:expected";
		silver_features.PgeneratedTest_Templating_sv_50_72.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_50_72] = "silver_features:generatedTest_Templating_sv_50_72:local:value";
		silver_features.PgeneratedTest_Templating_sv_50_72.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_50_72] = "silver_features:generatedTest_Templating_sv_50_72:local:expected";
		silver_features.PgeneratedTest_Templating_sv_52_73.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_52_73] = "silver_features:generatedTest_Templating_sv_52_73:local:value";
		silver_features.PgeneratedTest_Templating_sv_52_73.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_52_73] = "silver_features:generatedTest_Templating_sv_52_73:local:expected";
		silver_features.PgeneratedTest_Monad_sv_11_74.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Monad_sv_11_74] = "silver_features:generatedTest_Monad_sv_11_74:local:value";
		silver_features.PgeneratedTest_Monad_sv_11_74.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Monad_sv_11_74] = "silver_features:generatedTest_Monad_sv_11_74:local:expected";
		silver_features.PgeneratedTest_Monad_sv_20_75.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Monad_sv_20_75] = "silver_features:generatedTest_Monad_sv_20_75:local:value";
		silver_features.PgeneratedTest_Monad_sv_20_75.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Monad_sv_20_75] = "silver_features:generatedTest_Monad_sv_20_75:local:expected";
		silver_features.PgeneratedTest_Monad_sv_31_76.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Monad_sv_31_76] = "silver_features:generatedTest_Monad_sv_31_76:local:value";
		silver_features.PgeneratedTest_Monad_sv_31_76.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Monad_sv_31_76] = "silver_features:generatedTest_Monad_sv_31_76:local:expected";
		silver_features.PgeneratedTest_Monad_sv_32_77.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Monad_sv_32_77] = "silver_features:generatedTest_Monad_sv_32_77:local:value";
		silver_features.PgeneratedTest_Monad_sv_32_77.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Monad_sv_32_77] = "silver_features:generatedTest_Monad_sv_32_77:local:expected";
		silver_features.PgeneratedTest_Monad_sv_41_78.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Monad_sv_41_78] = "silver_features:generatedTest_Monad_sv_41_78:local:value";
		silver_features.PgeneratedTest_Monad_sv_41_78.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Monad_sv_41_78] = "silver_features:generatedTest_Monad_sv_41_78:local:expected";
		silver_features.NConcreteProductions.occurs_syn[silver_features.Init.core_fst__ON__silver_features_ConcreteProductions] = "core:fst";
		silver_features.NColNT.occurs_syn[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT] = "silver_features:colSyn";
		silver_features.NColNT.occurs_inh[silver_features.Init.silver_features_colInh__ON__silver_features_ColNT] = "silver_features:colInh";
		silver_features.PgeneratedTest_Collections_sv_17_79.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_17_79] = "silver_features:generatedTest_Collections_sv_17_79:local:value";
		silver_features.PgeneratedTest_Collections_sv_17_79.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_17_79] = "silver_features:generatedTest_Collections_sv_17_79:local:expected";
		silver_features.PgeneratedTest_Collections_sv_38_80.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_38_80] = "silver_features:generatedTest_Collections_sv_38_80:local:value";
		silver_features.PgeneratedTest_Collections_sv_38_80.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_38_80] = "silver_features:generatedTest_Collections_sv_38_80:local:expected";
		silver_features.PgeneratedTest_Collections_sv_47_81.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_47_81] = "silver_features:generatedTest_Collections_sv_47_81:local:value";
		silver_features.PgeneratedTest_Collections_sv_47_81.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_47_81] = "silver_features:generatedTest_Collections_sv_47_81:local:expected";
		silver_features.PcolProdLeaf.occurs_local[silver_features.Init.lo__ON__silver_features_colProdLeaf] = "silver_features:colProdLeaf:local:lo";
		silver_features.PcolProdLeaf.localAttributes[silver_features.Init.lo__ON__silver_features_colProdLeaf] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.StringCatter result = (common.StringCatter)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = new common.StringCatter(result, (common.StringCatter)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		silver_features.PgeneratedTest_Collections_sv_63_82.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_63_82] = "silver_features:generatedTest_Collections_sv_63_82:local:value";
		silver_features.PgeneratedTest_Collections_sv_63_82.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_63_82] = "silver_features:generatedTest_Collections_sv_63_82:local:expected";
		silver_features.PgeneratedTest_Collections_sv_64_83.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_64_83] = "silver_features:generatedTest_Collections_sv_64_83:local:value";
		silver_features.PgeneratedTest_Collections_sv_64_83.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_64_83] = "silver_features:generatedTest_Collections_sv_64_83:local:expected";
		//	local attribute t::ColNT;
		silver_features.PtestLocalInheritedCollections.localInheritedAttributes[silver_features.Init.t__ON__silver_features_testLocalInheritedCollections] = new common.Lazy[silver_features.NColNT.num_inh_attrs];
		silver_features.PtestLocalInheritedCollections.occurs_local[silver_features.Init.t__ON__silver_features_testLocalInheritedCollections] = "silver_features:testLocalInheritedCollections:local:t";
		silver_features.PgeneratedTest_Collections_sv_74_84.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_74_84] = "silver_features:generatedTest_Collections_sv_74_84:local:value";
		silver_features.PgeneratedTest_Collections_sv_74_84.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_74_84] = "silver_features:generatedTest_Collections_sv_74_84:local:expected";
		silver_features.NColNT.occurs_syn[silver_features.Init.silver_features_colNotCol__ON__silver_features_ColNT] = "silver_features:colNotCol";
		silver_features.NColNT.occurs_syn[silver_features.Init.silver_features_colList__ON__silver_features_ColNT] = "silver_features:colList";
		silver_features.NColNT.occurs_syn[silver_features.Init.silver_features_colOr__ON__silver_features_ColNT] = "silver_features:colOr";
		silver_features.NColNT.occurs_syn[silver_features.Init.silver_features_colAnd__ON__silver_features_ColNT] = "silver_features:colAnd";
		silver_features.NColNT.occurs_syn[silver_features.Init.silver_features_colFun__ON__silver_features_ColNT] = "silver_features:colFun";
		silver_features.NColNT.occurs_syn[silver_features.Init.silver_features_colProd__ON__silver_features_ColNT] = "silver_features:colProd";
		silver_features.PgeneratedTest_Collections_sv_133_85.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_133_85] = "silver_features:generatedTest_Collections_sv_133_85:local:value";
		silver_features.PgeneratedTest_Collections_sv_133_85.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_133_85] = "silver_features:generatedTest_Collections_sv_133_85:local:expected";
		silver_features.PgeneratedTest_Collections_sv_134_86.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_134_86] = "silver_features:generatedTest_Collections_sv_134_86:local:value";
		silver_features.PgeneratedTest_Collections_sv_134_86.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_134_86] = "silver_features:generatedTest_Collections_sv_134_86:local:expected";
		silver_features.PgeneratedTest_Collections_sv_135_87.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_135_87] = "silver_features:generatedTest_Collections_sv_135_87:local:value";
		silver_features.PgeneratedTest_Collections_sv_135_87.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_135_87] = "silver_features:generatedTest_Collections_sv_135_87:local:expected";
		silver_features.PgeneratedTest_Collections_sv_136_88.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_136_88] = "silver_features:generatedTest_Collections_sv_136_88:local:value";
		silver_features.PgeneratedTest_Collections_sv_136_88.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_136_88] = "silver_features:generatedTest_Collections_sv_136_88:local:expected";
		silver_features.PgeneratedTest_Collections_sv_137_89.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_137_89] = "silver_features:generatedTest_Collections_sv_137_89:local:value";
		silver_features.PgeneratedTest_Collections_sv_137_89.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_137_89] = "silver_features:generatedTest_Collections_sv_137_89:local:expected";
		silver_features.PgeneratedTest_Collections_sv_138_90.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_138_90] = "silver_features:generatedTest_Collections_sv_138_90:local:value";
		silver_features.PgeneratedTest_Collections_sv_138_90.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_138_90] = "silver_features:generatedTest_Collections_sv_138_90:local:expected";
		silver_features.PgeneratedTest_Collections_sv_155_91.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_155_91] = "silver_features:generatedTest_Collections_sv_155_91:local:value";
		silver_features.PgeneratedTest_Collections_sv_155_91.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_155_91] = "silver_features:generatedTest_Collections_sv_155_91:local:expected";
		silver_features.PgeneratedTest_Collections_sv_156_92.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_156_92] = "silver_features:generatedTest_Collections_sv_156_92:local:value";
		silver_features.PgeneratedTest_Collections_sv_156_92.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_156_92] = "silver_features:generatedTest_Collections_sv_156_92:local:expected";
		silver_features.PgeneratedTest_Collections_sv_157_93.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_157_93] = "silver_features:generatedTest_Collections_sv_157_93:local:value";
		silver_features.PgeneratedTest_Collections_sv_157_93.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_157_93] = "silver_features:generatedTest_Collections_sv_157_93:local:expected";
		silver_features.PgeneratedTest_Collections_sv_158_94.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_158_94] = "silver_features:generatedTest_Collections_sv_158_94:local:value";
		silver_features.PgeneratedTest_Collections_sv_158_94.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_158_94] = "silver_features:generatedTest_Collections_sv_158_94:local:expected";
		silver_features.PgeneratedTest_Collections_sv_159_95.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_159_95] = "silver_features:generatedTest_Collections_sv_159_95:local:value";
		silver_features.PgeneratedTest_Collections_sv_159_95.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_159_95] = "silver_features:generatedTest_Collections_sv_159_95:local:expected";
		silver_features.PgeneratedTest_Collections_sv_176_96.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_176_96] = "silver_features:generatedTest_Collections_sv_176_96:local:value";
		silver_features.PgeneratedTest_Collections_sv_176_96.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_176_96] = "silver_features:generatedTest_Collections_sv_176_96:local:expected";
		silver_features.PgeneratedTest_Collections_sv_177_97.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_177_97] = "silver_features:generatedTest_Collections_sv_177_97:local:value";
		silver_features.PgeneratedTest_Collections_sv_177_97.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_177_97] = "silver_features:generatedTest_Collections_sv_177_97:local:expected";
		silver_features.PgeneratedTest_Collections_sv_178_98.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_178_98] = "silver_features:generatedTest_Collections_sv_178_98:local:value";
		silver_features.PgeneratedTest_Collections_sv_178_98.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_178_98] = "silver_features:generatedTest_Collections_sv_178_98:local:expected";
		silver_features.PgeneratedTest_Collections_sv_179_99.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_179_99] = "silver_features:generatedTest_Collections_sv_179_99:local:value";
		silver_features.PgeneratedTest_Collections_sv_179_99.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_179_99] = "silver_features:generatedTest_Collections_sv_179_99:local:expected";
		silver_features.PgeneratedTest_Collections_sv_180_100.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_180_100] = "silver_features:generatedTest_Collections_sv_180_100:local:value";
		silver_features.PgeneratedTest_Collections_sv_180_100.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_180_100] = "silver_features:generatedTest_Collections_sv_180_100:local:expected";
		silver_features.PgeneratedTest_Collections_sv_181_101.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_181_101] = "silver_features:generatedTest_Collections_sv_181_101:local:value";
		silver_features.PgeneratedTest_Collections_sv_181_101.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_181_101] = "silver_features:generatedTest_Collections_sv_181_101:local:expected";
		silver_features.NADecoratedValue.occurs_syn[silver_features.Init.silver_features_aDecVal__ON__silver_features_ADecoratedValue] = "silver_features:aDecVal";
		silver_features.PgeneratedTest_ThunkTransforms_sv_37_102.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_ThunkTransforms_sv_37_102] = "silver_features:generatedTest_ThunkTransforms_sv_37_102:local:value";
		silver_features.PgeneratedTest_ThunkTransforms_sv_37_102.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_ThunkTransforms_sv_37_102] = "silver_features:generatedTest_ThunkTransforms_sv_37_102:local:expected";
		silver_features.PgeneratedTest_Scoping_sv_4_103.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Scoping_sv_4_103] = "silver_features:generatedTest_Scoping_sv_4_103:local:value";
		silver_features.PgeneratedTest_Scoping_sv_4_103.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Scoping_sv_4_103] = "silver_features:generatedTest_Scoping_sv_4_103:local:expected";
		silver_features.PrightCodeScoping.occurs_local[silver_features.Init.hi1__ON__silver_features_rightCodeScoping] = "silver_features:rightCodeScoping:local:hi1";
		silver_features.PrightCodeScoping.occurs_local[silver_features.Init.hi2__ON__silver_features_rightCodeScoping] = "silver_features:rightCodeScoping:local:hi2";
		//	local attribute sc::Scope;
		silver_features.PrightCodeNaming.localInheritedAttributes[silver_features.Init.sc__ON__silver_features_rightCodeNaming] = new common.Lazy[silver_features.NScope.num_inh_attrs];
		silver_features.PrightCodeNaming.occurs_local[silver_features.Init.sc__ON__silver_features_rightCodeNaming] = "silver_features:rightCodeNaming:local:sc";
		silver_features.PrightCodeNaming.occurs_local[silver_features.Init.tp__ON__silver_features_rightCodeNaming] = "silver_features:rightCodeNaming:local:tp";
		silver_features.NDefaultAttrNT.occurs_syn[silver_features.Init.silver_features_defaultsyn__ON__silver_features_DefaultAttrNT] = "silver_features:defaultsyn";
		silver_features.NDefaultAttrNT.occurs_syn[silver_features.Init.silver_features_defaultsyn2__ON__silver_features_DefaultAttrNT] = "silver_features:defaultsyn2";
		silver_features.PgeneratedTest_DefaultAttrs_sv_38_104.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_DefaultAttrs_sv_38_104] = "silver_features:generatedTest_DefaultAttrs_sv_38_104:local:value";
		silver_features.PgeneratedTest_DefaultAttrs_sv_38_104.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_DefaultAttrs_sv_38_104] = "silver_features:generatedTest_DefaultAttrs_sv_38_104:local:expected";
		silver_features.PgeneratedTest_DefaultAttrs_sv_39_105.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_DefaultAttrs_sv_39_105] = "silver_features:generatedTest_DefaultAttrs_sv_39_105:local:value";
		silver_features.PgeneratedTest_DefaultAttrs_sv_39_105.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_DefaultAttrs_sv_39_105] = "silver_features:generatedTest_DefaultAttrs_sv_39_105:local:expected";
		silver_features.PgeneratedTest_DefaultAttrs_sv_40_106.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_DefaultAttrs_sv_40_106] = "silver_features:generatedTest_DefaultAttrs_sv_40_106:local:value";
		silver_features.PgeneratedTest_DefaultAttrs_sv_40_106.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_DefaultAttrs_sv_40_106] = "silver_features:generatedTest_DefaultAttrs_sv_40_106:local:expected";
		silver_features.PgeneratedTest_DefaultAttrs_sv_41_107.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_DefaultAttrs_sv_41_107] = "silver_features:generatedTest_DefaultAttrs_sv_41_107:local:value";
		silver_features.PgeneratedTest_DefaultAttrs_sv_41_107.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_DefaultAttrs_sv_41_107] = "silver_features:generatedTest_DefaultAttrs_sv_41_107:local:expected";
		silver_features.PgeneratedTest_DefaultAttrs_sv_42_108.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_DefaultAttrs_sv_42_108] = "silver_features:generatedTest_DefaultAttrs_sv_42_108:local:value";
		silver_features.PgeneratedTest_DefaultAttrs_sv_42_108.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_DefaultAttrs_sv_42_108] = "silver_features:generatedTest_DefaultAttrs_sv_42_108:local:expected";
		silver_features.PgeneratedTest_DefaultAttrs_sv_44_109.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_DefaultAttrs_sv_44_109] = "silver_features:generatedTest_DefaultAttrs_sv_44_109:local:value";
		silver_features.PgeneratedTest_DefaultAttrs_sv_44_109.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_DefaultAttrs_sv_44_109] = "silver_features:generatedTest_DefaultAttrs_sv_44_109:local:expected";
		silver_features.PgeneratedTest_DefaultAttrs_sv_45_110.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_DefaultAttrs_sv_45_110] = "silver_features:generatedTest_DefaultAttrs_sv_45_110:local:value";
		silver_features.PgeneratedTest_DefaultAttrs_sv_45_110.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_DefaultAttrs_sv_45_110] = "silver_features:generatedTest_DefaultAttrs_sv_45_110:local:expected";
		silver_features.PgeneratedTest_DefaultAttrs_sv_46_111.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_DefaultAttrs_sv_46_111] = "silver_features:generatedTest_DefaultAttrs_sv_46_111:local:value";
		silver_features.PgeneratedTest_DefaultAttrs_sv_46_111.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_DefaultAttrs_sv_46_111] = "silver_features:generatedTest_DefaultAttrs_sv_46_111:local:expected";
		silver_features.PgeneratedTest_DefaultAttrs_sv_47_112.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_DefaultAttrs_sv_47_112] = "silver_features:generatedTest_DefaultAttrs_sv_47_112:local:value";
		silver_features.PgeneratedTest_DefaultAttrs_sv_47_112.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_DefaultAttrs_sv_47_112] = "silver_features:generatedTest_DefaultAttrs_sv_47_112:local:expected";
		//	local attribute testResults::TestSuite;
		silver_features.Pmain.localInheritedAttributes[silver_features.Init.testResults__ON__silver_features_main] = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];
		silver_features.Pmain.occurs_local[silver_features.Init.testResults__ON__silver_features_main] = "silver_features:main:local:testResults";
		silver_features.Psilver_tests.occurs_local[silver_features.Init.testsToPerform__ON__silver_features_silver_tests] = "silver_features:silver_tests:local:testsToPerform";
		silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		silver_features.PgeneratedTest_Main_sv_15_113.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_15_113] = "silver_features:generatedTest_Main_sv_15_113:local:value";
		silver_features.PgeneratedTest_Main_sv_15_113.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_15_113] = "silver_features:generatedTest_Main_sv_15_113:local:expected";
		silver_features.PgeneratedTest_Main_sv_16_114.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_16_114] = "silver_features:generatedTest_Main_sv_16_114:local:value";
		silver_features.PgeneratedTest_Main_sv_16_114.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_16_114] = "silver_features:generatedTest_Main_sv_16_114:local:expected";
		silver_features.PgeneratedTest_Main_sv_17_115.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_17_115] = "silver_features:generatedTest_Main_sv_17_115:local:value";
		silver_features.PgeneratedTest_Main_sv_17_115.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_17_115] = "silver_features:generatedTest_Main_sv_17_115:local:expected";
		silver_features.PgeneratedTest_Main_sv_18_116.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_18_116] = "silver_features:generatedTest_Main_sv_18_116:local:value";
		silver_features.PgeneratedTest_Main_sv_18_116.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_18_116] = "silver_features:generatedTest_Main_sv_18_116:local:expected";
		silver_features.PgeneratedTest_Main_sv_21_117.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_21_117] = "silver_features:generatedTest_Main_sv_21_117:local:value";
		silver_features.PgeneratedTest_Main_sv_21_117.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_21_117] = "silver_features:generatedTest_Main_sv_21_117:local:expected";
		silver_features.PgeneratedTest_Main_sv_23_118.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_23_118] = "silver_features:generatedTest_Main_sv_23_118:local:value";
		silver_features.PgeneratedTest_Main_sv_23_118.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_23_118] = "silver_features:generatedTest_Main_sv_23_118:local:expected";
		silver_features.PgeneratedTest_Main_sv_24_119.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_24_119] = "silver_features:generatedTest_Main_sv_24_119:local:value";
		silver_features.PgeneratedTest_Main_sv_24_119.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_24_119] = "silver_features:generatedTest_Main_sv_24_119:local:expected";
		silver_features.PgeneratedTest_Main_sv_26_120.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_26_120] = "silver_features:generatedTest_Main_sv_26_120:local:value";
		silver_features.PgeneratedTest_Main_sv_26_120.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_26_120] = "silver_features:generatedTest_Main_sv_26_120:local:expected";
		silver_features.PgeneratedTest_Main_sv_27_121.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_27_121] = "silver_features:generatedTest_Main_sv_27_121:local:value";
		silver_features.PgeneratedTest_Main_sv_27_121.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_27_121] = "silver_features:generatedTest_Main_sv_27_121:local:expected";
		silver_features.PgeneratedTest_Main_sv_28_122.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_28_122] = "silver_features:generatedTest_Main_sv_28_122:local:value";
		silver_features.PgeneratedTest_Main_sv_28_122.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_28_122] = "silver_features:generatedTest_Main_sv_28_122:local:expected";
		silver_features.PgeneratedTest_Main_sv_31_123.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_31_123] = "silver_features:generatedTest_Main_sv_31_123:local:value";
		silver_features.PgeneratedTest_Main_sv_31_123.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_31_123] = "silver_features:generatedTest_Main_sv_31_123:local:expected";
		silver_features.PgeneratedTest_Main_sv_32_124.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_32_124] = "silver_features:generatedTest_Main_sv_32_124:local:value";
		silver_features.PgeneratedTest_Main_sv_32_124.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_32_124] = "silver_features:generatedTest_Main_sv_32_124:local:expected";
		silver_features.PgeneratedTest_Main_sv_33_125.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_33_125] = "silver_features:generatedTest_Main_sv_33_125:local:value";
		silver_features.PgeneratedTest_Main_sv_33_125.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_33_125] = "silver_features:generatedTest_Main_sv_33_125:local:expected";
		silver_features.PgeneratedTest_Main_sv_37_126.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_37_126] = "silver_features:generatedTest_Main_sv_37_126:local:value";
		silver_features.PgeneratedTest_Main_sv_37_126.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_37_126] = "silver_features:generatedTest_Main_sv_37_126:local:expected";
		silver_features.PgeneratedTest_Main_sv_38_127.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_38_127] = "silver_features:generatedTest_Main_sv_38_127:local:value";
		silver_features.PgeneratedTest_Main_sv_38_127.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_38_127] = "silver_features:generatedTest_Main_sv_38_127:local:expected";
		silver_features.PgeneratedTest_Main_sv_41_128.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_41_128] = "silver_features:generatedTest_Main_sv_41_128:local:value";
		silver_features.PgeneratedTest_Main_sv_41_128.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_41_128] = "silver_features:generatedTest_Main_sv_41_128:local:expected";
		silver_features.PgeneratedTest_Main_sv_42_129.occurs_local[silver_features.Init.value__ON__silver_features_generatedTest_Main_sv_42_129] = "silver_features:generatedTest_Main_sv_42_129:local:value";
		silver_features.PgeneratedTest_Main_sv_42_129.occurs_local[silver_features.Init.expected__ON__silver_features_generatedTest_Main_sv_42_129] = "silver_features:generatedTest_Main_sv_42_129:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		silver_features.PgeneratedTest_CommentParsing_sv_4_0.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_CommentParsing_sv_4_0(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_CommentParsing_sv_4_0()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_CommentParsing_sv_5_1.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_CommentParsing_sv_5_1(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_CommentParsing_sv_5_1()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_CommentParsing_sv_6_2.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_CommentParsing_sv_6_2(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_CommentParsing_sv_6_2()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_CommentParsing_sv_7_3.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_CommentParsing_sv_7_3(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_CommentParsing_sv_7_3()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_CommentParsing_sv_8_4.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_CommentParsing_sv_8_4(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_CommentParsing_sv_8_4()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_CommentParsing_sv_9_5.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_CommentParsing_sv_9_5(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_CommentParsing_sv_9_5()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_CommentParsing_sv_10_6.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_CommentParsing_sv_10_6(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_CommentParsing_sv_10_6()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_CommentParsing_sv_11_7.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_CommentParsing_sv_11_7(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_CommentParsing_sv_11_7()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_CommentParsing_sv_12_8.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_CommentParsing_sv_12_8(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_CommentParsing_sv_12_8()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_CommentParsing_sv_13_9.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_CommentParsing_sv_13_9(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_CommentParsing_sv_13_9()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION zeroArgFunction Integer ::= 
		//FUNCTION twoArgFunction Integer ::= s::String i::Integer 
		silver_features.PgeneratedTest_FuncManipulation_sv_47_10.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncManipulation_sv_47_10(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncManipulation_sv_47_10()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_FuncManipulation_sv_48_11.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncManipulation_sv_48_11(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncManipulation_sv_48_11()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.Psection.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_FuncManipulation_sv_67_12.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncManipulation_sv_67_12(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncManipulation_sv_67_12()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_FuncManipulation_sv_68_13.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncManipulation_sv_68_13(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncManipulation_sv_68_13()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_FuncManipulation_sv_72_14.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncManipulation_sv_72_14(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncManipulation_sv_72_14()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_FuncManipulation_sv_93_15.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncManipulation_sv_93_15(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncManipulation_sv_93_15()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION addTwo Integer ::= a::Integer b::Integer 
		silver_features.PgeneratedTest_FuncManipulation_sv_101_16.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncManipulation_sv_101_16(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncManipulation_sv_101_16()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_FuncManipulation_sv_107_17.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncManipulation_sv_107_17(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncManipulation_sv_107_17()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.Pfmadd.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_FuncManipulation_sv_124_18.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncManipulation_sv_124_18(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncManipulation_sv_124_18()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_FuncManipulation_sv_125_19.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncManipulation_sv_125_19(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncManipulation_sv_125_19()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Types_sv_74_20.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Types_sv_74_20(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Types_sv_74_20()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Types_sv_75_21.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Types_sv_75_21(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Types_sv_75_21()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Types_sv_76_22.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Types_sv_76_22(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Types_sv_76_22()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Types_sv_77_23.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Types_sv_77_23(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Types_sv_77_23()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Types_sv_78_24.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Types_sv_78_24(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Types_sv_78_24()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Types_sv_98_25.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Types_sv_98_25(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Types_sv_98_25()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Types_sv_99_26.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Types_sv_99_26(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Types_sv_99_26()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PconsACTNT.initProductionAttributeDefinitions();
		silver_features.PconsACTNT2.initProductionAttributeDefinitions();
		silver_features.PnilACTNT.initProductionAttributeDefinitions();
		silver_features.PnilACTNT2.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_AutoCopy_sv_46_27.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_AutoCopy_sv_46_27(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_AutoCopy_sv_46_27()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.Pbar.initProductionAttributeDefinitions();
		silver_features.Pbar2.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_ForwardKeyword_sv_29_28.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_ForwardKeyword_sv_29_28(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_ForwardKeyword_sv_29_28()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_ForwardKeyword_sv_30_29.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_ForwardKeyword_sv_30_29(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_ForwardKeyword_sv_30_29()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Globals_sv_8_30.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Globals_sv_8_30(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Globals_sv_8_30()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Globals_sv_13_31.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Globals_sv_13_31(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Globals_sv_13_31()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Globals_sv_18_32.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Globals_sv_18_32(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Globals_sv_18_32()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Globals_sv_19_33.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Globals_sv_19_33(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Globals_sv_19_33()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Globals_sv_25_34.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Globals_sv_25_34(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Globals_sv_25_34()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PTfoo.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_Globals_sv_39_35.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Globals_sv_39_35(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Globals_sv_39_35()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Globals_sv_43_36.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Globals_sv_43_36(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Globals_sv_43_36()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PintTestProd.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_FuncProdTypes_sv_12_37.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncProdTypes_sv_12_37(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncProdTypes_sv_12_37()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PintAdd.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_FuncProdTypes_sv_33_38.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_FuncProdTypes_sv_33_38(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_FuncProdTypes_sv_33_38()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION makeInc (Integer ::= Integer) ::= i::Integer 
		silver_features.PgeneratedTest_Lambda_sv_9_39.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Lambda_sv_9_39(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Lambda_sv_9_39()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Lambda_sv_12_40.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Lambda_sv_12_40(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Lambda_sv_12_40()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Lambda_sv_15_41.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Lambda_sv_15_41(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Lambda_sv_15_41()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Lambda_sv_18_42.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Lambda_sv_18_42(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Lambda_sv_18_42()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Lambda_sv_22_43.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Lambda_sv_22_43(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Lambda_sv_22_43()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Lambda_sv_25_44.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Lambda_sv_25_44(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Lambda_sv_25_44()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Lambda_sv_26_45.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Lambda_sv_26_45(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Lambda_sv_26_45()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Lambda_sv_31_46.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Lambda_sv_31_46(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Lambda_sv_31_46()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Lambda_sv_34_47.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Lambda_sv_34_47(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Lambda_sv_34_47()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_EasyTerm_sv_5_48.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_EasyTerm_sv_5_48(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_EasyTerm_sv_5_48()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PconsFTNT.initProductionAttributeDefinitions();
		silver_features.PconsFTNT2.initProductionAttributeDefinitions();
		silver_features.PnilFTNT.initProductionAttributeDefinitions();
		silver_features.PnilFTNT2.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_Functor_sv_62_49.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Functor_sv_62_49(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Functor_sv_62_49()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Functor_sv_64_50.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Functor_sv_64_50(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Functor_sv_64_50()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_8_51.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_8_51(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_8_51()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_10_52.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_10_52(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_10_52()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_15_53.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_15_53(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_15_53()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_17_54.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_17_54(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_17_54()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_19_55.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_19_55(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_19_55()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_21_56.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_21_56(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_21_56()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_23_57.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_23_57(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_23_57()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_25_58.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_25_58(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_25_58()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_28_59.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_28_59(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_28_59()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_29_60.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_29_60(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_29_60()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_32_61.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_32_61(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_32_61()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_33_62.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_33_62(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_33_62()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_34_63.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_34_63(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_34_63()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_35_64.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_35_64(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_35_64()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_36_65.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_36_65(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_36_65()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_37_66.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_37_66(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_37_66()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_38_67.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_38_67(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_38_67()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_39_68.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_39_68(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_39_68()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_42_69.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_42_69(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_42_69()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_46_70.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_46_70(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_46_70()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_47_71.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_47_71(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_47_71()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_50_72.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_50_72(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_50_72()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Templating_sv_52_73.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Templating_sv_52_73(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Templating_sv_52_73()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Monad_sv_11_74.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Monad_sv_11_74(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Monad_sv_11_74()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Monad_sv_20_75.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Monad_sv_20_75(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Monad_sv_20_75()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Monad_sv_31_76.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Monad_sv_31_76(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Monad_sv_31_76()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Monad_sv_32_77.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Monad_sv_32_77(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Monad_sv_32_77()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Monad_sv_41_78.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Monad_sv_41_78(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Monad_sv_41_78()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PoneElem.initProductionAttributeDefinitions();
		silver_features.PconsElem.initProductionAttributeDefinitions();
		silver_features.PmoreElem.initProductionAttributeDefinitions();
		silver_features.Pp_silver_features_Convenience_sv_12_11.initProductionAttributeDefinitions();
		silver_features.PcolLeaf.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_Collections_sv_17_79.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_17_79(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_17_79()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PcolNode.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION colNode top ::= c1::ColNT c2::ColNT 
		// top.colSyn <- " b "
		if(silver_features.PcolNode.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT] == null)
			silver_features.PcolNode.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT] = new silver_features.CAcolSyn(silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT);
		((common.CollectionAttribute)silver_features.PcolNode.synthesizedAttributes[silver_features.Init.silver_features_colSyn__ON__silver_features_ColNT]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter(" b ")); } });
		// c1.colInh <- " c "
		if(silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c1][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT] == null)
			silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c1][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT] = new silver_features.CAcolInh();
		((common.CollectionAttribute)silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c1][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter(" c ")); } });
		// c2.colInh <- " e "
		if(silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c2][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT] == null)
			silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c2][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT] = new silver_features.CAcolInh();
		((common.CollectionAttribute)silver_features.PcolNode.childInheritedAttributes[silver_features.PcolNode.i_c2][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter(" e ")); } });
		silver_features.PgeneratedTest_Collections_sv_38_80.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_38_80(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_38_80()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PcolFwdNode.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_Collections_sv_47_81.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_47_81(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_47_81()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PcolProdLeaf.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION colProdLeaf top ::= 
		// lo <- " k "
		((common.CollectionAttribute)silver_features.PcolProdLeaf.localAttributes[silver_features.Init.lo__ON__silver_features_colProdLeaf]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter(" k ")); } });
		silver_features.PgeneratedTest_Collections_sv_63_82.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_63_82(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_63_82()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_64_83.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_64_83(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_64_83()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION testLocalInheritedCollections String ::= 
		// t.colInh := "x"
		if(silver_features.PtestLocalInheritedCollections.localInheritedAttributes[silver_features.Init.t__ON__silver_features_testLocalInheritedCollections][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT] == null)
			silver_features.PtestLocalInheritedCollections.localInheritedAttributes[silver_features.Init.t__ON__silver_features_testLocalInheritedCollections][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT] = new silver_features.CAcolInh();
		((common.CollectionAttribute)silver_features.PtestLocalInheritedCollections.localInheritedAttributes[silver_features.Init.t__ON__silver_features_testLocalInheritedCollections][silver_features.Init.silver_features_colInh__ON__silver_features_ColNT]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("x")); } });
		// t = colLeaf(,)
		silver_features.PtestLocalInheritedCollections.localAttributes[silver_features.Init.t__ON__silver_features_testLocalInheritedCollections] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver_features.NColNT)new silver_features.PcolLeaf()); } };
		silver_features.PgeneratedTest_Collections_sv_74_84.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_74_84(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_74_84()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PcolTest1.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_Collections_sv_133_85.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_133_85(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_133_85()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_134_86.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_134_86(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_134_86()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_135_87.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_135_87(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_135_87()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_136_88.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_136_88(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_136_88()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_137_89.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_137_89(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_137_89()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_138_90.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_138_90(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_138_90()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PcolTest2.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_Collections_sv_155_91.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_155_91(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_155_91()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_156_92.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_156_92(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_156_92()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_157_93.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_157_93(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_157_93()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_158_94.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_158_94(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_158_94()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_159_95.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_159_95(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_159_95()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PcolTest3.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_Collections_sv_176_96.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_176_96(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_176_96()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_177_97.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_177_97(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_177_97()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_178_98.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_178_98(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_178_98()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_179_99.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_179_99(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_179_99()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_180_100.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_180_100(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_180_100()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Collections_sv_181_101.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Collections_sv_181_101(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Collections_sv_181_101()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PaDevProd.initProductionAttributeDefinitions();
		silver_features.PbouncerDecProd.initProductionAttributeDefinitions();
		//FUNCTION thunkUndecGoWrong ADecoratedValue ::= ac::ADecoratedValue 
		silver_features.PgeneratedTest_ThunkTransforms_sv_37_102.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_ThunkTransforms_sv_37_102(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_ThunkTransforms_sv_37_102()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Scoping_sv_4_103.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Scoping_sv_4_103(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Scoping_sv_4_103()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PrightCodeScoping.initProductionAttributeDefinitions();
		silver_features.PrightCodeNaming.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION rightCodeNaming rightCodeScoping ::= top::String 
		// sc = rightCodeScoping
		silver_features.PrightCodeNaming.localAttributes[silver_features.Init.sc__ON__silver_features_rightCodeNaming] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// tp = top
		silver_features.PrightCodeNaming.localAttributes[silver_features.Init.tp__ON__silver_features_rightCodeNaming] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver_features.PrightCodeNaming.i_rightCodeScoping)); } };
		silver_features.Pcontext.initProductionAttributeDefinitions();
		//FUNCTION context2 Context ::= 
		silver_features.Pdefaultattr1.initProductionAttributeDefinitions();
		silver_features.Pdefaultattr2.initProductionAttributeDefinitions();
		silver_features.Pdefaultattrn.initProductionAttributeDefinitions();
		silver_features.Pdefaultfwd.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for DefaultAttrNT
		// top.defaultsyn = 0
		silver_features.NDefaultAttrNT.defaultSynthesizedAttributes[silver_features.Init.silver_features_defaultsyn__ON__silver_features_DefaultAttrNT] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// top.defaultsyn2 = top.defaultsyn
		silver_features.NDefaultAttrNT.defaultSynthesizedAttributes[silver_features.Init.silver_features_defaultsyn2__ON__silver_features_DefaultAttrNT] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.synthesized(silver_features.Init.silver_features_defaultsyn__ON__silver_features_DefaultAttrNT)); } };
		silver_features.PgeneratedTest_DefaultAttrs_sv_38_104.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_DefaultAttrs_sv_38_104(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_DefaultAttrs_sv_38_104()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_DefaultAttrs_sv_39_105.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_DefaultAttrs_sv_39_105(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_DefaultAttrs_sv_39_105()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_DefaultAttrs_sv_40_106.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_DefaultAttrs_sv_40_106(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_DefaultAttrs_sv_40_106()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_DefaultAttrs_sv_41_107.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_DefaultAttrs_sv_41_107(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_DefaultAttrs_sv_41_107()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_DefaultAttrs_sv_42_108.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_DefaultAttrs_sv_42_108(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_DefaultAttrs_sv_42_108()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_DefaultAttrs_sv_44_109.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_DefaultAttrs_sv_44_109(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_DefaultAttrs_sv_44_109()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_DefaultAttrs_sv_45_110.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_DefaultAttrs_sv_45_110(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_DefaultAttrs_sv_45_110()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_DefaultAttrs_sv_46_111.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_DefaultAttrs_sv_46_111(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_DefaultAttrs_sv_46_111()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_DefaultAttrs_sv_47_112.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_DefaultAttrs_sv_47_112(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_DefaultAttrs_sv_47_112()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION main IOVal<Integer> ::= [String] mainIO::core:IO 
		// testResults = silver_tests(,)
		silver_features.Pmain.localAttributes[silver_features.Init.testResults__ON__silver_features_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.testing.NTestSuite)new silver_features.Psilver_tests()); } };
		// testResults.ioIn = mainIO
		silver_features.Pmain.localInheritedAttributes[silver_features.Init.testResults__ON__silver_features_main][silver.testing.Init.silver_testing_ioIn__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(silver_features.Pmain.i_mainIO)); } };
		silver_features.Psilver_tests.initProductionAttributeDefinitions();
		silver_features.PgeneratedTest_Main_sv_15_113.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_15_113(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_15_113()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_16_114.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_16_114(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_16_114()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_17_115.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_17_115(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_17_115()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_18_116.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_18_116(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_18_116()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_21_117.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_21_117(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_21_117()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_23_118.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_23_118(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_23_118()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_24_119.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_24_119(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_24_119()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_26_120.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_26_120(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_26_120()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_27_121.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_27_121(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_27_121()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_28_122.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_28_122(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_28_122()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_31_123.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_31_123(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_31_123()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_32_124.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_32_124(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_32_124()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_33_125.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_33_125(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_33_125()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_37_126.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_37_126(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_37_126()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_38_127.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_38_127(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_38_127()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_41_128.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_41_128(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_41_128()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.PgeneratedTest_Main_sv_42_129.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Main_sv_42_129(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.PgeneratedTest_Main_sv_42_129()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__silver_features_generatedTest_CommentParsing_sv_4_0 = 0;
	public static int count_local__ON__silver_features_generatedTest_CommentParsing_sv_5_1 = 0;
	public static int count_local__ON__silver_features_generatedTest_CommentParsing_sv_6_2 = 0;
	public static int count_local__ON__silver_features_generatedTest_CommentParsing_sv_7_3 = 0;
	public static int count_local__ON__silver_features_generatedTest_CommentParsing_sv_8_4 = 0;
	public static int count_local__ON__silver_features_generatedTest_CommentParsing_sv_9_5 = 0;
	public static int count_local__ON__silver_features_generatedTest_CommentParsing_sv_10_6 = 0;
	public static int count_local__ON__silver_features_generatedTest_CommentParsing_sv_11_7 = 0;
	public static int count_local__ON__silver_features_generatedTest_CommentParsing_sv_12_8 = 0;
	public static int count_local__ON__silver_features_generatedTest_CommentParsing_sv_13_9 = 0;
	public static int count_local__ON__silver_features_zeroArgFunction = 0;
	public static int count_local__ON__silver_features_twoArgFunction = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncManipulation_sv_47_10 = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncManipulation_sv_48_11 = 0;
	public static int count_inh__ON__Section = 0;
	public static int count_syn__ON__Section = 0;
	public static int count_local__ON__silver_features_section = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncManipulation_sv_67_12 = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncManipulation_sv_68_13 = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncManipulation_sv_72_14 = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncManipulation_sv_93_15 = 0;
	public static int count_local__ON__silver_features_addTwo = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncManipulation_sv_101_16 = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncManipulation_sv_107_17 = 0;
	public static int count_inh__ON__FuncManipNT = 0;
	public static int count_syn__ON__FuncManipNT = 0;
	public static int count_local__ON__silver_features_fmadd = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncManipulation_sv_124_18 = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncManipulation_sv_125_19 = 0;
	public static int count_inh__ON__NTZero = 0;
	public static int count_syn__ON__NTZero = 0;
	public static int count_inh__ON__NTOne = 0;
	public static int count_syn__ON__NTOne = 0;
	public static int count_inh__ON__NTTwo = 0;
	public static int count_syn__ON__NTTwo = 0;
	public static int count_local__ON__silver_features_generatedTest_Types_sv_74_20 = 0;
	public static int count_local__ON__silver_features_generatedTest_Types_sv_75_21 = 0;
	public static int count_local__ON__silver_features_generatedTest_Types_sv_76_22 = 0;
	public static int count_local__ON__silver_features_generatedTest_Types_sv_77_23 = 0;
	public static int count_local__ON__silver_features_generatedTest_Types_sv_78_24 = 0;
	public static int count_local__ON__silver_features_generatedTest_Types_sv_98_25 = 0;
	public static int count_local__ON__silver_features_generatedTest_Types_sv_99_26 = 0;
	public static int count_inh__ON__AutoCopyTestNT = 0;
	public static int count_syn__ON__AutoCopyTestNT = 0;
	public static int count_inh__ON__AutoCopyTestNT2 = 0;
	public static int count_syn__ON__AutoCopyTestNT2 = 0;
	public static int count_local__ON__silver_features_consACTNT = 0;
	public static int count_local__ON__silver_features_consACTNT2 = 0;
	public static int count_local__ON__silver_features_nilACTNT = 0;
	public static int count_local__ON__silver_features_nilACTNT2 = 0;
	public static int count_local__ON__silver_features_generatedTest_AutoCopy_sv_46_27 = 0;
	public static int count_inh__ON__ForwardKeyword = 0;
	public static int count_syn__ON__ForwardKeyword = 0;
	public static int count_local__ON__silver_features_bar = 0;
	public static int count_local__ON__silver_features_bar2 = 0;
	public static int count_local__ON__silver_features_generatedTest_ForwardKeyword_sv_29_28 = 0;
	public static int count_local__ON__silver_features_generatedTest_ForwardKeyword_sv_30_29 = 0;
	public static int count_local__ON__silver_features_generatedTest_Globals_sv_8_30 = 0;
	public static int count_local__ON__silver_features_generatedTest_Globals_sv_13_31 = 0;
	public static int count_local__ON__silver_features_generatedTest_Globals_sv_18_32 = 0;
	public static int count_local__ON__silver_features_generatedTest_Globals_sv_19_33 = 0;
	public static int count_local__ON__silver_features_generatedTest_Globals_sv_25_34 = 0;
	public static int count_inh__ON__Tglob = 0;
	public static int count_syn__ON__Tglob = 0;
	public static int count_local__ON__silver_features_Tfoo = 0;
	public static int count_local__ON__silver_features_generatedTest_Globals_sv_39_35 = 0;
	public static int count_local__ON__silver_features_generatedTest_Globals_sv_43_36 = 0;
	public static int count_inh__ON__IntNT = 0;
	public static int count_syn__ON__IntNT = 0;
	public static int count_local__ON__silver_features_intTestProd = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncProdTypes_sv_12_37 = 0;
	public static int count_inh__ON__ListNT = 0;
	public static int count_syn__ON__ListNT = 0;
	public static int count_local__ON__silver_features_intAdd = 0;
	public static int count_local__ON__silver_features_generatedTest_FuncProdTypes_sv_33_38 = 0;
	public static int count_local__ON__silver_features_makeInc = 0;
	public static int count_local__ON__silver_features_generatedTest_Lambda_sv_9_39 = 0;
	public static int count_local__ON__silver_features_generatedTest_Lambda_sv_12_40 = 0;
	public static int count_local__ON__silver_features_generatedTest_Lambda_sv_15_41 = 0;
	public static int count_local__ON__silver_features_generatedTest_Lambda_sv_18_42 = 0;
	public static int count_local__ON__silver_features_generatedTest_Lambda_sv_22_43 = 0;
	public static int count_local__ON__silver_features_generatedTest_Lambda_sv_25_44 = 0;
	public static int count_local__ON__silver_features_generatedTest_Lambda_sv_26_45 = 0;
	public static int count_local__ON__silver_features_generatedTest_Lambda_sv_31_46 = 0;
	public static int count_local__ON__silver_features_generatedTest_Lambda_sv_34_47 = 0;
	public static int count_local__ON__silver_features_generatedTest_EasyTerm_sv_5_48 = 0;
	public static int count_inh__ON__FunctorTestNT = 0;
	public static int count_syn__ON__FunctorTestNT = 0;
	public static int count_inh__ON__FunctorTestNT2 = 0;
	public static int count_syn__ON__FunctorTestNT2 = 0;
	public static int count_local__ON__silver_features_consFTNT = 0;
	public static int count_local__ON__silver_features_consFTNT2 = 0;
	public static int count_local__ON__silver_features_nilFTNT = 0;
	public static int count_local__ON__silver_features_nilFTNT2 = 0;
	public static int count_local__ON__silver_features_generatedTest_Functor_sv_62_49 = 0;
	public static int count_local__ON__silver_features_generatedTest_Functor_sv_64_50 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_8_51 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_10_52 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_15_53 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_17_54 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_19_55 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_21_56 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_23_57 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_25_58 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_28_59 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_29_60 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_32_61 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_33_62 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_34_63 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_35_64 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_36_65 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_37_66 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_38_67 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_39_68 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_42_69 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_46_70 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_47_71 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_50_72 = 0;
	public static int count_local__ON__silver_features_generatedTest_Templating_sv_52_73 = 0;
	public static int count_local__ON__silver_features_generatedTest_Monad_sv_11_74 = 0;
	public static int count_local__ON__silver_features_generatedTest_Monad_sv_20_75 = 0;
	public static int count_local__ON__silver_features_generatedTest_Monad_sv_31_76 = 0;
	public static int count_local__ON__silver_features_generatedTest_Monad_sv_32_77 = 0;
	public static int count_local__ON__silver_features_generatedTest_Monad_sv_41_78 = 0;
	public static int count_inh__ON__ConcreteProductions = 0;
	public static int count_syn__ON__ConcreteProductions = 0;
	public static int count_local__ON__silver_features_oneElem = 0;
	public static int count_local__ON__silver_features_consElem = 0;
	public static int count_local__ON__silver_features_moreElem = 0;
	public static int count_local__ON__silver_features_p_silver_features_Convenience_sv_12_11 = 0;
	public static int count_inh__ON__ColNT = 0;
	public static int count_syn__ON__ColNT = 0;
	public static int count_local__ON__silver_features_colLeaf = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_17_79 = 0;
	public static int count_local__ON__silver_features_colNode = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_38_80 = 0;
	public static int count_local__ON__silver_features_colFwdNode = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_47_81 = 0;
	public static int count_local__ON__silver_features_colProdLeaf = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_63_82 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_64_83 = 0;
	public static int count_local__ON__silver_features_testLocalInheritedCollections = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_74_84 = 0;
	public static int count_local__ON__silver_features_colTest1 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_133_85 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_134_86 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_135_87 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_136_88 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_137_89 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_138_90 = 0;
	public static int count_local__ON__silver_features_colTest2 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_155_91 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_156_92 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_157_93 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_158_94 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_159_95 = 0;
	public static int count_local__ON__silver_features_colTest3 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_176_96 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_177_97 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_178_98 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_179_99 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_180_100 = 0;
	public static int count_local__ON__silver_features_generatedTest_Collections_sv_181_101 = 0;
	public static int count_inh__ON__ADecoratedValue = 0;
	public static int count_syn__ON__ADecoratedValue = 0;
	public static int count_local__ON__silver_features_aDevProd = 0;
	public static int count_local__ON__silver_features_bouncerDecProd = 0;
	public static int count_local__ON__silver_features_thunkUndecGoWrong = 0;
	public static int count_local__ON__silver_features_generatedTest_ThunkTransforms_sv_37_102 = 0;
	public static int count_local__ON__silver_features_generatedTest_Scoping_sv_4_103 = 0;
	public static int count_inh__ON__Scope = 0;
	public static int count_syn__ON__Scope = 0;
	public static int count_local__ON__silver_features_rightCodeScoping = 0;
	public static int count_local__ON__silver_features_rightCodeNaming = 0;
	public static int count_inh__ON__Context = 0;
	public static int count_syn__ON__Context = 0;
	public static int count_local__ON__silver_features_context = 0;
	public static int count_local__ON__silver_features_context2 = 0;
	public static int count_inh__ON__DefaultAttrNT = 0;
	public static int count_syn__ON__DefaultAttrNT = 0;
	public static int count_local__ON__silver_features_defaultattr1 = 0;
	public static int count_local__ON__silver_features_defaultattr2 = 0;
	public static int count_local__ON__silver_features_defaultattrn = 0;
	public static int count_local__ON__silver_features_defaultfwd = 0;
	public static int count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_38_104 = 0;
	public static int count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_39_105 = 0;
	public static int count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_40_106 = 0;
	public static int count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_41_107 = 0;
	public static int count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_42_108 = 0;
	public static int count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_44_109 = 0;
	public static int count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_45_110 = 0;
	public static int count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_46_111 = 0;
	public static int count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_47_112 = 0;
	public static int count_local__ON__silver_features_main = 0;
	public static int count_local__ON__silver_features_silver_tests = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_15_113 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_16_114 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_17_115 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_18_116 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_21_117 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_23_118 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_24_119 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_26_120 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_27_121 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_28_122 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_31_123 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_32_124 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_33_125 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_37_126 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_38_127 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_41_128 = 0;
	public static int count_local__ON__silver_features_generatedTest_Main_sv_42_129 = 0;
public static final int value__ON__silver_features_generatedTest_CommentParsing_sv_4_0 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_4_0++;
public static final int expected__ON__silver_features_generatedTest_CommentParsing_sv_4_0 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_4_0++;
public static final int value__ON__silver_features_generatedTest_CommentParsing_sv_5_1 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_5_1++;
public static final int expected__ON__silver_features_generatedTest_CommentParsing_sv_5_1 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_5_1++;
public static final int value__ON__silver_features_generatedTest_CommentParsing_sv_6_2 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_6_2++;
public static final int expected__ON__silver_features_generatedTest_CommentParsing_sv_6_2 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_6_2++;
public static final int value__ON__silver_features_generatedTest_CommentParsing_sv_7_3 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_7_3++;
public static final int expected__ON__silver_features_generatedTest_CommentParsing_sv_7_3 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_7_3++;
public static final int value__ON__silver_features_generatedTest_CommentParsing_sv_8_4 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_8_4++;
public static final int expected__ON__silver_features_generatedTest_CommentParsing_sv_8_4 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_8_4++;
public static final int value__ON__silver_features_generatedTest_CommentParsing_sv_9_5 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_9_5++;
public static final int expected__ON__silver_features_generatedTest_CommentParsing_sv_9_5 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_9_5++;
public static final int value__ON__silver_features_generatedTest_CommentParsing_sv_10_6 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_10_6++;
public static final int expected__ON__silver_features_generatedTest_CommentParsing_sv_10_6 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_10_6++;
public static final int value__ON__silver_features_generatedTest_CommentParsing_sv_11_7 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_11_7++;
public static final int expected__ON__silver_features_generatedTest_CommentParsing_sv_11_7 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_11_7++;
public static final int value__ON__silver_features_generatedTest_CommentParsing_sv_12_8 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_12_8++;
public static final int expected__ON__silver_features_generatedTest_CommentParsing_sv_12_8 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_12_8++;
public static final int value__ON__silver_features_generatedTest_CommentParsing_sv_13_9 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_13_9++;
public static final int expected__ON__silver_features_generatedTest_CommentParsing_sv_13_9 = silver_features.Init.count_local__ON__silver_features_generatedTest_CommentParsing_sv_13_9++;
public static final int value__ON__silver_features_generatedTest_FuncManipulation_sv_47_10 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_47_10++;
public static final int expected__ON__silver_features_generatedTest_FuncManipulation_sv_47_10 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_47_10++;
public static final int value__ON__silver_features_generatedTest_FuncManipulation_sv_48_11 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_48_11++;
public static final int expected__ON__silver_features_generatedTest_FuncManipulation_sv_48_11 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_48_11++;
public static final int silver_features_sec_valid__ON__silver_features_Section = silver_features.Init.count_syn__ON__Section++;
public static final int silver_features_sec_inv__ON__silver_features_Section = silver_features.Init.count_syn__ON__Section++;
public static final int silver_features_sec_inv3__ON__silver_features_Section = silver_features.Init.count_inh__ON__Section++;
public static final int value__ON__silver_features_generatedTest_FuncManipulation_sv_67_12 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_67_12++;
public static final int expected__ON__silver_features_generatedTest_FuncManipulation_sv_67_12 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_67_12++;
public static final int value__ON__silver_features_generatedTest_FuncManipulation_sv_68_13 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_68_13++;
public static final int expected__ON__silver_features_generatedTest_FuncManipulation_sv_68_13 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_68_13++;
public static final int value__ON__silver_features_generatedTest_FuncManipulation_sv_72_14 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_72_14++;
public static final int expected__ON__silver_features_generatedTest_FuncManipulation_sv_72_14 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_72_14++;
public static final int value__ON__silver_features_generatedTest_FuncManipulation_sv_93_15 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_93_15++;
public static final int expected__ON__silver_features_generatedTest_FuncManipulation_sv_93_15 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_93_15++;
public static final int value__ON__silver_features_generatedTest_FuncManipulation_sv_101_16 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_101_16++;
public static final int expected__ON__silver_features_generatedTest_FuncManipulation_sv_101_16 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_101_16++;
public static final int value__ON__silver_features_generatedTest_FuncManipulation_sv_107_17 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_107_17++;
public static final int expected__ON__silver_features_generatedTest_FuncManipulation_sv_107_17 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_107_17++;
public static final int silver_features_fmeval__ON__silver_features_FuncManipNT = silver_features.Init.count_syn__ON__FuncManipNT++;
public static final int value__ON__silver_features_generatedTest_FuncManipulation_sv_124_18 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_124_18++;
public static final int expected__ON__silver_features_generatedTest_FuncManipulation_sv_124_18 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_124_18++;
public static final int value__ON__silver_features_generatedTest_FuncManipulation_sv_125_19 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_125_19++;
public static final int expected__ON__silver_features_generatedTest_FuncManipulation_sv_125_19 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_125_19++;
public static final int value__ON__silver_features_generatedTest_Types_sv_74_20 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_74_20++;
public static final int expected__ON__silver_features_generatedTest_Types_sv_74_20 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_74_20++;
public static final int value__ON__silver_features_generatedTest_Types_sv_75_21 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_75_21++;
public static final int expected__ON__silver_features_generatedTest_Types_sv_75_21 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_75_21++;
public static final int value__ON__silver_features_generatedTest_Types_sv_76_22 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_76_22++;
public static final int expected__ON__silver_features_generatedTest_Types_sv_76_22 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_76_22++;
public static final int value__ON__silver_features_generatedTest_Types_sv_77_23 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_77_23++;
public static final int expected__ON__silver_features_generatedTest_Types_sv_77_23 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_77_23++;
public static final int value__ON__silver_features_generatedTest_Types_sv_78_24 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_78_24++;
public static final int expected__ON__silver_features_generatedTest_Types_sv_78_24 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_78_24++;
public static final int value__ON__silver_features_generatedTest_Types_sv_98_25 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_98_25++;
public static final int expected__ON__silver_features_generatedTest_Types_sv_98_25 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_98_25++;
public static final int value__ON__silver_features_generatedTest_Types_sv_99_26 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_99_26++;
public static final int expected__ON__silver_features_generatedTest_Types_sv_99_26 = silver_features.Init.count_local__ON__silver_features_generatedTest_Types_sv_99_26++;
public static final int silver_features_autoSyn__ON__silver_features_AutoCopyTestNT = silver_features.Init.count_syn__ON__AutoCopyTestNT++;
public static final int silver_features_autoTest__ON__silver_features_AutoCopyTestNT2 = silver_features.Init.count_inh__ON__AutoCopyTestNT2++;
public static final int silver_features_autoSyn__ON__silver_features_AutoCopyTestNT2 = silver_features.Init.count_syn__ON__AutoCopyTestNT2++;
public static final int silver_features_autoTest__ON__silver_features_AutoCopyTestNT = silver_features.Init.count_inh__ON__AutoCopyTestNT++;
public static final int value__ON__silver_features_generatedTest_AutoCopy_sv_46_27 = silver_features.Init.count_local__ON__silver_features_generatedTest_AutoCopy_sv_46_27++;
public static final int expected__ON__silver_features_generatedTest_AutoCopy_sv_46_27 = silver_features.Init.count_local__ON__silver_features_generatedTest_AutoCopy_sv_46_27++;
public static final int silver_features_fkSyn1__ON__silver_features_ForwardKeyword = silver_features.Init.count_syn__ON__ForwardKeyword++;
public static final int silver_features_fkInh1__ON__silver_features_ForwardKeyword = silver_features.Init.count_inh__ON__ForwardKeyword++;
public static final int silver_features_fkSyn2__ON__silver_features_ForwardKeyword = silver_features.Init.count_syn__ON__ForwardKeyword++;
public static final int silver_features_fkInh2__ON__silver_features_ForwardKeyword = silver_features.Init.count_inh__ON__ForwardKeyword++;
public static final int value__ON__silver_features_generatedTest_ForwardKeyword_sv_29_28 = silver_features.Init.count_local__ON__silver_features_generatedTest_ForwardKeyword_sv_29_28++;
public static final int expected__ON__silver_features_generatedTest_ForwardKeyword_sv_29_28 = silver_features.Init.count_local__ON__silver_features_generatedTest_ForwardKeyword_sv_29_28++;
public static final int value__ON__silver_features_generatedTest_ForwardKeyword_sv_30_29 = silver_features.Init.count_local__ON__silver_features_generatedTest_ForwardKeyword_sv_30_29++;
public static final int expected__ON__silver_features_generatedTest_ForwardKeyword_sv_30_29 = silver_features.Init.count_local__ON__silver_features_generatedTest_ForwardKeyword_sv_30_29++;
public static final int value__ON__silver_features_generatedTest_Globals_sv_8_30 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_8_30++;
public static final int expected__ON__silver_features_generatedTest_Globals_sv_8_30 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_8_30++;
public static final int value__ON__silver_features_generatedTest_Globals_sv_13_31 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_13_31++;
public static final int expected__ON__silver_features_generatedTest_Globals_sv_13_31 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_13_31++;
public static final int value__ON__silver_features_generatedTest_Globals_sv_18_32 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_18_32++;
public static final int expected__ON__silver_features_generatedTest_Globals_sv_18_32 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_18_32++;
public static final int value__ON__silver_features_generatedTest_Globals_sv_19_33 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_19_33++;
public static final int expected__ON__silver_features_generatedTest_Globals_sv_19_33 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_19_33++;
public static final int value__ON__silver_features_generatedTest_Globals_sv_25_34 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_25_34++;
public static final int expected__ON__silver_features_generatedTest_Globals_sv_25_34 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_25_34++;
public static final int silver_features_strGlob__ON__silver_features_Tglob = silver_features.Init.count_syn__ON__Tglob++;
public static final int value__ON__silver_features_generatedTest_Globals_sv_39_35 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_39_35++;
public static final int expected__ON__silver_features_generatedTest_Globals_sv_39_35 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_39_35++;
public static final int value__ON__silver_features_generatedTest_Globals_sv_43_36 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_43_36++;
public static final int expected__ON__silver_features_generatedTest_Globals_sv_43_36 = silver_features.Init.count_local__ON__silver_features_generatedTest_Globals_sv_43_36++;
public static final int silver_features_intValue__ON__silver_features_IntNT = silver_features.Init.count_syn__ON__IntNT++;
public static final int value__ON__silver_features_generatedTest_FuncProdTypes_sv_12_37 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncProdTypes_sv_12_37++;
public static final int expected__ON__silver_features_generatedTest_FuncProdTypes_sv_12_37 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncProdTypes_sv_12_37++;
public static final int value__ON__silver_features_generatedTest_FuncProdTypes_sv_33_38 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncProdTypes_sv_33_38++;
public static final int expected__ON__silver_features_generatedTest_FuncProdTypes_sv_33_38 = silver_features.Init.count_local__ON__silver_features_generatedTest_FuncProdTypes_sv_33_38++;
public static final int value__ON__silver_features_generatedTest_Lambda_sv_9_39 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_9_39++;
public static final int expected__ON__silver_features_generatedTest_Lambda_sv_9_39 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_9_39++;
public static final int value__ON__silver_features_generatedTest_Lambda_sv_12_40 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_12_40++;
public static final int expected__ON__silver_features_generatedTest_Lambda_sv_12_40 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_12_40++;
public static final int value__ON__silver_features_generatedTest_Lambda_sv_15_41 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_15_41++;
public static final int expected__ON__silver_features_generatedTest_Lambda_sv_15_41 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_15_41++;
public static final int value__ON__silver_features_generatedTest_Lambda_sv_18_42 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_18_42++;
public static final int expected__ON__silver_features_generatedTest_Lambda_sv_18_42 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_18_42++;
public static final int value__ON__silver_features_generatedTest_Lambda_sv_22_43 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_22_43++;
public static final int expected__ON__silver_features_generatedTest_Lambda_sv_22_43 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_22_43++;
public static final int value__ON__silver_features_generatedTest_Lambda_sv_25_44 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_25_44++;
public static final int expected__ON__silver_features_generatedTest_Lambda_sv_25_44 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_25_44++;
public static final int value__ON__silver_features_generatedTest_Lambda_sv_26_45 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_26_45++;
public static final int expected__ON__silver_features_generatedTest_Lambda_sv_26_45 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_26_45++;
public static final int value__ON__silver_features_generatedTest_Lambda_sv_31_46 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_31_46++;
public static final int expected__ON__silver_features_generatedTest_Lambda_sv_31_46 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_31_46++;
public static final int value__ON__silver_features_generatedTest_Lambda_sv_34_47 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_34_47++;
public static final int expected__ON__silver_features_generatedTest_Lambda_sv_34_47 = silver_features.Init.count_local__ON__silver_features_generatedTest_Lambda_sv_34_47++;
public static final int value__ON__silver_features_generatedTest_EasyTerm_sv_5_48 = silver_features.Init.count_local__ON__silver_features_generatedTest_EasyTerm_sv_5_48++;
public static final int expected__ON__silver_features_generatedTest_EasyTerm_sv_5_48 = silver_features.Init.count_local__ON__silver_features_generatedTest_EasyTerm_sv_5_48++;
public static final int silver_features_functorSyn__ON__silver_features_FunctorTestNT = silver_features.Init.count_syn__ON__FunctorTestNT++;
public static final int silver_features_functorTestAnnoSum__ON__silver_features_FunctorTestNT = silver_features.Init.count_syn__ON__FunctorTestNT++;
public static final int silver_features_functorSyn__ON__silver_features_FunctorTestNT2 = silver_features.Init.count_syn__ON__FunctorTestNT2++;
public static final int silver_features_functorTestAnnoSum__ON__silver_features_FunctorTestNT2 = silver_features.Init.count_syn__ON__FunctorTestNT2++;
public static final int value__ON__silver_features_generatedTest_Functor_sv_62_49 = silver_features.Init.count_local__ON__silver_features_generatedTest_Functor_sv_62_49++;
public static final int expected__ON__silver_features_generatedTest_Functor_sv_62_49 = silver_features.Init.count_local__ON__silver_features_generatedTest_Functor_sv_62_49++;
public static final int value__ON__silver_features_generatedTest_Functor_sv_64_50 = silver_features.Init.count_local__ON__silver_features_generatedTest_Functor_sv_64_50++;
public static final int expected__ON__silver_features_generatedTest_Functor_sv_64_50 = silver_features.Init.count_local__ON__silver_features_generatedTest_Functor_sv_64_50++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_8_51 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_8_51++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_8_51 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_8_51++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_10_52 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_10_52++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_10_52 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_10_52++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_15_53 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_15_53++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_15_53 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_15_53++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_17_54 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_17_54++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_17_54 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_17_54++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_19_55 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_19_55++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_19_55 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_19_55++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_21_56 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_21_56++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_21_56 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_21_56++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_23_57 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_23_57++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_23_57 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_23_57++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_25_58 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_25_58++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_25_58 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_25_58++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_28_59 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_28_59++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_28_59 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_28_59++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_29_60 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_29_60++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_29_60 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_29_60++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_32_61 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_32_61++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_32_61 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_32_61++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_33_62 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_33_62++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_33_62 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_33_62++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_34_63 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_34_63++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_34_63 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_34_63++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_35_64 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_35_64++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_35_64 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_35_64++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_36_65 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_36_65++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_36_65 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_36_65++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_37_66 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_37_66++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_37_66 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_37_66++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_38_67 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_38_67++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_38_67 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_38_67++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_39_68 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_39_68++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_39_68 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_39_68++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_42_69 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_42_69++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_42_69 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_42_69++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_46_70 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_46_70++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_46_70 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_46_70++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_47_71 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_47_71++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_47_71 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_47_71++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_50_72 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_50_72++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_50_72 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_50_72++;
public static final int value__ON__silver_features_generatedTest_Templating_sv_52_73 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_52_73++;
public static final int expected__ON__silver_features_generatedTest_Templating_sv_52_73 = silver_features.Init.count_local__ON__silver_features_generatedTest_Templating_sv_52_73++;
public static final int value__ON__silver_features_generatedTest_Monad_sv_11_74 = silver_features.Init.count_local__ON__silver_features_generatedTest_Monad_sv_11_74++;
public static final int expected__ON__silver_features_generatedTest_Monad_sv_11_74 = silver_features.Init.count_local__ON__silver_features_generatedTest_Monad_sv_11_74++;
public static final int value__ON__silver_features_generatedTest_Monad_sv_20_75 = silver_features.Init.count_local__ON__silver_features_generatedTest_Monad_sv_20_75++;
public static final int expected__ON__silver_features_generatedTest_Monad_sv_20_75 = silver_features.Init.count_local__ON__silver_features_generatedTest_Monad_sv_20_75++;
public static final int value__ON__silver_features_generatedTest_Monad_sv_31_76 = silver_features.Init.count_local__ON__silver_features_generatedTest_Monad_sv_31_76++;
public static final int expected__ON__silver_features_generatedTest_Monad_sv_31_76 = silver_features.Init.count_local__ON__silver_features_generatedTest_Monad_sv_31_76++;
public static final int value__ON__silver_features_generatedTest_Monad_sv_32_77 = silver_features.Init.count_local__ON__silver_features_generatedTest_Monad_sv_32_77++;
public static final int expected__ON__silver_features_generatedTest_Monad_sv_32_77 = silver_features.Init.count_local__ON__silver_features_generatedTest_Monad_sv_32_77++;
public static final int value__ON__silver_features_generatedTest_Monad_sv_41_78 = silver_features.Init.count_local__ON__silver_features_generatedTest_Monad_sv_41_78++;
public static final int expected__ON__silver_features_generatedTest_Monad_sv_41_78 = silver_features.Init.count_local__ON__silver_features_generatedTest_Monad_sv_41_78++;
public static final int core_fst__ON__silver_features_ConcreteProductions = silver_features.Init.count_syn__ON__ConcreteProductions++;
public static final int silver_features_colSyn__ON__silver_features_ColNT = silver_features.Init.count_syn__ON__ColNT++;
public static final int silver_features_colInh__ON__silver_features_ColNT = silver_features.Init.count_inh__ON__ColNT++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_17_79 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_17_79++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_17_79 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_17_79++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_38_80 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_38_80++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_38_80 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_38_80++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_47_81 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_47_81++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_47_81 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_47_81++;
public static final int lo__ON__silver_features_colProdLeaf = silver_features.Init.count_local__ON__silver_features_colProdLeaf++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_63_82 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_63_82++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_63_82 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_63_82++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_64_83 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_64_83++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_64_83 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_64_83++;
public static final int t__ON__silver_features_testLocalInheritedCollections = silver_features.Init.count_local__ON__silver_features_testLocalInheritedCollections++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_74_84 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_74_84++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_74_84 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_74_84++;
public static final int silver_features_colNotCol__ON__silver_features_ColNT = silver_features.Init.count_syn__ON__ColNT++;
public static final int silver_features_colList__ON__silver_features_ColNT = silver_features.Init.count_syn__ON__ColNT++;
public static final int silver_features_colOr__ON__silver_features_ColNT = silver_features.Init.count_syn__ON__ColNT++;
public static final int silver_features_colAnd__ON__silver_features_ColNT = silver_features.Init.count_syn__ON__ColNT++;
public static final int silver_features_colFun__ON__silver_features_ColNT = silver_features.Init.count_syn__ON__ColNT++;
public static final int silver_features_colProd__ON__silver_features_ColNT = silver_features.Init.count_syn__ON__ColNT++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_133_85 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_133_85++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_133_85 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_133_85++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_134_86 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_134_86++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_134_86 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_134_86++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_135_87 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_135_87++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_135_87 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_135_87++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_136_88 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_136_88++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_136_88 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_136_88++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_137_89 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_137_89++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_137_89 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_137_89++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_138_90 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_138_90++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_138_90 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_138_90++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_155_91 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_155_91++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_155_91 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_155_91++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_156_92 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_156_92++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_156_92 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_156_92++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_157_93 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_157_93++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_157_93 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_157_93++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_158_94 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_158_94++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_158_94 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_158_94++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_159_95 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_159_95++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_159_95 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_159_95++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_176_96 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_176_96++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_176_96 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_176_96++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_177_97 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_177_97++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_177_97 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_177_97++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_178_98 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_178_98++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_178_98 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_178_98++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_179_99 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_179_99++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_179_99 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_179_99++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_180_100 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_180_100++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_180_100 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_180_100++;
public static final int value__ON__silver_features_generatedTest_Collections_sv_181_101 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_181_101++;
public static final int expected__ON__silver_features_generatedTest_Collections_sv_181_101 = silver_features.Init.count_local__ON__silver_features_generatedTest_Collections_sv_181_101++;
public static final int silver_features_aDecVal__ON__silver_features_ADecoratedValue = silver_features.Init.count_syn__ON__ADecoratedValue++;
public static final int value__ON__silver_features_generatedTest_ThunkTransforms_sv_37_102 = silver_features.Init.count_local__ON__silver_features_generatedTest_ThunkTransforms_sv_37_102++;
public static final int expected__ON__silver_features_generatedTest_ThunkTransforms_sv_37_102 = silver_features.Init.count_local__ON__silver_features_generatedTest_ThunkTransforms_sv_37_102++;
public static final int value__ON__silver_features_generatedTest_Scoping_sv_4_103 = silver_features.Init.count_local__ON__silver_features_generatedTest_Scoping_sv_4_103++;
public static final int expected__ON__silver_features_generatedTest_Scoping_sv_4_103 = silver_features.Init.count_local__ON__silver_features_generatedTest_Scoping_sv_4_103++;
public static final int hi1__ON__silver_features_rightCodeScoping = silver_features.Init.count_local__ON__silver_features_rightCodeScoping++;
public static final int hi2__ON__silver_features_rightCodeScoping = silver_features.Init.count_local__ON__silver_features_rightCodeScoping++;
public static final int sc__ON__silver_features_rightCodeNaming = silver_features.Init.count_local__ON__silver_features_rightCodeNaming++;
public static final int tp__ON__silver_features_rightCodeNaming = silver_features.Init.count_local__ON__silver_features_rightCodeNaming++;
public static final int silver_features_defaultsyn__ON__silver_features_DefaultAttrNT = silver_features.Init.count_syn__ON__DefaultAttrNT++;
public static final int silver_features_defaultsyn2__ON__silver_features_DefaultAttrNT = silver_features.Init.count_syn__ON__DefaultAttrNT++;
public static final int value__ON__silver_features_generatedTest_DefaultAttrs_sv_38_104 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_38_104++;
public static final int expected__ON__silver_features_generatedTest_DefaultAttrs_sv_38_104 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_38_104++;
public static final int value__ON__silver_features_generatedTest_DefaultAttrs_sv_39_105 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_39_105++;
public static final int expected__ON__silver_features_generatedTest_DefaultAttrs_sv_39_105 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_39_105++;
public static final int value__ON__silver_features_generatedTest_DefaultAttrs_sv_40_106 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_40_106++;
public static final int expected__ON__silver_features_generatedTest_DefaultAttrs_sv_40_106 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_40_106++;
public static final int value__ON__silver_features_generatedTest_DefaultAttrs_sv_41_107 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_41_107++;
public static final int expected__ON__silver_features_generatedTest_DefaultAttrs_sv_41_107 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_41_107++;
public static final int value__ON__silver_features_generatedTest_DefaultAttrs_sv_42_108 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_42_108++;
public static final int expected__ON__silver_features_generatedTest_DefaultAttrs_sv_42_108 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_42_108++;
public static final int value__ON__silver_features_generatedTest_DefaultAttrs_sv_44_109 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_44_109++;
public static final int expected__ON__silver_features_generatedTest_DefaultAttrs_sv_44_109 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_44_109++;
public static final int value__ON__silver_features_generatedTest_DefaultAttrs_sv_45_110 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_45_110++;
public static final int expected__ON__silver_features_generatedTest_DefaultAttrs_sv_45_110 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_45_110++;
public static final int value__ON__silver_features_generatedTest_DefaultAttrs_sv_46_111 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_46_111++;
public static final int expected__ON__silver_features_generatedTest_DefaultAttrs_sv_46_111 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_46_111++;
public static final int value__ON__silver_features_generatedTest_DefaultAttrs_sv_47_112 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_47_112++;
public static final int expected__ON__silver_features_generatedTest_DefaultAttrs_sv_47_112 = silver_features.Init.count_local__ON__silver_features_generatedTest_DefaultAttrs_sv_47_112++;
public static final int testResults__ON__silver_features_main = silver_features.Init.count_local__ON__silver_features_main++;
public static final int testsToPerform__ON__silver_features_silver_tests = silver_features.Init.count_local__ON__silver_features_silver_tests++;
public static final int value__ON__silver_features_generatedTest_Main_sv_15_113 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_15_113++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_15_113 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_15_113++;
public static final int value__ON__silver_features_generatedTest_Main_sv_16_114 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_16_114++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_16_114 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_16_114++;
public static final int value__ON__silver_features_generatedTest_Main_sv_17_115 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_17_115++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_17_115 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_17_115++;
public static final int value__ON__silver_features_generatedTest_Main_sv_18_116 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_18_116++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_18_116 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_18_116++;
public static final int value__ON__silver_features_generatedTest_Main_sv_21_117 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_21_117++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_21_117 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_21_117++;
public static final int value__ON__silver_features_generatedTest_Main_sv_23_118 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_23_118++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_23_118 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_23_118++;
public static final int value__ON__silver_features_generatedTest_Main_sv_24_119 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_24_119++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_24_119 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_24_119++;
public static final int value__ON__silver_features_generatedTest_Main_sv_26_120 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_26_120++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_26_120 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_26_120++;
public static final int value__ON__silver_features_generatedTest_Main_sv_27_121 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_27_121++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_27_121 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_27_121++;
public static final int value__ON__silver_features_generatedTest_Main_sv_28_122 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_28_122++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_28_122 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_28_122++;
public static final int value__ON__silver_features_generatedTest_Main_sv_31_123 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_31_123++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_31_123 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_31_123++;
public static final int value__ON__silver_features_generatedTest_Main_sv_32_124 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_32_124++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_32_124 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_32_124++;
public static final int value__ON__silver_features_generatedTest_Main_sv_33_125 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_33_125++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_33_125 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_33_125++;
public static final int value__ON__silver_features_generatedTest_Main_sv_37_126 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_37_126++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_37_126 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_37_126++;
public static final int value__ON__silver_features_generatedTest_Main_sv_38_127 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_38_127++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_38_127 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_38_127++;
public static final int value__ON__silver_features_generatedTest_Main_sv_41_128 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_41_128++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_41_128 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_41_128++;
public static final int value__ON__silver_features_generatedTest_Main_sv_42_129 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_42_129++;
public static final int expected__ON__silver_features_generatedTest_Main_sv_42_129 = silver_features.Init.count_local__ON__silver_features_generatedTest_Main_sv_42_129++;
	public static final common.Thunk<Object> ca = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> cb = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> cc = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> cd = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> ce = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> cf = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> cg = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> ch = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> ci = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> cj = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> ck = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> cl = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
	public static final common.Thunk<Object> sections = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NSection)new silver_features.Psection()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NSection)new silver_features.Psection()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };
	public static final common.Thunk<Object> onePartFun = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return silver_features.PtwoArgFunction.factory.invokePartial(new int[]{0}, new Object[]{(new common.StringCatter("s"))}); } };
	public static final common.Thunk<Object> invoker = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return silver_features.PtwoArgFunction.factory.invokePartial(new int[]{1}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)core.PgenInt.invoke()); } }}); } };
	public static final common.Thunk<Object> fmeOne = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return silver_features.Pfmadd.factory.invokePartial(new int[]{0}, new Object[]{Integer.valueOf((int)2)}); } };
	public static final common.Thunk<Object> fmeTwo = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return silver_features.Pfmadd.factory.invokePartial(new int[]{1}, new Object[]{Integer.valueOf((int)3)}); } };
	public static final common.Thunk<Object> astr1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("hi")); } };
	public static final common.Thunk<Object> astr2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("yo")); } };
	public static final common.Thunk<Object> astr4 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver_features.Init.astr2.eval()); } };
	public static final common.Thunk<Object> anum1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)2); } };
	public static final common.Thunk<Object> astr3 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)silver_features.Init.anum1.eval()))); } };
	public static final common.Thunk<Object> stfcTestString = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("hello")); } };
	public static final common.Thunk<Object> stfcTestChars = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)104), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)101), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)108), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)108), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)111), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> aft1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Perror.invoke((new common.StringCatter("")))); } };
	public static final common.Thunk<Object> autoValue = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NAutoCopyTestNT)new silver_features.PconsACTNT(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NAutoCopyTestNT)new silver_features.PconsACTNT2(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NAutoCopyTestNT2)new silver_features.PnilACTNT2()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NAutoCopyTestNT)new silver_features.PnilACTNT()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NAutoCopyTestNT)new silver_features.PconsACTNT2(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NAutoCopyTestNT2)new silver_features.PnilACTNT2()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NAutoCopyTestNT)new silver_features.PnilACTNT()); } })); } })); } };
	public static final common.Thunk<Object> globstring1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("Hi")); } };
	public static final common.Thunk<Object> globstring2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)2), Integer.valueOf((int)4), (new common.StringCatter("Heyooo")))); } };
	public static final common.Thunk<Object> globint2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf((int)11) + ((Integer)silver_features.global_sub.Init.globalint1.eval())); } };
	public static final common.Thunk<Object> globalbool1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return false; } };
	public static final common.Thunk<Object> unT = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NTglob)new silver_features.PTfoo()); } };
	public static final common.Thunk<Object> deT = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NTglob)silver_features.Init.unT.eval()).decorate(context, (common.Lazy[])null); } };
	public static final common.Thunk<Object> letTest = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_977_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)5); } };
return ((Integer)(__SV_LOCAL_977_x.eval())); } }).eval()); } };
	public static final common.Thunk<Object> aList = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver_features.PintTestProd.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)3), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> addThree = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.NodeFactory<Integer>)silver_features.PmakeInc.invoke(Integer.valueOf((int)3))); } };
	public static final common.Thunk<Object> sumTwo = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.NodeFactory<Integer>() {
  public final Integer invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1019_x = args[0];
final Object __SV_LAMBDA_PARAM_1020_y = args[1];

    return Integer.valueOf(((Integer)common.Util.demand(__SV_LAMBDA_PARAM_1019_x)) + ((Integer)common.Util.demand(__SV_LAMBDA_PARAM_1020_y)));
  }
}); } };
	public static final common.Thunk<Object> curriedSum = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.NodeFactory<common.NodeFactory<Integer>>() {
  public final common.NodeFactory<Integer> invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1025_x = args[0];

    return (new common.NodeFactory<Integer>() {
  public final Integer invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1026_y = args[0];

    return Integer.valueOf(((Integer)common.Util.demand(__SV_LAMBDA_PARAM_1025_x)) + ((Integer)common.Util.demand(__SV_LAMBDA_PARAM_1026_y)));
  }
});
  }
}); } };
	public static final common.Thunk<Object> differentTypes = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.NodeFactory<common.NodeFactory<common.StringCatter>>() {
  public final common.NodeFactory<common.StringCatter> invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1031_x = args[0];

    return (new common.NodeFactory<common.StringCatter>() {
  public final common.StringCatter invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1032_x = args[0];

    return ((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_1032_x));
  }
});
  }
}); } };
	public static final common.Thunk<Object> param = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)4); } };
	public static final common.Thunk<Object> fn = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    
    return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)4), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }));
  }
}); } };
	public static final common.Thunk<Object> tl = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver_features.TTerm((new common.StringCatter("hi")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver_features.TTerm((new common.StringCatter("hello")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };
	public static final common.Thunk<Object> error23423 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver_features.TTerm)core.Phead.invoke(silver_features.Init.tl)).lexeme); } };
	public static final common.Thunk<Object> error23424 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver_features.TTerm)core.Phead.invoke(silver_features.Init.tl)).location); } };
	public static final common.Thunk<Object> functorValue = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PconsFTNT(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PconsFTNT2(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT2)new silver_features.PnilFTNT2((new common.StringCatter("a")), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PnilFTNT(Integer.valueOf((int)1), Integer.valueOf((int)2))); } }, Integer.valueOf((int)3))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PconsFTNT2(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT2)new silver_features.PnilFTNT2((new common.StringCatter("b")), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PnilFTNT(Integer.valueOf((int)2), Integer.valueOf((int)5))); } }, Integer.valueOf((int)6))); } }, Integer.valueOf((int)7))); } };
	public static final common.Thunk<Object> functorValueRes = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PconsFTNT(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PconsFTNT2(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT2)new silver_features.PnilFTNT2((new common.StringCatter("a")), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PnilFTNT(Integer.valueOf((int)10), Integer.valueOf((int)123))); } }, Integer.valueOf((int)3))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PconsFTNT2(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT2)new silver_features.PnilFTNT2((new common.StringCatter("b")), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PnilFTNT(Integer.valueOf((int)10), Integer.valueOf((int)123))); } }, Integer.valueOf((int)6))); } }, Integer.valueOf((int)7))); } };
	public static final common.Thunk<Object> aStrValue = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("\n\t$")); } };
	public static final common.Thunk<Object> monadRes1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)core.monad.PbindMaybe.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(Integer.valueOf((int)1))); } }, (new common.NodeFactory<core.NMaybe>() {
  public final core.NMaybe invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1254_a = args[0];

    return ((((Integer)common.Util.demand(__SV_LAMBDA_PARAM_1254_a)) > Integer.valueOf((int)0)) ? ((core.NMaybe)core.monad.PreturnMaybe.invoke(Integer.valueOf((int)2))) : ((core.NMaybe)new core.Pnothing()));
  }
}))); } };
	public static final common.Thunk<Object> monadRes2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.monad.PbindList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)3), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }, (new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1272_a = args[0];

    return ((common.ConsCell)core.monad.PbindList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)4), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)5), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)6), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }, (new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1278_b = args[0];

    return ((common.ConsCell)core.monad.PreturnList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)common.Util.demand(__SV_LAMBDA_PARAM_1272_a)) * ((Integer)common.Util.demand(__SV_LAMBDA_PARAM_1278_b))); } }));
  }
})));
  }
}))); } };
	public static final common.Thunk<Object> monadRes3 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NState)new core.monad.PbindState(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NState)new core.monad.PgetState()); } }, (new common.NodeFactory<core.monad.NState>() {
  public final core.monad.NState invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1300_a = args[0];

    return ((core.monad.NState)new core.monad.PbindState(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NState)new core.monad.PsetState(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)common.Util.demand(__SV_LAMBDA_PARAM_1300_a)) + Integer.valueOf((int)1)); } })); } }, (new common.NodeFactory<core.monad.NState>() {
  public final core.monad.NState invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1305__ = args[0];

    return ((core.monad.NState)new core.monad.PreturnState(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)common.Util.demand(__SV_LAMBDA_PARAM_1300_a)) * Integer.valueOf((int)2)); } }));
  }
})));
  }
}))); } };
	public static final common.Thunk<Object> res3 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)core.monad.PrunState.invoke(silver_features.Init.monadRes3, Integer.valueOf((int)2))); } };
	public static final common.Thunk<Object> monadRes4 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PwriteFileM((new common.StringCatter("test_out.txt")), (new common.StringCatter("Hello")))); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1325__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PbindIO(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.monad.NIOMonad)new core.monad.PappendFileM((new common.StringCatter("test_out.txt")), (new common.StringCatter(", World!")))); } }, (new common.NodeFactory<core.monad.NIOMonad>() {
  public final core.monad.NIOMonad invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1327__ = args[0];

    return ((core.monad.NIOMonad)new core.monad.PreadFileM((new common.StringCatter("test_out.txt"))));
  }
})));
  }
}))); } };
}
