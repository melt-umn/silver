
package silver.extension.bidirtransform;

public final class PattrStrings extends common.FunctionNode {

	public static final int i_tyName = 0;
	public static final int i_env = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_attrStrings;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PattrStrings(final Object c_tyName, final Object c_env) {
		this.child_tyName = c_tyName;
		this.child_env = c_env;

	}

	private Object child_tyName;
	public final common.StringCatter getChild_tyName() {
		return (common.StringCatter) (child_tyName = common.Util.demand(child_tyName));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tyName: return getChild_tyName();
			case i_env: return getChild_env();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tyName: return child_tyName;
			case i_env: return child_env;

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
		return "silver:extension:bidirtransform:attrStrings";
	}

	public static common.ConsCell invoke(final Object c_tyName, final Object c_env) {
		try {
		final common.DecoratedNode context = new PattrStrings(c_tyName, c_env).decorate();
		//map((.ppDebug), getAttrsOn(tyName, env))
		return (common.ConsCell)(((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo, context), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetAttrsOn.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PattrStrings.i_tyName), context.childAsIsLazy(silver.extension.bidirtransform.PattrStrings.i_env))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:attrStrings", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PattrStrings.invoke(children[0], children[1]);
		}
	};
}