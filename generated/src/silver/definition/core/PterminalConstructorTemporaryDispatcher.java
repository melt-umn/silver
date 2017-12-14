
package silver.definition.core;

// top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')' 
public final class PterminalConstructorTemporaryDispatcher extends silver.definition.core.NExpr {

	public static final int i__G_7 = 0;
	public static final int i__G_6 = 1;
	public static final int i_t = 2;
	public static final int i__G_4 = 3;
	public static final int i_es = 4;
	public static final int i__G_2 = 5;
	public static final int i_el = 6;
	public static final int i__G_0 = 7;


	public static final Class<?> childTypes[] = {silver.definition.core.TTerminal_kwd.class,silver.definition.core.TLParen_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TComma_t.class,silver.definition.core.NExpr.class,silver.definition.core.TComma_t.class,silver.definition.core.NExpr.class,silver.definition.core.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_terminalConstructorTemporaryDispatcher;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_es] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_el] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PterminalConstructorTemporaryDispatcher(final Object c__G_7, final Object c__G_6, final Object c_t, final Object c__G_4, final Object c_es, final Object c__G_2, final Object c_el, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_7 = c__G_7;
		this.child__G_6 = c__G_6;
		this.child_t = c_t;
		this.child__G_4 = c__G_4;
		this.child_es = c_es;
		this.child__G_2 = c__G_2;
		this.child_el = c_el;
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

	private Object child_es;
	public final silver.definition.core.NExpr getChild_es() {
		return (silver.definition.core.NExpr) (child_es = common.Util.demand(child_es));
	}

	private Object child__G_2;
	public final silver.definition.core.TComma_t getChild__G_2() {
		return (silver.definition.core.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_el;
	public final silver.definition.core.NExpr getChild_el() {
		return (silver.definition.core.NExpr) (child_el = common.Util.demand(child_el));
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
			case i_es: return getChild_es();
			case i__G_2: return getChild__G_2();
			case i_el: return getChild_el();
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
			case i_es: return child_es;
			case i__G_2: return child__G_2;
			case i_el: return child_el;
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
		return (((Boolean)((silver.definition.type.NType)context.childDecorated(silver.definition.core.PterminalConstructorTemporaryDispatcher.i_el).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isTerminal__ON__silver_definition_type_Type)) ? ((silver.definition.core.NExpr)new silver.definition.core.PterminalFunctionInherited(context.childAsIsLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i__G_7), context.childAsIsLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i__G_6), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i_t)), context.childAsIsLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i__G_4), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i_es)), context.childAsIsLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i__G_2), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i_el)), context.childAsIsLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NExpr)new silver.definition.core.PterminalConstructor(context.childAsIsLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i__G_7), context.childAsIsLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i__G_6), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i_t)), context.childAsIsLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i__G_4), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i_es)), context.childAsIsLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i__G_2), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i_el)), context.childAsIsLazy(silver.definition.core.PterminalConstructorTemporaryDispatcher.i__G_0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })));
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
		return "silver:definition:core:terminalConstructorTemporaryDispatcher";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "terminal(" ++ t.pp ++ ", " ++ es.pp ++ ", " ++ el.pp ++ ")"
		silver.definition.core.PterminalConstructorTemporaryDispatcher.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("terminal(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PterminalConstructorTemporaryDispatcher.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PterminalConstructorTemporaryDispatcher.i_es).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PterminalConstructorTemporaryDispatcher.i_el).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(")")))))))); } };
		// el.downSubst = emptySubst()
		silver.definition.core.PterminalConstructorTemporaryDispatcher.childInheritedAttributes[silver.definition.core.PterminalConstructorTemporaryDispatcher.i_el][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PemptySubst.invoke()); } };

	}

	public static final common.NodeFactory<PterminalConstructorTemporaryDispatcher> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PterminalConstructorTemporaryDispatcher> {

		@Override
		public PterminalConstructorTemporaryDispatcher invoke(final Object[] children, final Object[] annotations) {
			return new PterminalConstructorTemporaryDispatcher(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], annotations[0]);
		}
	};

}
