
package silver.extension.monad;

// top::Expr ::= e::Expr isFinalVal::Boolean returnFn::QName 
public final class PreturnExpr extends silver.definition.core.NExpr {

	public static final int i_e = 0;
	public static final int i_isFinalVal = 1;
	public static final int i_returnFn = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,Boolean.class,silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_monad_returnExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_returnFn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PreturnExpr(final Object c_e, final Object c_isFinalVal, final Object c_returnFn, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;
		this.child_isFinalVal = c_isFinalVal;
		this.child_returnFn = c_returnFn;

	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child_isFinalVal;
	public final Boolean getChild_isFinalVal() {
		return (Boolean) (child_isFinalVal = common.Util.demand(child_isFinalVal));
	}

	private Object child_returnFn;
	public final silver.definition.core.NQName getChild_returnFn() {
		return (silver.definition.core.NQName) (child_returnFn = common.Util.demand(child_returnFn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i_isFinalVal: return getChild_isFinalVal();
			case i_returnFn: return getChild_returnFn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
			case i_isFinalVal: return child_isFinalVal;
			case i_returnFn: return child_returnFn;

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
		return ((silver.definition.core.NExpr)new silver.definition.core.PapplicationExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.monad.PreturnExpr.i_returnFn)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.definition.core.PoneAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExpr)new silver.definition.core.PpresentAppExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.monad.PreturnExpr.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:monad:returnExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors <- if ! isFinalVal then [ wrn(top.location, "Return is not final value, and does not have type Unit") ] else []
		if(silver.extension.monad.PreturnExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.extension.monad.PreturnExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.extension.monad.PreturnExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.childAsIs(silver.extension.monad.PreturnExpr.i_isFinalVal))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Return is not final value, and does not have type Unit")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });

	}

	public static final common.NodeFactory<PreturnExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PreturnExpr> {

		@Override
		public PreturnExpr invoke(final Object[] children, final Object[] annotations) {
			return new PreturnExpr(children[0], children[1], children[2], annotations[0]);
		}
	};

}
