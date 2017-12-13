
package silver.definition.core;

// top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
public final class PfunctionDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_3 = 0;
	public static final int i_id = 1;
	public static final int i_ns = 2;
	public static final int i_body = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.TFunction_kwd.class,silver.definition.core.NName.class,silver.definition.core.NFunctionSignature.class,silver.definition.core.NProductionBody.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_functionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NFunctionSignature.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];

	}

	public PfunctionDcl(final Object c__G_3, final Object c_id, final Object c_ns, final Object c_body, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child_id = c_id;
		this.child_ns = c_ns;
		this.child_body = c_body;

	}

	private Object child__G_3;
	public final silver.definition.core.TFunction_kwd getChild__G_3() {
		return (silver.definition.core.TFunction_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_ns;
	public final silver.definition.core.NFunctionSignature getChild_ns() {
		return (silver.definition.core.NFunctionSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_body;
	public final silver.definition.core.NProductionBody getChild_body() {
		return (silver.definition.core.NProductionBody) (child_body = common.Util.demand(child_body));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
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
			case i__G_3: return child__G_3;
			case i_id: return child_id;
			case i_ns: return child_ns;
			case i_body: return child_body;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:functionDcl erroneously claimed to forward");
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
		return "silver:definition:core:functionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "function " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp
		silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("function ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PfunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PfunctionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_FunctionSignature)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PfunctionDcl.i_body).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionBody))))))); } };
		// fName = top.grammarName ++ ":" ++ id.name
		silver.definition.core.PfunctionDcl.localAttributes[silver.definition.core.Init.fName__ON__silver_definition_core_functionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PfunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// namedSig = ns.namedSignature
		silver.definition.core.PfunctionDcl.localAttributes[silver.definition.core.Init.namedSig__ON__silver_definition_core_functionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)context.childDecorated(silver.definition.core.PfunctionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_FunctionSignature)); } };
		// top.defs = (funDef(top.grammarName, id.location, namedSig) :: if null(body.productionAttributes) then [] else [ prodOccursDef(top.grammarName, id.location, namedSig, body.productionAttributes) ])
		silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PfunDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PfunctionDcl.i_id).undecorate()).getAnno_core_location()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.namedSig__ON__silver_definition_core_functionDcl)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionDcl.i_body, silver.definition.core.Init.silver_definition_core_productionAttributes__ON__silver_definition_core_ProductionBody))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PprodOccursDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PfunctionDcl.i_id).undecorate()).getAnno_core_location()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.namedSig__ON__silver_definition_core_functionDcl)), context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionDcl.i_body, silver.definition.core.Init.silver_definition_core_productionAttributes__ON__silver_definition_core_ProductionBody))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } })); } };
		// top.errors <- if length(getValueDclAll(fName, top.env)) > 1 then [ err(id.location, "Value '" ++ fName ++ "' is already bound.") ] else []
		if(silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetValueDclAll.invoke(context.localAsIsLazy(silver.definition.core.Init.fName__ON__silver_definition_core_functionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PfunctionDcl.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Value '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.core.Init.fName__ON__silver_definition_core_functionDcl)), (common.StringCatter)(new common.StringCatter("' is already bound.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors <- if null(body.uniqueSignificantExpression) then [ err(top.location, "Function '" ++ id.name ++ "' does not have a return value.") ] else if length(body.uniqueSignificantExpression) > 1 then [ err(top.location, "Function '" ++ id.name ++ "' has more than one declared return value.") ] else []
		if(silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionDcl.i_body, silver.definition.core.Init.silver_definition_core_uniqueSignificantExpression__ON__silver_definition_core_ProductionBody))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Function '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PfunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)(new common.StringCatter("' does not have a return value.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionDcl.i_body, silver.definition.core.Init.silver_definition_core_uniqueSignificantExpression__ON__silver_definition_core_ProductionBody))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Function '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PfunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)(new common.StringCatter("' has more than one declared return value.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke()))); } });
		// top.errors := ns.errors ++ body.errors
		if(silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PfunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionDcl.i_ns, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_FunctionSignature), context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionDcl.i_body, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionBody))); } });
		// sigDefs := ns.defs
		((common.CollectionAttribute)silver.definition.core.PfunctionDcl.localAttributes[silver.definition.core.Init.sigDefs__ON__silver_definition_core_functionDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PfunctionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_FunctionSignature)); } });
		// ns.signatureName = fName
		silver.definition.core.PfunctionDcl.childInheritedAttributes[silver.definition.core.PfunctionDcl.i_ns][silver.definition.core.Init.silver_definition_core_signatureName__ON__silver_definition_core_FunctionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localAsIs(silver.definition.core.Init.fName__ON__silver_definition_core_functionDcl)); } };
		// ns.env = newScopeEnv(sigDefs, top.env)
		silver.definition.core.PfunctionDcl.childInheritedAttributes[silver.definition.core.PfunctionDcl.i_ns][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_FunctionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.localAsIsLazy(silver.definition.core.Init.sigDefs__ON__silver_definition_core_functionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// prodAtts = defsFromPADcls(getProdAttrs(fName, top.env), namedSig)
		silver.definition.core.PfunctionDcl.localAttributes[silver.definition.core.Init.prodAtts__ON__silver_definition_core_functionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PdefsFromPADcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetProdAttrs.invoke(context.localAsIsLazy(silver.definition.core.Init.fName__ON__silver_definition_core_functionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.namedSig__ON__silver_definition_core_functionDcl)))); } };
		// body.env = newScopeEnv(body.defs ++ sigDefs, newScopeEnv(prodAtts, top.env))
		silver.definition.core.PfunctionDcl.childInheritedAttributes[silver.definition.core.PfunctionDcl.i_body][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PfunctionDcl.i_body, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionBody), context.localAsIsLazy(silver.definition.core.Init.sigDefs__ON__silver_definition_core_functionDcl))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.localAsIsLazy(silver.definition.core.Init.prodAtts__ON__silver_definition_core_functionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })); } };
		// body.frame = functionContext(namedSig, myFlowGraph)
		silver.definition.core.PfunctionDcl.childInheritedAttributes[silver.definition.core.PfunctionDcl.i_body][silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NBlockContext)new silver.definition.core.PfunctionContext(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.namedSig__ON__silver_definition_core_functionDcl)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.flow.env.Init.myFlowGraph__ON__silver_definition_core_functionDcl)))); } };

	}

	public static final common.NodeFactory<PfunctionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfunctionDcl> {

		@Override
		public PfunctionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PfunctionDcl(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
