package silver.extension.functorattrib;

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
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.util.Init.initAllStatics();
		silver.extension.functorattrib.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.core.Init.init();
		silver.util.Init.init();
		silver.extension.functorattrib.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.util.Init.postInit();
		silver.extension.functorattrib.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PpropagateAttrDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PpropagateOne.class);
	}

	private static void setupInheritedAttributes(){
		//	local attribute at::QName;
		silver.extension.functorattrib.PmakeArgs.localInheritedAttributes[silver.extension.functorattrib.Init.at__ON__silver_extension_functorattrib_makeArgs] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
		silver.extension.functorattrib.PmakeArgs.occurs_local[silver.extension.functorattrib.Init.at__ON__silver_extension_functorattrib_makeArgs] = "silver:extension:functorattrib:makeArgs:local:at";
		silver.extension.functorattrib.PmakeArgs.occurs_local[silver.extension.functorattrib.Init.attrOccursOnHead__ON__silver_extension_functorattrib_makeArgs] = "silver:extension:functorattrib:makeArgs:local:attrOccursOnHead";
		silver.extension.functorattrib.PmakeArgs.occurs_local[silver.extension.functorattrib.Init.validTypeHead__ON__silver_extension_functorattrib_makeArgs] = "silver:extension:functorattrib:makeArgs:local:validTypeHead";
		//	local attribute annoName::QName;
		silver.extension.functorattrib.PmakeAnnoArgs.localInheritedAttributes[silver.extension.functorattrib.Init.annoName__ON__silver_extension_functorattrib_makeAnnoArgs] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
		silver.extension.functorattrib.PmakeAnnoArgs.occurs_local[silver.extension.functorattrib.Init.annoName__ON__silver_extension_functorattrib_makeAnnoArgs] = "silver:extension:functorattrib:makeAnnoArgs:local:annoName";
		//	local attribute topName::QName;
		silver.extension.functorattrib.PpropagateOne.localInheritedAttributes[silver.extension.functorattrib.Init.topName__ON__silver_extension_functorattrib_propagateOne] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
		silver.extension.functorattrib.PpropagateOne.occurs_local[silver.extension.functorattrib.Init.topName__ON__silver_extension_functorattrib_propagateOne] = "silver:extension:functorattrib:propagateOne:local:topName";
		//	local attribute prodName::QName;
		silver.extension.functorattrib.PpropagateOne.localInheritedAttributes[silver.extension.functorattrib.Init.prodName__ON__silver_extension_functorattrib_propagateOne] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
		silver.extension.functorattrib.PpropagateOne.occurs_local[silver.extension.functorattrib.Init.prodName__ON__silver_extension_functorattrib_propagateOne] = "silver:extension:functorattrib:propagateOne:local:prodName";
		//	local attribute inputs::AppExprs;
		silver.extension.functorattrib.PpropagateOne.localInheritedAttributes[silver.extension.functorattrib.Init.inputs__ON__silver_extension_functorattrib_propagateOne] = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];
		silver.extension.functorattrib.PpropagateOne.occurs_local[silver.extension.functorattrib.Init.inputs__ON__silver_extension_functorattrib_propagateOne] = "silver:extension:functorattrib:propagateOne:local:inputs";
		//	local attribute annotations::AnnoAppExprs;
		silver.extension.functorattrib.PpropagateOne.localInheritedAttributes[silver.extension.functorattrib.Init.annotations__ON__silver_extension_functorattrib_propagateOne] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];
		silver.extension.functorattrib.PpropagateOne.occurs_local[silver.extension.functorattrib.Init.annotations__ON__silver_extension_functorattrib_propagateOne] = "silver:extension:functorattrib:propagateOne:local:annotations";
	}

	private static void initProductionAttributeDefinitions(){
		silver.extension.functorattrib.PpropagateAttrDcl.initProductionAttributeDefinitions();
		//FUNCTION makeArgs [AppExpr] ::= loc::Location env::Decorated Env attrName::QName inputs::[NamedSignatureElement] 
		// attrName.env = env
		silver.extension.functorattrib.PmakeArgs.childInheritedAttributes[silver.extension.functorattrib.PmakeArgs.i_attrName][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childAsIs(silver.extension.functorattrib.PmakeArgs.i_env)); } };
		// at = qName(loc, head(inputs).elementName)
		silver.extension.functorattrib.PmakeArgs.localAttributes[silver.extension.functorattrib.Init.at__ON__silver_extension_functorattrib_makeArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeArgs.i_loc)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsLazy(silver.extension.functorattrib.PmakeArgs.i_inputs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } })); } };
		// at.env = env
		silver.extension.functorattrib.PmakeArgs.localInheritedAttributes[silver.extension.functorattrib.Init.at__ON__silver_extension_functorattrib_makeArgs][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childAsIs(silver.extension.functorattrib.PmakeArgs.i_env)); } };
		// attrOccursOnHead = ! null(flatMap(getOccursDcl(_, head(inputs).typerep.typeName, env), map((.fullName), attrName.lookupAttribute.dcls)))
		silver.extension.functorattrib.PmakeArgs.localAttributes[silver.extension.functorattrib.Init.attrOccursOnHead__ON__silver_extension_functorattrib_makeArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PgetOccursDcl.factory.invokePartial(new int[]{1, 2}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsLazy(silver.extension.functorattrib.PmakeArgs.i_inputs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.childAsIsLazy(silver.extension.functorattrib.PmakeArgs.i_env)}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo, context), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.extension.functorattrib.PmakeArgs.i_attrName).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })); } })); } }))); } };
		// validTypeHead = head(inputs).typerep.isDecorable
		silver.extension.functorattrib.PmakeArgs.localAttributes[silver.extension.functorattrib.Init.validTypeHead__ON__silver_extension_functorattrib_makeArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsLazy(silver.extension.functorattrib.PmakeArgs.i_inputs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type)); } };
		//FUNCTION makeAnnoArgs [AnnoExpr] ::= loc::Location baseName::QName inputs::[NamedSignatureElement] 
		// annoName = qName(loc, last(explode(":", head(inputs).elementName)))
		silver.extension.functorattrib.PmakeAnnoArgs.localAttributes[silver.extension.functorattrib.Init.annoName__ON__silver_extension_functorattrib_makeAnnoArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.functorattrib.PmakeAnnoArgs.i_loc)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Plast.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pexplode.invoke((new common.StringCatter(":")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsLazy(silver.extension.functorattrib.PmakeAnnoArgs.i_inputs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } })); } })); } })); } };
		silver.extension.functorattrib.PpropagateOne.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_extension_functorattrib_propagateAttrDcl = 0;
	public static int count_local__ON__silver_extension_functorattrib_makeArgs = 0;
	public static int count_local__ON__silver_extension_functorattrib_makeAnnoArgs = 0;
	public static int count_local__ON__silver_extension_functorattrib_propagateOne = 0;
public static final int at__ON__silver_extension_functorattrib_makeArgs = silver.extension.functorattrib.Init.count_local__ON__silver_extension_functorattrib_makeArgs++;
public static final int attrOccursOnHead__ON__silver_extension_functorattrib_makeArgs = silver.extension.functorattrib.Init.count_local__ON__silver_extension_functorattrib_makeArgs++;
public static final int validTypeHead__ON__silver_extension_functorattrib_makeArgs = silver.extension.functorattrib.Init.count_local__ON__silver_extension_functorattrib_makeArgs++;
public static final int annoName__ON__silver_extension_functorattrib_makeAnnoArgs = silver.extension.functorattrib.Init.count_local__ON__silver_extension_functorattrib_makeAnnoArgs++;
public static final int topName__ON__silver_extension_functorattrib_propagateOne = silver.extension.functorattrib.Init.count_local__ON__silver_extension_functorattrib_propagateOne++;
public static final int prodName__ON__silver_extension_functorattrib_propagateOne = silver.extension.functorattrib.Init.count_local__ON__silver_extension_functorattrib_propagateOne++;
public static final int inputs__ON__silver_extension_functorattrib_propagateOne = silver.extension.functorattrib.Init.count_local__ON__silver_extension_functorattrib_propagateOne++;
public static final int annotations__ON__silver_extension_functorattrib_propagateOne = silver.extension.functorattrib.Init.count_local__ON__silver_extension_functorattrib_propagateOne++;
}
