
package core.monad;

public final class PevalState extends common.FunctionNode {

	public static final int i_st = 0;
	public static final int i_initialState = 1;


	public static final Class<?> childTypes[] = { core.monad.NState.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__core_monad_evalState;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_st] = new common.Lazy[core.monad.NState.num_inh_attrs];

	}

	public PevalState(final Object c_st, final Object c_initialState) {
		this.child_st = c_st;
		this.child_initialState = c_initialState;

	}

	private Object child_st;
	public final core.monad.NState getChild_st() {
		return (core.monad.NState) (child_st = common.Util.demand(child_st));
	}

	private Object child_initialState;
	public final Object getChild_initialState() {
		return (Object) (child_initialState = common.Util.demand(child_initialState));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_st: return getChild_st();
			case i_initialState: return getChild_initialState();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_st: return child_st;
			case i_initialState: return child_initialState;

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
		return "core:monad:evalState";
	}

	public static Object invoke(final Object c_st, final Object c_initialState) {
		try {
		final common.DecoratedNode context = new PevalState(c_st, c_initialState).decorate();
		//runState(st, initialState).snd
		return (Object)(((Object)((core.NPair)core.monad.PrunState.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(core.monad.PevalState.i_st)), context.childAsIsLazy(core.monad.PevalState.i_initialState))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:monad:evalState", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PevalState.invoke(children[0], children[1]);
		}
	};
}