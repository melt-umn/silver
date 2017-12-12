
package silver.modification.impide;

// top::IdeStmt ::= 'exporter' exporterName::QName ';' 
public final class PmakeIdeStmt_Exporter extends silver.modification.impide.NIdeStmt {

	public static final int i__G_2 = 0;
	public static final int i_exporterName = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.modification.impide.TImpIde_OptFunc_Exporter.class,silver.definition.core.NQName.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_makeIdeStmt_Exporter;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NIdeStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NIdeStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_exporterName] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PmakeIdeStmt_Exporter(final Object c__G_2, final Object c_exporterName, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_exporterName = c_exporterName;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.modification.impide.TImpIde_OptFunc_Exporter getChild__G_2() {
		return (silver.modification.impide.TImpIde_OptFunc_Exporter) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_exporterName;
	public final silver.definition.core.NQName getChild_exporterName() {
		return (silver.definition.core.NQName) (child_exporterName = common.Util.demand(child_exporterName));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_exporterName: return getChild_exporterName();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_exporterName: return child_exporterName;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:makeIdeStmt_Exporter erroneously claimed to forward");
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
		return "silver:modification:impide:makeIdeStmt_Exporter";
	}

	static void initProductionAttributeDefinitions() {
		// top.ideFunctions = [ exporterFunction(exporterName.lookupValue.fullName) ]
		silver.modification.impide.PmakeIdeStmt_Exporter.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.impide.spec.NIdeFunction)new silver.modification.impide.spec.PexporterFunction(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.modification.impide.PmakeIdeStmt_Exporter.i_exporterName).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := exporterName.lookupValue.errors
		if(silver.modification.impide.PmakeIdeStmt_Exporter.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] == null)
			silver.modification.impide.PmakeIdeStmt_Exporter.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt);
		((common.CollectionAttribute)silver.modification.impide.PmakeIdeStmt_Exporter.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.impide.PmakeIdeStmt_Exporter.i_exporterName).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } });
		// expectedType = functionType(t_iomsgs, [ t_proj, t_props, t_io ], [])
		silver.modification.impide.PmakeIdeStmt_Exporter.localAttributes[silver.modification.impide.Init.expectedType__ON__silver_modification_impide_makeIdeStmt_Exporter] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PfunctionType(silver.modification.impide.Init.t_iomsgs, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(silver.modification.impide.Init.t_proj, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(silver.modification.impide.Init.t_props, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(silver.modification.impide.Init.t_io, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// tc1 = check(freshenCompletely(exporterName.lookupValue.typerep), expectedType)
		silver.modification.impide.PmakeIdeStmt_Exporter.localAttributes[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Exporter] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshenCompletely.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.modification.impide.PmakeIdeStmt_Exporter.i_exporterName).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } })); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.impide.Init.expectedType__ON__silver_modification_impide_makeIdeStmt_Exporter)))); } };
		// tc1.downSubst = emptySubst()
		silver.modification.impide.PmakeIdeStmt_Exporter.localInheritedAttributes[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Exporter][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PemptySubst.invoke()); } };
		// tc1.finalSubst = tc1.upSubst
		silver.modification.impide.PmakeIdeStmt_Exporter.localInheritedAttributes[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Exporter][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Exporter).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// top.errors <- if ! tc1.typeerror then [] else [ err(exporterName.location, "Exporter function should have type:\n\t" ++ tc1.rightpp ++ "\nInstead it has the type:\n\t" ++ tc1.leftpp) ]
		if(silver.modification.impide.PmakeIdeStmt_Exporter.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] == null)
			silver.modification.impide.PmakeIdeStmt_Exporter.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt);
		((common.CollectionAttribute)silver.modification.impide.PmakeIdeStmt_Exporter.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.localDecorated(silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Exporter).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.modification.impide.PmakeIdeStmt_Exporter.i_exporterName).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Exporter function should have type:\n\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Exporter).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nInstead it has the type:\n\t")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeIdeStmt_Exporter).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });

	}

	public static final common.NodeFactory<PmakeIdeStmt_Exporter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmakeIdeStmt_Exporter> {

		@Override
		public PmakeIdeStmt_Exporter invoke(final Object[] children, final Object[] annotations) {
			return new PmakeIdeStmt_Exporter(children[0], children[1], children[2], annotations[0]);
		}
	};

}
