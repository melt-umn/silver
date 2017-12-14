
package silver.definition.type;

// top::Type ::= out::Type params::[Type] namedParams::[NamedArgType] 
public final class PfunctionType extends silver.definition.type.NType {

	public static final int i_out = 0;
	public static final int i_params = 1;
	public static final int i_namedParams = 2;


	public static final Class<?> childTypes[] = {silver.definition.type.NType.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_functionType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.NType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_out] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PfunctionType(final Object c_out, final Object c_params, final Object c_namedParams) {
		super();
		this.child_out = c_out;
		this.child_params = c_params;
		this.child_namedParams = c_namedParams;

	}

	private Object child_out;
	public final silver.definition.type.NType getChild_out() {
		return (silver.definition.type.NType) (child_out = common.Util.demand(child_out));
	}

	private Object child_params;
	public final common.ConsCell getChild_params() {
		return (common.ConsCell) (child_params = common.Util.demand(child_params));
	}

	private Object child_namedParams;
	public final common.ConsCell getChild_namedParams() {
		return (common.ConsCell) (child_namedParams = common.Util.demand(child_namedParams));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_out: return getChild_out();
			case i_params: return getChild_params();
			case i_namedParams: return getChild_namedParams();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_out: return child_out;
			case i_params: return child_params;
			case i_namedParams: return child_namedParams;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:functionType erroneously claimed to forward");
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
		return "silver:definition:type:functionType";
	}

	static void initProductionAttributeDefinitions() {
		// top.freeVariables = setUnionTyVarsAll(map((.freeVariables), (out :: params) ++ map((.argType), namedParams)))
		silver.definition.type.PfunctionType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PsetUnionTyVarsAll.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type, context), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PfunctionType.i_out)), context.childAsIsLazy(silver.definition.type.PfunctionType.i_params))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.type.Init.silver_definition_type_argType__ON__silver_definition_type_NamedArgType, context), context.childAsIsLazy(silver.definition.type.PfunctionType.i_namedParams))); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PfunctionType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfunctionType> {

		@Override
		public PfunctionType invoke(final Object[] children, final Object[] annotations) {
			return new PfunctionType(children[0], children[1], children[2]);
		}
	};

}
