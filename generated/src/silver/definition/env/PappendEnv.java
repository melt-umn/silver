
package silver.definition.env;

public final class PappendEnv extends common.FunctionNode {

	public static final int i_e1 = 0;
	public static final int i_e2 = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_appendEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PappendEnv(final Object c_e1, final Object c_e2) {
		this.child_e1 = c_e1;
		this.child_e2 = c_e2;

	}

	private Object child_e1;
	public final common.DecoratedNode getChild_e1() {
		return (common.DecoratedNode) (child_e1 = common.Util.demand(child_e1));
	}

	private Object child_e2;
	public final common.DecoratedNode getChild_e2() {
		return (common.DecoratedNode) (child_e2 = common.Util.demand(child_e2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e1: return getChild_e1();
			case i_e2: return getChild_e2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e1: return child_e1;
			case i_e2: return child_e2;

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
		return "silver:definition:env:appendEnv";
	}

	public static common.DecoratedNode invoke(final Object c_e1, final Object c_e2) {
		try {
		final common.DecoratedNode context = new PappendEnv(c_e1, c_e2).decorate();
		//decorate i_appendEnv(e1, e2) with {}
		return (common.DecoratedNode)(((silver.definition.env.NEnv)new silver.definition.env.Pi_appendEnv(context.childAsIsLazy(silver.definition.env.PappendEnv.i_e1), context.childAsIsLazy(silver.definition.env.PappendEnv.i_e2))).decorate(context, (common.Lazy[])null));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:appendEnv", t);
		}
	}

	public static final common.NodeFactory<common.DecoratedNode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.DecoratedNode> {
		@Override
		public common.DecoratedNode invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PappendEnv.invoke(children[0], children[1]);
		}
	};
}