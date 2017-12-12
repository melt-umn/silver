
package silver.definition.flow.driver;

public final class PinitialFlowType extends common.FunctionNode {

	public static final int i_x = 0;


	public static final Class<?> childTypes[] = { core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_initialFlowType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_x] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PinitialFlowType(final Object c_x) {
		this.child_x = c_x;

	}

	private Object child_x;
	public final core.NPair getChild_x() {
		return (core.NPair) (child_x = common.Util.demand(child_x));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_x: return getChild_x();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_x: return child_x;

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
		return "silver:definition:flow:driver:initialFlowType";
	}

	public static core.NPair invoke(final Object c_x) {
		try {
		final common.DecoratedNode context = new PinitialFlowType(c_x).decorate();
		//pair(x.fst, g:add(flatMap(toFlatEdges, x.snd), g:empty(compareString)))
		return (core.NPair)(((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PinitialFlowType.i_x, core.Init.core_fst__ON__core_Pair), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.Padd.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(silver.definition.flow.driver.PtoFlatEdges.factory, context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PinitialFlowType.i_x, core.Init.core_snd__ON__core_Pair))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.Pempty.invoke(core.PcompareString.factory)); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:initialFlowType", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PinitialFlowType.invoke(children[0]);
		}
	};
}