
package core.monad;

public final class PreturnList extends common.FunctionNode {

	public static final int i_x = 0;


	public static final Class<?> childTypes[] = { Object.class };

	public static final int num_local_attrs = Init.count_local__ON__core_monad_returnList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PreturnList(final Object c_x) {
		this.child_x = c_x;

	}

	private Object child_x;
	public final Object getChild_x() {
		return (Object) (child_x = common.Util.demand(child_x));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_x: return getChild_x();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_x: return child_x;

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
		return "core:monad:returnList";
	}

	public static common.ConsCell invoke(final Object c_x) {
		try {
		final common.DecoratedNode context = new PreturnList(c_x).decorate();
		//[ x ]
		return (common.ConsCell)(((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(core.monad.PreturnList.i_x), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:monad:returnList", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PreturnList.invoke(children[0]);
		}
	};
}