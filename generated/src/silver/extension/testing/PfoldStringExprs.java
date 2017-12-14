
package silver.extension.testing;

public final class PfoldStringExprs extends common.FunctionNode {

	public static final int i_es = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_testing_foldStringExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfoldStringExprs(final Object c_es) {
		this.child_es = c_es;

	}

	private Object child_es;
	public final common.ConsCell getChild_es() {
		return (common.ConsCell) (child_es = common.Util.demand(child_es));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_es: return getChild_es();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_es: return child_es;

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
		return "silver:extension:testing:foldStringExprs";
	}

	public static silver.definition.core.NExpr invoke(final Object c_es) {
		try {
		final common.DecoratedNode context = new PfoldStringExprs(c_es).decorate();
		//if null(es) then stringConst(terminal(String_t, "\"\"", core:loc("??", -1, -1, -1, -1, -1, -1)),location=bogusLocation()) else plusPlus(head(es), '++', foldStringExprs(tail(es)),location=head(es).location)
		return (silver.definition.core.NExpr)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.testing.PfoldStringExprs.i_es))) ? ((silver.definition.core.NExpr)new silver.definition.core.PstringConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TString_t((new common.StringCatter("\"\"")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)silver.definition.env.PbogusLocation.invoke()); } })) : ((silver.definition.core.NExpr)new silver.definition.core.PplusPlus(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)core.Phead.invoke(context.childAsIsLazy(silver.extension.testing.PfoldStringExprs.i_es))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TPlusPlus_t((new common.StringCatter("++")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.extension.testing.PfoldStringExprs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.testing.PfoldStringExprs.i_es))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)core.Phead.invoke(context.childAsIsLazy(silver.extension.testing.PfoldStringExprs.i_es))).getAnno_core_location()); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:testing:foldStringExprs", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfoldStringExprs.invoke(children[0]);
		}
	};
}