
package silver.modification.impide;

// top::IdePropertyOptions ::= h::IdePropertyOption t::IdePropertyOptions 
public final class PconsPropertyOptions extends silver.modification.impide.NIdePropertyOptions {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.modification.impide.NIdePropertyOption.class,silver.modification.impide.NIdePropertyOptions.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_consPropertyOptions;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NIdePropertyOptions.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NIdePropertyOptions.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.modification.impide.NIdePropertyOption.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.modification.impide.NIdePropertyOptions.num_inh_attrs];

	}

	public PconsPropertyOptions(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.modification.impide.NIdePropertyOption getChild_h() {
		return (silver.modification.impide.NIdePropertyOption) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.modification.impide.NIdePropertyOptions getChild_t() {
		return (silver.modification.impide.NIdePropertyOptions) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:consPropertyOptions erroneously claimed to forward");
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
		return "silver:modification:impide:consPropertyOptions";
	}

	static void initProductionAttributeDefinitions() {
		// top.propRequired = h.propRequired ++ t.propRequired
		silver.modification.impide.PconsPropertyOptions.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propRequired__ON__silver_modification_impide_IdePropertyOptions] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsPropertyOptions.i_h, silver.modification.impide.Init.silver_modification_impide_propRequired__ON__silver_modification_impide_IdePropertyOption), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsPropertyOptions.i_t, silver.modification.impide.Init.silver_modification_impide_propRequired__ON__silver_modification_impide_IdePropertyOptions))); } };
		// top.propDefault = h.propDefault ++ t.propDefault
		silver.modification.impide.PconsPropertyOptions.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOptions] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsPropertyOptions.i_h, silver.modification.impide.Init.silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOption), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsPropertyOptions.i_t, silver.modification.impide.Init.silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOptions))); } };
		// top.propDisplay = h.propDisplay ++ t.propDisplay
		silver.modification.impide.PconsPropertyOptions.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDisplay__ON__silver_modification_impide_IdePropertyOptions] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsPropertyOptions.i_h, silver.modification.impide.Init.silver_modification_impide_propDisplay__ON__silver_modification_impide_IdePropertyOption), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsPropertyOptions.i_t, silver.modification.impide.Init.silver_modification_impide_propDisplay__ON__silver_modification_impide_IdePropertyOptions))); } };

	}

	public static final common.NodeFactory<PconsPropertyOptions> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsPropertyOptions> {

		@Override
		public PconsPropertyOptions invoke(final Object[] children, final Object[] annotations) {
			return new PconsPropertyOptions(children[0], children[1]);
		}
	};

}
