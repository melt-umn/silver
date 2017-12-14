
package silver.util.raw.treemap;

public final class Pupdate extends common.FunctionNode {

	public static final int i_key = 0;
	public static final int i_value = 1;
	public static final int i_mp = 2;


	public static final Class<?> childTypes[] = { Object.class,common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_raw_treemap_update;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pupdate(final Object c_key, final Object c_value, final Object c_mp) {
		this.child_key = c_key;
		this.child_value = c_value;
		this.child_mp = c_mp;

	}

	private Object child_key;
	public final Object getChild_key() {
		return (Object) (child_key = common.Util.demand(child_key));
	}

	private Object child_value;
	public final common.ConsCell getChild_value() {
		return (common.ConsCell) (child_value = common.Util.demand(child_value));
	}

	private Object child_mp;
	public final Object getChild_mp() {
		return (Object) (child_mp = common.Util.demand(child_mp));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_key: return getChild_key();
			case i_value: return getChild_value();
			case i_mp: return getChild_mp();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_key: return child_key;
			case i_value: return child_value;
			case i_mp: return child_mp;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:util:raw:treemap:update";
	}

	public static Object invoke(final Object c_key, final Object c_value, final Object c_mp) {
		try {
		return (Object)common.rawlib.RawTreeMap.update(((Object)common.Util.demand(c_key)), ((common.ConsCell)common.Util.demand(c_value)), (java.util.TreeMap<Object,common.ConsCell>)((Object)common.Util.demand(c_mp)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:raw:treemap:update", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pupdate.invoke(children[0], children[1], children[2]);
		}
	};
}