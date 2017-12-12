
package silver.json;

// top::Json ::= str::String 
public final class PjsonString extends silver.json.NJson {

	public static final int i_str = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_json_jsonString;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.json.NJson.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.json.NJson.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PjsonString(final Object c_str) {
		super();
		this.child_str = c_str;

	}

	private Object child_str;
	public final common.StringCatter getChild_str() {
		return (common.StringCatter) (child_str = common.Util.demand(child_str));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_str: return getChild_str();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_str: return child_str;

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
		throw new common.exceptions.SilverInternalError("Production silver:json:jsonString erroneously claimed to forward");
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
		return "silver:json:jsonString";
	}

	static void initProductionAttributeDefinitions() {
		// escapeChar = \ ch::Integer  -> case ch of 0 -> "\\u0000" | 1 -> "\\u0001" | 2 -> "\\u0002" | 3 -> "\\u0003" | 4 -> "\\u0004" | 5 -> "\\u0005" | 6 -> "\\u0006" | 7 -> "\\u0007" | 8 -> "\\b" | 9 -> "\\t" | 10 -> "\\n" | 11 -> "\\u000b" | 12 -> "\\f" | 13 -> "\\r" | 14 -> "\\u000e" | 15 -> "\\u000f" | 16 -> "\\u0010" | 17 -> "\\u0011" | 18 -> "\\u0012" | 19 -> "\\u0013" | 20 -> "\\u0014" | 21 -> "\\u0015" | 22 -> "\\u0016" | 23 -> "\\u0017" | 24 -> "\\u0018" | 25 -> "\\u0019" | 26 -> "\\u001a" | 27 -> "\\u001b" | 28 -> "\\u001c" | 29 -> "\\u001d" | 30 -> "\\u001e" | 31 -> "\\u001f" | 34 -> "\\\"" | 92 -> "\\\\" | ch -> charsToString([ ch ]) end
		silver.json.PjsonString.localAttributes[silver.json.Init.escapeChar__ON__silver_json_jsonString] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<common.StringCatter>() {
  public final common.StringCatter invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_181_ch = args[0];

    return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_183___fail_184 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_182_ch = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)common.Util.demand(__SV_LAMBDA_PARAM_181_ch)); } };
return ((common.StringCatter)core.PcharsToString.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(__SV_LOCAL_182_ch, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }).eval()); } };
return new common.PatternLazy<Integer, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, Integer scrutineeIter) {final Integer scrutinee = scrutineeIter; if(scrutinee == 0) { return (common.StringCatter)(new common.StringCatter("\\u0000")); }
else if(scrutinee == 1) { return (common.StringCatter)(new common.StringCatter("\\u0001")); }
else if(scrutinee == 10) { return (common.StringCatter)(new common.StringCatter("\\n")); }
else if(scrutinee == 11) { return (common.StringCatter)(new common.StringCatter("\\u000b")); }
else if(scrutinee == 12) { return (common.StringCatter)(new common.StringCatter("\\f")); }
else if(scrutinee == 13) { return (common.StringCatter)(new common.StringCatter("\\r")); }
else if(scrutinee == 14) { return (common.StringCatter)(new common.StringCatter("\\u000e")); }
else if(scrutinee == 15) { return (common.StringCatter)(new common.StringCatter("\\u000f")); }
else if(scrutinee == 16) { return (common.StringCatter)(new common.StringCatter("\\u0010")); }
else if(scrutinee == 17) { return (common.StringCatter)(new common.StringCatter("\\u0011")); }
else if(scrutinee == 18) { return (common.StringCatter)(new common.StringCatter("\\u0012")); }
else if(scrutinee == 19) { return (common.StringCatter)(new common.StringCatter("\\u0013")); }
else if(scrutinee == 2) { return (common.StringCatter)(new common.StringCatter("\\u0002")); }
else if(scrutinee == 20) { return (common.StringCatter)(new common.StringCatter("\\u0014")); }
else if(scrutinee == 21) { return (common.StringCatter)(new common.StringCatter("\\u0015")); }
else if(scrutinee == 22) { return (common.StringCatter)(new common.StringCatter("\\u0016")); }
else if(scrutinee == 23) { return (common.StringCatter)(new common.StringCatter("\\u0017")); }
else if(scrutinee == 24) { return (common.StringCatter)(new common.StringCatter("\\u0018")); }
else if(scrutinee == 25) { return (common.StringCatter)(new common.StringCatter("\\u0019")); }
else if(scrutinee == 26) { return (common.StringCatter)(new common.StringCatter("\\u001a")); }
else if(scrutinee == 27) { return (common.StringCatter)(new common.StringCatter("\\u001b")); }
else if(scrutinee == 28) { return (common.StringCatter)(new common.StringCatter("\\u001c")); }
else if(scrutinee == 29) { return (common.StringCatter)(new common.StringCatter("\\u001d")); }
else if(scrutinee == 3) { return (common.StringCatter)(new common.StringCatter("\\u0003")); }
else if(scrutinee == 30) { return (common.StringCatter)(new common.StringCatter("\\u001e")); }
else if(scrutinee == 31) { return (common.StringCatter)(new common.StringCatter("\\u001f")); }
else if(scrutinee == 34) { return (common.StringCatter)(new common.StringCatter("\\\"")); }
else if(scrutinee == 4) { return (common.StringCatter)(new common.StringCatter("\\u0004")); }
else if(scrutinee == 5) { return (common.StringCatter)(new common.StringCatter("\\u0005")); }
else if(scrutinee == 6) { return (common.StringCatter)(new common.StringCatter("\\u0006")); }
else if(scrutinee == 7) { return (common.StringCatter)(new common.StringCatter("\\u0007")); }
else if(scrutinee == 8) { return (common.StringCatter)(new common.StringCatter("\\b")); }
else if(scrutinee == 9) { return (common.StringCatter)(new common.StringCatter("\\t")); }
else if(scrutinee == 92) { return (common.StringCatter)(new common.StringCatter("\\\\")); }return ((common.StringCatter)(__SV_LOCAL_183___fail_184.eval()));}}.eval(context, (Integer)((Integer)common.Util.demand(__SV_LAMBDA_PARAM_181_ch))); } }).eval());
  }
}); } };
		// top.jsonString = "\"" ++ implode("", map(escapeChar, stringToChars(str))) ++ "\""
		silver.json.PjsonString.synthesizedAttributes[silver.json.Init.silver_json_jsonString__ON__silver_json_Json] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(context.localAsIsLazy(silver.json.Init.escapeChar__ON__silver_json_jsonString), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PstringToChars.invoke(context.childAsIsLazy(silver.json.PjsonString.i_str))); } })); } })), (common.StringCatter)(new common.StringCatter("\"")))); } };

	}

	public static final common.NodeFactory<PjsonString> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PjsonString> {

		@Override
		public PjsonString invoke(final Object[] children, final Object[] annotations) {
			return new PjsonString(children[0]);
		}
	};

}
