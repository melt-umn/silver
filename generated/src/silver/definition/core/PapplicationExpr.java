
package silver.definition.core;

// top::Expr ::= e::Expr '(' es::AppExprs ')' 
public final class PapplicationExpr extends silver.definition.core.NExpr {

	public static final int i_e = 0;
	public static final int i__G_2 = 1;
	public static final int i_es = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,silver.definition.core.TLParen_t.class,silver.definition.core.NAppExprs.class,silver.definition.core.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_applicationExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_es] = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];

	}

	public PapplicationExpr(final Object c_e, final Object c__G_2, final Object c_es, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;
		this.child__G_2 = c__G_2;
		this.child_es = c_es;
		this.child__G_0 = c__G_0;

	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_2;
	public final silver.definition.core.TLParen_t getChild__G_2() {
		return (silver.definition.core.TLParen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_es;
	public final silver.definition.core.NAppExprs getChild_es() {
		return (silver.definition.core.NAppExprs) (child_es = common.Util.demand(child_es));
	}

	private Object child__G_0;
	public final silver.definition.core.TRParen_t getChild__G_0() {
		return (silver.definition.core.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i__G_2: return getChild__G_2();
			case i_es: return getChild_es();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
			case i__G_2: return child__G_2;
			case i_es: return child_es;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return ((silver.definition.core.NExpr)new silver.definition.core.Papplication(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PapplicationExpr.i_e)), context.childAsIsLazy(silver.definition.core.PapplicationExpr.i__G_2), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PapplicationExpr.i_es)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PemptyAnnoAppExprs(((core.NLocation)((silver.definition.core.TRParen_t)context.childAsIs(silver.definition.core.PapplicationExpr.i__G_0)).location))); } }, context.childAsIsLazy(silver.definition.core.PapplicationExpr.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:core:applicationExpr";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PapplicationExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PapplicationExpr> {

		@Override
		public PapplicationExpr invoke(final Object[] children, final Object[] annotations) {
			return new PapplicationExpr(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
