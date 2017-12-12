
package silver.json;

// top::Json ::= boolean::Boolean 
public final class PjsonBoolean extends silver.json.NJson {

	public static final int i_boolean = 0;


	public static final Class<?> childTypes[] = {Boolean.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_json_jsonBoolean;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.json.NJson.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.json.NJson.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PjsonBoolean(final Object c_boolean) {
		super();
		this.child_boolean = c_boolean;

	}

	private Object child_boolean;
	public final Boolean getChild_boolean() {
		return (Boolean) (child_boolean = common.Util.demand(child_boolean));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_boolean: return getChild_boolean();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_boolean: return child_boolean;

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
		throw new common.exceptions.SilverInternalError("Production silver:json:jsonBoolean erroneously claimed to forward");
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
		return "silver:json:jsonBoolean";
	}

	static void initProductionAttributeDefinitions() {
		// top.jsonString = toString(boolean)
		silver.json.PjsonBoolean.synthesizedAttributes[silver.json.Init.silver_json_jsonString__ON__silver_json_Json] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Boolean)context.childAsIs(silver.json.PjsonBoolean.i_boolean)))); } };

	}

	public static final common.NodeFactory<PjsonBoolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PjsonBoolean> {

		@Override
		public PjsonBoolean invoke(final Object[] children, final Object[] annotations) {
			return new PjsonBoolean(children[0]);
		}
	};

}
