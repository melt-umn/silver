
package silver.util.raw.treeset;

public final class Pcontains extends common.FunctionNode {

	public static final int i_e = 0;
	public static final int i_set = 1;


	public static final Class<?> childTypes[] = { Object.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_raw_treeset_contains;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pcontains(final Object c_e, final Object c_set) {
		this.child_e = c_e;
		this.child_set = c_set;

	}

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}

	private Object child_set;
	public final Object getChild_set() {
		return (Object) (child_set = common.Util.demand(child_set));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i_set: return getChild_set();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
			case i_set: return child_set;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:util:raw:treeset:contains";
	}

	public static Boolean invoke(final Object c_e, final Object c_set) {
		try {
		return (Boolean)common.rawlib.RawTreeSet.contains(((Object)common.Util.demand(c_e)),(java.util.TreeSet<Object>)((Object)common.Util.demand(c_set)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:raw:treeset:contains", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pcontains.invoke(children[0], children[1]);
		}
	};
}