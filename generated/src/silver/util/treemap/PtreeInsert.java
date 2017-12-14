
package silver.util.treemap;

public final class PtreeInsert extends common.FunctionNode {

	public static final int i_x = 0;
	public static final int i_v = 1;
	public static final int i_t = 2;


	public static final Class<?> childTypes[] = { Object.class,Object.class,silver.util.treemap.NTreeMap.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_treemap_treeInsert;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_t] = new common.Lazy[silver.util.treemap.NTreeMap.num_inh_attrs];

	}

	public PtreeInsert(final Object c_x, final Object c_v, final Object c_t) {
		this.child_x = c_x;
		this.child_v = c_v;
		this.child_t = c_t;

	}

	private Object child_x;
	public final Object getChild_x() {
		return (Object) (child_x = common.Util.demand(child_x));
	}

	private Object child_v;
	public final Object getChild_v() {
		return (Object) (child_v = common.Util.demand(child_v));
	}

	private Object child_t;
	public final silver.util.treemap.NTreeMap getChild_t() {
		return (silver.util.treemap.NTreeMap) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_x: return getChild_x();
			case i_v: return getChild_v();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_x: return child_x;
			case i_v: return child_v;
			case i_t: return child_t;

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
		return "silver:util:treemap:treeInsert";
	}

	public static silver.util.treemap.NTreeMap invoke(final Object c_x, final Object c_v, final Object c_t) {
		try {
		final common.DecoratedNode context = new PtreeInsert(c_x, c_v, c_t).decorate();
		//t.treeInsert.makeBlack
		return (silver.util.treemap.NTreeMap)(((silver.util.treemap.NTreeMap)((silver.util.treemap.NTreeMap)context.childDecorated(silver.util.treemap.PtreeInsert.i_t).synthesized(silver.util.treemap.Init.silver_util_treemap_treeInsert__ON__silver_util_treemap_TreeMap)).decorate(context, (common.Lazy[])null).synthesized(silver.util.treemap.Init.silver_util_treemap_makeBlack__ON__silver_util_treemap_TreeMap)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:treemap:treeInsert", t);
		}
	}

	public static final common.NodeFactory<silver.util.treemap.NTreeMap> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.treemap.NTreeMap> {
		@Override
		public silver.util.treemap.NTreeMap invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtreeInsert.invoke(children[0], children[1], children[2]);
		}
	};
}