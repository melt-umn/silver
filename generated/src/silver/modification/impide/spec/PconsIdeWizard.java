
package silver.modification.impide.spec;

// top::IdeWizards ::= h::IdeWizardDcl t::IdeWizards 
public final class PconsIdeWizard extends silver.modification.impide.spec.NIdeWizards {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.modification.impide.spec.NIdeWizardDcl.class,silver.modification.impide.spec.NIdeWizards.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_consIdeWizard;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.spec.NIdeWizards.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.spec.NIdeWizards.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.modification.impide.spec.NIdeWizardDcl.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.modification.impide.spec.NIdeWizards.num_inh_attrs];

	}

	public PconsIdeWizard(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.modification.impide.spec.NIdeWizardDcl getChild_h() {
		return (silver.modification.impide.spec.NIdeWizardDcl) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.modification.impide.spec.NIdeWizards getChild_t() {
		return (silver.modification.impide.spec.NIdeWizards) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:spec:consIdeWizard erroneously claimed to forward");
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
		return "silver:modification:impide:spec:consIdeWizard";
	}

	static void initProductionAttributeDefinitions() {
		// top.svIdeInterface = h.svIdeInterface ++ t.svIdeInterface
		silver.modification.impide.spec.PconsIdeWizard.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeWizards] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PconsIdeWizard.i_h).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeWizardDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PconsIdeWizard.i_t).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_svIdeInterface__ON__silver_modification_impide_spec_IdeWizards))); } };
		// top.pluginXmlWizards = h.pluginXmlWizards ++ t.pluginXmlWizards
		silver.modification.impide.spec.PconsIdeWizard.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlWizards__ON__silver_modification_impide_spec_IdeWizards] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PconsIdeWizard.i_h).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlWizards__ON__silver_modification_impide_spec_IdeWizardDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PconsIdeWizard.i_t).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginXmlWizards__ON__silver_modification_impide_spec_IdeWizards))); } };
		// top.pluginFiles = h.pluginFiles ++ t.pluginFiles
		silver.modification.impide.spec.PconsIdeWizard.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginFiles__ON__silver_modification_impide_spec_IdeWizards] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.spec.PconsIdeWizard.i_h, silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginFiles__ON__silver_modification_impide_spec_IdeWizardDcl), context.childDecoratedSynthesizedLazy(silver.modification.impide.spec.PconsIdeWizard.i_t, silver.modification.impide.spec.Init.silver_modification_impide_spec_pluginFiles__ON__silver_modification_impide_spec_IdeWizards))); } };

	}

	public static final common.NodeFactory<PconsIdeWizard> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsIdeWizard> {

		@Override
		public PconsIdeWizard invoke(final Object[] children, final Object[] annotations) {
			return new PconsIdeWizard(children[0], children[1]);
		}
	};

}
