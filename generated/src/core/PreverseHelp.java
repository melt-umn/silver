
package core;

public final class PreverseHelp extends common.FunctionNode {

	public static final int i_lst = 0;
	public static final int i_sofar = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_reverseHelp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PreverseHelp(final Object c_lst, final Object c_sofar) {
		this.child_lst = c_lst;
		this.child_sofar = c_sofar;

	}

	private Object child_lst;
	public final common.ConsCell getChild_lst() {
		return (common.ConsCell) (child_lst = common.Util.demand(child_lst));
	}

	private Object child_sofar;
	public final common.ConsCell getChild_sofar() {
		return (common.ConsCell) (child_sofar = common.Util.demand(child_sofar));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lst: return getChild_lst();
			case i_sofar: return getChild_sofar();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lst: return child_lst;
			case i_sofar: return child_sofar;

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
		return "core:reverseHelp";
	}

	public static common.ConsCell invoke(final Object c_lst, final Object c_sofar) {
		try {
		final common.DecoratedNode context = new PreverseHelp(c_lst, c_sofar).decorate();
		//if null(lst) then sofar else reverseHelp(tail(lst), (head(lst) :: sofar))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.PreverseHelp.i_lst))) ? ((common.ConsCell)context.childAsIs(core.PreverseHelp.i_sofar)) : ((common.ConsCell)core.PreverseHelp.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.PreverseHelp.i_lst))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.PreverseHelp.i_lst))); } }, context.childAsIsLazy(core.PreverseHelp.i_sofar))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:reverseHelp", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PreverseHelp.invoke(children[0], children[1]);
		}
	};
}