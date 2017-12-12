package silver.extension.list;

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
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.extension.list.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.core.Init.init();
		silver.definition.env.Init.init();
		silver.definition.type.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.extension.list.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.extension.list.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PlistTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PemptyList.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PconsListOp.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PfullList.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PlistPlusPlus.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PlistLengthBouncer.class);
		common.Decorator.applyDecorators(silver.definition.type.NType.decorators, PlistType.class);
	}

	private static void setupInheritedAttributes(){
		silver.definition.core.NExprs.occurs_syn[silver.extension.list.Init.silver_extension_list_listtrans__ON__silver_definition_core_Exprs] = "silver:extension:list:listtrans";
	}

	private static void initProductionAttributeDefinitions(){
		silver.extension.list.PlistTypeExpr.initProductionAttributeDefinitions();
		silver.extension.list.PemptyList.initProductionAttributeDefinitions();
		silver.extension.list.PconsListOp.initProductionAttributeDefinitions();
		silver.extension.list.PfullList.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION exprsEmpty top ::= 
		// top.listtrans = emptyList('[', ']',location=top.location)
		silver.definition.core.PexprsEmpty.synthesizedAttributes[silver.extension.list.Init.silver_extension_list_listtrans__ON__silver_definition_core_Exprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.list.PemptyList(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TLSqr_t((new common.StringCatter("[")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TRSqr_t((new common.StringCatter("]")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExprs)context.undecorate()).getAnno_core_location()); } })); } };
		//ASPECT PRODUCTION exprsSingle top ::= e::Expr 
		// top.listtrans = mkStrFunctionInvocation(e.location, "core:cons", [ e, emptyList('[', ']',location=top.location) ])
		silver.definition.core.PexprsSingle.synthesizedAttributes[silver.extension.list.Init.silver_extension_list_listtrans__ON__silver_definition_core_Exprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.definition.core.PmkStrFunctionInvocation.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.definition.core.PexprsSingle.i_e).undecorate()).getAnno_core_location()); } }, (new common.StringCatter("core:cons")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PexprsSingle.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.list.PemptyList(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TLSqr_t((new common.StringCatter("[")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TRSqr_t((new common.StringCatter("]")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExprs)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };
		//ASPECT PRODUCTION exprsCons top ::= e1::Expr ',' e2::Exprs 
		// top.listtrans = mkStrFunctionInvocation(e1.location, "core:cons", [ e1, e2.listtrans ])
		silver.definition.core.PexprsCons.synthesizedAttributes[silver.extension.list.Init.silver_extension_list_listtrans__ON__silver_definition_core_Exprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.definition.core.PmkStrFunctionInvocation.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.definition.core.PexprsCons.i_e1).undecorate()).getAnno_core_location()); } }, (new common.StringCatter("core:cons")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PexprsCons.i_e1)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PexprsCons.i_e2, silver.extension.list.Init.silver_extension_list_listtrans__ON__silver_definition_core_Exprs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };
		silver.extension.list.PlistPlusPlus.initProductionAttributeDefinitions();
		silver.extension.list.PlistLengthBouncer.initProductionAttributeDefinitions();
		silver.extension.list.PlistType.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_extension_list_listTypeExpr = 0;
	public static int count_local__ON__silver_extension_list_emptyList = 0;
	public static int count_local__ON__silver_extension_list_consListOp = 0;
	public static int count_local__ON__silver_extension_list_fullList = 0;
	public static int count_local__ON__silver_extension_list_listPlusPlus = 0;
	public static int count_local__ON__silver_extension_list_listLengthBouncer = 0;
	public static int count_local__ON__silver_extension_list_listType = 0;
public static final int silver_extension_list_listtrans__ON__silver_definition_core_Exprs = silver.definition.core.Init.count_syn__ON__Exprs++;
}
