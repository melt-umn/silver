
package silver.definition.core;

// top::ModuleExpr ::= pkg::QName 'hiding' ns::NameList 
public final class PmoduleHiding extends silver.definition.core.NModuleExpr {

	public static final int i_pkg = 0;
	public static final int i__G_1 = 1;
	public static final int i_ns = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.THiding_kwd.class,silver.definition.core.NNameList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_moduleHiding;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NModuleExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NModuleExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_pkg] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NNameList.num_inh_attrs];

	}

	public PmoduleHiding(final Object c_pkg, final Object c__G_1, final Object c_ns, final Object a_core_location) {
		super(a_core_location);
		this.child_pkg = c_pkg;
		this.child__G_1 = c__G_1;
		this.child_ns = c_ns;

	}

	private Object child_pkg;
	public final silver.definition.core.NQName getChild_pkg() {
		return (silver.definition.core.NQName) (child_pkg = common.Util.demand(child_pkg));
	}

	private Object child__G_1;
	public final silver.definition.core.THiding_kwd getChild__G_1() {
		return (silver.definition.core.THiding_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_ns;
	public final silver.definition.core.NNameList getChild_ns() {
		return (silver.definition.core.NNameList) (child_ns = common.Util.demand(child_ns));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pkg: return getChild_pkg();
			case i__G_1: return getChild__G_1();
			case i_ns: return getChild_ns();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pkg: return child_pkg;
			case i__G_1: return child__G_1;
			case i_ns: return child_ns;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:moduleHiding erroneously claimed to forward");
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
		return "silver:definition:core:moduleHiding";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = pkg.pp ++ " hiding " ++ ns.pp
		silver.definition.core.PmoduleHiding.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmoduleHiding.i_pkg).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" hiding ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PmoduleHiding.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_NameList)))); } };
		// top.moduleNames = [ pkg.name ]
		silver.definition.core.PmoduleHiding.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleHiding.i_pkg, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// m = module(pkg.location, [ pkg.name ], [ top.grammarName ], top.compiledGrammars, top.grammarDependencies, "", [], ns.names, [])
		silver.definition.core.PmoduleHiding.localAttributes[silver.definition.core.Init.m__ON__silver_definition_core_moduleHiding] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NModule)new silver.definition.core.Pmodule(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PmoduleHiding.i_pkg).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleHiding.i_pkg, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_ModuleExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_ModuleExpr), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarDependencies__ON__silver_definition_core_ModuleExpr), (new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleHiding.i_ns, silver.definition.core.Init.silver_definition_core_names__ON__silver_definition_core_NameList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := m.errors
		if(silver.definition.core.PmoduleHiding.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr] == null)
			silver.definition.core.PmoduleHiding.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr);
		((common.CollectionAttribute)silver.definition.core.PmoduleHiding.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.m__ON__silver_definition_core_moduleHiding).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Module)); } });
		// top.defs = m.defs
		silver.definition.core.PmoduleHiding.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.m__ON__silver_definition_core_moduleHiding).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_Module)); } };

	}

	public static final common.NodeFactory<PmoduleHiding> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmoduleHiding> {

		@Override
		public PmoduleHiding invoke(final Object[] children, final Object[] annotations) {
			return new PmoduleHiding(children[0], children[1], children[2], annotations[0]);
		}
	};

}
