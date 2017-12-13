
package silver.modification.copper;

// top::ProductionStmt ::= 'pushToken' '(' val::QName ',' lexeme::Expr ')' ';' 
public final class PpushTokenStmt extends silver.definition.core.NProductionStmt {

	public static final int i__G_6 = 0;
	public static final int i__G_5 = 1;
	public static final int i_val = 2;
	public static final int i__G_3 = 3;
	public static final int i_lexeme = 4;
	public static final int i__G_1 = 5;
	public static final int i__G_0 = 6;


	public static final Class<?> childTypes[] = {silver.modification.copper.TPushToken_kwd.class,silver.definition.core.TLParen_t.class,silver.definition.core.NQName.class,silver.definition.core.TComma_t.class,silver.definition.core.NExpr.class,silver.definition.core.TRParen_t.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_pushTokenStmt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_val] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_lexeme] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PpushTokenStmt(final Object c__G_6, final Object c__G_5, final Object c_val, final Object c__G_3, final Object c_lexeme, final Object c__G_1, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_6 = c__G_6;
		this.child__G_5 = c__G_5;
		this.child_val = c_val;
		this.child__G_3 = c__G_3;
		this.child_lexeme = c_lexeme;
		this.child__G_1 = c__G_1;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_6;
	public final silver.modification.copper.TPushToken_kwd getChild__G_6() {
		return (silver.modification.copper.TPushToken_kwd) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child__G_5;
	public final silver.definition.core.TLParen_t getChild__G_5() {
		return (silver.definition.core.TLParen_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_val;
	public final silver.definition.core.NQName getChild_val() {
		return (silver.definition.core.NQName) (child_val = common.Util.demand(child_val));
	}

	private Object child__G_3;
	public final silver.definition.core.TComma_t getChild__G_3() {
		return (silver.definition.core.TComma_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_lexeme;
	public final silver.definition.core.NExpr getChild_lexeme() {
		return (silver.definition.core.NExpr) (child_lexeme = common.Util.demand(child_lexeme));
	}

	private Object child__G_1;
	public final silver.definition.core.TRParen_t getChild__G_1() {
		return (silver.definition.core.TRParen_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_6: return getChild__G_6();
			case i__G_5: return getChild__G_5();
			case i_val: return getChild_val();
			case i__G_3: return getChild__G_3();
			case i_lexeme: return getChild_lexeme();
			case i__G_1: return getChild__G_1();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_6: return child__G_6;
			case i__G_5: return child__G_5;
			case i_val: return child_val;
			case i__G_3: return child__G_3;
			case i_lexeme: return child_lexeme;
			case i__G_1: return child__G_1;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		return ((silver.definition.core.NProductionStmt)new silver.modification.copper.PpushTokenIfStmt(context.childAsIsLazy(silver.modification.copper.PpushTokenStmt.i__G_6), context.childAsIsLazy(silver.modification.copper.PpushTokenStmt.i__G_5), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PpushTokenStmt.i_val)), context.childAsIsLazy(silver.modification.copper.PpushTokenStmt.i__G_3), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PpushTokenStmt.i_lexeme)), context.childAsIsLazy(silver.modification.copper.PpushTokenStmt.i__G_1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TIf_kwd((new common.StringCatter("if")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PtrueConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TTrue_kwd((new common.StringCatter("true")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, ((core.NLocation)((silver.definition.core.TSemi_t)context.childAsIs(silver.modification.copper.PpushTokenStmt.i__G_0)).location))); } }, context.childAsIsLazy(silver.modification.copper.PpushTokenStmt.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:copper:pushTokenStmt";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PpushTokenStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpushTokenStmt> {

		@Override
		public PpushTokenStmt invoke(final Object[] children, final Object[] annotations) {
			return new PpushTokenStmt(children[0], children[1], children[2], children[3], children[4], children[5], children[6], annotations[0]);
		}
	};

}
