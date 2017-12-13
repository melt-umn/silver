
package silver.definition.core;

public final class PmkStrFunctionInvocationDecorated extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_e = 1;
	public static final int i_es = 2;


	public static final Class<?> childTypes[] = { core.NLocation.class,common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_mkStrFunctionInvocationDecorated;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PmkStrFunctionInvocationDecorated(final Object c_l, final Object c_e, final Object c_es) {
		this.child_l = c_l;
		this.child_e = c_e;
		this.child_es = c_es;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_e;
	public final common.StringCatter getChild_e() {
		return (common.StringCatter) (child_e = common.Util.demand(child_e));
	}

	private Object child_es;
	public final common.ConsCell getChild_es() {
		return (common.ConsCell) (child_es = common.Util.demand(child_es));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_e: return getChild_e();
			case i_es: return getChild_es();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_e: return child_e;
			case i_es: return child_es;

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
		return "silver:definition:core:mkStrFunctionInvocationDecorated";
	}

	public static silver.definition.core.NExpr invoke(final Object c_l, final Object c_e, final Object c_es) {
		try {
		final common.DecoratedNode context = new PmkStrFunctionInvocationDecorated(c_l, c_e, c_es).decorate();
		//mkFunctionInvocation(l, baseExpr(qName(l, e),location=l), map(exprRef(_,location=l), es))
		return (silver.definition.core.NExpr)(((silver.definition.core.NExpr)silver.definition.core.PmkFunctionInvocation.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PmkStrFunctionInvocationDecorated.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PmkStrFunctionInvocationDecorated.i_l)), context.childAsIsLazy(silver.definition.core.PmkStrFunctionInvocationDecorated.i_e))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PmkStrFunctionInvocationDecorated.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.core.PexprRef.factory.invokeNamedPartial(null, new int[]{0}, new Object[]{common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PmkStrFunctionInvocationDecorated.i_l))}); } }, context.childAsIsLazy(silver.definition.core.PmkStrFunctionInvocationDecorated.i_es))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:mkStrFunctionInvocationDecorated", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmkStrFunctionInvocationDecorated.invoke(children[0], children[1], children[2]);
		}
	};
}