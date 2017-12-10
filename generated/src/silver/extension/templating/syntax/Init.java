package silver.extension.templating.syntax;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.extension.templating.syntax.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.core.Init.init();
		silver.extension.templating.syntax.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.extension.templating.syntax.Init.postInit();


		common.Decorator.applyDecorators(silver.extension.templating.syntax.NTemplateString.decorators, PtemplateString.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NTemplateString.decorators, PtemplateStringEmpty.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineTemplateString.decorators, PsingleLineTemplateString.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineTemplateString.decorators, PsingleLineTemplateStringEmpty.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NTemplateStringBody.decorators, PbodyCons.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NTemplateStringBody.decorators, PbodyOne.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NTemplateStringBody.decorators, PbodyOneWater.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineTemplateStringBody.decorators, PsingleLineBodyCons.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineTemplateStringBody.decorators, PsingleLineBodyOne.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineTemplateStringBody.decorators, PsingleLineBodyOneWater.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NTemplateStringBodyItem.decorators, PitemWaterEscape.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NTemplateStringBodyItem.decorators, PitemEscape.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineTemplateStringBodyItem.decorators, PsingleLineItemWaterEscape.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineTemplateStringBodyItem.decorators, PsingleLineItemEscape.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NNonWater.decorators, Pnonwater.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NWater.decorators, PwaterCons.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NWater.decorators, PwaterOne.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NWaterItem.decorators, Pwater.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NWaterItem.decorators, PwaterDollar.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NWaterItem.decorators, PwaterBackSlash.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NWaterItem.decorators, PwaterNewline.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NWaterItem.decorators, PwaterTab.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NWaterItem.decorators, PwaterQuote.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineWater.decorators, PsingleLineWaterCons.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineWater.decorators, PsingleLineWaterOne.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineWaterItem.decorators, PsingleLineWater.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineWaterItem.decorators, PsingleLineWaterDollar.class);
		common.Decorator.applyDecorators(silver.extension.templating.syntax.NSingleLineWaterItem.decorators, PsingleLineWaterBackSlash.class);
	}

	private static void setupInheritedAttributes(){
		silver.extension.templating.syntax.NWater.occurs_syn[silver.extension.templating.syntax.Init.silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_Water] = "silver:extension:templating:syntax:waterString";
		silver.extension.templating.syntax.NSingleLineWater.occurs_syn[silver.extension.templating.syntax.Init.silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_SingleLineWater] = "silver:extension:templating:syntax:waterString";
		silver.extension.templating.syntax.NWaterItem.occurs_syn[silver.extension.templating.syntax.Init.silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_WaterItem] = "silver:extension:templating:syntax:waterString";
		silver.extension.templating.syntax.NSingleLineWaterItem.occurs_syn[silver.extension.templating.syntax.Init.silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_SingleLineWaterItem] = "silver:extension:templating:syntax:waterString";
	}

	private static void initProductionAttributeDefinitions(){
		silver.extension.templating.syntax.PtemplateString.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PtemplateStringEmpty.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineTemplateString.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineTemplateStringEmpty.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PbodyCons.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PbodyOne.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PbodyOneWater.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineBodyCons.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineBodyOne.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineBodyOneWater.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PitemWaterEscape.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PitemEscape.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineItemWaterEscape.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineItemEscape.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.Pnonwater.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PwaterCons.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PwaterOne.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.Pwater.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PwaterDollar.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PwaterBackSlash.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PwaterNewline.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PwaterTab.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PwaterQuote.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineWaterCons.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineWaterOne.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineWater.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineWaterDollar.initProductionAttributeDefinitions();
		silver.extension.templating.syntax.PsingleLineWaterBackSlash.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__TemplateString = 0;
	public static int count_syn__ON__TemplateString = 0;
	public static int count_inh__ON__SingleLineTemplateString = 0;
	public static int count_syn__ON__SingleLineTemplateString = 0;
	public static int count_inh__ON__TemplateStringBody = 0;
	public static int count_syn__ON__TemplateStringBody = 0;
	public static int count_inh__ON__SingleLineTemplateStringBody = 0;
	public static int count_syn__ON__SingleLineTemplateStringBody = 0;
	public static int count_inh__ON__TemplateStringBodyItem = 0;
	public static int count_syn__ON__TemplateStringBodyItem = 0;
	public static int count_inh__ON__SingleLineTemplateStringBodyItem = 0;
	public static int count_syn__ON__SingleLineTemplateStringBodyItem = 0;
	public static int count_inh__ON__NonWater = 0;
	public static int count_syn__ON__NonWater = 0;
	public static int count_inh__ON__Water = 0;
	public static int count_syn__ON__Water = 0;
	public static int count_inh__ON__SingleLineWater = 0;
	public static int count_syn__ON__SingleLineWater = 0;
	public static int count_inh__ON__WaterItem = 0;
	public static int count_syn__ON__WaterItem = 0;
	public static int count_inh__ON__SingleLineWaterItem = 0;
	public static int count_syn__ON__SingleLineWaterItem = 0;
	public static int count_local__ON__silver_extension_templating_syntax_templateString = 0;
	public static int count_local__ON__silver_extension_templating_syntax_templateStringEmpty = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineTemplateString = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineTemplateStringEmpty = 0;
	public static int count_local__ON__silver_extension_templating_syntax_bodyCons = 0;
	public static int count_local__ON__silver_extension_templating_syntax_bodyOne = 0;
	public static int count_local__ON__silver_extension_templating_syntax_bodyOneWater = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineBodyCons = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineBodyOne = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineBodyOneWater = 0;
	public static int count_local__ON__silver_extension_templating_syntax_itemWaterEscape = 0;
	public static int count_local__ON__silver_extension_templating_syntax_itemEscape = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineItemWaterEscape = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineItemEscape = 0;
	public static int count_local__ON__silver_extension_templating_syntax_nonwater = 0;
	public static int count_local__ON__silver_extension_templating_syntax_waterCons = 0;
	public static int count_local__ON__silver_extension_templating_syntax_waterOne = 0;
	public static int count_local__ON__silver_extension_templating_syntax_water = 0;
	public static int count_local__ON__silver_extension_templating_syntax_waterDollar = 0;
	public static int count_local__ON__silver_extension_templating_syntax_waterBackSlash = 0;
	public static int count_local__ON__silver_extension_templating_syntax_waterNewline = 0;
	public static int count_local__ON__silver_extension_templating_syntax_waterTab = 0;
	public static int count_local__ON__silver_extension_templating_syntax_waterQuote = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineWaterCons = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineWaterOne = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineWater = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineWaterDollar = 0;
	public static int count_local__ON__silver_extension_templating_syntax_singleLineWaterBackSlash = 0;
public static final int silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_Water = silver.extension.templating.syntax.Init.count_syn__ON__Water++;
public static final int silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_SingleLineWater = silver.extension.templating.syntax.Init.count_syn__ON__SingleLineWater++;
public static final int silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_WaterItem = silver.extension.templating.syntax.Init.count_syn__ON__WaterItem++;
public static final int silver_extension_templating_syntax_waterString__ON__silver_extension_templating_syntax_SingleLineWaterItem = silver.extension.templating.syntax.Init.count_syn__ON__SingleLineWaterItem++;
}
