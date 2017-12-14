package silver.definition.regex;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.definition.regex.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.definition.regex.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.definition.regex.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.regex.NRegex.decorators, PregexEpsilon.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegex.decorators, PregexSeq.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegex.decorators, PregexChoice.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexSeq.decorators, PregexSeqSnoc.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexSeq.decorators, PregexSeqOne.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexRepetition.decorators, PregexKleene.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexRepetition.decorators, PregexPlus.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexRepetition.decorators, PregexOptional.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexRepetition.decorators, PregexOnce.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexItem.decorators, PregexCharItem.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexItem.decorators, PregexWildcard.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexItem.decorators, PregexSet.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexItem.decorators, PregexSetInverted.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexItem.decorators, PregexGroup.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexCharSet.decorators, PregexCharSetSnoc.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexCharSet.decorators, PregexCharSetOne.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexCharSetItem.decorators, PregexSetChar.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexCharSetItem.decorators, PregexSetRange.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexChar.decorators, PregexChar.class);
		common.Decorator.applyDecorators(silver.definition.regex.NRegexChar.decorators, PregexEscapedChar.class);
	}

	private static void setupInheritedAttributes(){
		silver.definition.regex.NRegex.occurs_syn[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_Regex] = "silver:definition:regex:regString";
		silver.definition.regex.NRegexSeq.occurs_syn[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexSeq] = "silver:definition:regex:regString";
		silver.definition.regex.NRegexRepetition.occurs_syn[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexRepetition] = "silver:definition:regex:regString";
		silver.definition.regex.NRegexItem.occurs_syn[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexItem] = "silver:definition:regex:regString";
		silver.definition.regex.NRegexCharSet.occurs_syn[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexCharSet] = "silver:definition:regex:regString";
		silver.definition.regex.NRegexCharSetItem.occurs_syn[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexCharSetItem] = "silver:definition:regex:regString";
		silver.definition.regex.NRegexChar.occurs_syn[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexChar] = "silver:definition:regex:regString";
	}

	private static void initProductionAttributeDefinitions(){
		silver.definition.regex.PregexEpsilon.initProductionAttributeDefinitions();
		silver.definition.regex.PregexSeq.initProductionAttributeDefinitions();
		silver.definition.regex.PregexChoice.initProductionAttributeDefinitions();
		silver.definition.regex.PregexSeqSnoc.initProductionAttributeDefinitions();
		silver.definition.regex.PregexSeqOne.initProductionAttributeDefinitions();
		silver.definition.regex.PregexKleene.initProductionAttributeDefinitions();
		silver.definition.regex.PregexPlus.initProductionAttributeDefinitions();
		silver.definition.regex.PregexOptional.initProductionAttributeDefinitions();
		silver.definition.regex.PregexOnce.initProductionAttributeDefinitions();
		silver.definition.regex.PregexCharItem.initProductionAttributeDefinitions();
		silver.definition.regex.PregexWildcard.initProductionAttributeDefinitions();
		silver.definition.regex.PregexSet.initProductionAttributeDefinitions();
		silver.definition.regex.PregexSetInverted.initProductionAttributeDefinitions();
		silver.definition.regex.PregexGroup.initProductionAttributeDefinitions();
		silver.definition.regex.PregexCharSetSnoc.initProductionAttributeDefinitions();
		silver.definition.regex.PregexCharSetOne.initProductionAttributeDefinitions();
		silver.definition.regex.PregexSetChar.initProductionAttributeDefinitions();
		silver.definition.regex.PregexSetRange.initProductionAttributeDefinitions();
		silver.definition.regex.PregexChar.initProductionAttributeDefinitions();
		silver.definition.regex.PregexEscapedChar.initProductionAttributeDefinitions();
		//FUNCTION regexConcatenate Regex ::= l::Regex r::Regex 
		//FUNCTION concatRegexItems RegexSeq ::= l::[RegexItem] 
		//FUNCTION regexCharToItem RegexItem ::= ch::String 
		//FUNCTION regexLiteral Regex ::= s::String 
	}

	public static int count_inh__ON__Regex = 0;
	public static int count_syn__ON__Regex = 0;
	public static int count_local__ON__silver_definition_regex_regexEpsilon = 0;
	public static int count_local__ON__silver_definition_regex_regexSeq = 0;
	public static int count_local__ON__silver_definition_regex_regexChoice = 0;
	public static int count_inh__ON__RegexSeq = 0;
	public static int count_syn__ON__RegexSeq = 0;
	public static int count_local__ON__silver_definition_regex_regexSeqSnoc = 0;
	public static int count_local__ON__silver_definition_regex_regexSeqOne = 0;
	public static int count_inh__ON__RegexRepetition = 0;
	public static int count_syn__ON__RegexRepetition = 0;
	public static int count_local__ON__silver_definition_regex_regexKleene = 0;
	public static int count_local__ON__silver_definition_regex_regexPlus = 0;
	public static int count_local__ON__silver_definition_regex_regexOptional = 0;
	public static int count_local__ON__silver_definition_regex_regexOnce = 0;
	public static int count_inh__ON__RegexItem = 0;
	public static int count_syn__ON__RegexItem = 0;
	public static int count_local__ON__silver_definition_regex_regexCharItem = 0;
	public static int count_local__ON__silver_definition_regex_regexWildcard = 0;
	public static int count_local__ON__silver_definition_regex_regexSet = 0;
	public static int count_local__ON__silver_definition_regex_regexSetInverted = 0;
	public static int count_local__ON__silver_definition_regex_regexGroup = 0;
	public static int count_inh__ON__RegexCharSet = 0;
	public static int count_syn__ON__RegexCharSet = 0;
	public static int count_local__ON__silver_definition_regex_regexCharSetSnoc = 0;
	public static int count_local__ON__silver_definition_regex_regexCharSetOne = 0;
	public static int count_inh__ON__RegexCharSetItem = 0;
	public static int count_syn__ON__RegexCharSetItem = 0;
	public static int count_local__ON__silver_definition_regex_regexSetChar = 0;
	public static int count_local__ON__silver_definition_regex_regexSetRange = 0;
	public static int count_inh__ON__RegexChar = 0;
	public static int count_syn__ON__RegexChar = 0;
	public static int count_local__ON__silver_definition_regex_regexChar = 0;
	public static int count_local__ON__silver_definition_regex_regexEscapedChar = 0;
	public static int count_local__ON__silver_definition_regex_regexConcatenate = 0;
	public static int count_local__ON__silver_definition_regex_concatRegexItems = 0;
	public static int count_local__ON__silver_definition_regex_regexCharToItem = 0;
	public static int count_local__ON__silver_definition_regex_regexLiteral = 0;
public static final int silver_definition_regex_regString__ON__silver_definition_regex_Regex = silver.definition.regex.Init.count_syn__ON__Regex++;
public static final int silver_definition_regex_regString__ON__silver_definition_regex_RegexSeq = silver.definition.regex.Init.count_syn__ON__RegexSeq++;
public static final int silver_definition_regex_regString__ON__silver_definition_regex_RegexRepetition = silver.definition.regex.Init.count_syn__ON__RegexRepetition++;
public static final int silver_definition_regex_regString__ON__silver_definition_regex_RegexItem = silver.definition.regex.Init.count_syn__ON__RegexItem++;
public static final int silver_definition_regex_regString__ON__silver_definition_regex_RegexCharSet = silver.definition.regex.Init.count_syn__ON__RegexCharSet++;
public static final int silver_definition_regex_regString__ON__silver_definition_regex_RegexCharSetItem = silver.definition.regex.Init.count_syn__ON__RegexCharSetItem++;
public static final int silver_definition_regex_regString__ON__silver_definition_regex_RegexChar = silver.definition.regex.Init.count_syn__ON__RegexChar++;
}
