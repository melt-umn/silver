package silver.util;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.util.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.util.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.util.Init.postInit();


	}

	private static void setupInheritedAttributes(){
		silver.util.PmakeSet.occurs_local[silver.util.Init.recurse__ON__silver_util_makeSet] = "silver:util:makeSet:local:recurse";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION contains Boolean ::= s::String sl::[String] 
		//FUNCTION containsSet Boolean ::= s::[String] sl::[[String]] 
		//FUNCTION containsDuplicates Boolean ::= s::[String] 
		//FUNCTION equals Boolean ::= s1::[String] s2::[String] 
		//FUNCTION containsAll Boolean ::= s1::[String] s2::[String] 
		//FUNCTION containsAny Boolean ::= s1::[String] s2::[String] 
		//FUNCTION makeSet [String] ::= list::[String] 
		// recurse = makeSet(tail(list))
		silver.util.PmakeSet.localAttributes[silver.util.Init.recurse__ON__silver_util_makeSet] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.util.PmakeSet.i_list))); } })); } };
		//FUNCTION rem [String] ::= n::[String] seen::[String] 
		//FUNCTION remove [String] ::= n::String s::[String] 
		//FUNCTION startsWithAny Boolean ::= pre::[String] s::String 
	}

	public static int count_local__ON__silver_util_contains = 0;
	public static int count_local__ON__silver_util_containsSet = 0;
	public static int count_local__ON__silver_util_containsDuplicates = 0;
	public static int count_local__ON__silver_util_equals = 0;
	public static int count_local__ON__silver_util_containsAll = 0;
	public static int count_local__ON__silver_util_containsAny = 0;
	public static int count_local__ON__silver_util_makeSet = 0;
	public static int count_local__ON__silver_util_rem = 0;
	public static int count_local__ON__silver_util_remove = 0;
	public static int count_local__ON__silver_util_startsWithAny = 0;
public static final int recurse__ON__silver_util_makeSet = silver.util.Init.count_local__ON__silver_util_makeSet++;
}
