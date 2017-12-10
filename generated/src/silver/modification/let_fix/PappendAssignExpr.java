
package silver.modification.let_fix;

// top::AssignExpr ::= a1::AssignExpr a2::AssignExpr 
public final class PappendAssignExpr extends silver.modification.let_fix.NAssignExpr {

	public static final int i_a1 = 0;
	public static final int i_a2 = 1;


	public static final Class<?> childTypes[] = {silver.modification.let_fix.NAssignExpr.class,silver.modification.let_fix.NAssignExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_let_fix_appendAssignExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.let_fix.NAssignExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.let_fix.NAssignExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_a1] = new common.Lazy[silver.modification.let_fix.NAssignExpr.num_inh_attrs];
	childInheritedAttributes[i_a2] = new common.Lazy[silver.modification.let_fix.NAssignExpr.num_inh_attrs];

	}

	public PappendAssignExpr(final Object c_a1, final Object c_a2, final Object a_core_location) {
		super(a_core_location);
		this.child_a1 = c_a1;
		this.child_a2 = c_a2;

	}

	private Object child_a1;
	public final silver.modification.let_fix.NAssignExpr getChild_a1() {
		return (silver.modification.let_fix.NAssignExpr) (child_a1 = common.Util.demand(child_a1));
	}

	private Object child_a2;
	public final silver.modification.let_fix.NAssignExpr getChild_a2() {
		return (silver.modification.let_fix.NAssignExpr) (child_a2 = common.Util.demand(child_a2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a1: return getChild_a1();
			case i_a2: return getChild_a2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a1: return child_a1;
			case i_a2: return child_a2;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:let_fix:appendAssignExpr erroneously claimed to forward");
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
		return "silver:modification:let_fix:appendAssignExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = a1.pp ++ ", " ++ a2.pp
		silver.modification.let_fix.PappendAssignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_AssignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PappendAssignExpr.i_a1).synthesized(silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_AssignExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.PappendAssignExpr.i_a2).synthesized(silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_AssignExpr)))); } };
		// top.defs = a1.defs ++ a2.defs
		silver.modification.let_fix.PappendAssignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_env_defs__ON__silver_modification_let_fix_AssignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PappendAssignExpr.i_a1, silver.modification.let_fix.Init.silver_definition_env_defs__ON__silver_modification_let_fix_AssignExpr), context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PappendAssignExpr.i_a2, silver.modification.let_fix.Init.silver_definition_env_defs__ON__silver_modification_let_fix_AssignExpr))); } };
		// top.errors := a1.errors ++ a2.errors
		if(silver.modification.let_fix.PappendAssignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr] == null)
			silver.modification.let_fix.PappendAssignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr] = new silver.definition.core.CAerrors(silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr);
		((common.CollectionAttribute)silver.modification.let_fix.PappendAssignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PappendAssignExpr.i_a1, silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr), context.childDecoratedSynthesizedLazy(silver.modification.let_fix.PappendAssignExpr.i_a2, silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr))); } });
		// a1.downSubst = top.downSubst
		silver.modification.let_fix.PappendAssignExpr.childInheritedAttributes[silver.modification.let_fix.PappendAssignExpr.i_a1][silver.modification.let_fix.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_let_fix_AssignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.let_fix.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_let_fix_AssignExpr)); } };
		// a2.downSubst = a1.upSubst
		silver.modification.let_fix.PappendAssignExpr.childInheritedAttributes[silver.modification.let_fix.PappendAssignExpr.i_a2][silver.modification.let_fix.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_let_fix_AssignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.let_fix.PappendAssignExpr.i_a1).synthesized(silver.modification.let_fix.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_let_fix_AssignExpr)); } };
		// top.upSubst = a2.upSubst
		silver.modification.let_fix.PappendAssignExpr.synthesizedAttributes[silver.modification.let_fix.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_let_fix_AssignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.let_fix.PappendAssignExpr.i_a2).synthesized(silver.modification.let_fix.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_let_fix_AssignExpr)); } };

	}

	public static final common.NodeFactory<PappendAssignExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PappendAssignExpr> {

		@Override
		public PappendAssignExpr invoke(final Object[] children, final Object[] annotations) {
			return new PappendAssignExpr(children[0], children[1], annotations[0]);
		}
	};

}
