
package silver.definition.flow.driver;

public final class PfindBrandNewEdges extends common.FunctionNode {

	public static final int i_candidates = 0;
	public static final int i_currentFlowType = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_findBrandNewEdges;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfindBrandNewEdges(final Object c_candidates, final Object c_currentFlowType) {
		this.child_candidates = c_candidates;
		this.child_currentFlowType = c_currentFlowType;

	}

	private Object child_candidates;
	public final common.ConsCell getChild_candidates() {
		return (common.ConsCell) (child_candidates = common.Util.demand(child_candidates));
	}

	private Object child_currentFlowType;
	public final Object getChild_currentFlowType() {
		return (Object) (child_currentFlowType = common.Util.demand(child_currentFlowType));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_candidates: return getChild_candidates();
			case i_currentFlowType: return getChild_currentFlowType();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_candidates: return child_candidates;
			case i_currentFlowType: return child_currentFlowType;

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
		return "silver:definition:flow:driver:findBrandNewEdges";
	}

	public static common.ConsCell invoke(final Object c_candidates, final Object c_currentFlowType) {
		try {
		final common.DecoratedNode context = new PfindBrandNewEdges(c_candidates, c_currentFlowType).decorate();
		//if null(candidates) then [] else newEdges ++ findBrandNewEdges(tail(candidates), currentFlowType)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.flow.driver.PfindBrandNewEdges.i_candidates))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(context.localAsIsLazy(silver.definition.flow.driver.Init.newEdges__ON__silver_definition_flow_driver_findBrandNewEdges), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PfindBrandNewEdges.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.flow.driver.PfindBrandNewEdges.i_candidates))); } }, context.childAsIsLazy(silver.definition.flow.driver.PfindBrandNewEdges.i_currentFlowType))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:findBrandNewEdges", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindBrandNewEdges.invoke(children[0], children[1]);
		}
	};
}