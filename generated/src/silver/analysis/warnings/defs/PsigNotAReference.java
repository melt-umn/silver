
package silver.analysis.warnings.defs;

public final class PsigNotAReference extends common.FunctionNode {

	public static final int i_sigName = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_defs_sigNotAReference;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsigNotAReference(final Object c_sigName, final Object c_e) {
		this.child_sigName = c_sigName;
		this.child_e = c_e;

	}

	private Object child_sigName;
	public final common.StringCatter getChild_sigName() {
		return (common.StringCatter) (child_sigName = common.Util.demand(child_sigName));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sigName: return getChild_sigName();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sigName: return child_sigName;
			case i_e: return child_e;

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
		return "silver:analysis:warnings:defs:sigNotAReference";
	}

	public static Boolean invoke(final Object c_sigName, final Object c_e) {
		try {
		final common.DecoratedNode context = new PsigNotAReference(c_sigName, c_e).decorate();
		//if null(d) then true else head(d).typerep.isDecorable
		return (Boolean)((((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.analysis.warnings.defs.Init.d__ON__silver_analysis_warnings_defs_sigNotAReference))) ? true : ((Boolean)((silver.definition.type.NType)((silver.definition.env.NDclInfo)core.Phead.invoke(context.localAsIsLazy(silver.analysis.warnings.defs.Init.d__ON__silver_analysis_warnings_defs_sigNotAReference))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorable__ON__silver_definition_type_Type))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:defs:sigNotAReference", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsigNotAReference.invoke(children[0], children[1]);
		}
	};
}