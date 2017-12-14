
package silver.modification.primitivepattern;

// top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr 
public final class PmatchPrimitiveReal extends silver.definition.core.NExpr {

	public static final int i_e = 0;
	public static final int i_t = 1;
	public static final int i_pr = 2;
	public static final int i_f = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,silver.definition.type.syntax.NTypeExpr.class,silver.modification.primitivepattern.NPrimPatterns.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_matchPrimitiveReal;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_pr] = new common.Lazy[silver.modification.primitivepattern.NPrimPatterns.num_inh_attrs];
	childInheritedAttributes[i_f] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PmatchPrimitiveReal(final Object c_e, final Object c_t, final Object c_pr, final Object c_f, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;
		this.child_t = c_t;
		this.child_pr = c_pr;
		this.child_f = c_f;

	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child_pr;
	public final silver.modification.primitivepattern.NPrimPatterns getChild_pr() {
		return (silver.modification.primitivepattern.NPrimPatterns) (child_pr = common.Util.demand(child_pr));
	}

	private Object child_f;
	public final silver.definition.core.NExpr getChild_f() {
		return (silver.definition.core.NExpr) (child_f = common.Util.demand(child_f));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i_t: return getChild_t();
			case i_pr: return getChild_pr();
			case i_f: return getChild_f();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
			case i_t: return child_t;
			case i_pr: return child_pr;
			case i_f: return child_f;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:primitivepattern:matchPrimitiveReal erroneously claimed to forward");
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
		return "silver:modification:primitivepattern:matchPrimitiveReal";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "match " ++ e.pp ++ " return " ++ t.pp ++ " with " ++ pr.pp ++ " else -> " ++ f.pp ++ "end"
		silver.modification.primitivepattern.PmatchPrimitiveReal.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("match ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" return ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" with ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_pr).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPatterns)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" else -> ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_f).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter("end")))))))))); } };
		// top.typerep = t.typerep
		silver.modification.primitivepattern.PmatchPrimitiveReal.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr)); } };
		// top.errors := e.errors ++ t.errors ++ pr.errors ++ f.errors
		if(silver.modification.primitivepattern.PmatchPrimitiveReal.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.modification.primitivepattern.PmatchPrimitiveReal.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.modification.primitivepattern.PmatchPrimitiveReal.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PmatchPrimitiveReal.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PmatchPrimitiveReal.i_t, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PmatchPrimitiveReal.i_pr, silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPatterns), context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PmatchPrimitiveReal.i_f, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr))); } })); } })); } });
		// scrutineeType = performSubstitution(e.typerep, e.upSubst)
		silver.modification.primitivepattern.PmatchPrimitiveReal.localAttributes[silver.modification.primitivepattern.Init.scrutineeType__ON__silver_modification_primitivepattern_matchPrimitiveReal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PmatchPrimitiveReal.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PmatchPrimitiveReal.i_e, silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr))); } };
		// errCheck2.finalSubst = top.finalSubst
		silver.modification.primitivepattern.PmatchPrimitiveReal.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_matchPrimitiveReal][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_Expr)); } };
		// errCheck2 = check(f.typerep, t.typerep)
		silver.modification.primitivepattern.PmatchPrimitiveReal.localAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_matchPrimitiveReal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PmatchPrimitiveReal.i_f, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PmatchPrimitiveReal.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } };
		// top.errors <- if errCheck2.typeerror then [ err(top.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp) ] else []
		if(silver.modification.primitivepattern.PmatchPrimitiveReal.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.modification.primitivepattern.PmatchPrimitiveReal.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.modification.primitivepattern.PmatchPrimitiveReal.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_matchPrimitiveReal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("pattern expression should have type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_matchPrimitiveReal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" instead it has type ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_matchPrimitiveReal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// e.downSubst = top.downSubst
		silver.modification.primitivepattern.PmatchPrimitiveReal.childInheritedAttributes[silver.modification.primitivepattern.PmatchPrimitiveReal.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr)); } };
		// pr.downSubst = e.upSubst
		silver.modification.primitivepattern.PmatchPrimitiveReal.childInheritedAttributes[silver.modification.primitivepattern.PmatchPrimitiveReal.i_pr][silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_primitivepattern_PrimPatterns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// f.downSubst = pr.upSubst
		silver.modification.primitivepattern.PmatchPrimitiveReal.childInheritedAttributes[silver.modification.primitivepattern.PmatchPrimitiveReal.i_f][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_pr).synthesized(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_primitivepattern_PrimPatterns)); } };
		// errCheck2.downSubst = f.upSubst
		silver.modification.primitivepattern.PmatchPrimitiveReal.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_matchPrimitiveReal][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_f).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// top.upSubst = errCheck2.upSubst
		silver.modification.primitivepattern.PmatchPrimitiveReal.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_matchPrimitiveReal).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// pr.scrutineeType = scrutineeType
		silver.modification.primitivepattern.PmatchPrimitiveReal.childInheritedAttributes[silver.modification.primitivepattern.PmatchPrimitiveReal.i_pr][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_scrutineeType__ON__silver_modification_primitivepattern_PrimPatterns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.localDecorated(silver.modification.primitivepattern.Init.scrutineeType__ON__silver_modification_primitivepattern_matchPrimitiveReal).undecorate(); } };
		// pr.returnType = t.typerep
		silver.modification.primitivepattern.PmatchPrimitiveReal.childInheritedAttributes[silver.modification.primitivepattern.PmatchPrimitiveReal.i_pr][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_returnType__ON__silver_modification_primitivepattern_PrimPatterns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr)); } };
		// resultTransType = performSubstitution(t.typerep, top.finalSubst).transType
		silver.modification.primitivepattern.PmatchPrimitiveReal.localAttributes[silver.modification.primitivepattern.Init.resultTransType__ON__silver_modification_primitivepattern_matchPrimitiveReal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PmatchPrimitiveReal.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr), context.contextInheritedLazy(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_Expr))).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)); } };
		// scrutineeFinalType = performSubstitution(scrutineeType, top.finalSubst)
		silver.modification.primitivepattern.PmatchPrimitiveReal.localAttributes[silver.modification.primitivepattern.Init.scrutineeFinalType__ON__silver_modification_primitivepattern_matchPrimitiveReal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.primitivepattern.Init.scrutineeType__ON__silver_modification_primitivepattern_matchPrimitiveReal)), context.contextInheritedLazy(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_Expr))); } };
		// scrutineeTransType = scrutineeFinalType.transType
		silver.modification.primitivepattern.PmatchPrimitiveReal.localAttributes[silver.modification.primitivepattern.Init.scrutineeTransType__ON__silver_modification_primitivepattern_matchPrimitiveReal] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.scrutineeFinalType__ON__silver_modification_primitivepattern_matchPrimitiveReal).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)); } };
		// top.translation = "new common.PatternLazy<" ++ scrutineeTransType ++ ", " ++ resultTransType ++ ">() { " ++ "public final " ++ resultTransType ++ " eval(final common.DecoratedNode context, " ++ scrutineeTransType ++ " scrutineeIter) {" ++ (if scrutineeFinalType.isDecorated then "while(true) {" ++ "final " ++ scrutineeTransType ++ " scrutinee = scrutineeIter; " ++ "final common.Node scrutineeNode = scrutinee.undecorate(); " ++ pr.translation ++ "if(!scrutineeIter.undecorate().hasForward()) break;" ++ "scrutineeIter = scrutineeIter.forward();" ++ "}" else "final " ++ scrutineeTransType ++ " scrutinee = scrutineeIter; " ++ pr.translation) ++ "return " ++ f.translation ++ ";" ++ "}}.eval(context, (" ++ scrutineeTransType ++ ")" ++ e.translation ++ ")"
		silver.modification.primitivepattern.PmatchPrimitiveReal.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("new common.PatternLazy<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.primitivepattern.Init.scrutineeTransType__ON__silver_modification_primitivepattern_matchPrimitiveReal)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.primitivepattern.Init.resultTransType__ON__silver_modification_primitivepattern_matchPrimitiveReal)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(">() { ")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("public final ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.primitivepattern.Init.resultTransType__ON__silver_modification_primitivepattern_matchPrimitiveReal)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" eval(final common.DecoratedNode context, ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.primitivepattern.Init.scrutineeTransType__ON__silver_modification_primitivepattern_matchPrimitiveReal)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" scrutineeIter) {")), (common.StringCatter)new common.StringCatter((common.StringCatter)(((Boolean)context.localDecorated(silver.modification.primitivepattern.Init.scrutineeFinalType__ON__silver_modification_primitivepattern_matchPrimitiveReal).synthesized(silver.definition.type.Init.silver_definition_type_isDecorated__ON__silver_definition_type_Type)) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("while(true) {")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("final ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.primitivepattern.Init.scrutineeTransType__ON__silver_modification_primitivepattern_matchPrimitiveReal)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" scrutinee = scrutineeIter; ")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("final common.Node scrutineeNode = scrutinee.undecorate(); ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_pr).synthesized(silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_PrimPatterns)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("if(!scrutineeIter.undecorate().hasForward()) break;")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("scrutineeIter = scrutineeIter.forward();")), (common.StringCatter)(new common.StringCatter("}")))))))))) : new common.StringCatter((common.StringCatter)(new common.StringCatter("final ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.primitivepattern.Init.scrutineeTransType__ON__silver_modification_primitivepattern_matchPrimitiveReal)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" scrutinee = scrutineeIter; ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_pr).synthesized(silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_PrimPatterns)))))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("return ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_f).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("}}.eval(context, (")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.primitivepattern.Init.scrutineeTransType__ON__silver_modification_primitivepattern_matchPrimitiveReal)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(")")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PmatchPrimitiveReal.i_e).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(")")))))))))))))))))))); } };
		// top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication)
		silver.modification.primitivepattern.PmatchPrimitiveReal.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_lazyTranslation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PwrapThunk.invoke(context.contextSynthesizedLazy(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)((silver.definition.core.NBlockContext)context.inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_Expr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_lazyApplication__ON__silver_definition_core_BlockContext)); } })); } };

	}

	public static final common.NodeFactory<PmatchPrimitiveReal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmatchPrimitiveReal> {

		@Override
		public PmatchPrimitiveReal invoke(final Object[] children, final Object[] annotations) {
			return new PmatchPrimitiveReal(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
