
package silver.definition.core;

// top::ModuleExpr ::= pkg::QName 
public final class PmoduleAll extends silver.definition.core.NModuleExpr {

	public static final int i_pkg = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_moduleAll;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NModuleExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NModuleExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_pkg] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PmoduleAll(final Object c_pkg, final Object a_core_location) {
		super(a_core_location);
		this.child_pkg = c_pkg;

	}

	private Object child_pkg;
	public final silver.definition.core.NQName getChild_pkg() {
		return (silver.definition.core.NQName) (child_pkg = common.Util.demand(child_pkg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pkg: return getChild_pkg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pkg: return child_pkg;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:moduleAll erroneously claimed to forward");
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
		return "silver:definition:core:moduleAll";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = pkg.pp
		silver.definition.core.PmoduleAll.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PmoduleAll.i_pkg).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// top.moduleNames = [ pkg.name ]
		silver.definition.core.PmoduleAll.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleAll.i_pkg, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// m = module(pkg.location, [ pkg.name ], [ top.grammarName ], top.compiledGrammars, top.grammarDependencies, "", [], [], [])
		silver.definition.core.PmoduleAll.localAttributes[silver.definition.core.Init.m__ON__silver_definition_core_moduleAll] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NModule)new silver.definition.core.Pmodule(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PmoduleAll.i_pkg).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PmoduleAll.i_pkg, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_ModuleExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_ModuleExpr), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarDependencies__ON__silver_definition_core_ModuleExpr), (new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := m.errors
		if(silver.definition.core.PmoduleAll.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr] == null)
			silver.definition.core.PmoduleAll.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr);
		((common.CollectionAttribute)silver.definition.core.PmoduleAll.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.m__ON__silver_definition_core_moduleAll).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Module)); } });
		// top.defs = m.defs
		silver.definition.core.PmoduleAll.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.m__ON__silver_definition_core_moduleAll).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_Module)); } };

	}

	public static final common.NodeFactory<PmoduleAll> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmoduleAll> {

		@Override
		public PmoduleAll invoke(final Object[] children, final Object[] annotations) {
			return new PmoduleAll(children[0], annotations[0]);
		}
	};

}
