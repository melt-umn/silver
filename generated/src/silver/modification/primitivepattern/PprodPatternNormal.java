
package silver.modification.primitivepattern;

// top::PrimPattern ::= qn::Decorated QName ns::VarBinders e::Expr 
public final class PprodPatternNormal extends silver.modification.primitivepattern.NPrimPattern {

	public static final int i_qn = 0;
	public static final int i_ns = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,silver.modification.primitivepattern.NVarBinders.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_prodPatternNormal;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ns] = new common.Lazy[silver.modification.primitivepattern.NVarBinders.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PprodPatternNormal(final Object c_qn, final Object c_ns, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_qn = c_qn;
		this.child_ns = c_ns;
		this.child_e = c_e;

	}

	private Object child_qn;
	public final common.DecoratedNode getChild_qn() {
		return (common.DecoratedNode) (child_qn = common.Util.demand(child_qn));
	}

	private Object child_ns;
	public final silver.modification.primitivepattern.NVarBinders getChild_ns() {
		return (silver.modification.primitivepattern.NVarBinders) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();
			case i_ns: return getChild_ns();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;
			case i_ns: return child_ns;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:primitivepattern:prodPatternNormal erroneously claimed to forward");
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
		return "silver:modification:primitivepattern:prodPatternNormal";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = qn.pp ++ "(" ++ ns.pp ++ ") -> " ++ e.pp
		silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PprodPatternNormal.i_ns).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_VarBinders)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(") -> ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PprodPatternNormal.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)))))); } };
		// chk = if null(qn.lookupValue.dcls) || ns.varBinderCount == length(prod_type.inputTypes) then [] else [ err(qn.location, qn.pp ++ " has " ++ toString(length(prod_type.inputTypes)) ++ " parameters but " ++ toString(ns.varBinderCount) ++ " patterns were provided") ]
		silver.modification.primitivepattern.PprodPatternNormal.localAttributes[silver.modification.primitivepattern.Init.chk__ON__silver_modification_primitivepattern_prodPatternNormal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) || ((Integer)context.childDecorated(silver.modification.primitivepattern.PprodPatternNormal.i_ns).synthesized(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_varBinderCount__ON__silver_modification_primitivepattern_VarBinders)).equals(((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.primitivepattern.Init.prod_type__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.definition.type.Init.silver_definition_type_inputTypes__ON__silver_definition_type_Type)); } })))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" has ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.primitivepattern.Init.prod_type__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.definition.type.Init.silver_definition_type_inputTypes__ON__silver_definition_type_Type)); } })))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" parameters but ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childDecorated(silver.modification.primitivepattern.PprodPatternNormal.i_ns).synthesized(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_varBinderCount__ON__silver_modification_primitivepattern_VarBinders)))), (common.StringCatter)(new common.StringCatter(" patterns were provided"))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } };
		// top.errors := qn.lookupValue.errors ++ ns.errors ++ chk ++ e.errors
		if(silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] == null)
			silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern);
		((common.CollectionAttribute)silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PprodPatternNormal.i_ns, silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.localAsIsLazy(silver.modification.primitivepattern.Init.chk__ON__silver_modification_primitivepattern_prodPatternNormal), context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PprodPatternNormal.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr))); } })); } })); } });
		// prod_type = skolemizeProductionType(qn.lookupValue.typerep)
		silver.modification.primitivepattern.PprodPatternNormal.localAttributes[silver.modification.primitivepattern.Init.prod_type__ON__silver_modification_primitivepattern_prodPatternNormal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.modification.primitivepattern.PskolemizeProductionType.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } })); } };
		// ns.bindingTypes = prod_type.inputTypes
		silver.modification.primitivepattern.PprodPatternNormal.childInheritedAttributes[silver.modification.primitivepattern.PprodPatternNormal.i_ns][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingTypes__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.primitivepattern.Init.prod_type__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.definition.type.Init.silver_definition_type_inputTypes__ON__silver_definition_type_Type)); } };
		// ns.bindingIndex = 0
		silver.modification.primitivepattern.PprodPatternNormal.childInheritedAttributes[silver.modification.primitivepattern.PprodPatternNormal.i_ns][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingIndex__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// ns.bindingNames = if null(qn.lookupValue.dcls) then [] else qn.lookupValue.dcl.namedSignature.inputNames
		silver.modification.primitivepattern.PprodPatternNormal.childInheritedAttributes[silver.modification.primitivepattern.PprodPatternNormal.i_ns][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingNames__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)((silver.definition.env.NNamedSignature)((silver.definition.env.NDclInfo)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_namedSignature__ON__silver_definition_env_DclInfo)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_inputNames__ON__silver_definition_env_NamedSignature))); } };
		// ns.matchingAgainst = if null(qn.lookupValue.dcls) then nothing() else just(qn.lookupValue.dcl)
		silver.modification.primitivepattern.PprodPatternNormal.childInheritedAttributes[silver.modification.primitivepattern.PprodPatternNormal.i_ns][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_matchingAgainst__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) ? ((core.NMaybe)new core.Pnothing()) : ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)); } }))); } };
		// errCheck1.finalSubst = top.finalSubst
		silver.modification.primitivepattern.PprodPatternNormal.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_prodPatternNormal][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// errCheck2.finalSubst = top.finalSubst
		silver.modification.primitivepattern.PprodPatternNormal.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_prodPatternNormal][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// errCheck1 = check(decoratedType(prod_type.outputType), top.scrutineeType)
		silver.modification.primitivepattern.PprodPatternNormal.localAttributes[silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_prodPatternNormal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PdecoratedType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)context.localDecorated(silver.modification.primitivepattern.Init.prod_type__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.definition.type.Init.silver_definition_type_outputType__ON__silver_definition_type_Type)); } })); } }, context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_scrutineeType__ON__silver_modification_primitivepattern_PrimPattern))); } };
		// top.errors <- if errCheck1.typeerror then [ err(top.location, qn.pp ++ " has type " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp) ] else []
		if(silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] == null)
			silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern);
		((common.CollectionAttribute)silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.primitivepattern.NPrimPattern)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" has type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" but we're trying to match against ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck)))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// errCheck2 = check(e.typerep, top.returnType)
		silver.modification.primitivepattern.PprodPatternNormal.localAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_prodPatternNormal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PprodPatternNormal.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_returnType__ON__silver_modification_primitivepattern_PrimPattern))); } };
		// top.errors <- if errCheck2.typeerror then [ err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp) ] else []
		if(silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] == null)
			silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern);
		((common.CollectionAttribute)silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.modification.primitivepattern.PprodPatternNormal.i_e).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("pattern expression should have type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" instead it has type ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// errCheck1.downSubst = top.downSubst
		silver.modification.primitivepattern.PprodPatternNormal.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_prodPatternNormal][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// e.downSubst = errCheck1.upSubst
		silver.modification.primitivepattern.PprodPatternNormal.childInheritedAttributes[silver.modification.primitivepattern.PprodPatternNormal.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// errCheck2.downSubst = e.upSubst
		silver.modification.primitivepattern.PprodPatternNormal.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_prodPatternNormal][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.primitivepattern.PprodPatternNormal.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// top.upSubst = errCheck2.upSubst
		silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_prodPatternNormal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// e.env = newScopeEnv(ns.defs, top.env)
		silver.modification.primitivepattern.PprodPatternNormal.childInheritedAttributes[silver.modification.primitivepattern.PprodPatternNormal.i_e][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PprodPatternNormal.i_ns, silver.modification.primitivepattern.Init.silver_definition_env_defs__ON__silver_modification_primitivepattern_VarBinders), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_definition_env_env__ON__silver_modification_primitivepattern_PrimPattern))); } };
		// top.translation = "if(scrutineeNode instanceof " ++ makeClassName(qn.lookupValue.fullName) ++ ") { " ++ ns.translation ++ " return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++ e.translation ++ "; }"
		silver.modification.primitivepattern.PprodPatternNormal.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("if(scrutineeNode instanceof ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeClassName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.primitivepattern.PprodPatternNormal.i_qn)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(") { ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PprodPatternNormal.i_ns).synthesized(silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_VarBinders)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" return (")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_returnType__ON__silver_modification_primitivepattern_PrimPattern), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_primitivepattern_PrimPattern))).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(")")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PprodPatternNormal.i_e).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter("; }")))))))))); } };

	}

	public static final common.NodeFactory<PprodPatternNormal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprodPatternNormal> {

		@Override
		public PprodPatternNormal invoke(final Object[] children, final Object[] annotations) {
			return new PprodPatternNormal(children[0], children[1], children[2], annotations[0]);
		}
	};

}
