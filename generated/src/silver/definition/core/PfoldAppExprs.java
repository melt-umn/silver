
package silver.definition.core;

public final class PfoldAppExprs extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { core.NLocation.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_foldAppExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PfoldAppExprs(final Object c_l, final Object c_e) {
		this.child_l = c_l;
		this.child_e = c_e;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_e;
	public final common.ConsCell getChild_e() {
		return (common.ConsCell) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
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
		return "silver:definition:core:foldAppExprs";
	}

	public static silver.definition.core.NAppExprs invoke(final Object c_l, final Object c_e) {
		try {
		final common.DecoratedNode context = new PfoldAppExprs(c_l, c_e).decorate();
		//if null(e) then emptyAppExprs(location=l) else if null(tail(e)) then oneAppExprs(presentAppExpr(head(e),location=l),location=l) else snocAppExprs(foldAppExprs(l, tail(e)), ',', presentAppExpr(head(e),location=l),location=l)
		return (silver.definition.core.NAppExprs)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.core.PfoldAppExprs.i_e))) ? ((silver.definition.core.NAppExprs)new silver.definition.core.PemptyAppExprs(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PfoldAppExprs.i_l)))) : (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.core.PfoldAppExprs.i_e))); } })) ? ((silver.definition.core.NAppExprs)new silver.definition.core.PoneAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExpr)new silver.definition.core.PpresentAppExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)core.Phead.invoke(context.childAsIsLazy(silver.definition.core.PfoldAppExprs.i_e))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PfoldAppExprs.i_l)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PfoldAppExprs.i_l)))) : ((silver.definition.core.NAppExprs)new silver.definition.core.PsnocAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)silver.definition.core.PfoldAppExprs.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PfoldAppExprs.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.core.PfoldAppExprs.i_e))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExpr)new silver.definition.core.PpresentAppExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)core.Phead.invoke(context.childAsIsLazy(silver.definition.core.PfoldAppExprs.i_e))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PfoldAppExprs.i_l)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PfoldAppExprs.i_l)))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:foldAppExprs", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NAppExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NAppExprs> {
		@Override
		public silver.definition.core.NAppExprs invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfoldAppExprs.invoke(children[0], children[1]);
		}
	};
}