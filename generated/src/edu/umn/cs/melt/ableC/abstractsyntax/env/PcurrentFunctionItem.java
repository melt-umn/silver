
package edu.umn.cs.melt.ableC.abstractsyntax.env;

// top::MiscItem ::= n::Decorated Name f::Decorated FunctionDecl 
public final class PcurrentFunctionItem extends edu.umn.cs.melt.ableC.abstractsyntax.env.NMiscItem {

	public static final int i_n = 0;
	public static final int i_f = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_currentFunctionItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NMiscItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.env.NMiscItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PcurrentFunctionItem(final Object c_n, final Object c_f) {
		super();
		this.child_n = c_n;
		this.child_f = c_f;

	}

	private Object child_n;
	public final common.DecoratedNode getChild_n() {
		return (common.DecoratedNode) (child_n = common.Util.demand(child_n));
	}

	private Object child_f;
	public final common.DecoratedNode getChild_f() {
		return (common.DecoratedNode) (child_f = common.Util.demand(child_f));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_f: return getChild_f();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_f: return child_f;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:env:currentFunctionItem erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:currentFunctionItem";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PcurrentFunctionItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcurrentFunctionItem> {

		@Override
		public PcurrentFunctionItem invoke(final Object[] children, final Object[] annotations) {
			return new PcurrentFunctionItem(children[0], children[1]);
		}
	};

}
