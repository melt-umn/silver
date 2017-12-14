
package core;

public final class PsflatMap extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_lst = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_sflatMap;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsflatMap(final Object c_f, final Object c_lst) {
		this.child_f = c_f;
		this.child_lst = c_lst;

	}

	private Object child_f;
	public final common.NodeFactory<common.StringCatter> getChild_f() {
		return (common.NodeFactory<common.StringCatter>) (child_f = common.Util.demand(child_f));
	}

	private Object child_lst;
	public final common.ConsCell getChild_lst() {
		return (common.ConsCell) (child_lst = common.Util.demand(child_lst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_lst: return getChild_lst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
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
		return "core:sflatMap";
	}

	public static common.StringCatter invoke(final Object c_f, final Object c_lst) {
		try {
		final common.DecoratedNode context = new PsflatMap(c_f, c_lst).decorate();
		//sconcat(map(f, lst))
		return (common.StringCatter)(((common.StringCatter)core.Psconcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(context.childAsIsLazy(core.PsflatMap.i_f), context.childAsIsLazy(core.PsflatMap.i_lst))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:sflatMap", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsflatMap.invoke(children[0], children[1]);
		}
	};
}