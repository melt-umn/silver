
package silver.modification.copper;

// top::TermPrecList ::= 
public final class PtermPrecListNull extends silver.modification.copper.NTermPrecList {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_termPrecListNull;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NTermPrecList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NTermPrecList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtermPrecListNull(final Object a_core_location) {
		super(a_core_location);

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:termPrecListNull erroneously claimed to forward");
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
		return "silver:modification:copper:termPrecListNull";
	}

	static void initProductionAttributeDefinitions() {
		// top.precTermList = []
		silver.modification.copper.PtermPrecListNull.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_precTermList__ON__silver_modification_copper_TermPrecList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.pp = ""
		silver.modification.copper.PtermPrecListNull.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TermPrecList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.errors := []
		if(silver.modification.copper.PtermPrecListNull.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermPrecList] == null)
			silver.modification.copper.PtermPrecListNull.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermPrecList] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermPrecList);
		((common.CollectionAttribute)silver.modification.copper.PtermPrecListNull.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermPrecList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });

	}

	public static final common.NodeFactory<PtermPrecListNull> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtermPrecListNull> {

		@Override
		public PtermPrecListNull invoke(final Object[] children, final Object[] annotations) {
			return new PtermPrecListNull(annotations[0]);
		}
	};

}
