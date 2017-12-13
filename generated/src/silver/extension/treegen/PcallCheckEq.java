
package silver.extension.treegen;

public final class PcallCheckEq extends common.FunctionNode {

	public static final int i_te = 0;
	public static final int i_l = 1;
	public static final int i_es = 2;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class,core.NLocation.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_callCheckEq;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PcallCheckEq(final Object c_te, final Object c_l, final Object c_es) {
		this.child_te = c_te;
		this.child_l = c_l;
		this.child_es = c_es;

	}

	private Object child_te;
	public final silver.definition.type.NType getChild_te() {
		return (silver.definition.type.NType) (child_te = common.Util.demand(child_te));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_es;
	public final common.ConsCell getChild_es() {
		return (common.ConsCell) (child_es = common.Util.demand(child_es));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_te: return getChild_te();
			case i_l: return getChild_l();
			case i_es: return getChild_es();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_te: return child_te;
			case i_l: return child_l;
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
		return "silver:extension:treegen:callCheckEq";
	}

	public static silver.definition.core.NExpr invoke(final Object c_te, final Object c_l, final Object c_es) {
		try {
		final common.DecoratedNode context = new PcallCheckEq(c_te, c_l, c_es).decorate();
		//mkStrFunctionInvocation(l, getCheckEqName(te), es)
		return (silver.definition.core.NExpr)(((silver.definition.core.NExpr)silver.definition.core.PmkStrFunctionInvocation.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PcallCheckEq.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.treegen.PgetCheckEqName.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PcallCheckEq.i_te)))); } }, context.childAsIsLazy(silver.extension.treegen.PcallCheckEq.i_es))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:callCheckEq", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcallCheckEq.invoke(children[0], children[1], children[2]);
		}
	};
}