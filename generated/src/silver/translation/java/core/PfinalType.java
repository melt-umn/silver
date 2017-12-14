
package silver.translation.java.core;

public final class PfinalType extends common.FunctionNode {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_core_finalType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfinalType(final Object c_e) {
		this.child_e = c_e;

	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;

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
		return "silver:translation:java:core:finalType";
	}

	public static silver.definition.type.NType invoke(final Object c_e) {
		try {
		final common.DecoratedNode context = new PfinalType(c_e).decorate();
		//performSubstitution(e.typerep, e.finalSubst)
		return (silver.definition.type.NType)(((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childAsIsSynthesizedLazy(silver.translation.java.core.PfinalType.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)((common.DecoratedNode)context.childAsIs(silver.translation.java.core.PfinalType.i_e)).inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_Expr)); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:core:finalType", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NType> {
		@Override
		public silver.definition.type.NType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfinalType.invoke(children[0]);
		}
	};
}