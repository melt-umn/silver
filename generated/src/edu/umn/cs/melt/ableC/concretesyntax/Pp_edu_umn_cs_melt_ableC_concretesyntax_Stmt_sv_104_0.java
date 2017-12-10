
package edu.umn.cs.melt.ableC.concretesyntax;

// top::IterationStmt_c ::= 'for' '(' init::Declaration_c cond::ExprStmt_c iter::Expr_c ')' body::Stmt_c 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0 extends edu.umn.cs.melt.ableC.concretesyntax.NIterationStmt_c {

	public static final int i__G_6 = 0;
	public static final int i__G_5 = 1;
	public static final int i_init = 2;
	public static final int i_cond = 3;
	public static final int i_iter = 4;
	public static final int i__G_1 = 5;
	public static final int i_body = 6;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.TFOR.class,edu.umn.cs.melt.ableC.concretesyntax.TLParen_t.class,edu.umn.cs.melt.ableC.concretesyntax.NDeclaration_c.class,edu.umn.cs.melt.ableC.concretesyntax.NExprStmt_c.class,edu.umn.cs.melt.ableC.concretesyntax.NExpr_c.class,edu.umn.cs.melt.ableC.concretesyntax.TRParen_t.class,edu.umn.cs.melt.ableC.concretesyntax.NStmt_c.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_p_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NIterationStmt_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NIterationStmt_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_init] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NDeclaration_c.num_inh_attrs];
	childInheritedAttributes[i_cond] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NExprStmt_c.num_inh_attrs];
	childInheritedAttributes[i_iter] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NExpr_c.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NStmt_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0(final Object c__G_6, final Object c__G_5, final Object c_init, final Object c_cond, final Object c_iter, final Object c__G_1, final Object c_body, final Object a_core_location) {
		super(a_core_location);
		this.child__G_6 = c__G_6;
		this.child__G_5 = c__G_5;
		this.child_init = c_init;
		this.child_cond = c_cond;
		this.child_iter = c_iter;
		this.child__G_1 = c__G_1;
		this.child_body = c_body;

	}

	private Object child__G_6;
	public final edu.umn.cs.melt.ableC.concretesyntax.TFOR getChild__G_6() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TFOR) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child__G_5;
	public final edu.umn.cs.melt.ableC.concretesyntax.TLParen_t getChild__G_5() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TLParen_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_init;
	public final edu.umn.cs.melt.ableC.concretesyntax.NDeclaration_c getChild_init() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NDeclaration_c) (child_init = common.Util.demand(child_init));
	}

	private Object child_cond;
	public final edu.umn.cs.melt.ableC.concretesyntax.NExprStmt_c getChild_cond() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NExprStmt_c) (child_cond = common.Util.demand(child_cond));
	}

	private Object child_iter;
	public final edu.umn.cs.melt.ableC.concretesyntax.NExpr_c getChild_iter() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NExpr_c) (child_iter = common.Util.demand(child_iter));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TRParen_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TRParen_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_body;
	public final edu.umn.cs.melt.ableC.concretesyntax.NStmt_c getChild_body() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NStmt_c) (child_body = common.Util.demand(child_body));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_6: return getChild__G_6();
			case i__G_5: return getChild__G_5();
			case i_init: return getChild_init();
			case i_cond: return getChild_cond();
			case i_iter: return getChild_iter();
			case i__G_1: return getChild__G_1();
			case i_body: return getChild_body();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_6: return child__G_6;
			case i__G_5: return child__G_5;
			case i_init: return child_init;
			case i_cond: return child_cond;
			case i_iter: return child_iter;
			case i__G_1: return child__G_1;
			case i_body: return child_body;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:p_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:p_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = ast:forDeclStmt(init.ast, cond.asMaybeExpr, ast:justExpr(iter.ast,), body.ast,)
		edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_IterationStmt_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)new edu.umn.cs.melt.ableC.abstractsyntax.host.PforDeclStmt(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0.i_init, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_Declaration_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0.i_cond, edu.umn.cs.melt.ableC.concretesyntax.Init.edu_umn_cs_melt_ableC_concretesyntax_asMaybeExpr__ON__edu_umn_cs_melt_ableC_concretesyntax_ExprStmt_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PjustExpr(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0.i_iter, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_Expr_c))); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.Pp_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0.i_body, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_Stmt_c))); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_Stmt_sv_104_0(children[0], children[1], children[2], children[3], children[4], children[5], children[6], annotations[0]);
		}
	};

}
