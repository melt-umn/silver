
package silver.driver.util;

// top::Grammars ::= 
public final class PnilGrammars extends silver.driver.util.NGrammars {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_nilGrammars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NGrammars.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NGrammars.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilGrammars() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:driver:util:nilGrammars erroneously claimed to forward");
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
		return "silver:driver:util:nilGrammars";
	}

	static void initProductionAttributeDefinitions() {
		// top.grammarList = []
		silver.driver.util.PnilGrammars.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.recheckGrammars = []
		silver.driver.util.PnilGrammars.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_recheckGrammars__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.translateGrammars = []
		silver.driver.util.PnilGrammars.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_translateGrammars__ON__silver_driver_util_Grammars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PnilGrammars> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilGrammars> {

		@Override
		public PnilGrammars invoke(final Object[] children, final Object[] annotations) {
			return new PnilGrammars();
		}
	};

}
