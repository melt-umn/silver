
package silver.support.monto.products;

public final class PproductDescriptorEq extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_r = 1;


	public static final Class<?> childTypes[] = { silver.support.monto.products.NProductDescriptor.class,silver.support.monto.products.NProductDescriptor.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_productDescriptorEq;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[silver.support.monto.products.NProductDescriptor.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.support.monto.products.NProductDescriptor.num_inh_attrs];

	}

	public PproductDescriptorEq(final Object c_l, final Object c_r) {
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_l;
	public final silver.support.monto.products.NProductDescriptor getChild_l() {
		return (silver.support.monto.products.NProductDescriptor) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final silver.support.monto.products.NProductDescriptor getChild_r() {
		return (silver.support.monto.products.NProductDescriptor) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_r: return child_r;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:support:monto:products:productDescriptorEq";
	}

	public static Boolean invoke(final Object c_l, final Object c_r) {
		try {
		final common.DecoratedNode context = new PproductDescriptorEq(c_l, c_r).decorate();
		//l.productName == r.productName && l.productLang == r.productLang
		return (Boolean)((((common.StringCatter)context.childDecorated(silver.support.monto.products.PproductDescriptorEq.i_l).synthesized(silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_ProductDescriptor)).equals(((common.StringCatter)context.childDecorated(silver.support.monto.products.PproductDescriptorEq.i_r).synthesized(silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_ProductDescriptor))) && ((common.StringCatter)context.childDecorated(silver.support.monto.products.PproductDescriptorEq.i_l).synthesized(silver.support.monto.products.Init.silver_support_monto_products_productLang__ON__silver_support_monto_products_ProductDescriptor)).equals(((common.StringCatter)context.childDecorated(silver.support.monto.products.PproductDescriptorEq.i_r).synthesized(silver.support.monto.products.Init.silver_support_monto_products_productLang__ON__silver_support_monto_products_ProductDescriptor)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:support:monto:products:productDescriptorEq", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PproductDescriptorEq.invoke(children[0], children[1]);
		}
	};
}