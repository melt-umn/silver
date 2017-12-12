
package silver.definition.env;

public final class PoneEnvScope extends common.FunctionNode {

	public static final int i_eis = 0;


	public static final Class<?> childTypes[] = { Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_oneEnvScope;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PoneEnvScope(final Object c_eis) {
		this.child_eis = c_eis;

	}

	private Object child_eis;
	public final Object getChild_eis() {
		return (Object) (child_eis = common.Util.demand(child_eis));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_eis: return getChild_eis();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_eis: return child_eis;

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
		return "silver:definition:env:oneEnvScope";
	}

	public static common.DecoratedNode invoke(final Object c_eis) {
		try {
		final common.DecoratedNode context = new PoneEnvScope(c_eis).decorate();
		//decorate i_envScope_dummy_record() with {envTrees = [ eis ];}
		return (common.DecoratedNode)(((silver.definition.env.NEnvScope)new silver.definition.env.Pi_envScope_dummy_record()).decorate(context, common.Util.populateInh(silver.definition.env.NEnvScope.num_inh_attrs, new int[]{silver.definition.env.Init.silver_definition_env_envTrees__ON__silver_definition_env_EnvScope}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.definition.env.PoneEnvScope.i_eis), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }})));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:oneEnvScope", t);
		}
	}

	public static final common.NodeFactory<common.DecoratedNode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.DecoratedNode> {
		@Override
		public common.DecoratedNode invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PoneEnvScope.invoke(children[0]);
		}
	};
}