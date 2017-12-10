
package silver.definition.core;

public final class PzipFilterDcls extends common.FunctionNode {

	public static final int i_at = 0;
	public static final int i_occ = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_zipFilterDcls;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PzipFilterDcls(final Object c_at, final Object c_occ) {
		this.child_at = c_at;
		this.child_occ = c_occ;

	}

	private Object child_at;
	public final common.ConsCell getChild_at() {
		return (common.ConsCell) (child_at = common.Util.demand(child_at));
	}

	private Object child_occ;
	public final common.ConsCell getChild_occ() {
		return (common.ConsCell) (child_occ = common.Util.demand(child_occ));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_at: return getChild_at();
			case i_occ: return getChild_occ();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_at: return child_at;
			case i_occ: return child_occ;

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
		return "silver:definition:core:zipFilterDcls";
	}

	public static common.ConsCell invoke(final Object c_at, final Object c_occ) {
		try {
		final common.DecoratedNode context = new PzipFilterDcls(c_at, c_occ).decorate();
		//if null(at) then [] else if null(head(occ)) then zipFilterDcls(tail(at), tail(occ)) else (head(at) :: zipFilterDcls(tail(at), tail(occ)))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.core.PzipFilterDcls.i_at))) ? ((common.ConsCell)core.Pnil.invoke()) : (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.childAsIsLazy(silver.definition.core.PzipFilterDcls.i_occ))); } })) ? ((common.ConsCell)silver.definition.core.PzipFilterDcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.core.PzipFilterDcls.i_at))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.core.PzipFilterDcls.i_occ))); } })) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)core.Phead.invoke(context.childAsIsLazy(silver.definition.core.PzipFilterDcls.i_at))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.core.PzipFilterDcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.core.PzipFilterDcls.i_at))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.core.PzipFilterDcls.i_occ))); } })); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:zipFilterDcls", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PzipFilterDcls.invoke(children[0], children[1]);
		}
	};
}