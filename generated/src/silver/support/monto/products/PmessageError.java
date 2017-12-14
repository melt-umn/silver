
package silver.support.monto.products;

// top::Error ::= msg::Message 
public final class PmessageError extends silver.support.monto.products.NError {

	public static final int i_msg = 0;


	public static final Class<?> childTypes[] = {silver.langutil.NMessage.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_messageError;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NError.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NError.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_msg] = new common.Lazy[silver.langutil.NMessage.num_inh_attrs];

	}

	public PmessageError(final Object c_msg) {
		super();
		this.child_msg = c_msg;

	}

	private Object child_msg;
	public final silver.langutil.NMessage getChild_msg() {
		return (silver.langutil.NMessage) (child_msg = common.Util.demand(child_msg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_msg: return getChild_msg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_msg: return child_msg;

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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:products:messageError erroneously claimed to forward");
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
		return "silver:support:monto:products:messageError";
	}

	static void initProductionAttributeDefinitions() {
		// top.json = jsonObject(obj)
		silver.support.monto.products.PmessageError.synthesizedAttributes[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_Error] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(context.localAsIsLazy(silver.support.monto.products.Init.obj__ON__silver_support_monto_products_messageError))); } };
		// severity = case msg.severity of 0 -> severityInfo() | 1 -> severityWarning() | _ -> severityError() end
		silver.support.monto.products.PmessageError.localAttributes[silver.support.monto.products.Init.severity__ON__silver_support_monto_products_messageError] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.support.monto.products.NErrorSeverity)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_254___fail_255 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.support.monto.products.NErrorSeverity)new silver.support.monto.products.PseverityError()); } };
return new common.PatternLazy<Integer, silver.support.monto.products.NErrorSeverity>() { public final silver.support.monto.products.NErrorSeverity eval(final common.DecoratedNode context, Integer scrutineeIter) {final Integer scrutinee = scrutineeIter; if(scrutinee == 0) { return (silver.support.monto.products.NErrorSeverity)((silver.support.monto.products.NErrorSeverity)new silver.support.monto.products.PseverityInfo()); }
else if(scrutinee == 1) { return (silver.support.monto.products.NErrorSeverity)((silver.support.monto.products.NErrorSeverity)new silver.support.monto.products.PseverityWarning()); }return ((silver.support.monto.products.NErrorSeverity)(__SV_LOCAL_254___fail_255.eval()));}}.eval(context, (Integer)((Integer)context.childDecorated(silver.support.monto.products.PmessageError.i_msg).synthesized(silver.langutil.Init.silver_langutil_severity__ON__silver_langutil_Message))); } }).eval()); } };
		// obj = [ pair("start_byte", jsonInteger(msg.where.index)), pair("end_byte", jsonInteger(msg.where.endIndex)), pair("message", jsonString(msg.output)), pair("severity", severity.json) ]
		silver.support.monto.products.PmessageError.localAttributes[silver.support.monto.products.Init.obj__ON__silver_support_monto_products_messageError] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("start_byte")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)silver.json.PjsonInteger.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((core.NLocation)context.childDecorated(silver.support.monto.products.PmessageError.i_msg).synthesized(silver.langutil.Init.silver_langutil_where__ON__silver_langutil_Message)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_index__ON__core_Location)); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("end_byte")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)silver.json.PjsonInteger.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((core.NLocation)context.childDecorated(silver.support.monto.products.PmessageError.i_msg).synthesized(silver.langutil.Init.silver_langutil_where__ON__silver_langutil_Message)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_endIndex__ON__core_Location)); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("message")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.childDecoratedSynthesizedLazy(silver.support.monto.products.PmessageError.i_msg, silver.langutil.Init.silver_langutil_output__ON__silver_langutil_Message))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("severity")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)context.localDecorated(silver.support.monto.products.Init.severity__ON__silver_support_monto_products_messageError).synthesized(silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ErrorSeverity)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PmessageError> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmessageError> {

		@Override
		public PmessageError invoke(final Object[] children, final Object[] annotations) {
			return new PmessageError(children[0]);
		}
	};

}
