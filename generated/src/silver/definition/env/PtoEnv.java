
package silver.definition.env;

public final class PtoEnv extends common.FunctionNode {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_toEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoEnv(final Object c_d) {
		this.child_d = c_d;

	}

	private Object child_d;
	public final common.ConsCell getChild_d() {
		return (common.ConsCell) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;

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
		return "silver:definition:env:toEnv";
	}

	public static common.DecoratedNode invoke(final Object c_d) {
		try {
		final common.DecoratedNode context = new PtoEnv(c_d).decorate();
		//newScopeEnv(d, emptyEnv())
		return (common.DecoratedNode)(((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.childAsIsLazy(silver.definition.env.PtoEnv.i_d), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PemptyEnv.invoke()); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:toEnv", t);
		}
	}

	public static final common.NodeFactory<common.DecoratedNode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.DecoratedNode> {
		@Override
		public common.DecoratedNode invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoEnv.invoke(children[0]);
		}
	};
}