package silver.modification.autocopyattr.java;

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
		silver.util.Init.initAllStatics();
		silver.translation.java.type.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.modification.autocopyattr.Init.initAllStatics();
		silver.modification.autocopyattr.java.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.util.Init.init();
		silver.translation.java.type.Init.init();
		silver.translation.java.core.Init.init();
		silver.definition.type.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.modification.autocopyattr.Init.init();
		silver.modification.autocopyattr.java.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.util.Init.postInit();
		silver.translation.java.type.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.modification.autocopyattr.Init.postInit();
		silver.modification.autocopyattr.java.Init.postInit();


	}

	private static void setupInheritedAttributes(){
		silver.modification.autocopyattr.PattributeDclAuto.occurs_local[silver.modification.autocopyattr.java.Init.className__ON__silver_modification_autocopyattr_attributeDclAuto] = "silver:modification:autocopyattr:attributeDclAuto:local:className";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION attributeDclAuto top ::= 'autocopy' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';' 
		// className = "D" ++ a.name
		silver.modification.autocopyattr.PattributeDclAuto.localAttributes[silver.modification.autocopyattr.java.Init.className__ON__silver_modification_autocopyattr_attributeDclAuto] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("D")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.autocopyattr.PattributeDclAuto.i_a).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name))); } };
		// top.genFiles := [ pair(className ++ ".java", "package " ++ makeName(top.grammarName) ++ ";\n\n" ++ "import java.util.*;\n\n" ++ "public class " ++ className ++ " extends common.Decorator {\n\n" ++ "public static final " ++ className ++ " singleton = new " ++ className ++ "();\n\n" ++ "\tpublic void decorate(Class production) {\n" ++ "\t\tdecorateAutoCopy(production, \"" ++ fName ++ "\");\n" ++ "\t}\n" ++ "}\n") ]
		if(silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl] == null)
			silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAgenFiles(silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.autocopyattr.PattributeDclAuto.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.autocopyattr.java.Init.className__ON__silver_modification_autocopyattr_attributeDclAuto)), (common.StringCatter)(new common.StringCatter(".java"))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("package ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeName.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("import java.util.*;\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("public class ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.autocopyattr.java.Init.className__ON__silver_modification_autocopyattr_attributeDclAuto)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" extends common.Decorator {\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("public static final ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.autocopyattr.java.Init.className__ON__silver_modification_autocopyattr_attributeDclAuto)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" singleton = new ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.autocopyattr.java.Init.className__ON__silver_modification_autocopyattr_attributeDclAuto)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("();\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\tpublic void decorate(Class production) {\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\t\tdecorateAutoCopy(production, \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.autocopyattr.Init.fName__ON__silver_modification_autocopyattr_attributeDclAuto)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\");\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\t}\n")), (common.StringCatter)(new common.StringCatter("}\n"))))))))))))))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//ASPECT PRODUCTION attributionDcl top ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';' 
		// top.setupInh <- if at.lookupAttribute.dcl.isAutocopy then "\t\t" ++ makeNTClassName(nt.lookupType.fullName) ++ ".decorators.add(" ++ makeDecoratorClassName(at.lookupAttribute.fullName) ++ ".singleton);\n" else ""
		if(silver.definition.core.PattributionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PattributionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAsetupInh(silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PattributionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.modification.autocopyattr.Init.silver_modification_autocopyattr_isAutocopy__ON__silver_definition_env_DclInfo)) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("\t\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeNTClassName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".decorators.add(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.autocopyattr.java.PmakeDecoratorClassName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.definition.core.PattributionDcl.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })), (common.StringCatter)(new common.StringCatter(".singleton);\n")))))) : (new common.StringCatter(""))); } });
		//FUNCTION makeDecoratorClassName String ::= s::String 
	}

	public static int count_local__ON__silver_modification_autocopyattr_java_makeDecoratorClassName = 0;
public static final int className__ON__silver_modification_autocopyattr_attributeDclAuto = silver.modification.autocopyattr.Init.count_local__ON__silver_modification_autocopyattr_attributeDclAuto++;
}
