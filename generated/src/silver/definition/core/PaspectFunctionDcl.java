
package silver.definition.core;

// top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
public final class PaspectFunctionDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_4 = 0;
	public static final int i__G_3 = 1;
	public static final int i_id = 2;
	public static final int i_ns = 3;
	public static final int i_body = 4;


	public static final Class<?> childTypes[] = {silver.definition.core.TAspect_kwd.class,silver.definition.core.TFunction_kwd.class,silver.definition.core.NQName.class,silver.definition.core.NAspectFunctionSignature.class,silver.definition.core.NProductionBody.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_aspectFunctionDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NAspectFunctionSignature.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];

	}

	public PaspectFunctionDcl(final Object c__G_4, final Object c__G_3, final Object c_id, final Object c_ns, final Object c_body, final Object a_core_location) {
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
	public final silver.definition.core.TFunction_kwd getChild__G_3() {
		return (silver.definition.core.TFunction_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_id;
	public final silver.definition.core.NQName getChild_id() {
		return (silver.definition.core.NQName) (child_id = common.Util.demand(child_id));
	}

	private Object child_ns;
	public final silver.definition.core.NAspectFunctionSignature getChild_ns() {
		return (silver.definition.core.NAspectFunctionSignature) (child_ns = common.Util.demand(child_ns));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:aspectFunctionDcl erroneously claimed to forward");
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
		return "silver:definition:core:aspectFunctionDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "aspect function " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp
		silver.definition.core.PaspectFunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("aspect function ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectFunctionSignature)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_body).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionBody))))))); } };
		// top.defs = if null(body.productionAttributes) then [] else [ prodOccursDef(top.grammarName, id.location, namedSig, body.productionAttributes) ]
		silver.definition.core.PaspectFunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectFunctionDcl.i_body, silver.definition.core.Init.silver_definition_core_productionAttributes__ON__silver_definition_core_ProductionBody))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PprodOccursDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_id).undecorate()).getAnno_core_location()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.namedSig__ON__silver_definition_core_aspectFunctionDcl)), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectFunctionDcl.i_body, silver.definition.core.Init.silver_definition_core_productionAttributes__ON__silver_definition_core_ProductionBody))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } };
		// namedSig = ns.namedSignature
		silver.definition.core.PaspectFunctionDcl.localAttributes[silver.definition.core.Init.namedSig__ON__silver_definition_core_aspectFunctionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_AspectFunctionSignature)); } };
		// realSig = if null(id.lookupValue.errors) then id.lookupValue.dcl.namedSignature else bogusNamedSignature()
		silver.definition.core.PaspectFunctionDcl.localAttributes[silver.definition.core.Init.realSig__ON__silver_definition_core_aspectFunctionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } })) ? ((silver.definition.env.NNamedSignature)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_namedSignature__ON__silver_definition_env_DclInfo)) : ((silver.definition.env.NNamedSignature)new silver.definition.env.PbogusNamedSignature())); } };
		// top.errors := id.lookupValue.errors ++ ns.errors ++ body.errors
		if(silver.definition.core.PaspectFunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.core.PaspectFunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.core.PaspectFunctionDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectFunctionDcl.i_ns, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectFunctionSignature), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectFunctionDcl.i_body, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionBody))); } })); } });
		// sigDefs := ns.defs
		((common.CollectionAttribute)silver.definition.core.PaspectFunctionDcl.localAttributes[silver.definition.core.Init.sigDefs__ON__silver_definition_core_aspectFunctionDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AspectFunctionSignature)); } });
		// ns.signatureName = id.lookupValue.fullName
		silver.definition.core.PaspectFunctionDcl.childInheritedAttributes[silver.definition.core.PaspectFunctionDcl.i_ns][silver.definition.core.Init.silver_definition_core_signatureName__ON__silver_definition_core_AspectFunctionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } };
		// ns.env = newScopeEnv(sigDefs, top.env)
		silver.definition.core.PaspectFunctionDcl.childInheritedAttributes[silver.definition.core.PaspectFunctionDcl.i_ns][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AspectFunctionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.localAsIsLazy(silver.definition.core.Init.sigDefs__ON__silver_definition_core_aspectFunctionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// ns.realSignature = if null(id.lookupValue.dcls) then [] else [ realSig.outputElement ] ++ realSig.inputElements
		silver.definition.core.PaspectFunctionDcl.childInheritedAttributes[silver.definition.core.PaspectFunctionDcl.i_ns][silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectFunctionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)context.localDecorated(silver.definition.core.Init.realSig__ON__silver_definition_core_aspectFunctionDcl).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.realSig__ON__silver_definition_core_aspectFunctionDcl).synthesized(silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature)); } }))); } };
		// prodAtts = if null(id.lookupValue.errors) then defsFromPADcls(getProdAttrs(id.lookupValue.fullName, top.env), namedSig) else []
		silver.definition.core.PaspectFunctionDcl.localAttributes[silver.definition.core.Init.prodAtts__ON__silver_definition_core_aspectFunctionDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)silver.definition.env.PdefsFromPADcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetProdAttrs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_id).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.namedSig__ON__silver_definition_core_aspectFunctionDcl)))) : ((common.ConsCell)core.Pnil.invoke())); } };
		// body.env = newScopeEnv(body.defs ++ sigDefs, newScopeEnv(prodAtts, top.env))
		silver.definition.core.PaspectFunctionDcl.childInheritedAttributes[silver.definition.core.PaspectFunctionDcl.i_body][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectFunctionDcl.i_body, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionBody), context.localAsIsLazy(silver.definition.core.Init.sigDefs__ON__silver_definition_core_aspectFunctionDcl))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.localAsIsLazy(silver.definition.core.Init.prodAtts__ON__silver_definition_core_aspectFunctionDcl), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })); } };
		// body.frame = aspectFunctionContext(namedSig, myFlowGraph)
		silver.definition.core.PaspectFunctionDcl.childInheritedAttributes[silver.definition.core.PaspectFunctionDcl.i_body][silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NBlockContext)new silver.definition.core.PaspectFunctionContext(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.namedSig__ON__silver_definition_core_aspectFunctionDcl)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.flow.env.Init.myFlowGraph__ON__silver_definition_core_aspectFunctionDcl)))); } };

	}

	public static final common.NodeFactory<PaspectFunctionDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaspectFunctionDcl> {

		@Override
		public PaspectFunctionDcl invoke(final Object[] children, final Object[] annotations) {
			return new PaspectFunctionDcl(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
