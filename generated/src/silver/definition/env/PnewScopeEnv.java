
package silver.definition.env;

public final class PnewScopeEnv extends common.FunctionNode {

	public static final int i_d = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_newScopeEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PnewScopeEnv(final Object c_d, final Object c_e) {
		this.child_d = c_d;
		this.child_e = c_e;

	}

	private Object child_d;
	public final common.ConsCell getChild_d() {
		return (common.ConsCell) (child_d = common.Util.demand(child_d));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;
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
		return "silver:definition:env:newScopeEnv";
	}

	public static common.DecoratedNode invoke(final Object c_d, final Object c_e) {
		try {
		final common.DecoratedNode context = new PnewScopeEnv(c_d, c_e).decorate();
		//decorate i_newScopeEnv(foldr(consDefs, nilDefs(), d), e) with {}
		return (common.DecoratedNode)(((silver.definition.env.NEnv)new silver.definition.env.Pi_newScopeEnv(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDefs)core.Pfoldr.invoke(silver.definition.env.PconsDefs.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDefs)new silver.definition.env.PnilDefs()); } }, context.childAsIsLazy(silver.definition.env.PnewScopeEnv.i_d))); } }, context.childAsIsLazy(silver.definition.env.PnewScopeEnv.i_e))).decorate(context, (common.Lazy[])null));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:newScopeEnv", t);
		}
	}

	public static final common.NodeFactory<common.DecoratedNode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.DecoratedNode> {
		@Override
		public common.DecoratedNode invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnewScopeEnv.invoke(children[0], children[1]);
		}
	};
}