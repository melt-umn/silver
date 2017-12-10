
package silver.modification.impide;

// top::IdeStmt ::= 'wizard' 'new file' '{' generator::StubGenerator props::PropertyList '}' 
public final class PnewfileWizard_c extends silver.modification.impide.NIdeStmt {

	public static final int i__G_5 = 0;
	public static final int i__G_4 = 1;
	public static final int i__G_3 = 2;
	public static final int i_generator = 3;
	public static final int i_props = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.modification.impide.TImpIde_Wizard.class,silver.modification.impide.TImpIde_Wizard_NewFile.class,silver.definition.core.TLCurly_t.class,silver.modification.impide.NStubGenerator.class,silver.modification.impide.NPropertyList.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_newfileWizard_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NIdeStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NIdeStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_generator] = new common.Lazy[silver.modification.impide.NStubGenerator.num_inh_attrs];
	childInheritedAttributes[i_props] = new common.Lazy[silver.modification.impide.NPropertyList.num_inh_attrs];

	}

	public PnewfileWizard_c(final Object c__G_5, final Object c__G_4, final Object c__G_3, final Object c_generator, final Object c_props, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child__G_4 = c__G_4;
		this.child__G_3 = c__G_3;
		this.child_generator = c_generator;
		this.child_props = c_props;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final silver.modification.impide.TImpIde_Wizard getChild__G_5() {
		return (silver.modification.impide.TImpIde_Wizard) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child__G_4;
	public final silver.modification.impide.TImpIde_Wizard_NewFile getChild__G_4() {
		return (silver.modification.impide.TImpIde_Wizard_NewFile) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child__G_3;
	public final silver.definition.core.TLCurly_t getChild__G_3() {
		return (silver.definition.core.TLCurly_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_generator;
	public final silver.modification.impide.NStubGenerator getChild_generator() {
		return (silver.modification.impide.NStubGenerator) (child_generator = common.Util.demand(child_generator));
	}

	private Object child_props;
	public final silver.modification.impide.NPropertyList getChild_props() {
		return (silver.modification.impide.NPropertyList) (child_props = common.Util.demand(child_props));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i__G_4: return getChild__G_4();
			case i__G_3: return getChild__G_3();
			case i_generator: return getChild_generator();
			case i_props: return getChild_props();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i__G_4: return child__G_4;
			case i__G_3: return child__G_3;
			case i_generator: return child_generator;
			case i_props: return child_props;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:newfileWizard_c erroneously claimed to forward");
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
		return "silver:modification:impide:newfileWizard_c";
	}

	static void initProductionAttributeDefinitions() {
		// top.wizards = [ newfileWizard(generator.funcDcl, props.propDcls) ]
		silver.modification.impide.PnewfileWizard_c.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.impide.spec.NIdeWizardDcl)new silver.modification.impide.spec.PnewfileWizard(context.childDecoratedSynthesizedLazy(silver.modification.impide.PnewfileWizard_c.i_generator, silver.modification.impide.Init.silver_modification_impide_funcDcl__ON__silver_modification_impide_StubGenerator), context.childDecoratedSynthesizedLazy(silver.modification.impide.PnewfileWizard_c.i_props, silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_PropertyList))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := generator.errors ++ props.errors
		if(silver.modification.impide.PnewfileWizard_c.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] == null)
			silver.modification.impide.PnewfileWizard_c.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt);
		((common.CollectionAttribute)silver.modification.impide.PnewfileWizard_c.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PnewfileWizard_c.i_generator, silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_StubGenerator), context.childDecoratedSynthesizedLazy(silver.modification.impide.PnewfileWizard_c.i_props, silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_PropertyList))); } });

	}

	public static final common.NodeFactory<PnewfileWizard_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnewfileWizard_c> {

		@Override
		public PnewfileWizard_c invoke(final Object[] children, final Object[] annotations) {
			return new PnewfileWizard_c(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
