package stdlib.treemap;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		stdlib.Init.initAllStatics();
		silver.util.treemap.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		stdlib.treemap.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		stdlib.Init.init();
		silver.util.treemap.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		stdlib.treemap.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		stdlib.Init.postInit();
		silver.util.treemap.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		stdlib.treemap.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_34_201.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_35_202.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_36_203.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_37_204.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_39_205.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_40_206.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_41_207.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_42_208.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_43_209.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_44_210.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_45_211.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_46_212.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_47_213.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_49_214.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_50_215.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_51_216.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_52_217.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_53_218.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_54_219.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_55_220.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_65_221.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_66_222.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_69_223.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_70_224.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_73_225.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_74_226.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_81_227.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_82_228.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_83_229.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_84_230.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_85_231.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_86_232.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_87_233.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_88_234.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_89_235.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_90_236.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_91_237.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_92_238.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_93_239.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_98_240.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_99_241.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_100_242.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_101_243.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_103_244.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_104_245.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_105_246.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_106_247.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_108_248.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TreeMap_sv_150_249.class);
	}

	private static void setupInheritedAttributes(){
		stdlib.treemap.PgeneratedTest_TreeMap_sv_34_201.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_34_201] = "stdlib:treemap:generatedTest_TreeMap_sv_34_201:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_34_201.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_34_201] = "stdlib:treemap:generatedTest_TreeMap_sv_34_201:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_35_202.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202] = "stdlib:treemap:generatedTest_TreeMap_sv_35_202:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_35_202.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202] = "stdlib:treemap:generatedTest_TreeMap_sv_35_202:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_36_203.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_36_203] = "stdlib:treemap:generatedTest_TreeMap_sv_36_203:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_36_203.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_36_203] = "stdlib:treemap:generatedTest_TreeMap_sv_36_203:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_37_204.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_37_204] = "stdlib:treemap:generatedTest_TreeMap_sv_37_204:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_37_204.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_37_204] = "stdlib:treemap:generatedTest_TreeMap_sv_37_204:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_39_205.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_39_205] = "stdlib:treemap:generatedTest_TreeMap_sv_39_205:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_39_205.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_39_205] = "stdlib:treemap:generatedTest_TreeMap_sv_39_205:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_40_206.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_40_206] = "stdlib:treemap:generatedTest_TreeMap_sv_40_206:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_40_206.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_40_206] = "stdlib:treemap:generatedTest_TreeMap_sv_40_206:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_41_207.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_41_207] = "stdlib:treemap:generatedTest_TreeMap_sv_41_207:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_41_207.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_41_207] = "stdlib:treemap:generatedTest_TreeMap_sv_41_207:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_42_208.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_42_208] = "stdlib:treemap:generatedTest_TreeMap_sv_42_208:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_42_208.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_42_208] = "stdlib:treemap:generatedTest_TreeMap_sv_42_208:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_43_209.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_43_209] = "stdlib:treemap:generatedTest_TreeMap_sv_43_209:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_43_209.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_43_209] = "stdlib:treemap:generatedTest_TreeMap_sv_43_209:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_44_210.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_44_210] = "stdlib:treemap:generatedTest_TreeMap_sv_44_210:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_44_210.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_44_210] = "stdlib:treemap:generatedTest_TreeMap_sv_44_210:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_45_211.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_45_211] = "stdlib:treemap:generatedTest_TreeMap_sv_45_211:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_45_211.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_45_211] = "stdlib:treemap:generatedTest_TreeMap_sv_45_211:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_46_212.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_46_212] = "stdlib:treemap:generatedTest_TreeMap_sv_46_212:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_46_212.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_46_212] = "stdlib:treemap:generatedTest_TreeMap_sv_46_212:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_47_213.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_47_213] = "stdlib:treemap:generatedTest_TreeMap_sv_47_213:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_47_213.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_47_213] = "stdlib:treemap:generatedTest_TreeMap_sv_47_213:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_49_214.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_49_214] = "stdlib:treemap:generatedTest_TreeMap_sv_49_214:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_49_214.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_49_214] = "stdlib:treemap:generatedTest_TreeMap_sv_49_214:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_50_215.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_50_215] = "stdlib:treemap:generatedTest_TreeMap_sv_50_215:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_50_215.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_50_215] = "stdlib:treemap:generatedTest_TreeMap_sv_50_215:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_51_216.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_51_216] = "stdlib:treemap:generatedTest_TreeMap_sv_51_216:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_51_216.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_51_216] = "stdlib:treemap:generatedTest_TreeMap_sv_51_216:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_52_217.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_52_217] = "stdlib:treemap:generatedTest_TreeMap_sv_52_217:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_52_217.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_52_217] = "stdlib:treemap:generatedTest_TreeMap_sv_52_217:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_53_218.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_53_218] = "stdlib:treemap:generatedTest_TreeMap_sv_53_218:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_53_218.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_53_218] = "stdlib:treemap:generatedTest_TreeMap_sv_53_218:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_54_219.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_54_219] = "stdlib:treemap:generatedTest_TreeMap_sv_54_219:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_54_219.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_54_219] = "stdlib:treemap:generatedTest_TreeMap_sv_54_219:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_55_220.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_55_220] = "stdlib:treemap:generatedTest_TreeMap_sv_55_220:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_55_220.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_55_220] = "stdlib:treemap:generatedTest_TreeMap_sv_55_220:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_65_221.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_65_221] = "stdlib:treemap:generatedTest_TreeMap_sv_65_221:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_65_221.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_65_221] = "stdlib:treemap:generatedTest_TreeMap_sv_65_221:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_66_222.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_66_222] = "stdlib:treemap:generatedTest_TreeMap_sv_66_222:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_66_222.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_66_222] = "stdlib:treemap:generatedTest_TreeMap_sv_66_222:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_69_223.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_69_223] = "stdlib:treemap:generatedTest_TreeMap_sv_69_223:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_69_223.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_69_223] = "stdlib:treemap:generatedTest_TreeMap_sv_69_223:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_70_224.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_70_224] = "stdlib:treemap:generatedTest_TreeMap_sv_70_224:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_70_224.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_70_224] = "stdlib:treemap:generatedTest_TreeMap_sv_70_224:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_73_225.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_73_225] = "stdlib:treemap:generatedTest_TreeMap_sv_73_225:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_73_225.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_73_225] = "stdlib:treemap:generatedTest_TreeMap_sv_73_225:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_74_226.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_74_226] = "stdlib:treemap:generatedTest_TreeMap_sv_74_226:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_74_226.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_74_226] = "stdlib:treemap:generatedTest_TreeMap_sv_74_226:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_81_227.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_81_227] = "stdlib:treemap:generatedTest_TreeMap_sv_81_227:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_81_227.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_81_227] = "stdlib:treemap:generatedTest_TreeMap_sv_81_227:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_82_228.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_82_228] = "stdlib:treemap:generatedTest_TreeMap_sv_82_228:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_82_228.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_82_228] = "stdlib:treemap:generatedTest_TreeMap_sv_82_228:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_83_229.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_83_229] = "stdlib:treemap:generatedTest_TreeMap_sv_83_229:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_83_229.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_83_229] = "stdlib:treemap:generatedTest_TreeMap_sv_83_229:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_84_230.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_84_230] = "stdlib:treemap:generatedTest_TreeMap_sv_84_230:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_84_230.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_84_230] = "stdlib:treemap:generatedTest_TreeMap_sv_84_230:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_85_231.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_85_231] = "stdlib:treemap:generatedTest_TreeMap_sv_85_231:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_85_231.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_85_231] = "stdlib:treemap:generatedTest_TreeMap_sv_85_231:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_86_232.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_86_232] = "stdlib:treemap:generatedTest_TreeMap_sv_86_232:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_86_232.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_86_232] = "stdlib:treemap:generatedTest_TreeMap_sv_86_232:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_87_233.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_87_233] = "stdlib:treemap:generatedTest_TreeMap_sv_87_233:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_87_233.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_87_233] = "stdlib:treemap:generatedTest_TreeMap_sv_87_233:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_88_234.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_88_234] = "stdlib:treemap:generatedTest_TreeMap_sv_88_234:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_88_234.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_88_234] = "stdlib:treemap:generatedTest_TreeMap_sv_88_234:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_89_235.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_89_235] = "stdlib:treemap:generatedTest_TreeMap_sv_89_235:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_89_235.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_89_235] = "stdlib:treemap:generatedTest_TreeMap_sv_89_235:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_90_236.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_90_236] = "stdlib:treemap:generatedTest_TreeMap_sv_90_236:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_90_236.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_90_236] = "stdlib:treemap:generatedTest_TreeMap_sv_90_236:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_91_237.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_91_237] = "stdlib:treemap:generatedTest_TreeMap_sv_91_237:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_91_237.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_91_237] = "stdlib:treemap:generatedTest_TreeMap_sv_91_237:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_92_238.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_92_238] = "stdlib:treemap:generatedTest_TreeMap_sv_92_238:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_92_238.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_92_238] = "stdlib:treemap:generatedTest_TreeMap_sv_92_238:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_93_239.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_93_239] = "stdlib:treemap:generatedTest_TreeMap_sv_93_239:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_93_239.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_93_239] = "stdlib:treemap:generatedTest_TreeMap_sv_93_239:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_98_240.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_98_240] = "stdlib:treemap:generatedTest_TreeMap_sv_98_240:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_98_240.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_98_240] = "stdlib:treemap:generatedTest_TreeMap_sv_98_240:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_99_241.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_99_241] = "stdlib:treemap:generatedTest_TreeMap_sv_99_241:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_99_241.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_99_241] = "stdlib:treemap:generatedTest_TreeMap_sv_99_241:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_100_242.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_100_242] = "stdlib:treemap:generatedTest_TreeMap_sv_100_242:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_100_242.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_100_242] = "stdlib:treemap:generatedTest_TreeMap_sv_100_242:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_101_243.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_101_243] = "stdlib:treemap:generatedTest_TreeMap_sv_101_243:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_101_243.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_101_243] = "stdlib:treemap:generatedTest_TreeMap_sv_101_243:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_103_244.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_103_244] = "stdlib:treemap:generatedTest_TreeMap_sv_103_244:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_103_244.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_103_244] = "stdlib:treemap:generatedTest_TreeMap_sv_103_244:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_104_245.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_104_245] = "stdlib:treemap:generatedTest_TreeMap_sv_104_245:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_104_245.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_104_245] = "stdlib:treemap:generatedTest_TreeMap_sv_104_245:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_105_246.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_105_246] = "stdlib:treemap:generatedTest_TreeMap_sv_105_246:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_105_246.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_105_246] = "stdlib:treemap:generatedTest_TreeMap_sv_105_246:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_106_247.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_106_247] = "stdlib:treemap:generatedTest_TreeMap_sv_106_247:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_106_247.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_106_247] = "stdlib:treemap:generatedTest_TreeMap_sv_106_247:local:expected";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_108_248.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_108_248] = "stdlib:treemap:generatedTest_TreeMap_sv_108_248:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_108_248.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_108_248] = "stdlib:treemap:generatedTest_TreeMap_sv_108_248:local:expected";
		silver.util.treemap.NTreeMap.occurs_syn[stdlib.treemap.Init.stdlib_treemap_debugIdentity__ON__silver_util_treemap_TreeMap] = "stdlib:treemap:debugIdentity";
		silver.util.treemap.NTreeMap.occurs_syn[stdlib.treemap.Init.stdlib_treemap_debugDot__ON__silver_util_treemap_TreeMap] = "stdlib:treemap:debugDot";
		silver.util.treemap.NTreeMap.occurs_syn[stdlib.treemap.Init.stdlib_treemap_debugHeight__ON__silver_util_treemap_TreeMap] = "stdlib:treemap:debugHeight";
		silver.util.treemap.NTreeMap.occurs_syn[stdlib.treemap.Init.stdlib_treemap_blackHeight__ON__silver_util_treemap_TreeMap] = "stdlib:treemap:blackHeight";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_150_249.occurs_local[stdlib.treemap.Init.value__ON__stdlib_treemap_generatedTest_TreeMap_sv_150_249] = "stdlib:treemap:generatedTest_TreeMap_sv_150_249:local:value";
		stdlib.treemap.PgeneratedTest_TreeMap_sv_150_249.occurs_local[stdlib.treemap.Init.expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_150_249] = "stdlib:treemap:generatedTest_TreeMap_sv_150_249:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		stdlib.treemap.PgeneratedTest_TreeMap_sv_34_201.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_34_201(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_34_201()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_35_202.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_35_202(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_35_202()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_36_203.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_36_203(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_36_203()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_37_204.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_37_204(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_37_204()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_39_205.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_39_205(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_39_205()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_40_206.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_40_206(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_40_206()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_41_207.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_41_207(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_41_207()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_42_208.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_42_208(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_42_208()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_43_209.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_43_209(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_43_209()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_44_210.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_44_210(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_44_210()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_45_211.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_45_211(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_45_211()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_46_212.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_46_212(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_46_212()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_47_213.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_47_213(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_47_213()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_49_214.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_49_214(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_49_214()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_50_215.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_50_215(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_50_215()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_51_216.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_51_216(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_51_216()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_52_217.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_52_217(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_52_217()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_53_218.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_53_218(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_53_218()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_54_219.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_54_219(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_54_219()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_55_220.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_55_220(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_55_220()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION genTree TreeMap<String Integer> ::= i::Integer t::TreeMap<String Integer> 
		stdlib.treemap.PgeneratedTest_TreeMap_sv_65_221.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_65_221(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_65_221()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_66_222.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_66_222(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_66_222()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_69_223.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_69_223(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_69_223()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_70_224.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_70_224(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_70_224()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_73_225.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_73_225(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_73_225()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_74_226.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_74_226(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_74_226()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_81_227.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_81_227(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_81_227()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_82_228.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_82_228(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_82_228()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_83_229.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_83_229(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_83_229()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_84_230.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_84_230(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_84_230()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_85_231.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_85_231(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_85_231()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_86_232.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_86_232(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_86_232()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_87_233.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_87_233(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_87_233()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_88_234.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_88_234(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_88_234()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_89_235.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_89_235(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_89_235()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_90_236.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_90_236(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_90_236()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_91_237.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_91_237(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_91_237()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_92_238.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_92_238(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_92_238()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_93_239.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_93_239(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_93_239()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_98_240.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_98_240(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_98_240()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_99_241.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_99_241(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_99_241()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_100_242.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_100_242(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_100_242()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_101_243.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_101_243(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_101_243()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_103_244.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_103_244(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_103_244()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_104_245.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_104_245(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_104_245()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_105_246.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_105_246(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_105_246()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_106_247.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_106_247(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_106_247()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.treemap.PgeneratedTest_TreeMap_sv_108_248.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_108_248(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_108_248()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION escape String ::= s::String 
		//ASPECT PRODUCTION leaf top ::= CMP::(Integer ::= a a) 
		// top.debugIdentity = "leaf" ++ toString(genInt(,))
		silver.util.treemap.Pleaf.synthesizedAttributes[stdlib.treemap.Init.stdlib_treemap_debugIdentity__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("leaf")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke())))); } };
		// top.debugDot = top.debugIdentity ++ "[color=black, style=filled];\n"
		silver.util.treemap.Pleaf.synthesizedAttributes[stdlib.treemap.Init.stdlib_treemap_debugDot__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.synthesized(stdlib.treemap.Init.stdlib_treemap_debugIdentity__ON__silver_util_treemap_TreeMap)), (common.StringCatter)(new common.StringCatter("[color=black, style=filled];\n"))); } };
		// top.debugHeight = 0
		silver.util.treemap.Pleaf.synthesizedAttributes[stdlib.treemap.Init.stdlib_treemap_debugHeight__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// top.blackHeight = 0
		silver.util.treemap.Pleaf.synthesizedAttributes[stdlib.treemap.Init.stdlib_treemap_blackHeight__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		//ASPECT PRODUCTION node top ::= black::Boolean lefttree::TreeMap<a b> righttree::TreeMap<a b> label::a values::[b] CMP::(Integer ::= a a) 
		// top.debugIdentity = "node" ++ toString(genInt(,))
		silver.util.treemap.Pnode.synthesizedAttributes[stdlib.treemap.Init.stdlib_treemap_debugIdentity__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("node")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke())))); } };
		// top.debugDot = top.debugIdentity ++ "[color=" ++ (if black then "black" else "red") ++ ", label=\"" ++ escape(hackUnparse(label,) ++ " -> " ++ hackUnparse(values,),) ++ "\"];\n" ++ top.debugIdentity ++ " -> " ++ lefttree.debugIdentity ++ " [label=\"l\"];\n" ++ top.debugIdentity ++ " -> " ++ righttree.debugIdentity ++ " [label=\"r\"];\n" ++ lefttree.debugDot ++ righttree.debugDot
		silver.util.treemap.Pnode.synthesizedAttributes[stdlib.treemap.Init.stdlib_treemap_debugDot__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.synthesized(stdlib.treemap.Init.stdlib_treemap_debugIdentity__ON__silver_util_treemap_TreeMap)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("[color=")), (common.StringCatter)new common.StringCatter((common.StringCatter)(((Boolean)context.childAsIs(silver.util.treemap.Pnode.i_black)) ? (new common.StringCatter("black")) : (new common.StringCatter("red"))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", label=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)stdlib.treemap.Pescape.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)core.PhackUnparse.invoke(context.childAsIsLazy(silver.util.treemap.Pnode.i_label))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" -> ")), (common.StringCatter)((common.StringCatter)core.PhackUnparse.invoke(context.childAsIsLazy(silver.util.treemap.Pnode.i_values))))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\"];\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.synthesized(stdlib.treemap.Init.stdlib_treemap_debugIdentity__ON__silver_util_treemap_TreeMap)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" -> ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.util.treemap.Pnode.i_lefttree).synthesized(stdlib.treemap.Init.stdlib_treemap_debugIdentity__ON__silver_util_treemap_TreeMap)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" [label=\"l\"];\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.synthesized(stdlib.treemap.Init.stdlib_treemap_debugIdentity__ON__silver_util_treemap_TreeMap)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" -> ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.util.treemap.Pnode.i_righttree).synthesized(stdlib.treemap.Init.stdlib_treemap_debugIdentity__ON__silver_util_treemap_TreeMap)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" [label=\"r\"];\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.util.treemap.Pnode.i_lefttree).synthesized(stdlib.treemap.Init.stdlib_treemap_debugDot__ON__silver_util_treemap_TreeMap)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.util.treemap.Pnode.i_righttree).synthesized(stdlib.treemap.Init.stdlib_treemap_debugDot__ON__silver_util_treemap_TreeMap))))))))))))))))); } };
		// top.debugHeight = if lefttree.debugHeight > righttree.debugHeight then lefttree.debugHeight + 1 else righttree.debugHeight + 1
		silver.util.treemap.Pnode.synthesizedAttributes[stdlib.treemap.Init.stdlib_treemap_debugHeight__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)context.childDecorated(silver.util.treemap.Pnode.i_lefttree).synthesized(stdlib.treemap.Init.stdlib_treemap_debugHeight__ON__silver_util_treemap_TreeMap)) > ((Integer)context.childDecorated(silver.util.treemap.Pnode.i_righttree).synthesized(stdlib.treemap.Init.stdlib_treemap_debugHeight__ON__silver_util_treemap_TreeMap))) ? Integer.valueOf(((Integer)context.childDecorated(silver.util.treemap.Pnode.i_lefttree).synthesized(stdlib.treemap.Init.stdlib_treemap_debugHeight__ON__silver_util_treemap_TreeMap)) + Integer.valueOf((int)1)) : Integer.valueOf(((Integer)context.childDecorated(silver.util.treemap.Pnode.i_righttree).synthesized(stdlib.treemap.Init.stdlib_treemap_debugHeight__ON__silver_util_treemap_TreeMap)) + Integer.valueOf((int)1))); } };
		// top.blackHeight = if lefttree.blackHeight != righttree.blackHeight then -9999999 else righttree.blackHeight + if black then 1 else 0
		silver.util.treemap.Pnode.synthesizedAttributes[stdlib.treemap.Init.stdlib_treemap_blackHeight__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (!((Integer)context.childDecorated(silver.util.treemap.Pnode.i_lefttree).synthesized(stdlib.treemap.Init.stdlib_treemap_blackHeight__ON__silver_util_treemap_TreeMap)).equals(((Integer)context.childDecorated(silver.util.treemap.Pnode.i_righttree).synthesized(stdlib.treemap.Init.stdlib_treemap_blackHeight__ON__silver_util_treemap_TreeMap))) ? Integer.valueOf((int)-9999999) : Integer.valueOf(((Integer)context.childDecorated(silver.util.treemap.Pnode.i_righttree).synthesized(stdlib.treemap.Init.stdlib_treemap_blackHeight__ON__silver_util_treemap_TreeMap)) + (((Boolean)context.childAsIs(silver.util.treemap.Pnode.i_black)) ? Integer.valueOf((int)1) : Integer.valueOf((int)0)))); } };
		stdlib.treemap.PgeneratedTest_TreeMap_sv_150_249.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TreeMap_sv_150_249(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.treemap.PgeneratedTest_TreeMap_sv_150_249()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_34_201 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_36_203 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_37_204 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_39_205 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_40_206 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_41_207 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_42_208 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_43_209 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_44_210 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_45_211 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_46_212 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_47_213 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_49_214 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_50_215 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_51_216 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_52_217 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_53_218 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_54_219 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_55_220 = 0;
	public static int count_local__ON__stdlib_treemap_genTree = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_65_221 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_66_222 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_69_223 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_70_224 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_73_225 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_74_226 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_81_227 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_82_228 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_83_229 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_84_230 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_85_231 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_86_232 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_87_233 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_88_234 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_89_235 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_90_236 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_91_237 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_92_238 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_93_239 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_98_240 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_99_241 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_100_242 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_101_243 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_103_244 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_104_245 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_105_246 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_106_247 = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_108_248 = 0;
	public static int count_local__ON__stdlib_treemap_escape = 0;
	public static int count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_150_249 = 0;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_34_201 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_34_201++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_34_201 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_34_201++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_35_202++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_36_203 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_36_203++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_36_203 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_36_203++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_37_204 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_37_204++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_37_204 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_37_204++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_39_205 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_39_205++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_39_205 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_39_205++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_40_206 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_40_206++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_40_206 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_40_206++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_41_207 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_41_207++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_41_207 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_41_207++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_42_208 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_42_208++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_42_208 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_42_208++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_43_209 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_43_209++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_43_209 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_43_209++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_44_210 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_44_210++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_44_210 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_44_210++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_45_211 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_45_211++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_45_211 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_45_211++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_46_212 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_46_212++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_46_212 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_46_212++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_47_213 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_47_213++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_47_213 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_47_213++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_49_214 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_49_214++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_49_214 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_49_214++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_50_215 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_50_215++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_50_215 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_50_215++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_51_216 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_51_216++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_51_216 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_51_216++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_52_217 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_52_217++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_52_217 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_52_217++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_53_218 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_53_218++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_53_218 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_53_218++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_54_219 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_54_219++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_54_219 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_54_219++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_55_220 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_55_220++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_55_220 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_55_220++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_65_221 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_65_221++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_65_221 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_65_221++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_66_222 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_66_222++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_66_222 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_66_222++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_69_223 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_69_223++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_69_223 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_69_223++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_70_224 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_70_224++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_70_224 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_70_224++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_73_225 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_73_225++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_73_225 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_73_225++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_74_226 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_74_226++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_74_226 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_74_226++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_81_227 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_81_227++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_81_227 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_81_227++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_82_228 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_82_228++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_82_228 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_82_228++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_83_229 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_83_229++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_83_229 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_83_229++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_84_230 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_84_230++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_84_230 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_84_230++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_85_231 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_85_231++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_85_231 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_85_231++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_86_232 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_86_232++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_86_232 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_86_232++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_87_233 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_87_233++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_87_233 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_87_233++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_88_234 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_88_234++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_88_234 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_88_234++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_89_235 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_89_235++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_89_235 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_89_235++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_90_236 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_90_236++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_90_236 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_90_236++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_91_237 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_91_237++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_91_237 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_91_237++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_92_238 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_92_238++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_92_238 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_92_238++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_93_239 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_93_239++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_93_239 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_93_239++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_98_240 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_98_240++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_98_240 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_98_240++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_99_241 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_99_241++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_99_241 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_99_241++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_100_242 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_100_242++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_100_242 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_100_242++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_101_243 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_101_243++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_101_243 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_101_243++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_103_244 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_103_244++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_103_244 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_103_244++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_104_245 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_104_245++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_104_245 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_104_245++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_105_246 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_105_246++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_105_246 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_105_246++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_106_247 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_106_247++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_106_247 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_106_247++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_108_248 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_108_248++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_108_248 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_108_248++;
public static final int stdlib_treemap_debugIdentity__ON__silver_util_treemap_TreeMap = silver.util.treemap.Init.count_syn__ON__TreeMap++;
public static final int stdlib_treemap_debugDot__ON__silver_util_treemap_TreeMap = silver.util.treemap.Init.count_syn__ON__TreeMap++;
public static final int stdlib_treemap_debugHeight__ON__silver_util_treemap_TreeMap = silver.util.treemap.Init.count_syn__ON__TreeMap++;
public static final int stdlib_treemap_blackHeight__ON__silver_util_treemap_TreeMap = silver.util.treemap.Init.count_syn__ON__TreeMap++;
public static final int value__ON__stdlib_treemap_generatedTest_TreeMap_sv_150_249 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_150_249++;
public static final int expected__ON__stdlib_treemap_generatedTest_TreeMap_sv_150_249 = stdlib.treemap.Init.count_local__ON__stdlib_treemap_generatedTest_TreeMap_sv_150_249++;
	public static final common.Thunk<Object> e = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeNew.invoke(core.PcompareString.factory)); } };
	public static final common.Thunk<Object> t1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("1")), Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("2")), Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("3")), Integer.valueOf((int)3), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("4")), Integer.valueOf((int)4), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("5")), Integer.valueOf((int)5), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("6")), Integer.valueOf((int)6), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("1")), Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("2")), Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("0")), Integer.valueOf((int)0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("7")), Integer.valueOf((int)7), stdlib.treemap.Init.e)); } })); } })); } })); } })); } })); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> t2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("g")), Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("f")), Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("d")), Integer.valueOf((int)3), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("s")), Integer.valueOf((int)4), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("a")), Integer.valueOf((int)5), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("p")), Integer.valueOf((int)6), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("q")), Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("h")), Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("i")), Integer.valueOf((int)0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke((new common.StringCatter("q")), Integer.valueOf((int)7), stdlib.treemap.Init.t1)); } })); } })); } })); } })); } })); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> t3 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)stdlib.treemap.PgenTree.invoke(Integer.valueOf((int)50), stdlib.treemap.Init.e)); } };
	public static final common.Thunk<Object> t4 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)stdlib.treemap.PgenTree.invoke(Integer.valueOf((int)500), stdlib.treemap.Init.e)); } };
	public static final common.Thunk<Object> t5 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)stdlib.treemap.PgenTree.invoke(Integer.valueOf((int)5000), stdlib.treemap.Init.e)); } };
	public static final common.Thunk<Object> l1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("hi")), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("hello")), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("hola")), Integer.valueOf((int)6))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("quepasa")), Integer.valueOf((int)11))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> t6 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeConvert.invoke(stdlib.treemap.Init.l1, stdlib.treemap.Init.t2)); } };
	public static final common.Thunk<Object> l2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.treemap.PtreeDeconvert.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeConvert.invoke(stdlib.treemap.Init.l1, stdlib.treemap.Init.e)); } })); } };
}
