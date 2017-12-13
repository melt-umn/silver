
package silver.driver.util;

public final class PisExportedBy extends common.FunctionNode {

	public static final int i_target = 0;
	public static final int i_sources = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_isExportedBy;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PisExportedBy(final Object c_target, final Object c_sources, final Object c_e) {
		this.child_target = c_target;
		this.child_sources = c_sources;
		this.child_e = c_e;

	}

	private Object child_target;
	public final common.StringCatter getChild_target() {
		return (common.StringCatter) (child_target = common.Util.demand(child_target));
	}

	private Object child_sources;
	public final common.ConsCell getChild_sources() {
		return (common.ConsCell) (child_sources = common.Util.demand(child_sources));
	}

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_target: return getChild_target();
			case i_sources: return getChild_sources();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_target: return child_target;
			case i_sources: return child_sources;
			case i_e: return child_e;

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
		return "silver:driver:util:isExportedBy";
	}

	public static Boolean invoke(final Object c_target, final Object c_sources, final Object c_e) {
		try {
		final common.DecoratedNode context = new PisExportedBy(c_target, c_sources, c_e).decorate();
		//contains(target, computeOptionalDeps(sources, e))
		return (Boolean)(((Boolean)silver.util.Pcontains.invoke(context.childAsIsLazy(silver.driver.util.PisExportedBy.i_target), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PcomputeOptionalDeps.invoke(context.childAsIsLazy(silver.driver.util.PisExportedBy.i_sources), context.childAsIsLazy(silver.driver.util.PisExportedBy.i_e))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:isExportedBy", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisExportedBy.invoke(children[0], children[1], children[2]);
		}
	};
}