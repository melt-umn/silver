
package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

// top::RuntimeMod ::= ins::(Stmt ::= Expr) 
public final class PruntimeInsertion extends edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod {

	public static final int i_ins = 0;


	public static final Class<?> childTypes[] = {common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_runtimeInsertion;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.injectable.NRuntimeMod.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PruntimeInsertion(final Object c_ins) {
		super();
		this.child_ins = c_ins;

	}

	private Object child_ins;
	public final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt> getChild_ins() {
		return (common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt>) (child_ins = common.Util.demand(child_ins));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ins: return getChild_ins();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ins: return child_ins;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:injectable:runtimeInsertion erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:injectable:runtimeInsertion";
	}

	static void initProductionAttributeDefinitions() {
		// top.modExpr = stmtExpr(ins(top.exprToModify,), top.exprToModify,location=bogusLoc(,))
		edu.umn.cs.melt.ableC.abstractsyntax.injectable.PruntimeInsertion.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstmtExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt)((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NStmt>)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.injectable.PruntimeInsertion.i_ins)).invoke(new Object[]{context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod)}, null)); } }, context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.edu_umn_cs_melt_ableC_abstractsyntax_injectable_exprToModify__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_RuntimeMod), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)core.PbogusLoc.invoke()); } })); } };

	}

	public static final common.NodeFactory<PruntimeInsertion> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PruntimeInsertion> {

		@Override
		public PruntimeInsertion invoke(final Object[] children, final Object[] annotations) {
			return new PruntimeInsertion(children[0]);
		}
	};

}
