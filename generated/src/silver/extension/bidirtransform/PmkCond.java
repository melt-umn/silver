
package silver.extension.bidirtransform;

// top::Expr ::= if_e::Expr then_e::Expr else_e::Expr 
public final class PmkCond extends silver.definition.core.NExpr {

	public static final int i_if_e = 0;
	public static final int i_then_e = 1;
	public static final int i_else_e = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,silver.definition.core.NExpr.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_mkCond;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_if_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_then_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_else_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PmkCond(final Object c_if_e, final Object c_then_e, final Object c_else_e, final Object a_core_location) {
		super(a_core_location);
		this.child_if_e = c_if_e;
		this.child_then_e = c_then_e;
		this.child_else_e = c_else_e;

	}

	private Object child_if_e;
	public final silver.definition.core.NExpr getChild_if_e() {
		return (silver.definition.core.NExpr) (child_if_e = common.Util.demand(child_if_e));
	}

	private Object child_then_e;
	public final silver.definition.core.NExpr getChild_then_e() {
		return (silver.definition.core.NExpr) (child_then_e = common.Util.demand(child_then_e));
	}

	private Object child_else_e;
	public final silver.definition.core.NExpr getChild_else_e() {
		return (silver.definition.core.NExpr) (child_else_e = common.Util.demand(child_else_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_if_e: return getChild_if_e();
			case i_then_e: return getChild_then_e();
			case i_else_e: return getChild_else_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_if_e: return child_if_e;
			case i_then_e: return child_then_e;
			case i_else_e: return child_else_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NExpr)new silver.definition.core.PifThenElse(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TIf_kwd((new common.StringCatter("if")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PmkCond.i_if_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TThen_kwd((new common.StringCatter("then")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PmkCond.i_then_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TElse_kwd((new common.StringCatter("else")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PmkCond.i_else_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:extension:bidirtransform:mkCond";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PmkCond> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmkCond> {

		@Override
		public PmkCond invoke(final Object[] children, final Object[] annotations) {
			return new PmkCond(children[0], children[1], children[2], annotations[0]);
		}
	};

}
