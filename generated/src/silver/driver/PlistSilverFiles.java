
package silver.driver;

public final class PlistSilverFiles extends common.FunctionNode {

	public static final int i_dir = 0;
	public static final int i_ioin = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_listSilverFiles;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PlistSilverFiles(final Object c_dir, final Object c_ioin) {
		this.child_dir = c_dir;
		this.child_ioin = c_ioin;

	}

	private Object child_dir;
	public final common.StringCatter getChild_dir() {
		return (common.StringCatter) (child_dir = common.Util.demand(child_dir));
	}

	private Object child_ioin;
	public final Object getChild_ioin() {
		return (Object) (child_ioin = common.Util.demand(child_ioin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dir: return getChild_dir();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dir: return child_dir;
			case i_ioin: return child_ioin;

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
		return "silver:driver:listSilverFiles";
	}

	public static core.NIOVal invoke(final Object c_dir, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PlistSilverFiles(c_dir, c_ioin).decorate();
		//ioval(files.io, filter(isValidSilverFile, files.iovalue))
		return (core.NIOVal)(((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.files__ON__silver_driver_listSilverFiles).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfilter.invoke(silver.driver.PisValidSilverFile.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.driver.Init.files__ON__silver_driver_listSilverFiles).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:listSilverFiles", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PlistSilverFiles.invoke(children[0], children[1]);
		}
	};
}