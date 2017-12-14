
package silver.modification.impide;

// top::PropertyList ::= 
public final class PnilPropertyList extends silver.modification.impide.NPropertyList {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_nilPropertyList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NPropertyList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NPropertyList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilPropertyList() {
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:nilPropertyList erroneously claimed to forward");
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
		return "silver:modification:impide:nilPropertyList";
	}

	static void initProductionAttributeDefinitions() {
		// top.propDcls = []
		silver.modification.impide.PnilPropertyList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_PropertyList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.errors := []
		if(silver.modification.impide.PnilPropertyList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_PropertyList] == null)
			silver.modification.impide.PnilPropertyList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_PropertyList] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_PropertyList);
		((common.CollectionAttribute)silver.modification.impide.PnilPropertyList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_PropertyList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });

	}

	public static final common.NodeFactory<PnilPropertyList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilPropertyList> {

		@Override
		public PnilPropertyList invoke(final Object[] children, final Object[] annotations) {
			return new PnilPropertyList();
		}
	};

}
