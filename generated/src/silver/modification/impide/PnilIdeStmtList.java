
package silver.modification.impide;

// top::IdeStmtList ::= 
public final class PnilIdeStmtList extends silver.modification.impide.NIdeStmtList {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_nilIdeStmtList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NIdeStmtList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NIdeStmtList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilIdeStmtList(final Object a_core_location) {
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:nilIdeStmtList erroneously claimed to forward");
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
		return "silver:modification:impide:nilIdeStmtList";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors := []
		if(silver.modification.impide.PnilIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmtList] == null)
			silver.modification.impide.PnilIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmtList] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmtList);
		((common.CollectionAttribute)silver.modification.impide.PnilIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmtList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.ideFunctions = []
		silver.modification.impide.PnilIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.propDcls = []
		silver.modification.impide.PnilIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.wizards = []
		silver.modification.impide.PnilIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.ideNames = []
		silver.modification.impide.PnilIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.ideVersions = []
		silver.modification.impide.PnilIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.ideResources = []
		silver.modification.impide.PnilIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PnilIdeStmtList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilIdeStmtList> {

		@Override
		public PnilIdeStmtList invoke(final Object[] children, final Object[] annotations) {
			return new PnilIdeStmtList(annotations[0]);
		}
	};

}
