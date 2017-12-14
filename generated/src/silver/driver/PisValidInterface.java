
package silver.driver;

public final class PisValidInterface extends common.FunctionNode {

	public static final int i_file = 0;
	public static final int i_ioin = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_isValidInterface;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PisValidInterface(final Object c_file, final Object c_ioin) {
		this.child_file = c_file;
		this.child_ioin = c_ioin;

	}

	private Object child_file;
	public final common.StringCatter getChild_file() {
		return (common.StringCatter) (child_file = common.Util.demand(child_file));
	}

	private Object child_ioin;
	public final Object getChild_ioin() {
		return (Object) (child_ioin = common.Util.demand(child_ioin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_file: return getChild_file();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_file: return child_file;
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
		return "silver:driver:isValidInterface";
	}

	public static core.NIOVal invoke(final Object c_file, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PisValidInterface(c_file, c_ioin).decorate();
		//if hasInterface.iovalue then ioval(modTime.io, just(modTime.iovalue)) else ioval(hasInterface.io, nothing())
		return (core.NIOVal)((((Boolean)context.localDecorated(silver.driver.Init.hasInterface__ON__silver_driver_isValidInterface).synthesized(core.Init.core_iovalue__ON__core_IOVal)) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.modTime__ON__silver_driver_isValidInterface).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)context.localDecorated(silver.driver.Init.modTime__ON__silver_driver_isValidInterface).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } })); } })) : ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.hasInterface__ON__silver_driver_isValidInterface).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:isValidInterface", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisValidInterface.invoke(children[0], children[1]);
		}
	};
}