
package silver.extension.templating;

public final class Pcatcall extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;
	public static final int i_l = 2;


	public static final Class<?> childTypes[] = { silver.definition.core.NExpr.class,silver.definition.core.NExpr.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_templating_catcall;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_a] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_b] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public Pcatcall(final Object c_a, final Object c_b, final Object c_l) {
		this.child_a = c_a;
		this.child_b = c_b;
		this.child_l = c_l;

	}

	private Object child_a;
	public final silver.definition.core.NExpr getChild_a() {
		return (silver.definition.core.NExpr) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final silver.definition.core.NExpr getChild_b() {
		return (silver.definition.core.NExpr) (child_b = common.Util.demand(child_b));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_b: return getChild_b();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_b: return child_b;
			case i_l: return child_l;

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
		return "silver:extension:templating:catcall";
	}

	public static silver.definition.core.NExpr invoke(final Object c_a, final Object c_b, final Object c_l) {
		try {
		final common.DecoratedNode context = new Pcatcall(c_a, c_b, c_l).decorate();
		//mkStrFunctionInvocation(l, "silver:langutil:pp:cat", [ a, b ])
		return (silver.definition.core.NExpr)(((silver.definition.core.NExpr)silver.definition.core.PmkStrFunctionInvocation.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.templating.Pcatcall.i_l)), (new common.StringCatter("silver:langutil:pp:cat")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.templating.Pcatcall.i_a)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.templating.Pcatcall.i_b)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:templating:catcall", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NExpr> {
		@Override
		public silver.definition.core.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pcatcall.invoke(children[0], children[1], children[2]);
		}
	};
}