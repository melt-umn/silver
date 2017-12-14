
package silver.support.monto.products;

// top::MetaItem ::= service::String value::String 
public final class PmetaDegraded extends silver.support.monto.products.NMetaItem {

	public static final int i_service = 0;
	public static final int i_value = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_metaDegraded;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NMetaItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NMetaItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PmetaDegraded(final Object c_service, final Object c_value) {
		super();
		this.child_service = c_service;
		this.child_value = c_value;

	}

	private Object child_service;
	public final common.StringCatter getChild_service() {
		return (common.StringCatter) (child_service = common.Util.demand(child_service));
	}

	private Object child_value;
	public final common.StringCatter getChild_value() {
		return (common.StringCatter) (child_value = common.Util.demand(child_value));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_service: return getChild_service();
			case i_value: return getChild_value();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_service: return child_service;
			case i_value: return child_value;

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
		return ((silver.support.monto.products.NMetaItem)new silver.support.monto.products.PmetaItem(context.childAsIsLazy(silver.support.monto.products.PmetaDegraded.i_service), (new common.StringCatter("degraded")), context.childAsIsLazy(silver.support.monto.products.PmetaDegraded.i_value)));
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
		return "silver:support:monto:products:metaDegraded";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PmetaDegraded> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmetaDegraded> {

		@Override
		public PmetaDegraded invoke(final Object[] children, final Object[] annotations) {
			return new PmetaDegraded(children[0], children[1]);
		}
	};

}
