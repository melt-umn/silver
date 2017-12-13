
package silver.modification.copper;

// top::ProductionStmt ::= 'print' e::Expr ';' 
public final class PprintStmt extends silver.definition.core.NProductionStmt {

	public static final int i__G_2 = 0;
	public static final int i_e = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.modification.copper.TPrint_kwd.class,silver.definition.core.NExpr.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_printStmt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PprintStmt(final Object c__G_2, final Object c_e, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_e = c_e;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.modification.copper.TPrint_kwd getChild__G_2() {
		return (silver.modification.copper.TPrint_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_e: return getChild_e();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_e: return child_e;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:printStmt erroneously claimed to forward");
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
		return "silver:modification:copper:printStmt";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "print " ++ e.pp ++ ";"
		silver.modification.copper.PprintStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("print ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PprintStmt.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(";")))); } };
		// top.translation = "System.err.println(" ++ e.translation ++ ");\n"
		silver.modification.copper.PprintStmt.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("System.err.println(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PprintStmt.i_e).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(");\n")))); } };
		// top.errors := (if ! top.frame.permitActions then [ err(top.location, "'print' statement allowed only in parser action blocks. You may be looking for print(String,IO) :: IO.") ] else []) ++ e.errors
		if(silver.modification.copper.PprintStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.modification.copper.PprintStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.modification.copper.PprintStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((!((Boolean)((silver.definition.core.NBlockContext)context.inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_ProductionStmt)).decorate(context, (common.Lazy[])null).synthesized(silver.modification.copper.Init.silver_modification_copper_permitActions__ON__silver_definition_core_BlockContext))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("'print' statement allowed only in parser action blocks. You may be looking for print(String,IO) :: IO.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper.PprintStmt.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr))); } });
		// errCheck1.finalSubst = top.finalSubst
		silver.modification.copper.PprintStmt.localInheritedAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_printStmt][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_ProductionStmt)); } };
		// e.downSubst = top.downSubst
		silver.modification.copper.PprintStmt.childInheritedAttributes[silver.modification.copper.PprintStmt.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_ProductionStmt)); } };
		// errCheck1.downSubst = e.upSubst
		silver.modification.copper.PprintStmt.localInheritedAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_printStmt][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.copper.PprintStmt.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// top.upSubst = errCheck1.upSubst
		silver.modification.copper.PprintStmt.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_printStmt).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// errCheck1 = check(e.typerep, stringType())
		silver.modification.copper.PprintStmt.localAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_printStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(context.childDecoratedSynthesizedLazy(silver.modification.copper.PprintStmt.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PstringType()); } })); } };
		// top.errors <- if errCheck1.typeerror then [ err(e.location, "print expects a string, instead it recieved a " ++ errCheck1.leftpp) ] else []
		if(silver.modification.copper.PprintStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.modification.copper.PprintStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.modification.copper.PprintStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_printStmt).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.modification.copper.PprintStmt.i_e).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("print expects a string, instead it recieved a ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_printStmt).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });

	}

	public static final common.NodeFactory<PprintStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprintStmt> {

		@Override
		public PprintStmt invoke(final Object[] children, final Object[] annotations) {
			return new PprintStmt(children[0], children[1], children[2], annotations[0]);
		}
	};

}
