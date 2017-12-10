
package silver.definition.flow.driver;

public final class PcollectInhs extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_l = 1;


	public static final Class<?> childTypes[] = { silver.definition.flow.ast.NFlowVertex.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_collectInhs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_f] = new common.Lazy[silver.definition.flow.ast.NFlowVertex.num_inh_attrs];

	}

	public PcollectInhs(final Object c_f, final Object c_l) {
		this.child_f = c_f;
		this.child_l = c_l;

	}

	private Object child_f;
	public final silver.definition.flow.ast.NFlowVertex getChild_f() {
		return (silver.definition.flow.ast.NFlowVertex) (child_f = common.Util.demand(child_f));
	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_l: return child_l;

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
		return "silver:definition:flow:driver:collectInhs";
	}

	public static common.ConsCell invoke(final Object c_f, final Object c_l) {
		try {
		final common.DecoratedNode context = new PcollectInhs(c_f, c_l).decorate();
		//case f of lhsInhVertex(a) -> (a :: l) | _ -> l end
		return (common.ConsCell)(((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6235___fail_6236 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.flow.driver.PcollectInhs.i_l)); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.flow.ast.PlhsInhVertex) { final common.Thunk<Object> __SV_LOCAL___pv6240___sv_pv_6239_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6241_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6240___sv_pv_6239_a.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(__SV_LOCAL_6241_a, context.childAsIsLazy(silver.definition.flow.driver.PcollectInhs.i_l))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_6235___fail_6236.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.flow.driver.PcollectInhs.i_f)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:collectInhs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcollectInhs.invoke(children[0], children[1]);
		}
	};
}