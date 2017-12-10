package silver.definition.env;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.definition.regex.Init.initAllStatics();
		silver.util.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();
		silver.definition.flow.driver.Init.initAllStatics();
		silver.util.cmdargs.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.util.raw.treemap.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.definition.regex.Init.init();
		silver.util.Init.init();
		silver.driver.util.Init.init();
		silver.definition.flow.driver.Init.init();
		silver.util.cmdargs.Init.init();
		silver.definition.type.Init.init();
		silver.util.raw.treemap.Init.init();
		silver.definition.env.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.definition.regex.Init.postInit();
		silver.util.Init.postInit();
		silver.driver.util.Init.postInit();
		silver.definition.flow.driver.Init.postInit();
		silver.util.cmdargs.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.util.raw.treemap.Init.postInit();
		silver.definition.env.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.env.NNamedSignature.decorators, PnamedSignature.class);
		common.Decorator.applyDecorators(silver.definition.env.NNamedSignature.decorators, PbogusNamedSignature.class);
		common.Decorator.applyDecorators(silver.definition.env.NNamedSignatureElement.decorators, PnamedSignatureElement.class);
		common.Decorator.applyDecorators(silver.definition.env.NNamedSignatureElement.decorators, PbogusNamedSignatureElement.class);
		common.Decorator.applyDecorators(silver.definition.env.NEnvScope.decorators, Pi_envScope_dummy_record.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PchildDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PlhsDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PlocalDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PforwardDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PprodDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PfunDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PglobalValueDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PntDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PtermDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PlexTyVarDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PsynDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PinhDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PannoDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PpaDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PoccursDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PannoInstanceDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NEnvItem.decorators, PrenamedEnvItem.class);
		common.Decorator.applyDecorators(silver.definition.env.NEnvItem.decorators, PfullNameEnvItem.class);
		common.Decorator.applyDecorators(silver.definition.env.NEnvItem.decorators, PonlyRenamedEnvItem.class);
		common.Decorator.applyDecorators(silver.definition.env.NEnv.decorators, Pi_emptyEnv.class);
		common.Decorator.applyDecorators(silver.definition.env.NEnv.decorators, Pi_appendEnv.class);
		common.Decorator.applyDecorators(silver.definition.env.NEnv.decorators, Pi_newScopeEnv.class);
		common.Decorator.applyDecorators(silver.definition.env.NDefs.decorators, PnilDefs.class);
		common.Decorator.applyDecorators(silver.definition.env.NDefs.decorators, PconsDefs.class);
		common.Decorator.applyDecorators(silver.definition.env.NDef.decorators, PtypeDef.class);
		common.Decorator.applyDecorators(silver.definition.env.NDef.decorators, PvalueDef.class);
		common.Decorator.applyDecorators(silver.definition.env.NDef.decorators, PattrDef.class);
		common.Decorator.applyDecorators(silver.definition.env.NDef.decorators, PprodDclDef.class);
		common.Decorator.applyDecorators(silver.definition.env.NDef.decorators, PpaDef.class);
		common.Decorator.applyDecorators(silver.definition.env.NDef.decorators, PoDef.class);
	}

	private static void setupInheritedAttributes(){
		silver.definition.env.NNamedSignature.occurs_syn[silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature] = "silver:definition:env:inputElements";
		silver.definition.env.NNamedSignature.occurs_syn[silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature] = "silver:definition:env:outputElement";
		silver.definition.env.NNamedSignature.occurs_syn[silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature] = "silver:definition:env:fullName";
		silver.definition.env.NNamedSignature.occurs_syn[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_NamedSignature] = "silver:definition:env:unparse";
		silver.definition.env.NNamedSignature.occurs_inh[silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignature] = "silver:definition:type:boundVariables";
		silver.definition.env.NNamedSignature.decorators.add(silver.definition.type.DboundVariables.singleton);
		silver.definition.env.NNamedSignature.occurs_syn[silver.definition.env.Init.silver_definition_env_inputNames__ON__silver_definition_env_NamedSignature] = "silver:definition:env:inputNames";
		silver.definition.env.NNamedSignature.occurs_syn[silver.definition.env.Init.silver_definition_type_inputTypes__ON__silver_definition_env_NamedSignature] = "silver:definition:type:inputTypes";
		silver.definition.env.NNamedSignature.occurs_syn[silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignature] = "silver:definition:env:typerep";
		silver.definition.env.NNamedSignature.occurs_syn[silver.definition.env.Init.silver_definition_env_namedInputElements__ON__silver_definition_env_NamedSignature] = "silver:definition:env:namedInputElements";
		silver.definition.env.NNamedSignatureElement.occurs_syn[silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement] = "silver:definition:env:typerep";
		silver.definition.env.NNamedSignatureElement.occurs_syn[silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement] = "silver:definition:env:elementName";
		silver.definition.env.NNamedSignatureElement.occurs_syn[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_NamedSignatureElement] = "silver:definition:env:unparse";
		silver.definition.env.NNamedSignatureElement.occurs_inh[silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignatureElement] = "silver:definition:type:boundVariables";
		silver.definition.env.NNamedSignatureElement.decorators.add(silver.definition.type.DboundVariables.singleton);
		silver.definition.env.NNamedSignatureElement.occurs_syn[silver.definition.env.Init.silver_definition_env_toNamedArgType__ON__silver_definition_env_NamedSignatureElement] = "silver:definition:env:toNamedArgType";
		silver.definition.env.PnamedSignatureElement.occurs_local[silver.definition.env.Init.annoShortName__ON__silver_definition_env_namedSignatureElement] = "silver:definition:env:namedSignatureElement:local:annoShortName";
		//	local attribute fst::Type;
		silver.definition.env.PmapUnparseTypes.localInheritedAttributes[silver.definition.env.Init.fst__ON__silver_definition_env_mapUnparseTypes] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.definition.env.PmapUnparseTypes.occurs_local[silver.definition.env.Init.fst__ON__silver_definition_env_mapUnparseTypes] = "silver:definition:env:mapUnparseTypes:local:fst";
		//	local attribute h::NamedSignatureElement;
		silver.definition.env.PunparseSignatureElementsHelp.localInheritedAttributes[silver.definition.env.Init.h__ON__silver_definition_env_unparseSignatureElementsHelp] = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_inh_attrs];
		silver.definition.env.PunparseSignatureElementsHelp.occurs_local[silver.definition.env.Init.h__ON__silver_definition_env_unparseSignatureElementsHelp] = "silver:definition:env:unparseSignatureElementsHelp:local:h";
		core.NLocation.occurs_syn[silver.definition.env.Init.silver_definition_env_unparse__ON__core_Location] = "silver:definition:env:unparse";
		silver.definition.env.NEnvScope.occurs_inh[silver.definition.env.Init.silver_definition_env_envTrees__ON__silver_definition_env_EnvScope] = "silver:definition:env:envTrees";
		silver.definition.type.NType.occurs_syn[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = "silver:definition:env:unparse";
		silver.definition.type.NType.occurs_syn[silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type] = "silver:definition:env:typeName";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo] = "silver:definition:env:sourceGrammar";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_sourceLocation__ON__silver_definition_env_DclInfo] = "silver:definition:env:sourceLocation";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo] = "silver:definition:env:fullName";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_DclInfo] = "silver:definition:env:unparse";
		silver.definition.env.NDclInfo.occurs_inh[silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_DclInfo] = "silver:definition:type:boundVariables";
		silver.definition.env.NDclInfo.decorators.add(silver.definition.type.DboundVariables.singleton);
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo] = "silver:definition:env:typerep";
		silver.definition.env.NDclInfo.occurs_inh[silver.definition.env.Init.silver_definition_env_givenNonterminalType__ON__silver_definition_env_DclInfo] = "silver:definition:env:givenNonterminalType";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_namedSignature__ON__silver_definition_env_DclInfo] = "silver:definition:env:namedSignature";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo] = "silver:definition:env:attrOccurring";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_isAnnotation__ON__silver_definition_env_DclInfo] = "silver:definition:env:isAnnotation";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_isInherited__ON__silver_definition_env_DclInfo] = "silver:definition:env:isInherited";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_isSynthesized__ON__silver_definition_env_DclInfo] = "silver:definition:env:isSynthesized";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_prodDefs__ON__silver_definition_env_DclInfo] = "silver:definition:env:prodDefs";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_dclBoundVars__ON__silver_definition_env_DclInfo] = "silver:definition:env:dclBoundVars";
		silver.definition.env.NDclInfo.occurs_syn[silver.definition.env.Init.silver_definition_env_substitutedDclInfo__ON__silver_definition_env_DclInfo] = "silver:definition:env:substitutedDclInfo";
		silver.definition.env.NDclInfo.occurs_inh[silver.definition.env.Init.silver_definition_env_givenSubstitution__ON__silver_definition_env_DclInfo] = "silver:definition:env:givenSubstitution";
		silver.definition.env.PprodDcl.occurs_local[silver.definition.env.Init.boundvars__ON__silver_definition_env_prodDcl] = "silver:definition:env:prodDcl:local:boundvars";
		silver.definition.env.PfunDcl.occurs_local[silver.definition.env.Init.boundvars__ON__silver_definition_env_funDcl] = "silver:definition:env:funDcl:local:boundvars";
		silver.definition.env.PpaDcl.occurs_local[silver.definition.env.Init.boundvars__ON__silver_definition_env_paDcl] = "silver:definition:env:paDcl:local:boundvars";
		//	local attribute subst::Substitution;
		silver.definition.env.PoccursDcl.localInheritedAttributes[silver.definition.env.Init.subst__ON__silver_definition_env_occursDcl] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];
		silver.definition.env.PoccursDcl.occurs_local[silver.definition.env.Init.subst__ON__silver_definition_env_occursDcl] = "silver:definition:env:occursDcl:local:subst";
		//	local attribute subst::Substitution;
		silver.definition.env.PannoInstanceDcl.localInheritedAttributes[silver.definition.env.Init.subst__ON__silver_definition_env_annoInstanceDcl] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];
		silver.definition.env.PannoInstanceDcl.occurs_local[silver.definition.env.Init.subst__ON__silver_definition_env_annoInstanceDcl] = "silver:definition:env:annoInstanceDcl:local:subst";
		//	local attribute subst::Substitution;
		silver.definition.env.PdefsFromPADcls.localInheritedAttributes[silver.definition.env.Init.subst__ON__silver_definition_env_defsFromPADcls] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];
		silver.definition.env.PdefsFromPADcls.occurs_local[silver.definition.env.Init.subst__ON__silver_definition_env_defsFromPADcls] = "silver:definition:env:defsFromPADcls:local:subst";
		silver.definition.env.NEnvItem.occurs_syn[silver.definition.env.Init.silver_definition_env_itemName__ON__silver_definition_env_EnvItem] = "silver:definition:env:itemName";
		silver.definition.env.NEnvItem.occurs_syn[silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_EnvItem] = "silver:definition:env:dcl";
		silver.definition.env.NEnvItem.occurs_syn[silver.definition.env.Init.silver_definition_env_envContribs__ON__silver_definition_env_EnvItem] = "silver:definition:env:envContribs";
		//	local attribute result::Maybe<String>;
		silver.definition.env.PenvItemApplyRenaming.localInheritedAttributes[silver.definition.env.Init.result__ON__silver_definition_env_envItemApplyRenaming] = new common.Lazy[core.NMaybe.num_inh_attrs];
		silver.definition.env.PenvItemApplyRenaming.occurs_local[silver.definition.env.Init.result__ON__silver_definition_env_envItemApplyRenaming] = "silver:definition:env:envItemApplyRenaming:local:result";
		silver.definition.env.NEnv.occurs_syn[silver.definition.env.Init.silver_definition_env_typeTree__ON__silver_definition_env_Env] = "silver:definition:env:typeTree";
		silver.definition.env.NEnv.occurs_syn[silver.definition.env.Init.silver_definition_env_valueTree__ON__silver_definition_env_Env] = "silver:definition:env:valueTree";
		silver.definition.env.NEnv.occurs_syn[silver.definition.env.Init.silver_definition_env_attrTree__ON__silver_definition_env_Env] = "silver:definition:env:attrTree";
		silver.definition.env.NEnv.occurs_syn[silver.definition.env.Init.silver_definition_env_prodOccursTree__ON__silver_definition_env_Env] = "silver:definition:env:prodOccursTree";
		silver.definition.env.NEnv.occurs_syn[silver.definition.env.Init.silver_definition_env_occursTree__ON__silver_definition_env_Env] = "silver:definition:env:occursTree";
		silver.definition.env.NEnv.occurs_syn[silver.definition.env.Init.silver_definition_env_prodsForNtTree__ON__silver_definition_env_Env] = "silver:definition:env:prodsForNtTree";
		silver.definition.env.PsearchEnv.occurs_local[silver.definition.env.Init.found__ON__silver_definition_env_searchEnv] = "silver:definition:env:searchEnv:local:found";
		silver.definition.env.PannotationsForNonterminal.occurs_local[silver.definition.env.Init.annos__ON__silver_definition_env_annotationsForNonterminal] = "silver:definition:env:annotationsForNonterminal:local:annos";
		silver.definition.env.NDefs.occurs_syn[silver.definition.env.Init.silver_definition_env_typeList__ON__silver_definition_env_Defs] = "silver:definition:env:typeList";
		silver.definition.env.NDefs.occurs_syn[silver.definition.env.Init.silver_definition_env_valueList__ON__silver_definition_env_Defs] = "silver:definition:env:valueList";
		silver.definition.env.NDefs.occurs_syn[silver.definition.env.Init.silver_definition_env_attrList__ON__silver_definition_env_Defs] = "silver:definition:env:attrList";
		silver.definition.env.NDefs.occurs_syn[silver.definition.env.Init.silver_definition_env_prodOccursList__ON__silver_definition_env_Defs] = "silver:definition:env:prodOccursList";
		silver.definition.env.NDefs.occurs_syn[silver.definition.env.Init.silver_definition_env_occursList__ON__silver_definition_env_Defs] = "silver:definition:env:occursList";
		silver.definition.env.NDefs.occurs_syn[silver.definition.env.Init.silver_definition_env_prodDclList__ON__silver_definition_env_Defs] = "silver:definition:env:prodDclList";
		silver.definition.env.NDef.occurs_syn[silver.definition.env.Init.silver_definition_env_typeList__ON__silver_definition_env_Def] = "silver:definition:env:typeList";
		silver.definition.env.NDef.occurs_syn[silver.definition.env.Init.silver_definition_env_valueList__ON__silver_definition_env_Def] = "silver:definition:env:valueList";
		silver.definition.env.NDef.occurs_syn[silver.definition.env.Init.silver_definition_env_attrList__ON__silver_definition_env_Def] = "silver:definition:env:attrList";
		silver.definition.env.NDef.occurs_syn[silver.definition.env.Init.silver_definition_env_prodOccursList__ON__silver_definition_env_Def] = "silver:definition:env:prodOccursList";
		silver.definition.env.NDef.occurs_syn[silver.definition.env.Init.silver_definition_env_occursList__ON__silver_definition_env_Def] = "silver:definition:env:occursList";
		silver.definition.env.NDef.occurs_syn[silver.definition.env.Init.silver_definition_env_prodDclList__ON__silver_definition_env_Def] = "silver:definition:env:prodDclList";
		silver.definition.env.NDef.occurs_syn[silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_Def] = "silver:definition:env:dcl";
		//	local attribute h::DclInfo;
		silver.definition.env.PmapUnparseDcls.localInheritedAttributes[silver.definition.env.Init.h__ON__silver_definition_env_mapUnparseDcls] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];
		silver.definition.env.PmapUnparseDcls.occurs_local[silver.definition.env.Init.h__ON__silver_definition_env_mapUnparseDcls] = "silver:definition:env:mapUnparseDcls:local:h";
	}

	private static void initProductionAttributeDefinitions(){
		silver.definition.env.PnamedSignature.initProductionAttributeDefinitions();
		silver.definition.env.PbogusNamedSignature.initProductionAttributeDefinitions();
		silver.definition.env.PnamedSignatureElement.initProductionAttributeDefinitions();
		silver.definition.env.PbogusNamedSignatureElement.initProductionAttributeDefinitions();
		//FUNCTION namedSignatureElementLte Boolean ::= a::NamedSignatureElement b::NamedSignatureElement 
		//FUNCTION findNamedSigElem Integer ::= s::String l::[NamedSignatureElement] z::Integer 
		//FUNCTION quoteStrings [String] ::= s::[String] 
		//FUNCTION quoteString String ::= s::String 
		//FUNCTION unparseStrings String ::= s::[String] 
		//FUNCTION unparseNonStrings String ::= s::[String] 
		//FUNCTION unparseTypes String ::= tes::[Type] bv::[TyVar] 
		//FUNCTION mapUnparseTypes [String] ::= tes::[Type] bv::[TyVar] 
		// fst = head(tes)
		silver.definition.env.PmapUnparseTypes.localAttributes[silver.definition.env.Init.fst__ON__silver_definition_env_mapUnparseTypes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)core.Phead.invoke(context.childAsIsLazy(silver.definition.env.PmapUnparseTypes.i_tes))); } };
		// fst.boundVariables = bv
		silver.definition.env.PmapUnparseTypes.localInheritedAttributes[silver.definition.env.Init.fst__ON__silver_definition_env_mapUnparseTypes][silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.env.PmapUnparseTypes.i_bv)); } };
		//FUNCTION unparseTyVars String ::= utvs::[TyVar] bv::[TyVar] 
		//FUNCTION mapUnparseTyVars [String] ::= tes::[TyVar] bv::[TyVar] 
		//FUNCTION unparseSignatureElements String ::= s::[NamedSignatureElement] bv::[TyVar] 
		//FUNCTION unparseSignatureElementsHelp String ::= s::[NamedSignatureElement] bv::[TyVar] 
		// h = head(s)
		silver.definition.env.PunparseSignatureElementsHelp.localAttributes[silver.definition.env.Init.h__ON__silver_definition_env_unparseSignatureElementsHelp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsLazy(silver.definition.env.PunparseSignatureElementsHelp.i_s))); } };
		// h.boundVariables = bv
		silver.definition.env.PunparseSignatureElementsHelp.localInheritedAttributes[silver.definition.env.Init.h__ON__silver_definition_env_unparseSignatureElementsHelp][silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignatureElement] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.env.PunparseSignatureElementsHelp.i_bv)); } };
		//FUNCTION escapeString String ::= s::String 
		//FUNCTION unescapeString String ::= s::String 
		//FUNCTION searchEnvTree [a] ::= search::String et::EnvTree<a> 
		//FUNCTION buildTree EnvTree<DclInfo> ::= eis::[EnvItem] 
		//FUNCTION directBuildTree EnvTree<a> ::= eis::[Pair<String a>] 
		//FUNCTION explodeEnvItems [Pair<String DclInfo>] ::= eis::[EnvItem] 
		//ASPECT PRODUCTION loc top ::= filename::String line::Integer column::Integer endLine::Integer endColumn::Integer index::Integer endIndex::Integer 
		// top.unparse = "'" ++ filename ++ "', " ++ implode(", ", [ toString(line), toString(column), toString(endLine), toString(endColumn), toString(index), toString(endIndex) ])
		core.Ploc.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("'")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(core.Ploc.i_filename)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)context.childAsIs(core.Ploc.i_line)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)context.childAsIs(core.Ploc.i_column)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)context.childAsIs(core.Ploc.i_endLine)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)context.childAsIs(core.Ploc.i_endColumn)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)context.childAsIs(core.Ploc.i_index)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)context.childAsIs(core.Ploc.i_endIndex)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } }))))); } };
		//FUNCTION bogusLocation Location ::= 
		silver.definition.env.Pi_envScope_dummy_record.initProductionAttributeDefinitions();
		//FUNCTION emptyEnvScope Decorated EnvScope<a> ::= 
		//FUNCTION oneEnvScope Decorated EnvScope<a> ::= eis::EnvTree<a> 
		//FUNCTION appendEnvScope Decorated EnvScope<a> ::= l::Decorated EnvScope<a> r::Decorated EnvScope<a> 
		//FUNCTION consEnvScope Decorated EnvScope<a> ::= l::EnvTree<a> r::Decorated EnvScope<a> 
		//FUNCTION searchEnvScope [a] ::= search::String e::Decorated EnvScope<a> 
		//FUNCTION searchEnvScopeHelp [a] ::= search::String e::[EnvTree<a>] 
		//ASPECT DEFAULT PRODUCTION for Type
		// top.typeName = ""
		silver.definition.type.NType.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		//ASPECT PRODUCTION varType top ::= tv::TyVar 
		// top.unparse = findAbbrevFor(tv, top.boundVariables)
		silver.definition.type.PvarType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.type.PfindAbbrevFor.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PvarType.i_tv)), context.contextInheritedLazy(silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type))); } };
		//ASPECT PRODUCTION skolemType top ::= tv::TyVar 
		// top.unparse = findAbbrevFor(tv, top.boundVariables)
		silver.definition.type.PskolemType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.type.PfindAbbrevFor.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PskolemType.i_tv)), context.contextInheritedLazy(silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type))); } };
		//ASPECT PRODUCTION intType top ::= 
		// top.unparse = "int"
		silver.definition.type.PintType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("int")); } };
		//ASPECT PRODUCTION boolType top ::= 
		// top.unparse = "bool"
		silver.definition.type.PboolType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("bool")); } };
		//ASPECT PRODUCTION floatType top ::= 
		// top.unparse = "float"
		silver.definition.type.PfloatType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("float")); } };
		//ASPECT PRODUCTION stringType top ::= 
		// top.unparse = "string"
		silver.definition.type.PstringType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("string")); } };
		//ASPECT PRODUCTION nonterminalType top ::= fn::String params::[Type] 
		// top.unparse = "nt('" ++ fn ++ "', " ++ unparseTypes(params, top.boundVariables) ++ ")"
		silver.definition.type.PnonterminalType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("nt('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.type.PnonterminalType.i_fn)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseTypes.invoke(context.childAsIsLazy(silver.definition.type.PnonterminalType.i_params), context.contextInheritedLazy(silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type))), (common.StringCatter)(new common.StringCatter(")")))))); } };
		// top.typeName = fn
		silver.definition.type.PnonterminalType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.type.PnonterminalType.i_fn)); } };
		//ASPECT PRODUCTION terminalType top ::= fn::String 
		// top.unparse = "term('" ++ fn ++ "')"
		silver.definition.type.PterminalType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("term('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.type.PterminalType.i_fn)), (common.StringCatter)(new common.StringCatter("')")))); } };
		// top.typeName = fn
		silver.definition.type.PterminalType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.type.PterminalType.i_fn)); } };
		//ASPECT PRODUCTION decoratedType top ::= te::Type 
		// top.unparse = "decorated(" ++ te.unparse ++ ")"
		silver.definition.type.PdecoratedType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("decorated(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.PdecoratedType.i_te).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)(new common.StringCatter(")")))); } };
		// top.typeName = te.typeName
		silver.definition.type.PdecoratedType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.type.PdecoratedType.i_te).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } };
		//ASPECT PRODUCTION functionType top ::= out::Type params::[Type] namedParams::[NamedArgType] 
		// top.unparse = "fun(" ++ unparseTypes(params, top.boundVariables) ++ ", " ++ out.unparse ++ unparseNameArgTypes(namedParams, top.boundVariables) ++ ")"
		silver.definition.type.PfunctionType.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("fun(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseTypes.invoke(context.childAsIsLazy(silver.definition.type.PfunctionType.i_params), context.contextInheritedLazy(silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.PfunctionType.i_out).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseNameArgTypes.invoke(context.childAsIsLazy(silver.definition.type.PfunctionType.i_namedParams), context.contextInheritedLazy(silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type))), (common.StringCatter)(new common.StringCatter(")"))))))); } };
		//FUNCTION unparseNameArgTypes String ::= np::[NamedArgType] bv::[TyVar] 
		//ASPECT DEFAULT PRODUCTION for DclInfo
		// top.attrOccurring = error("Internal compiler error: must be defined for all occurs declarations")
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Internal compiler error: must be defined for all occurs declarations")))); } };
		// top.prodDefs = error("Internal compiler error: must be defined for all production attribute declarations")
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodDefs__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Internal compiler error: must be defined for all production attribute declarations")))); } };
		// top.dclBoundVars = error("Internal compiler error: must be defined for all value declarations")
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_dclBoundVars__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Internal compiler error: must be defined for all value declarations")))); } };
		// top.substitutedDclInfo = error("Internal compiler error: must be defined for all value declarations that are production attributes")
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_substitutedDclInfo__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)core.Perror.invoke((new common.StringCatter("Internal compiler error: must be defined for all value declarations that are production attributes")))); } };
		// top.namedSignature = bogusNamedSignature()
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_namedSignature__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)new silver.definition.env.PbogusNamedSignature()); } };
		// top.isAnnotation = false
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_isAnnotation__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.isSynthesized = false
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_isSynthesized__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.isInherited = false
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_isInherited__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		silver.definition.env.PchildDcl.initProductionAttributeDefinitions();
		silver.definition.env.PlhsDcl.initProductionAttributeDefinitions();
		silver.definition.env.PlocalDcl.initProductionAttributeDefinitions();
		silver.definition.env.PforwardDcl.initProductionAttributeDefinitions();
		silver.definition.env.PprodDcl.initProductionAttributeDefinitions();
		silver.definition.env.PfunDcl.initProductionAttributeDefinitions();
		silver.definition.env.PglobalValueDcl.initProductionAttributeDefinitions();
		silver.definition.env.PntDcl.initProductionAttributeDefinitions();
		silver.definition.env.PtermDcl.initProductionAttributeDefinitions();
		silver.definition.env.PlexTyVarDcl.initProductionAttributeDefinitions();
		silver.definition.env.PsynDcl.initProductionAttributeDefinitions();
		silver.definition.env.PinhDcl.initProductionAttributeDefinitions();
		silver.definition.env.PannoDcl.initProductionAttributeDefinitions();
		silver.definition.env.PpaDcl.initProductionAttributeDefinitions();
		silver.definition.env.PoccursDcl.initProductionAttributeDefinitions();
		silver.definition.env.PannoInstanceDcl.initProductionAttributeDefinitions();
		//FUNCTION determineAttributeType Type ::= occursDclInfo::DclInfo ntty::Type 
		// occursDclInfo.givenNonterminalType = ntty
		silver.definition.env.PdetermineAttributeType.childInheritedAttributes[silver.definition.env.PdetermineAttributeType.i_occursDclInfo][silver.definition.env.Init.silver_definition_env_givenNonterminalType__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PdetermineAttributeType.i_ntty).undecorate(); } };
		//FUNCTION performSubstitutionDclInfo DclInfo ::= valueDclInfo::DclInfo s::Substitution 
		// valueDclInfo.givenSubstitution = s
		silver.definition.env.PperformSubstitutionDclInfo.childInheritedAttributes[silver.definition.env.PperformSubstitutionDclInfo.i_valueDclInfo][silver.definition.env.Init.silver_definition_env_givenSubstitution__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PperformSubstitutionDclInfo.i_s).undecorate(); } };
		//FUNCTION defsFromPADcls [Def] ::= valueDclInfos::[DclInfo] s::NamedSignature 
		// subst = unifyDirectional(head(valueDclInfos).typerep, s.typerep)
		silver.definition.env.PdefsFromPADcls.localAttributes[silver.definition.env.Init.subst__ON__silver_definition_env_defsFromPADcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PunifyDirectional.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.definition.env.PdefsFromPADcls.i_valueDclInfos))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo)); } }, context.childDecoratedSynthesizedLazy(silver.definition.env.PdefsFromPADcls.i_s, silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignature))); } };
		//FUNCTION fullNameToShort String ::= s::String 
		//FUNCTION defaultEnvItem EnvItem ::= di::DclInfo 
		silver.definition.env.PrenamedEnvItem.initProductionAttributeDefinitions();
		silver.definition.env.PfullNameEnvItem.initProductionAttributeDefinitions();
		silver.definition.env.PonlyRenamedEnvItem.initProductionAttributeDefinitions();
		//FUNCTION mapGetDcls [DclInfo] ::= i::[EnvItem] 
		//FUNCTION mapFullnameDcls [EnvItem] ::= i::[DclInfo] 
		//FUNCTION mapDefaultWrapDcls [EnvItem] ::= i::[DclInfo] 
		//FUNCTION envItemExclude Boolean ::= ei::EnvItem exclude::[String] 
		//FUNCTION envItemInclude Boolean ::= ei::EnvItem include::[String] 
		//FUNCTION envItemPrepend EnvItem ::= ei::EnvItem pfx::String 
		//FUNCTION envItemApplyRenaming EnvItem ::= ei::EnvItem renames::[Pair<String String>] 
		// result = lookupBy(stringEq, ei.itemName, renames)
		silver.definition.env.PenvItemApplyRenaming.localAttributes[silver.definition.env.Init.result__ON__silver_definition_env_envItemApplyRenaming] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.PlookupBy.invoke(core.PstringEq.factory, context.childDecoratedSynthesizedLazy(silver.definition.env.PenvItemApplyRenaming.i_ei, silver.definition.env.Init.silver_definition_env_itemName__ON__silver_definition_env_EnvItem), context.childAsIsLazy(silver.definition.env.PenvItemApplyRenaming.i_renames))); } };
		//FUNCTION envItemNTFromProdDcl EnvItem ::= di::DclInfo 
		//FUNCTION emptyEnv Decorated Env ::= 
		silver.definition.env.Pi_emptyEnv.initProductionAttributeDefinitions();
		//FUNCTION toEnv Decorated Env ::= d::[Def] 
		//FUNCTION appendEnv Decorated Env ::= e1::Decorated Env e2::Decorated Env 
		silver.definition.env.Pi_appendEnv.initProductionAttributeDefinitions();
		//FUNCTION newScopeEnv Decorated Env ::= d::[Def] e::Decorated Env 
		silver.definition.env.Pi_newScopeEnv.initProductionAttributeDefinitions();
		//FUNCTION searchEnvAll [a] ::= search::String e::[Decorated EnvScope<a>] 
		//FUNCTION searchEnv [a] ::= search::String e::[Decorated EnvScope<a>] 
		// found = searchEnvScope(search, head(e))
		silver.definition.env.PsearchEnv.localAttributes[silver.definition.env.Init.found__ON__silver_definition_env_searchEnv] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvScope.invoke(context.childAsIsLazy(silver.definition.env.PsearchEnv.i_search), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.definition.env.PsearchEnv.i_e))); } })); } };
		//FUNCTION getValueDclInScope [DclInfo] ::= search::String e::Decorated Env 
		//FUNCTION getValueDcl [DclInfo] ::= search::String e::Decorated Env 
		//FUNCTION getValueDclAll [DclInfo] ::= search::String e::Decorated Env 
		//FUNCTION getTypeDclInScope [DclInfo] ::= search::String e::Decorated Env 
		//FUNCTION getTypeDcl [DclInfo] ::= search::String e::Decorated Env 
		//FUNCTION getTypeDclAll [DclInfo] ::= search::String e::Decorated Env 
		//FUNCTION getAttrDclInScope [DclInfo] ::= search::String e::Decorated Env 
		//FUNCTION getAttrDcl [DclInfo] ::= search::String e::Decorated Env 
		//FUNCTION getAttrDclAll [DclInfo] ::= search::String e::Decorated Env 
		//FUNCTION getOccursDcl [DclInfo] ::= fnat::String fnnt::String e::Decorated Env 
		//FUNCTION occursOnHelp [DclInfo] ::= i::[DclInfo] fnat::String 
		//FUNCTION getProdAttrs [DclInfo] ::= fnprod::String e::Decorated Env 
		//FUNCTION getProdsForNt [DclInfo] ::= fnnt::String e::Decorated Env 
		//FUNCTION getAttrsOn [DclInfo] ::= fnnt::String e::Decorated Env 
		//FUNCTION annotationsForNonterminal [NamedSignatureElement] ::= nt::Type env::Decorated Env 
		// annos = filter((.isAnnotation), getAttrsOn(nt.typeName, env))
		silver.definition.env.PannotationsForNonterminal.localAttributes[silver.definition.env.Init.annos__ON__silver_definition_env_annotationsForNonterminal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfilter.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_isAnnotation__ON__silver_definition_env_DclInfo, context), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetAttrsOn.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PannotationsForNonterminal.i_nt, silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type), context.childAsIsLazy(silver.definition.env.PannotationsForNonterminal.i_env))); } })); } };
		//FUNCTION annoInstanceToNamed NamedSignatureElement ::= nt::Type anno::DclInfo 
		// anno.givenNonterminalType = nt
		silver.definition.env.PannoInstanceToNamed.childInheritedAttributes[silver.definition.env.PannoInstanceToNamed.i_anno][silver.definition.env.Init.silver_definition_env_givenNonterminalType__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PannoInstanceToNamed.i_nt).undecorate(); } };
		silver.definition.env.PnilDefs.initProductionAttributeDefinitions();
		silver.definition.env.PconsDefs.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for Def
		// top.typeList = []
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_typeList__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.valueList = []
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_valueList__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.attrList = []
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_attrList__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.prodOccursList = []
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodOccursList__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.occursList = []
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_occursList__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.prodDclList = []
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodDclList__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		silver.definition.env.PtypeDef.initProductionAttributeDefinitions();
		silver.definition.env.PvalueDef.initProductionAttributeDefinitions();
		silver.definition.env.PattrDef.initProductionAttributeDefinitions();
		silver.definition.env.PprodDclDef.initProductionAttributeDefinitions();
		silver.definition.env.PpaDef.initProductionAttributeDefinitions();
		silver.definition.env.PoDef.initProductionAttributeDefinitions();
		//FUNCTION childDef Def ::= sg::String sl::Location fn::String ty::Type 
		//FUNCTION lhsDef Def ::= sg::String sl::Location fn::String ty::Type 
		//FUNCTION localDef Def ::= sg::String sl::Location fn::String ty::Type 
		//FUNCTION prodDef Def ::= sg::String sl::Location ns::NamedSignature 
		//FUNCTION funDef Def ::= sg::String sl::Location ns::NamedSignature 
		//FUNCTION globalDef Def ::= sg::String sl::Location fn::String ty::Type 
		//FUNCTION ntDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type 
		//FUNCTION closedNtDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type 
		//FUNCTION termDef Def ::= sg::String sl::Location fn::String regex::Regex 
		//FUNCTION lexTyVarDef Def ::= sg::String sl::Location fn::String ty::Type 
		//FUNCTION synDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type 
		//FUNCTION inhDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type 
		//FUNCTION prodOccursDef Def ::= sg::String sl::Location ns::NamedSignature dcls::[Def] 
		//FUNCTION forwardDef Def ::= sg::String sl::Location ty::Type 
		//FUNCTION aliasedLhsDef Def ::= sg::String sl::Location fn::String ty::Type alias::String 
		//FUNCTION aliasedChildDef Def ::= sg::String sl::Location fn::String ty::Type alias::String 
		//FUNCTION annoDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type 
		//FUNCTION annoInstanceDef Def ::= sg::String sl::Location fnnt::String fnat::String ntty::Type atty::Type 
		//FUNCTION unparseDefs String ::= d::[Def] bv::[TyVar] 
		//FUNCTION mapUnparseDcls [String] ::= d::[DclInfo] bv::[TyVar] 
		// h = head(d)
		silver.definition.env.PmapUnparseDcls.localAttributes[silver.definition.env.Init.h__ON__silver_definition_env_mapUnparseDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.definition.env.PmapUnparseDcls.i_d))); } };
		// h.boundVariables = bv
		silver.definition.env.PmapUnparseDcls.localInheritedAttributes[silver.definition.env.Init.h__ON__silver_definition_env_mapUnparseDcls][silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.env.PmapUnparseDcls.i_bv)); } };
		//FUNCTION performSubstitutionDef Def ::= d::Def s::Substitution 
		//FUNCTION filterDefOnEnvItem Boolean ::= fn::(Boolean ::= EnvItem) d::Def 
		//FUNCTION mapDefOnEnvItem Def ::= fn::(EnvItem ::= EnvItem) d::Def 
	}

	public static int count_inh__ON__NamedSignature = 0;
	public static int count_syn__ON__NamedSignature = 0;
	public static int count_inh__ON__NamedSignatureElement = 0;
	public static int count_syn__ON__NamedSignatureElement = 0;
	public static int count_local__ON__silver_definition_env_namedSignature = 0;
	public static int count_local__ON__silver_definition_env_bogusNamedSignature = 0;
	public static int count_local__ON__silver_definition_env_namedSignatureElement = 0;
	public static int count_local__ON__silver_definition_env_bogusNamedSignatureElement = 0;
	public static int count_local__ON__silver_definition_env_namedSignatureElementLte = 0;
	public static int count_local__ON__silver_definition_env_findNamedSigElem = 0;
	public static int count_local__ON__silver_definition_env_quoteStrings = 0;
	public static int count_local__ON__silver_definition_env_quoteString = 0;
	public static int count_local__ON__silver_definition_env_unparseStrings = 0;
	public static int count_local__ON__silver_definition_env_unparseNonStrings = 0;
	public static int count_local__ON__silver_definition_env_unparseTypes = 0;
	public static int count_local__ON__silver_definition_env_mapUnparseTypes = 0;
	public static int count_local__ON__silver_definition_env_unparseTyVars = 0;
	public static int count_local__ON__silver_definition_env_mapUnparseTyVars = 0;
	public static int count_local__ON__silver_definition_env_unparseSignatureElements = 0;
	public static int count_local__ON__silver_definition_env_unparseSignatureElementsHelp = 0;
	public static int count_local__ON__silver_definition_env_escapeString = 0;
	public static int count_local__ON__silver_definition_env_unescapeString = 0;
	public static int count_local__ON__silver_definition_env_searchEnvTree = 0;
	public static int count_local__ON__silver_definition_env_buildTree = 0;
	public static int count_local__ON__silver_definition_env_directBuildTree = 0;
	public static int count_local__ON__silver_definition_env_explodeEnvItems = 0;
	public static int count_local__ON__silver_definition_env_bogusLocation = 0;
	public static int count_inh__ON__EnvScope = 0;
	public static int count_syn__ON__EnvScope = 0;
	public static int count_local__ON__silver_definition_env_i_envScope_dummy_record = 0;
	public static int count_local__ON__silver_definition_env_emptyEnvScope = 0;
	public static int count_local__ON__silver_definition_env_oneEnvScope = 0;
	public static int count_local__ON__silver_definition_env_appendEnvScope = 0;
	public static int count_local__ON__silver_definition_env_consEnvScope = 0;
	public static int count_local__ON__silver_definition_env_searchEnvScope = 0;
	public static int count_local__ON__silver_definition_env_searchEnvScopeHelp = 0;
	public static int count_local__ON__silver_definition_env_unparseNameArgTypes = 0;
	public static int count_inh__ON__DclInfo = 0;
	public static int count_syn__ON__DclInfo = 0;
	public static int count_local__ON__silver_definition_env_childDcl = 0;
	public static int count_local__ON__silver_definition_env_lhsDcl = 0;
	public static int count_local__ON__silver_definition_env_localDcl = 0;
	public static int count_local__ON__silver_definition_env_forwardDcl = 0;
	public static int count_local__ON__silver_definition_env_prodDcl = 0;
	public static int count_local__ON__silver_definition_env_funDcl = 0;
	public static int count_local__ON__silver_definition_env_globalValueDcl = 0;
	public static int count_local__ON__silver_definition_env_ntDcl = 0;
	public static int count_local__ON__silver_definition_env_termDcl = 0;
	public static int count_local__ON__silver_definition_env_lexTyVarDcl = 0;
	public static int count_local__ON__silver_definition_env_synDcl = 0;
	public static int count_local__ON__silver_definition_env_inhDcl = 0;
	public static int count_local__ON__silver_definition_env_annoDcl = 0;
	public static int count_local__ON__silver_definition_env_paDcl = 0;
	public static int count_local__ON__silver_definition_env_occursDcl = 0;
	public static int count_local__ON__silver_definition_env_annoInstanceDcl = 0;
	public static int count_local__ON__silver_definition_env_determineAttributeType = 0;
	public static int count_local__ON__silver_definition_env_performSubstitutionDclInfo = 0;
	public static int count_local__ON__silver_definition_env_defsFromPADcls = 0;
	public static int count_inh__ON__EnvItem = 0;
	public static int count_syn__ON__EnvItem = 0;
	public static int count_local__ON__silver_definition_env_fullNameToShort = 0;
	public static int count_local__ON__silver_definition_env_defaultEnvItem = 0;
	public static int count_local__ON__silver_definition_env_renamedEnvItem = 0;
	public static int count_local__ON__silver_definition_env_fullNameEnvItem = 0;
	public static int count_local__ON__silver_definition_env_onlyRenamedEnvItem = 0;
	public static int count_local__ON__silver_definition_env_mapGetDcls = 0;
	public static int count_local__ON__silver_definition_env_mapFullnameDcls = 0;
	public static int count_local__ON__silver_definition_env_mapDefaultWrapDcls = 0;
	public static int count_local__ON__silver_definition_env_envItemExclude = 0;
	public static int count_local__ON__silver_definition_env_envItemInclude = 0;
	public static int count_local__ON__silver_definition_env_envItemPrepend = 0;
	public static int count_local__ON__silver_definition_env_envItemApplyRenaming = 0;
	public static int count_local__ON__silver_definition_env_envItemNTFromProdDcl = 0;
	public static int count_inh__ON__Env = 0;
	public static int count_syn__ON__Env = 0;
	public static int count_local__ON__silver_definition_env_emptyEnv = 0;
	public static int count_local__ON__silver_definition_env_i_emptyEnv = 0;
	public static int count_local__ON__silver_definition_env_toEnv = 0;
	public static int count_local__ON__silver_definition_env_appendEnv = 0;
	public static int count_local__ON__silver_definition_env_i_appendEnv = 0;
	public static int count_local__ON__silver_definition_env_newScopeEnv = 0;
	public static int count_local__ON__silver_definition_env_i_newScopeEnv = 0;
	public static int count_local__ON__silver_definition_env_searchEnvAll = 0;
	public static int count_local__ON__silver_definition_env_searchEnv = 0;
	public static int count_local__ON__silver_definition_env_getValueDclInScope = 0;
	public static int count_local__ON__silver_definition_env_getValueDcl = 0;
	public static int count_local__ON__silver_definition_env_getValueDclAll = 0;
	public static int count_local__ON__silver_definition_env_getTypeDclInScope = 0;
	public static int count_local__ON__silver_definition_env_getTypeDcl = 0;
	public static int count_local__ON__silver_definition_env_getTypeDclAll = 0;
	public static int count_local__ON__silver_definition_env_getAttrDclInScope = 0;
	public static int count_local__ON__silver_definition_env_getAttrDcl = 0;
	public static int count_local__ON__silver_definition_env_getAttrDclAll = 0;
	public static int count_local__ON__silver_definition_env_getOccursDcl = 0;
	public static int count_local__ON__silver_definition_env_occursOnHelp = 0;
	public static int count_local__ON__silver_definition_env_getProdAttrs = 0;
	public static int count_local__ON__silver_definition_env_getProdsForNt = 0;
	public static int count_local__ON__silver_definition_env_getAttrsOn = 0;
	public static int count_local__ON__silver_definition_env_annotationsForNonterminal = 0;
	public static int count_local__ON__silver_definition_env_annoInstanceToNamed = 0;
	public static int count_inh__ON__Defs = 0;
	public static int count_syn__ON__Defs = 0;
	public static int count_inh__ON__Def = 0;
	public static int count_syn__ON__Def = 0;
	public static int count_local__ON__silver_definition_env_nilDefs = 0;
	public static int count_local__ON__silver_definition_env_consDefs = 0;
	public static int count_local__ON__silver_definition_env_typeDef = 0;
	public static int count_local__ON__silver_definition_env_valueDef = 0;
	public static int count_local__ON__silver_definition_env_attrDef = 0;
	public static int count_local__ON__silver_definition_env_prodDclDef = 0;
	public static int count_local__ON__silver_definition_env_paDef = 0;
	public static int count_local__ON__silver_definition_env_oDef = 0;
	public static int count_local__ON__silver_definition_env_childDef = 0;
	public static int count_local__ON__silver_definition_env_lhsDef = 0;
	public static int count_local__ON__silver_definition_env_localDef = 0;
	public static int count_local__ON__silver_definition_env_prodDef = 0;
	public static int count_local__ON__silver_definition_env_funDef = 0;
	public static int count_local__ON__silver_definition_env_globalDef = 0;
	public static int count_local__ON__silver_definition_env_ntDef = 0;
	public static int count_local__ON__silver_definition_env_closedNtDef = 0;
	public static int count_local__ON__silver_definition_env_termDef = 0;
	public static int count_local__ON__silver_definition_env_lexTyVarDef = 0;
	public static int count_local__ON__silver_definition_env_synDef = 0;
	public static int count_local__ON__silver_definition_env_inhDef = 0;
	public static int count_local__ON__silver_definition_env_prodOccursDef = 0;
	public static int count_local__ON__silver_definition_env_forwardDef = 0;
	public static int count_local__ON__silver_definition_env_aliasedLhsDef = 0;
	public static int count_local__ON__silver_definition_env_aliasedChildDef = 0;
	public static int count_local__ON__silver_definition_env_annoDef = 0;
	public static int count_local__ON__silver_definition_env_annoInstanceDef = 0;
	public static int count_local__ON__silver_definition_env_unparseDefs = 0;
	public static int count_local__ON__silver_definition_env_mapUnparseDcls = 0;
	public static int count_local__ON__silver_definition_env_performSubstitutionDef = 0;
	public static int count_local__ON__silver_definition_env_filterDefOnEnvItem = 0;
	public static int count_local__ON__silver_definition_env_mapDefOnEnvItem = 0;
public static final int silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature = silver.definition.env.Init.count_syn__ON__NamedSignature++;
public static final int silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature = silver.definition.env.Init.count_syn__ON__NamedSignature++;
public static final int silver_definition_env_fullName__ON__silver_definition_env_NamedSignature = silver.definition.env.Init.count_syn__ON__NamedSignature++;
public static final int silver_definition_env_unparse__ON__silver_definition_env_NamedSignature = silver.definition.env.Init.count_syn__ON__NamedSignature++;
public static final int silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignature = silver.definition.env.Init.count_inh__ON__NamedSignature++;
public static final int silver_definition_env_inputNames__ON__silver_definition_env_NamedSignature = silver.definition.env.Init.count_syn__ON__NamedSignature++;
public static final int silver_definition_type_inputTypes__ON__silver_definition_env_NamedSignature = silver.definition.env.Init.count_syn__ON__NamedSignature++;
public static final int silver_definition_env_typerep__ON__silver_definition_env_NamedSignature = silver.definition.env.Init.count_syn__ON__NamedSignature++;
public static final int silver_definition_env_namedInputElements__ON__silver_definition_env_NamedSignature = silver.definition.env.Init.count_syn__ON__NamedSignature++;
public static final int silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement = silver.definition.env.Init.count_syn__ON__NamedSignatureElement++;
public static final int silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement = silver.definition.env.Init.count_syn__ON__NamedSignatureElement++;
public static final int silver_definition_env_unparse__ON__silver_definition_env_NamedSignatureElement = silver.definition.env.Init.count_syn__ON__NamedSignatureElement++;
public static final int silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignatureElement = silver.definition.env.Init.count_inh__ON__NamedSignatureElement++;
public static final int silver_definition_env_toNamedArgType__ON__silver_definition_env_NamedSignatureElement = silver.definition.env.Init.count_syn__ON__NamedSignatureElement++;
public static final int annoShortName__ON__silver_definition_env_namedSignatureElement = silver.definition.env.Init.count_local__ON__silver_definition_env_namedSignatureElement++;
public static final int fst__ON__silver_definition_env_mapUnparseTypes = silver.definition.env.Init.count_local__ON__silver_definition_env_mapUnparseTypes++;
public static final int h__ON__silver_definition_env_unparseSignatureElementsHelp = silver.definition.env.Init.count_local__ON__silver_definition_env_unparseSignatureElementsHelp++;
public static final int silver_definition_env_unparse__ON__core_Location = core.Init.count_syn__ON__Location++;
public static final int silver_definition_env_envTrees__ON__silver_definition_env_EnvScope = silver.definition.env.Init.count_inh__ON__EnvScope++;
public static final int silver_definition_env_unparse__ON__silver_definition_type_Type = silver.definition.type.Init.count_syn__ON__Type++;
public static final int silver_definition_env_typeName__ON__silver_definition_type_Type = silver.definition.type.Init.count_syn__ON__Type++;
public static final int silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_sourceLocation__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_fullName__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_unparse__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_type_boundVariables__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_inh__ON__DclInfo++;
public static final int silver_definition_env_typerep__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_givenNonterminalType__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_inh__ON__DclInfo++;
public static final int silver_definition_env_namedSignature__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_isAnnotation__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_isInherited__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_isSynthesized__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_prodDefs__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_dclBoundVars__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_substitutedDclInfo__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_definition_env_givenSubstitution__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_inh__ON__DclInfo++;
public static final int boundvars__ON__silver_definition_env_prodDcl = silver.definition.env.Init.count_local__ON__silver_definition_env_prodDcl++;
public static final int boundvars__ON__silver_definition_env_funDcl = silver.definition.env.Init.count_local__ON__silver_definition_env_funDcl++;
public static final int boundvars__ON__silver_definition_env_paDcl = silver.definition.env.Init.count_local__ON__silver_definition_env_paDcl++;
public static final int subst__ON__silver_definition_env_occursDcl = silver.definition.env.Init.count_local__ON__silver_definition_env_occursDcl++;
public static final int subst__ON__silver_definition_env_annoInstanceDcl = silver.definition.env.Init.count_local__ON__silver_definition_env_annoInstanceDcl++;
public static final int subst__ON__silver_definition_env_defsFromPADcls = silver.definition.env.Init.count_local__ON__silver_definition_env_defsFromPADcls++;
public static final int silver_definition_env_itemName__ON__silver_definition_env_EnvItem = silver.definition.env.Init.count_syn__ON__EnvItem++;
public static final int silver_definition_env_dcl__ON__silver_definition_env_EnvItem = silver.definition.env.Init.count_syn__ON__EnvItem++;
public static final int silver_definition_env_envContribs__ON__silver_definition_env_EnvItem = silver.definition.env.Init.count_syn__ON__EnvItem++;
public static final int result__ON__silver_definition_env_envItemApplyRenaming = silver.definition.env.Init.count_local__ON__silver_definition_env_envItemApplyRenaming++;
public static final int silver_definition_env_typeTree__ON__silver_definition_env_Env = silver.definition.env.Init.count_syn__ON__Env++;
public static final int silver_definition_env_valueTree__ON__silver_definition_env_Env = silver.definition.env.Init.count_syn__ON__Env++;
public static final int silver_definition_env_attrTree__ON__silver_definition_env_Env = silver.definition.env.Init.count_syn__ON__Env++;
public static final int silver_definition_env_prodOccursTree__ON__silver_definition_env_Env = silver.definition.env.Init.count_syn__ON__Env++;
public static final int silver_definition_env_occursTree__ON__silver_definition_env_Env = silver.definition.env.Init.count_syn__ON__Env++;
public static final int silver_definition_env_prodsForNtTree__ON__silver_definition_env_Env = silver.definition.env.Init.count_syn__ON__Env++;
public static final int found__ON__silver_definition_env_searchEnv = silver.definition.env.Init.count_local__ON__silver_definition_env_searchEnv++;
public static final int annos__ON__silver_definition_env_annotationsForNonterminal = silver.definition.env.Init.count_local__ON__silver_definition_env_annotationsForNonterminal++;
public static final int silver_definition_env_typeList__ON__silver_definition_env_Defs = silver.definition.env.Init.count_syn__ON__Defs++;
public static final int silver_definition_env_valueList__ON__silver_definition_env_Defs = silver.definition.env.Init.count_syn__ON__Defs++;
public static final int silver_definition_env_attrList__ON__silver_definition_env_Defs = silver.definition.env.Init.count_syn__ON__Defs++;
public static final int silver_definition_env_prodOccursList__ON__silver_definition_env_Defs = silver.definition.env.Init.count_syn__ON__Defs++;
public static final int silver_definition_env_occursList__ON__silver_definition_env_Defs = silver.definition.env.Init.count_syn__ON__Defs++;
public static final int silver_definition_env_prodDclList__ON__silver_definition_env_Defs = silver.definition.env.Init.count_syn__ON__Defs++;
public static final int silver_definition_env_typeList__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_definition_env_valueList__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_definition_env_attrList__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_definition_env_prodOccursList__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_definition_env_occursList__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_definition_env_prodDclList__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_definition_env_dcl__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int h__ON__silver_definition_env_mapUnparseDcls = silver.definition.env.Init.count_local__ON__silver_definition_env_mapUnparseDcls++;
}
