
package silver.definition.flow.driver;

public final class PgenerateDotGraph extends common.FunctionNode {

	public static final int i_specs = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_generateDotGraph;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgenerateDotGraph(final Object c_specs) {
		this.child_specs = c_specs;

	}

	private Object child_specs;
	public final common.ConsCell getChild_specs() {
		return (common.ConsCell) (child_specs = common.Util.demand(child_specs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_specs: return getChild_specs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_specs: return child_specs;

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
		return "silver:definition:flow:driver:generateDotGraph";
	}

	public static common.StringCatter invoke(final Object c_specs) {
		try {
		final common.DecoratedNode context = new PgenerateDotGraph(c_specs).decorate();
		//case specs of [] -> "" | productionGraph(prod, _, _, graph, suspect, _)::t -> "subgraph \"cluster:" ++ prod ++ "\" {\n" ++ implode("", map(makeDotArrow(prod, _, ""), g:toList(graph))) ++ implode("", map(makeDotArrow(prod, _, " [style=dotted]"), suspect)) ++ "}\n" ++ generateDotGraph(t) end
		return (common.StringCatter)(new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6158___sv_tmp_pv_6157 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6159___sv_pv_6160_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.flow.driver.PproductionGraph) { final common.Thunk<Object> __SV_LOCAL___pv6162___sv_pv_6163_prod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6164___sv_tmp_pv_6165 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv6166___sv_tmp_pv_6167 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv6168___sv_pv_6169_graph = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Object)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv6170___sv_pv_6171_suspect = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(4); } };
final common.Thunk<Object> __SV_LOCAL___pv6172___sv_tmp_pv_6173 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(5); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6174_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_6159___sv_pv_6160_t.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6175_suspect = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv6170___sv_pv_6171_suspect.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6176_graph = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL___pv6168___sv_pv_6169_graph.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6177_prod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6162___sv_pv_6163_prod.eval())); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter("subgraph \"cluster:")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_6177_prod.eval())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" {\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PmakeDotArrow.factory.invokePartial(new int[]{0, 2}, new Object[]{__SV_LOCAL_6177_prod, (new common.StringCatter(""))}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.raw.graph.PtoList.invoke(__SV_LOCAL_6176_graph)); } })); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PmakeDotArrow.factory.invokePartial(new int[]{0, 2}, new Object[]{__SV_LOCAL_6177_prod, (new common.StringCatter(" [style=dotted]"))}); } }, __SV_LOCAL_6175_suspect)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("}\n")), (common.StringCatter)((common.StringCatter)silver.definition.flow.driver.PgenerateDotGraph.invoke(__SV_LOCAL_6174_t)))))))); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:flow:driver 'DumpGraph.sv', 104, 9, 112, 5, 3177, 3508\n"))));}}.eval(context, (common.DecoratedNode)((silver.definition.flow.driver.NProductionGraph)(__SV_LOCAL_6158___sv_tmp_pv_6157.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutinee.nil()) { return (common.StringCatter)(new common.StringCatter("")); }return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:flow:driver 'DumpGraph.sv', 104, 9, 112, 5, 3177, 3508\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.definition.flow.driver.PgenerateDotGraph.i_specs))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:generateDotGraph", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgenerateDotGraph.invoke(children[0]);
		}
	};
}