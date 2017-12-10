package copper_features;

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
		copper_features.token_pushing.Init.initAllStatics();
		copper_features.mdatests.Init.initAllStatics();
		copper_features.test_layout.Init.initAllStatics();
		copper_features.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		copper_features.token_pushing.Init.init();
		copper_features.mdatests.Init.init();
		copper_features.test_layout.Init.init();
		copper_features.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		copper_features.token_pushing.Init.postInit();
		copper_features.mdatests.Init.postInit();
		copper_features.test_layout.Init.postInit();
		copper_features.Init.postInit();


		common.Decorator.applyDecorators(copper_features.NAOrB.decorators, Paorb_a.class);
		common.Decorator.applyDecorators(copper_features.NAOrB.decorators, Paorb_b.class);
		common.Decorator.applyDecorators(copper_features.NAOrBs.decorators, Paorb_one.class);
		common.Decorator.applyDecorators(copper_features.NAOrBs.decorators, Paorb_cons.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SemanticActions_sv_62_2.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SemanticActions_sv_63_3.class);
		common.Decorator.applyDecorators(copper_features.NDGRoot.decorators, Prt.class);
		common.Decorator.applyDecorators(copper_features.NDGRoot.decorators, Prt2.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DisambiguationGroups_sv_31_4.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DisambiguationGroups_sv_32_5.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DisambiguationGroups_sv_33_6.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DisambiguationGroups_sv_36_7.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DisambiguationGroups_sv_37_8.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DisambiguationGroups_sv_38_9.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DisambiguationGroups_sv_39_10.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_DisambiguationGroups_sv_40_11.class);
		common.Decorator.applyDecorators(copper_features.NUseDcls.decorators, Poneud.class);
		common.Decorator.applyDecorators(copper_features.NUseDcls.decorators, Pconsud.class);
		common.Decorator.applyDecorators(copper_features.NUseDcl.decorators, PunUse.class);
		common.Decorator.applyDecorators(copper_features.NUseDcl.decorators, PkUse.class);
		common.Decorator.applyDecorators(copper_features.NUseDcl.decorators, PdDcl.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ParserAttributes_sv_60_12.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ParserAttributes_sv_61_13.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ParserAttributes_sv_63_14.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ParserAttributes_sv_64_15.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ParserAttributes_sv_66_16.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ParserAttributes_sv_67_17.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ParserAttributes_sv_68_18.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ParserAttributes_sv_69_19.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ParserAttributes_sv_70_20.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_ParserAttributes_sv_71_21.class);
		common.Decorator.applyDecorators(silver.testing.NTestSuite.decorators, Pcopper_tests.class);
	}

	private static void setupInheritedAttributes(){
		copper_features.NAOrB.occurs_syn[copper_features.Init.copper_features_semResult__ON__copper_features_AOrB] = "copper_features:semResult";
		copper_features.NAOrBs.occurs_syn[copper_features.Init.copper_features_semResult__ON__copper_features_AOrBs] = "copper_features:semResult";
		copper_features.PgeneratedTest_SemanticActions_sv_62_2.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_SemanticActions_sv_62_2] = "copper_features:generatedTest_SemanticActions_sv_62_2:local:value";
		copper_features.PgeneratedTest_SemanticActions_sv_62_2.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_SemanticActions_sv_62_2] = "copper_features:generatedTest_SemanticActions_sv_62_2:local:expected";
		copper_features.PgeneratedTest_SemanticActions_sv_63_3.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_SemanticActions_sv_63_3] = "copper_features:generatedTest_SemanticActions_sv_63_3:local:value";
		copper_features.PgeneratedTest_SemanticActions_sv_63_3.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_SemanticActions_sv_63_3] = "copper_features:generatedTest_SemanticActions_sv_63_3:local:expected";
		copper_features.NDGRoot.occurs_syn[copper_features.Init.copper_features_dgFoo__ON__copper_features_DGRoot] = "copper_features:dgFoo";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_31_4.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_DisambiguationGroups_sv_31_4] = "copper_features:generatedTest_DisambiguationGroups_sv_31_4:local:value";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_31_4.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_31_4] = "copper_features:generatedTest_DisambiguationGroups_sv_31_4:local:expected";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_32_5.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5] = "copper_features:generatedTest_DisambiguationGroups_sv_32_5:local:value";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_32_5.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5] = "copper_features:generatedTest_DisambiguationGroups_sv_32_5:local:expected";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_33_6.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_DisambiguationGroups_sv_33_6] = "copper_features:generatedTest_DisambiguationGroups_sv_33_6:local:value";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_33_6.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_33_6] = "copper_features:generatedTest_DisambiguationGroups_sv_33_6:local:expected";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_36_7.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_DisambiguationGroups_sv_36_7] = "copper_features:generatedTest_DisambiguationGroups_sv_36_7:local:value";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_36_7.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_36_7] = "copper_features:generatedTest_DisambiguationGroups_sv_36_7:local:expected";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_37_8.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_DisambiguationGroups_sv_37_8] = "copper_features:generatedTest_DisambiguationGroups_sv_37_8:local:value";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_37_8.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_37_8] = "copper_features:generatedTest_DisambiguationGroups_sv_37_8:local:expected";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_38_9.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_DisambiguationGroups_sv_38_9] = "copper_features:generatedTest_DisambiguationGroups_sv_38_9:local:value";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_38_9.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_38_9] = "copper_features:generatedTest_DisambiguationGroups_sv_38_9:local:expected";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_39_10.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_DisambiguationGroups_sv_39_10] = "copper_features:generatedTest_DisambiguationGroups_sv_39_10:local:value";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_39_10.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_39_10] = "copper_features:generatedTest_DisambiguationGroups_sv_39_10:local:expected";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_40_11.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_DisambiguationGroups_sv_40_11] = "copper_features:generatedTest_DisambiguationGroups_sv_40_11:local:value";
		copper_features.PgeneratedTest_DisambiguationGroups_sv_40_11.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_40_11] = "copper_features:generatedTest_DisambiguationGroups_sv_40_11:local:expected";
		copper_features.NUseDcl.occurs_syn[copper_features.Init.copper_features_unknownsused__ON__copper_features_UseDcl] = "copper_features:unknownsused";
		copper_features.NUseDcls.occurs_syn[copper_features.Init.copper_features_unknownsused__ON__copper_features_UseDcls] = "copper_features:unknownsused";
		copper_features.PgeneratedTest_ParserAttributes_sv_60_12.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_60_12] = "copper_features:generatedTest_ParserAttributes_sv_60_12:local:value";
		copper_features.PgeneratedTest_ParserAttributes_sv_60_12.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_60_12] = "copper_features:generatedTest_ParserAttributes_sv_60_12:local:expected";
		copper_features.PgeneratedTest_ParserAttributes_sv_61_13.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_61_13] = "copper_features:generatedTest_ParserAttributes_sv_61_13:local:value";
		copper_features.PgeneratedTest_ParserAttributes_sv_61_13.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_61_13] = "copper_features:generatedTest_ParserAttributes_sv_61_13:local:expected";
		copper_features.PgeneratedTest_ParserAttributes_sv_63_14.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_63_14] = "copper_features:generatedTest_ParserAttributes_sv_63_14:local:value";
		copper_features.PgeneratedTest_ParserAttributes_sv_63_14.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_63_14] = "copper_features:generatedTest_ParserAttributes_sv_63_14:local:expected";
		copper_features.PgeneratedTest_ParserAttributes_sv_64_15.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_64_15] = "copper_features:generatedTest_ParserAttributes_sv_64_15:local:value";
		copper_features.PgeneratedTest_ParserAttributes_sv_64_15.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_64_15] = "copper_features:generatedTest_ParserAttributes_sv_64_15:local:expected";
		copper_features.PgeneratedTest_ParserAttributes_sv_66_16.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_66_16] = "copper_features:generatedTest_ParserAttributes_sv_66_16:local:value";
		copper_features.PgeneratedTest_ParserAttributes_sv_66_16.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_66_16] = "copper_features:generatedTest_ParserAttributes_sv_66_16:local:expected";
		copper_features.PgeneratedTest_ParserAttributes_sv_67_17.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_67_17] = "copper_features:generatedTest_ParserAttributes_sv_67_17:local:value";
		copper_features.PgeneratedTest_ParserAttributes_sv_67_17.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_67_17] = "copper_features:generatedTest_ParserAttributes_sv_67_17:local:expected";
		copper_features.PgeneratedTest_ParserAttributes_sv_68_18.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_68_18] = "copper_features:generatedTest_ParserAttributes_sv_68_18:local:value";
		copper_features.PgeneratedTest_ParserAttributes_sv_68_18.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_68_18] = "copper_features:generatedTest_ParserAttributes_sv_68_18:local:expected";
		copper_features.PgeneratedTest_ParserAttributes_sv_69_19.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_69_19] = "copper_features:generatedTest_ParserAttributes_sv_69_19:local:value";
		copper_features.PgeneratedTest_ParserAttributes_sv_69_19.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_69_19] = "copper_features:generatedTest_ParserAttributes_sv_69_19:local:expected";
		copper_features.PgeneratedTest_ParserAttributes_sv_70_20.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_70_20] = "copper_features:generatedTest_ParserAttributes_sv_70_20:local:value";
		copper_features.PgeneratedTest_ParserAttributes_sv_70_20.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_70_20] = "copper_features:generatedTest_ParserAttributes_sv_70_20:local:expected";
		copper_features.PgeneratedTest_ParserAttributes_sv_71_21.occurs_local[copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_71_21] = "copper_features:generatedTest_ParserAttributes_sv_71_21:local:value";
		copper_features.PgeneratedTest_ParserAttributes_sv_71_21.occurs_local[copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_71_21] = "copper_features:generatedTest_ParserAttributes_sv_71_21:local:expected";
		//	local attribute testResults::TestSuite;
		copper_features.Pmain.localInheritedAttributes[copper_features.Init.testResults__ON__copper_features_main] = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];
		copper_features.Pmain.occurs_local[copper_features.Init.testResults__ON__copper_features_main] = "copper_features:main:local:testResults";
		copper_features.Pcopper_tests.occurs_local[copper_features.Init.testsToPerform__ON__copper_features_copper_tests] = "copper_features:copper_tests:local:testsToPerform";
		copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests] = new common.CollectionAttribute(){
			public Object eval(common.DecoratedNode context) {
				common.ConsCell result = (common.ConsCell)this.getBase().eval(context);
				for(int i = 0; i < this.getPieces().size(); i++){
					result = common.AppendCell.append(result, (common.ConsCell)this.getPieces().get(i).eval(context));
				}
				return result;
			}
		};
	}

	private static void initProductionAttributeDefinitions(){
		copper_features.Paorb_a.initProductionAttributeDefinitions();
		copper_features.Paorb_b.initProductionAttributeDefinitions();
		copper_features.Paorb_one.initProductionAttributeDefinitions();
		copper_features.Paorb_cons.initProductionAttributeDefinitions();
		copper_features.PgeneratedTest_SemanticActions_sv_62_2.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_SemanticActions_sv_62_2(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_SemanticActions_sv_62_2()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_SemanticActions_sv_63_3.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_SemanticActions_sv_63_3(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_SemanticActions_sv_63_3()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.Prt.initProductionAttributeDefinitions();
		copper_features.Prt2.initProductionAttributeDefinitions();
		copper_features.PgeneratedTest_DisambiguationGroups_sv_31_4.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_DisambiguationGroups_sv_31_4(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_DisambiguationGroups_sv_31_4()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_DisambiguationGroups_sv_32_5.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_DisambiguationGroups_sv_32_5(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_DisambiguationGroups_sv_32_5()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_DisambiguationGroups_sv_33_6.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_DisambiguationGroups_sv_33_6(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_DisambiguationGroups_sv_33_6()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_DisambiguationGroups_sv_36_7.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_DisambiguationGroups_sv_36_7(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_DisambiguationGroups_sv_36_7()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_DisambiguationGroups_sv_37_8.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_DisambiguationGroups_sv_37_8(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_DisambiguationGroups_sv_37_8()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_DisambiguationGroups_sv_38_9.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_DisambiguationGroups_sv_38_9(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_DisambiguationGroups_sv_38_9()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_DisambiguationGroups_sv_39_10.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_DisambiguationGroups_sv_39_10(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_DisambiguationGroups_sv_39_10()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_DisambiguationGroups_sv_40_11.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_DisambiguationGroups_sv_40_11(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_DisambiguationGroups_sv_40_11()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.Poneud.initProductionAttributeDefinitions();
		copper_features.Pconsud.initProductionAttributeDefinitions();
		copper_features.PunUse.initProductionAttributeDefinitions();
		copper_features.PkUse.initProductionAttributeDefinitions();
		copper_features.PdDcl.initProductionAttributeDefinitions();
		copper_features.PgeneratedTest_ParserAttributes_sv_60_12.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_ParserAttributes_sv_60_12(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_ParserAttributes_sv_60_12()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_ParserAttributes_sv_61_13.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_ParserAttributes_sv_61_13(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_ParserAttributes_sv_61_13()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_ParserAttributes_sv_63_14.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_ParserAttributes_sv_63_14(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_ParserAttributes_sv_63_14()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_ParserAttributes_sv_64_15.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_ParserAttributes_sv_64_15(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_ParserAttributes_sv_64_15()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_ParserAttributes_sv_66_16.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_ParserAttributes_sv_66_16(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_ParserAttributes_sv_66_16()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_ParserAttributes_sv_67_17.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_ParserAttributes_sv_67_17(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_ParserAttributes_sv_67_17()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_ParserAttributes_sv_68_18.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_ParserAttributes_sv_68_18(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_ParserAttributes_sv_68_18()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_ParserAttributes_sv_69_19.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_ParserAttributes_sv_69_19(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_ParserAttributes_sv_69_19()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_ParserAttributes_sv_70_20.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_ParserAttributes_sv_70_20(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_ParserAttributes_sv_70_20()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.PgeneratedTest_ParserAttributes_sv_71_21.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_ParserAttributes_sv_71_21(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.PgeneratedTest_ParserAttributes_sv_71_21()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION main IOVal<Integer> ::= [String] mainIO::core:IO 
		// testResults = copper_tests(,)
		copper_features.Pmain.localAttributes[copper_features.Init.testResults__ON__copper_features_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.testing.NTestSuite)new copper_features.Pcopper_tests()); } };
		// testResults.ioIn = mainIO
		copper_features.Pmain.localInheritedAttributes[copper_features.Init.testResults__ON__copper_features_main][silver.testing.Init.silver_testing_ioIn__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(copper_features.Pmain.i_mainIO)); } };
		copper_features.Pcopper_tests.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__AOrB = 0;
	public static int count_syn__ON__AOrB = 0;
	public static int count_local__ON__copper_features_aorb_a = 0;
	public static int count_local__ON__copper_features_aorb_b = 0;
	public static int count_inh__ON__AOrBs = 0;
	public static int count_syn__ON__AOrBs = 0;
	public static int count_local__ON__copper_features_aorb_one = 0;
	public static int count_local__ON__copper_features_aorb_cons = 0;
	public static int count_local__ON__copper_features_saParse = 0;
	public static int count_local__ON__copper_features_generatedTest_SemanticActions_sv_62_2 = 0;
	public static int count_local__ON__copper_features_generatedTest_SemanticActions_sv_63_3 = 0;
	public static int count_inh__ON__DGRoot = 0;
	public static int count_syn__ON__DGRoot = 0;
	public static int count_local__ON__copper_features_rt = 0;
	public static int count_local__ON__copper_features_rt2 = 0;
	public static int count_local__ON__copper_features_dgparse = 0;
	public static int count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_31_4 = 0;
	public static int count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5 = 0;
	public static int count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_33_6 = 0;
	public static int count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_36_7 = 0;
	public static int count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_37_8 = 0;
	public static int count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_38_9 = 0;
	public static int count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_39_10 = 0;
	public static int count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_40_11 = 0;
	public static int count_local__ON__copper_features_PAparse = 0;
	public static int count_inh__ON__UseDcl = 0;
	public static int count_syn__ON__UseDcl = 0;
	public static int count_inh__ON__UseDcls = 0;
	public static int count_syn__ON__UseDcls = 0;
	public static int count_local__ON__copper_features_oneud = 0;
	public static int count_local__ON__copper_features_consud = 0;
	public static int count_local__ON__copper_features_unUse = 0;
	public static int count_local__ON__copper_features_kUse = 0;
	public static int count_local__ON__copper_features_dDcl = 0;
	public static int count_local__ON__copper_features_generatedTest_ParserAttributes_sv_60_12 = 0;
	public static int count_local__ON__copper_features_generatedTest_ParserAttributes_sv_61_13 = 0;
	public static int count_local__ON__copper_features_generatedTest_ParserAttributes_sv_63_14 = 0;
	public static int count_local__ON__copper_features_generatedTest_ParserAttributes_sv_64_15 = 0;
	public static int count_local__ON__copper_features_generatedTest_ParserAttributes_sv_66_16 = 0;
	public static int count_local__ON__copper_features_generatedTest_ParserAttributes_sv_67_17 = 0;
	public static int count_local__ON__copper_features_generatedTest_ParserAttributes_sv_68_18 = 0;
	public static int count_local__ON__copper_features_generatedTest_ParserAttributes_sv_69_19 = 0;
	public static int count_local__ON__copper_features_generatedTest_ParserAttributes_sv_70_20 = 0;
	public static int count_local__ON__copper_features_generatedTest_ParserAttributes_sv_71_21 = 0;
	public static int count_inh__ON__Context = 0;
	public static int count_syn__ON__Context = 0;
	public static int count_local__ON__copper_features_main = 0;
	public static int count_local__ON__copper_features_copper_tests = 0;
public static final int copper_features_semResult__ON__copper_features_AOrB = copper_features.Init.count_syn__ON__AOrB++;
public static final int copper_features_semResult__ON__copper_features_AOrBs = copper_features.Init.count_syn__ON__AOrBs++;
public static final int value__ON__copper_features_generatedTest_SemanticActions_sv_62_2 = copper_features.Init.count_local__ON__copper_features_generatedTest_SemanticActions_sv_62_2++;
public static final int expected__ON__copper_features_generatedTest_SemanticActions_sv_62_2 = copper_features.Init.count_local__ON__copper_features_generatedTest_SemanticActions_sv_62_2++;
public static final int value__ON__copper_features_generatedTest_SemanticActions_sv_63_3 = copper_features.Init.count_local__ON__copper_features_generatedTest_SemanticActions_sv_63_3++;
public static final int expected__ON__copper_features_generatedTest_SemanticActions_sv_63_3 = copper_features.Init.count_local__ON__copper_features_generatedTest_SemanticActions_sv_63_3++;
public static final int copper_features_dgFoo__ON__copper_features_DGRoot = copper_features.Init.count_syn__ON__DGRoot++;
public static final int value__ON__copper_features_generatedTest_DisambiguationGroups_sv_31_4 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_31_4++;
public static final int expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_31_4 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_31_4++;
public static final int value__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5++;
public static final int expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5++;
public static final int value__ON__copper_features_generatedTest_DisambiguationGroups_sv_33_6 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_33_6++;
public static final int expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_33_6 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_33_6++;
public static final int value__ON__copper_features_generatedTest_DisambiguationGroups_sv_36_7 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_36_7++;
public static final int expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_36_7 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_36_7++;
public static final int value__ON__copper_features_generatedTest_DisambiguationGroups_sv_37_8 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_37_8++;
public static final int expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_37_8 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_37_8++;
public static final int value__ON__copper_features_generatedTest_DisambiguationGroups_sv_38_9 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_38_9++;
public static final int expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_38_9 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_38_9++;
public static final int value__ON__copper_features_generatedTest_DisambiguationGroups_sv_39_10 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_39_10++;
public static final int expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_39_10 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_39_10++;
public static final int value__ON__copper_features_generatedTest_DisambiguationGroups_sv_40_11 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_40_11++;
public static final int expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_40_11 = copper_features.Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_40_11++;
public static final int copper_features_unknownsused__ON__copper_features_UseDcl = copper_features.Init.count_syn__ON__UseDcl++;
public static final int copper_features_unknownsused__ON__copper_features_UseDcls = copper_features.Init.count_syn__ON__UseDcls++;
public static final int value__ON__copper_features_generatedTest_ParserAttributes_sv_60_12 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_60_12++;
public static final int expected__ON__copper_features_generatedTest_ParserAttributes_sv_60_12 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_60_12++;
public static final int value__ON__copper_features_generatedTest_ParserAttributes_sv_61_13 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_61_13++;
public static final int expected__ON__copper_features_generatedTest_ParserAttributes_sv_61_13 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_61_13++;
public static final int value__ON__copper_features_generatedTest_ParserAttributes_sv_63_14 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_63_14++;
public static final int expected__ON__copper_features_generatedTest_ParserAttributes_sv_63_14 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_63_14++;
public static final int value__ON__copper_features_generatedTest_ParserAttributes_sv_64_15 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_64_15++;
public static final int expected__ON__copper_features_generatedTest_ParserAttributes_sv_64_15 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_64_15++;
public static final int value__ON__copper_features_generatedTest_ParserAttributes_sv_66_16 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_66_16++;
public static final int expected__ON__copper_features_generatedTest_ParserAttributes_sv_66_16 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_66_16++;
public static final int value__ON__copper_features_generatedTest_ParserAttributes_sv_67_17 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_67_17++;
public static final int expected__ON__copper_features_generatedTest_ParserAttributes_sv_67_17 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_67_17++;
public static final int value__ON__copper_features_generatedTest_ParserAttributes_sv_68_18 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_68_18++;
public static final int expected__ON__copper_features_generatedTest_ParserAttributes_sv_68_18 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_68_18++;
public static final int value__ON__copper_features_generatedTest_ParserAttributes_sv_69_19 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_69_19++;
public static final int expected__ON__copper_features_generatedTest_ParserAttributes_sv_69_19 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_69_19++;
public static final int value__ON__copper_features_generatedTest_ParserAttributes_sv_70_20 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_70_20++;
public static final int expected__ON__copper_features_generatedTest_ParserAttributes_sv_70_20 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_70_20++;
public static final int value__ON__copper_features_generatedTest_ParserAttributes_sv_71_21 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_71_21++;
public static final int expected__ON__copper_features_generatedTest_ParserAttributes_sv_71_21 = copper_features.Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_71_21++;
public static final int testResults__ON__copper_features_main = copper_features.Init.count_local__ON__copper_features_main++;
public static final int testsToPerform__ON__copper_features_copper_tests = copper_features.Init.count_local__ON__copper_features_copper_tests++;
}
