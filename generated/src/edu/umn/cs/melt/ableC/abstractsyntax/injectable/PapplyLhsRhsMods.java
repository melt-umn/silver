
package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

public final class PapplyLhsRhsMods extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_lhs = 1;
	public static final int i_rhs = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PapplyLhsRhsMods(final Object c_l, final Object c_lhs, final Object c_rhs) {
		this.child_l = c_l;
		this.child_lhs = c_lhs;
		this.child_rhs = c_rhs;

	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_lhs;
	public final common.DecoratedNode getChild_lhs() {
		return (common.DecoratedNode) (child_lhs = common.Util.demand(child_lhs));
	}

	private Object child_rhs;
	public final common.DecoratedNode getChild_rhs() {
		return (common.DecoratedNode) (child_rhs = common.Util.demand(child_rhs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_lhs: return getChild_lhs();
			case i_rhs: return getChild_rhs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_lhs: return child_lhs;
			case i_rhs: return child_rhs;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyLhsRhsMods";
	}

	public static core.NPair invoke(final Object c_l, final Object c_lhs, final Object c_rhs) {
		try {
		final common.DecoratedNode context = new PapplyLhsRhsMods(c_l, c_lhs, c_rhs).decorate();
		//pair(modLhs, modRhs,)
		return (core.NPair)(((core.NPair)new core.Ppair(common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modLhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods)), common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyLhsRhsMods)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyLhsRhsMods", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PapplyLhsRhsMods.invoke(children[0], children[1], children[2]);
		}
	};
}