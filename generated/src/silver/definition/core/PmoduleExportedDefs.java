
package silver.definition.core;

// top::ModuleExportedDefs ::= l::Location compiledGrammars::EnvTree<Decorated RootSpec> grammarDependencies::[String] need::[String] seen::[String] 
public final class PmoduleExportedDefs extends silver.definition.core.NModuleExportedDefs {

	public static final int i_l = 0;
	public static final int i_compiledGrammars = 1;
	public static final int i_grammarDependencies = 2;
	public static final int i_need = 3;
	public static final int i_seen = 4;


	public static final Class<?> childTypes[] = {core.NLocation.class,Object.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_moduleExportedDefs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NModuleExportedDefs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NModuleExportedDefs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PmoduleExportedDefs(final Object c_l, final Object c_compiledGrammars, final Object c_grammarDependencies, final Object c_need, final Object c_seen) {
		super();
		this.child_l = c_l;
		this.child_compiledGrammars = c_compiledGrammars;
		this.child_grammarDependencies = c_grammarDependencies;
		this.child_need = c_need;
		this.child_seen = c_seen;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_compiledGrammars;
	public final Object getChild_compiledGrammars() {
		return (Object) (child_compiledGrammars = common.Util.demand(child_compiledGrammars));
	}

	private Object child_grammarDependencies;
	public final common.ConsCell getChild_grammarDependencies() {
		return (common.ConsCell) (child_grammarDependencies = common.Util.demand(child_grammarDependencies));
	}

	private Object child_need;
	public final common.ConsCell getChild_need() {
		return (common.ConsCell) (child_need = common.Util.demand(child_need));
	}

	private Object child_seen;
	public final common.ConsCell getChild_seen() {
		return (common.ConsCell) (child_seen = common.Util.demand(child_seen));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_compiledGrammars: return getChild_compiledGrammars();
			case i_grammarDependencies: return getChild_grammarDependencies();
			case i_need: return getChild_need();
			case i_seen: return getChild_seen();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_compiledGrammars: return child_compiledGrammars;
			case i_grammarDependencies: return child_grammarDependencies;
			case i_need: return child_need;
			case i_seen: return child_seen;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:moduleExportedDefs erroneously claimed to forward");
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
		return "silver:definition:core:moduleExportedDefs";
	}

	static void initProductionAttributeDefinitions() {
		// recurse = moduleExportedDefs(l, compiledGrammars, grammarDependencies, new_need, new_seen)
		silver.definition.core.PmoduleExportedDefs.localAttributes[silver.definition.core.Init.recurse__ON__silver_definition_core_moduleExportedDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NModuleExportedDefs)new silver.definition.core.PmoduleExportedDefs(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PmoduleExportedDefs.i_l)), context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_compiledGrammars), context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_grammarDependencies), context.localAsIsLazy(silver.definition.core.Init.new_need__ON__silver_definition_core_moduleExportedDefs), context.localAsIsLazy(silver.definition.core.Init.new_seen__ON__silver_definition_core_moduleExportedDefs))); } };
		// gram = head(need)
		silver.definition.core.PmoduleExportedDefs.localAttributes[silver.definition.core.Init.gram__ON__silver_definition_core_moduleExportedDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_need))); } };
		// rs = searchEnvTree(gram, compiledGrammars)
		silver.definition.core.PmoduleExportedDefs.localAttributes[silver.definition.core.Init.rs__ON__silver_definition_core_moduleExportedDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.localAsIsLazy(silver.definition.core.Init.gram__ON__silver_definition_core_moduleExportedDefs), context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_compiledGrammars))); } };
		// new_seen = (gram :: seen)
		silver.definition.core.PmoduleExportedDefs.localAttributes[silver.definition.core.Init.new_seen__ON__silver_definition_core_moduleExportedDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.localAsIsLazy(silver.definition.core.Init.gram__ON__silver_definition_core_moduleExportedDefs), context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_seen))); } };
		// add_to_need = head(rs).exportedGrammars ++ triggeredGrammars(grammarDependencies, head(rs).condBuild)
		silver.definition.core.PmoduleExportedDefs.localAttributes[silver.definition.core.Init.add_to_need__ON__silver_definition_core_moduleExportedDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.rs__ON__silver_definition_core_moduleExportedDefs))).synthesized(silver.driver.util.Init.silver_definition_env_exportedGrammars__ON__silver_driver_util_RootSpec)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.core.PtriggeredGrammars.invoke(context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_grammarDependencies), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.rs__ON__silver_definition_core_moduleExportedDefs))).synthesized(silver.driver.util.Init.silver_definition_env_condBuild__ON__silver_driver_util_RootSpec)); } })); } })); } };
		// new_need = if null(rs) then tail(need) else rem(makeSet(tail(need) ++ add_to_need), new_seen)
		silver.definition.core.PmoduleExportedDefs.localAttributes[silver.definition.core.Init.new_need__ON__silver_definition_core_moduleExportedDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.core.Init.rs__ON__silver_definition_core_moduleExportedDefs))) ? ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_need))) : ((common.ConsCell)silver.util.Prem.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_need))); } }, context.localAsIsLazy(silver.definition.core.Init.add_to_need__ON__silver_definition_core_moduleExportedDefs))); } })); } }, context.localAsIsLazy(silver.definition.core.Init.new_seen__ON__silver_definition_core_moduleExportedDefs)))); } };
		// top.defs = if null(need) then [] else if null(rs) then recurse.defs else head(rs).defs ++ recurse.defs
		silver.definition.core.PmoduleExportedDefs.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleExportedDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_need))) ? ((common.ConsCell)core.Pnil.invoke()) : (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.core.Init.rs__ON__silver_definition_core_moduleExportedDefs))) ? ((common.ConsCell)context.localDecorated(silver.definition.core.Init.recurse__ON__silver_definition_core_moduleExportedDefs).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleExportedDefs)) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.rs__ON__silver_definition_core_moduleExportedDefs))).synthesized(silver.driver.util.Init.silver_definition_env_defs__ON__silver_driver_util_RootSpec)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.recurse__ON__silver_definition_core_moduleExportedDefs).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleExportedDefs)); } })))); } };
		// top.errors := if null(need) then [] else if null(rs) then [ err(l, "Grammar '" ++ gram ++ "' cannot be found.") ] ++ recurse.errors else recurse.errors
		if(silver.definition.core.PmoduleExportedDefs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExportedDefs] == null)
			silver.definition.core.PmoduleExportedDefs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExportedDefs] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExportedDefs);
		((common.CollectionAttribute)silver.definition.core.PmoduleExportedDefs.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExportedDefs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_need))) ? ((common.ConsCell)core.Pnil.invoke()) : (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.core.Init.rs__ON__silver_definition_core_moduleExportedDefs))) ? ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PmoduleExportedDefs.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Grammar '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.core.Init.gram__ON__silver_definition_core_moduleExportedDefs)), (common.StringCatter)(new common.StringCatter("' cannot be found.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.recurse__ON__silver_definition_core_moduleExportedDefs).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExportedDefs)); } })) : ((common.ConsCell)context.localDecorated(silver.definition.core.Init.recurse__ON__silver_definition_core_moduleExportedDefs).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExportedDefs)))); } });

	}

	public static final common.NodeFactory<PmoduleExportedDefs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmoduleExportedDefs> {

		@Override
		public PmoduleExportedDefs invoke(final Object[] children, final Object[] annotations) {
			return new PmoduleExportedDefs(children[0], children[1], children[2], children[3], children[4]);
		}
	};

}
