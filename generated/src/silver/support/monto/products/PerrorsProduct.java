
package silver.support.monto.products;

// top::ProductValue ::= errs::[Error] 
public final class PerrorsProduct extends silver.support.monto.products.NProductValue {

	public static final int i_errs = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_errorsProduct;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NProductValue.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NProductValue.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PerrorsProduct(final Object c_errs) {
		super();
		this.child_errs = c_errs;

	}

	private Object child_errs;
	public final common.ConsCell getChild_errs() {
		return (common.ConsCell) (child_errs = common.Util.demand(child_errs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_errs: return getChild_errs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_errs: return child_errs;

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
		return ((silver.support.monto.products.NProductValue)new silver.support.monto.products.PproductValue((new common.StringCatter("errors")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonArray(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_Error, context), context.childAsIsLazy(silver.support.monto.products.PerrorsProduct.i_errs))); } })); } }));
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
		return "silver:support:monto:products:errorsProduct";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PerrorsProduct> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PerrorsProduct> {

		@Override
		public PerrorsProduct invoke(final Object[] children, final Object[] annotations) {
			return new PerrorsProduct(children[0]);
		}
	};

}
