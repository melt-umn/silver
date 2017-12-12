
package silver.modification.impide;

// top::StubGenerator ::= 'stub generator' genName::QName ';' 
public final class PmakeStubGenerator extends silver.modification.impide.NStubGenerator {

	public static final int i__G_2 = 0;
	public static final int i_genName = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.modification.impide.TImpIde_Wizard_StubGen.class,silver.definition.core.NQName.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_makeStubGenerator;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NStubGenerator.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NStubGenerator.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_genName] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PmakeStubGenerator(final Object c__G_2, final Object c_genName, final Object c__G_0) {
		super();
		this.child__G_2 = c__G_2;
		this.child_genName = c_genName;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.modification.impide.TImpIde_Wizard_StubGen getChild__G_2() {
		return (silver.modification.impide.TImpIde_Wizard_StubGen) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_genName;
	public final silver.definition.core.NQName getChild_genName() {
		return (silver.definition.core.NQName) (child_genName = common.Util.demand(child_genName));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_genName: return getChild_genName();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_genName: return child_genName;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:makeStubGenerator erroneously claimed to forward");
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
		return "silver:modification:impide:makeStubGenerator";
	}

	static void initProductionAttributeDefinitions() {
		// top.funcDcl = genName.lookupValue.fullName
		silver.modification.impide.PmakeStubGenerator.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_funcDcl__ON__silver_modification_impide_StubGenerator] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.modification.impide.PmakeStubGenerator.i_genName).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } };
		// stubGenTypeExpected = functionType(stringType(), [ listType(nonterminalType("ide:IdeProperty", [])) ], [])
		silver.modification.impide.PmakeStubGenerator.localAttributes[silver.modification.impide.Init.stubGenTypeExpected__ON__silver_modification_impide_makeStubGenerator] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PfunctionType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PstringType()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.extension.list.PlistType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PnonterminalType((new common.StringCatter("ide:IdeProperty")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// tc1 = check(freshenCompletely(genName.lookupValue.typerep), stubGenTypeExpected)
		silver.modification.impide.PmakeStubGenerator.localAttributes[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeStubGenerator] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.analysis.typechecking.core.NTypeCheck)new silver.analysis.typechecking.core.Pcheck(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshenCompletely.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.modification.impide.PmakeStubGenerator.i_genName).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } })); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.impide.Init.stubGenTypeExpected__ON__silver_modification_impide_makeStubGenerator)))); } };
		// tc1.downSubst = emptySubst()
		silver.modification.impide.PmakeStubGenerator.localInheritedAttributes[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeStubGenerator][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PemptySubst.invoke()); } };
		// tc1.finalSubst = tc1.upSubst
		silver.modification.impide.PmakeStubGenerator.localInheritedAttributes[silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeStubGenerator][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeStubGenerator).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } };
		// top.errors := if ! tc1.typeerror then [] else [ err(genName.location, "Stub generator should have type:\n\t" ++ tc1.rightpp ++ "\nInstead it has the type:\n\t" ++ tc1.leftpp) ]
		if(silver.modification.impide.PmakeStubGenerator.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_StubGenerator] == null)
			silver.modification.impide.PmakeStubGenerator.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_StubGenerator] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_StubGenerator);
		((common.CollectionAttribute)silver.modification.impide.PmakeStubGenerator.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_StubGenerator]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.localDecorated(silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeStubGenerator).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.modification.impide.PmakeStubGenerator.i_genName).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Stub generator should have type:\n\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeStubGenerator).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nInstead it has the type:\n\t")), (common.StringCatter)((common.StringCatter)context.localDecorated(silver.modification.impide.Init.tc1__ON__silver_modification_impide_makeStubGenerator).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });

	}

	public static final common.NodeFactory<PmakeStubGenerator> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmakeStubGenerator> {

		@Override
		public PmakeStubGenerator invoke(final Object[] children, final Object[] annotations) {
			return new PmakeStubGenerator(children[0], children[1], children[2]);
		}
	};

}
