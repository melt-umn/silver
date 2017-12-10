
package silver.definition.flow.driver;

public final class PisLhsInhSet extends common.FunctionNode {

	public static final int i_v = 0;
	public static final int i_inhSet = 1;


	public static final Class<?> childTypes[] = { silver.definition.flow.ast.NFlowVertex.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_isLhsInhSet;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_v] = new common.Lazy[silver.definition.flow.ast.NFlowVertex.num_inh_attrs];

	}

	public PisLhsInhSet(final Object c_v, final Object c_inhSet) {
		this.child_v = c_v;
		this.child_inhSet = c_inhSet;

	}

	private Object child_v;
	public final silver.definition.flow.ast.NFlowVertex getChild_v() {
		return (silver.definition.flow.ast.NFlowVertex) (child_v = common.Util.demand(child_v));
	}

	private Object child_inhSet;
	public final Object getChild_inhSet() {
		return (Object) (child_inhSet = common.Util.demand(child_inhSet));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();
			case i_inhSet: return getChild_inhSet();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;
			case i_inhSet: return child_inhSet;

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
		return "silver:definition:flow:driver:isLhsInhSet";
	}

	public static Boolean invoke(final Object c_v, final Object c_inhSet) {
		try {
		final common.DecoratedNode context = new PisLhsInhSet(c_v, c_inhSet).decorate();
		//case v of lhsInhVertex(a) -> set:contains(a, inhSet) | _ -> false end
		return (Boolean)(((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6092___fail_6093 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return false; } };
return new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.flow.ast.PlhsInhVertex) { final common.Thunk<Object> __SV_LOCAL___pv6097___sv_pv_6096_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6098_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6097___sv_pv_6096_a.eval())); } };
return ((Boolean)silver.util.raw.treeset.Pcontains.invoke(__SV_LOCAL_6098_a, context.childAsIsLazy(silver.definition.flow.driver.PisLhsInhSet.i_inhSet))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)(__SV_LOCAL_6092___fail_6093.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.flow.driver.PisLhsInhSet.i_v)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:isLhsInhSet", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisLhsInhSet.invoke(children[0], children[1]);
		}
	};
}