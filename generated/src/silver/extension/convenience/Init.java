package silver.extension.convenience;

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
		silver.modification.copper.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.extension.convenience.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.modification.copper.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.core.Init.init();
		silver.definition.env.Init.init();
		silver.extension.convenience.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.modification.copper.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.extension.convenience.Init.postInit();


		common.Decorator.applyDecorators(silver.extension.convenience.NQNameWithTL.decorators, PqNameWithTL.class);
		common.Decorator.applyDecorators(silver.extension.convenience.NQNames2.decorators, PqNames2Two.class);
		common.Decorator.applyDecorators(silver.extension.convenience.NQNames2.decorators, PqNames2Cons.class);
		common.Decorator.applyDecorators(silver.extension.convenience.NQNames.decorators, PqNamesSingle.class);
		common.Decorator.applyDecorators(silver.extension.convenience.NQNames.decorators, PqNamesCons.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PmultipleAttributionDclsManyMany.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PmultipleAttributionDclsSingleMany.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PmultipleAttributionDclsManySingle.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PmultipleAnnotationDclsManyMany.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PmultipleAnnotationDclsSingleMany.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PmultipleAnnotationDclsManySingle.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnonterminalWithDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PattributeDclInhMultiple.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PattributeDclSynMultiple.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PshortLocalDecl.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PshortLocalDeclwKwds.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PshortProductionDecl.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PshortProductionDeclwKwds.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PchildrenRef.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PproductionDclC.class);
		common.Decorator.applyDecorators(silver.extension.convenience.NProductionDclStmts.decorators, PproductionDclStmtsOne.class);
		common.Decorator.applyDecorators(silver.extension.convenience.NProductionDclStmts.decorators, PproductionDclStmtsCons.class);
		common.Decorator.applyDecorators(silver.extension.convenience.NProductionDclStmt.decorators, PproductionDclStmt.class);
		common.Decorator.applyDecorators(silver.extension.convenience.NOptionalName.decorators, PnoOptionalName.class);
		common.Decorator.applyDecorators(silver.extension.convenience.NOptionalName.decorators, PanOptionalName.class);
		common.Decorator.applyDecorators(silver.extension.convenience.NOptionalAction.decorators, PnoOptionalAction.class);
		common.Decorator.applyDecorators(silver.extension.convenience.NOptionalAction.decorators, PanOptionalAction.class);
	}

	private static void setupInheritedAttributes(){
		silver.extension.convenience.NQNameWithTL.occurs_syn[silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_QNameWithTL] = "silver:definition:env:pp";
		silver.extension.convenience.NQNameWithTL.occurs_syn[silver.extension.convenience.Init.silver_extension_convenience_qnwtQN__ON__silver_extension_convenience_QNameWithTL] = "silver:extension:convenience:qnwtQN";
		silver.extension.convenience.NQNameWithTL.occurs_syn[silver.extension.convenience.Init.silver_extension_convenience_qnwtTL__ON__silver_extension_convenience_QNameWithTL] = "silver:extension:convenience:qnwtTL";
		silver.extension.convenience.NQNames2.occurs_syn[silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_QNames2] = "silver:definition:env:pp";
		silver.extension.convenience.NQNames2.occurs_syn[silver.extension.convenience.Init.silver_extension_convenience_qnames__ON__silver_extension_convenience_QNames2] = "silver:extension:convenience:qnames";
		silver.extension.convenience.NQNames.occurs_syn[silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_QNames] = "silver:definition:env:pp";
		silver.extension.convenience.NQNames.occurs_syn[silver.extension.convenience.Init.silver_extension_convenience_qnames__ON__silver_extension_convenience_QNames] = "silver:extension:convenience:qnames";
		silver.extension.convenience.PchildrenRef.occurs_local[silver.extension.convenience.Init.ref__ON__silver_extension_convenience_childrenRef] = "silver:extension:convenience:childrenRef:local:ref";
		silver.extension.convenience.NProductionDclStmts.occurs_syn[silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_ProductionDclStmts] = "silver:definition:env:pp";
		silver.extension.convenience.NProductionDclStmts.occurs_syn[silver.extension.convenience.Init.silver_extension_convenience_proddcls__ON__silver_extension_convenience_ProductionDclStmts] = "silver:extension:convenience:proddcls";
		silver.extension.convenience.NProductionDclStmts.occurs_inh[silver.extension.convenience.Init.silver_extension_convenience_lhsdcl__ON__silver_extension_convenience_ProductionDclStmts] = "silver:extension:convenience:lhsdcl";
		silver.extension.convenience.NProductionDclStmts.decorators.add(silver.extension.convenience.Dlhsdcl.singleton);
		silver.extension.convenience.NProductionDclStmts.occurs_inh[silver.extension.convenience.Init.silver_definition_core_grammarName__ON__silver_extension_convenience_ProductionDclStmts] = "silver:definition:core:grammarName";
		silver.extension.convenience.NProductionDclStmts.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.extension.convenience.NProductionDclStmt.occurs_syn[silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_ProductionDclStmt] = "silver:definition:env:pp";
		silver.extension.convenience.NProductionDclStmt.occurs_syn[silver.extension.convenience.Init.silver_extension_convenience_proddcls__ON__silver_extension_convenience_ProductionDclStmt] = "silver:extension:convenience:proddcls";
		silver.extension.convenience.NProductionDclStmt.occurs_inh[silver.extension.convenience.Init.silver_extension_convenience_lhsdcl__ON__silver_extension_convenience_ProductionDclStmt] = "silver:extension:convenience:lhsdcl";
		silver.extension.convenience.NProductionDclStmt.decorators.add(silver.extension.convenience.Dlhsdcl.singleton);
		silver.extension.convenience.NProductionDclStmt.occurs_inh[silver.extension.convenience.Init.silver_definition_core_grammarName__ON__silver_extension_convenience_ProductionDclStmt] = "silver:definition:core:grammarName";
		silver.extension.convenience.NProductionDclStmt.decorators.add(silver.definition.core.DgrammarName.singleton);
		//	local attribute nme::Name;
		silver.extension.convenience.PproductionDclStmt.localInheritedAttributes[silver.extension.convenience.Init.nme__ON__silver_extension_convenience_productionDclStmt] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
		silver.extension.convenience.PproductionDclStmt.occurs_local[silver.extension.convenience.Init.nme__ON__silver_extension_convenience_productionDclStmt] = "silver:extension:convenience:productionDclStmt:local:nme";
		//	local attribute newSig::ProductionSignature;
		silver.extension.convenience.PproductionDclStmt.localInheritedAttributes[silver.extension.convenience.Init.newSig__ON__silver_extension_convenience_productionDclStmt] = new common.Lazy[silver.definition.core.NProductionSignature.num_inh_attrs];
		silver.extension.convenience.PproductionDclStmt.occurs_local[silver.extension.convenience.Init.newSig__ON__silver_extension_convenience_productionDclStmt] = "silver:extension:convenience:productionDclStmt:local:newSig";
	}

	private static void initProductionAttributeDefinitions(){
		silver.extension.convenience.PqNameWithTL.initProductionAttributeDefinitions();
		silver.extension.convenience.PqNames2Two.initProductionAttributeDefinitions();
		silver.extension.convenience.PqNames2Cons.initProductionAttributeDefinitions();
		silver.extension.convenience.PqNamesSingle.initProductionAttributeDefinitions();
		silver.extension.convenience.PqNamesCons.initProductionAttributeDefinitions();
		//FUNCTION makeOccursDcls AGDcl ::= l::Location ats::[QNameWithTL] nts::[QNameWithTL] 
		//FUNCTION makeOccursDclsHelp AGDcl ::= l::Location at::QNameWithTL nts::[QNameWithTL] 
		silver.extension.convenience.PmultipleAttributionDclsManyMany.initProductionAttributeDefinitions();
		silver.extension.convenience.PmultipleAttributionDclsSingleMany.initProductionAttributeDefinitions();
		silver.extension.convenience.PmultipleAttributionDclsManySingle.initProductionAttributeDefinitions();
		silver.extension.convenience.PmultipleAnnotationDclsManyMany.initProductionAttributeDefinitions();
		silver.extension.convenience.PmultipleAnnotationDclsSingleMany.initProductionAttributeDefinitions();
		silver.extension.convenience.PmultipleAnnotationDclsManySingle.initProductionAttributeDefinitions();
		silver.extension.convenience.PnonterminalWithDcl.initProductionAttributeDefinitions();
		silver.extension.convenience.PattributeDclInhMultiple.initProductionAttributeDefinitions();
		silver.extension.convenience.PattributeDclSynMultiple.initProductionAttributeDefinitions();
		silver.extension.convenience.PshortLocalDecl.initProductionAttributeDefinitions();
		silver.extension.convenience.PshortLocalDeclwKwds.initProductionAttributeDefinitions();
		silver.extension.convenience.PshortProductionDecl.initProductionAttributeDefinitions();
		silver.extension.convenience.PshortProductionDeclwKwds.initProductionAttributeDefinitions();
		silver.extension.convenience.PchildrenRef.initProductionAttributeDefinitions();
		//FUNCTION findChild Maybe<String> ::= i::Integer s::[String] 
		silver.extension.convenience.PproductionDclC.initProductionAttributeDefinitions();
		silver.extension.convenience.PproductionDclStmtsOne.initProductionAttributeDefinitions();
		silver.extension.convenience.PproductionDclStmtsCons.initProductionAttributeDefinitions();
		silver.extension.convenience.PproductionDclStmt.initProductionAttributeDefinitions();
		silver.extension.convenience.PnoOptionalName.initProductionAttributeDefinitions();
		silver.extension.convenience.PanOptionalName.initProductionAttributeDefinitions();
		silver.extension.convenience.PnoOptionalAction.initProductionAttributeDefinitions();
		silver.extension.convenience.PanOptionalAction.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__QNameWithTL = 0;
	public static int count_syn__ON__QNameWithTL = 0;
	public static int count_local__ON__silver_extension_convenience_qNameWithTL = 0;
	public static int count_inh__ON__QNames2 = 0;
	public static int count_syn__ON__QNames2 = 0;
	public static int count_inh__ON__QNames = 0;
	public static int count_syn__ON__QNames = 0;
	public static int count_local__ON__silver_extension_convenience_qNames2Two = 0;
	public static int count_local__ON__silver_extension_convenience_qNames2Cons = 0;
	public static int count_local__ON__silver_extension_convenience_qNamesSingle = 0;
	public static int count_local__ON__silver_extension_convenience_qNamesCons = 0;
	public static int count_local__ON__silver_extension_convenience_makeOccursDcls = 0;
	public static int count_local__ON__silver_extension_convenience_makeOccursDclsHelp = 0;
	public static int count_local__ON__silver_extension_convenience_multipleAttributionDclsManyMany = 0;
	public static int count_local__ON__silver_extension_convenience_multipleAttributionDclsSingleMany = 0;
	public static int count_local__ON__silver_extension_convenience_multipleAttributionDclsManySingle = 0;
	public static int count_local__ON__silver_extension_convenience_multipleAnnotationDclsManyMany = 0;
	public static int count_local__ON__silver_extension_convenience_multipleAnnotationDclsSingleMany = 0;
	public static int count_local__ON__silver_extension_convenience_multipleAnnotationDclsManySingle = 0;
	public static int count_local__ON__silver_extension_convenience_nonterminalWithDcl = 0;
	public static int count_local__ON__silver_extension_convenience_attributeDclInhMultiple = 0;
	public static int count_local__ON__silver_extension_convenience_attributeDclSynMultiple = 0;
	public static int count_local__ON__silver_extension_convenience_shortLocalDecl = 0;
	public static int count_local__ON__silver_extension_convenience_shortLocalDeclwKwds = 0;
	public static int count_local__ON__silver_extension_convenience_shortProductionDecl = 0;
	public static int count_local__ON__silver_extension_convenience_shortProductionDeclwKwds = 0;
	public static int count_local__ON__silver_extension_convenience_childrenRef = 0;
	public static int count_local__ON__silver_extension_convenience_findChild = 0;
	public static int count_inh__ON__ProductionDclStmts = 0;
	public static int count_syn__ON__ProductionDclStmts = 0;
	public static int count_inh__ON__ProductionDclStmt = 0;
	public static int count_syn__ON__ProductionDclStmt = 0;
	public static int count_local__ON__silver_extension_convenience_productionDclC = 0;
	public static int count_local__ON__silver_extension_convenience_productionDclStmtsOne = 0;
	public static int count_local__ON__silver_extension_convenience_productionDclStmtsCons = 0;
	public static int count_local__ON__silver_extension_convenience_productionDclStmt = 0;
	public static int count_inh__ON__OptionalName = 0;
	public static int count_syn__ON__OptionalName = 0;
	public static int count_local__ON__silver_extension_convenience_noOptionalName = 0;
	public static int count_local__ON__silver_extension_convenience_anOptionalName = 0;
	public static int count_inh__ON__OptionalAction = 0;
	public static int count_syn__ON__OptionalAction = 0;
	public static int count_local__ON__silver_extension_convenience_noOptionalAction = 0;
	public static int count_local__ON__silver_extension_convenience_anOptionalAction = 0;
public static final int silver_definition_env_pp__ON__silver_extension_convenience_QNameWithTL = silver.extension.convenience.Init.count_syn__ON__QNameWithTL++;
public static final int silver_extension_convenience_qnwtQN__ON__silver_extension_convenience_QNameWithTL = silver.extension.convenience.Init.count_syn__ON__QNameWithTL++;
public static final int silver_extension_convenience_qnwtTL__ON__silver_extension_convenience_QNameWithTL = silver.extension.convenience.Init.count_syn__ON__QNameWithTL++;
public static final int silver_definition_env_pp__ON__silver_extension_convenience_QNames2 = silver.extension.convenience.Init.count_syn__ON__QNames2++;
public static final int silver_extension_convenience_qnames__ON__silver_extension_convenience_QNames2 = silver.extension.convenience.Init.count_syn__ON__QNames2++;
public static final int silver_definition_env_pp__ON__silver_extension_convenience_QNames = silver.extension.convenience.Init.count_syn__ON__QNames++;
public static final int silver_extension_convenience_qnames__ON__silver_extension_convenience_QNames = silver.extension.convenience.Init.count_syn__ON__QNames++;
public static final int ref__ON__silver_extension_convenience_childrenRef = silver.extension.convenience.Init.count_local__ON__silver_extension_convenience_childrenRef++;
public static final int silver_definition_env_pp__ON__silver_extension_convenience_ProductionDclStmts = silver.extension.convenience.Init.count_syn__ON__ProductionDclStmts++;
public static final int silver_extension_convenience_proddcls__ON__silver_extension_convenience_ProductionDclStmts = silver.extension.convenience.Init.count_syn__ON__ProductionDclStmts++;
public static final int silver_extension_convenience_lhsdcl__ON__silver_extension_convenience_ProductionDclStmts = silver.extension.convenience.Init.count_inh__ON__ProductionDclStmts++;
public static final int silver_definition_core_grammarName__ON__silver_extension_convenience_ProductionDclStmts = silver.extension.convenience.Init.count_inh__ON__ProductionDclStmts++;
public static final int silver_definition_env_pp__ON__silver_extension_convenience_ProductionDclStmt = silver.extension.convenience.Init.count_syn__ON__ProductionDclStmt++;
public static final int silver_extension_convenience_proddcls__ON__silver_extension_convenience_ProductionDclStmt = silver.extension.convenience.Init.count_syn__ON__ProductionDclStmt++;
public static final int silver_extension_convenience_lhsdcl__ON__silver_extension_convenience_ProductionDclStmt = silver.extension.convenience.Init.count_inh__ON__ProductionDclStmt++;
public static final int silver_definition_core_grammarName__ON__silver_extension_convenience_ProductionDclStmt = silver.extension.convenience.Init.count_inh__ON__ProductionDclStmt++;
public static final int nme__ON__silver_extension_convenience_productionDclStmt = silver.extension.convenience.Init.count_local__ON__silver_extension_convenience_productionDclStmt++;
public static final int newSig__ON__silver_extension_convenience_productionDclStmt = silver.extension.convenience.Init.count_local__ON__silver_extension_convenience_productionDclStmt++;
}
