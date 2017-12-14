
package silver.driver;

public final class PcheckPreBuild extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_benv = 1;
	public static final int i_buildGrammar = 2;
	public static final int i_ioin = 3;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,silver.driver.util.NBuildEnv.class,common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_checkPreBuild;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_benv] = new common.Lazy[silver.driver.util.NBuildEnv.num_inh_attrs];

	}

	public PcheckPreBuild(final Object c_a, final Object c_benv, final Object c_buildGrammar, final Object c_ioin) {
		this.child_a = c_a;
		this.child_benv = c_benv;
		this.child_buildGrammar = c_buildGrammar;
		this.child_ioin = c_ioin;

	}

	private Object child_a;
	public final common.DecoratedNode getChild_a() {
		return (common.DecoratedNode) (child_a = common.Util.demand(child_a));
	}

	private Object child_benv;
	public final silver.driver.util.NBuildEnv getChild_benv() {
		return (silver.driver.util.NBuildEnv) (child_benv = common.Util.demand(child_benv));
	}

	private Object child_buildGrammar;
	public final common.StringCatter getChild_buildGrammar() {
		return (common.StringCatter) (child_buildGrammar = common.Util.demand(child_buildGrammar));
	}

	private Object child_ioin;
	public final Object getChild_ioin() {
		return (Object) (child_ioin = common.Util.demand(child_ioin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_benv: return getChild_benv();
			case i_buildGrammar: return getChild_buildGrammar();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_benv: return child_benv;
			case i_buildGrammar: return child_buildGrammar;
			case i_ioin: return child_ioin;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:driver:checkPreBuild";
	}

	public static core.NIOVal invoke(final Object c_a, final Object c_benv, final Object c_buildGrammar, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PcheckPreBuild(c_a, c_benv, c_buildGrammar, c_ioin).decorate();
		//ioval(ioin, errors)
		return (core.NIOVal)(((core.NIOVal)new core.Pioval(context.childAsIsLazy(silver.driver.PcheckPreBuild.i_ioin), context.localAsIsLazy(silver.driver.Init.errors__ON__silver_driver_checkPreBuild))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:checkPreBuild", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcheckPreBuild.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}