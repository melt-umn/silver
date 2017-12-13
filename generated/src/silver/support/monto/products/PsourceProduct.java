
package silver.support.monto.products;

// top::ProductValue ::= source::String 
public final class PsourceProduct extends silver.support.monto.products.NProductValue {

	public static final int i_source = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_sourceProduct;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NProductValue.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NProductValue.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PsourceProduct(final Object c_source) {
		super();
		this.child_source = c_source;

	}

	private Object child_source;
	public final common.StringCatter getChild_source() {
		return (common.StringCatter) (child_source = common.Util.demand(child_source));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_source: return getChild_source();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_source: return child_source;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.support.monto.products.NProductValue)new silver.support.monto.products.PproductValue((new common.StringCatter("source")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.childAsIsLazy(silver.support.monto.products.PsourceProduct.i_source))); } }));
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
		return "silver:support:monto:products:sourceProduct";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PsourceProduct> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsourceProduct> {

		@Override
		public PsourceProduct invoke(final Object[] children, final Object[] annotations) {
			return new PsourceProduct(children[0]);
		}
	};

}
