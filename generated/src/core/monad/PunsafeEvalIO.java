
package core.monad;

public final class PunsafeEvalIO extends common.FunctionNode {

	public static final int i_st = 0;


	public static final Class<?> childTypes[] = { core.monad.NIOMonad.class };

	public static final int num_local_attrs = Init.count_local__ON__core_monad_unsafeEvalIO;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_st] = new common.Lazy[core.monad.NIOMonad.num_inh_attrs];

	}

	public PunsafeEvalIO(final Object c_st) {
		this.child_st = c_st;

	}

	private Object child_st;
	public final core.monad.NIOMonad getChild_st() {
		return (core.monad.NIOMonad) (child_st = common.Util.demand(child_st));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_st: return getChild_st();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_st: return child_st;

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
		return "core:monad:unsafeEvalIO";
	}

	public static Object invoke(final Object c_st) {
		try {
		final common.DecoratedNode context = new PunsafeEvalIO(c_st).decorate();
		//evalIO(st, unsafeIO()).iovalue
		return (Object)(((Object)((core.NIOVal)core.monad.PevalIO.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(core.monad.PunsafeEvalIO.i_st)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.PunsafeIO.invoke()); } })).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_iovalue__ON__core_IOVal)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:monad:unsafeEvalIO", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunsafeEvalIO.invoke(children[0]);
		}
	};
}