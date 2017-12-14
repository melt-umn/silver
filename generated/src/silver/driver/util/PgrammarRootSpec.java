
package silver.driver.util;

// top::RootSpec ::= g::Grammar grammarName::String grammarSource::String grammarTime::Integer 
public final class PgrammarRootSpec extends silver.driver.util.NRootSpec {

	public static final int i_g = 0;
	public static final int i_grammarName = 1;
	public static final int i_grammarSource = 2;
	public static final int i_grammarTime = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.NGrammar.class,common.StringCatter.class,common.StringCatter.class,Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_grammarRootSpec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NRootSpec.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NRootSpec.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_g] = new common.Lazy[silver.definition.core.NGrammar.num_inh_attrs];

	}

	public PgrammarRootSpec(final Object c_g, final Object c_grammarName, final Object c_grammarSource, final Object c_grammarTime) {
		super();
		this.child_g = c_g;
		this.child_grammarName = c_grammarName;
		this.child_grammarSource = c_grammarSource;
		this.child_grammarTime = c_grammarTime;

	}

	private Object child_g;
	public final silver.definition.core.NGrammar getChild_g() {
		return (silver.definition.core.NGrammar) (child_g = common.Util.demand(child_g));
	}

	private Object child_grammarName;
	public final common.StringCatter getChild_grammarName() {
		return (common.StringCatter) (child_grammarName = common.Util.demand(child_grammarName));
	}

	private Object child_grammarSource;
	public final common.StringCatter getChild_grammarSource() {
		return (common.StringCatter) (child_grammarSource = common.Util.demand(child_grammarSource));
	}

	private Object child_grammarTime;
	public final Integer getChild_grammarTime() {
		return (Integer) (child_grammarTime = common.Util.demand(child_grammarTime));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_g: return getChild_g();
			case i_grammarName: return getChild_grammarName();
			case i_grammarSource: return getChild_grammarSource();
			case i_grammarTime: return getChild_grammarTime();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_g: return child_g;
			case i_grammarName: return child_grammarName;
			case i_grammarSource: return child_grammarSource;
			case i_grammarTime: return child_grammarTime;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:driver:util:grammarRootSpec erroneously claimed to forward");
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
		return "silver:driver:util:grammarRootSpec";
	}

	static void initProductionAttributeDefinitions() {
		// g.grammarName = grammarName
		silver.driver.util.PgrammarRootSpec.childInheritedAttributes[silver.driver.util.PgrammarRootSpec.i_g][silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.driver.util.PgrammarRootSpec.i_grammarName)); } };
		// g.env = toEnv(g.defs)
		silver.driver.util.PgrammarRootSpec.childInheritedAttributes[silver.driver.util.PgrammarRootSpec.i_g][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PtoEnv.invoke(context.childDecoratedSynthesizedLazy(silver.driver.util.PgrammarRootSpec.i_g, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_Grammar))); } };
		// g.globalImports = toEnv(if contains("core", g.moduleNames) || grammarName == "core" then g.importedDefs else g.importedDefs ++ head(searchEnvTree("core", top.compiledGrammars)).defs)
		silver.driver.util.PgrammarRootSpec.childInheritedAttributes[silver.driver.util.PgrammarRootSpec.i_g][silver.definition.core.Init.silver_definition_core_globalImports__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PtoEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((((Boolean)silver.util.Pcontains.invoke((new common.StringCatter("core")), context.childDecoratedSynthesizedLazy(silver.driver.util.PgrammarRootSpec.i_g, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_Grammar))) || ((common.StringCatter)context.childAsIs(silver.driver.util.PgrammarRootSpec.i_grammarName)).equals((new common.StringCatter("core")))) ? ((common.ConsCell)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.definition.core.Init.silver_definition_core_importedDefs__ON__silver_definition_core_Grammar)) : ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.driver.util.PgrammarRootSpec.i_g, silver.definition.core.Init.silver_definition_core_importedDefs__ON__silver_definition_core_Grammar), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke((new common.StringCatter("core")), context.contextInheritedLazy(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_RootSpec))); } })).synthesized(silver.driver.util.Init.silver_definition_env_defs__ON__silver_driver_util_RootSpec)); } }))); } })); } };
		// actualDependencies = makeSet(computeDependencies((grammarName :: top.moduleNames), top.compiledGrammars))
		silver.driver.util.PgrammarRootSpec.localAttributes[silver.driver.util.Init.actualDependencies__ON__silver_driver_util_grammarRootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PcomputeDependencies.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.driver.util.PgrammarRootSpec.i_grammarName), context.contextSynthesizedLazy(silver.driver.util.Init.silver_definition_env_moduleNames__ON__silver_driver_util_RootSpec))); } }, context.contextInheritedLazy(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_RootSpec))); } })); } };
		// depsPlusOptions = makeSet(completeDependencyClosure(actualDependencies, top.compiledGrammars))
		silver.driver.util.PgrammarRootSpec.localAttributes[silver.driver.util.Init.depsPlusOptions__ON__silver_driver_util_grammarRootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PcompleteDependencyClosure.invoke(context.localAsIsLazy(silver.driver.util.Init.actualDependencies__ON__silver_driver_util_grammarRootSpec), context.contextInheritedLazy(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_RootSpec))); } })); } };
		// g.grammarDependencies = actualDependencies
		silver.driver.util.PgrammarRootSpec.childInheritedAttributes[silver.driver.util.PgrammarRootSpec.i_g][silver.definition.core.Init.silver_definition_core_grammarDependencies__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localAsIs(silver.driver.util.Init.actualDependencies__ON__silver_driver_util_grammarRootSpec)); } };
		// g.flowEnv = fromFlowDefs(foldr(consFlow, nilFlow(), gatherFlowEnv(depsPlusOptions, top.compiledGrammars)))
		silver.driver.util.PgrammarRootSpec.childInheritedAttributes[silver.driver.util.PgrammarRootSpec.i_g][silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.flow.env.PfromFlowDefs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowDefs)core.Pfoldr.invoke(silver.definition.flow.ast.PconsFlow.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowDefs)new silver.definition.flow.ast.PnilFlow()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PgatherFlowEnv.invoke(context.localAsIsLazy(silver.driver.util.Init.depsPlusOptions__ON__silver_driver_util_grammarRootSpec), context.contextInheritedLazy(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_RootSpec))); } })); } })); } };
		// g.config = top.config
		silver.driver.util.PgrammarRootSpec.childInheritedAttributes[silver.driver.util.PgrammarRootSpec.i_g][silver.definition.core.Init.silver_definition_env_config__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.driver.util.Init.silver_definition_env_config__ON__silver_driver_util_RootSpec)); } };
		// g.compiledGrammars = top.compiledGrammars
		silver.driver.util.PgrammarRootSpec.childInheritedAttributes[silver.driver.util.PgrammarRootSpec.i_g][silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_RootSpec)); } };
		// top.grammarSource = grammarSource
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_grammarSource__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.driver.util.PgrammarRootSpec.i_grammarSource)); } };
		// top.grammarTime = grammarTime
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_grammarTime__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.driver.util.PgrammarRootSpec.i_grammarTime)); } };
		// top.interfaceTime = grammarTime
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_interfaceTime__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.driver.util.PgrammarRootSpec.i_grammarTime)); } };
		// top.recheckGrammars = []
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.translateGrammars = [ top ]
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_translateGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.declaredName = g.declaredName
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.definition.core.Init.silver_definition_env_declaredName__ON__silver_definition_core_Grammar)); } };
		// top.moduleNames = makeSet(g.moduleNames ++ [ "core" ])
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_moduleNames__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.driver.util.PgrammarRootSpec.i_g, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_Grammar), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("core")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };
		// top.exportedGrammars = g.exportedGrammars
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_exportedGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.definition.core.Init.silver_definition_env_exportedGrammars__ON__silver_definition_core_Grammar)); } };
		// top.optionalGrammars = g.optionalGrammars
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_optionalGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.definition.core.Init.silver_definition_env_optionalGrammars__ON__silver_definition_core_Grammar)); } };
		// top.condBuild = g.condBuild
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_condBuild__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.definition.core.Init.silver_definition_env_condBuild__ON__silver_definition_core_Grammar)); } };
		// top.allGrammarDependencies = actualDependencies
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_allGrammarDependencies__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localAsIs(silver.driver.util.Init.actualDependencies__ON__silver_driver_util_grammarRootSpec)); } };
		// top.defs = g.defs
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_defs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_Grammar)); } };
		// top.grammarErrors = g.grammarErrors
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_core_grammarErrors__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.definition.core.Init.silver_definition_core_grammarErrors__ON__silver_definition_core_Grammar)); } };
		// top.parsingErrors = []
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_parsingErrors__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PgrammarRootSpec> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgrammarRootSpec> {

		@Override
		public PgrammarRootSpec invoke(final Object[] children, final Object[] annotations) {
			return new PgrammarRootSpec(children[0], children[1], children[2], children[3]);
		}
	};

}
