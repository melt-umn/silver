
package silver.definition.type;

// top::Type ::= fn::String params::[Type] 
public final class PnonterminalType extends silver.definition.type.NType {

	public static final int i_fn = 0;
	public static final int i_params = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_nonterminalType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.NType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnonterminalType(final Object c_fn, final Object c_params) {
		super();
		this.child_fn = c_fn;
		this.child_params = c_params;

	}

	private Object child_fn;
	public final common.StringCatter getChild_fn() {
		return (common.StringCatter) (child_fn = common.Util.demand(child_fn));
	}

	private Object child_params;
	public final common.ConsCell getChild_params() {
		return (common.ConsCell) (child_params = common.Util.demand(child_params));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fn: return getChild_fn();
			case i_params: return getChild_params();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fn: return child_fn;
			case i_params: return child_params;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:nonterminalType erroneously claimed to forward");
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
		return "silver:definition:type:nonterminalType";
	}

	static void initProductionAttributeDefinitions() {
		// top.freeVariables = setUnionTyVarsAll(map((.freeVariables), params))
		silver.definition.type.PnonterminalType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PsetUnionTyVarsAll.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type, context), context.childAsIsLazy(silver.definition.type.PnonterminalType.i_params))); } })); } };

	}

	public static final common.NodeFactory<PnonterminalType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnonterminalType> {

		@Override
		public PnonterminalType invoke(final Object[] children, final Object[] annotations) {
			return new PnonterminalType(children[0], children[1]);
		}
	};

}
