package silver.util.treemap;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.util.treemap.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.util.treemap.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.util.treemap.Init.postInit();


		common.Decorator.applyDecorators(silver.util.treemap.NTreeMap.decorators, Pleaf.class);
		common.Decorator.applyDecorators(silver.util.treemap.NTreeMap.decorators, Pnode.class);
	}

	private static void setupInheritedAttributes(){
		silver.util.treemap.NTreeMap.occurs_inh[silver.util.treemap.Init.silver_util_treemap_treeKey__ON__silver_util_treemap_TreeMap] = "silver:util:treemap:treeKey";
		silver.util.treemap.NTreeMap.occurs_syn[silver.util.treemap.Init.silver_util_treemap_treeLookup__ON__silver_util_treemap_TreeMap] = "silver:util:treemap:treeLookup";
		silver.util.treemap.NTreeMap.occurs_inh[silver.util.treemap.Init.silver_util_treemap_treeValue__ON__silver_util_treemap_TreeMap] = "silver:util:treemap:treeValue";
		silver.util.treemap.NTreeMap.occurs_syn[silver.util.treemap.Init.silver_util_treemap_treeInsert__ON__silver_util_treemap_TreeMap] = "silver:util:treemap:treeInsert";
		silver.util.treemap.NTreeMap.occurs_syn[silver.util.treemap.Init.silver_util_treemap_makeBlack__ON__silver_util_treemap_TreeMap] = "silver:util:treemap:makeBlack";
		silver.util.treemap.NTreeMap.occurs_syn[silver.util.treemap.Init.silver_util_treemap_treeToList__ON__silver_util_treemap_TreeMap] = "silver:util:treemap:treeToList";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION treeNew TreeMap<a b> ::= CMP::(Integer ::= a a) 
		//FUNCTION treeInsert TreeMap<a b> ::= x::a v::b t::TreeMap<a b> 
		// t.treeKey = x
		silver.util.treemap.PtreeInsert.childInheritedAttributes[silver.util.treemap.PtreeInsert.i_t][silver.util.treemap.Init.silver_util_treemap_treeKey__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(silver.util.treemap.PtreeInsert.i_x)); } };
		// t.treeValue = v
		silver.util.treemap.PtreeInsert.childInheritedAttributes[silver.util.treemap.PtreeInsert.i_t][silver.util.treemap.Init.silver_util_treemap_treeValue__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(silver.util.treemap.PtreeInsert.i_v)); } };
		//FUNCTION treeLookup [b] ::= x::a t::TreeMap<a b> 
		// t.treeKey = x
		silver.util.treemap.PtreeLookup.childInheritedAttributes[silver.util.treemap.PtreeLookup.i_t][silver.util.treemap.Init.silver_util_treemap_treeKey__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(silver.util.treemap.PtreeLookup.i_x)); } };
		//FUNCTION treeConvert TreeMap<a b> ::= l::[Pair<a b>] t::TreeMap<a b> 
		//FUNCTION treeDeconvert [Pair<a b>] ::= t::TreeMap<a b> 
		silver.util.treemap.Pleaf.initProductionAttributeDefinitions();
		silver.util.treemap.Pnode.initProductionAttributeDefinitions();
		//FUNCTION treeMapKeyValues [Pair<a b>] ::= k::a v::[b] 
		//FUNCTION balanceL TreeMap<a b> ::= lefttree::TreeMap<a b> righttree::TreeMap<a b> label::a values::[b] CMP::(Integer ::= a a) 
		//FUNCTION balanceR TreeMap<a b> ::= lefttree::TreeMap<a b> righttree::TreeMap<a b> label::a values::[b] CMP::(Integer ::= a a) 
	}

	public static int count_local__ON__silver_util_treemap_treeNew = 0;
	public static int count_local__ON__silver_util_treemap_treeInsert = 0;
	public static int count_local__ON__silver_util_treemap_treeLookup = 0;
	public static int count_local__ON__silver_util_treemap_treeConvert = 0;
	public static int count_local__ON__silver_util_treemap_treeDeconvert = 0;
	public static int count_inh__ON__TreeMap = 0;
	public static int count_syn__ON__TreeMap = 0;
	public static int count_local__ON__silver_util_treemap_leaf = 0;
	public static int count_local__ON__silver_util_treemap_node = 0;
	public static int count_local__ON__silver_util_treemap_treeMapKeyValues = 0;
	public static int count_local__ON__silver_util_treemap_balanceL = 0;
	public static int count_local__ON__silver_util_treemap_balanceR = 0;
public static final int silver_util_treemap_treeKey__ON__silver_util_treemap_TreeMap = silver.util.treemap.Init.count_inh__ON__TreeMap++;
public static final int silver_util_treemap_treeLookup__ON__silver_util_treemap_TreeMap = silver.util.treemap.Init.count_syn__ON__TreeMap++;
public static final int silver_util_treemap_treeValue__ON__silver_util_treemap_TreeMap = silver.util.treemap.Init.count_inh__ON__TreeMap++;
public static final int silver_util_treemap_treeInsert__ON__silver_util_treemap_TreeMap = silver.util.treemap.Init.count_syn__ON__TreeMap++;
public static final int silver_util_treemap_makeBlack__ON__silver_util_treemap_TreeMap = silver.util.treemap.Init.count_syn__ON__TreeMap++;
public static final int silver_util_treemap_treeToList__ON__silver_util_treemap_TreeMap = silver.util.treemap.Init.count_syn__ON__TreeMap++;
}
