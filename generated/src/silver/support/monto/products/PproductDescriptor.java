
package silver.support.monto.products;

// top::ProductDescriptor ::= name::String language::String 
public final class PproductDescriptor extends silver.support.monto.products.NProductDescriptor {

	public static final int i_name = 0;
	public static final int i_language = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_productDescriptor;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NProductDescriptor.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NProductDescriptor.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PproductDescriptor(final Object c_name, final Object c_language) {
		super();
		this.child_name = c_name;
		this.child_language = c_language;

	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_language;
	public final common.StringCatter getChild_language() {
		return (common.StringCatter) (child_language = common.Util.demand(child_language));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i_language: return getChild_language();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i_language: return child_language;

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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:products:productDescriptor erroneously claimed to forward");
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
		return "silver:support:monto:products:productDescriptor";
	}

	static void initProductionAttributeDefinitions() {
		// top.productName = name
		silver.support.monto.products.PproductDescriptor.synthesizedAttributes[silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_ProductDescriptor] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.support.monto.products.PproductDescriptor.i_name)); } };
		// top.productLang = language
		silver.support.monto.products.PproductDescriptor.synthesizedAttributes[silver.support.monto.products.Init.silver_support_monto_products_productLang__ON__silver_support_monto_products_ProductDescriptor] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.support.monto.products.PproductDescriptor.i_language)); } };
		// top.json = jsonObject([ pair("name", jsonString(top.productName)), pair("language", jsonString(top.productLang)) ])
		silver.support.monto.products.PproductDescriptor.synthesizedAttributes[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ProductDescriptor] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("name")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.contextSynthesizedLazy(silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_ProductDescriptor))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("language")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.contextSynthesizedLazy(silver.support.monto.products.Init.silver_support_monto_products_productLang__ON__silver_support_monto_products_ProductDescriptor))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PproductDescriptor> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductDescriptor> {

		@Override
		public PproductDescriptor invoke(final Object[] children, final Object[] annotations) {
			return new PproductDescriptor(children[0], children[1]);
		}
	};

}
