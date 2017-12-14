
package silver.definition.core;

// top::Expr ::= e1::Decorated Expr e2::Decorated Expr 
public final class PerrorPlusPlus extends silver.definition.core.NExpr {

	public static final int i_e1 = 0;
	public static final int i_e2 = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_errorPlusPlus;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PerrorPlusPlus(final Object c_e1, final Object c_e2, final Object a_core_location) {
		super(a_core_location);
		this.child_e1 = c_e1;
		this.child_e2 = c_e2;

	}

	private Object child_e1;
	public final common.DecoratedNode getChild_e1() {
		return (common.DecoratedNode) (child_e1 = common.Util.demand(child_e1));
	}

	private Object child_e2;
	public final common.DecoratedNode getChild_e2() {
		return (common.DecoratedNode) (child_e2 = common.Util.demand(child_e2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e1: return getChild_e1();
			case i_e2: return getChild_e2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e1: return child_e1;
			case i_e2: return child_e2;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:errorPlusPlus erroneously claimed to forward");
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
		return "silver:definition:core:errorPlusPlus";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e1.pp ++ " ++ " ++ e2.pp
		silver.definition.core.PerrorPlusPlus.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PerrorPlusPlus.i_e1)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ++ ")), (common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PerrorPlusPlus.i_e2)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)))); } };
		// top.errors := [ err(e1.location, prettyType(performSubstitution(e1.typerep, e1.upSubst)) ++ " is not a concatenable type.") ]
		if(silver.definition.core.PerrorPlusPlus.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PerrorPlusPlus.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PerrorPlusPlus.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)((common.DecoratedNode)context.childAsIs(silver.definition.core.PerrorPlusPlus.i_e1)).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.type.PprettyType.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childAsIsSynthesizedLazy(silver.definition.core.PerrorPlusPlus.i_e1, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.childAsIsSynthesizedLazy(silver.definition.core.PerrorPlusPlus.i_e1, silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr))); } })), (common.StringCatter)(new common.StringCatter(" is not a concatenable type."))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// top.typerep = errorType()
		silver.definition.core.PerrorPlusPlus.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()); } };

	}

	public static final common.NodeFactory<PerrorPlusPlus> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PerrorPlusPlus> {

		@Override
		public PerrorPlusPlus invoke(final Object[] children, final Object[] annotations) {
			return new PerrorPlusPlus(children[0], children[1], annotations[0]);
		}
	};

}
