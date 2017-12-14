
package silver.driver;

public final class PfileTimes extends common.FunctionNode {

	public static final int i_dir = 0;
	public static final int i_is = 1;
	public static final int i_i = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_fileTimes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfileTimes(final Object c_dir, final Object c_is, final Object c_i) {
		this.child_dir = c_dir;
		this.child_is = c_is;
		this.child_i = c_i;

	}

	private Object child_dir;
	public final common.StringCatter getChild_dir() {
		return (common.StringCatter) (child_dir = common.Util.demand(child_dir));
	}

	private Object child_is;
	public final common.ConsCell getChild_is() {
		return (common.ConsCell) (child_is = common.Util.demand(child_is));
	}

	private Object child_i;
	public final Object getChild_i() {
		return (Object) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dir: return getChild_dir();
			case i_is: return getChild_is();
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dir: return child_dir;
			case i_is: return child_is;
			case i_i: return child_i;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:driver:fileTimes";
	}

	public static core.NIOVal invoke(final Object c_dir, final Object c_is, final Object c_i) {
		try {
		final common.DecoratedNode context = new PfileTimes(c_dir, c_is, c_i).decorate();
		//if null(is) then fileTime(dir, i) else if ft.iovalue > rest.iovalue then ioval(rest.io, ft.iovalue) else rest
		return (core.NIOVal)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.PfileTimes.i_is))) ? ((core.NIOVal)core.PfileTime.invoke(context.childAsIsLazy(silver.driver.PfileTimes.i_dir), context.childAsIsLazy(silver.driver.PfileTimes.i_i))) : ((((Integer)context.localDecorated(silver.driver.Init.ft__ON__silver_driver_fileTimes).synthesized(core.Init.core_iovalue__ON__core_IOVal)) > ((Integer)context.localDecorated(silver.driver.Init.rest__ON__silver_driver_fileTimes).synthesized(core.Init.core_iovalue__ON__core_IOVal))) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.rest__ON__silver_driver_fileTimes).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)context.localDecorated(silver.driver.Init.ft__ON__silver_driver_fileTimes).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } })) : context.localDecorated(silver.driver.Init.rest__ON__silver_driver_fileTimes).undecorate())));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:fileTimes", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfileTimes.invoke(children[0], children[1], children[2]);
		}
	};
}