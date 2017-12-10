
package silver.definition.flow.driver;

public final class PcompareFlowVertex extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = { silver.definition.flow.ast.NFlowVertex.class,silver.definition.flow.ast.NFlowVertex.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_compareFlowVertex;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_a] = new common.Lazy[silver.definition.flow.ast.NFlowVertex.num_inh_attrs];
	childInheritedAttributes[i_b] = new common.Lazy[silver.definition.flow.ast.NFlowVertex.num_inh_attrs];

	}

	public PcompareFlowVertex(final Object c_a, final Object c_b) {
		this.child_a = c_a;
		this.child_b = c_b;

	}

	private Object child_a;
	public final silver.definition.flow.ast.NFlowVertex getChild_a() {
		return (silver.definition.flow.ast.NFlowVertex) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final silver.definition.flow.ast.NFlowVertex getChild_b() {
		return (silver.definition.flow.ast.NFlowVertex) (child_b = common.Util.demand(child_b));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_b: return getChild_b();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_b: return child_b;

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
		return "silver:definition:flow:driver:compareFlowVertex";
	}

	public static Integer invoke(final Object c_a, final Object c_b) {
		try {
		final common.DecoratedNode context = new PcompareFlowVertex(c_a, c_b).decorate();
		//if a.unparse < b.unparse then -1 else if a.unparse == b.unparse then 0 else 1
		return (Integer)(((((common.StringCatter)context.childDecorated(silver.definition.flow.driver.PcompareFlowVertex.i_a).synthesized(silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_FlowVertex)).toString().compareTo(((common.StringCatter)context.childDecorated(silver.definition.flow.driver.PcompareFlowVertex.i_b).synthesized(silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_FlowVertex)).toString()) < 0) ? Integer.valueOf((int)-1) : (((common.StringCatter)context.childDecorated(silver.definition.flow.driver.PcompareFlowVertex.i_a).synthesized(silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_FlowVertex)).equals(((common.StringCatter)context.childDecorated(silver.definition.flow.driver.PcompareFlowVertex.i_b).synthesized(silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_FlowVertex))) ? Integer.valueOf((int)0) : Integer.valueOf((int)1))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:compareFlowVertex", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcompareFlowVertex.invoke(children[0], children[1]);
		}
	};
}