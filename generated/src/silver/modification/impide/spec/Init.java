package silver.modification.impide.spec;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.modification.impide.cstast.Init.initAllStatics();
		silver.definition.concrete_syntax.ast.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.modification.impide.spec.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.driver.util.Init.init();
		silver.translation.java.core.Init.init();
		silver.modification.impide.cstast.Init.init();
		silver.definition.concrete_syntax.ast.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.env.Init.init();
		silver.modification.impide.spec.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.driver.util.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.modification.impide.cstast.Init.postInit();
		silver.definition.concrete_syntax.ast.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.modification.impide.spec.Init.postInit();


		common.Decorator.applyDecorators(silver.modification.impide.spec.NIdeProperty.decorators, PmakeIdeProperty.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NPropType.decorators, PstringPropType.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NPropType.decorators, PpathPropType.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NPropType.decorators, PurlPropType.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NPropType.decorators, PintegerPropType.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NIdeSpec.decorators, PideSpec.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NFont.decorators, Pfont.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NColor.decorators, PmakeColor.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NIdeFunctions.decorators, PconsIdeFunction.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NIdeFunctions.decorators, PnilIdeFunction.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NIdeFunction.decorators, PbuilderFunction.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NIdeFunction.decorators, PpostbuilderFunction.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NIdeFunction.decorators, PexporterFunction.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NIdeFunction.decorators, PfolderFunction.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NIdeWizards.decorators, PconsIdeWizard.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NIdeWizards.decorators, PnilIdeWizard.class);
		common.Decorator.applyDecorators(silver.modification.impide.spec.NIdeWizardDcl.decorators, PnewfileWizard.class);
	}

	private static void setupInheritedAttributes(){
		silver.modification.impide.spec.NIdeProperty.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_controlJavaTranslation__ON__silver_modification_impide_spec_IdeProperty] = "silver:modification:impide:spec:controlJavaTranslation";
		silver.modification.impide.spec.NIdeProperty.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_generatorJavaTranslation__ON__silver_modification_impide_spec_IdeProperty] = "silver:modification:impide:spec:generatorJavaTranslation";
		silver.modification.impide.spec.NPropType.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_strPropType__ON__silver_modification_impide_spec_PropType] = "silver:modification:impide:spec:strPropType";
		silver.modification.impide.spec.NPropType.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_propControl__ON__silver_modification_impide_spec_PropType] = "silver:modification:impide:spec:propControl";
		silver.modification.impide.spec.NIdeSpec.occurs_inh[silver.modification.impide.spec.Init.silver_definition_env_compiledGrammars__ON__silver_modification_impide_spec_IdeSpec] = "silver:definition:env:compiledGrammars";
		silver.modification.impide.spec.NIdeSpec.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.impide.spec.NIdeSpec.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginParserClass__ON__silver_modification_impide_spec_IdeSpec] = "silver:modification:impide:spec:pluginParserClass";
		silver.modification.impide.spec.NIdeSpec.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginGrammar__ON__silver_modification_impide_spec_IdeSpec] = "silver:modification:impide:spec:pluginGrammar";
		silver.modification.impide.spec.NIdeSpec.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_ideName__ON__silver_modification_impide_spec_IdeSpec] = "silver:modification:impide:spec:ideName";
		silver.modification.impide.spec.NIdeSpec.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_ideVersion__ON__silver_modification_impide_spec_IdeSpec] = "silver:modification:impide:spec:ideVersion";
		silver.modification.impide.spec.NIdeSpec.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginFiles__ON__silver_modification_impide_spec_IdeSpec] = "silver:modification:impide:spec:pluginFiles";
		silver.modification.impide.spec.NIdeSpec.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_spec_IdeSpec] = "silver:modification:impide:spec:ideResources";
		silver.modification.impide.spec.PideSpec.occurs_local[silver.modification.impide.spec.Init.implang__ON__silver_modification_impide_spec_ideSpec] = "silver:modification:impide:spec:ideSpec:local:implang";
		silver.modification.impide.spec.PideSpec.occurs_local[silver.modification.impide.spec.Init.package__ON__silver_modification_impide_spec_ideSpec] = "silver:modification:impide:spec:ideSpec:local:package";
		silver.modification.impide.spec.PideSpec.occurs_local[silver.modification.impide.spec.Init.bundle__ON__silver_modification_impide_spec_ideSpec] = "silver:modification:impide:spec:ideSpec:local:bundle";
		silver.modification.impide.spec.PideSpec.occurs_local[silver.modification.impide.spec.Init.pluginPkgPath__ON__silver_modification_impide_spec_ideSpec] = "silver:modification:impide:spec:ideSpec:local:pluginPkgPath";
		//	local attribute ast::SyntaxRoot;
		silver.modification.impide.spec.PideSpec.localInheritedAttributes[silver.modification.impide.spec.Init.ast__ON__silver_modification_impide_spec_ideSpec] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxRoot.num_inh_attrs];
		silver.modification.impide.spec.PideSpec.occurs_local[silver.modification.impide.spec.Init.ast__ON__silver_modification_impide_spec_ideSpec] = "silver:modification:impide:spec:ideSpec:local:ast";
		//	local attribute funcs::IdeFunctions;
		silver.modification.impide.spec.PideSpec.localInheritedAttributes[silver.modification.impide.spec.Init.funcs__ON__silver_modification_impide_spec_ideSpec] = new common.Lazy[silver.modification.impide.spec.NIdeFunctions.num_inh_attrs];
		silver.modification.impide.spec.PideSpec.occurs_local[silver.modification.impide.spec.Init.funcs__ON__silver_modification_impide_spec_ideSpec] = "silver:modification:impide:spec:ideSpec:local:funcs";
		//	local attribute wizs::IdeWizards;
		silver.modification.impide.spec.PideSpec.localInheritedAttributes[silver.modification.impide.spec.Init.wizs__ON__silver_modification_impide_spec_ideSpec] = new common.Lazy[silver.modification.impide.spec.NIdeWizards.num_inh_attrs];
		silver.modification.impide.spec.PideSpec.occurs_local[silver.modification.impide.spec.Init.wizs__ON__silver_modification_impide_spec_ideSpec] = "silver:modification:impide:spec:ideSpec:local:wizs";
		silver.modification.impide.spec.PideSpec.occurs_local[silver.modification.impide.spec.Init.tabs__ON__silver_modification_impide_spec_ideSpec] = "silver:modification:impide:spec:ideSpec:local:tabs";
		silver.modification.impide.spec.PideSpec.occurs_local[silver.modification.impide.spec.Init.sourceGrammarName__ON__silver_modification_impide_spec_ideSpec] = "silver:modification:impide:spec:ideSpec:local:sourceGrammarName";
		silver.modification.impide.spec.PgetPropertyGenerator.occurs_local[silver.modification.impide.spec.Init.pkgPart__ON__silver_modification_impide_spec_getPropertyGenerator] = "silver:modification:impide:spec:getPropertyGenerator:local:pkgPart";
		silver.modification.impide.spec.NFont.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_getTextAttribute__ON__silver_modification_impide_spec_Font] = "silver:modification:impide:spec:getTextAttribute";
		silver.modification.impide.spec.NColor.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_r__ON__silver_modification_impide_spec_Color] = "silver:modification:impide:spec:r";
		silver.modification.impide.spec.NColor.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_g__ON__silver_modification_impide_spec_Color] = "silver:modification:impide:spec:g";
		silver.modification.impide.spec.NColor.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_b__ON__silver_modification_impide_spec_Color] = "silver:modification:impide:spec:b";
		silver.modification.impide.spec.NIdeFunctions.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_package__ON__silver_modification_impide_spec_IdeFunctions] = "silver:modification:impide:spec:package";
		silver.modification.impide.spec.NIdeFunctions.decorators.add(silver.modification.impide.spec.Dpackage.singleton);
		silver.modification.impide.spec.NIdeFunctions.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_visibleName__ON__silver_modification_impide_spec_IdeFunctions] = "silver:modification:impide:spec:visibleName";
		silver.modification.impide.spec.NIdeFunctions.decorators.add(silver.modification.impide.spec.DvisibleName.singleton);
		silver.modification.impide.spec.NIdeFunctions.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_implang__ON__silver_modification_impide_spec_IdeFunctions] = "silver:modification:impide:spec:implang";
		silver.modification.impide.spec.NIdeFunctions.decorators.add(silver.modification.impide.spec.Dimplang.singleton);
		silver.modification.impide.spec.NIdeFunctions.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_bundle__ON__silver_modification_impide_spec_IdeFunctions] = "silver:modification:impide:spec:bundle";
		silver.modification.impide.spec.NIdeFunctions.decorators.add(silver.modification.impide.spec.Dbundle.singleton);
		silver.modification.impide.spec.NIdeFunctions.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeFunctions] = "silver:modification:impide:spec:svIdeInterface";
		silver.modification.impide.spec.NIdeFunctions.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXml__ON__silver_modification_impide_spec_IdeFunctions] = "silver:modification:impide:spec:pluginXml";
		silver.modification.impide.spec.NIdeFunctions.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlActions__ON__silver_modification_impide_spec_IdeFunctions] = "silver:modification:impide:spec:pluginXmlActions";
		silver.modification.impide.spec.NIdeFunction.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_package__ON__silver_modification_impide_spec_IdeFunction] = "silver:modification:impide:spec:package";
		silver.modification.impide.spec.NIdeFunction.decorators.add(silver.modification.impide.spec.Dpackage.singleton);
		silver.modification.impide.spec.NIdeFunction.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_visibleName__ON__silver_modification_impide_spec_IdeFunction] = "silver:modification:impide:spec:visibleName";
		silver.modification.impide.spec.NIdeFunction.decorators.add(silver.modification.impide.spec.DvisibleName.singleton);
		silver.modification.impide.spec.NIdeFunction.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_implang__ON__silver_modification_impide_spec_IdeFunction] = "silver:modification:impide:spec:implang";
		silver.modification.impide.spec.NIdeFunction.decorators.add(silver.modification.impide.spec.Dimplang.singleton);
		silver.modification.impide.spec.NIdeFunction.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_bundle__ON__silver_modification_impide_spec_IdeFunction] = "silver:modification:impide:spec:bundle";
		silver.modification.impide.spec.NIdeFunction.decorators.add(silver.modification.impide.spec.Dbundle.singleton);
		silver.modification.impide.spec.NIdeFunction.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeFunction] = "silver:modification:impide:spec:svIdeInterface";
		silver.modification.impide.spec.NIdeFunction.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXml__ON__silver_modification_impide_spec_IdeFunction] = "silver:modification:impide:spec:pluginXml";
		silver.modification.impide.spec.NIdeFunction.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlActions__ON__silver_modification_impide_spec_IdeFunction] = "silver:modification:impide:spec:pluginXmlActions";
		silver.modification.impide.spec.NIdeWizards.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginPkgPath__ON__silver_modification_impide_spec_IdeWizards] = "silver:modification:impide:spec:pluginPkgPath";
		silver.modification.impide.spec.NIdeWizards.decorators.add(silver.modification.impide.spec.DpluginPkgPath.singleton);
		silver.modification.impide.spec.NIdeWizards.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginFiles__ON__silver_modification_impide_spec_IdeWizards] = "silver:modification:impide:spec:pluginFiles";
		silver.modification.impide.spec.NIdeWizards.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_package__ON__silver_modification_impide_spec_IdeWizards] = "silver:modification:impide:spec:package";
		silver.modification.impide.spec.NIdeWizards.decorators.add(silver.modification.impide.spec.Dpackage.singleton);
		silver.modification.impide.spec.NIdeWizards.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_visibleName__ON__silver_modification_impide_spec_IdeWizards] = "silver:modification:impide:spec:visibleName";
		silver.modification.impide.spec.NIdeWizards.decorators.add(silver.modification.impide.spec.DvisibleName.singleton);
		silver.modification.impide.spec.NIdeWizards.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_implang__ON__silver_modification_impide_spec_IdeWizards] = "silver:modification:impide:spec:implang";
		silver.modification.impide.spec.NIdeWizards.decorators.add(silver.modification.impide.spec.Dimplang.singleton);
		silver.modification.impide.spec.NIdeWizards.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_bundle__ON__silver_modification_impide_spec_IdeWizards] = "silver:modification:impide:spec:bundle";
		silver.modification.impide.spec.NIdeWizards.decorators.add(silver.modification.impide.spec.Dbundle.singleton);
		silver.modification.impide.spec.NIdeWizards.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeWizards] = "silver:modification:impide:spec:svIdeInterface";
		silver.modification.impide.spec.NIdeWizards.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlWizards__ON__silver_modification_impide_spec_IdeWizards] = "silver:modification:impide:spec:pluginXmlWizards";
		silver.modification.impide.spec.NIdeWizardDcl.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginPkgPath__ON__silver_modification_impide_spec_IdeWizardDcl] = "silver:modification:impide:spec:pluginPkgPath";
		silver.modification.impide.spec.NIdeWizardDcl.decorators.add(silver.modification.impide.spec.DpluginPkgPath.singleton);
		silver.modification.impide.spec.NIdeWizardDcl.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginFiles__ON__silver_modification_impide_spec_IdeWizardDcl] = "silver:modification:impide:spec:pluginFiles";
		silver.modification.impide.spec.NIdeWizardDcl.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_package__ON__silver_modification_impide_spec_IdeWizardDcl] = "silver:modification:impide:spec:package";
		silver.modification.impide.spec.NIdeWizardDcl.decorators.add(silver.modification.impide.spec.Dpackage.singleton);
		silver.modification.impide.spec.NIdeWizardDcl.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_visibleName__ON__silver_modification_impide_spec_IdeWizardDcl] = "silver:modification:impide:spec:visibleName";
		silver.modification.impide.spec.NIdeWizardDcl.decorators.add(silver.modification.impide.spec.DvisibleName.singleton);
		silver.modification.impide.spec.NIdeWizardDcl.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_implang__ON__silver_modification_impide_spec_IdeWizardDcl] = "silver:modification:impide:spec:implang";
		silver.modification.impide.spec.NIdeWizardDcl.decorators.add(silver.modification.impide.spec.Dimplang.singleton);
		silver.modification.impide.spec.NIdeWizardDcl.occurs_inh[silver.modification.impide.spec.Init.silver_modification_impide_spec_bundle__ON__silver_modification_impide_spec_IdeWizardDcl] = "silver:modification:impide:spec:bundle";
		silver.modification.impide.spec.NIdeWizardDcl.decorators.add(silver.modification.impide.spec.Dbundle.singleton);
		silver.modification.impide.spec.NIdeWizardDcl.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeWizardDcl] = "silver:modification:impide:spec:svIdeInterface";
		silver.modification.impide.spec.NIdeWizardDcl.occurs_syn[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlWizards__ON__silver_modification_impide_spec_IdeWizardDcl] = "silver:modification:impide:spec:pluginXmlWizards";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.impide.spec.PmakeIdeProperty.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PstringPropType.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PpathPropType.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PurlPropType.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PintegerPropType.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PideSpec.initProductionAttributeDefinitions();
		//FUNCTION newTabClass String ::= tab::String 
		//FUNCTION getPropertyProvider String ::= pkgName::String propDcls::[IdeProperty] pkgPart::String 
		//FUNCTION getPropertyGenerator String ::= pkgName::String propDcls::[IdeProperty] pkgFinalPart::String 
		// pkgPart = if pkgFinalPart == "" then "" else "." ++ pkgFinalPart
		silver.modification.impide.spec.PgetPropertyGenerator.localAttributes[silver.modification.impide.spec.Init.pkgPart__ON__silver_modification_impide_spec_getPropertyGenerator] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PgetPropertyGenerator.i_pkgFinalPart)).equals((new common.StringCatter(""))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PgetPropertyGenerator.i_pkgFinalPart)))); } };
		//FUNCTION getTokenClassifier String ::= pkgName::String fontList::[Pair<String Font>] termFontPairList::[Pair<String String>] parserName::String 
		//FUNCTION getPutNameFontPairIntoMap String ::= tokenNameAndFontName::Pair<String String> 
		//FUNCTION getConstantDeclarations String ::= i::Integer fontList::[Pair<String Font>] 
		//FUNCTION getTextAttributeInit String ::= f::Pair<String Font> 
		silver.modification.impide.spec.Pfont.initProductionAttributeDefinitions();
		//FUNCTION boolToString String ::= b::Boolean 
		silver.modification.impide.spec.PmakeColor.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PconsIdeFunction.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PnilIdeFunction.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for IdeFunction
		// top.svIdeInterface = ""
		silver.modification.impide.spec.NIdeFunction.defaultSynthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeFunction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.pluginXml = ""
		silver.modification.impide.spec.NIdeFunction.defaultSynthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXml__ON__silver_modification_impide_spec_IdeFunction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.pluginXmlActions = ""
		silver.modification.impide.spec.NIdeFunction.defaultSynthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlActions__ON__silver_modification_impide_spec_IdeFunction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		silver.modification.impide.spec.PbuilderFunction.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PpostbuilderFunction.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PexporterFunction.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PfolderFunction.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PconsIdeWizard.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PnilIdeWizard.initProductionAttributeDefinitions();
		silver.modification.impide.spec.PnewfileWizard.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__IdeProperty = 0;
	public static int count_syn__ON__IdeProperty = 0;
	public static int count_local__ON__silver_modification_impide_spec_makeIdeProperty = 0;
	public static int count_inh__ON__PropType = 0;
	public static int count_syn__ON__PropType = 0;
	public static int count_local__ON__silver_modification_impide_spec_stringPropType = 0;
	public static int count_local__ON__silver_modification_impide_spec_pathPropType = 0;
	public static int count_local__ON__silver_modification_impide_spec_urlPropType = 0;
	public static int count_local__ON__silver_modification_impide_spec_integerPropType = 0;
	public static int count_inh__ON__IdeSpec = 0;
	public static int count_syn__ON__IdeSpec = 0;
	public static int count_local__ON__silver_modification_impide_spec_ideSpec = 0;
	public static int count_local__ON__silver_modification_impide_spec_newTabClass = 0;
	public static int count_local__ON__silver_modification_impide_spec_getPropertyProvider = 0;
	public static int count_local__ON__silver_modification_impide_spec_getPropertyGenerator = 0;
	public static int count_local__ON__silver_modification_impide_spec_getTokenClassifier = 0;
	public static int count_local__ON__silver_modification_impide_spec_getPutNameFontPairIntoMap = 0;
	public static int count_local__ON__silver_modification_impide_spec_getConstantDeclarations = 0;
	public static int count_local__ON__silver_modification_impide_spec_getTextAttributeInit = 0;
	public static int count_inh__ON__Font = 0;
	public static int count_syn__ON__Font = 0;
	public static int count_local__ON__silver_modification_impide_spec_font = 0;
	public static int count_local__ON__silver_modification_impide_spec_boolToString = 0;
	public static int count_inh__ON__Color = 0;
	public static int count_syn__ON__Color = 0;
	public static int count_local__ON__silver_modification_impide_spec_makeColor = 0;
	public static int count_inh__ON__IdeFunctions = 0;
	public static int count_syn__ON__IdeFunctions = 0;
	public static int count_local__ON__silver_modification_impide_spec_consIdeFunction = 0;
	public static int count_local__ON__silver_modification_impide_spec_nilIdeFunction = 0;
	public static int count_inh__ON__IdeFunction = 0;
	public static int count_syn__ON__IdeFunction = 0;
	public static int count_local__ON__silver_modification_impide_spec_builderFunction = 0;
	public static int count_local__ON__silver_modification_impide_spec_postbuilderFunction = 0;
	public static int count_local__ON__silver_modification_impide_spec_exporterFunction = 0;
	public static int count_local__ON__silver_modification_impide_spec_folderFunction = 0;
	public static int count_inh__ON__IdeWizards = 0;
	public static int count_syn__ON__IdeWizards = 0;
	public static int count_local__ON__silver_modification_impide_spec_consIdeWizard = 0;
	public static int count_local__ON__silver_modification_impide_spec_nilIdeWizard = 0;
	public static int count_inh__ON__IdeWizardDcl = 0;
	public static int count_syn__ON__IdeWizardDcl = 0;
	public static int count_local__ON__silver_modification_impide_spec_newfileWizard = 0;
public static final int silver_modification_impide_spec_controlJavaTranslation__ON__silver_modification_impide_spec_IdeProperty = silver.modification.impide.spec.Init.count_syn__ON__IdeProperty++;
public static final int silver_modification_impide_spec_generatorJavaTranslation__ON__silver_modification_impide_spec_IdeProperty = silver.modification.impide.spec.Init.count_syn__ON__IdeProperty++;
public static final int silver_modification_impide_spec_strPropType__ON__silver_modification_impide_spec_PropType = silver.modification.impide.spec.Init.count_syn__ON__PropType++;
public static final int silver_modification_impide_spec_propControl__ON__silver_modification_impide_spec_PropType = silver.modification.impide.spec.Init.count_syn__ON__PropType++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_impide_spec_IdeSpec = silver.modification.impide.spec.Init.count_inh__ON__IdeSpec++;
public static final int silver_modification_impide_spec_pluginParserClass__ON__silver_modification_impide_spec_IdeSpec = silver.modification.impide.spec.Init.count_syn__ON__IdeSpec++;
public static final int silver_modification_impide_spec_pluginGrammar__ON__silver_modification_impide_spec_IdeSpec = silver.modification.impide.spec.Init.count_syn__ON__IdeSpec++;
public static final int silver_modification_impide_spec_ideName__ON__silver_modification_impide_spec_IdeSpec = silver.modification.impide.spec.Init.count_syn__ON__IdeSpec++;
public static final int silver_modification_impide_spec_ideVersion__ON__silver_modification_impide_spec_IdeSpec = silver.modification.impide.spec.Init.count_syn__ON__IdeSpec++;
public static final int silver_modification_impide_spec_pluginFiles__ON__silver_modification_impide_spec_IdeSpec = silver.modification.impide.spec.Init.count_syn__ON__IdeSpec++;
public static final int silver_modification_impide_spec_ideResources__ON__silver_modification_impide_spec_IdeSpec = silver.modification.impide.spec.Init.count_syn__ON__IdeSpec++;
public static final int implang__ON__silver_modification_impide_spec_ideSpec = silver.modification.impide.spec.Init.count_local__ON__silver_modification_impide_spec_ideSpec++;
public static final int package__ON__silver_modification_impide_spec_ideSpec = silver.modification.impide.spec.Init.count_local__ON__silver_modification_impide_spec_ideSpec++;
public static final int bundle__ON__silver_modification_impide_spec_ideSpec = silver.modification.impide.spec.Init.count_local__ON__silver_modification_impide_spec_ideSpec++;
public static final int pluginPkgPath__ON__silver_modification_impide_spec_ideSpec = silver.modification.impide.spec.Init.count_local__ON__silver_modification_impide_spec_ideSpec++;
public static final int ast__ON__silver_modification_impide_spec_ideSpec = silver.modification.impide.spec.Init.count_local__ON__silver_modification_impide_spec_ideSpec++;
public static final int funcs__ON__silver_modification_impide_spec_ideSpec = silver.modification.impide.spec.Init.count_local__ON__silver_modification_impide_spec_ideSpec++;
public static final int wizs__ON__silver_modification_impide_spec_ideSpec = silver.modification.impide.spec.Init.count_local__ON__silver_modification_impide_spec_ideSpec++;
public static final int tabs__ON__silver_modification_impide_spec_ideSpec = silver.modification.impide.spec.Init.count_local__ON__silver_modification_impide_spec_ideSpec++;
public static final int sourceGrammarName__ON__silver_modification_impide_spec_ideSpec = silver.modification.impide.spec.Init.count_local__ON__silver_modification_impide_spec_ideSpec++;
public static final int pkgPart__ON__silver_modification_impide_spec_getPropertyGenerator = silver.modification.impide.spec.Init.count_local__ON__silver_modification_impide_spec_getPropertyGenerator++;
public static final int silver_modification_impide_spec_getTextAttribute__ON__silver_modification_impide_spec_Font = silver.modification.impide.spec.Init.count_syn__ON__Font++;
public static final int silver_modification_impide_spec_r__ON__silver_modification_impide_spec_Color = silver.modification.impide.spec.Init.count_syn__ON__Color++;
public static final int silver_modification_impide_spec_g__ON__silver_modification_impide_spec_Color = silver.modification.impide.spec.Init.count_syn__ON__Color++;
public static final int silver_modification_impide_spec_b__ON__silver_modification_impide_spec_Color = silver.modification.impide.spec.Init.count_syn__ON__Color++;
public static final int silver_modification_impide_spec_package__ON__silver_modification_impide_spec_IdeFunctions = silver.modification.impide.spec.Init.count_inh__ON__IdeFunctions++;
public static final int silver_modification_impide_spec_visibleName__ON__silver_modification_impide_spec_IdeFunctions = silver.modification.impide.spec.Init.count_inh__ON__IdeFunctions++;
public static final int silver_modification_impide_spec_implang__ON__silver_modification_impide_spec_IdeFunctions = silver.modification.impide.spec.Init.count_inh__ON__IdeFunctions++;
public static final int silver_modification_impide_spec_bundle__ON__silver_modification_impide_spec_IdeFunctions = silver.modification.impide.spec.Init.count_inh__ON__IdeFunctions++;
public static final int silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeFunctions = silver.modification.impide.spec.Init.count_syn__ON__IdeFunctions++;
public static final int silver_modification_impide_spec_pluginXml__ON__silver_modification_impide_spec_IdeFunctions = silver.modification.impide.spec.Init.count_syn__ON__IdeFunctions++;
public static final int silver_modification_impide_spec_pluginXmlActions__ON__silver_modification_impide_spec_IdeFunctions = silver.modification.impide.spec.Init.count_syn__ON__IdeFunctions++;
public static final int silver_modification_impide_spec_package__ON__silver_modification_impide_spec_IdeFunction = silver.modification.impide.spec.Init.count_inh__ON__IdeFunction++;
public static final int silver_modification_impide_spec_visibleName__ON__silver_modification_impide_spec_IdeFunction = silver.modification.impide.spec.Init.count_inh__ON__IdeFunction++;
public static final int silver_modification_impide_spec_implang__ON__silver_modification_impide_spec_IdeFunction = silver.modification.impide.spec.Init.count_inh__ON__IdeFunction++;
public static final int silver_modification_impide_spec_bundle__ON__silver_modification_impide_spec_IdeFunction = silver.modification.impide.spec.Init.count_inh__ON__IdeFunction++;
public static final int silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeFunction = silver.modification.impide.spec.Init.count_syn__ON__IdeFunction++;
public static final int silver_modification_impide_spec_pluginXml__ON__silver_modification_impide_spec_IdeFunction = silver.modification.impide.spec.Init.count_syn__ON__IdeFunction++;
public static final int silver_modification_impide_spec_pluginXmlActions__ON__silver_modification_impide_spec_IdeFunction = silver.modification.impide.spec.Init.count_syn__ON__IdeFunction++;
public static final int silver_modification_impide_spec_pluginPkgPath__ON__silver_modification_impide_spec_IdeWizards = silver.modification.impide.spec.Init.count_inh__ON__IdeWizards++;
public static final int silver_modification_impide_spec_pluginFiles__ON__silver_modification_impide_spec_IdeWizards = silver.modification.impide.spec.Init.count_syn__ON__IdeWizards++;
public static final int silver_modification_impide_spec_package__ON__silver_modification_impide_spec_IdeWizards = silver.modification.impide.spec.Init.count_inh__ON__IdeWizards++;
public static final int silver_modification_impide_spec_visibleName__ON__silver_modification_impide_spec_IdeWizards = silver.modification.impide.spec.Init.count_inh__ON__IdeWizards++;
public static final int silver_modification_impide_spec_implang__ON__silver_modification_impide_spec_IdeWizards = silver.modification.impide.spec.Init.count_inh__ON__IdeWizards++;
public static final int silver_modification_impide_spec_bundle__ON__silver_modification_impide_spec_IdeWizards = silver.modification.impide.spec.Init.count_inh__ON__IdeWizards++;
public static final int silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeWizards = silver.modification.impide.spec.Init.count_syn__ON__IdeWizards++;
public static final int silver_modification_impide_spec_pluginXmlWizards__ON__silver_modification_impide_spec_IdeWizards = silver.modification.impide.spec.Init.count_syn__ON__IdeWizards++;
public static final int silver_modification_impide_spec_pluginPkgPath__ON__silver_modification_impide_spec_IdeWizardDcl = silver.modification.impide.spec.Init.count_inh__ON__IdeWizardDcl++;
public static final int silver_modification_impide_spec_pluginFiles__ON__silver_modification_impide_spec_IdeWizardDcl = silver.modification.impide.spec.Init.count_syn__ON__IdeWizardDcl++;
public static final int silver_modification_impide_spec_package__ON__silver_modification_impide_spec_IdeWizardDcl = silver.modification.impide.spec.Init.count_inh__ON__IdeWizardDcl++;
public static final int silver_modification_impide_spec_visibleName__ON__silver_modification_impide_spec_IdeWizardDcl = silver.modification.impide.spec.Init.count_inh__ON__IdeWizardDcl++;
public static final int silver_modification_impide_spec_implang__ON__silver_modification_impide_spec_IdeWizardDcl = silver.modification.impide.spec.Init.count_inh__ON__IdeWizardDcl++;
public static final int silver_modification_impide_spec_bundle__ON__silver_modification_impide_spec_IdeWizardDcl = silver.modification.impide.spec.Init.count_inh__ON__IdeWizardDcl++;
public static final int silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeWizardDcl = silver.modification.impide.spec.Init.count_syn__ON__IdeWizardDcl++;
public static final int silver_modification_impide_spec_pluginXmlWizards__ON__silver_modification_impide_spec_IdeWizardDcl = silver.modification.impide.spec.Init.count_syn__ON__IdeWizardDcl++;
	public static final common.Thunk<Object> extid_nature = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("nature")); } };
	public static final common.Thunk<Object> extid_builder = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("builder")); } };
	public static final common.Thunk<Object> extid_problem = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("builder.problem")); } };
	public static final common.Thunk<Object> extid_perspective = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("perspective")); } };
	public static final common.Thunk<Object> extid_projectmenu = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("actions.projectmenu")); } };
	public static final common.Thunk<Object> extid_action_nature = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("actions.nature")); } };
	public static final common.Thunk<Object> extid_action_export = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("actions.export")); } };
	public static final common.Thunk<Object> extid_wizard_category = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("wizards.category")); } };
	public static final common.Thunk<Object> extid_wizard_newproject = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("wizards.newproject")); } };
	public static final common.Thunk<Object> extid_wizard_newfile = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("wizards.newfile")); } };
	public static final common.Thunk<Object> extid_properties = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("properties")); } };
}
