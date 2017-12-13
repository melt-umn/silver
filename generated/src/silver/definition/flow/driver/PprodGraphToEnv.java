
package silver.definition.flow.driver;

public final class PprodGraphToEnv extends common.FunctionNode {

	public static final int i_p = 0;


	public static final Class<?> childTypes[] = { silver.definition.flow.driver.NProductionGraph.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_prodGraphToEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_p] = new common.Lazy[silver.definition.flow.driver.NProductionGraph.num_inh_attrs];

	}

	public PprodGraphToEnv(final Object c_p) {
		this.child_p = c_p;

	}

	private Object child_p;
	public final silver.definition.flow.driver.NProductionGraph getChild_p() {
		return (silver.definition.flow.driver.NProductionGraph) (child_p = common.Util.demand(child_p));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;

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
		return "silver:definition:flow:driver:prodGraphToEnv";
	}

	public static core.NPair invoke(final Object c_p) {
		try {
		final common.DecoratedNode context = new PprodGraphToEnv(c_p).decorate();
		//pair(p.prod, p)
		return (core.NPair)(((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PprodGraphToEnv.i_p, silver.definition.flow.driver.Init.silver_definition_flow_driver_prod__ON__silver_definition_flow_driver_ProductionGraph), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.flow.driver.PprodGraphToEnv.i_p)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:prodGraphToEnv", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PprodGraphToEnv.invoke(children[0]);
		}
	};
}