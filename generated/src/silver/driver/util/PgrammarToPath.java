
package silver.driver.util;

public final class PgrammarToPath extends common.FunctionNode {

	public static final int i_g = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_grammarToPath;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgrammarToPath(final Object c_g) {
		this.child_g = c_g;

	}

	private Object child_g;
	public final common.StringCatter getChild_g() {
		return (common.StringCatter) (child_g = common.Util.demand(child_g));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_g: return getChild_g();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_g: return child_g;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:driver:util:grammarToPath";
	}

	public static common.StringCatter invoke(final Object c_g) {
		try {
		final common.DecoratedNode context = new PgrammarToPath(c_g).decorate();
		//substitute(":", "/", g) ++ "/"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)((common.StringCatter)core.Psubstitute.invoke((new common.StringCatter(":")), (new common.StringCatter("/")), context.childAsIsLazy(silver.driver.util.PgrammarToPath.i_g))), (common.StringCatter)(new common.StringCatter("/"))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:grammarToPath", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgrammarToPath.invoke(children[0]);
		}
	};
}