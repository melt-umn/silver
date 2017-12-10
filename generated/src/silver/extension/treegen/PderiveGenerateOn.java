
package silver.extension.treegen;

public final class PderiveGenerateOn extends common.FunctionNode {

	public static final int i_id = 0;
	public static final int i_l = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_deriveGenerateOn;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PderiveGenerateOn(final Object c_id, final Object c_l) {
		this.child_id = c_id;
		this.child_l = c_l;

	}

	private Object child_id;
	public final silver.definition.env.NDclInfo getChild_id() {
		return (silver.definition.env.NDclInfo) (child_id = common.Util.demand(child_id));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;
			case i_l: return child_l;

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
		return "silver:extension:treegen:deriveGenerateOn";
	}

	public static silver.definition.core.NExpr invoke(final Object c_id, final Object c_l) {
		try {
		final common.DecoratedNode context = new PderiveGenerateOn(c_id, c_l).decorate();
		//application(callid, '(', foldAppExprs(l, reverse(es)), ',', annos, ')',location=l)
		return (silver.definition.core.NExpr)(((silver.definition.core.NExpr)new silver.definition.core.Papplication(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.callid__ON__silver_extension_treegen_deriveGenerateOn)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)silver.definition.core.PfoldAppExprs.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PderiveGenerateOn.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Preverse.invoke(context.localAsIsLazy(silver.extension.treegen.Init.es__ON__silver_extension_treegen_deriveGenerateOn))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.annos__ON__silver_extension_treegen_deriveGenerateOn)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PderiveGenerateOn.i_l)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:deriveGenerateOn", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PderiveGenerateOn.invoke(children[0], children[1]);
		}
	};
}