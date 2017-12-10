
package silver.definition.core;

// top::Expr ::= 'terminal' '(' t::TypeExpr ',' e1::Expr ',' e2::Expr ',' e3::Expr ')' 
public final class PterminalFunctionLineCol extends silver.definition.core.NExpr {

	public static final int i__G_9 = 0;
	public static final int i__G_8 = 1;
	public static final int i_t = 2;
	public static final int i__G_6 = 3;
	public static final int i_e1 = 4;
	public static final int i__G_4 = 5;
	public static final int i_e2 = 6;
	public static final int i__G_2 = 7;
	public static final int i_e3 = 8;
	public static final int i__G_0 = 9;


	public static final Class<?> childTypes[] = {silver.definition.core.TTerminal_kwd.class,silver.definition.core.TLParen_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TComma_t.class,silver.definition.core.NExpr.class,silver.definition.core.TComma_t.class,silver.definition.core.NExpr.class,silver.definition.core.TComma_t.class,silver.definition.core.NExpr.class,silver.definition.core.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_terminalFunctionLineCol;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[10][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_e1] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_e2] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_e3] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PterminalFunctionLineCol(final Object c__G_9, final Object c__G_8, final Object c_t, final Object c__G_6, final Object c_e1, final Object c__G_4, final Object c_e2, final Object c__G_2, final Object c_e3, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_9 = c__G_9;
		this.child__G_8 = c__G_8;
		this.child_t = c_t;
		this.child__G_6 = c__G_6;
		this.child_e1 = c_e1;
		this.child__G_4 = c__G_4;
		this.child_e2 = c_e2;
		this.child__G_2 = c__G_2;
		this.child_e3 = c_e3;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_9;
	public final silver.definition.core.TTerminal_kwd getChild__G_9() {
		return (silver.definition.core.TTerminal_kwd) (child__G_9 = common.Util.demand(child__G_9));
	}

	private Object child__G_8;
	public final silver.definition.core.TLParen_t getChild__G_8() {
		return (silver.definition.core.TLParen_t) (child__G_8 = common.Util.demand(child__G_8));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_6;
	public final silver.definition.core.TComma_t getChild__G_6() {
		return (silver.definition.core.TComma_t) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_e1;
	public final silver.definition.core.NExpr getChild_e1() {
		return (silver.definition.core.NExpr) (child_e1 = common.Util.demand(child_e1));
	}

	private Object child__G_4;
	public final silver.definition.core.TComma_t getChild__G_4() {
		return (silver.definition.core.TComma_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_e2;
	public final silver.definition.core.NExpr getChild_e2() {
		return (silver.definition.core.NExpr) (child_e2 = common.Util.demand(child_e2));
	}

	private Object child__G_2;
	public final silver.definition.core.TComma_t getChild__G_2() {
		return (silver.definition.core.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_e3;
	public final silver.definition.core.NExpr getChild_e3() {
		return (silver.definition.core.NExpr) (child_e3 = common.Util.demand(child_e3));
	}

	private Object child__G_0;
	public final silver.definition.core.TRParen_t getChild__G_0() {
		return (silver.definition.core.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_9: return getChild__G_9();
			case i__G_8: return getChild__G_8();
			case i_t: return getChild_t();
			case i__G_6: return getChild__G_6();
			case i_e1: return getChild_e1();
			case i__G_4: return getChild__G_4();
			case i_e2: return getChild_e2();
			case i__G_2: return getChild__G_2();
			case i_e3: return getChild_e3();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_9: return child__G_9;
			case i__G_8: return child__G_8;
			case i_t: return child_t;
			case i__G_6: return child__G_6;
			case i_e1: return child_e1;
			case i__G_4: return child__G_4;
			case i_e2: return child_e2;
			case i__G_2: return child__G_2;
			case i_e3: return child_e3;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 10;
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
		return ((silver.definition.core.NExpr)new silver.definition.core.PterminalConstructor(context.childAsIsLazy(silver.definition.core.PterminalFunctionLineCol.i__G_9), context.childAsIsLazy(silver.definition.core.PterminalFunctionLineCol.i__G_8), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalFunctionLineCol.i_t)), context.childAsIsLazy(silver.definition.core.PterminalFunctionLineCol.i__G_6), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalFunctionLineCol.i_e1)), context.childAsIsLazy(silver.definition.core.PterminalFunctionLineCol.i__G_4), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.definition.core.PmkStrFunctionInvocation.invoke(((core.NLocation)((silver.definition.core.TRParen_t)context.childAsIs(silver.definition.core.PterminalFunctionLineCol.i__G_0)).location), (new common.StringCatter("core:loc")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PstringConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TString_t((new common.StringCatter("\"??\"")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, ((core.NLocation)((silver.definition.core.TRParen_t)context.childAsIs(silver.definition.core.PterminalFunctionLineCol.i__G_0)).location))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalFunctionLineCol.i_e2)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalFunctionLineCol.i_e3)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PintConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TInt_t((new common.StringCatter("-1")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, ((core.NLocation)((silver.definition.core.TRParen_t)context.childAsIs(silver.definition.core.PterminalFunctionLineCol.i__G_0)).location))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PintConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TInt_t((new common.StringCatter("-1")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, ((core.NLocation)((silver.definition.core.TRParen_t)context.childAsIs(silver.definition.core.PterminalFunctionLineCol.i__G_0)).location))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PintConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TInt_t((new common.StringCatter("-1")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, ((core.NLocation)((silver.definition.core.TRParen_t)context.childAsIs(silver.definition.core.PterminalFunctionLineCol.i__G_0)).location))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PintConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TInt_t((new common.StringCatter("-1")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, ((core.NLocation)((silver.definition.core.TRParen_t)context.childAsIs(silver.definition.core.PterminalFunctionLineCol.i__G_0)).location))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } })); } })); } }, context.childAsIsLazy(silver.definition.core.PterminalFunctionLineCol.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:core:terminalFunctionLineCol";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors <- [ wrn(t.location, "terminal(type,lexeme,line,column) is deprecated. Use terminal(type,lexeme,location) instead.") ]
		if(silver.definition.core.PterminalFunctionLineCol.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PterminalFunctionLineCol.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PterminalFunctionLineCol.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Pwrn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.childDecorated(silver.definition.core.PterminalFunctionLineCol.i_t).undecorate()).getAnno_core_location()); } }, (new common.StringCatter("terminal(type,lexeme,line,column) is deprecated. Use terminal(type,lexeme,location) instead.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });

	}

	public static final common.NodeFactory<PterminalFunctionLineCol> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalFunctionLineCol> {

		@Override
		public PterminalFunctionLineCol invoke(final Object[] children, final Object[] annotations) {
			return new PterminalFunctionLineCol(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], children[9], annotations[0]);
		}
	};

}
