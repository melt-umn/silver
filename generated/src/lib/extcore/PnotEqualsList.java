
package lib.extcore;

public final class PnotEqualsList extends common.FunctionNode {

	public static final int i_neq = 0;
	public static final int i_l1 = 1;
	public static final int i_l2 = 2;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_extcore_notEqualsList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PnotEqualsList(final Object c_neq, final Object c_l1, final Object c_l2) {
		this.child_neq = c_neq;
		this.child_l1 = c_l1;
		this.child_l2 = c_l2;

	}

	private Object child_neq;
	public final common.NodeFactory<Boolean> getChild_neq() {
		return (common.NodeFactory<Boolean>) (child_neq = common.Util.demand(child_neq));
	}

	private Object child_l1;
	public final common.ConsCell getChild_l1() {
		return (common.ConsCell) (child_l1 = common.Util.demand(child_l1));
	}

	private Object child_l2;
	public final common.ConsCell getChild_l2() {
		return (common.ConsCell) (child_l2 = common.Util.demand(child_l2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_neq: return getChild_neq();
			case i_l1: return getChild_l1();
			case i_l2: return getChild_l2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_neq: return child_neq;
			case i_l1: return child_l1;
			case i_l2: return child_l2;

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
		return "lib:extcore:notEqualsList";
	}

	public static Boolean invoke(final Object c_neq, final Object c_l1, final Object c_l2) {
		try {
		final common.DecoratedNode context = new PnotEqualsList(c_neq, c_l1, c_l2).decorate();
		//if null(l1) then ! null(l2) else null(l2) || neq(head(l1), head(l2)) || notEqualsList(neq, tail(l1), tail(l2))
		return (Boolean)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(lib.extcore.PnotEqualsList.i_l1))) ? (!((Boolean)core.Pnull.invoke(context.childAsIsLazy(lib.extcore.PnotEqualsList.i_l2)))) : ((((Boolean)core.Pnull.invoke(context.childAsIsLazy(lib.extcore.PnotEqualsList.i_l2))) || ((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(lib.extcore.PnotEqualsList.i_neq)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(lib.extcore.PnotEqualsList.i_l1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(lib.extcore.PnotEqualsList.i_l2))); } }}, null))) || ((Boolean)lib.extcore.PnotEqualsList.invoke(context.childAsIsLazy(lib.extcore.PnotEqualsList.i_neq), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(lib.extcore.PnotEqualsList.i_l1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(lib.extcore.PnotEqualsList.i_l2))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:extcore:notEqualsList", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnotEqualsList.invoke(children[0], children[1], children[2]);
		}
	};
}