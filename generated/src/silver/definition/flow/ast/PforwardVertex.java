
package silver.definition.flow.ast;

public final class PforwardVertex extends common.FunctionNode {

	public static final int i_attrName = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_forwardVertex;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PforwardVertex(final Object c_attrName) {
		this.child_attrName = c_attrName;

	}

	private Object child_attrName;
	public final common.StringCatter getChild_attrName() {
		return (common.StringCatter) (child_attrName = common.Util.demand(child_attrName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_attrName: return getChild_attrName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_attrName: return child_attrName;

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
		return "silver:definition:flow:ast:forwardVertex";
	}

	public static silver.definition.flow.ast.NFlowVertex invoke(final Object c_attrName) {
		try {
		final common.DecoratedNode context = new PforwardVertex(c_attrName).decorate();
		//localVertex("forward", attrName)
		return (silver.definition.flow.ast.NFlowVertex)(((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PlocalVertex((new common.StringCatter("forward")), context.childAsIsLazy(silver.definition.flow.ast.PforwardVertex.i_attrName))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:ast:forwardVertex", t);
		}
	}

	public static final common.NodeFactory<silver.definition.flow.ast.NFlowVertex> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.flow.ast.NFlowVertex> {
		@Override
		public silver.definition.flow.ast.NFlowVertex invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PforwardVertex.invoke(children[0]);
		}
	};
}