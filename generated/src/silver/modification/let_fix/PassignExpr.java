
package silver.modification.let_fix;

// top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr 
public final class PassignExpr extends silver.modification.let_fix.NAssignExpr {

	public static final int i_id = 0;
	public static final int i__G_3 = 1;
	public static final int i_t = 2;
	public static final int i__G_1 = 3;
	public static final int i_e = 4;


	public static final Class<?> childTypes[] = {silver.definition.core.NName.class,silver.definition.core.TColonColon_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TEqual_t.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_let_fix_assignExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.let_fix.NAssignExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.let_fix.NAssignExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PassignExpr(final Object c_id, final Object c__G_3, final Object c_t, final Object c__G_1, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_id = c_id;
		this.child__G_3 = c__G_3;
		this.child_t = c_t;
		this.child__G_1 = c__G_1;
		this.child_e = c_e;

	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child__G_3;
	public final silver.definition.core.TColonColon_t getChild__G_3() {
		return (silver.definition.core.TColonColon_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_1;
	public final silver.definition.core.TEqual_t getChild__G_1() {
		return (silver.definition.core.TEqual_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();
			case i__G_3: return getChild__G_3();
			case i_t: return getChild_t();
			case i__G_1: return getChild__G_1();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;
			case i__G_3: return child__G_3;
			case i_t: return child_t;
			case i__G_1: return child__G_1;
			case i_e: return child_e;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:let_fix:assignExpr erroneously claimed to forward");
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
		return "silver:modification:let_fix:assignExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = id.pp ++ " :: " ++ t.pp ++ " = " ++ e.pp
		silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_AssignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PassignExpr.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" :: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PassignExpr.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" = ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PassignExpr.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)))))); } };
		// top.errors := t.errors ++ e.errors
		if(silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr] == null)
			silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr] = new silver.definition.core.CAerrors(silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr);
		((common.CollectionAttribute)silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PassignExpr.i_t, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr), context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PassignExpr.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr))); } });
		// finalTy = performSubstitution(t.typerep, errCheck1.upSubst)
		silver.modification.let_fix.PassignExpr.localAttributes[silver.modification.let_fix.Init.finalTy__ON__silver_modification_let_fix_assignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PassignExpr.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.let_fix.Init.errCheck1__ON__silver_modification_let_fix_assignExpr).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } })); } };
		// fName = toString(genInt()) ++ ":" ++ id.name
		silver.modification.let_fix.PassignExpr.localAttributes[silver.modification.let_fix.Init.fName__ON__silver_modification_let_fix_assignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke()))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PassignExpr.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// top.defs = [ lexicalLocalDef(top.grammarName, id.location, fName, finalTy, e.flowVertexInfo, e.flowDeps) ]
		silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_env_defs__ON__silver_modification_let_fix_AssignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.let_fix.PlexicalLocalDef.invoke(context.contextInheritedLazy(silver.modification.let_fix.Init.silver_definition_core_grammarName__ON__silver_modification_let_fix_AssignExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.let_fix.PassignExpr.i_id).undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.modification.let_fix.Init.fName__ON__silver_modification_let_fix_assignExpr), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.let_fix.Init.finalTy__ON__silver_modification_let_fix_assignExpr)), context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PassignExpr.i_e, silver.definition.flow.env.Init.silver_definition_flow_env_flowVertexInfo__ON__silver_definition_core_Expr), context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PassignExpr.i_e, silver.definition.flow.env.Init.silver_definition_flow_env_flowDeps__ON__silver_definition_core_Expr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors <- if length(getValueDclInScope(id.name, top.env)) > 1 then [ err(id.location, "Value '" ++ id.name ++ "' is already bound.") ] else []
		if(silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr] == null)
			silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr] = new silver.definition.core.CAerrors(silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr);
		((common.CollectionAttribute)silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetValueDclInScope.invoke(context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PassignExpr.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.contextInheritedLazy(silver.modification.let_fix.Init.silver_definition_env_env__ON__silver_modification_let_fix_AssignExpr))); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.let_fix.PassignExpr.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Value '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PassignExpr.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)(new common.StringCatter("' is already bound.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// e.downSubst = top.downSubst
		silver.modification.let_fix.PassignExpr.childInheritedAttributes[silver.modification.let_fix.PassignExpr.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.let_fix.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_let_fix_AssignExpr)); } };
		// errCheck1.downSubst = e.upSubst
		silver.modification.let_fix.PassignExpr.localInheritedAttributes[silver.modification.let_fix.Init.errCheck1__ON__silver_modification_let_fix_assignExpr][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.let_fix.PassignExpr.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// top.upSubst = errCheck1.upSubst
		silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_let_fix_AssignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.let_fix.Init.errCheck1__ON__silver_modification_let_fix_assignExpr).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// errCheck1.finalSubst = top.finalSubst
		silver.modification.let_fix.PassignExpr.localInheritedAttributes[silver.modification.let_fix.Init.errCheck1__ON__silver_modification_let_fix_assignExpr][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.let_fix.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_modification_let_fix_AssignExpr)); } };
		// errCheck1 = check(e.typerep, t.typerep)
		silver.modification.let_fix.PassignExpr.localAttributes[silver.modification.let_fix.Init.errCheck1__ON__silver_modification_let_fix_assignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PassignExpr.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PassignExpr.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } };
		// top.errors <- if errCheck1.typeerror then [ err(id.location, "Value " ++ id.name ++ " declared with type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp) ] else []
		if(silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr] == null)
			silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr] = new silver.definition.core.CAerrors(silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr);
		((common.CollectionAttribute)silver.modification.let_fix.PassignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.let_fix.Init.errCheck1__ON__silver_modification_let_fix_assignExpr).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.let_fix.PassignExpr.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Value ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PassignExpr.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" declared with type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.let_fix.Init.errCheck1__ON__silver_modification_let_fix_assignExpr).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" but the expression being assigned to it has type ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.let_fix.Init.errCheck1__ON__silver_modification_let_fix_assignExpr).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });

	}

	public static final common.NodeFactory<PassignExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PassignExpr> {

		@Override
		public PassignExpr invoke(final Object[] children, final Object[] annotations) {
			return new PassignExpr(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
