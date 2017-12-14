
package silver.extension.bidirtransform;

// pd::ProductionDef ::= qn::QName '(' args::PatternList ')' 
public final class PproductionDef extends silver.extension.bidirtransform.NProductionDef {

	public static final int i_qn = 0;
	public static final int i__G_2 = 1;
	public static final int i_args = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TLParen_t.class,silver.extension.patternmatching.NPatternList.class,silver.definition.core.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_productionDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NProductionDef.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NProductionDef.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_args] = new common.Lazy[silver.extension.patternmatching.NPatternList.num_inh_attrs];

	}

	public PproductionDef(final Object c_qn, final Object c__G_2, final Object c_args, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_qn = c_qn;
		this.child__G_2 = c__G_2;
		this.child_args = c_args;
		this.child__G_0 = c__G_0;

	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child__G_2;
	public final silver.definition.core.TLParen_t getChild__G_2() {
		return (silver.definition.core.TLParen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_args;
	public final silver.extension.patternmatching.NPatternList getChild_args() {
		return (silver.extension.patternmatching.NPatternList) (child_args = common.Util.demand(child_args));
	}

	private Object child__G_0;
	public final silver.definition.core.TRParen_t getChild__G_0() {
		return (silver.definition.core.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();
			case i__G_2: return getChild__G_2();
			case i_args: return getChild_args();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;
			case i__G_2: return child__G_2;
			case i_args: return child_args;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:productionDef erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:productionDef";
	}

	static void initProductionAttributeDefinitions() {
		// pd.errors := []
		if(silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef] == null)
			silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef);
		((common.CollectionAttribute)silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// pd.pp = qn.pp ++ "(" ++ args.pp ++ ")"
		silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_ProductionDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PproductionDef.i_qn).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PproductionDef.i_args).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_PatternList)), (common.StringCatter)(new common.StringCatter(")"))))); } };
		// pd.patternList = args
		silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_patternList__ON__silver_extension_bidirtransform_ProductionDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.bidirtransform.PproductionDef.i_args).undecorate(); } };
		// prodNames = map(unFull, map((.fullName), pd.inhProds))
		silver.extension.bidirtransform.PproductionDef.localAttributes[silver.extension.bidirtransform.Init.prodNames__ON__silver_extension_bidirtransform_productionDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.extension.bidirtransform.PunFull.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature), context.contextInheritedLazy(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inhProds__ON__silver_extension_bidirtransform_ProductionDef))); } })); } };
		// idx = findIdx(prodNames, qn.name)
		silver.extension.bidirtransform.PproductionDef.localAttributes[silver.extension.bidirtransform.Init.idx__ON__silver_extension_bidirtransform_productionDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)silver.extension.bidirtransform.PfindIdx.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.prodNames__ON__silver_extension_bidirtransform_productionDef), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PproductionDef.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName))); } };
		// pd.namedSig = idxOf(pd.inhProds, idx)
		silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_ProductionDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.extension.bidirtransform.PidxOf.invoke(context.contextInheritedLazy(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inhProds__ON__silver_extension_bidirtransform_ProductionDef), context.localAsIsLazy(silver.extension.bidirtransform.Init.idx__ON__silver_extension_bidirtransform_productionDef))); } };
		// pd.matchProd = matchProd(args.rawPatternList, pd.namedSig.inputElements,location=pd.location)
		silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_matchProd__ON__silver_extension_bidirtransform_ProductionDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PmatchProd(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PproductionDef.i_args, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rawPatternList__ON__silver_extension_patternmatching_PatternList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_ProductionDef)).synthesized(silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.bidirtransform.NProductionDef)context.undecorate()).getAnno_core_location()); } })); } };
		// pd.typerep = pd.namedSig.outputElement.typerep
		silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_typerep__ON__silver_extension_bidirtransform_ProductionDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)context.synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_ProductionDef)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)); } };
		// pd.errors <- if idx != -1 then [] else [ err(pd.location, "Unknown Production " ++ qn.name) ]
		if(silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef] == null)
			silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef);
		((common.CollectionAttribute)silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (!((Integer)context.localAsIs(silver.extension.bidirtransform.Init.idx__ON__silver_extension_bidirtransform_productionDef)).equals(Integer.valueOf((int)-1)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.bidirtransform.NProductionDef)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Unknown Production ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PproductionDef.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// pd.errors <- if idx == -1 then [] else if length(pd.namedSig.inputElements) != length(args.rawPatternList) then [ err(pd.location, "Transformation Production does not match size with Production Signature") ] else []
		if(silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef] == null)
			silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef);
		((common.CollectionAttribute)silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Integer)context.localAsIs(silver.extension.bidirtransform.Init.idx__ON__silver_extension_bidirtransform_productionDef)).equals(Integer.valueOf((int)-1)) ? ((common.ConsCell)core.Pnil.invoke()) : (!((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_ProductionDef)).synthesized(silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature)); } })).equals(((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PproductionDef.i_args, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rawPatternList__ON__silver_extension_patternmatching_PatternList)))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.bidirtransform.NProductionDef)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Transformation Production does not match size with Production Signature")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke()))); } });
		// pd.errors <- tyCheckProd(pd.location, args.rawPatternList, pd.namedSig.inputElements)
		if(silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef] == null)
			silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef);
		((common.CollectionAttribute)silver.extension.bidirtransform.PproductionDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PtyCheckProd.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.bidirtransform.NProductionDef)context.undecorate()).getAnno_core_location()); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PproductionDef.i_args, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rawPatternList__ON__silver_extension_patternmatching_PatternList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_ProductionDef)).synthesized(silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature)); } })); } });

	}

	public static final common.NodeFactory<PproductionDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionDef> {

		@Override
		public PproductionDef invoke(final Object[] children, final Object[] annotations) {
			return new PproductionDef(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
