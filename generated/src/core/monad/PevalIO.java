
package core.monad;

public final class PevalIO extends common.FunctionNode {

	public static final int i_st = 0;
	public static final int i_ioIn = 1;


	public static final Class<?> childTypes[] = { core.monad.NIOMonad.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__core_monad_evalIO;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_st] = new common.Lazy[core.monad.NIOMonad.num_inh_attrs];

	}

	public PevalIO(final Object c_st, final Object c_ioIn) {
		this.child_st = c_st;
		this.child_ioIn = c_ioIn;

	}

	private Object child_st;
	public final core.monad.NIOMonad getChild_st() {
		return (core.monad.NIOMonad) (child_st = common.Util.demand(child_st));
	}

	private Object child_ioIn;
	public final Object getChild_ioIn() {
		return (Object) (child_ioIn = common.Util.demand(child_ioIn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_st: return getChild_st();
			case i_ioIn: return getChild_ioIn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_st: return child_st;
			case i_ioIn: return child_ioIn;

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
		return "core:monad:evalIO";
	}

	public static core.NIOVal invoke(final Object c_st, final Object c_ioIn) {
		try {
		final common.DecoratedNode context = new PevalIO(c_st, c_ioIn).decorate();
		//ioval(st.stateOut, st.stateVal)
		return (core.NIOVal)(((core.NIOVal)new core.Pioval(context.childDecoratedSynthesizedLazy(core.monad.PevalIO.i_st, core.monad.Init.core_monad_stateOut__ON__core_monad_IOMonad), context.childDecoratedSynthesizedLazy(core.monad.PevalIO.i_st, core.monad.Init.core_monad_stateVal__ON__core_monad_IOMonad))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:monad:evalIO", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PevalIO.invoke(children[0], children[1]);
		}
	};
}