
package silver.extension.convenience;

// top::ProductionDclStmts ::= s::ProductionDclStmt 
public final class PproductionDclStmtsOne extends silver.extension.convenience.NProductionDclStmts {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = {silver.extension.convenience.NProductionDclStmt.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_productionDclStmtsOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.convenience.NProductionDclStmts.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.convenience.NProductionDclStmts.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_s] = new common.Lazy[silver.extension.convenience.NProductionDclStmt.num_inh_attrs];

	}

	public PproductionDclStmtsOne(final Object c_s, final Object a_core_location) {
		super(a_core_location);
		this.child_s = c_s;

	}

	private Object child_s;
	public final silver.extension.convenience.NProductionDclStmt getChild_s() {
		return (silver.extension.convenience.NProductionDclStmt) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:convenience:productionDclStmtsOne erroneously claimed to forward");
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
		return "silver:extension:convenience:productionDclStmtsOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = s.pp
		silver.extension.convenience.PproductionDclStmtsOne.synthesizedAttributes[silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_ProductionDclStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.convenience.PproductionDclStmtsOne.i_s).synthesized(silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_ProductionDclStmt)); } };
		// top.proddcls = s.proddcls
		silver.extension.convenience.PproductionDclStmtsOne.synthesizedAttributes[silver.extension.convenience.Init.silver_extension_convenience_proddcls__ON__silver_extension_convenience_ProductionDclStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)context.childDecorated(silver.extension.convenience.PproductionDclStmtsOne.i_s).synthesized(silver.extension.convenience.Init.silver_extension_convenience_proddcls__ON__silver_extension_convenience_ProductionDclStmt)); } };

	}

	public static final common.NodeFactory<PproductionDclStmtsOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionDclStmtsOne> {

		@Override
		public PproductionDclStmtsOne invoke(final Object[] children, final Object[] annotations) {
			return new PproductionDclStmtsOne(children[0], annotations[0]);
		}
	};

}
