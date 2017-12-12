package silver.modification.let_fix.java;

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
		silver.definition.flow.ast.Init.initAllStatics();
		silver.translation.java.type.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.modification.let_fix.Init.initAllStatics();
		silver.modification.let_fix.java.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.flow.ast.Init.init();
		silver.translation.java.type.Init.init();
		silver.translation.java.core.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.modification.let_fix.Init.init();
		silver.modification.let_fix.java.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.flow.ast.Init.postInit();
		silver.translation.java.type.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.modification.let_fix.Init.postInit();
		silver.modification.let_fix.java.Init.postInit();


	}

	private static void setupInheritedAttributes(){
		//	local attribute finTy::Type;
		silver.modification.let_fix.Pletp.localInheritedAttributes[silver.modification.let_fix.java.Init.finTy__ON__silver_modification_let_fix_letp] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.modification.let_fix.Pletp.occurs_local[silver.modification.let_fix.java.Init.finTy__ON__silver_modification_let_fix_letp] = "silver:modification:let_fix:letp:local:finTy";
		silver.modification.let_fix.Pletp.occurs_local[silver.modification.let_fix.java.Init.closureExpr__ON__silver_modification_let_fix_letp] = "silver:modification:let_fix:letp:local:closureExpr";
		silver.modification.let_fix.NAssignExpr.occurs_syn[silver.modification.let_fix.java.Init.silver_modification_let_fix_java_let_translation__ON__silver_modification_let_fix_AssignExpr] = "silver:modification:let_fix:java:let_translation";
		silver.modification.let_fix.PlexicalLocalReference.occurs_local[silver.modification.let_fix.java.Init.needsUndecorating__ON__silver_modification_let_fix_lexicalLocalReference] = "silver:modification:let_fix:lexicalLocalReference:local:needsUndecorating";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION letp top ::= la::AssignExpr e::Expr 
		// finTy = finalType(top)
		silver.modification.let_fix.Pletp.localAttributes[silver.modification.let_fix.java.Init.finTy__ON__silver_modification_let_fix_letp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.translation.java.core.PfinalType.invoke(context)); } };
		// closureExpr = "new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { " ++ la.let_translation ++ "return " ++ e.translation ++ "; } }"
		silver.modification.let_fix.Pletp.localAttributes[silver.modification.let_fix.java.Init.closureExpr__ON__silver_modification_let_fix_letp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.Pletp.i_la).synthesized(silver.modification.let_fix.java.Init.silver_modification_let_fix_java_let_translation__ON__silver_modification_let_fix_AssignExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("return ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.Pletp.i_e).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter("; } }")))))); } };
		// top.translation = "((" ++ finTy.transType ++ ")(" ++ closureExpr ++ ").eval())"
		silver.modification.let_fix.Pletp.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("((")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.let_fix.java.Init.finTy__ON__silver_modification_let_fix_letp).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(")(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.let_fix.java.Init.closureExpr__ON__silver_modification_let_fix_letp)), (common.StringCatter)(new common.StringCatter(").eval())")))))); } };
		// top.lazyTranslation = if top.frame.lazyApplication then closureExpr else top.translation
		silver.modification.let_fix.Pletp.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_lazyTranslation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((silver.definition.core.NBlockContext)context.inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_Expr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_lazyApplication__ON__silver_definition_core_BlockContext)) ? ((common.StringCatter)context.localAsIs(silver.modification.let_fix.java.Init.closureExpr__ON__silver_modification_let_fix_letp)) : ((common.StringCatter)context.synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr))); } };
		//FUNCTION makeLocalValueName String ::= s::String 
		//ASPECT PRODUCTION appendAssignExpr top ::= a1::AssignExpr a2::AssignExpr 
		// top.let_translation = a1.let_translation ++ a2.let_translation
		silver.modification.let_fix.PappendAssignExpr.synthesizedAttributes[silver.modification.let_fix.java.Init.silver_modification_let_fix_java_let_translation__ON__silver_modification_let_fix_AssignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PappendAssignExpr.i_a1).synthesized(silver.modification.let_fix.java.Init.silver_modification_let_fix_java_let_translation__ON__silver_modification_let_fix_AssignExpr)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PappendAssignExpr.i_a2).synthesized(silver.modification.let_fix.java.Init.silver_modification_let_fix_java_let_translation__ON__silver_modification_let_fix_AssignExpr))); } };
		//ASPECT PRODUCTION assignExpr top ::= id::Name '::' t::TypeExpr '=' e::Expr 
		// top.let_translation = makeSpecialLocalBinding(fName, e.translation, finalTy.transType)
		silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.java.Init.silver_modification_let_fix_java_let_translation__ON__silver_modification_let_fix_AssignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.modification.let_fix.java.PmakeSpecialLocalBinding.invoke(context.localAsIsLazy(silver.modification.let_fix.Init.fName__ON__silver_modification_let_fix_assignExpr), context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PassignExpr.i_e, silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.modification.let_fix.Init.finalTy__ON__silver_modification_let_fix_assignExpr).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)); } })); } };
		//FUNCTION makeSpecialLocalBinding String ::= fn::String et::String ty::String 
		//ASPECT PRODUCTION lexicalLocalReference top ::= q::Decorated QName fi::ExprVertexInfo fd::[FlowVertex] 
		// needsUndecorating = performSubstitution(q.lookupValue.typerep, top.finalSubst).isDecorated && ! finalType(top).isDecorated
		silver.modification.let_fix.PlexicalLocalReference.localAttributes[silver.modification.let_fix.java.Init.needsUndecorating__ON__silver_modification_let_fix_lexicalLocalReference] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.let_fix.PlexicalLocalReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } }, context.contextInheritedLazy(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_Expr))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorated__ON__silver_definition_type_Type)) && (!((Boolean)((silver.definition.type.NType)silver.translation.java.core.PfinalType.invoke(context)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorated__ON__silver_definition_type_Type)))); } };
		// top.translation = if needsUndecorating then "((" ++ finalType(top).transType ++ ")((common.DecoratedNode)" ++ makeLocalValueName(q.lookupValue.fullName) ++ ".eval()).undecorate())" else "((" ++ finalType(top).transType ++ ")(" ++ makeLocalValueName(q.lookupValue.fullName) ++ ".eval()))"
		silver.modification.let_fix.PlexicalLocalReference.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localAsIs(silver.modification.let_fix.java.Init.needsUndecorating__ON__silver_modification_let_fix_lexicalLocalReference)) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("((")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)silver.translation.java.core.PfinalType.invoke(context)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(")((common.DecoratedNode)")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.let_fix.java.PmakeLocalValueName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.let_fix.PlexicalLocalReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })), (common.StringCatter)(new common.StringCatter(".eval()).undecorate())")))))) : new common.StringCatter((common.StringCatter)(new common.StringCatter("((")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)silver.translation.java.core.PfinalType.invoke(context)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(")(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.let_fix.java.PmakeLocalValueName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.let_fix.PlexicalLocalReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })), (common.StringCatter)(new common.StringCatter(".eval()))"))))))); } };
		// top.lazyTranslation = if ! top.frame.lazyApplication then top.translation else if needsUndecorating then "common.Thunk.transformUndecorate(" ++ makeLocalValueName(q.lookupValue.fullName) ++ ")" else makeLocalValueName(q.lookupValue.fullName)
		silver.modification.let_fix.PlexicalLocalReference.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_lazyTranslation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)((silver.definition.core.NBlockContext)context.inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_Expr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_lazyApplication__ON__silver_definition_core_BlockContext))) ? ((common.StringCatter)context.synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)) : (((Boolean)context.localAsIs(silver.modification.let_fix.java.Init.needsUndecorating__ON__silver_modification_let_fix_lexicalLocalReference)) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("common.Thunk.transformUndecorate(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.let_fix.java.PmakeLocalValueName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.let_fix.PlexicalLocalReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })), (common.StringCatter)(new common.StringCatter(")")))) : ((common.StringCatter)silver.modification.let_fix.java.PmakeLocalValueName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.let_fix.PlexicalLocalReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })))); } };
	}

	public static int count_local__ON__silver_modification_let_fix_java_makeLocalValueName = 0;
	public static int count_local__ON__silver_modification_let_fix_java_makeSpecialLocalBinding = 0;
public static final int finTy__ON__silver_modification_let_fix_letp = silver.modification.let_fix.Init.count_local__ON__silver_modification_let_fix_letp++;
public static final int closureExpr__ON__silver_modification_let_fix_letp = silver.modification.let_fix.Init.count_local__ON__silver_modification_let_fix_letp++;
public static final int silver_modification_let_fix_java_let_translation__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_syn__ON__AssignExpr++;
public static final int needsUndecorating__ON__silver_modification_let_fix_lexicalLocalReference = silver.modification.let_fix.Init.count_local__ON__silver_modification_let_fix_lexicalLocalReference++;
}
