
package silver.modification.copper;

// top::TerminalPrefixItems ::= t::TerminalPrefixItem 
public final class PoneTerminalPrefixItem extends silver.modification.copper.NTerminalPrefixItems {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {silver.modification.copper.NTerminalPrefixItem.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_oneTerminalPrefixItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefixItems.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefixItems.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.modification.copper.NTerminalPrefixItem.num_inh_attrs];

	}

	public PoneTerminalPrefixItem(final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;

	}

	private Object child_t;
	public final silver.modification.copper.NTerminalPrefixItem getChild_t() {
		return (silver.modification.copper.NTerminalPrefixItem) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:oneTerminalPrefixItem erroneously claimed to forward");
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
		return "silver:modification:copper:oneTerminalPrefixItem";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = t.pp
		silver.modification.copper.PoneTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItems] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.copper.PoneTerminalPrefixItem.i_t).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItem)); } };
		// top.errors := t.errors
		if(silver.modification.copper.PoneTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems] == null)
			silver.modification.copper.PoneTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems);
		((common.CollectionAttribute)silver.modification.copper.PoneTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PoneTerminalPrefixItem.i_t).synthesized(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItem)); } });
		// top.prefixItemNames = t.prefixItemNames
		silver.modification.copper.PoneTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItems] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PoneTerminalPrefixItem.i_t).synthesized(silver.modification.copper.Init.silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItem)); } };

	}

	public static final common.NodeFactory<PoneTerminalPrefixItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneTerminalPrefixItem> {

		@Override
		public PoneTerminalPrefixItem invoke(final Object[] children, final Object[] annotations) {
			return new PoneTerminalPrefixItem(children[0], annotations[0]);
		}
	};

}
