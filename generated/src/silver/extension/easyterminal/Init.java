package silver.extension.easyterminal;

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
		silver.definition.regex.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.extension.easyterminal.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.regex.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.extension.easyterminal.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.regex.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.extension.easyterminal.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NRegExpr.decorators, PregExprEasyTerm.class);
		common.Decorator.applyDecorators(silver.extension.easyterminal.NEasyTerminalRef.decorators, PeasyTerminalRef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionRHSElem.decorators, PproductionRhsElemEasyReg.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionRHSElem.decorators, PproductionRhsElemTypeEasyReg.class);
		common.Decorator.applyDecorators(silver.definition.core.NAspectRHSElem.decorators, PaspectRHSElemEasyReg.class);
		common.Decorator.applyDecorators(silver.definition.core.NAspectRHSElem.decorators, PaspectRHSElemTypedEasyReg.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PterminalExprReg.class);
	}

	private static void setupInheritedAttributes(){
		silver.definition.env.NEnv.occurs_syn[silver.extension.easyterminal.Init.silver_extension_easyterminal_terminalTree__ON__silver_definition_env_Env] = "silver:extension:easyterminal:terminalTree";
		silver.extension.easyterminal.NEasyTerminalRef.occurs_inh[silver.extension.easyterminal.Init.silver_definition_env_config__ON__silver_extension_easyterminal_EasyTerminalRef] = "silver:definition:env:config";
		silver.extension.easyterminal.NEasyTerminalRef.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.easyterminal.NEasyTerminalRef.occurs_inh[silver.extension.easyterminal.Init.silver_definition_core_grammarName__ON__silver_extension_easyterminal_EasyTerminalRef] = "silver:definition:core:grammarName";
		silver.extension.easyterminal.NEasyTerminalRef.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.extension.easyterminal.NEasyTerminalRef.occurs_syn[silver.extension.easyterminal.Init.silver_definition_env_pp__ON__silver_extension_easyterminal_EasyTerminalRef] = "silver:definition:env:pp";
		silver.extension.easyterminal.NEasyTerminalRef.occurs_syn[silver.extension.easyterminal.Init.silver_definition_core_errors__ON__silver_extension_easyterminal_EasyTerminalRef] = "silver:definition:core:errors";
		silver.extension.easyterminal.NEasyTerminalRef.occurs_syn[silver.extension.easyterminal.Init.silver_definition_env_typerep__ON__silver_extension_easyterminal_EasyTerminalRef] = "silver:definition:env:typerep";
		silver.extension.easyterminal.NEasyTerminalRef.occurs_syn[silver.extension.easyterminal.Init.silver_extension_easyterminal_easyString__ON__silver_extension_easyterminal_EasyTerminalRef] = "silver:extension:easyterminal:easyString";
		silver.extension.easyterminal.NEasyTerminalRef.occurs_inh[silver.extension.easyterminal.Init.silver_definition_env_env__ON__silver_extension_easyterminal_EasyTerminalRef] = "silver:definition:env:env";
		silver.extension.easyterminal.NEasyTerminalRef.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.easyterminal.NEasyTerminalRef.occurs_syn[silver.extension.easyterminal.Init.silver_definition_core_dcls__ON__silver_extension_easyterminal_EasyTerminalRef] = "silver:definition:core:dcls";
		//	local attribute regHack::Regex;
		silver.extension.easyterminal.PeasyTerminalRef.localInheritedAttributes[silver.extension.easyterminal.Init.regHack__ON__silver_extension_easyterminal_easyTerminalRef] = new common.Lazy[silver.definition.regex.NRegex.num_inh_attrs];
		silver.extension.easyterminal.PeasyTerminalRef.occurs_local[silver.extension.easyterminal.Init.regHack__ON__silver_extension_easyterminal_easyTerminalRef] = "silver:extension:easyterminal:easyTerminalRef:local:regHack";
		silver.extension.easyterminal.PterminalExprReg.occurs_local[silver.extension.easyterminal.Init.escapedName__ON__silver_extension_easyterminal_terminalExprReg] = "silver:extension:easyterminal:terminalExprReg:local:escapedName";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION getTerminalRegexDclAll [DclInfo] ::= search::String e::Decorated Env 
		//FUNCTION filterAndConvertTermDcls [Pair<String DclInfo>] ::= ei::EnvItem sofar::[Pair<String DclInfo>] 
		//FUNCTION buildTerminalTree EnvTree<DclInfo> ::= eis::[EnvItem] 
		//ASPECT PRODUCTION i_emptyEnv top ::= 
		// top.terminalTree = [ emptyEnvScope() ]
		silver.definition.env.Pi_emptyEnv.synthesizedAttributes[silver.extension.easyterminal.Init.silver_extension_easyterminal_terminalTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PemptyEnvScope.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		//ASPECT PRODUCTION i_appendEnv top ::= e1::Decorated Env e2::Decorated Env 
		// top.terminalTree = e1.terminalTree ++ e2.terminalTree
		silver.definition.env.Pi_appendEnv.synthesizedAttributes[silver.extension.easyterminal.Init.silver_extension_easyterminal_terminalTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e1, silver.extension.easyterminal.Init.silver_extension_easyterminal_terminalTree__ON__silver_definition_env_Env), context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e2, silver.extension.easyterminal.Init.silver_extension_easyterminal_terminalTree__ON__silver_definition_env_Env))); } };
		//ASPECT PRODUCTION i_newScopeEnv top ::= d::Defs e::Decorated Env 
		// top.terminalTree = (oneEnvScope(buildTerminalTree(d.typeList)) :: e.terminalTree)
		silver.definition.env.Pi_newScopeEnv.synthesizedAttributes[silver.extension.easyterminal.Init.silver_extension_easyterminal_terminalTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PoneEnvScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.extension.easyterminal.PbuildTerminalTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_d, silver.definition.env.Init.silver_definition_env_typeList__ON__silver_definition_env_Defs))); } })); } }, context.childAsIsSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_e, silver.extension.easyterminal.Init.silver_extension_easyterminal_terminalTree__ON__silver_definition_env_Env))); } };
		silver.extension.easyterminal.PregExprEasyTerm.initProductionAttributeDefinitions();
		silver.extension.easyterminal.PeasyTerminalRef.initProductionAttributeDefinitions();
		silver.extension.easyterminal.PproductionRhsElemEasyReg.initProductionAttributeDefinitions();
		silver.extension.easyterminal.PproductionRhsElemTypeEasyReg.initProductionAttributeDefinitions();
		silver.extension.easyterminal.PaspectRHSElemEasyReg.initProductionAttributeDefinitions();
		silver.extension.easyterminal.PaspectRHSElemTypedEasyReg.initProductionAttributeDefinitions();
		silver.extension.easyterminal.PterminalExprReg.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_extension_easyterminal_getTerminalRegexDclAll = 0;
	public static int count_local__ON__silver_extension_easyterminal_filterAndConvertTermDcls = 0;
	public static int count_local__ON__silver_extension_easyterminal_buildTerminalTree = 0;
	public static int count_local__ON__silver_extension_easyterminal_regExprEasyTerm = 0;
	public static int count_inh__ON__EasyTerminalRef = 0;
	public static int count_syn__ON__EasyTerminalRef = 0;
	public static int count_local__ON__silver_extension_easyterminal_easyTerminalRef = 0;
	public static int count_local__ON__silver_extension_easyterminal_productionRhsElemEasyReg = 0;
	public static int count_local__ON__silver_extension_easyterminal_productionRhsElemTypeEasyReg = 0;
	public static int count_local__ON__silver_extension_easyterminal_aspectRHSElemEasyReg = 0;
	public static int count_local__ON__silver_extension_easyterminal_aspectRHSElemTypedEasyReg = 0;
	public static int count_local__ON__silver_extension_easyterminal_terminalExprReg = 0;
public static final int silver_extension_easyterminal_terminalTree__ON__silver_definition_env_Env = silver.definition.env.Init.count_syn__ON__Env++;
public static final int silver_definition_env_config__ON__silver_extension_easyterminal_EasyTerminalRef = silver.extension.easyterminal.Init.count_inh__ON__EasyTerminalRef++;
public static final int silver_definition_core_grammarName__ON__silver_extension_easyterminal_EasyTerminalRef = silver.extension.easyterminal.Init.count_inh__ON__EasyTerminalRef++;
public static final int silver_definition_env_pp__ON__silver_extension_easyterminal_EasyTerminalRef = silver.extension.easyterminal.Init.count_syn__ON__EasyTerminalRef++;
public static final int silver_definition_core_errors__ON__silver_extension_easyterminal_EasyTerminalRef = silver.extension.easyterminal.Init.count_syn__ON__EasyTerminalRef++;
public static final int silver_definition_env_typerep__ON__silver_extension_easyterminal_EasyTerminalRef = silver.extension.easyterminal.Init.count_syn__ON__EasyTerminalRef++;
public static final int silver_extension_easyterminal_easyString__ON__silver_extension_easyterminal_EasyTerminalRef = silver.extension.easyterminal.Init.count_syn__ON__EasyTerminalRef++;
public static final int silver_definition_env_env__ON__silver_extension_easyterminal_EasyTerminalRef = silver.extension.easyterminal.Init.count_inh__ON__EasyTerminalRef++;
public static final int silver_definition_core_dcls__ON__silver_extension_easyterminal_EasyTerminalRef = silver.extension.easyterminal.Init.count_syn__ON__EasyTerminalRef++;
public static final int regHack__ON__silver_extension_easyterminal_easyTerminalRef = silver.extension.easyterminal.Init.count_local__ON__silver_extension_easyterminal_easyTerminalRef++;
public static final int escapedName__ON__silver_extension_easyterminal_terminalExprReg = silver.extension.easyterminal.Init.count_local__ON__silver_extension_easyterminal_terminalExprReg++;
}
