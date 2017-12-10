
package core.monad;

public final class PbindList extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_fn = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.NodeFactory.class };

	public static final int num_local_attrs = Init.count_local__ON__core_monad_bindList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PbindList(final Object c_l, final Object c_fn) {
		this.child_l = c_l;
		this.child_fn = c_fn;

	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_fn;
	public final common.NodeFactory<common.ConsCell> getChild_fn() {
		return (common.NodeFactory<common.ConsCell>) (child_fn = common.Util.demand(child_fn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_fn: return getChild_fn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_fn: return child_fn;

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
		return "core:monad:bindList";
	}

	public static common.ConsCell invoke(final Object c_l, final Object c_fn) {
		try {
		final common.DecoratedNode context = new PbindList(c_l, c_fn).decorate();
		//flatMap(fn, l)
		return (common.ConsCell)(((common.ConsCell)core.PflatMap.invoke(context.childAsIsLazy(core.monad.PbindList.i_fn), context.childAsIsLazy(core.monad.PbindList.i_l))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:monad:bindList", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbindList.invoke(children[0], children[1]);
		}
	};
}