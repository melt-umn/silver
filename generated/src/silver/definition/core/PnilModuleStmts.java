
package silver.definition.core;

// top::ModuleStmts ::= 
public final class PnilModuleStmts extends silver.definition.core.NModuleStmts {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_nilModuleStmts;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NModuleStmts.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NModuleStmts.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilModuleStmts(final Object a_core_location) {
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:nilModuleStmts erroneously claimed to forward");
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
		return "silver:definition:core:nilModuleStmts";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = ""
		silver.definition.core.PnilModuleStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.errors := []
		if(silver.definition.core.PnilModuleStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmts] == null)
			silver.definition.core.PnilModuleStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmts] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmts);
		((common.CollectionAttribute)silver.definition.core.PnilModuleStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmts]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.moduleNames = []
		silver.definition.core.PnilModuleStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.defs = []
		silver.definition.core.PnilModuleStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.exportedGrammars = []
		silver.definition.core.PnilModuleStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_exportedGrammars__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.optionalGrammars = []
		silver.definition.core.PnilModuleStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_optionalGrammars__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.condBuild = []
		silver.definition.core.PnilModuleStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_condBuild__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PnilModuleStmts> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilModuleStmts> {

		@Override
		public PnilModuleStmts invoke(final Object[] children, final Object[] annotations) {
			return new PnilModuleStmts(annotations[0]);
		}
	};

}
