
package silver.util.fixedmap;

// top::Map<b> ::= label::String values::[b] l::Map<b> r::Map<b> 
public final class Pnode extends silver.util.fixedmap.NMap {

	public static final int i_label = 0;
	public static final int i_values = 1;
	public static final int i_l = 2;
	public static final int i_r = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class,silver.util.fixedmap.NMap.class,silver.util.fixedmap.NMap.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_util_fixedmap_node;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.util.fixedmap.NMap.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.util.fixedmap.NMap.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.util.fixedmap.NMap.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.util.fixedmap.NMap.num_inh_attrs];

	}

	public Pnode(final Object c_label, final Object c_values, final Object c_l, final Object c_r) {
		super();
		this.child_label = c_label;
		this.child_values = c_values;
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_label;
	public final common.StringCatter getChild_label() {
		return (common.StringCatter) (child_label = common.Util.demand(child_label));
	}

	private Object child_values;
	public final common.ConsCell getChild_values() {
		return (common.ConsCell) (child_values = common.Util.demand(child_values));
	}

	private Object child_l;
	public final silver.util.fixedmap.NMap getChild_l() {
		return (silver.util.fixedmap.NMap) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final silver.util.fixedmap.NMap getChild_r() {
		return (silver.util.fixedmap.NMap) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_label: return getChild_label();
			case i_values: return getChild_values();
			case i_l: return getChild_l();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_label: return child_label;
			case i_values: return child_values;
			case i_l: return child_l;
			case i_r: return child_r;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:util:fixedmap:node erroneously claimed to forward");
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
		return "silver:util:fixedmap:node";
	}

	static void initProductionAttributeDefinitions() {
		// l.treeKey = top.treeKey
		silver.util.fixedmap.Pnode.childInheritedAttributes[silver.util.fixedmap.Pnode.i_l][silver.util.fixedmap.Init.silver_util_fixedmap_treeKey__ON__silver_util_fixedmap_Map] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver.util.fixedmap.Init.silver_util_fixedmap_treeKey__ON__silver_util_fixedmap_Map)); } };
		// r.treeKey = top.treeKey
		silver.util.fixedmap.Pnode.childInheritedAttributes[silver.util.fixedmap.Pnode.i_r][silver.util.fixedmap.Init.silver_util_fixedmap_treeKey__ON__silver_util_fixedmap_Map] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver.util.fixedmap.Init.silver_util_fixedmap_treeKey__ON__silver_util_fixedmap_Map)); } };
		// top.treeLookup = if top.treeKey <= label then if top.treeKey == label then values else l.treeLookup else r.treeLookup
		silver.util.fixedmap.Pnode.synthesizedAttributes[silver.util.fixedmap.Init.silver_util_fixedmap_treeLookup__ON__silver_util_fixedmap_Map] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((common.StringCatter)context.inherited(silver.util.fixedmap.Init.silver_util_fixedmap_treeKey__ON__silver_util_fixedmap_Map)).toString().compareTo(((common.StringCatter)context.childAsIs(silver.util.fixedmap.Pnode.i_label)).toString()) <= 0) ? (((common.StringCatter)context.inherited(silver.util.fixedmap.Init.silver_util_fixedmap_treeKey__ON__silver_util_fixedmap_Map)).equals(((common.StringCatter)context.childAsIs(silver.util.fixedmap.Pnode.i_label))) ? ((common.ConsCell)context.childAsIs(silver.util.fixedmap.Pnode.i_values)) : ((common.ConsCell)context.childDecorated(silver.util.fixedmap.Pnode.i_l).synthesized(silver.util.fixedmap.Init.silver_util_fixedmap_treeLookup__ON__silver_util_fixedmap_Map))) : ((common.ConsCell)context.childDecorated(silver.util.fixedmap.Pnode.i_r).synthesized(silver.util.fixedmap.Init.silver_util_fixedmap_treeLookup__ON__silver_util_fixedmap_Map))); } };
		// top.treeToList = l.treeToList ++ treeMapKeyValues(label, values,) ++ r.treeToList
		silver.util.fixedmap.Pnode.synthesizedAttributes[silver.util.fixedmap.Init.silver_util_fixedmap_treeToList__ON__silver_util_fixedmap_Map] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.util.fixedmap.Pnode.i_l, silver.util.fixedmap.Init.silver_util_fixedmap_treeToList__ON__silver_util_fixedmap_Map), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.fixedmap.PtreeMapKeyValues.invoke(context.childAsIsLazy(silver.util.fixedmap.Pnode.i_label), context.childAsIsLazy(silver.util.fixedmap.Pnode.i_values))); } }, context.childDecoratedSynthesizedLazy(silver.util.fixedmap.Pnode.i_r, silver.util.fixedmap.Init.silver_util_fixedmap_treeToList__ON__silver_util_fixedmap_Map))); } })); } };

	}

	public static final common.NodeFactory<Pnode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pnode> {

		@Override
		public Pnode invoke(final Object[] children, final Object[] annotations) {
			return new Pnode(children[0], children[1], children[2], children[3]);
		}
	};

}
