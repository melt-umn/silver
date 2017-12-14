package core;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();


		common.Decorator.applyDecorators(core.NParseResult.decorators, PparseFailed.class);
		common.Decorator.applyDecorators(core.NParseResult.decorators, PparseSucceeded.class);
		common.Decorator.applyDecorators(core.NParseError.decorators, PsyntaxError.class);
		common.Decorator.applyDecorators(core.NParseError.decorators, PunknownParseError.class);
		common.Decorator.applyDecorators(core.NTerminalDescriptor.decorators, PterminalDescriptor.class);
		common.Decorator.applyDecorators(core.NList.decorators, Pi_nilList.class);
		common.Decorator.applyDecorators(core.NList.decorators, Pi_consList.class);
		common.Decorator.applyDecorators(core.NUnit.decorators, Punit.class);
		common.Decorator.applyDecorators(core.NLocation.decorators, Ploc.class);
		common.Decorator.applyDecorators(core.NLocation.decorators, PtxtLoc.class);
		common.Decorator.applyDecorators(core.NIOVal.decorators, Pioval.class);
		common.Decorator.applyDecorators(core.NEither.decorators, Pleft.class);
		common.Decorator.applyDecorators(core.NEither.decorators, Pright.class);
		common.Decorator.applyDecorators(core.NPair.decorators, Ppair.class);
		common.Decorator.applyDecorators(core.NMaybe.decorators, Pjust.class);
		common.Decorator.applyDecorators(core.NMaybe.decorators, Pnothing.class);
	}

	private static void setupInheritedAttributes(){
		core.NParseResult.occurs_syn[core.Init.core_parseSuccess__ON__core_ParseResult] = "core:parseSuccess";
		core.NParseResult.occurs_syn[core.Init.core_parseError__ON__core_ParseResult] = "core:parseError";
		core.NParseResult.occurs_syn[core.Init.core_parseErrors__ON__core_ParseResult] = "core:parseErrors";
		core.NParseResult.occurs_syn[core.Init.core_parseTree__ON__core_ParseResult] = "core:parseTree";
		core.NParseResult.occurs_syn[core.Init.core_parseTerminals__ON__core_ParseResult] = "core:parseTerminals";
		core.NParseError.occurs_syn[core.Init.core_parseErrors__ON__core_ParseError] = "core:parseErrors";
		core.NTerminalDescriptor.occurs_syn[core.Init.core_lexeme__ON__core_TerminalDescriptor] = "core:lexeme";
		core.NTerminalDescriptor.occurs_syn[core.Init.core_lexerClasses__ON__core_TerminalDescriptor] = "core:lexerClasses";
		core.NTerminalDescriptor.occurs_syn[core.Init.core_terminalLocation__ON__core_TerminalDescriptor] = "core:terminalLocation";
		core.NTerminalDescriptor.occurs_syn[core.Init.core_terminalName__ON__core_TerminalDescriptor] = "core:terminalName";
		//	local attribute recurse::Pair<[a] [a]>;
		core.Ppartition.localInheritedAttributes[core.Init.recurse__ON__core_partition] = new common.Lazy[core.NPair.num_inh_attrs];
		core.Ppartition.occurs_local[core.Init.recurse__ON__core_partition] = "core:partition:local:recurse";
		core.PsortByHelp.occurs_local[core.Init.front_half__ON__core_sortByHelp] = "core:sortByHelp:local:front_half";
		core.PsortByHelp.occurs_local[core.Init.back_half__ON__core_sortByHelp] = "core:sortByHelp:local:back_half";
		core.PsortByHelp.occurs_local[core.Init.middle__ON__core_sortByHelp] = "core:sortByHelp:local:middle";
		//	local attribute helpercall::Pair<[a] [a]>;
		core.PgroupBy.localInheritedAttributes[core.Init.helpercall__ON__core_groupBy] = new common.Lazy[core.NPair.num_inh_attrs];
		core.PgroupBy.occurs_local[core.Init.helpercall__ON__core_groupBy] = "core:groupBy:local:helpercall";
		//	local attribute recurse::Pair<[a] [a]>;
		core.PgroupByHelp.localInheritedAttributes[core.Init.recurse__ON__core_groupByHelp] = new common.Lazy[core.NPair.num_inh_attrs];
		core.PgroupByHelp.occurs_local[core.Init.recurse__ON__core_groupByHelp] = "core:groupByHelp:local:recurse";
		core.NList.occurs_syn[core.Init.core_i_headList__ON__core_List] = "core:i_headList";
		core.NList.occurs_syn[core.Init.core_i_tailList__ON__core_List] = "core:i_tailList";
		core.NList.occurs_syn[core.Init.core_i_emptyList__ON__core_List] = "core:i_emptyList";
		core.NList.occurs_syn[core.Init.core_i_lengthList__ON__core_List] = "core:i_lengthList";
		core.NLocation.occurs_syn[core.Init.core_filename__ON__core_Location] = "core:filename";
		core.NLocation.occurs_syn[core.Init.core_line__ON__core_Location] = "core:line";
		core.NLocation.occurs_syn[core.Init.core_column__ON__core_Location] = "core:column";
		core.NLocation.occurs_syn[core.Init.core_endLine__ON__core_Location] = "core:endLine";
		core.NLocation.occurs_syn[core.Init.core_endColumn__ON__core_Location] = "core:endColumn";
		core.NLocation.occurs_syn[core.Init.core_index__ON__core_Location] = "core:index";
		core.NLocation.occurs_syn[core.Init.core_endIndex__ON__core_Location] = "core:endIndex";
		core.NIOVal.occurs_syn[core.Init.core_io__ON__core_IOVal] = "core:io";
		core.NIOVal.occurs_syn[core.Init.core_iovalue__ON__core_IOVal] = "core:iovalue";
		core.PdirNameInFilePath.occurs_local[core.Init.indexOfLastSlash__ON__core_dirNameInFilePath] = "core:dirNameInFilePath:local:indexOfLastSlash";
		core.PfileNameInFilePath.occurs_local[core.Init.indexOfLastSlash__ON__core_fileNameInFilePath] = "core:fileNameInFilePath:local:indexOfLastSlash";
		core.PsplitFileNameAndExtension.occurs_local[core.Init.indexOfLastDot__ON__core_splitFileNameAndExtension] = "core:splitFileNameAndExtension:local:indexOfLastDot";
		core.PexplodeNormal.occurs_local[core.Init.i__ON__core_explodeNormal] = "core:explodeNormal:local:i";
		//	local attribute recurse::Pair<[a] [b]>;
		core.PpartitionEithers.localInheritedAttributes[core.Init.recurse__ON__core_partitionEithers] = new common.Lazy[core.NPair.num_inh_attrs];
		core.PpartitionEithers.occurs_local[core.Init.recurse__ON__core_partitionEithers] = "core:partitionEithers:local:recurse";
		core.NPair.occurs_syn[core.Init.core_fst__ON__core_Pair] = "core:fst";
		core.NPair.occurs_syn[core.Init.core_snd__ON__core_Pair] = "core:snd";
		//	local attribute recurse::Pair<[a] [b]>;
		core.PunzipPairs.localInheritedAttributes[core.Init.recurse__ON__core_unzipPairs] = new common.Lazy[core.NPair.num_inh_attrs];
		core.PunzipPairs.occurs_local[core.Init.recurse__ON__core_unzipPairs] = "core:unzipPairs:local:recurse";
		core.NMaybe.occurs_syn[core.Init.core_fromJust__ON__core_Maybe] = "core:fromJust";
		core.NMaybe.occurs_syn[core.Init.core_isJust__ON__core_Maybe] = "core:isJust";
	}

	private static void initProductionAttributeDefinitions(){
		core.PparseFailed.initProductionAttributeDefinitions();
		core.PparseSucceeded.initProductionAttributeDefinitions();
		//FUNCTION parseTreeOrDieWithoutStackTrace a ::= pr::ParseResult<a> 
		core.PsyntaxError.initProductionAttributeDefinitions();
		core.PunknownParseError.initProductionAttributeDefinitions();
		core.PterminalDescriptor.initProductionAttributeDefinitions();
		//FUNCTION map [b] ::= f::(b ::= a) l::[a] 
		//FUNCTION foldr b ::= f::(b ::= a b) i::b l::[a] 
		//FUNCTION foldl b ::= f::(b ::= b a) i::b l::[a] 
		//FUNCTION foldr1 a ::= f::(a ::= a a) l::[a] 
		//FUNCTION foldl1 a ::= f::(a ::= a a) l::[a] 
		//FUNCTION filter [a] ::= f::(Boolean ::= a) lst::[a] 
		//FUNCTION partition Pair<[a] [a]> ::= f::(Boolean ::= a) lst::[a] 
		// recurse = partition(f, tail(lst))
		core.Ppartition.localAttributes[core.Init.recurse__ON__core_partition] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)core.Ppartition.invoke(context.childAsIsLazy(core.Ppartition.i_f), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.Ppartition.i_lst))); } })); } };
		//FUNCTION containsBy Boolean ::= eq::(Boolean ::= a a) elem::a lst::[a] 
		//FUNCTION nubBy [a] ::= eq::(Boolean ::= a a) xs::[a] 
		//FUNCTION removeBy [a] ::= eq::(Boolean ::= a a) x::a xs::[a] 
		//FUNCTION removeAllBy [a] ::= eq::(Boolean ::= a a) ys::[a] xs::[a] 
		//FUNCTION last a ::= lst::[a] 
		//FUNCTION concat [a] ::= lst::[[a]] 
		//FUNCTION flatMap [b] ::= f::([b] ::= a) lst::[a] 
		//FUNCTION drop [a] ::= number::Integer lst::[a] 
		//FUNCTION take [a] ::= number::Integer lst::[a] 
		//FUNCTION dropWhile [a] ::= f::(Boolean ::= a) lst::[a] 
		//FUNCTION takeWhile [a] ::= f::(Boolean ::= a) lst::[a] 
		//FUNCTION takeUntil [a] ::= f::(Boolean ::= a) lst::[a] 
		//FUNCTION positionOf Integer ::= eq::(Boolean ::= a a) x::a xs::[a] 
		//FUNCTION positionOfHelper Integer ::= eq::(Boolean ::= a a) x::a xs::[a] currentPos::Integer 
		//FUNCTION repeat [a] ::= v::a times::Integer 
		//FUNCTION zipWith [c] ::= f::(c ::= a b) l1::[a] l2::[b] 
		//FUNCTION reverse [a] ::= lst::[a] 
		//FUNCTION reverseHelp [a] ::= lst::[a] sofar::[a] 
		//FUNCTION sortBy [a] ::= lte::(Boolean ::= a a) lst::[a] 
		//FUNCTION sortByHelp [a] ::= lte::(Boolean ::= a a) lst::[a] upTo::Integer 
		// front_half = sortByHelp(lte, lst, middle)
		core.PsortByHelp.localAttributes[core.Init.front_half__ON__core_sortByHelp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PsortByHelp.invoke(context.childAsIsLazy(core.PsortByHelp.i_lte), context.childAsIsLazy(core.PsortByHelp.i_lst), context.localAsIsLazy(core.Init.middle__ON__core_sortByHelp))); } };
		// back_half = sortByHelp(lte, drop(middle, lst), upTo - middle)
		core.PsortByHelp.localAttributes[core.Init.back_half__ON__core_sortByHelp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PsortByHelp.invoke(context.childAsIsLazy(core.PsortByHelp.i_lte), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pdrop.invoke(context.localAsIsLazy(core.Init.middle__ON__core_sortByHelp), context.childAsIsLazy(core.PsortByHelp.i_lst))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(core.PsortByHelp.i_upTo)) - ((Integer)context.localAsIs(core.Init.middle__ON__core_sortByHelp))); } })); } };
		// middle = toInt(toFloat(upTo) / 2.0)
		core.PsortByHelp.localAttributes[core.Init.middle__ON__core_sortByHelp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Float)Float.valueOf(Float.valueOf(((Integer)((Integer)context.childAsIs(core.PsortByHelp.i_upTo))).floatValue()) / Float.valueOf((float)2.0))).intValue()); } };
		//FUNCTION mergeBy [a] ::= lte::(Boolean ::= a a) l1::[a] l2::[a] 
		//FUNCTION groupBy [[a]] ::= eq::(Boolean ::= a a) l::[a] 
		// helpercall = groupByHelp(eq, head(l), l)
		core.PgroupBy.localAttributes[core.Init.helpercall__ON__core_groupBy] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)core.PgroupByHelp.invoke(context.childAsIsLazy(core.PgroupBy.i_eq), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.PgroupBy.i_l))); } }, context.childAsIsLazy(core.PgroupBy.i_l))); } };
		//FUNCTION groupByHelp Pair<[a] [a]> ::= eq::(Boolean ::= a a) f::a l::[a] 
		// recurse = groupByHelp(eq, f, tail(l))
		core.PgroupByHelp.localAttributes[core.Init.recurse__ON__core_groupByHelp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)core.PgroupByHelp.invoke(context.childAsIsLazy(core.PgroupByHelp.i_eq), context.childAsIsLazy(core.PgroupByHelp.i_f), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.PgroupByHelp.i_l))); } })); } };
		//FUNCTION intersperse [a] ::= sep::a xs::[a] 
		//FUNCTION unionBy [a] ::= eq::(Boolean ::= a a) l::[a] r::[a] 
		//FUNCTION intersectBy [a] ::= eq::(Boolean ::= a a) l::[a] r::[a] 
		//FUNCTION unionsBy [a] ::= eq::(Boolean ::= a a) ss::[[a]] 
		core.Pi_nilList.initProductionAttributeDefinitions();
		core.Pi_consList.initProductionAttributeDefinitions();
		core.Punit.initProductionAttributeDefinitions();
		core.Ploc.initProductionAttributeDefinitions();
		core.PtxtLoc.initProductionAttributeDefinitions();
		//FUNCTION builtinLoc Location ::= module::String 
		//FUNCTION bogusLoc Location ::= 
		//FUNCTION locationLte Boolean ::= l1::Location l2::Location 
		core.Pioval.initProductionAttributeDefinitions();
		//FUNCTION dirNameInFilePath String ::= filePath::String 
		// indexOfLastSlash = lastIndexOf("/", filePath)
		core.PdirNameInFilePath.localAttributes[core.Init.indexOfLastSlash__ON__core_dirNameInFilePath] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PlastIndexOf.invoke((new common.StringCatter("/")), context.childAsIsLazy(core.PdirNameInFilePath.i_filePath))); } };
		//FUNCTION fileNameInFilePath String ::= filePath::String 
		// indexOfLastSlash = lastIndexOf("/", filePath)
		core.PfileNameInFilePath.localAttributes[core.Init.indexOfLastSlash__ON__core_fileNameInFilePath] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PlastIndexOf.invoke((new common.StringCatter("/")), context.childAsIsLazy(core.PfileNameInFilePath.i_filePath))); } };
		//FUNCTION splitFileNameAndExtension Pair<String String> ::= filePath::String 
		// indexOfLastDot = lastIndexOf(".", filePath)
		core.PsplitFileNameAndExtension.localAttributes[core.Init.indexOfLastDot__ON__core_splitFileNameAndExtension] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PlastIndexOf.invoke((new common.StringCatter(".")), context.childAsIsLazy(core.PsplitFileNameAndExtension.i_filePath))); } };
		//FUNCTION implode String ::= sep::String lst::[String] 
		//FUNCTION explode [String] ::= sep::String str::String 
		//FUNCTION explodeNormal [String] ::= sep::String str::String 
		// i = indexOf(sep, str)
		core.PexplodeNormal.localAttributes[core.Init.i__ON__core_explodeNormal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PindexOf.invoke(context.childAsIsLazy(core.PexplodeNormal.i_sep), context.childAsIsLazy(core.PexplodeNormal.i_str))); } };
		//FUNCTION explodeSingle [String] ::= str::String 
		//FUNCTION sconcat String ::= lst::[String] 
		//FUNCTION sflatMap String ::= f::(String ::= a) lst::[a] 
		//FUNCTION stringConcat String ::= s1::String s2::String 
		//FUNCTION stringEq Boolean ::= s1::String s2::String 
		//FUNCTION stringLte Boolean ::= s1::String s2::String 
		core.Pleft.initProductionAttributeDefinitions();
		core.Pright.initProductionAttributeDefinitions();
		//FUNCTION partitionEithers Pair<[a] [b]> ::= l::[Either<a b>] 
		// recurse = partitionEithers(tail(l))
		core.PpartitionEithers.localAttributes[core.Init.recurse__ON__core_partitionEithers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)core.PpartitionEithers.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.PpartitionEithers.i_l))); } })); } };
		//FUNCTION fromLeft a ::= e::Either<a b> o::a 
		//FUNCTION fromRight b ::= e::Either<a b> o::b 
		//FUNCTION max Integer ::= l::Integer r::Integer 
		//FUNCTION min Integer ::= l::Integer r::Integer 
		core.Ppair.initProductionAttributeDefinitions();
		//FUNCTION fst a ::= p::Pair<a b> 
		//FUNCTION snd b ::= p::Pair<a b> 
		//FUNCTION lookupBy Maybe<b> ::= eqf::(Boolean ::= a a) elem::a lst::[Pair<a b>] 
		//FUNCTION lookupAllBy [b] ::= eqf::(Boolean ::= a a) elem::a lst::[Pair<a b>] 
		//FUNCTION unzipPairs Pair<[a] [b]> ::= lst::[Pair<a b>] 
		// recurse = unzipPairs(tail(lst))
		core.PunzipPairs.localAttributes[core.Init.recurse__ON__core_unzipPairs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)core.PunzipPairs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.PunzipPairs.i_lst))); } })); } };
		core.Pjust.initProductionAttributeDefinitions();
		core.Pnothing.initProductionAttributeDefinitions();
		//FUNCTION fromMaybe a ::= otherwise::a ifJust::Maybe<a> 
		//FUNCTION orElse Maybe<a> ::= l::Maybe<a> r::Maybe<a> 
		//FUNCTION consMaybe [a] ::= h::Maybe<a> t::[a] 
		//FUNCTION catMaybes [a] ::= l::[Maybe<a>] 
		//FUNCTION mapMaybe Maybe<b> ::= f::(b ::= a) m::Maybe<a> 
		//FUNCTION find Maybe<a> ::= f::(Boolean ::= a) l::[a] 
	}

	public static int count_inh__ON__ParseResult = 0;
	public static int count_syn__ON__ParseResult = 0;
	public static int count_local__ON__core_parseFailed = 0;
	public static int count_local__ON__core_parseSucceeded = 0;
	public static int count_local__ON__core_parseTreeOrDieWithoutStackTrace = 0;
	public static int count_inh__ON__ParseError = 0;
	public static int count_syn__ON__ParseError = 0;
	public static int count_local__ON__core_syntaxError = 0;
	public static int count_local__ON__core_unknownParseError = 0;
	public static int count_inh__ON__TerminalDescriptor = 0;
	public static int count_syn__ON__TerminalDescriptor = 0;
	public static int count_local__ON__core_terminalDescriptor = 0;
	public static int count_local__ON__core_map = 0;
	public static int count_local__ON__core_foldr = 0;
	public static int count_local__ON__core_foldl = 0;
	public static int count_local__ON__core_foldr1 = 0;
	public static int count_local__ON__core_foldl1 = 0;
	public static int count_local__ON__core_filter = 0;
	public static int count_local__ON__core_partition = 0;
	public static int count_local__ON__core_containsBy = 0;
	public static int count_local__ON__core_nubBy = 0;
	public static int count_local__ON__core_removeBy = 0;
	public static int count_local__ON__core_removeAllBy = 0;
	public static int count_local__ON__core_last = 0;
	public static int count_local__ON__core_concat = 0;
	public static int count_local__ON__core_flatMap = 0;
	public static int count_local__ON__core_drop = 0;
	public static int count_local__ON__core_take = 0;
	public static int count_local__ON__core_dropWhile = 0;
	public static int count_local__ON__core_takeWhile = 0;
	public static int count_local__ON__core_takeUntil = 0;
	public static int count_local__ON__core_positionOf = 0;
	public static int count_local__ON__core_positionOfHelper = 0;
	public static int count_local__ON__core_repeat = 0;
	public static int count_local__ON__core_zipWith = 0;
	public static int count_local__ON__core_reverse = 0;
	public static int count_local__ON__core_reverseHelp = 0;
	public static int count_local__ON__core_sortBy = 0;
	public static int count_local__ON__core_sortByHelp = 0;
	public static int count_local__ON__core_mergeBy = 0;
	public static int count_local__ON__core_groupBy = 0;
	public static int count_local__ON__core_groupByHelp = 0;
	public static int count_local__ON__core_intersperse = 0;
	public static int count_local__ON__core_unionBy = 0;
	public static int count_local__ON__core_intersectBy = 0;
	public static int count_local__ON__core_unionsBy = 0;
	public static int count_local__ON__core_nil = 0;
	public static int count_local__ON__core_cons = 0;
	public static int count_local__ON__core_append = 0;
	public static int count_local__ON__core_null = 0;
	public static int count_local__ON__core_listLength = 0;
	public static int count_local__ON__core_head = 0;
	public static int count_local__ON__core_tail = 0;
	public static int count_inh__ON__List = 0;
	public static int count_syn__ON__List = 0;
	public static int count_local__ON__core_i_nilList = 0;
	public static int count_local__ON__core_i_consList = 0;
	public static int count_inh__ON__Unit = 0;
	public static int count_syn__ON__Unit = 0;
	public static int count_local__ON__core_unit = 0;
	public static int count_inh__ON__Location = 0;
	public static int count_syn__ON__Location = 0;
	public static int count_local__ON__core_loc = 0;
	public static int count_local__ON__core_txtLoc = 0;
	public static int count_local__ON__core_builtinLoc = 0;
	public static int count_local__ON__core_bogusLoc = 0;
	public static int count_local__ON__core_locationLte = 0;
	public static int count_inh__ON__IOVal = 0;
	public static int count_syn__ON__IOVal = 0;
	public static int count_local__ON__core_ioval = 0;
	public static int count_local__ON__core_print = 0;
	public static int count_local__ON__core_readLineStdin = 0;
	public static int count_local__ON__core_exit = 0;
	public static int count_local__ON__core_mkdir = 0;
	public static int count_local__ON__core_system = 0;
	public static int count_local__ON__core_writeFile = 0;
	public static int count_local__ON__core_appendFile = 0;
	public static int count_local__ON__core_fileTime = 0;
	public static int count_local__ON__core_isFile = 0;
	public static int count_local__ON__core_isDirectory = 0;
	public static int count_local__ON__core_readFile = 0;
	public static int count_local__ON__core_cwd = 0;
	public static int count_local__ON__core_envVar = 0;
	public static int count_local__ON__core_listContents = 0;
	public static int count_local__ON__core_deleteFile = 0;
	public static int count_local__ON__core_deleteFiles = 0;
	public static int count_local__ON__core_deleteDirFiles = 0;
	public static int count_local__ON__core_deleteTree = 0;
	public static int count_local__ON__core_copyFile = 0;
	public static int count_local__ON__core_touchFile = 0;
	public static int count_local__ON__core_touchFiles = 0;
	public static int count_local__ON__core_error = 0;
	public static int count_local__ON__core_unsafeIO = 0;
	public static int count_local__ON__core_genInt = 0;
	public static int count_local__ON__core_genRand = 0;
	public static int count_local__ON__core_unsafeTrace = 0;
	public static int count_local__ON__core_dirNameInFilePath = 0;
	public static int count_local__ON__core_fileNameInFilePath = 0;
	public static int count_local__ON__core_splitFileNameAndExtension = 0;
	public static int count_local__ON__core_implode = 0;
	public static int count_local__ON__core_explode = 0;
	public static int count_local__ON__core_explodeNormal = 0;
	public static int count_local__ON__core_explodeSingle = 0;
	public static int count_local__ON__core_indexOf = 0;
	public static int count_local__ON__core_lastIndexOf = 0;
	public static int count_local__ON__core_substring = 0;
	public static int count_local__ON__core_startsWith = 0;
	public static int count_local__ON__core_endsWith = 0;
	public static int count_local__ON__core_substitute = 0;
	public static int count_local__ON__core_replicate = 0;
	public static int count_local__ON__core_isDigit = 0;
	public static int count_local__ON__core_isAlpha = 0;
	public static int count_local__ON__core_isSpace = 0;
	public static int count_local__ON__core_isLower = 0;
	public static int count_local__ON__core_isUpper = 0;
	public static int count_local__ON__core_toIntSafe = 0;
	public static int count_local__ON__core_sconcat = 0;
	public static int count_local__ON__core_sflatMap = 0;
	public static int count_local__ON__core_compareString = 0;
	public static int count_local__ON__core_stringConcat = 0;
	public static int count_local__ON__core_stringEq = 0;
	public static int count_local__ON__core_stringLte = 0;
	public static int count_local__ON__core_charsToString = 0;
	public static int count_local__ON__core_stringToChars = 0;
	public static int count_inh__ON__Either = 0;
	public static int count_syn__ON__Either = 0;
	public static int count_local__ON__core_left = 0;
	public static int count_local__ON__core_right = 0;
	public static int count_local__ON__core_partitionEithers = 0;
	public static int count_local__ON__core_fromLeft = 0;
	public static int count_local__ON__core_fromRight = 0;
	public static int count_local__ON__core_max = 0;
	public static int count_local__ON__core_min = 0;
	public static int count_local__ON__core_hackUnparse = 0;
	public static int count_inh__ON__Pair = 0;
	public static int count_syn__ON__Pair = 0;
	public static int count_local__ON__core_pair = 0;
	public static int count_local__ON__core_fst = 0;
	public static int count_local__ON__core_snd = 0;
	public static int count_local__ON__core_lookupBy = 0;
	public static int count_local__ON__core_lookupAllBy = 0;
	public static int count_local__ON__core_unzipPairs = 0;
	public static int count_inh__ON__Maybe = 0;
	public static int count_syn__ON__Maybe = 0;
	public static int count_local__ON__core_just = 0;
	public static int count_local__ON__core_nothing = 0;
	public static int count_local__ON__core_fromMaybe = 0;
	public static int count_local__ON__core_orElse = 0;
	public static int count_local__ON__core_consMaybe = 0;
	public static int count_local__ON__core_catMaybes = 0;
	public static int count_local__ON__core_mapMaybe = 0;
	public static int count_local__ON__core_find = 0;
public static final int core_parseSuccess__ON__core_ParseResult = core.Init.count_syn__ON__ParseResult++;
public static final int core_parseError__ON__core_ParseResult = core.Init.count_syn__ON__ParseResult++;
public static final int core_parseErrors__ON__core_ParseResult = core.Init.count_syn__ON__ParseResult++;
public static final int core_parseTree__ON__core_ParseResult = core.Init.count_syn__ON__ParseResult++;
public static final int core_parseTerminals__ON__core_ParseResult = core.Init.count_syn__ON__ParseResult++;
public static final int core_parseErrors__ON__core_ParseError = core.Init.count_syn__ON__ParseError++;
public static final int core_lexeme__ON__core_TerminalDescriptor = core.Init.count_syn__ON__TerminalDescriptor++;
public static final int core_lexerClasses__ON__core_TerminalDescriptor = core.Init.count_syn__ON__TerminalDescriptor++;
public static final int core_terminalLocation__ON__core_TerminalDescriptor = core.Init.count_syn__ON__TerminalDescriptor++;
public static final int core_terminalName__ON__core_TerminalDescriptor = core.Init.count_syn__ON__TerminalDescriptor++;
public static final int recurse__ON__core_partition = core.Init.count_local__ON__core_partition++;
public static final int front_half__ON__core_sortByHelp = core.Init.count_local__ON__core_sortByHelp++;
public static final int back_half__ON__core_sortByHelp = core.Init.count_local__ON__core_sortByHelp++;
public static final int middle__ON__core_sortByHelp = core.Init.count_local__ON__core_sortByHelp++;
public static final int helpercall__ON__core_groupBy = core.Init.count_local__ON__core_groupBy++;
public static final int recurse__ON__core_groupByHelp = core.Init.count_local__ON__core_groupByHelp++;
public static final int core_i_headList__ON__core_List = core.Init.count_syn__ON__List++;
public static final int core_i_tailList__ON__core_List = core.Init.count_syn__ON__List++;
public static final int core_i_emptyList__ON__core_List = core.Init.count_syn__ON__List++;
public static final int core_i_lengthList__ON__core_List = core.Init.count_syn__ON__List++;
public static final int core_filename__ON__core_Location = core.Init.count_syn__ON__Location++;
public static final int core_line__ON__core_Location = core.Init.count_syn__ON__Location++;
public static final int core_column__ON__core_Location = core.Init.count_syn__ON__Location++;
public static final int core_endLine__ON__core_Location = core.Init.count_syn__ON__Location++;
public static final int core_endColumn__ON__core_Location = core.Init.count_syn__ON__Location++;
public static final int core_index__ON__core_Location = core.Init.count_syn__ON__Location++;
public static final int core_endIndex__ON__core_Location = core.Init.count_syn__ON__Location++;
public static final int core_io__ON__core_IOVal = core.Init.count_syn__ON__IOVal++;
public static final int core_iovalue__ON__core_IOVal = core.Init.count_syn__ON__IOVal++;
public static final int indexOfLastSlash__ON__core_dirNameInFilePath = core.Init.count_local__ON__core_dirNameInFilePath++;
public static final int indexOfLastSlash__ON__core_fileNameInFilePath = core.Init.count_local__ON__core_fileNameInFilePath++;
public static final int indexOfLastDot__ON__core_splitFileNameAndExtension = core.Init.count_local__ON__core_splitFileNameAndExtension++;
public static final int i__ON__core_explodeNormal = core.Init.count_local__ON__core_explodeNormal++;
public static final int recurse__ON__core_partitionEithers = core.Init.count_local__ON__core_partitionEithers++;
public static final int core_fst__ON__core_Pair = core.Init.count_syn__ON__Pair++;
public static final int core_snd__ON__core_Pair = core.Init.count_syn__ON__Pair++;
public static final int recurse__ON__core_unzipPairs = core.Init.count_local__ON__core_unzipPairs++;
public static final int core_fromJust__ON__core_Maybe = core.Init.count_syn__ON__Maybe++;
public static final int core_isJust__ON__core_Maybe = core.Init.count_syn__ON__Maybe++;
}
