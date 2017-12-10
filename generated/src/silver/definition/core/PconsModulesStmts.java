
package silver.definition.core;

// top::ModuleStmts ::= h::ModuleStmt t::ModuleStmts 
public final class PconsModulesStmts extends silver.definition.core.NModuleStmts {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NModuleStmt.class,silver.definition.core.NModuleStmts.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_consModulesStmts;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NModuleStmts.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NModuleStmts.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.core.NModuleStmt.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.core.NModuleStmts.num_inh_attrs];

	}

	public PconsModulesStmts(final Object c_h, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.core.NModuleStmt getChild_h() {
		return (silver.definition.core.NModuleStmt) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.definition.core.NModuleStmts getChild_t() {
		return (silver.definition.core.NModuleStmts) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:consModulesStmts erroneously claimed to forward");
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
		return "silver:definition:core:consModulesStmts";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp ++ "\n" ++ t.pp
		silver.definition.core.PconsModulesStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PconsModulesStmts.i_h).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleStmt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PconsModulesStmts.i_t).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleStmts)))); } };
		// top.errors := h.errors ++ t.errors
		if(silver.definition.core.PconsModulesStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmts] == null)
			silver.definition.core.PconsModulesStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmts] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmts);
		((common.CollectionAttribute)silver.definition.core.PconsModulesStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmts]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_h, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmt), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_t, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmts))); } });
		// top.moduleNames = h.moduleNames ++ t.moduleNames
		silver.definition.core.PconsModulesStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_h, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleStmt), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_t, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleStmts))); } };
		// top.defs = h.defs ++ t.defs
		silver.definition.core.PconsModulesStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_h, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleStmt), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_t, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleStmts))); } };
		// top.exportedGrammars = h.exportedGrammars ++ t.exportedGrammars
		silver.definition.core.PconsModulesStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_exportedGrammars__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_h, silver.definition.core.Init.silver_definition_env_exportedGrammars__ON__silver_definition_core_ModuleStmt), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_t, silver.definition.core.Init.silver_definition_env_exportedGrammars__ON__silver_definition_core_ModuleStmts))); } };
		// top.optionalGrammars = h.optionalGrammars ++ t.optionalGrammars
		silver.definition.core.PconsModulesStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_optionalGrammars__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_h, silver.definition.core.Init.silver_definition_env_optionalGrammars__ON__silver_definition_core_ModuleStmt), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_t, silver.definition.core.Init.silver_definition_env_optionalGrammars__ON__silver_definition_core_ModuleStmts))); } };
		// top.condBuild = h.condBuild ++ t.condBuild
		silver.definition.core.PconsModulesStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_condBuild__ON__silver_definition_core_ModuleStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_h, silver.definition.core.Init.silver_definition_env_condBuild__ON__silver_definition_core_ModuleStmt), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsModulesStmts.i_t, silver.definition.core.Init.silver_definition_env_condBuild__ON__silver_definition_core_ModuleStmts))); } };

	}

	public static final common.NodeFactory<PconsModulesStmts> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsModulesStmts> {

		@Override
		public PconsModulesStmts invoke(final Object[] children, final Object[] annotations) {
			return new PconsModulesStmts(children[0], children[1], annotations[0]);
		}
	};

}
