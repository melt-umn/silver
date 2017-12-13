
package silver.definition.core;

// top::ProductionBody ::= '{' stmts::ProductionStmts '}' 
public final class PproductionBody extends silver.definition.core.NProductionBody {

	public static final int i__G_2 = 0;
	public static final int i_stmts = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.TLCurly_t.class,silver.definition.core.NProductionStmts.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_productionBody;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionBody.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_stmts] = new common.Lazy[silver.definition.core.NProductionStmts.num_inh_attrs];

	}

	public PproductionBody(final Object c__G_2, final Object c_stmts, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_stmts = c_stmts;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.definition.core.TLCurly_t getChild__G_2() {
		return (silver.definition.core.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_stmts;
	public final silver.definition.core.NProductionStmts getChild_stmts() {
		return (silver.definition.core.NProductionStmts) (child_stmts = common.Util.demand(child_stmts));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_stmts: return getChild_stmts();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_stmts: return child_stmts;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:productionBody erroneously claimed to forward");
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
		return "silver:definition:core:productionBody";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = stmts.pp
		silver.definition.core.PproductionBody.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PproductionBody.i_stmts).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionStmts)); } };
		// top.productionAttributes = stmts.productionAttributes
		silver.definition.core.PproductionBody.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_productionAttributes__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PproductionBody.i_stmts).synthesized(silver.definition.core.Init.silver_definition_core_productionAttributes__ON__silver_definition_core_ProductionStmts)); } };
		// top.uniqueSignificantExpression = stmts.uniqueSignificantExpression
		silver.definition.core.PproductionBody.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_uniqueSignificantExpression__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PproductionBody.i_stmts).synthesized(silver.definition.core.Init.silver_definition_core_uniqueSignificantExpression__ON__silver_definition_core_ProductionStmts)); } };
		// top.defs = stmts.defs
		silver.definition.core.PproductionBody.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PproductionBody.i_stmts).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionStmts)); } };
		// top.errors := stmts.errors
		if(silver.definition.core.PproductionBody.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionBody] == null)
			silver.definition.core.PproductionBody.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionBody] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionBody);
		((common.CollectionAttribute)silver.definition.core.PproductionBody.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionBody]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PproductionBody.i_stmts).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmts)); } });

	}

	public static final common.NodeFactory<PproductionBody> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionBody> {

		@Override
		public PproductionBody invoke(final Object[] children, final Object[] annotations) {
			return new PproductionBody(children[0], children[1], children[2], annotations[0]);
		}
	};

}
