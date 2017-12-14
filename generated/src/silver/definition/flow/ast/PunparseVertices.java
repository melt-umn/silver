
package silver.definition.flow.ast;

public final class PunparseVertices extends common.FunctionNode {

	public static final int i_fvs = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_unparseVertices;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PunparseVertices(final Object c_fvs) {
		this.child_fvs = c_fvs;

	}

	private Object child_fvs;
	public final common.ConsCell getChild_fvs() {
		return (common.ConsCell) (child_fvs = common.Util.demand(child_fvs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fvs: return getChild_fvs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fvs: return child_fvs;

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
		return "silver:definition:flow:ast:unparseVertices";
	}

	public static common.StringCatter invoke(final Object c_fvs) {
		try {
		final common.DecoratedNode context = new PunparseVertices(c_fvs).decorate();
		//"[" ++ implode(", ", map((.unparse), fvs)) ++ "]"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_FlowVertex, context), context.childAsIsLazy(silver.definition.flow.ast.PunparseVertices.i_fvs))); } })), (common.StringCatter)(new common.StringCatter("]")))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:ast:unparseVertices", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunparseVertices.invoke(children[0]);
		}
	};
}