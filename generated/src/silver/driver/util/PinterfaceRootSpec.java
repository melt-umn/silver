
package silver.driver.util;

// top::RootSpec ::= p::IRoot interfaceTime::Integer 
public final class PinterfaceRootSpec extends silver.driver.util.NRootSpec {

	public static final int i_p = 0;
	public static final int i_interfaceTime = 1;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.NIRoot.class,Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_interfaceRootSpec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NRootSpec.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NRootSpec.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_p] = new common.Lazy[silver.definition.env.env_parser.NIRoot.num_inh_attrs];

	}

	public PinterfaceRootSpec(final Object c_p, final Object c_interfaceTime) {
		super();
		this.child_p = c_p;
		this.child_interfaceTime = c_interfaceTime;

	}

	private Object child_p;
	public final silver.definition.env.env_parser.NIRoot getChild_p() {
		return (silver.definition.env.env_parser.NIRoot) (child_p = common.Util.demand(child_p));
	}

	private Object child_interfaceTime;
	public final Integer getChild_interfaceTime() {
		return (Integer) (child_interfaceTime = common.Util.demand(child_interfaceTime));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();
			case i_interfaceTime: return getChild_interfaceTime();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;
			case i_interfaceTime: return child_interfaceTime;

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
		throw new common.exceptions.SilverInternalError("Production silver:driver:util:interfaceRootSpec erroneously claimed to forward");
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
		return "silver:driver:util:interfaceRootSpec";
	}

	static void initProductionAttributeDefinitions() {
		// p.grammarName = p.declaredName
		silver.driver.util.PinterfaceRootSpec.childInheritedAttributes[silver.driver.util.PinterfaceRootSpec.i_p][silver.definition.env.env_parser.Init.silver_definition_core_grammarName__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_declaredName__ON__silver_definition_env_env_parser_IRoot)); } };
		// top.grammarSource = p.grammarSource
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_grammarSource__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_grammarSource__ON__silver_definition_env_env_parser_IRoot)); } };
		// top.grammarTime = p.grammarTime
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_grammarTime__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_grammarTime__ON__silver_definition_env_env_parser_IRoot)); } };
		// top.interfaceTime = interfaceTime
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_interfaceTime__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.driver.util.PinterfaceRootSpec.i_interfaceTime)); } };
		// ood = isOutOfDate(interfaceTime, top.allGrammarDependencies, top.compiledGrammars)
		silver.driver.util.PinterfaceRootSpec.localAttributes[silver.driver.util.Init.ood__ON__silver_driver_util_interfaceRootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)silver.driver.util.PisOutOfDate.invoke(context.childAsIsLazy(silver.driver.util.PinterfaceRootSpec.i_interfaceTime), context.contextSynthesizedLazy(silver.driver.util.Init.silver_definition_env_allGrammarDependencies__ON__silver_driver_util_RootSpec), context.contextInheritedLazy(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_RootSpec))); } };
		// top.recheckGrammars = if ood then [ p.grammarName ] else []
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localAsIs(silver.driver.util.Init.ood__ON__silver_driver_util_interfaceRootSpec)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).inherited(silver.definition.env.env_parser.Init.silver_definition_core_grammarName__ON__silver_definition_env_env_parser_IRoot)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } };
		// top.translateGrammars = []
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_translateGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.declaredName = p.declaredName
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_declaredName__ON__silver_definition_env_env_parser_IRoot)); } };
		// top.moduleNames = p.moduleNames
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_moduleNames__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_moduleNames__ON__silver_definition_env_env_parser_IRoot)); } };
		// top.exportedGrammars = p.exportedGrammars
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_exportedGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_exportedGrammars__ON__silver_definition_env_env_parser_IRoot)); } };
		// top.optionalGrammars = p.optionalGrammars
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_optionalGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_optionalGrammars__ON__silver_definition_env_env_parser_IRoot)); } };
		// top.condBuild = p.condBuild
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_condBuild__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_condBuild__ON__silver_definition_env_env_parser_IRoot)); } };
		// top.allGrammarDependencies = p.allGrammarDependencies
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_allGrammarDependencies__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_allGrammarDependencies__ON__silver_definition_env_env_parser_IRoot)); } };
		// top.defs = p.defs
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_defs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IRoot)); } };
		// top.grammarErrors = []
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_core_grammarErrors__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.parsingErrors = []
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_parsingErrors__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PinterfaceRootSpec> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PinterfaceRootSpec> {

		@Override
		public PinterfaceRootSpec invoke(final Object[] children, final Object[] annotations) {
			return new PinterfaceRootSpec(children[0], children[1]);
		}
	};

}
