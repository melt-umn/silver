
package silver.driver.util;

public final class PgrammarPairing extends common.FunctionNode {

	public static final int i_r = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_grammarPairing;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgrammarPairing(final Object c_r) {
		this.child_r = c_r;

	}

	private Object child_r;
	public final common.DecoratedNode getChild_r() {
		return (common.DecoratedNode) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_r: return child_r;

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
		return "silver:driver:util:grammarPairing";
	}

	public static core.NPair invoke(final Object c_r) {
		try {
		final common.DecoratedNode context = new PgrammarPairing(c_r).decorate();
		//pair(r.declaredName, r)
		return (core.NPair)(((core.NPair)new core.Ppair(context.childAsIsSynthesizedLazy(silver.driver.util.PgrammarPairing.i_r, silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec), context.childAsIsLazy(silver.driver.util.PgrammarPairing.i_r))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:grammarPairing", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgrammarPairing.invoke(children[0]);
		}
	};
}