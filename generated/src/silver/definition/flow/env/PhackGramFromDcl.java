
package silver.definition.flow.env;

public final class PhackGramFromDcl extends common.FunctionNode {

	public static final int i_qn = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_hackGramFromDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PhackGramFromDcl(final Object c_qn) {
		this.child_qn = c_qn;

	}

	private Object child_qn;
	public final common.DecoratedNode getChild_qn() {
		return (common.DecoratedNode) (child_qn = common.Util.demand(child_qn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;

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
		return "silver:definition:flow:env:hackGramFromDcl";
	}

	public static common.StringCatter invoke(final Object c_qn) {
		try {
		final common.DecoratedNode context = new PhackGramFromDcl(c_qn).decorate();
		//if null(qn.errors) then qn.dcl.sourceGrammar else ""
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsSynthesizedLazy(silver.definition.flow.env.PhackGramFromDcl.i_qn, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur))) ? ((common.StringCatter)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childAsIs(silver.definition.flow.env.PhackGramFromDcl.i_qn)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameAttrOccur)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo)) : (new common.StringCatter(""))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:env:hackGramFromDcl", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PhackGramFromDcl.invoke(children[0]);
		}
	};
}