
package silver.modification.let_fix;

// top::Expr ::= la::AssignExpr e::Expr 
public final class Pletp extends silver.definition.core.NExpr {

	public static final int i_la = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = {silver.modification.let_fix.NAssignExpr.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_let_fix_letp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_la] = new common.Lazy[silver.modification.let_fix.NAssignExpr.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public Pletp(final Object c_la, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_la = c_la;
		this.child_e = c_e;

	}

	private Object child_la;
	public final silver.modification.let_fix.NAssignExpr getChild_la() {
		return (silver.modification.let_fix.NAssignExpr) (child_la = common.Util.demand(child_la));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_la: return getChild_la();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_la: return child_la;
			case i_e: return child_e;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:let_fix:letp erroneously claimed to forward");
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
		return "silver:modification:let_fix:letp";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "let " ++ la.pp ++ " in " ++ e.pp ++ " end"
		silver.modification.let_fix.Pletp.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("let ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.Pletp.i_la).synthesized(silver.modification.let_fix.Init.silver_definition_env_pp__ON__silver_modification_let_fix_AssignExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" in ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.let_fix.Pletp.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(" end")))))); } };
		// top.errors := la.errors ++ e.errors
		if(silver.modification.let_fix.Pletp.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.modification.let_fix.Pletp.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.modification.let_fix.Pletp.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.let_fix.Pletp.i_la, silver.modification.let_fix.Init.silver_definition_core_errors__ON__silver_modification_let_fix_AssignExpr), context.childDecoratedSynthesizedLazy(silver.modification.let_fix.Pletp.i_e, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr))); } });
		// top.typerep = e.typerep
		silver.modification.let_fix.Pletp.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)context.childDecorated(silver.modification.let_fix.Pletp.i_e).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr)); } };
		// la.downSubst = top.downSubst
		silver.modification.let_fix.Pletp.childInheritedAttributes[silver.modification.let_fix.Pletp.i_la][silver.modification.let_fix.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_let_fix_AssignExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr)); } };
		// e.downSubst = la.upSubst
		silver.modification.let_fix.Pletp.childInheritedAttributes[silver.modification.let_fix.Pletp.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.let_fix.Pletp.i_la).synthesized(silver.modification.let_fix.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_let_fix_AssignExpr)); } };
		// top.upSubst = e.upSubst
		silver.modification.let_fix.Pletp.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.let_fix.Pletp.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// e.env = newScopeEnv(la.defs, top.env)
		silver.modification.let_fix.Pletp.childInheritedAttributes[silver.modification.let_fix.Pletp.i_e][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.childDecoratedSynthesizedLazy(silver.modification.let_fix.Pletp.i_la, silver.modification.let_fix.Init.silver_definition_env_defs__ON__silver_modification_let_fix_AssignExpr), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Expr))); } };

	}

	public static final common.NodeFactory<Pletp> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pletp> {

		@Override
		public Pletp invoke(final Object[] children, final Object[] annotations) {
			return new Pletp(children[0], children[1], annotations[0]);
		}
	};

}
