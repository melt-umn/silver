
package silver.json;

// top::Json ::= float::Float 
public final class PjsonFloat extends silver.json.NJson {

	public static final int i_float = 0;


	public static final Class<?> childTypes[] = {Float.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_json_jsonFloat;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.json.NJson.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.json.NJson.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PjsonFloat(final Object c_float) {
		super();
		this.child_float = c_float;

	}

	private Object child_float;
	public final Float getChild_float() {
		return (Float) (child_float = common.Util.demand(child_float));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_float: return getChild_float();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_float: return child_float;

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
		throw new common.exceptions.SilverInternalError("Production silver:json:jsonFloat erroneously claimed to forward");
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
		return "silver:json:jsonFloat";
	}

	static void initProductionAttributeDefinitions() {
		// top.jsonString = toString(float)
		silver.json.PjsonFloat.synthesizedAttributes[silver.json.Init.silver_json_jsonString__ON__silver_json_Json] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Float)context.childAsIs(silver.json.PjsonFloat.i_float)))); } };

	}

	public static final common.NodeFactory<PjsonFloat> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PjsonFloat> {

		@Override
		public PjsonFloat invoke(final Object[] children, final Object[] annotations) {
			return new PjsonFloat(children[0]);
		}
	};

}
