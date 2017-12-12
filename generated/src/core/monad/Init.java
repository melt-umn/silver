package core.monad;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.Init.initAllStatics();
		core.monad.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.Init.init();
		core.monad.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.Init.postInit();
		core.monad.Init.postInit();


		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PbindIO.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PreturnIO.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PprintM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PreadLineStdinM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PexitM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PmkdirM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PsystemM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PwriteFileM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PappendFileM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PfileTimeM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PisFileM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PisDirectoryM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PreadFileM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PcwdM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PenvVarM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PlistContentsM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PdeleteFileM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PdeleteTreeM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PcopyFileM.class);
		common.Decorator.applyDecorators(core.monad.NIOMonad.decorators, PtouchFileM.class);
		common.Decorator.applyDecorators(core.monad.NState.decorators, PbindState.class);
		common.Decorator.applyDecorators(core.monad.NState.decorators, PreturnState.class);
		common.Decorator.applyDecorators(core.monad.NState.decorators, PgetState.class);
		common.Decorator.applyDecorators(core.monad.NState.decorators, PsetState.class);
	}

	private static void setupInheritedAttributes(){
		core.monad.NIOMonad.occurs_inh[core.monad.Init.core_monad_stateIn__ON__core_monad_IOMonad] = "core:monad:stateIn";
		core.monad.NIOMonad.occurs_syn[core.monad.Init.core_monad_stateOut__ON__core_monad_IOMonad] = "core:monad:stateOut";
		core.monad.NIOMonad.occurs_syn[core.monad.Init.core_monad_stateVal__ON__core_monad_IOMonad] = "core:monad:stateVal";
		//	local attribute newState::IOMonad<b>;
		core.monad.PbindIO.localInheritedAttributes[core.monad.Init.newState__ON__core_monad_bindIO] = new common.Lazy[core.monad.NIOMonad.num_inh_attrs];
		core.monad.PbindIO.occurs_local[core.monad.Init.newState__ON__core_monad_bindIO] = "core:monad:bindIO:local:newState";
		core.monad.PbindIO.occurs_local[core.monad.Init.stateOut__ON__core_monad_bindIO] = "core:monad:bindIO:local:stateOut";
		core.monad.PbindIO.occurs_local[core.monad.Init.stateVal__ON__core_monad_bindIO] = "core:monad:bindIO:local:stateVal";
		//	local attribute res::IOVal<String>;
		core.monad.PreadLineStdinM.localInheritedAttributes[core.monad.Init.res__ON__core_monad_readLineStdinM] = new common.Lazy[core.NIOVal.num_inh_attrs];
		core.monad.PreadLineStdinM.occurs_local[core.monad.Init.res__ON__core_monad_readLineStdinM] = "core:monad:readLineStdinM:local:res";
		//	local attribute res::IOVal<Boolean>;
		core.monad.PmkdirM.localInheritedAttributes[core.monad.Init.res__ON__core_monad_mkdirM] = new common.Lazy[core.NIOVal.num_inh_attrs];
		core.monad.PmkdirM.occurs_local[core.monad.Init.res__ON__core_monad_mkdirM] = "core:monad:mkdirM:local:res";
		//	local attribute res::IOVal<Integer>;
		core.monad.PsystemM.localInheritedAttributes[core.monad.Init.res__ON__core_monad_systemM] = new common.Lazy[core.NIOVal.num_inh_attrs];
		core.monad.PsystemM.occurs_local[core.monad.Init.res__ON__core_monad_systemM] = "core:monad:systemM:local:res";
		//	local attribute res::IOVal<Integer>;
		core.monad.PfileTimeM.localInheritedAttributes[core.monad.Init.res__ON__core_monad_fileTimeM] = new common.Lazy[core.NIOVal.num_inh_attrs];
		core.monad.PfileTimeM.occurs_local[core.monad.Init.res__ON__core_monad_fileTimeM] = "core:monad:fileTimeM:local:res";
		//	local attribute res::IOVal<Boolean>;
		core.monad.PisFileM.localInheritedAttributes[core.monad.Init.res__ON__core_monad_isFileM] = new common.Lazy[core.NIOVal.num_inh_attrs];
		core.monad.PisFileM.occurs_local[core.monad.Init.res__ON__core_monad_isFileM] = "core:monad:isFileM:local:res";
		//	local attribute res::IOVal<Boolean>;
		core.monad.PisDirectoryM.localInheritedAttributes[core.monad.Init.res__ON__core_monad_isDirectoryM] = new common.Lazy[core.NIOVal.num_inh_attrs];
		core.monad.PisDirectoryM.occurs_local[core.monad.Init.res__ON__core_monad_isDirectoryM] = "core:monad:isDirectoryM:local:res";
		//	local attribute res::IOVal<String>;
		core.monad.PreadFileM.localInheritedAttributes[core.monad.Init.res__ON__core_monad_readFileM] = new common.Lazy[core.NIOVal.num_inh_attrs];
		core.monad.PreadFileM.occurs_local[core.monad.Init.res__ON__core_monad_readFileM] = "core:monad:readFileM:local:res";
		//	local attribute res::IOVal<String>;
		core.monad.PcwdM.localInheritedAttributes[core.monad.Init.res__ON__core_monad_cwdM] = new common.Lazy[core.NIOVal.num_inh_attrs];
		core.monad.PcwdM.occurs_local[core.monad.Init.res__ON__core_monad_cwdM] = "core:monad:cwdM:local:res";
		//	local attribute res::IOVal<String>;
		core.monad.PenvVarM.localInheritedAttributes[core.monad.Init.res__ON__core_monad_envVarM] = new common.Lazy[core.NIOVal.num_inh_attrs];
		core.monad.PenvVarM.occurs_local[core.monad.Init.res__ON__core_monad_envVarM] = "core:monad:envVarM:local:res";
		//	local attribute res::IOVal<[String]>;
		core.monad.PlistContentsM.localInheritedAttributes[core.monad.Init.res__ON__core_monad_listContentsM] = new common.Lazy[core.NIOVal.num_inh_attrs];
		core.monad.PlistContentsM.occurs_local[core.monad.Init.res__ON__core_monad_listContentsM] = "core:monad:listContentsM:local:res";
		//	local attribute res::IOVal<Boolean>;
		core.monad.PdeleteFileM.localInheritedAttributes[core.monad.Init.res__ON__core_monad_deleteFileM] = new common.Lazy[core.NIOVal.num_inh_attrs];
		core.monad.PdeleteFileM.occurs_local[core.monad.Init.res__ON__core_monad_deleteFileM] = "core:monad:deleteFileM:local:res";
		core.monad.NState.occurs_inh[core.monad.Init.core_monad_stateIn__ON__core_monad_State] = "core:monad:stateIn";
		core.monad.NState.occurs_syn[core.monad.Init.core_monad_stateOut__ON__core_monad_State] = "core:monad:stateOut";
		core.monad.NState.occurs_syn[core.monad.Init.core_monad_stateVal__ON__core_monad_State] = "core:monad:stateVal";
		//	local attribute newState::State<s b>;
		core.monad.PbindState.localInheritedAttributes[core.monad.Init.newState__ON__core_monad_bindState] = new common.Lazy[core.monad.NState.num_inh_attrs];
		core.monad.PbindState.occurs_local[core.monad.Init.newState__ON__core_monad_bindState] = "core:monad:bindState:local:newState";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION bindList [b] ::= l::[a] fn::([b] ::= a) 
		//FUNCTION returnList [a] ::= x::a 
		core.monad.PbindIO.initProductionAttributeDefinitions();
		core.monad.PreturnIO.initProductionAttributeDefinitions();
		//FUNCTION runIO IO ::= st::IOMonad<a> ioIn::IO 
		//FUNCTION evalIO IOVal<a> ::= st::IOMonad<a> ioIn::IO 
		// st.stateIn = ioIn
		core.monad.PevalIO.childInheritedAttributes[core.monad.PevalIO.i_st][core.monad.Init.core_monad_stateIn__ON__core_monad_IOMonad] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(core.monad.PevalIO.i_ioIn)); } };
		//FUNCTION unsafeEvalIO a ::= st::IOMonad<a> 
		core.monad.PprintM.initProductionAttributeDefinitions();
		core.monad.PreadLineStdinM.initProductionAttributeDefinitions();
		core.monad.PexitM.initProductionAttributeDefinitions();
		core.monad.PmkdirM.initProductionAttributeDefinitions();
		core.monad.PsystemM.initProductionAttributeDefinitions();
		core.monad.PwriteFileM.initProductionAttributeDefinitions();
		core.monad.PappendFileM.initProductionAttributeDefinitions();
		core.monad.PfileTimeM.initProductionAttributeDefinitions();
		core.monad.PisFileM.initProductionAttributeDefinitions();
		core.monad.PisDirectoryM.initProductionAttributeDefinitions();
		core.monad.PreadFileM.initProductionAttributeDefinitions();
		core.monad.PcwdM.initProductionAttributeDefinitions();
		core.monad.PenvVarM.initProductionAttributeDefinitions();
		core.monad.PlistContentsM.initProductionAttributeDefinitions();
		core.monad.PdeleteFileM.initProductionAttributeDefinitions();
		core.monad.PdeleteTreeM.initProductionAttributeDefinitions();
		core.monad.PcopyFileM.initProductionAttributeDefinitions();
		core.monad.PtouchFileM.initProductionAttributeDefinitions();
		core.monad.PbindState.initProductionAttributeDefinitions();
		core.monad.PreturnState.initProductionAttributeDefinitions();
		core.monad.PgetState.initProductionAttributeDefinitions();
		core.monad.PsetState.initProductionAttributeDefinitions();
		//FUNCTION runState Pair<s a> ::= st::State<s a> initialState::s 
		// st.stateIn = initialState
		core.monad.PrunState.childInheritedAttributes[core.monad.PrunState.i_st][core.monad.Init.core_monad_stateIn__ON__core_monad_State] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(core.monad.PrunState.i_initialState)); } };
		//FUNCTION evalState a ::= st::State<s a> initialState::s 
		//FUNCTION bindMaybe Maybe<b> ::= m::Maybe<a> fn::(Maybe<b> ::= a) 
		//FUNCTION returnMaybe Maybe<a> ::= x::a 
	}

	public static int count_local__ON__core_monad_bindList = 0;
	public static int count_local__ON__core_monad_returnList = 0;
	public static int count_inh__ON__IOMonad = 0;
	public static int count_syn__ON__IOMonad = 0;
	public static int count_local__ON__core_monad_bindIO = 0;
	public static int count_local__ON__core_monad_returnIO = 0;
	public static int count_local__ON__core_monad_runIO = 0;
	public static int count_local__ON__core_monad_evalIO = 0;
	public static int count_local__ON__core_monad_unsafeEvalIO = 0;
	public static int count_local__ON__core_monad_printM = 0;
	public static int count_local__ON__core_monad_readLineStdinM = 0;
	public static int count_local__ON__core_monad_exitM = 0;
	public static int count_local__ON__core_monad_mkdirM = 0;
	public static int count_local__ON__core_monad_systemM = 0;
	public static int count_local__ON__core_monad_writeFileM = 0;
	public static int count_local__ON__core_monad_appendFileM = 0;
	public static int count_local__ON__core_monad_fileTimeM = 0;
	public static int count_local__ON__core_monad_isFileM = 0;
	public static int count_local__ON__core_monad_isDirectoryM = 0;
	public static int count_local__ON__core_monad_readFileM = 0;
	public static int count_local__ON__core_monad_cwdM = 0;
	public static int count_local__ON__core_monad_envVarM = 0;
	public static int count_local__ON__core_monad_listContentsM = 0;
	public static int count_local__ON__core_monad_deleteFileM = 0;
	public static int count_local__ON__core_monad_deleteTreeM = 0;
	public static int count_local__ON__core_monad_copyFileM = 0;
	public static int count_local__ON__core_monad_touchFileM = 0;
	public static int count_inh__ON__State = 0;
	public static int count_syn__ON__State = 0;
	public static int count_local__ON__core_monad_bindState = 0;
	public static int count_local__ON__core_monad_returnState = 0;
	public static int count_local__ON__core_monad_getState = 0;
	public static int count_local__ON__core_monad_setState = 0;
	public static int count_local__ON__core_monad_runState = 0;
	public static int count_local__ON__core_monad_evalState = 0;
	public static int count_local__ON__core_monad_bindMaybe = 0;
	public static int count_local__ON__core_monad_returnMaybe = 0;
public static final int core_monad_stateIn__ON__core_monad_IOMonad = core.monad.Init.count_inh__ON__IOMonad++;
public static final int core_monad_stateOut__ON__core_monad_IOMonad = core.monad.Init.count_syn__ON__IOMonad++;
public static final int core_monad_stateVal__ON__core_monad_IOMonad = core.monad.Init.count_syn__ON__IOMonad++;
public static final int newState__ON__core_monad_bindIO = core.monad.Init.count_local__ON__core_monad_bindIO++;
public static final int stateOut__ON__core_monad_bindIO = core.monad.Init.count_local__ON__core_monad_bindIO++;
public static final int stateVal__ON__core_monad_bindIO = core.monad.Init.count_local__ON__core_monad_bindIO++;
public static final int res__ON__core_monad_readLineStdinM = core.monad.Init.count_local__ON__core_monad_readLineStdinM++;
public static final int res__ON__core_monad_mkdirM = core.monad.Init.count_local__ON__core_monad_mkdirM++;
public static final int res__ON__core_monad_systemM = core.monad.Init.count_local__ON__core_monad_systemM++;
public static final int res__ON__core_monad_fileTimeM = core.monad.Init.count_local__ON__core_monad_fileTimeM++;
public static final int res__ON__core_monad_isFileM = core.monad.Init.count_local__ON__core_monad_isFileM++;
public static final int res__ON__core_monad_isDirectoryM = core.monad.Init.count_local__ON__core_monad_isDirectoryM++;
public static final int res__ON__core_monad_readFileM = core.monad.Init.count_local__ON__core_monad_readFileM++;
public static final int res__ON__core_monad_cwdM = core.monad.Init.count_local__ON__core_monad_cwdM++;
public static final int res__ON__core_monad_envVarM = core.monad.Init.count_local__ON__core_monad_envVarM++;
public static final int res__ON__core_monad_listContentsM = core.monad.Init.count_local__ON__core_monad_listContentsM++;
public static final int res__ON__core_monad_deleteFileM = core.monad.Init.count_local__ON__core_monad_deleteFileM++;
public static final int core_monad_stateIn__ON__core_monad_State = core.monad.Init.count_inh__ON__State++;
public static final int core_monad_stateOut__ON__core_monad_State = core.monad.Init.count_syn__ON__State++;
public static final int core_monad_stateVal__ON__core_monad_State = core.monad.Init.count_syn__ON__State++;
public static final int newState__ON__core_monad_bindState = core.monad.Init.count_local__ON__core_monad_bindState++;
}
