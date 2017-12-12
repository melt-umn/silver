
package silver.util.treemap;

// top::TreeMap<a b> ::= black::Boolean lefttree::TreeMap<a b> righttree::TreeMap<a b> label::a values::[b] CMP::(Integer ::= a a) 
public final class Pnode extends silver.util.treemap.NTreeMap {

	public static final int i_black = 0;
	public static final int i_lefttree = 1;
	public static final int i_righttree = 2;
	public static final int i_label = 3;
	public static final int i_values = 4;
	public static final int i_CMP = 5;


	public static final Class<?> childTypes[] = {Boolean.class,silver.util.treemap.NTreeMap.class,silver.util.treemap.NTreeMap.class,Object.class,common.DecoratedNode.class,common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_util_treemap_node;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.util.treemap.NTreeMap.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.util.treemap.NTreeMap.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_lefttree] = new common.Lazy[silver.util.treemap.NTreeMap.num_inh_attrs];
	childInheritedAttributes[i_righttree] = new common.Lazy[silver.util.treemap.NTreeMap.num_inh_attrs];

	}

	public Pnode(final Object c_black, final Object c_lefttree, final Object c_righttree, final Object c_label, final Object c_values, final Object c_CMP) {
		super();
		this.child_black = c_black;
		this.child_lefttree = c_lefttree;
		this.child_righttree = c_righttree;
		this.child_label = c_label;
		this.child_values = c_values;
		this.child_CMP = c_CMP;

	}

	private Object child_black;
	public final Boolean getChild_black() {
		return (Boolean) (child_black = common.Util.demand(child_black));
	}

	private Object child_lefttree;
	public final silver.util.treemap.NTreeMap getChild_lefttree() {
		return (silver.util.treemap.NTreeMap) (child_lefttree = common.Util.demand(child_lefttree));
	}

	private Object child_righttree;
	public final silver.util.treemap.NTreeMap getChild_righttree() {
		return (silver.util.treemap.NTreeMap) (child_righttree = common.Util.demand(child_righttree));
	}

	private Object child_label;
	public final Object getChild_label() {
		return (Object) (child_label = common.Util.demand(child_label));
	}

	private Object child_values;
	public final common.ConsCell getChild_values() {
		return (common.ConsCell) (child_values = common.Util.demand(child_values));
	}

	private Object child_CMP;
	public final common.NodeFactory<Integer> getChild_CMP() {
		return (common.NodeFactory<Integer>) (child_CMP = common.Util.demand(child_CMP));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_black: return getChild_black();
			case i_lefttree: return getChild_lefttree();
			case i_righttree: return getChild_righttree();
			case i_label: return getChild_label();
			case i_values: return getChild_values();
			case i_CMP: return getChild_CMP();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_black: return child_black;
			case i_lefttree: return child_lefttree;
			case i_righttree: return child_righttree;
			case i_label: return child_label;
			case i_values: return child_values;
			case i_CMP: return child_CMP;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:util:treemap:node erroneously claimed to forward");
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
		return "silver:util:treemap:node";
	}

	static void initProductionAttributeDefinitions() {
		// top.treeLookup = let cmpr :: Integer = CMP(top.treeKey, label) in if cmpr <= 0 then if cmpr == 0 then values else lefttree.treeLookup else righttree.treeLookup end
		silver.util.treemap.Pnode.synthesizedAttributes[silver.util.treemap.Init.silver_util_treemap_treeLookup__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10869_cmpr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((common.NodeFactory<Integer>)context.childAsIs(silver.util.treemap.Pnode.i_CMP)).invoke(new Object[]{context.contextInheritedLazy(silver.util.treemap.Init.silver_util_treemap_treeKey__ON__silver_util_treemap_TreeMap), context.childAsIsLazy(silver.util.treemap.Pnode.i_label)}, null)); } };
return ((((Integer)(__SV_LOCAL_10869_cmpr.eval())) <= Integer.valueOf((int)0)) ? (((Integer)(__SV_LOCAL_10869_cmpr.eval())).equals(Integer.valueOf((int)0)) ? ((common.ConsCell)context.childAsIs(silver.util.treemap.Pnode.i_values)) : ((common.ConsCell)context.childDecorated(silver.util.treemap.Pnode.i_lefttree).synthesized(silver.util.treemap.Init.silver_util_treemap_treeLookup__ON__silver_util_treemap_TreeMap))) : ((common.ConsCell)context.childDecorated(silver.util.treemap.Pnode.i_righttree).synthesized(silver.util.treemap.Init.silver_util_treemap_treeLookup__ON__silver_util_treemap_TreeMap))); } }).eval()); } };
		// top.treeInsert = let cmpr :: Integer = CMP(top.treeKey, label) in if cmpr <= 0 then if cmpr == 0 then node(black, lefttree, righttree, label, (top.treeValue :: values), CMP) else if black then balanceL(lefttree.treeInsert, righttree, label, values, CMP) else node(false, lefttree.treeInsert, righttree, label, values, CMP) else if black then balanceR(lefttree, righttree.treeInsert, label, values, CMP) else node(false, lefttree, righttree.treeInsert, label, values, CMP) end
		silver.util.treemap.Pnode.synthesizedAttributes[silver.util.treemap.Init.silver_util_treemap_treeInsert__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10878_cmpr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((common.NodeFactory<Integer>)context.childAsIs(silver.util.treemap.Pnode.i_CMP)).invoke(new Object[]{context.contextInheritedLazy(silver.util.treemap.Init.silver_util_treemap_treeKey__ON__silver_util_treemap_TreeMap), context.childAsIsLazy(silver.util.treemap.Pnode.i_label)}, null)); } };
return ((((Integer)(__SV_LOCAL_10878_cmpr.eval())) <= Integer.valueOf((int)0)) ? (((Integer)(__SV_LOCAL_10878_cmpr.eval())).equals(Integer.valueOf((int)0)) ? ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(context.childAsIsLazy(silver.util.treemap.Pnode.i_black), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.Pnode.i_lefttree)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.Pnode.i_righttree)), context.childAsIsLazy(silver.util.treemap.Pnode.i_label), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.contextInheritedLazy(silver.util.treemap.Init.silver_util_treemap_treeValue__ON__silver_util_treemap_TreeMap), context.childAsIsLazy(silver.util.treemap.Pnode.i_values))); } }, context.childAsIsLazy(silver.util.treemap.Pnode.i_CMP))) : (((Boolean)context.childAsIs(silver.util.treemap.Pnode.i_black)) ? ((silver.util.treemap.NTreeMap)silver.util.treemap.PbalanceL.invoke(context.childDecoratedSynthesizedLazy(silver.util.treemap.Pnode.i_lefttree, silver.util.treemap.Init.silver_util_treemap_treeInsert__ON__silver_util_treemap_TreeMap), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.Pnode.i_righttree)), context.childAsIsLazy(silver.util.treemap.Pnode.i_label), context.childAsIsLazy(silver.util.treemap.Pnode.i_values), context.childAsIsLazy(silver.util.treemap.Pnode.i_CMP))) : ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(false, context.childDecoratedSynthesizedLazy(silver.util.treemap.Pnode.i_lefttree, silver.util.treemap.Init.silver_util_treemap_treeInsert__ON__silver_util_treemap_TreeMap), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.Pnode.i_righttree)), context.childAsIsLazy(silver.util.treemap.Pnode.i_label), context.childAsIsLazy(silver.util.treemap.Pnode.i_values), context.childAsIsLazy(silver.util.treemap.Pnode.i_CMP))))) : (((Boolean)context.childAsIs(silver.util.treemap.Pnode.i_black)) ? ((silver.util.treemap.NTreeMap)silver.util.treemap.PbalanceR.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.Pnode.i_lefttree)), context.childDecoratedSynthesizedLazy(silver.util.treemap.Pnode.i_righttree, silver.util.treemap.Init.silver_util_treemap_treeInsert__ON__silver_util_treemap_TreeMap), context.childAsIsLazy(silver.util.treemap.Pnode.i_label), context.childAsIsLazy(silver.util.treemap.Pnode.i_values), context.childAsIsLazy(silver.util.treemap.Pnode.i_CMP))) : ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(false, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.Pnode.i_lefttree)), context.childDecoratedSynthesizedLazy(silver.util.treemap.Pnode.i_righttree, silver.util.treemap.Init.silver_util_treemap_treeInsert__ON__silver_util_treemap_TreeMap), context.childAsIsLazy(silver.util.treemap.Pnode.i_label), context.childAsIsLazy(silver.util.treemap.Pnode.i_values), context.childAsIsLazy(silver.util.treemap.Pnode.i_CMP))))); } }).eval()); } };
		// top.makeBlack = if black then top else node(true, lefttree, righttree, label, values, CMP)
		silver.util.treemap.Pnode.synthesizedAttributes[silver.util.treemap.Init.silver_util_treemap_makeBlack__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childAsIs(silver.util.treemap.Pnode.i_black)) ? context.undecorate() : ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(true, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.Pnode.i_lefttree)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.Pnode.i_righttree)), context.childAsIsLazy(silver.util.treemap.Pnode.i_label), context.childAsIsLazy(silver.util.treemap.Pnode.i_values), context.childAsIsLazy(silver.util.treemap.Pnode.i_CMP)))); } };
		// top.treeToList = lefttree.treeToList ++ treeMapKeyValues(label, values) ++ righttree.treeToList
		silver.util.treemap.Pnode.synthesizedAttributes[silver.util.treemap.Init.silver_util_treemap_treeToList__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.util.treemap.Pnode.i_lefttree, silver.util.treemap.Init.silver_util_treemap_treeToList__ON__silver_util_treemap_TreeMap), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.treemap.PtreeMapKeyValues.invoke(context.childAsIsLazy(silver.util.treemap.Pnode.i_label), context.childAsIsLazy(silver.util.treemap.Pnode.i_values))); } }, context.childDecoratedSynthesizedLazy(silver.util.treemap.Pnode.i_righttree, silver.util.treemap.Init.silver_util_treemap_treeToList__ON__silver_util_treemap_TreeMap))); } })); } };
		// lefttree.treeKey = top.treeKey
		silver.util.treemap.Pnode.childInheritedAttributes[silver.util.treemap.Pnode.i_lefttree][silver.util.treemap.Init.silver_util_treemap_treeKey__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.util.treemap.Init.silver_util_treemap_treeKey__ON__silver_util_treemap_TreeMap)); } };
		// lefttree.treeValue = top.treeValue
		silver.util.treemap.Pnode.childInheritedAttributes[silver.util.treemap.Pnode.i_lefttree][silver.util.treemap.Init.silver_util_treemap_treeValue__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.util.treemap.Init.silver_util_treemap_treeValue__ON__silver_util_treemap_TreeMap)); } };
		// righttree.treeKey = top.treeKey
		silver.util.treemap.Pnode.childInheritedAttributes[silver.util.treemap.Pnode.i_righttree][silver.util.treemap.Init.silver_util_treemap_treeKey__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.util.treemap.Init.silver_util_treemap_treeKey__ON__silver_util_treemap_TreeMap)); } };
		// righttree.treeValue = top.treeValue
		silver.util.treemap.Pnode.childInheritedAttributes[silver.util.treemap.Pnode.i_righttree][silver.util.treemap.Init.silver_util_treemap_treeValue__ON__silver_util_treemap_TreeMap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.util.treemap.Init.silver_util_treemap_treeValue__ON__silver_util_treemap_TreeMap)); } };

	}

	public static final common.NodeFactory<Pnode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pnode> {

		@Override
		public Pnode invoke(final Object[] children, final Object[] annotations) {
			return new Pnode(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};

}
