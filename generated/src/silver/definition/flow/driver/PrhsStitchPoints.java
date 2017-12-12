
package silver.definition.flow.driver;

public final class PrhsStitchPoints extends common.FunctionNode {

	public static final int i_rhs = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_rhsStitchPoints;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PrhsStitchPoints(final Object c_rhs) {
		this.child_rhs = c_rhs;

	}

	private Object child_rhs;
	public final common.ConsCell getChild_rhs() {
		return (common.ConsCell) (child_rhs = common.Util.demand(child_rhs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rhs: return getChild_rhs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rhs: return child_rhs;

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
		return "silver:definition:flow:driver:rhsStitchPoints";
	}

	public static common.ConsCell invoke(final Object c_rhs) {
		try {
		final common.DecoratedNode context = new PrhsStitchPoints(c_rhs).decorate();
		//if null(rhs) then [] else if head(rhs).typerep.isDecorable then (nonterminalStitchPoint(head(rhs).typerep.typeName, rhsVertexType(head(rhs).elementName)) :: rhsStitchPoints(tail(rhs))) else rhsStitchPoints(tail(rhs))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.flow.driver.PrhsStitchPoints.i_rhs))) ? ((common.ConsCell)core.Pnil.invoke()) : (((Boolean)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PrhsStitchPoints.i_rhs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NStitchPoint)new silver.definition.flow.driver.PnonterminalStitchPoint(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PrhsStitchPoints.i_rhs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)new silver.definition.flow.ast.PrhsVertexType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PrhsStitchPoints.i_rhs))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PrhsStitchPoints.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.flow.driver.PrhsStitchPoints.i_rhs))); } })); } })) : ((common.ConsCell)silver.definition.flow.driver.PrhsStitchPoints.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.flow.driver.PrhsStitchPoints.i_rhs))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:rhsStitchPoints", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrhsStitchPoints.invoke(children[0]);
		}
	};
}