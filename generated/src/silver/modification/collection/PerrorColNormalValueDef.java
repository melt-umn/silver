
package silver.modification.collection;

// top::ProductionStmt ::= val::Decorated QName e::Expr 
public final class PerrorColNormalValueDef extends silver.definition.core.NProductionStmt {

	public static final int i_val = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_collection_errorColNormalValueDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PerrorColNormalValueDef(final Object c_val, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_val = c_val;
		this.child_e = c_e;

	}

	private Object child_val;
	public final common.DecoratedNode getChild_val() {
		return (common.DecoratedNode) (child_val = common.Util.demand(child_val));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_val: return getChild_val();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_val: return child_val;
			case i_e: return child_e;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NProductionStmt)new silver.definition.core.PerrorValueDef(context.childAsIsLazy(silver.modification.collection.PerrorColNormalValueDef.i_val), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.collection.PerrorColNormalValueDef.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:collection:errorColNormalValueDef";
	}

	static void initProductionAttributeDefinitions() {
		// top.errors <- [ err(top.location, val.pp ++ " is a collection attribute, and you must use ':=' or '<-', not '='.") ]
		if(silver.modification.collection.PerrorColNormalValueDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.modification.collection.PerrorColNormalValueDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.modification.collection.PerrorColNormalValueDef.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.collection.PerrorColNormalValueDef.i_val)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter(" is a collection attribute, and you must use ':=' or '<-', not '='."))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });

	}

	public static final common.NodeFactory<PerrorColNormalValueDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PerrorColNormalValueDef> {

		@Override
		public PerrorColNormalValueDef invoke(final Object[] children, final Object[] annotations) {
			return new PerrorColNormalValueDef(children[0], children[1], annotations[0]);
		}
	};

}
