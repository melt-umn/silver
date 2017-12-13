
package silver.definition.core;

// top::ModuleStmt ::= 'imports' m::ModuleExpr ';' 
public final class PimportsStmt extends silver.definition.core.NModuleStmt {

	public static final int i__G_2 = 0;
	public static final int i_m = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.TImports_kwd.class,silver.definition.core.NModuleExpr.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_importsStmt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NModuleStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NModuleStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_m] = new common.Lazy[silver.definition.core.NModuleExpr.num_inh_attrs];

	}

	public PimportsStmt(final Object c__G_2, final Object c_m, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_m = c_m;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.definition.core.TImports_kwd getChild__G_2() {
		return (silver.definition.core.TImports_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_m;
	public final silver.definition.core.NModuleExpr getChild_m() {
		return (silver.definition.core.NModuleExpr) (child_m = common.Util.demand(child_m));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_m: return getChild_m();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_m: return child_m;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:importsStmt erroneously claimed to forward");
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
		return "silver:definition:core:importsStmt";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "imports " ++ m.pp ++ ";"
		silver.definition.core.PimportsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("imports ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PimportsStmt.i_m).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleExpr)), (common.StringCatter)(new common.StringCatter(";")))); } };
		// top.errors := m.errors
		if(silver.definition.core.PimportsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmt] == null)
			silver.definition.core.PimportsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmt);
		((common.CollectionAttribute)silver.definition.core.PimportsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PimportsStmt.i_m).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleExpr)); } });
		// top.moduleNames = m.moduleNames
		silver.definition.core.PimportsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PimportsStmt.i_m).synthesized(silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleExpr)); } };
		// top.defs = m.defs
		silver.definition.core.PimportsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PimportsStmt.i_m).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleExpr)); } };
		// top.exportedGrammars = []
		silver.definition.core.PimportsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_exportedGrammars__ON__silver_definition_core_ModuleStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.optionalGrammars = []
		silver.definition.core.PimportsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_optionalGrammars__ON__silver_definition_core_ModuleStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.condBuild = []
		silver.definition.core.PimportsStmt.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_condBuild__ON__silver_definition_core_ModuleStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PimportsStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PimportsStmt> {

		@Override
		public PimportsStmt invoke(final Object[] children, final Object[] annotations) {
			return new PimportsStmt(children[0], children[1], children[2], annotations[0]);
		}
	};

}
