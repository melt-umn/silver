
package silver.extension.bidirtransform;

// top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
public final class PfakeAspectProductionDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_4 = 0;
	public static final int i__G_3 = 1;
	public static final int i_id = 2;
	public static final int i_ns = 3;
	public static final int i_body = 4;


	public static final Class<?> childTypes[] = {silver.definition.core.TAspect_kwd.class,silver.definition.core.TProduction_kwd.class,silver.definition.core.NQName.class,silver.definition.core.NAspectProductionSignature.class,silver.definition.core.NProductionBody.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_fakeAspectProductionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NAspectProductionSignature.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];

	}

	public PfakeAspectProductionDcl(final Object c__G_4, final Object c__G_3, final Object c_id, final Object c_ns, final Object c_body, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child__G_3 = c__G_3;
		this.child_id = c_id;
		this.child_ns = c_ns;
		this.child_body = c_body;

	}

	private Object child__G_4;
	public final silver.definition.core.TAspect_kwd getChild__G_4() {
		return (silver.definition.core.TAspect_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child__G_3;
	public final silver.definition.core.TProduction_kwd getChild__G_3() {
		return (silver.definition.core.TProduction_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_id;
	public final silver.definition.core.NQName getChild_id() {
		return (silver.definition.core.NQName) (child_id = common.Util.demand(child_id));
	}

	private Object child_ns;
	public final silver.definition.core.NAspectProductionSignature getChild_ns() {
		return (silver.definition.core.NAspectProductionSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_body;
	public final silver.definition.core.NProductionBody getChild_body() {
		return (silver.definition.core.NProductionBody) (child_body = common.Util.demand(child_body));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i__G_3: return getChild__G_3();
			case i_id: return getChild_id();
			case i_ns: return getChild_ns();
			case i_body: return getChild_body();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i__G_3: return child__G_3;
			case i_id: return child_id;
			case i_ns: return child_ns;
			case i_body: return child_body;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
	}

	@Override
	public common.Lazy[] getLocalInheritedAttributes(final int key) {
		return localInheritedAttributes[key];
	}

	@Override
	public common.Lazy[] getChildInheritedAttributes(final int key) {
		return childInheritedAttributes[key];
	}

	@Override
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:fakeAspectProductionDcl erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
	}

	@Override
	public common.Lazy getLocal(final int key) {
		return localAttributes[key];
	}

	@Override
	public final int getNumberOfLocalAttrs() {
		return num_local_attrs;
	}

	@Override
	public final String getNameOfLocalAttr(final int index) {
		return occurs_local[index];
	}

	@Override
	public String getName() {
		return "silver:extension:bidirtransform:fakeAspectProductionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// namedSig = ns.namedSignature
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localAttributes[silver.extension.bidirtransform.Init.namedSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_AspectProductionSignature)); } };
		// realSig = if null(id.lookupValue.errors) then id.lookupValue.dcl.namedSignature else bogusNamedSignature()
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localAttributes[silver.extension.bidirtransform.Init.realSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } })) ? ((silver.definition.env.NNamedSignature)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_namedSignature__ON__silver_definition_env_DclInfo)) : ((silver.definition.env.NNamedSignature)new silver.definition.env.PbogusNamedSignature())); } };
		// sigDefs = ns.defs ++ addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, allLexicalTyVars)
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localAttributes[silver.extension.bidirtransform.Init.sigDefs__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AspectProductionSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.syntax.PaddNewLexicalTyVars_ActuallyVariables.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.extension.bidirtransform.Init.allLexicalTyVars__ON__silver_extension_bidirtransform_fakeAspectProductionDcl))); } })); } };
		// prodAtts = []
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localAttributes[silver.extension.bidirtransform.Init.prodAtts__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// allLexicalTyVars = makeSet(ns.lexicalTypeVariables)
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localAttributes[silver.extension.bidirtransform.Init.allLexicalTyVars__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_core_AspectProductionSignature))); } };
		// myGraphs = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localAttributes[silver.extension.bidirtransform.Init.myGraphs__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)((common.DecoratedNode)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl))); } })).inherited(silver.driver.util.Init.silver_definition_env_productionFlowGraphs__ON__silver_driver_util_RootSpec)); } };
		// myFlowGraph = findProductionGraph(id.lookupValue.fullName, myGraphs)
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localAttributes[silver.extension.bidirtransform.Init.myFlowGraph__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NProductionGraph)silver.definition.flow.driver.PfindProductionGraph.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, context.localAsIsLazy(silver.extension.bidirtransform.Init.myGraphs__ON__silver_extension_bidirtransform_fakeAspectProductionDcl))); } };
		// errCheck1 = check(id.lookupValue.typerep, namedSig.typerep)
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localAttributes[silver.extension.bidirtransform.Init.errCheck1__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)context.localDecorated(silver.extension.bidirtransform.Init.namedSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignature)); } })); } };
		// top.errors := id.lookupValue.errors ++ ns.errors ++ body.errors
		if(silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectProductionSignature), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_body, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionBody))); } })); } });
		// ns.signatureName = id.lookupValue.fullName
		silver.extension.bidirtransform.PfakeAspectProductionDcl.childInheritedAttributes[silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns][silver.definition.core.Init.silver_definition_core_signatureName__ON__silver_definition_core_AspectProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } };
		// ns.env = newScopeEnv(sigDefs, top.env)
		silver.extension.bidirtransform.PfakeAspectProductionDcl.childInheritedAttributes[silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AspectProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.sigDefs__ON__silver_extension_bidirtransform_fakeAspectProductionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// ns.realSignature = if null(id.lookupValue.dcls) then [] else [ realSig.outputElement ] ++ realSig.inputElements
		silver.extension.bidirtransform.PfakeAspectProductionDcl.childInheritedAttributes[silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns][silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)context.localDecorated(silver.extension.bidirtransform.Init.realSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.realSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl).synthesized(silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature)); } }))); } };
		// body.env = newScopeEnv(body.defs ++ sigDefs, newScopeEnv(prodAtts, top.env))
		silver.extension.bidirtransform.PfakeAspectProductionDcl.childInheritedAttributes[silver.extension.bidirtransform.PfakeAspectProductionDcl.i_body][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_body, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionBody), context.localAsIsLazy(silver.extension.bidirtransform.Init.sigDefs__ON__silver_extension_bidirtransform_fakeAspectProductionDcl))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.prodAtts__ON__silver_extension_bidirtransform_fakeAspectProductionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })); } };
		// body.frame = aspectProductionContext(namedSig, myFlowGraph)
		silver.extension.bidirtransform.PfakeAspectProductionDcl.childInheritedAttributes[silver.extension.bidirtransform.PfakeAspectProductionDcl.i_body][silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NBlockContext)new silver.definition.core.PaspectProductionContext(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.namedSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.myFlowGraph__ON__silver_extension_bidirtransform_fakeAspectProductionDcl)))); } };
		// body.prodOutput = ns.namedSignature.outputElement
		silver.extension.bidirtransform.PfakeAspectProductionDcl.childInheritedAttributes[silver.extension.bidirtransform.PfakeAspectProductionDcl.i_body][silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_AspectProductionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)); } };
		// top.pp = "aspect production " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp
		silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("aspect production ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectProductionSignature)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_body).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionBody))))))); } };
		// top.setupInh := body.setupInh
		if(silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl] == null)
			silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAsetupInh(silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_body).synthesized(silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_ProductionBody)); } });
		// top.initProd := "\t\t//FAKE ASPECT PRODUCTION " ++ id.name ++ " " ++ ns.pp ++ "\n" ++ body.translation
		if(silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl] == null)
			silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAinitProd(silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\t\t//FAKE ASPECT PRODUCTION ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectProductionSignature)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_body).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_ProductionBody))))))); } });
		// top.valueWeaving := body.valueWeaving
		if(silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl] == null)
			silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAvalueWeaving(silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_body).synthesized(silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_ProductionBody)); } });
		// top.docs := []
		if(silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] == null)
			silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl] = new silver.extension.doc.core.CAdocs(silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_docs__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.flowDefs = body.flowDefs
		silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_body).synthesized(silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_ProductionBody)); } };
		// top.defs = []
		silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.errors <- if errCheck1.typeerror then [ err(top.location, "Aspect for '" ++ id.name ++ "' does not have the right signature.\nExpected: " ++ errCheck1.leftpp ++ "\nActual: " ++ errCheck1.rightpp) ] else case id.lookupValue.dcls of prodDcl(_, _, _, _)::_ -> [] | funDcl(_, _, _)::_ -> [ err(top.location, "Production aspect for '" ++ id.name ++ "' should be a 'function' aspect instead.") ] | _ -> [] end
		if(silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.bidirtransform.PfakeAspectProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.extension.bidirtransform.Init.errCheck1__ON__silver_extension_bidirtransform_fakeAspectProductionDcl).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Aspect for '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("' does not have the right signature.\nExpected: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.bidirtransform.Init.errCheck1__ON__silver_extension_bidirtransform_fakeAspectProductionDcl).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nActual: ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.extension.bidirtransform.Init.errCheck1__ON__silver_extension_bidirtransform_fakeAspectProductionDcl).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_12441___fail_12442 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_12449___sv_tmp_pv_12448 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_12450___sv_tmp_pv_12451 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.env.PfunDcl) { final common.Thunk<Object> __SV_LOCAL___pv12453___sv_tmp_pv_12454 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12455___sv_tmp_pv_12456 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12457___sv_tmp_pv_12458 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Production aspect for '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter("' should be a 'function' aspect instead.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }
else if(scrutineeNode instanceof silver.definition.env.PprodDcl) { final common.Thunk<Object> __SV_LOCAL___pv12461___sv_tmp_pv_12462 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv12463___sv_tmp_pv_12464 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv12465___sv_tmp_pv_12466 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv12467___sv_tmp_pv_12468 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(3); } };
 return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_12441___fail_12442.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.env.NDclInfo)(__SV_LOCAL_12449___sv_tmp_pv_12448.eval())).decorate(context, (common.Lazy[])null)); }return ((common.ConsCell)(__SV_LOCAL_12441___fail_12442.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup))); } }).eval())); } });
		// ns.downSubst = emptySubst()
		silver.extension.bidirtransform.PfakeAspectProductionDcl.childInheritedAttributes[silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_AspectProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PemptySubst.invoke()); } };
		// errCheck1.downSubst = ns.upSubst
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localInheritedAttributes[silver.extension.bidirtransform.Init.errCheck1__ON__silver_extension_bidirtransform_fakeAspectProductionDcl][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_AspectProductionSignature)); } };
		// body.downSubst = errCheck1.upSubst
		silver.extension.bidirtransform.PfakeAspectProductionDcl.childInheritedAttributes[silver.extension.bidirtransform.PfakeAspectProductionDcl.i_body][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.extension.bidirtransform.Init.errCheck1__ON__silver_extension_bidirtransform_fakeAspectProductionDcl).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// ns.finalSubst = errCheck1.upSubst
		silver.extension.bidirtransform.PfakeAspectProductionDcl.childInheritedAttributes[silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_AspectProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.extension.bidirtransform.Init.errCheck1__ON__silver_extension_bidirtransform_fakeAspectProductionDcl).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// errCheck1.finalSubst = ns.finalSubst
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localInheritedAttributes[silver.extension.bidirtransform.Init.errCheck1__ON__silver_extension_bidirtransform_fakeAspectProductionDcl][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PfakeAspectProductionDcl.i_ns).inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_AspectProductionSignature)); } };

	}

	public static final common.NodeFactory<PfakeAspectProductionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfakeAspectProductionDcl> {

		@Override
		public PfakeAspectProductionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PfakeAspectProductionDcl(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
