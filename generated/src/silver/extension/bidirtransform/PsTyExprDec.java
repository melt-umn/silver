
package silver.extension.bidirtransform;

public final class PsTyExprDec extends common.FunctionNode {

	public static final int i_s = 0;
	public static final int i_loc = 1;
	public static final int i_env = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NLocation.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_sTyExprDec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_loc] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PsTyExprDec(final Object c_s, final Object c_loc, final Object c_env) {
		this.child_s = c_s;
		this.child_loc = c_loc;
		this.child_env = c_env;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_loc;
	public final core.NLocation getChild_loc() {
		return (core.NLocation) (child_loc = common.Util.demand(child_loc));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_loc: return getChild_loc();
			case i_env: return getChild_env();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_loc: return child_loc;
			case i_env: return child_env;

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
		return "silver:extension:bidirtransform:sTyExprDec";
	}

	public static common.DecoratedNode invoke(final Object c_s, final Object c_loc, final Object c_env) {
		try {
		final common.DecoratedNode context = new PsTyExprDec(c_s, c_loc, c_env).decorate();
		//decorate sTyExpr(s,location=loc) with {env = env;}
		return (common.DecoratedNode)(((silver.definition.type.syntax.NTypeExpr)new silver.extension.bidirtransform.PsTyExpr(context.childAsIsLazy(silver.extension.bidirtransform.PsTyExprDec.i_s), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PsTyExprDec.i_loc)))).decorate(context, common.Util.populateInh(silver.definition.type.syntax.NTypeExpr.num_inh_attrs, new int[]{silver.definition.type.syntax.Init.silver_definition_env_env__ON__silver_definition_type_syntax_TypeExpr}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PsTyExprDec.i_env)); } }})));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:sTyExprDec", t);
		}
	}

	public static final common.NodeFactory<common.DecoratedNode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.DecoratedNode> {
		@Override
		public common.DecoratedNode invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsTyExprDec.invoke(children[0], children[1], children[2]);
		}
	};
}