
package silver.extension.bidirtransform;

public final class PhasNamedAttr extends common.FunctionNode {

	public static final int i_tyName = 0;
	public static final int i_env = 1;
	public static final int i_attr = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_hasNamedAttr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PhasNamedAttr(final Object c_tyName, final Object c_env, final Object c_attr) {
		this.child_tyName = c_tyName;
		this.child_env = c_env;
		this.child_attr = c_attr;

	}

	private Object child_tyName;
	public final common.StringCatter getChild_tyName() {
		return (common.StringCatter) (child_tyName = common.Util.demand(child_tyName));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}

	private Object child_attr;
	public final common.StringCatter getChild_attr() {
		return (common.StringCatter) (child_attr = common.Util.demand(child_attr));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tyName: return getChild_tyName();
			case i_env: return getChild_env();
			case i_attr: return getChild_attr();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tyName: return child_tyName;
			case i_env: return child_env;
			case i_attr: return child_attr;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:extension:bidirtransform:hasNamedAttr";
	}

	public static Boolean invoke(final Object c_tyName, final Object c_env, final Object c_attr) {
		try {
		final common.DecoratedNode context = new PhasNamedAttr(c_tyName, c_env, c_attr).decorate();
		//containsAttr(getAttrsOn(tyName, env), attr)
		return (Boolean)(((Boolean)silver.extension.bidirtransform.PcontainsAttr.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetAttrsOn.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasNamedAttr.i_tyName), context.childAsIsLazy(silver.extension.bidirtransform.PhasNamedAttr.i_env))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PhasNamedAttr.i_attr))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:hasNamedAttr", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PhasNamedAttr.invoke(children[0], children[1], children[2]);
		}
	};
}