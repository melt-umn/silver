
package silver.util.fixedmap;

public final class PtreeMapKeyValues extends common.FunctionNode {

	public static final int i_k = 0;
	public static final int i_v = 1;


	public static final Class<?> childTypes[] = { Object.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_fixedmap_treeMapKeyValues;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtreeMapKeyValues(final Object c_k, final Object c_v) {
		this.child_k = c_k;
		this.child_v = c_v;

	}

	private Object child_k;
	public final Object getChild_k() {
		return (Object) (child_k = common.Util.demand(child_k));
	}

	private Object child_v;
	public final common.ConsCell getChild_v() {
		return (common.ConsCell) (child_v = common.Util.demand(child_v));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_k: return getChild_k();
			case i_v: return getChild_v();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_k: return child_k;
			case i_v: return child_v;

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
		return "silver:util:fixedmap:treeMapKeyValues";
	}

	public static common.ConsCell invoke(final Object c_k, final Object c_v) {
		try {
		final common.DecoratedNode context = new PtreeMapKeyValues(c_k, c_v).decorate();
		//if null(v,) then [] else (pair(k, head(v,),) :: treeMapKeyValues(k, tail(v,),))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.util.fixedmap.PtreeMapKeyValues.i_v))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.util.fixedmap.PtreeMapKeyValues.i_k), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(silver.util.fixedmap.PtreeMapKeyValues.i_v))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.fixedmap.PtreeMapKeyValues.invoke(context.childAsIsLazy(silver.util.fixedmap.PtreeMapKeyValues.i_k), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.util.fixedmap.PtreeMapKeyValues.i_v))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:fixedmap:treeMapKeyValues", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtreeMapKeyValues.invoke(children[0], children[1]);
		}
	};
}