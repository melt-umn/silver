
package silver.modification.impide.spec;

// top::IdeWizardDcl ::= func::String props::[IdeProperty] 
public final class PnewfileWizard extends silver.modification.impide.spec.NIdeWizardDcl {

	public static final int i_func = 0;
	public static final int i_props = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_newfileWizard;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.spec.NIdeWizardDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.spec.NIdeWizardDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnewfileWizard(final Object c_func, final Object c_props) {
		super();
		this.child_func = c_func;
		this.child_props = c_props;

	}

	private Object child_func;
	public final common.StringCatter getChild_func() {
		return (common.StringCatter) (child_func = common.Util.demand(child_func));
	}

	private Object child_props;
	public final common.ConsCell getChild_props() {
		return (common.ConsCell) (child_props = common.Util.demand(child_props));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_func: return getChild_func();
			case i_props: return getChild_props();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_func: return child_func;
			case i_props: return child_props;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:spec:newfileWizard erroneously claimed to forward");
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
		return "silver:modification:impide:spec:newfileWizard";
	}

	static void initProductionAttributeDefinitions() {
		// top.svIdeInterface = "\n\t@Override\n\tpublic IPropertyControlsProvider getNewFileProperties() {\n\t\treturn new " ++ top.package ++ ".eclipse.wizard.newfile.PropertyControlsProvider();\n\t}\n\t@Override\n\tpublic StringCatter fileStub(ConsCell properties) {\n\t\treturn (StringCatter)" ++ makeClassName(func) ++ ".invoke(properties);\n\t}\n"
		silver.modification.impide.spec.PnewfileWizard.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeWizardDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\t@Override\n\tpublic IPropertyControlsProvider getNewFileProperties() {\n\t\treturn new ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.modification.impide.spec.Init.silver_modification_impide_spec_package__ON__silver_modification_impide_spec_IdeWizardDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".eclipse.wizard.newfile.PropertyControlsProvider();\n\t}\n\t@Override\n\tpublic StringCatter fileStub(ConsCell properties) {\n\t\treturn (StringCatter)")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeClassName.invoke(context.childAsIsLazy(silver.modification.impide.spec.PnewfileWizard.i_func))), (common.StringCatter)(new common.StringCatter(".invoke(properties);\n\t}\n")))))); } };
		// top.pluginXmlWizards = "\n  <wizard\n      category=\"" ++ top.bundle ++ "." ++ extid_wizard_category ++ "\"\n      class=\"edu.umn.cs.melt.ide.wizard.NewSourceFileWizard\"\n      id=\"" ++ top.bundle ++ "." ++ extid_wizard_newfile ++ "\"\n      name=\"New " ++ top.visibleName ++ " Source File\">\n  </wizard>\n"
		silver.modification.impide.spec.PnewfileWizard.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlWizards__ON__silver_modification_impide_spec_IdeWizardDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n  <wizard\n      category=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.modification.impide.spec.Init.silver_modification_impide_spec_bundle__ON__silver_modification_impide_spec_IdeWizardDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.impide.spec.Init.extid_wizard_category.eval()), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\"\n      class=\"edu.umn.cs.melt.ide.wizard.NewSourceFileWizard\"\n      id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.modification.impide.spec.Init.silver_modification_impide_spec_bundle__ON__silver_modification_impide_spec_IdeWizardDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.impide.spec.Init.extid_wizard_newfile.eval()), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\"\n      name=\"New ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.modification.impide.spec.Init.silver_modification_impide_spec_visibleName__ON__silver_modification_impide_spec_IdeWizardDcl)), (common.StringCatter)(new common.StringCatter(" Source File\">\n  </wizard>\n")))))))))))); } };
		// top.pluginFiles = [ pair(top.pluginPkgPath ++ "eclipse/wizard/newfile/PropertyControlsProvider.java", getPropertyProvider(top.package, props, "wizard.newfile")) ]
		silver.modification.impide.spec.PnewfileWizard.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginFiles__ON__silver_modification_impide_spec_IdeWizardDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginPkgPath__ON__silver_modification_impide_spec_IdeWizardDcl)), (common.StringCatter)(new common.StringCatter("eclipse/wizard/newfile/PropertyControlsProvider.java"))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.modification.impide.spec.PgetPropertyProvider.invoke(context.contextInheritedLazy(silver.modification.impide.spec.Init.silver_modification_impide_spec_package__ON__silver_modification_impide_spec_IdeWizardDcl), context.childAsIsLazy(silver.modification.impide.spec.PnewfileWizard.i_props), (new common.StringCatter("wizard.newfile")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PnewfileWizard> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnewfileWizard> {

		@Override
		public PnewfileWizard invoke(final Object[] children, final Object[] annotations) {
			return new PnewfileWizard(children[0], children[1]);
		}
	};

}
