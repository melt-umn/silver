package silver.modification.ffi.util;

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
		silver.modification.ffi.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.modification.ffi.util.Init.initAllStatics();

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
		silver.modification.ffi.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.modification.ffi.util.Init.init();

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
		silver.modification.ffi.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.modification.ffi.util.Init.postInit();


	}

	private static void setupInheritedAttributes(){
		silver.modification.ffi.util.PcleanStringEscapes.occurs_local[silver.modification.ffi.util.Init.i__ON__silver_modification_ffi_util_cleanStringEscapes] = "silver:modification:ffi:util:cleanStringEscapes:local:i";
		silver.modification.ffi.util.PcleanStringEscapes.occurs_local[silver.modification.ffi.util.Init.c__ON__silver_modification_ffi_util_cleanStringEscapes] = "silver:modification:ffi:util:cleanStringEscapes:local:c";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION substituteAll String ::= s::String names::[String] results::[String] 
		//FUNCTION wrapStrictNotation String ::= s::String 
		//FUNCTION wrapLazyNotation String ::= s::String 
		//FUNCTION cleanStringLexeme String ::= s::String 
		//FUNCTION cleanStringEscapes String ::= s::String 
		// i = indexOf("\\", s)
		silver.modification.ffi.util.PcleanStringEscapes.localAttributes[silver.modification.ffi.util.Init.i__ON__silver_modification_ffi_util_cleanStringEscapes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PindexOf.invoke((new common.StringCatter("\\")), context.childAsIsLazy(silver.modification.ffi.util.PcleanStringEscapes.i_s))); } };
		// c = substring(i + 1, i + 2, s)
		silver.modification.ffi.util.PcleanStringEscapes.localAttributes[silver.modification.ffi.util.Init.c__ON__silver_modification_ffi_util_cleanStringEscapes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.localAsIs(silver.modification.ffi.util.Init.i__ON__silver_modification_ffi_util_cleanStringEscapes)) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.localAsIs(silver.modification.ffi.util.Init.i__ON__silver_modification_ffi_util_cleanStringEscapes)) + Integer.valueOf((int)2)); } }, context.childAsIsLazy(silver.modification.ffi.util.PcleanStringEscapes.i_s))); } };
	}

	public static int count_local__ON__silver_modification_ffi_util_substituteAll = 0;
	public static int count_local__ON__silver_modification_ffi_util_wrapStrictNotation = 0;
	public static int count_local__ON__silver_modification_ffi_util_wrapLazyNotation = 0;
	public static int count_local__ON__silver_modification_ffi_util_cleanStringLexeme = 0;
	public static int count_local__ON__silver_modification_ffi_util_cleanStringEscapes = 0;
public static final int i__ON__silver_modification_ffi_util_cleanStringEscapes = silver.modification.ffi.util.Init.count_local__ON__silver_modification_ffi_util_cleanStringEscapes++;
public static final int c__ON__silver_modification_ffi_util_cleanStringEscapes = silver.modification.ffi.util.Init.count_local__ON__silver_modification_ffi_util_cleanStringEscapes++;
}
