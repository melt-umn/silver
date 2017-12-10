
package silver.modification.copper;

// top::ProductionStmt ::= 'pushToken' '(' val::QName ',' lexeme::Expr ')' 'if' condition::Expr ';' 
public final class PpushTokenIfStmt extends silver.definition.core.NProductionStmt {

	public static final int i__G_8 = 0;
	public static final int i__G_7 = 1;
	public static final int i_val = 2;
	public static final int i__G_5 = 3;
	public static final int i_lexeme = 4;
	public static final int i__G_3 = 5;
	public static final int i__G_2 = 6;
	public static final int i_condition = 7;
	public static final int i__G_0 = 8;


	public static final Class<?> childTypes[] = {silver.modification.copper.TPushToken_kwd.class,silver.definition.core.TLParen_t.class,silver.definition.core.NQName.class,silver.definition.core.TComma_t.class,silver.definition.core.NExpr.class,silver.definition.core.TRParen_t.class,silver.definition.core.TIf_kwd.class,silver.definition.core.NExpr.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_pushTokenIfStmt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[9][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_val] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_lexeme] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_condition] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PpushTokenIfStmt(final Object c__G_8, final Object c__G_7, final Object c_val, final Object c__G_5, final Object c_lexeme, final Object c__G_3, final Object c__G_2, final Object c_condition, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_8 = c__G_8;
		this.child__G_7 = c__G_7;
		this.child_val = c_val;
		this.child__G_5 = c__G_5;
		this.child_lexeme = c_lexeme;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_condition = c_condition;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_8;
	public final silver.modification.copper.TPushToken_kwd getChild__G_8() {
		return (silver.modification.copper.TPushToken_kwd) (child__G_8 = common.Util.demand(child__G_8));
	}

	private Object child__G_7;
	public final silver.definition.core.TLParen_t getChild__G_7() {
		return (silver.definition.core.TLParen_t) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child_val;
	public final silver.definition.core.NQName getChild_val() {
		return (silver.definition.core.NQName) (child_val = common.Util.demand(child_val));
	}

	private Object child__G_5;
	public final silver.definition.core.TComma_t getChild__G_5() {
		return (silver.definition.core.TComma_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_lexeme;
	public final silver.definition.core.NExpr getChild_lexeme() {
		return (silver.definition.core.NExpr) (child_lexeme = common.Util.demand(child_lexeme));
	}

	private Object child__G_3;
	public final silver.definition.core.TRParen_t getChild__G_3() {
		return (silver.definition.core.TRParen_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TIf_kwd getChild__G_2() {
		return (silver.definition.core.TIf_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_condition;
	public final silver.definition.core.NExpr getChild_condition() {
		return (silver.definition.core.NExpr) (child_condition = common.Util.demand(child_condition));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_8: return getChild__G_8();
			case i__G_7: return getChild__G_7();
			case i_val: return getChild_val();
			case i__G_5: return getChild__G_5();
			case i_lexeme: return getChild_lexeme();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_condition: return getChild_condition();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_8: return child__G_8;
			case i__G_7: return child__G_7;
			case i_val: return child_val;
			case i__G_5: return child__G_5;
			case i_lexeme: return child_lexeme;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_condition: return child_condition;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 9;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:pushTokenIfStmt erroneously claimed to forward");
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
		return "silver:modification:copper:pushTokenIfStmt";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "\t" ++ "pushToken(" ++ val.pp ++ ", " ++ lexeme.pp ++ ") if " ++ condition.pp ++ ";"
		silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("pushToken(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PpushTokenIfStmt.i_val).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PpushTokenIfStmt.i_lexeme).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(") if ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PpushTokenIfStmt.i_condition).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(";"))))))))); } };
		// top.errors := lexeme.errors ++ condition.errors ++ (if ! top.frame.permitActions then [ err(top.location, "Tokens may only be pushed in action blocks") ] else [])
		if(silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PpushTokenIfStmt.i_lexeme, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PpushTokenIfStmt.i_condition, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((!((Boolean)((silver.definition.core.NBlockContext)context.inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_ProductionStmt)).decorate(context, (common.Lazy[])null).synthesized(silver.modification.copper.Init.silver_modification_copper_permitActions__ON__silver_definition_core_BlockContext))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Tokens may only be pushed in action blocks")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } })); } })); } });
		// top.translation = "if(" ++ condition.translation ++ "){" ++ " pushToken(Terminals." ++ makeCopperName(val.lookupType.fullName) ++ ", (" ++ lexeme.translation ++ ").toString()" ++ ");}"
		silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("if(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PpushTokenIfStmt.i_condition).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("){")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" pushToken(Terminals.")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.modification.copper.PpushTokenIfStmt.i_val).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", (")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PpushTokenIfStmt.i_lexeme).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(").toString()")), (common.StringCatter)(new common.StringCatter(");}")))))))))); } };
		// errCheck1.finalSubst = top.finalSubst
		silver.modification.copper.PpushTokenIfStmt.localInheritedAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_pushTokenIfStmt][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_ProductionStmt)); } };
		// errCheck2.finalSubst = top.finalSubst
		silver.modification.copper.PpushTokenIfStmt.localInheritedAttributes[silver.modification.copper.Init.errCheck2__ON__silver_modification_copper_pushTokenIfStmt][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_ProductionStmt)); } };
		// lexeme.downSubst = top.downSubst
		silver.modification.copper.PpushTokenIfStmt.childInheritedAttributes[silver.modification.copper.PpushTokenIfStmt.i_lexeme][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_ProductionStmt)); } };
		// errCheck1.downSubst = lexeme.upSubst
		silver.modification.copper.PpushTokenIfStmt.localInheritedAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_pushTokenIfStmt][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.copper.PpushTokenIfStmt.i_lexeme).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// condition.downSubst = errCheck1.upSubst
		silver.modification.copper.PpushTokenIfStmt.childInheritedAttributes[silver.modification.copper.PpushTokenIfStmt.i_condition][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_pushTokenIfStmt).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// errCheck2.downSubst = condition.upSubst
		silver.modification.copper.PpushTokenIfStmt.localInheritedAttributes[silver.modification.copper.Init.errCheck2__ON__silver_modification_copper_pushTokenIfStmt][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.copper.PpushTokenIfStmt.i_condition).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// top.upSubst = errCheck2.upSubst
		silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.copper.Init.errCheck2__ON__silver_modification_copper_pushTokenIfStmt).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// errCheck1 = check(lexeme.typerep, stringType())
		silver.modification.copper.PpushTokenIfStmt.localAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_pushTokenIfStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(context.childDecoratedSynthesizedLazy(silver.modification.copper.PpushTokenIfStmt.i_lexeme, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PstringType()); } })); } };
		// top.errors <- if errCheck1.typeerror then [ err(lexeme.location, "Lexeme parameter has type " ++ errCheck1.leftpp ++ " which is not a String") ] else []
		if(silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_pushTokenIfStmt).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.modification.copper.PpushTokenIfStmt.i_lexeme).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Lexeme parameter has type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_pushTokenIfStmt).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)(new common.StringCatter(" which is not a String")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// errCheck2 = check(condition.typerep, boolType())
		silver.modification.copper.PpushTokenIfStmt.localAttributes[silver.modification.copper.Init.errCheck2__ON__silver_modification_copper_pushTokenIfStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(context.childDecoratedSynthesizedLazy(silver.modification.copper.PpushTokenIfStmt.i_condition, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PboolType()); } })); } };
		// top.errors <- if errCheck2.typeerror then [ err(condition.location, "pushToken condition has type " ++ errCheck1.leftpp ++ " which is not a Boolean") ] else []
		if(silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.modification.copper.PpushTokenIfStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.copper.Init.errCheck2__ON__silver_modification_copper_pushTokenIfStmt).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.modification.copper.PpushTokenIfStmt.i_condition).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("pushToken condition has type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_pushTokenIfStmt).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)(new common.StringCatter(" which is not a Boolean")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });

	}

	public static final common.NodeFactory<PpushTokenIfStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpushTokenIfStmt> {

		@Override
		public PpushTokenIfStmt invoke(final Object[] children, final Object[] annotations) {
			return new PpushTokenIfStmt(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], annotations[0]);
		}
	};

}
