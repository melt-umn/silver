
package silver.support.monto.products;

// top::HighlightToken ::= startByte::Integer endByte::Integer color::Color 
public final class PhighlightToken extends silver.support.monto.products.NHighlightToken {

	public static final int i_startByte = 0;
	public static final int i_endByte = 1;
	public static final int i_color = 2;


	public static final Class<?> childTypes[] = {Integer.class,Integer.class,silver.support.monto.products.NColor.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_highlightToken;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NHighlightToken.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NHighlightToken.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_color] = new common.Lazy[silver.support.monto.products.NColor.num_inh_attrs];

	}

	public PhighlightToken(final Object c_startByte, final Object c_endByte, final Object c_color) {
		super();
		this.child_startByte = c_startByte;
		this.child_endByte = c_endByte;
		this.child_color = c_color;

	}

	private Object child_startByte;
	public final Integer getChild_startByte() {
		return (Integer) (child_startByte = common.Util.demand(child_startByte));
	}

	private Object child_endByte;
	public final Integer getChild_endByte() {
		return (Integer) (child_endByte = common.Util.demand(child_endByte));
	}

	private Object child_color;
	public final silver.support.monto.products.NColor getChild_color() {
		return (silver.support.monto.products.NColor) (child_color = common.Util.demand(child_color));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_startByte: return getChild_startByte();
			case i_endByte: return getChild_endByte();
			case i_color: return getChild_color();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_startByte: return child_startByte;
			case i_endByte: return child_endByte;
			case i_color: return child_color;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:products:highlightToken erroneously claimed to forward");
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
		return "silver:support:monto:products:highlightToken";
	}

	static void initProductionAttributeDefinitions() {
		// top.json = jsonObject(obj)
		silver.support.monto.products.PhighlightToken.synthesizedAttributes[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_HighlightToken] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(context.localAsIsLazy(silver.support.monto.products.Init.obj__ON__silver_support_monto_products_highlightToken))); } };
		// obj = [ pair("start_byte", jsonInteger(startByte)), pair("end_byte", jsonInteger(endByte)), pair("color", color.json) ]
		silver.support.monto.products.PhighlightToken.localAttributes[silver.support.monto.products.Init.obj__ON__silver_support_monto_products_highlightToken] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("start_byte")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)silver.json.PjsonInteger.invoke(context.childAsIsLazy(silver.support.monto.products.PhighlightToken.i_startByte))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("end_byte")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)silver.json.PjsonInteger.invoke(context.childAsIsLazy(silver.support.monto.products.PhighlightToken.i_endByte))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("color")), context.childDecoratedSynthesizedLazy(silver.support.monto.products.PhighlightToken.i_color, silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_Color))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PhighlightToken> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PhighlightToken> {

		@Override
		public PhighlightToken invoke(final Object[] children, final Object[] annotations) {
			return new PhighlightToken(children[0], children[1], children[2]);
		}
	};

}
