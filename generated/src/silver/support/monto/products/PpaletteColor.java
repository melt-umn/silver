
package silver.support.monto.products;

// top::Color ::= idx::Integer 
public final class PpaletteColor extends silver.support.monto.products.NColor {

	public static final int i_idx = 0;


	public static final Class<?> childTypes[] = {Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_paletteColor;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NColor.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NColor.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PpaletteColor(final Object c_idx) {
		super();
		this.child_idx = c_idx;

	}

	private Object child_idx;
	public final Integer getChild_idx() {
		return (Integer) (child_idx = common.Util.demand(child_idx));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_idx: return getChild_idx();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_idx: return child_idx;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:products:paletteColor erroneously claimed to forward");
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
		return "silver:support:monto:products:paletteColor";
	}

	static void initProductionAttributeDefinitions() {
		// top.json = jsonObject([ pair("type", jsonString("palette")), pair("value", jsonInteger(idx)) ])
		silver.support.monto.products.PpaletteColor.synthesizedAttributes[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_Color] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("type")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString((new common.StringCatter("palette")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("value")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)silver.json.PjsonInteger.invoke(context.childAsIsLazy(silver.support.monto.products.PpaletteColor.i_idx))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PpaletteColor> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpaletteColor> {

		@Override
		public PpaletteColor invoke(final Object[] children, final Object[] annotations) {
			return new PpaletteColor(children[0]);
		}
	};

}
