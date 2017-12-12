
package silver.definition.core;

public final class PaccessBounceUndecorate extends common.FunctionNode {

	public static final int i_target = 0;
	public static final int i_e = 1;
	public static final int i_q = 2;
	public static final int i_l = 3;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class,common.DecoratedNode.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_accessBounceUndecorate;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PaccessBounceUndecorate(final Object c_target, final Object c_e, final Object c_q, final Object c_l) {
		this.child_target = c_target;
		this.child_e = c_e;
		this.child_q = c_q;
		this.child_l = c_l;

	}

	private Object child_target;
	public final common.NodeFactory<silver.definition.core.NExpr> getChild_target() {
		return (common.NodeFactory<silver.definition.core.NExpr>) (child_target = common.Util.demand(child_target));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}

	private Object child_q;
	public final common.DecoratedNode getChild_q() {
		return (common.DecoratedNode) (child_q = common.Util.demand(child_q));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_target: return getChild_target();
			case i_e: return getChild_e();
			case i_q: return getChild_q();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_target: return child_target;
			case i_e: return child_e;
			case i_q: return child_q;
			case i_l: return child_l;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:definition:core:accessBounceUndecorate";
	}

	public static silver.definition.core.NExpr invoke(final Object c_target, final Object c_e, final Object c_q, final Object c_l) {
		try {
		final common.DecoratedNode context = new PaccessBounceUndecorate(c_target, c_e, c_q, c_l).decorate();
		//accessBouncer(target, newFunction('new', '(', exprRef(e,location=l), ')',location=l), q,location=l)
		return (silver.definition.core.NExpr)(((silver.definition.core.NExpr)new silver.definition.core.PaccessBouncer(context.childAsIsLazy(silver.definition.core.PaccessBounceUndecorate.i_target), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PnewFunction(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TNew_kwd((new common.StringCatter("new")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PexprRef(context.childAsIsLazy(silver.definition.core.PaccessBounceUndecorate.i_e), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PaccessBounceUndecorate.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PaccessBounceUndecorate.i_l)))); } }, context.childAsIsLazy(silver.definition.core.PaccessBounceUndecorate.i_q), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PaccessBounceUndecorate.i_l)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:accessBounceUndecorate", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaccessBounceUndecorate.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}