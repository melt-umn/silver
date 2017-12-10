package stdlib.rawtreemap;

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
		silver.util.raw.treemap.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		stdlib.rawtreemap.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		stdlib.Init.init();
		silver.util.raw.treemap.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		stdlib.rawtreemap.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		stdlib.Init.postInit();
		silver.util.raw.treemap.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		stdlib.rawtreemap.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_16_377.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_17_378.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_18_379.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_19_380.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_20_381.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_21_382.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_22_383.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_23_384.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_24_385.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_26_386.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_27_387.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_28_388.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_29_389.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_30_390.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_31_391.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_32_392.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_40_393.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_41_394.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_42_395.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_43_396.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_44_397.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_45_398.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_46_399.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_47_400.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_48_401.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_49_402.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_50_403.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_51_404.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_52_405.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_57_406.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_58_407.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_59_408.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_60_409.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_62_410.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_63_411.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_64_412.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_65_413.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestTreeMap_sv_67_414.class);
	}

	private static void setupInheritedAttributes(){
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_16_377.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_16_377] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_16_377:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_16_377.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_16_377] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_16_377:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_17_378.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_17_378] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_17_378:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_17_378.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_17_378] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_17_378:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_18_379.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_18_379] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_18_379:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_18_379.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_18_379] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_18_379:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_19_380.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_19_380] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_19_380:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_19_380.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_19_380] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_19_380:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_20_381.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_20_381] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_20_381:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_20_381.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_20_381] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_20_381:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_21_382.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_21_382] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_21_382:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_21_382.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_21_382] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_21_382:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_22_383.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_22_383] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_22_383:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_22_383.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_22_383] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_22_383:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_23_384.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_23_384] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_23_384:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_23_384.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_23_384] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_23_384:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_24_385.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_24_385] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_24_385:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_24_385.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_24_385] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_24_385:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_26_386.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_26_386] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_26_386:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_26_386.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_26_386] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_26_386:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_27_387.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_27_387] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_27_387:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_27_387.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_27_387] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_27_387:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_28_388.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_28_388] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_28_388:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_28_388.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_28_388] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_28_388:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_29_389.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_29_389] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_29_389:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_29_389.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_29_389] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_29_389:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_30_390.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_30_390] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_30_390:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_30_390.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_30_390] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_30_390:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_31_391.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_31_391] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_31_391:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_31_391.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_31_391] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_31_391:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_32_392.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_32_392] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_32_392:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_32_392.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_32_392] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_32_392:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_40_393.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_40_393] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_40_393:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_40_393.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_40_393] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_40_393:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_41_394.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_41_394] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_41_394:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_41_394.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_41_394] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_41_394:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_42_395.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_42_395] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_42_395:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_42_395.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_42_395] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_42_395:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_43_396.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_43_396] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_43_396:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_43_396.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_43_396] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_43_396:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_44_397.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_44_397] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_44_397:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_44_397.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_44_397] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_44_397:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_45_398.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_45_398] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_45_398:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_45_398.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_45_398] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_45_398:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_46_399.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_46_399] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_46_399:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_46_399.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_46_399] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_46_399:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_47_400.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_47_400] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_47_400:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_47_400.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_47_400] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_47_400:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_48_401.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_48_401] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_48_401:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_48_401.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_48_401] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_48_401:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_49_402.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_49_402] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_49_402:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_49_402.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_49_402] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_49_402:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_50_403.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_50_403] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_50_403:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_50_403.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_50_403] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_50_403:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_51_404.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_51_404] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_51_404:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_51_404.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_51_404] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_51_404:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_52_405.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_52_405] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_52_405:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_52_405.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_52_405] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_52_405:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_57_406.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_57_406] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_57_406:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_57_406.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_57_406] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_57_406:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_58_407.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_58_407] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_58_407:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_58_407.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_58_407] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_58_407:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_59_408.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_59_408] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_59_408:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_59_408.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_59_408] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_59_408:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_60_409.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_60_409] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_60_409:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_60_409.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_60_409] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_60_409:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_62_410.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_62_410] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_62_410:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_62_410.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_62_410] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_62_410:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_63_411.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_63_411:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_63_411.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_63_411:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_64_412.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_64_412] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_64_412:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_64_412.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_64_412] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_64_412:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_65_413.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_65_413] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_65_413:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_65_413.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_65_413] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_65_413:local:expected";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_67_414.occurs_local[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_67_414] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_67_414:local:value";
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_67_414.occurs_local[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_67_414] = "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_67_414:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_16_377.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_16_377(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_16_377()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_17_378.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_17_378(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_17_378()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_18_379.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_18_379(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_18_379()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_19_380.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_19_380(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_19_380()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_20_381.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_20_381(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_20_381()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_21_382.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_21_382(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_21_382()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_22_383.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_22_383(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_22_383()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_23_384.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_23_384(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_23_384()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_24_385.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_24_385(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_24_385()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_26_386.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_26_386(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_26_386()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_27_387.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_27_387(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_27_387()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_28_388.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_28_388(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_28_388()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_29_389.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_29_389(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_29_389()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_30_390.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_30_390(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_30_390()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_31_391.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_31_391(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_31_391()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_32_392.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_32_392(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_32_392()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_40_393.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_40_393(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_40_393()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_41_394.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_41_394(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_41_394()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_42_395.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_42_395(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_42_395()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_43_396.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_43_396(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_43_396()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_44_397.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_44_397(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_44_397()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_45_398.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_45_398(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_45_398()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_46_399.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_46_399(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_46_399()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_47_400.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_47_400(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_47_400()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_48_401.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_48_401(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_48_401()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_49_402.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_49_402(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_49_402()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_50_403.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_50_403(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_50_403()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_51_404.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_51_404(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_51_404()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_52_405.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_52_405(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_52_405()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_57_406.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_57_406(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_57_406()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_58_407.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_58_407(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_58_407()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_59_408.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_59_408(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_59_408()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_60_409.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_60_409(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_60_409()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_62_410.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_62_410(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_62_410()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_63_411.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_63_411(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_63_411()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_64_412.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_64_412(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_64_412()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_65_413.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_65_413(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_65_413()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_67_414.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestTreeMap_sv_67_414(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_67_414()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_16_377 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_17_378 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_18_379 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_19_380 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_20_381 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_21_382 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_22_383 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_23_384 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_24_385 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_26_386 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_27_387 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_28_388 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_29_389 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_30_390 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_31_391 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_32_392 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_40_393 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_41_394 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_42_395 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_43_396 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_44_397 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_45_398 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_46_399 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_47_400 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_48_401 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_49_402 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_50_403 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_51_404 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_52_405 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_57_406 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_58_407 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_59_408 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_60_409 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_62_410 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_64_412 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_65_413 = 0;
	public static int count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_67_414 = 0;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_16_377 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_16_377++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_16_377 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_16_377++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_17_378 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_17_378++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_17_378 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_17_378++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_18_379 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_18_379++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_18_379 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_18_379++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_19_380 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_19_380++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_19_380 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_19_380++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_20_381 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_20_381++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_20_381 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_20_381++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_21_382 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_21_382++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_21_382 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_21_382++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_22_383 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_22_383++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_22_383 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_22_383++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_23_384 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_23_384++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_23_384 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_23_384++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_24_385 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_24_385++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_24_385 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_24_385++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_26_386 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_26_386++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_26_386 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_26_386++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_27_387 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_27_387++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_27_387 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_27_387++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_28_388 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_28_388++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_28_388 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_28_388++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_29_389 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_29_389++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_29_389 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_29_389++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_30_390 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_30_390++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_30_390 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_30_390++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_31_391 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_31_391++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_31_391 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_31_391++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_32_392 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_32_392++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_32_392 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_32_392++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_40_393 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_40_393++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_40_393 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_40_393++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_41_394 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_41_394++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_41_394 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_41_394++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_42_395 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_42_395++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_42_395 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_42_395++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_43_396 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_43_396++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_43_396 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_43_396++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_44_397 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_44_397++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_44_397 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_44_397++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_45_398 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_45_398++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_45_398 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_45_398++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_46_399 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_46_399++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_46_399 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_46_399++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_47_400 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_47_400++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_47_400 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_47_400++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_48_401 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_48_401++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_48_401 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_48_401++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_49_402 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_49_402++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_49_402 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_49_402++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_50_403 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_50_403++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_50_403 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_50_403++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_51_404 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_51_404++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_51_404 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_51_404++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_52_405 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_52_405++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_52_405 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_52_405++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_57_406 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_57_406++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_57_406 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_57_406++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_58_407 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_58_407++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_58_407 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_58_407++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_59_408 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_59_408++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_59_408 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_59_408++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_60_409 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_60_409++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_60_409 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_60_409++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_62_410 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_62_410++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_62_410 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_62_410++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_64_412 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_64_412++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_64_412 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_64_412++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_65_413 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_65_413++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_65_413 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_65_413++;
public static final int value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_67_414 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_67_414++;
public static final int expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_67_414 = stdlib.rawtreemap.Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_67_414++;
	public static final common.Thunk<Object> e = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treemap.Pempty.invoke(core.PcompareString.factory)); } };
	public static final common.Thunk<Object> t1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treemap.Padd.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("1")), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("2")), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("3")), Integer.valueOf((int)3))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("4")), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("5")), Integer.valueOf((int)5))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("6")), Integer.valueOf((int)6))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("1")), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("2")), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("0")), Integer.valueOf((int)0))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("7")), Integer.valueOf((int)7))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } }, stdlib.rawtreemap.Init.e)); } };
	public static final common.Thunk<Object> t2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treemap.Padd.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("g")), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("f")), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("d")), Integer.valueOf((int)3))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("s")), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("a")), Integer.valueOf((int)5))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("p")), Integer.valueOf((int)6))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("q")), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("h")), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("i")), Integer.valueOf((int)0))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("q")), Integer.valueOf((int)7))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } }, stdlib.rawtreemap.Init.t1)); } };
	public static final common.Thunk<Object> l1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("hi")), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("hello")), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("hola")), Integer.valueOf((int)6))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("quepasa")), Integer.valueOf((int)11))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> t6 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treemap.Padd.invoke(stdlib.rawtreemap.Init.l1, stdlib.rawtreemap.Init.t2)); } };
	public static final common.Thunk<Object> l2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.raw.treemap.PtoList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treemap.Padd.invoke(stdlib.rawtreemap.Init.l1, stdlib.rawtreemap.Init.e)); } })); } };
}
