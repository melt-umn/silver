package silver.modification.impide;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		silver.extension.list.java.Init.initAllStatics();
		silver.modification.ffi.java.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		silver.translation.java.type.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.util.cmdargs.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.translation.java.driver.Init.initAllStatics();
		silver.driver.Init.initAllStatics();
		silver.translation.java.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();
		silver.modification.impide.spec.Init.initAllStatics();
		silver.modification.impide.cstast.Init.initAllStatics();
		silver.modification.copper.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.modification.ffi.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		silver.extension.list.Init.initAllStatics();
		silver.modification.copper_mda.Init.initAllStatics();
		silver.modification.impide.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		silver.extension.list.java.Init.init();
		silver.modification.ffi.java.Init.init();
		core.monad.Init.init();
		silver.translation.java.type.Init.init();
		silver.definition.flow.env.Init.init();
		core.Init.init();
		silver.util.cmdargs.Init.init();
		silver.translation.java.core.Init.init();
		silver.translation.java.driver.Init.init();
		silver.driver.Init.init();
		silver.translation.java.Init.init();
		silver.driver.util.Init.init();
		silver.modification.impide.spec.Init.init();
		silver.modification.impide.cstast.Init.init();
		silver.modification.copper.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.definition.type.Init.init();
		silver.modification.ffi.Init.init();
		silver.analysis.typechecking.core.Init.init();
		silver.extension.list.Init.init();
		silver.modification.copper_mda.Init.init();
		silver.modification.impide.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		silver.extension.list.java.Init.postInit();
		silver.modification.ffi.java.Init.postInit();
		core.monad.Init.postInit();
		silver.translation.java.type.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		core.Init.postInit();
		silver.util.cmdargs.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.translation.java.driver.Init.postInit();
		silver.driver.Init.postInit();
		silver.translation.java.Init.postInit();
		silver.driver.util.Init.postInit();
		silver.modification.impide.spec.Init.postInit();
		silver.modification.impide.cstast.Init.postInit();
		silver.modification.copper.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.modification.ffi.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		silver.extension.list.Init.postInit();
		silver.modification.copper_mda.Init.postInit();
		silver.modification.impide.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PideDcl.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmts.decorators, PemptyIdeStmts.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmts.decorators, PlistIdeStmts.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmts.decorators, PlistIdeStmts2.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmtList.decorators, PnilIdeStmtList.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmtList.decorators, PconsIdeStmtList.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmt.decorators, PmakeIdeStmt_Builder.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmt.decorators, PmakeIdeStmt_PostBuilder.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmt.decorators, PmakeIdeStmt_Exporter.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmt.decorators, PmakeIdeStmt_Folder.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmt.decorators, PmakeIdeStmt_Porperty.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmt.decorators, PnameIdeStmt.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmt.decorators, PversionIdeStmt.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmt.decorators, PresourceIdeStmt.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdeStmt.decorators, PnewfileWizard_c.class);
		common.Decorator.applyDecorators(silver.modification.impide.NStubGenerator.decorators, PmakeStubGenerator.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PfontDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDef.decorators, PfontStyleDef.class);
		common.Decorator.applyDecorators(silver.driver.util.NDriverAction.decorators, PgenerateNCS.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PfontDecl.class);
		common.Decorator.applyDecorators(silver.modification.impide.NFontStyles.decorators, PconsFontStylesDcl.class);
		common.Decorator.applyDecorators(silver.modification.impide.NFontStyles.decorators, PnilFontStylesDcl.class);
		common.Decorator.applyDecorators(silver.modification.impide.NFontStyle.decorators, PfontStyleBoldDcl.class);
		common.Decorator.applyDecorators(silver.modification.impide.NFontStyle.decorators, PfontStyleItalicDcl.class);
		common.Decorator.applyDecorators(silver.modification.impide.NPropertyList.decorators, PnilPropertyList.class);
		common.Decorator.applyDecorators(silver.modification.impide.NPropertyList.decorators, PconsPropertyList.class);
		common.Decorator.applyDecorators(silver.modification.impide.NProperty.decorators, PmakeProperty.class);
		common.Decorator.applyDecorators(silver.modification.impide.NTypeName.decorators, PpropType_String.class);
		common.Decorator.applyDecorators(silver.modification.impide.NTypeName.decorators, PpropType_Integer.class);
		common.Decorator.applyDecorators(silver.modification.impide.NTypeName.decorators, PpropType_Path.class);
		common.Decorator.applyDecorators(silver.modification.impide.NTypeName.decorators, PpropType_URL.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdePropertyOptions.decorators, PnilPropertyOptions.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdePropertyOptions.decorators, PconsPropertyOptions.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdePropertyOption.decorators, PidePropertyOption_optional.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdePropertyOption.decorators, PidePropertyOption_defaultVal.class);
		common.Decorator.applyDecorators(silver.modification.impide.NIdePropertyOption.decorators, PidePropertyOption_displayName.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalModifier.decorators, PterminalModifierFont.class);
		common.Decorator.applyDecorators(silver.modification.copper.NLexerClassModifier.decorators, PlexerClassModifierFont.class);
	}

	private static void setupInheritedAttributes(){
		silver.modification.impide.PideDcl.occurs_local[silver.modification.impide.Init.parsergrammar__ON__silver_modification_impide_ideDcl] = "silver:modification:impide:ideDcl:local:parsergrammar";
		silver.modification.impide.PideDcl.occurs_local[silver.modification.impide.Init.spec__ON__silver_modification_impide_ideDcl] = "silver:modification:impide:ideDcl:local:spec";
		silver.modification.impide.PideDcl.occurs_local[silver.modification.impide.Init.fext__ON__silver_modification_impide_ideDcl] = "silver:modification:impide:ideDcl:local:fext";
		silver.modification.impide.PideDcl.occurs_local[silver.modification.impide.Init.ideName__ON__silver_modification_impide_ideDcl] = "silver:modification:impide:ideDcl:local:ideName";
		silver.modification.impide.PideDcl.occurs_local[silver.modification.impide.Init.ideVersion__ON__silver_modification_impide_ideDcl] = "silver:modification:impide:ideDcl:local:ideVersion";
		silver.modification.impide.NIdeStmts.occurs_inh[silver.modification.impide.Init.silver_definition_env_env__ON__silver_modification_impide_IdeStmts] = "silver:definition:env:env";
		silver.modification.impide.NIdeStmts.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.impide.NIdeStmts.occurs_syn[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmts] = "silver:definition:core:errors";
		silver.modification.impide.NIdeStmts.occurs_inh[silver.modification.impide.Init.silver_definition_core_grammarName__ON__silver_modification_impide_IdeStmts] = "silver:definition:core:grammarName";
		silver.modification.impide.NIdeStmts.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.impide.NIdeStmts.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmts] = "silver:modification:impide:ideFunctions";
		silver.modification.impide.NIdeStmts.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmts] = "silver:modification:impide:propDcls";
		silver.modification.impide.NIdeStmts.occurs_syn[silver.modification.impide.Init.silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmts] = "silver:modification:impide:wizards";
		silver.modification.impide.NIdeStmts.occurs_inh[silver.modification.impide.Init.silver_modification_impide_startNTName__ON__silver_modification_impide_IdeStmts] = "silver:modification:impide:startNTName";
		silver.modification.impide.NIdeStmts.decorators.add(silver.modification.impide.DstartNTName.singleton);
		silver.modification.impide.NIdeStmts.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmts] = "silver:modification:impide:ideNames";
		silver.modification.impide.NIdeStmts.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmts] = "silver:modification:impide:ideVersions";
		silver.modification.impide.NIdeStmts.occurs_syn[silver.modification.impide.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmts] = "silver:modification:impide:spec:ideResources";
		silver.modification.impide.NIdeStmt.occurs_inh[silver.modification.impide.Init.silver_definition_env_env__ON__silver_modification_impide_IdeStmt] = "silver:definition:env:env";
		silver.modification.impide.NIdeStmt.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.impide.NIdeStmt.occurs_syn[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] = "silver:definition:core:errors";
		silver.modification.impide.NIdeStmt.occurs_inh[silver.modification.impide.Init.silver_definition_core_grammarName__ON__silver_modification_impide_IdeStmt] = "silver:definition:core:grammarName";
		silver.modification.impide.NIdeStmt.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.impide.NIdeStmt.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmt] = "silver:modification:impide:ideFunctions";
		silver.modification.impide.NIdeStmt.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmt] = "silver:modification:impide:propDcls";
		silver.modification.impide.NIdeStmt.occurs_syn[silver.modification.impide.Init.silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmt] = "silver:modification:impide:wizards";
		silver.modification.impide.NIdeStmt.occurs_inh[silver.modification.impide.Init.silver_modification_impide_startNTName__ON__silver_modification_impide_IdeStmt] = "silver:modification:impide:startNTName";
		silver.modification.impide.NIdeStmt.decorators.add(silver.modification.impide.DstartNTName.singleton);
		silver.modification.impide.NIdeStmt.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmt] = "silver:modification:impide:ideNames";
		silver.modification.impide.NIdeStmt.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmt] = "silver:modification:impide:ideVersions";
		silver.modification.impide.NIdeStmt.occurs_syn[silver.modification.impide.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmt] = "silver:modification:impide:spec:ideResources";
		silver.modification.impide.NIdeStmtList.occurs_inh[silver.modification.impide.Init.silver_definition_env_env__ON__silver_modification_impide_IdeStmtList] = "silver:definition:env:env";
		silver.modification.impide.NIdeStmtList.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.impide.NIdeStmtList.occurs_syn[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmtList] = "silver:definition:core:errors";
		silver.modification.impide.NIdeStmtList.occurs_inh[silver.modification.impide.Init.silver_definition_core_grammarName__ON__silver_modification_impide_IdeStmtList] = "silver:definition:core:grammarName";
		silver.modification.impide.NIdeStmtList.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.impide.NIdeStmtList.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmtList] = "silver:modification:impide:ideFunctions";
		silver.modification.impide.NIdeStmtList.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmtList] = "silver:modification:impide:propDcls";
		silver.modification.impide.NIdeStmtList.occurs_syn[silver.modification.impide.Init.silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmtList] = "silver:modification:impide:wizards";
		silver.modification.impide.NIdeStmtList.occurs_inh[silver.modification.impide.Init.silver_modification_impide_startNTName__ON__silver_modification_impide_IdeStmtList] = "silver:modification:impide:startNTName";
		silver.modification.impide.NIdeStmtList.decorators.add(silver.modification.impide.DstartNTName.singleton);
		silver.modification.impide.NIdeStmtList.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmtList] = "silver:modification:impide:ideNames";
		silver.modification.impide.NIdeStmtList.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmtList] = "silver:modification:impide:ideVersions";
		silver.modification.impide.NIdeStmtList.occurs_syn[silver.modification.impide.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmtList] = "silver:modification:impide:spec:ideResources";
		//	local attribute expectedType::Type;
		silver.modification.impide.PmakeIdeStmt_Builder.localInheritedAttributes[silver.modification.impide.Init.expectedType__ON__silver_modification_impide_makeIdeStmt_Builder] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.modification.impide.PmakeIdeStmt_Builder.occurs_local[silver.modification.impide.Init.expectedType__ON__silver_modification_impide_makeIdeStmt_Builder] = "silver:modification:impide:makeIdeStmt_Builder:local:expectedType";
		//	local attribute tc1::TypeCheck;
		silver.modification.impide.PmakeIdeStmt_Builder.localInheritedAttributes[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Builder] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.impide.PmakeIdeStmt_Builder.occurs_local[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Builder] = "silver:modification:impide:makeIdeStmt_Builder:local:tc1";
		//	local attribute expectedType::Type;
		silver.modification.impide.PmakeIdeStmt_PostBuilder.localInheritedAttributes[silver.modification.impide.Init.expectedType__ON__silver_modification_impide_makeIdeStmt_PostBuilder] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.modification.impide.PmakeIdeStmt_PostBuilder.occurs_local[silver.modification.impide.Init.expectedType__ON__silver_modification_impide_makeIdeStmt_PostBuilder] = "silver:modification:impide:makeIdeStmt_PostBuilder:local:expectedType";
		//	local attribute tc1::TypeCheck;
		silver.modification.impide.PmakeIdeStmt_PostBuilder.localInheritedAttributes[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_PostBuilder] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.impide.PmakeIdeStmt_PostBuilder.occurs_local[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_PostBuilder] = "silver:modification:impide:makeIdeStmt_PostBuilder:local:tc1";
		//	local attribute expectedType::Type;
		silver.modification.impide.PmakeIdeStmt_Exporter.localInheritedAttributes[silver.modification.impide.Init.expectedType__ON__silver_modification_impide_makeIdeStmt_Exporter] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.modification.impide.PmakeIdeStmt_Exporter.occurs_local[silver.modification.impide.Init.expectedType__ON__silver_modification_impide_makeIdeStmt_Exporter] = "silver:modification:impide:makeIdeStmt_Exporter:local:expectedType";
		//	local attribute tc1::TypeCheck;
		silver.modification.impide.PmakeIdeStmt_Exporter.localInheritedAttributes[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Exporter] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.impide.PmakeIdeStmt_Exporter.occurs_local[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Exporter] = "silver:modification:impide:makeIdeStmt_Exporter:local:tc1";
		//	local attribute expectedType::Type;
		silver.modification.impide.PmakeIdeStmt_Folder.localInheritedAttributes[silver.modification.impide.Init.expectedType__ON__silver_modification_impide_makeIdeStmt_Folder] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.modification.impide.PmakeIdeStmt_Folder.occurs_local[silver.modification.impide.Init.expectedType__ON__silver_modification_impide_makeIdeStmt_Folder] = "silver:modification:impide:makeIdeStmt_Folder:local:expectedType";
		//	local attribute tc1::TypeCheck;
		silver.modification.impide.PmakeIdeStmt_Folder.localInheritedAttributes[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Folder] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.impide.PmakeIdeStmt_Folder.occurs_local[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Folder] = "silver:modification:impide:makeIdeStmt_Folder:local:tc1";
		silver.modification.impide.PnameIdeStmt.occurs_local[silver.modification.impide.Init.iName__ON__silver_modification_impide_nameIdeStmt] = "silver:modification:impide:nameIdeStmt:local:iName";
		silver.modification.impide.PversionIdeStmt.occurs_local[silver.modification.impide.Init.iV__ON__silver_modification_impide_versionIdeStmt] = "silver:modification:impide:versionIdeStmt:local:iV";
		silver.modification.impide.NStubGenerator.occurs_inh[silver.modification.impide.Init.silver_definition_env_env__ON__silver_modification_impide_StubGenerator] = "silver:definition:env:env";
		silver.modification.impide.NStubGenerator.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.impide.NStubGenerator.occurs_syn[silver.modification.impide.Init.silver_modification_impide_funcDcl__ON__silver_modification_impide_StubGenerator] = "silver:modification:impide:funcDcl";
		silver.modification.impide.NStubGenerator.occurs_syn[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_StubGenerator] = "silver:definition:core:errors";
		//	local attribute stubGenTypeExpected::Type;
		silver.modification.impide.PmakeStubGenerator.localInheritedAttributes[silver.modification.impide.Init.stubGenTypeExpected__ON__silver_modification_impide_makeStubGenerator] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.modification.impide.PmakeStubGenerator.occurs_local[silver.modification.impide.Init.stubGenTypeExpected__ON__silver_modification_impide_makeStubGenerator] = "silver:modification:impide:makeStubGenerator:local:stubGenTypeExpected";
		//	local attribute tc1::TypeCheck;
		silver.modification.impide.PmakeStubGenerator.localInheritedAttributes[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeStubGenerator] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.impide.PmakeStubGenerator.occurs_local[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeStubGenerator] = "silver:modification:impide:makeStubGenerator:local:tc1";
		silver.modification.impide.PisLegalVersion.occurs_local[silver.modification.impide.Init.parts__ON__silver_modification_impide_isLegalVersion] = "silver:modification:impide:isLegalVersion:local:parts";
		silver.driver.util.NRootSpec.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_driver_util_RootSpec] = "silver:modification:impide:ideSpecs";
		silver.definition.core.NRoot.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_Root] = "silver:modification:impide:ideSpecs";
		silver.definition.core.NAGDcls.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcls] = "silver:modification:impide:ideSpecs";
		silver.definition.core.NAGDcl.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcl] = "silver:modification:impide:ideSpecs";
		silver.definition.core.NGrammar.occurs_syn[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_Grammar] = "silver:modification:impide:ideSpecs";
		silver.definition.env.NDefs.occurs_syn[silver.modification.impide.Init.silver_modification_impide_fontDefList__ON__silver_definition_env_Defs] = "silver:modification:impide:fontDefList";
		silver.definition.env.NDef.occurs_syn[silver.modification.impide.Init.silver_modification_impide_fontDefList__ON__silver_definition_env_Def] = "silver:modification:impide:fontDefList";
		silver.definition.env.NEnv.occurs_syn[silver.modification.impide.Init.silver_modification_impide_fontDefTree__ON__silver_definition_env_Env] = "silver:modification:impide:fontDefTree";
		silver.definition.core.NQName.occurs_syn[silver.modification.impide.Init.silver_modification_impide_lookupFont__ON__silver_definition_core_QName] = "silver:modification:impide:lookupFont";
		silver.modification.impide.PgenerateNCS.occurs_local[silver.modification.impide.Init.io0__ON__silver_modification_impide_generateNCS] = "silver:modification:impide:generateNCS:local:io0";
		silver.modification.impide.PgenerateNCS.occurs_local[silver.modification.impide.Init.io1__ON__silver_modification_impide_generateNCS] = "silver:modification:impide:generateNCS:local:io1";
		silver.modification.impide.PgenerateNCS.occurs_local[silver.modification.impide.Init.io2__ON__silver_modification_impide_generateNCS] = "silver:modification:impide:generateNCS:local:io2";
		silver.modification.impide.PgenerateNCS.occurs_local[silver.modification.impide.Init.io3__ON__silver_modification_impide_generateNCS] = "silver:modification:impide:generateNCS:local:io3";
		silver.modification.impide.PfontDecl.occurs_local[silver.modification.impide.Init.fName__ON__silver_modification_impide_fontDecl] = "silver:modification:impide:fontDecl:local:fName";
		silver.modification.impide.NFontStyles.occurs_syn[silver.modification.impide.Init.silver_modification_impide_isBold__ON__silver_modification_impide_FontStyles] = "silver:modification:impide:isBold";
		silver.modification.impide.NFontStyles.occurs_syn[silver.modification.impide.Init.silver_modification_impide_isItalic__ON__silver_modification_impide_FontStyles] = "silver:modification:impide:isItalic";
		silver.modification.impide.NFontStyles.occurs_syn[silver.modification.impide.Init.silver_definition_env_pp__ON__silver_modification_impide_FontStyles] = "silver:definition:env:pp";
		silver.modification.impide.NFontStyle.occurs_syn[silver.modification.impide.Init.silver_definition_env_pp__ON__silver_modification_impide_FontStyle] = "silver:definition:env:pp";
		silver.modification.impide.NFontStyle.occurs_syn[silver.modification.impide.Init.silver_modification_impide_isBold__ON__silver_modification_impide_FontStyle] = "silver:modification:impide:isBold";
		silver.modification.impide.NFontStyle.occurs_syn[silver.modification.impide.Init.silver_modification_impide_isItalic__ON__silver_modification_impide_FontStyle] = "silver:modification:impide:isItalic";
		silver.driver.util.Pcompilation.occurs_local[silver.modification.impide.Init.builtGrammar__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:builtGrammar";
		//	local attribute ide::IdeSpec;
		silver.driver.util.Pcompilation.localInheritedAttributes[silver.modification.impide.Init.ide__ON__silver_driver_util_compilation] = new common.Lazy[silver.modification.impide.spec.NIdeSpec.num_inh_attrs];
		silver.driver.util.Pcompilation.occurs_local[silver.modification.impide.Init.ide__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:ide";
		silver.driver.util.Pcompilation.occurs_local[silver.modification.impide.Init.isIde__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:isIde";
		silver.driver.util.Pcompilation.occurs_local[silver.modification.impide.Init.pkgName__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:pkgName";
		silver.driver.util.Pcompilation.occurs_local[silver.modification.impide.Init.ideGenPath__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:ideGenPath";
		silver.modification.impide.NPropertyList.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_PropertyList] = "silver:modification:impide:propDcls";
		silver.modification.impide.NPropertyList.occurs_syn[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_PropertyList] = "silver:definition:core:errors";
		silver.modification.impide.NProperty.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_Property] = "silver:modification:impide:propDcls";
		silver.modification.impide.NProperty.occurs_syn[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property] = "silver:definition:core:errors";
		silver.modification.impide.PmakeProperty.occurs_local[silver.modification.impide.Init.optional__ON__silver_modification_impide_makeProperty] = "silver:modification:impide:makeProperty:local:optional";
		silver.modification.impide.PmakeProperty.occurs_local[silver.modification.impide.Init.defaultVal__ON__silver_modification_impide_makeProperty] = "silver:modification:impide:makeProperty:local:defaultVal";
		silver.modification.impide.PmakeProperty.occurs_local[silver.modification.impide.Init.displayName__ON__silver_modification_impide_makeProperty] = "silver:modification:impide:makeProperty:local:displayName";
		silver.modification.impide.NTypeName.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propType__ON__silver_modification_impide_TypeName] = "silver:modification:impide:propType";
		silver.modification.impide.NIdePropertyOption.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propRequired__ON__silver_modification_impide_IdePropertyOption] = "silver:modification:impide:propRequired";
		silver.modification.impide.NIdePropertyOption.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOption] = "silver:modification:impide:propDefault";
		silver.modification.impide.NIdePropertyOption.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propDisplay__ON__silver_modification_impide_IdePropertyOption] = "silver:modification:impide:propDisplay";
		silver.modification.impide.NIdePropertyOptions.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propRequired__ON__silver_modification_impide_IdePropertyOptions] = "silver:modification:impide:propRequired";
		silver.modification.impide.NIdePropertyOptions.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOptions] = "silver:modification:impide:propDefault";
		silver.modification.impide.NIdePropertyOptions.occurs_syn[silver.modification.impide.Init.silver_modification_impide_propDisplay__ON__silver_modification_impide_IdePropertyOptions] = "silver:modification:impide:propDisplay";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.impide.PideDcl.initProductionAttributeDefinitions();
		//FUNCTION deriveLangNameFromGrammar String ::= gram::String 
		silver.modification.impide.PemptyIdeStmts.initProductionAttributeDefinitions();
		silver.modification.impide.PlistIdeStmts.initProductionAttributeDefinitions();
		silver.modification.impide.PlistIdeStmts2.initProductionAttributeDefinitions();
		silver.modification.impide.PnilIdeStmtList.initProductionAttributeDefinitions();
		silver.modification.impide.PconsIdeStmtList.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for IdeStmt
		// top.ideFunctions = []
		silver.modification.impide.NIdeStmt.defaultSynthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.propDcls = []
		silver.modification.impide.NIdeStmt.defaultSynthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.wizards = []
		silver.modification.impide.NIdeStmt.defaultSynthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.ideNames = []
		silver.modification.impide.NIdeStmt.defaultSynthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.ideVersions = []
		silver.modification.impide.NIdeStmt.defaultSynthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.ideResources = []
		silver.modification.impide.NIdeStmt.defaultSynthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		silver.modification.impide.PmakeIdeStmt_Builder.initProductionAttributeDefinitions();
		silver.modification.impide.PmakeIdeStmt_PostBuilder.initProductionAttributeDefinitions();
		silver.modification.impide.PmakeIdeStmt_Exporter.initProductionAttributeDefinitions();
		silver.modification.impide.PmakeIdeStmt_Folder.initProductionAttributeDefinitions();
		silver.modification.impide.PmakeIdeStmt_Porperty.initProductionAttributeDefinitions();
		silver.modification.impide.PnameIdeStmt.initProductionAttributeDefinitions();
		silver.modification.impide.PversionIdeStmt.initProductionAttributeDefinitions();
		silver.modification.impide.PresourceIdeStmt.initProductionAttributeDefinitions();
		silver.modification.impide.PnewfileWizard_c.initProductionAttributeDefinitions();
		silver.modification.impide.PmakeStubGenerator.initProductionAttributeDefinitions();
		//FUNCTION isLegalVersion Boolean ::= ver::String 
		// parts = explode(".", ver)
		silver.modification.impide.PisLegalVersion.localAttributes[silver.modification.impide.Init.parts__ON__silver_modification_impide_isLegalVersion] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pexplode.invoke((new common.StringCatter(".")), context.childAsIsLazy(silver.modification.impide.PisLegalVersion.i_ver))); } };
		//FUNCTION isAllDigital Boolean ::= parts::[String] 
		//ASPECT PRODUCTION root top ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls 
		// top.ideSpecs = ags.ideSpecs
		silver.definition.core.Proot.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.Proot.i_ags).synthesized(silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcls)); } };
		//ASPECT PRODUCTION nilAGDcls top ::= 
		// top.ideSpecs = []
		silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION consAGDcls top ::= h::AGDcl t::AGDcls 
		// top.ideSpecs = h.ideSpecs ++ t.ideSpecs
		silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_h, silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_t, silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcls))); } };
		//ASPECT DEFAULT PRODUCTION for AGDcl
		// top.ideSpecs = []
		silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION appendAGDcl top ::= ag1::AGDcl ag2::AGDcl 
		// top.ideSpecs = ag1.ideSpecs ++ ag2.ideSpecs
		silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_h, silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_t, silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcl))); } };
		//ASPECT PRODUCTION grammarRootSpec top ::= g::Grammar _ _ _ 
		// top.ideSpecs = g.ideSpecs
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_Grammar)); } };
		//ASPECT PRODUCTION interfaceRootSpec top ::= _ _ 
		// top.ideSpecs = []
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION errorRootSpec top ::= _ _ _ _ 
		// top.ideSpecs = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION nilGrammar top ::= 
		// top.ideSpecs = []
		silver.definition.core.PnilGrammar.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION consGrammar top ::= h::Root t::Grammar 
		// top.ideSpecs = h.ideSpecs ++ t.ideSpecs
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_Grammar))); } };
		silver.modification.impide.PfontDcl.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION nilDefs top ::= 
		// top.fontDefList = []
		silver.definition.env.PnilDefs.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_fontDefList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION consDefs top ::= e1::Def e2::Defs 
		// top.fontDefList = e1.fontDefList ++ e2.fontDefList
		silver.definition.env.PconsDefs.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_fontDefList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e1, silver.modification.impide.Init.silver_modification_impide_fontDefList__ON__silver_definition_env_Def), context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e2, silver.modification.impide.Init.silver_modification_impide_fontDefList__ON__silver_definition_env_Defs))); } };
		//ASPECT DEFAULT PRODUCTION for Def
		// top.fontDefList = []
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_fontDefList__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		silver.modification.impide.PfontStyleDef.initProductionAttributeDefinitions();
		//FUNCTION fontDef Def ::= sg::String sl::Location fn::String 
		//ASPECT PRODUCTION i_emptyEnv top ::= 
		// top.fontDefTree = emptyEnvScope()
		silver.definition.env.Pi_emptyEnv.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_fontDefTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PemptyEnvScope.invoke()); } };
		//ASPECT PRODUCTION i_appendEnv top ::= e1::Decorated Env e2::Decorated Env 
		// top.fontDefTree = appendEnvScope(e1.fontDefTree, e2.fontDefTree)
		silver.definition.env.Pi_appendEnv.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_fontDefTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PappendEnvScope.invoke(context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e1, silver.modification.impide.Init.silver_modification_impide_fontDefTree__ON__silver_definition_env_Env), context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e2, silver.modification.impide.Init.silver_modification_impide_fontDefTree__ON__silver_definition_env_Env))); } };
		//ASPECT PRODUCTION i_newScopeEnv top ::= d::Defs e::Decorated Env 
		// top.fontDefTree = consEnvScope(buildTree(d.fontDefList), e.fontDefTree)
		silver.definition.env.Pi_newScopeEnv.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_fontDefTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PconsEnvScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PbuildTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_d, silver.modification.impide.Init.silver_modification_impide_fontDefList__ON__silver_definition_env_Defs))); } }, context.childAsIsSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_e, silver.modification.impide.Init.silver_modification_impide_fontDefTree__ON__silver_definition_env_Env))); } };
		//FUNCTION getFontDcl [DclInfo] ::= search::String e::Decorated Env 
		//ASPECT PRODUCTION qNameId top ::= id::Name 
		// top.lookupFont = decorate customLookup("font style", getFontDcl(top.name, top.env), top.name, top.location) with {}
		silver.definition.core.PqNameId.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_lookupFont__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("font style")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.modification.impide.PgetFontDcl.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName))); } }, context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.undecorate()).getAnno_core_location()); } })).decorate(context, (common.Lazy[])null); } };
		//ASPECT PRODUCTION qNameCons top ::= id::Name ':' qn::QName 
		// top.lookupFont = decorate customLookup("font style", getFontDcl(top.name, top.env), top.name, top.location) with {}
		silver.definition.core.PqNameCons.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_lookupFont__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("font style")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.modification.impide.PgetFontDcl.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName))); } }, context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.undecorate()).getAnno_core_location()); } })).decorate(context, (common.Lazy[])null); } };
		silver.modification.impide.PgenerateNCS.initProductionAttributeDefinitions();
		//FUNCTION mkdirs IO ::= path::String paths::[String] i::IO 
		silver.modification.impide.PfontDecl.initProductionAttributeDefinitions();
		silver.modification.impide.PconsFontStylesDcl.initProductionAttributeDefinitions();
		silver.modification.impide.PnilFontStylesDcl.initProductionAttributeDefinitions();
		silver.modification.impide.PfontStyleBoldDcl.initProductionAttributeDefinitions();
		silver.modification.impide.PfontStyleItalicDcl.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION compilation top ::= g::Grammars _ buildGrammar::String benv::BuildEnv 
		// builtGrammar = searchEnvTree(buildGrammar, g.compiledGrammars)
		silver.driver.util.Pcompilation.localAttributes[silver.modification.impide.Init.builtGrammar__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childAsIsLazy(silver.driver.util.Pcompilation.i_buildGrammar), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.driver.util.Pcompilation.i_g).inherited(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_Grammars)); } })); } };
		// ide = head(head(builtGrammar).ideSpecs)
		silver.driver.util.Pcompilation.localAttributes[silver.modification.impide.Init.ide__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.modification.impide.spec.NIdeSpec)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.modification.impide.Init.builtGrammar__ON__silver_driver_util_compilation))).synthesized(silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_driver_util_RootSpec)); } })); } };
		// isIde = ! null(builtGrammar) && ! null(head(builtGrammar).ideSpecs)
		silver.driver.util.Pcompilation.localAttributes[silver.modification.impide.Init.isIde__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.modification.impide.Init.builtGrammar__ON__silver_driver_util_compilation)))) && (!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.modification.impide.Init.builtGrammar__ON__silver_driver_util_compilation))).synthesized(silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_driver_util_RootSpec)); } })))); } };
		// pkgName = makeName(ide.pluginGrammar)
		silver.driver.util.Pcompilation.localAttributes[silver.modification.impide.Init.pkgName__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PmakeName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.modification.impide.Init.ide__ON__silver_driver_util_compilation).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginGrammar__ON__silver_modification_impide_spec_IdeSpec)); } })); } };
		// ideGenPath = benv.silverGen ++ "ide/" ++ pkgName
		silver.driver.util.Pcompilation.localAttributes[silver.modification.impide.Init.ideGenPath__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.driver.util.Pcompilation.i_benv).synthesized(silver.driver.util.Init.silver_driver_util_silverGen__ON__silver_driver_util_BuildEnv)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("ide/")), (common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.impide.Init.pkgName__ON__silver_driver_util_compilation)))); } };
		// top.postOps <- if ! isIde then [] else [ generateNCS(g.compiledGrammars, ide, ideGenPath, pkgName) ]
		if(silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] == null)
			silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] = new silver.driver.util.CApostOps(silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation);
		((common.CollectionAttribute)silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.localAsIs(silver.modification.impide.Init.isIde__ON__silver_driver_util_compilation))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.driver.util.NDriverAction)new silver.modification.impide.PgenerateNCS(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.driver.util.Pcompilation.i_g).inherited(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_Grammars)); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.impide.Init.ide__ON__silver_driver_util_compilation)), context.localAsIsLazy(silver.modification.impide.Init.ideGenPath__ON__silver_driver_util_compilation), context.localAsIsLazy(silver.modification.impide.Init.pkgName__ON__silver_driver_util_compilation))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// classpathCompiler <- if ! isIde then [] else [ "${sh}/jars/IDEPluginRuntime.jar" ]
		((common.CollectionAttribute)silver.driver.util.Pcompilation.localAttributes[silver.translation.java.driver.Init.classpathCompiler__ON__silver_driver_util_compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.localAsIs(silver.modification.impide.Init.isIde__ON__silver_driver_util_compilation))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("${sh}/jars/IDEPluginRuntime.jar")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// extraTopLevelDecls <- if ! isIde then [] else [ "\n    <property name='grammar.path' value='" ++ head(builtGrammar).grammarSource ++ "'/>\n    <property name='res' value='${sh}/resources'/>\n    <property name='ide.version' value='" ++ ide.ideVersion ++ "'/>\n    <property name='lang.name' value='" ++ ide.ideName ++ "'/>\n    <property name='lang.composed' value='" ++ pkgName ++ "'/>\n    <property name='ide.pkg.name' value='" ++ pkgName ++ "'/>\n    <property name='ide.proj.parent.path' location='${jg}/ide/${ide.pkg.name}'/>\n    <property name='ide.proj.plugin.path' location='${ide.proj.parent.path}/plugin'/>\n    <property name='ide.pkg.path' location='${ide.proj.plugin.path}/src/" ++ pkgToPath(pkgName) ++ "'/>\n    <property name='ide.parser.classname' value='" ++ ide.pluginParserClass ++ "' />\n    <target name='ide' depends='jars'>\n      <tstamp>\n        <format property='ide.build-timestamp' pattern='yyMMddHHmmss' timezone='UTC'/>\n      </tstamp>\n      <filter token=\"GROUP_ID\" value='${ide.pkg.name}'/>\n      <filter token=\"PKG_NAME\" value='${ide.pkg.name}'/>\n      <filter token=\"LANG_NAME\" value='${lang.name}'/>\n      <filter token=\"IDE_VERSION\" value='${ide.version}'/>\n      <filter token=\"IDE_BUILD_TIMESTAMP\" value='${ide.build-timestamp}'/>\n      <filter token=\"LANG_COMPOSED\" value='${lang.composed}'/>\n      <filter token=\"FEATURE_DESCRIPTION_URL\" value='http://some.user.provided.url'/>\n      <filter token=\"FEATURE_DESCRIPTION_TEXT\" value='no description of the software'/>\n      <filter token=\"FEATURE_COPYRIGHT_URL\" value='http://some.user.provided.url'/>\n      <filter token=\"FEATURE_COPYRIGHT_TEXT\" value='no copyright information available'/>\n      <filter token=\"FEATURE_LICENSE_URL\" value='http://some.user.provided.url'/>\n      <filter token=\"FEATURE_LICENSE_TEXT\" value='no license information available'/>\n\n    <!-- 1. create project structure -->\n      <mkdir dir='${ide.proj.plugin.path}/resource/'/>\n      <mkdir dir='${ide.pkg.path}/'/>\n      <copy todir=\"${ide.proj.parent.path}\" overwrite=\"false\" filtering=\"true\">\n        <fileset dir=\"${res}/ide_skeleton\"/>\n      </copy>\n\n    <!-- 5. plugin dependencies -->\n      <copy file=\"${lang.composed}.jar\" tofile=\"${ide.proj.plugin.path}/${lang.composed}.jar\"/>\n      <copy file=\"${sh}/jars/CopperRuntime.jar\" tofile=\"${ide.proj.plugin.path}/CopperRuntime.jar\"/>\n      <copy file=\"${sh}/jars/SilverRuntime.jar\" tofile=\"${ide.proj.plugin.path}/SilverRuntime.jar\"/>\n      <copy file=\"${sh}/jars/IDEPluginRuntime.jar\" tofile=\"${ide.proj.plugin.path}/IDEPluginRuntime.jar\"/>\n    <!-- 10. Ide resources -->\n" ++ implode("", map(copyIdeResource, ide.ideResources)) ++ "\n    </target>\n" ]
		((common.CollectionAttribute)silver.driver.util.Pcompilation.localAttributes[silver.translation.java.driver.Init.extraTopLevelDecls__ON__silver_driver_util_compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.localAsIs(silver.modification.impide.Init.isIde__ON__silver_driver_util_compilation))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n    <property name='grammar.path' value='")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.modification.impide.Init.builtGrammar__ON__silver_driver_util_compilation))).synthesized(silver.driver.util.Init.silver_definition_env_grammarSource__ON__silver_driver_util_RootSpec)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'/>\n    <property name='res' value='${sh}/resources'/>\n    <property name='ide.version' value='")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.impide.Init.ide__ON__silver_driver_util_compilation).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_ideVersion__ON__silver_modification_impide_spec_IdeSpec)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'/>\n    <property name='lang.name' value='")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.impide.Init.ide__ON__silver_driver_util_compilation).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_ideName__ON__silver_modification_impide_spec_IdeSpec)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'/>\n    <property name='lang.composed' value='")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.impide.Init.pkgName__ON__silver_driver_util_compilation)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'/>\n    <property name='ide.pkg.name' value='")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.impide.Init.pkgName__ON__silver_driver_util_compilation)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'/>\n    <property name='ide.proj.parent.path' location='${jg}/ide/${ide.pkg.name}'/>\n    <property name='ide.proj.plugin.path' location='${ide.proj.parent.path}/plugin'/>\n    <property name='ide.pkg.path' location='${ide.proj.plugin.path}/src/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.impide.PpkgToPath.invoke(context.localAsIsLazy(silver.modification.impide.Init.pkgName__ON__silver_driver_util_compilation))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'/>\n    <property name='ide.parser.classname' value='")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.impide.Init.ide__ON__silver_driver_util_compilation).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginParserClass__ON__silver_modification_impide_spec_IdeSpec)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("' />\n    <target name='ide' depends='jars'>\n      <tstamp>\n        <format property='ide.build-timestamp' pattern='yyMMddHHmmss' timezone='UTC'/>\n      </tstamp>\n      <filter token=\"GROUP_ID\" value='${ide.pkg.name}'/>\n      <filter token=\"PKG_NAME\" value='${ide.pkg.name}'/>\n      <filter token=\"LANG_NAME\" value='${lang.name}'/>\n      <filter token=\"IDE_VERSION\" value='${ide.version}'/>\n      <filter token=\"IDE_BUILD_TIMESTAMP\" value='${ide.build-timestamp}'/>\n      <filter token=\"LANG_COMPOSED\" value='${lang.composed}'/>\n      <filter token=\"FEATURE_DESCRIPTION_URL\" value='http://some.user.provided.url'/>\n      <filter token=\"FEATURE_DESCRIPTION_TEXT\" value='no description of the software'/>\n      <filter token=\"FEATURE_COPYRIGHT_URL\" value='http://some.user.provided.url'/>\n      <filter token=\"FEATURE_COPYRIGHT_TEXT\" value='no copyright information available'/>\n      <filter token=\"FEATURE_LICENSE_URL\" value='http://some.user.provided.url'/>\n      <filter token=\"FEATURE_LICENSE_TEXT\" value='no license information available'/>\n\n    <!-- 1. create project structure -->\n      <mkdir dir='${ide.proj.plugin.path}/resource/'/>\n      <mkdir dir='${ide.pkg.path}/'/>\n      <copy todir=\"${ide.proj.parent.path}\" overwrite=\"false\" filtering=\"true\">\n        <fileset dir=\"${res}/ide_skeleton\"/>\n      </copy>\n\n    <!-- 5. plugin dependencies -->\n      <copy file=\"${lang.composed}.jar\" tofile=\"${ide.proj.plugin.path}/${lang.composed}.jar\"/>\n      <copy file=\"${sh}/jars/CopperRuntime.jar\" tofile=\"${ide.proj.plugin.path}/CopperRuntime.jar\"/>\n      <copy file=\"${sh}/jars/SilverRuntime.jar\" tofile=\"${ide.proj.plugin.path}/SilverRuntime.jar\"/>\n      <copy file=\"${sh}/jars/IDEPluginRuntime.jar\" tofile=\"${ide.proj.plugin.path}/IDEPluginRuntime.jar\"/>\n    <!-- 10. Ide resources -->\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.modification.impide.PcopyIdeResource.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.impide.Init.ide__ON__silver_driver_util_compilation).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_spec_IdeSpec)); } })); } })), (common.StringCatter)(new common.StringCatter("\n    </target>\n")))))))))))))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// extraDistDeps <- if ! isIde then [] else [ "ide" ]
		((common.CollectionAttribute)silver.driver.util.Pcompilation.localAttributes[silver.translation.java.driver.Init.extraDistDeps__ON__silver_driver_util_compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.localAsIs(silver.modification.impide.Init.isIde__ON__silver_driver_util_compilation))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("ide")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		//FUNCTION pkgToPath String ::= pkg::String 
		//FUNCTION copyIdeResource String ::= r::Pair<String String> 
		silver.modification.impide.PnilPropertyList.initProductionAttributeDefinitions();
		silver.modification.impide.PconsPropertyList.initProductionAttributeDefinitions();
		silver.modification.impide.PmakeProperty.initProductionAttributeDefinitions();
		silver.modification.impide.PpropType_String.initProductionAttributeDefinitions();
		silver.modification.impide.PpropType_Integer.initProductionAttributeDefinitions();
		silver.modification.impide.PpropType_Path.initProductionAttributeDefinitions();
		silver.modification.impide.PpropType_URL.initProductionAttributeDefinitions();
		silver.modification.impide.PnilPropertyOptions.initProductionAttributeDefinitions();
		silver.modification.impide.PconsPropertyOptions.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for IdePropertyOption
		// top.propRequired = []
		silver.modification.impide.NIdePropertyOption.defaultSynthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propRequired__ON__silver_modification_impide_IdePropertyOption] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.propDefault = []
		silver.modification.impide.NIdePropertyOption.defaultSynthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOption] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.propDisplay = []
		silver.modification.impide.NIdePropertyOption.defaultSynthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDisplay__ON__silver_modification_impide_IdePropertyOption] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		silver.modification.impide.PidePropertyOption_optional.initProductionAttributeDefinitions();
		silver.modification.impide.PidePropertyOption_defaultVal.initProductionAttributeDefinitions();
		silver.modification.impide.PidePropertyOption_displayName.initProductionAttributeDefinitions();
		silver.modification.impide.PterminalModifierFont.initProductionAttributeDefinitions();
		silver.modification.impide.PlexerClassModifierFont.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_modification_impide_ideDcl = 0;
	public static int count_local__ON__silver_modification_impide_deriveLangNameFromGrammar = 0;
	public static int count_inh__ON__IdeStmts = 0;
	public static int count_syn__ON__IdeStmts = 0;
	public static int count_inh__ON__IdeStmt = 0;
	public static int count_syn__ON__IdeStmt = 0;
	public static int count_inh__ON__IdeStmtList = 0;
	public static int count_syn__ON__IdeStmtList = 0;
	public static int count_local__ON__silver_modification_impide_emptyIdeStmts = 0;
	public static int count_local__ON__silver_modification_impide_listIdeStmts = 0;
	public static int count_local__ON__silver_modification_impide_listIdeStmts2 = 0;
	public static int count_local__ON__silver_modification_impide_nilIdeStmtList = 0;
	public static int count_local__ON__silver_modification_impide_consIdeStmtList = 0;
	public static int count_local__ON__silver_modification_impide_makeIdeStmt_Builder = 0;
	public static int count_local__ON__silver_modification_impide_makeIdeStmt_PostBuilder = 0;
	public static int count_local__ON__silver_modification_impide_makeIdeStmt_Exporter = 0;
	public static int count_local__ON__silver_modification_impide_makeIdeStmt_Folder = 0;
	public static int count_local__ON__silver_modification_impide_makeIdeStmt_Porperty = 0;
	public static int count_local__ON__silver_modification_impide_nameIdeStmt = 0;
	public static int count_local__ON__silver_modification_impide_versionIdeStmt = 0;
	public static int count_local__ON__silver_modification_impide_resourceIdeStmt = 0;
	public static int count_local__ON__silver_modification_impide_newfileWizard_c = 0;
	public static int count_inh__ON__StubGenerator = 0;
	public static int count_syn__ON__StubGenerator = 0;
	public static int count_local__ON__silver_modification_impide_makeStubGenerator = 0;
	public static int count_local__ON__silver_modification_impide_isLegalVersion = 0;
	public static int count_local__ON__silver_modification_impide_isAllDigital = 0;
	public static int count_local__ON__silver_modification_impide_fontDcl = 0;
	public static int count_local__ON__silver_modification_impide_fontStyleDef = 0;
	public static int count_local__ON__silver_modification_impide_fontDef = 0;
	public static int count_local__ON__silver_modification_impide_getFontDcl = 0;
	public static int count_local__ON__silver_modification_impide_generateNCS = 0;
	public static int count_local__ON__silver_modification_impide_mkdirs = 0;
	public static int count_local__ON__silver_modification_impide_fontDecl = 0;
	public static int count_inh__ON__FontStyles = 0;
	public static int count_syn__ON__FontStyles = 0;
	public static int count_local__ON__silver_modification_impide_consFontStylesDcl = 0;
	public static int count_local__ON__silver_modification_impide_nilFontStylesDcl = 0;
	public static int count_inh__ON__FontStyle = 0;
	public static int count_syn__ON__FontStyle = 0;
	public static int count_local__ON__silver_modification_impide_fontStyleBoldDcl = 0;
	public static int count_local__ON__silver_modification_impide_fontStyleItalicDcl = 0;
	public static int count_local__ON__silver_modification_impide_toUpperCase = 0;
	public static int count_local__ON__silver_modification_impide_pkgToPath = 0;
	public static int count_local__ON__silver_modification_impide_copyIdeResource = 0;
	public static int count_inh__ON__PropertyList = 0;
	public static int count_syn__ON__PropertyList = 0;
	public static int count_inh__ON__Property = 0;
	public static int count_syn__ON__Property = 0;
	public static int count_local__ON__silver_modification_impide_nilPropertyList = 0;
	public static int count_local__ON__silver_modification_impide_consPropertyList = 0;
	public static int count_local__ON__silver_modification_impide_makeProperty = 0;
	public static int count_inh__ON__TypeName = 0;
	public static int count_syn__ON__TypeName = 0;
	public static int count_local__ON__silver_modification_impide_propType_String = 0;
	public static int count_local__ON__silver_modification_impide_propType_Integer = 0;
	public static int count_local__ON__silver_modification_impide_propType_Path = 0;
	public static int count_local__ON__silver_modification_impide_propType_URL = 0;
	public static int count_inh__ON__IdePropertyOption = 0;
	public static int count_syn__ON__IdePropertyOption = 0;
	public static int count_inh__ON__IdePropertyOptions = 0;
	public static int count_syn__ON__IdePropertyOptions = 0;
	public static int count_local__ON__silver_modification_impide_nilPropertyOptions = 0;
	public static int count_local__ON__silver_modification_impide_consPropertyOptions = 0;
	public static int count_local__ON__silver_modification_impide_idePropertyOption_optional = 0;
	public static int count_local__ON__silver_modification_impide_idePropertyOption_defaultVal = 0;
	public static int count_local__ON__silver_modification_impide_idePropertyOption_displayName = 0;
	public static int count_local__ON__silver_modification_impide_terminalModifierFont = 0;
	public static int count_local__ON__silver_modification_impide_lexerClassModifierFont = 0;
public static final int parsergrammar__ON__silver_modification_impide_ideDcl = silver.modification.impide.Init.count_local__ON__silver_modification_impide_ideDcl++;
public static final int spec__ON__silver_modification_impide_ideDcl = silver.modification.impide.Init.count_local__ON__silver_modification_impide_ideDcl++;
public static final int fext__ON__silver_modification_impide_ideDcl = silver.modification.impide.Init.count_local__ON__silver_modification_impide_ideDcl++;
public static final int ideName__ON__silver_modification_impide_ideDcl = silver.modification.impide.Init.count_local__ON__silver_modification_impide_ideDcl++;
public static final int ideVersion__ON__silver_modification_impide_ideDcl = silver.modification.impide.Init.count_local__ON__silver_modification_impide_ideDcl++;
public static final int silver_definition_env_env__ON__silver_modification_impide_IdeStmts = silver.modification.impide.Init.count_inh__ON__IdeStmts++;
public static final int silver_definition_core_errors__ON__silver_modification_impide_IdeStmts = silver.modification.impide.Init.count_syn__ON__IdeStmts++;
public static final int silver_definition_core_grammarName__ON__silver_modification_impide_IdeStmts = silver.modification.impide.Init.count_inh__ON__IdeStmts++;
public static final int silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmts = silver.modification.impide.Init.count_syn__ON__IdeStmts++;
public static final int silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmts = silver.modification.impide.Init.count_syn__ON__IdeStmts++;
public static final int silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmts = silver.modification.impide.Init.count_syn__ON__IdeStmts++;
public static final int silver_modification_impide_startNTName__ON__silver_modification_impide_IdeStmts = silver.modification.impide.Init.count_inh__ON__IdeStmts++;
public static final int silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmts = silver.modification.impide.Init.count_syn__ON__IdeStmts++;
public static final int silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmts = silver.modification.impide.Init.count_syn__ON__IdeStmts++;
public static final int silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmts = silver.modification.impide.Init.count_syn__ON__IdeStmts++;
public static final int silver_definition_env_env__ON__silver_modification_impide_IdeStmt = silver.modification.impide.Init.count_inh__ON__IdeStmt++;
public static final int silver_definition_core_errors__ON__silver_modification_impide_IdeStmt = silver.modification.impide.Init.count_syn__ON__IdeStmt++;
public static final int silver_definition_core_grammarName__ON__silver_modification_impide_IdeStmt = silver.modification.impide.Init.count_inh__ON__IdeStmt++;
public static final int silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmt = silver.modification.impide.Init.count_syn__ON__IdeStmt++;
public static final int silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmt = silver.modification.impide.Init.count_syn__ON__IdeStmt++;
public static final int silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmt = silver.modification.impide.Init.count_syn__ON__IdeStmt++;
public static final int silver_modification_impide_startNTName__ON__silver_modification_impide_IdeStmt = silver.modification.impide.Init.count_inh__ON__IdeStmt++;
public static final int silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmt = silver.modification.impide.Init.count_syn__ON__IdeStmt++;
public static final int silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmt = silver.modification.impide.Init.count_syn__ON__IdeStmt++;
public static final int silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmt = silver.modification.impide.Init.count_syn__ON__IdeStmt++;
public static final int silver_definition_env_env__ON__silver_modification_impide_IdeStmtList = silver.modification.impide.Init.count_inh__ON__IdeStmtList++;
public static final int silver_definition_core_errors__ON__silver_modification_impide_IdeStmtList = silver.modification.impide.Init.count_syn__ON__IdeStmtList++;
public static final int silver_definition_core_grammarName__ON__silver_modification_impide_IdeStmtList = silver.modification.impide.Init.count_inh__ON__IdeStmtList++;
public static final int silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmtList = silver.modification.impide.Init.count_syn__ON__IdeStmtList++;
public static final int silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmtList = silver.modification.impide.Init.count_syn__ON__IdeStmtList++;
public static final int silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmtList = silver.modification.impide.Init.count_syn__ON__IdeStmtList++;
public static final int silver_modification_impide_startNTName__ON__silver_modification_impide_IdeStmtList = silver.modification.impide.Init.count_inh__ON__IdeStmtList++;
public static final int silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmtList = silver.modification.impide.Init.count_syn__ON__IdeStmtList++;
public static final int silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmtList = silver.modification.impide.Init.count_syn__ON__IdeStmtList++;
public static final int silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmtList = silver.modification.impide.Init.count_syn__ON__IdeStmtList++;
public static final int expectedType__ON__silver_modification_impide_makeIdeStmt_Builder = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeIdeStmt_Builder++;
public static final int tc1__ON__silver_modification_impide_makeIdeStmt_Builder = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeIdeStmt_Builder++;
public static final int expectedType__ON__silver_modification_impide_makeIdeStmt_PostBuilder = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeIdeStmt_PostBuilder++;
public static final int tc1__ON__silver_modification_impide_makeIdeStmt_PostBuilder = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeIdeStmt_PostBuilder++;
public static final int expectedType__ON__silver_modification_impide_makeIdeStmt_Exporter = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeIdeStmt_Exporter++;
public static final int tc1__ON__silver_modification_impide_makeIdeStmt_Exporter = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeIdeStmt_Exporter++;
public static final int expectedType__ON__silver_modification_impide_makeIdeStmt_Folder = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeIdeStmt_Folder++;
public static final int tc1__ON__silver_modification_impide_makeIdeStmt_Folder = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeIdeStmt_Folder++;
public static final int iName__ON__silver_modification_impide_nameIdeStmt = silver.modification.impide.Init.count_local__ON__silver_modification_impide_nameIdeStmt++;
public static final int iV__ON__silver_modification_impide_versionIdeStmt = silver.modification.impide.Init.count_local__ON__silver_modification_impide_versionIdeStmt++;
public static final int silver_definition_env_env__ON__silver_modification_impide_StubGenerator = silver.modification.impide.Init.count_inh__ON__StubGenerator++;
public static final int silver_modification_impide_funcDcl__ON__silver_modification_impide_StubGenerator = silver.modification.impide.Init.count_syn__ON__StubGenerator++;
public static final int silver_definition_core_errors__ON__silver_modification_impide_StubGenerator = silver.modification.impide.Init.count_syn__ON__StubGenerator++;
public static final int stubGenTypeExpected__ON__silver_modification_impide_makeStubGenerator = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeStubGenerator++;
public static final int tc1__ON__silver_modification_impide_makeStubGenerator = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeStubGenerator++;
public static final int parts__ON__silver_modification_impide_isLegalVersion = silver.modification.impide.Init.count_local__ON__silver_modification_impide_isLegalVersion++;
public static final int silver_modification_impide_ideSpecs__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_modification_impide_ideSpecs__ON__silver_definition_core_Root = silver.definition.core.Init.count_syn__ON__Root++;
public static final int silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcls = silver.definition.core.Init.count_syn__ON__AGDcls++;
public static final int silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_syn__ON__AGDcl++;
public static final int silver_modification_impide_ideSpecs__ON__silver_definition_core_Grammar = silver.definition.core.Init.count_syn__ON__Grammar++;
public static final int silver_modification_impide_fontDefList__ON__silver_definition_env_Defs = silver.definition.env.Init.count_syn__ON__Defs++;
public static final int silver_modification_impide_fontDefList__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_modification_impide_fontDefTree__ON__silver_definition_env_Env = silver.definition.env.Init.count_syn__ON__Env++;
public static final int silver_modification_impide_lookupFont__ON__silver_definition_core_QName = silver.definition.core.Init.count_syn__ON__QName++;
public static final int io0__ON__silver_modification_impide_generateNCS = silver.modification.impide.Init.count_local__ON__silver_modification_impide_generateNCS++;
public static final int io1__ON__silver_modification_impide_generateNCS = silver.modification.impide.Init.count_local__ON__silver_modification_impide_generateNCS++;
public static final int io2__ON__silver_modification_impide_generateNCS = silver.modification.impide.Init.count_local__ON__silver_modification_impide_generateNCS++;
public static final int io3__ON__silver_modification_impide_generateNCS = silver.modification.impide.Init.count_local__ON__silver_modification_impide_generateNCS++;
public static final int fName__ON__silver_modification_impide_fontDecl = silver.modification.impide.Init.count_local__ON__silver_modification_impide_fontDecl++;
public static final int silver_modification_impide_isBold__ON__silver_modification_impide_FontStyles = silver.modification.impide.Init.count_syn__ON__FontStyles++;
public static final int silver_modification_impide_isItalic__ON__silver_modification_impide_FontStyles = silver.modification.impide.Init.count_syn__ON__FontStyles++;
public static final int silver_definition_env_pp__ON__silver_modification_impide_FontStyles = silver.modification.impide.Init.count_syn__ON__FontStyles++;
public static final int silver_definition_env_pp__ON__silver_modification_impide_FontStyle = silver.modification.impide.Init.count_syn__ON__FontStyle++;
public static final int silver_modification_impide_isBold__ON__silver_modification_impide_FontStyle = silver.modification.impide.Init.count_syn__ON__FontStyle++;
public static final int silver_modification_impide_isItalic__ON__silver_modification_impide_FontStyle = silver.modification.impide.Init.count_syn__ON__FontStyle++;
public static final int builtGrammar__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int ide__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int isIde__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int pkgName__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int ideGenPath__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int silver_modification_impide_propDcls__ON__silver_modification_impide_PropertyList = silver.modification.impide.Init.count_syn__ON__PropertyList++;
public static final int silver_definition_core_errors__ON__silver_modification_impide_PropertyList = silver.modification.impide.Init.count_syn__ON__PropertyList++;
public static final int silver_modification_impide_propDcls__ON__silver_modification_impide_Property = silver.modification.impide.Init.count_syn__ON__Property++;
public static final int silver_definition_core_errors__ON__silver_modification_impide_Property = silver.modification.impide.Init.count_syn__ON__Property++;
public static final int optional__ON__silver_modification_impide_makeProperty = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeProperty++;
public static final int defaultVal__ON__silver_modification_impide_makeProperty = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeProperty++;
public static final int displayName__ON__silver_modification_impide_makeProperty = silver.modification.impide.Init.count_local__ON__silver_modification_impide_makeProperty++;
public static final int silver_modification_impide_propType__ON__silver_modification_impide_TypeName = silver.modification.impide.Init.count_syn__ON__TypeName++;
public static final int silver_modification_impide_propRequired__ON__silver_modification_impide_IdePropertyOption = silver.modification.impide.Init.count_syn__ON__IdePropertyOption++;
public static final int silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOption = silver.modification.impide.Init.count_syn__ON__IdePropertyOption++;
public static final int silver_modification_impide_propDisplay__ON__silver_modification_impide_IdePropertyOption = silver.modification.impide.Init.count_syn__ON__IdePropertyOption++;
public static final int silver_modification_impide_propRequired__ON__silver_modification_impide_IdePropertyOptions = silver.modification.impide.Init.count_syn__ON__IdePropertyOptions++;
public static final int silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOptions = silver.modification.impide.Init.count_syn__ON__IdePropertyOptions++;
public static final int silver_modification_impide_propDisplay__ON__silver_modification_impide_IdePropertyOptions = silver.modification.impide.Init.count_syn__ON__IdePropertyOptions++;
	public static final common.Thunk<Object> t_iomsgs = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PnonterminalType((new common.StringCatter("core:IOVal")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.extension.list.PlistType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PnonterminalType((new common.StringCatter("ide:IdeMessage")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };
	public static final common.Thunk<Object> t_props = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.extension.list.PlistType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PnonterminalType((new common.StringCatter("ide:IdeProperty")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };
	public static final common.Thunk<Object> t_io = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.modification.ffi.PforeignType((new common.StringCatter("core:IO")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
	public static final common.Thunk<Object> t_proj = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.modification.ffi.PforeignType((new common.StringCatter("ide:IdeProject")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
	public static final common.Thunk<Object> t_loc = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PnonterminalType((new common.StringCatter("core:Location")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
}
