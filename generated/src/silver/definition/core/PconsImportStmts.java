
package silver.definition.core;

// top::ImportStmts ::= h::ImportStmt t::ImportStmts 
public final class PconsImportStmts extends silver.definition.core.NImportStmts {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NImportStmt.class,silver.definition.core.NImportStmts.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_consImportStmts;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NImportStmts.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NImportStmts.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.core.NImportStmt.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.core.NImportStmts.num_inh_attrs];

	}

	public PconsImportStmts(final Object c_h, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.core.NImportStmt getChild_h() {
		return (silver.definition.core.NImportStmt) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.definition.core.NImportStmts getChild_t() {
		return (silver.definition.core.NImportStmts) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:consImportStmts erroneously claimed to forward");
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
		return "silver:definition:core:consImportStmts";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp ++ "\n" ++ t.pp
		silver.definition.core.PconsImportStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ImportStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PconsImportStmts.i_h).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ImportStmt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PconsImportStmts.i_t).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ImportStmts)))); } };
		// top.errors := h.errors ++ t.errors
		if(silver.definition.core.PconsImportStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ImportStmts] == null)
			silver.definition.core.PconsImportStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ImportStmts] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ImportStmts);
		((common.CollectionAttribute)silver.definition.core.PconsImportStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ImportStmts]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsImportStmts.i_h, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ImportStmt), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsImportStmts.i_t, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ImportStmts))); } });
		// top.moduleNames = h.moduleNames ++ t.moduleNames
		silver.definition.core.PconsImportStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ImportStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsImportStmts.i_h, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ImportStmt), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsImportStmts.i_t, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ImportStmts))); } };
		// top.defs = h.defs ++ t.defs
		silver.definition.core.PconsImportStmts.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ImportStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsImportStmts.i_h, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ImportStmt), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsImportStmts.i_t, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ImportStmts))); } };

	}

	public static final common.NodeFactory<PconsImportStmts> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsImportStmts> {

		@Override
		public PconsImportStmts invoke(final Object[] children, final Object[] annotations) {
			return new PconsImportStmts(children[0], children[1], annotations[0]);
		}
	};

}
