
package silver.definition.core;

// top::ModuleExpr ::= pkg::QName 'only' ns::NameList 'with' wc::WithElems 
public final class PmoduleOnlyWith extends silver.definition.core.NModuleExpr {

	public static final int i_pkg = 0;
	public static final int i__G_3 = 1;
	public static final int i_ns = 2;
	public static final int i__G_1 = 3;
	public static final int i_wc = 4;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TOnly_kwd.class,silver.definition.core.NNameList.class,silver.definition.core.TWith_kwd.class,silver.definition.core.NWithElems.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_moduleOnlyWith;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NModuleExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NModuleExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_pkg] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NNameList.num_inh_attrs];
	childInheritedAttributes[i_wc] = new common.Lazy[silver.definition.core.NWithElems.num_inh_attrs];

	}

	public PmoduleOnlyWith(final Object c_pkg, final Object c__G_3, final Object c_ns, final Object c__G_1, final Object c_wc, final Object a_core_location) {
		super(a_core_location);
		this.child_pkg = c_pkg;
		this.child__G_3 = c__G_3;
		this.child_ns = c_ns;
		this.child__G_1 = c__G_1;
		this.child_wc = c_wc;

	}

	private Object child_pkg;
	public final silver.definition.core.NQName getChild_pkg() {
		return (silver.definition.core.NQName) (child_pkg = common.Util.demand(child_pkg));
	}

	private Object child__G_3;
	public final silver.definition.core.TOnly_kwd getChild__G_3() {
		return (silver.definition.core.TOnly_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_ns;
	public final silver.definition.core.NNameList getChild_ns() {
		return (silver.definition.core.NNameList) (child_ns = common.Util.demand(child_ns));
	}

	private Object child__G_1;
	public final silver.definition.core.TWith_kwd getChild__G_1() {
		return (silver.definition.core.TWith_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_wc;
	public final silver.definition.core.NWithElems getChild_wc() {
		return (silver.definition.core.NWithElems) (child_wc = common.Util.demand(child_wc));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pkg: return getChild_pkg();
			case i__G_3: return getChild__G_3();
			case i_ns: return getChild_ns();
			case i__G_1: return getChild__G_1();
			case i_wc: return getChild_wc();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pkg: return child_pkg;
			case i__G_3: return child__G_3;
			case i_ns: return child_ns;
			case i__G_1: return child__G_1;
			case i_wc: return child_wc;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:moduleOnlyWith erroneously claimed to forward");
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
		return "silver:definition:core:moduleOnlyWith";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = pkg.pp ++ " only " ++ ns.pp ++ " with " ++ wc.pp
		silver.definition.core.PmoduleOnlyWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmoduleOnlyWith.i_pkg).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" only ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmoduleOnlyWith.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_NameList)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" with ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmoduleOnlyWith.i_wc).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_WithElems)))))); } };
		// top.moduleNames = [ pkg.name ]
		silver.definition.core.PmoduleOnlyWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleOnlyWith.i_pkg, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// m = module(pkg.location, [ pkg.name ], [ top.grammarName ], top.compiledGrammars, top.grammarDependencies, "", ns.names, [], wc.envMaps)
		silver.definition.core.PmoduleOnlyWith.localAttributes[silver.definition.core.Init.m__ON__silver_definition_core_moduleOnlyWith] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NModule)new silver.definition.core.Pmodule(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PmoduleOnlyWith.i_pkg).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleOnlyWith.i_pkg, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_ModuleExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_ModuleExpr), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarDependencies__ON__silver_definition_core_ModuleExpr), (new common.StringCatter("")), context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleOnlyWith.i_ns, silver.definition.core.Init.silver_definition_core_names__ON__silver_definition_core_NameList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleOnlyWith.i_wc, silver.definition.core.Init.silver_definition_core_envMaps__ON__silver_definition_core_WithElems))); } };
		// top.errors := m.errors
		if(silver.definition.core.PmoduleOnlyWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr] == null)
			silver.definition.core.PmoduleOnlyWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr);
		((common.CollectionAttribute)silver.definition.core.PmoduleOnlyWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.m__ON__silver_definition_core_moduleOnlyWith).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Module)); } });
		// top.defs = m.defs
		silver.definition.core.PmoduleOnlyWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.m__ON__silver_definition_core_moduleOnlyWith).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_Module)); } };

	}

	public static final common.NodeFactory<PmoduleOnlyWith> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmoduleOnlyWith> {

		@Override
		public PmoduleOnlyWith invoke(final Object[] children, final Object[] annotations) {
			return new PmoduleOnlyWith(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
