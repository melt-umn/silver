
package silver.extension.convenience;

// top::AGDcl ::= 'concrete' 'productions' lhs::ProductionLHS stmts::ProductionDclStmts 
public final class PproductionDclC extends silver.definition.core.NAGDcl {

	public static final int i__G_3 = 0;
	public static final int i__G_2 = 1;
	public static final int i_lhs = 2;
	public static final int i_stmts = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.TConcrete_kwd.class,silver.extension.convenience.TProductions_kwd.class,silver.definition.core.NProductionLHS.class,silver.extension.convenience.NProductionDclStmts.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_productionDclC;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_lhs] = new common.Lazy[silver.definition.core.NProductionLHS.num_inh_attrs];
	childInheritedAttributes[i_stmts] = new common.Lazy[silver.extension.convenience.NProductionDclStmts.num_inh_attrs];

	}

	public PproductionDclC(final Object c__G_3, final Object c__G_2, final Object c_lhs, final Object c_stmts, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_lhs = c_lhs;
		this.child_stmts = c_stmts;

	}

	private Object child__G_3;
	public final silver.definition.core.TConcrete_kwd getChild__G_3() {
		return (silver.definition.core.TConcrete_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.extension.convenience.TProductions_kwd getChild__G_2() {
		return (silver.extension.convenience.TProductions_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_lhs;
	public final silver.definition.core.NProductionLHS getChild_lhs() {
		return (silver.definition.core.NProductionLHS) (child_lhs = common.Util.demand(child_lhs));
	}

	private Object child_stmts;
	public final silver.extension.convenience.NProductionDclStmts getChild_stmts() {
		return (silver.extension.convenience.NProductionDclStmts) (child_stmts = common.Util.demand(child_stmts));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_lhs: return getChild_lhs();
			case i_stmts: return getChild_stmts();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_lhs: return child_lhs;
			case i_stmts: return child_stmts;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAGDcl)context.childDecorated(silver.extension.convenience.PproductionDclC.i_stmts).synthesized(silver.extension.convenience.Init.silver_extension_convenience_proddcls__ON__silver_extension_convenience_ProductionDclStmts));
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
		return "silver:extension:convenience:productionDclC";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "concrete productions " ++ lhs.pp ++ stmts.pp
		silver.extension.convenience.PproductionDclC.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("concrete productions ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PproductionDclC.i_lhs).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionLHS)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PproductionDclC.i_stmts).synthesized(silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_ProductionDclStmts)))); } };
		// top.moduleNames = []
		silver.extension.convenience.PproductionDclC.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// stmts.lhsdcl = lhs
		silver.extension.convenience.PproductionDclC.childInheritedAttributes[silver.extension.convenience.PproductionDclC.i_stmts][silver.extension.convenience.Init.silver_extension_convenience_lhsdcl__ON__silver_extension_convenience_ProductionDclStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.convenience.PproductionDclC.i_lhs).undecorate(); } };

	}

	public static final common.NodeFactory<PproductionDclC> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionDclC> {

		@Override
		public PproductionDclC invoke(final Object[] children, final Object[] annotations) {
			return new PproductionDclC(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
