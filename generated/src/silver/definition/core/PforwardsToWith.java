
package silver.definition.core;

// top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';' 
public final class PforwardsToWith extends silver.definition.core.NProductionStmt {

	public static final int i__G_7 = 0;
	public static final int i__G_6 = 1;
	public static final int i_e = 2;
	public static final int i__G_4 = 3;
	public static final int i__G_3 = 4;
	public static final int i_inh = 5;
	public static final int i__G_1 = 6;
	public static final int i__G_0 = 7;


	public static final Class<?> childTypes[] = {silver.definition.core.TForwards_kwd.class,silver.definition.core.TTo_kwd.class,silver.definition.core.NExpr.class,silver.definition.core.TWith_kwd.class,silver.definition.core.TLCurly_t.class,silver.definition.core.NForwardInhs.class,silver.definition.core.TRCurly_t.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_forwardsToWith;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_inh] = new common.Lazy[silver.definition.core.NForwardInhs.num_inh_attrs];

	}

	public PforwardsToWith(final Object c__G_7, final Object c__G_6, final Object c_e, final Object c__G_4, final Object c__G_3, final Object c_inh, final Object c__G_1, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_7 = c__G_7;
		this.child__G_6 = c__G_6;
		this.child_e = c_e;
		this.child__G_4 = c__G_4;
		this.child__G_3 = c__G_3;
		this.child_inh = c_inh;
		this.child__G_1 = c__G_1;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_7;
	public final silver.definition.core.TForwards_kwd getChild__G_7() {
		return (silver.definition.core.TForwards_kwd) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child__G_6;
	public final silver.definition.core.TTo_kwd getChild__G_6() {
		return (silver.definition.core.TTo_kwd) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_4;
	public final silver.definition.core.TWith_kwd getChild__G_4() {
		return (silver.definition.core.TWith_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child__G_3;
	public final silver.definition.core.TLCurly_t getChild__G_3() {
		return (silver.definition.core.TLCurly_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_inh;
	public final silver.definition.core.NForwardInhs getChild_inh() {
		return (silver.definition.core.NForwardInhs) (child_inh = common.Util.demand(child_inh));
	}

	private Object child__G_1;
	public final silver.definition.core.TRCurly_t getChild__G_1() {
		return (silver.definition.core.TRCurly_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_7: return getChild__G_7();
			case i__G_6: return getChild__G_6();
			case i_e: return getChild_e();
			case i__G_4: return getChild__G_4();
			case i__G_3: return getChild__G_3();
			case i_inh: return getChild_inh();
			case i__G_1: return getChild__G_1();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_7: return child__G_7;
			case i__G_6: return child__G_6;
			case i_e: return child_e;
			case i__G_4: return child__G_4;
			case i__G_3: return child__G_3;
			case i_inh: return child_inh;
			case i__G_1: return child__G_1;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 8;
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
		return ((silver.definition.core.NProductionStmt)new silver.definition.core.PproductionStmtAppend(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmt)new silver.definition.core.PforwardsTo(context.childAsIsLazy(silver.definition.core.PforwardsToWith.i__G_7), context.childAsIsLazy(silver.definition.core.PforwardsToWith.i__G_6), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PforwardsToWith.i_e)), context.childAsIsLazy(silver.definition.core.PforwardsToWith.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NProductionStmt)new silver.definition.core.PforwardingWith(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TForwarding_kwd((new common.StringCatter("forwarding")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.childAsIsLazy(silver.definition.core.PforwardsToWith.i__G_4), context.childAsIsLazy(silver.definition.core.PforwardsToWith.i__G_3), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PforwardsToWith.i_inh)), context.childAsIsLazy(silver.definition.core.PforwardsToWith.i__G_1), context.childAsIsLazy(silver.definition.core.PforwardsToWith.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:core:forwardsToWith";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "\tforwards to " ++ e.pp ++ " with {" ++ inh.pp ++ "};"
		silver.definition.core.PforwardsToWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\tforwards to ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PforwardsToWith.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" with {")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PforwardsToWith.i_inh).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ForwardInhs)), (common.StringCatter)(new common.StringCatter("};")))))); } };

	}

	public static final common.NodeFactory<PforwardsToWith> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PforwardsToWith> {

		@Override
		public PforwardsToWith invoke(final Object[] children, final Object[] annotations) {
			return new PforwardsToWith(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], annotations[0]);
		}
	};

}
