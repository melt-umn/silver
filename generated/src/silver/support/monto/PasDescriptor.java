
package silver.support.monto;

public final class PasDescriptor extends common.FunctionNode {

	public static final int i_pi = 0;


	public static final Class<?> childTypes[] = { silver.support.monto.products.NProductIdentifier.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_asDescriptor;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_pi] = new common.Lazy[silver.support.monto.products.NProductIdentifier.num_inh_attrs];

	}

	public PasDescriptor(final Object c_pi) {
		this.child_pi = c_pi;

	}

	private Object child_pi;
	public final silver.support.monto.products.NProductIdentifier getChild_pi() {
		return (silver.support.monto.products.NProductIdentifier) (child_pi = common.Util.demand(child_pi));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pi: return getChild_pi();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pi: return child_pi;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:support:monto:asDescriptor";
	}

	public static silver.support.monto.products.NProductDescriptor invoke(final Object c_pi) {
		try {
		final common.DecoratedNode context = new PasDescriptor(c_pi).decorate();
		//productDescriptor(pi.productName, pi.productLang)
		return (silver.support.monto.products.NProductDescriptor)(((silver.support.monto.products.NProductDescriptor)new silver.support.monto.products.PproductDescriptor(context.childDecoratedSynthesizedLazy(silver.support.monto.PasDescriptor.i_pi, silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_ProductIdentifier), context.childDecoratedSynthesizedLazy(silver.support.monto.PasDescriptor.i_pi, silver.support.monto.products.Init.silver_support_monto_products_productLang__ON__silver_support_monto_products_ProductIdentifier))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:support:monto:asDescriptor", t);
		}
	}

	public static final common.NodeFactory<silver.support.monto.products.NProductDescriptor> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.support.monto.products.NProductDescriptor> {
		@Override
		public silver.support.monto.products.NProductDescriptor invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PasDescriptor.invoke(children[0]);
		}
	};
}