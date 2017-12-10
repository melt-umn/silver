
package silver.definition.flow.driver;

public final class PpatternStitchPoints extends common.FunctionNode {

	public static final int i_realEnv = 0;
	public static final int i_defs = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_patternStitchPoints;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PpatternStitchPoints(final Object c_realEnv, final Object c_defs) {
		this.child_realEnv = c_realEnv;
		this.child_defs = c_defs;

	}

	private Object child_realEnv;
	public final common.DecoratedNode getChild_realEnv() {
		return (common.DecoratedNode) (child_realEnv = common.Util.demand(child_realEnv));
	}

	private Object child_defs;
	public final common.ConsCell getChild_defs() {
		return (common.ConsCell) (child_defs = common.Util.demand(child_defs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_realEnv: return getChild_realEnv();
			case i_defs: return getChild_defs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_realEnv: return child_realEnv;
			case i_defs: return child_defs;

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
		return "silver:definition:flow:driver:patternStitchPoints";
	}

	public static common.ConsCell invoke(final Object c_realEnv, final Object c_defs) {
		try {
		final common.DecoratedNode context = new PpatternStitchPoints(c_realEnv, c_defs).decorate();
		//case defs of [] -> [] | patternRuleEq(_, matchProd, scrutinee, vars)::rest -> flatMap(patVarStitchPoints(matchProd, scrutinee, realEnv, _), vars) ++ patternStitchPoints(realEnv, rest) | _::rest -> patternStitchPoints(realEnv, rest) end
		return (common.ConsCell)(new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6021___sv_tmp_pv_6022 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6023___sv_pv_6020_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6025___fail_6026 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6024_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_6023___sv_pv_6020_rest.eval())); } };
return ((common.ConsCell)silver.definition.flow.driver.PpatternStitchPoints.invoke(context.childAsIsLazy(silver.definition.flow.driver.PpatternStitchPoints.i_realEnv), __SV_LOCAL_6024_rest)); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.flow.ast.PpatternRuleEq) { final common.Thunk<Object> __SV_LOCAL___pv6030___sv_tmp_pv_6031 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6032___sv_pv_6033_matchProd = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6034___sv_pv_6035_scrutinee = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv6036___sv_pv_6037_vars = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(3); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6038_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_6023___sv_pv_6020_rest.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6039_vars = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv6036___sv_pv_6037_vars.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6040_scrutinee = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv6034___sv_pv_6035_scrutinee.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6041_matchProd = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6032___sv_pv_6033_matchProd.eval())); } };
return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PpatVarStitchPoints.factory.invokePartial(new int[]{0, 1, 2}, new Object[]{__SV_LOCAL_6041_matchProd, common.Thunk.transformUndecorate(__SV_LOCAL_6040_scrutinee), context.childAsIsLazy(silver.definition.flow.driver.PpatternStitchPoints.i_realEnv)}); } }, __SV_LOCAL_6039_vars)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PpatternStitchPoints.invoke(context.childAsIsLazy(silver.definition.flow.driver.PpatternStitchPoints.i_realEnv), __SV_LOCAL_6038_rest)); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_6025___fail_6026.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.flow.ast.NFlowDef)(__SV_LOCAL_6021___sv_tmp_pv_6022.eval())).decorate(context, (common.Lazy[])null)); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:flow:driver 'ProductionGraph.sv', 373, 9, 379, 5, 15184, 15445\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.definition.flow.driver.PpatternStitchPoints.i_defs))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:patternStitchPoints", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PpatternStitchPoints.invoke(children[0], children[1]);
		}
	};
}