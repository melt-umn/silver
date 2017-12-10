
package edu.umn.cs.melt.ableC.abstractsyntax.injectable;

public final class PapplyMods extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PapplyMods(final Object c_l, final Object c_e) {
		this.child_l = c_l;
		this.child_e = c_e;

	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyMods";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr invoke(final Object c_l, final Object c_e) {
		try {
		final common.DecoratedNode context = new PapplyMods(c_l, c_e).decorate();
		//modExpr
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)(context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.injectable.Init.modExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_injectable_applyMods).undecorate());

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:injectable:applyMods", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PapplyMods.invoke(children[0], children[1]);
		}
	};
}