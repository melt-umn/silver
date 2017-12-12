
package silver.driver;

public final class PsviPath extends common.FunctionNode {

	public static final int i_r = 0;
	public static final int i_genPath = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_sviPath;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsviPath(final Object c_r, final Object c_genPath) {
		this.child_r = c_r;
		this.child_genPath = c_genPath;

	}

	private Object child_r;
	public final common.DecoratedNode getChild_r() {
		return (common.DecoratedNode) (child_r = common.Util.demand(child_r));
	}

	private Object child_genPath;
	public final common.StringCatter getChild_genPath() {
		return (common.StringCatter) (child_genPath = common.Util.demand(child_genPath));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_r: return getChild_r();
			case i_genPath: return getChild_genPath();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_r: return child_r;
			case i_genPath: return child_genPath;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:driver:sviPath";
	}

	public static common.StringCatter invoke(final Object c_r, final Object c_genPath) {
		try {
		final common.DecoratedNode context = new PsviPath(c_r, c_genPath).decorate();
		//genPath ++ "src/" ++ grammarToPath(r.declaredName) ++ "Silver.svi"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.driver.PsviPath.i_genPath)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("src/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.driver.util.PgrammarToPath.invoke(context.childAsIsSynthesizedLazy(silver.driver.PsviPath.i_r, silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec))), (common.StringCatter)(new common.StringCatter("Silver.svi"))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:sviPath", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsviPath.invoke(children[0], children[1]);
		}
	};
}