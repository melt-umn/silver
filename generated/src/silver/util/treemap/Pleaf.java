
package silver.util.treemap;

// top::TreeMap<a b> ::= CMP::(Integer ::= a a) 
public final class Pleaf extends silver.util.treemap.NTreeMap {

	public static final int i_CMP = 0;


	public static final Class<?> childTypes[] = {common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_util_treemap_leaf;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.util.treemap.NTreeMap.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.util.treemap.NTreeMap.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pleaf(final Object c_CMP) {
		super();
		this.child_CMP = c_CMP;

	}

	private Object child_CMP;
	public final common.NodeFactory<Integer> getChild_CMP() {
		return (common.NodeFactory<Integer>) (child_CMP = common.Util.demand(child_CMP));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_CMP: return getChild_CMP();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_CMP: return child_CMP;

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
		throw new common.exceptions.SilverInternalError("Production silver:util:treemap:leaf erroneously claimed to forward");
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
		return "silver:util:treemap:leaf";
	}

	static void initProductionAttributeDefinitions() {
		// top.treeLookup = []
		silver.util.treemap.Pleaf.synthesizedAttributes[silver.util.treemap.Init.silver_util_treemap_treeLookup__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.treeInsert = node(false, top, top, top.treeKey, [ top.treeValue ], CMP)
		silver.util.treemap.Pleaf.synthesizedAttributes[silver.util.treemap.Init.silver_util_treemap_treeInsert__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(false, context.undecorate(), context.undecorate(), context.contextInheritedLazy(silver.util.treemap.Init.silver_util_treemap_treeKey__ON__silver_util_treemap_TreeMap), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.contextInheritedLazy(silver.util.treemap.Init.silver_util_treemap_treeValue__ON__silver_util_treemap_TreeMap), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childAsIsLazy(silver.util.treemap.Pleaf.i_CMP))); } };
		// top.makeBlack = top
		silver.util.treemap.Pleaf.synthesizedAttributes[silver.util.treemap.Init.silver_util_treemap_makeBlack__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.treeToList = []
		silver.util.treemap.Pleaf.synthesizedAttributes[silver.util.treemap.Init.silver_util_treemap_treeToList__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<Pleaf> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pleaf> {

		@Override
		public Pleaf invoke(final Object[] children, final Object[] annotations) {
			return new Pleaf(children[0]);
		}
	};

}
