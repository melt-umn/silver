
package silver.support.monto.products;

// top::Error ::= startByte::Integer endByte::Integer message::String severity::ErrorSeverity 
public final class PbyteRangeError extends silver.support.monto.products.NError {

	public static final int i_startByte = 0;
	public static final int i_endByte = 1;
	public static final int i_message = 2;
	public static final int i_severity = 3;


	public static final Class<?> childTypes[] = {Integer.class,Integer.class,common.StringCatter.class,silver.support.monto.products.NErrorSeverity.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_byteRangeError;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NError.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NError.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_severity] = new common.Lazy[silver.support.monto.products.NErrorSeverity.num_inh_attrs];

	}

	public PbyteRangeError(final Object c_startByte, final Object c_endByte, final Object c_message, final Object c_severity) {
		super();
		this.child_startByte = c_startByte;
		this.child_endByte = c_endByte;
		this.child_message = c_message;
		this.child_severity = c_severity;

	}

	private Object child_startByte;
	public final Integer getChild_startByte() {
		return (Integer) (child_startByte = common.Util.demand(child_startByte));
	}

	private Object child_endByte;
	public final Integer getChild_endByte() {
		return (Integer) (child_endByte = common.Util.demand(child_endByte));
	}

	private Object child_message;
	public final common.StringCatter getChild_message() {
		return (common.StringCatter) (child_message = common.Util.demand(child_message));
	}

	private Object child_severity;
	public final silver.support.monto.products.NErrorSeverity getChild_severity() {
		return (silver.support.monto.products.NErrorSeverity) (child_severity = common.Util.demand(child_severity));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_startByte: return getChild_startByte();
			case i_endByte: return getChild_endByte();
			case i_message: return getChild_message();
			case i_severity: return getChild_severity();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_startByte: return child_startByte;
			case i_endByte: return child_endByte;
			case i_message: return child_message;
			case i_severity: return child_severity;

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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:products:byteRangeError erroneously claimed to forward");
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
		return "silver:support:monto:products:byteRangeError";
	}

	static void initProductionAttributeDefinitions() {
		// top.json = jsonObject(obj)
		silver.support.monto.products.PbyteRangeError.synthesizedAttributes[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_Error] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(context.localAsIsLazy(silver.support.monto.products.Init.obj__ON__silver_support_monto_products_byteRangeError))); } };
		// obj = [ pair("start_byte", jsonInteger(startByte)), pair("end_byte", jsonInteger(endByte)), pair("message", jsonString(message)), pair("severity", severity.json) ]
		silver.support.monto.products.PbyteRangeError.localAttributes[silver.support.monto.products.Init.obj__ON__silver_support_monto_products_byteRangeError] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("start_byte")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)silver.json.PjsonInteger.invoke(context.childAsIsLazy(silver.support.monto.products.PbyteRangeError.i_startByte))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("end_byte")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)silver.json.PjsonInteger.invoke(context.childAsIsLazy(silver.support.monto.products.PbyteRangeError.i_endByte))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("message")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.childAsIsLazy(silver.support.monto.products.PbyteRangeError.i_message))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("severity")), context.childDecoratedSynthesizedLazy(silver.support.monto.products.PbyteRangeError.i_severity, silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ErrorSeverity))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PbyteRangeError> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbyteRangeError> {

		@Override
		public PbyteRangeError invoke(final Object[] children, final Object[] annotations) {
			return new PbyteRangeError(children[0], children[1], children[2], children[3]);
		}
	};

}
