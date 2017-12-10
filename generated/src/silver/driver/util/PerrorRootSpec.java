
package silver.driver.util;

// top::RootSpec ::= e::[ParseError] grammarName::String grammarSource::String grammarTime::Integer 
public final class PerrorRootSpec extends silver.driver.util.NRootSpec {

	public static final int i_e = 0;
	public static final int i_grammarName = 1;
	public static final int i_grammarSource = 2;
	public static final int i_grammarTime = 3;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.StringCatter.class,common.StringCatter.class,Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_errorRootSpec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NRootSpec.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NRootSpec.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PerrorRootSpec(final Object c_e, final Object c_grammarName, final Object c_grammarSource, final Object c_grammarTime) {
		super();
		this.child_e = c_e;
		this.child_grammarName = c_grammarName;
		this.child_grammarSource = c_grammarSource;
		this.child_grammarTime = c_grammarTime;

	}

	private Object child_e;
	public final common.ConsCell getChild_e() {
		return (common.ConsCell) (child_e = common.Util.demand(child_e));
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
			case i_e: return getChild_e();
			case i_grammarName: return getChild_grammarName();
			case i_grammarSource: return getChild_grammarSource();
			case i_grammarTime: return getChild_grammarTime();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
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
		throw new common.exceptions.SilverInternalError("Production silver:driver:util:errorRootSpec erroneously claimed to forward");
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
		return "silver:driver:util:errorRootSpec";
	}

	static void initProductionAttributeDefinitions() {
		// top.grammarSource = grammarSource
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_grammarSource__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.driver.util.PerrorRootSpec.i_grammarSource)); } };
		// top.grammarTime = grammarTime
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_grammarTime__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.driver.util.PerrorRootSpec.i_grammarTime)); } };
		// top.interfaceTime = grammarTime
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_interfaceTime__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.driver.util.PerrorRootSpec.i_grammarTime)); } };
		// top.recheckGrammars = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.translateGrammars = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_translateGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.declaredName = grammarName
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.driver.util.PerrorRootSpec.i_grammarName)); } };
		// top.moduleNames = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_moduleNames__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.exportedGrammars = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_exportedGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.optionalGrammars = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_optionalGrammars__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.condBuild = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_condBuild__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.allGrammarDependencies = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_allGrammarDependencies__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.defs = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_env_defs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.grammarErrors = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_definition_core_grammarErrors__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.parsingErrors = map(parseErrorToMessage(grammarSource, _), e)
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_parsingErrors__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.driver.util.PparseErrorToMessage.factory.invokePartial(new int[]{0}, new Object[]{context.childAsIsLazy(silver.driver.util.PerrorRootSpec.i_grammarSource)}); } }, context.childAsIsLazy(silver.driver.util.PerrorRootSpec.i_e))); } };

	}

	public static final common.NodeFactory<PerrorRootSpec> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PerrorRootSpec> {

		@Override
		public PerrorRootSpec invoke(final Object[] children, final Object[] annotations) {
			return new PerrorRootSpec(children[0], children[1], children[2], children[3]);
		}
	};

}
