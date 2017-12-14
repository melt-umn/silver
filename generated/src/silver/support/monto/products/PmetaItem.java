
package silver.support.monto.products;

// top::MetaItem ::= service::String metaType::String value::String 
public final class PmetaItem extends silver.support.monto.products.NMetaItem {

	public static final int i_service = 0;
	public static final int i_metaType = 1;
	public static final int i_value = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_metaItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NMetaItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NMetaItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PmetaItem(final Object c_service, final Object c_metaType, final Object c_value) {
		super();
		this.child_service = c_service;
		this.child_metaType = c_metaType;
		this.child_value = c_value;

	}

	private Object child_service;
	public final common.StringCatter getChild_service() {
		return (common.StringCatter) (child_service = common.Util.demand(child_service));
	}

	private Object child_metaType;
	public final common.StringCatter getChild_metaType() {
		return (common.StringCatter) (child_metaType = common.Util.demand(child_metaType));
	}

	private Object child_value;
	public final common.StringCatter getChild_value() {
		return (common.StringCatter) (child_value = common.Util.demand(child_value));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_service: return getChild_service();
			case i_metaType: return getChild_metaType();
			case i_value: return getChild_value();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_service: return child_service;
			case i_metaType: return child_metaType;
			case i_value: return child_value;

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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:products:metaItem erroneously claimed to forward");
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
		return "silver:support:monto:products:metaItem";
	}

	static void initProductionAttributeDefinitions() {
		// top.json = jsonObject([ pair("service", jsonString(service)), pair("type", jsonString(metaType)), pair("value", jsonString(value)) ])
		silver.support.monto.products.PmetaItem.synthesizedAttributes[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_MetaItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("service")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.childAsIsLazy(silver.support.monto.products.PmetaItem.i_service))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("type")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.childAsIsLazy(silver.support.monto.products.PmetaItem.i_metaType))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("value")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.childAsIsLazy(silver.support.monto.products.PmetaItem.i_value))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PmetaItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmetaItem> {

		@Override
		public PmetaItem invoke(final Object[] children, final Object[] annotations) {
			return new PmetaItem(children[0], children[1], children[2]);
		}
	};

}
