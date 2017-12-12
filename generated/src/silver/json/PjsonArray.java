
package silver.json;

// top::Json ::= vals::[Json] 
public final class PjsonArray extends silver.json.NJson {

	public static final int i_vals = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_json_jsonArray;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.json.NJson.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.json.NJson.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PjsonArray(final Object c_vals) {
		super();
		this.child_vals = c_vals;

	}

	private Object child_vals;
	public final common.ConsCell getChild_vals() {
		return (common.ConsCell) (child_vals = common.Util.demand(child_vals));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_vals: return getChild_vals();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_vals: return child_vals;

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
		throw new common.exceptions.SilverInternalError("Production silver:json:jsonArray erroneously claimed to forward");
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
		return "silver:json:jsonArray";
	}

	static void initProductionAttributeDefinitions() {
		// strs = map(\ j::Json  -> j.jsonString, vals)
		silver.json.PjsonArray.localAttributes[silver.json.Init.strs__ON__silver_json_jsonArray] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke((new common.NodeFactory<common.StringCatter>() {
  public final common.StringCatter invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_185_j = args[0];

    return ((common.StringCatter)((silver.json.NJson)common.Util.demand(__SV_LAMBDA_PARAM_185_j)).decorate(context, (common.Lazy[])null).synthesized(silver.json.Init.silver_json_jsonString__ON__silver_json_Json));
  }
}), context.childAsIsLazy(silver.json.PjsonArray.i_vals))); } };
		// top.jsonString = "[" ++ implode(",", strs) ++ "]"
		silver.json.PjsonArray.synthesizedAttributes[silver.json.Init.silver_json_jsonString__ON__silver_json_Json] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(",")), context.localAsIsLazy(silver.json.Init.strs__ON__silver_json_jsonArray))), (common.StringCatter)(new common.StringCatter("]")))); } };

	}

	public static final common.NodeFactory<PjsonArray> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PjsonArray> {

		@Override
		public PjsonArray invoke(final Object[] children, final Object[] annotations) {
			return new PjsonArray(children[0]);
		}
	};

}
