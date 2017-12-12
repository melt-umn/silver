
package silver.modification.collection;

// top::ProductionStmt ::= dl::Decorated DefLHS attr::Decorated QNameAttrOccur e::Expr 
public final class PinhBaseColAttributeDef extends silver.definition.core.NProductionStmt {

	public static final int i_dl = 0;
	public static final int i_attr = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_collection_inhBaseColAttributeDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PinhBaseColAttributeDef(final Object c_dl, final Object c_attr, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_dl = c_dl;
		this.child_attr = c_attr;
		this.child_e = c_e;

	}

	private Object child_dl;
	public final common.DecoratedNode getChild_dl() {
		return (common.DecoratedNode) (child_dl = common.Util.demand(child_dl));
	}

	private Object child_attr;
	public final common.DecoratedNode getChild_attr() {
		return (common.DecoratedNode) (child_attr = common.Util.demand(child_attr));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dl: return getChild_dl();
			case i_attr: return getChild_attr();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dl: return child_dl;
			case i_attr: return child_attr;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:collection:inhBaseColAttributeDef erroneously claimed to forward");
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
		return "silver:modification:collection:inhBaseColAttributeDef";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " := " ++ e.pp ++ ";"
		silver.modification.collection.PinhBaseColAttributeDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.collection.PinhBaseColAttributeDef.i_dl)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_DefLHS)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.collection.PinhBaseColAttributeDef.i_attr)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" := ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.collection.PinhBaseColAttributeDef.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(";")))))))); } };
		// top.errors := e.errors
		if(silver.modification.collection.PinhBaseColAttributeDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.modification.collection.PinhBaseColAttributeDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.modification.collection.PinhBaseColAttributeDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.collection.PinhBaseColAttributeDef.i_e).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr)); } });
		// errCheck1.finalSubst = top.finalSubst
		silver.modification.collection.PinhBaseColAttributeDef.localInheritedAttributes[silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_inhBaseColAttributeDef][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_ProductionStmt)); } };
		// e.downSubst = top.downSubst
		silver.modification.collection.PinhBaseColAttributeDef.childInheritedAttributes[silver.modification.collection.PinhBaseColAttributeDef.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_ProductionStmt)); } };
		// errCheck1.downSubst = e.upSubst
		silver.modification.collection.PinhBaseColAttributeDef.localInheritedAttributes[silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_inhBaseColAttributeDef][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.collection.PinhBaseColAttributeDef.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// top.upSubst = errCheck1.upSubst
		silver.modification.collection.PinhBaseColAttributeDef.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_inhBaseColAttributeDef).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// errCheck1 = check(attr.typerep, e.typerep)
		silver.modification.collection.PinhBaseColAttributeDef.localAttributes[silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_inhBaseColAttributeDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(context.childAsIsSynthesizedLazy(silver.modification.collection.PinhBaseColAttributeDef.i_attr, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameAttrOccur), context.childDecoratedSynthesizedLazy(silver.modification.collection.PinhBaseColAttributeDef.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr))); } };
		// top.errors <- if errCheck1.typeerror then [ err(top.location, "Attribute " ++ attr.name ++ " has type " ++ errCheck1.leftpp ++ " but the expression being assigned to it has type " ++ errCheck1.rightpp) ] else []
		if(silver.modification.collection.PinhBaseColAttributeDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.modification.collection.PinhBaseColAttributeDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.modification.collection.PinhBaseColAttributeDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_inhBaseColAttributeDef).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Attribute ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.collection.PinhBaseColAttributeDef.i_attr)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" has type ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_inhBaseColAttributeDef).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" but the expression being assigned to it has type ")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.collection.Init.errCheck1__ON__silver_modification_collection_inhBaseColAttributeDef).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });

	}

	public static final common.NodeFactory<PinhBaseColAttributeDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PinhBaseColAttributeDef> {

		@Override
		public PinhBaseColAttributeDef invoke(final Object[] children, final Object[] annotations) {
			return new PinhBaseColAttributeDef(children[0], children[1], children[2], annotations[0]);
		}
	};

}
