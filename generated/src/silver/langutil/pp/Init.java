package silver.langutil.pp;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.util.deque.Init.initAllStatics();
		silver.langutil.pp.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.util.deque.Init.init();
		silver.langutil.pp.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.util.deque.Init.postInit();
		silver.langutil.pp.Init.postInit();


		common.Decorator.applyDecorators(silver.langutil.pp.NDocument.decorators, Ptext.class);
		common.Decorator.applyDecorators(silver.langutil.pp.NDocument.decorators, Pcat.class);
		common.Decorator.applyDecorators(silver.langutil.pp.NDocument.decorators, Pline.class);
		common.Decorator.applyDecorators(silver.langutil.pp.NDocument.decorators, Pgroup.class);
		common.Decorator.applyDecorators(silver.langutil.pp.NDocument.decorators, Pnest.class);
		common.Decorator.applyDecorators(silver.langutil.pp.NDocument.decorators, Pnotext.class);
		common.Decorator.applyDecorators(silver.langutil.pp.NDocument.decorators, Pbox.class);
		common.Decorator.applyDecorators(silver.langutil.pp.NDocument.decorators, PrealLine.class);
	}

	private static void setupInheritedAttributes(){
		silver.langutil.pp.NDocument.occurs_inh[silver.langutil.pp.Init.silver_langutil_pp_indent__ON__silver_langutil_pp_Document] = "silver:langutil:pp:indent";
		silver.langutil.pp.NDocument.decorators.add(silver.langutil.pp.Dindent.singleton);
		silver.langutil.pp.NDocument.occurs_inh[silver.langutil.pp.Init.silver_langutil_pp_width__ON__silver_langutil_pp_Document] = "silver:langutil:pp:width";
		silver.langutil.pp.NDocument.decorators.add(silver.langutil.pp.Dwidth.singleton);
		silver.langutil.pp.NDocument.occurs_inh[silver.langutil.pp.Init.silver_langutil_pp_inPosition__ON__silver_langutil_pp_Document] = "silver:langutil:pp:inPosition";
		silver.langutil.pp.NDocument.occurs_inh[silver.langutil.pp.Init.silver_langutil_pp_inDq__ON__silver_langutil_pp_Document] = "silver:langutil:pp:inDq";
		silver.langutil.pp.NDocument.occurs_inh[silver.langutil.pp.Init.silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document] = "silver:langutil:pp:inCHorizontals";
		silver.langutil.pp.NDocument.occurs_inh[silver.langutil.pp.Init.silver_langutil_pp_inRemaining__ON__silver_langutil_pp_Document] = "silver:langutil:pp:inRemaining";
		silver.langutil.pp.NDocument.occurs_syn[silver.langutil.pp.Init.silver_langutil_pp_outPosition__ON__silver_langutil_pp_Document] = "silver:langutil:pp:outPosition";
		silver.langutil.pp.NDocument.occurs_syn[silver.langutil.pp.Init.silver_langutil_pp_outDq__ON__silver_langutil_pp_Document] = "silver:langutil:pp:outDq";
		silver.langutil.pp.NDocument.occurs_syn[silver.langutil.pp.Init.silver_langutil_pp_outCHorizontals__ON__silver_langutil_pp_Document] = "silver:langutil:pp:outCHorizontals";
		silver.langutil.pp.NDocument.occurs_syn[silver.langutil.pp.Init.silver_langutil_pp_outRemaining__ON__silver_langutil_pp_Document] = "silver:langutil:pp:outRemaining";
		silver.langutil.pp.NDocument.occurs_syn[silver.langutil.pp.Init.silver_langutil_pp_result__ON__silver_langutil_pp_Document] = "silver:langutil:pp:result";
		silver.langutil.pp.NDocument.occurs_syn[silver.langutil.pp.Init.silver_langutil_pp_horizontals__ON__silver_langutil_pp_Document] = "silver:langutil:pp:horizontals";
		//	local attribute pr::Pair<Deque<Pair<Integer [Boolean]>> [Boolean]>;
		silver.langutil.pp.Ptext.localInheritedAttributes[silver.langutil.pp.Init.pr__ON__silver_langutil_pp_text] = new common.Lazy[core.NPair.num_inh_attrs];
		silver.langutil.pp.Ptext.occurs_local[silver.langutil.pp.Init.pr__ON__silver_langutil_pp_text] = "silver:langutil:pp:text:local:pr";
		//	local attribute pr::Pair<Deque<Pair<Integer [Boolean]>> [Boolean]>;
		silver.langutil.pp.Pline.localInheritedAttributes[silver.langutil.pp.Init.pr__ON__silver_langutil_pp_line] = new common.Lazy[core.NPair.num_inh_attrs];
		silver.langutil.pp.Pline.occurs_local[silver.langutil.pp.Init.pr__ON__silver_langutil_pp_line] = "silver:langutil:pp:line:local:pr";
		silver.langutil.pp.Pline.occurs_local[silver.langutil.pp.Init.horizontal__ON__silver_langutil_pp_line] = "silver:langutil:pp:line:local:horizontal";
		//	local attribute le::Pair<Deque<Pair<Integer [Boolean]>> [Boolean]>;
		silver.langutil.pp.Pgroup.localInheritedAttributes[silver.langutil.pp.Init.le__ON__silver_langutil_pp_group] = new common.Lazy[core.NPair.num_inh_attrs];
		silver.langutil.pp.Pgroup.occurs_local[silver.langutil.pp.Init.le__ON__silver_langutil_pp_group] = "silver:langutil:pp:group:local:le";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION show String ::= width::Integer d::Document 
		// d.indent = 0
		silver.langutil.pp.Pshow.childInheritedAttributes[silver.langutil.pp.Pshow.i_d][silver.langutil.pp.Init.silver_langutil_pp_indent__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// d.width = width
		silver.langutil.pp.Pshow.childInheritedAttributes[silver.langutil.pp.Pshow.i_d][silver.langutil.pp.Init.silver_langutil_pp_width__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.langutil.pp.Pshow.i_width)); } };
		// d.inPosition = 0
		silver.langutil.pp.Pshow.childInheritedAttributes[silver.langutil.pp.Pshow.i_d][silver.langutil.pp.Init.silver_langutil_pp_inPosition__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// d.inDq = dqEmpty()
		silver.langutil.pp.Pshow.childInheritedAttributes[silver.langutil.pp.Pshow.i_d][silver.langutil.pp.Init.silver_langutil_pp_inDq__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqEmpty.invoke()); } };
		// d.inCHorizontals = (false :: d.horizontals)
		silver.langutil.pp.Pshow.childInheritedAttributes[silver.langutil.pp.Pshow.i_d][silver.langutil.pp.Init.silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(false, context.childDecoratedSynthesizedLazy(silver.langutil.pp.Pshow.i_d, silver.langutil.pp.Init.silver_langutil_pp_horizontals__ON__silver_langutil_pp_Document))); } };
		// d.inRemaining = width
		silver.langutil.pp.Pshow.childInheritedAttributes[silver.langutil.pp.Pshow.i_d][silver.langutil.pp.Init.silver_langutil_pp_inRemaining__ON__silver_langutil_pp_Document] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.langutil.pp.Pshow.i_width)); } };
		//FUNCTION ppConcat Document ::= ds::[Document] 
		//FUNCTION ppImplode Document ::= sep::Document ds::[Document] 
		//FUNCTION terminate Document ::= sep::Document ds::[Document] 
		//FUNCTION initiate Document ::= sep::Document ds::[Document] 
		//FUNCTION nestlines Document ::= n::Integer inner::Document 
		//FUNCTION groupnest Document ::= n::Integer inner::Document 
		//FUNCTION groupnestlines Document ::= n::Integer inner::Document 
		//FUNCTION softbreak Document ::= 
		//FUNCTION space Document ::= 
		//FUNCTION semi Document ::= 
		//FUNCTION comma Document ::= 
		//FUNCTION braces Document ::= d::Document 
		//FUNCTION parens Document ::= d::Document 
		//FUNCTION brackets Document ::= d::Document 
		silver.langutil.pp.Ptext.initProductionAttributeDefinitions();
		silver.langutil.pp.Pcat.initProductionAttributeDefinitions();
		silver.langutil.pp.Pline.initProductionAttributeDefinitions();
		silver.langutil.pp.Pgroup.initProductionAttributeDefinitions();
		silver.langutil.pp.Pnest.initProductionAttributeDefinitions();
		silver.langutil.pp.Pnotext.initProductionAttributeDefinitions();
		silver.langutil.pp.Pbox.initProductionAttributeDefinitions();
		silver.langutil.pp.PrealLine.initProductionAttributeDefinitions();
		//FUNCTION prune Pair<Deque<Pair<Integer [Boolean]>> [Boolean]> ::= p::Integer q::Deque<Pair<Integer [Boolean]>> 
		//FUNCTION enter Deque<Pair<Integer [Boolean]>> ::= p::Integer q::Deque<Pair<Integer [Boolean]>> 
		//FUNCTION leave Pair<Deque<Pair<Integer [Boolean]>> [Boolean]> ::= p::Integer q::Deque<Pair<Integer [Boolean]>> 
	}

	public static int count_local__ON__silver_langutil_pp_show = 0;
	public static int count_local__ON__silver_langutil_pp_ppConcat = 0;
	public static int count_local__ON__silver_langutil_pp_ppImplode = 0;
	public static int count_local__ON__silver_langutil_pp_terminate = 0;
	public static int count_local__ON__silver_langutil_pp_initiate = 0;
	public static int count_local__ON__silver_langutil_pp_nestlines = 0;
	public static int count_local__ON__silver_langutil_pp_groupnest = 0;
	public static int count_local__ON__silver_langutil_pp_groupnestlines = 0;
	public static int count_local__ON__silver_langutil_pp_softbreak = 0;
	public static int count_local__ON__silver_langutil_pp_space = 0;
	public static int count_local__ON__silver_langutil_pp_semi = 0;
	public static int count_local__ON__silver_langutil_pp_comma = 0;
	public static int count_local__ON__silver_langutil_pp_braces = 0;
	public static int count_local__ON__silver_langutil_pp_parens = 0;
	public static int count_local__ON__silver_langutil_pp_brackets = 0;
	public static int count_inh__ON__Document = 0;
	public static int count_syn__ON__Document = 0;
	public static int count_local__ON__silver_langutil_pp_text = 0;
	public static int count_local__ON__silver_langutil_pp_cat = 0;
	public static int count_local__ON__silver_langutil_pp_line = 0;
	public static int count_local__ON__silver_langutil_pp_group = 0;
	public static int count_local__ON__silver_langutil_pp_nest = 0;
	public static int count_local__ON__silver_langutil_pp_notext = 0;
	public static int count_local__ON__silver_langutil_pp_box = 0;
	public static int count_local__ON__silver_langutil_pp_realLine = 0;
	public static int count_local__ON__silver_langutil_pp_prune = 0;
	public static int count_local__ON__silver_langutil_pp_enter = 0;
	public static int count_local__ON__silver_langutil_pp_leave = 0;
public static final int silver_langutil_pp_indent__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_inh__ON__Document++;
public static final int silver_langutil_pp_width__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_inh__ON__Document++;
public static final int silver_langutil_pp_inPosition__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_inh__ON__Document++;
public static final int silver_langutil_pp_inDq__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_inh__ON__Document++;
public static final int silver_langutil_pp_inCHorizontals__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_inh__ON__Document++;
public static final int silver_langutil_pp_inRemaining__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_inh__ON__Document++;
public static final int silver_langutil_pp_outPosition__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_syn__ON__Document++;
public static final int silver_langutil_pp_outDq__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_syn__ON__Document++;
public static final int silver_langutil_pp_outCHorizontals__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_syn__ON__Document++;
public static final int silver_langutil_pp_outRemaining__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_syn__ON__Document++;
public static final int silver_langutil_pp_result__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_syn__ON__Document++;
public static final int silver_langutil_pp_horizontals__ON__silver_langutil_pp_Document = silver.langutil.pp.Init.count_syn__ON__Document++;
public static final int pr__ON__silver_langutil_pp_text = silver.langutil.pp.Init.count_local__ON__silver_langutil_pp_text++;
public static final int pr__ON__silver_langutil_pp_line = silver.langutil.pp.Init.count_local__ON__silver_langutil_pp_line++;
public static final int horizontal__ON__silver_langutil_pp_line = silver.langutil.pp.Init.count_local__ON__silver_langutil_pp_line++;
public static final int le__ON__silver_langutil_pp_group = silver.langutil.pp.Init.count_local__ON__silver_langutil_pp_group++;
}
