
package silver.modification.copper;

// top::TerminalPrefixItems ::= t::TerminalPrefixItem ',' ts::TerminalPrefixItems 
public final class PconsTerminalPrefixItem extends silver.modification.copper.NTerminalPrefixItems {

	public static final int i_t = 0;
	public static final int i__G_1 = 1;
	public static final int i_ts = 2;


	public static final Class<?> childTypes[] = {silver.modification.copper.NTerminalPrefixItem.class,silver.definition.core.TComma_t.class,silver.modification.copper.NTerminalPrefixItems.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_consTerminalPrefixItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefixItems.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefixItems.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.modification.copper.NTerminalPrefixItem.num_inh_attrs];
	childInheritedAttributes[i_ts] = new common.Lazy[silver.modification.copper.NTerminalPrefixItems.num_inh_attrs];

	}

	public PconsTerminalPrefixItem(final Object c_t, final Object c__G_1, final Object c_ts, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;
		this.child__G_1 = c__G_1;
		this.child_ts = c_ts;

	}

	private Object child_t;
	public final silver.modification.copper.NTerminalPrefixItem getChild_t() {
		return (silver.modification.copper.NTerminalPrefixItem) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_1;
	public final silver.definition.core.TComma_t getChild__G_1() {
		return (silver.definition.core.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_ts;
	public final silver.modification.copper.NTerminalPrefixItems getChild_ts() {
		return (silver.modification.copper.NTerminalPrefixItems) (child_ts = common.Util.demand(child_ts));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i__G_1: return getChild__G_1();
			case i_ts: return getChild_ts();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i__G_1: return child__G_1;
			case i_ts: return child_ts;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:consTerminalPrefixItem erroneously claimed to forward");
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
		return "silver:modification:copper:consTerminalPrefixItem";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = ts.pp ++ ", " ++ t.pp
		silver.modification.copper.PconsTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItems] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PconsTerminalPrefixItem.i_ts).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItems)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PconsTerminalPrefixItem.i_t).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItem)))); } };
		// top.errors := ts.errors ++ t.errors
		if(silver.modification.copper.PconsTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems] == null)
			silver.modification.copper.PconsTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems);
		((common.CollectionAttribute)silver.modification.copper.PconsTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsTerminalPrefixItem.i_ts, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems), context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsTerminalPrefixItem.i_t, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItem))); } });
		// top.prefixItemNames = ts.prefixItemNames ++ t.prefixItemNames
		silver.modification.copper.PconsTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItems] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsTerminalPrefixItem.i_ts, silver.modification.copper.Init.silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItems), context.childDecoratedSynthesizedLazy(silver.modification.copper.PconsTerminalPrefixItem.i_t, silver.modification.copper.Init.silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItem))); } };

	}

	public static final common.NodeFactory<PconsTerminalPrefixItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsTerminalPrefixItem> {

		@Override
		public PconsTerminalPrefixItem invoke(final Object[] children, final Object[] annotations) {
			return new PconsTerminalPrefixItem(children[0], children[1], children[2], annotations[0]);
		}
	};

}
