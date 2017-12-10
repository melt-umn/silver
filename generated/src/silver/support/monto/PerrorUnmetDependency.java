
package silver.support.monto;

// top::ServiceError ::= identifier::ProductIdentifier 
public final class PerrorUnmetDependency extends silver.support.monto.NServiceError {

	public static final int i_identifier = 0;


	public static final Class<?> childTypes[] = {silver.support.monto.products.NProductIdentifier.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_errorUnmetDependency;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.NServiceError.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.NServiceError.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_identifier] = new common.Lazy[silver.support.monto.products.NProductIdentifier.num_inh_attrs];

	}

	public PerrorUnmetDependency(final Object c_identifier) {
		super();
		this.child_identifier = c_identifier;

	}

	private Object child_identifier;
	public final silver.support.monto.products.NProductIdentifier getChild_identifier() {
		return (silver.support.monto.products.NProductIdentifier) (child_identifier = common.Util.demand(child_identifier));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_identifier: return getChild_identifier();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_identifier: return child_identifier;

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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:errorUnmetDependency erroneously claimed to forward");
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
		return "silver:support:monto:errorUnmetDependency";
	}

	static void initProductionAttributeDefinitions() {
		// top.json = jsonObject([ pair("type", jsonString("unmet_dependency")), pair("value", identifier.json) ])
		silver.support.monto.PerrorUnmetDependency.synthesizedAttributes[silver.support.monto.Init.silver_json_json__ON__silver_support_monto_ServiceError] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("type")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString((new common.StringCatter("unmet_dependency")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("value")), context.childDecoratedSynthesizedLazy(silver.support.monto.PerrorUnmetDependency.i_identifier, silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ProductIdentifier))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PerrorUnmetDependency> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PerrorUnmetDependency> {

		@Override
		public PerrorUnmetDependency invoke(final Object[] children, final Object[] annotations) {
			return new PerrorUnmetDependency(children[0]);
		}
	};

}
