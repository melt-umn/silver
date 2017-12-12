
package silver.support.monto.products;

// top::ProductValue ::= name::String json::Json 
public final class PproductValue extends silver.support.monto.products.NProductValue {

	public static final int i_name = 0;
	public static final int i_json = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.json.NJson.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_productValue;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NProductValue.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NProductValue.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_json] = new common.Lazy[silver.json.NJson.num_inh_attrs];

	}

	public PproductValue(final Object c_name, final Object c_json) {
		super();
		this.child_name = c_name;
		this.child_json = c_json;

	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_json;
	public final silver.json.NJson getChild_json() {
		return (silver.json.NJson) (child_json = common.Util.demand(child_json));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i_json: return getChild_json();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i_json: return child_json;

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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:products:productValue erroneously claimed to forward");
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
		return "silver:support:monto:products:productValue";
	}

	static void initProductionAttributeDefinitions() {
		// top.json = json
		silver.support.monto.products.PproductValue.synthesizedAttributes[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ProductValue] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.support.monto.products.PproductValue.i_json).undecorate(); } };
		// top.productName = name
		silver.support.monto.products.PproductValue.synthesizedAttributes[silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_ProductValue] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.support.monto.products.PproductValue.i_name)); } };

	}

	public static final common.NodeFactory<PproductValue> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductValue> {

		@Override
		public PproductValue invoke(final Object[] children, final Object[] annotations) {
			return new PproductValue(children[0], children[1]);
		}
	};

}
