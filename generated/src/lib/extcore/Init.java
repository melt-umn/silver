package lib.extcore;

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

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		lib.extcore.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		lib.extcore.Init.postInit();


	}

	private static void setupInheritedAttributes(){
		lib.extcore.PstripWhiteSpaceHelper.occurs_local[lib.extcore.Init.hd__ON__lib_extcore_stripWhiteSpaceHelper] = "lib:extcore:stripWhiteSpaceHelper:local:hd";
		lib.extcore.PstripExtraWhiteSpace.occurs_local[lib.extcore.Init.woLeadingOrEndingWhiteSpace__ON__lib_extcore_stripExtraWhiteSpace] = "lib:extcore:stripExtraWhiteSpace:local:woLeadingOrEndingWhiteSpace";
		lib.extcore.PstripExtraWhiteSpaceHelper.occurs_local[lib.extcore.Init.hd__ON__lib_extcore_stripExtraWhiteSpaceHelper] = "lib:extcore:stripExtraWhiteSpaceHelper:local:hd";
		lib.extcore.PstripExtraWhiteSpaceHelper.occurs_local[lib.extcore.Init.nxt__ON__lib_extcore_stripExtraWhiteSpaceHelper] = "lib:extcore:stripExtraWhiteSpaceHelper:local:nxt";
		lib.extcore.PaddLineNumbers.occurs_local[lib.extcore.Init.lines__ON__lib_extcore_addLineNumbers] = "lib:extcore:addLineNumbers:local:lines";
		lib.extcore.PaddLineNums.occurs_local[lib.extcore.Init.ln__ON__lib_extcore_addLineNums] = "lib:extcore:addLineNums:local:ln";
		lib.extcore.PaddLineNums.occurs_local[lib.extcore.Init.pad__ON__lib_extcore_addLineNums] = "lib:extcore:addLineNums:local:pad";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION toStringFromInteger String ::= v::Integer 
		//FUNCTION toStringFromFloat String ::= v::Float 
		//FUNCTION toStringFromBoolean String ::= v::Boolean 
		//FUNCTION toStringFromString String ::= v::String 
		//FUNCTION toStringFromList String ::= toStr::(String ::= a) xs::[a] 
		//FUNCTION toStringFromListHelper String ::= toStr::(String ::= a) xs::[a] 
		//FUNCTION stripWhiteSpace String ::= s::String 
		//FUNCTION stripWhiteSpaceHelper [String] ::= ss::[String] 
		// hd = head(ss)
		lib.extcore.PstripWhiteSpaceHelper.localAttributes[lib.extcore.Init.hd__ON__lib_extcore_stripWhiteSpaceHelper] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(lib.extcore.PstripWhiteSpaceHelper.i_ss))); } };
		//FUNCTION replaceChars String ::= toReplace::String replaceWith::String str::String 
		//FUNCTION replaceCharsHelper [String] ::= toReplace::String replaceWith::String chars::[String] 
		//FUNCTION stripExtraWhiteSpace String ::= str::String 
		// woLeadingOrEndingWhiteSpace = reverse((dropWhile(isSpace, reverse(dropWhile(isSpace, explode("", str))))))
		lib.extcore.PstripExtraWhiteSpace.localAttributes[lib.extcore.Init.woLeadingOrEndingWhiteSpace__ON__lib_extcore_stripExtraWhiteSpace] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Preverse.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PdropWhile.invoke(core.PisSpace.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Preverse.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PdropWhile.invoke(core.PisSpace.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pexplode.invoke((new common.StringCatter("")), context.childAsIsLazy(lib.extcore.PstripExtraWhiteSpace.i_str))); } })); } })); } })); } })); } };
		//FUNCTION stripExtraWhiteSpaceHelper [String] ::= ss::[String] 
		// hd = head(ss)
		lib.extcore.PstripExtraWhiteSpaceHelper.localAttributes[lib.extcore.Init.hd__ON__lib_extcore_stripExtraWhiteSpaceHelper] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(lib.extcore.PstripExtraWhiteSpaceHelper.i_ss))); } };
		// nxt = head(tail(ss))
		lib.extcore.PstripExtraWhiteSpaceHelper.localAttributes[lib.extcore.Init.nxt__ON__lib_extcore_stripExtraWhiteSpaceHelper] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(lib.extcore.PstripExtraWhiteSpaceHelper.i_ss))); } })); } };
		//FUNCTION isNotWhiteSpace Boolean ::= str::String 
		//FUNCTION addLineNumbers String ::= code::String 
		// lines = explode("\n", code)
		lib.extcore.PaddLineNumbers.localAttributes[lib.extcore.Init.lines__ON__lib_extcore_addLineNumbers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pexplode.invoke((new common.StringCatter("\n")), context.childAsIsLazy(lib.extcore.PaddLineNumbers.i_code))); } };
		//FUNCTION addLineNums String ::= next::Integer width::Integer lines::[String] 
		// ln = toString(next)
		lib.extcore.PaddLineNums.localAttributes[lib.extcore.Init.ln__ON__lib_extcore_addLineNums] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)context.childAsIs(lib.extcore.PaddLineNums.i_next)))); } };
		// pad = implode("", repeat(" ", width - length(ln)))
		lib.extcore.PaddLineNums.localAttributes[lib.extcore.Init.pad__ON__lib_extcore_addLineNums] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Prepeat.invoke((new common.StringCatter(" ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(lib.extcore.PaddLineNums.i_width)) - Integer.valueOf(((common.StringCatter)((common.StringCatter)context.localAsIs(lib.extcore.Init.ln__ON__lib_extcore_addLineNums))).length())); } })); } })); } };
		//FUNCTION equalsInteger Boolean ::= a::Integer b::Integer 
		//FUNCTION equalsFloat Boolean ::= a::Float b::Float 
		//FUNCTION equalsString Boolean ::= a::String b::String 
		//FUNCTION equalsBoolean Boolean ::= a::Boolean b::Boolean 
		//FUNCTION equalsList Boolean ::= eq::(Boolean ::= a a) l1::[a] l2::[a] 
		//FUNCTION notEqualsInteger Boolean ::= a::Integer b::Integer 
		//FUNCTION notEqualsFloat Boolean ::= a::Float b::Float 
		//FUNCTION notEqualsString Boolean ::= a::String b::String 
		//FUNCTION notEqualsBoolean Boolean ::= a::Boolean b::Boolean 
		//FUNCTION notEqualsList Boolean ::= neq::(Boolean ::= a a) l1::[a] l2::[a] 
	}

	public static int count_local__ON__lib_extcore_toStringFromInteger = 0;
	public static int count_local__ON__lib_extcore_toStringFromFloat = 0;
	public static int count_local__ON__lib_extcore_toStringFromBoolean = 0;
	public static int count_local__ON__lib_extcore_toStringFromString = 0;
	public static int count_local__ON__lib_extcore_toStringFromList = 0;
	public static int count_local__ON__lib_extcore_toStringFromListHelper = 0;
	public static int count_local__ON__lib_extcore_stripWhiteSpace = 0;
	public static int count_local__ON__lib_extcore_stripWhiteSpaceHelper = 0;
	public static int count_local__ON__lib_extcore_replaceChars = 0;
	public static int count_local__ON__lib_extcore_replaceCharsHelper = 0;
	public static int count_local__ON__lib_extcore_stripExtraWhiteSpace = 0;
	public static int count_local__ON__lib_extcore_stripExtraWhiteSpaceHelper = 0;
	public static int count_local__ON__lib_extcore_isNotWhiteSpace = 0;
	public static int count_local__ON__lib_extcore_addLineNumbers = 0;
	public static int count_local__ON__lib_extcore_addLineNums = 0;
	public static int count_local__ON__lib_extcore_equalsInteger = 0;
	public static int count_local__ON__lib_extcore_equalsFloat = 0;
	public static int count_local__ON__lib_extcore_equalsString = 0;
	public static int count_local__ON__lib_extcore_equalsBoolean = 0;
	public static int count_local__ON__lib_extcore_equalsList = 0;
	public static int count_local__ON__lib_extcore_notEqualsInteger = 0;
	public static int count_local__ON__lib_extcore_notEqualsFloat = 0;
	public static int count_local__ON__lib_extcore_notEqualsString = 0;
	public static int count_local__ON__lib_extcore_notEqualsBoolean = 0;
	public static int count_local__ON__lib_extcore_notEqualsList = 0;
public static final int hd__ON__lib_extcore_stripWhiteSpaceHelper = lib.extcore.Init.count_local__ON__lib_extcore_stripWhiteSpaceHelper++;
public static final int woLeadingOrEndingWhiteSpace__ON__lib_extcore_stripExtraWhiteSpace = lib.extcore.Init.count_local__ON__lib_extcore_stripExtraWhiteSpace++;
public static final int hd__ON__lib_extcore_stripExtraWhiteSpaceHelper = lib.extcore.Init.count_local__ON__lib_extcore_stripExtraWhiteSpaceHelper++;
public static final int nxt__ON__lib_extcore_stripExtraWhiteSpaceHelper = lib.extcore.Init.count_local__ON__lib_extcore_stripExtraWhiteSpaceHelper++;
public static final int lines__ON__lib_extcore_addLineNumbers = lib.extcore.Init.count_local__ON__lib_extcore_addLineNumbers++;
public static final int ln__ON__lib_extcore_addLineNums = lib.extcore.Init.count_local__ON__lib_extcore_addLineNums++;
public static final int pad__ON__lib_extcore_addLineNums = lib.extcore.Init.count_local__ON__lib_extcore_addLineNums++;
}
