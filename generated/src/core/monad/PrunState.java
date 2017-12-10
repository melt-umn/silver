
package core.monad;

public final class PrunState extends common.FunctionNode {

	public static final int i_st = 0;
	public static final int i_initialState = 1;


	public static final Class<?> childTypes[] = { core.monad.NState.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__core_monad_runState;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_st] = new common.Lazy[core.monad.NState.num_inh_attrs];

	}

	public PrunState(final Object c_st, final Object c_initialState) {
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
		return "core:monad:runState";
	}

	public static core.NPair invoke(final Object c_st, final Object c_initialState) {
		try {
		final common.DecoratedNode context = new PrunState(c_st, c_initialState).decorate();
		//pair(st.stateOut, st.stateVal)
		return (core.NPair)(((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(core.monad.PrunState.i_st, core.monad.Init.core_monad_stateOut__ON__core_monad_State), context.childDecoratedSynthesizedLazy(core.monad.PrunState.i_st, core.monad.Init.core_monad_stateVal__ON__core_monad_State))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:monad:runState", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrunState.invoke(children[0], children[1]);
		}
	};
}