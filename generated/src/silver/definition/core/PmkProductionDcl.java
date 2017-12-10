
package silver.definition.core;

// top::AGDcl ::= id::Name ns::ProductionSignature body::ProductionBody isAbstract::Boolean 
public final class PmkProductionDcl extends silver.definition.core.NAGDcl {

	public static final int i_id = 0;
	public static final int i_ns = 1;
	public static final int i_body = 2;
	public static final int i_isAbstract = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.NName.class,silver.definition.core.NProductionSignature.class,silver.definition.core.NProductionBody.class,Boolean.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_mkProductionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NProductionSignature.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];

	}

	public PmkProductionDcl(final Object c_id, final Object c_ns, final Object c_body, final Object c_isAbstract, final Object a_core_location) {
		super(a_core_location);
		this.child_id = c_id;
		this.child_ns = c_ns;
		this.child_body = c_body;
		this.child_isAbstract = c_isAbstract;

	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_ns;
	public final silver.definition.core.NProductionSignature getChild_ns() {
		return (silver.definition.core.NProductionSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_body;
	public final silver.definition.core.NProductionBody getChild_body() {
		return (silver.definition.core.NProductionBody) (child_body = common.Util.demand(child_body));
	}

	private Object child_isAbstract;
	public final Boolean getChild_isAbstract() {
		return (Boolean) (child_isAbstract = common.Util.demand(child_isAbstract));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();
			case i_ns: return getChild_ns();
			case i_body: return getChild_body();
			case i_isAbstract: return getChild_isAbstract();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;
			case i_ns: return child_ns;
			case i_body: return child_body;
			case i_isAbstract: return child_isAbstract;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:mkProductionDcl erroneously claimed to forward");
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
		return "silver:definition:core:mkProductionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// fName = top.grammarName ++ ":" ++ id.name
		silver.definition.core.PmkProductionDcl.localAttributes[silver.definition.core.Init.fName__ON__silver_definition_core_mkProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmkProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// namedSig = ns.namedSignature
		silver.definition.core.PmkProductionDcl.localAttributes[silver.definition.core.Init.namedSig__ON__silver_definition_core_mkProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)context.childDecorated(silver.definition.core.PmkProductionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature)); } };
		// sigDefs := ns.defs
		((common.CollectionAttribute)silver.definition.core.PmkProductionDcl.localAttributes[silver.definition.core.Init.sigDefs__ON__silver_definition_core_mkProductionDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PmkProductionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionSignature)); } });
		// ns.signatureName = fName
		silver.definition.core.PmkProductionDcl.childInheritedAttributes[silver.definition.core.PmkProductionDcl.i_ns][silver.definition.core.Init.silver_definition_core_signatureName__ON__silver_definition_core_ProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localAsIs(silver.definition.core.Init.fName__ON__silver_definition_core_mkProductionDcl)); } };
		// ns.env = newScopeEnv(sigDefs, top.env)
		silver.definition.core.PmkProductionDcl.childInheritedAttributes[silver.definition.core.PmkProductionDcl.i_ns][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.localAsIsLazy(silver.definition.core.Init.sigDefs__ON__silver_definition_core_mkProductionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// top.defs = (prodDef(top.grammarName, id.location, namedSig, isAbstract) :: if null(body.productionAttributes) then [] else [ prodOccursDef(top.grammarName, id.location, namedSig, body.productionAttributes) ])
		silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PprodDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PmkProductionDcl.i_id).undecorate()).getAnno_core_location()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.namedSig__ON__silver_definition_core_mkProductionDcl)), context.childAsIsLazy(silver.definition.core.PmkProductionDcl.i_isAbstract))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmkProductionDcl.i_body, silver.definition.core.Init.silver_definition_core_productionAttributes__ON__silver_definition_core_ProductionBody))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PprodOccursDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PmkProductionDcl.i_id).undecorate()).getAnno_core_location()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.namedSig__ON__silver_definition_core_mkProductionDcl)), context.childDecoratedSynthesizedLazy(silver.definition.core.PmkProductionDcl.i_body, silver.definition.core.Init.silver_definition_core_productionAttributes__ON__silver_definition_core_ProductionBody))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } })); } };
		// top.errors <- if length(getValueDclAll(fName, top.env)) > 1 then [ err(id.location, "Value '" ++ fName ++ "' is already bound.") ] else if length(getValueDclAll(id.name, top.env)) > 1 then [ err(top.location, "Production " ++ id.pp ++ " shares a name with another production from an imported grammar. Either this production is meant to be an aspect, or you should use 'import ... with " ++ id.pp ++ " as ...' to change the other production's apparent name.") ] else []
		if(silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetValueDclAll.invoke(context.localAsIsLazy(silver.definition.core.Init.fName__ON__silver_definition_core_mkProductionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PmkProductionDcl.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Value '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.core.Init.fName__ON__silver_definition_core_mkProductionDcl)), (common.StringCatter)(new common.StringCatter("' is already bound.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetValueDclAll.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmkProductionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Production ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmkProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" shares a name with another production from an imported grammar. Either this production is meant to be an aspect, or you should use 'import ... with ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmkProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)(new common.StringCatter(" as ...' to change the other production's apparent name.")))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke()))); } });
		// top.errors <- if length(body.uniqueSignificantExpression) > 1 then [ err(top.location, "Production '" ++ id.name ++ "' has more than one forward declaration.") ] else []
		if(silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmkProductionDcl.i_body, silver.definition.core.Init.silver_definition_core_uniqueSignificantExpression__ON__silver_definition_core_ProductionBody))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Production '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmkProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)(new common.StringCatter("' has more than one forward declaration.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors <- if isLower(substring(0, 1, id.name)) then [] else [ wrn(id.location, "(future) " ++ id.name ++ ": productions may be required to begin with a lower-case letter.") ]
		if(silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.PisLower.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), Integer.valueOf((int)1), context.childDecoratedSynthesizedLazy(silver.definition.core.PmkProductionDcl.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name))); } })) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PmkProductionDcl.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(future) ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmkProductionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)(new common.StringCatter(": productions may be required to begin with a lower-case letter.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.errors := ns.errors ++ body.errors
		if(silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PmkProductionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmkProductionDcl.i_ns, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionSignature), context.childDecoratedSynthesizedLazy(silver.definition.core.PmkProductionDcl.i_body, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionBody))); } });
		// prodAtts = defsFromPADcls(getProdAttrs(fName, top.env), namedSig)
		silver.definition.core.PmkProductionDcl.localAttributes[silver.definition.core.Init.prodAtts__ON__silver_definition_core_mkProductionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PdefsFromPADcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetProdAttrs.invoke(context.localAsIsLazy(silver.definition.core.Init.fName__ON__silver_definition_core_mkProductionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.namedSig__ON__silver_definition_core_mkProductionDcl)))); } };
		// body.env = newScopeEnv(body.defs ++ sigDefs, newScopeEnv(prodAtts, top.env))
		silver.definition.core.PmkProductionDcl.childInheritedAttributes[silver.definition.core.PmkProductionDcl.i_body][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmkProductionDcl.i_body, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionBody), context.localAsIsLazy(silver.definition.core.Init.sigDefs__ON__silver_definition_core_mkProductionDcl))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.localAsIsLazy(silver.definition.core.Init.prodAtts__ON__silver_definition_core_mkProductionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })); } };
		// body.frame = productionContext(namedSig, myFlowGraph)
		silver.definition.core.PmkProductionDcl.childInheritedAttributes[silver.definition.core.PmkProductionDcl.i_body][silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NBlockContext)new silver.definition.core.PproductionContext(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.namedSig__ON__silver_definition_core_mkProductionDcl)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.flow.env.Init.myFlowGraph__ON__silver_definition_core_mkProductionDcl)))); } };

	}

	public static final common.NodeFactory<PmkProductionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmkProductionDcl> {

		@Override
		public PmkProductionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PmkProductionDcl(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
