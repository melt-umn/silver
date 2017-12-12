
package silver.util.treemap;

public final class PtreeConvert extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,silver.util.treemap.NTreeMap.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_treemap_treeConvert;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_t] = new common.Lazy[silver.util.treemap.NTreeMap.num_inh_attrs];

	}

	public PtreeConvert(final Object c_l, final Object c_t) {
		this.child_l = c_l;
		this.child_t = c_t;

	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_t;
	public final silver.util.treemap.NTreeMap getChild_t() {
		return (silver.util.treemap.NTreeMap) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_t: return child_t;

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
		return "silver:util:treemap:treeConvert";
	}

	public static silver.util.treemap.NTreeMap invoke(final Object c_l, final Object c_t) {
		try {
		final common.DecoratedNode context = new PtreeConvert(c_l, c_t).decorate();
		//if null(l) then t else treeConvert(tail(l), treeInsert(head(l).fst, head(l).snd, t))
		return (silver.util.treemap.NTreeMap)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.util.treemap.PtreeConvert.i_l))) ? context.childDecorated(silver.util.treemap.PtreeConvert.i_t).undecorate() : ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeConvert.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.util.treemap.PtreeConvert.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NPair)core.Phead.invoke(context.childAsIsLazy(silver.util.treemap.PtreeConvert.i_l))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NPair)core.Phead.invoke(context.childAsIsLazy(silver.util.treemap.PtreeConvert.i_l))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.PtreeConvert.i_t)))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:treemap:treeConvert", t);
		}
	}

	public static final common.NodeFactory<silver.util.treemap.NTreeMap> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.treemap.NTreeMap> {
		@Override
		public silver.util.treemap.NTreeMap invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtreeConvert.invoke(children[0], children[1]);
		}
	};
}