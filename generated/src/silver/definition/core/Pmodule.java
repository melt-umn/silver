
package silver.definition.core;

// top::Module ::= l::Location need::[String] seen::[String] compiledGrammars::EnvTree<Decorated RootSpec> grammarDependencies::[String] asPrepend::String onlyFilter::[String] hidingFilter::[String] withRenames::[Pair<String String>] 
public final class Pmodule extends silver.definition.core.NModule {

	public static final int i_l = 0;
	public static final int i_need = 1;
	public static final int i_seen = 2;
	public static final int i_compiledGrammars = 3;
	public static final int i_grammarDependencies = 4;
	public static final int i_asPrepend = 5;
	public static final int i_onlyFilter = 6;
	public static final int i_hidingFilter = 7;
	public static final int i_withRenames = 8;


	public static final Class<?> childTypes[] = {core.NLocation.class,common.DecoratedNode.class,common.DecoratedNode.class,Object.class,common.DecoratedNode.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_module;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NModule.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NModule.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[9][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public Pmodule(final Object c_l, final Object c_need, final Object c_seen, final Object c_compiledGrammars, final Object c_grammarDependencies, final Object c_asPrepend, final Object c_onlyFilter, final Object c_hidingFilter, final Object c_withRenames) {
		super();
		this.child_l = c_l;
		this.child_need = c_need;
		this.child_seen = c_seen;
		this.child_compiledGrammars = c_compiledGrammars;
		this.child_grammarDependencies = c_grammarDependencies;
		this.child_asPrepend = c_asPrepend;
		this.child_onlyFilter = c_onlyFilter;
		this.child_hidingFilter = c_hidingFilter;
		this.child_withRenames = c_withRenames;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_need;
	public final common.ConsCell getChild_need() {
		return (common.ConsCell) (child_need = common.Util.demand(child_need));
	}

	private Object child_seen;
	public final common.ConsCell getChild_seen() {
		return (common.ConsCell) (child_seen = common.Util.demand(child_seen));
	}

	private Object child_compiledGrammars;
	public final Object getChild_compiledGrammars() {
		return (Object) (child_compiledGrammars = common.Util.demand(child_compiledGrammars));
	}

	private Object child_grammarDependencies;
	public final common.ConsCell getChild_grammarDependencies() {
		return (common.ConsCell) (child_grammarDependencies = common.Util.demand(child_grammarDependencies));
	}

	private Object child_asPrepend;
	public final common.StringCatter getChild_asPrepend() {
		return (common.StringCatter) (child_asPrepend = common.Util.demand(child_asPrepend));
	}

	private Object child_onlyFilter;
	public final common.ConsCell getChild_onlyFilter() {
		return (common.ConsCell) (child_onlyFilter = common.Util.demand(child_onlyFilter));
	}

	private Object child_hidingFilter;
	public final common.ConsCell getChild_hidingFilter() {
		return (common.ConsCell) (child_hidingFilter = common.Util.demand(child_hidingFilter));
	}

	private Object child_withRenames;
	public final common.ConsCell getChild_withRenames() {
		return (common.ConsCell) (child_withRenames = common.Util.demand(child_withRenames));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_need: return getChild_need();
			case i_seen: return getChild_seen();
			case i_compiledGrammars: return getChild_compiledGrammars();
			case i_grammarDependencies: return getChild_grammarDependencies();
			case i_asPrepend: return getChild_asPrepend();
			case i_onlyFilter: return getChild_onlyFilter();
			case i_hidingFilter: return getChild_hidingFilter();
			case i_withRenames: return getChild_withRenames();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_need: return child_need;
			case i_seen: return child_seen;
			case i_compiledGrammars: return child_compiledGrammars;
			case i_grammarDependencies: return child_grammarDependencies;
			case i_asPrepend: return child_asPrepend;
			case i_onlyFilter: return child_onlyFilter;
			case i_hidingFilter: return child_hidingFilter;
			case i_withRenames: return child_withRenames;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:module erroneously claimed to forward");
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
		return "silver:definition:core:module";
	}

	static void initProductionAttributeDefinitions() {
		// med = moduleExportedDefs(l, compiledGrammars, grammarDependencies, need, seen)
		silver.definition.core.Pmodule.localAttributes[silver.definition.core.Init.med__ON__silver_definition_core_module] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NModuleExportedDefs)new silver.definition.core.PmoduleExportedDefs(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.Pmodule.i_l)), context.childAsIsLazy(silver.definition.core.Pmodule.i_compiledGrammars), context.childAsIsLazy(silver.definition.core.Pmodule.i_grammarDependencies), context.childAsIsLazy(silver.definition.core.Pmodule.i_need), context.childAsIsLazy(silver.definition.core.Pmodule.i_seen))); } };
		// defs_after_only = if null(onlyFilter) then med.defs else filter(filterDefOnEnvItem(envItemInclude(_, onlyFilter), _), med.defs)
		silver.definition.core.Pmodule.localAttributes[silver.definition.core.Init.defs_after_only__ON__silver_definition_core_module] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.core.Pmodule.i_onlyFilter))) ? ((common.ConsCell)context.localDecorated(silver.definition.core.Init.med__ON__silver_definition_core_module).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleExportedDefs)) : ((common.ConsCell)core.Pfilter.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PfilterDefOnEnvItem.factory.invokePartial(new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PenvItemInclude.factory.invokePartial(new int[]{1}, new Object[]{context.childAsIsLazy(silver.definition.core.Pmodule.i_onlyFilter)}); } }}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.med__ON__silver_definition_core_module).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleExportedDefs)); } }))); } };
		// defs_after_hiding = if null(hidingFilter) then defs_after_only else filter(filterDefOnEnvItem(envItemExclude(_, hidingFilter), _), defs_after_only)
		silver.definition.core.Pmodule.localAttributes[silver.definition.core.Init.defs_after_hiding__ON__silver_definition_core_module] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.core.Pmodule.i_hidingFilter))) ? ((common.ConsCell)context.localAsIs(silver.definition.core.Init.defs_after_only__ON__silver_definition_core_module)) : ((common.ConsCell)core.Pfilter.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PfilterDefOnEnvItem.factory.invokePartial(new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PenvItemExclude.factory.invokePartial(new int[]{1}, new Object[]{context.childAsIsLazy(silver.definition.core.Pmodule.i_hidingFilter)}); } }}); } }, context.localAsIsLazy(silver.definition.core.Init.defs_after_only__ON__silver_definition_core_module)))); } };
		// defs_after_renames = if null(withRenames) then defs_after_hiding else map(mapDefOnEnvItem(envItemApplyRenaming(_, withRenames), _), defs_after_hiding)
		silver.definition.core.Pmodule.localAttributes[silver.definition.core.Init.defs_after_renames__ON__silver_definition_core_module] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.core.Pmodule.i_withRenames))) ? ((common.ConsCell)context.localAsIs(silver.definition.core.Init.defs_after_hiding__ON__silver_definition_core_module)) : ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PmapDefOnEnvItem.factory.invokePartial(new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PenvItemApplyRenaming.factory.invokePartial(new int[]{1}, new Object[]{context.childAsIsLazy(silver.definition.core.Pmodule.i_withRenames)}); } }}); } }, context.localAsIsLazy(silver.definition.core.Init.defs_after_hiding__ON__silver_definition_core_module)))); } };
		// defs_after_prepend = if asPrepend == "" then defs_after_renames else map(mapDefOnEnvItem(envItemPrepend(_, asPrepend ++ ":"), _), defs_after_renames)
		silver.definition.core.Pmodule.localAttributes[silver.definition.core.Init.defs_after_prepend__ON__silver_definition_core_module] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)context.childAsIs(silver.definition.core.Pmodule.i_asPrepend)).equals((new common.StringCatter(""))) ? ((common.ConsCell)context.localAsIs(silver.definition.core.Init.defs_after_renames__ON__silver_definition_core_module)) : ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PmapDefOnEnvItem.factory.invokePartial(new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PenvItemPrepend.factory.invokePartial(new int[]{1}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.core.Pmodule.i_asPrepend)), (common.StringCatter)(new common.StringCatter(":"))); } }}); } }}); } }, context.localAsIsLazy(silver.definition.core.Init.defs_after_renames__ON__silver_definition_core_module)))); } };
		// top.defs = defs_after_prepend
		silver.definition.core.Pmodule.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_Module] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localAsIs(silver.definition.core.Init.defs_after_prepend__ON__silver_definition_core_module)); } };
		// top.errors := med.errors
		if(silver.definition.core.Pmodule.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Module] == null)
			silver.definition.core.Pmodule.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Module] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Module);
		((common.CollectionAttribute)silver.definition.core.Pmodule.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Module]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.med__ON__silver_definition_core_module).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExportedDefs)); } });

	}

	public static final common.NodeFactory<Pmodule> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pmodule> {

		@Override
		public Pmodule invoke(final Object[] children, final Object[] annotations) {
			return new Pmodule(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8]);
		}
	};

}
