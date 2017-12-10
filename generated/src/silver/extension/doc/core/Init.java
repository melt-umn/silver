package silver.extension.doc.core;

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
		silver.util.treemap.Init.initAllStatics();
		silver.extension.convenience.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.driver.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();
		silver.extension.doc.core.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.util.treemap.Init.init();
		silver.extension.convenience.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.core.Init.init();
		silver.driver.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.driver.util.Init.init();
		silver.extension.doc.core.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.util.treemap.Init.postInit();
		silver.extension.convenience.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.driver.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.driver.util.Init.postInit();
		silver.extension.doc.core.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdocAspectProductionDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnoDocAspectProductionDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdocAspectFunctionDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnoDocAspectFunctionDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, Pconfig.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDocConfigs.decorators, PconsConfigs.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDocConfigs.decorators, PnilConfigs.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDocConfig.decorators, PheaderConfig.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDocConfig.decorators, PsplitFilesConfig.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDocConfig.decorators, PnoDocConfig.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDclComment.decorators, PdclComment.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDclCommentComponents.decorators, PconsCommentComps.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDclCommentComponents.decorators, PnilCommentComps.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDclCommentComponent.decorators, PcomponentLink.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDclCommentComponent.decorators, PcomponentText.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDclCommentComponent.decorators, PcomponentWhiteSpace.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NCommentItem.decorators, PdclCommentItem.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NCommentItem.decorators, PbodilessDclCommentItem.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdocProductionDecl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnoDocProductionDecl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdocConcreteProductionDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnoDocConcreteProductionDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdocNonterminalDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnoDocNonterminalDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdocNonterminalWithDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnoDocNonterminalWithDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdocFunctionDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnoDocFunctionDcl.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDocDclInfo.decorators, PfunctionDocDclInfo.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDocDclInfo.decorators, PfunctionDocDclInfoP.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDocDclInfo.decorators, PproductionDocDclInfo.class);
		common.Decorator.applyDecorators(silver.extension.doc.core.NDocDclInfo.decorators, PproductionDocDclInfoP.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdocGlobalValueDclConcrete.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnoDocGlobalValueDclConcrete.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdocAnnotationDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnoDocAnnotationDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdocAttributionDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnoDocAttributionDcl.class);
	}

	private static void setupInheritedAttributes(){
		silver.driver.util.NRootSpec.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec] = "silver:extension:doc:core:genFiles";
		silver.extension.doc.core.PplaceComment.occurs_local[silver.extension.doc.core.Init.markdown__ON__silver_extension_doc_core_placeComment] = "silver:extension:doc:core:placeComment:local:markdown";
		silver.extension.doc.core.PtoSingleFile.occurs_local[silver.extension.doc.core.Init.commentMarkdown__ON__silver_extension_doc_core_toSingleFile] = "silver:extension:doc:core:toSingleFile:local:commentMarkdown";
		silver.extension.doc.core.NDocConfigs.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfigs] = "silver:extension:doc:core:header";
		silver.extension.doc.core.NDocConfigs.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_splitFiles__ON__silver_extension_doc_core_DocConfigs] = "silver:extension:doc:core:splitFiles";
		silver.extension.doc.core.NDocConfigs.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_noDoc__ON__silver_extension_doc_core_DocConfigs] = "silver:extension:doc:core:noDoc";
		silver.extension.doc.core.NDocConfigs.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_warnings__ON__silver_extension_doc_core_DocConfigs] = "silver:extension:doc:core:warnings";
		silver.extension.doc.core.NDocConfig.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfig] = "silver:extension:doc:core:header";
		silver.extension.doc.core.NDocConfig.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_splitFiles__ON__silver_extension_doc_core_DocConfig] = "silver:extension:doc:core:splitFiles";
		silver.extension.doc.core.NDocConfig.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_noDoc__ON__silver_extension_doc_core_DocConfig] = "silver:extension:doc:core:noDoc";
		silver.extension.doc.core.PconsConfigs.occurs_local[silver.extension.doc.core.Init.headerWarnings__ON__silver_extension_doc_core_consConfigs] = "silver:extension:doc:core:consConfigs:local:headerWarnings";
		silver.extension.doc.core.PconsConfigs.occurs_local[silver.extension.doc.core.Init.splitFilesWarnings__ON__silver_extension_doc_core_consConfigs] = "silver:extension:doc:core:consConfigs:local:splitFilesWarnings";
		silver.extension.doc.core.NDclComment.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclComment] = "silver:extension:doc:core:body";
		silver.extension.doc.core.NDclComment.occurs_inh[silver.extension.doc.core.Init.silver_definition_env_env__ON__silver_extension_doc_core_DclComment] = "silver:definition:env:env";
		silver.extension.doc.core.NDclComment.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.doc.core.NDclCommentComponent.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclCommentComponent] = "silver:extension:doc:core:body";
		silver.extension.doc.core.NDclCommentComponent.occurs_inh[silver.extension.doc.core.Init.silver_definition_env_env__ON__silver_extension_doc_core_DclCommentComponent] = "silver:definition:env:env";
		silver.extension.doc.core.NDclCommentComponent.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.doc.core.NDclCommentComponents.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_DclCommentComponents] = "silver:extension:doc:core:body";
		silver.extension.doc.core.NDclCommentComponents.occurs_inh[silver.extension.doc.core.Init.silver_definition_env_env__ON__silver_extension_doc_core_DclCommentComponents] = "silver:definition:env:env";
		silver.extension.doc.core.NDclCommentComponents.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.doc.core.NDclComment.occurs_inh[silver.extension.doc.core.Init.silver_extension_doc_core_docEnv__ON__silver_extension_doc_core_DclComment] = "silver:extension:doc:core:docEnv";
		silver.extension.doc.core.NDclComment.decorators.add(silver.extension.doc.core.DdocEnv.singleton);
		silver.extension.doc.core.NDclCommentComponent.occurs_inh[silver.extension.doc.core.Init.silver_extension_doc_core_docEnv__ON__silver_extension_doc_core_DclCommentComponent] = "silver:extension:doc:core:docEnv";
		silver.extension.doc.core.NDclCommentComponent.decorators.add(silver.extension.doc.core.DdocEnv.singleton);
		silver.extension.doc.core.NDclCommentComponents.occurs_inh[silver.extension.doc.core.Init.silver_extension_doc_core_docEnv__ON__silver_extension_doc_core_DclCommentComponents] = "silver:extension:doc:core:docEnv";
		silver.extension.doc.core.NDclCommentComponents.decorators.add(silver.extension.doc.core.DdocEnv.singleton);
		//	local attribute dclInfo::DocDclInfo;
		silver.extension.doc.core.PcomponentLink.localInheritedAttributes[silver.extension.doc.core.Init.dclInfo__ON__silver_extension_doc_core_componentLink] = new common.Lazy[silver.extension.doc.core.NDocDclInfo.num_inh_attrs];
		silver.extension.doc.core.PcomponentLink.occurs_local[silver.extension.doc.core.Init.dclInfo__ON__silver_extension_doc_core_componentLink] = "silver:extension:doc:core:componentLink:local:dclInfo";
		silver.extension.doc.core.NCommentItem.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_body__ON__silver_extension_doc_core_CommentItem] = "silver:extension:doc:core:body";
		silver.extension.doc.core.NCommentItem.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_file__ON__silver_extension_doc_core_CommentItem] = "silver:extension:doc:core:file";
		silver.definition.core.NGrammar.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar] = "silver:extension:doc:core:docs";
		silver.definition.core.NRoot.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Root] = "silver:extension:doc:core:docs";
		silver.definition.core.NAGDcls.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls] = "silver:extension:doc:core:docs";
		silver.definition.core.NAGDcl.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = "silver:extension:doc:core:docs";
		silver.definition.core.NGrammar.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_Grammar] = "silver:extension:doc:core:docsHeader";
		silver.definition.core.NRoot.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_Root] = "silver:extension:doc:core:docsHeader";
		silver.definition.core.NAGDcls.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcls] = "silver:extension:doc:core:docsHeader";
		silver.definition.core.NAGDcl.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcl] = "silver:extension:doc:core:docsHeader";
		silver.definition.core.NGrammar.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_Grammar] = "silver:extension:doc:core:docsSplit";
		silver.definition.core.NRoot.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_Root] = "silver:extension:doc:core:docsSplit";
		silver.definition.core.NAGDcls.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcls] = "silver:extension:doc:core:docsSplit";
		silver.definition.core.NAGDcl.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcl] = "silver:extension:doc:core:docsSplit";
		silver.definition.core.NGrammar.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_Grammar] = "silver:extension:doc:core:docsNoDoc";
		silver.definition.core.NRoot.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_Root] = "silver:extension:doc:core:docsNoDoc";
		silver.definition.core.NAGDcls.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcls] = "silver:extension:doc:core:docsNoDoc";
		silver.definition.core.NAGDcl.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcl] = "silver:extension:doc:core:docsNoDoc";
		silver.definition.core.NGrammar.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar] = "silver:extension:doc:core:docDcls";
		silver.definition.core.NRoot.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Root] = "silver:extension:doc:core:docDcls";
		silver.definition.core.NAGDcls.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls] = "silver:extension:doc:core:docDcls";
		silver.definition.core.NAGDcl.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] = "silver:extension:doc:core:docDcls";
		silver.definition.core.NGrammar.occurs_inh[silver.extension.doc.core.Init.silver_extension_doc_core_docEnv__ON__silver_definition_core_Grammar] = "silver:extension:doc:core:docEnv";
		silver.definition.core.NGrammar.decorators.add(silver.extension.doc.core.DdocEnv.singleton);
		silver.definition.core.NRoot.occurs_inh[silver.extension.doc.core.Init.silver_extension_doc_core_docEnv__ON__silver_definition_core_Root] = "silver:extension:doc:core:docEnv";
		silver.definition.core.NRoot.decorators.add(silver.extension.doc.core.DdocEnv.singleton);
		silver.definition.core.NAGDcls.occurs_inh[silver.extension.doc.core.Init.silver_extension_doc_core_docEnv__ON__silver_definition_core_AGDcls] = "silver:extension:doc:core:docEnv";
		silver.definition.core.NAGDcls.decorators.add(silver.extension.doc.core.DdocEnv.singleton);
		silver.definition.core.NAGDcl.occurs_inh[silver.extension.doc.core.Init.silver_extension_doc_core_docEnv__ON__silver_definition_core_AGDcl] = "silver:extension:doc:core:docEnv";
		silver.definition.core.NAGDcl.decorators.add(silver.extension.doc.core.DdocEnv.singleton);
		silver.extension.doc.core.NDocDclInfo.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_id__ON__silver_extension_doc_core_DocDclInfo] = "silver:extension:doc:core:id";
		silver.extension.doc.core.NDocDclInfo.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_file__ON__silver_extension_doc_core_DocDclInfo] = "silver:extension:doc:core:file";
		silver.extension.doc.core.NDocDclInfo.occurs_syn[silver.extension.doc.core.Init.silver_extension_doc_core_path__ON__silver_extension_doc_core_DocDclInfo] = "silver:extension:doc:core:path";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION interfaceRootSpec top ::= _ _ 
		// top.genFiles := []
		if(silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec] == null)
			silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec] = new silver.extension.doc.core.CAgenFiles(silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec);
		((common.CollectionAttribute)silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//ASPECT PRODUCTION errorRootSpec top ::= _ _ _ _ 
		// top.genFiles := []
		if(silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec] == null)
			silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec] = new silver.extension.doc.core.CAgenFiles(silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec);
		((common.CollectionAttribute)silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//ASPECT PRODUCTION grammarRootSpec top ::= g::Grammar _ _ _ 
		// top.genFiles := if g.docsNoDoc then [] else if "true" == g.docsSplit then toSplitFiles(g.docs, [], g.docsHeader) else [ toSingleFile(g.docs, g.docsHeader) ]
		if(silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec] == null)
			silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec] = new silver.extension.doc.core.CAgenFiles(silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec);
		((common.CollectionAttribute)silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_Grammar)) ? ((common.ConsCell)core.Pnil.invoke()) : ((new common.StringCatter("true")).equals(((common.StringCatter)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_Grammar))) ? ((common.ConsCell)silver.extension.doc.core.PtoSplitFiles.invoke(context.childDecoratedSynthesizedLazy(silver.driver.util.PgrammarRootSpec.i_g, silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, context.childDecoratedSynthesizedLazy(silver.driver.util.PgrammarRootSpec.i_g, silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_Grammar))) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)silver.extension.doc.core.PtoSingleFile.invoke(context.childDecoratedSynthesizedLazy(silver.driver.util.PgrammarRootSpec.i_g, silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar), context.childDecoratedSynthesizedLazy(silver.driver.util.PgrammarRootSpec.i_g, silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_Grammar))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })))); } });
		// g.docEnv = treeConvert(g.docDcls, treeNew(compareString))
		silver.driver.util.PgrammarRootSpec.childInheritedAttributes[silver.driver.util.PgrammarRootSpec.i_g][silver.extension.doc.core.Init.silver_extension_doc_core_docEnv__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeConvert.invoke(context.childDecoratedSynthesizedLazy(silver.driver.util.PgrammarRootSpec.i_g, silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeNew.invoke(core.PcompareString.factory)); } })); } };
		//FUNCTION toSplitFiles [Pair<String String>] ::= comments::[CommentItem] sortedComments::[Pair<String String>] header::String 
		//FUNCTION placeComment [Pair<String String>] ::= comment::CommentItem sortedComments::[Pair<String String>] header::String 
		// markdown = toMarkdown(comment)
		silver.extension.doc.core.PplaceComment.localAttributes[silver.extension.doc.core.Init.markdown__ON__silver_extension_doc_core_placeComment] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.doc.core.PtoMarkdown.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PplaceComment.i_comment)))); } };
		//FUNCTION makeIndexFile String ::= sortedComments::[Pair<String String>] header::String 
		//FUNCTION toSingleFile Pair<String String> ::= comments::[CommentItem] header::String 
		// commentMarkdown = toSingleMarkdown(comments)
		silver.extension.doc.core.PtoSingleFile.localAttributes[silver.extension.doc.core.Init.commentMarkdown__ON__silver_extension_doc_core_toSingleFile] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.doc.core.PtoSingleMarkdown.invoke(context.childAsIsLazy(silver.extension.doc.core.PtoSingleFile.i_comments))); } };
		//FUNCTION toSingleMarkdown String ::= comments::[CommentItem] 
		//FUNCTION toMarkdown String ::= c::CommentItem 
		//FUNCTION toMarkdownExtension String ::= filename::String 
		//ASPECT PRODUCTION aspectProductionDcl top ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
		// top.docs := [ bodilessDclCommentItem("aspect production", id.name, ns.pp, id.location.filename) ]
		if(silver.definition.core.PaspectProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PaspectProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PaspectProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PbodilessDclCommentItem((new common.StringCatter("aspect production")), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectProductionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectProductionDcl.i_ns, silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectProductionSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PaspectProductionDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver.extension.doc.core.PdocAspectProductionDcl.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnoDocAspectProductionDcl.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION aspectFunctionDcl top ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
		// top.docs := [ bodilessDclCommentItem("aspect function", id.name, ns.pp, id.location.filename) ]
		if(silver.definition.core.PaspectFunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PaspectFunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PaspectFunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PbodilessDclCommentItem((new common.StringCatter("aspect function")), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectFunctionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectFunctionDcl.i_ns, silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectFunctionSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver.extension.doc.core.PdocAspectFunctionDcl.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnoDocAspectFunctionDcl.initProductionAttributeDefinitions();
		silver.extension.doc.core.Pconfig.initProductionAttributeDefinitions();
		silver.extension.doc.core.PconsConfigs.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnilConfigs.initProductionAttributeDefinitions();
		silver.extension.doc.core.PheaderConfig.initProductionAttributeDefinitions();
		silver.extension.doc.core.PsplitFilesConfig.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnoDocConfig.initProductionAttributeDefinitions();
		//FUNCTION cleanDocValue String ::= s::String 
		silver.extension.doc.core.PdclComment.initProductionAttributeDefinitions();
		silver.extension.doc.core.PconsCommentComps.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnilCommentComps.initProductionAttributeDefinitions();
		silver.extension.doc.core.PcomponentLink.initProductionAttributeDefinitions();
		silver.extension.doc.core.PcomponentText.initProductionAttributeDefinitions();
		silver.extension.doc.core.PcomponentWhiteSpace.initProductionAttributeDefinitions();
		silver.extension.doc.core.PdclCommentItem.initProductionAttributeDefinitions();
		silver.extension.doc.core.PbodilessDclCommentItem.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION productionDcl top ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody 
		// top.docs := [ bodilessDclCommentItem("abstract production", id.name, ns.pp, id.location.filename) ]
		if(silver.definition.core.PproductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PproductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PproductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PbodilessDclCommentItem((new common.StringCatter("abstract production")), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionDcl.i_ns, silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PproductionDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// top.docDcls := [ pair(top.grammarName ++ ":" ++ id.name, productionDocDclInfoP(id.name, id.location.filename, top.grammarName ++ ":" ++ id.name)) ]
		if(silver.definition.core.PproductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PproductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PproductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PproductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NDocDclInfo)new silver.extension.doc.core.PproductionDocDclInfoP(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PproductionDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PproductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver.extension.doc.core.PdocProductionDecl.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnoDocProductionDecl.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION concreteProductionDcl top ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody 
		// top.docs := [ bodilessDclCommentItem("concrete production", id.name, ns.pp, id.location.filename) ]
		if(silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PbodilessDclCommentItem((new common.StringCatter("concrete production")), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PconcreteProductionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PconcreteProductionDcl.i_ns, silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// top.docDcls := [ pair(top.grammarName ++ ":" ++ id.name, productionDocDclInfoP(id.name, id.location.filename, top.grammarName ++ ":" ++ id.name)) ]
		if(silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] == null)
			silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PconcreteProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NDocDclInfo)new silver.extension.doc.core.PproductionDocDclInfoP(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PconcreteProductionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PconcreteProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver.extension.doc.core.PdocConcreteProductionDcl.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnoDocConcreteProductionDcl.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION nonterminalDcl top ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeExprs ';' 
		// top.docs := [ bodilessDclCommentItem("nonterminal", id.name ++ tl.pp, "", id.location.filename) ]
		if(silver.definition.core.PnonterminalDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PnonterminalDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PnonterminalDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PbodilessDclCommentItem((new common.StringCatter("nonterminal")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PnonterminalDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PnonterminalDcl.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } }, (new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PnonterminalDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver.extension.doc.core.PdocNonterminalDcl.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnoDocNonterminalDcl.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION nonterminalWithDcl top ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeExprs 'with' attrs::QNames ';' 
		// top.docs := [ bodilessDclCommentItem("nonterminal", id.name ++ tl.pp, "", id.location.filename) ]
		if(silver.extension.convenience.PnonterminalWithDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.extension.convenience.PnonterminalWithDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.convenience.PnonterminalWithDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PbodilessDclCommentItem((new common.StringCatter("nonterminal")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PnonterminalWithDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PnonterminalWithDcl.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } }, (new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.extension.convenience.PnonterminalWithDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver.extension.doc.core.PdocNonterminalWithDcl.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnoDocNonterminalWithDcl.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION functionDcl top ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
		// top.docs := [ bodilessDclCommentItem("function", id.name, ns.pp, id.location.filename) ]
		if(silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PbodilessDclCommentItem((new common.StringCatter("function")), context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionDcl.i_ns, silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_FunctionSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PfunctionDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// top.docDcls := [ pair(top.grammarName ++ ":" ++ id.name, functionDocDclInfoP(id.name, id.location.filename, nameToPath(top.grammarName ++ ":" ++ id.name))) ]
		if(silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PfunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NDocDclInfo)new silver.extension.doc.core.PfunctionDocDclInfoP(context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PfunctionDcl.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.doc.core.PnameToPath.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PfunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } })); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver.extension.doc.core.PdocFunctionDcl.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnoDocFunctionDcl.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION root top ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls 
		// top.docs := ags.docs
		if(silver.definition.core.Proot.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Root] == null)
			silver.definition.core.Proot.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Root] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Root);
		((common.CollectionAttribute)silver.definition.core.Proot.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Root]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.Proot.i_ags).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls)); } });
		// top.docsHeader = ags.docsHeader
		silver.definition.core.Proot.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.Proot.i_ags).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcls)); } };
		// top.docsSplit = ags.docsSplit
		silver.definition.core.Proot.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.Proot.i_ags).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcls)); } };
		// top.docsNoDoc = false
		silver.definition.core.Proot.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.docDcls := ags.docDcls
		if(silver.definition.core.Proot.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Root] == null)
			silver.definition.core.Proot.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Root] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Root);
		((common.CollectionAttribute)silver.definition.core.Proot.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Root]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.Proot.i_ags).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls)); } });
		//ASPECT PRODUCTION nilAGDcls top ::= 
		// top.docs := []
		if(silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls] == null)
			silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.docsHeader = ""
		silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.docsSplit = ""
		silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.docsNoDoc = false
		silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.docDcls := []
		if(silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls] == null)
			silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//ASPECT PRODUCTION consAGDcls top ::= h::AGDcl t::AGDcls 
		// top.docs := h.docs ++ t.docs
		if(silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls] == null)
			silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_h, silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_t, silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls))); } });
		// top.docsHeader = if "" == h.docsHeader then t.docsHeader else h.docsHeader
		silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((new common.StringCatter("")).equals(((common.StringCatter)context.childDecorated(silver.definition.core.PconsAGDcls.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcl))) ? ((common.StringCatter)context.childDecorated(silver.definition.core.PconsAGDcls.i_t).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcls)) : ((common.StringCatter)context.childDecorated(silver.definition.core.PconsAGDcls.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcl))); } };
		// top.docsSplit = if "" == h.docsSplit then t.docsSplit else h.docsSplit
		silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((new common.StringCatter("")).equals(((common.StringCatter)context.childDecorated(silver.definition.core.PconsAGDcls.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcl))) ? ((common.StringCatter)context.childDecorated(silver.definition.core.PconsAGDcls.i_t).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcls)) : ((common.StringCatter)context.childDecorated(silver.definition.core.PconsAGDcls.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcl))); } };
		// top.docsNoDoc = h.docsNoDoc || t.docsNoDoc
		silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.core.PconsAGDcls.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcl)) || ((Boolean)context.childDecorated(silver.definition.core.PconsAGDcls.i_t).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcls))); } };
		// top.docDcls := h.docDcls ++ t.docDcls
		if(silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls] == null)
			silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls);
		((common.CollectionAttribute)silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_h, silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_t, silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls))); } });
		//ASPECT DEFAULT PRODUCTION for AGDcl
		// top.docs := []
		if(silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.docsHeader = ""
		silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.docsSplit = ""
		silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.docsNoDoc = false
		silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.docDcls := []
		if(silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//ASPECT PRODUCTION appendAGDcl top ::= h::AGDcl t::AGDcl 
		// top.docs := h.docs ++ t.docs
		if(silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_h, silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_t, silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl))); } });
		// top.docsHeader = if "" == h.docsHeader then t.docsHeader else h.docsHeader
		silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((new common.StringCatter("")).equals(((common.StringCatter)context.childDecorated(silver.definition.core.PappendAGDcl.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcl))) ? ((common.StringCatter)context.childDecorated(silver.definition.core.PappendAGDcl.i_t).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcl)) : ((common.StringCatter)context.childDecorated(silver.definition.core.PappendAGDcl.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcl))); } };
		// top.docsSplit = if "" == h.docsSplit then t.docsSplit else h.docsSplit
		silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((new common.StringCatter("")).equals(((common.StringCatter)context.childDecorated(silver.definition.core.PappendAGDcl.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcl))) ? ((common.StringCatter)context.childDecorated(silver.definition.core.PappendAGDcl.i_t).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcl)) : ((common.StringCatter)context.childDecorated(silver.definition.core.PappendAGDcl.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcl))); } };
		// top.docsNoDoc = h.docsNoDoc || t.docsNoDoc
		silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.core.PappendAGDcl.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcl)) || ((Boolean)context.childDecorated(silver.definition.core.PappendAGDcl.i_t).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcl))); } };
		// top.docDcls := h.docDcls ++ t.docDcls
		if(silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_h, silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_t, silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl))); } });
		//ASPECT PRODUCTION nilGrammar top ::= 
		// top.docs := []
		if(silver.definition.core.PnilGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar] == null)
			silver.definition.core.PnilGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar);
		((common.CollectionAttribute)silver.definition.core.PnilGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.docsHeader = ""
		silver.definition.core.PnilGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.docsSplit = ""
		silver.definition.core.PnilGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.docsNoDoc = false
		silver.definition.core.PnilGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.docDcls := []
		if(silver.definition.core.PnilGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar] == null)
			silver.definition.core.PnilGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar);
		((common.CollectionAttribute)silver.definition.core.PnilGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//ASPECT PRODUCTION consGrammar top ::= c1::Root c2::Grammar 
		// top.docs := c1.docs ++ c2.docs
		if(silver.definition.core.PconsGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar] == null)
			silver.definition.core.PconsGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar);
		((common.CollectionAttribute)silver.definition.core.PconsGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_Grammar))); } });
		// top.docsHeader = if "" == c1.docsHeader then c2.docsHeader else c1.docsHeader
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((new common.StringCatter("")).equals(((common.StringCatter)context.childDecorated(silver.definition.core.PconsGrammar.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_Root))) ? ((common.StringCatter)context.childDecorated(silver.definition.core.PconsGrammar.i_t).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_Grammar)) : ((common.StringCatter)context.childDecorated(silver.definition.core.PconsGrammar.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsHeader__ON__silver_definition_core_Root))); } };
		// top.docsSplit = if "" == c1.docsSplit then c2.docsSplit else c1.docsSplit
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((new common.StringCatter("")).equals(((common.StringCatter)context.childDecorated(silver.definition.core.PconsGrammar.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_Root))) ? ((common.StringCatter)context.childDecorated(silver.definition.core.PconsGrammar.i_t).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_Grammar)) : ((common.StringCatter)context.childDecorated(silver.definition.core.PconsGrammar.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsSplit__ON__silver_definition_core_Root))); } };
		// top.docsNoDoc = c1.docsNoDoc || c2.docsNoDoc
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.core.PconsGrammar.i_h).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_Root)) || ((Boolean)context.childDecorated(silver.definition.core.PconsGrammar.i_t).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_Grammar))); } };
		// top.docDcls := c1.docDcls ++ c2.docDcls
		if(silver.definition.core.PconsGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar] == null)
			silver.definition.core.PconsGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar] = new silver.extension.doc.core.CAdocDcls(silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar);
		((common.CollectionAttribute)silver.definition.core.PconsGrammar.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.extension.doc.core.Init.silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar))); } });
		//ASPECT PRODUCTION errorReference top ::= msg::[Message] q::Decorated QName 
		//ASPECT PRODUCTION childReference top ::= q::Decorated QName 
		//ASPECT PRODUCTION lhsReference top ::= q::Decorated QName 
		//ASPECT PRODUCTION localReference top ::= q::Decorated QName 
		//ASPECT PRODUCTION productionReference top ::= q::Decorated QName 
		//ASPECT PRODUCTION functionReference top ::= q::Decorated QName 
		//ASPECT PRODUCTION forwardReference top ::= q::Decorated QName 
		//ASPECT PRODUCTION globalValueReference top ::= q::Decorated QName 
		//ASPECT PRODUCTION errorApplication top ::= e::Decorated Expr es::AppExprs annos::AnnoAppExprs 
		//ASPECT PRODUCTION functionInvocation top ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs 
		//ASPECT PRODUCTION partialApplication top ::= e::Decorated Expr es::Decorated AppExprs annos::Decorated AnnoAppExprs 
		//ASPECT PRODUCTION attributeSection top ::= '(' '.' q::QName ')' 
		//ASPECT PRODUCTION errorAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		//ASPECT PRODUCTION errorDecoratedAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		//ASPECT PRODUCTION forwardAccess top ::= e::Expr '.' 'forward' 
		//ASPECT PRODUCTION synDecoratedAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		//ASPECT PRODUCTION inhDecoratedAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		//ASPECT PRODUCTION terminalAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		//ASPECT PRODUCTION annoAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		//ASPECT PRODUCTION decorateExprWith top ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}' 
		//ASPECT PRODUCTION trueConst top ::= 'true' 
		//ASPECT PRODUCTION falseConst top ::= 'false' 
		//ASPECT PRODUCTION and top ::= e1::Expr '&&' e2::Expr 
		//ASPECT PRODUCTION or top ::= e1::Expr '||' e2::Expr 
		//ASPECT PRODUCTION not top ::= '!' e::Expr 
		//ASPECT PRODUCTION gt top ::= e1::Expr '>' e2::Expr 
		//ASPECT PRODUCTION lt top ::= e1::Expr '<' e2::Expr 
		//ASPECT PRODUCTION gteq top ::= e1::Expr '>=' e2::Expr 
		//ASPECT PRODUCTION lteq top ::= e1::Expr '<=' e2::Expr 
		//ASPECT PRODUCTION eqeq top ::= e1::Expr '==' e2::Expr 
		//ASPECT PRODUCTION neq top ::= e1::Expr '!=' e2::Expr 
		//ASPECT PRODUCTION ifThenElse top ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr 
		//ASPECT PRODUCTION intConst top ::= i::Int_t 
		//ASPECT PRODUCTION floatConst top ::= f::Float_t 
		//ASPECT PRODUCTION plus top ::= e1::Expr '+' e2::Expr 
		//ASPECT PRODUCTION minus top ::= e1::Expr '-' e2::Expr 
		//ASPECT PRODUCTION multiply top ::= e1::Expr '*' e2::Expr 
		//ASPECT PRODUCTION divide top ::= e1::Expr '/' e2::Expr 
		//ASPECT PRODUCTION modulus top ::= e1::Expr '%' e2::Expr 
		//ASPECT PRODUCTION neg top ::= '-' e::Expr 
		//ASPECT PRODUCTION stringConst top ::= s::String_t 
		//ASPECT PRODUCTION errorPlusPlus top ::= e1::Decorated Expr e2::Decorated Expr 
		//ASPECT PRODUCTION stringPlusPlus top ::= e1::Decorated Expr e2::Decorated Expr 
		//ASPECT PRODUCTION exprsEmpty top ::= 
		//ASPECT PRODUCTION exprsSingle top ::= e::Expr 
		//ASPECT PRODUCTION exprsCons top ::= e1::Expr ',' e2::Exprs 
		//ASPECT PRODUCTION exprRef top ::= e::Decorated Expr 
		silver.extension.doc.core.PfunctionDocDclInfo.initProductionAttributeDefinitions();
		silver.extension.doc.core.PfunctionDocDclInfoP.initProductionAttributeDefinitions();
		silver.extension.doc.core.PproductionDocDclInfo.initProductionAttributeDefinitions();
		silver.extension.doc.core.PproductionDocDclInfoP.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION globalValueDclConcrete top ::= 'global' id::Name '::' t::TypeExpr '=' e::Expr ';' 
		// top.docs := [ bodilessDclCommentItem("global", id.name, t.pp, id.location.filename) ]
		if(silver.definition.core.PglobalValueDclConcrete.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PglobalValueDclConcrete.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PglobalValueDclConcrete.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PbodilessDclCommentItem((new common.StringCatter("global")), context.childDecoratedSynthesizedLazy(silver.definition.core.PglobalValueDclConcrete.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.childDecoratedSynthesizedLazy(silver.definition.core.PglobalValueDclConcrete.i_t, silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PglobalValueDclConcrete.i_id).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver.extension.doc.core.PdocGlobalValueDclConcrete.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnoDocGlobalValueDclConcrete.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION annotationDcl top ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';' 
		// top.docs := [ bodilessDclCommentItem("annotation", a.name ++ tl.pp, te.pp, a.location.filename) ]
		if(silver.definition.core.PannotationDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PannotationDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PannotationDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PbodilessDclCommentItem((new common.StringCatter("annotation")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PannotationDcl.i_a).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PannotationDcl.i_tl).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } }, context.childDecoratedSynthesizedLazy(silver.definition.core.PannotationDcl.i_te, silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PannotationDcl.i_a).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver.extension.doc.core.PdocAnnotationDcl.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnoDocAnnotationDcl.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION attributionDcl top ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';' 
		// top.docs := [ bodilessDclCommentItem("attribute", at.name, "", at.location.filename) ]
		if(silver.definition.core.PattributionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PattributionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PattributionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)new silver.extension.doc.core.PbodilessDclCommentItem((new common.StringCatter("attribute")), context.childDecoratedSynthesizedLazy(silver.definition.core.PattributionDcl.i_at, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), (new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PattributionDcl.i_at).undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_filename__ON__core_Location)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver.extension.doc.core.PdocAttributionDcl.initProductionAttributeDefinitions();
		silver.extension.doc.core.PnoDocAttributionDcl.initProductionAttributeDefinitions();
		//FUNCTION nameToPath String ::= name::String 
		//FUNCTION nameToPathHelp String ::= s::[String] 
	}

	public static int count_local__ON__silver_extension_doc_core_toSplitFiles = 0;
	public static int count_local__ON__silver_extension_doc_core_placeComment = 0;
	public static int count_local__ON__silver_extension_doc_core_makeIndexFile = 0;
	public static int count_local__ON__silver_extension_doc_core_toSingleFile = 0;
	public static int count_local__ON__silver_extension_doc_core_toSingleMarkdown = 0;
	public static int count_local__ON__silver_extension_doc_core_toMarkdown = 0;
	public static int count_local__ON__silver_extension_doc_core_toMarkdownExtension = 0;
	public static int count_local__ON__silver_extension_doc_core_docAspectProductionDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_noDocAspectProductionDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_docAspectFunctionDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_noDocAspectFunctionDcl = 0;
	public static int count_inh__ON__DocConfigs = 0;
	public static int count_syn__ON__DocConfigs = 0;
	public static int count_inh__ON__DocConfig = 0;
	public static int count_syn__ON__DocConfig = 0;
	public static int count_local__ON__silver_extension_doc_core_config = 0;
	public static int count_local__ON__silver_extension_doc_core_consConfigs = 0;
	public static int count_local__ON__silver_extension_doc_core_nilConfigs = 0;
	public static int count_local__ON__silver_extension_doc_core_headerConfig = 0;
	public static int count_local__ON__silver_extension_doc_core_splitFilesConfig = 0;
	public static int count_local__ON__silver_extension_doc_core_noDocConfig = 0;
	public static int count_local__ON__silver_extension_doc_core_cleanDocValue = 0;
	public static int count_inh__ON__DclComment = 0;
	public static int count_syn__ON__DclComment = 0;
	public static int count_inh__ON__DclCommentComponent = 0;
	public static int count_syn__ON__DclCommentComponent = 0;
	public static int count_inh__ON__DclCommentComponents = 0;
	public static int count_syn__ON__DclCommentComponents = 0;
	public static int count_local__ON__silver_extension_doc_core_dclComment = 0;
	public static int count_local__ON__silver_extension_doc_core_consCommentComps = 0;
	public static int count_local__ON__silver_extension_doc_core_nilCommentComps = 0;
	public static int count_local__ON__silver_extension_doc_core_componentLink = 0;
	public static int count_local__ON__silver_extension_doc_core_componentText = 0;
	public static int count_local__ON__silver_extension_doc_core_componentWhiteSpace = 0;
	public static int count_local__ON__silver_extension_doc_core_dclCommentItem = 0;
	public static int count_local__ON__silver_extension_doc_core_bodilessDclCommentItem = 0;
	public static int count_inh__ON__CommentItem = 0;
	public static int count_syn__ON__CommentItem = 0;
	public static int count_local__ON__silver_extension_doc_core_docProductionDecl = 0;
	public static int count_local__ON__silver_extension_doc_core_noDocProductionDecl = 0;
	public static int count_local__ON__silver_extension_doc_core_docConcreteProductionDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_noDocConcreteProductionDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_docNonterminalDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_noDocNonterminalDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_docNonterminalWithDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_noDocNonterminalWithDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_docFunctionDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_noDocFunctionDcl = 0;
	public static int count_inh__ON__DocDclInfo = 0;
	public static int count_syn__ON__DocDclInfo = 0;
	public static int count_local__ON__silver_extension_doc_core_functionDocDclInfo = 0;
	public static int count_local__ON__silver_extension_doc_core_functionDocDclInfoP = 0;
	public static int count_local__ON__silver_extension_doc_core_productionDocDclInfo = 0;
	public static int count_local__ON__silver_extension_doc_core_productionDocDclInfoP = 0;
	public static int count_local__ON__silver_extension_doc_core_docGlobalValueDclConcrete = 0;
	public static int count_local__ON__silver_extension_doc_core_noDocGlobalValueDclConcrete = 0;
	public static int count_local__ON__silver_extension_doc_core_docAnnotationDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_noDocAnnotationDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_docAttributionDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_noDocAttributionDcl = 0;
	public static int count_local__ON__silver_extension_doc_core_nameToPath = 0;
	public static int count_local__ON__silver_extension_doc_core_nameToPathHelp = 0;
public static final int silver_extension_doc_core_genFiles__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int markdown__ON__silver_extension_doc_core_placeComment = silver.extension.doc.core.Init.count_local__ON__silver_extension_doc_core_placeComment++;
public static final int commentMarkdown__ON__silver_extension_doc_core_toSingleFile = silver.extension.doc.core.Init.count_local__ON__silver_extension_doc_core_toSingleFile++;
public static final int silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfigs = silver.extension.doc.core.Init.count_syn__ON__DocConfigs++;
public static final int silver_extension_doc_core_splitFiles__ON__silver_extension_doc_core_DocConfigs = silver.extension.doc.core.Init.count_syn__ON__DocConfigs++;
public static final int silver_extension_doc_core_noDoc__ON__silver_extension_doc_core_DocConfigs = silver.extension.doc.core.Init.count_syn__ON__DocConfigs++;
public static final int silver_extension_doc_core_warnings__ON__silver_extension_doc_core_DocConfigs = silver.extension.doc.core.Init.count_syn__ON__DocConfigs++;
public static final int silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfig = silver.extension.doc.core.Init.count_syn__ON__DocConfig++;
public static final int silver_extension_doc_core_splitFiles__ON__silver_extension_doc_core_DocConfig = silver.extension.doc.core.Init.count_syn__ON__DocConfig++;
public static final int silver_extension_doc_core_noDoc__ON__silver_extension_doc_core_DocConfig = silver.extension.doc.core.Init.count_syn__ON__DocConfig++;
public static final int headerWarnings__ON__silver_extension_doc_core_consConfigs = silver.extension.doc.core.Init.count_local__ON__silver_extension_doc_core_consConfigs++;
public static final int splitFilesWarnings__ON__silver_extension_doc_core_consConfigs = silver.extension.doc.core.Init.count_local__ON__silver_extension_doc_core_consConfigs++;
public static final int silver_extension_doc_core_body__ON__silver_extension_doc_core_DclComment = silver.extension.doc.core.Init.count_syn__ON__DclComment++;
public static final int silver_definition_env_env__ON__silver_extension_doc_core_DclComment = silver.extension.doc.core.Init.count_inh__ON__DclComment++;
public static final int silver_extension_doc_core_body__ON__silver_extension_doc_core_DclCommentComponent = silver.extension.doc.core.Init.count_syn__ON__DclCommentComponent++;
public static final int silver_definition_env_env__ON__silver_extension_doc_core_DclCommentComponent = silver.extension.doc.core.Init.count_inh__ON__DclCommentComponent++;
public static final int silver_extension_doc_core_body__ON__silver_extension_doc_core_DclCommentComponents = silver.extension.doc.core.Init.count_syn__ON__DclCommentComponents++;
public static final int silver_definition_env_env__ON__silver_extension_doc_core_DclCommentComponents = silver.extension.doc.core.Init.count_inh__ON__DclCommentComponents++;
public static final int silver_extension_doc_core_docEnv__ON__silver_extension_doc_core_DclComment = silver.extension.doc.core.Init.count_inh__ON__DclComment++;
public static final int silver_extension_doc_core_docEnv__ON__silver_extension_doc_core_DclCommentComponent = silver.extension.doc.core.Init.count_inh__ON__DclCommentComponent++;
public static final int silver_extension_doc_core_docEnv__ON__silver_extension_doc_core_DclCommentComponents = silver.extension.doc.core.Init.count_inh__ON__DclCommentComponents++;
public static final int dclInfo__ON__silver_extension_doc_core_componentLink = silver.extension.doc.core.Init.count_local__ON__silver_extension_doc_core_componentLink++;
public static final int silver_extension_doc_core_body__ON__silver_extension_doc_core_CommentItem = silver.extension.doc.core.Init.count_syn__ON__CommentItem++;
public static final int silver_extension_doc_core_file__ON__silver_extension_doc_core_CommentItem = silver.extension.doc.core.Init.count_syn__ON__CommentItem++;
public static final int silver_extension_doc_core_docs__ON__silver_definition_core_Grammar = silver.definition.core.Init.count_syn__ON__Grammar++;
public static final int silver_extension_doc_core_docs__ON__silver_definition_core_Root = silver.definition.core.Init.count_syn__ON__Root++;
public static final int silver_extension_doc_core_docs__ON__silver_definition_core_AGDcls = silver.definition.core.Init.count_syn__ON__AGDcls++;
public static final int silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_syn__ON__AGDcl++;
public static final int silver_extension_doc_core_docsHeader__ON__silver_definition_core_Grammar = silver.definition.core.Init.count_syn__ON__Grammar++;
public static final int silver_extension_doc_core_docsHeader__ON__silver_definition_core_Root = silver.definition.core.Init.count_syn__ON__Root++;
public static final int silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcls = silver.definition.core.Init.count_syn__ON__AGDcls++;
public static final int silver_extension_doc_core_docsHeader__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_syn__ON__AGDcl++;
public static final int silver_extension_doc_core_docsSplit__ON__silver_definition_core_Grammar = silver.definition.core.Init.count_syn__ON__Grammar++;
public static final int silver_extension_doc_core_docsSplit__ON__silver_definition_core_Root = silver.definition.core.Init.count_syn__ON__Root++;
public static final int silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcls = silver.definition.core.Init.count_syn__ON__AGDcls++;
public static final int silver_extension_doc_core_docsSplit__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_syn__ON__AGDcl++;
public static final int silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_Grammar = silver.definition.core.Init.count_syn__ON__Grammar++;
public static final int silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_Root = silver.definition.core.Init.count_syn__ON__Root++;
public static final int silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcls = silver.definition.core.Init.count_syn__ON__AGDcls++;
public static final int silver_extension_doc_core_docsNoDoc__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_syn__ON__AGDcl++;
public static final int silver_extension_doc_core_docDcls__ON__silver_definition_core_Grammar = silver.definition.core.Init.count_syn__ON__Grammar++;
public static final int silver_extension_doc_core_docDcls__ON__silver_definition_core_Root = silver.definition.core.Init.count_syn__ON__Root++;
public static final int silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcls = silver.definition.core.Init.count_syn__ON__AGDcls++;
public static final int silver_extension_doc_core_docDcls__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_syn__ON__AGDcl++;
public static final int silver_extension_doc_core_docEnv__ON__silver_definition_core_Grammar = silver.definition.core.Init.count_inh__ON__Grammar++;
public static final int silver_extension_doc_core_docEnv__ON__silver_definition_core_Root = silver.definition.core.Init.count_inh__ON__Root++;
public static final int silver_extension_doc_core_docEnv__ON__silver_definition_core_AGDcls = silver.definition.core.Init.count_inh__ON__AGDcls++;
public static final int silver_extension_doc_core_docEnv__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_inh__ON__AGDcl++;
public static final int silver_extension_doc_core_id__ON__silver_extension_doc_core_DocDclInfo = silver.extension.doc.core.Init.count_syn__ON__DocDclInfo++;
public static final int silver_extension_doc_core_file__ON__silver_extension_doc_core_DocDclInfo = silver.extension.doc.core.Init.count_syn__ON__DocDclInfo++;
public static final int silver_extension_doc_core_path__ON__silver_extension_doc_core_DocDclInfo = silver.extension.doc.core.Init.count_syn__ON__DocDclInfo++;
}
