package performance;

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
		performance.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		performance.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		performance.Init.postInit();


		common.Decorator.applyDecorators(performance.NQ.decorators, Pq1.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_18_0.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_19_1.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_20_2.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_21_3.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_26_4.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_29_5.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_30_6.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_31_7.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_32_8.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_33_9.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_34_10.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_35_11.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Lazy_sv_39_12.class);
		common.Decorator.applyDecorators(silver.testing.NTestSuite.decorators, Pperformance_tests.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Append_sv_13_13.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Append_sv_18_14.class);
	}

	private static void setupInheritedAttributes(){
		performance.NQ.occurs_syn[performance.Init.performance_a_q__ON__performance_Q] = "performance:a_q";
		performance.NQ.occurs_syn[performance.Init.performance_str__ON__performance_Q] = "performance:str";
		performance.PgeneratedTest_Lazy_sv_18_0.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_18_0] = "performance:generatedTest_Lazy_sv_18_0:local:value";
		performance.PgeneratedTest_Lazy_sv_18_0.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_18_0] = "performance:generatedTest_Lazy_sv_18_0:local:expected";
		performance.PgeneratedTest_Lazy_sv_19_1.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_19_1] = "performance:generatedTest_Lazy_sv_19_1:local:value";
		performance.PgeneratedTest_Lazy_sv_19_1.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_19_1] = "performance:generatedTest_Lazy_sv_19_1:local:expected";
		performance.PgeneratedTest_Lazy_sv_20_2.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_20_2] = "performance:generatedTest_Lazy_sv_20_2:local:value";
		performance.PgeneratedTest_Lazy_sv_20_2.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_20_2] = "performance:generatedTest_Lazy_sv_20_2:local:expected";
		performance.PgeneratedTest_Lazy_sv_21_3.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_21_3] = "performance:generatedTest_Lazy_sv_21_3:local:value";
		performance.PgeneratedTest_Lazy_sv_21_3.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_21_3] = "performance:generatedTest_Lazy_sv_21_3:local:expected";
		performance.PgeneratedTest_Lazy_sv_26_4.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_26_4] = "performance:generatedTest_Lazy_sv_26_4:local:value";
		performance.PgeneratedTest_Lazy_sv_26_4.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_26_4] = "performance:generatedTest_Lazy_sv_26_4:local:expected";
		performance.PgeneratedTest_Lazy_sv_29_5.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_29_5] = "performance:generatedTest_Lazy_sv_29_5:local:value";
		performance.PgeneratedTest_Lazy_sv_29_5.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_29_5] = "performance:generatedTest_Lazy_sv_29_5:local:expected";
		performance.PgeneratedTest_Lazy_sv_30_6.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_30_6] = "performance:generatedTest_Lazy_sv_30_6:local:value";
		performance.PgeneratedTest_Lazy_sv_30_6.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_30_6] = "performance:generatedTest_Lazy_sv_30_6:local:expected";
		performance.PgeneratedTest_Lazy_sv_31_7.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_31_7] = "performance:generatedTest_Lazy_sv_31_7:local:value";
		performance.PgeneratedTest_Lazy_sv_31_7.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_31_7] = "performance:generatedTest_Lazy_sv_31_7:local:expected";
		performance.PgeneratedTest_Lazy_sv_32_8.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_32_8] = "performance:generatedTest_Lazy_sv_32_8:local:value";
		performance.PgeneratedTest_Lazy_sv_32_8.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_32_8] = "performance:generatedTest_Lazy_sv_32_8:local:expected";
		performance.PgeneratedTest_Lazy_sv_33_9.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_33_9] = "performance:generatedTest_Lazy_sv_33_9:local:value";
		performance.PgeneratedTest_Lazy_sv_33_9.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_33_9] = "performance:generatedTest_Lazy_sv_33_9:local:expected";
		performance.PgeneratedTest_Lazy_sv_34_10.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_34_10] = "performance:generatedTest_Lazy_sv_34_10:local:value";
		performance.PgeneratedTest_Lazy_sv_34_10.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_34_10] = "performance:generatedTest_Lazy_sv_34_10:local:expected";
		performance.PgeneratedTest_Lazy_sv_35_11.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_35_11] = "performance:generatedTest_Lazy_sv_35_11:local:value";
		performance.PgeneratedTest_Lazy_sv_35_11.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_35_11] = "performance:generatedTest_Lazy_sv_35_11:local:expected";
		performance.PgeneratedTest_Lazy_sv_39_12.occurs_local[performance.Init.value__ON__performance_generatedTest_Lazy_sv_39_12] = "performance:generatedTest_Lazy_sv_39_12:local:value";
		performance.PgeneratedTest_Lazy_sv_39_12.occurs_local[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_39_12] = "performance:generatedTest_Lazy_sv_39_12:local:expected";
		//	local attribute testResults::TestSuite;
		performance.Pmain.localInheritedAttributes[performance.Init.testResults__ON__performance_main] = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];
		performance.Pmain.occurs_local[performance.Init.testResults__ON__performance_main] = "performance:main:local:testResults";
		performance.Pperformance_tests.occurs_local[performance.Init.testsToPerform__ON__performance_performance_tests] = "performance:performance_tests:local:testsToPerform";
		performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
		performance.PgeneratedTest_Append_sv_13_13.occurs_local[performance.Init.value__ON__performance_generatedTest_Append_sv_13_13] = "performance:generatedTest_Append_sv_13_13:local:value";
		performance.PgeneratedTest_Append_sv_13_13.occurs_local[performance.Init.expected__ON__performance_generatedTest_Append_sv_13_13] = "performance:generatedTest_Append_sv_13_13:local:expected";
		performance.PgeneratedTest_Append_sv_18_14.occurs_local[performance.Init.value__ON__performance_generatedTest_Append_sv_18_14] = "performance:generatedTest_Append_sv_18_14:local:value";
		performance.PgeneratedTest_Append_sv_18_14.occurs_local[performance.Init.expected__ON__performance_generatedTest_Append_sv_18_14] = "performance:generatedTest_Append_sv_18_14:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		performance.Pq1.initProductionAttributeDefinitions();
		performance.PgeneratedTest_Lazy_sv_18_0.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_18_0(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_18_0()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_19_1.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_19_1(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_19_1()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_20_2.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_20_2(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_20_2()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_21_3.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_21_3(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_21_3()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_26_4.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_26_4(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_26_4()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_29_5.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_29_5(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_29_5()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_30_6.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_30_6(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_30_6()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_31_7.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_31_7(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_31_7()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_32_8.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_32_8(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_32_8()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_33_9.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_33_9(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_33_9()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_34_10.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_34_10(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_34_10()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_35_11.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_35_11(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_35_11()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Lazy_sv_39_12.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Lazy_sv_39_12(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Lazy_sv_39_12()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION main IOVal<Integer> ::= [String] mainIO::core:IO 
		// testResults = performance_tests(,)
		performance.Pmain.localAttributes[performance.Init.testResults__ON__performance_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.testing.NTestSuite)new performance.Pperformance_tests()); } };
		// testResults.ioIn = mainIO
		performance.Pmain.localInheritedAttributes[performance.Init.testResults__ON__performance_main][silver.testing.Init.silver_testing_ioIn__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(performance.Pmain.i_mainIO)); } };
		performance.Pperformance_tests.initProductionAttributeDefinitions();
		performance.PgeneratedTest_Append_sv_13_13.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Append_sv_13_13(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Append_sv_13_13()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		performance.PgeneratedTest_Append_sv_18_14.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION performance_tests t ::= 
		// testsToPerform <- [ generatedTest_Append_sv_18_14(,) ]
		((common.CollectionAttribute)performance.Pperformance_tests.localAttributes[performance.Init.testsToPerform__ON__performance_performance_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new performance.PgeneratedTest_Append_sv_18_14()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_inh__ON__Q = 0;
	public static int count_syn__ON__Q = 0;
	public static int count_local__ON__performance_q1 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_18_0 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_19_1 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_20_2 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_21_3 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_26_4 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_29_5 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_30_6 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_31_7 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_32_8 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_33_9 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_34_10 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_35_11 = 0;
	public static int count_local__ON__performance_generatedTest_Lazy_sv_39_12 = 0;
	public static int count_local__ON__performance_main = 0;
	public static int count_local__ON__performance_performance_tests = 0;
	public static int count_local__ON__performance_generatedTest_Append_sv_13_13 = 0;
	public static int count_local__ON__performance_generatedTest_Append_sv_18_14 = 0;
public static final int performance_a_q__ON__performance_Q = performance.Init.count_syn__ON__Q++;
public static final int performance_str__ON__performance_Q = performance.Init.count_syn__ON__Q++;
public static final int value__ON__performance_generatedTest_Lazy_sv_18_0 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_18_0++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_18_0 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_18_0++;
public static final int value__ON__performance_generatedTest_Lazy_sv_19_1 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_19_1++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_19_1 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_19_1++;
public static final int value__ON__performance_generatedTest_Lazy_sv_20_2 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_20_2++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_20_2 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_20_2++;
public static final int value__ON__performance_generatedTest_Lazy_sv_21_3 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_21_3++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_21_3 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_21_3++;
public static final int value__ON__performance_generatedTest_Lazy_sv_26_4 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_26_4++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_26_4 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_26_4++;
public static final int value__ON__performance_generatedTest_Lazy_sv_29_5 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_29_5++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_29_5 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_29_5++;
public static final int value__ON__performance_generatedTest_Lazy_sv_30_6 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_30_6++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_30_6 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_30_6++;
public static final int value__ON__performance_generatedTest_Lazy_sv_31_7 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_31_7++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_31_7 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_31_7++;
public static final int value__ON__performance_generatedTest_Lazy_sv_32_8 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_32_8++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_32_8 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_32_8++;
public static final int value__ON__performance_generatedTest_Lazy_sv_33_9 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_33_9++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_33_9 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_33_9++;
public static final int value__ON__performance_generatedTest_Lazy_sv_34_10 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_34_10++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_34_10 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_34_10++;
public static final int value__ON__performance_generatedTest_Lazy_sv_35_11 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_35_11++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_35_11 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_35_11++;
public static final int value__ON__performance_generatedTest_Lazy_sv_39_12 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_39_12++;
public static final int expected__ON__performance_generatedTest_Lazy_sv_39_12 = performance.Init.count_local__ON__performance_generatedTest_Lazy_sv_39_12++;
public static final int testResults__ON__performance_main = performance.Init.count_local__ON__performance_main++;
public static final int testsToPerform__ON__performance_performance_tests = performance.Init.count_local__ON__performance_performance_tests++;
public static final int value__ON__performance_generatedTest_Append_sv_13_13 = performance.Init.count_local__ON__performance_generatedTest_Append_sv_13_13++;
public static final int expected__ON__performance_generatedTest_Append_sv_13_13 = performance.Init.count_local__ON__performance_generatedTest_Append_sv_13_13++;
public static final int value__ON__performance_generatedTest_Append_sv_18_14 = performance.Init.count_local__ON__performance_generatedTest_Append_sv_18_14++;
public static final int expected__ON__performance_generatedTest_Append_sv_18_14 = performance.Init.count_local__ON__performance_generatedTest_Append_sv_18_14++;
	public static final common.Thunk<Object> q = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((performance.NQ)new performance.Pq1(performance.Init.q)); } };
	public static final common.Thunk<Object> infiniteOnes = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)1), performance.Init.infiniteOnes)); } };
	public static final common.Thunk<Object> nolist = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("oops!")))); } };
	public static final common.Thunk<Object> ll = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("a")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
	public static final common.Thunk<Object> ss = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("a")); } };
}
