
package stdlib.treemap;

public final class PgenTree extends common.FunctionNode {

	public static final int i_i = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = { Integer.class,silver.util.treemap.NTreeMap.class };

	public static final int num_local_attrs = Init.count_local__ON__stdlib_treemap_genTree;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_t] = new common.Lazy[silver.util.treemap.NTreeMap.num_inh_attrs];

	}

	public PgenTree(final Object c_i, final Object c_t) {
		this.child_i = c_i;
		this.child_t = c_t;

	}

	private Object child_i;
	public final Integer getChild_i() {
		return (Integer) (child_i = common.Util.demand(child_i));
	}

	private Object child_t;
	public final silver.util.treemap.NTreeMap getChild_t() {
		return (silver.util.treemap.NTreeMap) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;
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
		return "stdlib:treemap:genTree";
	}

	public static silver.util.treemap.NTreeMap invoke(final Object c_i, final Object c_t) {
		try {
		final common.DecoratedNode context = new PgenTree(c_i, c_t).decorate();
		//if i > 0 then genTree(i - 1, treeInsert(toString(i), i, t,),) else t
		return (silver.util.treemap.NTreeMap)(((((Integer)context.childAsIs(stdlib.treemap.PgenTree.i_i)) > Integer.valueOf((int)0)) ? ((silver.util.treemap.NTreeMap)stdlib.treemap.PgenTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(stdlib.treemap.PgenTree.i_i)) - Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)silver.util.treemap.PtreeInsert.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)context.childAsIs(stdlib.treemap.PgenTree.i_i)))); } }, context.childAsIsLazy(stdlib.treemap.PgenTree.i_i), common.Thunk.transformUndecorate(context.childDecoratedLazy(stdlib.treemap.PgenTree.i_t)))); } })) : context.childDecorated(stdlib.treemap.PgenTree.i_t).undecorate()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function stdlib:treemap:genTree", t);
		}
	}

	public static final common.NodeFactory<silver.util.treemap.NTreeMap> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.treemap.NTreeMap> {
		@Override
		public silver.util.treemap.NTreeMap invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgenTree.invoke(children[0], children[1]);
		}
	};
}