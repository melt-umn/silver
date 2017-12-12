
package silver.definition.flow.driver;

public final class PexpandGraph extends common.FunctionNode {

	public static final int i_v = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,silver.definition.flow.driver.NProductionGraph.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_expandGraph;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.flow.driver.NProductionGraph.num_inh_attrs];

	}

	public PexpandGraph(final Object c_v, final Object c_e) {
		this.child_v = c_v;
		this.child_e = c_e;

	}

	private Object child_v;
	public final common.ConsCell getChild_v() {
		return (common.ConsCell) (child_v = common.Util.demand(child_v));
	}

	private Object child_e;
	public final silver.definition.flow.driver.NProductionGraph getChild_e() {
		return (silver.definition.flow.driver.NProductionGraph) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;
			case i_e: return child_e;

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
		return "silver:definition:flow:driver:expandGraph";
	}

	public static common.ConsCell invoke(final Object c_v, final Object c_e) {
		try {
		final common.DecoratedNode context = new PexpandGraph(c_v, c_e).decorate();
		//set:toList(set:add(v, foldr(set:union, set:empty(compareFlowVertex), map(e.edgeMap, v))))
		return (common.ConsCell)(((common.ConsCell)silver.util.raw.treeset.PtoList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treeset.Padd.invoke(context.childAsIsLazy(silver.definition.flow.driver.PexpandGraph.i_v), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pfoldr.invoke(silver.util.raw.treeset.Punion.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treeset.Pempty.invoke(silver.definition.flow.driver.PcompareFlowVertex.factory)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PexpandGraph.i_e, silver.definition.flow.driver.Init.silver_definition_flow_driver_edgeMap__ON__silver_definition_flow_driver_ProductionGraph), context.childAsIsLazy(silver.definition.flow.driver.PexpandGraph.i_v))); } })); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:expandGraph", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PexpandGraph.invoke(children[0], children[1]);
		}
	};
}