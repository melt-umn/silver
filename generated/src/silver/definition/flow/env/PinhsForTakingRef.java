
package silver.definition.flow.env;

public final class PinhsForTakingRef extends common.FunctionNode {

	public static final int i_nt = 0;
	public static final int i_flowEnv = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_inhsForTakingRef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PinhsForTakingRef(final Object c_nt, final Object c_flowEnv) {
		this.child_nt = c_nt;
		this.child_flowEnv = c_flowEnv;

	}

	private Object child_nt;
	public final common.StringCatter getChild_nt() {
		return (common.StringCatter) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_flowEnv;
	public final common.DecoratedNode getChild_flowEnv() {
		return (common.DecoratedNode) (child_flowEnv = common.Util.demand(child_flowEnv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nt: return getChild_nt();
			case i_flowEnv: return getChild_flowEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;
			case i_flowEnv: return child_flowEnv;

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
		return "silver:definition:flow:env:inhsForTakingRef";
	}

	public static common.ConsCell invoke(final Object c_nt, final Object c_flowEnv) {
		try {
		final common.DecoratedNode context = new PinhsForTakingRef(c_nt, c_flowEnv).decorate();
		//case getInhsForNtRef(nt, flowEnv) of ntRefFlowDef(_, inhs)::_ -> inhs | _ -> [] end
		return (common.ConsCell)(((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4990___fail_4991 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_4993___sv_tmp_pv_4992 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_4994___sv_tmp_pv_4995 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.flow.ast.PntRefFlowDef) { final common.Thunk<Object> __SV_LOCAL___pv5000___sv_tmp_pv_5001 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv5002___sv_pv_4999_inhs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5003_inhs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv5002___sv_pv_4999_inhs.eval())); } };
return ((common.ConsCell)(__SV_LOCAL_5003_inhs.eval())); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_4990___fail_4991.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.flow.ast.NFlowDef)(__SV_LOCAL_4993___sv_tmp_pv_4992.eval())).decorate(context, (common.Lazy[])null)); }return ((common.ConsCell)(__SV_LOCAL_4990___fail_4991.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)silver.definition.flow.env.PgetInhsForNtRef.invoke(context.childAsIsLazy(silver.definition.flow.env.PinhsForTakingRef.i_nt), context.childAsIsLazy(silver.definition.flow.env.PinhsForTakingRef.i_flowEnv)))); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:env:inhsForTakingRef", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PinhsForTakingRef.invoke(children[0], children[1]);
		}
	};
}