
package silver.definition.core;

// top::Expr ::= 'terminal' '(' t::TypeExpr ',' e1::Expr ',' e2::Expr ')' 
public final class PterminalFunctionInherited extends silver.definition.core.NExpr {

	public static final int i__G_7 = 0;
	public static final int i__G_6 = 1;
	public static final int i_t = 2;
	public static final int i__G_4 = 3;
	public static final int i_e1 = 4;
	public static final int i__G_2 = 5;
	public static final int i_e2 = 6;
	public static final int i__G_0 = 7;


	public static final Class<?> childTypes[] = {silver.definition.core.TTerminal_kwd.class,silver.definition.core.TLParen_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TComma_t.class,silver.definition.core.NExpr.class,silver.definition.core.TComma_t.class,silver.definition.core.NExpr.class,silver.definition.core.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_terminalFunctionInherited;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_e1] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_e2] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PterminalFunctionInherited(final Object c__G_7, final Object c__G_6, final Object c_t, final Object c__G_4, final Object c_e1, final Object c__G_2, final Object c_e2, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_7 = c__G_7;
		this.child__G_6 = c__G_6;
		this.child_t = c_t;
		this.child__G_4 = c__G_4;
		this.child_e1 = c_e1;
		this.child__G_2 = c__G_2;
		this.child_e2 = c_e2;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_7;
	public final silver.definition.core.TTerminal_kwd getChild__G_7() {
		return (silver.definition.core.TTerminal_kwd) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child__G_6;
	public final silver.definition.core.TLParen_t getChild__G_6() {
		return (silver.definition.core.TLParen_t) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_4;
	public final silver.definition.core.TComma_t getChild__G_4() {
		return (silver.definition.core.TComma_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_e1;
	public final silver.definition.core.NExpr getChild_e1() {
		return (silver.definition.core.NExpr) (child_e1 = common.Util.demand(child_e1));
	}

	private Object child__G_2;
	public final silver.definition.core.TComma_t getChild__G_2() {
		return (silver.definition.core.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_e2;
	public final silver.definition.core.NExpr getChild_e2() {
		return (silver.definition.core.NExpr) (child_e2 = common.Util.demand(child_e2));
	}

	private Object child__G_0;
	public final silver.definition.core.TRParen_t getChild__G_0() {
		return (silver.definition.core.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_7: return getChild__G_7();
			case i__G_6: return getChild__G_6();
			case i_t: return getChild_t();
			case i__G_4: return getChild__G_4();
			case i_e1: return getChild_e1();
			case i__G_2: return getChild__G_2();
			case i_e2: return getChild_e2();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_7: return child__G_7;
			case i__G_6: return child__G_6;
			case i_t: return child_t;
			case i__G_4: return child__G_4;
			case i_e1: return child_e1;
			case i__G_2: return child__G_2;
			case i_e2: return child_e2;
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
		return ((silver.definition.core.NExpr)new silver.definition.core.PterminalConstructor(context.childAsIsLazy(silver.definition.core.PterminalFunctionInherited.i__G_7), context.childAsIsLazy(silver.definition.core.PterminalFunctionInherited.i__G_6), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalFunctionInherited.i_t)), context.childAsIsLazy(silver.definition.core.PterminalFunctionInherited.i__G_4), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalFunctionInherited.i_e1)), context.childAsIsLazy(silver.definition.core.PterminalFunctionInherited.i__G_2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.Paccess(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalFunctionInherited.i_e2)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TDot_t((new common.StringCatter(".")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameAttrOccur)new silver.definition.core.PqNameAttrOccur(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.forward().undecorate()).getAnno_core_location()); } }, (new common.StringCatter("location")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, context.childAsIsLazy(silver.definition.core.PterminalFunctionInherited.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:core:terminalFunctionInherited";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors <- [ wrn(t.location, "terminal(type,lexeme,terminal) is deprecated. Please just add '.location' on the terminal to use terminal(type,lexeme,location)") ]
		if(silver.definition.core.PterminalFunctionInherited.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PterminalFunctionInherited.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PterminalFunctionInherited.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.childDecorated(silver.definition.core.PterminalFunctionInherited.i_t).undecorate()).getAnno_core_location()); } }, (new common.StringCatter("terminal(type,lexeme,terminal) is deprecated. Please just add '.location' on the terminal to use terminal(type,lexeme,location)")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });

	}

	public static final common.NodeFactory<PterminalFunctionInherited> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalFunctionInherited> {

		@Override
		public PterminalFunctionInherited invoke(final Object[] children, final Object[] annotations) {
			return new PterminalFunctionInherited(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], annotations[0]);
		}
	};

}
