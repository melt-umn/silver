
package core;

public final class Prepeat extends common.FunctionNode {

	public static final int i_v = 0;
	public static final int i_times = 1;


	public static final Class<?> childTypes[] = { Object.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__core_repeat;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Prepeat(final Object c_v, final Object c_times) {
		this.child_v = c_v;
		this.child_times = c_times;

	}

	private Object child_v;
	public final Object getChild_v() {
		return (Object) (child_v = common.Util.demand(child_v));
	}

	private Object child_times;
	public final Integer getChild_times() {
		return (Integer) (child_times = common.Util.demand(child_times));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();
			case i_times: return getChild_times();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;
			case i_times: return child_times;

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
		return "core:repeat";
	}

	public static common.ConsCell invoke(final Object c_v, final Object c_times) {
		try {
		final common.DecoratedNode context = new Prepeat(c_v, c_times).decorate();
		//if times <= 0 then [] else (v :: repeat(v, times - 1))
		return (common.ConsCell)(((((Integer)context.childAsIs(core.Prepeat.i_times)) <= Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(core.Prepeat.i_v), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Prepeat.invoke(context.childAsIsLazy(core.Prepeat.i_v), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(core.Prepeat.i_times)) - Integer.valueOf((int)1)); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:repeat", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Prepeat.invoke(children[0], children[1]);
		}
	};
}