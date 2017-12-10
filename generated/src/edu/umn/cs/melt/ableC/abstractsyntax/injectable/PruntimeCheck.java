
package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

// top::RuntimeMod ::= check::(Expr ::= Expr) failMessage::String l::Location 
public final class PruntimeCheck extends edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod {

	public static final int i_check = 0;
	public static final int i_failMessage = 1;
	public static final int i_l = 2;


	public static final Class<?> childTypes[] = {common.NodeFactory.class,common.StringCatter.class,core.NLocation.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_runtimeCheck;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PruntimeCheck(final Object c_check, final Object c_failMessage, final Object c_l) {
		super();
		this.child_check = c_check;
		this.child_failMessage = c_failMessage;
		this.child_l = c_l;

	}

	private Object child_check;
	public final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> getChild_check() {
		return (common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>) (child_check = common.Util.demand(child_check));
	}

	private Object child_failMessage;
	public final common.StringCatter getChild_failMessage() {
		return (common.StringCatter) (child_failMessage = common.Util.demand(child_failMessage));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_check: return getChild_check();
			case i_failMessage: return getChild_failMessage();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_check: return child_check;
			case i_failMessage: return child_failMessage;
			case i_l: return child_l;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:injectable:runtimeCheck erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:injectable:runtimeCheck";
	}

	static void initProductionAttributeDefinitions() {
		// top.modExpr = stmtExpr(ifStmtNoElse(check(top.exprToModify,), parseStmt("fprintf(stderr, \"" ++ l.unparse ++ ":" ++ failMessage ++ "\"); exit(255);",),), top.exprToModify,location=bogusLoc(,))
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PruntimeCheck.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstmtExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)new edu.umn.cs.melt.ableC.abstractsyntax.host.PifStmtNoElse(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PruntimeCheck.i_check)).invoke(new Object[]{context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod)}, null)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PparseStmt.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("fprintf(stderr, \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PruntimeCheck.i_l).synthesized(silver.langutil.Init.silver_langutil_unparse__ON__core_Location)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PruntimeCheck.i_failMessage)), (common.StringCatter)(new common.StringCatter("\"); exit(255);")))))); } })); } })); } }, context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)core.PbogusLoc.invoke()); } })); } };

	}

	public static final common.NodeFactory<PruntimeCheck> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PruntimeCheck> {

		@Override
		public PruntimeCheck invoke(final Object[] children, final Object[] annotations) {
			return new PruntimeCheck(children[0], children[1], children[2]);
		}
	};

}
