
package silver.definition.flow.ast;

// top::ExprVertexInfo ::= vertexType::VertexType 
public final class PhasVertex extends silver.definition.flow.ast.NExprVertexInfo {

	public static final int i_vertexType = 0;


	public static final Class<?> childTypes[] = {silver.definition.flow.ast.NVertexType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_hasVertex;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NExprVertexInfo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NExprVertexInfo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_vertexType] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];

	}

	public PhasVertex(final Object c_vertexType) {
		super();
		this.child_vertexType = c_vertexType;

	}

	private Object child_vertexType;
	public final silver.definition.flow.ast.NVertexType getChild_vertexType() {
		return (silver.definition.flow.ast.NVertexType) (child_vertexType = common.Util.demand(child_vertexType));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_vertexType: return getChild_vertexType();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_vertexType: return child_vertexType;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:hasVertex erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:definition:flow:ast:hasVertex";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PhasVertex> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PhasVertex> {

		@Override
		public PhasVertex invoke(final Object[] children, final Object[] annotations) {
			return new PhasVertex(children[0]);
		}
	};

}
