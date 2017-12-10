
package silver.modification.impide;

public final class PpkgToPath extends common.FunctionNode {

	public static final int i_pkg = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_pkgToPath;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PpkgToPath(final Object c_pkg) {
		this.child_pkg = c_pkg;

	}

	private Object child_pkg;
	public final common.StringCatter getChild_pkg() {
		return (common.StringCatter) (child_pkg = common.Util.demand(child_pkg));
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
		return "silver:modification:impide:pkgToPath";
	}

	public static common.StringCatter invoke(final Object c_pkg) {
		try {
		final common.DecoratedNode context = new PpkgToPath(c_pkg).decorate();
		//substitute(".", "/", pkg)
		return (common.StringCatter)(((common.StringCatter)core.Psubstitute.invoke((new common.StringCatter(".")), (new common.StringCatter("/")), context.childAsIsLazy(silver.modification.impide.PpkgToPath.i_pkg))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:pkgToPath", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PpkgToPath.invoke(children[0]);
		}
	};
}