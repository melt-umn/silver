
package silver.definition.core;

// top::Expr ::= e1::Expr '++' e2::Expr 
public final class PplusPlus extends silver.definition.core.NExpr {

	public static final int i_e1 = 0;
	public static final int i__G_1 = 1;
	public static final int i_e2 = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,silver.definition.core.TPlusPlus_t.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_plusPlus;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e1] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_e2] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PplusPlus(final Object c_e1, final Object c__G_1, final Object c_e2, final Object a_core_location) {
		super(a_core_location);
		this.child_e1 = c_e1;
		this.child__G_1 = c__G_1;
		this.child_e2 = c_e2;

	}

	private Object child_e1;
	public final silver.definition.core.NExpr getChild_e1() {
		return (silver.definition.core.NExpr) (child_e1 = common.Util.demand(child_e1));
	}

	private Object child__G_1;
	public final silver.definition.core.TPlusPlus_t getChild__G_1() {
		return (silver.definition.core.TPlusPlus_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e2;
	public final silver.definition.core.NExpr getChild_e2() {
		return (silver.definition.core.NExpr) (child_e2 = common.Util.demand(child_e2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e1: return getChild_e1();
			case i__G_1: return getChild__G_1();
			case i_e2: return getChild_e2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e1: return child_e1;
			case i__G_1: return child__G_1;
			case i_e2: return child_e2;

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
		return ((silver.definition.core.NExpr)((common.NodeFactory<silver.definition.core.NExpr>)((silver.definition.type.NType)context.synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_appendDispatcher__ON__silver_definition_type_Type)).invoke(new Object[]{context.childDecoratedLazy(silver.definition.core.PplusPlus.i_e1), context.childDecoratedLazy(silver.definition.core.PplusPlus.i_e2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }}, null));
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
		return "silver:definition:core:plusPlus";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e1.pp ++ " ++ " ++ e2.pp
		silver.definition.core.PplusPlus.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PplusPlus.i_e1).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ++ ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PplusPlus.i_e2).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)))); } };
		// top.typerep = performSubstitution(e1.typerep, errCheck1.upSubst)
		silver.definition.core.PplusPlus.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PplusPlus.i_e1, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.localDecorated(silver.analysis.typechecking.core.Init.errCheck1__ON__silver_definition_core_plusPlus).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)); } })); } };
		// top.errors := e1.errors ++ e2.errors ++ forward.errors
		if(silver.definition.core.PplusPlus.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PplusPlus.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PplusPlus.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PplusPlus.i_e1, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PplusPlus.i_e2, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.forward().synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr)); } })); } })); } });

	}

	public static final common.NodeFactory<PplusPlus> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PplusPlus> {

		@Override
		public PplusPlus invoke(final Object[] children, final Object[] annotations) {
			return new PplusPlus(children[0], children[1], children[2], annotations[0]);
		}
	};

}
