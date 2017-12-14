
package silver.definition.core;

// top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr 
public final class PifThenElse extends silver.definition.core.NExpr {

	public static final int i__G_5 = 0;
	public static final int i_e1 = 1;
	public static final int i__G_3 = 2;
	public static final int i_e2 = 3;
	public static final int i__G_1 = 4;
	public static final int i_e3 = 5;


	public static final Class<?> childTypes[] = {silver.definition.core.TIf_kwd.class,silver.definition.core.NExpr.class,silver.definition.core.TThen_kwd.class,silver.definition.core.NExpr.class,silver.definition.core.TElse_kwd.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_ifThenElse;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e1] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_e2] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_e3] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PifThenElse(final Object c__G_5, final Object c_e1, final Object c__G_3, final Object c_e2, final Object c__G_1, final Object c_e3, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child_e1 = c_e1;
		this.child__G_3 = c__G_3;
		this.child_e2 = c_e2;
		this.child__G_1 = c__G_1;
		this.child_e3 = c_e3;

	}

	private Object child__G_5;
	public final silver.definition.core.TIf_kwd getChild__G_5() {
		return (silver.definition.core.TIf_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_e1;
	public final silver.definition.core.NExpr getChild_e1() {
		return (silver.definition.core.NExpr) (child_e1 = common.Util.demand(child_e1));
	}

	private Object child__G_3;
	public final silver.definition.core.TThen_kwd getChild__G_3() {
		return (silver.definition.core.TThen_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_e2;
	public final silver.definition.core.NExpr getChild_e2() {
		return (silver.definition.core.NExpr) (child_e2 = common.Util.demand(child_e2));
	}

	private Object child__G_1;
	public final silver.definition.core.TElse_kwd getChild__G_1() {
		return (silver.definition.core.TElse_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e3;
	public final silver.definition.core.NExpr getChild_e3() {
		return (silver.definition.core.NExpr) (child_e3 = common.Util.demand(child_e3));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i_e1: return getChild_e1();
			case i__G_3: return getChild__G_3();
			case i_e2: return getChild_e2();
			case i__G_1: return getChild__G_1();
			case i_e3: return getChild_e3();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i_e1: return child_e1;
			case i__G_3: return child__G_3;
			case i_e2: return child_e2;
			case i__G_1: return child__G_1;
			case i_e3: return child_e3;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:ifThenElse erroneously claimed to forward");
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
		return "silver:definition:core:ifThenElse";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "if " ++ e1.pp ++ " then " ++ e2.pp ++ " else " ++ e3.pp
		silver.definition.core.PifThenElse.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("if ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PifThenElse.i_e1).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" then ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PifThenElse.i_e2).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" else ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PifThenElse.i_e3).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr))))))); } };
		// top.errors := e1.errors ++ e2.errors ++ e3.errors
		if(silver.definition.core.PifThenElse.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PifThenElse.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PifThenElse.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PifThenElse.i_e1, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PifThenElse.i_e2, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr), context.childDecoratedSynthesizedLazy(silver.definition.core.PifThenElse.i_e3, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr))); } })); } });
		// top.typerep = e2.typerep
		silver.definition.core.PifThenElse.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)context.childDecorated(silver.definition.core.PifThenElse.i_e2).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr)); } };

	}

	public static final common.NodeFactory<PifThenElse> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PifThenElse> {

		@Override
		public PifThenElse invoke(final Object[] children, final Object[] annotations) {
			return new PifThenElse(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
