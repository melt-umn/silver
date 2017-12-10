package silver.util.fixedmap;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.util.fixedmap.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.util.fixedmap.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.util.fixedmap.Init.postInit();


		common.Decorator.applyDecorators(silver.util.fixedmap.NMap.decorators, Pempty.class);
		common.Decorator.applyDecorators(silver.util.fixedmap.NMap.decorators, Pnode.class);
	}

	private static void setupInheritedAttributes(){
		silver.util.fixedmap.NMap.occurs_inh[silver.util.fixedmap.Init.silver_util_fixedmap_treeKey__ON__silver_util_fixedmap_Map] = "silver:util:fixedmap:treeKey";
		silver.util.fixedmap.NMap.occurs_syn[silver.util.fixedmap.Init.silver_util_fixedmap_treeLookup__ON__silver_util_fixedmap_Map] = "silver:util:fixedmap:treeLookup";
		silver.util.fixedmap.NMap.occurs_syn[silver.util.fixedmap.Init.silver_util_fixedmap_treeToList__ON__silver_util_fixedmap_Map] = "silver:util:fixedmap:treeToList";
		//	local attribute ltree::Map<b>;
		silver.util.fixedmap.PinternalBuildHelp.localInheritedAttributes[silver.util.fixedmap.Init.ltree__ON__silver_util_fixedmap_internalBuildHelp] = new common.Lazy[silver.util.fixedmap.NMap.num_inh_attrs];
		silver.util.fixedmap.PinternalBuildHelp.occurs_local[silver.util.fixedmap.Init.ltree__ON__silver_util_fixedmap_internalBuildHelp] = "silver:util:fixedmap:internalBuildHelp:local:ltree";
		//	local attribute rtree::Map<b>;
		silver.util.fixedmap.PinternalBuildHelp.localInheritedAttributes[silver.util.fixedmap.Init.rtree__ON__silver_util_fixedmap_internalBuildHelp] = new common.Lazy[silver.util.fixedmap.NMap.num_inh_attrs];
		silver.util.fixedmap.PinternalBuildHelp.occurs_local[silver.util.fixedmap.Init.rtree__ON__silver_util_fixedmap_internalBuildHelp] = "silver:util:fixedmap:internalBuildHelp:local:rtree";
		silver.util.fixedmap.PinternalBuildHelp.occurs_local[silver.util.fixedmap.Init.right_list__ON__silver_util_fixedmap_internalBuildHelp] = "silver:util:fixedmap:internalBuildHelp:local:right_list";
		silver.util.fixedmap.PinternalBuildHelp.occurs_local[silver.util.fixedmap.Init.middle__ON__silver_util_fixedmap_internalBuildHelp] = "silver:util:fixedmap:internalBuildHelp:local:middle";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION lookup [b] ::= key::String mp::Map<b> 
		// mp.treeKey = key
		silver.util.fixedmap.Plookup.childInheritedAttributes[silver.util.fixedmap.Plookup.i_mp][silver.util.fixedmap.Init.silver_util_fixedmap_treeKey__ON__silver_util_fixedmap_Map] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.util.fixedmap.Plookup.i_key)); } };
		//FUNCTION create Map<b> ::= lst::[Pair<String b>] 
		//FUNCTION toList [Pair<String b>] ::= mp::Map<b> 
		silver.util.fixedmap.Pempty.initProductionAttributeDefinitions();
		silver.util.fixedmap.Pnode.initProductionAttributeDefinitions();
		//FUNCTION treeMapKeyValues [Pair<a b>] ::= k::a v::[b] 
		//FUNCTION fstStringLte Boolean ::= l::Pair<String a> r::Pair<String b> 
		//FUNCTION fstStringEq Boolean ::= l::Pair<String a> r::Pair<String b> 
		//FUNCTION internalBuild Map<b> ::= collected::[[Pair<String b>]] 
		//FUNCTION internalBuildHelp Map<b> ::= collected::[[Pair<String b>]] upTo::Integer 
		// ltree = internalBuildHelp(collected, middle,)
		silver.util.fixedmap.PinternalBuildHelp.localAttributes[silver.util.fixedmap.Init.ltree__ON__silver_util_fixedmap_internalBuildHelp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.fixedmap.NMap)silver.util.fixedmap.PinternalBuildHelp.invoke(context.childAsIsLazy(silver.util.fixedmap.PinternalBuildHelp.i_collected), context.localAsIsLazy(silver.util.fixedmap.Init.middle__ON__silver_util_fixedmap_internalBuildHelp))); } };
		// rtree = internalBuildHelp(tail(right_list,), upTo - middle - 1,)
		silver.util.fixedmap.PinternalBuildHelp.localAttributes[silver.util.fixedmap.Init.rtree__ON__silver_util_fixedmap_internalBuildHelp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.fixedmap.NMap)silver.util.fixedmap.PinternalBuildHelp.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.localAsIsLazy(silver.util.fixedmap.Init.right_list__ON__silver_util_fixedmap_internalBuildHelp))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((Integer)context.childAsIs(silver.util.fixedmap.PinternalBuildHelp.i_upTo)) - ((Integer)context.localAsIs(silver.util.fixedmap.Init.middle__ON__silver_util_fixedmap_internalBuildHelp))) - Integer.valueOf((int)1)); } })); } };
		// right_list = drop(middle, collected,)
		silver.util.fixedmap.PinternalBuildHelp.localAttributes[silver.util.fixedmap.Init.right_list__ON__silver_util_fixedmap_internalBuildHelp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pdrop.invoke(context.localAsIsLazy(silver.util.fixedmap.Init.middle__ON__silver_util_fixedmap_internalBuildHelp), context.childAsIsLazy(silver.util.fixedmap.PinternalBuildHelp.i_collected))); } };
		// middle = toInt(toFloat(upTo) / 2.0)
		silver.util.fixedmap.PinternalBuildHelp.localAttributes[silver.util.fixedmap.Init.middle__ON__silver_util_fixedmap_internalBuildHelp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Float)Float.valueOf(Float.valueOf(((Integer)((Integer)context.childAsIs(silver.util.fixedmap.PinternalBuildHelp.i_upTo))).floatValue()) / Float.valueOf((float)2.0))).intValue()); } };
		//FUNCTION getSnd b ::= l::Pair<a b> 
	}

	public static int count_local__ON__silver_util_fixedmap_lookup = 0;
	public static int count_local__ON__silver_util_fixedmap_create = 0;
	public static int count_local__ON__silver_util_fixedmap_toList = 0;
	public static int count_inh__ON__Map = 0;
	public static int count_syn__ON__Map = 0;
	public static int count_local__ON__silver_util_fixedmap_empty = 0;
	public static int count_local__ON__silver_util_fixedmap_node = 0;
	public static int count_local__ON__silver_util_fixedmap_treeMapKeyValues = 0;
	public static int count_local__ON__silver_util_fixedmap_fstStringLte = 0;
	public static int count_local__ON__silver_util_fixedmap_fstStringEq = 0;
	public static int count_local__ON__silver_util_fixedmap_internalBuild = 0;
	public static int count_local__ON__silver_util_fixedmap_internalBuildHelp = 0;
	public static int count_local__ON__silver_util_fixedmap_getSnd = 0;
public static final int silver_util_fixedmap_treeKey__ON__silver_util_fixedmap_Map = silver.util.fixedmap.Init.count_inh__ON__Map++;
public static final int silver_util_fixedmap_treeLookup__ON__silver_util_fixedmap_Map = silver.util.fixedmap.Init.count_syn__ON__Map++;
public static final int silver_util_fixedmap_treeToList__ON__silver_util_fixedmap_Map = silver.util.fixedmap.Init.count_syn__ON__Map++;
public static final int ltree__ON__silver_util_fixedmap_internalBuildHelp = silver.util.fixedmap.Init.count_local__ON__silver_util_fixedmap_internalBuildHelp++;
public static final int rtree__ON__silver_util_fixedmap_internalBuildHelp = silver.util.fixedmap.Init.count_local__ON__silver_util_fixedmap_internalBuildHelp++;
public static final int right_list__ON__silver_util_fixedmap_internalBuildHelp = silver.util.fixedmap.Init.count_local__ON__silver_util_fixedmap_internalBuildHelp++;
public static final int middle__ON__silver_util_fixedmap_internalBuildHelp = silver.util.fixedmap.Init.count_local__ON__silver_util_fixedmap_internalBuildHelp++;
}
