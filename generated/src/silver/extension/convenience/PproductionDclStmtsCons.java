
package silver.extension.convenience;

// top::ProductionDclStmts ::= s::ProductionDclStmt ss::ProductionDclStmts 
public final class PproductionDclStmtsCons extends silver.extension.convenience.NProductionDclStmts {

	public static final int i_s = 0;
	public static final int i_ss = 1;


	public static final Class<?> childTypes[] = {silver.extension.convenience.NProductionDclStmt.class,silver.extension.convenience.NProductionDclStmts.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_productionDclStmtsCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.convenience.NProductionDclStmts.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.convenience.NProductionDclStmts.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_s] = new common.Lazy[silver.extension.convenience.NProductionDclStmt.num_inh_attrs];
	childInheritedAttributes[i_ss] = new common.Lazy[silver.extension.convenience.NProductionDclStmts.num_inh_attrs];

	}

	public PproductionDclStmtsCons(final Object c_s, final Object c_ss, final Object a_core_location) {
		super(a_core_location);
		this.child_s = c_s;
		this.child_ss = c_ss;

	}

	private Object child_s;
	public final silver.extension.convenience.NProductionDclStmt getChild_s() {
		return (silver.extension.convenience.NProductionDclStmt) (child_s = common.Util.demand(child_s));
	}

	private Object child_ss;
	public final silver.extension.convenience.NProductionDclStmts getChild_ss() {
		return (silver.extension.convenience.NProductionDclStmts) (child_ss = common.Util.demand(child_ss));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_ss: return getChild_ss();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_ss: return child_ss;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:convenience:productionDclStmtsCons erroneously claimed to forward");
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
		return "silver:extension:convenience:productionDclStmtsCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = s.pp ++ ss.pp
		silver.extension.convenience.PproductionDclStmtsCons.synthesizedAttributes[silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_ProductionDclStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PproductionDclStmtsCons.i_s).synthesized(silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_ProductionDclStmt)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PproductionDclStmtsCons.i_ss).synthesized(silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_ProductionDclStmts))); } };
		// top.proddcls = appendAGDcl(s.proddcls, ss.proddcls,location=top.location)
		silver.extension.convenience.PproductionDclStmtsCons.synthesizedAttributes[silver.extension.convenience.Init.silver_extension_convenience_proddcls__ON__silver_extension_convenience_ProductionDclStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(context.childDecoratedSynthesizedLazy(silver.extension.convenience.PproductionDclStmtsCons.i_s, silver.extension.convenience.Init.silver_extension_convenience_proddcls__ON__silver_extension_convenience_ProductionDclStmt), context.childDecoratedSynthesizedLazy(silver.extension.convenience.PproductionDclStmtsCons.i_ss, silver.extension.convenience.Init.silver_extension_convenience_proddcls__ON__silver_extension_convenience_ProductionDclStmts), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.convenience.NProductionDclStmts)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PproductionDclStmtsCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionDclStmtsCons> {

		@Override
		public PproductionDclStmtsCons invoke(final Object[] children, final Object[] annotations) {
			return new PproductionDclStmtsCons(children[0], children[1], annotations[0]);
		}
	};

}
