
package edu.umn.cs.melt.ableC.concretesyntax;

// top::BlockItemList_c ::= h::BlockItem_c 
public final class PoneBlockItem_c extends edu.umn.cs.melt.ableC.concretesyntax.NBlockItemList_c {

	public static final int i_h = 0;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.NBlockItem_c.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_oneBlockItem_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NBlockItemList_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NBlockItemList_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NBlockItem_c.num_inh_attrs];

	}

	public PoneBlockItem_c(final Object c_h, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;

	}

	private Object child_h;
	public final edu.umn.cs.melt.ableC.concretesyntax.NBlockItem_c getChild_h() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NBlockItem_c) (child_h = common.Util.demand(child_h));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:oneBlockItem_c erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:oneBlockItem_c";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = h.ast
		edu.umn.cs.melt.ableC.concretesyntax.PoneBlockItem_c.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_BlockItemList_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.concretesyntax.PoneBlockItem_c.i_h).synthesized(edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_BlockItem_c)); } };
		// top.firstBlockItemList_c = oneBlockItem_c(blockStmt_c(exprStmt_c(emptyExprStmt_c(terminal(Semi_t, ";", l),location=l),location=l),location=l),location=l)
		edu.umn.cs.melt.ableC.concretesyntax.PoneBlockItem_c.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.edu_umn_cs_melt_ableC_concretesyntax_firstBlockItemList_c__ON__edu_umn_cs_melt_ableC_concretesyntax_BlockItemList_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.concretesyntax.NBlockItemList_c)new edu.umn.cs.melt.ableC.concretesyntax.PoneBlockItem_c(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.concretesyntax.NBlockItem_c)new edu.umn.cs.melt.ableC.concretesyntax.PblockStmt_c(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.concretesyntax.NStmt_c)new edu.umn.cs.melt.ableC.concretesyntax.PexprStmt_c(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.concretesyntax.NExprStmt_c)new edu.umn.cs.melt.ableC.concretesyntax.PemptyExprStmt_c(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new edu.umn.cs.melt.ableC.concretesyntax.TSemi_t((new common.StringCatter(";")), (core.NLocation)context.localDecorated(edu.umn.cs.melt.ableC.concretesyntax.Init.l__ON__edu_umn_cs_melt_ableC_concretesyntax_oneBlockItem_c).undecorate()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.concretesyntax.Init.l__ON__edu_umn_cs_melt_ableC_concretesyntax_oneBlockItem_c)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.concretesyntax.Init.l__ON__edu_umn_cs_melt_ableC_concretesyntax_oneBlockItem_c)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.concretesyntax.Init.l__ON__edu_umn_cs_melt_ableC_concretesyntax_oneBlockItem_c)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.concretesyntax.Init.l__ON__edu_umn_cs_melt_ableC_concretesyntax_oneBlockItem_c)))); } };
		// top.lastBlockItem_c = h
		edu.umn.cs.melt.ableC.concretesyntax.PoneBlockItem_c.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.edu_umn_cs_melt_ableC_concretesyntax_lastBlockItem_c__ON__edu_umn_cs_melt_ableC_concretesyntax_BlockItemList_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(edu.umn.cs.melt.ableC.concretesyntax.PoneBlockItem_c.i_h).undecorate(); } };
		// l = loc("internal, should not be accessed", 0, 0, 0, 0, 0, 0,)
		edu.umn.cs.melt.ableC.concretesyntax.PoneBlockItem_c.localAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.l__ON__edu_umn_cs_melt_ableC_concretesyntax_oneBlockItem_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NLocation)new core.Ploc((new common.StringCatter("internal, should not be accessed")), Integer.valueOf((int)0), Integer.valueOf((int)0), Integer.valueOf((int)0), Integer.valueOf((int)0), Integer.valueOf((int)0), Integer.valueOf((int)0))); } };

	}

	public static final common.NodeFactory<PoneBlockItem_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneBlockItem_c> {

		@Override
		public PoneBlockItem_c invoke(final Object[] children, final Object[] annotations) {
			return new PoneBlockItem_c(children[0], annotations[0]);
		}
	};

}
