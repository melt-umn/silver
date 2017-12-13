
package silver.support.monto.products;

// top::ErrorSeverity ::= 
public final class PseverityInfo extends silver.support.monto.products.NErrorSeverity {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_severityInfo;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NErrorSeverity.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NErrorSeverity.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PseverityInfo() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:products:severityInfo erroneously claimed to forward");
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
		return "silver:support:monto:products:severityInfo";
	}

	static void initProductionAttributeDefinitions() {
		// top.json = jsonString("info")
		silver.support.monto.products.PseverityInfo.synthesizedAttributes[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ErrorSeverity] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString((new common.StringCatter("info")))); } };

	}

	public static final common.NodeFactory<PseverityInfo> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PseverityInfo> {

		@Override
		public PseverityInfo invoke(final Object[] children, final Object[] annotations) {
			return new PseverityInfo();
		}
	};

}
