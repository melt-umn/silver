
package silver.modification.primitivepattern;

// top::PrimPattern ::= e::Expr 
public final class PnilPattern extends silver.modification.primitivepattern.NPrimPattern {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_nilPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PnilPattern(final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;

	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:primitivepattern:nilPattern erroneously claimed to forward");
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
		return "silver:modification:primitivepattern:nilPattern";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "nil() -> " ++ e.pp
		silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("nil() -> ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PnilPattern.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr))); } };
		// top.errors := e.errors
		if(silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] == null)
			silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern);
		((common.CollectionAttribute)silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.primitivepattern.PnilPattern.i_e).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr)); } });
		// errCheck1.finalSubst = top.finalSubst
		silver.modification.primitivepattern.PnilPattern.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_nilPattern][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// errCheck2.finalSubst = top.finalSubst
		silver.modification.primitivepattern.PnilPattern.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_nilPattern][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// errCheck1 = check(listType(freshType()), top.scrutineeType)
		silver.modification.primitivepattern.PnilPattern.localAttributes[silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_nilPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.extension.list.PlistType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshType.invoke()); } })); } }, context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_scrutineeType__ON__silver_modification_primitivepattern_PrimPattern))); } };
		// top.errors <- if errCheck1.typeerror then [ err(top.location, "nil matches lists but we're trying to match against " ++ errCheck1.rightpp) ] else []
		if(silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] == null)
			silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern);
		((common.CollectionAttribute)silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_nilPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.primitivepattern.NPrimPattern)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("nil matches lists but we're trying to match against ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_nilPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// errCheck2 = check(e.typerep, top.returnType)
		silver.modification.primitivepattern.PnilPattern.localAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_nilPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PnilPattern.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_returnType__ON__silver_modification_primitivepattern_PrimPattern))); } };
		// top.errors <- if errCheck2.typeerror then [ err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp) ] else []
		if(silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] == null)
			silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern);
		((common.CollectionAttribute)silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_nilPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.modification.primitivepattern.PnilPattern.i_e).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("pattern expression should have type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_nilPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" instead it has type ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_nilPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// errCheck1.downSubst = top.downSubst
		silver.modification.primitivepattern.PnilPattern.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_nilPattern][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// e.downSubst = errCheck1.upSubst
		silver.modification.primitivepattern.PnilPattern.childInheritedAttributes[silver.modification.primitivepattern.PnilPattern.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_nilPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// errCheck2.downSubst = e.upSubst
		silver.modification.primitivepattern.PnilPattern.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_nilPattern][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.primitivepattern.PnilPattern.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// top.upSubst = errCheck2.upSubst
		silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_nilPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// top.translation = "if(scrutinee.nil()) { return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++ e.translation ++ "; }"
		silver.modification.primitivepattern.PnilPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("if(scrutinee.nil()) { return (")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_returnType__ON__silver_modification_primitivepattern_PrimPattern), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_primitivepattern_PrimPattern))).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(")")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PnilPattern.i_e).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter("; }")))))); } };

	}

	public static final common.NodeFactory<PnilPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilPattern> {

		@Override
		public PnilPattern invoke(final Object[] children, final Object[] annotations) {
			return new PnilPattern(children[0], annotations[0]);
		}
	};

}
