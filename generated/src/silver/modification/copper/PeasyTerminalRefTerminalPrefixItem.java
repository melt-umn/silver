
package silver.modification.copper;

// top::TerminalPrefixItem ::= t::EasyTerminalRef 
public final class PeasyTerminalRefTerminalPrefixItem extends silver.modification.copper.NTerminalPrefixItem {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {silver.extension.easyterminal.NEasyTerminalRef.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_easyTerminalRefTerminalPrefixItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefixItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefixItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.extension.easyterminal.NEasyTerminalRef.num_inh_attrs];

	}

	public PeasyTerminalRefTerminalPrefixItem(final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;

	}

	private Object child_t;
	public final silver.extension.easyterminal.NEasyTerminalRef getChild_t() {
		return (silver.extension.easyterminal.NEasyTerminalRef) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:easyTerminalRefTerminalPrefixItem erroneously claimed to forward");
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
		return "silver:modification:copper:easyTerminalRefTerminalPrefixItem";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = t.pp
		silver.modification.copper.PeasyTerminalRefTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.copper.PeasyTerminalRefTerminalPrefixItem.i_t).synthesized(silver.extension.easyterminal.Init.silver_definition_env_pp__ON__silver_extension_easyterminal_EasyTerminalRef)); } };
		// top.errors := t.errors
		if(silver.modification.copper.PeasyTerminalRefTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItem] == null)
			silver.modification.copper.PeasyTerminalRefTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItem] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItem);
		((common.CollectionAttribute)silver.modification.copper.PeasyTerminalRefTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItem]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PeasyTerminalRefTerminalPrefixItem.i_t).synthesized(silver.extension.easyterminal.Init.silver_definition_core_errors__ON__silver_extension_easyterminal_EasyTerminalRef)); } });
		// top.prefixItemNames = map(qName(top.location, _), map((.fullName), t.dcls))
		silver.modification.copper.PeasyTerminalRefTerminalPrefixItem.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.core.PqName.factory.invokePartial(new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefixItem)context.undecorate()).getAnno_core_location()); } }}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo, context), context.childDecoratedSynthesizedLazy(silver.modification.copper.PeasyTerminalRefTerminalPrefixItem.i_t, silver.extension.easyterminal.Init.silver_definition_core_dcls__ON__silver_extension_easyterminal_EasyTerminalRef))); } })); } };

	}

	public static final common.NodeFactory<PeasyTerminalRefTerminalPrefixItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PeasyTerminalRefTerminalPrefixItem> {

		@Override
		public PeasyTerminalRefTerminalPrefixItem invoke(final Object[] children, final Object[] annotations) {
			return new PeasyTerminalRefTerminalPrefixItem(children[0], annotations[0]);
		}
	};

}
