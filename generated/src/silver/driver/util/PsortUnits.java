
package silver.driver.util;

public final class PsortUnits extends common.FunctionNode {

	public static final int i_c1 = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_sortUnits;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsortUnits(final Object c_c1) {
		this.child_c1 = c_c1;

	}

	private Object child_c1;
	public final common.ConsCell getChild_c1() {
		return (common.ConsCell) (child_c1 = common.Util.demand(child_c1));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_c1: return getChild_c1();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_c1: return child_c1;

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
		return "silver:driver:util:sortUnits";
	}

	public static common.ConsCell invoke(final Object c_c1) {
		try {
		final common.DecoratedNode context = new PsortUnits(c_c1).decorate();
		//sortBy(unitLTE, c1)
		return (common.ConsCell)(((common.ConsCell)core.PsortBy.invoke(silver.driver.util.PunitLTE.factory, context.childAsIsLazy(silver.driver.util.PsortUnits.i_c1))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:sortUnits", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsortUnits.invoke(children[0]);
		}
	};
}