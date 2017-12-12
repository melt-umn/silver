package silver.modification.ffi.java;

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
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.translation.java.type.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.modification.ffi.util.Init.initAllStatics();
		silver.modification.ffi.Init.initAllStatics();
		silver.modification.ffi.java.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.translation.java.type.Init.init();
		silver.translation.java.core.Init.init();
		silver.modification.ffi.util.Init.init();
		silver.modification.ffi.Init.init();
		silver.modification.ffi.java.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.translation.java.type.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.modification.ffi.util.Init.postInit();
		silver.modification.ffi.Init.postInit();
		silver.modification.ffi.java.Init.postInit();


	}

	private static void setupInheritedAttributes(){
		silver.modification.ffi.NFFIDef.occurs_syn[silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDef] = "silver:modification:ffi:java:ffiTranslationString";
		silver.modification.ffi.NFFIDefs.occurs_syn[silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDefs] = "silver:modification:ffi:java:ffiTranslationString";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION foreignType top ::= fn::String params::[Type] 
		// top.transType = "Object"
		silver.modification.ffi.PforeignType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Object")); } };
		// top.transClassType = "Object"
		silver.modification.ffi.PforeignType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Object")); } };
		//ASPECT PRODUCTION ffidefsOne top ::= one::FFIDef 
		// top.ffiTranslationString = one.ffiTranslationString
		silver.modification.ffi.PffidefsOne.synthesizedAttributes[silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.ffi.PffidefsOne.i_one).synthesized(silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDef)); } };
		//ASPECT PRODUCTION ffidefsMany top ::= one::FFIDef more::FFIDefs 
		// top.ffiTranslationString = one.ffiTranslationString ++ more.ffiTranslationString
		silver.modification.ffi.PffidefsMany.synthesizedAttributes[silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.ffi.PffidefsMany.i_one, silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDef), context.childDecoratedSynthesizedLazy(silver.modification.ffi.PffidefsMany.i_more, silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDefs))); } };
		//ASPECT PRODUCTION ffidef top ::= name::String_t ':' 'return' code::String_t ';' 
		// top.ffiTranslationString = if name.lexeme == "\"java\"" then [ cleanStringLexeme(code.lexeme) ] else []
		silver.modification.ffi.Pffidef.synthesizedAttributes[silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.ffi.Pffidef.i_name)).lexeme).equals((new common.StringCatter("\"java\""))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.modification.ffi.util.PcleanStringLexeme.invoke(((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.ffi.Pffidef.i_code)).lexeme))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } };
		//FUNCTION strictChildAccessor String ::= ns::NamedSignatureElement 
		//FUNCTION lazyChildAccessor String ::= ns::NamedSignatureElement 
		//FUNCTION computeSigTranslation String ::= str::String sig::NamedSignature 
		//ASPECT PRODUCTION functionDclFFI top ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}' 
		// top.setupInh := if null(ffidefs.ffiTranslationString) then forward.setupInh else ""
		if(silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl] == null)
			silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAsetupInh(silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.modification.ffi.PfunctionDclFFI.i_ffidefs, silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDefs))) ? ((common.StringCatter)context.forward().synthesized(silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl)) : (new common.StringCatter(""))); } });
		// top.initProd := if null(ffidefs.ffiTranslationString) then forward.initProd else ""
		if(silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl] == null)
			silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAinitProd(silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.modification.ffi.PfunctionDclFFI.i_ffidefs, silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDefs))) ? ((common.StringCatter)context.forward().synthesized(silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl)) : (new common.StringCatter(""))); } });
		// top.initValues := ""
		if(silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initValues__ON__silver_definition_core_AGDcl] == null)
			silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initValues__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAinitValues(silver.translation.java.core.Init.silver_translation_java_core_initValues__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initValues__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } });
		// top.postInit := ""
		if(silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_postInit__ON__silver_definition_core_AGDcl] == null)
			silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_postInit__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CApostInit(silver.translation.java.core.Init.silver_translation_java_core_postInit__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_postInit__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } });
		// top.genFiles := if null(ffidefs.ffiTranslationString) then forward.genFiles else [ pair("P" ++ id.name ++ ".java", generateFunctionClassString(top.grammarName, id.name, namedSig, "return (" ++ namedSig.outputElement.typerep.transType ++ ")" ++ computeSigTranslation(head(ffidefs.ffiTranslationString), namedSig) ++ ";\n")) ]
		if(silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl] == null)
			silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAgenFiles(silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.modification.ffi.PfunctionDclFFI.i_ffidefs, silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDefs))) ? ((common.ConsCell)context.forward().synthesized(silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl)) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("P")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.ffi.PfunctionDclFFI.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)(new common.StringCatter(".java")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PgenerateFunctionClassString.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.modification.ffi.PfunctionDclFFI.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.ffi.Init.namedSig__ON__silver_modification_ffi_functionDclFFI)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("return (")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)context.localDecorated(silver.modification.ffi.Init.namedSig__ON__silver_modification_ffi_functionDclFFI).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(")")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.ffi.java.PcomputeSigTranslation.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childDecoratedSynthesizedLazy(silver.modification.ffi.PfunctionDclFFI.i_ffidefs, silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDefs))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.ffi.Init.namedSig__ON__silver_modification_ffi_functionDclFFI)))), (common.StringCatter)(new common.StringCatter(";\n")))))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.errors <- if length(ffidefs.ffiTranslationString) > 1 then [ err(ffidefs.location, "There is more than one Java translation in this FFI function") ] else []
		if(silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.modification.ffi.PfunctionDclFFI.i_ffidefs, silver.modification.ffi.java.Init.silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDefs))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.ffi.NFFIDefs)context.childDecorated(silver.modification.ffi.PfunctionDclFFI.i_ffidefs).undecorate()).getAnno_core_location()); } }, (new common.StringCatter("There is more than one Java translation in this FFI function")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
	}

	public static int count_local__ON__silver_modification_ffi_java_strictChildAccessor = 0;
	public static int count_local__ON__silver_modification_ffi_java_lazyChildAccessor = 0;
	public static int count_local__ON__silver_modification_ffi_java_computeSigTranslation = 0;
public static final int silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDef = silver.modification.ffi.Init.count_syn__ON__FFIDef++;
public static final int silver_modification_ffi_java_ffiTranslationString__ON__silver_modification_ffi_FFIDefs = silver.modification.ffi.Init.count_syn__ON__FFIDefs++;
}
