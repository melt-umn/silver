
package silver.util.fixedmap;

public final class Pcreate extends common.FunctionNode {

	public static final int i_lst = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_fixedmap_create;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pcreate(final Object c_lst) {
		this.child_lst = c_lst;

	}

	private Object child_lst;
	public final common.ConsCell getChild_lst() {
		return (common.ConsCell) (child_lst = common.Util.demand(child_lst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lst: return getChild_lst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lst: return child_lst;

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
		return "silver:util:fixedmap:create";
	}

	public static silver.util.fixedmap.NMap invoke(final Object c_lst) {
		try {
		final common.DecoratedNode context = new Pcreate(c_lst).decorate();
		//internalBuild(groupBy(fstStringEq, sortBy(fstStringLte, lst,),),)
		return (silver.util.fixedmap.NMap)(((silver.util.fixedmap.NMap)silver.util.fixedmap.PinternalBuild.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PgroupBy.invoke(silver.util.fixedmap.PfstStringEq.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PsortBy.invoke(silver.util.fixedmap.PfstStringLte.factory, context.childAsIsLazy(silver.util.fixedmap.Pcreate.i_lst))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:fixedmap:create", t);
		}
	}

	public static final common.NodeFactory<silver.util.fixedmap.NMap> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.fixedmap.NMap> {
		@Override
		public silver.util.fixedmap.NMap invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pcreate.invoke(children[0]);
		}
	};
}