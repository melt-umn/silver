
package silver.driver.util;

// top::BuildEnv ::= silverHome::String silverGen::String grammarPath::[String] 
public final class PbuildEnv extends silver.driver.util.NBuildEnv {

	public static final int i_silverHome = 0;
	public static final int i_silverGen = 1;
	public static final int i_grammarPath = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_buildEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NBuildEnv.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NBuildEnv.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PbuildEnv(final Object c_silverHome, final Object c_silverGen, final Object c_grammarPath) {
		super();
		this.child_silverHome = c_silverHome;
		this.child_silverGen = c_silverGen;
		this.child_grammarPath = c_grammarPath;

	}

	private Object child_silverHome;
	public final common.StringCatter getChild_silverHome() {
		return (common.StringCatter) (child_silverHome = common.Util.demand(child_silverHome));
	}

	private Object child_silverGen;
	public final common.StringCatter getChild_silverGen() {
		return (common.StringCatter) (child_silverGen = common.Util.demand(child_silverGen));
	}

	private Object child_grammarPath;
	public final common.ConsCell getChild_grammarPath() {
		return (common.ConsCell) (child_grammarPath = common.Util.demand(child_grammarPath));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_silverHome: return getChild_silverHome();
			case i_silverGen: return getChild_silverGen();
			case i_grammarPath: return getChild_grammarPath();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_silverHome: return child_silverHome;
			case i_silverGen: return child_silverGen;
			case i_grammarPath: return child_grammarPath;

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
		throw new common.exceptions.SilverInternalError("Production silver:driver:util:buildEnv erroneously claimed to forward");
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
		return "silver:driver:util:buildEnv";
	}

	static void initProductionAttributeDefinitions() {
		// top.silverHome = silverHome
		silver.driver.util.PbuildEnv.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_silverHome__ON__silver_driver_util_BuildEnv] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.driver.util.PbuildEnv.i_silverHome)); } };
		// top.silverGen = silverGen
		silver.driver.util.PbuildEnv.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_silverGen__ON__silver_driver_util_BuildEnv] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.driver.util.PbuildEnv.i_silverGen)); } };
		// top.grammarPath = grammarPath
		silver.driver.util.PbuildEnv.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_grammarPath__ON__silver_driver_util_BuildEnv] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.driver.util.PbuildEnv.i_grammarPath)); } };
		// top.defaultSilverGen = silverHome ++ "generated/"
		silver.driver.util.PbuildEnv.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_defaultSilverGen__ON__silver_driver_util_BuildEnv] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.driver.util.PbuildEnv.i_silverHome)), (common.StringCatter)(new common.StringCatter("generated/"))); } };
		// top.defaultGrammarPath = silverHome ++ "grammars/"
		silver.driver.util.PbuildEnv.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_defaultGrammarPath__ON__silver_driver_util_BuildEnv] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.driver.util.PbuildEnv.i_silverHome)), (common.StringCatter)(new common.StringCatter("grammars/"))); } };

	}

	public static final common.NodeFactory<PbuildEnv> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbuildEnv> {

		@Override
		public PbuildEnv invoke(final Object[] children, final Object[] annotations) {
			return new PbuildEnv(children[0], children[1], children[2]);
		}
	};

}
