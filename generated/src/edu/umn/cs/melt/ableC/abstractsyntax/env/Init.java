package edu.umn.cs.melt.ableC.abstractsyntax.env;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.util.raw.treemap.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.initAllStatics();
		silver.langutil.pp.Init.initAllStatics();
		silver.langutil.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.util.raw.treemap.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.init();
		silver.langutil.pp.Init.init();
		silver.langutil.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.util.raw.treemap.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postInit();
		silver.langutil.pp.Init.postInit();
		silver.langutil.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.env.Init.postInit();


		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.decorators, PlabelDef.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.decorators, PtagDef.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.decorators, PvalueDef.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.decorators, PrefIdDef.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.decorators, PmiscDef.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.decorators, PglobalDefsDef.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NMiscItem.decorators, PemptyMiscItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NMiscItem.decorators, PcurrentFunctionItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.decorators, PdeclaratorValueItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.decorators, PfunctionValueItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.decorators, PbuiltinFunctionValueItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.decorators, PfieldValueItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.decorators, PenumValueItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.decorators, PparameterValueItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.decorators, PerrorValueItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.decorators, PemptyEnv_i.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.decorators, PaddEnv_i.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.decorators, PopenScope_i.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.decorators, PglobalEnv_i.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.decorators, PnilDefs.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.decorators, PconsDefs.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NLabelItem.decorators, PlabelItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NLabelItem.decorators, PerrorLabelItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem.decorators, PstructRefIdItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem.decorators, PunionRefIdItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem.decorators, PenumTagItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem.decorators, PrefIdTagItem.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem.decorators, PerrorTagItem.class);
	}

	private static void setupInheritedAttributes(){
		edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = "edu:umn:cs:melt:ableC:abstractsyntax:host:typerep";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_sourceLocation__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = "edu:umn:cs:melt:ableC:abstractsyntax:env:sourceLocation";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_directCallHandler__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = "edu:umn:cs:melt:ableC:abstractsyntax:env:directCallHandler";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_isItemTypedef__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = "edu:umn:cs:melt:ableC:abstractsyntax:env:isItemTypedef";
		//	local attribute gd::Defs;
		edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.localInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.gd__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.num_inh_attrs];
		edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.occurs_local[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.gd__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i] = "edu:umn:cs:melt:ableC:abstractsyntax:env:addEnv_i:local:gd";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = "silver:langutil:pp";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_TagItem] = "silver:langutil:pp";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_RefIdItem] = "silver:langutil:pp";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labels__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = "edu:umn:cs:melt:ableC:abstractsyntax:env:labels";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tags__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = "edu:umn:cs:melt:ableC:abstractsyntax:env:tags";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_values__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = "edu:umn:cs:melt:ableC:abstractsyntax:env:values";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIds__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = "edu:umn:cs:melt:ableC:abstractsyntax:env:refIds";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NEnv.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_misc__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env] = "edu:umn:cs:melt:ableC:abstractsyntax:env:misc";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labelContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = "edu:umn:cs:melt:ableC:abstractsyntax:env:labelContribs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tagContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = "edu:umn:cs:melt:ableC:abstractsyntax:env:tagContribs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_valueContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = "edu:umn:cs:melt:ableC:abstractsyntax:env:valueContribs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIdContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = "edu:umn:cs:melt:ableC:abstractsyntax:env:refIdContribs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_miscContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = "edu:umn:cs:melt:ableC:abstractsyntax:env:miscContribs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDefs.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs] = "edu:umn:cs:melt:ableC:abstractsyntax:env:globalDefs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labelContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = "edu:umn:cs:melt:ableC:abstractsyntax:env:labelContribs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tagContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = "edu:umn:cs:melt:ableC:abstractsyntax:env:tagContribs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_valueContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = "edu:umn:cs:melt:ableC:abstractsyntax:env:valueContribs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIdContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = "edu:umn:cs:melt:ableC:abstractsyntax:env:refIdContribs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_miscContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = "edu:umn:cs:melt:ableC:abstractsyntax:env:miscContribs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = "edu:umn:cs:melt:ableC:abstractsyntax:env:globalDefs";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_moduleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_RefIdItem] = "edu:umn:cs:melt:ableC:abstractsyntax:host:moduleName";
		edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem.occurs_syn[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tagEnv__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_RefIdItem] = "edu:umn:cs:melt:ableC:abstractsyntax:env:tagEnv";
	}

	private static void initProductionAttributeDefinitions(){
		edu.umn.cs.melt.ableC.abstractsyntax.env.PlabelDef.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PtagDef.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PvalueDef.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PrefIdDef.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PmiscDef.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PglobalDefsDef.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PemptyMiscItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PcurrentFunctionItem.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for ValueItem
		// top.directCallHandler = ordinaryFunctionHandler
		edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.defaultSynthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_directCallHandler__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return edu.umn.cs.melt.ableC.abstractsyntax.host.PordinaryFunctionHandler.factory; } };
		// top.isItemTypedef = false
		edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem.defaultSynthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_isItemTypedef__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		edu.umn.cs.melt.ableC.abstractsyntax.env.PdeclaratorValueItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PfunctionValueItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PbuiltinFunctionValueItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PfieldValueItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PenumValueItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PparameterValueItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PerrorValueItem.initProductionAttributeDefinitions();
		//FUNCTION augmentScope_i Scope<a> ::= d::Contribs<a> s::Scope<a> 
		//FUNCTION augmentGlobalScope_i Scope<a> ::= d::Contribs<a> s::Scope<a> 
		//FUNCTION readScope_i [a] ::= name::String scope::Scope<a> 
		edu.umn.cs.melt.ableC.abstractsyntax.env.PemptyEnv_i.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv_i.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope_i.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PglobalEnv_i.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PnilDefs.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PconsDefs.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for Def
		// top.labelContribs = []
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.defaultSynthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_labelContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.tagContribs = []
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.defaultSynthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_tagContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.valueContribs = []
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.defaultSynthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_valueContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.refIdContribs = []
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.defaultSynthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_refIdContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.miscContribs = []
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.defaultSynthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_miscContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.globalDefs = []
		edu.umn.cs.melt.ableC.abstractsyntax.env.NDef.defaultSynthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		edu.umn.cs.melt.ableC.abstractsyntax.env.PlabelItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PerrorLabelItem.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION declaratorValueItem top ::= s::Decorated Declarator 
		// top.pp = ppImplode(text(" ",), s.pps,)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PdeclaratorValueItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppImplode.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter(" ")))); } }, context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PdeclaratorValueItem.i_s, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_pps__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Declarator))); } };
		//ASPECT PRODUCTION functionValueItem top ::= s::Decorated FunctionDecl 
		// top.pp = text("FunctionDecl",)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PfunctionValueItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("FunctionDecl")))); } };
		//ASPECT PRODUCTION builtinFunctionValueItem top ::= t::Type handler::(Expr ::= Name Exprs Location) 
		// top.pp = text("BuiltinFunctionValueItem",)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PbuiltinFunctionValueItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("BuiltinFunctionValueItem")))); } };
		//ASPECT PRODUCTION fieldValueItem top ::= s::Decorated StructDeclarator 
		// top.pp = text("StructDeclarator",)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PfieldValueItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("StructDeclarator")))); } };
		//ASPECT PRODUCTION enumValueItem top ::= s::Decorated EnumItem 
		// top.pp = text("EnumItem",)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PenumValueItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("EnumItem")))); } };
		//ASPECT PRODUCTION parameterValueItem top ::= s::Decorated ParameterDecl 
		// top.pp = text("ParameterDecl",)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PparameterValueItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("ParameterDecl")))); } };
		//ASPECT PRODUCTION errorValueItem top ::= 
		// top.pp = text("errorValueItem",)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PerrorValueItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("errorValueItem")))); } };
		//ASPECT PRODUCTION enumTagItem top ::= s::Decorated EnumDecl 
		// top.pp = text("Decorated Enum",)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PenumTagItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_TagItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("Decorated Enum")))); } };
		//ASPECT PRODUCTION refIdTagItem top ::= tag::StructOrEnumOrUnion refId::String 
		// top.pp = text("Struct|Enum, refId = " ++ refId,)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PrefIdTagItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_TagItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Struct|Enum, refId = ")), (common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.env.PrefIdTagItem.i_refId))); } })); } };
		//ASPECT PRODUCTION errorTagItem top ::= 
		// top.pp = text("ErrorTagItem",)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PerrorTagItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_TagItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("ErrorTagItem")))); } };
		//ASPECT PRODUCTION structRefIdItem top ::= s::Decorated StructDecl 
		// top.pp = text("StructDecl: s.refId=" ++ s.refId,)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PstructRefIdItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_RefIdItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("StructDecl: s.refId=")), (common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.env.PstructRefIdItem.i_s)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_refId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StructDecl))); } })); } };
		//ASPECT PRODUCTION unionRefIdItem top ::= s::Decorated UnionDecl 
		// top.pp = text("UnionDecl",)
		edu.umn.cs.melt.ableC.abstractsyntax.env.PunionRefIdItem.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.env.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_RefIdItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("UnionDecl")))); } };
		//FUNCTION emptyEnv Decorated Env ::= 
		//FUNCTION addEnv Decorated Env ::= d::[Def] e::Decorated Env 
		//FUNCTION addEnvDefs Decorated Env ::= d::Defs e::Decorated Env 
		//FUNCTION openScope Decorated Env ::= e::Decorated Env 
		//FUNCTION globalEnv Decorated Env ::= e::Decorated Env 
		//FUNCTION lookupValue [ValueItem] ::= n::String e::Decorated Env 
		//FUNCTION lookupTag [TagItem] ::= n::String e::Decorated Env 
		//FUNCTION lookupLabel [LabelItem] ::= n::String e::Decorated Env 
		//FUNCTION lookupRefId [RefIdItem] ::= n::String e::Decorated Env 
		//FUNCTION lookupMisc [MiscItem] ::= n::String e::Decorated Env 
		//FUNCTION lookupValueInLocalScope [ValueItem] ::= n::String e::Decorated Env 
		//FUNCTION lookupTagInLocalScope [TagItem] ::= n::String e::Decorated Env 
		//FUNCTION lookupLabelInLocalScope [LabelItem] ::= n::String e::Decorated Env 
		//FUNCTION lookupMiscInLocalScope [MiscItem] ::= n::String e::Decorated Env 
		edu.umn.cs.melt.ableC.abstractsyntax.env.PstructRefIdItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PunionRefIdItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PenumTagItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PrefIdTagItem.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.abstractsyntax.env.PerrorTagItem.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_labelDef = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_tagDef = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_valueDef = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_refIdDef = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_miscDef = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefsDef = 0;
	public static int count_inh__ON__MiscItem = 0;
	public static int count_syn__ON__MiscItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_emptyMiscItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_currentFunctionItem = 0;
	public static int count_inh__ON__ValueItem = 0;
	public static int count_syn__ON__ValueItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_declaratorValueItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_functionValueItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_builtinFunctionValueItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_fieldValueItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_enumValueItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_parameterValueItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_errorValueItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_augmentScope_i = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_augmentGlobalScope_i = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_readScope_i = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_emptyEnv_i = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_openScope_i = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_globalEnv_i = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_nilDefs = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_consDefs = 0;
	public static int count_inh__ON__LabelItem = 0;
	public static int count_syn__ON__LabelItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_labelItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_errorLabelItem = 0;
	public static int count_inh__ON__Env = 0;
	public static int count_syn__ON__Env = 0;
	public static int count_inh__ON__Defs = 0;
	public static int count_syn__ON__Defs = 0;
	public static int count_inh__ON__Def = 0;
	public static int count_syn__ON__Def = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_emptyEnv = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnvDefs = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_openScope = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_globalEnv = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_lookupValue = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_lookupTag = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_lookupLabel = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_lookupRefId = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_lookupMisc = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_lookupValueInLocalScope = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_lookupTagInLocalScope = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_lookupLabelInLocalScope = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_lookupMiscInLocalScope = 0;
	public static int count_inh__ON__RefIdItem = 0;
	public static int count_syn__ON__RefIdItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_structRefIdItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_unionRefIdItem = 0;
	public static int count_inh__ON__TagItem = 0;
	public static int count_syn__ON__TagItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_enumTagItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_refIdTagItem = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_errorTagItem = 0;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__ValueItem++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_sourceLocation__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__ValueItem++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_directCallHandler__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__ValueItem++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_isItemTypedef__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__ValueItem++;
public static final int gd__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_addEnv_i++;
public static final int silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__ValueItem++;
public static final int silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_TagItem = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__TagItem++;
public static final int silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_RefIdItem = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__RefIdItem++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_labels__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Env++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_tags__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Env++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_values__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Env++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_refIds__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Env++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_misc__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Env = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Env++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_labelContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Defs++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_tagContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Defs++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_valueContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Defs++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_refIdContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Defs++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_miscContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Defs++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Defs = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Defs++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_labelContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Def++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_tagContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Def++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_valueContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Def++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_refIdContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Def++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_miscContribs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Def++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_globalDefs__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_Def = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__Def++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_host_moduleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_RefIdItem = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__RefIdItem++;
public static final int edu_umn_cs_melt_ableC_abstractsyntax_env_tagEnv__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_RefIdItem = edu.umn.cs.melt.ableC.abstractsyntax.env.Init.count_syn__ON__RefIdItem++;
}
