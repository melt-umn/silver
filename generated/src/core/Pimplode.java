
package core;

public final class Pimplode extends common.FunctionNode {

	public static final int i_sep = 0;
	public static final int i_lst = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_implode;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pimplode(final Object c_sep, final Object c_lst) {
		this.child_sep = c_sep;
		this.child_lst = c_lst;

	}

	private Object child_sep;
	public final common.StringCatter getChild_sep() {
		return (common.StringCatter) (child_sep = common.Util.demand(child_sep));
	}

	private Object child_lst;
	public final common.ConsCell getChild_lst() {
		return (common.ConsCell) (child_lst = common.Util.demand(child_lst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sep: return getChild_sep();
			case i_lst: return getChild_lst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sep: return child_sep;
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
		return "core:implode";
	}

	public static common.StringCatter invoke(final Object c_sep, final Object c_lst) {
		try {
		final common.DecoratedNode context = new Pimplode(c_sep, c_lst).decorate();
		//if null(lst) then "" else head(lst) ++ if null(tail(lst)) then "" else sep ++ implode(sep, tail(lst))
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.Pimplode.i_lst))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(core.Pimplode.i_lst))), (common.StringCatter)(((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.Pimplode.i_lst))); } })) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(core.Pimplode.i_sep)), (common.StringCatter)((common.StringCatter)core.Pimplode.invoke(context.childAsIsLazy(core.Pimplode.i_sep), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.Pimplode.i_lst))); } })))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:implode", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pimplode.invoke(children[0], children[1]);
		}
	};
}