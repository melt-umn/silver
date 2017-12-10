package silver.modification.collection;

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
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.util.Init.initAllStatics();
		silver.extension.list.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.modification.collection.Init.initAllStatics();

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
		silver.definition.core.Init.init();
		silver.definition.env.Init.init();
		silver.util.Init.init();
		silver.extension.list.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.modification.collection.Init.init();

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
		silver.definition.core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.util.Init.postInit();
		silver.extension.list.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.modification.collection.Init.postInit();


		common.Decorator.applyDecorators(silver.modification.collection.NNameOrBOperator.decorators, PnameOperator.class);
		common.Decorator.applyDecorators(silver.modification.collection.NNameOrBOperator.decorators, PplusplusOperator.class);
		common.Decorator.applyDecorators(silver.modification.collection.NNameOrBOperator.decorators, PborOperator.class);
		common.Decorator.applyDecorators(silver.modification.collection.NNameOrBOperator.decorators, PbandOperator.class);
		common.Decorator.applyDecorators(silver.modification.collection.NOperation.decorators, PfunctionOperation.class);
		common.Decorator.applyDecorators(silver.modification.collection.NOperation.decorators, PproductionOperation.class);
		common.Decorator.applyDecorators(silver.modification.collection.NOperation.decorators, PplusPlusOperationString.class);
		common.Decorator.applyDecorators(silver.modification.collection.NOperation.decorators, PplusPlusOperationList.class);
		common.Decorator.applyDecorators(silver.modification.collection.NOperation.decorators, PborOperation.class);
		common.Decorator.applyDecorators(silver.modification.collection.NOperation.decorators, PbandOperation.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PcollectionAttributeDclSyn.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PcollectionAttributeDclInh.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PcollectionAttributeDclProd.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PerrorCollectionValueDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PerrorColNormalValueDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PbaseCollectionValueDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PappendCollectionValueDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PsynBaseColAttributeDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PsynAppendColAttributeDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PinhBaseColAttributeDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PinhAppendColAttributeDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PattrContainsAppend.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PattrContainsBase.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PvalContainsAppend.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PvalContainsBase.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PsynCollectionDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PinhCollectionDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PlocalCollectionDcl.class);
	}

	private static void setupInheritedAttributes(){
		silver.modification.collection.NNameOrBOperator.occurs_inh[silver.modification.collection.Init.silver_definition_env_config__ON__silver_modification_collection_NameOrBOperator] = "silver:definition:env:config";
		silver.modification.collection.NNameOrBOperator.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.collection.NNameOrBOperator.occurs_inh[silver.modification.collection.Init.silver_definition_core_grammarName__ON__silver_modification_collection_NameOrBOperator] = "silver:definition:core:grammarName";
		silver.modification.collection.NNameOrBOperator.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.collection.NNameOrBOperator.occurs_syn[silver.modification.collection.Init.silver_definition_core_errors__ON__silver_modification_collection_NameOrBOperator] = "silver:definition:core:errors";
		silver.modification.collection.NNameOrBOperator.occurs_inh[silver.modification.collection.Init.silver_definition_env_env__ON__silver_modification_collection_NameOrBOperator] = "silver:definition:env:env";
		silver.modification.collection.NNameOrBOperator.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.collection.NNameOrBOperator.occurs_syn[silver.modification.collection.Init.silver_definition_env_pp__ON__silver_modification_collection_NameOrBOperator] = "silver:definition:env:pp";
		silver.modification.collection.NNameOrBOperator.occurs_syn[silver.modification.collection.Init.silver_modification_collection_operation__ON__silver_modification_collection_NameOrBOperator] = "silver:modification:collection:operation";
		silver.modification.collection.NNameOrBOperator.occurs_inh[silver.modification.collection.Init.silver_modification_collection_operatorForType__ON__silver_modification_collection_NameOrBOperator] = "silver:modification:collection:operatorForType";
		silver.modification.collection.NOperation.occurs_syn[silver.modification.collection.Init.silver_definition_env_unparse__ON__silver_modification_collection_Operation] = "silver:definition:env:unparse";
		//	local attribute checkOperationType::TypeCheck;
		silver.modification.collection.PnameOperator.localInheritedAttributes[silver.modification.collection.Init.checkOperationType__ON__silver_modification_collection_nameOperator] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.collection.PnameOperator.occurs_local[silver.modification.collection.Init.checkOperationType__ON__silver_modification_collection_nameOperator] = "silver:modification:collection:nameOperator:local:checkOperationType";
		silver.modification.collection.PnameOperator.occurs_local[silver.modification.collection.Init.operationErrors__ON__silver_modification_collection_nameOperator] = "silver:modification:collection:nameOperator:local:operationErrors";
		silver.modification.collection.PcollectionAttributeDclSyn.occurs_local[silver.modification.collection.Init.fName__ON__silver_modification_collection_collectionAttributeDclSyn] = "silver:modification:collection:collectionAttributeDclSyn:local:fName";
		silver.modification.collection.PcollectionAttributeDclInh.occurs_local[silver.modification.collection.Init.fName__ON__silver_modification_collection_collectionAttributeDclInh] = "silver:modification:collection:collectionAttributeDclInh:local:fName";
		silver.modification.collection.PcollectionAttributeDclProd.occurs_local[silver.modification.collection.Init.fName__ON__silver_modification_collection_collectionAttributeDclProd] = "silver:modification:collection:collectionAttributeDclProd:local:fName";
		//	local attribute errCheck1::TypeCheck;
		silver.modification.collection.PsynBaseColAttributeDef.localInheritedAttributes[silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_synBaseColAttributeDef] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.collection.PsynBaseColAttributeDef.occurs_local[silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_synBaseColAttributeDef] = "silver:modification:collection:synBaseColAttributeDef:local:errCheck1";
		//	local attribute errCheck1::TypeCheck;
		silver.modification.collection.PsynAppendColAttributeDef.localInheritedAttributes[silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_synAppendColAttributeDef] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.collection.PsynAppendColAttributeDef.occurs_local[silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_synAppendColAttributeDef] = "silver:modification:collection:synAppendColAttributeDef:local:errCheck1";
		//	local attribute errCheck1::TypeCheck;
		silver.modification.collection.PinhBaseColAttributeDef.localInheritedAttributes[silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_inhBaseColAttributeDef] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.collection.PinhBaseColAttributeDef.occurs_local[silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_inhBaseColAttributeDef] = "silver:modification:collection:inhBaseColAttributeDef:local:errCheck1";
		//	local attribute errCheck1::TypeCheck;
		silver.modification.collection.PinhAppendColAttributeDef.localInheritedAttributes[silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_inhAppendColAttributeDef] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.collection.PinhAppendColAttributeDef.occurs_local[silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_inhAppendColAttributeDef] = "silver:modification:collection:inhAppendColAttributeDef:local:errCheck1";
		silver.definition.env.NDclInfo.occurs_syn[silver.modification.collection.Init.silver_modification_collection_attrBaseDefDispatcher__ON__silver_definition_env_DclInfo] = "silver:modification:collection:attrBaseDefDispatcher";
		silver.definition.env.NDclInfo.occurs_syn[silver.modification.collection.Init.silver_modification_collection_attrAppendDefDispatcher__ON__silver_definition_env_DclInfo] = "silver:modification:collection:attrAppendDefDispatcher";
		silver.definition.env.NDclInfo.occurs_syn[silver.modification.collection.Init.silver_modification_collection_baseDefDispatcher__ON__silver_definition_env_DclInfo] = "silver:modification:collection:baseDefDispatcher";
		silver.definition.env.NDclInfo.occurs_syn[silver.modification.collection.Init.silver_modification_collection_appendDefDispatcher__ON__silver_definition_env_DclInfo] = "silver:modification:collection:appendDefDispatcher";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.collection.PnameOperator.initProductionAttributeDefinitions();
		silver.modification.collection.PplusplusOperator.initProductionAttributeDefinitions();
		silver.modification.collection.PborOperator.initProductionAttributeDefinitions();
		silver.modification.collection.PbandOperator.initProductionAttributeDefinitions();
		silver.modification.collection.PfunctionOperation.initProductionAttributeDefinitions();
		silver.modification.collection.PproductionOperation.initProductionAttributeDefinitions();
		silver.modification.collection.PplusPlusOperationString.initProductionAttributeDefinitions();
		silver.modification.collection.PplusPlusOperationList.initProductionAttributeDefinitions();
		silver.modification.collection.PborOperation.initProductionAttributeDefinitions();
		silver.modification.collection.PbandOperation.initProductionAttributeDefinitions();
		silver.modification.collection.PcollectionAttributeDclSyn.initProductionAttributeDefinitions();
		silver.modification.collection.PcollectionAttributeDclInh.initProductionAttributeDefinitions();
		silver.modification.collection.PcollectionAttributeDclProd.initProductionAttributeDefinitions();
		silver.modification.collection.PerrorCollectionValueDef.initProductionAttributeDefinitions();
		silver.modification.collection.PerrorColNormalValueDef.initProductionAttributeDefinitions();
		silver.modification.collection.PbaseCollectionValueDef.initProductionAttributeDefinitions();
		silver.modification.collection.PappendCollectionValueDef.initProductionAttributeDefinitions();
		silver.modification.collection.PsynBaseColAttributeDef.initProductionAttributeDefinitions();
		silver.modification.collection.PsynAppendColAttributeDef.initProductionAttributeDefinitions();
		silver.modification.collection.PinhBaseColAttributeDef.initProductionAttributeDefinitions();
		silver.modification.collection.PinhAppendColAttributeDef.initProductionAttributeDefinitions();
		silver.modification.collection.PattrContainsAppend.initProductionAttributeDefinitions();
		silver.modification.collection.PattrContainsBase.initProductionAttributeDefinitions();
		silver.modification.collection.PvalContainsAppend.initProductionAttributeDefinitions();
		silver.modification.collection.PvalContainsBase.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for DclInfo
		// top.attrBaseDefDispatcher = \ dl::Decorated DefLHS attr::Decorated QNameAttrOccur e::Expr l::Location  -> errorAttributeDef([ err(l, "The ':=' operator can only be used for collections. " ++ attr.pp ++ " is not a collection.") ], dl, attr, e,location=l)
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.modification.collection.Init.silver_modification_collection_attrBaseDefDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<silver.definition.core.NProductionStmt>() {
  public final silver.definition.core.NProductionStmt invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_7075_dl = args[0];
final Object __SV_LAMBDA_PARAM_7076_attr = args[1];
final Object __SV_LAMBDA_PARAM_7077_e = args[2];
final Object __SV_LAMBDA_PARAM_7078_l = args[3];

    return ((silver.definition.core.NProductionStmt)new silver.definition.core.PerrorAttributeDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(((core.NLocation)common.Util.demand(__SV_LAMBDA_PARAM_7078_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("The ':=' operator can only be used for collections. ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_7076_attr)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)), (common.StringCatter)(new common.StringCatter(" is not a collection.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_7075_dl)), ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_7076_attr)), ((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_7077_e)), ((core.NLocation)common.Util.demand(__SV_LAMBDA_PARAM_7078_l))));
  }
}); } };
		// top.attrAppendDefDispatcher = \ dl::Decorated DefLHS attr::Decorated QNameAttrOccur e::Expr l::Location  -> errorAttributeDef([ err(l, "The '<-' operator can only be used for collections. " ++ attr.pp ++ " is not a collection.") ], dl, attr, e,location=l)
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.modification.collection.Init.silver_modification_collection_attrAppendDefDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<silver.definition.core.NProductionStmt>() {
  public final silver.definition.core.NProductionStmt invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_7079_dl = args[0];
final Object __SV_LAMBDA_PARAM_7080_attr = args[1];
final Object __SV_LAMBDA_PARAM_7081_e = args[2];
final Object __SV_LAMBDA_PARAM_7082_l = args[3];

    return ((silver.definition.core.NProductionStmt)new silver.definition.core.PerrorAttributeDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(((core.NLocation)common.Util.demand(__SV_LAMBDA_PARAM_7082_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("The '<-' operator can only be used for collections. ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_7080_attr)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)), (common.StringCatter)(new common.StringCatter(" is not a collection.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_7079_dl)), ((common.DecoratedNode)common.Util.demand(__SV_LAMBDA_PARAM_7080_attr)), ((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_7081_e)), ((core.NLocation)common.Util.demand(__SV_LAMBDA_PARAM_7082_l))));
  }
}); } };
		// top.baseDefDispatcher = errorCollectionValueDef(_, _,location=_)
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.modification.collection.Init.silver_modification_collection_baseDefDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.modification.collection.PerrorCollectionValueDef.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		// top.appendDefDispatcher = errorCollectionValueDef(_, _,location=_)
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.modification.collection.Init.silver_modification_collection_appendDefDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.modification.collection.PerrorCollectionValueDef.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		silver.modification.collection.PsynCollectionDcl.initProductionAttributeDefinitions();
		silver.modification.collection.PinhCollectionDcl.initProductionAttributeDefinitions();
		silver.modification.collection.PlocalCollectionDcl.initProductionAttributeDefinitions();
		//FUNCTION synColDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type o::Operation 
		//FUNCTION inhColDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type o::Operation 
		//FUNCTION localColDef Def ::= sg::String sl::Location fn::String ty::Type o::Operation 
	}

	public static int count_inh__ON__NameOrBOperator = 0;
	public static int count_syn__ON__NameOrBOperator = 0;
	public static int count_inh__ON__Operation = 0;
	public static int count_syn__ON__Operation = 0;
	public static int count_local__ON__silver_modification_collection_nameOperator = 0;
	public static int count_local__ON__silver_modification_collection_plusplusOperator = 0;
	public static int count_local__ON__silver_modification_collection_borOperator = 0;
	public static int count_local__ON__silver_modification_collection_bandOperator = 0;
	public static int count_local__ON__silver_modification_collection_functionOperation = 0;
	public static int count_local__ON__silver_modification_collection_productionOperation = 0;
	public static int count_local__ON__silver_modification_collection_plusPlusOperationString = 0;
	public static int count_local__ON__silver_modification_collection_plusPlusOperationList = 0;
	public static int count_local__ON__silver_modification_collection_borOperation = 0;
	public static int count_local__ON__silver_modification_collection_bandOperation = 0;
	public static int count_local__ON__silver_modification_collection_collectionAttributeDclSyn = 0;
	public static int count_local__ON__silver_modification_collection_collectionAttributeDclInh = 0;
	public static int count_local__ON__silver_modification_collection_collectionAttributeDclProd = 0;
	public static int count_local__ON__silver_modification_collection_errorCollectionValueDef = 0;
	public static int count_local__ON__silver_modification_collection_errorColNormalValueDef = 0;
	public static int count_local__ON__silver_modification_collection_baseCollectionValueDef = 0;
	public static int count_local__ON__silver_modification_collection_appendCollectionValueDef = 0;
	public static int count_local__ON__silver_modification_collection_synBaseColAttributeDef = 0;
	public static int count_local__ON__silver_modification_collection_synAppendColAttributeDef = 0;
	public static int count_local__ON__silver_modification_collection_inhBaseColAttributeDef = 0;
	public static int count_local__ON__silver_modification_collection_inhAppendColAttributeDef = 0;
	public static int count_local__ON__silver_modification_collection_attrContainsAppend = 0;
	public static int count_local__ON__silver_modification_collection_attrContainsBase = 0;
	public static int count_local__ON__silver_modification_collection_valContainsAppend = 0;
	public static int count_local__ON__silver_modification_collection_valContainsBase = 0;
	public static int count_local__ON__silver_modification_collection_synCollectionDcl = 0;
	public static int count_local__ON__silver_modification_collection_inhCollectionDcl = 0;
	public static int count_local__ON__silver_modification_collection_localCollectionDcl = 0;
	public static int count_local__ON__silver_modification_collection_synColDef = 0;
	public static int count_local__ON__silver_modification_collection_inhColDef = 0;
	public static int count_local__ON__silver_modification_collection_localColDef = 0;
public static final int silver_definition_env_config__ON__silver_modification_collection_NameOrBOperator = silver.modification.collection.Init.count_inh__ON__NameOrBOperator++;
public static final int silver_definition_core_grammarName__ON__silver_modification_collection_NameOrBOperator = silver.modification.collection.Init.count_inh__ON__NameOrBOperator++;
public static final int silver_definition_core_errors__ON__silver_modification_collection_NameOrBOperator = silver.modification.collection.Init.count_syn__ON__NameOrBOperator++;
public static final int silver_definition_env_env__ON__silver_modification_collection_NameOrBOperator = silver.modification.collection.Init.count_inh__ON__NameOrBOperator++;
public static final int silver_definition_env_pp__ON__silver_modification_collection_NameOrBOperator = silver.modification.collection.Init.count_syn__ON__NameOrBOperator++;
public static final int silver_modification_collection_operation__ON__silver_modification_collection_NameOrBOperator = silver.modification.collection.Init.count_syn__ON__NameOrBOperator++;
public static final int silver_modification_collection_operatorForType__ON__silver_modification_collection_NameOrBOperator = silver.modification.collection.Init.count_inh__ON__NameOrBOperator++;
public static final int silver_definition_env_unparse__ON__silver_modification_collection_Operation = silver.modification.collection.Init.count_syn__ON__Operation++;
public static final int checkOperationType__ON__silver_modification_collection_nameOperator = silver.modification.collection.Init.count_local__ON__silver_modification_collection_nameOperator++;
public static final int operationErrors__ON__silver_modification_collection_nameOperator = silver.modification.collection.Init.count_local__ON__silver_modification_collection_nameOperator++;
public static final int fName__ON__silver_modification_collection_collectionAttributeDclSyn = silver.modification.collection.Init.count_local__ON__silver_modification_collection_collectionAttributeDclSyn++;
public static final int fName__ON__silver_modification_collection_collectionAttributeDclInh = silver.modification.collection.Init.count_local__ON__silver_modification_collection_collectionAttributeDclInh++;
public static final int fName__ON__silver_modification_collection_collectionAttributeDclProd = silver.modification.collection.Init.count_local__ON__silver_modification_collection_collectionAttributeDclProd++;
public static final int errCheck1__ON__silver_modification_collection_synBaseColAttributeDef = silver.modification.collection.Init.count_local__ON__silver_modification_collection_synBaseColAttributeDef++;
public static final int errCheck1__ON__silver_modification_collection_synAppendColAttributeDef = silver.modification.collection.Init.count_local__ON__silver_modification_collection_synAppendColAttributeDef++;
public static final int errCheck1__ON__silver_modification_collection_inhBaseColAttributeDef = silver.modification.collection.Init.count_local__ON__silver_modification_collection_inhBaseColAttributeDef++;
public static final int errCheck1__ON__silver_modification_collection_inhAppendColAttributeDef = silver.modification.collection.Init.count_local__ON__silver_modification_collection_inhAppendColAttributeDef++;
public static final int silver_modification_collection_attrBaseDefDispatcher__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_modification_collection_attrAppendDefDispatcher__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_modification_collection_baseDefDispatcher__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_modification_collection_appendDefDispatcher__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
}
