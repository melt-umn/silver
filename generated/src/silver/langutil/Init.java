package silver.langutil;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.langutil.pp.Init.initAllStatics();
		silver.langutil.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.langutil.pp.Init.init();
		silver.langutil.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.langutil.pp.Init.postInit();
		silver.langutil.Init.postInit();


		common.Decorator.applyDecorators(silver.langutil.NMessage.decorators, Perr.class);
		common.Decorator.applyDecorators(silver.langutil.NMessage.decorators, Pwrn.class);
		common.Decorator.applyDecorators(silver.langutil.NMessage.decorators, Pinfo.class);
		common.Decorator.applyDecorators(silver.langutil.NMessage.decorators, Pnested.class);
	}

	private static void setupInheritedAttributes(){
		core.NLocation.occurs_syn[silver.langutil.Init.silver_langutil_unparse__ON__core_Location] = "silver:langutil:unparse";
		silver.langutil.NMessage.occurs_syn[silver.langutil.Init.silver_langutil_message__ON__silver_langutil_Message] = "silver:langutil:message";
		silver.langutil.NMessage.occurs_syn[silver.langutil.Init.silver_langutil_where__ON__silver_langutil_Message] = "silver:langutil:where";
		silver.langutil.NMessage.occurs_syn[silver.langutil.Init.silver_langutil_output__ON__silver_langutil_Message] = "silver:langutil:output";
		silver.langutil.NMessage.occurs_syn[silver.langutil.Init.silver_langutil_severity__ON__silver_langutil_Message] = "silver:langutil:severity";
		silver.langutil.Pnested.occurs_local[silver.langutil.Init.header__ON__silver_langutil_nested] = "silver:langutil:nested:local:header";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION loc top ::= filename::String line::Integer column::Integer endLine::Integer endColumn::Integer index::Integer endIndex::Integer 
		// top.unparse = filename ++ ":" ++ toString(line) ++ ":" ++ toString(column)
		core.Ploc.synthesizedAttributes[silver.langutil.Init.silver_langutil_unparse__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(core.Ploc.i_filename)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childAsIs(core.Ploc.i_line)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childAsIs(core.Ploc.i_column)))))))); } };
		//ASPECT PRODUCTION txtLoc top ::= text::String 
		// top.unparse = text
		core.PtxtLoc.synthesizedAttributes[silver.langutil.Init.silver_langutil_unparse__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(core.PtxtLoc.i_text)); } };
		silver.langutil.Perr.initProductionAttributeDefinitions();
		silver.langutil.Pwrn.initProductionAttributeDefinitions();
		silver.langutil.Pinfo.initProductionAttributeDefinitions();
		silver.langutil.Pnested.initProductionAttributeDefinitions();
		//FUNCTION nestedOutputHelper String ::= header::String msg::Message 
		//FUNCTION containsErrors Boolean ::= l::[Message] wError::Boolean 
		//FUNCTION messagesToString String ::= msgs::[Message] 
		//FUNCTION messageLte Boolean ::= m1::Message m2::Message 
	}

	public static int count_inh__ON__Message = 0;
	public static int count_syn__ON__Message = 0;
	public static int count_local__ON__silver_langutil_err = 0;
	public static int count_local__ON__silver_langutil_wrn = 0;
	public static int count_local__ON__silver_langutil_info = 0;
	public static int count_local__ON__silver_langutil_nested = 0;
	public static int count_local__ON__silver_langutil_nestedOutputHelper = 0;
	public static int count_local__ON__silver_langutil_containsErrors = 0;
	public static int count_local__ON__silver_langutil_messagesToString = 0;
	public static int count_local__ON__silver_langutil_messageLte = 0;
public static final int silver_langutil_unparse__ON__core_Location = core.Init.count_syn__ON__Location++;
public static final int silver_langutil_message__ON__silver_langutil_Message = silver.langutil.Init.count_syn__ON__Message++;
public static final int silver_langutil_where__ON__silver_langutil_Message = silver.langutil.Init.count_syn__ON__Message++;
public static final int silver_langutil_output__ON__silver_langutil_Message = silver.langutil.Init.count_syn__ON__Message++;
public static final int silver_langutil_severity__ON__silver_langutil_Message = silver.langutil.Init.count_syn__ON__Message++;
public static final int header__ON__silver_langutil_nested = silver.langutil.Init.count_local__ON__silver_langutil_nested++;
}
