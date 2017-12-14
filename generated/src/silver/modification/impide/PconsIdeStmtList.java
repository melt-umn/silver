
package silver.modification.impide;

// top::IdeStmtList ::= stmt::IdeStmt stmtList::IdeStmtList 
public final class PconsIdeStmtList extends silver.modification.impide.NIdeStmtList {

	public static final int i_stmt = 0;
	public static final int i_stmtList = 1;


	public static final Class<?> childTypes[] = {silver.modification.impide.NIdeStmt.class,silver.modification.impide.NIdeStmtList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_consIdeStmtList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NIdeStmtList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NIdeStmtList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_stmt] = new common.Lazy[silver.modification.impide.NIdeStmt.num_inh_attrs];
	childInheritedAttributes[i_stmtList] = new common.Lazy[silver.modification.impide.NIdeStmtList.num_inh_attrs];

	}

	public PconsIdeStmtList(final Object c_stmt, final Object c_stmtList, final Object a_core_location) {
		super(a_core_location);
		this.child_stmt = c_stmt;
		this.child_stmtList = c_stmtList;

	}

	private Object child_stmt;
	public final silver.modification.impide.NIdeStmt getChild_stmt() {
		return (silver.modification.impide.NIdeStmt) (child_stmt = common.Util.demand(child_stmt));
	}

	private Object child_stmtList;
	public final silver.modification.impide.NIdeStmtList getChild_stmtList() {
		return (silver.modification.impide.NIdeStmtList) (child_stmtList = common.Util.demand(child_stmtList));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_stmt: return getChild_stmt();
			case i_stmtList: return getChild_stmtList();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_stmt: return child_stmt;
			case i_stmtList: return child_stmtList;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:consIdeStmtList erroneously claimed to forward");
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
		return "silver:modification:impide:consIdeStmtList";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors := stmt.errors ++ stmtList.errors
		if(silver.modification.impide.PconsIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmtList] == null)
			silver.modification.impide.PconsIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmtList] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmtList);
		((common.CollectionAttribute)silver.modification.impide.PconsIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmtList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmt, silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmtList, silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmtList))); } });
		// top.ideFunctions = stmt.ideFunctions ++ stmtList.ideFunctions
		silver.modification.impide.PconsIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmt, silver.modification.impide.Init.silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmt), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmtList, silver.modification.impide.Init.silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmtList))); } };
		// top.propDcls = stmt.propDcls ++ stmtList.propDcls
		silver.modification.impide.PconsIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmt, silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmt), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmtList, silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmtList))); } };
		// top.wizards = stmt.wizards ++ stmtList.wizards
		silver.modification.impide.PconsIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmt, silver.modification.impide.Init.silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmt), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmtList, silver.modification.impide.Init.silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmtList))); } };
		// top.ideNames = stmt.ideNames ++ stmtList.ideNames
		silver.modification.impide.PconsIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmt, silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmt), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmtList, silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmtList))); } };
		// top.ideVersions = stmt.ideVersions ++ stmtList.ideVersions
		silver.modification.impide.PconsIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmt, silver.modification.impide.Init.silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmt), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmtList, silver.modification.impide.Init.silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmtList))); } };
		// top.ideResources = stmt.ideResources ++ stmtList.ideResources
		silver.modification.impide.PconsIdeStmtList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmt, silver.modification.impide.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmt), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsIdeStmtList.i_stmtList, silver.modification.impide.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmtList))); } };

	}

	public static final common.NodeFactory<PconsIdeStmtList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsIdeStmtList> {

		@Override
		public PconsIdeStmtList invoke(final Object[] children, final Object[] annotations) {
			return new PconsIdeStmtList(children[0], children[1], annotations[0]);
		}
	};

}
