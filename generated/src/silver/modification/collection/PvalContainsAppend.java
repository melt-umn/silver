
package silver.modification.collection;

// top::ProductionStmt ::= val::QName '<-' e::Expr ';' 
public final class PvalContainsAppend extends silver.definition.core.NProductionStmt {

	public static final int i_val = 0;
	public static final int i__G_2 = 1;
	public static final int i_e = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.modification.collection.TContains_t.class,silver.definition.core.NExpr.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_collection_valContainsAppend;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_val] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PvalContainsAppend(final Object c_val, final Object c__G_2, final Object c_e, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_val = c_val;
		this.child__G_2 = c__G_2;
		this.child_e = c_e;
		this.child__G_0 = c__G_0;

	}

	private Object child_val;
	public final silver.definition.core.NQName getChild_val() {
		return (silver.definition.core.NQName) (child_val = common.Util.demand(child_val));
	}

	private Object child__G_2;
	public final silver.modification.collection.TContains_t getChild__G_2() {
		return (silver.modification.collection.TContains_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_val: return getChild_val();
			case i__G_2: return getChild__G_2();
			case i_e: return getChild_e();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_val: return child_val;
			case i__G_2: return child__G_2;
			case i_e: return child_e;
			case i__G_0: return child__G_0;

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
		return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.collection.PvalContainsAppend.i_val).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) ? ((silver.definition.core.NProductionStmt)new silver.definition.core.PerrorValueDef(context.childDecoratedLazy(silver.modification.collection.PvalContainsAppend.i_val), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.collection.PvalContainsAppend.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NProductionStmt)((common.NodeFactory<silver.definition.core.NProductionStmt>)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.modification.collection.PvalContainsAppend.i_val).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.modification.collection.Init.silver_modification_collection_appendDefDispatcher__ON__silver_definition_env_DclInfo)).invoke(new Object[]{context.childDecoratedLazy(silver.modification.collection.PvalContainsAppend.i_val), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.collection.PvalContainsAppend.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }}, null)));
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
		return "silver:modification:collection:valContainsAppend";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = val.pp ++ " <- " ++ e.pp ++ ";"
		silver.modification.collection.PvalContainsAppend.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.collection.PvalContainsAppend.i_val).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" <- ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.collection.PvalContainsAppend.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(";"))))); } };
		// top.errors <- val.lookupValue.errors
		if(silver.modification.collection.PvalContainsAppend.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.modification.collection.PvalContainsAppend.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.modification.collection.PvalContainsAppend.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.collection.PvalContainsAppend.i_val).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } });
		// top.productionAttributes = []
		silver.modification.collection.PvalContainsAppend.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_productionAttributes__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.defs = []
		silver.modification.collection.PvalContainsAppend.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PvalContainsAppend> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PvalContainsAppend> {

		@Override
		public PvalContainsAppend invoke(final Object[] children, final Object[] annotations) {
			return new PvalContainsAppend(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
