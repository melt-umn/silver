
package silver.modification.copper;

// top::ProductionStmt ::= val::Decorated QName e::Expr 
public final class PtermAttrValueValueDef extends silver.definition.core.NProductionStmt {

	public static final int i_val = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_termAttrValueValueDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PtermAttrValueValueDef(final Object c_val, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_val = c_val;
		this.child_e = c_e;

	}

	private Object child_val;
	public final common.DecoratedNode getChild_val() {
		return (common.DecoratedNode) (child_val = common.Util.demand(child_val));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_val: return getChild_val();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_val: return child_val;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:termAttrValueValueDef erroneously claimed to forward");
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
		return "silver:modification:copper:termAttrValueValueDef";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";"
		silver.modification.copper.PtermAttrValueValueDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PtermAttrValueValueDef.i_val)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PtermAttrValueValueDef.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(";")))))); } };
		// top.errors := e.errors
		if(silver.modification.copper.PtermAttrValueValueDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.modification.copper.PtermAttrValueValueDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.modification.copper.PtermAttrValueValueDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PtermAttrValueValueDef.i_e).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr)); } });
		// memberfunc = if val.name == "filename" then "setFileName" else if val.name == "line" then "setLine" else if val.name == "column" then "setColumn" else error("unknown assignment to terminal attribute: " ++ val.name)
		silver.modification.copper.PtermAttrValueValueDef.localAttributes[silver.modification.copper.Init.memberfunc__ON__silver_modification_copper_termAttrValueValueDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PtermAttrValueValueDef.i_val)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)).equals((new common.StringCatter("filename"))) ? (new common.StringCatter("setFileName")) : (((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PtermAttrValueValueDef.i_val)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)).equals((new common.StringCatter("line"))) ? (new common.StringCatter("setLine")) : (((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PtermAttrValueValueDef.i_val)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)).equals((new common.StringCatter("column"))) ? (new common.StringCatter("setColumn")) : ((common.StringCatter)core.Perror.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("unknown assignment to terminal attribute: ")), (common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PtermAttrValueValueDef.i_val)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName))); } }))))); } };
		// top.translation = "virtualLocation." ++ memberfunc ++ "(" ++ e.translation ++ (if val.name == "filename" then ".toString()" else "") ++ ");\n"
		silver.modification.copper.PtermAttrValueValueDef.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("virtualLocation.")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.memberfunc__ON__silver_modification_copper_termAttrValueValueDef)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PtermAttrValueValueDef.i_e).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PtermAttrValueValueDef.i_val)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)).equals((new common.StringCatter("filename"))) ? (new common.StringCatter(".toString()")) : (new common.StringCatter(""))), (common.StringCatter)(new common.StringCatter(");\n"))))))); } };
		// errCheck1.finalSubst = top.finalSubst
		silver.modification.copper.PtermAttrValueValueDef.localInheritedAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_termAttrValueValueDef][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_ProductionStmt)); } };
		// e.downSubst = top.downSubst
		silver.modification.copper.PtermAttrValueValueDef.childInheritedAttributes[silver.modification.copper.PtermAttrValueValueDef.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_ProductionStmt)); } };
		// errCheck1.downSubst = e.upSubst
		silver.modification.copper.PtermAttrValueValueDef.localInheritedAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_termAttrValueValueDef][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.copper.PtermAttrValueValueDef.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// top.upSubst = errCheck1.upSubst
		silver.modification.copper.PtermAttrValueValueDef.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_termAttrValueValueDef).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// errCheck1 = check(e.typerep, val.lookupValue.typerep)
		silver.modification.copper.PtermAttrValueValueDef.localAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_termAttrValueValueDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(context.childDecoratedSynthesizedLazy(silver.modification.copper.PtermAttrValueValueDef.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PtermAttrValueValueDef.i_val)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } })); } };
		// top.errors <- if errCheck1.typeerror then [ err(top.location, "Value " ++ val.name ++ " has type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp) ] else []
		if(silver.modification.copper.PtermAttrValueValueDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.modification.copper.PtermAttrValueValueDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.modification.copper.PtermAttrValueValueDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_termAttrValueValueDef).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Value ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PtermAttrValueValueDef.i_val)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" has type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_termAttrValueValueDef).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" but the expression being assigned to it has type ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_termAttrValueValueDef).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });

	}

	public static final common.NodeFactory<PtermAttrValueValueDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtermAttrValueValueDef> {

		@Override
		public PtermAttrValueValueDef invoke(final Object[] children, final Object[] annotations) {
			return new PtermAttrValueValueDef(children[0], children[1], annotations[0]);
		}
	};

}
