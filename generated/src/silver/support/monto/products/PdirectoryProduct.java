
package silver.support.monto.products;

// top::ProductValue ::= entries::[DirectoryEntry] 
public final class PdirectoryProduct extends silver.support.monto.products.NProductValue {

	public static final int i_entries = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_directoryProduct;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NProductValue.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NProductValue.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PdirectoryProduct(final Object c_entries) {
		super();
		this.child_entries = c_entries;

	}

	private Object child_entries;
	public final common.ConsCell getChild_entries() {
		return (common.ConsCell) (child_entries = common.Util.demand(child_entries));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_entries: return getChild_entries();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_entries: return child_entries;

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
		return ((silver.support.monto.products.NProductValue)new silver.support.monto.products.PproductValue((new common.StringCatter("directory")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonArray(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_DirectoryEntry, context), context.childAsIsLazy(silver.support.monto.products.PdirectoryProduct.i_entries))); } })); } }));
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
		return "silver:support:monto:products:directoryProduct";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PdirectoryProduct> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdirectoryProduct> {

		@Override
		public PdirectoryProduct invoke(final Object[] children, final Object[] annotations) {
			return new PdirectoryProduct(children[0]);
		}
	};

}
