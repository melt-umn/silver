package silver_features.defs;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver_features.defs.subdefs.Init.initAllStatics();
		silver_features.defs.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver_features.defs.subdefs.Init.init();
		silver_features.defs.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver_features.defs.subdefs.Init.postInit();
		silver_features.defs.Init.postInit();


		common.Decorator.applyDecorators(silver_features.defs.NFoo.decorators, Pfoo.class);
		common.Decorator.applyDecorators(silver_features.defs.NFoo.decorators, PgoodEqs.class);
	}

	private static void setupInheritedAttributes(){
		silver_features.defs.NFoo.occurs_syn[silver_features.defs.Init.silver_features_defs_foo__ON__silver_features_defs_Foo] = "silver_features:defs:foo";
		silver_features.defs.NFoo.occurs_inh[silver_features.defs.Init.silver_features_defs_bar__ON__silver_features_defs_Foo] = "silver_features:defs:bar";
		//	local attribute f3::Foo;
		silver_features.defs.Pfoo.localInheritedAttributes[silver_features.defs.Init.f3__ON__silver_features_defs_foo] = new common.Lazy[silver_features.defs.NFoo.num_inh_attrs];
		silver_features.defs.Pfoo.occurs_local[silver_features.defs.Init.f3__ON__silver_features_defs_foo] = "silver_features:defs:foo:local:f3";
		//	local attribute c::Foo;
		silver_features.defs.PgoodEqs.localInheritedAttributes[silver_features.defs.Init.c__ON__silver_features_defs_goodEqs] = new common.Lazy[silver_features.defs.NFoo.num_inh_attrs];
		silver_features.defs.PgoodEqs.occurs_local[silver_features.defs.Init.c__ON__silver_features_defs_goodEqs] = "silver_features:defs:goodEqs:local:c";
		silver_features.defs.PgoodEqs.occurs_local[silver_features.defs.Init.d__ON__silver_features_defs_goodEqs] = "silver_features:defs:goodEqs:local:d";
	}

	private static void initProductionAttributeDefinitions(){
		silver_features.defs.Pfoo.initProductionAttributeDefinitions();
		silver_features.defs.PgoodEqs.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__Foo = 0;
	public static int count_syn__ON__Foo = 0;
	public static int count_local__ON__silver_features_defs_foo = 0;
	public static int count_local__ON__silver_features_defs_goodEqs = 0;
public static final int silver_features_defs_foo__ON__silver_features_defs_Foo = silver_features.defs.Init.count_syn__ON__Foo++;
public static final int silver_features_defs_bar__ON__silver_features_defs_Foo = silver_features.defs.Init.count_inh__ON__Foo++;
public static final int f3__ON__silver_features_defs_foo = silver_features.defs.Init.count_local__ON__silver_features_defs_foo++;
public static final int c__ON__silver_features_defs_goodEqs = silver_features.defs.Init.count_local__ON__silver_features_defs_goodEqs++;
public static final int d__ON__silver_features_defs_goodEqs = silver_features.defs.Init.count_local__ON__silver_features_defs_goodEqs++;
	public static final common.Thunk<Object> newglobalsubdefs = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.defs.subdefs.NBar)new silver_features.defs.subdefs.PbarBar()); } };
}
