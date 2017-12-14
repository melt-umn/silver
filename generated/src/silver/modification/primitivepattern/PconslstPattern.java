
package silver.modification.primitivepattern;

// top::PrimPattern ::= h::Name t::Name e::Expr 
public final class PconslstPattern extends silver.modification.primitivepattern.NPrimPattern {

	public static final int i_h = 0;
	public static final int i_t = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NName.class,silver.definition.core.NName.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_conslstPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPattern.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPattern.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PconslstPattern(final Object c_h, final Object c_t, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;
		this.child_e = c_e;

	}

	private Object child_h;
	public final silver.definition.core.NName getChild_h() {
		return (silver.definition.core.NName) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.definition.core.NName getChild_t() {
		return (silver.definition.core.NName) (child_t = common.Util.demand(child_t));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:primitivepattern:conslstPattern erroneously claimed to forward");
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
		return "silver:modification:primitivepattern:conslstPattern";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "cons(" ++ h.pp ++ ", " ++ t.pp ++ ") -> " ++ e.pp
		silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("cons(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconslstPattern.i_h).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconslstPattern.i_t).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(") -> ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconslstPattern.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr))))))); } };
		// top.errors := e.errors
		if(silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] == null)
			silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern);
		((common.CollectionAttribute)silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.primitivepattern.PconslstPattern.i_e).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr)); } });
		// h_fName = toString(genInt()) ++ ":" ++ h.name
		silver.modification.primitivepattern.PconslstPattern.localAttributes[silver.modification.primitivepattern.Init.h_fName__ON__silver_modification_primitivepattern_conslstPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke()))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconslstPattern.i_h).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// t_fName = toString(genInt()) ++ ":" ++ t.name
		silver.modification.primitivepattern.PconslstPattern.localAttributes[silver.modification.primitivepattern.Init.t_fName__ON__silver_modification_primitivepattern_conslstPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke()))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconslstPattern.i_t).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// errCheck1.finalSubst = top.finalSubst
		silver.modification.primitivepattern.PconslstPattern.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_conslstPattern][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// errCheck2.finalSubst = top.finalSubst
		silver.modification.primitivepattern.PconslstPattern.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_conslstPattern][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// elemType = freshType()
		silver.modification.primitivepattern.PconslstPattern.localAttributes[silver.modification.primitivepattern.Init.elemType__ON__silver_modification_primitivepattern_conslstPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshType.invoke()); } };
		// errCheck1 = check(listType(elemType), top.scrutineeType)
		silver.modification.primitivepattern.PconslstPattern.localAttributes[silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_conslstPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.extension.list.PlistType(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.primitivepattern.Init.elemType__ON__silver_modification_primitivepattern_conslstPattern)))); } }, context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_scrutineeType__ON__silver_modification_primitivepattern_PrimPattern))); } };
		// top.errors <- if errCheck1.typeerror then [ err(top.location, "cons matches lists but we're trying to match against " ++ errCheck1.rightpp) ] else []
		if(silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] == null)
			silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern);
		((common.CollectionAttribute)silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_conslstPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.primitivepattern.NPrimPattern)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("cons matches lists but we're trying to match against ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_conslstPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// errCheck2 = check(e.typerep, top.returnType)
		silver.modification.primitivepattern.PconslstPattern.localAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_conslstPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PconslstPattern.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_returnType__ON__silver_modification_primitivepattern_PrimPattern))); } };
		// top.errors <- if errCheck2.typeerror then [ err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp) ] else []
		if(silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] == null)
			silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern);
		((common.CollectionAttribute)silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_conslstPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.modification.primitivepattern.PconslstPattern.i_e).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("pattern expression should have type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_conslstPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" instead it has type ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_conslstPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// errCheck1.downSubst = top.downSubst
		silver.modification.primitivepattern.PconslstPattern.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_conslstPattern][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// e.downSubst = errCheck1.upSubst
		silver.modification.primitivepattern.PconslstPattern.childInheritedAttributes[silver.modification.primitivepattern.PconslstPattern.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.primitivepattern.Init.errCheck1__ON__silver_modification_primitivepattern_conslstPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// errCheck2.downSubst = e.upSubst
		silver.modification.primitivepattern.PconslstPattern.localInheritedAttributes[silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_conslstPattern][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.primitivepattern.PconslstPattern.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// top.upSubst = errCheck2.upSubst
		silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.primitivepattern.Init.errCheck2__ON__silver_modification_primitivepattern_conslstPattern).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// consdefs = [ lexicalLocalDef(top.grammarName, top.location, h_fName, elemType, noVertex(), []), lexicalLocalDef(top.grammarName, top.location, t_fName, top.scrutineeType, noVertex(), []) ]
		silver.modification.primitivepattern.PconslstPattern.localAttributes[silver.modification.primitivepattern.Init.consdefs__ON__silver_modification_primitivepattern_conslstPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.let_fix.PlexicalLocalDef.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_definition_core_grammarName__ON__silver_modification_primitivepattern_PrimPattern), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.primitivepattern.NPrimPattern)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.modification.primitivepattern.Init.h_fName__ON__silver_modification_primitivepattern_conslstPattern), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.primitivepattern.Init.elemType__ON__silver_modification_primitivepattern_conslstPattern)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NExprVertexInfo)new silver.definition.flow.ast.PnoVertex()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.let_fix.PlexicalLocalDef.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_definition_core_grammarName__ON__silver_modification_primitivepattern_PrimPattern), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.primitivepattern.NPrimPattern)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.modification.primitivepattern.Init.t_fName__ON__silver_modification_primitivepattern_conslstPattern), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_scrutineeType__ON__silver_modification_primitivepattern_PrimPattern), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NExprVertexInfo)new silver.definition.flow.ast.PnoVertex()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };
		// e.env = newScopeEnv(consdefs, top.env)
		silver.modification.primitivepattern.PconslstPattern.childInheritedAttributes[silver.modification.primitivepattern.PconslstPattern.i_e][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.localAsIsLazy(silver.modification.primitivepattern.Init.consdefs__ON__silver_modification_primitivepattern_conslstPattern), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_definition_env_env__ON__silver_modification_primitivepattern_PrimPattern))); } };
		// top.translation = "if(!scrutineeIter.nil()) {" ++ makeSpecialLocalBinding(h_fName, "scrutinee.head()", performSubstitution(elemType, top.finalSubst).transType) ++ makeSpecialLocalBinding(t_fName, "scrutinee.tail()", performSubstitution(top.scrutineeType, top.finalSubst).transType) ++ "return " ++ e.translation ++ "; }"
		silver.modification.primitivepattern.PconslstPattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("if(!scrutineeIter.nil()) {")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.let_fix.java.PmakeSpecialLocalBinding.invoke(context.localAsIsLazy(silver.modification.primitivepattern.Init.h_fName__ON__silver_modification_primitivepattern_conslstPattern), (new common.StringCatter("scrutinee.head()")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.primitivepattern.Init.elemType__ON__silver_modification_primitivepattern_conslstPattern)), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_primitivepattern_PrimPattern))).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.let_fix.java.PmakeSpecialLocalBinding.invoke(context.localAsIsLazy(silver.modification.primitivepattern.Init.t_fName__ON__silver_modification_primitivepattern_conslstPattern), (new common.StringCatter("scrutinee.tail()")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_scrutineeType__ON__silver_modification_primitivepattern_PrimPattern), context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_primitivepattern_PrimPattern))).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("return ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconslstPattern.i_e).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter("; }"))))))); } };

	}

	public static final common.NodeFactory<PconslstPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconslstPattern> {

		@Override
		public PconslstPattern invoke(final Object[] children, final Object[] annotations) {
			return new PconslstPattern(children[0], children[1], children[2], annotations[0]);
		}
	};

}
