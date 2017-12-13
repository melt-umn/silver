
package silver.definition.flow.driver;

public final class PmakeDotArrow extends common.FunctionNode {

	public static final int i_p = 0;
	public static final int i_e = 1;
	public static final int i_style = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NPair.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_makeDotArrow;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_e] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PmakeDotArrow(final Object c_p, final Object c_e, final Object c_style) {
		this.child_p = c_p;
		this.child_e = c_e;
		this.child_style = c_style;

	}

	private Object child_p;
	public final common.StringCatter getChild_p() {
		return (common.StringCatter) (child_p = common.Util.demand(child_p));
	}

	private Object child_e;
	public final core.NPair getChild_e() {
		return (core.NPair) (child_e = common.Util.demand(child_e));
	}

	private Object child_style;
	public final common.StringCatter getChild_style() {
		return (common.StringCatter) (child_style = common.Util.demand(child_style));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();
			case i_e: return getChild_e();
			case i_style: return getChild_style();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;
			case i_e: return child_e;
			case i_style: return child_style;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:definition:flow:driver:makeDotArrow";
	}

	public static common.StringCatter invoke(final Object c_p, final Object c_e, final Object c_style) {
		try {
		final common.DecoratedNode context = new PmakeDotArrow(c_p, c_e, c_style).decorate();
		//"\"" ++ p ++ "/" ++ e.fst.dotName ++ "\" -> \"" ++ p ++ "/" ++ e.snd.dotName ++ "\"" ++ style ++ ";\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.driver.PmakeDotArrow.i_p)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.flow.ast.NFlowVertex)context.childDecorated(silver.definition.flow.driver.PmakeDotArrow.i_e).synthesized(core.Init.core_fst__ON__core_Pair)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.flow.driver.Init.silver_definition_flow_driver_dotName__ON__silver_definition_flow_ast_FlowVertex)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" -> \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.driver.PmakeDotArrow.i_p)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.flow.ast.NFlowVertex)context.childDecorated(silver.definition.flow.driver.PmakeDotArrow.i_e).synthesized(core.Init.core_snd__ON__core_Pair)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.flow.driver.Init.silver_definition_flow_driver_dotName__ON__silver_definition_flow_ast_FlowVertex)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.driver.PmakeDotArrow.i_style)), (common.StringCatter)(new common.StringCatter(";\n")))))))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:makeDotArrow", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeDotArrow.invoke(children[0], children[1], children[2]);
		}
	};
}