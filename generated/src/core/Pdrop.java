
package core;

public final class Pdrop extends common.FunctionNode {

	public static final int i_number = 0;
	public static final int i_lst = 1;


	public static final Class<?> childTypes[] = { Integer.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_drop;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pdrop(final Object c_number, final Object c_lst) {
		this.child_number = c_number;
		this.child_lst = c_lst;

	}

	private Object child_number;
	public final Integer getChild_number() {
		return (Integer) (child_number = common.Util.demand(child_number));
	}

	private Object child_lst;
	public final common.ConsCell getChild_lst() {
		return (common.ConsCell) (child_lst = common.Util.demand(child_lst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_number: return getChild_number();
			case i_lst: return getChild_lst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_number: return child_number;
			case i_lst: return child_lst;

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
		return "core:drop";
	}

	public static common.ConsCell invoke(final Object c_number, final Object c_lst) {
		try {
		final common.DecoratedNode context = new Pdrop(c_number, c_lst).decorate();
		//if null(lst) || number <= 0 then lst else drop(number - 1, tail(lst))
		return (common.ConsCell)(((((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.Pdrop.i_lst))) || (((Integer)context.childAsIs(core.Pdrop.i_number)) <= Integer.valueOf((int)0))) ? ((common.ConsCell)context.childAsIs(core.Pdrop.i_lst)) : ((common.ConsCell)core.Pdrop.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(core.Pdrop.i_number)) - Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.Pdrop.i_lst))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:drop", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pdrop.invoke(children[0], children[1]);
		}
	};
}