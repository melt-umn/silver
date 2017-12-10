
package silver.driver.util;

public final class PgatherFlowEnv extends common.FunctionNode {

	public static final int i_deps = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_gatherFlowEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgatherFlowEnv(final Object c_deps, final Object c_e) {
		this.child_deps = c_deps;
		this.child_e = c_e;

	}

	private Object child_deps;
	public final common.ConsCell getChild_deps() {
		return (common.ConsCell) (child_deps = common.Util.demand(child_deps));
	}

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_deps: return getChild_deps();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_deps: return child_deps;
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
		return "silver:driver:util:gatherFlowEnv";
	}

	public static common.ConsCell invoke(final Object c_deps, final Object c_e) {
		try {
		final common.DecoratedNode context = new PgatherFlowEnv(c_deps, c_e).decorate();
		//if null(deps) then [] else case searchEnvTree(head(deps), e) of r::_ -> r.flowDefs ++ gatherFlowEnv(tail(deps), e) | [] -> gatherFlowEnv(tail(deps), e) end
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.util.PgatherFlowEnv.i_deps))) ? ((common.ConsCell)core.Pnil.invoke()) : new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_4285___sv_pv_4284_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_4286___sv_tmp_pv_4287 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4288_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL_4285___sv_pv_4284_r.eval())); } };
return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)(__SV_LOCAL_4288_r.eval())).synthesized(silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_driver_util_RootSpec)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PgatherFlowEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.util.PgatherFlowEnv.i_deps))); } }, context.childAsIsLazy(silver.driver.util.PgatherFlowEnv.i_e))); } })); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)silver.driver.util.PgatherFlowEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.util.PgatherFlowEnv.i_deps))); } }, context.childAsIsLazy(silver.driver.util.PgatherFlowEnv.i_e))); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:driver:util 'RootSpec.sv', 180, 14, 183, 17, 6223, 6397\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PgatherFlowEnv.i_deps))); } }, context.childAsIsLazy(silver.driver.util.PgatherFlowEnv.i_e))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:gatherFlowEnv", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgatherFlowEnv.invoke(children[0], children[1]);
		}
	};
}