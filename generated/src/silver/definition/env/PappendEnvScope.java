
package silver.definition.env;

public final class PappendEnvScope extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_r = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_appendEnvScope;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PappendEnvScope(final Object c_l, final Object c_r) {
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_l;
	public final common.DecoratedNode getChild_l() {
		return (common.DecoratedNode) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final common.DecoratedNode getChild_r() {
		return (common.DecoratedNode) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_r: return child_r;

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
		return "silver:definition:env:appendEnvScope";
	}

	public static common.DecoratedNode invoke(final Object c_l, final Object c_r) {
		try {
		final common.DecoratedNode context = new PappendEnvScope(c_l, c_r).decorate();
		//decorate i_envScope_dummy_record() with {envTrees = l.envTrees ++ r.envTrees;}
		return (common.DecoratedNode)(((silver.definition.env.NEnvScope)new silver.definition.env.Pi_envScope_dummy_record()).decorate(context, common.Util.populateInh(silver.definition.env.NEnvScope.num_inh_attrs, new int[]{silver.definition.env.Init.silver_definition_env_envTrees__ON__silver_definition_env_EnvScope}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childAsIs(silver.definition.env.PappendEnvScope.i_l)).inherited(silver.definition.env.Init.silver_definition_env_envTrees__ON__silver_definition_env_EnvScope)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childAsIs(silver.definition.env.PappendEnvScope.i_r)).inherited(silver.definition.env.Init.silver_definition_env_envTrees__ON__silver_definition_env_EnvScope)); } })); } }})));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:appendEnvScope", t);
		}
	}

	public static final common.NodeFactory<common.DecoratedNode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.DecoratedNode> {
		@Override
		public common.DecoratedNode invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PappendEnvScope.invoke(children[0], children[1]);
		}
	};
}