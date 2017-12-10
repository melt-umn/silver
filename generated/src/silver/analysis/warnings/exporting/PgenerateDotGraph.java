
package silver.analysis.warnings.exporting;

public final class PgenerateDotGraph extends common.FunctionNode {

	public static final int i_specs = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_exporting_generateDotGraph;
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
		return "silver:analysis:warnings:exporting:generateDotGraph";
	}

	public static common.StringCatter invoke(final Object c_specs) {
		try {
		final common.DecoratedNode context = new PgenerateDotGraph(c_specs).decorate();
		//case specs of [] -> "" | h::t -> "\"" ++ h.declaredName ++ "\"[label=\"" ++ h.declaredName ++ "\"];\n" ++ implode("", map(makeDotArrow(h.declaredName, _), h.moduleNames)) ++ generateDotGraph(t) end
		return (common.StringCatter)(new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_9208___sv_pv_9209_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_9210___sv_pv_9207_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9211_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_9210___sv_pv_9207_t.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9212_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL_9208___sv_pv_9209_h.eval())); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter("\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_9212_h.eval())).synthesized(silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\"[label=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_9212_h.eval())).synthesized(silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\"];\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.analysis.warnings.exporting.PmakeDotArrow.factory.invokePartial(new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_9212_h.eval())).synthesized(silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec)); } }}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)(__SV_LOCAL_9212_h.eval())).synthesized(silver.driver.util.Init.silver_definition_env_moduleNames__ON__silver_driver_util_RootSpec)); } })); } })), (common.StringCatter)((common.StringCatter)silver.analysis.warnings.exporting.PgenerateDotGraph.invoke(__SV_LOCAL_9211_t)))))))); } }).eval()); } }).eval()); }
else if(scrutinee.nil()) { return (common.StringCatter)(new common.StringCatter("")); }return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:analysis:warnings:exporting 'Graph.sv', 49, 9, 55, 5, 1229, 1452\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.analysis.warnings.exporting.PgenerateDotGraph.i_specs))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:exporting:generateDotGraph", t);
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