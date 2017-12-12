
package silver.driver.util;

// top::Grammars ::= h::RootSpec t::Grammars 
public final class PconsGrammars extends silver.driver.util.NGrammars {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.driver.util.NRootSpec.class,silver.driver.util.NGrammars.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_consGrammars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NGrammars.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NGrammars.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.driver.util.NRootSpec.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.driver.util.NGrammars.num_inh_attrs];

	}

	public PconsGrammars(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.driver.util.NRootSpec getChild_h() {
		return (silver.driver.util.NRootSpec) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.driver.util.NGrammars getChild_t() {
		return (silver.driver.util.NGrammars) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production silver:driver:util:consGrammars erroneously claimed to forward");
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
		return "silver:driver:util:consGrammars";
	}

	static void initProductionAttributeDefinitions() {
		// top.grammarList = (h :: t.grammarList)
		silver.driver.util.PconsGrammars.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.driver.util.PconsGrammars.i_h), context.childDecoratedSynthesizedLazy(silver.driver.util.PconsGrammars.i_t, silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars))); } };
		// top.recheckGrammars = h.recheckGrammars ++ t.recheckGrammars
		silver.driver.util.PconsGrammars.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.driver.util.PconsGrammars.i_h, silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_RootSpec), context.childDecoratedSynthesizedLazy(silver.driver.util.PconsGrammars.i_t, silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_Grammars))); } };
		// top.translateGrammars = h.translateGrammars ++ t.translateGrammars
		silver.driver.util.PconsGrammars.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_translateGrammars__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.driver.util.PconsGrammars.i_h, silver.driver.util.Init.silver_driver_util_translateGrammars__ON__silver_driver_util_RootSpec), context.childDecoratedSynthesizedLazy(silver.driver.util.PconsGrammars.i_t, silver.driver.util.Init.silver_driver_util_translateGrammars__ON__silver_driver_util_Grammars))); } };

	}

	public static final common.NodeFactory<PconsGrammars> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsGrammars> {

		@Override
		public PconsGrammars invoke(final Object[] children, final Object[] annotations) {
			return new PconsGrammars(children[0], children[1]);
		}
	};

}
