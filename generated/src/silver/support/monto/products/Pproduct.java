
package silver.support.monto.products;

// top::Product ::= value::ProductValue metas::[MetaItem] language::String path::String 
public final class Pproduct extends silver.support.monto.products.NProduct {

	public static final int i_value = 0;
	public static final int i_metas = 1;
	public static final int i_language = 2;
	public static final int i_path = 3;


	public static final Class<?> childTypes[] = {silver.support.monto.products.NProductValue.class,common.DecoratedNode.class,common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_product;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NProduct.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NProduct.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_value] = new common.Lazy[silver.support.monto.products.NProductValue.num_inh_attrs];

	}

	public Pproduct(final Object c_value, final Object c_metas, final Object c_language, final Object c_path) {
		super();
		this.child_value = c_value;
		this.child_metas = c_metas;
		this.child_language = c_language;
		this.child_path = c_path;

	}

	private Object child_value;
	public final silver.support.monto.products.NProductValue getChild_value() {
		return (silver.support.monto.products.NProductValue) (child_value = common.Util.demand(child_value));
	}

	private Object child_metas;
	public final common.ConsCell getChild_metas() {
		return (common.ConsCell) (child_metas = common.Util.demand(child_metas));
	}

	private Object child_language;
	public final common.StringCatter getChild_language() {
		return (common.StringCatter) (child_language = common.Util.demand(child_language));
	}

	private Object child_path;
	public final common.StringCatter getChild_path() {
		return (common.StringCatter) (child_path = common.Util.demand(child_path));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_value: return getChild_value();
			case i_metas: return getChild_metas();
			case i_language: return getChild_language();
			case i_path: return getChild_path();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_value: return child_value;
			case i_metas: return child_metas;
			case i_language: return child_language;
			case i_path: return child_path;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:products:product erroneously claimed to forward");
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
		return "silver:support:monto:products:product";
	}

	static void initProductionAttributeDefinitions() {
		// top.productName = top.productValue.productName
		silver.support.monto.products.Pproduct.synthesizedAttributes[silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_Product] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.support.monto.products.NProductValue)context.synthesized(silver.support.monto.products.Init.silver_support_monto_products_productValue__ON__silver_support_monto_products_Product)).decorate(context, (common.Lazy[])null).synthesized(silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_ProductValue)); } };
		// top.productLang = language
		silver.support.monto.products.Pproduct.synthesizedAttributes[silver.support.monto.products.Init.silver_support_monto_products_productLang__ON__silver_support_monto_products_Product] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.support.monto.products.Pproduct.i_language)); } };
		// top.productPath = path
		silver.support.monto.products.Pproduct.synthesizedAttributes[silver.support.monto.products.Init.silver_support_monto_products_productPath__ON__silver_support_monto_products_Product] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.support.monto.products.Pproduct.i_path)); } };
		// top.productValue = value
		silver.support.monto.products.Pproduct.synthesizedAttributes[silver.support.monto.products.Init.silver_support_monto_products_productValue__ON__silver_support_monto_products_Product] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.support.monto.products.Pproduct.i_value).undecorate(); } };
		// top.json = jsonObject([ pair("contents", value.json), pair("language", jsonString(top.productLang)), pair("name", jsonString(top.productName)), pair("meta", jsonArray(map((.json), metas))), pair("path", jsonString(top.productPath)) ])
		silver.support.monto.products.Pproduct.synthesizedAttributes[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_Product] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("contents")), context.childDecoratedSynthesizedLazy(silver.support.monto.products.Pproduct.i_value, silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ProductValue))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("language")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.contextSynthesizedLazy(silver.support.monto.products.Init.silver_support_monto_products_productLang__ON__silver_support_monto_products_Product))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("name")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.contextSynthesizedLazy(silver.support.monto.products.Init.silver_support_monto_products_productName__ON__silver_support_monto_products_Product))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("meta")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonArray(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_MetaItem, context), context.childAsIsLazy(silver.support.monto.products.Pproduct.i_metas))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("path")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.contextSynthesizedLazy(silver.support.monto.products.Init.silver_support_monto_products_productPath__ON__silver_support_monto_products_Product))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } };

	}

	public static final common.NodeFactory<Pproduct> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pproduct> {

		@Override
		public Pproduct invoke(final Object[] children, final Object[] annotations) {
			return new Pproduct(children[0], children[1], children[2], children[3]);
		}
	};

}
