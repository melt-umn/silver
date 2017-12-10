
package silver.driver.util;

public final class PfromArgsAndEnv extends common.FunctionNode {

	public static final int i_SILVER_HOME = 0;
	public static final int i_SILVER_GEN = 1;
	public static final int i_GRAMMAR_PATH = 2;
	public static final int i_homeArg = 3;
	public static final int i_genArg = 4;
	public static final int i_pathArg = 5;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_fromArgsAndEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfromArgsAndEnv(final Object c_SILVER_HOME, final Object c_SILVER_GEN, final Object c_GRAMMAR_PATH, final Object c_homeArg, final Object c_genArg, final Object c_pathArg) {
		this.child_SILVER_HOME = c_SILVER_HOME;
		this.child_SILVER_GEN = c_SILVER_GEN;
		this.child_GRAMMAR_PATH = c_GRAMMAR_PATH;
		this.child_homeArg = c_homeArg;
		this.child_genArg = c_genArg;
		this.child_pathArg = c_pathArg;

	}

	private Object child_SILVER_HOME;
	public final common.StringCatter getChild_SILVER_HOME() {
		return (common.StringCatter) (child_SILVER_HOME = common.Util.demand(child_SILVER_HOME));
	}

	private Object child_SILVER_GEN;
	public final common.StringCatter getChild_SILVER_GEN() {
		return (common.StringCatter) (child_SILVER_GEN = common.Util.demand(child_SILVER_GEN));
	}

	private Object child_GRAMMAR_PATH;
	public final common.ConsCell getChild_GRAMMAR_PATH() {
		return (common.ConsCell) (child_GRAMMAR_PATH = common.Util.demand(child_GRAMMAR_PATH));
	}

	private Object child_homeArg;
	public final common.ConsCell getChild_homeArg() {
		return (common.ConsCell) (child_homeArg = common.Util.demand(child_homeArg));
	}

	private Object child_genArg;
	public final common.ConsCell getChild_genArg() {
		return (common.ConsCell) (child_genArg = common.Util.demand(child_genArg));
	}

	private Object child_pathArg;
	public final common.ConsCell getChild_pathArg() {
		return (common.ConsCell) (child_pathArg = common.Util.demand(child_pathArg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_SILVER_HOME: return getChild_SILVER_HOME();
			case i_SILVER_GEN: return getChild_SILVER_GEN();
			case i_GRAMMAR_PATH: return getChild_GRAMMAR_PATH();
			case i_homeArg: return getChild_homeArg();
			case i_genArg: return getChild_genArg();
			case i_pathArg: return getChild_pathArg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_SILVER_HOME: return child_SILVER_HOME;
			case i_SILVER_GEN: return child_SILVER_GEN;
			case i_GRAMMAR_PATH: return child_GRAMMAR_PATH;
			case i_homeArg: return child_homeArg;
			case i_genArg: return child_genArg;
			case i_pathArg: return child_pathArg;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		return "silver:driver:util:fromArgsAndEnv";
	}

	public static silver.driver.util.NBuildEnv invoke(final Object c_SILVER_HOME, final Object c_SILVER_GEN, final Object c_GRAMMAR_PATH, final Object c_homeArg, final Object c_genArg, final Object c_pathArg) {
		try {
		final common.DecoratedNode context = new PfromArgsAndEnv(c_SILVER_HOME, c_SILVER_GEN, c_GRAMMAR_PATH, c_homeArg, c_genArg, c_pathArg).decorate();
		//benv
		return (silver.driver.util.NBuildEnv)(context.localDecorated(silver.driver.util.Init.benv__ON__silver_driver_util_fromArgsAndEnv).undecorate());

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:fromArgsAndEnv", t);
		}
	}

	public static final common.NodeFactory<silver.driver.util.NBuildEnv> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.driver.util.NBuildEnv> {
		@Override
		public silver.driver.util.NBuildEnv invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfromArgsAndEnv.invoke(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};
}