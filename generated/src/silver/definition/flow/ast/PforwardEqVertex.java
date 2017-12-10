
package silver.definition.flow.ast;

public final class PforwardEqVertex extends common.FunctionNode {



	public static final Class<?> childTypes[] = {  };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_forwardEqVertex;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PforwardEqVertex() {

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		return "silver:definition:flow:ast:forwardEqVertex";
	}

	public static silver.definition.flow.ast.NFlowVertex invoke() {
		try {
		final common.DecoratedNode context = new PforwardEqVertex().decorate();
		//localEqVertex("forward")
		return (silver.definition.flow.ast.NFlowVertex)(((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PlocalEqVertex((new common.StringCatter("forward")))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:ast:forwardEqVertex", t);
		}
	}

	public static final common.NodeFactory<silver.definition.flow.ast.NFlowVertex> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.flow.ast.NFlowVertex> {
		@Override
		public silver.definition.flow.ast.NFlowVertex invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PforwardEqVertex.invoke();
		}
	};
}