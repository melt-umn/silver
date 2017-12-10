
package silver.definition.flow.driver;

public final class PgetPhantomEdge extends common.FunctionNode {

	public static final int i_f = 0;


	public static final Class<?> childTypes[] = { silver.definition.flow.ast.NFlowDef.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_getPhantomEdge;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_f] = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_inh_attrs];

	}

	public PgetPhantomEdge(final Object c_f) {
		this.child_f = c_f;

	}

	private Object child_f;
	public final silver.definition.flow.ast.NFlowDef getChild_f() {
		return (silver.definition.flow.ast.NFlowDef) (child_f = common.Util.demand(child_f));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;

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
		return "silver:definition:flow:driver:getPhantomEdge";
	}

	public static core.NPair invoke(final Object c_f) {
		try {
		final common.DecoratedNode context = new PgetPhantomEdge(c_f).decorate();
		//case f of extSynFlowDef(_, at) -> pair(lhsSynVertex(at), forwardEqVertex()) end
		return (core.NPair)(new common.PatternLazy<common.DecoratedNode, core.NPair>() { public final core.NPair eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.flow.ast.PextSynFlowDef) { final common.Thunk<Object> __SV_LOCAL___pv5946___sv_tmp_pv_5947 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv5948___sv_pv_5945_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (core.NPair)((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5949_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv5948___sv_pv_5945_at.eval())); } };
return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PlhsSynVertex(__SV_LOCAL_5949_at)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)silver.definition.flow.ast.PforwardEqVertex.invoke()); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NPair)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:flow:driver 'ProductionGraph.sv', 271, 9, 273, 5, 11047, 11132\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.flow.driver.PgetPhantomEdge.i_f)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:getPhantomEdge", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetPhantomEdge.invoke(children[0]);
		}
	};
}