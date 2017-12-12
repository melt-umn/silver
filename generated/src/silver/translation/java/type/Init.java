package silver.translation.java.type;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.translation.java.type.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.translation.java.core.Init.init();
		silver.definition.type.Init.init();
		silver.translation.java.type.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.translation.java.type.Init.postInit();


	}

	private static void setupInheritedAttributes(){
		silver.definition.type.NType.occurs_syn[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = "silver:translation:java:type:transType";
		silver.definition.type.NType.occurs_syn[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = "silver:translation:java:type:transClassType";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION varType top ::= tv::TyVar 
		// top.transType = "Object"
		silver.definition.type.PvarType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Object")); } };
		// top.transClassType = "Object"
		silver.definition.type.PvarType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Object")); } };
		//ASPECT PRODUCTION skolemType top ::= tv::TyVar 
		// top.transType = "Object"
		silver.definition.type.PskolemType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Object")); } };
		// top.transClassType = "Object"
		silver.definition.type.PskolemType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Object")); } };
		//ASPECT PRODUCTION intType top ::= 
		// top.transType = "Integer"
		silver.definition.type.PintType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Integer")); } };
		// top.transClassType = "Integer"
		silver.definition.type.PintType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Integer")); } };
		//ASPECT PRODUCTION boolType top ::= 
		// top.transType = "Boolean"
		silver.definition.type.PboolType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Boolean")); } };
		// top.transClassType = "Boolean"
		silver.definition.type.PboolType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Boolean")); } };
		//ASPECT PRODUCTION floatType top ::= 
		// top.transType = "Float"
		silver.definition.type.PfloatType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Float")); } };
		// top.transClassType = "Float"
		silver.definition.type.PfloatType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("Float")); } };
		//ASPECT PRODUCTION stringType top ::= 
		// top.transType = "common.StringCatter"
		silver.definition.type.PstringType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("common.StringCatter")); } };
		// top.transClassType = "common.StringCatter"
		silver.definition.type.PstringType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("common.StringCatter")); } };
		//ASPECT PRODUCTION nonterminalType top ::= fn::String params::[Type] 
		// top.transType = makeNTClassName(fn)
		silver.definition.type.PnonterminalType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PmakeNTClassName.invoke(context.childAsIsLazy(silver.definition.type.PnonterminalType.i_fn))); } };
		// top.transClassType = top.transType
		silver.definition.type.PnonterminalType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)); } };
		//ASPECT PRODUCTION terminalType top ::= fn::String 
		// top.transType = makeTerminalName(fn)
		silver.definition.type.PterminalType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PmakeTerminalName.invoke(context.childAsIsLazy(silver.definition.type.PterminalType.i_fn))); } };
		// top.transClassType = makeTerminalName(fn)
		silver.definition.type.PterminalType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PmakeTerminalName.invoke(context.childAsIsLazy(silver.definition.type.PterminalType.i_fn))); } };
		//ASPECT PRODUCTION decoratedType top ::= te::Type 
		// top.transType = "common.DecoratedNode"
		silver.definition.type.PdecoratedType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("common.DecoratedNode")); } };
		// top.transClassType = "common.DecoratedNode"
		silver.definition.type.PdecoratedType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("common.DecoratedNode")); } };
		//ASPECT PRODUCTION functionType top ::= out::Type params::[Type] namedParams::[NamedArgType] 
		// top.transType = "common.NodeFactory<" ++ out.transType ++ ">"
		silver.definition.type.PfunctionType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("common.NodeFactory<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.PfunctionType.i_out).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)(new common.StringCatter(">")))); } };
		// top.transClassType = "common.NodeFactory"
		silver.definition.type.PfunctionType.synthesizedAttributes[silver.translation.java.type.Init.silver_translation_java_type_transClassType__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("common.NodeFactory")); } };
	}

public static final int silver_translation_java_type_transType__ON__silver_definition_type_Type = silver.definition.type.Init.count_syn__ON__Type++;
public static final int silver_translation_java_type_transClassType__ON__silver_definition_type_Type = silver.definition.type.Init.count_syn__ON__Type++;
}
